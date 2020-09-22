package egovframework.injeinc.boffice.main.top.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.main.top.service.TopImagesSvc;
import egovframework.injeinc.boffice.main.top.vo.TopImagesVo;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.common.util.EgovDateUtil;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class TopImagesCtr extends CmmLogCtr{
	
	@Autowired
	private CodeSvc codeSvc;
	
	@Resource(name = "TopImagesSvc")
	private TopImagesSvc topImagesSvc;
	
	private EgovDateUtil egovDateUtil;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	/** 이미지 리스트*/
	@RequestMapping(value= "/boffice/main/top/Top_Images_List.do")
	public String topImagesList(
			HttpServletRequest request, @ModelAttribute("TopImagesVo") TopImagesVo topImagesVO
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(topImagesVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(topImagesVO.getPageUnit());
		paginationInfo.setPageSize(topImagesVO.getPageSize());
		
		topImagesVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		topImagesVO.setLastIndex(paginationInfo.getLastRecordIndex());
		topImagesVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = topImagesSvc.retrievePagTopImages(topImagesVO); 
		paginationInfo.setTotalRecordCount(totCnt);
		
		List retrieveListTopImages = topImagesSvc.retrieveListTopImages(topImagesVO);		//리스트 조회
		
		HashMap<String, String> param = new HashMap<String, String>();
		
		param.put("codeAxuse", "28000000");
		HashMap<String, Object> serviceMap = codeSvc.retrieveCommonCode(param);
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");
		
		param.put("codeAxuse", "26000000");
		HashMap<String, Object> divisionMap = codeSvc.retrieveCommonCode(param);
		List<CodeVo> divisionList = (List<CodeVo>) divisionMap.get("rowDataList");
		if(model != null){
			model.addAttribute("totCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("TopImagesVo", topImagesVO);
			model.addAttribute("resultList", retrieveListTopImages);
			model.addAttribute("rowDataList", rowDataList);
			model.addAttribute("divisionList", divisionList);
		}
		
		return "injeinc/boffice/main/top/top_images_list";		
 	}
	
	/** 등록/수정 폼*/
	@RequestMapping(value= "/boffice/main/top/Top_Images_Form.do")
	public String topImagesForm(
			HttpServletRequest request, @ModelAttribute("TopImagesVo") TopImagesVo topImagesVO
			, ModelMap model ) throws Exception {
		
		HashMap<String, String> param = new HashMap<String, String>();

		param.put("codeAxuse", "28000000");

		HashMap<String, Object> serviceMap = codeSvc.retrieveCommonCode(param);

		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		debugLog("rowDataList : " + rowDataList);
		if(model != null){
			model.addAttribute("rowDataList", rowDataList);
		}
		
		if(!topImagesVO.getTiIdx().equals("")){//수정폼 호출시
			TopImagesVo result= topImagesSvc.retrieveTopImages(topImagesVO);
			result.setPageIndex(topImagesVO.getPageIndex());
			result.setSearchKeyword(topImagesVO.getSearchKeyword());
			result.setSearchDivision(topImagesVO.getSearchDivision());
			result.setSearchSite(topImagesVO.getSearchSite());
			
			String startDay = result.getTiPostSdt().substring(0, 8);
			String startHour = result.getTiPostSdt().substring(8, 10);
			String startMinute = result.getTiPostSdt().substring(10, 12);
			result.setStartday(startDay.substring(0, 4)+"-"+startDay.substring(4, 6)+"-"+startDay.substring(6, 8));
			result.setStarthour(startHour);
			result.setStartminute(startMinute);
			
			String endDay = result.getTiPostEdt().substring(0, 8);
			String endHour = result.getTiPostEdt().substring(8, 10);
			String endMinute = result.getTiPostEdt().substring(10, 12);
			result.setEndday(endDay.substring(0, 4)+"-"+endDay.substring(4, 6)+"-"+endDay.substring(6, 8));
			result.setEndhour(endHour);
			result.setEndminute(endMinute);
			if(model != null){	
				model.addAttribute("TopImagesVo", result);
			}
		}else{
			if(model != null){
				model.addAttribute("TopImagesVo", topImagesVO);
			}
		}
		
		return "injeinc/boffice/main/top/top_images_form";
	}
	
	/** 이미지 호출 및 다운로드 */
	@ResponseBody
	@RequestMapping("/top/imagesFile/fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String fd_file_name=request.getParameter("fd_file_name") != null ? request.getParameter("fd_file_name").trim() : "";
		String fd_file_path=request.getParameter("fd_file_path") != null ? request.getParameter("fd_file_path").trim() : "";

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fd_file_name, "UTF-8")  + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileCopyUtils.copy(new FileInputStream(new File(EgovWebUtil.filePathBlackList(fd_file_path),EgovWebUtil.filePathBlackList(fd_file_name))), response.getOutputStream());    

	}
	
	/** 등록 */
	@RequestMapping(value= "/boffice/main/top/Top_Images_Reg.do")
	public String topImagesReg(
			HttpServletRequest request, @ModelAttribute("TopImagesVo") TopImagesVo topImagesVO
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		
		topImagesVO.setRegId((String)ses.getAttribute("SesUserId"));
		
		String SVC_MSGE = "";
		
		String uploadPath = Message.getMessage("topImages.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath));
		  if (!saveFolder.exists() || saveFolder.isFile()) saveFolder.mkdirs();

		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		
		//단일 파일 업로드
		//MultipartFile file = multipartRequest.getFile("fileUpload"); 
		
		//다중파일 업로드
		List<MultipartFile> files = multipartRequest.getFiles("fileUpload"); 
		String fileSystemName = "";
		File ufile = null;
		try{
			
			for(MultipartFile file : files){
				if(!file.isEmpty()){            
					  
					String fileName = file.getOriginalFilename();
					topImagesVO.setTiFileOrgName(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					topImagesVO.setTiFileName(fileSystemName);	// 파일명 랜덤으로 set
					
					topImagesVO.setTiFilePath(uploadPath);
					
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName));  
					
					//파일 저장    
					file.transferTo((ufile));
				}
			} 
			topImagesSvc.registTopImages(topImagesVO);		//등록
			SVC_MSGE = Message.getMessage("201.message"); //인서트성공
		}catch(IllegalStateException e){
			;ufile.delete();
			SVC_MSGE = Message.getMessage("901.message"); //인서트실패
		}catch(IOException e){
			;ufile.delete();
			SVC_MSGE = Message.getMessage("901.message"); //인서트실패
		}catch(Exception e){
			;ufile.delete();
			SVC_MSGE = Message.getMessage("901.message"); //인서트실패
		}
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(topImagesVO.getPageIndex());
		addParam.append("&searchKeyword=").append(topImagesVO.getSearchKeyword());
		addParam.append("&searchDivision=").append(topImagesVO.getSearchDivision());
		addParam.append("&searchSite=").append(topImagesVO.getSearchSite());
		
		return alert("/boffice/main/top/Top_Images_List.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	/** 파일 삭제 
	 * @throws Exception */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/main/top/Top_ImagesFileRmv_Ax.do")
	public void topImagesFileRmvAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("tiFileName") != null ? request.getParameter("tiFileName").trim() : "";     
		String tiIdx = EgovStringUtil.nullConvert(request.getParameter("tiIdx"));
		
		String uploadPath = Message.getMessage("topImages.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
			
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("tiIdx", tiIdx);

		topImagesSvc.removeTopImagesFile(param);		//저장이름,원본이름,경로 삭제(update)
		
		File file = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath+fileName));

		if(!file.exists()){ 
			//System.out.println(fileName + " File not exist!"); 
		} else if(file.isFile()) { 
			file.delete(); 		//파일 삭제
		}  
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("message",Message.getMessage("501.message")); //삭제);
		jsonView.render(jsonMap, request, response);	

	}
	
	/** 수정 */
	@RequestMapping(value= "/boffice/main/top/Top_Images_Mod.do")
	public String topImagesMod(
			HttpServletRequest request, @ModelAttribute("TopImagesVo") TopImagesVo topImagesVO
			, ModelMap model ) throws Exception {
		
		HttpSession ses = request.getSession();
		
		topImagesVO.setModId((String)ses.getAttribute("SesUserId"));
		
		String SVC_MSGE = "";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadPath = Message.getMessage("topImages.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
		File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath));
		  if (!saveFolder.exists() || saveFolder.isFile()) saveFolder.mkdirs();

		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		
		//단일 파일 업로드
		//MultipartFile file = multipartRequest.getFile("fileUpload"); 
		
		//다중파일 업로드
		List<MultipartFile> files = multipartRequest.getFiles("fileUpload"); 
		
		String fileSystemName = "";
		File ufile = null;
		try{
			
			for(MultipartFile file : files){
				if(!file.isEmpty()){            
					TopImagesVo result= topImagesSvc.retrieveTopImages(topImagesVO);
					if(result.getTiFileName() != null && !result.getTiFileName().equals("")){
						//기존 파일 삭제 시작
						File beforeFile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath+result.getTiFileName()));
						
						beforeFile.delete();		//WAS파일 삭제
						
						//기존 파일 삭제 끝
					}
					String fileName = file.getOriginalFilename();
					topImagesVO.setTiFileOrgName(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					topImagesVO.setTiFileName(fileSystemName);	// 파일명 랜덤으로 set
					
					topImagesVO.setTiFilePath(uploadPath);
					
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName));  
					
					HashMap<String, Object> param = new HashMap<String, Object>();        

					//파일 저장    
					file.transferTo((ufile));
				}
			} 
			topImagesSvc.modifyTopImages(topImagesVO);		//수정
			SVC_MSGE = Message.getMessage("401.message"); //수정성공
		}catch(IllegalStateException e){
			ufile.delete();
			SVC_MSGE = Message.getMessage("901.message"); //수정실패
		}catch(IOException e){
			ufile.delete();
			SVC_MSGE = Message.getMessage("901.message"); //수정실패
		}catch(Exception e){
			ufile.delete();
			SVC_MSGE = Message.getMessage("901.message"); //수정실패
		}
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(topImagesVO.getPageIndex());
		addParam.append("&tiIdx=").append(topImagesVO.getTiIdx());
		addParam.append("&searchKeyword=").append(topImagesVO.getSearchKeyword());
		addParam.append("&searchDivision=").append(topImagesVO.getSearchDivision());
		addParam.append("&searchSite=").append(topImagesVO.getSearchSite());
		return alert("/boffice/main/top/Top_Images_List.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	/** 삭제 */
	@RequestMapping(value= "/boffice/main/top/Top_Images_Rmv.do")
	public String topImagesRmv(
			HttpServletRequest request, @ModelAttribute("TopImagesVo") TopImagesVo topImagesVO
			, ModelMap model ) throws Exception {
		
		String SVC_MSGE = "";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String uploadPath = Message.getMessage("topImages.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
			
		TopImagesVo result= topImagesSvc.retrieveTopImages(topImagesVO);
		if(result.getTiFileName() != null && !result.getTiFileName().equals("")){
			//기존 파일 삭제 시작
			File beforeFile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath+result.getTiFileName()));
			
			beforeFile.delete();		//WAS파일 삭제
			
			//기존 파일 삭제 끝
			
			topImagesSvc.removeTopImages(topImagesVO);
			SVC_MSGE = Message.getMessage("501.message"); //삭제성공
		}else{
			SVC_MSGE = Message.getMessage("901.message"); //오류
		}
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(topImagesVO.getPageIndex());
		addParam.append("&searchKeyword=").append(topImagesVO.getSearchKeyword());
		addParam.append("&searchDivision=").append(topImagesVO.getSearchDivision());
		addParam.append("&searchSite=").append(topImagesVO.getSearchSite());
		
		return alert("/boffice/main/top/Top_Images_List.do"+addParam.toString(), SVC_MSGE, model);
	}
}