package egovframework.injeinc.common.social.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.common.social.dao.SocialDao;
import egovframework.injeinc.common.social.service.SocialSvc;
import egovframework.injeinc.common.social.vo.PushVo;
import egovframework.injeinc.common.social.vo.SocialVo;

@Service("SocialSvc")
public class SocialImpl implements SocialSvc{

	@Resource(name = "SocialDao")
	private SocialDao socialDao;

  
	public void registUserComment(SocialVo socialvo) throws Exception{
		socialDao.createUserComment(socialvo);
	}
	
	@SuppressWarnings("rawtypes")
	public List getCommentList(SocialVo socialvo) throws Exception {
		return socialDao.selectCommentList(socialvo);
	}
	
	public void deleteUserComment(SocialVo socialvo) throws Exception{
		socialDao.deleteUserComment(socialvo);
	}
	
	public int getCommetnListCnt(SocialVo socialvo) throws Exception{
		return socialDao.selectCommetnListCnt(socialvo);
	}
		
	
	public List selectPushInfoAndroid() throws Exception{
		return socialDao.selectPushInfoAndroid();
	}
	
	public List selectPushInfoIos() throws Exception{
		return socialDao.selectPushInfoIos();
	}
	
	public void createUserPushInfo(PushVo pushVo) throws Exception {		
		socialDao.createUserPushInfo(pushVo);
	}
	
	public void deletePushInfo(PushVo pushVo) throws Exception {		
		socialDao.deletePushInfo(pushVo);
	}
	
	public List UserPWd() throws Exception{
		return socialDao.UserPWd();
	}
	
	public void updatePwd(Map param) throws Exception{
		socialDao.updatePwd(param);
	}
}
