<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script>
function dofindPwd_step2(){
	document.UserFVo.action = "/site/DRP0000/user/User_Pwd_Search_Step_03.do";
	document.UserFVo.submit();
}

</script>
<!-- 여기부터 -->
<form method="post" name="UserFVo" id="UserFVo">
	<input type="hidden" id="cuId" name="cuId" value="${cuId}"/>
	<h3>비밀번호찾기</h3>
	<div class="member_info_bg pw_info_bg">
		<div>
			<span>아이핀 인증 또는 휴대전화 인증 중 선택하여 본인인증을 완료하여 주시기 바랍니다.</span>
		</div>
	</div>

	<div class="divGroup certification">
		<div class="cer_sms w100p">
			<strong>휴대전화 인증</strong>
			<span>
				본인 명의의 휴대전화로 
				간단하고 빠른 로그인을 하실 수 있습니다.
			</span>
			<div class="btnArea"> 
				<input type="submit" class="btn_l" onclick="dofindPwd_step2();" value="휴대폰 인증">
			</div>

		</div>
	</div>
</from>
	<!-- 여기까지 -->