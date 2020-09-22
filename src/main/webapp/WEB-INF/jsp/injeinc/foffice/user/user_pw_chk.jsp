<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<head>
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
<script type="text/javascript">

var confirmid = false;
var idchk = /^[a-zA-Z0-9]{4,10}$/;
var pwchk1 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-]).{8,15}$/;
var pwchk2 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$/;
var pwchk3 = /^(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
var emailchk = /^(?=.*[a-zA-Z])(?=.*[0-9])$/;

function send(){
	if (!pwchk1.test($("input[name=cuPwd]").val()) && !pwchk2.test($("input[name=cuPwd]").val()) && !pwchk3.test($("input[name=cuPwd]").val())) {
		alert("비밀번호는 영문, 숫자, 특수문자 중 2가지 이상 혼합하여 최소8자~ 15자로 입니다.");
		$('input[name=cuPwd]').focus();
		return false;
	}
	
	document.UserChForm.submit();
}
</script>
</head>
<form name="UserChForm" id="UserChForm" method="post" action="/site/demo/user/UserPwCheck_Ax.do" onsubmit="send();return false;">
<input type="hidden" name="userMenu" id="userMenu" value="myform"/>
<div class="find-password-container">
<fieldset>
<legend>비밀번호확인</legend>
	<div class="password-container">

		<ul class="password-input-container">
			 <li class="password-id">
				<label for="">회원ID</label><input type="text" name="cuId" id="cuId" class="" value="<c:out value="${ss_id}"/>" readonly="readonly"/>
			</li>
			<li class="password-pass">
				<label for="">비밀번호</label><input type="password"  name="cuPwd" id="cuPwd" class="" onblur="pwdValidate();"/>
				<span class="sub-text">8~15자 영어 대소문자, 숫자, 특수문자 조합</span>
			</li>			
		</ul>

		<input type="submit" value="확인" class="btn password-confirm-btn" />
	</div>
</fieldset>
</div>
</form>