<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 프로그램선택(팝업)
- 파일명 : progrmSearch.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<c:import url="/layout/cms/head.jsp" ></c:import>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	$(".btn_selected").click(function(e){
    	e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		if(opener.$('#progrmGrpNm').length > 0){
			opener.$('#progrmGrpNm').val(trIdx);	
		}
		if(opener.$('#mgrUrl').length > 0){
			opener.$('#mgrUrl').val(trIdx);
			opener.doNodeAdd('mgrUrl');
		}
	    window.close();
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

<!-- ============================== 모달영역 ============================== -->
<div class="modalWrap popup">
	<div class="modalTitle">
		<h3>프로그램선택</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<form name="progrmForm" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
		<input name="checkedProgrmFileNmForDel" type="hidden" />
		<input type="hidden" name="cmd" />
		<input type="hidden" name="tmp_progrmNm" />
		<div class="search">
			<div class="right">
				<select name="searchCondition" id="searchCondition">
					<option value="" <c:if test="${searchVO.searchCondition eq '' }">selected="selected"</c:if>>전체</option>
					<option value="1" <c:if test="${searchVO.searchCondition eq '1' }">selected="selected"</c:if>>프로그램명</option>
					<option value="2" <c:if test="${searchVO.searchCondition eq '2' }">selected="selected"</c:if>>프로그램그룹명</option>
					<option value="3" <c:if test="${searchVO.searchCondition eq '3' }">selected="selected"</c:if>>프로그램한글명</option>
				</select>
				<span class="sfont">
				<input type="text" id="searchKeyword" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>" title="검색어" size="30" />
				<input type="submit" value="검색" class="btn_inline btn_search">
			</div>
		</div>
		</form>
		<div class="tableBox">
			<table class="list">
				<caption>프로그램 목록</caption>
				<colgroup>
					<col class="w50">
					<col class="w30p">
					<col>
					<col class="w70">
				</colgroup>
				<thead>
				<tr>
				    <th scope="col">순번</th>
				    <th scope="col">프로그램명</th>
				    <th scope="col">프로그램명(한글)</th>
				    <th scope="col">관리</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="result" items="${list_progrmmanage}" varStatus="status">
				<tr data-idx="<c:out value="${result.progrmFileNm}"/>">
					<td><c:out value="${(paginationInfo.totalRecordCount+1)-(status.count+(paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)}" /></td>
					<td class="left"><c:out value="${result.progrmFileNm}"/></td>
					<td class="left"><c:out value="${result.progrmKoreanNm}"/></td>
					<td><a href="#" class="btn_inline btn_selected">선택</a></td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(list_progrmmanage) == 0}">
				<tr>
					<td colspan="4"><spring:message code="common.nodata.msg" /></td>
				</tr>
				</c:if>
				</tbody>
			</table>	
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>
	</div>
</div>
</body>
</html>

