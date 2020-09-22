package egovframework.injeinc.foffice.ex.myscrap.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.foffice.ex.myscrap.dao.MyscrapUserDao;
import egovframework.injeinc.foffice.ex.myscrap.service.MyscrapUserSvc;
import egovframework.injeinc.foffice.ex.myscrap.vo.MyscrapVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MyscrapUserSvc")
public class MyscrapUserImpl extends EgovAbstractServiceImpl implements MyscrapUserSvc{

	@Resource(name = "MyscrapUserDao")
	private MyscrapUserDao myscrapUserDao;
	
	public void insertMyscrapUser(MyscrapVO myscrapVO) throws Exception {
		myscrapUserDao.insertMyscrap(myscrapVO);
	}

	
	public Map<String, Object> selectMyscrapUserList(MyscrapVO myscrapVO)	throws Exception {
		List<MyscrapVO> result = myscrapUserDao.MyscrapUserList(myscrapVO);
		int cnt = myscrapUserDao.MyscrapUserListCnt(myscrapVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;	
	}
	
	public int selectdupCnt(MyscrapVO myscrapVO) throws Exception {
		return myscrapUserDao.MyscrapUserDupCnt(myscrapVO);
	}
	

	public void deleteMyscrap(MyscrapVO myscrapVO) throws Exception {
		myscrapUserDao.deleteMyscrap(myscrapVO);
		
	}
	
	

}
