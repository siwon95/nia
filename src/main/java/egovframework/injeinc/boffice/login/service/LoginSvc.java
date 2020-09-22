package egovframework.injeinc.boffice.login.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.login.vo.LoginVo;

@Service
public interface LoginSvc {
	
	public LoginVo retrieveListlogin(LoginVo loginVo) throws Exception;
	public void registUserLog(LoginVo loginVo) throws Exception;
	public List retrieveLectureDeptList(HashMap<String, String> paramMap) throws Exception;
	
}