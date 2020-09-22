package egovframework.injeinc.boffice.sy.file.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("FileDao")
public class FileDao extends EgovAbstractMapper {
	
	public boolean createFile(HashMap<String, Object> param) throws Exception{
		Integer nResult = 0;
		nResult = update("FileDao.insertFile", param);
		return nResult > 0;
	}
	
}