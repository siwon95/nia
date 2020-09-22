package egovframework.injeinc.boffice.hot.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import egovframework.injeinc.boffice.hot.manage.service.HotManageSvc;
import egovframework.injeinc.boffice.hot.manage.vo.HotManageVo;
import egovframework.injeinc.common.util.EgovDateUtil;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.injeinc.common.util.EgovStringUtil;
//import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class HotManageCtr extends CmmLogCtr{
	
	@Resource(name = "HotManageSvc")
	private HotManageSvc hotManageSvc;
	
	private EgovDateUtil egovDateUtil;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	/** 빠른서비스관리 리스트*/
	@RequestMapping(value= "/boffice/hot/manage/HotManage_List.do")
	public String hotManageList(
			HttpServletRequest request, @ModelAttribute("HotManageVo") HotManageVo hotManageVO
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		
		/*PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(hotManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(hotManageVO.getPageUnit());
		paginationInfo.setPageSize(hotManageVO.getPageSize());
		
		hotManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		hotManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		hotManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());*/
		
		//int totCnt = hotManageSvc.retrievePagHotManage(hotManageVO); 
		//paginationInfo.setTotalRecordCount(totCnt);
		
		List retrieveListHotManage = hotManageSvc.retrieveListHotManage(hotManageVO);		//리스트 조회
		
		//model.addAttribute("totCnt", totCnt);
		//model.addAttribute("paginationInfo", paginationInfo);
		if(model != null){
			model.addAttribute("HotManageVo", hotManageVO);
			model.addAttribute("resultList", retrieveListHotManage);
		}
		
		return "injeinc/boffice/hot/manage/manage_list";		
 	}
	
	/** 등록 */
	@RequestMapping(value= "/boffice/hot/manage/HotManage_Reg.do")
	public String hotManageReg(
			HttpServletRequest request, @ModelAttribute("HotManageVo") HotManageVo hotManageVO
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		
		hotManageVO.setRegId((String)ses.getAttribute("SesUserId"));
		
		// process files
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String SVC_MSGE = "";
		
		String uploadPath = Message.getMessage("hotManage.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
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
					hotManageVO.setHlFileOrgName(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					hotManageVO.setHlFileName(fileSystemName);	// 파일명 랜덤으로 set
					
					hotManageVO.setHlFilePath(uploadPath);
					
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + hotManageVO.getHlFileName()));  
					    
					//파일 저장
					file.transferTo(ufile);	//was서버 파일저장
				}
			}
			
			List<MultipartFile> files2 = multipartRequest.getFiles("fileUpload2");
			for(MultipartFile file : files2){
				if(!file.isEmpty()){            
					  
					String fileName = file.getOriginalFilename();
					hotManageVO.setHlMoFileOrgName(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					hotManageVO.setHlMoFileName(fileSystemName);	// 파일명 랜덤으로 set
					
					hotManageVO.setHlMoFilePath(uploadPath);
					
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + hotManageVO.getHlMoFileName()));  
					    
					//파일 저장
					file.transferTo(ufile);	//was서버 파일저장
					
				}
			}
			hotManageSvc.registHotManage(hotManageVO);		//등록
			
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
		
		return alert("/boffice/hot/manage/HotManage_List.do", SVC_MSGE, model);
	}
	
	/** 이미지 호출 및 다운로드 */
	@ResponseBody
	@RequestMapping("/hotManageFile/fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String fd_file_name=request.getParameter("fd_file_name") != null ? request.getParameter("fd_file_name").trim() : "";
		String fd_file_path=request.getParameter("fd_file_path") != null ? request.getParameter("fd_file_path").trim() : "";

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fd_file_name, "UTF-8")  + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileCopyUtils.copy(new FileInputStream(new File(EgovWebUtil.filePathBlackList(fd_file_path),EgovWebUtil.filePathBlackList(fd_file_name))), response.getOutputStream());    

	}
	
	/** 수정 */
	@RequestMapping(value= "/boffice/hot/manage/HotManage_Mod.do")
	public String hotManageMod(
			HttpServletRequest request, @ModelAttribute("HotManageVo") HotManageVo hotManageVO
			, ModelMap model ) throws Exception {
		
		HttpSession ses = request.getSession();
		
		hotManageVO.setModId((String)ses.getAttribute("SesUserId"));
		
		String SVC_MSGE = "";
		// process files
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String uploadPath = Message.getMessage("hotManage.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
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
			HotManageVo resultFile = hotManageSvc.retrieveFile(hotManageVO);	//파일명,경로,원본명 조회
			
			for(MultipartFile file : files){
				if(!file.isEmpty()){            
					if(resultFile.getHlFileName() !=null && !resultFile.getHlFileName().equals("")){
						//기존 파일 삭제 시작
						File beforeFile = new File(EgovWebUtil.filePathBlackList(rootPath + resultFile.getHlFilePath()+resultFile.getHlFileName()));
						
						beforeFile.delete();		//WAS파일 삭제
						
						//기존 파일 삭제 끝
					}
					//새 파일 등록
					String fileName = file.getOriginalFilename();
					hotManageVO.setHlFileOrgName(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					hotManageVO.setHlFileName(fileSystemName);	// 파일명 랜덤으로 set
					
					hotManageVO.setHlFilePath(uploadPath);
					
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + hotManageVO.getHlFileName()));   
					
					//파일 저장  
					file.transferTo(ufile);	//was서버 파일저장
				}else{
					
					hotManageVO.setHlFileName(resultFile.getHlFileName());
					hotManageVO.setHlFileOrgName(resultFile.getHlFileOrgName());
					hotManageVO.setHlFilePath(resultFile.getHlFilePath());
				}
			}
			
			List<MultipartFile> files2 = multipartRequest.getFiles("fileUpload2");
			for(MultipartFile file : files2){
				if(!file.isEmpty()){            
					  
					String fileName = file.getOriginalFilename();
					hotManageVO.setHlMoFileOrgName(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					hotManageVO.setHlMoFileName(fileSystemName);	// 파일명 랜덤으로 set
					
					hotManageVO.setHlMoFilePath(uploadPath);
					
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + hotManageVO.getHlMoFileName()));  
					    
					//파일 저장
					file.transferTo((ufile));	//was서버 파일저장
				}else{
					
					hotManageVO.setHlMoFileName(resultFile.getHlMoFileName());
					hotManageVO.setHlMoFileOrgName(resultFile.getHlMoFileOrgName());
					hotManageVO.setHlMoFilePath(resultFile.getHlMoFilePath());
				}
			}
			
			hotManageSvc.modifyHotManage(hotManageVO);		//수정
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
		return alert("/boffice/hot/manage/HotManage_List.do", SVC_MSGE, model);
	}
	
	/** 순서 수정 */
	@RequestMapping(value= "/boffice/hot/manage/Step_Mod.do")
	public String stepMod(
			HttpServletRequest request, @ModelAttribute("HotManageVo") HotManageVo hotManageVO
			, ModelMap model ) throws Exception {
		
		if(EgovStringUtil.nullConvert(request.getParameter("type")).equals("down")){		//순서 내릴시
				HotManageVo result = hotManageSvc.retrieveMinStep(hotManageVO);
				hotManageVO.setHlTempSort(result.getHlSort());
				hotManageVO.setHlTempIdx(result.getHlIdx());
		}else if(EgovStringUtil.nullConvert(request.getParameter("type")).equals("up")){	//순서 올릴시
				HotManageVo result = hotManageSvc.retrieveMaxStep(hotManageVO);
				hotManageVO.setHlTempSort(result.getHlSort());
				hotManageVO.setHlTempIdx(result.getHlIdx());
		}
		
		hotManageSvc.modifyStep(hotManageVO);	//순서 교체
		return "redirect:/boffice/hot/manage/HotManage_List.do";
	}
	
	/** 삭제 */
	@RequestMapping(value= "/boffice/hot/manage/HotManage_Rmv.do")
	public String hotManageRmv(
			HttpServletRequest request, @ModelAttribute("HotManageVo") HotManageVo hotManageVO
			, ModelMap model ) throws Exception {
		
		String SVC_MSGE = "";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		try{
			
			HotManageVo resultFile = hotManageSvc.retrieveFile(hotManageVO);
			
			if(resultFile.getHlFileName() == null && !resultFile.getHlFileName().equals("")){
				File file = new File(EgovWebUtil.filePathBlackList(rootPath + resultFile.getHlFilePath()+resultFile.getHlFileName()));
				
				file.delete();		//WAS파일 삭제
			}
			hotManageSvc.removeHotManage(hotManageVO);	//삭제
			
			SVC_MSGE = Message.getMessage("501.message"); //삭제성공
		
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message"); //오류
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message"); //오류
		}
		
		return alert("/boffice/hot/manage/HotManage_List.do", SVC_MSGE, model);
	}
}