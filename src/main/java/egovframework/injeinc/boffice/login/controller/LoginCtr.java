package egovframework.injeinc.boffice.login.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.login.service.LoginSvc;
import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.sy.menu.service.MgrMenuSvc;
import egovframework.injeinc.boffice.sy.mgr.service.MgrListSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrListVo;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class LoginCtr extends CmmLogCtr {
	
	@Autowired
	private LoginSvc loginSvc;
	
	@Resource(name = "MgrMenuSvc")
	private MgrMenuSvc mgrMenuSvc;
	
	@Resource(name = "MgrListSvc")
	private MgrListSvc mgrListSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@Autowired(required=true)
	private CodeSvc codeSvc;
	
	/**
	 * 로그인 화면
	 */
	@RequestMapping(value= "/boffice/login/Login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("LoginVo") LoginVo loginVo , ModelMap model) throws Exception {

		response.addHeader("Pragma", "No-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addDateHeader("Expires", 1);

//		HttpSession session = request.getSession();
//		session.invalidate();
		
		return "injeinc/boffice/login/login";
		
		/*ArrayList<Object> menuList = (ArrayList<Object>)mgrMenuSvc.retrieveListMgrMenuTop();
		
		HttpSession session = request.getSession();
		session.setAttribute("SesTopMenuList", menuList);
		session.setMaxInactiveInterval(6000);
		System.out.println("session : " + session.getAttribute("SesTopMenuList"));
		session.setAttribute("SesMgrIdx", "ML00000112");
		session.setAttribute("SesUserId", "injeincc");
		session.setAttribute("SesUserName", "소통담당관");
		session.setAttribute("SesUserPermCd", "05010000");
		session.setAttribute("SesUserPermNm", "최고관리자");
		session.setAttribute("SesUserDeptCd", "d00000008");
		session.setAttribute("SesUserDeptNm", "감사팀");
		session.setAttribute("SesUserRoleIdx", null);
			
			return "redirect:/boffice/sy/mgr/MgrMain.do?cmm_code=MM010000";*/
	}
	
	/**
	 * 로그인 프로세스
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login/LoginList.do")
	public String loginList(HttpServletRequest request, @ModelAttribute("LoginVo") LoginVo loginVo, ModelMap model) throws Exception {
		
		LoginVo result = (LoginVo) loginSvc.retrieveListlogin(loginVo);
		
		if(result == null) {

			String SVC_MSGE = Message.getMessage("601.message");
			return alert(request.getContextPath()+"/boffice/login/Login.do", SVC_MSGE, model);
			
		}else {

			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(6000);
			ArrayList<Object> menuList = (ArrayList<Object>)mgrMenuSvc.retrieveListMgrMenuTop(result);
			session.setAttribute("SesTopMenuList", menuList);
			session.setAttribute("SesMgrIdx", result.getMgrIdx());
			session.setAttribute("SesUserId", result.getUserId());
			session.setAttribute("SesUserName", result.getUserName());
			session.setAttribute("SesUserPermCd", result.getPermCd());
			session.setAttribute("SesUserPermNm", result.getPermNm());
			session.setAttribute("SesUserParentDeptCd", result.getDeptCd());
			session.setAttribute("SesUserDeptNm", result.getDeptNm());
			session.setAttribute("SesUserDeptCd", result.getDeptCd());
			session.setAttribute("SesUserDeptNm", result.getDeptNm());
			session.setAttribute("SesUserRoleIdx", result.getRoleIdx());
			session.setAttribute("SesUserMgrSiteCd", result.getMgrSiteCd());
			result.setIp(request.getRemoteAddr());
			
			/* 관리자 vo세션 */
			session.setAttribute("CMSUSER", result);

			loginSvc.registUserLog(result);
		
		/*HttpSession session = request.getSession();
		session.setMaxInactiveInterval(6000);
		System.out.println("session : " + session.getAttribute("SesTopMenuList"));
		session.setAttribute("SesMgrIdx", "ML00000112");
		session.setAttribute("SesUserId", "injeincc");
		session.setAttribute("SesUserName", "소통담당관");
		session.setAttribute("SesUserPermCd", "05010000");
		session.setAttribute("SesUserPermNm", "최고관리자");
		session.setAttribute("SesUserDeptCd", "d00000008");
		session.setAttribute("SesUserDeptNm", "감사팀");
		session.setAttribute("SesUserRoleIdx", null);
			*/
		return "redirect:/boffice/sy/mgr/MgrMain.do?cmm_code=MM010000";
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/login/EminwonLogin.do")
	public String EminwonLogin(HttpServletRequest request, ModelMap model) throws Exception {
		
		System.out.println("새올연계 로그인 시작");
		String method = request.getMethod();//GET 전송방식시 오류
		System.out.println("method=============>"+method);
		if(method.equals("GET")){
			return alert("", "GET 방식으로 접속이 불가능 합니다.", model);
		}

		String referer = EgovStringUtil.isNullToString(request.getHeader("Referer"));//유입경로
		System.out.println("referer=============>"+referer);
		if(referer.indexOf("98.22.10.73:3100") == -1) {
			return alert("", "알수없는 위치에서의 접근입니다.", model);
		}

		String departName = EgovStringUtil.isNullToString(request.getParameter("departName"));
		System.out.println("departName=============>"+departName);
		if(departName.equals("")) {
			return alert("", "필요한 값이 없습니다.", model);
		}
		
		if(departName.equals("오-케이민원센터")){
			departName = "OK민원센터";
		}
		
		MgrListVo mgrListVo = mgrListSvc.retrieveMgrListByName(departName);
		if(mgrListVo == null) {
			return alert("", "해당 부서명으로 정보를 받아올수가 없습니다.", model);
		}
		
		LoginVo loginVo = new LoginVo();
		loginVo.setUserId(mgrListVo.getMgrId());
		loginVo.setPassWd(mgrListVo.getMgrPw());
		
		LoginVo result = (LoginVo) loginSvc.retrieveListlogin(loginVo);
		
		if(result == null) {

			String SVC_MSGE = Message.getMessage("601.message");
			return alert(request.getContextPath()+"/boffice/login/Login.do", SVC_MSGE, model);
			
		}else {

			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(6000);
			ArrayList<Object> menuList = (ArrayList<Object>)mgrMenuSvc.retrieveListMgrMenuTop(result);
			session.setAttribute("SesTopMenuList", menuList);
			session.setAttribute("SesMgrIdx", result.getMgrIdx());
			session.setAttribute("SesUserId", result.getUserId());
			session.setAttribute("SesUserName", result.getUserName());
			session.setAttribute("SesUserPermCd", result.getPermCd());
			session.setAttribute("SesUserPermNm", result.getPermNm());
			session.setAttribute("SesUserDeptCd", result.getDeptCd());
			session.setAttribute("SesUserDeptNm", result.getDeptNm());
			
			result.setIp(request.getRemoteAddr());
			loginSvc.registUserLog(result);
			
			return "redirect:/boffice/sy/mgr/MgrMain.do?cmm_code=MM010000";
			
		}
		
	}
	
	@RequestMapping(value = "/login/Logout.do")
	public String loginOut(HttpServletRequest request, ModelMap model) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		String SVC_MSGE = Message.getMessage("616.message"); 
		return alert(request.getContextPath()+"/boffice/login/Login.do", SVC_MSGE, model);
	}
	
	@RequestMapping(value = "/login/LoginSessionEmpty.do")
	public String webSessionEmpty(HttpServletRequest request, ModelMap model) throws Exception {
		
		System.out.println("Referer ======================= " + request.getHeader("Referer"));
		debugLog("webSessionEmpty call");
		HttpSession session = request.getSession();
		session.invalidate();
		String SVC_MSGE = Message.getMessage("602.message"); 
		return alert(request.getContextPath()+"/boffice/login/Login.do", SVC_MSGE, model);
	}
	
	@RequestMapping(value = "/login/LoginSessionEmptyAx.do")
	public void webSessionEmptyAx(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		debugLog("webSessionEmptyAx call");
		HttpSession session = request.getSession();
		session.invalidate();

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", false);
		jsonMap.put("message", Message.getMessage("602.message"));
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping(value = "/login/LoginSessionEmptyPop.do")
	public String webSessionEmptyPop(HttpServletRequest request, ModelMap model) throws Exception {
		
		debugLog("webSessionEmptyPop call");
		HttpSession session = request.getSession();
		session.invalidate();
		String SVC_MSGE = Message.getMessage("602.message"); 
		model.addAttribute("message", SVC_MSGE);
		return "injeinc/common/blank";
	}
	
	@RequestMapping("/login/LoginSessionEmptyParking.do")
	public String LoginSessionEmptyParking(HttpServletRequest request, ModelMap model) throws Exception {
		
		debugLog("webSessionEmptyPop call");
		HttpSession session = request.getSession();
		session.invalidate();
		String SVC_MSGE = "임시 점검입니다. 당분간 접속이 불가능 합니다."; 
		model.addAttribute("message", SVC_MSGE);
		return "injeinc/common/blank";
	}
	
	@RequestMapping(value = "/login/LoginSessionAuthEmpty.do")
	public String LoginSessionAuthEmpty(HttpServletRequest request, ModelMap model) throws Exception {
		String referer = request.getHeader("Referer");
		
		String SVC_MSGE = Message.getMessage("621.message"); 
		return alert(referer, SVC_MSGE, model);
	}
}