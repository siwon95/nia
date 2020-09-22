package egovframework.injeinc.foffice.login.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;


//import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.login.vo.LoginFVo;

/**
 * <PRE>
 * 1. FileName : EduDao.java
 * 2. Package  : egovframework.injeinc.common.edu.dao;
 * 3. Comment  : 
 * 4. Programmer   : ldy
 * 5. Date   : 2015.05.08
 * </PRE>
 */
@Repository("LoginFDao")
public class LoginFDao extends EgovAbstractMapper {
	
	/** 아이디중복체크 */
	@SuppressWarnings("unchecked")
	public LoginFVo selectLogin(HashMap<String, String> param) throws Exception {
		return  (LoginFVo)selectOne("LoginFDao.selectLogin", param);
	}
	
	/** 로그인 로그 등록 */
	@SuppressWarnings("unchecked")
	public void insertUserLog(HashMap<String, String> param) throws Exception {
		insert("LoginFDao.insertUserLog", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selectLoginListAx(HashMap<String, String> param) throws Exception {
		return (List<Object>) selectList("LoginFDao.selectLoginListAx", param);
	}
	
	/** 비밀번호 5회 오류자 30분 이상 로그인불가 확인 */
	@SuppressWarnings("unchecked")
	public int selectLoginFfalseCnt(HashMap<String, String> param) throws Exception {
		return (Integer)selectOne("LoginFDao.selectLoginFfalseCnt", param);
	}
	
	/** 로그인 실패 횟수 */
	@SuppressWarnings("unchecked")
	public int selectLoginFSucYnCnt(HashMap<String, String> param) throws Exception {
		return (Integer)selectOne("LoginFDao.selectLoginFSucYnCnt", param);
	}
	
	/** 해당 아이디 그룹 */
	@SuppressWarnings("unchecked")
	public List selectLoginGroup(String cuIdx) throws Exception {
		return selectList("LoginFDao.selectLoginGroup", cuIdx);
	}
	
	/** 최종로그인 시간 update */
	@SuppressWarnings("unchecked")
	public void updateUserLog(String cuIdx) throws Exception {
		update("LoginFDao.updateUserLog", cuIdx);
	}
	
	/**  탈퇴회원 여부 조회 */
	@SuppressWarnings("unchecked")
	public int selectLoginFOutUserCnt(HashMap<String, String> param) throws Exception {
		return (Integer)selectOne("LoginFDao.selectLoginFOutUserCnt", param);
	}
	
	/**  오늘 포인트 획득 유무 조회 */
	@SuppressWarnings("unchecked")
	public int selectTodayPointCnt(String cuId) throws Exception {
		return (Integer)selectOne("LoginFDao.selectTodayPointCnt", cuId);
	}
	
}