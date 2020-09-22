package egovframework.injeinc.common.ckeditor.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.ckeditor.vo.FileBean;

@Controller(value = "viewController")
public class ViewController {
    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    @RequestMapping(value = "/Ckeditor/File_Upload.do", method = RequestMethod.POST)
    public String fileUpload(FileBean fileBean,HttpServletRequest request, Model model)  throws Exception {
    	if(model == null){
			return "injeinc/common/code500";
		}
    	if(fileBean == null){
			return "injeinc/common/code500";
		}
         String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
         String editorAttachPath = Message.getMessage("ckeditor.file.upload.path");

        MultipartFile upload = fileBean.getUpload();
        String filename = "";
        String CKEditorFuncNum = "";
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
        
        int randomNum = (int) (Math.random() * 10);
        int randomNum2 = (int) (Math.random() * 10);
        
        if (upload != null) {
        	int pos = upload.getOriginalFilename().lastIndexOf(".");
        	String ext = upload.getOriginalFilename().substring(pos);
        	filename = upload.getOriginalFilename().substring(0, pos);        			
            filename = filename+"_"+today+randomNum+randomNum2+ext;
            fileBean.setFilename(filename);
            CKEditorFuncNum = fileBean.getCKEditorFuncNum();
            try {
            	File saveFolder = new File(rootPath + editorAttachPath);
        		
        		if(!saveFolder.exists() || saveFolder.isFile()) {
        			saveFolder.mkdirs();
        		}
        		
                File file = new File(EgovWebUtil.filePathBlackList(rootPath + editorAttachPath + filename));
                logger.info(rootPath + editorAttachPath + filename);
                upload.transferTo(file);
            } catch (IOException e) {
                System.out.println("/Ckeditor/File_Upload.do IOException");
                throw e;
            }
        }

        model.addAttribute("file_path", "/ckeditor/fileDownload.do?file_name="+filename);
        model.addAttribute("CKEditorFuncNum", CKEditorFuncNum);

         return "injeinc/common/ckeditor/fileUpload";
    }
    
	/** 이미지 호출 및 다운로드 */
	@ResponseBody
	@RequestMapping("/ckeditor/fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String file_name=request.getParameter("file_name") != null ? request.getParameter("file_name").trim() : "";
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
        String editorAttachPath = Message.getMessage("ckeditor.file.upload.path");
		String file_path= rootPath+editorAttachPath;

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file_name, "UTF-8")  + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileCopyUtils.copy(new FileInputStream(new File(EgovWebUtil.filePathBlackList(file_path),EgovWebUtil.filePathBlackList(file_name))), response.getOutputStream());    

	}
	
	/** 이미지 호출 및 다운로드 */
	@ResponseBody
	@RequestMapping("/Crosseditor/fileDownload.do")
	public void CrosseditorFileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String file_name=request.getParameter("file_name") != null ? request.getParameter("file_name").trim() : "";
//		String rootPath = EgovProperties.getProperty("Tomcat.ROOT_PATH");
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		System.out.println("rootPath : " + rootPath);
        String editorAttachPath = Message.getMessage("crosseditor.file.upload.path");
		String file_path= rootPath+editorAttachPath;
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file_name, "UTF-8")  + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileCopyUtils.copy(new FileInputStream(new File(EgovWebUtil.filePathBlackList(file_path),EgovWebUtil.filePathBlackList(file_name))), response.getOutputStream());    

	}
}
