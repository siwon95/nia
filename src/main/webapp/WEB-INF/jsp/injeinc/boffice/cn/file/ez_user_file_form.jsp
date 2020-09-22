<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.injeinc.common.util.EgovDateUtil" %>
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
           function doEzUserFileList() {
				var frm = document.EzUserFileVo;
				frm.action = "<c:url value='/boffice/cn/file/Ez_User_File_List.do' />";
				frm.submit();
			}
			
			function doEzUserFileReg() {
				var frm = document.EzUserFileVo;
				if (cm_check_empty($('input[name=title]').attr("title"), $('input[name=title]')) == false) {
					return;
				}
			
				frm.action = "/boffice/cn/file/Ez_User_File_Reg.do";	
				
				frm.submit();
			}
			
			function doEzUserFileMod() {
				var frm = document.EzUserFileVo;
				frm.action = "/boffice/cn/file/Ez_User_File_Mod.do";
				frm.submit();
			}
			
			function doEzUserFileDel() {
				if(confirm("삭제하시겠습니까?")){
					var frm = document.EzUserFileVo;
					frm.action = "/boffice/cn/file/Ez_User_File_Del.do";
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
				<span style="font-weight: bold;">파일업로드 관리</span><br />
<form:form commandName="EzUserFileVo"  name="EzUserFileVo" method="post" enctype="multipart/form-data">
	<form:hidden path="seq" />
				<table summary="" class="write" width="100%">
					<caption></caption>
					<colgroup>
						<col width="150px" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th><label for="title">제목</th>
							<td>
								<form:input path="title"  title="제목" size="80"/>
							</td>
						</tr>
						<tr>
							<th><label for="atchFileId">첨부파일</label></th>
							<td>
							<c:set var="fileCount" value="0"/>
							<input type="hidden" name="atchFileId" id="atchFileId" value="<c:out value="${EzUserFileVo.atchFileId}"/>"/>
							<c:forEach items="${ezFileVoList}" var="result" varStatus="status">
								<c:set var="fileCount" value="${status.index+1}"/>
 									<span id="fileView_<c:out value="${result.fileSn}"/>"><c:out value="${result.orignlFileNm}"/> <input type="button" value="삭제" class="btn" style="cursor:pointer;" onclick="file_delete_submit('<c:out value="${result.atchFileId}"/>','<c:out value="${result.fileSn}"/>')"/><br/></span>
 								</c:forEach>
<%
String formFileName = "upfile";
int viewCount = Integer.parseInt(pageContext.getAttribute("fileCount")+"");
int fileSize = 1;
fileSize = fileSize - viewCount;
%>
 <script type="text/javascript">
//<![CDATA[
	function file_delete_submit(inDelNum,inSn){
		var delFileStr =  "<input type=\"hidden\" name=\"del<%=formFileName%>Id\" value=\""+inDelNum+"\">";
		delFileStr += "<input type=\"hidden\" name=\"del<%=formFileName%>Sn\" value=\""+inSn+"\">";
		delFileStr += "<div style=\"padding:2px\"><input type=\"file\" name=\"<%=formFileName%>\" value=\""+inSn+"\"  size=\"40\"></div>";
		document.getElementById("fileView_"+inSn).innerHTML = delFileStr;
	}
//]]>
</script>
							<%for(int i=0;i<fileSize;i++){%>
									<div style="padding:2px"><input type="file" name="<%=formFileName%>" size="40"/></div>
							<%}%> 
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 버튼 -->
				
				<div class="btn_zone">
<c:choose>
	<c:when test="${EzUserFileVo.seq eq ''}"><a href="javascript:doEzUserFileReg();" class="btn2">저장</a></c:when>
	<c:otherwise>
					<a href="javascript:doEzUserFileMod();" class="btn2">수정</a>
					<a href="javascript:doEzUserFileDel();" class="btn1">삭제</a>
	</c:otherwise>
</c:choose>
					
					
					<a href="/boffice/cn/file/Ez_User_File_List.do" class="btn1">목록</a>
				</div>
</form:form>

	</div>

</body>
</html>
