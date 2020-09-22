package egovframework.injeinc.boffice.login.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.login.vo.LoginVo;

@Repository("LoginDao")
public class LoginDao extends EgovAbstractMapper {
	
	public LoginVo selectlogin(LoginVo loginVo) throws Exception {
		return (LoginVo)selectOne("LoginDao.selectListlogin", loginVo);
	}
	
	public void insertUserLog(LoginVo loginVo) throws Exception {
		insert("LoginDao.insertUserLog", loginVo);
	}

	public List selectLectureDeptList(HashMap<String, String> paramMap) {
		return selectList("LoginDao.selectLectureDeptList", paramMap);
	}
	
}