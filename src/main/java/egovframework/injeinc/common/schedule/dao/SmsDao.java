
package egovframework.injeinc.common.schedule.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.common.schedule.vo.SmsVo;


@Repository("SmsDao")
public class SmsDao extends EgovAbstractMapper {
	
	public List selectListSms(SmsVo smsVo) throws Exception {
		return selectList("SmsDao.selectListSms", smsVo);
	}
	//교육전안내문자보낼 수강생리스트
	public List selectLectureEduListSms(SmsVo smsVo) throws Exception {
		return selectList("SmsDao.selectLectureEduListSms", smsVo);
	}
	//승인,미승인 문자보낼 수강생리스트
	public List selectLectureStateListSms(SmsVo smsVo) throws Exception {
		return selectList("SmsDao.SmsDao.selectLectureStateListSms", smsVo);
	}
	//승인,미승인 문자보낼 강좌 idx값 조회리스트
	public List selectLectureStateList(SmsVo smsVo) throws Exception {
		return selectList("SmsDao.SmsDao.selectLectureStateList", smsVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListSmsTean() throws Exception {
		return selectList("SmsDao.selectListSmsTean", "");
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListSmsHoengSeong() throws Exception {
		return selectList("SmsDao.selectListSmsHoengSeong", "");
	}
	
}