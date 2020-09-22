package egovframework.injeinc.boffice.sy.mgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.mgr.dao.MgrMainDao;
import egovframework.injeinc.boffice.sy.mgr.service.MgrMainSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("MgrMainSvc")
public class MgrMainImpl extends EgovAbstractServiceImpl implements MgrMainSvc {

	@Resource(name = "MgrMainDao")
	private MgrMainDao mgrMainDao;

	@Resource(name = "mgrListIdGnrService")
	private EgovIdGnrService idgenService;
	
	
	public List retrieveMgrMainMinwonList(MgrMainVo mgrMainVo) throws Exception {
		return mgrMainDao.selectMgrMainMinwonList(mgrMainVo);
	}
	
	public List retrieveMgrMainBoardList(MgrMainVo mgrMainVo) throws Exception {
		return mgrMainDao.selectMgrMainBoardList(mgrMainVo);
	}

	public List retrieveMgrMainCommExpandList(MgrMainVo mgrMainVo) throws Exception {
		return mgrMainDao.selectMgrMainCommExpandList(mgrMainVo);
	}

	public List retrieveMgrMainCommEventList(MgrMainVo mgrMainVo) throws Exception {
		return mgrMainDao.selectMgrMainCommEventList(mgrMainVo);
	}
	
	public int retrieveMgrMainMinwonCount(MgrMainVo mgrMainVo) throws Exception {
		return mgrMainDao.selectMgrMainMinwonCount(mgrMainVo);
	}
	
	public int retrieveMgrMainBoardCount(MgrMainVo mgrMainVo) throws Exception {
		return mgrMainDao.selectMgrMainBoardCount(mgrMainVo);
	}
	
	public int retrieveMgrMainCommCount(MgrMainVo mgrMainVo) throws Exception {
		return mgrMainDao.selectMgrMainCommCount(mgrMainVo);
	}

	
}