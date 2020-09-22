package egovframework.injeinc.common.schedule.service;


import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.common.schedule.vo.SmsVo;

public interface SmsSvc {
	
	public List retrieveListSms(SmsVo smsVo) throws Exception;
	
	//교육전안내문자보낼 수강생리스트
	public List retrieveLectureEduListSms(SmsVo smsVo) throws Exception;
	//승인,미승인 문자보낼 수강생리스트
	public List retrieveLectureStateListSms(SmsVo smsVo) throws Exception;
	//승인,미승인 문자보낼 강좌 idx값 조회리스트
	public List retrieveLectureStateList(SmsVo smsVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListSmsTean() throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListSmsHoengSeong() throws Exception;
	
}