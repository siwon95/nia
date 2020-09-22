package egovframework.injeinc.boffice.sy.mgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.mgr.dao.MgrAuthorityDao;
import egovframework.injeinc.boffice.sy.mgr.service.MgrAuthoritySvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrAuthorityVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MgrAuthoritySvc")
public class MgrAuthorityImpl extends EgovAbstractServiceImpl implements MgrAuthoritySvc {

	@Resource(name="MgrAuthorityDao")
	private MgrAuthorityDao mgrAuthorityDao;

	public void registMgrAuthority(MgrAuthorityVo mgrAuthorityVo, List<MgrAuthorityVo> list) throws Exception {
		mgrAuthorityDao.deleteMgrAuthority(mgrAuthorityVo);
		for(MgrAuthorityVo resultVo : list) {
			mgrAuthorityDao.createMgrAuthority(resultVo);
		}
		mgrAuthorityDao.createMgrAuthorityHistory(mgrAuthorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrAuthorityMenu(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		return mgrAuthorityDao.selectListMgrAuthorityMenu(mgrAuthorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrAuthorityBoard(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		return mgrAuthorityDao.selectListMgrAuthorityBoard(mgrAuthorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrAuthoritySite(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		return mgrAuthorityDao.selectListMgrAuthoritySite(mgrAuthorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public int selectMgrAuthorityChk(MgrAuthorityVo mgrAuthorityVo) throws Exception {
		return mgrAuthorityDao.selectMgrAuthorityChk(mgrAuthorityVo);
	}
}