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
<title>Basic Board List</title>
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
<script type="text/javascript" src="<c:url value='/crosseditor3_5/js/namo_scripteditor.js' />"></script>
<script type="text/javascript">

var JSAdd;

function AddJS(path){
	head = document.getElementsByTagName('HEAD')[0];
	if (JSAdd){ head.removeChild(JSAdd); }
	JSAdd = document.createElement('SCRIPT');
	JSAdd.innerHTML = path;
	head.appendChild(JSAdd);
}


/* 리스트 이동 */
function doList(){
	document.BoardAdminVo.pageIndex.value = 1;
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_list.do'/>";
	document.BoardAdminVo.submit();
}

function testaa(){
	var checkObj = $("input[type='file']");
	alert(checkObj.attr("id"));
	checkObj.attr("name", checkObj.attr("id"));
	alert(checkObj.attr("name"));
}

function doNotiYnChk(){
	var notiYnChk = document.getElementById("notiYn").checked;
	if(notiYnChk){
		document.getElementById("notiYnVal").value = "Y";
	}else{
		document.getElementById("notiYnVal").value = "N";
	}
}

function doBbsFileRmvAx(cbIdx ,bcIdx ,fileNo){
	
	if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")){
		return;
	}
		 var vHtml ="<input type='file' name='fileTagId' class='w90' id='fileTagId2'>"; 
	 $.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/gBoardAdmin/File_RmvAx.do'/>",
		data : {"cbIdx" : cbIdx
			   , "bcIdx" : bcIdx
			   , "fileNo" : fileNo},
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

$(window).load(function() {
	var vScript = "<c:out value='${resultMap.outScript}' />";
	vScript = vScript.replace(/&#039;/g, "'").replace(/quot;/g, '"').replace(/&lt;/g, "<").replace(/&gt;/g, ">");
	
	AddJS(vScript);
	
	var searchBbsGroup = "<c:out value="${searchBbsGroup}"/>";
	var searchBbs = "<c:out value="${searchBbs}"/>";
	
	document.getElementById("searchBbsGroup").value = searchBbsGroup;
	document.getElementById("searchBbs").value = searchBbs;
 	doDetailDataSet();
 	/* var clobContVal = CrossEditor.SetBodyValue($("#clobCont").val()); */
 	//var captionContVal = CrossEditor.SetBodyValue($("#captionCont").val());
	
});

</script>
<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
</style>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<div class="board board-list">
	<!-- 주소팝업사용시 무조건 만드셔야됨. -->
	<form id="form" name="form" method="post" style="visibility: hidden;">
		<input type="text" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2" />
		<input type="hidden" id="returnUrl" name="returnUrl" value="" />
	</form>
	<!-- //주소팝업사용시 무조건 만드셔야됨. -->	
	<form:form commandName="BoardAdminVo" name="BoardAdminVo" method="post" enctype="multipart/form-data" action="/boffice/ex/bbs/boardAdmin/boardAdmin_Reg.do">
		<form:hidden path="pageIndex" />
		<form:hidden path="cbIdx" />
		<form:hidden path="mode" />
		<%-- <form:hidden path="captionCont" /> --%>
		<form:hidden path="parentSeq" />
		<form:hidden path="answerStep" />
		<form:hidden path="answerDepth" />
		
		<form:hidden path="clobCont" />
		<form:hidden path="subContTemp" />
		
		<input type="hidden" name="searchBbsGroup" id="searchBbsGroup" value="<c:out value="${searchBbsGroup}"/>" />
		<input type="hidden" name="searchBbs" id="searchBbs" value="<c:out value="${searchBbs}"/>" />
		<input type="hidden" name="bcIdx" id="bcIdx" value="<c:out value="${boardAdminVo.bcIdx}"/>"/>
		<input type="hidden" name="fileTagId" id="fileTagId" />
		<input type="hidden" name="notiYnVal" id="notiYnVal" />
		<input type="hidden" name="ssNameVal" id="ssNameVal" value="<c:out value="${ssName}"/>" />
		
		<!-- 타이틀 -->
		<div class="title">
			<h2>통합게시물관리 &gt;</h2>
			<h3>통합게시물관리 글쓰기</h3>
		</div>
		<div class="linebox mgb10">
			<ul class="icon-dot">
				<li>글 내용에 표와 이미지를 올리시면 장애인차별금지법에 위반이 됩니다. 표와 이미지는 첨부로 올리십시오.</li>
				<li>게시글 올리기 전, 민원인의 상세정보(주민번호, 상세주소, 핸드폰번호, 차량번호 등)이 포함되어 있는지 다시 확인 하시기 바랍니다.<br>
				본문내용이나 첨부파일에 민원인의 상세정보를 숨김처리(***)하여 개인정보가 노출되지 않도록 하시기 바랍니다.</li>
			</ul>
		</div>

		<p class="required-guide"><span class="required">*</span> 표시된 항목은 <em>필수입력</em> 항목입니다.</p>
		<table class="write">
			<colgroup>
				<col style="width:15%">
				<col style="width:85%">
			</colgroup>
			<tbody>
			<tr>
				<th>위치</th>
				<td><c:out value="${searchBbsGroupText}"/> -> <c:out value="${searchBbsText}"/></td>
			</tr>
			<c:out value="${resultMap.outHtml}"/>
			<c:if test="${bbsTempletGbn eq '16050700'}">
			<tr>
				<th>공개여부</th>
				<td>
					<input type="radio" id="mwUsOpenYnN" name="mwUsOpenYn" value="N" />비공개
					<input type="radio" id="mwUsOpenYnY" name="mwUsOpenYn" value="Y" />공개
				</td>
			</tr>
			<tr>
				<th>답변여부</th>
				<td>
					<input type="radio" id="mwRYnY" name="mwRYn" value="Y" />답변요청
					<input type="radio" id="mwRYnN" name="mwRYn" value="N" />답변필요없음
				</td>
			</tr>
			<tr>
				<th>통보방법</th>
				<td>
					<input type="checkbox" id="mwRSmsYn" name="mwRSmsYn" value="S" />문자안내
					<input type="checkbox" id="mwRVmsYn" name="mwRVmsYn" value="V" />음성안내
					<input type="checkbox" id="mwREmailYn" name="mwREmailYn" value="E" />이메일안내
				</td>
			</tr>
			</c:if>
			<!-- 공지사항, 행사소식, 고시공고 -->
			<c:if test="${BoardAdminVo.cbIdx eq '57' || BoardAdminVo.cbIdx eq '58' || BoardAdminVo.cbIdx eq '59'}">
			<tr>
				<th>게시 홈페이지</th>
				<td>
					<select id="noticeGbn" name="noticeGbn" style="width: 250px;">
						<option value="Z">[구청홈페이지, <c:out value="${ssName}"/>]</option>
						<option value="A">[구청홈페이지]</option>
						<option value="B">[<c:out value="${ssName}"/>]</option>
					</select> (게재하고자 하는 홈페이지를 선택해주세요)
				</td>
			</tr>
			</c:if>
			<tr>
				<th>공지여부</th>
				<td>
					<input type="checkbox" name="notiYn" id="notiYn" onClick="javascript:doNotiYnChk();" /> 상단고정으로 사용
				</td>
			</tr>
			</tbody>
		</table><!-- //regist -->
		<div class="btn_zone">
			<input type="button" value="저장" class="btn2" onclick="doBbsFReg();">
			<input type="button" value="취소" class="btn1" onclick="history.back();">
			<!-- <a href="javascript:doList();" class="btn1">목록</a> -->
			<!-- <input type="button" value="testaa" class="btn1" onclick="javascript:testaa();"> -->
		</div>
	</form:form>
</div>
<!--  웹필터 수정 -->  
<%@ include file="/webfilter/inc/initCheckWebfilter.jsp"%>
<!--  웹필터 수정 -->
</body>
</html>
