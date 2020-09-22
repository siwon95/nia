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
- 파일명 : poll_question_list.jsp
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
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	//버튼 이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/ex/poll/PollQuestionForm.do' />";
		$("#pqIdx").val("");
		var modal_param = $("#PollQuestionVo").serializeArray();
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/ex/poll/PollQuestionForm.do' />";
		$("#pqIdx").val(formIdx);
		var modal_param = $("#PollQuestionVo").serializeArray();
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		$("#pqIdx").val(formIdx);
		$("#PollQuestionVo").attr("action", "<c:url value='/boffice/ex/poll/PollQuestionRmv.do' />") .submit();
	});
	$(".btn_sortUp").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = {"pqIdx":formIdx};
		ajaxActionMessage("<c:url value='/boffice/ex/poll/PollQuestionSortUpAx.do'/>", ajaxParam, '', true);
	});
	$(".btn_sortDown").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = {"pqIdx":formIdx};
		ajaxActionMessage("<c:url value='/boffice/ex/poll/PollQuestionSortDownAx.do'/>", ajaxParam, '', true);
	});
	$(".btn_list").click(function(e){
		e.preventDefault();
		$("#PollQuestionVo").attr("action", "<c:url value='/boffice/ex/poll/PollList.do' />").submit();
	});
});
</script>
</head>
<body>
	<form:form commandName="PollQuestionVo" name="PollQuestionVo" method="post">
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCdIdx" />
	<form:hidden path="searchUse" />
	<form:hidden path="plIdx" />
	<form:hidden path="pqIdx" />
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
						<td><c:out value="${PollListVo.plTitle}" /></td>
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
					<%-- <tr>
						<th scope="row">참여자/모집인원</th>
						<td colspan="3"><c:out value="${resultCnt}" />/<c:out value="${PollListVo.plNumber}" /></td>
					</tr> --%>
				</tbody>
			</table>
		</div>
		<div class="tableTitle">
			<!-- <div class="btnArea left">
				<a href="#" class="btn_inline">선택삭제</a>
			</div> -->
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list">
				<caption>설문조사 질문 목록</caption>
				<colgroup>
					<col class="w50">
					<col>
					<col>
					<col class="w80">
					<col class="w120">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">순번</th>
						<th scope="col">질문내용</th>
						<th scope="col">답변목록</th>
						<th scope="col">순서변경</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value="${result.pqIdx}" />">
						<td><c:out value="${result.pqSort}" /></td>
						<td class="left">
							<b><c:out value="${result.pqTitle}" /></b><br />
							<c:if test="${!empty result.pqDetail}">
								<c:out value="${cmm:textAreaToHtml(result.pqDetail)}" />
							</c:if>
						</td>
						<td class="left">
							<!-- <ul>
								<li>1번 답변</li>
								<li>2번 답변</li>
								<li>3번 답변</li>
								<li>4번 답변</li>
							</ul> -->
							<c:if test="${result.pqType eq 'radio'}">
								<c:set var="pqItemList" value="${cmm:split(result.pqItemList, '｜', result.pqItemCnt)}" />
								<c:forEach begin="0" varStatus="status2" end="${result.pqItemCnt-1}">
									<p <c:if test="${status2.last && result.pqEtc eq 'Y'}">class="etc"</c:if>>
										<input type="radio" id="<c:out value="${result.pqIdx}"/>_<c:out value="${status2.count}"/>" name="<c:out value="${result.pqIdx}"/>" value="<c:out value="${status2.count}"/>" /> 
										<label for="<c:out value="${result.pqIdx}"/>_<c:out value="${status2.count}"/>"><c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}"/></label>
										<c:if test="${status2.last && result.pqEtc eq 'Y'}">
											<input type="text" id="<c:out value="${result.pqIdx}"/>_etc" name="<c:out value="${result.pqIdx}"/>_etc" class="w30" title="기타의견 입력" />
										</c:if>
									</p>
								</c:forEach>
							</c:if>
							<c:if test="${result.pqType eq 'selectbox'}">
								<select id="<c:out value="${result.pqIdx}"/>" name="<c:out value="${result.pqIdx}"/>"
									class="select2">
									<option value="">선택하세요</option>
									<c:set var="pqItemList" value="${cmm:split(result.pqItemList, '｜', result.pqItemCnt)}" />
									<c:forEach begin="0" varStatus="status2" end="${result.pqItemCnt-1}">
										<option value="<c:out value="${status2.count}"/>"><c:out value="${fn:length(pqItemList) > 0 ? pqItemList[status2.index]:''}" /></option>
									</c:forEach>
								</select>
							</c:if>
							<c:if test="${result.pqType eq 'checkbox'}">
								<c:set var="pqItemList" value="${cmm:split(result.pqItemList, '｜', result.pqItemCnt)}" />
								<c:forEach begin="0" varStatus="status2" end="${result.pqItemCnt-1}">
									<p <c:if test="${status2.last && result.pqEtc eq 'Y'}">class="etc"</c:if>>
										<input type="checkbox" id="<c:out value="${result.pqIdx}"/>_<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>" name="<c:out value="${result.pqIdx}"/>" value="<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>" /> 
										<label for="<c:out value="${result.pqIdx}"/>_<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>"><c:out value="${fn:length(pqItemList) > 0 ? pqItemList[status2.index]:''}"/></label>
										<c:if test="${status2.last && result.pqEtc eq 'Y'}">
											<input type="text" id="<c:out value="${result.pqIdx}"/>_etc" name="<c:out value="${result.pqIdx}"/>_etc" value="" class="w30" title="기타의견 입력" />
										</c:if>
									</p>
								</c:forEach>
							</c:if>
							<c:if test="${result.pqType eq 'text'}">
								<input type="text" id="<c:out value="${result.pqIdx}"/>" name="<c:out value="${result.pqIdx}"/>" value="" class="w95" />
							</c:if>
							<c:if test="${result.pqType eq 'textarea'}">
								<textarea id="<c:out value="${result.pqIdx}"/>" name="<c:out value="${result.pqIdx}"/>" rows="5" class="w95"></textarea>
							</c:if>
						</td>
						<td>
							<c:if test="${PollQuestionVo.modifyYn}">
								<c:if test="${!status.first}">
									<a href="#" class="btn_ss btn_sortUp">▲</a>
								</c:if>
								<c:if test="${!status.last}">
									<a href="#" class="btn_ss btn_sortDown">▼</a>
								</c:if>
							</c:if> <c:if test="${!PollQuestionVo.modifyYn}">-</c:if>
						</td>
						<td>
							<c:if test="${PollQuestionVo.modifyYn}">
								<a href="#" class="btn_inline btn_mod">수정</a>
								<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
							</c:if>
							<c:if test="${!PollQuestionVo.modifyYn}">-</c:if>
						</td>
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
		<div class="btnArea right">
			<a href="#" class="btn_inline btn_list">설문목록</a>
		</div>
	</div>
</body>
</html>
