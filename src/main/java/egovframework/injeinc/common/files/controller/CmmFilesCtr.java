package egovframework.injeinc.common.files.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.imgscalr.Scalr;
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
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.EgovProperties;
import egovframework.cmm.service.FileVO;
import egovframework.injeinc.common.files.service.CmmFilesSvc;
import egovframework.injeinc.common.files.vo.CmmFilesVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

@SuppressWarnings("restriction")
@Controller
public class CmmFilesCtr extends CmmLogCtr {
	
	@Resource(name = "CmmFilesSvc")
	private CmmFilesSvc cmmFilesSvc;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/boffice/common/files/CmmFilesReg.do")
	public String CmmFilesReg(HttpServletRequest request, @ModelAttribute("CmmFilesVo") CmmFilesVo cmmFilesVo, ModelMap model) throws Exception {

		cmmFilesVo.setCfGroup("COMMON"); 											//그룹명은 프로그램에 따라 다르게 합니다.
		cmmFilesVo.setCfPkidx(""); 													//프로그램 고유키
		cmmFilesVo.setCfPath(Message.getMessage("common.file.upload.path"));		//업로드 URL 입력
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		cmmFilesVo.setRegId(userid);
		
		cmmFilesSvc.registCmmFiles(request, cmmFilesVo);
		
		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/common/files/CmmFilesList.do?siteCd="+cmmFilesVo.getSiteCd(), SVC_MSGE, model); 
	}
	
	@RequestMapping("/boffice/common/files/CmmFilesRmv.do")
	public String CmmFilesRmv(HttpServletRequest request, @ModelAttribute("CmmFilesVo") CmmFilesVo cmmFilesVo, ModelMap model) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		cmmFilesVo.setModId(userid);
		
		cmmFilesSvc.removeCmmFilesByGroupAndRename(cmmFilesVo);	// 필수파라미터 : cfIdx, cfGroup, cfRename
		//cmmFilesSvc.removeCmmFiles(cmmFilesVo);				// 필수파라미터 : cfIdx
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(cmmFilesVo.getPageIndex());
		addParam.append("&searchCondition=").append(cmmFilesVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(cmmFilesVo.getSearchKeyword());

		
		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/common/files/CmmFilesList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/common/files/CmmFilesRmvAx.do")
	public void CmmFilesRmvAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CmmFilesVo") CmmFilesVo cmmFilesVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		cmmFilesVo.setModId(userid);
		
		String cfIdx = cmmFilesVo.getCfIdx();
		String cfGroup = cmmFilesVo.getCfGroup();
		String cfRename = cmmFilesVo.getCfRename();
		
		boolean result = false;
		String message = "";
		
		if(!cfIdx.equals("") && !cfGroup.equals("") && !cfRename.equals("")) {
			
			cmmFilesSvc.removeCmmFilesByGroupAndRename(cmmFilesVo);	// 필수파라미터 : cfIdx, cfGroup, cfRename
			//cmmFilesSvc.removeCmmFiles(cmmFilesVo);				// 필수파라미터 : cfIdx
			
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
	
	@RequestMapping("/boffice/common/files/CmmFilesList.do")
	public String CmmFilesList(@ModelAttribute("CmmFilesVo") CmmFilesVo cmmFilesVo, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cmmFilesVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(cmmFilesVo.getPageUnit());
		paginationInfo.setPageSize(cmmFilesVo.getPageSize());
		
		cmmFilesVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cmmFilesVo.setLastIndex(paginationInfo.getLastRecordIndex());
		cmmFilesVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		cmmFilesVo.setCfGroup("COMMON");
		cmmFilesVo.setCfPkidx("");
		String siteCd = "";
		if(cmmFilesVo.getSiteCd()!=null){
			if(!cmmFilesVo.getSiteCd().replace("null", "").equals("")){
				siteCd = cmmFilesVo.getSiteCd().trim();
			}
		}
		
		Map<String, Object> map = cmmFilesSvc.retrievePagCmmFiles(cmmFilesVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "injeinc/common/files/cmm_files_list";
	}
	
	@RequestMapping("/common/files/Download.do")
	public void CmmFilesDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CmmFilesVo") CmmFilesVo cmmFilesVo) throws Exception {

		cmmFilesVo = cmmFilesSvc.retrieveCmmFilesByGroupAndRename(cmmFilesVo);	// 필수파라미터 : cfIdx, cfGroup, cfRename
		//cmmFilesVo = cmmFilesSvc.retrieveCmmFiles(cmmFilesVo);				// 필수파라미터 : cfIdx
		
		if(cmmFilesVo == null) {
			throw new Exception("Download File is null");
		}else {
			cmmFilesSvc.modifyCmmFilesDown(cmmFilesVo);
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String downFileName = rootPath + cmmFilesVo.getCfPath() + cmmFilesVo.getCfRename();
		
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
				setDisposition(cmmFilesVo.getCfName(), request, response);
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
	
	//이미지 실제 결로 다운로드
	@RequestMapping("/common/files/ImageDownload.do")
	public void ImageDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String filePath = "";
		if(request.getParameter("filePath")!=null){
			filePath = request.getParameter("filePath");
		}
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String downFileName = rootPath + filePath;
		
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
//				setDisposition(cmmFilesVo.getCfName(), request, response);
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
	
	@RequestMapping("/common/files/ThumbnailDownload.do")
	public void CmmThumbnailDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CmmFilesVo") CmmFilesVo cmmFilesVo) throws Exception {
		/*
		 * ?mode={smb/normal}&filePath=&fileName=&imageHeight=&imageWidth
		 */
		String mode = EgovStringUtil.isNullToString(request.getParameter("mode"));
		String thumbnailyn = EgovStringUtil.isNullToString(request.getParameter("thumbnailyn"));
		
		if(mode.equals("smb")){
			
			String nasIp = EgovProperties.getProperty("Globals.SmbIp");
			String nasId = EgovProperties.getProperty("Globals.SmbUserId");
			String nasPasswd = EgovProperties.getProperty("Globals.SmbUserPasswd");
			
			String filePath = EgovStringUtil.isNullToString(request.getParameter("filePath"));
			String fileName = EgovStringUtil.isNullToString(request.getParameter("fileName"));
			
			String url = "smb://"+nasId+":"+nasPasswd+"@"+nasIp+filePath+fileName;
		    SmbFile smbFile = new SmbFile(url);
		    
		    if (!smbFile.exists()) {
				throw new FileNotFoundException(smbFile.getName());
			}

			if (!smbFile.isFile()) {
				throw new FileNotFoundException(smbFile.getName());
			}
			
			int fSize = (int) smbFile.length();
			
			if (fSize > 0) {
				
				if(!thumbnailyn.equals("n")){
					BufferedInputStream fin = null;
					FileInputStream fis = null;
					
					
					int width = 150;
					int height = 100;
					
					String strWidth = EgovStringUtil.isNullToString(request.getParameter("width"));
					String strHeight = EgovStringUtil.isNullToString(request.getParameter("height"));
					
					if(!strWidth.equals("")) {
						width = Integer.parseInt(strWidth);
					}
					if(!strHeight.equals("")) {
						height = Integer.parseInt(strHeight);
					}
					
					Image srcImg = null;
					
					String suffix = smbFile.getName().substring(smbFile.getName().lastIndexOf('.')+1).toLowerCase();
					String fileOrgName = smbFile.getName().substring(0,smbFile.getName().lastIndexOf('.'));
					
					//if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")) {
					//}else {
						// BMP가 아닌 경우 ImageIcon을 활용해서 Image 생성
						// 이렇게 하는 이유는 getScaledInstance를 통해 구한 이미지를
						// PixelGrabber.grabPixels로 리사이즈 할때
						// 빠르게 처리하기 위함이다.
					//	srcImg = new ImageIcon(orgFilePath).getImage();
					//}
					
					String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
					
					String thumbNailStorePath = rootPath + Message.getMessage("Globals.fileThumbNailStorePath")+"NAS"+filePath;
					
					String thumbNailFileName = fileOrgName+"_"+width+"X"+height+".jpg";
					String thumbNailFilePath = thumbNailStorePath + thumbNailFileName;
					File thumbNailStoreFolder = new File(EgovWebUtil.filePathBlackList(thumbNailStorePath));
					if (!thumbNailStoreFolder.exists()) {
						thumbNailStoreFolder.mkdirs();
					}
					
					File imgFile = new File(thumbNailFilePath);
					
					if(!imgFile.exists()){
						srcImg = ImageIO.read(smbFile.getInputStream());
						Image imgTarget = srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
						int pixels[] = new int[width * height];
						PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, width, height, pixels, 0, width); 
						
						try {
							pg.grabPixels();
						}catch (InterruptedException e) {
							throw new IOException(e.getMessage());
						}
						
						BufferedImage destImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
						destImg.setRGB(0, 0, width, height, pixels, 0, width); 
						
						
						ImageIO.write(destImg, "jpg", imgFile);
					}
					
					
					FileInputStream fis2 = null;
					BufferedInputStream in = null;
					ByteArrayOutputStream bStream = null;
				
					
					try {
						fis2 = new FileInputStream(imgFile);
	
						in = new BufferedInputStream(fis2);
						bStream = new ByteArrayOutputStream();
	
						int imgByte;
						while ((imgByte = in.read()) != -1) {
							bStream.write(imgByte);
						}
	
						response.addHeader("Accept-Ranges", "bytes");
						response.setHeader("Content-Type", "image/jpeg");
	
						String mimetype = "application/x-msdownload";
						
	
						response.setBufferSize(bStream.size());
						response.setContentType(mimetype);
						setDisposition(smbFile.getName(), request, response);
						response.setContentLength(bStream.size());
						
						FileCopyUtils.copy(bStream.toByteArray(), response.getOutputStream());
						response.getOutputStream().flush();
						response.getOutputStream().close();
						
						
					}catch(FileNotFoundException e) {
						EgovResourceCloseHelper.close(fin);
						EgovResourceCloseHelper.close(fis);
						EgovResourceCloseHelper.close(fis2);
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}catch(IOException e) {
						EgovResourceCloseHelper.close(fin);
						EgovResourceCloseHelper.close(fis);
						EgovResourceCloseHelper.close(fis2);
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}catch(Exception e) {
						EgovResourceCloseHelper.close(fin);
						EgovResourceCloseHelper.close(fis);
						EgovResourceCloseHelper.close(fis2);
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}finally {
						EgovResourceCloseHelper.close(fin);
						EgovResourceCloseHelper.close(fis);
						EgovResourceCloseHelper.close(fis2);
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
					}
					
				}else{
					
					ByteArrayOutputStream bStream = null;
					SmbFileInputStream in = new SmbFileInputStream(smbFile);
					try {
						bStream = new ByteArrayOutputStream();
						byte[] b = new byte[8192];
						int imgByte;
						System.out.println("bStream.size():"+bStream.size());
						while ((imgByte = in.read(b)) != -1) {
							bStream.write(imgByte);
						}
						System.out.println("bStream.size():"+bStream.size());
						response.addHeader("Accept-Ranges", "bytes");
						response.setHeader("Content-Type", "image/jpeg");

						String mimetype = "application/x-msdownload";
						
						
						response.setBufferSize(bStream.size()*8192);
						response.setContentType(mimetype);
						setDisposition(smbFile.getName(), request, response);
						response.setContentLength(bStream.size()*8192);
						
						FileCopyUtils.copy(bStream.toByteArray(), response.getOutputStream());
						response.getOutputStream().flush();
						response.getOutputStream().close();
						
						
					}catch(FileNotFoundException e) {
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}catch(IOException e) {
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}catch(Exception e) {
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}finally {
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
					}
				}
			}
		}else if(mode.equals("nas")){
			
			String filePath = EgovStringUtil.isNullToString(request.getParameter("filePath"));
			String fileName = EgovStringUtil.isNullToString(request.getParameter("fileName"));
			
			String url = filePath+fileName;
		    File smbFile = new File(url);
		    
		    if (!smbFile.exists()) {
				throw new FileNotFoundException(smbFile.getName());
			}

			if (!smbFile.isFile()) {
				throw new FileNotFoundException(smbFile.getName());
			}
			
			int fSize = (int) smbFile.length();
			
			if (fSize > 0) {
				
				if(!thumbnailyn.equals("n")){
					BufferedInputStream fin = null;
					FileInputStream fis = null;
					
					
					int width = 150;
					int height = 100;
					
					String strWidth = EgovStringUtil.isNullToString(request.getParameter("width"));
					String strHeight = EgovStringUtil.isNullToString(request.getParameter("height"));
					
					if(!strWidth.equals("")) {
						width = Integer.parseInt(strWidth);
					}
					if(!strHeight.equals("")) {
						height = Integer.parseInt(strHeight);
					}
					
					Image srcImg = null;
					
					String suffix = smbFile.getName().substring(smbFile.getName().lastIndexOf('.')+1).toLowerCase();
					String fileOrgName = smbFile.getName().substring(0,smbFile.getName().lastIndexOf('.'));
					
					//if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")) {
					//}else {
						// BMP가 아닌 경우 ImageIcon을 활용해서 Image 생성
						// 이렇게 하는 이유는 getScaledInstance를 통해 구한 이미지를
						// PixelGrabber.grabPixels로 리사이즈 할때
						// 빠르게 처리하기 위함이다.
					//	srcImg = new ImageIcon(orgFilePath).getImage();
					//}
					
					String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
					
					String thumbNailStorePath = rootPath + Message.getMessage("Globals.fileThumbNailStorePath")+"NAS"+filePath;
					
					thumbNailStorePath = thumbNailStorePath.replaceAll("D:/nas","");
					
					System.out.println("thumbNailStorePath:"+thumbNailStorePath);
					
					String thumbNailFileName = fileOrgName+"_"+width+"X"+height+".jpg";
					String thumbNailFilePath = thumbNailStorePath + thumbNailFileName;
					File thumbNailStoreFolder = new File(EgovWebUtil.filePathBlackList(thumbNailStorePath));
					if (!thumbNailStoreFolder.exists()) {
						thumbNailStoreFolder.mkdirs();
					}
					
					File imgFile = new File(thumbNailFilePath);
					
					if(!imgFile.exists()){
						srcImg = ImageIO.read(smbFile);
						Image imgTarget = srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
						int pixels[] = new int[width * height];
						PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, width, height, pixels, 0, width); 
						
						try {
							pg.grabPixels();
						}catch (InterruptedException e) {
							throw new IOException(e.getMessage());
						}
						
						BufferedImage destImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
						destImg.setRGB(0, 0, width, height, pixels, 0, width); 
						
						
						ImageIO.write(destImg, "jpg", imgFile);
					}
					
					
					FileInputStream fis2 = null;
					BufferedInputStream in = null;
					ByteArrayOutputStream bStream = null;
				
					
					try {
						fis2 = new FileInputStream(imgFile);
	
						in = new BufferedInputStream(fis2);
						bStream = new ByteArrayOutputStream();
	
						int imgByte;
						while ((imgByte = in.read()) != -1) {
							bStream.write(imgByte);
						}
	
						response.addHeader("Accept-Ranges", "bytes");
						response.setHeader("Content-Type", "image/jpeg");
	
						String mimetype = "application/x-msdownload";
						
	
						response.setBufferSize(bStream.size());
						response.setContentType(mimetype);
						setDisposition(smbFile.getName(), request, response);
						response.setContentLength(bStream.size());
						
						FileCopyUtils.copy(bStream.toByteArray(), response.getOutputStream());
						response.getOutputStream().flush();
						response.getOutputStream().close();
						
						
					}catch(FileNotFoundException e) {
						EgovResourceCloseHelper.close(fin);
						EgovResourceCloseHelper.close(fis);
						EgovResourceCloseHelper.close(fis2);
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}catch(IOException e) {
						EgovResourceCloseHelper.close(fin);
						EgovResourceCloseHelper.close(fis);
						EgovResourceCloseHelper.close(fis2);
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}catch(Exception e) {
						EgovResourceCloseHelper.close(fin);
						EgovResourceCloseHelper.close(fis);
						EgovResourceCloseHelper.close(fis2);
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}finally {
						EgovResourceCloseHelper.close(fin);
						EgovResourceCloseHelper.close(fis);
						EgovResourceCloseHelper.close(fis2);
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
					}
					
				}else{
					
					ByteArrayOutputStream bStream = null;
					FileInputStream in = new FileInputStream(smbFile);
					try {
						bStream = new ByteArrayOutputStream();
						byte[] b = new byte[8192];
						int imgByte;
						System.out.println("bStream.size():"+bStream.size());
						while ((imgByte = in.read(b)) != -1) {
							bStream.write(imgByte);
						}
						System.out.println("bStream.size():"+bStream.size());
						response.addHeader("Accept-Ranges", "bytes");
						response.setHeader("Content-Type", "image/jpeg");

						String mimetype = "application/x-msdownload";
						
						
						response.setBufferSize(bStream.size()*8192);
						response.setContentType(mimetype);
						setDisposition(smbFile.getName(), request, response);
						response.setContentLength(bStream.size()*8192);
						
						FileCopyUtils.copy(bStream.toByteArray(), response.getOutputStream());
						response.getOutputStream().flush();
						response.getOutputStream().close();
						
						
					}catch(FileNotFoundException e) {
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}catch(IOException e) {
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}catch(Exception e) {
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
						debugLog("IGNORE: " + e.getMessage());
					}finally {
						EgovResourceCloseHelper.close(bStream);
						EgovResourceCloseHelper.close(in);
					}
				}
			}
		}
	}
	
	@RequestMapping("/common/files/ThumbCreateDown.do") // 주어진 width, height로 썸네일 생성 후 다운로드 
	public void CmmThumbCreateDown(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CmmFilesVo") CmmFilesVo cmmFilesVo) throws Exception {
		
		String opath = EgovStringUtil.isNullToString(request.getParameter("opath"));
		int twidth = Integer.parseInt(EgovStringUtil.isNullToString(request.getParameter("twidth")));
		//System.out.println("111111111111111111"+twidth);
		int theight = Integer.parseInt(EgovStringUtil.isNullToString(request.getParameter("theight")));
		//System.out.println("22222222222222222"+theight);
		String mode = EgovStringUtil.isNullToString(request.getParameter("mode")); // web 또는 nas
		//System.out.println("33333333333333333"+mode);
		String oFile = "";
		String oDir = "/";
		String oFileName = "";
		
		String tFile = "";
		String tDir = "";
		String tFileName = "";
		
		String[] arrOpath = opath.split("/");

		for(int k=1;k<(arrOpath.length-1);k++){
			oDir += arrOpath[k]+"/"; 	
		}

		oFileName = arrOpath[arrOpath.length-1];
		
		String rootFilePath="";
		if(mode.equals("nas")){
			rootFilePath = EgovProperties.getProperty("Globals.Nas");
		}else{
			rootFilePath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		}
		
		tDir = EgovProperties.getProperty("WasServer.ROOT_PATH");
		tDir += Message.getMessage("Globals.fileThumbNailStorePath");
		tDir += oDir.substring(1);
		
		oFile = rootFilePath + opath;
		
		tFileName = "t"+twidth+"X"+theight+"_"+oFileName;
		
		tFile = tDir + tFileName;
		File tFileExist = new File(tFile);
		
		System.out.println("oFile:"+oFile);
		System.out.println("tFile:"+tFile);
		
		
		File ofileExist = new File(oFile);
		if(ofileExist.exists()){
			if(!tFileExist.exists()){
				File fileDir = new File(tDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				
				EgovWebUtil.makeThumbNail(oFile, tFile, twidth, theight);
			}
			
			
			FileInputStream fis2 = null;
			BufferedInputStream in = null;
			ByteArrayOutputStream bStream = null;
		
			
			try {
				fis2 = new FileInputStream(tFileExist);
	
				in = new BufferedInputStream(fis2);
				bStream = new ByteArrayOutputStream();
	
				int imgByte;
				while ((imgByte = in.read()) != -1) {
					bStream.write(imgByte);
				}
	
				response.addHeader("Accept-Ranges", "bytes");
				response.setHeader("Content-Type", "image/jpeg");
	
				String mimetype = "application/x-msdownload";
				
	
				response.setBufferSize(bStream.size());
				response.setContentType(mimetype);
				setDisposition(tFileName, request, response);
				response.setContentLength(bStream.size());
				
				FileCopyUtils.copy(bStream.toByteArray(), response.getOutputStream());
				response.getOutputStream().flush();
				response.getOutputStream().close();
				
				
			}catch(FileNotFoundException e) {
				EgovResourceCloseHelper.close(fis2);
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				debugLog("IGNORE: " + e.getMessage());
			}catch(IOException e) {
				EgovResourceCloseHelper.close(fis2);
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				debugLog("IGNORE: " + e.getMessage());
			}catch(Exception e) {
				EgovResourceCloseHelper.close(fis2);
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				debugLog("IGNORE: " + e.getMessage());
			}finally {
				EgovResourceCloseHelper.close(fis2);
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
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
	
	@RequestMapping("/common/files/Thumbnail.do")
	public void CmmFilesThumbnail(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CmmFilesVo") CmmFilesVo cmmFilesVo) throws Exception {
System.out.println("##################### + /common/files/Thumbnail.do");
		cmmFilesVo = cmmFilesSvc.retrieveCmmFilesByGroupAndRename(cmmFilesVo);	// 필수파라미터 : cfIdx, cfGroup, cfRename
		
		if(cmmFilesVo == null) {
			String cfIdx = EgovStringUtil.isNullToString(request.getParameter("cfIdx"));
			String cfGroup = EgovStringUtil.isNullToString(request.getParameter("cfGroup"));
			String cfRename = EgovStringUtil.isNullToString(request.getParameter("cfRename"));
			System.out.println("cfIdx=>"+cfIdx+", cfGroup=>"+cfGroup+", cfRename=>"+cfRename);
			throw new Exception("Download File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String downFileName = rootPath + cmmFilesVo.getCfPath() + cmmFilesVo.getCfRename();
		
		File file = new File(EgovWebUtil.filePathBlackList(downFileName));
		int fSize = (int) file.length();
		
		if (fSize > 0) {
			
			int width = 196;
			int height = 220;
			
			String strWidth = EgovStringUtil.isNullToString(request.getParameter("width"));
			String strHeight = EgovStringUtil.isNullToString(request.getParameter("height"));
			
			if(!strWidth.equals("")) {
				width = Integer.parseInt(strWidth);
			}
			if(!strHeight.equals("")) {
				height = Integer.parseInt(strHeight);
			}
			
			String thumbNailStorePath = rootPath + Message.getMessage("Globals.fileThumbNailStorePath");
			String cfRename = cmmFilesVo.getCfRename();
			String orgFilePath = rootPath + cmmFilesVo.getCfPath() + cfRename;
			
			cfRename = cfRename.substring(0, cfRename.indexOf("."))+"_"+width+"x"+height+".jpg";
			
			String thumbNailFilePath = thumbNailStorePath + cfRename;
			File thumbNailStoreFolder = new File(EgovWebUtil.filePathBlackList(thumbNailStorePath));
			if (!thumbNailStoreFolder.exists()) {
				thumbNailStoreFolder.mkdirs();
			}
			File thumbNailFile = new File(EgovWebUtil.filePathBlackList(thumbNailFilePath));
			if (!thumbNailFile.exists()) {
				boolean result = makeThumbNail3(orgFilePath, thumbNailFilePath, width, height);
				if(result) {
					System.out.println("CMM 썸네일 제작 성공");
				}else{
					System.out.println("CMM 썸네일 제작 실패");
				}
			}

			FileInputStream fis = null;
			BufferedInputStream in = null;
			ByteArrayOutputStream bStream = null;

			try {
				file = new File(thumbNailStorePath, cfRename);
				fis = new FileInputStream(file);

				in = new BufferedInputStream(fis);
				bStream = new ByteArrayOutputStream();

				int imgByte;
				while ((imgByte = in.read()) != -1) {
					bStream.write(imgByte);
				}

				response.addHeader("Accept-Ranges", "bytes");
				response.setHeader("Content-Type", "image/jpeg");

				String mimetype = "application/x-msdownload";
				response.setContentType(mimetype);
				
				setDisposition(cmmFilesVo.getCfRename(), request, response);
				
				response.setContentLength(bStream.size());
				bStream.writeTo(response.getOutputStream());

				response.getOutputStream().flush();
				response.getOutputStream().close();

				// 2011.10.10 보안점검 후속조치 끝
			}catch(FileNotFoundException e) {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
				debugLog("IGNORE: " + e.getMessage());
			}catch(IOException e) {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
				debugLog("IGNORE: " + e.getMessage());
			}catch(Exception e) {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
				debugLog("IGNORE: " + e.getMessage());
			}finally {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
			}
			
		}else{
			throw new Exception("Download File is Zero Size.");
		}
	}
	
	@RequestMapping("/common/files/Thumbnail2.do")
	public void CmmFilesThumbnail2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String atchFileId = EgovStringUtil.isNullToString(request.getParameter("atchFileId"));
		String fileSn = EgovStringUtil.isNullToString(request.getParameter("fileSn"));
		
		FileVO vo = new FileVO();
		vo.setAtchFileId(atchFileId);
		vo.setFileSn(fileSn);
		FileVO fvo = fileService.selectImageFileInf(vo);
		
		if(fvo == null) {
			throw new Exception("Download File2 is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String downFileName = rootPath + fvo.getFileStreCours() + fvo.getStreFileNm();
		
		File file = new File(EgovWebUtil.filePathBlackList(downFileName));
		int fSize = (int) file.length();
		
		if (fSize > 0) {
			
			int width = 196;
			int height = 220;
			
			String strWidth = EgovStringUtil.isNullToString(request.getParameter("width"));
			String strHeight = EgovStringUtil.isNullToString(request.getParameter("height"));
			
			if(!strWidth.equals("")) {
				width = Integer.parseInt(strWidth);
			}
			if(!strHeight.equals("")) {
				height = Integer.parseInt(strHeight);
			}
			
			String thumbNailStorePath = rootPath + Message.getMessage("Globals.fileThumbNailStorePath");
			String cfRename = fvo.getStreFileNm();
			String orgFilePath = rootPath + fvo.getFileStreCours() + cfRename;
			
			cfRename = cfRename+"_"+width+"x"+height+".jpg";
			
			String thumbNailFilePath = thumbNailStorePath + cfRename;
			File thumbNailStoreFolder = new File(EgovWebUtil.filePathBlackList(thumbNailStorePath));
			if (!thumbNailStoreFolder.exists()) {
				thumbNailStoreFolder.mkdirs();
			}
			File thumbNailFile = new File(EgovWebUtil.filePathBlackList(thumbNailFilePath));
			if (!thumbNailFile.exists()) {
				boolean result = makeThumbNail3(orgFilePath, thumbNailFilePath, width, height);
				if(result) {
					System.out.println("CMM 썸네일 제작 성공");
				}else{
					System.out.println("CMM 썸네일 제작 실패");
				}
			}

			FileInputStream fis = null;
			BufferedInputStream in = null;
			ByteArrayOutputStream bStream = null;

			try {
				file = new File(thumbNailStorePath, cfRename);
				fis = new FileInputStream(file);

				in = new BufferedInputStream(fis);
				bStream = new ByteArrayOutputStream();

				int imgByte;
				while ((imgByte = in.read()) != -1) {
					bStream.write(imgByte);
				}

				response.addHeader("Accept-Ranges", "bytes");
				response.setHeader("Content-Type", "image/jpeg");

				String mimetype = "application/x-msdownload";
				response.setContentType(mimetype);
				
				setDisposition(cfRename, request, response);
				
				response.setContentLength(bStream.size());
				bStream.writeTo(response.getOutputStream());

				response.getOutputStream().flush();
				response.getOutputStream().close();

				// 2011.10.10 보안점검 후속조치 끝
			}catch(FileNotFoundException e) {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
				debugLog("IGNORE: " + e.getMessage());
			}catch(IOException e) {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
				debugLog("IGNORE: " + e.getMessage());
			}catch(Exception e) {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
				debugLog("IGNORE: " + e.getMessage());
			}finally {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
			}
			
		}else{
			throw new Exception("Download File is Zero Size.");
		}
	}
	
	public boolean makeThumbNail(String orgFilePath, String thumbNailFilePath, int width, int height) throws Exception {

		FileOutputStream os = null;
		boolean result = false;
		
		try {
			
			BufferedImage thumbImage = Scalr.resize(ImageIO.read(new File(orgFilePath)), width, height);
	
			os = new FileOutputStream(thumbNailFilePath);
	
			//JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
	
			//JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(thumbImage);
			//jep.setQuality(1f, true);
	
			//encoder.encode(thumbImage, jep);
			result = true;
	
		}catch(FileNotFoundException e) {
			EgovResourceCloseHelper.close(os);
			debugLog("IGNORE: " + e.getMessage());
		}catch(IOException e) {
			EgovResourceCloseHelper.close(os);
			debugLog("IGNORE: " + e.getMessage());
		}catch(Exception e) {
			EgovResourceCloseHelper.close(os);
			debugLog("IGNORE: " + e.getMessage());
		}finally {
			EgovResourceCloseHelper.close(os);
		}
		
		return result;
		
	}
	
	public boolean makeThumbNail3(String orgFilePath, String thumbNailFilePath, int width, int height) throws Exception {

		boolean result = false;
		
		Image srcImg = null;
		
		String suffix = orgFilePath.substring(orgFilePath.lastIndexOf('.')+1).toLowerCase();
		
		if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")) {
			srcImg = ImageIO.read(new File(orgFilePath));
		}else {
			// BMP가 아닌 경우 ImageIcon을 활용해서 Image 생성
			// 이렇게 하는 이유는 getScaledInstance를 통해 구한 이미지를
			// PixelGrabber.grabPixels로 리사이즈 할때
			// 빠르게 처리하기 위함이다.
			srcImg = new ImageIcon(orgFilePath).getImage();
		}
		
		Image imgTarget = srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		int pixels[] = new int[width * height];
		PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, width, height, pixels, 0, width); 
		
		try {
			pg.grabPixels();
		}catch (InterruptedException e) {
			throw new IOException(e.getMessage());
		}
		
		BufferedImage destImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
		destImg.setRGB(0, 0, width, height, pixels, 0, width); 
		
		ImageIO.write(destImg, "jpg", new File(thumbNailFilePath));
		
		return result;
	}
}