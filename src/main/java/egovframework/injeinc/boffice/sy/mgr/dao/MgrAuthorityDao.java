package egovframework.injeinc.boffice.sy.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrAuthorityVo;

@Repository("MgrAuthorityDao")
public class MgrAuthorityDao extends EgovAbstractMapper {
	
	public void createMgrAuthority(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		insert("MgrAuthorityDao.insertMgrAuthority", mgrAuthorityVo);
	}
	
	public void deleteMgrAuthority(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		delete("MgrAuthorityDao.deleteMgrAuthority", mgrAuthorityVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListMgrAuthorityMenu(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		return selectList("MgrAuthorityDao.selectListMgrAuthorityMenu", mgrAuthorityVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListMgrAuthorityBoard(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		return selectList("MgrAuthorityDao.selectListMgrAuthorityBoard", mgrAuthorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListMgrAuthoritySite(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		return selectList("MgrAuthorityDao.selectListMgrAuthoritySite", mgrAuthorityVo);
	}
	
	public void createMgrAuthorityHistory(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		insert("MgrAuthorityDao.insertMgrAuthorityHistory", mgrAuthorityVo);
	}
	
	public int selectMgrAuthorityChk(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		return (Integer)selectOne("MgrAuthorityDao.selectMgrAuthorityChk", mgrAuthorityVo);
	}
	
}