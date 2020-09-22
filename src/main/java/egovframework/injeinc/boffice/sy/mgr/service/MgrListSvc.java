package egovframework.injeinc.boffice.sy.mgr.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.boffice.sy.mgr.vo.MgrListVo;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;
import egovframework.injeinc.boffice.sy.mgr.vo.RoleVo;

public interface MgrListSvc {
	
	public String registMgrList(MgrListVo mgrListVo) throws Exception;
	public void registRole(RoleVo roleVo) throws Exception;
	public void modifyMgrList(MgrListVo mgrListVo) throws Exception;
	public void modifyRole(RoleVo roleVo) throws Exception;
	public void modifyRoleMgrSiteCd(RoleVo roleVo) throws Exception;
	public void modifyMgrRoleUseYn(RoleVo roleVo) throws Exception;
	public void modifyMgrRolePublishAuthYn(RoleVo roleVo) throws Exception;
	public void removeMgrList(MgrListVo mgrListVo) throws Exception;
	public void removeRole(RoleVo roleVo) throws Exception;
	public MgrListVo retrieveMgrList(MgrListVo mgrListVo) throws Exception;
	public RoleVo retrieveRole(RoleVo roleVo) throws Exception;
	public Map<String, Object> retrievePagMgrList(MgrListVo mgrListVo) throws Exception;
	public Map<String, Object> retrievePagRole(RoleVo roleVo) throws Exception;
	public Map<String, Object> retrieveRoleMap(RoleVo roleVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrList(MgrListVo mgrListVo) throws Exception;
	public void modifyMgrListEtcInfo(MgrListVo mgrListVo) throws Exception;
	public int retrieveMgrListCntCheckId(MgrListVo mgrListVo) throws Exception;
	public int retrieveRoleListCntCheckCode(RoleVo roleVo) throws Exception;
	public void modifyMgrListMyInfo(MgrListVo mgrListVo) throws Exception;
	public MgrListVo retrieveMgrListByName(String mgrName) throws Exception;
	
	public int retrieveAuthMgrMenuListCnt(MgrVo vo) throws Exception;
	
}