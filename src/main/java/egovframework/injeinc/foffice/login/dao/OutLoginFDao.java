package egovframework.injeinc.foffice.login.dao;

import java.util.HashMap;

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
@Repository("OutLoginFDao")
public class OutLoginFDao extends EgovAbstractMapper {
	
	/** 아이디중복체크 */
	@SuppressWarnings("unchecked")
	public LoginFVo selectLogin(HashMap<String, String> param) throws Exception {
		return  (LoginFVo)selectOne("OutLoginFDao.selectLogin", param);
	}
	
	/** 로그인 로그 등록 */
	@SuppressWarnings("unchecked")
	public void insertUserLog(HashMap<String, String> param) throws Exception {
		insert("OutLoginFDao.insertUserLog", param);
	}
	
}