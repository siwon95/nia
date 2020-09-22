package egovframework.injeinc.boffice.sy.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

//import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.file.service.FileSvc;
import egovframework.injeinc.boffice.sy.file.vo.FileVo;
import egovframework.injeinc.common.util.EgovDateUtil;




@Controller
public class FileCtr extends CmmLogCtr{
	
	@Resource(name = "FileSvc")
	private FileSvc fileSvc;
	
	private EgovDateUtil egovDateUtil;

	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	
	
	
	/**
	 * 파일 업로드화면
	 * @param request
	 * @param fileVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/sy/file/File_List.do")
	public String FileList(
			HttpServletRequest request, @ModelAttribute("FileVo") FileVo fileVo
			, ModelMap model) throws Exception {

		return "injeinc/boffice/sy/file/file_list";		
	}
	
	@RequestMapping(value= "/boffice/sy/file/File_Reg.do")
	public String fileUpload(HttpServletRequest request, ModelMap model) throws Exception {
		
		String date = egovDateUtil.yearMonthDay();
		
		String SVC_MSGE = "";
		
		String uploadPath = Message.getMessage("board.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
		debugLog("uploadPath ====>> : " + uploadPath);
		uploadPath = uploadPath + "/" + date + "/";
		File saveFolder = new File(EgovWebUtil.filePathBlackList(uploadPath));
		  if (!saveFolder.exists() || saveFolder.isFile()) saveFolder.mkdirs();

		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		
		//단일 파일 업로드
		//MultipartFile file = multipartRequest.getFile("fileUpload"); 
		
		//다중파일 업로드
		List<MultipartFile> files = multipartRequest.getFiles("fileUpload"); 
		
			
		for(MultipartFile file : files){
			if(!file.isEmpty()){            
				File ufile = new File(EgovWebUtil.filePathBlackList(uploadPath + file.getOriginalFilename()));    
				String fileName = file.getOriginalFilename();
				debugLog("fileName => " + fileName);
				
				String fileSize = Integer.toString((int)file.getSize());
				debugLog("fileSize => " + fileSize);            
				
				String filePath = uploadPath;    
				debugLog("filePath => "+ filePath);
				
				String imgExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());


				//String fileType = new MimetypesFileTypeMap().getContentType(ufile);
				debugLog("imgExt => " + imgExt);
				

				HashMap<String, Object> param = new HashMap<String, Object>();        
				param.put("fileName", fileName);
				param.put("fileSize", fileSize);
				param.put("filePath", filePath);            
				param.put("fileType", imgExt);            
				param.put("regId", "홍길동");            

				//파일 저장    
				fileSvc.registFile(param);
				file.transferTo(ufile);
				SVC_MSGE = Message.getMessage("201.message"); //인서트성공

			}
			
		} 
			
		return alert("/boffice/sy/file/File_List.do", SVC_MSGE, model);	
	}


	/* 아직 사용안함. */
	@ResponseBody
	@RequestMapping("/file/fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {

		String fd_file_name = request.getParameter("fd_file_name") != null ? request.getParameter("fd_file_name").trim() : "";        
		String fd_file_path=request.getParameter("fd_file_path") != null ? request.getParameter("fd_file_path").trim() : "";
		String fd_file_size =request.getParameter("fd_file_size") != null ? request.getParameter("fd_file_size").trim() : "";

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fd_file_name, "UTF-8")  + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileCopyUtils.copy(new FileInputStream(new File(EgovWebUtil.filePathBlackList(fd_file_path),EgovWebUtil.filePathBlackList(fd_file_name))), response.getOutputStream());    

	}


	/* 아직 사용안함. */
	@RequestMapping("/file/fileDelete.do")
	public ModelAndView fileDelete(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException    {
		String pk_bbs = request.getParameter("pk_bbs") != null ? request.getParameter("pk_bbs").trim() : "";    
		String seq_bbs_file = request.getParameter("seq_bbs_file") != null ? request.getParameter("seq_bbs_file").trim() : "";        
		String fd_file_name = request.getParameter("fd_file_name") != null ? request.getParameter("fd_file_name").trim() : "";        
		String fd_file_path=request.getParameter("fd_file_path") != null ? request.getParameter("fd_file_path").trim() : "";
		String fd_file_size =request.getParameter("fd_file_size") != null ? request.getParameter("fd_file_size").trim() : "";

		debugLog("seq_bbs_file"+seq_bbs_file);
		debugLog("pk_bbs"+pk_bbs);
		debugLog("fd_file_name"+fd_file_name);
		debugLog("fd_file_path"+fd_file_path);
		debugLog("fd_file_size"+fd_file_size);

		File file = new File(EgovWebUtil.filePathBlackList(fd_file_path+fd_file_name));
		//System.out.println("file은"+file);

		if(!file.exists()){ 
			//System.out.println(fd_file_name + " File not exist!"); 
		} else if(file.isFile()) { 
			file.delete(); 
		}  

		String queryString = "?pk_bbs="+pk_bbs;
		//String queryString = "?page=" + String.valueOf(page) + "&searchColumn=" + searchColumn + "&searchString=" + searchString;
		ModelAndView mav = new ModelAndView("redirect:/bbs/boardModifyForm.do" + queryString);
		return mav;
	}





}