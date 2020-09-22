package egovframework.injeinc.common.social.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.common.social.vo.PushVo;
import egovframework.injeinc.common.social.vo.SocialVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("SocialDao")
public class SocialDao  extends EgovAbstractMapper {

	public void createUserComment(SocialVo socialvo) throws Exception {
		insert("SocialDao.insertUserComment", socialvo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectCommentList(SocialVo socialvo) throws Exception {
		return selectList("SocialDao.selectCommentList", socialvo);
	}
	
	public void deleteUserComment(SocialVo socialvo) throws Exception {
		delete("SocialDao.deleteComment", socialvo);
	}
	
	public int selectCommetnListCnt(SocialVo socialvo) {
		return (Integer)selectOne("SocialDao.selectCommentListCnt",socialvo);
	}
	
	
	public List selectPushInfoAndroid() throws Exception {
		return selectList("SocialDao.selectPushListAndroid");
	}
	
	public List UserPWd() throws Exception {
		return selectList("SocialDao.UserPWd");
	}
	
	
	
	public List selectPushInfoIos() throws Exception {
		return selectList("SocialDao.selectPushListIos");
	}
	
	public void updatePwd(Map param) throws Exception {
		update("SocialDao.updatePwd", param);
	}
	
	public void createUserPushInfo(PushVo pushVo) throws Exception {
		insert("SocialDao.insertPushInfo", pushVo);
	}
	
	public void deletePushInfo(PushVo pushVo) throws Exception {
		delete("SocialDao.deletePushInfo", pushVo);
	}

}
