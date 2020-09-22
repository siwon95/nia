<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<h3>로그인</h3>
<div class="outline_info_bg login_icon_bg">
	<div>
		<strong>디지털역기능 대응 서비스 통합안내 시스템에 오신것을 환영합니다.</strong>
		<span>
			홈페이지 서비스를 이용하시기 위해서는 로그인해주시기 바랍니다.
		</span>
	</div>
</div>

<div class='member_login'>
	<div>
		<form name="lginForm" method="post" action="/site/DRP0000/login/Login_List.do">
		<input type="hidden" name="referUrl" value="<%= request.getHeader("referer") %>" class="w400"/>
		<input type="hidden" name="loginReferer" value="${loginReferer}" class="w400"/>
		<input type="hidden" name="searchPwd" value='${searchPwd}' class="w400"/>
	   	<fieldset>
			<legend>로그인입력폼</legend>
			<input type='text' name="cuId" placeholder='아이디'>
			<input type='password' name="cuPwd" placeholder='비밀번호'>
			<input type='submit' value='로그인'>
		</fieldset>
		</form>
		<a href='/site/DRP0000/user/User_Id_Search_Step_01.do' class='first'>아이디 찾기</a>
		<a href='/site/DRP0000/user/User_Pwd_Search_Step_01.do'>비밀번호 찾기</a>
		<a href='/site/DRP0000/user/User_Step_01.do'>회원가입</a>
	</div>
</div>