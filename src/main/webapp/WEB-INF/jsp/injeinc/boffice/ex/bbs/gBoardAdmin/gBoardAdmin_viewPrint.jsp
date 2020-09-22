<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Basic Board List</title>
<link href="<c:url value='/css/admin.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/board.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/layout.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery.timepicker.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.min.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.1.10.2.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery-ui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.plugin.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
<script type="text/javascript">

function PrintPage(){
	window.print();
}

</script>

<script type="text/javascript">
$(document).ready(function() {
	PrintPage();
});

</script>

<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
</style>
</head>
<body style="text-align:left; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="GBoardAdminVo" name="GBoardAdminVo" method="post" >
<form:hidden path="cbIdx" />
<form:hidden path="bcIdx" />
<form:hidden path="mcIdx" />
<form:hidden path="gbnVal" />
<form:hidden path="mcDeptCd" />
<form:hidden path="mcReplyer" />
<form:hidden path="modGbn" />
<form:hidden path="transCbIdx" />
<input type="hidden" id="openYnCont" name="openYnCont" value="<c:out value="${detailMap.openYnCont}"/>" />
<input type="hidden" id="mode" name="mode" value="<c:out value="${param.mode}"/>" />
<c:set var="chiefName" value=""/>
<div class="board board-list">
	<table summary="" class="write">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="35%" />
		</colgroup>
		
		<tbody>
			<tr>
				<th>글번호</th>
				<td colspan="3"><c:out value="${detailMap.bcIdx}"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3"><c:out value="${detailMap.subCont}"/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td colspan="3"><c:out value="${detailMap.nameCont}"/></td>
			</tr>
			<tr>
				<th>주소</th>
				<td colspan="3">
				<c:out value="${detailMap.addrCont1}"/><br/>
				<c:out value="${detailMap.addrCont2}"/>&nbsp;<c:out value="${detailMap.addrCont3}"/>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><c:out value="${detailMap.emailCont}"/></td>
				<th>조회수</th>
				<td><c:out value="${detailMap.countCont}"/></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>
				<c:out value="${detailMap.telCont1}"/> - <c:out value="${detailMap.telCont2}"/> - <c:out value="${detailMap.telCont3}"/>
				</td>
				<th>이동전화</th>
				<td>
				<c:out value="${detailMap.phoneCont1}"/> - <c:out value="${detailMap.phoneCont2}"/> - <c:out value="${detailMap.phoneCont3}"/>
				</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td><c:out value="${detailMap.regDt}"/></td>
				<th>수정일</th>
				<td><c:out value="${detailMap.modDt}"/></td>
			</tr>
		</tbody>
	</table>
	<table summary="" class="write">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="85%" />
		</colgroup>
		<tbody>
		<tr>
			<th colspan="2">내용</th>
		</tr>
		<tr>
			<td colspan="2"><c:out value="${cmm:outClobCont(detailMap.clobCont)}"/></td>
		</tr>
		
		<c:if test="${detailFileList ne null || detailFileList ne ''}">
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach items="${detailFileList}" var="detailFileList">
				<c:if test="${detailFileList.mcIdx eq null}">
				<a href="/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_fileDownload.do?bcIdx=<c:out value="${detailFileList.bcIdx }" />&cbIdx=<c:out value="${detailFileList.cbIdx }" />&fileNo=<c:out value="${detailFileList.fileNo }" />" class="file">
				파일명 : <c:out value="${detailFileList.orignlFileNm}" />&nbsp;&nbsp;&nbsp;[ <fmt:formatNumber value="${detailFileList.fileSize}"></fmt:formatNumber> byte ]
				</a>
				<%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${detailFileList.fileStreCours }' />&filename=<c:out value='${detailFileList.streFileNm }' />" title="새창" class="view-direct">바로보기</a> --%>
				<br/>
				</c:if>
				</c:forEach>
			</td>
		</tr>	
		</c:if>
		</tbody>
	</table>
</div>
</form:form>
</body>
</html>
