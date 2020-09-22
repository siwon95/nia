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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>설문조사 참여자 목록 및 결과</title>
</head>
<body>
	<table summary="설문조사 참여자 목록 및 결과" border="1">
		<caption>설문조사 참여자 목록 및 결과</caption>
		<thead>
			<tr>
				<th scope="col">순번</th>
				<c:if test="${PollListVo.plAuthType eq 'A' || PollListVo.plAuthType eq 'L'}">
				<th scope="col">이름</th>
				<th scope="col">아이디</th>
				</c:if>
				<c:if test="${PollListVo.plAddrYn eq 'Y'}">
				<th scope="col">주소</th>
				</c:if>
				<c:if test="${PollListVo.plTelYn eq 'Y'}">
				<th scope="col">전화번호</th>
				</c:if>
				<c:if test="${PollListVo.plHpYn eq 'Y'}">
				<th scope="col">휴대폰번호</th>
				</c:if>
				<c:if test="${PollListVo.plEmailYn eq 'Y'}">
				<th scope="col">이메일</th>
				</c:if>
				<c:forEach var="questionInfo" items="${questionList }">
				<th scope="col"><c:out value="${questionInfo.pqTitle}"/></th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<c:set var="answerList" value="${poll:getContentListAll(result.puIdx)}" />
			<tr>
				<td scope="row"><c:out value="${status.count}"/></td>
				<c:if test="${PollListVo.plAuthType eq 'A' || PollListVo.plAuthType eq 'L'}">
				<td><c:out value="${result.puName}" /></td>
				<td><c:out value="${result.puId}" /></td>
				</c:if>
				<c:if test="${PollListVo.plAddrYn eq 'Y'}">
				<td>(<c:out value="${result.puZip}" />) <c:out value="${result.puAddr1}" /> <c:out value="${result.puAddr2}" /></td>
				</c:if>
				<c:if test="${PollListVo.plTelYn eq 'Y'}">
				<td><c:out value="${result.puTel}" /></td>
				</c:if>
				<c:if test="${PollListVo.plHpYn eq 'Y'}">
				<td><c:out value="${result.puHp}" /></td>
				</c:if>
				<c:if test="${PollListVo.plEmailYn eq 'Y'}">
				<td><c:out value="${result.puEmail}" /></td>
				</c:if>
				<c:forEach var="answerInfo" items="${answerList }">

				<c:if test="${answerInfo.pqType eq 'title'}">
				<td>&nbsp;</td>
				</c:if>

				<c:if test="${answerInfo.pqType eq 'radio' || answerInfo.pqType eq 'checkbox'}">
				<td><c:out value="${answerInfo.paAnswer}"/>
					<c:if test="${answerInfo.pqEtc eq 'Y' && !empty answerInfo.paText}">
					<br/>기타답변 : <c:out value="${answerInfo.paText}"/>
					</c:if>
				</td>
				</c:if>

				<c:if test="${answerInfo.pqType eq 'selectbox'}">
				<td><c:out value="${answerInfo.paAnswer}"/></td>
				</c:if>

				<c:if test="${answerInfo.pqType eq 'text' || answerInfo.pqType eq 'textarea'}">
				<td><c:out value="${answerInfo.paText}"/></td>
				</c:if>

				</c:forEach>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
