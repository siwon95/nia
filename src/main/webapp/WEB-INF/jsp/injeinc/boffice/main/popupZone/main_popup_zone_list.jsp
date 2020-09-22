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
- 파일명 : main_popup_zone_list.jsp
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
	
	//트리
	$('#menuTree').css("display","").jstree({
		"core":{"check_callback":true},
		"default":{draggable:false} //, "plugins":["checkbox"]
	}).bind("loaded.jstree",function(event, data){ //로딩시
	}).bind("select_node.jstree", function(event, data){ //선택시
		if(data.node.id && data.node.id != 'j1_1'){
			$("#code").val(data.node.id);
			$(".searchListPage").submit();	
		}
	}).bind("move_node.jstree",function(event, data){ //드레그이동시
	});
	if($("#code").val() && $("#searchSiteCd").val()){
		$(".treePath").text($("#siteCd > option:selected").text()+" > "+$(".jstree-clicked").text());
	}

	//버튼 이벤트
	$(".btn_area").click(function(e){
		e.preventDefault();
		var modal_id = "modal_area";
		var modal_url = "<c:url value='/boffice_noDeco/main/popupZone/MainPopupZoneSite.do' />";
		var modal_param = {};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/main/popupZone/MainPopupZoneForm.do' />";
		var modal_param = $("#MainPopupZoneVo").serializeObject();
		modal_param['mzIdx'] = "new";
		modal_param['code'] = $("#code").val();
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/main/popupZone/MainPopupZoneForm.do' />";
		var modal_param = $("#MainPopupZoneVo").serializeObject();
		modal_param['mzIdx'] = trIdx;
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//동작버튼
	$("#siteCd").change(function(){
		$("#searchSiteCd").val($(this).val());
		$(".searchListPage").submit();
	});
	$(".btn_sortUp").click(function(e){
		e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var trCode = $(this).parents("tr").eq(0).attr("data-code");
		var ajaxParam = {"mzIdx":trIdx, "code":trCode};
		ajaxActionMessage("<c:url value='/boffice/popupZone/MainPopupZoneSortUpAx.do'/>", ajaxParam, '', true);
	});
	$(".btn_sortDown").click(function(e){
		e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var trCode = $(this).parents("tr").eq(0).attr("data-code");
		var ajaxParam = {"mzIdx":trIdx, "code":trCode};
		ajaxActionMessage("<c:url value='/boffice/popupZone/MainPopupZoneSortDownAx.do'/>", ajaxParam, '', true);
	});
	$(".changeUse").change(function(){
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = {"mzIdx":trIdx, "mzUse":$(this).val()};
		ajaxActionMessage("/boffice/main/popupZone/MainPopupZoneMzUseChangeAx.do", ajaxParam, '', true);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section divGroup leftTree">
		<div class="left">
			<div class="treeTopBtn">
				<input type="button" value="사이트별 영역 관리" class="btn_inline btn_area">
			</div>
			<div class="siteSelect">
				<select id="siteCd" name="siteCd" title="사이트선택" class="w100p">
					<option value="">사이트선택</option>
					<c:forEach var="siteInfo" items="${siteList }">
					<option value="${siteInfo.siteCd}"<c:if test="${siteInfo.siteCd eq param.searchSiteCd}"> selected</c:if>>${siteInfo.siteNm}</option>
					</c:forEach>
				</select>
			</div>
			<div id="menuTree" class=" optionBar rows2">
				<ul>
					<c:choose>
						<c:when test="${fn:trim(param.searchSiteCd) eq ''}">
							<li>사이트를 선택해주세요</li>
						</c:when>
						<c:otherwise>
							<c:set var="itemList" value="${cmm:getCodeList('35000000')}" />
							<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
								<c:if test="${fn:indexOf(itemInfo.codeValue, param.searchSiteCd) > -1 }">
									<li id="<c:out value="${itemInfo.code}"/>"<c:if test="${itemInfo.code eq param.code}"> data-jstree='{"selected":true}'</c:if>><a href="#"><c:out value="${itemInfo.codeName}"/></a></li>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<div class="right">
			<div class="tableTitle">
				<h5 class="treePath">&nbsp;</h5>
			</div>
			<form:form commandName="MainPopupZoneVo" name="MainPopupZoneVo" method="post" action="/boffice/main/popupZone/MainPopupZoneList.do" class="searchListPage" onsubmit="return doSearch(this);">
			<form:hidden path="pageIndex" />
			<form:hidden path="mzIdx" />
			<form:hidden path="searchSiteCd" />
			<form:hidden path="code" />
			<div class="search">
				<table>
					<caption>검색테이블</caption>
					<tbody>
						<tr>
							<th scope="row"><label for="searchUse">사용유무</label></th>
							<td>
								<form:select path="searchUse">
									<form:option value="" label="전체" />
									<form:option value="Y" label="사용중" />
									<form:option value="N" label="종료" />
								</form:select>
							</td>
							<th scope="row"><label for="searchKeyword">제목 검색</label></th>
							<td><form:input path="searchKeyword" size="30" /></td>
						</tr>
					</tbody>
				</table>
				<button class="btn_inline btn_search">검색</button>
			</div>
			</form:form>
			
			<c:if test="${fn:trim(param.code) ne ''}">
			<div class="tableTitle">
				<!-- <div class="btnArea left">
					<a href="#" class="btn_inline">선택수정</a>
					<a href="#" class="btn_inline">선택삭제</a>
				</div> -->
				<div class="btnArea right">
					<a href="#" class="btn_inline btn_add">신규등록</a>
				</div>
			</div>
			
			<div class="tableBox">
				<table class="list">
					<caption>배너 목록</caption>
					<colgroup>
						<!-- <col class="w50"> -->
						<col class="w50">
						<col class="w70">
						<col class="w100">
						<col>
						<col class="w150">
						<col class="w70">
						<col class="w70">
					</colgroup>
					<thead>
						<tr>
							<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
							<th scope="col">순번</th>
							<th scope="col">사용여부</th>
							<th scope="col">이미지</th>
							<th scope="col">제목</th>
							<th scope="col">기간</th>
							<th scope="col">순위</th>
							<th scope="col">관리</th>
							<!-- <th scope="col">구분</th> -->
							<!-- <th scope="col">사이트</th> -->
						</tr>
					</thead>
					<tbody>
						<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }" />
						<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr data-idx="<c:out value='${result.mzIdx}' />" data-code="<c:out value='${result.code}' />">
							<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
							<td><c:out value="${n - status.index}" /></td>
							<td>
								<c:if test="${result.mzUse ne 'N'}">
									<select name="mzUse" class="changeUse">
										<option value="A" <c:if test="${result.mzUse eq 'A'}">selected="selected"</c:if>>상시게시</option>
										<option value="Y" <c:if test="${result.mzUse eq 'Y'}">selected="selected"</c:if>>사용중</option>
										<option value="N">종료</option>
									</select>
								</c:if>
								<c:if test="${result.mzUse eq 'N'}">
									<span class="captionText">종료</span>
								</c:if>
							</td>
							<td><div class="thumbImg"><img src="<c:url value="/common/files/ImageDownload.do?filePath=/upload/mainPopupZone/${result.mzFilename}" />" alt=""></div></td>
							<td class="left"><c:out value="${result.mzTitle}" /></td>
							<td><c:out value="${result.mzStartDate}" />~<br /><c:out value="${result.mzEndDate}" /></td>
							<td>
								<a href="#" class="btn_ss btn_sortUp">▲</a>
								<a href="#" class="btn_ss btn_sortDown">▼</a>
							</td>
							<td><a href="#" class="btn_inline btn_mod">수정</a></td>
						</tr>
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="7" class="empty"><spring:message code="common.nodata.msg" /></td>
						</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="paging">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
			</div>
			</c:if>
		</div>
	</div>
</body>
</html>