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
<title>EzUserFile List</title>
<script type="text/javascript">
//<![CDATA[
//등록페이지
function doEzUserFileForm() {
	document.EzUserFileVo.action = "<c:url value='/boffice/cn/file/Ez_User_File_Form.do' />";
	document.EzUserFileVo.seq.value = "";
	document.EzUserFileVo.submit();
}

//뷰페이지
function doEzUserFileView(inIdx){

	document.EzUserFileVo.seq.value = inIdx;
	document.EzUserFileVo.action = "<c:url value='/boffice/cn/file/Ez_User_File_Form.do' />";
	document.EzUserFileVo.submit();
}

//페이징처리 
function doEzUserFilePag(pageIndex) {
	document.EzUserFileVo.pageIndex.value = pageIndex;
	document.EzUserFileVo.action = "<c:url value='/boffice/cn/file/Ez_User_File_List.do' />";
	document.EzUserFileVo.submit();
}

//페이징처리 
function doSearch() {
	document.EzUserFileVo.action = "<c:url value='/boffice/cn/file/Ez_User_File_List.do' />";
	document.EzUserFileVo.submit();
}

//리스트
function doEzUserFileList(pageIndex) {
	if(pageIndex == null) {
		document.EzUserFileVo.pageIndex.value = 1;	
	} else {
		document.EzUserFileVo.pageIndex.value = pageIndex;
	}
	
	document.EzUserFileVo.action = "<c:url value='/boffice/cn/file/Ez_User_File_List.do' />";
	document.EzUserFileVo.submit();
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
	//cm_combo("#codeList", "${pageContext.request.contextPath}");
	
});
function fn_download(inAtchFileId,inFileSn){
	document.location.href="/site/FileDown.do?atchFileId="+inAtchFileId+"&fileSn="+inFileSn;
}
//]]>
</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="EzUserFileVo" name="EzUserFileVo" method="post" >
<form:hidden path="pageIndex" />
<form:hidden path="seq" />
	<!-- 컨텐츠 영역 -->
			<div id="contents">
				<fieldset class="date_search">
					<label for="title" class="hidden">제목</label>
					<span class="sfont">
					<form:input path="title" size="30"/>
					<a href="javascript:doSearch()" class="btn2">조회</a>
					</span>
				</fieldset>
				
				<table summary="" class="list1">
					<caption></caption>
					<colgroup>
						<col width="7%" />
						<col width="*" />
						<col width="40%" />
						<col width="20%" />
					</colgroup>
					<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>링크URL</th>
						<th>파일</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList}" var="result" varStatus="status">
						<tr>
							<td><c:out value="${status.count}" /></td>
							<td class="sbj"><a href="javascript:doEzUserFileView('<c:out value="${result.seq}" />');"><c:out value="${result.title}" /></a></td>						
							<td>/site/FileDown.do?atchFileId=<c:out value="${result.atchFileId}" />&fileSn=0</td>						
							<td><input type="button" value="다운로드" class="btn" style="cursor:pointer;" onclick="fn_download('<c:out value="${result.atchFileId}"/>', '0'); return false;"/></td>						
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<!--paginate -->
				<div class="paginate">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doEzUserFilePag" /> 
				</div>
				<!--//paginate -->
				
				<!-- 버튼 -->
				<div class="btn_zone">
<!-- 				<a href="" class="btn1">취소하기</a> -->
					<a href="javascript:doEzUserFileForm();" class="btn2">등록</a>
				</div>
				<!-- //버튼 -->
		
			</div>
			<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
