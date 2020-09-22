<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<script>

$(function(){
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	<c:if test="${not empty resultMsg}">
	resultMessage("<c:out value="${resultMsg}" />");
	</c:if>
	
	//버튼이벤트
    $(".btn_add").click(function(e){
    	e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/sy/workflow/workflowProcForm.do'/>";
		var modal_param = {"pageIndex":$("#pageIndex").val(), "type":"I"};
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
    });
    $(".btn_mod").click(function(e){
    	e.preventDefault();
    	var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/sy/workflow/workflowProcForm.do'/>";
		var modal_param = {"pageIndex":$("#pageIndex").val(), "type":"U", "workflowId":trIdx};
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
    });
});

</script>

</head>
<body>
	<div class="section">
		<form name="form1" class="searchListPage"  method="post" onsubmit="return false;">
			<input type="hidden" name="pageIndex" value="<c:out value='${WorkflowVo.pageIndex}'/>">
		</form>
		
		<div class="tableTitle">
			<!-- <div class="btnArea left">
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div> -->
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		
		<div class="tableBox">
			<table class="list useBtn">
				<caption>워크플로우 목록</caption>
				<colgroup>
					<col class="w70">
					<col class="w30p">
					<col>
					<col class="w70">
					<col class="w70">
				</colgroup>
				<thead>
					<tr>
					    <th scope="col">순번</th>
					    <th scope="col">유형ID</th>
					    <th scope="col">유형명</th>
					    <th scope="col">사용여부</th>
					    <th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value='${result.workflowId}' />">
						<td><c:out value="${(paginationInfo.totalRecordCount+1)-(status.count+(paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)}" /></td>
						<td class="left"><c:out value="${result.workflowId}"/></td>
						<td class="left"><c:out value="${result.workflowNm}"/></td>
						<td><c:out value="${result.useYn}"/></td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="5" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>	
		</div>
				
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>
	</div>
</body>
</html>