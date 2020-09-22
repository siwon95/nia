package egovframework.injeinc.boffice.sy.mgr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.mgr.dao.MgrListDao;
import egovframework.injeinc.boffice.sy.mgr.service.MgrListSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrListVo;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;
import egovframework.injeinc.boffice.sy.mgr.vo.RoleVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("MgrListSvc")
public class MgrListImpl extends EgovAbstractServiceImpl implements MgrListSvc {

	@Resource(name = "MgrListDao")
	private MgrListDao mgrListDao;

	@Resource(name = "mgrListIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Resource(name = "roleIdGnrService")
	private EgovIdGnrService idgenService2;
	
	

	public String registMgrList(MgrListVo mgrListVo) throws Exception {
		
		String mgrIdx = idgenService.getNextStringId();
		if(mgrListVo != null){
			mgrListVo.setMgrIdx(mgrIdx);
		}
		mgrListDao.createMgrList(mgrListVo);
		mgrListDao.createMgrListHistory(mgrListVo);
		return mgrIdx;
	}
	
	public void registRole(RoleVo roleVo) throws Exception {
		
		String roleIdx = idgenService2.getNextStringId();
		if(roleVo != null){
			roleVo.setRoleIdx(roleIdx);
		}
		mgrListDao.createRole(roleVo);
		mgrListDao.createRoleHis(roleVo);
	}

	public void modifyMgrList(MgrListVo mgrListVo) throws Exception {
		mgrListDao.updateMgrList(mgrListVo);
		mgrListDao.createMgrListHistory(mgrListVo);
	}
	public void modifyRole(RoleVo roleVo) throws Exception {
		mgrListDao.updateRole(roleVo);
		mgrListDao.createRoleHis(roleVo);
	}

	public void removeMgrList(MgrListVo mgrListVo) throws Exception {
		mgrListDao.createMgrListHistory(mgrListVo);
		mgrListDao.deleteMgrList(mgrListVo);
	}
	
	public void removeRole(RoleVo roleVo) throws Exception {
		mgrListDao.createRoleHis(roleVo);
		mgrListDao.deleteRole(roleVo);
	}

	public MgrListVo retrieveMgrList(MgrListVo mgrListVo) throws Exception {
		return mgrListDao.selectMgrList(mgrListVo);
	}
	public RoleVo retrieveRole(RoleVo roleVo) throws Exception {
		return mgrListDao.selectRoleList(roleVo);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> retrieveRoleMap(RoleVo roleVo) throws Exception {
		
		List<RoleVo> result = mgrListDao.selectRoleMap(roleVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);

		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagMgrList(MgrListVo mgrListVo) throws Exception {
		
		List<MgrListVo> result = mgrListDao.selectPagMgrList(mgrListVo);
		int cnt = mgrListDao.selectMgrListCnt(mgrListVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagRole(RoleVo roleVo) throws Exception {
		
		List<MgrListVo> result = mgrListDao.selectPagRole(roleVo);
		int cnt = mgrListDao.selectRoleCnt(roleVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListMgrList(MgrListVo mgrListVo) throws Exception {
		return mgrListDao.selectListMgrList(mgrListVo);
	}

	public void modifyMgrListEtcInfo(MgrListVo mgrListVo) throws Exception {
		mgrListDao.updateMgrListEtcInfo(mgrListVo);
		mgrListDao.createMgrListHistory(mgrListVo);
	}

	public int retrieveMgrListCntCheckId(MgrListVo mgrListVo) throws Exception {
		return mgrListDao.selectMgrListCntCheckId(mgrListVo);
	}
	
	public int retrieveRoleListCntCheckCode(RoleVo roleVo) throws Exception {
		return mgrListDao.selectRoleListCntCheckCode(roleVo);
	}

	public void modifyMgrListMyInfo(MgrListVo mgrListVo) throws Exception {
		mgrListDao.updateMgrListMyInfo(mgrListVo);
	}

	public MgrListVo retrieveMgrListByName(String mgrName) throws Exception {
		return mgrListDao.selectMgrListByName(mgrName);
	}

	
	public void modifyMgrRoleUseYn(RoleVo roleVo) throws Exception {
		mgrListDao.updateMgrRoleUseYn(roleVo);
		mgrListDao.createRoleHis(roleVo);
	}
	
	public void modifyMgrRolePublishAuthYn(RoleVo roleVo) throws Exception {
		mgrListDao.updateMgrRolePublishAuthYn(roleVo);
		mgrListDao.createRoleHis(roleVo);
	}
	
	public void modifyRoleMgrSiteCd(RoleVo roleVo) throws Exception {
		mgrListDao.updateRoleMgrSiteCd(roleVo);
	}
	
	public int retrieveAuthMgrMenuListCnt(MgrVo vo) throws Exception {
		int cnt = mgrListDao.selectMatchMgrMenu(vo);
		return cnt;
	}
}