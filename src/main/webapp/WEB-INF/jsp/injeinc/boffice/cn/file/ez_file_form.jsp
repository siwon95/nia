<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>통합관리시스템</title>
<link href="/css/admin.css" rel="stylesheet" type="text/css" />
<link href="/css/site/mnt/board.css" rel="stylesheet" type="text/css" />
<link href="/css/site/mnt/layout.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.timepicker.css" rel="stylesheet" type="text/css" />
<link href="/css/style.min.css" rel="stylesheet" type="text/css" />
<link href="/js/vakata-jstree-5bece58/dist/themes/default/style.min.css"	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/js/vakata-jstree-5bece58/dist/jquery.js"></script>
<script type="text/javascript" src="/js/vakata-jstree-5bece58/dist/jstree.min.js"></script>

<script type="text/javascript" src="/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.plugin.js"></script>
<script type="text/javascript" src="/js/common.js"></script>

<!-- page head start -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
//<![CDATA[
           function doEzFileList() {
				var frm = document.EzFileVo;
				frm.action = "<c:url value='/boffice/cn/file/Ez_File_List.do' />";
				frm.submit();
			}
			
			function doEzFileReg() {
				
				var frm = document.EzFileVo;
				if (cm_check_empty($('input[name=fileSn]').attr("title"), $('input[name=fileSn]')) == false) {
					return;
				}
				
				if (cm_check_empty($('input[name=fileStreCours]').attr("title"), $('input[name=fileStreCours]')) == false) {
					return;
				}
				
				if (cm_check_empty($('input[name=streFileNm]').attr("title"), $('input[name=streFileNm]')) == false) {
					return;
				}
				
				if (cm_check_empty($('input[name=orignlFileNm]').attr("title"), $('input[name=orignlFileNm]')) == false) {
					return;
				}
				
				if (cm_check_empty($('input[name=fileExtsn]').attr("title"), $('input[name=fileExtsn]')) == false) {
					return;
				}
				
				if (cm_check_empty($('input[name=fileCn]').attr("title"), $('input[name=fileCn]')) == false) {
					return;
				}
				
				if (cm_check_empty($('input[name=fileSize]').attr("title"), $('input[name=fileSize]')) == false) {
					return;
				}
				
				frm.action = "/boffice/cn/file/Ez_File_Reg.do";	
				
				frm.submit();
			}
			
			function doEzFileMod() {
				var frm = document.EzFileVo;
				frm.action = "/boffice/cn/file/Ez_File_Mod.do";
				frm.submit();
			}
			
			function doEzFileDel() {
				if(confirm("삭제하시겠습니까?")){
					var frm = document.EzFileVo;
					frm.action = "/boffice/cn/file/Ez_File_Del.do";
					frm.submit();
				}
			}
//]]>
</script>
</head>
<body>
		<div style="width: 100%">
			<!-- 컨텐츠 영역 -->
			<div id="contents">
				<span style="font-weight: bold;">컨텐츠 관리</span><br />
<form:form commandName="EzFileVo"  name="EzFileVo" method="post" >
	<form:hidden path="atchFileId" />
				<table summary="" class="write" width="100%">
					<caption></caption>
					<colgroup>
						<col width="150px" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th><label for="atchFileId">첨부파일ID</label></th>
							<td>
								<form:input path="atchFileId" title="첨부파일ID"/>
							</td>
						</tr>
						<tr>
							<th><label for="fileSn">파일순번</label></th>
							<td>
								<form:input path="fileSn" title="파일순번"/>
							</td>
						</tr>
						<tr>
							<th><label for="fileStreCours">파일저장경로</label></th>
							<td>
								<form:input path="fileStreCours" title="파일저장경로"/>
							</td>
						</tr>
						<tr>
							<th><label for="streFileNm">저장파일명</label></th>
							<td>
								<form:input path="streFileNm" title="저장파일명"/>
							</td>
						</tr>
						<tr>
							<th><label for="orignlFileNm">원파일명</label></th>
							<td>
								<form:input path="orignlFileNm" title="원파일명"/>
							</td>
						</tr>
						<tr>
							<th><label for="fileExtsn">파일확장자</label></th>
							<td>
								<form:input path="fileExtsn" title="파일확장자"/>
							</td>
						</tr>
						<tr>
							<th><label for="atchFileId">파일내용</label></th>
							<td>
								<form:input path="fileCn"  title="파일내용"/>
							</td>
						</tr>
						<tr>
							<th><label for="atchFileId">파일크기</label></th>
							<td>
								<form:input path="fileSize"  title="파일크기"/>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 버튼 -->
				
				<div class="btn_zone">
					<a href="javascript:doEzFileReg();" class="btn2">저장</a>
					<a href="javascript:doEzFileMod();" class="btn2">수정</a>
					<a href="javascript:doEzFileDel();" class="btn1">삭제</a>
					<a href="javascript:doEzFileClose();" class="btn1">목록</a>
				</div>
</form:form>

	</div>

</body>
</html>
