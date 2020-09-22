package egovframework.injeinc.common.schedule.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import egovframework.cmm.EgovMessageSource;
import egovframework.injeinc.common.schedule.dao.SmsDao;
import egovframework.injeinc.common.schedule.service.SmsSvc;
import egovframework.injeinc.common.schedule.vo.SmsVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("SmsSvc")
public class SmsImpl extends EgovAbstractServiceImpl implements
SmsSvc {
	
	@Resource(name="SmsDao")
	private SmsDao smsDao;
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	public List retrieveListSms(SmsVo smsVo) throws Exception {
		return smsDao.selectListSms(smsVo);
	}
	
	//교육전안내문자보낼 수강생리스트
	public List retrieveLectureEduListSms(SmsVo smsVo) throws Exception {
		return smsDao.selectLectureEduListSms(smsVo);
	}
	
	//승인,미승인 문자보낼 수강생리스트
	public List retrieveLectureStateListSms(SmsVo smsVo) throws Exception {
		return smsDao.selectLectureStateListSms(smsVo);
	}
	//승인,미승인 문자보낼 강좌 idx값 조회리스트
	public List retrieveLectureStateList(SmsVo smsVo) throws Exception {
		return smsDao.selectLectureStateList(smsVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListSmsTean() throws Exception {
		return smsDao.selectListSmsTean();
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListSmsHoengSeong() throws Exception {
		return smsDao.selectListSmsHoengSeong();
	}
}