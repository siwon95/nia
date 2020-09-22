package egovframework.injeinc.boffice.pledge.service.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.pledge.dao.PledgeFileDao;
import egovframework.injeinc.boffice.pledge.service.PledgeFileSvc;
import egovframework.injeinc.boffice.pledge.vo.PledgeFileVo;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("PledgeFileSvc")
public class PledgeFileImpl extends EgovAbstractServiceImpl implements PledgeFileSvc {

	@Resource(name="PledgeFileDao")
	private PledgeFileDao pledgeFileDao;
	
	public void registPledgeFile(HttpServletRequest request, PledgeFileVo pledgeFileVo) throws Exception {
		
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		int plIdx = 0;
		if(pledgeFileVo != null){
			plIdx = pledgeFileVo.getPlIdx();
		}
		
		if(plIdx == 0) {
			return ;
		}
		
		// process files
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadPath = "/upload/pledge/";
		
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
			
			if((int)file.getSize() > Integer.parseInt(pledgeFileVo.getFileMaxSize()) * 1024 * 1024){
				throw new FileUploadException();
			}else{
				if (!fileName.equals("")) {
					
					int index = fileName.lastIndexOf(".");
					fileExt = fileName.substring(index + 1);
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;
					
					if(pledgeFileVo != null){
						pledgeFileVo.setFileStreCours(uploadPath);
						pledgeFileVo.setStreFileNm(fileSystemName);
						pledgeFileVo.setOrignlFileNm(fileName.replace(" ", "_"));
						pledgeFileVo.setFileExtsn(fileExt.toLowerCase());
						pledgeFileVo.setFileSize(Long.toString(file.getSize()));
					}
					
					file.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName)));
	
					pledgeFileDao.createPledgeFile(pledgeFileVo);
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListPledgeFile(PledgeFileVo pledgeFileVo) throws Exception {
		return pledgeFileDao.selectListPledgeFile(pledgeFileVo);
	}
	public PledgeFileVo retrievePledgeFile(PledgeFileVo pledgeFileVo) throws Exception {
		return pledgeFileDao.selectPledgeFile(pledgeFileVo);
	}
	
	public void removeContentFile(PledgeFileVo pledgeFileVo) throws Exception {
		
		PledgeFileVo result = pledgeFileDao.selectPledgeFile(pledgeFileVo);
		
		if(result == null) {
			throw new Exception("Delete File is null");
		}

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String fileStreCours = result.getFileStreCours();
		String streFileNm = result.getStreFileNm();
		
		pledgeFileDao.deletePledgeFile(pledgeFileVo);

		File fileDelete = new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours + streFileNm));
		
		fileDelete.delete();
	}
}