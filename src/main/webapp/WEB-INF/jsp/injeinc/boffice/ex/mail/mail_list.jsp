<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 이메일전송기록
- 파일명 : mail_list.jsp
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
		var modal_url = "<c:url value='/boffice_noDeco/ex/mail/MailForm.do' />";
		var modal_param = $("#MailVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<div class="section">
		<form:form commandName="MailVo" name="MailVo" method="post"  class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<tbody>
					<tr>
						<th scope="row"><label for="searchKeyword">검색</label></th>
						<td>
							<form:select path="searchCondition">
							<form:option value="1" label="제목" />
							<form:option value="2" label="수신자" />
							<form:option value="3" label="수신이메일" />
							</form:select>
							<label for="searchKeyword" class="hidden">검색어</label>
							<form:input path="searchKeyword" size="30"/>
						</td>
					</tr>
				</tbody>	
			</table>
			<input type="submit" value="검색" class="btn_inline btn_search">
		</div>
		
		<div class="tableTitle">
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div> 
		
		<div class="tableBox">
			<table class="list">
				<caption>이메일 전송기록</caption>
				<colgroup>
					<col class="w50">
					<col>
					<col class="w100">
					<col class="w170">
					<col class="w170">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">순번</th>
						<th scope="col">제목</th>
						<th scope="col">수신자</th>
						<th scope="col">수신이메일</th>
						<th scope="col">전송일</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value='${result.ceIdx}' />">
						<td><c:out value="${n - status.index}" /></td>
						<td class="left"><c:out value="${result.subject}" /></td>
						<td><c:out value="${result.to}" /></td>
						<td><c:out value="${result.receipt}" /></td>
						<td><c:out value="${result.regDt}" /></td>
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
		</form:form>
	</div>
</body>
</html>
