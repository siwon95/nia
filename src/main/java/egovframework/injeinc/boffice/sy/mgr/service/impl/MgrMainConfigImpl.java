package egovframework.injeinc.boffice.sy.mgr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.mgr.dao.MgrMainConfigDao;
import egovframework.injeinc.boffice.sy.mgr.service.MgrMainConfigSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainConfigVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("MgrMainConfigSvc")
public class MgrMainConfigImpl extends EgovAbstractServiceImpl implements MgrMainConfigSvc {

	@Resource(name = "MgrMainConfigDao")
	private MgrMainConfigDao mgrMainConfigDao;

	public void registMgrMainConfig(MgrMainConfigVo mgrMainConfigVo) throws Exception {
		mgrMainConfigDao.createMgrMainConfig(mgrMainConfigVo);
	}
	
	public void modifyMgrMainConfig(MgrMainConfigVo mgrMainConfigVo) throws Exception {
		mgrMainConfigDao.updateMgrMainConfig(mgrMainConfigVo);
	}

	public MgrMainConfigVo retrieveMgrMainConfig(MgrMainConfigVo mgrMainConfigVo) throws Exception {
		return mgrMainConfigDao.selectMgrMainConfig(mgrMainConfigVo);
	}
	
}