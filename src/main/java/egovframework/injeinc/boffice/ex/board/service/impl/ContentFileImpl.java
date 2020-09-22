package egovframework.injeinc.boffice.ex.board.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.DataOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.board.dao.ContentFileDao;
import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.injeinc.boffice.sy.board.service.BoardSvc;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.cmm.EgovWebUtil;


@Service("ContentFileSvc")
public class ContentFileImpl extends EgovAbstractServiceImpl implements ContentFileSvc {

	@Resource(name = "ContentFileDao")
	private ContentFileDao contentFileDao;
	
	@Resource(name = "BoardSvc")
	private BoardSvc boardSvc;

	public void registContentFile2(HttpServletRequest request, ContentFileVo contentFileVo ,BbsContentVo bbsContentVo) throws Exception {
		
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		int bcidx = 0;
		int cbidx = 0;
		if(contentFileVo != null){
			bcidx = contentFileVo.getBcIdx();
			cbidx = contentFileVo.getCbIdx();
		}
		
		if(bcidx == 0 || cbidx == 0) {
			return ;
		}
		
		// process files
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadPath = Message.getMessage("board.file.upload.path");
		
		File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + cbidx + "/"));
		
		if(!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		
		BoardVo boardVo = new BoardVo();
		boardVo.setCbIdx(cbidx);
		boardVo = boardSvc.retrieveBoard(boardVo);
		int thumbnailWidth = 0;
		int thumbnailHeight = 0;
		if(EgovStringUtil.isNumeric(boardVo.getThumbnailWidth())){
			thumbnailWidth = Integer.parseInt(boardVo.getThumbnailWidth());
		}
		if(EgovStringUtil.isNumeric(boardVo.getThumbnailHeight())){
			thumbnailHeight = Integer.parseInt(boardVo.getThumbnailHeight());
		}
		Iterator<Entry<String, MultipartFile>> iter = files.entrySet().iterator();
		MultipartFile file = null;
		String fileName = "";
		String fileSystemName = "";
		String fileExt = "";
		if(request.getParameterValues("nasFilePath") != null){
			for(int i=0;i<request.getParameterValues("nasFilePath").length;i++){
				String filePath =  request.getParameterValues("nasFilePath")[i].toString();
				filePath = filePath.replaceAll("%3A",":");
				filePath = filePath.replaceAll("%2F","/");
				
				File nasFile = new File(EgovWebUtil.filePathBlackList(filePath));
				
				fileName = nasFile.getName();
				int index = fileName.lastIndexOf(".");
				fileExt = fileName.substring(index + 1);
				fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;
				
				File nasCopyFile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + cbidx + "/" + fileSystemName));
				
				FileCopyUtils.copy(nasFile, nasCopyFile);
	
				contentFileVo.setFileStreCours(uploadPath + cbidx + "/");
				contentFileVo.setStreFileNm(fileSystemName);
				contentFileVo.setOrignlFileNm(fileName.replace(" ", "_"));
				contentFileVo.setFileExtsn(fileExt.toLowerCase());
				contentFileVo.setFileSize(Long.toString(nasFile.length()));
				
				if(thumbnailWidth>0 && thumbnailHeight>0){ //썸네일 사이즈가 있을경우
					
					
					if(fileExt.toLowerCase().equals("jpg") || fileExt.toLowerCase().equals("gif") || fileExt.toLowerCase().equals("bmp")){
						String orgFilePath = filePath;
						String thumbNailFilePath = rootPath + uploadPath + cbidx + "/thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ fileSystemName;
						EgovWebUtil.makeThumbNail(orgFilePath,thumbNailFilePath, thumbnailWidth, thumbnailHeight);
						contentFileVo.setThumbnail(uploadPath + cbidx + "/thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ fileSystemName);
					}
				}

				contentFileDao.createContentFile(contentFileVo);
			}
		}
		if(bbsContentVo.getTempname() != null && !bbsContentVo.getTempname().equals("")){			
			String fileStreCours = Message.getMessage("board.file.upload.path");
			String cbFolder = Integer.toString(cbidx);
			
			uploadPath = uploadPath + cbFolder + "/";
			
			  if (!saveFolder.exists() || saveFolder.isFile()){
				  saveFolder.mkdirs();
			  }
			System.out.println(bbsContentVo.getTempname());
			System.out.println(bbsContentVo.getTempsname());
			System.out.println(bbsContentVo.getTempsize());
			
			String tempname[] = bbsContentVo.getTempname().split("[|]");
			String tempsname[] = bbsContentVo.getTempsname().split("[|]");
			String tempsize[] = bbsContentVo.getTempsize().split("[|]");
			System.out.println("----------");
			for(int i=0; i < tempname.length; i++){				
				File tempfile = new File(rootPath+uploadPath+tempsname[i]);
				String fileExtsn = tempsname[i].substring(tempsname[i].lastIndexOf(".") + 1, tempsname[i].length());
				if(Integer.parseInt(tempsize[i]) > Integer.parseInt(contentFileVo.getFileMaxSize()) * 1024 * 1024){
					Map FileRmvMap = new HashMap<String, String>();
					FileRmvMap.put("bcIdx", bbsContentVo.getBcIdx());
					FileRmvMap.put("cbIdx", bbsContentVo.getCbIdx());
					FileRmvMap.put("regId", bbsContentVo.getRegId());
					contentFileVo.setBcIdx(bbsContentVo.getBcIdx());
					contentFileVo.setCbIdx(bbsContentVo.getCbIdx());
					contentFileVo.setStreFileNm(tempsname[i]);
					removeContentFile(contentFileVo);
					throw new FileUploadException();
				}else{
					if(bbsContentVo != null){ 
						System.out.println("-----------------------");
						contentFileVo.setFileStreCours(uploadPath);
						contentFileVo.setStreFileNm(tempsname[i]);
						contentFileVo.setOrignlFileNm(tempname[i]);
						contentFileVo.setFileExtsn(fileExtsn.toLowerCase());
						contentFileVo.setFileSize(tempsize[i]);
						System.out.println("-----------------------");
					}		
					tempfile.renameTo(new File(rootPath + uploadPath + tempsname[i]));
					if(thumbnailWidth>0 && thumbnailHeight>0){ //썸네일 사이즈가 있을경우
						if(fileExtsn.toLowerCase().equals("jpg") || fileExtsn.toLowerCase().equals("gif")){
							String orgFilePath = rootPath + uploadPath + "" + tempsname[i];
							String thumbNailFilePath = rootPath + uploadPath + "thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ tempsname[i];
							EgovWebUtil.makeThumbNail(orgFilePath,thumbNailFilePath, thumbnailWidth, thumbnailHeight);
							contentFileVo.setThumbnail(uploadPath + "thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ tempsname[i]);
						}
					}
					contentFileDao.createContentFile(contentFileVo);
				}				
			}
		}else{
			while (iter.hasNext()) {

				Entry<String, MultipartFile> entry = iter.next();
				file = entry.getValue();
				fileName = file.getOriginalFilename();
				
				if((int)file.getSize() > Integer.parseInt(contentFileVo.getFileMaxSize()) * 1024 * 1024){
					throw new FileUploadException();
				}else{
					if (!fileName.equals("")) {
						
						int index = fileName.lastIndexOf(".");
						fileExt = fileName.substring(index + 1);
						fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;
						
						if(contentFileVo != null){
							contentFileVo.setFileStreCours(uploadPath + cbidx + "/");
							contentFileVo.setStreFileNm(fileSystemName);
							contentFileVo.setOrignlFileNm(fileName.replace(" ", "_"));
							contentFileVo.setFileExtsn(fileExt.toLowerCase());
							contentFileVo.setFileSize(Long.toString(file.getSize()));
						}
						
						file.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + cbidx + "/" + fileSystemName)));
						
						if(thumbnailWidth>0 && thumbnailHeight>0){ //썸네일 사이즈가 있을경우
							if(fileExt.toLowerCase().equals("jpg") || fileExt.toLowerCase().equals("gif")){
								String orgFilePath = rootPath + uploadPath + cbidx + "/" + fileSystemName;
								String thumbNailFilePath = rootPath + uploadPath + cbidx + "/thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ fileSystemName;
								EgovWebUtil.makeThumbNail(orgFilePath,thumbNailFilePath, thumbnailWidth, thumbnailHeight);
								contentFileVo.setThumbnail(uploadPath + cbidx + "/thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ fileSystemName);
							}
						}
		
						contentFileDao.createContentFile(contentFileVo);
					}
				}
			}
		}
		
		
		
	}
	
	public void registContentFile(HttpServletRequest request, ContentFileVo contentFileVo) throws Exception {
		
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		int bcidx = 0;
		int cbidx = 0;
		if(contentFileVo != null){
			bcidx = contentFileVo.getBcIdx();
			cbidx = contentFileVo.getCbIdx();
		}
		
		if(bcidx == 0 || cbidx == 0) {
			return ;
		}
		
		// process files
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadPath = Message.getMessage("board.file.upload.path");
		
		File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + cbidx + "/"));
		
		if(!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		
		BoardVo boardVo = new BoardVo();
		boardVo.setCbIdx(cbidx);
		boardVo = boardSvc.retrieveBoard(boardVo);
		int thumbnailWidth = 0;
		int thumbnailHeight = 0;
		if(EgovStringUtil.isNumeric(boardVo.getThumbnailWidth())){
			thumbnailWidth = Integer.parseInt(boardVo.getThumbnailWidth());
		}
		if(EgovStringUtil.isNumeric(boardVo.getThumbnailHeight())){
			thumbnailHeight = Integer.parseInt(boardVo.getThumbnailHeight());
		}
		Iterator<Entry<String, MultipartFile>> iter = files.entrySet().iterator();
		MultipartFile file = null;
		String fileName = "";
		String fileSystemName = "";
		String fileExt = "";
		if(request.getParameterValues("nasFilePath") != null){
			for(int i=0;i<request.getParameterValues("nasFilePath").length;i++){
				String filePath =  request.getParameterValues("nasFilePath")[i].toString();
				filePath = filePath.replaceAll("%3A",":");
				filePath = filePath.replaceAll("%2F","/");
				
				File nasFile = new File(EgovWebUtil.filePathBlackList(filePath));
				
				fileName = nasFile.getName();
				int index = fileName.lastIndexOf(".");
				fileExt = fileName.substring(index + 1);
				fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;
				
				File nasCopyFile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + cbidx + "/" + fileSystemName));
				
				FileCopyUtils.copy(nasFile, nasCopyFile);
	
				contentFileVo.setFileStreCours(uploadPath + cbidx + "/");
				contentFileVo.setStreFileNm(fileSystemName);
				contentFileVo.setOrignlFileNm(fileName.replace(" ", "_"));
				contentFileVo.setFileExtsn(fileExt.toLowerCase());
				contentFileVo.setFileSize(Long.toString(nasFile.length()));
				
				if(thumbnailWidth>0 && thumbnailHeight>0){ //썸네일 사이즈가 있을경우
					
					
					if(fileExt.toLowerCase().equals("jpg") || fileExt.toLowerCase().equals("gif")){
						String orgFilePath = filePath;
						String thumbNailFilePath = rootPath + uploadPath + cbidx + "/thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ fileSystemName;
						EgovWebUtil.makeThumbNail(orgFilePath,thumbNailFilePath, thumbnailWidth, thumbnailHeight);
						contentFileVo.setThumbnail(uploadPath + cbidx + "/thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ fileSystemName);
					}
				}

				contentFileDao.createContentFile(contentFileVo);
			}
		}
		
		while (iter.hasNext()) {

			Entry<String, MultipartFile> entry = iter.next();
			file = entry.getValue();
			fileName = file.getOriginalFilename();
			
			if((int)file.getSize() > Integer.parseInt(contentFileVo.getFileMaxSize()) * 1024 * 1024){
				throw new FileUploadException();
			}else{
				if (!fileName.equals("")) {
					
					int index = fileName.lastIndexOf(".");
					fileExt = fileName.substring(index + 1);
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;
					
					if(contentFileVo != null){
						contentFileVo.setFileStreCours(uploadPath + cbidx + "/");
						contentFileVo.setStreFileNm(fileSystemName);
						contentFileVo.setOrignlFileNm(fileName.replace(" ", "_"));
						contentFileVo.setFileExtsn(fileExt.toLowerCase());
						contentFileVo.setFileSize(Long.toString(file.getSize()));
					}
					
					file.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + cbidx + "/" + fileSystemName)));
					
					if(thumbnailWidth>0 && thumbnailHeight>0){ //썸네일 사이즈가 있을경우
						if(fileExt.toLowerCase().equals("jpg") || fileExt.toLowerCase().equals("gif")){
							String orgFilePath = rootPath + uploadPath + cbidx + "/" + fileSystemName;
							String thumbNailFilePath = rootPath + uploadPath + cbidx + "/thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ fileSystemName;
							EgovWebUtil.makeThumbNail(orgFilePath,thumbNailFilePath, thumbnailWidth, thumbnailHeight);
							contentFileVo.setThumbnail(uploadPath + cbidx + "/thumb_"+thumbnailWidth+"X"+thumbnailHeight+"_"+ fileSystemName);
						}
					}
	
					contentFileDao.createContentFile(contentFileVo);
				}
			}
		}
		
		
		
		
	}

	public void removeContentFile(ContentFileVo contentFileVo) throws Exception {
		
		ContentFileVo result = contentFileDao.selectContentFile(contentFileVo);
		
		if(result == null) {
			throw new Exception("Delete File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String fileStreCours = result.getFileStreCours();
		String streFileNm = result.getStreFileNm();
		
		contentFileDao.deleteContentFile(contentFileVo);

		File fileDelete = new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours + streFileNm));
		
		fileDelete.delete();
	}

	public ContentFileVo retrieveContentFile(ContentFileVo contentFileVo) throws Exception {
		return contentFileDao.selectContentFile(contentFileVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListContentFile(ContentFileVo contentFileVo) throws Exception {
		return contentFileDao.selectListContentFile(contentFileVo);
	}
	
	public ContentFileVo retrieveListContentFileOne(ContentFileVo contentFileVo) throws Exception {
		return contentFileDao.selectListContentFileOne(contentFileVo);
	}

	public void moveContentFile(ContentFileVo contentFileVo, int cbIdx, int bcIdx) throws Exception {
		contentFileDao.deleteContentFile(contentFileVo);
		if(contentFileVo != null){
			contentFileVo.setCbIdx(cbIdx);
			contentFileVo.setBcIdx(bcIdx);
		}
		contentFileDao.createContentFile(contentFileVo);
	}

}