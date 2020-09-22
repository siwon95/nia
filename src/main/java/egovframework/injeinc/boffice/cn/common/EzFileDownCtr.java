package egovframework.injeinc.boffice.cn.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.injeinc.boffice.cn.file.service.EzFileSvc;
import egovframework.injeinc.boffice.cn.file.vo.EzFileVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class EzFileDownCtr extends CmmLogCtr  {
	@Resource(name = "EzFileSvc")
	private EzFileSvc ezFileSvc;
	
	
	/**
     * 브라우저 구분 얻기.
     * 
     * @param request
     * @return
     */
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) {
        	// InternetExplorer11 이상
            return "MSIE";    
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }
	 /**
     * Disposition 지정하기.
     * 
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    private void setDisposition(String filename, String dispositionPrefixStr, HttpServletRequest request, HttpServletResponse response) throws Exception {
	String browser = getBrowser(request);
	
	String dispositionPrefix = "attachment; filename=";
	
	// inline 지정가능
	if ("inline".equals(dispositionPrefixStr)) {
		dispositionPrefix = "inline; filename=";
	}
	
	String encodedFilename = null;
	
	if (browser.equals("MSIE")) {
	    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	} else if (browser.equals("Firefox")) {
		if(filename != null){
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		}
	} else if (browser.equals("Opera")) {
		if(filename != null){
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		}
	} else if (browser.equals("Chrome")) {
	    StringBuffer sb = new StringBuffer();
	    if(filename != null){
		    for (int i = 0; i < filename.length(); i++) {
		    	if(filename != null){
					char c = filename.charAt(i);
					if (c > '~') {
					    sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
					    sb.append(c);
					}
		    	}
		    }
	    }
	    encodedFilename = sb.toString();
	} else {
	    //throw new RuntimeException("Not supported browser");
	    throw new IOException("Not supported browser");
	}
	
	response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

	if ("Opera".equals(browser)){
	    response.setContentType("application/octet-stream;charset=UTF-8");
	}
    }
    /**
     * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
     * 
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/site/FileDown.do")    
    public void siteFileDown(Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	/*String atchFileId = (String)commandMap.get("atchFileId");
    	String fileSn = (String)commandMap.get("fileSn");*/
    	
    	String atchFileId = EgovStringUtil.nullConvert(request.getParameter("atchFileId"));
    	String fileSn = EgovStringUtil.nullConvert(request.getParameter("fileSn"));    	

    	EzFileVo ezFileVo = new EzFileVo();
    	ezFileVo.setAtchFileId(atchFileId);
    	ezFileVo.setFileSn(fileSn);
    	EzFileVo fvo = ezFileSvc.retrieveEzFile(ezFileVo);
	    
	    String fileExtsn = fvo.getFileExtsn().toLowerCase();
	    
	    String mime = "";
	    
	    if(fileExtsn.equals("doc")){
	    	mime = "application/msword";
	    }else if(fileExtsn.equals("pdf")){
	    	mime = "application/pdf";
	    }else if(fileExtsn.equals("xls")){
	    	mime = "application/x-msexcel";
	    }else if(fileExtsn.equals("ppt")){
	    	mime = "application/x-mspowerpoint";
	    }else if(fileExtsn.equals("gif")){
	    	mime = "image/gif";
	    }else if(fileExtsn.equals("jpeg")||fileExtsn.equals("jpg")||fileExtsn.equals("jpe")){
	    	mime = "image/jpeg";
	    }else if(fileExtsn.equals("hwp")){
	    	mime = "application/haansofthwp";
	    }else{
	    	mime = "application/x-msdownload";	
	    }

	    File uFile = new File(EgovWebUtil.filePathBlackList(fvo.getFileStreCours()), EgovWebUtil.filePathBlackList(fvo.getStreFileNm()));
	    int fSize = (int)uFile.length();

	    if (fSize > 0) {
		String mimetype = mime;

		//response.setBufferSize(fSize);	// OutOfMemeory 발생
		response.setContentType(mimetype);
		//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
		setDisposition(fvo.getOrignlFileNm(), "", request, response);
		response.setContentLength(fSize);

		/*
		 * FileCopyUtils.copy(in, response.getOutputStream());
		 * in.close(); 
		 * response.getOutputStream().flush();
		 * response.getOutputStream().close();
		 */
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
		    in = new BufferedInputStream(new FileInputStream(uFile));
		    out = new BufferedOutputStream(response.getOutputStream());

		    FileCopyUtils.copy(in, out);
		    out.flush();
		} catch (Exception ex) {
		    //ex.printStackTrace();
		    // 다음 Exception 무시 처리
		    // Connection reset by peer: socket write error
			infoLog("IGNORED: " + ex.getMessage());
		} finally {
		    if (in != null) {
			try {
			    in.close();
			} catch (Exception ignore) {
			    // no-op
				infoLog("IGNORED: " + ignore.getMessage());
			}
		    }
		    if (out != null) {
			try {
			    out.close();
			} catch (Exception ignore) {
			    // no-op
				infoLog("IGNORED: " + ignore.getMessage());
			}
		    }
		}

	    } else {
			response.setContentType("application/x-msdownload");
	
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
	    }
    }
}
