package egovframework.injeinc.boffice.sy.userlog.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.userlog.dao.UserLogDao;
import egovframework.injeinc.boffice.sy.userlog.service.UserLogSvc;
import egovframework.injeinc.boffice.sy.userlog.vo.UserLogVo;

@Service("UserLogSvc")
public class UserLogImpl implements UserLogSvc {
	@Resource(name="UserLogDao")
	private UserLogDao userLogDao;
	
	public List retrieveListUserLog(UserLogVo vo) throws Exception {
		return userLogDao.selectListUserLog(vo);
	}

	public int selectUserLogListTotCnt(UserLogVo vo) throws Exception {		
		return userLogDao.selectUserLogListTotCnt(vo);
	}

	public void userLogCheckRmv(HashMap<String, Object> paramObject) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		if(paramObject != null){
			for(int i=0; i < paramObject.size(); i++) {
				param.put("ulIdx", paramObject.get("ulIdx"+i));
				userLogDao.deleteUserLogCheck(param);
			}
		}
	}
	
	public void userLogRmv(int ulIdx) throws Exception {
		userLogDao.deleteUserLog(ulIdx);
	}

	public void userLogClear() throws Exception {
		userLogDao.clearUserLog();
	}

}
