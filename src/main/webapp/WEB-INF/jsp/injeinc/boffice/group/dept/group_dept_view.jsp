<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 부서및직원관리
- 파일명 : group_dept_view.jsp
- 작성일 : 2018-01-19
- 작성자 : 김근 차장
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<c:import url="/layout/cms/head.jsp" ></c:import>
<script type="text/javascript">
function doGroupDeptDetailMod() {
	if ($("#cdTel").val() == '') {
		alert("전화번호를  입력해주세요!");
		$("#cdTel").focus();
		return false;
	}
	/*
	if ($("#cdHomepage").val() != '') {
		if($("#cdHomepage").val().indexOf("http://") != 0){
			alert("홈페이지는 http:// 를 시작으로 입력하십시오");
			$("#cdHomepage").focus();
			return false;
		}
	}
	 if(($("#cdLinkName").val() == '' && $("#cdLink").val() != '') || ($("#cdLinkName").val() != '' && $("#cdLink").val() == '')){
		alert("링크 명과 링크 URL은 함께 적거나 혹은 지워야 합니다");
		return false;
	}
	if(($("#cdLinkName2").val() == '' && $("#cdLink2").val() != '') || ($("#cdLinkName2").val() != '' && $("#cdLink2").val() == '')){
		alert("링크2 명과 링크2 URL은 함께 적거나 혹은 지워야 합니다");
		return false;
	}
	if(($("#cdLinkName3").val() == '' && $("#cdLink3").val() != '') || ($("#cdLinkName3").val() != '' && $("#cdLink3").val() == '')){
		alert("링크3 명과 링크3 URL은 함께 적거나 혹은 지워야 합니다");
		return false;
	}
	if(($("#cdLinkName4").val() == '' && $("#cdLink4").val() != '') || ($("#cdLinkName4").val() != '' && $("#cdLink4").val() == '')){
		alert("링크4 명과 링크4 URL은 함께 적거나 혹은 지워야 합니다");
		return false;
	}
	if(($("#cdLinkName5").val() == '' && $("#cdLink5").val() != '') || ($("#cdLinkName5").val() != '' && $("#cdLink5").val() == '')){
		alert("링크5 명과 링크5 URL은 함께 적거나 혹은 지워야 합니다");
		return false;
	} */
	if ($("#cdText").val() == '') {
		alert("주요업무를  입력해주세요!");
		$("#cdText").focus();
		return false;
	}
	
	if(confirm("저장하시겠습니까?")){
		$("#groupDeptVo").attr("action","/boffice/group/dept/GroupDeptDetail_Mod.do");
		$("#groupDeptVo").submit();
	}
		
}
</script>
</head>
<body>
	<table class="form">
	<colgroup>
		<col style="width:20%;">
		<col style="width:30%;">
		<col style="width:20%;">
		<col style="width:30%;">
	</colgroup>
	<tbody>
	<form:form commandName="groupDeptVo" name="groupDeptVo" method="post">
		<form:hidden path="layout"/>
		<form:hidden path="cdIdx" value="${groupDeptVo.cdIdx }" />
		<form:hidden path="cdCode" value="${groupDeptVo.cdUpdate }" />
		<tr>
			<td colspan="4"><c:out value="${groupDeptVo.cdSubject }" />
				[<c:out value="${groupDeptVo.cdCode }" />]<br><c:out value="${groupDeptVo.cdUpdate }" /> 최종수정</td>
		</tr>
		<tr>
			<th scope="row">전화</th>
			<td><form:input style="width:100%;" path="cdTel" /></td>
			<th scope="row">팩스</th>
			<td><form:input style="width:100%;" path="cdFax" /></td>
		</tr>
		<tr>
			<th scope="row">위치</th>
			<td colspan="3"><form:input style="width:100%;" path="cdArea" /></td>
		</tr>
		<%-- <tr>
			<th scope="row"><label for="cdHomepage">홈페이지</label></th>
			<td colspan="3"><form:input path="cdHomepage" style="width:100%;" /></td>
		</tr> --%>
		<tr>
			<th><label for="cdEngSubject">영문이름</label></th>
			<td colspan="3"><form:input style="width:100%;" path="cdEngSubject" /></td>
		</tr>
		<%-- <tr>
			<th scope="row"><label for="cdFraSubject">불어이름</label></th>
			<td colspan="3"><form:input style="width:100%;" path="cdFraSubject" /></td>
		</tr> --%>
		<%-- <tr>
			<th scope="row"><label for="cdJapSubject">일어이름</label></th>
			<td colspan="3"><form:input style="width:100%;" path="cdJapSubject" /></td>
		</tr> --%>
		<tr>
			<th scope="row"><label for="cdChiSubject">중국어이름</label></th>
			<td colspan="3"><form:input style="width:100%;" path="cdChiSubject" /></td>
		</tr>
		<%-- <tr>
			<th scope="row"><label for="cdLinkName">링크 명</label></th>
			<td><form:input style="width:100%;" path="cdLinkName" /></td>
			<th scope="row"><label for="cdLink">링크 URL</label></th>
			<td><form:input style="width:100%;" path="cdLink" /></td>
		</tr>
		<tr>
			<th scope="row"><label for="cdLinkName2">링크2 명</label></th>
			<td><form:input style="width:100%;" path="cdLinkName2" /></td>
			<th scope="row"><label for="cdLink2">링크2 URL</label></th>
			<td><form:input style="width:100%;" path="cdLink2" /></td>
		</tr>
		<tr>
			<th scope="row"><label for="cdLinkName3">링크3 명</label></th>
			<td><form:input style="width:100%;" path="cdLinkName3" /></td>
			<th scope="row"><label for="cdLink3">링크3 URL</label></th>
			<td><form:input style="width:100%;" path="cdLink3" /></td>
		</tr>
		<tr>
			<th scope="row"><label for="cdLinkName4">링크4 명</label></th>
			<td><form:input style="width:100%;" path="cdLinkName4" /></td>
			<th scope="row"><label for="cdLink4">링크4 URL</label></th>
			<td><form:input style="width:100%;" path="cdLink4" /></td>
		</tr>
		<tr>
			<th scope="row"><label for="cdLinkName5">링크5 명</label></th>
			<td><form:input style="width:100%;" path="cdLinkName5" /></td>
			<th scope="row"><label for="cdLink5">링크5 URL</label></th>
			<td><form:input style="width:100%;" path="cdLink5" /></td>
		</tr> --%>
		<tr>
			<th scope="col" colspan="4"><label for="cdText">주요업무1</label></th>
		</tr>
		<tr>
			<td colspan="4"><form:textarea path="cdText" style="width:100%;height:250px" /></td>
		</tr>
	</form:form>
	</tbody>
	</table>
	<div class="btnArea">
		<c:if test="${param.layout ne 'n' }">
			<input type="button" value="확인" class="btn_m btn_focus" onclick="doGroupDeptDetailMod();">
		</c:if>
	</div>
	<%//@ include file="../../common/navigation.jsp" %>
</body>
</html>
