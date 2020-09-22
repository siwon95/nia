package egovframework.injeinc.boffice.sy.mgr.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrListVo;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;
import egovframework.injeinc.boffice.sy.mgr.vo.RoleVo;

@Repository("MgrListDao")
public class MgrListDao extends EgovAbstractMapper {
	
	public void createMgrList(MgrListVo mgrListVo) throws Exception {
		insert("MgrListDao.insertMgrList", mgrListVo);
	}
	
	public void createRole(RoleVo roleVo) throws Exception {
		insert("MgrListDao.insertRole", roleVo);
	}
	
	public void updateMgrList(MgrListVo mgrListVo) throws Exception {
		update("MgrListDao.updateMgrList", mgrListVo);
	}
	
	public void updateRole(RoleVo roleVo) throws Exception {
		update("MgrListDao.updateRole", roleVo);
	}
	
	public void updateRoleMgrSiteCd(RoleVo roleVo) throws Exception {
		update("MgrListDao.updateRoleMgrSiteCd", roleVo);
	}
	
	public void updateMgrRoleUseYn(RoleVo roleVo) throws Exception {
		update("MgrListDao.updateMgrRoleUseYn", roleVo);
	}
	
	public void updateMgrRolePublishAuthYn(RoleVo roleVo) throws Exception {
		update("MgrListDao.updateMgrRolePublishAuthYn", roleVo);
	}
	
	public void deleteMgrList(MgrListVo mgrListVo) throws Exception {
		delete("MgrListDao.deleteMgrList", mgrListVo);
	}
	
	public void deleteRole(RoleVo roleVo) throws Exception {
		delete("MgrListDao.deleteRole", roleVo);
	}
	
	public MgrListVo selectMgrList(MgrListVo mgrListVo) throws Exception {
		return (MgrListVo)selectOne("MgrListDao.selectMgrList", mgrListVo);
	}
	
	public RoleVo selectRoleList(RoleVo roleVo) throws Exception {
		return (RoleVo)selectOne("MgrListDao.selectRoleList", roleVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagMgrList(MgrListVo mgrListVo) throws Exception {
		return selectList("MgrListDao.selectPagMgrList", mgrListVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectPagRole(RoleVo roleVo) throws Exception {
		return selectList("MgrListDao.selectPagRole", roleVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectRoleMap(RoleVo roleVo) throws Exception {
		return selectList("MgrListDao.selectRoleMap", roleVo);
	}
	
	public int selectMgrListCnt(MgrListVo mgrListVo) throws Exception {
		return (Integer)selectOne("MgrListDao.selectMgrListCnt", mgrListVo);
	}
	public int selectRoleCnt(RoleVo roleVo) throws Exception {
		return (Integer)selectOne("MgrListDao.selectRoleCnt", roleVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListMgrList(MgrListVo mgrListVo) throws Exception {
		return selectList("MgrListDao.selectListMgrList", mgrListVo);
	}
	
	public void updateMgrListEtcInfo(MgrListVo mgrListVo) throws Exception {
		update("MgrListDao.updateMgrListEtcInfo", mgrListVo);
	}
	
	public void createMgrListHistory(MgrListVo mgrListVo) throws Exception {
		insert("MgrListDao.insertMgrListHistory", mgrListVo);
	}
	
	public int selectMgrListCntCheckId(MgrListVo mgrListVo) throws Exception {
		return (Integer)selectOne("MgrListDao.selectMgrListCntCheckId", mgrListVo);
	}
	
	public int selectRoleListCntCheckCode(RoleVo roleVo) throws Exception {
		return (Integer)selectOne("MgrListDao.selectRoleListCntCheckCode", roleVo);
	}
	
	public void updateMgrListMyInfo(MgrListVo mgrListVo) throws Exception {
		update("MgrListDao.updateMgrListMyInfo", mgrListVo);
	}
	
	public MgrListVo selectMgrListByName(String mgrName) throws Exception {
		return (MgrListVo)selectOne("MgrListDao.selectMgrListByName", mgrName);
	}

	public void createRoleHis(RoleVo roleVo) throws Exception {
		insert("MgrListDao.insertRoleHistory", roleVo);
	}
	
	public int selectMatchMgrMenu(MgrVo vo) throws Exception {
		return (Integer) selectOne("MgrListDao.selectMatchMgrMenu", vo);
	}
}