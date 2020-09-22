package egovframework.injeinc.boffice.sy.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.sy.fileupload.service.FileUploadSvc;
import egovframework.injeinc.boffice.sy.fileupload.vo.FileUploadVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
@Controller
public class FileuploadCtr extends CmmLogCtr{
	
	@Resource(name = "FileUploadSvc")
	private FileUploadSvc fileUploadSvc;
	
	/** 드래그 파일 업로드 리스트 **/
	@RequestMapping(value= "/boffice/sy/fileupload/Fileupload_List.do")
	public String FileUploadList(@ModelAttribute("FileUploadVo") FileUploadVo fileUploadVo, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(fileUploadVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(fileUploadVo.getPageUnit());
		paginationInfo.setPageSize(fileUploadVo.getPageSize());
		
		fileUploadVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		fileUploadVo.setLastIndex(paginationInfo.getLastRecordIndex());
		fileUploadVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		fileUploadVo.setFuGroup("COMMON");
		fileUploadVo.setFuPkidx("");
		Map<String, Object> map = fileUploadSvc.retrievePagFileUpload(fileUploadVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		

		return "injeinc/boffice/sy/fileupload/fileupload_list";
	}
	
	/** 드래그 파일 업로드 등록 **/	
	@RequestMapping("/boffice/sy/fileupload/FileUploadReg.do")
	public String FileUploadReg(HttpServletRequest request, @ModelAttribute("FileUploadVo") FileUploadVo fileUploadVo, ModelMap model) throws Exception {

			fileUploadVo.setFuGroup("COMMON"); 														//그룹명은 프로그램에 따라 다르게 합니다.
			fileUploadVo.setFuPkidx(""); 																		//프로그램 고유키
			fileUploadVo.setFuPath(Message.getMessage("common.file.upload.path"));		//업로드 URL 입력
			
			String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
			fileUploadVo.setRegId(userid);
			
			fileUploadSvc.registFileUpload(request, fileUploadVo);
			
			String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/sy/fileupload/Fileupload_List.do", SVC_MSGE, model); 
	}
	
	
	/** 드래그 파일 업로드 삭제(Y->N) 사용여부를 체크한다.**/
	@RequestMapping("/boffice/sy/fileupload/FileUploadRmv.do")
	public String FileUploadRmv(HttpServletRequest request, @ModelAttribute("FileUploadVo") FileUploadVo fileUploadVo, ModelMap model) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		fileUploadVo.setModId(userid);
		
		fileUploadSvc.removeFileUploadByGroupAndRename(fileUploadVo);	// 필수파라미터 : cfIdx, cfGroup, cfRename
		//cmmFilesSvc.removeCmmFiles(cmmFilesVo);				// 필수파라미터 : cfIdx
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(fileUploadVo.getPageIndex());
		addParam.append("&searchCondition=").append(fileUploadVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(fileUploadVo.getSearchKeyword());

		
		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/sy/fileupload/Fileupload_List.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	/** 드래그 파일 업로드 다운로드 **/
	@RequestMapping("/boffice/sy/fileupload/Download.do")
	public void FileUploadDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("FileUploadVo") FileUploadVo fileUploadVo) throws Exception {

		fileUploadVo = fileUploadSvc.retrieveFileUploadByGroupAndRename(fileUploadVo);	// 필수파라미터 : cfIdx, cfGroup, cfRename
		//cmmFilesVo = cmmFilesSvc.retrieveCmmFiles(cmmFilesVo);				// 필수파라미터 : cfIdx
		
		if(fileUploadVo == null) {
			throw new Exception("Download File is null");
		}else {
			fileUploadSvc.modifyFileUploadDown(fileUploadVo);
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String downFileName = rootPath + fileUploadVo.getFuPath() + fileUploadVo.getFuRename();
		
		File file = new File(EgovWebUtil.filePathBlackList(downFileName));

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}
		
		int fSize = (int) file.length();
		
		if (fSize > 0) {
			BufferedInputStream fin = null;
			FileInputStream fis = null;

			try {
				fis = new FileInputStream(file);
	
				fin = new BufferedInputStream(fis);
				String mimetype = "application/x-msdownload";
	 
				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				setDisposition(fileUploadVo.getFuName(), request, response);
				response.setContentLength(fSize);
				
				FileCopyUtils.copy(fin, response.getOutputStream());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}catch(FileNotFoundException e) {
				EgovResourceCloseHelper.close(fin);
				EgovResourceCloseHelper.close(fis);
				debugLog("IGNORE: " + e.getMessage());
			}catch(IOException e) {
				EgovResourceCloseHelper.close(fin);
				EgovResourceCloseHelper.close(fis);
				debugLog("IGNORE: " + e.getMessage());
			}catch(Exception e) {
				EgovResourceCloseHelper.close(fin);
				EgovResourceCloseHelper.close(fis);
				debugLog("IGNORE: " + e.getMessage());
			}finally {
				EgovResourceCloseHelper.close(fin);
				EgovResourceCloseHelper.close(fis);
			}
			
		}
	}
	
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		}else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		}else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}else if (header.indexOf("Firefox") > -1) {
			return "Firefox";
		}else if (header.indexOf("Safari") > -1) {
			return "Safari";
		}
		return "MSIE";
	}
	
	public void setDisposition(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String browser = getBrowser(request);
		
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		
		Charset defaultCharset = Charset.defaultCharset();
		
		if(browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		}else if (browser.equals("Firefox")) {
			if(defaultCharset.toString().toLowerCase().equals("euc-kr")) {
				encodedFilename = "\"" + fileName + "\"";
			}else{
				encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			}
		}else if (browser.equals("Safari")) {
			if(defaultCharset.toString().toLowerCase().equals("euc-kr")) {
				encodedFilename = "\"" + fileName + "\"";
			}else{
				encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			}
		}else if (browser.equals("Opera")) {
			if(defaultCharset.toString().toLowerCase().equals("euc-kr")) {
				encodedFilename = "\"" + fileName + "\"";
			}else{
				encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			}
		}else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < fileName.length(); i++) {
				char c = fileName.charAt(i);
				if(c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		}else {
			throw new IOException("Not supported browser");
		}
		
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
	
		if("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
}
