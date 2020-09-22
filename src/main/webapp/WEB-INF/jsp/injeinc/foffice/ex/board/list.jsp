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
<head>
<script type="text/javascript">
//<![CDATA[
	function doBbsContentFForm() {
		$("#BbsContentFVo").attr("action", "<c:url value='/site/<c:out value="${strDomain}"/>/ex/board/Form.do' />?cbIdx=<c:out value="${BoardVo.cbIdx}"/>").submit();
	}
	
	function doBbsContentFView(bcIdx) {
		$("#BbsContentFVo").attr("action", "<c:url value='/site/<c:out value="${strDomain}"/>/ex/board/View.do' />?cbIdx=<c:out value="${BoardVo.cbIdx}"/>&bcidx="+bcIdx).submit();
	}
	
	function doBbsContentFPag(pageIndex) {
		$("#pageIndex").val(pageIndex);
		$("#BbsContentFVo").attr("action", "<c:url value='/site/<c:out value="${strDomain}"/>/ex/board/List.do' />?cbIdx=<c:out value="${BoardVo.cbIdx}"/>").submit();
	}
//]]>
</script>
</head>
<body>
<fmt:setLocale value="ko_kr"/>
<form:form commandName="BbsContentFVo" name="BbsContentFVo" method="post">
<form:hidden path="pageIndex" />
	<div class="board-top">
		<span class="count">총 <em><fmt:formatNumber value="${resultCnt}" /></em>건</span>
	</div>
	<div class="board">
		
		<jsp:include page="./inc/${bbsTempletName}/list.jsp" />
		
		<!--paginate -->
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doBbsContentFPag" />
		</div>
		<!--//paginate -->
			
		<c:if test="${isWriteGbn}">
			<div class="btns aright">
				<a href="javascript:doBbsContentFForm();" class="type1">글쓰기</a>
			</div>
		</c:if>
		<div class="board-search">
			<fieldset>
				<legend>게시판검색</legend>
				<c:if test="${BoardVo.categoryUseYn eq 'Y'}">
				<form:select title="카테고리선택" path="searchCateCont">
					<form:option value="" label="선택하세요" />
					<c:forEach var="categoryInfo" items="${categoryList}">
					<form:option value="${categoryInfo.code}" label="${categoryInfo.codeName}" />
					</c:forEach>
				</form:select>
				</c:if>
				<form:select title="조건선택" path="searchCondition">
				<c:forEach var="conditionInfo" items="${conditionlist}">
					<form:option value="${conditionInfo.contentMappingL}" label="${conditionInfo.labelName}" />
				</c:forEach>
				</form:select>
				<form:input path="searchKeyword" class="w30" title="검색어입력" />
				<input type="image" src="/images/seocho/board/btn_search.gif" onclick="doBbsContentFPag('1');return false;" alt="검색">
			</fieldset>
		</div><!-- //search -->
		
	</div><!-- //board -->
</form:form>
</body>
