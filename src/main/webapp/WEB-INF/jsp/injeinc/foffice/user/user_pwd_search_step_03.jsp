<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script>
function doSearchPwd(){
	document.pwdform.action = "/site/DRP0000/user/User_Npwd_do.do";
	document.pwdform.submit();
}
</script>

<!-- 여기부터 -->
<form id="pwdform" name="pwdform" method="post" >
<input type="hidden" id="searchPwd" name="searchPwd" value="Y" />
	<h3>임시 비밀번호 발급</h3>
	<div class="member_info_bg id_info_bg">
		<div>
			<span>
				로그인을 위한 임시비밀번호를 발급합니다.<br>
				로그인 후 비밀번호를 변경하여 주시기 바랍니다.
			</span>
		</div>
	</div>

	<div class="pw_find_result">
		<span>임시 비밀번호는 아래와 같습니다.</span>
		<em>${userFVo.cuPwd }</em>
		<div class="btnArea">
			<input type="submit" class="btn_l" value="로그인" onclick="doSearchPwd()" />
		</div>
	</div>
</form>
<!-- 여기까지 -->