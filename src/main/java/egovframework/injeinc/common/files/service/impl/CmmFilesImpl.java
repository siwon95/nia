package egovframework.injeinc.common.files.service.impl;

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
import egovframework.injeinc.common.files.dao.CmmFilesDao;
import egovframework.injeinc.common.files.service.CmmFilesSvc;
import egovframework.injeinc.common.files.vo.CmmFilesVo;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("CmmFilesSvc")
public class CmmFilesImpl extends EgovAbstractServiceImpl implements CmmFilesSvc {

	@Resource(name = "CmmFilesDao")
	private CmmFilesDao cmmFilesDao;

	@Resource(name = "cmmFilesIdGnrService")
	private EgovIdGnrService idgenService;

	public void registCmmFiles(HttpServletRequest request, CmmFilesVo cmmFilesVo) throws Exception {
		registCmmFiles(request, cmmFilesVo, "1");
	}

	public void registCmmFiles(HttpServletRequest request, CmmFilesVo cmmFilesVo, String renameType) throws Exception {
		
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		// process files
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String uploadPath = cmmFilesVo == null ? "" : cmmFilesVo.getCfPath();
		
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
					if(cmmFilesVo != null){
						fileSystemName = cmmFilesVo.getCfGroup()+"_"+getTimeStamp()+"."+fileExt;				// 전자정부 컴포넌트 방법
					}
				}else if(renameType.equals("3")) {
					fileSystemName = new UID().toString().replace(':', '_').toLowerCase()+"."+fileExt;		// ASIS 방법
				}else{
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;	// 전자정부 UTIL 방법
				}
				if(cmmFilesVo != null){
					cmmFilesVo.setCfName(fileName.replace(" ", "_"));
					cmmFilesVo.setCfRename(fileSystemName);
					cmmFilesVo.setCfSize(Long.toString(file.getSize()));
					cmmFilesVo.setCfMime(file.getContentType());
					cmmFilesVo.setCfFormat(fileExt);
				}
			
				file.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName)));
				
				String cfIdx = idgenService.getNextStringId();
				if(cmmFilesVo != null){
					cmmFilesVo.setCfIdx(cfIdx);
				}
				cmmFilesDao.createCmmFiles(cmmFilesVo);
				
			}
		}
	}

	public void removeCmmFiles(CmmFilesVo cmmFilesVo) throws Exception {
		
		CmmFilesVo result = cmmFilesDao.selectCmmFiles(cmmFilesVo);
		
		if(result == null) {
			throw new Exception("Delete File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String cfPath = result.getCfPath();
		String fileName = result.getCfRename();
		
		cmmFilesDao.deleteCmmFiles(cmmFilesVo);

		File fileDelete = new File(EgovWebUtil.filePathBlackList(rootPath + cfPath + fileName));
		
		fileDelete.delete();

	}

	public CmmFilesVo retrieveCmmFiles(CmmFilesVo cmmFilesVo) throws Exception {
		return cmmFilesDao.selectCmmFiles(cmmFilesVo);
	}

	public void removeCmmFilesByGroupAndRename(CmmFilesVo cmmFilesVo) throws Exception {
		
		CmmFilesVo result = cmmFilesDao.selectCmmFilesByGroupAndRename(cmmFilesVo);
		
		if(result == null) {
			throw new Exception("Delete File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String cfPath = result.getCfPath();
		String fileName = result.getCfRename();
		
		cmmFilesDao.deleteCmmFiles(cmmFilesVo);

		File fileDelete = new File(EgovWebUtil.filePathBlackList(rootPath + cfPath + fileName));
		
		fileDelete.delete();

	}

	public CmmFilesVo retrieveCmmFilesByGroupAndRename(CmmFilesVo cmmFilesVo) throws Exception {
		return cmmFilesDao.selectCmmFilesByGroupAndRename(cmmFilesVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagCmmFiles(CmmFilesVo cmmFilesVo) throws Exception {
		
		List<CmmFilesVo> result = cmmFilesDao.selectPagCmmFiles(cmmFilesVo);
		int cnt = cmmFilesDao.selectCmmFilesCnt(cmmFilesVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListCmmFiles(CmmFilesVo cmmFilesVo) throws Exception {
		return cmmFilesDao.selectListCmmFiles(cmmFilesVo);
	}

	public CmmFilesVo retrieveListCmmFilesOne(CmmFilesVo cmmFilesVo) throws Exception {
		return cmmFilesDao.selectListCmmFilesOne(cmmFilesVo);
	}

	public void modifyCmmFilesDown(CmmFilesVo cmmFilesVo) throws Exception {
		cmmFilesDao.updateCmmFilesDown(cmmFilesVo);
	}
	
	private String getTimeStamp() throws Exception {

		String rtnStr = null;
	
		String pattern = "yyyyMMddhhmmssSSS";
		
		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		rtnStr = sdfCurrent.format(ts.getTime());
		
		return rtnStr;
	}

	
	public void registCmmFiles(List<MultipartFile> files, CmmFilesVo cmmFilesVo)throws Exception {
		
		// process files
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String uploadPath = cmmFilesVo == null ? "" : cmmFilesVo.getCfPath();
		
		if(uploadPath.equals("")) {
			throw new Exception("Upload Path is null");
		}
		
		File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath));

		if(!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		String fileName = "";
		String fileSystemName = "";
		String fileExt = "";
		
		for (MultipartFile file : files) {
			fileName = file.getOriginalFilename();
			
			if (!fileName.equals("")) {
				
				int index = fileName.lastIndexOf(".");
				fileExt = fileName.substring(index + 1);
				
				fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;	// 전자정부 UTIL 방법
				
				if(cmmFilesVo != null){
					cmmFilesVo.setCfName(fileName.replace(" ", "_"));
					cmmFilesVo.setCfRename(fileSystemName);
					cmmFilesVo.setCfSize(Long.toString(file.getSize()));
					cmmFilesVo.setCfMime(file.getContentType());
					cmmFilesVo.setCfFormat(fileExt);
				}
			
				file.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName)));
				
				String cfIdx = idgenService.getNextStringId();
				if(cmmFilesVo != null){
					cmmFilesVo.setCfIdx(cfIdx);
				}
				cmmFilesDao.createCmmFiles(cmmFilesVo);
				
			}
		}
		
	}

	
	public List<CmmFilesVo> retrieveListCmmFilesByCfType(CmmFilesVo cmmFilesVo) throws Exception {
		return cmmFilesDao.selectListCmmFilesByCfType(cmmFilesVo);
	}

	
	public CmmFilesVo retrieveCmmFilesByCfIdx(String cfIdx) throws Exception {
		return cmmFilesDao.selectCmmFilesByCfIdx(cfIdx);
	}

}