package egovframework.injeinc.boffice.ex.board.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
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

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.common.util.EgovStringUtil;


@SuppressWarnings("restriction")
@Controller
public class ContentFileCtr extends CmmLogCtr {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentFileCtr.class);
	
	@Resource(name = "ContentFileSvc") 
	private ContentFileSvc contentFileSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/common/board/ContentFileRmvAx.do")
	public void ContentFileRmvAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, ModelMap model) throws Exception {

			String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
			contentFileVo.setModId(userid);
			
			int bcIdx = contentFileVo.getBcIdx();
			int cbIdx = contentFileVo.getCbIdx();
			String streFileNm = contentFileVo.getStreFileNm();
			
			boolean result = false;
			String message = "";
			
			if(bcIdx > 0 && cbIdx > 0 && !streFileNm.equals("")) {
				
				contentFileSvc.removeContentFile(contentFileVo);	// 필수파라미터 : bcIdx, cbIdx, streFileNm
				
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
	
	@RequestMapping("/common/board/Download2015.do")
	public void ContentFileDownload2015(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo) throws Exception {

		String bcIdx = EgovStringUtil.isNullToString(request.getParameter("bcIdx"));
		String cbIdx = EgovStringUtil.isNullToString(request.getParameter("cbIdx"));
		String streFileNm = EgovStringUtil.isNullToString(request.getParameter("streFileNm"));
		System.out.println("bcIdx="+bcIdx+",cbIdx="+cbIdx+",streFileNm="+streFileNm);
		
		contentFileVo = contentFileSvc.retrieveContentFile(contentFileVo);	// 필수파라미터 : bcIdx, cbIdx, streFileNm
		if(contentFileVo == null) {
			throw new Exception("Download File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String downFileName = rootPath + contentFileVo.getFileStreCours() + contentFileVo.getStreFileNm();
		
		File file = new File(EgovWebUtil.filePathBlackList(downFileName));

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}
		
		int fSize = (int) file.length();
		
		if (fSize > 0) {
			FileInputStream fis = null;
			ServletOutputStream out = null;

			try {
	
				fis = new FileInputStream(file);
				String mimetype = "application/x-msdownload";
	 
				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				setDisposition(contentFileVo.getOrignlFileNm(), request, response);
				response.setContentLength(fSize);
				
				out = response.getOutputStream();
				
				byte[] buffer = new byte[8192];
				
				int bytesRead = 0;
				int bytesBuffered = 0;
				
				while((bytesRead = fis.read(buffer)) > -1) {
					out.write(buffer, 0, bytesRead);
					bytesBuffered += bytesRead;
					
					if(bytesBuffered > 1024*1024) {
						bytesBuffered = 0;
						out.flush();
					}
				}
				
				out.flush();
			}catch(FileNotFoundException fne) {
				EgovResourceCloseHelper.close(out);
				EgovResourceCloseHelper.close(fis);
				throw fne;
			}catch(IOException ioe) {
				EgovResourceCloseHelper.close(out);
				EgovResourceCloseHelper.close(fis);
				throw ioe;
			}catch(Exception e) {
				EgovResourceCloseHelper.close(out);
				EgovResourceCloseHelper.close(fis);
				throw e;
			}finally {
				EgovResourceCloseHelper.close(out);
				EgovResourceCloseHelper.close(fis);				
			}
			
		}else{
			throw new Exception("Download File is Zero Size.");
		}
	}
	
	@RequestMapping("/common/board/Download.do")
	public void ContentFileDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo) throws Exception {

		String bcIdx = EgovStringUtil.isNullToString(request.getParameter("bcIdx"));
		String cbIdx = EgovStringUtil.isNullToString(request.getParameter("cbIdx"));
		String streFileNm = EgovStringUtil.isNullToString(request.getParameter("streFileNm"));
		System.out.println("bcIdx="+bcIdx+",cbIdx="+cbIdx+",streFileNm="+streFileNm);
		
		contentFileVo = contentFileSvc.retrieveContentFile(contentFileVo);	// 필수파라미터 : bcIdx, cbIdx, streFileNm
		if(contentFileVo == null) {
			throw new Exception("Download File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String downFileName = rootPath + contentFileVo.getFileStreCours() + contentFileVo.getStreFileNm();
		
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
				setDisposition(contentFileVo.getOrignlFileNm(), request, response);
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
	
	@RequestMapping("/common/board/DownloadView.do")
	public String ContentFileDownloadView(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, ModelMap model) throws Exception {

		String bcIdx = EgovStringUtil.isNullToString(request.getParameter("bcIdx"));
		String cbIdx = EgovStringUtil.isNullToString(request.getParameter("cbIdx"));
		String streFileNm = EgovStringUtil.isNullToString(request.getParameter("streFileNm"));
		System.out.println("bcIdx="+bcIdx+",cbIdx="+cbIdx+",streFileNm="+streFileNm);
		
		
		
		contentFileVo = contentFileSvc.retrieveContentFile(contentFileVo);	// 필수파라미터 : bcIdx, cbIdx, streFileNm
		if(contentFileVo == null) {
			throw new Exception("Download File is null");
		}

		//String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String rootPath = "";
		
		String downFileName = rootPath + contentFileVo.getFileStreCours() + contentFileVo.getStreFileNm();
		
		String strofilename = streFileNm;
		
		return "injeinc/foffice/ex/webbook/docviewresult";
	}
	
	@RequestMapping("/common/board/DownloadThumbnail.do")
	public void ContentFileDownloadThumbnail(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo) throws Exception {

		String bcIdx = EgovStringUtil.isNullToString(request.getParameter("bcIdx"));
		String cbIdx = EgovStringUtil.isNullToString(request.getParameter("cbIdx"));
		String streFileNm = EgovStringUtil.isNullToString(request.getParameter("streFileNm"));
		System.out.println("bcIdx="+bcIdx+",cbIdx="+cbIdx+",streFileNm="+streFileNm);
		
		contentFileVo = contentFileSvc.retrieveContentFile(contentFileVo);	// 필수파라미터 : bcIdx, cbIdx, streFileNm
		if(contentFileVo == null) {
			throw new Exception("Download File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		//String downFileName = rootPath + contentFileVo.getFileStreCours() + contentFileVo.getStreFileNm();
		String downFileName = rootPath + contentFileVo.getThumbnail();
		
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
				setDisposition(contentFileVo.getOrignlFileNm(), request, response);
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
	
	@RequestMapping("/common/board/Thumbnail.do")
	public void ContentFileThumbnail(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo) throws Exception {

		contentFileVo = contentFileSvc.retrieveContentFile(contentFileVo);	// 필수파라미터 : bcIdx, cbIdx, streFileNm
		
		if(contentFileVo == null) {
			throw new Exception("Download File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String downFileName = rootPath + contentFileVo.getFileStreCours() + contentFileVo.getStreFileNm();
		
		File file = new File(EgovWebUtil.filePathBlackList(downFileName));
		int fSize = (int) file.length();
		
		if (fSize > 0) {
			
			int width = 174;
			int height = 120;
			
			String strWidth = EgovStringUtil.isNullToString(request.getParameter("width"));
			String strHeight = EgovStringUtil.isNullToString(request.getParameter("height"));
			
			if(!strWidth.equals("")) {
				width = Integer.parseInt(strWidth);
			}
			if(!strHeight.equals("")) {
				height = Integer.parseInt(strHeight);
			}
			
			String thumbNailStorePath = rootPath + Message.getMessage("Globals.fileThumbNailStorePath");
			String streFileNm = contentFileVo.getStreFileNm();
			String orgFilePath = rootPath + contentFileVo.getFileStreCours() + streFileNm;
			
			streFileNm = streFileNm.substring(0, streFileNm.indexOf("."))+"_"+width+"x"+height+".jpg";
			
			String thumbNailFilePath = thumbNailStorePath + streFileNm;
			File thumbNailStoreFolder = new File(EgovWebUtil.filePathBlackList(thumbNailStorePath));
			if (!thumbNailStoreFolder.exists()) {
				thumbNailStoreFolder.mkdirs();
			}
			File thumbNailFile = new File(EgovWebUtil.filePathBlackList(thumbNailFilePath));
			if (!thumbNailFile.exists()) {
				/*
				//Thumbnails.of(orgFilePath).size(width, height).toFile(thumbNailFilePath);
				*/
				boolean result = makeThumbNail3(orgFilePath, thumbNailFilePath, width, height);
				if(result) {
					System.out.println("썸네일 제작 성공");
				}else{
					System.out.println("썸네일 제작 실패");
				}
				
			}

			FileInputStream fis = null;
			BufferedInputStream in = null;
			ByteArrayOutputStream bStream = null;

			try {
				file = new File(thumbNailStorePath, streFileNm);
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
				
				setDisposition(contentFileVo.getStreFileNm(), request, response);
				
				response.setContentLength(bStream.size());
				bStream.writeTo(response.getOutputStream());

				response.getOutputStream().flush();
				response.getOutputStream().close();

				// 2011.10.10 보안점검 후속조치 끝
			}catch(FileNotFoundException fne) {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
				LOGGER.debug("IGNORED: " + fne.getMessage());
			}catch(IOException ioe) {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
				LOGGER.debug("IGNORED: " + ioe.getMessage());
			}catch(Exception e) {
				EgovResourceCloseHelper.close(bStream);
				EgovResourceCloseHelper.close(in);
				EgovResourceCloseHelper.close(fis);
				LOGGER.debug("IGNORED: " + e.getMessage());
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
	
		}catch(FileNotFoundException fne) {
			EgovResourceCloseHelper.close(os);
			LOGGER.debug("IGNORED: " + fne.getMessage());
		}catch(IOException ioe) {
			EgovResourceCloseHelper.close(os);
			LOGGER.debug("IGNORED: " + ioe.getMessage());
		}catch(Exception e) {
			EgovResourceCloseHelper.close(os);
			LOGGER.debug("IGNORED: " + e.getMessage());
		}finally {
			EgovResourceCloseHelper.close(os);
		}
		
		return result;
		
	}
	
	public boolean makeThumbNail2(String orgFilePath, String thumbNailFilePath, int width, int height) throws Exception {

		boolean result = false;
			
		BufferedImage thumbImage = ImageIO.read(new File(orgFilePath));
		
		BufferedImage resizedImage = getScaledInstance(thumbImage, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		ImageIO.write(resizedImage, "jpg", new File(thumbNailFilePath));
		
		result = true;
		
		return result;
		
	}
	
	public static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
		
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	
		BufferedImage ret = (BufferedImage)img;
		
		int w, h;
		
		if (higherQuality) {
			w = img.getWidth();
			h = img.getHeight();
		}else {
			w = targetWidth;
			h = targetHeight;
		}

		do {
			
			if (higherQuality && w > targetWidth) {
				w /= 2;
					
				if (w < targetWidth) {
					w = targetWidth;
					h = targetHeight;
				}
			}

			if (higherQuality && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					w = targetWidth;
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
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