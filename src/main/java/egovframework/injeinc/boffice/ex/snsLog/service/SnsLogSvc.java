package egovframework.injeinc.boffice.ex.snsLog.service;

import java.util.List;

import egovframework.injeinc.boffice.ex.snsLog.vo.SnsLogVo;

public interface SnsLogSvc {
	
	public void registSnsLog(SnsLogVo snsLogVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListSnsLog(SnsLogVo snsLogVo) throws Exception;
	
}