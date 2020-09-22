<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 관리자정보변경
- 파일명 : mgr_my_form.jsp
- 작성일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	if($("#mgrPw").val() != $("#mgrPw2").val()) {
		alert("비밀번호를 확인하여 주십시오.");
		$("#mgrPw2").focus();
		return;
	}
	
	f.action = "/boffice/sy/mgr/MgrListModMyInfo.do";
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<form:form commandName="MgrListVo" name="MgrListVo" method="post" onsubmit="return doForm(this);">
	<div class="section">
		<div class="tableBox">
			<table class="form">
				<caption>관리자정보변경</caption>
				<colgroup>
					<col class="w100">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">과</th>
						<td><c:out value="${MgrListVo.deptNm}" /></td>
					</tr>
					<tr>
						<th scope="row">이름</th>
						<td><c:out value="${MgrListVo.mgrName}" /></td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td><c:out value="${MgrListVo.mgrId}" /></td>
					</tr>
					<tr>
						<th scope="row"><label for="mgrPw">비밀번호</label></th>
						<td><form:password path="mgrPw" class="required w150" /></td>
					</tr>
					<tr>
						<th scope="row"><label for="mgrPw2">비밀번호확인</label></th>
						<td>
							<input type="password" id="mgrPw2" name="mgrPw2" class="required w150" />
							<%-- <c:out value="${MgrListVo.mgrPw}"/> --%>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="mgrEmail">이메일</label></th>
						<td><form:input path="mgrEmail" class="w300" /></td>
					</tr>
					<tr>
						<th scope="row">권한</th>
						<td><c:out value="${MgrListVo.permNm}" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<input type="submit" value="저장" class="btn_m on">
		</div>
	</div>
	</form:form>
</body>
</html>
