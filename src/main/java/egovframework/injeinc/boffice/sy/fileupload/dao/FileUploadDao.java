package egovframework.injeinc.boffice.sy.fileupload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.injeinc.boffice.sy.fileupload.vo.FileUploadVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("FileUploadDao")
public class FileUploadDao extends EgovAbstractMapper {

	public void createFileUpload(FileUploadVo fileUploadVo) throws Exception{
		insert("FileUploadDao.insertFileUpload", fileUploadVo);
		
	}
	
	@SuppressWarnings("rawtypes")
	public List selectPagFileUpload(FileUploadVo fileUploadVo) throws Exception{
		return selectList("FileUploadDao.selectPagFileUpload", fileUploadVo);
	}

	public int selectFileUploadCnt(FileUploadVo fileUploadVo) throws Exception{
		return (Integer)selectOne("FileUploadDao.selectFileUploadCnt", fileUploadVo);
	}

	public FileUploadVo selectFileUploadByGroupAndRename(FileUploadVo fileUploadVo) throws Exception{
		return (FileUploadVo)selectOne("FileUploadDao.selectFileUploadByGroupAndRename", fileUploadVo);
	}

	public void deleteFileUpload(FileUploadVo fileUploadVo) throws Exception{
		delete("FileUploadDao.deleteFileUpload", fileUploadVo);
		
	}

	@SuppressWarnings("rawtypes")
	public List selectListFileUpload(FileUploadVo fileUploadVo) throws Exception{
		return selectList("FileUploadDao.selectListFileUpload", fileUploadVo);
	}

	public void updateFileUploadDown(FileUploadVo fileUploadVo) {
		update("FileUploadDao.updateFileUploadDown", fileUploadVo);		
	}


	



}
