package egovframework.injeinc.boffice.ex.umsLog.service;

import java.util.Map;

import egovframework.injeinc.boffice.ex.umsLog.vo.UmsLogVo;

public interface UmsLogSvc {
	
	public UmsLogVo retrieveUmsLog(UmsLogVo umsLogVo) throws Exception;
	public Map<String, Object> retrievePagUmsLog(UmsLogVo umsLogVo) throws Exception;
	
}