package egovframework.injeinc.foffice.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.login.service.LoginSvc;

@Controller
public class SnsLoginCtr extends CmmLogCtr{
	
	@Autowired
	private LoginSvc loginSvc;
	
	@RequestMapping(value = "/snsLogin/all.do")
	public String all(HttpServletRequest request, ModelMap model) throws Exception {
		
		debugLog("####################SNS LOGIN ALL PAGE");
		return "injeinc/foffice/login/all";
	}

	@RequestMapping(value = "/snsLogin/facebook.do")
	public String facebook(HttpServletRequest request, ModelMap model) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		String SVC_MSGE = Message.getMessage("616.message"); 
		return null;
	}
	
	@RequestMapping(value = "/snsLogin/twitter.do")
	public String twitter(HttpServletRequest request, ModelMap model) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		String SVC_MSGE = Message.getMessage("616.message"); 
		return null;
	}
	
	@RequestMapping(value = "/snsLogin/kakao.do")
	public String kakao(HttpServletRequest request, ModelMap model) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		String SVC_MSGE = Message.getMessage("616.message"); 
		return null;
	}
	
	@RequestMapping(value = "/snsLogin/naver.do")
	public String naver(HttpServletRequest request, ModelMap model) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		String SVC_MSGE = Message.getMessage("616.message"); 
		return null;
	}
	
	
}
