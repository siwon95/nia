package egovframework.injeinc.foffice.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.common.util.EgovFileScrty;
import egovframework.injeinc.common.util.XecureUtil;
import egovframework.injeinc.foffice.member.service.MemberSvc;
import egovframework.injeinc.foffice.user.service.UserFSvc;
import egovframework.injeinc.foffice.user.vo.UserFVo;
import gov.mogaha.gpin.sp.proxy.GPinProxy;

@Controller
public class MemberCtr extends CmmLogCtr {

	final Logger logger = Logger.getLogger(getClass());
	
	@Resource(name = "MemberSvc")
	private MemberSvc memberSvc;
	
	@Resource(name = "UserFSvc")
	private UserFSvc userSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	public void setMemberSvc(MemberSvc memberSvc) {
		this.memberSvc = memberSvc;
	}
	
	/**
	 * 회원구분( 14세이상 / 14세 이하 ) step01
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/user/User_Step_01.do")
	public String memberChoice(@PathVariable("strDomain") String strDomain, HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("UserFVo") UserFVo userFVo) throws Exception {
		model.addAttribute("UserFVo", userFVo);
		model.addAttribute("strDomain", strDomain);
		return "injeinc/foffice/user/user_step_01";
	}
	
	/**
	 * 약관동의  ( 14세 이상이하 공통 ) step02
	 * @param request
	 * @param response
	 * @param userFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/user/User_Step_02.do")
	public String normalTerms(@PathVariable("strDomain") String strDomain, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("UserFVo") UserFVo userFVo, ModelMap model) throws Exception {
		model.addAttribute("UserFVo", userFVo);
		model.addAttribute("strDomain", strDomain);
		return "injeinc/foffice/user/user_step_02";
	}
	
	/**
	 * 본인인증(14세이상 일반회원) step03
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/site/{strDomain}/user/User_Step_03.do")
	public String normalConfirm(@PathVariable("strDomain") String strDomain, HttpServletRequest request
			,HttpServletResponse response, HttpSession session, ModelMap model
			, @ModelAttribute("UserFVo") UserFVo userFVo) throws Exception {

		HttpSession ses = request.getSession();

		String cert_return_url = request.getScheme() + "://" + request.getServerName();
		if ((!"80".equals(Integer.valueOf(request.getServerPort()))) && (!"443".equals(Integer.valueOf(request.getServerPort())))) {
			cert_return_url = cert_return_url + ":" + request.getServerPort();
		}
		String youngYn = request.getParameter("youngYn");
		userFVo.setYoungYn(youngYn);
		
		model.addAttribute("strDomain", strDomain);
		model.addAttribute("userFVo", userFVo);
		
		return "injeinc/foffice/user/user_step_03";
	}
	
	@RequestMapping(value= "/site/{strDomain}/user/gpinRequest.do")		
	public void gpinRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)throws Exception{
		
		HttpSession ses = request.getSession();			  								
		session.setAttribute("gpinAuthRetPage", "/site/{strDomain}/user/gpinResponse.do");
		System.out.println("세견갑 리턴 페이지" +session.getAttribute("gpinAuthRetPage"));
		session.setAttribute("gpinUserIP", request.getRemoteAddr());

		GPinProxy proxy = new GPinProxy();
		proxy.Init("211.42.168.72:9080", "/G-PIN", 15000);
		String requestHTML;
		if (ServletRequestUtils.getIntParameter(request, "Attr") != null)
			requestHTML = proxy.makeAuthRequest(ServletRequestUtils.getIntParameter(request, "Attr").intValue());
		else {
			requestHTML = proxy.makeAuthRequest();
		}
		
		
		response.getWriter().write(requestHTML);
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@RequestMapping(value= "/site/{strDomain}/user/gpinResponse.do")
	public String gpinResponse(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)throws Exception
	{
		HttpSession ses = request.getSession();
		
		GPinProxy proxy = new GPinProxy();		
		proxy.Init("211.42.168.72:9080", "/G-PIN", 15000);
	
		String[] attrNames = { "dupInfo", "virtualNo", "realName", "sex", "age", "birthDate", "nationalInfo", "authInfo", "GPIN_AQ_SERVICE_SITE_USER_CONFIRM" };
	
		String samlResponse = ServletRequestUtils.getStringParameter(request, "SAMLResponse");
		String[] rets = proxy.parseSAMLResponse(samlResponse, attrNames);
		
		for(int i = 0; i < rets.length; i++){
			System.out.println(i+" : "+rets[i]);
		}
		session.setAttribute("ss_name", rets[2]);
		session.setAttribute("ss_level", "8");
		session.setAttribute("ss_dupkey", rets[0]);
		session.setAttribute("ss_birth", rets[5]);		
		
		return "injeinc/common/gpin/gpin_auth";
	}
	
	/**
	 * 본인인증(14세미만 어린이회원) step03
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/user/User_Young_Step_03.do")
	public String youngConfirm(@PathVariable("strDomain") String strDomain, HttpServletRequest request
			,HttpServletResponse response, HttpSession session
			,@ModelAttribute("UserFVo") UserFVo userFVo, ModelMap model) throws Exception {

		HttpSession ses = request.getSession();

		String cert_return_url = request.getScheme() + "://" + request.getServerName();

		if ((!"80".equals(Integer.valueOf(request.getServerPort()))) && (!"443".equals(Integer.valueOf(request.getServerPort())))) {
			cert_return_url = cert_return_url + ":" + request.getServerPort();
		}
		
		String youngYn = request.getParameter("youngYn");
		userFVo.setYoungYn(youngYn);
		
		model.addAttribute("strDomain", strDomain);

		return "injeinc/foffice/user/user_young_step_03";
	}
	
	/**
	 * 휴대폰 인증
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/user/mobileAuth.do")
	public String memberCertResponse1(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		HttpSession ses = request.getSession();

		NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();

		String sEnc_data = request.getParameter("enc_data");
		String sEnc_data2 = request.getParameter("EncodeData");

		String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
		String sReserved1 = requestReplace(request.getParameter("param_r1"), "");
		String sReserved2 = requestReplace(request.getParameter("param_r2"), "");
		String sReserved3 = requestReplace(request.getParameter("param_r3"), "");

		String sSiteCode = "G6419"; // NICE로부터 부여받은 사이트 코드
		String sSitePassword = "7ZE70AH55WN6"; // NICE로부터 부여받은 사이트 패스워드

		String name = ""; // 성명
		String sex = ""; // 성별
		String birYMD = ""; // 생년월일
		String fgnGbn = ""; // 내외국인 구분값

		String di = ""; // DI

		String sCipherTime = ""; // 복호화한 시간
		String sRequestNumber = ""; // 요청 번호
		String sResponseNumber = ""; // 인증 고유번호
		String sAuthType = ""; // 인증 수단
		String sName = ""; // 성명
		String sDupInfo = ""; // 중복가입 확인값 (DI_64 byte)
		String sConnInfo = ""; // 연계정보 확인값 (CI_88 byte)
		String sBirthDate = ""; // 생일
		String sGender = ""; // 성별
		String sNationalInfo = ""; // 내/외국인정보 (개발가이드 참조)
		String sMessage = "";
		String sPlainData = "";
		
		int iReturn1 = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

		if (iReturn1 == 0) {
			sPlainData = niceCheck.getPlainData();
			sCipherTime = niceCheck.getCipherDateTime();

			// 데이타를 추출합니다.
			java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);

			sRequestNumber = (String) mapresult.get("REQ_SEQ");
			sResponseNumber = (String) mapresult.get("RES_SEQ");
			sAuthType = (String) mapresult.get("AUTH_TYPE");
			sName = (String) mapresult.get("NAME");
			sBirthDate = (String) mapresult.get("BIRTHDATE");
			sGender = (String) mapresult.get("GENDER");
			sNationalInfo = (String) mapresult.get("NATIONALINFO");
			sDupInfo = (String) mapresult.get("DI");
			sConnInfo = (String) mapresult.get("CI");
			// 휴대폰 번호 : MOBILE_NO => (String)mapresult.get("MOBILE_NO");
			// 이통사 정보 : MOBILE_CO => (String)mapresult.get("MOBILE_CO");
			// checkplus_success 페이지에서 결과값 null 일 경우, 관련 문의는 관리담당자에게 하시기 바랍니다.
			String session_sRequestNumber = (String) ses.getAttribute("REQ_SEQ");
			if (!sRequestNumber.equals(session_sRequestNumber)) {
				sMessage = "세션값이 다릅니다. 올바른 경로로 접근하시기 바랍니다.";
				sResponseNumber = "";
				sAuthType = "";
			}
			
		} else if (iReturn1 == -1) {
			sMessage = "복호화 시스템 에러입니다.";
		} else if( iReturn1 == -4) {
	        sMessage = "복호화 처리오류입니다.";
	    } else if( iReturn1 == -5) {
	        sMessage = "복호화 해쉬 오류입니다.";
	    } else if( iReturn1 == -6) {
	        sMessage = "복호화 데이터 오류입니다.";
	    } else if( iReturn1 == -9) {
	        sMessage = "입력 데이터 오류입니다.";
	    } else if( iReturn1 == -12) {
	        sMessage = "사이트 패스워드 오류입니다.";
	    } else {
	        sMessage = "알수 없는 에러 입니다. iReturn1 : " + iReturn1;
	    }
		
		if (sEnc_data2 != "") {
			int iReturn = 0;
			if (iReturn1 == 0) {
				iReturn = 1;
			}
		}
		return "/common/mobileAuth/mobile_auth";
	}
	
	@RequestMapping(value = "/site/{strDomain}/user/mobileAuth_P.do")
	public String parentCertResponse1(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		HttpSession ses = request.getSession();

		NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();

		String sEnc_data = request.getParameter("parent_enc_data");
		String sEnc_data2 = request.getParameter("EncodeData");

		String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
		String sReserved1 = requestReplace(request.getParameter("param_r1"), "");
		String sReserved2 = requestReplace(request.getParameter("param_r2"), "");
		String sReserved3 = requestReplace(request.getParameter("param_r3"), "");

		String sSiteCode = "G6419"; // NICE로부터 부여받은 사이트 코드
		String sSitePassword = "7ZE70AH55WN6"; // NICE로부터 부여받은 사이트 패스워드

		String name = ""; // 성명
		String sex = ""; // 성별
		String birYMD = ""; // 생년월일
		String fgnGbn = ""; // 내외국인 구분값

		String di = ""; // DI

		String sCipherTime = ""; // 복호화한 시간
		String sRequestNumber = ""; // 요청 번호
		String sResponseNumber = ""; // 인증 고유번호
		String sAuthType = ""; // 인증 수단
		String sName = ""; // 성명
		String sDupInfo = ""; // 중복가입 확인값 (DI_64 byte)
		String sConnInfo = ""; // 연계정보 확인값 (CI_88 byte)
		String sBirthDate = ""; // 생일
		String sGender = ""; // 성별
		String sNationalInfo = ""; // 내/외국인정보 (개발가이드 참조)
		String sMessage = "";
		String sPlainData = "";
		int iReturn1 = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

		if (iReturn1 == 0) {
			sPlainData = niceCheck.getPlainData();
			sCipherTime = niceCheck.getCipherDateTime();

			// 데이타를 추출합니다.
			java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);

			sRequestNumber = (String) mapresult.get("REQ_SEQ");
			sResponseNumber = (String) mapresult.get("RES_SEQ");
			sAuthType = (String) mapresult.get("AUTH_TYPE");
			sName = (String) mapresult.get("NAME");
			sBirthDate = (String) mapresult.get("BIRTHDATE");
			sGender = (String) mapresult.get("GENDER");
			sNationalInfo = (String) mapresult.get("NATIONALINFO");
			sDupInfo = (String) mapresult.get("DI");
			sConnInfo = (String) mapresult.get("CI");
			// 휴대폰 번호 : MOBILE_NO => (String)mapresult.get("MOBILE_NO");
			// 이통사 정보 : MOBILE_CO => (String)mapresult.get("MOBILE_CO");
			// checkplus_success 페이지에서 결과값 null 일 경우, 관련 문의는 관리담당자에게 하시기 바랍니다.
			String session_sRequestNumber = (String) ses.getAttribute("REQ_SEQ");
			if (!sRequestNumber.equals(session_sRequestNumber)) {
				sMessage = "세션값이 다릅니다. 올바른 경로로 접근하시기 바랍니다.";
				sResponseNumber = "";
				sAuthType = "";
			}
		}
		return "/common/mobileAuth/mobile_auth_p";
	}
	
	/**
	 * 회원정보 입력폼 step04
	 * @param userFVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/user/User_Step_04.do")
	public String memberUserInsert(@PathVariable("strDomain") String strDomain, HttpServletRequest request,@ModelAttribute("UserFVo") UserFVo userFVo, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		//String gpincode = (String)ses.getAttribute("ss_dupkey");
		String gpincode = "true"; //테스트
		String returnurl = "";		

		String youngYn = request.getParameter("youngYn");
		userFVo.setYoungYn(youngYn);
		model.addAttribute("userFVo",userFVo);
		
		if(gpincode == null || gpincode.equals("")){
			return alert("/site/{strDomain}/user/User_Step_03.do", "본인확인 후 이용가능합니다.", model);
		}else{
			int ducount = userSvc.retrieveUserFCnt(gpincode);
			if(ducount < 1){
				String cuId = (String)ses.getAttribute("ss_id");
				if(cuId != null){
					userFVo.setCuId(cuId);					
				}
				returnurl = "injeinc/foffice/user/user_step_04";
			}else if(ducount == 1){
				ses.invalidate();
				return alert("/site/{strDomain}/main.do", "이미 가입하신 회원이십니다. 로그인 후 사용하시기 바랍니다.", model);
			}else if(ducount > 1){
				userFVo.setOwnAuth(gpincode);
				List userlist = userSvc.retrieveUserListF(userFVo);
				model.addAttribute("userlist", userlist);
				returnurl = "injeinc/foffice/user/user_step_dupuserlist";
			}
		}	
		model.addAttribute("strDomain", strDomain);
		return returnurl;
	}
	/**
	 * 회원가입 등록
	 * @param request
	 * @param userFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/user/User_Reg.do")
	public String memberUserReg(HttpServletRequest request,@ModelAttribute("UserFVo") UserFVo userFVo, ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();

		userFVo.setCuPwd(EgovFileScrty.encryptPassword(userFVo.getCuPwd())); // 비밀번호 암호화(단방향)
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		userFVo.setIp(ip);
		//String ownAuth = (String)ses.getAttribute("ss_dupkey");
		//userFVo.setOwnAuth(ownAuth);
		
		//전화번호
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		
		String telNum = tel1 +"-"+ tel2 +"-"+ tel3;
		userFVo.setTelNum(telNum);
		
		//핸드폰 번호
		String hp1 = request.getParameter("hp1");
		String hp2 = request.getParameter("hp2");
		String hp3 = request.getParameter("hp3");
		
		String hpNum = hp1 +"-"+ hp2 +"-"+ hp3;
		userFVo.setHpNum(hpNum);
		
		//이메일 주소
		String emailid = request.getParameter("emailid");
		String emaildomain = request.getParameter("emaildomain");
		String emaildomainText = request.getParameter("emaildomainText");
		
		if("X".equals(emaildomain)) {
			String email = emailid + "@" + emaildomainText;
			userFVo.setEmail(email);
		}else {
			String email = emailid + "@" + emaildomain;
			userFVo.setEmail(email);
		}
		
		userSvc.registUserF(userFVo); // 회원가입 등록
		ses.setAttribute("ss_user", "user");
		
		
		if(userFVo.getSlibno() != null && !userFVo.getSlibno().equals("")){
			URL url = null;
			HttpURLConnection  connection = null;
			try{
				url = new URL("http://lib.domain.go.kr/sync/sync_xml.asp");
				connection = (HttpURLConnection)url.openConnection();
				connection.setConnectTimeout(3000);
				connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=euc-kr");
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestMethod("POST");		
				
				StringBuffer sb = new StringBuffer();
							
				System.out.println(" libno : "+userFVo.getSlibno());
				System.out.println(" uid : "+userFVo.getCuId());
				System.out.println(" uname : "+URLEncoder.encode(userFVo.getCuName(), "euc-kr"));
				System.out.println(" uname : "+userFVo.getCuName());
				System.out.println(" sex : "+userFVo.getSex());
				System.out.println(" birthdate : "+userFVo.getBirthDate());
				System.out.println(" mobile : "+userFVo.getCoNum());
				System.out.println(" zipcode : "+userFVo.getZip());
				System.out.println(" addr1 : "+userFVo.getAddr1());
				System.out.println(" addr2 : "+userFVo.getAddr2());
				System.out.println(" addr1 : "+URLEncoder.encode(userFVo.getAddr1(), "euc-kr"));
				System.out.println(" addr2 : "+URLEncoder.encode(userFVo.getAddr2(), "euc-kr"));
				
				sb.append("fname=F502");
				sb.append("&libno="+userFVo.getSlibno());
				sb.append("&uid="+userFVo.getCuId());
				sb.append("&uname="+URLEncoder.encode(userFVo.getCuName(), "euc-kr"));
				sb.append("&sex="+userFVo.getSex());
				sb.append("&birthdate="+userFVo.getBirthDate());
				sb.append("&mobile="+userFVo.getCoNum());
				sb.append("&zipcode="+userFVo.getZip());	
				sb.append("&addr1="+URLEncoder.encode(userFVo.getAddr1(), "euc-kr"));	
				sb.append("&addr2="+URLEncoder.encode(userFVo.getAddr2(), "euc-kr"));	
						
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
				pw.write(sb.toString());
				pw.flush();
				Document doc = parseXML(connection.getInputStream());
				NodeList descNodes = doc.getElementsByTagName("item");		
				pw.close();
				
				Node node = (Node)descNodes.item(0);
				Node uidNode = ((Element)node).getElementsByTagName("RESULT").item(0);
				System.out.println("RESULT : "+URLDecoder.decode(unescape(uidNode.getTextContent()),"euc-kr"));
			} catch(SocketTimeoutException e){
				return "redirect:/site/{strDomain}/user/joinEnd.do";
			} catch(IOException e){
				return "redirect:/site/{strDomain}/user/joinEnd.do";
			} finally{
				if(connection != null){
					connection.disconnect();
				}				
			}									
		}				
		return "redirect:/site/{strDomain}/user/joinEnd.do";
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
	/**
	 * 회원가입 완료
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value= "/site/{strDomain}/user/joinEnd.do")
	public String memberWriteFinish(@PathVariable("strDomain") String strDomain, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		model.addAttribute("strDomain", strDomain);
		return "injeinc/foffice/user/joinEnd";
	}
	
	
	/** 아이디 사용여부 체크 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/site/{strDomain}/user/UserIdCheck_Ax.do")
	public void SearchthemeUseYn_Ax (@PathVariable("strDomain") String strDomain, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession ses = request.getSession();
		ses.invalidate();
		String cuId = request.getParameter("cuId");

		HashMap<String, String> param = new HashMap<String, String>();
		param.put("cuId", cuId);

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();

		int idCnt = userSvc.retrieveCuIdCnt(param); // 아이디 사용여부 조회
		int idCnt2 = userSvc.retrieveCuIdCnt2(param); // 아이디 사용여부 조회 (탈퇴 회원 조회)

		if (idCnt > 0 || idCnt2 > 0) {
			jsonMap.put("message", "N");
		} else {
			jsonMap.put("message", "Y");
		}
		jsonView.render(jsonMap, request, response);
	}
	
	
	/**
	 * 입력 취소
	 * @param request
	 * @param userFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/site/{strDomain}/foffice/user/User_Cancel.do")
	public String userCancel(HttpServletRequest request, @ModelAttribute("UserFVo") UserFVo userFVo, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		ses.invalidate();
		String SVC_MSGE = "";
		SVC_MSGE = Message.getMessage("904.message");
		
		return alert("/site/{strDomain}/main.do", SVC_MSGE, model);
	}
	
	public static String requestReplace(String paramValue, String gubun) {
		String result = "";

		if (paramValue != null) {
			paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			paramValue = paramValue.replaceAll("\\*", "");
			paramValue = paramValue.replaceAll("\\?", "");
			paramValue = paramValue.replaceAll("\\[", "");
			paramValue = paramValue.replaceAll("\\{", "");
			paramValue = paramValue.replaceAll("\\(", "");
			paramValue = paramValue.replaceAll("\\)", "");
			paramValue = paramValue.replaceAll("\\^", "");
			paramValue = paramValue.replaceAll("\\$", "");
			paramValue = paramValue.replaceAll("'", "");
			paramValue = paramValue.replaceAll("@", "");
			paramValue = paramValue.replaceAll("%", "");
			paramValue = paramValue.replaceAll(";", "");
			paramValue = paramValue.replaceAll(":", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll("#", "");
			paramValue = paramValue.replaceAll("--", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll(",", "");

			if (gubun != "encodeData") {
				paramValue = paramValue.replaceAll("\\+", "");
				paramValue = paramValue.replaceAll("/", "");
				paramValue = paramValue.replaceAll("=", "");
			}
			result = paramValue;
		}
		return result;
	}
	
	/**
	 * 비밀번호 확인폼
	 * @param request
	 * @param response
	 * @param userFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/user/UserPwCheck.do")
	public String SearchPwChk(@PathVariable("strDomain") String strDomain,HttpServletRequest request, HttpServletResponse response, @ModelAttribute("UserFVo") UserFVo userFVo, ModelMap model) throws Exception {
		model.addAttribute("strDomain", strDomain);
		model.addAttribute("UserFVo", userFVo);
		return "injeinc/foffice/user/user_pw_chk";
	}
	
	/** 비밀번호 체크 
	 * @return */
	@RequestMapping(value = "/site/{strDomain}/user/UserPwCheck_Ax.do")
	public String SearchPwChk_Ax
	(@PathVariable("strDomain") String strDomain, @ModelAttribute("UserFVo") UserFVo userFVo,
    HttpServletRequest request,
    HttpServletResponse response, ModelMap model) throws Exception {
		
		String returnUrl = "";
		String SVC_MSGE = "";
		
		try{
			String cuPwd = userSvc.retrieveUserFPwd(userFVo);		//비밀번호 조회
			if(!cuPwd.equals(EgovFileScrty.encryptPassword(userFVo.getCuPwd()))){
				SVC_MSGE = Message.getMessage("605.message");
				returnUrl = alert("/site/" + strDomain + "/user/UserPwCheck.do?", SVC_MSGE, model);
			}else{				
				UserFVo view = userSvc.retrieveUserF(userFVo);
				view.setUserMenu(userFVo.getUserMenu());				
				String hpTemp = view.getHpNum();
				String telTemp = view.getTelNum();
				String[] hpArray = null;
				String[] telArray = null;
				
				if(hpTemp != null) {
					hpArray = hpTemp.split("-", 3);
					view.setHp1(hpArray[0]);
					view.setHp2(hpArray[1]);
					view.setHp3(hpArray[2]);	
				}
				
				if(telTemp != null) {
					telArray = telTemp.split("-", 3);
					view.setTel1(telArray[0]);
					view.setTel2(telArray[1]);
					view.setTel3(telArray[2]);
				}
				
				List<String> emailList = userSvc.selectEmailList();
				if(model != null){
					model.addAttribute("UserFVo",view);
					model.addAttribute("emailList",emailList);
				}
				debugLog("toString : " + view);
				
				returnUrl = "injeinc/foffice/user/user_step_06";
			}

		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}
		
		return returnUrl; 
	}
	
	//회원관리 수정 X
	@RequestMapping(value="/site/{strDomain}/user/User_Mod.do")
	public String memberuserMod(@PathVariable("strDomain") String strDomain,HttpServletRequest request,
			@ModelAttribute("UserFVo") UserFVo userFVo,
			ModelMap model) throws Exception {
		String returnUrl = "";
		//HttpSession ses = request.getSession();
		//String modId = (String)ses.getAttribute("ss_id");
		
		if(!userFVo.getCuPwd().equals("")){
			userFVo.setNewpwd(XecureUtil.DigestX64(userFVo.getCuPwd()));
		}
		
		//userSvc.modifyUserFPwd(userFVo);	//비밀번호 변경
		userSvc.modifyUserF(userFVo);

		model.addAttribute("strDomain", strDomain);
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		return returnUrl;
	}
	
	/**
	 * 회원정보 수정폼 
	 * @param userFVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/user/User_Step_06.do")
	public String memberModify(@PathVariable("strDomain") String strDomain, HttpServletRequest request
			,@ModelAttribute("UserFVo") UserFVo userFVo, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		String returnurl = "";		

		//회원정보 조회
		String cuId = (String)ses.getAttribute("ss_id");
		userFVo.setCuId(cuId);
		UserFVo userlist = userSvc.retrieveUserF(userFVo);
		model.addAttribute("userlist", userlist);
		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/user/user_step_06";
	}
	
	//회원정보 수정
	@RequestMapping(value = "/site/{strDomain}/user/memberModify_step01.do")
	public String memberModify_step01(@PathVariable("strDomain") String strDomain, HttpServletRequest request
			,@ModelAttribute("UserFVo") UserFVo userFVo
			,RedirectAttributes rttr , ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		String returnUrl = "";		
		String ip = request.getHeader("X-FORWARDED-FOR");
		
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		userFVo.setIp(ip);
		String cuId = (String)ses.getAttribute("ss_id");
		userFVo.setCuId(cuId);
		
		//전화번호
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		
		String telNum = tel1 +"-"+ tel2 +"-"+ tel3;
		userFVo.setTelNum(telNum);
		
		//핸드폰 번호
		String hp1 = request.getParameter("hp1");
		String hp2 = request.getParameter("hp2");
		String hp3 = request.getParameter("hp3");
		
		String hpNum = hp1 +"-"+ hp2 +"-"+ hp3;
		userFVo.setHpNum(hpNum);
		
		//이메일 주소
		String emailid = request.getParameter("emailid");
		String emaildomain = request.getParameter("emaildomain");
		String emaildomainText = request.getParameter("emaildomainText");
		
		if("X".equals(emaildomain)) {
			String email = emailid + "@" + emaildomainText;
			userFVo.setEmail(email);
		}else {
			String email = emailid + "@" + emaildomain;
			userFVo.setEmail(email);
		}
		
		//수정된 회원정보 저장
		userSvc.modifyUserF(userFVo); 
		
		model.addAttribute("strDomain", strDomain);
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		returnUrl = alert("/site/DRP0000/user/User_Step_06.do", SVC_MSGE, model);
		//returnUrl = "redirect:/site/DRP0000/user/User_Step_06.do";
		return returnUrl;
	}
}
