package egovframework.injeinc.boffice.sy.mgr.service;

import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainConfigVo;

public interface MgrMainConfigSvc {
	public void registMgrMainConfig(MgrMainConfigVo mgrMainConfigVo) throws Exception;
	public void modifyMgrMainConfig(MgrMainConfigVo mgrMainConfigVo) throws Exception;
	public MgrMainConfigVo retrieveMgrMainConfig(MgrMainConfigVo mgrMainConfigVo) throws Exception;

}

