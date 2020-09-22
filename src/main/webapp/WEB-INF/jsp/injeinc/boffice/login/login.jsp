<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 로그인
- 파일명 : login.jsp
- 최종수정일 : 2019-04-18
- 상태 : 작업완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<c:import url="/layout/cms/head.jsp" ></c:import>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
function doLoginList() {	
	if($("#userId").val() == "") {
		alert("아이디를 입력해 주십시오.");
		$("#userId").focus();
		return false;
	}
	
	if($("#passWd").val() == "") {
		alert("패스워드 를 입력해 주십시오.");
		$("#passWd").focus();
		return false;
	}
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
<div id="wrap">
	<div id="login" class="loginWrap">
		<h1><img src="/images/cms/logo_b.png" alt="콘텐츠 통합관리 솔루션 EASYFRAME"></h1>
		<!--
		<h1><b>CMS</b> LOGIN</h1>
		<span>콘텐츠관리시스템 로그인</span> 
		-->
		<form id="loginForm" name="loginForm" method="post" action="/login/LoginList.do" onsubmit="return doLoginList();">
		<input type="text" id="userId" name="userId" title="아이디" value="" placeholder="아이디">
		<input type="password" id="passWd" name="passWd" title="비밀번호" value="" placeholder="비밀번호">
		<input type="submit" value="로그인">
		</form>
	</div>
	<div id="footer">
		Copyrightⓒ INJEINC 2018. All Rights Reserved.
	</div>
</div>
</body>
</html>