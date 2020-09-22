<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="java.util.*,java.text.SimpleDateFormat"%>
<%@ page import="egovframework.injeinc.common.util.EgovStringUtil"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="org.springframework.web.util.WebUtils"%>
<%@ page import="egovframework.cmm.service.EgovProperties"%>
<%
String retInfo = EgovStringUtil.isNullToString(request.getParameter("retInfo"));	// 결과정보

if(retInfo.equals("")) {
		
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-store");
	response.setHeader("Cache-Control", "no-cache");
	
	Calendar today = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String day = sdf.format(today.getTime());

	java.util.Random ran = new Random();
	
	int numLength = 6;
	String randomStr = "";

	for (int i = 0; i < numLength; i++) {
		randomStr += ran.nextInt(10);
	}

	StringBuffer urlBuffer = request.getRequestURL();
	String firstUrl = urlBuffer.toString();
	firstUrl = firstUrl.substring(0, firstUrl.indexOf("/common/mobileAuth"));
	
	String id       = "SCHO002";													// 본인실명확인 회원사 아이디
	String srvNo    = "001015";														// 본인실명확인 서비스번호
	
	/*
	if(firstUrl.startsWith("https")) {
		srvNo = "012003";
	}else{
		srvNo = "011003";
	}
	*/
	
	String reqNum   = day + randomStr;												// 본인실명확인 요청번호
	String exVar    = "0000000000000000";											// 복호화용 임시필드
	String retUrl   = "32"+firstUrl+"/common/mobileAuth/mobile_auth_parent.jsp";	// 본인실명확인 결과수신 URL
	String certDate	= day;															// 본인실명확인 요청시간
	String certGb	= "H";															// 본인실명확인 본인확인 인증수단
	String addVar	= "";															// 본인실명확인 추가 파라메터
	
	String platform	= EgovProperties.getProperty("Globals.PLATFORM");
	if(platform != null){
		if(!platform.equals("service")) {	
			srvNo = "015003";																//localhost 용
			retUrl = "32http://localhost:8080/common/mobileAuth/mobile_auth_parent.jsp";	//localhost 용
		}
	}

	session.setAttribute("reqNum", reqNum);

	com.sci.v2.pcc.secu.SciSecuManager seed  = new com.sci.v2.pcc.secu.SciSecuManager();

	String encStr   = "";
	String reqInfo = id+"^"+srvNo+"^"+reqNum+"^"+certDate+"^"+certGb+"^"+addVar+"^"+exVar;  // 데이터 암호화
	encStr         = seed.getEncPublic(reqInfo);

	com.sci.v2.pcc.secu.hmac.SciHmac hmac = new com.sci.v2.pcc.secu.hmac.SciHmac();
	String hmacMsg = hmac.HMacEncriptPublic(encStr);

	reqInfo  = seed.getEncPublic(encStr + "^" + hmacMsg + "^" + "0000000000000000");  //2차암호화
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>본인실명확인 서비스</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language=javascript>
	function openPCCWindow(){ 
		document.reqPCCForm.submit();
	}	
</script>
</head>
<body onload="openPCCWindow();">
	<form name="reqPCCForm" method="post" action="https://pcc.siren24.com/pcc_V3/jsp/pcc_V3_j10.jsp">
		<input type="hidden" name="reqInfo" value="<%=reqInfo%>">
		<input type="hidden" name="retUrl"  value="<%=retUrl%>">
		<input type="submit" alt="확인" style="display: none" value="본인확인서비스V3 요청" >	
	</form>
</body>
</HTML>
<%
}else{
	
	String name			= "";	//성명
	String sex			= "";	//성별
	String birYMD		= "";	//생년월일
	String fgnGbn		= "";	//내외국인 구분값
	
	String di			= "";	//DI
	String ci1			= "";	//CI
	String ci2			= "";	//CI
	String civersion	= "";	//CI Version
	
	String result		= "";	// 본인확인결과 (Y/N)
	String certDate		= "";	// 검증시간
	String certGb		= "";	// 인증수단
	String cellNo		= "";	// 핸드폰 번호
	String cellCorp		= "";	// 이동통신사
	String addVar		= "";

	//복화화용 변수
	String encPara		= "";
	String encMsg		= "";
	String msgChk		= "N";
	
	//-----------------------------------------------------------------------------------------------------------------

	String reqNum = (String) WebUtils.getSessionAttribute(request, "reqNum");

	try{
		com.sci.v2.pcc.secu.SciSecuManager sciSecuMg = new com.sci.v2.pcc.secu.SciSecuManager();
		
		retInfo  = sciSecuMg.getDec(retInfo, reqNum);
		String[] aRetInfo1 = null;
		if(retInfo != null){
			aRetInfo1 = retInfo.split("\\^");
		}

		encPara  = aRetInfo1[0];		//암호화된 통합 파라미터
		encMsg   = aRetInfo1[1];		//암호화된 통합 파라미터의 Hash값
		
		String  encMsg2   = sciSecuMg.getMsg(encPara);
		if(encMsg2 != null){
			if(encMsg2.equals(encMsg)){
				msgChk="Y";
			}
		}

		if(msgChk.equals("N")) {
			
			out.println("<script type='text/javascript'>");
			out.println("	alert('비정상적인 접근입니다.!!"+msgChk+"');");
			out.println("</script>");
			return;
		}


		retInfo = sciSecuMg.getDec(encPara, reqNum);
		String[] aRetInfo = null;
		if(retInfo != null){
			aRetInfo = retInfo.split("\\^");
		}
		
		name		= aRetInfo[0];
		birYMD		= aRetInfo[1];
		sex			= aRetInfo[2];
		fgnGbn		= aRetInfo[3];
		di			= aRetInfo[4];
		ci1			= aRetInfo[5];
		ci2			= aRetInfo[6];
		civersion	= aRetInfo[7];
		reqNum		= aRetInfo[8];
		result		= aRetInfo[9];
		certGb		= aRetInfo[10];
		cellNo		= aRetInfo[11];
		cellCorp	= aRetInfo[12];
		certDate	= aRetInfo[13];
		addVar		= aRetInfo[14];

		String platform	= EgovProperties.getProperty("Globals.PLATFORM");
		if(platform != null){
			if(!platform.equals("service")) {
				//System.out.println("인코딩전 name===========>"+name);
				name = URLDecoder.decode(name,"UTF-8");
				//System.out.println("인코딩후 name===========>"+name);
			}
		}
		if(result != null){
			if(result.equals("Y")) {
				
				session.setMaxInactiveInterval(3600);
				session.setAttribute("ss_parent_name", name);
				session.setAttribute("ss_parent_dupkey", di);
				session.setAttribute("ss_parent_birth", birYMD);
				
				//System.out.println("[mobile check] mobile_check blank: "+di+"///"+request.getRemoteAddr());
					
				out.println("<script type='text/javascript'>");
				out.println("	window.opener.focus();");
				out.println("	window.opener.writeJNParent();");
				out.println("	window.close();");
				out.println("</script>");
				
			}else{
				out.println("<script type='text/javascript'>");
				out.println("	alert('인증에 실패했습니다.!!');");
				out.println("	window.close();");
				out.println("</script>");
			}
		}

	}catch(Exception ex){
		System.out.println("[pcc] Receive Error");
		throw ex;
	}
}
%>