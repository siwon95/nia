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
var pagreeyn = 'N';
var youngAgree = 'N';

//gpin
function gpinAuth() {
	if(pagreeyn == 'N'){
		alert("부모님 인증을 먼저 해주십시오");
		return;
	}else{
		var wWidth = 360;
		var wHight = 120;

		var wX = (window.screen.width - wWidth) / 2;
		var wY = (window.screen.height - wHight) / 2;

		var w = window.open("/G-PIN/pin8520Requ.jsp", "gPinLoginWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);
		w.focus();
	}
}

function mobileAuth() {
	var PAgree = open("/common/mobileAuth/mobile_auth.jsp", "CheckJN", "width=420, height=570, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=480, top=115");
	PAgree.focus();
}

function writeJN() {
//처리
	youngAgree = 'Y';
//	$("#complete1").css("display","block");
//	$("#certify1").css("display","none");
	
	if(youngAgree == 'Y' && pagreeyn == 'Y'){
//		location.href = "<c:url value='/site/yangcheon/foffice/user/User_Step_04.do?type=y'/>";
		location.href = "<c:url value='/site/yangcheon/user/User_Step_06.do' />";
	}
}

//부모님 gpin인증
function gpinAuthParent() {
	
	var wWidth = 360;
	var wHight = 120;
	
	var wX = (window.screen.width - wWidth) / 2;
	var wY = (window.screen.height - wHight) / 2;

	var w = window.open("/G-PIN/pin8520Requ.jsp?parentAuth=Y", "gPinLoginWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);
	w.focus();
	
}

//부모님 모바일인증
function mobileAuthParent() {
	var PAgree = open("/common/mobileAuth/mobile_auth_parent.jsp", "CheckJN", "width=420, height=570, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=480, top=115");
	PAgree.focus();
}

function writeJNParent() {
	pagreeyn = 'Y';
//	$("#complete2").css("display","block");
//	$("#certify2").css("display","none");
	
	if(youngAgree == 'Y' && pagreeyn == 'Y'){
//		location.href = "<c:url value='/site/seocho/foffice/user/User_Step_04.do?type=y' />";
		location.href = "<c:url value='/site/yangcheon/user/User_Step_06.do' />";
	}
}

var certPopWindow;

//실명인증팝업(한국신용평가정보 안심 실명확인 팝업)
var windowObject;
function fn_confirmCert() {
	if(windowObject!="") {
		windowObject=certPopWindow=window.open('', 'certpop', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	}
	document.dform.target = "certpop";
	document.dform.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
	document.dform.submit();
}

//보호자실명인증팝업(한국신용평가정보 안심 실명확인 팝업)
var windowObject2;
function fn_confirmParentCert() {
	/* if(document.eform.ipin_no_v.value=="") {
		alert("먼저 14세 미만 인증을 해 주시기 바랍니다.");
		return false;
	} else {
		if(windowObject2!="") {
			windowObject2=window.open('', 'pcertpop','width=410, height=590');
		}

		document.pform.target = "pcertpop";
		document.pform.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
		document.pform.submit();
	} */
	
	
		if(windowObject2!="") {
			windowObject2=window.open('', 'pcertpop','width=410, height=590');
		}
		document.pform.target = "pcertpop";
		document.pform.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
		document.pform.submit();
	
}
//휴대폰 인증 개발완료시 삭제
function doJoinForm(){
	document.dform.action = "/site/DRP0000/user/User_Step_04.do"; 
	document.dform.submit();
}
//]]>
</script>
<!-- 여기부터 -->
	<form method="post" id="dform" name="dform" >
	<input type="hidden" name="youngYn" value="Y">
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
	
			<fieldset>
				<legend>개인 - 회원정보입력관리</legend>
				<h4>만 14세 미만 가입자 정보</h4>
				<div class="member_insert mTable">
					<span>필수 입력사항입니다.</span>
					<table>
						<caption>만 14세 미만 가입자 정보</caption>
						<colgroup>
							<col style="width:15%;">
							<col style="width:85%;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><label for="아이디값0"><em>필수</em>성명</label></th>
								<td>
									<input type="text" id="아이디값0">
								</td>
							</tr>
							<tr>
								<th scope="row"><em>필수</em>휴대전화 번호</th>
								<td>
									<label for="아이디값매칭4" class="soundOnly">첫번째자리</label><input type="text" id="아이디값매칭4" class="w130"> -
									<label for="아이디값매칭5" class="soundOnly">가운데자리</label><input type="text" id="아이디값매칭5" class="w130"> -
									<label for="아이디값매칭6" class="soundOnly">마지막자리</label><input type="text" id="아이디값매칭6" class="w130">
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<h4>보호자(법정대리인) 본인인증 및 동의</h4>
				<div class="member_insert mTable">
					<span>필수 입력사항입니다.</span>
					<table>
						<caption>보호자(법정대리인) 본인인증 및 동의</caption>
						<colgroup>
							<col style="width:15%;">
							<col style="width:85%;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><em>필수</em>보호자 동의</th>
								<td>
									<label for="아이디값1" class="c_wrap"><input type="checkbox" name="bb" id="아이디값1">회원가입신청을 위한 약관에 동의합니다.</label>
								</td>
							</tr>
							<tr>
								<th scope="row"><em>필수</em>본인인증</th>
								<td>
									<a href="#" class="btn_duble">아이디 중복확인</a>
									<span class="txt">보호자 본인 명의의 휴대폰으로 본인 확인을 합니다.<span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="btnArea">
					<!-- <input type="submit" class="btn_l large" value="확인"> -->
					<!-- <a href="#n" class="btn_l large" onclick="doJoinForm()">확인</a> -->
					<input type="submit" class="btn_l large" value="확인" onclick="doJoinForm()"/>
					<a href="/site/DRP0000/main.do" class="btn_l large off">가입신청취소</a>
				</div>
			</fieldset>
		</form>
	<!-- 여기까지 -->