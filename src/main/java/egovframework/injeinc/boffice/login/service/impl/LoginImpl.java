package egovframework.injeinc.boffice.login.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.login.dao.LoginDao;
import egovframework.injeinc.boffice.login.service.LoginSvc;
import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("LoginSvc")
public class LoginImpl extends EgovAbstractServiceImpl implements LoginSvc {
	
	@Resource(name="LoginDao")
	private LoginDao loginDao;
	
	public LoginVo retrieveListlogin(LoginVo loginVo) throws Exception {
		return loginDao.selectlogin(loginVo);
	}
	
	public void registUserLog(LoginVo loginVo) throws Exception {
		loginDao.insertUserLog(loginVo);
	}

	
	public List retrieveLectureDeptList(HashMap<String, String> paramMap) throws Exception {
		return loginDao.selectLectureDeptList(paramMap);
	}
	
}