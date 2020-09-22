<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script>
function doform(){
	document.UserFVo.action = "/site/DRP0000/login/Login.do";
	document.UserFVo.submit();
}
	
</script>
<!-- 여기부터 -->
	<h3>아이디 확인</h3>
	<div class="member_info_bg id_info_bg">
		<div>
			<span>본인인증을 통한 아이디 확인 결과입니다.</span>
		</div>
	</div>

	<div class="id_find_result">
		<form id="UserFVo" name="UserFVo" method="post" >
		<input type="hidden" name="loginExclude" value="Y" />
			<c:set value="${userlistCnt}" var="userlistCnt"/>
			<c:if test="${userlistCnt != 0 }">
				<span>${userlist.cuName}님의 아이디는 <em>${userlist.cuId}</em> 입니다.</span>
				<div class="btnArea">
					<a href="/site/DRP0000/user/User_Pwd_Search_Step_01.do" class="btn_l on">비밀번호 찾기</a>
					<!-- <a href="" class="btn_l">로그인</a> -->
					<input type="submit" class="btn_l" value="로그인" onclick="doform()"/>
				</div>
			</c:if>
		</form>
		
		<c:if test="${userlistCnt == 0 }">
			<span>입력하신 정보와 일치하는 아이디가 없습니다.</span>
			<div class="btnArea">
				<a href="/site/DRP0000/user/User_Step_01.do" class="btn_l on">회원가입</a>
				<a href="/site/DRP0000/main.do" class="btn_l">메인으로</a>
			</div>
		</c:if>
	</div>

<!-- 여기까지 -->