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
- 파일명 : poll_user_list.jsp
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
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	//버튼 이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		$("#puIdx").val('');
		$("#PollUserVo").attr("action", "<c:url value='/boffice/ex/poll/PollUserForm.do' />").submit();
	});
	$(".btn_detail").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		$("#puIdx").val(formIdx);
		$("#PollUserVo").attr("action", "<c:url value='/boffice/ex/poll/PollUserView.do' />").submit();
	});
	$(".btn_list").click(function(e){
		e.preventDefault();
		$("#PollUserVo").attr("action", "<c:url value='/boffice/ex/poll/PollList.do' />").submit();
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<div class="section">	
		<div class="tableBox">
			<table class="form">
				<caption>설문조사 상세정보</caption>
				<colgroup>
					<col class="w120">
					<col>
					<col class="w120">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">제목</th>
						<td><c:out value="${PollListVo.plTitle}" /></td>
						<th scope="row">사이트</th>
						<td><c:out value="${PollListVo.siteNm}" /></td>
					<tr>
						<th scope="row">기간</th>
						<td><c:out value="${fn:substring(PollListVo.plStartDate, 0, 16)}" /> ~ <c:out value="${fn:substring(PollListVo.plEndDate, 0, 16)}" /></td>
						<th scope="row">담당부서</th>
						<td><c:out value="${PollListVo.cdSubject}" /></td>
					</tr>
					<tr>
						<th scope="row">담당자</th>
						<td><c:out value="${PollListVo.plManagerName}" /></td>
						<th scope="row">담당자 연락처</th>
						<td><c:out value="${PollListVo.plManagerTel}" /></td>
					</tr>
					<%-- <tr>
						<th scope="row">참여자/모집인원</th>
						<td colspan="3"><c:out value="${resultCnt}" />/<c:out value="${PollListVo.plNumber}" /></td>
					</tr> --%>
				</tbody>
			</table>
		</div>
		<!-- <div class="btnArea">
			<a href="javascript:doPollUserListExcel();" class="btn_m">엑셀다운</a>
			<a href="javascript:doPollList();" class="btn_m">설문조사 목록</a>
		</div> -->
		
		<form:form commandName="PollUserVo" name="PollUserVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<form:hidden path="searchCdIdx" />
		<form:hidden path="searchUse" />
		<form:hidden path="plIdx" />
		<form:hidden path="pageSubIndex" />
		<form:hidden path="puIdx" />
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<tbody>
					<tr>
						<th scope="row"><label for="searchSubCondition">검색</label></th>
						<td>
							<form:select path="searchSubCondition">
								<form:option value="1" label="이름" />
								<form:option value="2" label="전화번호" />
								<form:option value="3" label="휴대폰번호" />
							</form:select>
							<label for="searchSubKeyword" class="hidden">검색어</label>
							<form:input path="searchSubKeyword" size="30"/>
						</td>
					</tr>
				</tbody>
			</table>
			<button class="btn_inline btn_search" onclick="doPollUserList(); return false;">검색</button>
		</div>
		</form:form>
		
		<div class="tableTitle">
			<!-- <div class="btnArea left">
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div> -->
			<div class="btnArea right">
				<a href="#" class="btn_inline btn_add">참여하기</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list">
				<caption>설문조사 참여자 목록</caption>
				<colgroup>
					<col class="w50">
					<col>
					<col>
					<col>
					<col>
					<col class="w80">
					<col class="w80">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">순번</th>
						<th scope="col">이름</th>
						<th scope="col">전화번호</th>
						<th scope="col">휴대폰번호</th>
						<th scope="col">ID(IP)</th>
						<th scope="col">참여일</th>
						<th scope="col">설문지보기</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value="${result.puIdx}" />">
						<td><c:out value="${n - status.index}" /></td>
						<td>
							<c:if test="${PollListVo.plAuthType eq 'A' || PollListVo.plAuthType eq 'L'}"><c:out value="${result.puName}" /></c:if>
							<c:if test="${PollListVo.plAuthType eq 'N' || PollListVo.plAuthType eq 'I'}">-</c:if>
						</td>
						<td>
							<c:if test="${PollListVo.plTelYn eq 'Y'}"><c:out value="${result.puTel}" /></c:if>
							<c:if test="${PollListVo.plTelYn eq 'N'}">-</c:if>
						</td>
						<td>
							<c:if test="${PollListVo.plHpYn eq 'Y'}"><c:out value="${result.puHp}" /></c:if>
							<c:if test="${PollListVo.plHpYn eq 'N'}">-</c:if>
						</td>
						<td><c:out value="${result.regId}" /></td>
						<td><c:out value="${fn:substring(result.regDt, 0, 10)}" /></td>
						<td><a href="#" class="btn_inline btn_detail">설문지</a></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="7" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>
		<div class="btnArea right">
			<a href="#" class="btn_inline btn_list">설문목록</a>
		</div>
	</div>
</body>
</html>
