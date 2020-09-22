<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<head>
<script type="text/javascript">
//<![CDATA[
	
	function doPollUserFForm(plIdx) {
		$("#plIdx").val(plIdx);
		$("#PollListFVo").attr("action", "<c:url value='/site/${strDomain}/ex/poll/PollUserFForm.do' />").submit();
	}
	
	function doPollListF() {
		$("#pageIndex").val("1");
		$("#PollListFVo").attr("action", "<c:url value='/site/${strDomain}/ex/poll/PollListI.do' />").submit();
	}
	
	function doPollListPag(pageIndex) {
		$("#pageIndex").val(pageIndex);
		$("#PollListVo").attr("action", "<c:url value='/site/${strDomain}/ex/poll/PollListI.do' />").submit();
	}
		
	
//]]>
</script>
</head>
<body>
<fmt:setLocale value="ko_kr"/>
<form:form commandName="PollListFVo" name="PollListFVo" method="post">
<form:hidden path="pageIndex" />
<form:hidden path="plIdx" />

<div class="bbsSearch">	
	<span class="total">
		전체 : <b class="txtBlue"><c:out value="${paginationInfo.totalRecordCount}" /></b>건, 
		<b><c:out value="${paginationInfo.currentPageNo}"/></b> / <c:out value="${paginationInfo.totalPageCount}"/> 페이지
	</span>		
	관리부서 :
	<select id="searchCdIdx" name="searchCdIdx" class="board-top-select" onchange="doPollListF()">
		<option value="">전체</option>
		<c:forEach var="departInfo" items="${departList }">
		<c:if test="${departInfo.cdDepstep2 eq '00' }">
			<option value="<c:out value='${departInfo.cdIdx}'/>" <c:if test="${PollListFVo.searchCdIdx eq departInfo.cdIdx}">selected="selected"</c:if>><c:out value="${departInfo.cdSubject}"/></option>
		</c:if>
		<c:if test="${departInfo.cdDepstep2 ne '00' }">
		<option value="<c:out value='${departInfo.cdIdx}'/>" <c:if test="${PollListFVo.searchCdIdx eq departInfo.cdIdx}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${departInfo.cdSubject}"/></option>
		</c:if>
		</c:forEach>
	</select>
	&nbsp;&nbsp;
	제목 : 
	<form:input path="searchKeyword" id="searchKeyword" title="검색어를 입력해주세요." class="board-top-text" />
	<input type="submit" value="검색" class="btn btn-board-top-search" title="검색하기"/>
</div>
<article class="tableBox">

	<table class="list">
		<caption>설문조사 목록</caption>
		<colgroup>
			<col width="10%" />
			<col width="*" />
			<col width="20%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="cell1">번호</th>
				<th scope="col" class="cell2">제목</th>
				<th scope="col" class="cell3">담당부서</th>
				<th scope="col" class="cell4">시작일</th>
				<th scope="col" class="cell5">마감일</th>
				<th scope="col" class="cell6">참여자</th>
				<th scope="col" class="cell7">상태</th>
			</tr>
		</thead>
		<tbody>		
			<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }" />	
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td class="num"><c:out value="${n-status.index}" /></td>
				<td class="subject"><a href="/site/<c:out value="${strDomain}" />/ex/poll/PollListFViewIng.do?plIdx=${result.plIdx}"><c:out value="${result.plTitle}" /></a></td>
				<td><c:out value="${result.cdSubject}" /></td>
				<td><c:out value="${fn:substring(result.plStartDate, 0, 10)}" /></td>
				<td><c:out value="${fn:substring(result.plEndDate, 0, 10)}" /></td>
				<td><c:out value="${result.totalCnt}" /></td>
				<td>
					<c:if test="${result.totalCnt <= result.plNumber}">
						<fmt:parseDate var="plStartDate" value="${result.plStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate var="plStartDate" value="${plStartDate}" pattern="yyyyMMddHHmmss"/>
						<fmt:parseDate var="plEndDate" value="${result.plEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate var="plEndDate" value="${plEndDate}" pattern="yyyyMMddHHmmss"/>
						<c:if test="${nowDate < plStartDate}">
							<span style="color:green;">설문예정</span>
						</c:if>
						<c:if test="${plStartDate <= nowDate && nowDate <= plEndDate}">
							<a href="#none" onclick="doPollUserFForm('<c:out value="${result.plIdx}" />'); return false;" class="btn-inner">설문하기</a>						
						</c:if>		
					</c:if>				
				</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="7"><spring:message code="common.nodata.msg" /></td>
			</tr>
			</c:if>
		</tbody>		
	</table>
</article>

<div class="board-foot-container">				
	<div class="btn-area">
				
	</div>
	<!-- Pagination -->
	<div class="paging">			
		<ui:pagination paginationInfo="${paginationInfo}" type="imagenew" jsFunction="doPollListPag" />
	</div>
	<!-- // Pagination -->


</div>

</form:form>
</body>

