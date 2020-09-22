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
- 제목 : 배너관리
- 파일명 : main_layer_popup_list.jsp
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
	
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/main/layerPopup/MainLayerPopupForm.do' />";
		var modal_param = $("#MainLayerPopupVo").serializeObject();
		modal_param['mlIdx'] = "new";
		modal_param['mlUse'] = "";
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/main/layerPopup/MainLayerPopupForm.do' />";
		var modal_param = $("#MainLayerPopupVo").serializeObject();
		modal_param['mlIdx'] = trIdx;
		modal_param['mlUse'] = "";
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".changeUse").change(function(){
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = {"mlIdx":trIdx, "mlUse":$(this).val()};
		ajaxActionMessage("/boffice/main/layerPopup/MainLayerPopupMlUseChangeAx.do", ajaxParam, '', true);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<div class="section">
		<form:form commandName="MainLayerPopupVo" name="MainLayerPopupVo" method="post" action="/boffice/main/layerPopup/MainLayerPopupList.do" class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<form:hidden path="mlIdx" />
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<tbody>
					<tr>
						<th scope="row"><label for="searchSiteCd">사이트선택</label></th>
						<td>
							<form:select path="searchSiteCd">
								<c:forEach var="siteInfo" items="${siteList }">
									<form:option value="${siteInfo.siteCd}" label="${siteInfo.siteNm}" />
								</c:forEach>
							</form:select>
						</td>
						<th scope="row"><label for="searchUse">사용유무</label></th>
						<td>
							<form:select path="searchUse">
								<form:option value="" label="전체" />
								<form:option value="Y" label="사용중" />
								<form:option value="N" label="종료" />
							</form:select>
						</td>
						<th scope="row"><label for="searchKeyword">제목 검색</label></th>
						<td><form:input path="searchKeyword" class="w150" /></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="검색" class="btn_inline btn_search">
		</div>
		
		<div class="tableTitle">
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		
		<div class="tableBox">
			<table class="list">
				<caption>레이어팝업목록</caption>
				<colgroup>
					<!-- <col class="w50"> -->
					<col class="w50">
					<col class="w100">
					<col>
					<col class="w150">
					<col class="w80">
					<col class="w80">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">순번</th>
						<th scope="col">이미지</th>
						<th scope="col">제목</th>
						<th scope="col">기간</th>
						<th scope="col">상태</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }" />
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr data-idx="<c:out value='${result.mlIdx}' />">
						<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
							<td><c:out value="${n - status.index}" /></td>
							<td><div class="thumbImg"><img src="<c:url value="/common/files/ImageDownload.do?filePath=/upload/mainLayerPopup/${result.mlFilename}" />" alt=""></div></td>
							<td class="left"><c:out value="${result.mlTitle}" /></td>
							<td><c:out value="${result.mlStartDate}" />~<br /><c:out value="${result.mlEndDate}" /></td>
							<td>
								<c:if test="${result.mlUse eq 'Y'}">
									<select name="mlUse" class="changeUse">
										<option value="A" <c:if test="${result.mlUse eq 'A'}">selected="selected"</c:if>>상시게시</option>
										<option value="Y" <c:if test="${result.mlUse eq 'Y'}">selected="selected"</c:if>>사용중</option>
										<option value="N">종료</option>
									</select>
								</c:if>
								<c:if test="${result.mlUse ne 'Y'}">
									<span class="captionText">종료</span>
								</c:if>
							</td>
							<td><a href="#" class="btn_inline btn_mod">수정</a></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="6"><spring:message code="common.nodata.msg" /></td>
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