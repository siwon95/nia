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
	
	function doBbsContentFView(cbIdx, bcIdx) {
		$("#cbIdx").val(cbIdx);
		$("#bcIdx").val(bcIdx);
		$("#BbsContentFVo").attr("action", "<c:url value='/site/seocho/foffice/board/MyMinwonView.do' />").submit();
	}
	
	function doBbsContentFPag(pageIndex) {
		$("#pageIndex").val(pageIndex);
		$("#BbsContentFVo").attr("action", "<c:url value='/site/seocho/foffice/board/MyMinwonList.do' />").submit();
	}
//]]>
</script>
</head>
<body>
<fmt:setLocale value="ko_kr"/>
<form:form commandName="BbsContentFVo" name="BbsContentFVo" method="post">
<form:hidden path="pageIndex" />
<input type="hidden" id="cbIdx" name="cbIdx" value="0" />
<input type="hidden" id="bcIdx" name="bcIdx" value="0" />
	<div class="board-top">
		<span class="count">총 <em><fmt:formatNumber value="${resultCnt}" /></em>건</span>
	</div>
	<div class="board">
		
		
		<div class="board-list">
		
			<p id="b_summary" class="hide">내가 신청한 민원글의 번호, 게시판구분, 진행상태, 제목, 작성일, 조회수, 공개여부 안내</p>
			<table class="list" aria-describedby="b_summary">
				<caption>나의민원 게시물 목록</caption>
				<colgroup>
					<col style="width:8%" class="none">
					<col style="width:15%" class="none">
					<col style="width:12%" class="none">
					<col class="title">
					<col style="width:12%" class="date">
					<col style="width:10%" class="none">
					<col style="width:10%" class="status">
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="none">번호</th>
						<th scope="col" class="none">게시판구분</th>
						<th scope="col">진행상태</th>
						<th scope="col">제목</th>
						<th scope="col">작성일</th>
						<th scope="col" class="none">조회수</th>
						<th scope="col" class="none">공개여부</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<c:set var="processYn" value="N" />
					<tr>
						<td class="none"><c:out value="${n - status.index}" /></td>
						<td class="none"><c:out value="${result.cbName}" /></td>
						<td>
						<c:if test="${result.cbIdx == 230}">
							<c:choose>
							<c:when test="${result.ansYnCont eq '22000200'}"><span class="status-move">답변요청없음</span></c:when>
							<c:when test="${result.mwNoReplyYn eq 'Y'}"><span class="status-move">답변비대상</span></c:when>
							<c:otherwise>
								<c:set var="MinwonResultList" value="${bbs:getMinwonResultList(result.bcIdx, result.cbIdx)}" />
								<c:set var="MinwonResultTotal" value="${bbs:getMinwonResultTotal(result.bcIdx, result.cbIdx)}" />
								<c:choose>
									<c:when test="${!empty MinwonResultTotal && MinwonResultTotal.auditYn ne '27000200'}"><span class="status-end">답변완료</span></c:when>
									<c:when test="${(fn:length(MinwonResultList) == 1) && (MinwonResultList[0].tempYn eq 'Y')}"><span class="status-end">답변완료</span></c:when>
									<c:otherwise>
										<c:set var="processYn" value="Y" />
										<span class="status-ing">처리중</span>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${result.cbIdx != 230}">
							<c:if test="${result.ansRYn eq 'Y'}"><span class="status-end">답변완료</span></c:if>
							<c:if test="${result.ansRYn eq 'N'}"><span class="status-ing">답변중</span></c:if>
						</c:if>
						</td>
						<c:set var="subCont" value="${fn:replace(fn:replace(fn:replace(result.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" />
						<c:set var="subCont" value="${fn:replace(subCont,'&amp;#','&#')}" />
						<td class="title">
							<a href="<c:url value='/site/seocho/foffice/board/MyMinwonView.do' />?cbIdx=<c:out value="${result.cbIdx}"/>&bcIdx=<c:out value="${result.bcIdx}"/>" onclick="doBbsContentFView('<c:out value="${result.cbIdx}"/>', '<c:out value="${result.bcIdx}"/>');return false;"><c:out value="${subCont}" /></a>
							<c:if test="${processYn eq 'Y'}">
								<c:set var="deadLine" value="${bbs:getMinwonResultDeadLine(result.bcIdx, result.cbIdx)}" />
								<c:set var="delayInfo" value="${bbs:getMinwonResultDelay(result.bcIdx, result.cbIdx)}" />
								<c:if test="${!empty delayInfo}">
									<fmt:parseDate var="mcDelayDay" value="${delayInfo.mcDelayDay}" pattern="yyyyMMdd"/>
									<fmt:formatDate var="mcDelayDay" value="${mcDelayDay}" pattern="yyyy.MM.dd"/>
									<p class="del" style='color:red;'><c:out value="${delayInfo.mcDelayRsn}" /> 등으로 <c:out value="${mcDelayDay}" />까지 답변이 연기되었습니다.</p>
								</c:if>
								<c:if test="${empty delayInfo && !empty deadLine}">
									<p class="del" <c:if test="${fn:replace(deadLine, '.', '') <= nowDate}">style='color:red;'</c:if>  ><c:out value="${deadLine}" />까지 답변 드리겠습니다.</p>
								</c:if>
							</c:if>
						</td>
						<td>
							<fmt:parseDate var="regDt" value="${result.regDt}" pattern="yyyyMMddHHmmss"/>
							<fmt:formatDate var="regDt" value="${regDt}" pattern="yyyy.MM.dd"/>
							<c:out value="${regDt}" />
						</td>
						<td class="none"><c:out value="${result.countCont}" /></td>
						<td class="none">
							<c:if test="${result.openYnCont eq '21000200'}"><span class="open">공개</span></c:if>
							<c:if test="${result.openYnCont eq '21000100'}"><span class="no-open">비공개</span></c:if>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${resultCnt == 0}">
					<tr>
						<td colspan="7"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<!--paginate -->
		<div class="paging">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doBbsContentFPag" />
		</div>
		<!--//paginate -->
		
		<div class="board-search">
			<fieldset>
				<legend>게시판검색</legend>
				<form:select title="조건선택" path="searchCondition">
					<form:option value="1" label="글제목 " />
					<form:option value="2" label="글내용" />
				</form:select>
				<form:input path="searchKeyword" class="w30" title="검색어입력" />
				<input type="image" src="/images/seocho/board/btn_search.gif" onclick="doBbsContentFPag('1');return false;" alt="검색">
			</fieldset>
		</div><!-- //search -->
		
	</div><!-- //board -->
</form:form>
</body>
