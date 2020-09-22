<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%-- ------------------------------------------------------------
- 제목 : 설문관리
- 파일명 : poll_list_text_report.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	//버튼 이벤트
	$(".btn_pollResult").click(function(e){
		e.preventDefault();
		$("#PollAnswerViewVo").attr("action", "<c:url value='/boffice/ex/poll/PollListReport.do' />").submit();
	});
	$(".btn_textReport").click(function(e){
		e.preventDefault();
		$("#pqIdx").val($(this).attr("data-pqIdx"));
		$("#pqType").val($(this).attr("data-pqType"));
		$("#paAnswer").val($(this).attr("data-paAnswer"));
		$("#PollListVo").attr("action", "<c:url value='/boffice/ex/poll/PollListTextReport.do' />").submit();
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<form:form commandName="PollAnswerViewVo" name="PollAnswerViewVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCdIdx" />
	<form:hidden path="searchUse" />
	<form:hidden path="plIdx" />
	<form:hidden path="pageSubIndex" />
	<form:hidden path="pqIdx" />
	<form:hidden path="pqType" />
	<form:hidden path="paAnswer" />
	</form:form>
	
	<div class="section">
		<div class="tableBox">
			<table class="list">
			<caption>설문조사 주관식 답변 목록</caption>
			<colgroup>
				<col style="width:60px">
				<col style="width:170px">
				<col>
			</colgroup>
			<thead>
				<tr>
					<th scope="col">순번</th>
					<th scope="col">이름(ID or IP)</th>
					<th scope="col">
					<c:if test="${PollQuestionVo.pqType eq 'radio' || PollQuestionVo.pqType eq 'checkbox'}">
						기타의견
					</c:if>
					<c:if test="${PollQuestionVo.pqType eq 'text' || PollQuestionVo.pqType eq 'textarea'}">
						내용
					</c:if>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
				<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td><c:out value="${n - status.index}" /></td>
					<td>
						<c:if test="${!empty result.puName}"><c:out value="${result.puName}" /></c:if>
						<c:if test="${empty result.puName}">-</c:if>
						(<c:out value="${result.puId}" />)
					</td>
					<td class="PollContent">
					<c:if test="${PollQuestionVo.pqType ne 'textarea'}">
						<c:out value="${result.paText}"/>
					</c:if>
					<c:if test="${PollQuestionVo.pqType eq 'textarea'}">
						<c:out value="${cmm:textAreaToHtml(result.paText)}"/>
					</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="3" class="empty"><spring:message code="common.nodata.msg" /></td>
				</tr>
				</c:if>
			</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>
		<div class="btnArea right">
			<a href="#" class="btn_m btn_pollResult">설문결과목록</a>
		</div>
	</div>
</body>
</html>
