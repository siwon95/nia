package egovframework.injeinc.boffice.sy.fileupload.service.impl;

import java.io.File;
import java.rmi.server.UID;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.sy.fileupload.dao.FileUploadDao;
import egovframework.injeinc.boffice.sy.fileupload.service.FileUploadSvc;
import egovframework.injeinc.boffice.sy.fileupload.vo.FileUploadVo;
import egovframework.injeinc.common.files.vo.CmmFilesVo;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("FileUploadSvc")
public class FileUploadImpl extends EgovAbstractServiceImpl implements FileUploadSvc{

	@Resource(name = "FileUploadDao")
	private FileUploadDao fileUploadDao;

	@Resource(name = "cmmFilesIdGnrService")
	private EgovIdGnrService idgenService;
	

	public void registFileUpload(HttpServletRequest request, FileUploadVo fileUploadVo) throws Exception {
		registFileUpload(request, fileUploadVo, "1");		
	}
	
	
	public void registFileUpload(HttpServletRequest request,	FileUploadVo fileUploadVo, String renameType) throws Exception {
		
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		// process files
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String uploadPath = fileUploadVo == null ? "" : fileUploadVo.getFuPath();
		
		if(uploadPath.equals("")) {
			throw new Exception("Upload Path is null");
		}
		
		File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath));

		if(!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> iter = files.entrySet().iterator();
		MultipartFile file = null;
		String fileName = "";
		String fileSystemName = "";
		String fileExt = "";

		while (iter.hasNext()) {

			Entry<String, MultipartFile> entry = iter.next();
			file = entry.getValue();
			fileName = file.getOriginalFilename();
			
			if (!fileName.equals("")) {
				
				int index = fileName.lastIndexOf(".");
				fileExt = fileName.substring(index + 1);
				
				if(renameType.equals("1")) {
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;	// 전자정부 UTIL 방법
				}else if(renameType.equals("2")) {
					if(fileUploadVo != null){
						fileSystemName = fileUploadVo.getFuGroup()+"_"+getTimeStamp()+"."+fileExt;				// 전자정부 컴포넌트 방법
					}
				}else if(renameType.equals("3")) {
					fileSystemName = new UID().toString().replace(':', '_').toLowerCase()+"."+fileExt;		// ASIS 방법
				}else{
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;	// 전자정부 UTIL 방법
				}
				if(fileUploadVo != null){
					fileUploadVo.setFuName(fileName.replace(" ", "_"));
					fileUploadVo.setFuRename(fileSystemName);
					fileUploadVo.setFuSize(Long.toString(file.getSize()));
					fileUploadVo.setFuMime(file.getContentType());
					fileUploadVo.setFuFormat(fileExt);
				}
			
				file.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName)));
				
				String cfIdx = idgenService.getNextStringId();
				if(fileUploadVo != null){
					fileUploadVo.setFuIdx(cfIdx);
				}
				fileUploadDao.createFileUpload(fileUploadVo);
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagFileUpload(FileUploadVo fileUploadVo) throws Exception {
		
		List<CmmFilesVo> result = fileUploadDao.selectPagFileUpload(fileUploadVo);
		int cnt = fileUploadDao.selectFileUploadCnt(fileUploadVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}


	public void removeFileUploadByGroupAndRename(FileUploadVo fileUploadVo) throws Exception {

		FileUploadVo result = fileUploadDao.selectFileUploadByGroupAndRename(fileUploadVo);
		
		if(result == null) {
			throw new Exception("Delete File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String cfPath = result.getFuPath();
		String fileName = result.getFuRename();
		
		fileUploadDao.deleteFileUpload(fileUploadVo);

		File fileDelete = new File(EgovWebUtil.filePathBlackList(rootPath + cfPath + fileName));
		
		fileDelete.delete();
		
	}

	private String getTimeStamp() throws Exception {

		String rtnStr = null;
	
		String pattern = "yyyyMMddhhmmssSSS";
		
		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		rtnStr = sdfCurrent.format(ts.getTime());
		
		return rtnStr;
	}


	@SuppressWarnings("rawtypes")
	public List retrieveListFileUpload(FileUploadVo fileUploadVo) throws Exception {
		return fileUploadDao.selectListFileUpload(fileUploadVo);
	}


	public FileUploadVo retrieveFileUploadByGroupAndRename(FileUploadVo fileUploadVo) throws Exception {
		return fileUploadDao.selectFileUploadByGroupAndRename(fileUploadVo);
	}


	public void modifyFileUploadDown(FileUploadVo fileUploadVo) throws Exception {
		fileUploadDao.updateFileUploadDown(fileUploadVo);
		
	}	

}
	
