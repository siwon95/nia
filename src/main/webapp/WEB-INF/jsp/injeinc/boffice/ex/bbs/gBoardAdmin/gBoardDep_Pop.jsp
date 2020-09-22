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
           	
/* 부서지정 */
function doSave(cbIdx, bcIdx){
	
	var depCbIdx = $("#cdSelectBoxList_1").val();
	
	if( cm_is_empty(depCbIdx) ){
		alert("담당부서를 지정해 주세요.");
		$("#cdSelectBoxList_1").focus;
		return;
	}
	
	$("#cbIdx").val(cbIdx);
	$("#bcIdx").val(bcIdx);
	
	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardDep_RegPop.do'/>"
		//, data : param
		, data : $("#GBoardAdminVo").serialize()
		, type : "POST"
		, dataType : "json"
		, success : function(data) {
			alert("담당부서가 지정되었습니다.");
			window.opener.document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do'/>";
			window.opener.document.GBoardAdminVo.target = "";
			window.opener.document.GBoardAdminVo.submit();
			//window.opener.location.reload();
			popClose();
 		},error : function(code, msg, error) {
 			alert(status);
 		}
			
	});
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
		<%-- <form:hidden path="cbIdx" />
		<form:hidden path="bcIdx" />
		<form:hidden path="mode" /> --%>
		<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${param.cbIdx}"/>" />
		<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value="${param.bcIdx}"/>" />
		<input type="hidden" id="mode" name="mode" />
		<table summary="게시물 복사 선택" class="view">
			<colgroup>
				<col width="30%"/>
				<col width="70%"/>
			</colgroup>
			<tbody>
				<tr>
					<th align="center"><h3><label for="cdSelectBoxList_1">담당부서 지정</label></h3></th>
					<td>
						<select id="cdSelectBoxList_1" name="cdSelectBoxList" style="width: 300px;" onchange="chgholiday();">
							<option value="">민원 답변부서 선택</option>
							<c:forEach items="${cdSelectBoxList}" var="cdSelectBoxList_1">
								<option value="<c:out value="${cdSelectBoxList_1.cdIdx}"/>">
									<c:if test="${cdSelectBoxList_1.cdDepstep2 ne '00'}"></c:if>
									<c:if test="${cdSelectBoxList_1.cdDepstep3 ne '00'}"></c:if>
									<c:out value="${cdSelectBoxList_1.cdSubject}"/>
								</option>
							</c:forEach>
						</select>
						<br/><br/>
						<select id="cdSelectBoxList_2" name="cdSelectBoxList" style="width: 300px;">
							<option value="">추가 답변부서 선택</option>
							<c:forEach items="${cdSelectBoxList}" var="cdSelectBoxList_2">
								<option value="<c:out value="${cdSelectBoxList_2.cdIdx}"/>">
									<c:if test="${cdSelectBoxList_2.cdDepstep2 ne '00'}"></c:if>
									<c:if test="${cdSelectBoxList_2.cdDepstep3 ne '00'}"></c:if>
									<c:out value="${cdSelectBoxList_2.cdSubject}"/>
								</option>
							</c:forEach>
						</select>
						<br/><br/>
						<select id="cdSelectBoxList_3" name="cdSelectBoxList" style="width: 300px;">
							<option value="">추가 답변부서 선택</option>
							<c:forEach items="${cdSelectBoxList}" var="cdSelectBoxList_3">
								<option value="<c:out value="${cdSelectBoxList_3.cdIdx}"/>">
									<c:if test="${cdSelectBoxList_3.cdDepstep2 ne '00'}"></c:if>
									<c:if test="${cdSelectBoxList_3.cdDepstep3 ne '00'}"></c:if>
									<c:out value="${cdSelectBoxList_3.cdSubject}"/>
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_zone" style="text-align:right;">
			<a href="javascript:doSave('<c:out value="${param.cbIdx}"/>','<c:out value="${param.bcIdx}"/>');" class="btn2">부서지정</a>
			<a href="javascript:popClose();" class="btn1">취소</a>
		</div>
		</form>
	</div>
</body>
</html>
