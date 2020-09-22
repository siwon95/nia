<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<head>
<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
<script type="text/javascript">

var confirmid = false;
var idchk = /^[a-zA-Z0-9]{4,10}$/;
var pwchk1 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-]).{8,15}$/;
var pwchk2 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$/;
var pwchk3 = /^(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
var emailchk = /^(?=.*[a-zA-Z])(?=.*[0-9])$/;

function pwdValidate() {
	if (!pwchk1.test($("input[name=newpwd]").val()) && !pwchk2.test($("input[name=newpwd]").val()) && !pwchk3.test($("input[name=newpwd]").val())) {
		$("#pwdCheck2").css("display", "block");
		$("#pwdCheck1").css("display", "none");
		return;
	} else {
		$("#pwdCheck1").css("display", "block");
		$("#pwdCheck2").css("display", "none");
	}
}

//비밀번호 확인
function chkpwd() {
	if ($("input[name=newpwdCnf]").val() != $("input[name=newpwd]").val()) {
		$("#pwdchk").css("display", "block");
		return;
	} else {
		$("#pwdchk").css("display", "none");
	}
}

function send(){
	if (!pwchk1.test($("input[name=newpwd]").val()) && !pwchk2.test($("input[name=newpwd]").val()) && !pwchk3.test($("input[name=newpwd]").val())) {
		alert("비밀번호는 영문, 숫자, 특수문자 중 2가지 이상 혼합하여 최소8자~ 15자로 설정 바랍니다.");
		$('input[name=newpwd]').focus();
		return false;
	} else if ($("input[name=newpwd]").val() != $("input[name=newpwdCnf]").val()) {
		alert("비밀번호, 비밀번호확인이 서로 맞지않습니다. 다시 입력해주세요");
		$('input[name=newpwdCnf]').focus();
		return false;
	}
	
	document.UserChForm.submit();
}
</script>
</head>
<div class="login-tab-container">
	<ul>
		<li class="item1"><a href="#">로그인</a></li>
		<li class="item2"><a href="#" class="on">아이디찾기</a></li>
		<li class="item3"><a href="#">비밀번호찾기</a></li>
	</ul>
</div>
<form name="UserChForm" id="UserChForm" method="post" action="/site/yangcheon/user/User_Npwd_do.do" onsubmit="send();return false;">
<div class="find-password-container">
<fieldset>
<legend>새로운 비밀번호 변경</legend>
	<div class="newpassword-container">

		<p class="newpassword-text"><span>회원님이 앞으로 사용하실 통합아이디는 <strong><c:out value="${UserFVo.cuId}"/></strong>입니다.</span><span>비밀번호를 재설정하시기 바랍니다.</span></p>

		<ul class="newpassword-input-container">
			 <li class="newpassword-id">
				<label for="">회원ID</label><input type="text" name="cuId" id="cuId" class="" value="<c:out value="${UserFVo.cuId}"/>" readonly="readonly"/>
			</li>
			<li class="newpassword-pass">
				<label for="">새로운 비밀번호</label><input type="password"  name="newpwd" id="newpwd" class="" onblur="pwdValidate();"/>
				<font id="pwdCheck1" style="display: none; color: blue; font-size: 15px;">사용 가능합니다.</font>
				<font id="pwdCheck2" style="display: none; color: red; font-size: 15px;">8~15자 영어 대소문자, 숫자, 특수문자 조합하여 입력하여 주시기 바랍니다.</font>
				<span class="sub-text">8~15자 영어 대소문자, 숫자, 특수문자 조합</span>
			</li>
			<li class="newpassword-pass-chk">
				<label for="">새로운 비밀번호 확인</label><input type="password" name="newpwdCnf" id="newpwdCnf" class="" onkeyup="chkpwd();" />
				<span class="sub-txt" id="pwdchk" style="display: none;">비밀번호가 일치하지 않습니다.</span>
			</li>
		</ul>					

		<p class="newpassword-information-text">안전한 비밀번호 만들기!</p>
		<ol class="newpassword-information">
			<li>1. 영문과 숫자를 혼용하여 8자 이상 15자 이하</li>
			<li>2. 특수문자(&#33;, &#64;, &#37;, &#94;, &amp;, &#42;)를 포함가능, 아이디 포함 불가</li>
			<li>3. 생년월일 등 개인정보와 관련된 숫자와 유추 가능한 숫자 사용금지</li>
		</ol>

		<input type="submit" value="확인" class="btn newpassword-confirm-btn" />
	</div>
</fieldset>
</div>
</form>