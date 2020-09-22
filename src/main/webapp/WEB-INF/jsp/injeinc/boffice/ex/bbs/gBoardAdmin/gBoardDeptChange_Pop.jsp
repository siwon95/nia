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
<title>담당부서변경</title>
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
           
function doDeptMod(cbIdx, bcIdx, mcIdx){
	
	var depCbIdx = $("#cdSelectBoxList").val();
	
	if( cm_is_empty(depCbIdx) ){
		alert("담당부서를 지정해 주세요.");
		$("#cdSelectBoxList").focus;
		return;
	}
	
	//var param = "cbIdx="+cbIdx+"&bcIdx="+bcIdx+"&mcIdx="+mcIdx+"&depCbIdx="+depCbIdx+"&"+;
    
	$("#cbIdx").val(cbIdx);
	$("#bcIdx").val(bcIdx);
	$("#mcIdx").val(mcIdx);
	
	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardUpdateDep_RegPop.do'/>"
		//, data : param
		, data : $("#GBoardAdminVo").serialize()
		, type : "POST"
		, dataType : "json"
		, success : function(data) {
			alert("담당부서가 변경되었습니다.");
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
			<form:hidden path="mcIdx" /> --%>
			<input type="hidden" id="cbIdx" name="cbIdx" />
			<input type="hidden" id="bcIdx" name="bcIdx" />
			<input type="hidden" id="mcIdx" name="mcIdx" />
			
			<table summary="담당부서변경" class="view">
				<colgroup>
					<col width="35%"/>
					<col width="65%"/>
				</colgroup>
				<tbody>
					<tr>
						<th align="center"><h3><label for="cdSelectBoxList">부서선택</label></h3></th>
						<td>
							<select id="cdSelectBoxList" name="cdSelectBoxList" style="width: 300px;">
								<option value="">민원 답변부서 선택</option>
								<c:forEach items="${cdSelectBoxList}" var="cdSelectBoxList">
									<option value="<c:out value="${cdSelectBoxList.cdSubject}"/>" <c:if test="${cdSelectBoxList.cdSubject eq param.mcDeptCd}">selected="selected"</c:if>>
										<c:if test="${cdSelectBoxList.cdDepstep2 ne '00'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
										<c:if test="${cdSelectBoxList.cdDepstep3 ne '00'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
										<c:out value="${cdSelectBoxList.cdSubject}"/>
									
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th align="center"><h3><label for="mcDeptHist">부서지정이력</label></h3></th>
						<td><textarea name="mcDeptHist" id="mcDeptHist" style="width: 95%;" rows="3"></textarea></td>
					</tr>
				</tbody>
			</table>
			<div class="btn_zone" style="text-align:right;">
				<a href="javascript:doDeptMod('<c:out value="${param.cbIdx}"/>','<c:out value="${param.bcIdx}"/>','<c:out value="${param.mcIdx}"/>');" class="btn2">저장</a>
				<a href="javascript:popClose();" class="btn1">닫기</a>
			</div>
		</form>
	</div>
</body>
</html>
