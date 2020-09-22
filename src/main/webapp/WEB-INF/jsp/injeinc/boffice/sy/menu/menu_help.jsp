<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<c:set var="editor" value="${cmm:getGlobalProperties('editor')}"/>
<%-- ------------------------------------------------------------
- 제목 : 컨텐츠관리 > 매뉴얼등록
- 파일명 : menu_help.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<c:import url="/layout/cms/head.jsp" ></c:import>

<!-- 페이지 전용 스타일/스크립트 -->
<c:choose>
	<c:when test="${editor eq 'crosseditor'}">
		<script src="/plugin/crosseditor/js/namo_scripteditor.js"></script>
	</c:when>
	<c:otherwise>
		<script src="/plugin/ckeditor/ckeditor.js"></script>
	</c:otherwise>
</c:choose>
<script>
var CrossEditor;
$(function(){
	<c:choose>
		<c:when test="${editor eq 'crosseditor'}">
			CrossEditor = new NamoSE('mmHelp');
			CrossEditor.params.Width = "100%";
			CrossEditor.params.UserLang = "auto";
			CrossEditor.params.FullScreen = false;
			CrossEditor.params.Menu = false;
			CrossEditor.params.ParentEditor = document.getElementById("mmHelpWrap");
			CrossEditor.EditorStart();
		</c:when>
		<c:otherwise>
			CKEDITOR.replace('mmHelp',{"height":300});
		</c:otherwise>
	</c:choose>
});
function OnInitCompleted(e){
	CrossEditor.SetBodyValue(document.getElementById("mmHelp").value);
}
function doMenuUpdate() {
	var frm = document.MgrMenuVo;
	<c:choose>
		<c:when test="${editor eq 'crosseditor'}">
			$("#mmHelp").val(CrossEditor.GetBodyValue());
		</c:when>
		<c:otherwise>
			$("#mmHelp").val(CKEDITOR.instances["mmHelp"].getData());
		</c:otherwise>
	</c:choose>
	frm.action = "/boffice_noDeco/sy/menu/MgrMenuHelpMod.do";
	frm.submit();
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<form:form commandName="MgrMenuVo" id="MgrMenuVo" name="MgrMenuVo" method="post">
	<form:hidden path="mmCd" />
	<table class="form">
	<caption></caption>
	<colgroup>
		<col style="width:150px;">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th>메뉴명</th>
			<td><c:out value="${MgrMenuVo.mmName}"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="mmHelpWrap">
				<textarea id="mmHelp" name="mmHelp" title="BODY 컨텐츠" rows="60" class="<c:out value='${editor}' />">${MgrMenuVo.mmHelp}</textarea>
				</div>
			</td>
		</tr>
	</tbody>
	</table>
	<div class="btnArea">
		<a href="javascript:doMenuUpdate();" class="btn_m on">저장</a>
	</div>
	</form:form>
	
</body>
</html>



