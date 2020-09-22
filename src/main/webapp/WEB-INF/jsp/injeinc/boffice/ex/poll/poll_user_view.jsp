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
- 파일명 : poll_user_view.jsp
- 최종수정일 : 2019-04-18
- 상태 : 수정중
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
function doPollUserForm() {
	$("#PollUserVo").attr("action", "<c:url value='/boffice/ex/poll/PollUserForm.do' />").submit();
}

function doPollUserList() {
	$("#PollUserVo").attr("action", "<c:url value='/boffice/ex/poll/PollUserList.do' />").submit();
}
</script>
</head>
<body>
<form:form commandName="PollUserVo" name="PollUserVo" method="post" enctype="multipart/form-data">
<form:hidden path="pageIndex" />
<form:hidden path="searchCdIdx" />
<form:hidden path="searchUse" />
<form:hidden path="plIdx" />
<form:hidden path="pageSubIndex" />
<form:hidden path="searchSubCondition" />
<form:hidden path="searchSubKeyword" />
<form:hidden path="puIdx" />

	<div class="section">
		<div class="tableBox">
			<table class="form">
				<caption>설문조사 정보 및 참여자  기본정보 상세보기</caption>
				<colgroup>
					<col class="w15p">
					<col class="w35p">
					<col class="w15p">
					<col class="w35p">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">제목</th>
						<td colspan="3"><c:out value="${PollListVo.plTitle}" /></td>
					</tr>
					<tr>
						<th scope="row">담당부서</th>
						<td><c:out value="${PollListVo.cdSubject}" /></td>
						<th scope="row">사이트</th>
						<td><c:out value="${PollListVo.siteNm}" /></td>
					</tr>
					<tr>
						<th scope="row">담당자</th>
						<td><c:out value="${PollListVo.plManagerName}" /></td>
						<th scope="row">담당자 연락처</th>
						<td><c:out value="${PollListVo.plManagerTel}" /></td>
					</tr>
					<tr>
						<th scope="row">기간</th>
						<td colspan="3"><c:out value="${fn:substring(PollListVo.plStartDate, 0, 16)}" /> ~ <c:out value="${fn:substring(PollListVo.plEndDate, 0, 16)}" /></td>
					</tr>
					<tr>
						<th scope="row">안내문</th>
						<td colspan="3"><c:out value="${cmm:textAreaToHtml(PollListVo.plGuide)}"/></td>
					</tr>
					<c:if test="${PollListVo.plAuthType eq 'A' || PollListVo.plAuthType eq 'L'}">
					<tr>
						<th scope="row">이름</th>
						<td><c:out value="${PollUserVo.puName}" /></td>
						<th scope="row">아이디</th>
						<td><c:out value="${PollUserVo.puId}" /></td>
					</tr>
					</c:if>
					<c:if test="${PollListVo.plAddrYn eq 'Y'}">
					<tr>
						<th scope="row">주소</th>
						<td colspan="3">(<c:out value="${PollUserVo.puZip}" />) <c:out value="${PollUserVo.puAddr1}" /> <c:out value="${PollUserVo.puAddr2}" /></td>
					</tr>
					</c:if>
					<c:if test="${PollListVo.plTelYn eq 'Y'}">
					<tr>
						<th scope="row"><label for="puTel1">전화번호</label></th>
						<td colspan="3"><c:out value="${PollUserVo.puTel}" /></td>
					</tr>
					</c:if>
					<c:if test="${PollListVo.plHpYn eq 'Y'}">
					<tr>
						<th scope="row"><label for="puHp1">휴대폰번호</label></th>
						<td colspan="3"><c:out value="${PollUserVo.puHp}" /></td>
					</tr>
					</c:if>
					<c:if test="${PollListVo.plEmailYn eq 'Y'}">
					<tr>
						<th scope="row"><label for="puEmail1">이메일</label></th>
						<td colspan="3"><c:out value="${PollUserVo.puEmail}" /></td>
					</tr>
					</c:if>
					<tr>
						<th scope="row">설문내용</th>
						<td colspan="3">
							<div class="poll-wrap"> <!-- [퍼블리싱] 재수정 필요 20180124 -->
							<c:forEach var="questionInfo" items="${questionList }" varStatus="status">		
								<c:set var="answerInfo" value="${answerList[status.index]}" />
								<c:set var="paAnswer" value="${answerInfo.paAnswer}" />
								<c:set var="paText" value="${answerInfo.paText}" />				
								<c:if test="${questionInfo.pqType eq 'title'}">
									<h4><c:out value="${questionInfo.pqTitle}" /></h4>
									<c:if test="${!empty questionInfo.pqDetail}">
									<p><c:out value="${cmm:textAreaToHtml(questionInfo.pqDetail)}" /></p>
									</c:if>
								</c:if>
								<c:if test="${questionInfo.pqType ne 'title'}">
									<dl>
										<dt><c:if test="${questionInfo.pqCheck eq 'Y'}"><span class="required">*</span></c:if><c:out value="${questionInfo.pqTitle}" /></dt>
										<c:if test="${!empty questionInfo.pqDetail}">
										<dd class="guide"><c:out value="${cmm:textAreaToHtml(questionInfo.pqDetail)}" /></dd>
										</c:if>
										<dd <c:if test="${questionInfo.pqItemDirection eq 'C'}">class="type-col"</c:if>>
											<c:if test="${questionInfo.pqType eq 'radio'}">
												<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
												<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">
												<p <c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">class="etc"</c:if>>
												<c:out value='${status2.count}' />. <c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}"/> <c:if test="${paAnswer == status2.count}"><strong>∨</strong></c:if>
												<c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">
												: <c:out value='${paText}' />
												</c:if>
												</p>
												</c:forEach>
											</c:if>
											<c:if test="${questionInfo.pqType eq 'selectbox'}">
												<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
												<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">
												<p <c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">class="etc"</c:if>>
												<c:out value='${status2.count}' />. <c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}" /> <c:if test="${paAnswer == status2.count}"><strong>∨</strong></c:if>
												</p>
												</c:forEach>
											</c:if>
											<c:if test="${questionInfo.pqType eq 'checkbox'}">
												<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
												<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">
												<p <c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">class="etc"</c:if>>
												<c:out value='${cmm:changeNumberAlphabat(status2.count)}' />. <c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}"/> <c:if test="${fn:indexOf(paAnswer, cmm:changeNumberAlphabat(status2.count)) > -1}"><strong>∨</strong></c:if>
												<c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">
												: <c:out value='${paText}' />
												</c:if>
												</p>
												</c:forEach>
											</c:if>
											<c:if test="${questionInfo.pqType eq 'text'}">
												<c:out value='${paText}' />
											</c:if>
											<c:if test="${questionInfo.pqType eq 'textarea'}">
												<c:out value="${cmm:textAreaToHtml(paText)}"/>
											</c:if>
										</dd>
									</dl>
								</c:if>
							</c:forEach>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<a href="javascript:doPollUserForm();" class="btn_inline on">수정</a>
			<a href="javascript:doPollUserList();" class="btn_inline">참여자 목록</a>
		</div>
	</div>
	
</form:form>
</body>
</html>