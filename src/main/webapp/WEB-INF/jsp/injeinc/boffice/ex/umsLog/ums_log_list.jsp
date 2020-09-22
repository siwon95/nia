<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="bbs" uri="http://cms.injeinc.co.kr/bbs"%>
<%-- ------------------------------------------------------------
- 제목 : UMS전송기록
- 파일명 : ums_log_list.jsp
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
//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/ex/umsLog/UmsLogForm.do' />";
		var modal_param = $("#UmsLogVo").serializeArray();
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_view").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var modal_id = "modal_view";
		var modal_url = "<c:url value='/boffice_noDeco/ex/umsLog/UmsLogView.do' />";
		$("#ulIdx").val(formIdx);
		var modal_param = $("#UmsLogVo").serializeArray();
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<div class="section">
		<form:form commandName="UmsLogVo" name="UmsLogVo" method="post"  class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<input type="hidden" id="ulIdx" name="ulIdx" value="0">
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<tbody>
					<tr>
						<th scope="row"><label for="searchSiteCd">카테고리</label></th>
						<td>
							<form:select path="searchSiteCd">
								<form:option value="" label="전체" />
								<c:forEach var="SiteCdInfo" items="${SiteCdList}">
								<form:option value="${SiteCdInfo.code}" label="${SiteCdInfo.codeName}" />
								</c:forEach>
							</form:select>
						</td>
						<th scope="row"><label for="pageUnit">페이지수</label></th>
						<td>
							<form:select path="pageUnit">
								<form:option value="10" label="10" />
								<form:option value="20" label="20" />
								<form:option value="30" label="30" />
								<form:option value="50" label="50" />
								<form:option value="100" label="100" />
							</form:select>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="searchStartDt">기간</label></th>
						<td>
							<form:input path="searchStartDt" size="10" class="useDatepicker" /> ~ <form:input path="searchEndDt" size="10" class="useDatepicker" />
						</td>
						<th scope="row"><label for="searchCondition">검색</label></th>
						<td>
							<form:select path="searchCondition" class="w20p">
								<form:option value="1" label="제목" />
								<form:option value="2" label="수신자명^수신자번호" />
								<form:option value="3" label="회신번호" />
							</form:select>
							<label for="searchKeyword" class="hidden">검색어</label>
							<form:input path="searchKeyword" size="25"/>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="검색" class="btn_inline btn_search">
		</div>
		
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
			<table class="list">
				<caption>UMS LOG 리스트</caption>
				<colgroup>
					<col class="w50">
					<col class="w50">
					<col class="w150">
					<col class="w100">
					<col>
					<col class="w150">
					<col class="w130">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">순번</th>
						<th scope="col">전송일</th>
						<th scope="col">카테고리</th>
						<th scope="col">제목</th>
						<th scope="col">수신자명 수신자번호</th>
						<th scope="col">회신번호</th>
						<th scope="col">상세보기</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value="${result.ulIdx}"/>">
						<td><input type="checkbox" name="searchUlIdx" value="<c:out value="${result.ulIdx}"/>"></td>
						<td><c:out value="${n - status.index}" /></td>
						<td><c:out value="${result.regDt}" /></td>
						<td class="left">
							<c:if test="${empty result.ulSiteCd}">-</c:if>
							<c:forEach var="SiteCdInfo" items="${SiteCdList}">
							<c:if test="${result.ulSiteCd eq SiteCdInfo.code}"></c:if>
							</c:forEach>
						</td>
						<td class="left"><c:out value="${result.ulSubject}" /></td>
						<td><c:out value="${result.ulAddress}" /></td>
						<td><c:out value="${result.ulCallbackNo}" /></td>
						<td><a href="#" class="btn_inline btn_view">상세보기</a></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="8" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>	
		</form:form>
	</div>
</body>
</html>
