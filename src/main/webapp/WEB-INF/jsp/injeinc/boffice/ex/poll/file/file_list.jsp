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
<title>Basic File Upload List</title>
<script type="text/javascript">

function doFileUpload(){
	document.FileVo.action = "/boffice/sy/file/File_Reg.do";
	document.FileVo.submit();
}

</script>
<script type="text/javascript">

$(window).load(function() {

});

</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="FileVo" name="FileVo" method="post" enctype="multipart/form-data">

	<!-- 컨텐츠 영역 -->
	<div id="contents">
		<table summary="" class="write">
			<caption></caption>
			<colgroup>
				<col width="20%" />
				<col width="20%" />
				<col width="*" />
			</colgroup>
			<tr>
				<th><label for="fileUpload">파일업로드</label></th>
				<td>
					<input type="file" name="fileUpload" id="fileUpload" size="40" />
				</td>
				<td>
					<a href="javascript:doFileUpload();" class="btn2">저장</a>
				</td>
			</tr>
		</table>
		<%-- <br/>
		<table class="write">
			<caption></caption>
			<colgroup>
				<col width="20%" />
				<col width="20%" />
				<col width="*" />
			</colgroup>
			<tr>
				<th>파일명</th>
				<th>파일사이즈</th>
				<th>파일경로</th>
			</tr>
			<tr>
				<th>ㅋㅋㅋ</th>
				<th>222</th>
				<th>ㅁㄴㅇㅁㄴㅇ</th>
			</tr>
		</table> --%>
	</div>
	<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
