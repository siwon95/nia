<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 회원그룹관리
- 파일명 : group_config_list.jsp
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
	alert("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	alert("<spring:message code="${param.message}" />");
	</c:if>
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/sy/group/GroupConfig_Form.do'/>";
		$("#gcIdx").val("");
		var modal_param = $("#GroupConfigVo").serializeArray();
		var modal_class = "small"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/sy/group/GroupConfig_View.do' />";
		$("#gcIdx").val(formIdx);
		var modal_param = $("#GroupConfigVo").serializeArray();
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
    $(".btn_del").click(function(e){
    	e.preventDefault();
		if(!confirm("해당 그룹을 삭제하실 경우 데이터를 복원하실수 없습니다.\n그룹을 삭제하시겠습니까?")){
			return;
		}
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		$("#GroupConfigVo #gcIdx").val(formIdx);
		$("#GroupConfigVo").attr("action","<c:url value='/boffice/sy/group/GroupConfig_Rmv.do' />").submit();
    });
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->
</head>
<body>
	<div class="section">
		<form:form commandName="GroupConfigVo" name="GroupConfigVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<form:hidden path="gcIdx" />
		<form:hidden path="gcMenu" />
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<tbody>
					<tr>
						<th>생성일</th>
						<td>
							<label for="scRegDtSt" class="hidden">생성일(부터)</label>
							<form:input path="scRegDtSt" class="useDatepicker dateFrom"/> ~
							<label for="scRegDtEd" class="hidden">생성일(까지)</label>
							<form:input path="scRegDtEd" class="useDatepicker dateTo"/>		
						</td>
						<th><label for="searchCondition">검색어</label></th>
						<td>
							<form:select path="searchCondition">
								<form:option value="" label="전체"/>
								<form:option value="gcName" label="그룹명"/>
							</form:select>
							<label for="searchKeyword" class="hidden">검색어</label>
							<form:input path="searchKeyword" size="30"/>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="검색" class="btn_inline btn_search">
		</div>
		
		<div class="tableTitle">
			<!-- <div class="btnArea left">
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div> -->
			<div class="btnArea right">
				<!--<a href="" class="btn_m">취소하기</a> -->
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		
		<div class="tableBox">
			<table class="list">
				<caption>회원그룹목록</caption>
				<colgroup>
					<!-- <col class="w50"> -->
					<col class="w50">
					<col class="w120">
					<col class="w120">
					<col>
					<col class="w140">
					<col class="w120">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">순번</th>
						<th scope="col">그룹명</th>
						<th scope="col">회원수</th>
						<th scope="col">관련게시판</th>
						<th scope="col">등록일</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList}" var="result" varStatus="status">
					<tr data-idx="<c:out value="${result.gcIdx}" />">
						<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
						<td><c:out value="${(totCnt+1)-(status.count+(UserVo.pageIndex-1)*UserVo.recordCountPerPage)}" /></td>						
						<td><c:out value="${result.gcName}" /></td>
						<td><c:out value="${result.memberCount}" /></td>
						<td></td>
						<td><c:out value="${result.regDt}" /></td>
						<td>
							<a href="#" class="btn_inline btn_mod">수정</a>
							<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="6" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>
		</form:form>
	</div>
</body>
</html>
