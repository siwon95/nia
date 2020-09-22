package egovframework.injeinc.boffice.pledge.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.pledge.service.PledgeFileSvc;
import egovframework.injeinc.boffice.pledge.vo.PledgeFileVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class PledgeFileCtr extends CmmLogCtr{
	
	@Resource(name = "PledgeFileSvc")
	private PledgeFileSvc pledgeFileSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/common/pledge/Download.do")
	public void PledgeFileDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PledgeFileVo") PledgeFileVo pledgeFileVo) throws Exception {

		String bcIdx = EgovStringUtil.isNullToString(request.getParameter("bcIdx"));
		String cbIdx = EgovStringUtil.isNullToString(request.getParameter("cbIdx"));
		String streFileNm = EgovStringUtil.isNullToString(request.getParameter("streFileNm"));
		System.out.println("bcIdx="+bcIdx+",cbIdx="+cbIdx+",streFileNm="+streFileNm);
		
		pledgeFileVo = pledgeFileSvc.retrievePledgeFile(pledgeFileVo);	// 필수파라미터 : bcIdx, cbIdx, streFileNm
		if(pledgeFileVo == null) {
			throw new Exception("Download File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String downFileName = rootPath + pledgeFileVo.getFileStreCours() + pledgeFileVo.getStreFileNm();
		
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

			try {
	
				fin = new BufferedInputStream(new FileInputStream(file));
				String mimetype = "application/x-msdownload";
	 
				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				setDisposition(pledgeFileVo.getOrignlFileNm(), request, response);
				response.setContentLength(fSize);
				
				FileCopyUtils.copy(fin, response.getOutputStream());
				fin.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}catch(FileNotFoundException fne) {
				EgovResourceCloseHelper.close(fin);
				throw fne;
			}catch(IOException ioe) {
				EgovResourceCloseHelper.close(fin);
				throw ioe;
			}catch(Exception e) {
				EgovResourceCloseHelper.close(fin);
				throw e;
			}finally {
				EgovResourceCloseHelper.close(fin);
			}
			
		}else{
			throw new Exception("Download File is Zero Size.");
		}
	}
	
	@RequestMapping("/common/pledge/PledgeFileRmvAx.do")
	public void PledgeFileRmvAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PledgeFileVo") PledgeFileVo pledgeFileVo, ModelMap model) throws Exception {

			String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
			pledgeFileVo.setModId(userid);
			
			int plIdx = pledgeFileVo.getPlIdx();
			String streFileNm = pledgeFileVo.getStreFileNm();
			
			boolean result = false;
			String message = "";
			
			if(plIdx > 0 && !streFileNm.equals("")) {
				
				pledgeFileSvc.removeContentFile(pledgeFileVo);	// 필수파라미터 : plIdx, streFileNm
				
				message = "삭제되었습니다.";
				result = true;
			}else{
				message = "필요한 자료가 없습니다.";
			}

			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", result);
			jsonMap.put("message", message);
			jsonView.render(jsonMap, request, response);
		
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
}