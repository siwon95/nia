<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Pg리스트</title>
<script type="text/javascript">
// 등록페이지
function doPgForm() {
	$("#PgVo").attr("action", "<c:url value='/boffice/common/pg/Pg_RegForm.do' />").submit();
}

// 페이징처리 
function doPgList(pageIndex) {
	$("#pageIndex").val(pageIndex);
	$("#PgVo").attr("action", "<c:url value='/boffice/common/pg/Pg_List.do' />").submit();
}

// 수정페이지
function doPgMod(cstMid, pgAls) {
	$("#cstMid").val(cstMid);
	$("#pgAls").val(pgAls);
	$("#PgVo").attr("action", "<c:url value='/boffice/common/pg/Pg_ModForm.do' />").submit();
}

$(document).ready(function() {
});
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
<form:form commandName="PgVo" name="PgVo" method="post">
	<form:hidden path="pageIndex" />
	<form:hidden path="type" />
	<form:hidden path="cstMid" />
	<form:hidden path="pgAls" />

	<!-- 컨텐츠 영역 -->
	<div id="contents">

		<!-- 검색 -->
		<fieldset class="ad_main_search">
			<select name="searchCondition" title="검색어조건">
				<option value="als" <c:if test="${PgVo.searchCondition eq 'als'}">selected="selected"</c:if>>PG별칭</option>
				<option value="id" <c:if test="${PgVo.searchCondition eq 'id'}">selected="selected"</c:if>>상점ID</option>
				<option value="name" <c:if test="${PgVo.searchCondition eq 'name'}">selected="selected"</c:if>>담당자</option>
			</select>
			<span class="sfont">
				<form:input path="searchKeyword" size="30" title="검색어" />
				<a href="javascript:doPgList('1');" class="btn2 btn_input" title="검색">
					<i class="fa fa-search"></i>검색
				</a>
			</span>
		</fieldset>
		<!-- //검색 -->

		<table summary="Pg리스트" class="list1">
			<caption>Pg리스트</caption>
			<colgroup>
				<col width="7%" />
				<col width="15%" />
				<col width="15%" />
				<col width="*" />
				<col width="7%" />
				<col width="10%" />
				<col width="15%" />
				<col width="7%" />
			</colgroup>
			<thead>
				<tr>
					<th>No</th>
					<th>상점 ID</th>
					<th>PG별칭</th>
					<th>PG설명</th>
					<th>담당자</th>
					<th>담당자 연락처</th>
					<th>등록일시</th>
					<th>수정</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resultList}" var="result" varStatus="status">
					<tr>
						<td><c:out value="${(totCnt+1)-(status.count+(PgVo.pageIndex-1)*PgVo.recordCountPerPage)}" /></td>
						<td><c:out value="${result.cstMid}" /></td>
						<td><c:out value="${result.pgAls}" /></td>
						<td><c:out value="${result.pgMemo}" /></td>
						<td><c:out value="${result.pgDeptNm}" /></td>
						<td><c:out value="${result.pgDeptTelNum}" /></td>
						<td><c:out value="${result.regDt}" /></td>
						<td class="sbj">
							<a href="javascript:doPgMod('<c:out value="${result.cstMid }"/>','<c:out value="${result.pgAls}"/>');" title="수정">
								<u>수정</u>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!--paginate -->
		<div class="paginate">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPgList" />
		</div>
		<!--//paginate -->

		<!-- 버튼 -->
		<div class="btn_zone">
			<div class="right">
				<a href="javascript:doPgForm();" class="btn2" title="등록">등록</a>
			</div>
		</div>
		<!-- //버튼 -->

	</div>
	<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>