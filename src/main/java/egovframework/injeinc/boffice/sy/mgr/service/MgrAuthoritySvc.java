package egovframework.injeinc.boffice.sy.mgr.service;

import java.util.List;

import egovframework.injeinc.boffice.sy.mgr.vo.MgrAuthorityVo;

public interface MgrAuthoritySvc {
	
	public void registMgrAuthority(MgrAuthorityVo mgrAuthorityVo, List<MgrAuthorityVo> list) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrAuthorityMenu(MgrAuthorityVo mgrAuthorityVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrAuthorityBoard(MgrAuthorityVo mgrAuthorityVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrAuthoritySite(MgrAuthorityVo mgrAuthorityVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public int selectMgrAuthorityChk(MgrAuthorityVo mgrAuthorityVo) throws Exception;
	
}