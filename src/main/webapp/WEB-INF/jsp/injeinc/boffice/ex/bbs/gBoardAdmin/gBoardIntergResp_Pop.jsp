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
<title>통합답변 입력/수정</title>
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

/* 통합답변 저장 */
function doSave() {
	
	 /* if( cm_is_empty($("input:radio[name='auditYn']:checked").val()) ){
		alert("답변상태를 선택해 주세요.");
		$("input:radio[name='auditYn']:checked").focus;
		return;
	} */
	if( cm_is_empty($("#mcPointTxt").val()) ){
		alert("통합요지를 작성해 주세요.");
		$("#mcPointTxt").focus;
		return;
	}
	if( cm_is_empty($("#contentClob").val()) ){
		alert("통합답변를 작성해 주세요.");
		$("#contentClob").focus;
		return;
	}
	
	if(!confirm("저장 하시겠습니까?")){
		return;
	}
	var options = {
		    dataType:'json',
		    url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardIntergRespSaveAx.do'/>",
		    success:function(data){
	    	window.opener.document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do'/>";
	    	window.opener.document.GBoardAdminVo.target = "";
	    	window.opener.document.GBoardAdminVo.submit();
		   	//location.reload();
		   	popClose();
		    }
		  };
  	$('#GBoardAdminVo').ajaxForm(options).submit();

}

//첨부파일 삭제
function doBbsFileRmvAx(cbIdx, bcIdx, mcIdx, fileNo){
	
	if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")){
		return;
	}
	var vHtml ="<input type='file' name='fileTagId' class='w90' id='fileTagId2'>"; 
	 $.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/gBoardAdmin/File_RmvAx.do'/>",
		data : {"cbIdx" : cbIdx
			   , "bcIdx" : bcIdx
			   , "fileNo" : fileNo
			   , "mcIdx" : mcIdx},
		dataType : "json",
		success:function(object){
			alert("삭제되었습니다.");
			$("#upFile"+fileNo).html( vHtml );
        	$("#fileRmv"+fileNo).hide();
		 },
        error: function(xhr,status,error){
        	alert(status);
        }
	});
}

function doFileMake(fileCnt){
	var vHtml ="";
	for(var i = 1; i <= parseInt(fileCnt); i++){
		vHtml += "<input type='file' name='fileTagId' class='w90' id='fileTagId'>";
	}
	$("#upFileSpan").html( vHtml );
}

//창 닫기
function popClose() {
	self.close();
}
//]]>
</script>
<script type="text/javascript">
$(window).load(function() {
	
	var fileCnt = '1';
	doFileMake(fileCnt);

});
</script>
</head>
<body style="background:none;">
	<div id="contents">
		<form id="GBoardAdminVo" name="GBoardAdminVo" method="post" action="" enctype="multipart/form-data" >
			<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${param.cbIdx}"/>" />
			<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value="${param.bcIdx}"/>" />
			<input type="hidden" id="mcIdx" name="mcIdx" value="<c:out value="${param.mcIdx}"/>" />
			<input type="hidden" id="gbnVal" name="gbnVal" value="<c:out value="${param.gbnVal}"/>" />
			
			<table summary="통합답변 입력/수정" class="view">
				<colgroup>
					<col width="35%"/>
					<col width="65%"/>
				</colgroup>
				<tbody>
					<%-- <tr>
						<th align="center"><h3><label for="auditYn">답변상태</label></h3></th>
						<td>
							<c:forEach items="${retrieveListCode}" var="retrieveListCode">
								<input type="radio" name="auditYn" id="auditYn" value="<c:out value="${retrieveListCode.code }"/>" <c:if test="${selectIntergRespMap.auditYn eq retrieveListCode.code}">checked="checked"</c:if> /><c:out value="${retrieveListCode.codeName }" />
							</c:forEach>
						</td>
					</tr> --%>
					<tr>
						<th align="center"><h3><label for="mcPointTxt">통합요지</label></h3></th>
						<td>
							<input type="text" name="mcPointTxt" id="mcPointTxt" class='w90' value="<c:out value="${selectIntergRespMap.mcPointTxt}"/>" />
						</td>
					</tr>
					<tr>
						<th align="center"><h3><label for="contentClob">통합답변</label></h3></th>
						<td><textarea name="contentClob" id="contentClob" style="width: 95%;" rows="7"><c:out value="${selectIntergRespMap.contentClob}"/></textarea></td>
					</tr>
					
					<c:if test="${fn:length(detailDeptFileList) > 0}">
						<tr>
							<th width="15%"><label for="fileTagId">첨부파일</label></th>
							<td width="85%" colspan="3">
								<c:forEach items="${detailDeptFileList}" var="detailDeptFileList">
								<c:if test="${selectIntergRespMap.mcIdx eq detailDeptFileList.mcIdx}">
								<%-- <a href="/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_fileDownload.do?bcIdx=<c:out value="${detailDeptFileList.bcIdx }" />&cbIdx=<c:out value="${detailDeptFileList.cbIdx }" />&fileNo=<c:out value="${detailDeptFileList.fileNo }" />" class="file">
								파일명 : <c:out value="${detailDeptFileList.orignlFileNm}" />&nbsp;&nbsp;&nbsp;[ <fmt:formatNumber value="${detailDeptFileList.fileSize}"></fmt:formatNumber> byte ]
								</a>
								<a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${detailDeptFileList.fileStreCours }' />&filename=<c:out value='${detailDeptFileList.streFileNm }' />" title="새창" class="view-direct">바로보기</a> --%>
								<p class="uploaded_file" id="fileRmv<c:out value='${detailDeptFileList.fileNo}'/>">
								<a href="/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_fileDownload.do?bcIdx=<c:out value="${detailDeptFileList.bcIdx }" />&cbIdx=<c:out value="${detailDeptFileList.cbIdx }" />&fileNo=<c:out value="${detailDeptFileList.fileNo }" />&mcIdx=<c:out value="${detailDeptFileList.mcIdx }" />" class="file">
								파일명 : <c:out value="${detailDeptFileList.orignlFileNm}" />&nbsp;&nbsp;&nbsp;[ <fmt:formatNumber value="${detailDeptFileList.fileSize}"></fmt:formatNumber> byte ]
								</a>
								<%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${detailDeptFileList.fileStreCours }' />&filename=<c:out value='${detailDeptFileList.streFileNm }' />" title="새창" class="view-direct">바로보기</a> --%>
								<a href="javascript:doBbsFileRmvAx('<c:out value="${detailDeptFileList.cbIdx}"/>','<c:out value="${detailDeptFileList.bcIdx}"/>','<c:out value="${detailDeptFileList.mcIdx}"/>','<c:out value="${detailDeptFileList.fileNo}"/>');" class="delete">삭제</a>
								<br/>
								</p>
								<span id="upFile<c:out value='${detailDeptFileList.fileNo}'/>"></span>
								</c:if>
								</c:forEach>
	
								<c:if test="${detailDeptFileList eq null}">
									<span id="upFileSpan"></span>
								</c:if>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${fn:length(detailDeptFileList) <= 0}">
						<tr>
							<th width="15%"><label for="fileTagId">첨부파일</label></th>
							<td width="85%" colspan="3"><span id="upFileSpan"></span></td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<div class="btn_zone" style="text-align:right;">
				<a href="javascript:doSave();" class="btn2">저장</a>
				<a href="javascript:popClose();" class="btn1">닫기</a>
			</div>
		</form>
	</div>
</body>
</html>
