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
<title>답변기한 연기</title>
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
	$("input[id=mcDelayDay], input[id=mcDelayDay]").datepicker();
	$("input[id=mcDelayDay], input[id=mcDelayDay]").mask("9999-99-99");
    $("input[id=mcDelayDay], input[id=mcDelayDay]").datepicker( "option", "dateFormat", "yy-mm-dd" );
});

function doSelectDelay(val){
	$("#mcDelayRsn").val(val);
}

function doDelayDtDel(){
	$("#selectMcDelayDay").val('');
	$("#mcDelayRsn").val('');
	$("#mcDelayDay").val('');
}

function doDelayDt(){
	$(".ui-datepicker-trigger").click();
}

/* 연기사유 저장 */
function doDelayDtMod() {
	
	var mcDelayRsn = $("#mcDelayRsn").val();
	var mcDelayDay = $("#mcDelayDay").val();
	
	
	if( cm_is_empty(mcDelayRsn) ){
		alert("연기사유를 작성해 주세요.");
		$("#mcDelayRsn").focus;
		return;
	}
	if( cm_is_empty(mcDelayDay) ){
		alert("연기할 답변기한을 작성해 주세요.");
		$(".ui-datepicker-trigger").click();
		return;
	}
	
	if(!confirm("저장 하시겠습니까?")){
		return;
	}
	
	  $.ajax({
		type: "POST",
		url: "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardDelayDtModAx.do'/>",
		data : $("#GBoardAdminVo").serialize(),
		dataType : "json",
		success:function(object){
			alert("저장 되었습니다.");
			//window.opener.location.reload();
			window.opener.document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do'/>";
			window.opener.document.GBoardAdminVo.target = "";
			window.opener.document.GBoardAdminVo.submit();
			popClose();
		 },
       error: function(xhr,status,error){
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
			<form:hidden path="mcIdx" /> --%>
			<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${param.cbIdx}"/>" />
			<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value="${param.bcIdx}"/>" />
			<input type="hidden" id="mcIdx" name="mcIdx" value="<c:out value="${param.mcIdx}"/>" />
			
			
			<table summary="답변기한 연기" class="view">
				<colgroup>
					<col width="35%"/>
					<col width="65%"/>
				</colgroup>
				<tbody>
					<tr>
						<th align="center"><h3><label for="selectMcDelayDay">연기사유 선택</label></h3></th>
						<td>
							<select name="selectMcDelayDay" id="selectMcDelayDay" onchange="javascript:doSelectDelay(this.value);" style="width: 200px;">
								<option value="">직접인력</option>
								<option value="현장확인검토">현장확인검토</option>
								<option value="법률 검토">법률 검토</option>
								<option value="타기관 협의">타기관 협의</option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="center"><h3><label for="mcDelayRsn">연기사유</label></h3></th>
						<td><textarea name="mcDelayRsn" id="mcDelayRsn" style="width: 95%;" rows="3"></textarea></td>
					</tr>
					<tr>
						<th align="center"><h3><label for="mcDelayDay">연기할 답변기한</label></h3></th>
						<td>
							<span class="cal_in" ><input id="mcDelayDay" name="mcDelayDay" size="8" title="연기할 답변기한" onclick="doDelayDt();" readonly="readonly" /></span>
							<a href="javascript:doDelayDtDel();" class="btn2">답변기한 삭제</a>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_zone" style="text-align:right;">
				<a href="javascript:doDelayDtMod();" class="btn2">저장</a>
				<a href="javascript:popClose();" class="btn1">닫기</a>
			</div>
		</form>
	</div>
</body>
</html>
