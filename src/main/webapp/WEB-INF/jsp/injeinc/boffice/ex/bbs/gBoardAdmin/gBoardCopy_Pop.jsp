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
<script type="text/javascript" src="<c:url value='/js/jquery/jquery-ui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.plugin.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
<script type="text/javascript">
//<![CDATA[
           
$(document).ready(function(){
	doSecondSelect();
});
           	
/* 검색범위 second selectBox 조회 */
function doSecondSelect(val) {
	  $.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/gBoardAdmin/secondSelectListAx.do'/>",
		data : {"cbUprCd" : val},
		dataType : "json",
		success:function(data){
			var vHtml ="";
			$(data.boardTgtSecondList).each(function(index, element){
				vHtml += "<option value="+element.cbIdx+">"+ element.cbName +"</option>";
			});
			if(data.boardTgtSecondList.length <= 0){
				vHtml = "<option value=''>::: 선택하세요 :::</option>";
			}
			$("#searchBbs").html( vHtml );
		 },
        error: function(xhr,status,error){
        	alert(status);
        }
	});
}

/* 복사 */
function doCopy(cbIdx, bcIdx, openYn) {
	var copyCbIdx = $("#searchBbs").val();
	
	if( cm_is_empty(copyCbIdx) ){
		alert("복사할 게시판을 선택해 주세요.");
		$("#searchBbs").focus;
		return;
	}
	
	if(openYn == "21000100"){
		if(!confirm("비공개 게시물은 복사시  노출되지 않아\n2차 민원 발생 요지가 될 수 있습니다.\n게시물을 복사하시겠습니까?")){
			return;
		}
	}
	
	
	
	if(!confirm("복사 하시겠습니까?")){
		return;
	}	
	
	$.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/gBoardAdmin/copyBoardAx.do'/>",
		data : {"cbIdx" : cbIdx
			  , "bcIdx" : bcIdx
			  , "copyCbIdx" : copyCbIdx
			   },
		dataType : "json",
		success:function(object){
			alert("복사 되었습니다.");
			popClose();
			//opener.parent.location.reload();
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
		<form id="gBoardAdminVo" name="gBoardAdminVo" method="post" action="">
		<table summary="게시물 복사 선택" class="view">
			<colgroup>
				<col width="100%"/>
			</colgroup>
			<tbody>
				<tr>
					<td align="center"><h3><label for="searchBbsGroup">선택된 게시물을 복사합니다.</label></h3></td>
				</tr>
				<tr>
					<td>
						<select name="searchBbsGroup" id="searchBbsGroup" onchange="javascript:doSecondSelect(this.value);" style="width: 200px;">
							<option value="">::: 선택하세요 :::</option>
							<c:forEach items="${boardTgtGroupList}" var="boardTgtGroupList" varStatus="status">
							<option value="<c:out value="${boardTgtGroupList.cbCd}" />"><c:out value="${boardTgtGroupList.cbName}" /></option>
							</c:forEach>
						</select>
						<select name="searchBbs" id="searchBbs" style="width: 200px;">
							<!-- <option>::: 선택하세요 :::</option> -->
						</select>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_zone" style="text-align:center;">
			<a href="javascript:doCopy('<c:out value="${gBoardAdminVo.cbIdx}" />','<c:out value="${gBoardAdminVo.bcIdx}" />','<c:out value="${param.mwOpenynCont}"/>');" class="btn2">복사</a>
			<a href="javascript:popClose();" class="btn1">취소</a>
		</div>
		</form>
	</div>
</body>
</html>
