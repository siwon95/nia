<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="poll" uri="http://cms.injeinc.co.kr/poll"%>
<%-- ------------------------------------------------------------
- 제목 : 설문관리
- 파일명 : poll_list_report.jsp
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
	$(".btn_list").click(function(e){
		e.preventDefault();
		$("#PollListVo").attr("action", "<c:url value='/boffice/ex/poll/PollList.do' />").submit();
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
	<form:form commandName="PollListVo" name="PollListVo" method="post">
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCdIdx" />
	<form:hidden path="searchUse" />
	<form:hidden path="plIdx" />
	<input type="hidden" id="pqIdx" name="pqIdx" value="" />
	<input type="hidden" id="pqType" name="pqType" value="" />
	<input type="hidden" id="paAnswer" name="paAnswer" value="" />
	</form:form>

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
						<td><c:out value="${PollListVo.plTitle}" />
						<th scope="row">사이트</th>
						<td><c:out value="${PollListVo.siteNm}" /></td>
					</tr>
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
					<tr>
						<th scope="row">참여자</th>
						<td><c:out value="${resultTotal}" /></td>
						<th scope="row">모집인원</th>
						<td><c:out value="${PollListVo.plNumber}" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
				<caption></caption>
				<colgroup>
					<col class="w50">
					<col class="w200">
					<col class="w200">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">순번</th>
						<th scope="col">질문내용</th>
						<th scope="col">답변목록</th>
						<th scope="col">선택결과</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td><c:out value="${result.pqSort}" /></td>
						<td class="left">
							<b><c:if test="${result.pqCheck eq 'Y'}"><span class="required">*</span></c:if><c:out value="${result.pqTitle}" /></b><br>
							<c:if test="${!empty result.pqDetail}">
								<c:out value="${cmm:textAreaToHtml(result.pqDetail)}" />
							</c:if>
						</td>
					
						<td class="left">
						<c:if test="${result.pqType eq 'radio' || result.pqType eq 'selectbox'}">
								<ul>
								<c:set var="pqItemList" value="${cmm:split(result.pqItemList, '｜', result.pqItemCnt)}" />
								<c:forEach begin="0" varStatus="status2" end="${result.pqItemCnt-1}">
									<li>
										<c:out value='${status2.count}' />번 <a href="#" class="btn_textReport" data-pqIdx="<c:out value="${result.pqIdx}"/>" data-pqType="<c:out value="${result.pqType}"/>" data-paAnswer="<c:out value="${status2.count}"/>"><c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}"/></a>
									</li>
								</c:forEach>
								</ul>	
						</c:if>
						<c:if test="${result.pqType eq 'checkbox'}">
						<ul>
						<c:set var="pqItemList" value="${cmm:split(result.pqItemList, '｜', result.pqItemCnt)}" />
						<c:forEach begin="0" varStatus="status2" end="${result.pqItemCnt-1}">
							<li>
								<c:out value='${cmm:changeNumberAlphabat(status2.count)}' />번 <a href="#" class="btn_textReport" data-pqIdx="<c:out value="${result.pqIdx}"/>" data-pqType="<c:out value="${result.pqType}"/>" data-paAnswer="<c:out value="${status2.count}"/>"><c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}"/></a>
							</li>
						</c:forEach>
						</ul>
						</c:if>
						</td>
						<td class="left barUI">
						<c:if test="${result.pqType eq 'radio' || result.pqType eq 'selectbox'}">
							<ul>
							<c:forEach begin="0" varStatus="status2" end="${result.pqItemCnt-1}">
								<c:set var="count" value="${poll:getCount(result.pqIdx, result.pqType, status2.count)}" />
								<c:if test="${resultTotal > 0}">
									<c:set var="percent" value="${100*count/resultTotal}" />
									<c:set var="percent" value="${percent+((percent%1>0.5)?(1-(percent%1))%1:-(percent%1))}" />
								</c:if>
								<c:if test="${resultTotal == 0}">
									<c:set var="percent" value="0" />
								</c:if>
								<fmt:parseNumber var="percent" integerOnly="true" value="${percent}" />
								<li>
									<label><c:out value='${status2.index%10+1}' /></label>
									<span class="bar">
										<span class="curr" style="width:<c:out value='${percent}' />%;"><em><c:out value='${percent}' /><span>%</span></em></span>
										<span class="left"><c:out value='${count}' /></span>
										<%-- <span class="right"><c:out value='${resultTotal}' /></span> --%>
									</span>
								</li>
							</c:forEach>
							</ul>
						</c:if>
						<c:if test="${result.pqType eq 'checkbox'}">
						<ul>
						<c:set var="pqItemList" value="${cmm:split(result.pqItemList, '｜', result.pqItemCnt)}" />
						<c:forEach begin="0" varStatus="status2" end="${result.pqItemCnt-1}">
							<c:set var="count" value="${poll:getCount(result.pqIdx, result.pqType, cmm:changeNumberAlphabat(status2.count))}" />
							<c:if test="${resultTotal > 0}">
								<c:set var="percent" value="${100*count/resultTotal}" />
								<c:set var="percent" value="${percent+((percent%1>0.5)?(1-(percent%1))%1:-(percent%1))}" />
							</c:if>
							<c:if test="${resultTotal == 0}">
								<c:set var="percent" value="0" />
							</c:if>
							<fmt:parseNumber var="percent" integerOnly="true" value="${percent}" />
							<li>
								<label><c:out value='${cmm:changeNumberAlphabat(status2.count)}' /></label>
								<span class="bar">
									<span class="curr" style="width:<c:out value='${percent}' />%;"><em><c:out value='${percent}' /><span>%</span></em></span>
									<span class="left"><c:out value='${count}' /></span>
									<%-- <span class="right"><c:out value='${resultTotal}' /></span> --%>
								</span>
							</li>
						</c:forEach>
						</ul>
						</c:if>
						<c:if test="${result.pqType ne 'title'}">
						<div class="btnArea right mb0">
							<c:if test="${result.pqEtc eq 'Y'}">
							<a href="#" data-pqIdx="<c:out value="${result.pqIdx}"/>" data-pqType="<c:out value="${result.pqType}"/>" data-paAnswer="<c:out value="${result.pqType eq 'checkbox' ? cmm:changeNumberAlphabat(result.pqItemCnt) : result.pqItemCnt}"/>" class="btn_inline btn_textReport">기타보기</a>
							</c:if>
							<c:if test="${result.pqType eq 'text' || result.pqType eq 'textarea'}">
							<a href="#" data-pqIdx="<c:out value="${result.pqIdx}"/>" data-pqType="" data-paAnswer="" class="btn_inline btn_textReport">결과보기</a>
							</c:if>
						</div>
						</c:if>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="4" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="btnArea right">
			<a href="javascript:doPollList();" class="btn_inline btn_list">설문목록</a>
		</div>
	</div>
</body>
</html>
