<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>EzFile List</title>
<script type="text/javascript">
//<![CDATA[
//등록페이지
function doEzFileForm() {
	document.EzFileVo.action = "<c:url value='/boffice/cn/file/Ez_File_Form.do' />";
	document.EzFileVo.submit();
}

//뷰페이지
function doEzFileView(inIdx){
	document.EzFileVo.atchFileId.value = idx;
	document.EzFileVo.action = "<c:url value='/boffice/cn/file/Ez_File_View.do' />";
	document.EzFileVo.submit();
}

//페이징처리 
function doEzFilePag(pageIndex) {
	document.EzFileVo.pageIndex.value = pageIndex;
	document.EzFileVo.action = "<c:url value='/boffice/cn/file/Ez_File_List.do' />";
	document.EzFileVo.submit();
}

//리스트
function doEzFileList(pageIndex) {
	if(pageIndex == null) {
		document.EzFileVo.pageIndex.value = 1;	
	} else {
		document.EzFileVo.pageIndex.value = pageIndex;
	}
	
	document.EzFileVo.action = "<c:url value='/boffice/cn/file/Ez_File_List.do' />";
	document.EzFileVo.submit();
}

$(document).ready(function(){
	<c:if test="${not empty message}">
	alert("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	alert("<spring:message code="${param.message}" />");
	</c:if>
});

$(window).load(function() {
	cm_combo("#codeList", "<c:out value="${pageContext.request.contextPath}"/>");
	
});
//]]>
</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="EzFileVo" name="EzFileVo" method="post" >
<form:hidden path="pageIndex" />
<form:hidden path="atchFileId" />
	<!-- 컨텐츠 영역 -->
			<div id="contents">
				<fieldset class="date_search">
				<label for="searchCondition">사이트 유형</label>	
					<form:select path="searchCondition">
						<form:option value="" label="--선택하세요--" />
						<form:options items="${codeList}" itemValue="code" itemLabel="codeName" />
					</form:select>
					<span class="sfont">
					<label for="searchKeyword">사이트명</label>
					<form:input path="searchKeyword" size="30"/>
					<a href="javascript:doSiteList(1);" class="btn2">조회</a>
					</span>
				</fieldset>
				
				<table summary="" class="list1">
					<caption></caption>
					<colgroup>
						<col width="7%" />
						<col width="*" />
						<col width="20%" />
						<col width="20%" />
						<col width="20%" />
						<col width="20%" />
					</colgroup>
					<thead>
					<tr>
						<th>번호</th>
						<th>파일순번</th>
						<th>파일저장경로</th>
						<th>저장파일명</th>
						<th>원파일명</th>
						<th>파일확장자</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList}" var="result" varStatus="status">
					<tr>
						<td><c:out value="${status.count}" /></td>
						<td class="sbj"><a href="javascript:doEzFileView('<c:out value="${result.atchFileId}" />');"><c:out value="${result.fileSn}" /></a></td>						
						<td><c:out value="${result.fileStreCours}" /></td>						
						<td><c:out value="${result.streFileNm}" /></td>						
						<td><c:out value="${result.orignlFileNm}" /></td>						
						<td><c:out value="${result.fileExtsn}" /></td>						
					</tr>
					</c:forEach>
				
					</tbody>
				</table>
				
				<!--paginate -->
				<div class="paginate">
						<%-- <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doEzFilePag" /> --%>
				</div>
				<!--//paginate -->
				
				<!-- 버튼 -->
				<div class="btn_zone">
<!-- 				<a href="" class="btn1">취소하기</a> -->
					<a href="javascript:doEzFileForm();" class="btn2">등록</a>
				</div>
				<!-- //버튼 -->
		
			</div>
			<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
