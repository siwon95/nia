<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
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
	location.href = "/site/yangcheon/user/User_Step_06.do";
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
</script>
<form method="post" id="dform" name="dform" target="certpop">
	<input type="hidden" name="EncodeData" value="${enc_data}" />
	<input type="hidden" name="m" value="checkplusSerivce">
	<!-- 업체에서 응답받기 원하는 데이타를 설정하기 위해 사용할 수 있으며, 인증결과 응답시 해당 값을 그대로 송신합니다.
    	 해당 파라미터는 추가하실 수 없습니다. -->
	<input type="hidden" name="param_r1" value="">
	<input type="hidden" name="param_r2" value="">
	<input type="hidden" name="param_r3" value="">
	<input type="hidden" id="formType" name="formType" value="signUp" />
</form>

<form method="post" name="eform" id="eform" action="/site/yangcheon/user/User_Step_06.do">
	<input type="hidden" name="cuName" id="cuName" value=""/>
</form>
<div class="join-process-container">
	<p class="decription">회원가입은 회원구분 - 약관동의 - 본인인증 - 개인정보입력 - 가입완료 순으로 진행됩니다.</p>
	<ol>
		<li class="join-pr1">
			<div>
				<span class="step">STEP 01</span>
				<span class="join-text">회원구분</span>
			</div>
		</li>
		<li class="join-pr2">
			<div>
				<span class="step">STEP 02</span>
				<span class="join-text">약관동의</span>
			</div>
		</li>
		<li class="join-pr3 on">
			<div>
				<span class="step">STEP 03</span>
				<h1 class="join-process-3"><span class="join-text">본인인증</span></h1>
			</div>
		</li>
		<li class="join-pr4">
			<div>
				<span class="step">STEP 04</span>
				<span class="join-text">개인정보 입력</span>
			</div>
		</li>
		<li class="join-pr5">
			<div>
				<span class="step"> STEP 05</span>
				<span class="join-text"> 가입완료</span>
			</div>
		</li>

	</ol>
</div>

<div class="join-content-wrap join-certification">

	<p class="join-certification-txt"><strong>만 14세 이상 일반회원</strong>의 본인 인증 방법을 선택해 주세요.</p>

	<div class="join-certification-kind">
		<ul>
			<li>
				<div>
					<h2>G-PIN</h2>
					<p>information : 02-818-3050</p>
					<a href="javascript:gpinAuth();" class="btn btn-certification-ipin">G-PIN Certify</a>
					<a href="http://www.g-pin.go.kr/center/join/join_index.gpin" class="btn btn-certification-ipin-join" target="_blank">G-PIN New Account</a>
				</div>
			</li>
			<li>
				<div>
					<h2>Mobile</h2>
					<a href="#" onclick="fn_confirmCert();return false;" class="btn btn-certification-mobile">Mobile Certify</a>
				</div>
			</li>
		</ul>
	</div>

	<div class="join-process-notice">
		<p>아이핀(I-Pin)은 발급기관과 상관없이 본인이 발급받은 아이핀을 이용하여 실명인증을 할 수 있습니다.</p>
		<p>공공 아이핀(I-PIN) 이란?</p>
		<p>공공 아이핀(I-PIN)은 행정자치부에서 주관하는 주민등록번호 대체 수단으로 회원님의 주민등록 번호 대신 식별 ID를 행정자치부로부터 발급받아 대면확인이 어려운 인터넷에서 주민등록번호를 사용하지 않고도 본인임을 확인할 수 있는 서비스입니다.</p>
		<p>공공 I-PIN 이용안내</p>
		<p>2014년 8월 7일부터 개정 시행되는 개인정보보호법에 의해 회원가입시 주민등록번호를 사용하는 실명확인 인증이 폐지되고, 공공 I-PIN인증 또는 휴대폰 본인인증 절차를 거쳐서 회원가입을 하실 수 있습니다</p>
	</div>

</div>