package egovframework.injeinc.foffice.login.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.w3c.dom.Document;

import com.ibm.icu.text.SimpleDateFormat;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovMessageSource;
import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.Message;
import egovframework.injeinc.common.util.EgovFileScrty;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.injeinc.foffice.login.service.LoginFSvc;
import egovframework.injeinc.foffice.login.vo.LoginFVo;
import egovframework.injeinc.foffice.user.service.UserFSvc;

/**
 * 공통로그인 모델 클래스
 * @author 공통서비스 개발팀 이동열
 */
 
@Controller
public class LoginFCtr extends CmmLogCtr {

	@Autowired
	private LoginFSvc loginFSvc;

	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;

	@Resource(name = "UserFSvc")
	private UserFSvc userSvc;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
	 * 로그인 폼이동
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
    
    @RequestMapping(value= "/{strSitePath}/{strDomain}/login/Login.do")
	public String loginList( @PathVariable("strDomain") String strDomain, HttpServletRequest request
						   , @ModelAttribute("LoginFVo") LoginFVo loginFVo
						   , ModelMap model ) throws Exception {
    	
		String loginReferer = request.getParameter("loginReferer");
		String searchPwd = request.getParameter("searchPwd");
		String loginExclude = request.getParameter("loginExclude");
		
		if(model == null) {
			return "injeinc/common/code500";
		}
		if(request.getParameter("reUrl") != null) {
			loginFVo.setReturnurl(request.getParameter("reUrl"));
		}
		
		model.addAttribute("strDomain", strDomain);
		model.addAttribute("LoginFVo", loginFVo);
		model.addAttribute("loginReferer", loginReferer);
		model.addAttribute("searchPwd", searchPwd);
		model.addAttribute("loginExclude", loginExclude);
		return "injeinc/foffice/user/login";
 	}
    
	@RequestMapping(value= "/{strSitePath}/{strDomain}/login/NonMember.do")
	public String NonMember(@PathVariable("strDomain") String strDomain, HttpServletRequest request,HttpServletResponse response, @ModelAttribute("LoginFVo") LoginFVo loginFVo, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		if(model == null) {
			return "injeinc/common/code500";
		}
		if(request.getParameter("reUrl") != null) {
			loginFVo.setReturnurl(request.getParameter("reUrl"));
		}				

		String cert_return_url = request.getScheme() + "://" + request.getServerName();

		if ((!"80".equals(Integer.valueOf(request.getServerPort()))) && (!"443".equals(Integer.valueOf(request.getServerPort())))) {
			cert_return_url = cert_return_url + ":" + request.getServerPort();
		}

		model.addAttribute("strDomain", strDomain);
		model.addAttribute("LoginFVo", loginFVo);
		return "injeinc/foffice/login/nonmember";
 	}

	
	/**
	 * 로그인 처리 ( 기존 소스 )
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 *//*
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{strSitePath}/{strDomain}/login/Login_List.do")
	public String loginList(
			  @PathVariable("strSitePath") String strSitePath
			, @PathVariable("strDomain") String strDomain
			, HttpServletRequest request, HttpServletResponse response , ModelMap model ,@ModelAttribute("LoginFVo") LoginFVo loginFVo
			, @ModelAttribute("BbsFVo") BbsFVo bbsFVo) throws Exception {
		HttpSession ses = request.getSession();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;			//as-is db접근을 위한 선언(마일리지관련)
		String returnUrl = "";
		String SVC_MSGE = "";
		String loginSucYn = "";
		String logKdCd = "L";
		infoLog("loginList");

		String cuId = request.getParameter("cuId");
		String referUrl = request.getParameter("referUrl");
		String loginReferer = request.getParameter("loginReferer");
		String dcuPwd = request.getParameter("cuPwd"); 
		String cuPwd = EgovFileScrty.encryptPassword(request.getParameter("cuPwd"));	//비밀번호 암호화
		String searchPwd = request.getParameter("searchPwd");

		HashMap<String, String> param = new HashMap<String, String>();
		String ip = request.getHeader("X-FORWARDED-FOR");
       if (ip == null) {
           ip = request.getRemoteAddr();
           param.put("ip", ip);
       }
		param.put("cuId",cuId);
		param.put("cuPwd",cuPwd);
		
		//탈퇴회원 확인
		int outUserCnt = loginFSvc.retrieveLoginFOutUserCnt(param);
		//비밀번호 5회 오류자 30분 이상 로그인불가 확인
		int loginFalseCnt = loginFSvc.retrieveLoginFfalseCnt(param);				
		
		if(loginFalseCnt > 0){
			SVC_MSGE = Message.getMessage("609.message");	//로그인 불가
			returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do", SVC_MSGE, model);
		}else{
			URL url = null;			
			HttpURLConnection  connection = null;			
			HashMap<String, String> logParam = new HashMap<String, String>();
			try {
				
				HashMap<String, Object> serviceMap = (HashMap<String, Object>) loginFSvc.retrieveListloginF(param);
				//SVC_CODE :100 정상
				
				if (serviceMap.get("SVC_CODE").equals("100")) {
					List list = userSvc.retrieveUserFIds((String)serviceMap.get("ownAuth"));
					
					if(list != null && list.size() > 1){
						ses.setAttribute("ss_dupkey", serviceMap.get("ownAuth"));
						model.addAttribute("userlist", list);
						returnUrl = "injeinc/foffice/user/user_step_dupuserlist";
					}else{
						ses.setAttribute("ss_user", null);
						ses.setAttribute("ss_idx",   serviceMap.get("cuIdx"));
						ses.setAttribute("ss_id",   serviceMap.get("cuId"));
						ses.setAttribute("ss_name", serviceMap.get("cuName"));
						ses.setAttribute("ss_level","7");
						ses.setAttribute("ss_dupkey", serviceMap.get("ownAuth"));
						ses.setAttribute("ss_email", serviceMap.get("email"));
						ses.setAttribute("ss_zip", serviceMap.get("zip"));
						ses.setAttribute("ss_addr1", serviceMap.get("addr1"));
						ses.setAttribute("ss_addr2", serviceMap.get("addr2"));
						ses.setAttribute("ss_tel", serviceMap.get("coNum"));
						ses.setAttribute("ss_hp", serviceMap.get("hpNum"));
						ses.setAttribute("ss_numgubun", serviceMap.get("numGubun"));
						ses.setAttribute("ss_ip", ip);
						System.out.println(" ss_birth :: "+serviceMap.get("birth"));
						ses.setAttribute("ss_birth",serviceMap.get("birth"));
						ses.setAttribute("ss_sex",serviceMap.get("sex"));
						ses.setAttribute("ss_slibno",serviceMap.get("slibno"));
						ses.setAttribute("ss_team",serviceMap.get("teamName"));
						
						SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
						Date date = new Date();

						if(serviceMap.get("pwdChgDt") != null && !serviceMap.get("pwdChgDt").equals("")){
							date = transFormat.parse((String)serviceMap.get("pwdChgDt"));
						}else{
							
							date = transFormat.parse((String)serviceMap.get("regDt"));
						}

						Calendar cal = Calendar.getInstance();
						Calendar cal2 = Calendar.getInstance();		//현재날짜
						cal.setTime(date);
						cal.add(Calendar.MONTH, 6);						//6개월 후 날짜

						SVC_MSGE = Message.getMessage("604.message");	//로그인
						
						if(Long.parseLong(transFormat.format(cal.getTime())) < Long.parseLong(transFormat.format(cal2.getTime()))){		//6개월후 날짜 > 현재날짜
							returnUrl = "redirect:/site/{strDomain}/foffice/user/User_Pwd_Form.do?type=ex";
						}else if(loginFVo != null && loginFVo.getReturnurl() != null && !loginFVo.getReturnurl().equals("")){
							int limmitdt = userSvc.retrieveLimmitDt(cuId);
							if(limmitdt > 61){
								if(loginFVo.getReturnurl().indexOf("http://") >= 0){
									returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do", SVC_MSGE, model);
								}else{
									returnUrl = alert(""+loginFVo.getReturnurl(),SVC_MSGE, model);
								}
							}else{
								returnUrl = "redirect:injeinc/foffice/user/login";		
							}							
						}else{
							int limmitdt = userSvc.retrieveLimmitDt(cuId);
							if(limmitdt > 61){
								 2020.09.11 이솔이 수정 
								if(loginReferer == null || "".equals(loginReferer)) { // 직무연수등 로그인이 필요한 페이지 구분
									if("Y".equals(searchPwd)) {
										returnUrl = alert("/site/DRP0000/main.do",SVC_MSGE, model);  //비밀번호 찾기 후 로그인시 메인페이지로 이동
									}else {
										returnUrl = alert(referUrl,SVC_MSGE, model);
									}
								}else {
									returnUrl = alert(loginReferer,SVC_MSGE, model);
								}
							}else{
								returnUrl = "redirect:/site/{strDomain}/foffice/user/My_User_Mod_Step_04.do";
							}							
						}
						loginSucYn = "Y";
					}
				} else {
					//로그인 실패 횟수
					int loginFCnt = loginFSvc.retrieveLoginFSucYnCnt(param);
					
					if(!"site".equals(strSitePath)){
						returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do?yn=n", "로그인 실패 ("+(loginFCnt+1)+"회/5회 오류) 하셨습니다."+(String) serviceMap.get("SVC_MSGE"), model);
					}else{
						returnUrl = alert("/site/"+strDomain+"/login/Login.do?yn=n", "로그인 실패 ("+(loginFCnt+1)+"회/5회 오류) 하셨습니다."+(String) serviceMap.get("SVC_MSGE"), model);
					}
					
					if(loginFCnt == 4){
						logKdCd = "F";
					}
					loginSucYn = "N";
				}
				
				System.out.println("1-------------------------");
				url = new URL("http://lib.inje.go.kr/sync/sync_xml.asp");
				connection = (HttpURLConnection)url.openConnection();				
				connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=euc-kr");
				connection.setConnectTimeout(3000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestMethod("POST");		
				System.out.println("2-------------------------");
				StringBuffer sb = new StringBuffer();
				
				sb.append("fname=F501");		
				sb.append("&uid="+serviceMap.get("cuId"));				
				System.out.println("3-------------------------");
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
				System.out.println("4-------------------------");
				pw.write(sb.toString());
				pw.flush();
				System.out.println("5-------------------------");
				Document doc = parseXML(connection.getInputStream());
				NodeList descNodes = doc.getElementsByTagName("item");		
				System.out.println("6-------------------------");
				pw.close();
				
				Node node = (Node)descNodes.item(0);
				Node uidNode = ((Element)node).getElementsByTagName("RESULT").item(0);
				String result = URLDecoder.decode(unescape(uidNode.getTextContent()),"euc-kr");
				String msg[] = result.split("\\|");
				if(msg[0] != null && msg[0].equals("00")){
					ses.setAttribute("ss_lib_key", msg[1]);
				}
				System.out.println("RESULT : "+URLDecoder.decode(unescape(uidNode.getTextContent()),"euc-kr"));

				
				logParam.put("cuId", request.getParameter("cuId"));
			    logParam.put("ip", ip);

				logParam.put("loginSucYn", loginSucYn);
				logParam.put("logKdCd", logKdCd);

				loginFSvc.registUserLog(logParam);	//로그 등록

			} catch (NumberFormatException e) {
				SVC_MSGE = Message.getMessage("901.message");	//에러
				loginSucYn = "N";
				returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do",SVC_MSGE, model);
				throw e;
			} catch (ParseException e) {
				SVC_MSGE = Message.getMessage("901.message");	//에러
				loginSucYn = "N";
				returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do",SVC_MSGE, model);
				throw e;
			} catch (IllegalArgumentException e) {
				SVC_MSGE = Message.getMessage("901.message");	//에러
				loginSucYn = "N";
				returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do",SVC_MSGE, model);
				throw e;
			} catch (RuntimeException e) {
				SVC_MSGE = Message.getMessage("901.message");	//에러
				loginSucYn = "N";
				returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do",SVC_MSGE, model);
				throw e;
			} catch (SocketTimeoutException e) {
				logParam.put("cuId", request.getParameter("cuId"));
			    logParam.put("ip", ip);

				logParam.put("loginSucYn", loginSucYn);
				logParam.put("logKdCd", logKdCd);

				loginFSvc.registUserLog(logParam);
				//SVC_MSGE = Message.getMessage("604.message");	//에러
				//loginSucYn = "Y";
				//returnUrl = "site/"+strDomain+"/main.do";
				//throw e;
			}catch (IOException e) {
				logParam.put("cuId", request.getParameter("cuId"));
			    logParam.put("ip", ip);

				logParam.put("loginSucYn", loginSucYn);
				logParam.put("logKdCd", logKdCd);

				loginFSvc.registUserLog(logParam);
				//SVC_MSGE = Message.getMessage("604.message");	//에러
				//loginSucYn = "Y";
				//returnUrl = "site/"+strDomain+"/main.do";
				//throw e;
			}catch (Exception e) {
				
				//SVC_MSGE = Message.getMessage("604.message");	//에러
				//loginSucYn = "Y";
				//returnUrl = "site/"+strDomain+"/main.do";
				throw e;
			} finally {
				EgovResourceCloseHelper.closeDBObjects(rs);
				EgovResourceCloseHelper.closeDBObjects(pstmt);
				EgovResourceCloseHelper.closeDBObjects(conn);
				if(connection != null){
					connection.disconnect();
				}				
			}

		}

		return returnUrl;
	}*/
	
	/**
	 * 로그인 처리
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{strSitePath}/{strDomain}/login/Login_List.do")
	public String loginList(
			  @PathVariable("strSitePath") String strSitePath
			, @PathVariable("strDomain") String strDomain
			, HttpServletRequest request, HttpServletResponse response , ModelMap model ,@ModelAttribute("LoginFVo") LoginFVo loginFVo
			, @ModelAttribute("BbsFVo") BbsFVo bbsFVo) throws Exception {
		HttpSession ses = request.getSession();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;			//as-is db접근을 위한 선언(마일리지관련)
		String returnUrl = "";
		String SVC_MSGE = "";
		String loginSucYn = "";
		String logKdCd = "L";
		infoLog("loginList");

		String cuId = request.getParameter("cuId");
		String referUrl = request.getParameter("referUrl");
		String loginReferer = request.getParameter("loginReferer");
		String dcuPwd = request.getParameter("cuPwd"); 
		String cuPwd = EgovFileScrty.encryptPassword(request.getParameter("cuPwd"));	//비밀번호 암호화
		String searchPwd = request.getParameter("searchPwd");
		String loginExclude = request.getParameter("loginExclude");

		HashMap<String, String> param = new HashMap<String, String>();
		String ip = request.getHeader("X-FORWARDED-FOR");
       if (ip == null) {
           ip = request.getRemoteAddr();
           param.put("ip", ip);
       }
		param.put("cuId",cuId);
		param.put("cuPwd",cuPwd);
		
		HttpURLConnection  connection = null;			
		HashMap<String, String> logParam = new HashMap<String, String>();
		try {
			HashMap<String, Object> serviceMap = (HashMap<String, Object>) loginFSvc.retrieveListloginF(param);
			if (serviceMap.get("SVC_CODE").equals("100")) { //SVC_CODE :100 정상
				//List list = userSvc.retrieveUserFIds((String)serviceMap.get("ownAuth"));
				ses.setAttribute("ss_user", null);
				ses.setAttribute("ss_idx",   serviceMap.get("cuIdx"));
				ses.setAttribute("ss_id",   serviceMap.get("cuId"));
				ses.setAttribute("ss_name", serviceMap.get("cuName"));
				ses.setAttribute("ss_level","7");
				ses.setAttribute("ss_dupkey", serviceMap.get("ownAuth"));
				ses.setAttribute("ss_email", serviceMap.get("email"));
				ses.setAttribute("ss_zip", serviceMap.get("zip"));
				ses.setAttribute("ss_addr1", serviceMap.get("addr1"));
				ses.setAttribute("ss_addr2", serviceMap.get("addr2"));
				ses.setAttribute("ss_tel", serviceMap.get("coNum"));
				ses.setAttribute("ss_hp", serviceMap.get("hpNum"));
				ses.setAttribute("ss_numgubun", serviceMap.get("numGubun"));
				ses.setAttribute("ss_ip", ip);
				System.out.println(" ss_birth :: "+serviceMap.get("birth"));
				ses.setAttribute("ss_birth",serviceMap.get("birth"));
				ses.setAttribute("ss_sex",serviceMap.get("sex"));
				ses.setAttribute("ss_slibno",serviceMap.get("slibno"));
				ses.setAttribute("ss_team",serviceMap.get("teamName"));
					
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = new Date();

				if(serviceMap.get("pwdChgDt") != null && !serviceMap.get("pwdChgDt").equals("")){
					date = transFormat.parse((String)serviceMap.get("pwdChgDt"));
				}else{
					date = transFormat.parse((String)serviceMap.get("regDt"));
				}
				SVC_MSGE = Message.getMessage("604.message");	//로그인
				/* 2020.09.11 이솔이 수정 
				if(loginReferer == null || "".equals(loginReferer)) { // 직무연수등 로그인이 필요한 페이지 구분
					if("Y".equals(searchPwd)) { // 비밀번호 찾기
						returnUrl = alert("/site/DRP0000/main.do",SVC_MSGE, model);  //비밀번호 찾기 후 로그인시 메인페이지로 이동
					}else if("Y".equals(loginExclude)){ // 아이디 찾기 or 비밀번호 변경
						returnUrl = alert("/site/DRP0000/main.do",SVC_MSGE, model);
					}else if("http://localhost:9081/site/DRP0000/login/Login_List.do".equals(referUrl)){ 
						returnUrl = "redirect:/site/DRP0000/main.do";
					}else {
						returnUrl = alert(referUrl,SVC_MSGE, model);
					}
				}else {
					returnUrl = alert(loginReferer,SVC_MSGE, model);
				}*/
				if(loginReferer == null || "".equals(loginReferer)) { // 직무연수등 로그인이 필요한 페이지 구분
					if("Y".equals(searchPwd)) { // 비밀번호 찾기
						returnUrl = alert("/site/DRP0000/main.do",SVC_MSGE, model);  //비밀번호 찾기 후 로그인시 메인페이지로 이동
					}else if("Y".equals(loginExclude)){ // 아이디 찾기 or 비밀번호 변경
						returnUrl = alert("/site/DRP0000/main.do",SVC_MSGE, model);
					}else {
						returnUrl = alert("/site/DRP0000/main.do",SVC_MSGE, model);
					}
				}else {
					returnUrl = alert(loginReferer,SVC_MSGE, model);
				}
			}else{
				returnUrl = alert("/site/DRP0000/login/Login.do", ""+(String) serviceMap.get("SVC_MSGE"), model);
			}
			logParam.put("cuId", request.getParameter("cuId"));
		    logParam.put("ip", ip);

			logParam.put("loginSucYn", loginSucYn);
			logParam.put("logKdCd", logKdCd);

			loginFSvc.registUserLog(logParam);	//로그 등록

		} catch (NumberFormatException e) {
			SVC_MSGE = Message.getMessage("901.message");	//에러
			loginSucYn = "N";
			returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do",SVC_MSGE, model);
			throw e;
		} catch (ParseException e) {
			SVC_MSGE = Message.getMessage("901.message");	//에러
			loginSucYn = "N";
			returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do",SVC_MSGE, model);
			throw e;
		} catch (IllegalArgumentException e) {
			SVC_MSGE = Message.getMessage("901.message");	//에러
			loginSucYn = "N";
			returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do",SVC_MSGE, model);
			throw e;
		} catch (RuntimeException e) {
			SVC_MSGE = Message.getMessage("901.message");	//에러
			loginSucYn = "N";
			returnUrl = alert("/"+strSitePath+"/"+strDomain+"/main.do",SVC_MSGE, model);
			throw e;
		} catch (SocketTimeoutException e) {
			logParam.put("cuId", request.getParameter("cuId"));
		    logParam.put("ip", ip);

			logParam.put("loginSucYn", loginSucYn);
			logParam.put("logKdCd", logKdCd);

			loginFSvc.registUserLog(logParam);
		}catch (IOException e) {
			logParam.put("cuId", request.getParameter("cuId"));
		    logParam.put("ip", ip);

			logParam.put("loginSucYn", loginSucYn);
			logParam.put("logKdCd", logKdCd);

			loginFSvc.registUserLog(logParam);
		}catch (Exception e) {
			throw e;
		} finally {
			EgovResourceCloseHelper.closeDBObjects(rs);
			EgovResourceCloseHelper.closeDBObjects(pstmt);
			EgovResourceCloseHelper.closeDBObjects(conn);
			if(connection != null){
				connection.disconnect();
			}				
		}
		return returnUrl;
	}

	/**
	 * 로그아웃 처리
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/login/Logout.do")
	public String loginOut(@PathVariable("strDomain") String strDomain,HttpServletRequest request, ModelMap model) throws Exception {

		HttpSession ses = request.getSession();
		ses.invalidate();
		String SVC_MSGE = "로그아웃되었습니다.";
		String reUrl = request.getHeader("referer");
		String returnUrl = "";

		if (reUrl != null) {
			returnUrl = "/site/DRP0000/main.do";
		} else {
			returnUrl = "/site/DRP0000/main.do";
		}
		return alert(returnUrl, SVC_MSGE, model);
	}

	/**
	 * web 커넥션및 세션종료시 리턴
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/login/Login_Session_Empty.do")
	public String webSessionEmpty(@PathVariable("strDomain") String strDomain,HttpServletRequest request, ModelMap model) throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();

		debugLog("webSessionEmpty call");

		HttpSession ses = request.getSession();
		String reUrl = "";
		try{

			ses.invalidate();

			if(request.getParameter("reUrl") != null){
				reUrl = request.getParameter("reUrl").replace("?", "%3F").replaceAll("&", "%26");
			}
			
			String SVC_MSGE = Message.getMessage("602.message");

			String returnUrl = alert("/site/"+strDomain+"/login/Login.do?reUrl="+reUrl, SVC_MSGE, model);

			return returnUrl;

		} catch (RuntimeException e) {
			errorLog("ajaxSessionEmpty ERROR!", e);
			throw e;
		} catch (Exception e) {
			errorLog("ajaxSessionEmpty ERROR!", e);
			throw e;
		}

	}
	
	@RequestMapping(value = "/site/{strDomain}/login/NonMember_Empty.do")
	public String NonMemberEmpty(@PathVariable("strDomain") String strDomain,HttpServletRequest request, ModelMap model) throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();

		debugLog("webSessionEmpty call");

		HttpSession ses = request.getSession();
		String reUrl = "";
		try{

			ses.invalidate();
 
			if(request.getParameter("reUrl") != null){
				reUrl = request.getParameter("reUrl").replace("?", "%3F").replaceAll("&", "%26");
			}
			
			String SVC_MSGE = Message.getMessage("602.message");

			String returnUrl = alert("/site/{strDomain}/login/NonMember.do?reUrl="+reUrl, SVC_MSGE, model);

			return returnUrl;

		} catch (RuntimeException e) {
			errorLog("ajaxSessionEmpty ERROR!", e);
			throw e;
		} catch (Exception e) {
			errorLog("ajaxSessionEmpty ERROR!", e);
			throw e;
		}

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cmm/login/Login_ListAx.do")
	public void fofficeLoginListAx(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String username = request.getParameter("username");
		String userdi = request.getParameter("userdi");
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("userName", username);
		param.put("userDi", userdi);

		HashMap<String, Object> serviceMap = (HashMap<String, Object>) loginFSvc.retrieveListloginListDiF(param);
		
		List<LoginFVo> rowDataList = (List<LoginFVo>) serviceMap.get("rowDataList");

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		jsonMap.put("rowDataList", rowDataList);
		jsonView.render(jsonMap, request, response);
		
	}
	
	private Document parseXML(InputStream stream) throws Exception{
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try{
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(stream);
		}catch(Exception ex){
			throw ex;
		}
		return doc;
	}
	
	public static String escape(String src) {   
        int i;   
        char j;   
        StringBuffer tmp = new StringBuffer();   
        tmp.ensureCapacity(src.length() * 6);   
        for (i = 0; i < src.length(); i++) {   
                j = src.charAt(i);   
                if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))   
                        tmp.append(j);   
                else if (j < 256) {   
                        tmp.append("%");   
                        if (j < 16)   
                                tmp.append("0");   
                        tmp.append(Integer.toString(j, 16));   
                } else {   
                        tmp.append("%u");   
                        tmp.append(Integer.toString(j, 16));   
                }   
        }   
        return tmp.toString();   
	}   

	public static String unescape(String src) {   
	        StringBuffer tmp = new StringBuffer();   
	        tmp.ensureCapacity(src.length());   
	        int lastPos = 0, pos = 0;   
	        char ch;   
	        while (lastPos < src.length()) {   
	                pos = src.indexOf("%", lastPos);   
	                if (pos == lastPos) {   
	                        if (src.charAt(pos + 1) == 'u') {   
	                                ch = (char) Integer.parseInt(src   
	                                                .substring(pos + 2, pos + 6), 16);   
	                                tmp.append(ch);   
	                                lastPos = pos + 6;   
	                        } else {   
	                                ch = (char) Integer.parseInt(src   
	                                                .substring(pos + 1, pos + 3), 16);   
	                                tmp.append(ch);   
	                                lastPos = pos + 3;   
	                        }   
	                } else {   
	                        if (pos == -1) {   
	                                tmp.append(src.substring(lastPos));   
	                                lastPos = src.length();   
	                        } else {   
	                                tmp.append(src.substring(lastPos, pos));   
	                                lastPos = pos;   
	                        }   
	                }   
	        }   
	        return tmp.toString();   
	} 

}