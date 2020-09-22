package egovframework.cmm;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.EgovProperties;
import egovframework.cmm.service.FileVO;
import egovframework.injeinc.common.util.EgovStringUtil;


/**
 * @Class Name : EgovImageProcessController.java
 * @Description :
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 4. 2.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 4. 2.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
@Controller
public class EgovImageProcessController extends HttpServlet {

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	private static final Logger LOG = Logger.getLogger(EgovImageProcessController.class.getName());

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
	private void setDisposition(String filename, String dispositionPrefixStr,HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			if (filename != null) {
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
			}
		} else if (browser.equals("Opera")) {
			if (filename != null) {
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
			}
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			if (filename != null) {
				for (int i = 0; i < filename.length(); i++) {
					if (filename != null) {
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
			// throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	/**
	 * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
	 *
	 * @param atchFileId
	 * @param fileSn
	 * @param sessionVO
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/getImage.do")
	public void getImageInf(ModelMap model, Map<String, Object> commandMap, HttpServletResponse response, HttpServletRequest request) throws Exception {

		// @RequestParam("atchFileId") String atchFileId,
		// @RequestParam("fileSn") String fileSn,
		/*
		 * String atchFileId = (String)commandMap.get("atchFileId"); String
		 * fileSn = (String)commandMap.get("fileSn"); String thumbNail =
		 * (String)commandMap.get("thumbNail"); String preView =
		 * (String)commandMap.get("preView");
		 */
		String atchFileId = EgovStringUtil.nullConvert(request.getParameter("atchFileId"));
		String fileSn = EgovStringUtil.nullConvert(request.getParameter("fileSn"));
		String thumbNail = EgovStringUtil.nullConvert(request.getParameter("thumbNail"));
		String preView = EgovStringUtil.nullConvert(request.getParameter("preView"));

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");

		FileVO vo = new FileVO();
		vo.setAtchFileId(atchFileId);
		vo.setFileSn(fileSn);
		// FileVO fvo = fileService.selectFileInf(vo);
		FileVO fvo;
		if (thumbNail != null && "ok".equals(thumbNail)) {
			/* ThumbNail 이미지 만들기(2015.07.01) */
			fvo = fileService.selectImageFileInf(vo);
			if (fvo != null) {

				String thumbNailStorePath = rootPath + Message.getMessage("Globals.fileThumbNailStorePath");
				String orgFilePath = rootPath + fvo.getFileStreCours() + fvo.getStreFileNm();
				String thumbNailFilePath = thumbNailStorePath + fvo.getStreFileNm();
				int width = 196;
				int height = 220;
				File thumbNailStoreFolder = new File(EgovWebUtil.filePathBlackList(thumbNailStorePath));
				if (!thumbNailStoreFolder.exists()) {
					thumbNailStoreFolder.mkdirs();
				}
				File thumbNailFile = new File(EgovWebUtil.filePathBlackList(thumbNailFilePath));
				if (!thumbNailFile.exists()) {
					EgovWebUtil.makeThumbNail(orgFilePath, thumbNailFilePath,width, height);
				}
				fvo.setFileStreCours(Message.getMessage("Globals.fileThumbNailStorePath"));
			}
		} else if (preView != null && "ok".equals(preView)) {
			/* PreView 이미지 만들기(2015.07.01) */
			fvo = fileService.selectImageFileInf(vo);
			if (fvo != null) {

				String preViewStorePath = rootPath + Message.getMessage("Globals.filePreViewStorePath");
				String orgFilePath = rootPath + fvo.getFileStreCours() + fvo.getStreFileNm();
				String preViewFilePath = preViewStorePath + fvo.getStreFileNm();
				int width = 653;
				int height = 583;
				File preViewStoreFolder = new File(EgovWebUtil.filePathBlackList(preViewStorePath));
				if (!preViewStoreFolder.exists()) {
					preViewStoreFolder.mkdirs();
				}
				File preViewFile = new File(EgovWebUtil.filePathBlackList(preViewFilePath));
				if (!preViewFile.exists()) {
					EgovWebUtil.makeThumbNail(orgFilePath, preViewFilePath,width, height);
				}
				fvo.setFileStreCours(preViewStorePath);
			}
		} else {
			fvo = fileService.selectFileInf(vo);
		}

		// String fileLoaction = fvo.getFileStreCours() + fvo.getStreFileNm();

		// 2011.10.10 보안점검 후속조치
		File file = null;
		FileInputStream fis = null;

		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;

		try {
			file = new File(EgovWebUtil.filePathBlackList(rootPath + fvo.getFileStreCours()),EgovWebUtil.filePathBlackList(fvo.getStreFileNm()));
			fis = new FileInputStream(file);

			in = new BufferedInputStream(fis);
			bStream = new ByteArrayOutputStream();

			int imgByte;
			while ((imgByte = in.read()) != -1) {
				bStream.write(imgByte);
			}

			String type = "";

			if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
				if ("jpg".equals(fvo.getFileExtsn().toLowerCase())) {
					type = "image/jpeg";
				} else {
					type = "image/" + fvo.getFileExtsn().toLowerCase();
				}
				type = "image/" + fvo.getFileExtsn().toLowerCase();

			} else {
				LOG.debug("Image fileType is null.");
			}
			type = type.replaceAll("\r", "").replaceAll("\n", "");

			response.setHeader("Content-Type", type);

			setDisposition(fvo.getOrignlFileNm(), "inline", request, response);
			String mimetype = "application/x-msdownload";
			response.setContentType(mimetype);

			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();

			// 2011.10.10 보안점검 후속조치 끝
		} finally {
			if (bStream != null) {
				try {
					bStream.close();
				} catch (Exception ignore) {
					// System.out.println("IGNORE: " + ignore);
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception ignore) {
					// System.out.println("IGNORE: " + ignore);
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ignore) {
					// System.out.println("IGNORE: " + ignore);
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
		}
	}

}