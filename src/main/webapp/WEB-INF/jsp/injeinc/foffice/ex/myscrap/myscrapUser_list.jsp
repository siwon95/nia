<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>마이페이지 목록</title>
<script type="text/javascript">
//<![CDATA[
/* function doSuggestUserForm(idea_key) {
	$("#idea_key").val(idea_key);
	$("#ref").val(idea_key);
	$("#SuggestUserVo").attr("action", "<c:url value='/site/yangcheon/ex/ideaHouse/suggestUserView.do' />").submit();
}

function doSuggestPag(pageIndex) {
	$("#pageIndex").val(pageIndex);
	$("#SuggestUserVo").attr("action", "<c:url value='/site/yangcheon/foffice/ex/ideaHouse/suggestUserList.do' />").submit();
}

function fn_suggestInsert() {
	//alert('aaaaaaaaa');
	window.location.href = "/site/yangcheon/foffice/ex/ideaHouse/suggestUserInsertForm.do";
} */

//]]>
</script>
</head>
<body>
<form:form commandName="MyscrapVO" name="MyscrapVO" method="post">
	<form:hidden path="pageIndex" />
	<form:hidden path="scrap_seq_n" />

	
	<%-- <div class="board-top-search">
	<ul class="board-top-information">
		<li><span>전체 : <c:out value="${paginationInfo.totalRecordCount}" /></span></li>
		<li>, <span><c:out value="${paginationInfo.currentPageNo}"/> / <c:out value="${paginationInfo.totalPageCount}"/> 페이지</span></li>
	</ul>		 	
	
	<div class="board-top-form">
		<form:select path="searchCondition" class="board-top-select">			
			<form:option value="subject" label="제목" />
			<form:option value="writer" label="작성자" />
		</form:select>		
		<form:input path="searchKeyword" id="searchKeyword" title="검색어를 입력해주세요." class="board-top-text" />
		<input type="submit" value="" class="btn btn-board-top-search" title="검색하기"/>
	</div>
</div> --%>
	
	<!-- 컨텐츠 영역 -->
	<article class="board-row">
		<table class="basic-list">
			<caption>마이페이지 목록</caption>
			<colgroup>
				<col width="10%" />
				<col width="75%" />		
				<col width="15%" />		
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">등록일</th>							
				</tr>
			</thead>
			<tbody>
				<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }" />
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td class="num txt-center"><c:out value="${n-status.index}" /></td>
						<td class="subject">
							<a href="${result.scrap_loc_v}" target="_blank" title="새창"/><c:out value="${result.scrap_title_v}" /></a>
						</td>						
						<td><c:out value="${result.scrap_dt_d}" /></td>
					</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="4"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</article>

	<div class="board-foot-container">
		<!--paginate -->
		<div class="pagination">
			<ui:pagination paginationInfo="${paginationInfo}" type="imagenew" jsFunction="doSuggestPag" />
		</div>
		<!--//paginate -->
	</div>

</form:form>
</body>
</html>