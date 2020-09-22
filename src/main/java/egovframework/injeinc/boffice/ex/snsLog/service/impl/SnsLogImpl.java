package egovframework.injeinc.boffice.ex.snsLog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.snsLog.dao.SnsLogDao;
import egovframework.injeinc.boffice.ex.snsLog.service.SnsLogSvc;
import egovframework.injeinc.boffice.ex.snsLog.vo.SnsLogVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("SnsLogSvc")
public class SnsLogImpl extends EgovAbstractServiceImpl implements SnsLogSvc {

	@Resource(name = "SnsLogDao")
	private SnsLogDao snsLogDao;

	public void registSnsLog(SnsLogVo snsLogVo) throws Exception {
		snsLogDao.createSnsLog(snsLogVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListSnsLog(SnsLogVo snsLogVo) throws Exception {
		return snsLogDao.selectListSnsLog(snsLogVo);
	}
	
}