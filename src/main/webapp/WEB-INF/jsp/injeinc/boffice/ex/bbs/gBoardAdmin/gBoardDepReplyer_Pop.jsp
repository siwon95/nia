<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>담당부서 지정</title>
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
//<![CDATA[
           
$(document).ready(function(){
	
});
           	
/* 복사 */
function doSave(cbIdx, bcIdx, mcIdx) {
	var mcReplyer = $("#mcReplyer").val();
	
	if( cm_is_empty(mcReplyer) ){
		alert("담당명을 입력해 주세요.");
		$("#mcReplyer").focus;
		return;
	}
	
	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardMcReplyer_RegPop.do'/>"
		, data : $("#GBoardAdminVo").serialize()
		, type : "POST"
		//, dataType: "json"
		, async : false
		, success: function(data) {
			alert("담당자가 지정되었습니다.");
			window.opener.document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do'/>";
			window.opener.document.GBoardAdminVo.target = "";
			window.opener.document.GBoardAdminVo.submit();
 		},error : function(code, msg, error) {
 			alert(status);
 		}
			
	});
	popClose();
}

//창 닫기
function popClose() {
	self.close();
}
//]]>
</script>
</head>
<body style="background:none;">
	<div id="contents">
		<form id="GBoardAdminVo" name="GBoardAdminVo" method="post" action="">
		<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${param.cbIdx}"/>" />
		<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value="${param.bcIdx}"/>" />
		<input type="hidden" id="mcIdx" name="mcIdx" value="<c:out value="${param.mcIdx}"/>" />
		<%-- <form:hidden path="cbIdx" />
		<form:hidden path="bcIdx" />
		<form:hidden path="mcIdx" /> --%>
		<input type="hidden" id="mode" name="mode" />
		<table summary="게시물 복사 선택" class="view">
			<colgroup>
				<col width="30%"/>
				<col width="70%"/>
			</colgroup>
			<tbody>
				<tr>
					<th align="center"><h3><label for="mcReplyer">담당자명</label></h3></th>
					<td>
						<input type="text" id="mcReplyer" name="mcReplyer" value="<c:out value="${param.mcReplyer}"/>" />
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_zone" style="text-align:right;">
			<a href="javascript:doSave('<c:out value="${param.cbIdx}"/>','<c:out value="${param.bcIdx}"/>','<c:out value="${param.mcIdx}"/>');" class="btn2">저장</a>
			<a href="javascript:popClose();" class="btn1">취소</a>
		</div>
		</form>
	</div>
</body>
</html>
