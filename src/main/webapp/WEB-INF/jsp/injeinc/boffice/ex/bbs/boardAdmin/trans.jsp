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
<title>민원글 삭제 및 삭제 사유</title>
<link href="<c:url value='/css/admin.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/board.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/layout.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery.timepicker.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.min.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.1.10.2.js' />"></script>
<script type="text/javascript">
//<![CDATA[
	
function doMwRmv(cbIdx,bcIdx) {
	 var delRsnCd = $('input:radio[name="delRsnCd"]:checked').val();
	 
	  $.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/boardAdmin/Mw_RmvAx.do'/>",
		data : {"cbIdx" : cbIdx
			   , "bcIdx" : bcIdx
			   , "delRsnCd" : delRsnCd
			   },
		dataType : "json",
		success:function(object){
			alert("삭제되었습니다.");
			popClose();
			opener.parent.location.reload();
		 },
        error: function(xhr,status,error){
        	alert(status);
        }
	});
}

/* function changeGroup() {
	$("#BigWasteCategoryVo").attr("action", "<c:url value='/bigWaste/BigWasteCategorySelectPop.do' />").submit();
} */

//창 닫기
function popClose() {
	self.close();
}
//]]>
</script>
</head>
<body style="background:none;">
	<div id="contents">
		<form id="BoardAdminVo" name="BoardAdminVo" method="post" action="">
		<table summary="민원글 삭제 및 삭제 사유 선택" class="view">
			<colgroup>
				<col width="100%"/>
			</colgroup>
			<tbody>
				<tr>
					<td align="center"><h3>선택된 민원글을 이관합니다.</h3></td>
				</tr>
				<tr>
					<td>
						<ul>
							<c:forEach var="delRsnList" items="${delRsnList}">
								<li><input type="radio" name="delRsnCd" value="<c:out value="${delRsnList.delRsnCd }" />"/><c:out value="${delRsnList.delRsnTxt }" /></li>
							</c:forEach>
						</ul>
						<%-- <select id="searchGroup" name="searchGroup" onchange="changeGroup();">
							<option value="">구분선택</option>
							<c:forEach var="delRsnList" items="${delRsnList}">
							<option value="${delRsnList.bcGroup}" <c:if test="${BigWasteCategoryVo.searchGroup eq groupInfo.bcGroup}">selected="selected"</c:if>>${groupInfo.bcGroup}</option>
							</c:forEach>
						</select> --%>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_zone" style="text-align:center;">
			<a href="javascript:doMwRmv('<c:out value="${boardAdminVo.cbIdx }" />','<c:out value="${boardAdminVo.bcIdx }" />');" class="btn2">삭제</a>
			<a href="javascript:popClose();" class="btn1">취소</a>
		</div>
		</form>
	</div>
</body>
</html>
