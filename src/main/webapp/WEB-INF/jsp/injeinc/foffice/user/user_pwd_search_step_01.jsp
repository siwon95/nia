<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">
//<![CDATA[
//gpin
function gpinAuth() {
	if(cm_check_empty($('input[id=cuId]').attr("title"), $('input[id=cuId]')) == false) {
		return;
	}
	var wWidth = 360;
	var wHight = 120;
	
	var wX = (window.screen.width - wWidth) / 2;
	var wY = (window.screen.height - wHight) / 2;

	var w = window.open("/G-PIN/pin8520Requ.jsp", "gPinLoginWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);
	w.focus();
	
}

function mobileAuth() {
	if(cm_check_empty($('input[id=cuId]').attr("title"), $('input[id=cuId]')) == false) {
		return;
	}
	var PAgree = open("/common/mobileAuth/mobile_auth.jsp", "CheckJN", "width=420, height=570, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=480, top=115");
	PAgree.focus();

}

function writeJN() {
	//처리
//	location.href = "/site/yangcheon/foffice/user/User_Pwd_Search_Step_02.do";
	document.UserFVo.action= "/site/${strDomain}/user/User_Pwd_Search_Step_02.do";
	document.UserFVo.submit();
}

var certPopWindow;

var windowObject;

function fn_confirmCert(){
 	if(windowObject!=""){
		windowObject=certPopWindow=window.open('', 'certpop', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	}	
	document.dform.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
	document.dform.target = "certpop";
	document.dform.submit();
 }

function dofindPwd(){

	if(!$("#cuId").val()){    
		alert("아이디를 입력해 주세요");
		$("#cuId").focus();
		return false;
	}else{
		document.UserFVo.action = "/site/DRP0000/user/User_Pwd_Search_Step_02.do";
		document.UserFVo.submit();
	}
}
 
//]]>
</script>
<form method="post" id="dform" name="dform" target="certpop" >	
		
	<input type="hidden" name="EncodeData" value="${enc_data}"/>
	<input type="hidden" name="m" value="checkplusSerivce">		
	<!-- 업체에서 응답받기 원하는 데이타를 설정하기 위해 사용할 수 있으며, 인증결과 응답시 해당 값을 그대로 송신합니다.
    	 해당 파라미터는 추가하실 수 없습니다. -->
	<input type="hidden" name="param_r1" value="">
	<input type="hidden" name="param_r2" value="">
	<input type="hidden" name="param_r3" value="">
	<input type="hidden" id="formType" name="formType" value="findPw" />
		
</form>
<form method="post" name="UserFVo" id="UserFVo">
	<input type="hidden" name="ownAuth" value="${sDupInfo}"/>
	<!-- 여기부터 -->
	<h3>비밀번호 찾기</h3>
	<div class="member_info_bg pw_info_bg">
		<div>
			<span>아이디 입력 후 “확인”버튼을 클릭하여 아이디가 존재하는지 확인해 주십시오.</span>
		</div>
	</div>

	<div class="pw_find degital_find">
		<fieldset>
			<legend>비밀번호찾기</legend>
			<div>
				<label fore="아이디값1">아이디</label>
				<input type="text" id="cuId" name="cuId" placeholder="아이디">
				<input type="submit" value="확인" onclick="dofindPwd();">
			</div>
		</fieldset>
	</div>
</form>
	<!-- 여기까지 -->