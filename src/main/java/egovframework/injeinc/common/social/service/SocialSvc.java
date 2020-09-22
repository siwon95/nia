package egovframework.injeinc.common.social.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.common.social.vo.PushVo;
import egovframework.injeinc.common.social.vo.SocialVo;


public interface SocialSvc {

	
	public void registUserComment(SocialVo socialvo) throws Exception;
		
	public List getCommentList(SocialVo socialvo) throws Exception;
	
	public void deleteUserComment(SocialVo socialvo) throws Exception;
	
	public int getCommetnListCnt(SocialVo socialvo) throws Exception;
		
	public void createUserPushInfo(PushVo pushVo) throws Exception;
	
	public void deletePushInfo(PushVo pushVo) throws Exception;
	
	public List selectPushInfoAndroid() throws Exception;
	
	public List selectPushInfoIos() throws Exception;
	
	public List UserPWd() throws Exception;
	
	public void updatePwd(Map param) throws Exception;
}
