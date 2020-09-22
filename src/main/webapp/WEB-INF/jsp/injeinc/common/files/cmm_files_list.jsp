<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%-- ------------------------------------------------------------
- 제목 : 파일업로드관리
- 파일명 : cmm_files_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
var modalActionType = "";
$(function(){ 
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		modalOpen("#modal_add");
	});
	$(".btn_save").click(function(e){
		e.preventDefault();
		modalActionType = "regist";
		$("#CmmFilesVo").submit();
	});
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		var cfIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var cfGroup = $(this).parents("tr").eq(0).attr("data-group");
		var cfRename = $(this).parents("tr").eq(0).attr("data-rename");
		$("#cfIdx").val(cfIdx);
		$("#cfGroup").val(cfGroup);
		$("#cfRename").val(cfRename);
		modalActionType = "delete";
		$("#CmmFilesVo").submit();
	});
	$(".btn_urlCopy").click(function(e){
		e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var objId = "fileUrl_"+trIdx;
		copyToClipboard(objId);
	});
});

var searchFunc = function(f){
	if(modalActionType == "delete"){
		f.action = "<c:url value='/boffice/common/files/CmmFilesRmv.do'/>";
	}else if(modalActionType == "regist"){
		if ($("#fileUpload").val() == "") {
			alert("파일을 선택해 주십시오.");
			$("#fileUpload").focus();
			return false;
		}
		f.action = "<c:url value='/boffice/common/files/CmmFilesReg.do'/>";
	}
	return true;
};
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<c:set var="siteCd" value="${fn:trim(param.siteCd) eq ''?'yangcheon':(fn:trim(param.siteCd))}" />
	<div class="section">
		<form:form commandName="CmmFilesVo" name="CmmFilesVo" method="post" enctype="multipart/form-data" class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<form:hidden path="cfIdx" />
		<form:hidden path="cfGroup" />
		<form:hidden path="cfRename" />
		<div class="search">
			<div class="right">
				<select name="siteCd" onchange="doCmmFilesPag('1')">
					<c:set var="siteList" value="${cmm:getSiteList()}" />
					<c:forEach var="siteInfo" items="${siteList}" varStatus="status">
						<option value="<c:out value="${siteInfo.siteCd}"/>" <c:if test="${siteCd eq siteInfo.siteCd}">selected</c:if>><c:out value="${siteInfo.siteNm }" /></option>
					</c:forEach>
				</select>
				<form:select path="searchCondition">
					<form:option value="1" label="파일명" />
					<form:option value="2" label="변환된 파일명" />
					<form:option value="3" label="파일ID" />
					<form:option value="4" label="제목" />
				</form:select> <form:input path="searchKeyword" size="20" class="w150" />
				<button class="btn_inline btn_search">검색</button>
			</div>
		</div>
			
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
				<caption>파일업로드 목록</caption>
				<colgroup>
					<!-- <col class="w50"> -->
					<col class="w50">
					<col>
					<col>
					<col class="w80">
					<col class="w80">
					<col class="w140">
					<col class="w220">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">순번</th>
						<th scope="col">파일명</th>
						<th scope="col">제목</th>
						<th scope="col">크기</th>
						<th scope="col">파일형식</th>
						<th scope="col">등록일</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }" />
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr data-idx="<c:out value="${result.cfIdx}"/>" data-group="<c:out value="${result.cfGroup}"/>" data-rename="<c:out value="${result.cfRename}"/>">
							<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
							<td><c:out value="${n - status.index}" /></td>
							<td class="left">
								<img src="/images/egovframework/com/cmm/fileicon/new/<c:out value="${result.cfFormat}"/>.png" alt="<c:out value="${result.cfFormat}"/> 아이콘" onerror="this.src='/images/egovframework/com/cmm/fileicon/new/text.png';" />
								<c:choose>
									<c:when test="${fn:length(result.cfName)>20}">
										<c:out value="${fn:substring(result.cfName,0,20)}" />
										<c:out value="${fn:substring(result.cfName,20,fn:length(result.cfName))}" />
									</c:when>
									<c:otherwise>
										<c:out value="${result.cfName}" />
									</c:otherwise>
								</c:choose>
							</td>
							<td class="left">
								<c:choose>
									<c:when test="${fn:length(result.subCont)>25}">
										<c:out value="${fn:substring(result.subCont,0,25)}" />
										<c:out value="${fn:substring(result.subCont,25,fn:length(result.subCont))}" />
									</c:when>
									<c:otherwise>
										<c:out value="${result.subCont}" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${not empty result.cfSize}">
										<fmt:formatNumber value="${result.cfSize/1024}" pattern="#,###" type="number"/> MB
									</c:when>
									<c:otherwise>
										0 MB
									</c:otherwise>
								</c:choose>
							</td>
							<td><c:out value="${result.cfFormat}"/></td>
							<td>
								<fmt:parseDate value="${result.regDt}" var="regDt" pattern="yyyy-MM-dd HH:mm:ss.S"/>
								<fmt:formatDate var="regDt" value="${regDt}" pattern="yyyy-MM-dd HH:mm:ss"/>
								<c:out value="${regDt}" />
							</td>
							<td>
								<c:set var="req" value="${pageContext.request}" />
								<c:set var="baseURL" value="${fn:replace(req.requestURL, fn:substring(req.requestURI, 1, fn:length(req.requestURI)), req.contextPath)}" />			
							 
								<input type="text" id="filePath_<c:out value="${result.cfIdx}"/>" title="파일위치" class="soundOnly" value="<c:out value="${result.cfPath}" /><c:out value="${result.cfRename}" />">
								<input type="text" id="fileUrl_<c:out value="${result.cfIdx}"/>" title="파일URL" class="soundOnly" value="<c:out value="${baseURL}" />common/files/Download.do?cfIdx=<c:out value="${result.cfIdx}" />&amp;cfGroup=<c:out value="${result.cfGroup}" />&amp;cfRename=<c:out value="${result.cfRename}" />">
								<a href="/common/files/Download.do?cfIdx=<c:out value="${result.cfIdx}" />&amp;cfGroup=<c:out value="${result.cfGroup}" />&amp;cfRename=<c:out value="${result.cfRename}" />" class="btn_inline on" target="_blank" title="새창열림">다운로드</a>
								<a href="#" class="btn_inline on btn_urlCopy">URL복사</a>
								<c:if test="${sessionScope['SesUserPermCd'] eq '05010000' || sessionScope['SesUserId'] eq result.regId}">
								<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
								</c:if>
							</td>
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
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doCmmFilesPag" />
		</div>

		<!-- ============================== 모달영역 ============================== -->
		<div id="modal_add" class="modalWrap small">
			<div class="modalTitle">
				<h3>파일등록</h3>
				<a href="#" class="btn_modalClose">모달팝업닫기</a>
			</div>
			<div class="modalContent">
				<div class="tableBox">
					<table class="form">
						<caption>파일업로드</caption>
						<colgroup>
							<col class="w20p">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">제목</th>
								<td><input type="text" name="subCont" id="subCont" size="100" maxlength="400" class="required w100p"></td>
							</tr>
							<tr>
								<th scope="row">파일업로드</th>
								<td><input type="file" name="fileUpload" id="fileUpload" size="40"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btnArea">
					<a href="#" class="btn_m on btn_save">확인</a>
					<a href="#" class="btn_m btn_modalClose">취소</a>
				</div>
			</div>
		</div>
		<!-- ============================== //모달영역 ============================== -->

	</form:form>
	</div>
</body>
</html>
