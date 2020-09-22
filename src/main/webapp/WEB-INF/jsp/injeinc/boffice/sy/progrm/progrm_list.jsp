<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 프로그램
- 파일명 : progrmList.jsp
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
	<c:if test="${not empty resultMsg}">
	resultMessage("<c:out value="${resultMsg}" />");
	</c:if>
	
	//버튼이벤트
    $(".btn_add").click(function(e){
    	e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/sy/progrm/ProgramRegist.do'/>";
		var modal_param = {"pageIndex":$("#pageIndex").val(), "cmd":$("#cmd").val(), "tmp_progrmNm":$("#tmp_progrmNm").val()};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
    });
    $(".btn_mod").click(function(e){
    	e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/sy/progrm/ProgramDetail.do'/>";
		var modal_param = {"pageIndex":$("#pageIndex").val(), "cmd":$("#cmd").val(), "tmp_progrmNm":trIdx};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
    });
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section">
		<form name="progrmForm" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
		<input type="hidden" name="cmd" />
		<input type="hidden" name="tmp_progrmNm" />
		<div class="search">
			<div class="right">
				<select name="searchCondition" id="searchCondition">
					<option value="" <c:if test="${searchVO.searchCondition eq '' }">selected="selected"</c:if>>전체</option>
					<option value="2" <c:if test="${searchVO.searchCondition eq '2' }">selected="selected"</c:if>>그룹명</option>
					<option value="1" <c:if test="${searchVO.searchCondition eq '1' }">selected="selected"</c:if>>프로그램명</option>
					<option value="3" <c:if test="${searchVO.searchCondition eq '3' }">selected="selected"</c:if>>프로그램명(한글)</option>
				</select>
				<input type="text" id="searchKeyword" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>" title="검색어" size="30" />
				<input type="submit" value="검색" class="btn_inline btn_search">
			</div>
		</div>
		</form>
		
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
			<table class="list useBtn">
				<caption>프로그램 목록</caption>
				<colgroup>
					<col class="w50">
					<col class="w20p">
					<col class="w20p">
					<col class="w20p">
					<col>
					<col class="w70">
				</colgroup>
				<thead>
					<tr>
					    <th scope="col">순번</th>
					    <th scope="col">그룹명</th>
					    <th scope="col">프로그램명</th>
					    <th scope="col">프로그램명(한글)</th>
					    <th scope="col">URL</th>
					    <th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${list_progrmmanage}" varStatus="status">
					<tr data-idx="<c:out value='${result.progrmFileNm}' />">
						<td><c:out value="${(paginationInfo.totalRecordCount+1)-(status.count+(paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)}" /></td>
						<td class="left"><c:out value="${result.progrmGrpNm}"/></td>
						<td class="left"><c:out value="${result.progrmFileNm}"/></td>
						<td class="left"><c:out value="${result.progrmKoreanNm}"/></td>
						<td class="left"><c:out value="${result.url}"/></td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(list_progrmmanage) == 0}">
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
	</div>

</body>
</html>