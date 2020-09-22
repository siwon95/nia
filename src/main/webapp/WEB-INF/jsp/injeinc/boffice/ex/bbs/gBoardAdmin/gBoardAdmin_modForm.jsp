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
<title>Basic Board List</title>
<script type="text/javascript">

/* 우편번호 팝업 */
function openAddrSearch() {
	var from = document.form;
	doJusoPop(from);
}

function setAddrValue(addr1, addr2, zip) {
	$("#addrCont1").val(zip);
	$("#addrCont2").val(addr1);
	$("#addrCont3").val(addr2);
	$("#addrCont3").focus();
}

/* 수정(저장) */
function doUpdate(cbIdx, bcIdx, modGbn){
	$("#ansCompCont").val($("input[name='ansCompContArr']:checkbox:checked").map(function () {return this.value;}).get());
	document.GBoardAdminVo.modGbn.value = modGbn;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_mod2.do'/>";
	document.GBoardAdminVo.submit();
}

/* 취소(상세페이지) */
function doDetail(cbIdx, bcIdx){
	document.GBoardAdminVo.cbIdx.value = cbIdx;
	document.GBoardAdminVo.bcIdx.value = bcIdx;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do'/>";
	document.GBoardAdminVo.submit();
}

function doMwAdOpenYnChk(val, rVal){
	document.GBoardAdminVo.mwOpenynCont.value = rVal;
	document.GBoardAdminVo.mwAdOpenYn.value = val;
}

function doBbsFileRmvAx(cbIdx ,bcIdx ,fileNo){
	
	if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")){
		return;
	}
		 var vHtml ="<input type='file' name='fileTagId' class='w90' id='fileTagId2'>"; 
	 $.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/gBoardAdmin/File_RmvAx.do'/>",
		data : {"cbIdx" : cbIdx
			   , "bcIdx" : bcIdx
			   , "fileNo" : fileNo},
		dataType : "json",
		success:function(object){
			alert("삭제되었습니다.");
			$("#upFile"+fileNo).html( vHtml );
        	$("#fileRmv"+fileNo).hide();
		 },
        error: function(xhr,status,error){
        	alert(status);
        }
	});
}

function doFileMake(fileCnt){
	var vHtml ="";
	for(var i = 1; i <= parseInt(fileCnt); i++){
		vHtml += "<input type='file' name='fileTagId' class='w90' id='fileTagId'>";
	}
	$("#upFileSpan").html( vHtml );
}

$(window).load(function() {
	
	var fileCnt = '<c:out value='${fileCnt}'/>';
	doFileMake(fileCnt);

});
</script>
<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
</style>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form id="form" name="form" method="post" style="visibility: hidden;">
	<input type="text" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2"/>
	<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
</form>
<form:form commandName="GBoardAdminVo" name="GBoardAdminVo" method="post" enctype="multipart/form-data" >
<form:hidden path="cbIdx" />
<form:hidden path="bcIdx" />
<form:hidden path="modGbn" />
<div class="board board-list">
	<table summary="" class="write">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="*" />
		</colgroup>
		
		<tbody>
		<tr>
			<th>글번호</th>
			<td><c:out value="${detailMap.bcIdx}"/></td>
		</tr>
		<tr>
			<th><label for="subCont">제목</label></th>
			<td>
				<input type="text" id="subCont" name="subCont" size="100" value="<c:out value="${detailMap.subCont}"/>" />
			</td>
		</tr>
		<tr>
			<th><label for="subCont">작성자</label></th>
			<td><c:out value="${detailMap.nameCont}"/></td>
		</tr>
		<tr>
			<th><label for="addrCont1">주소</label></th>
			<td>
				<input type="text" id="addrCont1" name="addrCont1" size="4" readonly value="<c:out value="${detailMap.addrCont1}"/>" />
				&nbsp;&nbsp;<a href="javascript:openAddrSearch();" class="btn2">주소검색</a><br/>
				<input type="text" id="addrCont2" name="addrCont2" size="40" value="<c:out value="${detailMap.addrCont2}"/>" />&nbsp;<br/>
				<input type="text" id="addrCont3" name="addrCont3" size="40" value="<c:out value="${detailMap.addrCont3}"/>" />
			</td>
		</tr>
		<tr>
			<th><label for="phoneCont1">이동전화</label></th>
			<td>
				<input type="text" id="phoneCont1" name="phoneCont1" size="10" maxlength="3" value="<c:out value="${detailMap.phoneCont1}"/>" />&nbsp;-&nbsp;
				<input type="text" id="phoneCont2" name="phoneCont2" size="10" maxlength="4" value="<c:out value="${detailMap.phoneCont2}"/>" />&nbsp;-&nbsp;
				<input type="text" id="phoneCont3" name="phoneCont3" size="10" maxlength="4" value="<c:out value="${detailMap.phoneCont3}"/>" />
			</td>
		</tr>
		<tr>
			<th><label for="telCont1">연락처</label></th>
			<td>
				<input type="text" id="telCont1" name="telCont1" size="10" maxlength="3" value="<c:out value="${detailMap.telCont1}"/>" />&nbsp;-&nbsp;
				<input type="text" id="telCont2" name="telCont2" size="10" maxlength="4" value="<c:out value="${detailMap.telCont2}"/>" />&nbsp;-&nbsp;
				<input type="text" id="telCont3" name="telCont3" size="10" maxlength="4" value="<c:out value="${detailMap.telCont3}"/>" />
			</td>
		</tr>
		<tr>
			<th><label for="emailCont">이메일</label></th>
			<td>
				<input type="text" id="emailCont" name="emailCont" size="150" value="<c:out value="${detailMap.emailCont}"/>" />
			</td>
		</tr>
		<tr>
			<th><label for="clobCont">내용</label></th>
			<td>
				<textarea id="clobCont" name="clobCont" title="clob 컨텐츠" rows="10" cols="50"  class="w90 board_input"><c:out value="${detailMap.clobCont}"/></textarea>
			</td>
		</tr>
		<tr>
			<th><label for="openYnCont">공개여부</label></th>
			<td>
				<input type="radio" id="openYnCont1" name="openYnCont" value="21000100" <c:if test="${detailMap.openYnCont eq '21000100'}">checked="checked"</c:if> />비공개&nbsp;&nbsp;
				<input type="radio" id="openYnCont2" name="openYnCont" value="21000200" <c:if test="${detailMap.openYnCont eq '21000200'}">checked="checked"</c:if> />공개
			</td>
		</tr>
		<tr>
			<th><label for="ansYnCont">답변여부</label></th>
			<td>
				<input type="radio" id="ansYnCont1" name="ansYnCont" value="22000100" <c:if test="${detailMap.ansYnCont eq '22000100'}">checked="checked"</c:if> />답변요청&nbsp;&nbsp;
				<input type="radio" id="ansYnCont2" name="ansYnCont" value="22000200" <c:if test="${detailMap.ansYnCont eq '22000200'}">checked="checked"</c:if> />답변요청없음
			</td>
		</tr>
		<tr>
			<th><label for="ansCompContArr1">답변여부알림</label></th>
			<td>
				<input type="checkbox" id="ansCompContArr1" name="ansCompContArr" value="23000100" <c:if test="${fn:indexOf(detailMap.ansCompCont, '23000100') > -1}">checked="checked"</c:if> />문자안내&nbsp;&nbsp;
				<input type="checkbox" id="ansCompContArr2" name="ansCompContArr" value="23000300" <c:if test="${fn:indexOf(detailMap.ansCompCont, '23000300') > -1}">checked="checked"</c:if> />이메일안내
				<input type="hidden" id="ansCompCont" name="ansCompCont" value="" />
			</td>
		</tr>
		<tr>
			<th><label for="mwAdOpenYn1">관리자권한비공개</label></th>
			<td>
				<input type="radio" id="mwAdOpenYn1" name="mwAdOpenYn" value="Y" <c:if test="${detailMap.mwAdOpenYn eq 'Y'}">checked="checked"</c:if> />사용&nbsp;&nbsp;
				<input type="radio" id="mwAdOpenYn2" name="mwAdOpenYn" value="N" <c:if test="${detailMap.mwAdOpenYn eq 'N'}">checked="checked"</c:if> />미사용
			</td>
		</tr>
		<c:choose>
			<c:when test="${detailFileListSize > 0}">
			<tr>
				<th><label for="fileTagId2">첨부파일</label></th>
				<td>
					<c:forEach items="${detailFileList}" var="detailFileList">
					<p class="uploaded_file" id="fileRmv<c:out value='${detailFileList.fileNo}'/>">
					<a href="/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_fileDownload.do?bcIdx=<c:out value="${detailFileList.bcIdx}" />&cbIdx=<c:out value="${detailFileList.cbIdx }" />&fileNo=<c:out value="${detailFileList.fileNo }" />" class="file">
					<c:out value="${detailFileList.orignlFileNm}" />&nbsp;&nbsp;&nbsp;[<fmt:formatNumber value="${detailFileList.fileSize}"></fmt:formatNumber> byte]
					</a>
					<%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${detailFileList.fileStreCours }' />&filename=<c:out value='${detailFileList.streFileNm }' />" title="새창" class="view-direct">바로보기</a> --%>
					<a href="javascript:doBbsFileRmvAx('<c:out value="${detailFileList.cbIdx}"/>','<c:out value="${detailFileList.bcIdx}"/>','<c:out value="${detailFileList.fileNo}"/>');" class="delete">삭제</a>
					</p>
					<span id="upFile<c:out value='${detailFileList.fileNo}'/>"></span>
					<br/>
					</c:forEach>
				</td>
			</tr>	
			</c:when>
			<c:otherwise>
			<tr>
				<th><label for="fileTagId">첨부파일</label></th>
				<td><span id="upFileSpan"></span></td>
			</tr>	
			</c:otherwise>
		</c:choose>
		
		</tbody>
	</table>
	<div class="btn_zone">
		<a href="javascript:doUpdate('<c:out value="${gBoardAdminVo.cbIdx }"/>', '<c:out value="${gBoardAdminVo.bcIdx}"/>', 'U');" class="btn2">수정</a>
		<a href="javascript:doDetail('<c:out value="${gBoardAdminVo.cbIdx }"/>', '<c:out value="${gBoardAdminVo.bcIdx}"/>');" class="btn2">취소</a>
	</div>	
</div>
</form:form>
<!--  웹필터 수정 -->  
<%@ include file="/webfilter/inc/initCheckWebfilter.jsp"%>
<!--  웹필터 수정 -->
</body>
</html>
