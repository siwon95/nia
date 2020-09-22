<%@ page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="java.util.*,java.text.SimpleDateFormat"%>
<%@ page import="egovframework.injeinc.common.util.EgovStringUtil"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="org.springframework.web.util.WebUtils"%>
<%@ page import="egovframework.cmm.service.EgovProperties"%>
<%
	HttpSession ses = request.getSession();

	NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();

	String sEnc_data = request.getParameter("parent_enc_data");
	String sEnc_data2 = request.getParameter("EncodeData");

	System.out.println("EncodeData : " + sEnc_data2);

	String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
	String sReserved1 = requestReplace(request.getParameter("param_r1"), "");
	String sReserved2 = requestReplace(request.getParameter("param_r2"), "");
	String sReserved3 = requestReplace(request.getParameter("param_r3"), "");

	String sSiteCode = "G6419"; // NICE로부터 부여받은 사이트 코드
	String sSitePassword = "7ZE70AH55WN6"; // NICE로부터 부여받은 사이트 패스워드

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

	System.out.println("iReturn1 : " + iReturn1);

	int iReturn = 0;
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
		String session_sRequestNumber = (String) ses.getAttribute("REQ_SEQP");
		if (!sRequestNumber.equals(session_sRequestNumber)) {
			out.println("<script type='text/javascript'>");
			out.println("	alert('비정상적인 접근입니다.');");
			out.println("	self.close(); ");
			out.println("</script>");
			return;
		}

		out.println("<script type='text/javascript'>");
		out.println("	alert('인증되었습니다.');");
		out.println("	self.close(); ");
		out.println("</script>");

		session.setMaxInactiveInterval(3600);
		session.setAttribute("ss_id", "실명인증");
		session.setAttribute("ss_name", sName);
		session.setAttribute("ss_level", "8");
		session.setAttribute("ss_dupkey", sDupInfo);
		session.setAttribute("ss_birth", sBirthDate);

		System.out.println("sName : " + sName);
		System.out.println("sDupInfo : " + sDupInfo);
		System.out.println("sBirthDate : " + sBirthDate);
	}

	if (sEnc_data != "") {
		iReturn = 0;
		if (iReturn1 == 0) {
			iReturn = 1;
		}
	}

	else if (iReturn1 == -1) {
		sMessage = "복호화 시스템 에러입니다.";
	} else if (iReturn1 == -4) {
		sMessage = "복호화 처리오류입니다.";
	} else if (iReturn1 == -5) {
		sMessage = "복호화 해쉬 오류입니다.";
	} else if (iReturn1 == -6) {
		sMessage = "복호화 데이터 오류입니다.";
	} else if (iReturn1 == -9) {
		sMessage = "입력 데이터 오류입니다.";
	} else if (iReturn1 == -12) {
		sMessage = "사이트 패스워드 오류입니다.";
	} else {
		sMessage = "알수 없는 에러 입니다. iReturn1 : " + iReturn1;
	}
%>
<script type="text/javascript">
<!--
	var iReturn = <%=iReturn%>;
	
	if(iReturn == 1){
		window.opener.focus();
		window.opener.writeJNParent();
		window.close();
	}else{
		alert('<%=sMessage%>');
		self.close();
	}
//-->
</script>
<%!
public static String requestReplace(String paramValue, String gubun) {
	String result = "";

	if (paramValue != null) {

		paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">","&gt;");

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
%>