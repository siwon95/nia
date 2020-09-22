package egovframework.injeinc.boffice.ex.mainImage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.cmm.ComDefaultCodeVO;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.EgovFileMngUtil;
import egovframework.cmm.service.FileVO;
import egovframework.injeinc.boffice.ex.mainImage.service.MainImageSvc;
import egovframework.injeinc.boffice.ex.mainImage.vo.MainImage;
import egovframework.injeinc.boffice.ex.mainImage.vo.MainImageVO;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class MainImageCtr {

	@Autowired
	private CodeSvc codeSvc;

	@Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    /** Message ID Generation */
    @Resource(name="mainImageIdGnrService")
    private EgovIdGnrService mainImageIdGnrService;

    @Resource(name = "mainImageService")
    private MainImageSvc mainImageService;

    @Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping("/boffice/ex/mainImage/mainImageList.do")
	public String selectMainImageList(@ModelAttribute("mainImageVO") MainImageVO mainImageVO,
                                       ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mainImageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mainImageVO.getPageUnit());
		paginationInfo.setPageSize(mainImageVO.getPageSize());

		mainImageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mainImageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mainImageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		mainImageVO.setMainImageList(mainImageService.selectMainImageList(mainImageVO));
        int totCnt = mainImageService.selectLoginScrinImageListTotCnt(mainImageVO);
		paginationInfo.setTotalRecordCount(totCnt);

        HashMap<String, String> param = new HashMap<String, String>();

		param.put("codeAxuse", "28000000");

		HashMap<String, Object> serviceMap = codeSvc.retrieveCommonCode(param);

		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		if(model != null){
			model.addAttribute("mainImageList", mainImageVO.getMainImageList());
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("rowDataList", rowDataList);
			model.addAttribute("message", Message.getMessage("300.message"));
		}

        return "injeinc/boffice/ex/mainImage/main_image_list";
	}

	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/boffice/ex/mainImage/mainImage.do")
	public String selectMainImage(@RequestParam("imageId") String imageId,
                                  @ModelAttribute("mainImageVO") MainImageVO mainImageVO,
                                   ModelMap model) throws Exception {

    	mainImageVO.setImageId(imageId);
    	MainImageVO mainImage = mainImageService.retrieveMainImage(mainImageVO);

    	if (!"Y".equals(mainImage.getOrdtmNtceYn())) {
    		// 상시게시가 아닌경우
	    	String sNtceBgnde = mainImage.getNtceBgnde();
	        String sNtceEndde = mainImage.getNtceEndde();

	        mainImage.setNtceBgndeHH(sNtceBgnde.substring(8, 10));
	        mainImage.setNtceBgndeMM(sNtceBgnde.substring(10, 12));

	        mainImage.setNtceEnddeHH(sNtceEndde.substring(8, 10));
	        mainImage.setNtceEnddeMM(sNtceEndde.substring(10, 12));
    	}
    	HashMap<String, String> param = new HashMap<String, String>();
    	param.put("codeAxuse", "28000000");

		HashMap<String, Object> serviceMap = codeSvc.retrieveCommonCode(param);

		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		if(model != null){
			model.addAttribute("mainImage", mainImage);
			model.addAttribute("message", Message.getMessage("300.message"));
			model.addAttribute("rowDataList", rowDataList);
			//게시시작일자(시)
			model.addAttribute("ntceBgndeHH", (List)getTimeHH());
			//게시시작일자(분)
			model.addAttribute("ntceBgndeMM", (List)getTimeMM());
			//게시종료일자(시)
			model.addAttribute("ntceEnddeHH", (List)getTimeHH());
			//게시정료일자(분)
			model.addAttribute("ntceEnddeMM", (List)getTimeMM());
		}
    	return "injeinc/boffice/ex/mainImage/main_image_updt";
	}

	/**
	 * 메인인화면이미지 등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/boffice/ex/mainImage/mainImageForm.do")
	public String insertViewMainImage(@ModelAttribute("mainImageVO") MainImageVO mainImageVO, ModelMap model) throws Exception {
    	HashMap<String, String> param = new HashMap<String, String>();

		param.put("codeAxuse", "28000000");

		HashMap<String, Object> serviceMap = codeSvc.retrieveCommonCode(param);

		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		if(model != null){
			model.addAttribute("rowDataList", rowDataList);
	        //게시시작일자(시)
	        model.addAttribute("ntceBgndeHH", (List)getTimeHH());
	        //게시시작일자(분)
	        model.addAttribute("ntceBgndeMM", (List)getTimeMM());
	        //게시종료일자(시)
	        model.addAttribute("ntceEnddeHH", (List)getTimeHH());
	        //게시정료일자(분)
	        model.addAttribute("ntceEnddeMM", (List)getTimeMM());
	
	        model.addAttribute("mainImage", new MainImageVO());
		}

    	return "injeinc/boffice/ex/mainImage/main_image_regist";
	}

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * @param mainImage - 메인이미지 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/boffice/ex/mainImage/mainImageReg.do")
	public String insertMainImage(final MultipartHttpServletRequest multiRequest,
			                      @ModelAttribute("mainImage") MainImage mainImage,
			                      @ModelAttribute("mainImageVO") MainImageVO mainImageVO,
			                       BindingResult bindingResult,
			                       ModelMap model) throws Exception {

    	List<FileVO> result = null;

    	String uploadFolder = "mainImage.file.upload.path";
    	String image = "";
    	String imageFile = "";
    	String atchFileId = "";

    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

    	if(!files.isEmpty()){
    	    result = fileUtil.parseFileInf(files, "MSI_", 0, "", uploadFolder);
    	    atchFileId = fileMngService.insertFileInfs(result);

        	FileVO vo = (FileVO)result.get(0);
        	Iterator iter = result.iterator();

        	while (iter.hasNext()) {
        	    vo = (FileVO)iter.next();
        	    image = vo.getOrignlFileNm();
        	    imageFile = vo.getStreFileNm();
        	}
    	}

    	String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");

    	mainImage.setImageId(mainImageIdGnrService.getNextStringId());
    	mainImage.setImage(image);
    	mainImage.setImageFile(atchFileId);
    	mainImage.setImageId(mainImage.getImageId());
    	mainImage.setUserId(userid);

    	if(model != null){
	    	model.addAttribute("message", Message.getMessage("201.message"));
	    	model.addAttribute("mainImage", mainImageService.registMainImage(mainImage, mainImageVO));
    	}

		return "redirect:/boffice/ex/mainImage/mainImageList.do";

	}

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * @param mainImage - 메인이미지 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/boffice/ex/mainImage/mainImageMod.do")
	public String updateMainImage(final MultipartHttpServletRequest multiRequest,
                                  @ModelAttribute("mainImage") MainImage mainImage,
                                   BindingResult bindingResult,
                                   ModelMap model) throws Exception {

    	List<FileVO> result = null;

    	String uploadFolder = "mainImage.file.upload.path";
    	String image = "";
    	String imageFile = "";
    	String atchFileId = "";

    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

    	if(!files.isEmpty()){
    	    result = fileUtil.parseFileInf(files, "MSI_", 0, "", uploadFolder);
    	    atchFileId = fileMngService.insertFileInfs(result);

        	FileVO vo = null;
        	Iterator iter = result.iterator();

        	while (iter.hasNext()) {
        	    vo = (FileVO)iter.next();
        	    image = vo.getOrignlFileNm();
        	    imageFile = vo.getStreFileNm();
        	}

        	if (vo == null) {
        		mainImage.setAtchFile(false);
        	} else {
        		mainImage.setImage(image);
        		mainImage.setImageFile(atchFileId);
        		mainImage.setAtchFile(true);
        	}
    	} else {
    		mainImage.setAtchFile(false);
    	}

    	String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");
    	mainImage.setUserId(userid);

    	mainImageService.modifyMainImage(mainImage);
    	return "redirect:/boffice/ex/mainImage/mainImageList.do";

    }

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * @param mainImage - 메인이미지 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/boffice/ex/mainImage/mainImageRmv.do")
	public String deleteMainImage(@RequestParam("imageId") String imageId,
                                  @ModelAttribute("mainImage") MainImage mainImage,
  			                       ModelMap model) throws Exception {

		mainImage.setImageId(imageId);
		mainImageService.removeMainImage(mainImage);
		if(model != null){
			model.addAttribute("message", Message.getMessage("501.message"));
		}
		return "redirect:/boffice/ex/mainImage/mainImageList.do";
	}

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * @param mainImage - 메인이미지 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/boffice/ex/mainImage/mainImageListRmv.do")
	public String deleteMainImageList(@RequestParam("imageIds") String imageIds,
                                      @ModelAttribute("mainImage") MainImage mainImage,
                                       ModelMap model) throws Exception {

    	String [] strImageIds = imageIds.split(";");

    	for(int i=0; i<strImageIds.length;i++) {
    		mainImage.setImageId(strImageIds[i]);
    		mainImageService.removeMainImage(mainImage);
    	}
    	if(model != null){
    		model.addAttribute("message", Message.getMessage("501.message"));
    	}
    	return "redirect:/boffice/ex/mainImage/mainImageList.do";
	}

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/boffice/ex/mainImage/mainImageResult.do")
	public String selectMainImageResult(@ModelAttribute("mainImageVO") MainImageVO mainImageVO,
		                                 ModelMap model) throws Exception {

		List<MainImageVO> fileList = mainImageService.selectMainImageResult(mainImageVO);
		if(model != null){
			model.addAttribute("fileList", fileList);
		}

		return "injeinc/boffice/ex/mainImage/main_image_view";
	}

    /**
     * 시간을 LIST를 반환한다.
     * @return  List
     * @throws
     */
    private List getTimeHH (){
	    ArrayList listHH = new ArrayList();
	    HashMap hmHHMM;
	    for(int i=0;i < 24; i++){
		    String sHH = "";
		    String strI = String.valueOf(i);
		    if(i<10){
		            sHH = "0" + strI;
		    }else{
		            sHH = strI;
		    }

		    ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		    codeVO.setCode(sHH);
		    codeVO.setCodeNm(sHH);

		    listHH.add(codeVO);
	    }

	    return listHH;
    }
    /**
     * 분을 LIST를 반환한다.
     * @return  List
     * @throws
     */
    private List getTimeMM (){
	    ArrayList listMM = new ArrayList();
	    HashMap hmHHMM;
	    for(int i=0;i < 60; i++){
            String sMM = "";
            String strI = String.valueOf(i);
            if(i<10){
                    sMM = "0" + strI;
            }else{
                    sMM = strI;
            }

            ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
            codeVO.setCode(sMM);
            codeVO.setCodeNm(sMM);

            listMM.add(codeVO);
	    }
	    return listMM;
    }
    /**
     * 0을 붙여 반환
     * @return  String
     * @throws
     */
    public String dateTypeIntForString(int iInput){
	    String sOutput = "";
	    if(Integer.toString(iInput).length() == 1){
	            sOutput = "0" + Integer.toString(iInput);
	    }else{
	            sOutput = Integer.toString(iInput);
	    }
       return sOutput;
    }
    
    /**
	 * 순서를 바꾼다
	 * @param mainImage - 메인이미지 model
	 */
	@RequestMapping(value="/boffice/ex/mainImage/Step_Mod.do")
	public String imageSortMod(HttpServletRequest request,
										@ModelAttribute("MainImage") MainImage mainImage,
                                       ModelMap model) throws Exception {
		//System.out.println("mainImage:"+mainImage.getSort());
		if(EgovStringUtil.nullConvert(request.getParameter("temp")).equals("up")){		//위로 올릴경우
			mainImageService.modifyMainImageStepUp(mainImage);
		}else if(EgovStringUtil.nullConvert(request.getParameter("temp")).equals("down")){		//아래로 내릴경우
			mainImageService.modifyMainImageStepDown(mainImage);
		}
		
		
		return "redirect:/boffice/ex/mainImage/mainImageList.do?pageIndex="+mainImage.getPageIndex();
	}
}
