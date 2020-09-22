package egovframework.injeinc.boffice.cn.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



/*import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;*/
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.file.service.EzFileSvc;
import egovframework.injeinc.boffice.cn.file.vo.EzFileVo;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.common.util.EgovDateUtil;
import egovframework.injeinc.boffice.cn.common.UtilSvc;

public class Util extends CmmLogCtr {
	@Resource(name = "UtilSvc")
	private UtilSvc utilSvc;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
	
	String rootPath = "";
	
	
	public String fileWriteRootPath(HttpServletRequest request, String inPath, String inFileName,  String inContent, String inRootPath) {
		rootPath = inRootPath;
		return fileWrite(request, inPath, inFileName,  inContent);
	}
	
	@SuppressWarnings("unused")
	public String fileWrite(HttpServletRequest request, String inPath, String inFileName,  String inContent) {
		String[] arrInPath = null;
		String realPath = "";
		if(inPath != null){
			arrInPath = inPath.split("/");
		}
		realPath = request.getSession().getServletContext().getRealPath(inPath);
		
		if(rootPath.equals("")){
			rootPath = request.getSession().getServletContext().getRealPath("/");
		}
		
		String tmpRealPath1 = "";
		String tmpRealPath2 = "";
		for(int i=1;i<arrInPath.length;i++){
			tmpRealPath1 += "/"+arrInPath[i];
			tmpRealPath2 =  rootPath + tmpRealPath1;
			File file = new File(tmpRealPath2);
			if(!file.exists()){
				file.mkdir();
			}
		}
		String chkText = "yes";
		
		String fullFilePath = tmpRealPath2 +"/"+ inFileName;

		OutputStreamWriter osw = null;
		
		try {
			
			osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(fullFilePath)), "UTF-8");
			if(inContent != null){
				char[] bytes = inContent.toCharArray();
				osw.write(bytes, 0, bytes.length);
			}
						
		}catch(IOException e) {
			EgovResourceCloseHelper.close(osw);
			chkText = e.getMessage();
		}finally{
			if(osw != null) {
				try {
					osw.flush();
					osw.close();
				} catch (IOException e) {
					chkText = e.getMessage();
				}
			}
		}
		
		return chkText ;
	}
	
	@SuppressWarnings("unused")
	public String fileWriteAll(String inRootPath,String inPath, String inFileName,  String inContent) {
		String[] arrInPath = null;
		String realPath = "";
		if(inPath != null){
			arrInPath = inPath.split("/");
		}
		realPath = inPath;
		
		
		String tmpRealPath1 = "";
		String tmpRealPath2 = "";
		for(int i=1;i<arrInPath.length;i++){
			tmpRealPath1 += "/"+arrInPath[i];
			tmpRealPath2 =  inRootPath + tmpRealPath1;
			File file = new File(tmpRealPath2);
			if(!file.exists()){
				file.mkdir();
			}
		}
		String chkText = "yes";
		
		String fullFilePath = tmpRealPath2 +"/"+ inFileName;

		OutputStreamWriter osw = null;
		
		try {
			
			osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(fullFilePath)), "UTF-8");
			if(inContent != null){
				char[] bytes = inContent.toCharArray();
				osw.write(bytes, 0, bytes.length);
			}
						
		}catch(IOException e) {
			EgovResourceCloseHelper.close(osw);
			chkText = e.getMessage();
		}finally{
			if(osw != null) {
				try {
					osw.flush();
					osw.close();
				} catch (IOException e) {
					chkText = e.getMessage();
				}
			}
		}
		
		return chkText ;
	}
	
	public static boolean isEmpty(String inValue){
			boolean trueFalse = false;
			if((inValue+"").replaceAll("null", "").equals("")){
				trueFalse = true;
			}
			return trueFalse;
	}
	
	public static String getSiteCode(HttpServletRequest request) throws Exception {
		String strUrl = request.getRequestURL().toString()+"//";
		String[] arrStrUrl = strUrl.split("/");
		return arrStrUrl[4];
	}
	
	public static List<Element> makeMenuXml(String inSiteCd,HttpServletRequest request) throws Exception {
		SAXBuilder builder = new SAXBuilder();

		String filePath = request.getSession().getServletContext().getRealPath("/site/"+inSiteCd+"/menu.xml");
		File file = new File(filePath);
		
		
		InputStream ins = null;
		InputStreamReader isr = null;
		
		Element element = null;
		List<Element> elements = null;
		
		try {
			ins = new FileInputStream(file);
			isr = new InputStreamReader(ins,"UTF-8");
			Document doc = builder.build(isr);
		
			element = doc.getRootElement();
			elements = element.getChildren();
		}catch(FileNotFoundException fne) {
			LOGGER.debug("IGNORED: " + fne.getMessage());
			EgovResourceCloseHelper.close(isr);
			EgovResourceCloseHelper.close(ins);
		}catch(IOException ioe) {
			LOGGER.debug("IGNORED: " + ioe.getMessage());
			EgovResourceCloseHelper.close(isr);
			EgovResourceCloseHelper.close(ins);
		}catch(Exception e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
			EgovResourceCloseHelper.close(isr);
			EgovResourceCloseHelper.close(ins);
		}finally{
			EgovResourceCloseHelper.close(isr);
			EgovResourceCloseHelper.close(ins);
		}
		return elements;
	}
	
	public static List<Element> makeMenuLayoutXml(String inSiteCd,HttpServletRequest request) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		String filePath = request.getSession().getServletContext().getRealPath("/site/"+inSiteCd+"/menu_layout.xml");
		File file = new File(filePath);
		
		Document doc = builder.build(file);
		
		Element element = doc.getRootElement();
		List<Element> elements = element.getChildren();
		return elements;
	}

	
@SuppressWarnings({ "static-access", "unused" })
public String ezFileUpload(HttpServletRequest request, String rootPath, EzFileSvc ezFileSvc, String uploadPath, String inFormName, String inAtchFileId) throws Exception {
	String returnId = "";
	EgovDateUtil egovDateUtil = new EgovDateUtil();
		
		String inPath ="";
		String date = egovDateUtil.getToday();
		
		String saveFileName = egovDateUtil.yearMonthDayTime();
		
		inPath += uploadPath+"/"+date+"/";
		String[] arrInPath = inPath.split("/");
		
		String tmpRealPath1 = "";
		String tmpRealPath2 = "";
		for(int i=1;i<arrInPath.length;i++){
			if(i!=1){
				tmpRealPath1 += "/";
			}
			tmpRealPath1 += arrInPath[i];
			tmpRealPath2 =  rootPath + tmpRealPath1;
			File file = new File(tmpRealPath2);
			if(!file.exists()){
				file.mkdir();
			}
		}

		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		
		EzFileVo ezFileVo = new EzFileVo();
		//다중파일 업로드
		List<MultipartFile> files = multipartRequest.getFiles(inFormName); 
		uploadPath = tmpRealPath2;
		String fileId = "";
		if(ezFileSvc != null){
			fileId = ezFileSvc.retrieveEzFileKey(ezFileVo);
		}
		//System.out.println("fileId:"+fileId);
		int intMax = Integer.parseInt(fileId.replaceAll("FILE_",""))+1;
		
		ezFileVo.setAtchFileId(intMax+"");
	
		int maxSn = -1;
		if(!(inAtchFileId+"").replaceAll("null", "").equals("")){
			ezFileVo.setAtchFileId(inAtchFileId);
			//System.out.println("del"+inFormName+"Sn:"+request.getParameter("del"+inFormName+"Sn"));
			if(!(request.getParameter("del"+inFormName+"Sn")+"").replaceAll("null", "").equals("")){
				String[] delFileSn = request.getParameterValues("del"+inFormName+"Sn");
					for(int i=0;i<delFileSn.length;i++){
						ezFileVo.setFileSn(delFileSn[i].toString());
						if(ezFileSvc != null){
							ezFileSvc.removeEzFile(ezFileVo);
							ezFileSvc.removeEzFileSort(ezFileVo);
						}
				}
			}
		}
		
		if(ezFileSvc != null){
			maxSn = Integer.parseInt(ezFileSvc.retrieveEzFileDetailSn(ezFileVo))+1;
		}
		
		try{
			int fileCount = 0;
			for(MultipartFile file : files){
				if(!file.isEmpty()){
					if((inAtchFileId+"").replaceAll("null", "").equals("")){
						if(fileCount==0){
							if(ezFileSvc != null){
								ezFileSvc.registEzFile(ezFileVo, request,"");
								returnId = ezFileSvc.retrieveEzFileKey(ezFileVo);
							}
						}
					}else{
						intMax = Integer.parseInt((ezFileVo.getAtchFileId()+"").replaceAll("FILE_",""));
						ezFileVo.setAtchFileId(intMax+"");
					}
					    
					String fileName = file.getOriginalFilename();
					
					String fileSize = Integer.toString((int)file.getSize());
           
					
					String filePath = uploadPath;
					
					String imgExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
					
					String saveFileName2 = saveFileName +fileCount+ "." + imgExt;
					File ufile = new File(uploadPath + "/" + saveFileName2);
					
					ezFileVo.setFileSn((maxSn+fileCount)+"");
					ezFileVo.setFileStreCours(uploadPath+"/");
					ezFileVo.setStreFileNm(saveFileName2);
					ezFileVo.setOrignlFileNm(fileName);
					ezFileVo.setFileCn("");
					ezFileVo.setFileExtsn(imgExt);
					ezFileVo.setFileSize(fileSize);
					
					if(ezFileSvc != null){
						ezFileSvc.registEzFileDetail(ezFileVo);
					}
					
					//파일 저장    
					try {

						file.transferTo((ufile));
					} catch (IllegalStateException e) {                    
						LOGGER.debug("IGNORED: " + e.getMessage());
					} catch (IOException e) {
						LOGGER.debug("IGNORED: " + e.getMessage());
					}
					fileCount++;
				}
			} 
			
		}catch(NumberFormatException e){
			LOGGER.debug("IGNORED: " + e.getMessage());
		}catch(IllegalStateException e){
			LOGGER.debug("IGNORED: " + e.getMessage());
		}catch(IOException e){
			LOGGER.debug("IGNORED: " + e.getMessage());
		}catch(Exception e){
			LOGGER.debug("IGNORED: " + e.getMessage());
		}
		ezFileSvc = null;
		
		return returnId;
	}
	
}
