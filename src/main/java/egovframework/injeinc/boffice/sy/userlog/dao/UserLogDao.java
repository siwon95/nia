package egovframework.injeinc.boffice.sy.userlog.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.userlog.vo.UserLogVo;

@Repository("UserLogDao")
public class UserLogDao extends EgovAbstractMapper {
	
	public List selectListUserLog(UserLogVo vo) throws Exception {
		return selectList("UserLogDao.selectListUserLog", vo);
	}
	
	public int selectUserLogListTotCnt(UserLogVo vo) throws Exception {
		return (Integer) selectOne("UserLogDao.selectUserLogListTotCnt", vo);
	}
	
	public void deleteUserLog(int ulIdx) throws Exception {
		delete("UserLogDao.deleteUserLog", ulIdx);
	}
	
	public void deleteUserLogCheck(HashMap<String, Object> param) throws Exception {
		delete("UserLogDao.deleteUserLogCheck", param);
	}
	
	public void clearUserLog() throws Exception {
		delete("UserLogDao.clearUserLog",null);
	}
	
	
	
}
