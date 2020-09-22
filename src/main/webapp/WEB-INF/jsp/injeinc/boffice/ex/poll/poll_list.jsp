<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 설문관리
- 파일명 : poll_list.jsp
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
		var modal_url = "<c:url value='/boffice_noDeco/ex/poll/PollListForm.do' />";
		var modal_param = {"plIdx":"", "pageIndex":$("#pageIndex").val()};
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/ex/poll/PollListForm.do' />";
		var modal_param = {"plIdx":trIdx, "pageIndex":$("#pageIndex").val()};
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_pollMember").click(function(e){
		e.preventDefault();
		$("#plIdx").val($(this).parents("tr").eq(0).attr("data-idx"));
		$("#PollListVo").attr("action", "<c:url value='/boffice/ex/poll/PollUserList.do' />").submit();
	});
	$(".btn_pollResult").click(function(e){
		e.preventDefault();
		$("#plIdx").val($(this).parents("tr").eq(0).attr("data-idx"));
		$("#PollListVo").attr("action", "<c:url value='/boffice/ex/poll/PollListReport.do' />").submit();
	});
	$(".btn_pollQuestion").click(function(e){
		e.preventDefault();
		$("#plIdx").val($(this).parents("tr").eq(0).attr("data-idx"));
		$("#PollListVo").attr("action", "<c:url value='/boffice/ex/poll/PollQuestionList.do' />").submit();
	});
	
	//선택박스 실행
	$("select[name='plUse']").change(function(){
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = {"plIdx":trIdx, "plUse":$(this).val()};
		ajaxActionMessage("<c:url value='/boffice/ex/poll/PollListPlUseChangeAx.do'/>", ajaxParam, '', true);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<div class="section">
		<form:form commandName="PollListVo" name="PollListVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<form:hidden path="plIdx" />
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<tbody>
					<tr>
						<th scope="row"><label for="searchCdIdx">관리부서</label></th>
						<td>
							<select id="searchCdIdx" name="searchCdIdx">
								<option value="">담당부서 선택</option>
								<c:forEach var="departInfo" items="${departList }">
								<c:if test="${departInfo.cdDepstep2 eq '00' }">
									<option value="<c:out value="${departInfo.cdIdx}"/>" <c:if test="${PollListVo.searchCdIdx eq departInfo.cdIdx}">selected="selected"</c:if>><c:out value="${departInfo.cdSubject}"/></option>
								</c:if>
								<c:if test="${departInfo.cdDepstep2 ne '00' }">
								<option value="<c:out value="${departInfo.cdIdx}"/>" <c:if test="${PollListVo.searchCdIdx eq departInfo.cdIdx}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${departInfo.cdSubject}"/></option>
								</c:if>
								</c:forEach>
							</select>
						</td>
						<th scope="row"><label for="searchUse">상태선택</label></th>
						<td>
							<form:select path="searchUse">
								<form:option value="" label="전체보기" />
								<form:option value="Y" label="진행중" />
								<form:option value="N" label="마감" />
							</form:select>
						</td>
						<th scope="row"><label for="searchSiteCd">사이트 선택</label></th>
						<td>
							<select id="searchSiteCd" name="searchSiteCd">
								<option value="">사이트 선택</option>
								<c:forEach var="siteInfo" items="${siteList}">
									<option value="<c:out value="${siteInfo.siteCd}"/>" <c:if test="${PollListVo.searchSiteCd eq siteInfo.siteCd}">selected="selected"</c:if>><c:out value="${siteInfo.siteNm}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="검색" class="btn_inline btn_search">
		</div>
		</form:form>
		<div class="tableTitle">
			<!-- <div class="btnArea left">
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div> -->
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list">
				<caption>설문조사 목록</caption>
				<colgroup>
					<!-- <col class="w50"> -->
					<col class="w50">
					<col>
					<col class="w100">
					<col class="w100">
					<col class="w180">
					<col class="w100">
					<col class="w60">
					<col class="w170">
					<col class="w170">
					<col class="w140">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">순번</th>
						<th scope="col">제목</th>
						<th scope="col">사이트</th>
						<th scope="col">담당부서</th>
						<th scope="col">설문기간</th>
						<th scope="col">상태</th>
						<th scope="col">참여수</th>
						<th scope="col">미리보기</th>
						<th scope="col">설문결과</th>
						<th scope="col">설문관리</th>
						<!-- <th scope="col">설문문항</th>
						<th scope="col">참여자목록</th>
						<th scope="col">통계</th> -->
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value='${result.plIdx}' />">
						<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
						<td><c:out value="${n - status.index}" /></td>
						<td class="left"><c:out value="${result.plTitle}" /></td>
						<td><c:out value="${result.siteNm}" /></td>
						<td><c:out value="${result.cdSubject}" /></td>
						<td><c:out value="${fn:substring(result.plStartDate, 0, 10)}" /> ~ <c:out value="${fn:substring(result.plEndDate, 0, 10)}" /></td>
						<td>
							<c:if test="${result.plUse eq 'Y'}">
							<select name="plUse" class="w100p">
								<option value="Y" selected="selected">진행중</option>
								<option value="N">마감</option>
							</select>
							</c:if>
							<c:if test="${result.plUse ne 'Y'}">
							<select name="plUse" class="w100p">
								<option value="Y">진행중</option>
								<option value="N" selected="selected">마감</option>
							</select>
							</c:if>
						</td>
						<td><c:out value="${result.totalCnt}" /></td>
						<td>
							<div class="previewBox inline">
								<ul>
									<c:set var="mgrSiteCd" value="demo" />
									<li class="item1"><a href="#" class="btn_windowPopup" data-url="/site/<c:out value="${result.siteCd}"/>/ex/poll/PollListFViewIng.do?plIdx=<c:out value='${result.plIdx}' />" data-option="width=1200,height=800"><em>데스크톱</em></a></li>
									<li class="item2"><a href="#" class="btn_windowPopup" data-url="/site/<c:out value="${result.siteCd}"/>/ex/poll/PollListFViewIng.do?plIdx=<c:out value='${result.plIdx}' />" data-option="width=768,height=700"><em>테블릿</em></a></li>
									<li class="item3"><a href="#" class="btn_windowPopup" data-url="/site/<c:out value="${result.siteCd}"/>/ex/poll/PollListFViewIng.do?plIdx=<c:out value='${result.plIdx}' />" data-option="width=360,height=640"><em>모바일</em></a></li>
								</ul>
							</div>
						</td>
						<td>
							<a href="#" class="btn_inline btn_pollMember">참여자목록</a>
							<a href="#" class="btn_inline btn_pollResult">설문결과</a>
						</td>
						<td>
							<a href="#" class="btn_inline btn_pollQuestion">질문관리</a>
							<a href="#" class="btn_inline btn_mod">수정</a>
						</td>
						<%-- <td><a href="javascript:doPollQuestionList('<c:out value="${result.plIdx}" />');" class="btn_s">점검문항</a></td>
						<td><a href="javascript:doPollUserList('<c:out value="${result.plIdx}" />');" class="btn_s">참여자목록</a></td>
						<td><a href="javascript:doPollListReport('<c:out value="${result.plIdx}" />');" class="btn_s">통계</a></td> --%>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="10" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>	
	</div>
</body>
</html>
