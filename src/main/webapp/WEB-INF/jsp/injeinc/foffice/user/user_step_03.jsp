<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
//<![CDATA[
//gpin
function gpinAuth() {
	var wWidth = 360;
	var wHight = 120;
	
	var wX = (window.screen.width - wWidth) / 2;
	var wY = (window.screen.height - wHight) / 2;

	var w = window.open("/G-PIN/pin8520Requ.jsp", "gPinLoginWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);
	w.focus();
}

function mobileAuth() {
	var PAgree = open("/common/mobileAuth/mobile_auth.jsp", "CheckJN", "width=420, height=570, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=480, top=115");
	PAgree.focus();
}

//처리
function writeJN() {
	location.href = "/site/${strDomain}/user/User_Step_06.do";
}

var certPopWindow;

var windowObject;

// 휴대폰 본인인증
function fn_confirmCert() {
	if(windowObject!="") {
		windowObject=certPopWindow=window.open('', 'certpop', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	}
	document.dform.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
	document.dform.target = "certpop";
	document.dform.submit();
 }
//]]>

//휴대폰 인증 개발완료시 삭제
function doJoinForm(){
	document.dform.action = "/site/DRP0000/user/User_Step_04.do"; 
	document.dform.submit();
}

</script>
<form method="post" name="eform" id="eform" action="/site/${strDomain}/user/User_Step_06.do">
	<input type="hidden" name="cuName" id="cuName" value=""/>
</form>

<form method="post" id="dform" name="dform" >
	<input type="hidden" name="EncodeData" value="${enc_data}" />
	<input type="hidden" name="m" value="checkplusSerivce">
	<!-- 업체에서 응답받기 원하는 데이타를 설정하기 위해 사용할 수 있으며, 인증결과 응답시 해당 값을 그대로 송신합니다.
    	 해당 파라미터는 추가하실 수 없습니다. -->
	<input type="hidden" name="param_r1" value="">
	<input type="hidden" name="param_r2" value="">
	<input type="hidden" name="param_r3" value="">
	<input type="hidden" name="youngYn" value="${userFVo.youngYn }">
	<input type="hidden" id="formType" name="formType" value="signUp" />

<!-- 여기부터 -->
	<h3>회원가입</h3>
	<div class="member_info_bg leave_info_bg">
		<div>
			<span>
				디지털 역기능 대응서비스 통합안내 시스템에 방문하신 것을 환영합니다.<br>
				회원가입을 위해 아래 약관에 동의하여 주십시오.
			</span>
		</div>
	</div>


	<ul class="agree_step">
		<li class="step01">
			<span></span>
			<b>Step01<em>약관동의</em></b>
		</li>
		<li class="step02 on">
			<span></span>
			<b>Step02<em>본인확인</em></b>
		</li>
		<li class="step03">
			<span></span>
			<b>Step03<em>정보입력</em></b>
		</li>
		<li class="step04">
			<span></span>
			<b>Step04<em>가입신청완료</em></b>
		</li>
	</ul>
	
	<div class="divGroup certification">
		<div class="cer_sms w100p">
			<strong>휴대전화 인증</strong>
			<span>
				본인 명의의 휴대전화로 
				간단하고 빠른 로그인을 하실 수 있습니다.
			</span>
			<div class="btnArea">
				<input type="submit" class="btn_l" value="휴대전화 인증하기" onclick="doJoinForm()"/>
			</div>

		</div>
	</div>
</form>	
	<!-- 여기까지 -->