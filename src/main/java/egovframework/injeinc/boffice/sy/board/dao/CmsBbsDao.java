package egovframework.injeinc.boffice.sy.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;

@Repository("CmsBbsDao")
public class CmsBbsDao extends EgovAbstractMapper {
	
	public void createCmsBbs(CmsBbsVo cmsBbsVo) throws Exception {
		insert("CmsBbsDao.insertCmsBbs", cmsBbsVo);
	}
	
	public CmsBbsVo selectCmsBbs(CmsBbsVo cmsBbsVo) throws Exception {
		return (CmsBbsVo)selectOne("CmsBbsDao.selectCmsBbs", cmsBbsVo);
	}
	
	public List selectCmsBbsMypage(CmsBbsVo cmsBbsVo) throws Exception {
		return selectList("CmsBbsDao.selectCmsBbsMypage", cmsBbsVo);
	}
	
	public int updateCmsBbs(CmsBbsVo cmsBbsVo) throws Exception {
		return update("CmsBbsDao.updateCmsBbs", cmsBbsVo);
	}
	
	public int deleteCmsBbsList(CmsBbsVo cmsBbsVo) throws Exception {
		return update("CmsBbsDao.deleteCmsBbsList", cmsBbsVo);
	}
	
	public void deleteCmsBbs(CmsBbsVo cmsBbsVo) throws Exception {
		delete("CmsBbsDao.deleteCmsBbs", cmsBbsVo);
	}
	
	public int selectCmsBbsCnt(CmsBbsVo cmsBbsVo) throws Exception {
		return (Integer)selectOne("CmsBbsDao.selectCmsBbsCnt", cmsBbsVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListCmsBbs() throws Exception {
		return selectList("CmsBbsDao.selectListCmsBbs", "");
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListCmsBbsSite(CmsBbsVo cmsBbsVo) throws Exception {
		return selectList("CmsBbsDao.selectListCmsBbsSite", cmsBbsVo);
	}


	@SuppressWarnings("rawtypes")
	public List selectListCmsBbsTop() throws Exception {
		return selectList("CmsBbsDao.selectListCmsBbsTop", "");
	}
	
	public String retrieveSiteCd(String searchCbCd) throws Exception {
		return (String)selectOne("CmsBbsDao.selectSiteCd", searchCbCd);
	}
	
	public String retrieveGroup(String searchCbCd) throws Exception {
		return (String)selectOne("CmsBbsDao.selectGroup", searchCbCd);
	}
	
	public String retrieveCbIdx(String searchCbCd) throws Exception {
		return (String)selectOne("CmsBbsDao.selectCbIdx", searchCbCd);
	}

	public String retrieveGroupYn(String searchCbCd) throws Exception {
		return (String)selectOne("CmsBbsDao.selectGroupYn", searchCbCd);
	}
	
}