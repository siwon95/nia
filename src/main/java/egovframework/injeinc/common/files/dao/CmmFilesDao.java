package egovframework.injeinc.common.files.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.common.files.vo.CmmFilesVo;

@Repository("CmmFilesDao")
public class CmmFilesDao extends EgovAbstractMapper {
	
	public void createCmmFiles(CmmFilesVo cmmFilesVo) throws Exception {
		insert("CmmFilesDao.insertCmmFiles", cmmFilesVo);
	}
	
	public void deleteCmmFiles(CmmFilesVo cmmFilesVo) throws Exception {
		delete("CmmFilesDao.deleteCmmFiles", cmmFilesVo);
	}
	
	public CmmFilesVo selectCmmFiles(CmmFilesVo cmmFilesVo) throws Exception {
		return (CmmFilesVo)selectOne("CmmFilesDao.selectCmmFiles", cmmFilesVo);
	}
	
	public void deleteCmmFilesByGroupAndRename(CmmFilesVo cmmFilesVo) throws Exception {
		delete("CmmFilesDao.deleteCmmFilesByGroupAndRename", cmmFilesVo);
	}
	
	public CmmFilesVo selectCmmFilesByGroupAndRename(CmmFilesVo cmmFilesVo) throws Exception {
		return (CmmFilesVo)selectOne("CmmFilesDao.selectCmmFilesByGroupAndRename", cmmFilesVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagCmmFiles(CmmFilesVo cmmFilesVo) throws Exception {
		return selectList("CmmFilesDao.selectPagCmmFiles", cmmFilesVo);
	}
	
	public int selectCmmFilesCnt(CmmFilesVo cmmFilesVo) throws Exception {
		return (Integer)selectOne("CmmFilesDao.selectCmmFilesCnt", cmmFilesVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListCmmFiles(CmmFilesVo cmmFilesVo) throws Exception {
		return selectList("CmmFilesDao.selectListCmmFiles", cmmFilesVo);
	}
	
	public CmmFilesVo selectListCmmFilesOne(CmmFilesVo cmmFilesVo) throws Exception {
		return (CmmFilesVo)selectOne("CmmFilesDao.selectListCmmFilesOne", cmmFilesVo);
	}
	
	public void updateCmmFilesDown(CmmFilesVo cmmFilesVo) throws Exception {
		update("CmmFilesDao.updateCmmFilesDown", cmmFilesVo);
	}

	public List<CmmFilesVo> selectListCmmFilesByCfType(CmmFilesVo cmmFilesVo) throws Exception {
		return selectList("CmmFilesDao.selectListCmmFilesByCfType", cmmFilesVo);
	}

	public CmmFilesVo selectCmmFilesByCfIdx(String cfIdx) throws Exception {
		return (CmmFilesVo) selectOne("CmmFilesDao.selectCmmFilesByCfIdx", cfIdx);
	}
	
}