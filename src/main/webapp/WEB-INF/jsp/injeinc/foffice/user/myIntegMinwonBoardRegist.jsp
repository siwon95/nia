<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript">

 var JSAdd;

function AddJS(path){
	head = document.getElementsByTagName('HEAD')[0];
	if (JSAdd){ head.removeChild(JSAdd); }
	JSAdd = document.createElement('SCRIPT');
	JSAdd.innerHTML = path;
	head.appendChild(JSAdd);
}

$(window).load(function() {
	var vScript = "<c:out value='${resultMap.outScript}' />";
		
	vScript = vScript.replace(/&#039;/g, "'").replace(/quot;/g, '"').replace(/&lt;/g, "<").replace(/&gt;/g, ">");
	
	console.log(vScript);
	AddJS(vScript);
	
	doDetailDataSet();
	
});


function doBbsFileRmvAx(cbIdx ,bcIdx ,fileNo){
	
	if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")){
		return;
	}
		 var vHtml ="<input type='file' name='fileTagId' class='w90' id='fileTagId2'>"; 
	 $.ajax({
		type: "GET",
		url: "/site/seocho/ex/bbs/File_RmvAx.do'/>",
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

</script>

<div class="board">
	<p class="required-guide">
		<span class="required">*</span> 표시된 항목은 <em>필수입력</em> 항목입니다.
	</p>
	<!-- 주소팝업사용시 무조건 만드셔야됨. -->
	<form id="form" name="form" method="post" style="visibility: hidden;">
		<input type="hidden" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2" />
		<input type="hidden" id="returnUrl" name="returnUrl" value="" />
	</form>
	<!-- //주소팝업사용시 무조건 만드셔야됨. -->
	<form:form commandName="bbsFVo" name="bbsFVo" method="post" enctype="multipart/form-data" action="/site/seocho/foffice/user/MyIntegMinwonBoardRegProc.do">
		<form:hidden path="cbIdx" />
		<form:hidden path="bcIdx" />
		<form:hidden path="mode" />
		<form:hidden path="ssId" />
		<form:hidden path="parentSeq" />
		<form:hidden path="answerStep" />
		<form:hidden path="answerDepth" />
		
		<table class="regist">
			<caption><c:out value="${bbsFVo.cbName }" /> 등록폼</caption>
			<colgroup>
				<col style="width: 15%">
				<col style="width: 85%">
			</colgroup>
			<tbody>
			
			<c:out value="${resultMap.outHtml}"/>

			</tbody>
		</table>
		<!-- //regist -->
	</form:form>
	<div class="regist-agree">
		<label><span><input type="checkbox" id="userChk"></span> 위 글 등록을 위해 기입하신 <em>개인정보수집 및 이용</em>에 동의합니다.</label>
	</div>
	<div class="btns">
		<input type="button" value="취소" onclick="history.back();" class="type2">
		<input type="button" value="저장" onclick="doBbsFReg();" class="type3">
			
	</div>
</div>
<!-- //board -->
</html>





