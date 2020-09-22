package egovframework.injeinc.boffice.searchtheme.controller;

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
import egovframework.injeinc.boffice.searchtheme.service.SearchthemeSvc;
import egovframework.injeinc.boffice.searchtheme.vo.SearchthemeVo;
import egovframework.injeinc.common.util.EgovDateUtil;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class SearchthemeCtr extends CmmLogCtr{
	
	@Resource(name = "SearchthemeSvc")
	private SearchthemeSvc searchthemeSvc;
	
	private EgovDateUtil egovDateUtil;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	/** 테마 리스트*/
	@RequestMapping(value= "/boffice/searchtheme/Searchtheme_List.do")
	public String searchthemeList(
			HttpServletRequest request, @ModelAttribute("SearchthemeVo") SearchthemeVo searchthemeVO
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchthemeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchthemeVO.getPageUnit());
		paginationInfo.setPageSize(searchthemeVO.getPageSize());
		
		searchthemeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchthemeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchthemeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = searchthemeSvc.retrievePagSearchtheme(searchthemeVO); 
		paginationInfo.setTotalRecordCount(totCnt);
		
		List retrieveListSearchtheme = searchthemeSvc.retrieveListSearchtheme(searchthemeVO);		//리스트 조회
		
		List retrieveListGroupCd = searchthemeSvc.retrieveListGroupCd();		//그룹 리스트 조회
		if(model != null){
			model.addAttribute("totCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("SearchthemeVo", searchthemeVO);
			model.addAttribute("resultList", retrieveListSearchtheme);
			model.addAttribute("groupCdList", retrieveListGroupCd);
		}
		
		return "injeinc/boffice/searchtheme/searchtheme_list";		
 	}
	
	/** 삭제(update) */
	@RequestMapping(value= "/boffice/searchtheme/Searchtheme_Rmv.do")
	public String searchthemeRmv(
			HttpServletRequest request, @ModelAttribute("SearchthemeVo") SearchthemeVo searchthemeVO
			, ModelMap model ) throws Exception {
		
		String SVC_MSGE = "";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String uploadPath = Message.getMessage("searchtheme.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
		
		SearchthemeVo result = searchthemeSvc.retrieveSearchtheme(searchthemeVO);	//조회
		
		if(result.getStreFileNm() != null && !result.getStreFileNm().equals("")){
			File file = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + searchthemeVO.getStreFileNm()));
			file.delete();		//파일 삭제
			
			searchthemeSvc.removeSearchtheme(searchthemeVO);	//삭제(update)
			
			SVC_MSGE = Message.getMessage("501.message"); //삭제
		}else{
			SVC_MSGE = Message.getMessage("901.message"); //삭제실패
		}
			
		return alert("/boffice/searchtheme/Searchtheme_List.do", SVC_MSGE, model);
	}
	
	/** 등록/수정 폼 */
	@RequestMapping(value= "/boffice/searchtheme/Searchtheme_Form.do")
	public String searchthemeForm(
			HttpServletRequest request, @ModelAttribute("SearchthemeVo") SearchthemeVo searchthemeVO
			, ModelMap model ) throws Exception {
		
		List retrieveListGroupCd = searchthemeSvc.retrieveListGroupCd();		//그룹 리스트 조회
		if(model != null){
			model.addAttribute("groupCdList", retrieveListGroupCd);
		}
		if(!searchthemeVO.getStIdx().equals("")){
			SearchthemeVo result = searchthemeSvc.retrieveSearchtheme(searchthemeVO);	//조회
			result.setSearchCondition(searchthemeVO.getSearchCondition());
			result.setSearchKeyword(searchthemeVO.getSearchKeyword());
			result.setSchgroupcd(searchthemeVO.getSchgroupcd());
			result.setUseYn(searchthemeVO.getUseYn());
			result.setPageIndex(searchthemeVO.getPageIndex());
			result.setTelNum(result.getTelNum()+"--null");			//여러조건을 고려한 임의값 추가
			String[] tel = result.getTelNum().split("-");
			if(!tel[0].replaceAll("null", "").equals("")){
				result.setTel1(tel[0]);
			}
			if(!tel[1].replaceAll("null", "").equals("")){
				result.setTel2(tel[1]);
			}
			if(!tel[2].replaceAll("null", "").equals("")){
				result.setTel3(tel[2]);
			}
			if(model != null){
				model.addAttribute("SearchthemeVo", result);
			}
		}else{
			if(model != null){
				model.addAttribute("SearchthemeVo", searchthemeVO);
			}
		}
		
		return "injeinc/boffice/searchtheme/searchtheme_form";
	}
	
	/** 등록 */
	@RequestMapping(value= "/boffice/searchtheme/Searchtheme_Reg.do")
	public String searchthemeReg(
			HttpServletRequest request, @ModelAttribute("SearchthemeVo") SearchthemeVo searchthemeVO
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		
		searchthemeVO.setRegId((String)ses.getAttribute("SesUserId"));
		
		String SVC_MSGE = "";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String uploadPath = Message.getMessage("searchtheme.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
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
					searchthemeVO.setOrignlFileNm(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					searchthemeVO.setStreFileNm(fileSystemName);	// 파일명 랜덤으로 set
					
					searchthemeVO.setFilePath(uploadPath);
					debugLog("uploadPath:"+uploadPath);
					debugLog("fileSystemName:"+fileSystemName);
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName));  
					
					//파일 저장  
					file.transferTo(ufile);	//was서버 파일저장
				}
			} 
			searchthemeSvc.registSearchtheme(searchthemeVO);		//등록
			
			SVC_MSGE = Message.getMessage("201.message"); //인서트성공
		}catch(IllegalStateException e){
			ufile.delete();
			SVC_MSGE = Message.getMessage("901.message"); //인서트실패
		}catch(IOException e){
			ufile.delete();
			SVC_MSGE = Message.getMessage("901.message"); //인서트실패
		}catch(Exception e){
			ufile.delete();
			SVC_MSGE = Message.getMessage("901.message"); //인서트실패
		}
		
		return alert("/boffice/searchtheme/Searchtheme_List.do", SVC_MSGE, model);
	}
	
	/** 이미지 호출 및 다운로드 */
	@ResponseBody
	@RequestMapping("/searchthemeFile/fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String fd_file_name=request.getParameter("fd_file_name") != null ? request.getParameter("fd_file_name").trim() : "";
		String fd_file_path=request.getParameter("fd_file_path") != null ? request.getParameter("fd_file_path").trim() : "";

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fd_file_name, "UTF-8")  + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileCopyUtils.copy(new FileInputStream(new File(EgovWebUtil.filePathBlackList(fd_file_path),EgovWebUtil.filePathBlackList(fd_file_name))), response.getOutputStream());    

	}
	
	/** 사용여부 체크 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/searchtheme/SearchthemeUseYn_Ax.do")
	public void SearchthemeUseYn_Ax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String stIdx = EgovStringUtil.nullConvert(request.getParameter("stIdx"));
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("stIdx", stIdx);
		param.put("modId", (String)ses.getAttribute("SesUserId"));
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		searchthemeSvc.modifySearchthemeUseYn(param);
		
		jsonMap.put("message",Message.getMessage("401.message")); //수정);
		jsonView.render(jsonMap, request, response);
		
	}
	
	/** 수정 */
	@RequestMapping(value= "/boffice/searchtheme/Searchtheme_Mod.do")
	public String searchthemeMod(
			HttpServletRequest request, @ModelAttribute("SearchthemeVo") SearchthemeVo searchthemeVO
			, ModelMap model ) throws Exception {
		
		HttpSession ses = request.getSession();
		
		searchthemeVO.setModId((String)ses.getAttribute("SesUserId"));
		
		String SVC_MSGE = "";
		
		String uploadPath = Message.getMessage("searchtheme.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
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
					
					SearchthemeVo result = searchthemeSvc.retrieveSearchtheme(searchthemeVO);	//조회
					
					if(result.getStreFileNm() !=  null && !result.getStreFileNm().equals("")){
						//기존 파일 삭제 시작
						File beforeFile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath+result.getStreFileNm()));
						
						beforeFile.delete();		//WAS파일 삭제
						
					}
					
					String fileName = file.getOriginalFilename();
					searchthemeVO.setOrignlFileNm(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					searchthemeVO.setStreFileNm(fileSystemName);	// 파일명 랜덤으로 set
					
					searchthemeVO.setFilePath(uploadPath);
					
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName));  
					
					//파일 저장    
					file.transferTo(ufile);	//was서버 파일저장
				}
			} 
			searchthemeSvc.modifySearchtheme(searchthemeVO);		//수정
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
		return alert("/boffice/searchtheme/Searchtheme_Form.do?stIdx="+searchthemeVO.getStIdx(), SVC_MSGE, model);
	}
	
	/** 파일 삭제 
	 * @throws Exception */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/searchtheme/SearchthemeFileRmv_Ax.do")
	public void searchthemeFileRmvAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("streFileNm") != null ? request.getParameter("streFileNm").trim() : "";     
		String stIdx = EgovStringUtil.nullConvert(request.getParameter("stIdx"));

		String uploadPath = Message.getMessage("searchtheme.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("stIdx", stIdx);

		searchthemeSvc.removeSearchthemeFile(param);		//저장이름,원본이름,경로 삭제(update)
		
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
	
}