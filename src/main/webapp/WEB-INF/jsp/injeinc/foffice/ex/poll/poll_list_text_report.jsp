<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>설문조사 주관식 답변 목록</title>
<link href="/css/board_Poll.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
//<![CDATA[
	
	function doPollListReport() {
		$("#PollAnswerViewVo").attr("action", "<c:url value='/boffice/ex/poll/PollListReport.do' />").submit();
	}
	
	function doPollAnswerPag(pageSubIndex) {
		$("#pageSubIndex").val(pageSubIndex);
		$("#PollAnswerViewVo").attr("action", "<c:url value='/boffice/ex/poll/PollListTextReport.do' />").submit();
	}
//]]>
</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="PollAnswerViewVo" name="PollAnswerViewVo" method="post">
<form:hidden path="pageIndex" />
<form:hidden path="searchCdIdx" />
<form:hidden path="searchUse" />
<form:hidden path="plIdx" />

<form:hidden path="pageSubIndex" />
<form:hidden path="pqIdx" />
<form:hidden path="pqType" />
<form:hidden path="paAnswer" />
	<!-- 타이틀 -->
			<div class="title">
				<h2>설문조사 &gt;</h2>
				<h3>주관식 답변 목록</h3>
			</div>
			
	<!-- 컨텐츠 영역 -->
			<div id="contents">
				
				<table summary="설문조사 주관식 답변 목록" class="list1">
					<caption>설문조사 주관식 답변 목록</caption>
					<colgroup>
						<col width="10%" />
						<col width="20%" />
						<col width="70%" />
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
							<td colspan="3"><spring:message code="common.nodata.msg" /></td>
						</tr>
						</c:if>
					</tbody>
				</table>
				
				<!--paginate -->
				<div class="paginate">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPollAnswerPag" />
				</div>
				<!--//paginate -->
				
				<!-- 버튼 -->
				<div class="btn_zone">
					<a href="#report" onclick="doPollListReport();return false;" class="btn2">통계로 돌아가기</a>
				</div>
				<!-- //버튼 -->
		
			</div>
			<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
