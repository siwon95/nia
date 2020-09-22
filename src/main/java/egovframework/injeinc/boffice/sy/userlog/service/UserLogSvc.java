package egovframework.injeinc.boffice.sy.userlog.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.sy.userlog.vo.UserLogVo;

public interface UserLogSvc {
	public List retrieveListUserLog(UserLogVo vo) throws Exception;
	
	public int selectUserLogListTotCnt(UserLogVo vo) throws Exception;
	
	public void userLogRmv(int ulIdx) throws Exception;
	
	public void userLogCheckRmv(HashMap<String, Object> paramObject) throws Exception;
	
	public void userLogClear() throws Exception;
}
