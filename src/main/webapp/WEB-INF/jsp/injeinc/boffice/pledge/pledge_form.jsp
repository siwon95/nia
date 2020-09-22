<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="bbs" uri="http://cms.injeinc.co.kr/bbs"%>
<c:set var="editor" value="crosseditor${cmm:getGlobalProperties('editor')}"/>
<%
String editorYN = "Y";
%>
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
			if($("#clobCont").length > 0){
				CrossEditor = new NamoSE('clobCont');
				CrossEditor.params.Width = "100%";
				CrossEditor.params.UserLang = "auto";
				CrossEditor.params.FullScreen = false;
				CrossEditor.params.Css = "/css/cms/editor.css";
				CrossEditor.params.Menu = false;
				CrossEditor.params.ParentEditor = document.getElementById("clobContWrap");
				CrossEditor.EditorStart();
			}
			function OnInitCompleted(e){
				if($("#clobCont").length > 0){
					CrossEditor.SetBodyValue(document.getElementById("clobCont").value);
				}
			}
		</c:when>
		<c:otherwise>
			if($("#clobCont").length > 0){
				CKEDITOR.replace('clobCont');
			}
		</c:otherwise>
	</c:choose>
	
	//시도 선택시 하위 시군구 나오도록
	$("#plWiwId1").change(function(){
		$("#plWiwId2").find("option:not(:first)").remove();
    	var ajaxParam = {"searchPlWiwid1":this.value};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
    		if(data.result){
    			$("#plWiwId2").show();
    			if(data.plWiwidList2.length==0){
    				$("#plWiwId2").hide();
    				return;
    			}
    			$.each(data.plWiwidList2, function (index, item) {
    				$("#plWiwId2").append("<option value='"+item.wiwId+"'>"+item.wiwName+"</option>");
        		});
    		}
    	};
    	ajaxAction("<c:url value='/boffice_noDeco/pledge/WiwIdListAx.do'/>", ajaxParam, ajaxOption);
	});
	$("#selEmail").change(function(){
		if(this.value!=""){
			$("#emailCont2").val(this.value);
			$("#emailCont2").attr("readonly",true);
		}else{
			$("#emailCont2").val("");
			$("#emailCont2").attr("readonly",false);
			$("#emailCont2").focus();
		}
	});
	//버튼이벤트
	$(".btn_delFile").click(function(e){
    	e.preventDefault();
    	if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")){
    		return;
    	}
    	var ajaxParam = {"plIdx":$(this).attr("data-plIdx"),"streFileNm":$(this).attr("data-streFileNm")};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
    		alert(data.message);
    		if(data.result) {
    			var modal_id = "modal_mod";
    			var modal_url = "<c:url value='/boffice_nodeco/pledge/PledgeForm.do' />";
    			var modal_param = $(".modalContent #PledgeVo").serializeArray();
    			var modal_class = "wide"; //wide, small
    			ajaxModal(modal_id, modal_url, modal_param, modal_class);
    		}
    	};
    	ajaxAction("<c:url value='/common/pledge/PledgeFileRmvAx.do'/>", ajaxParam, ajaxOption);
    });
});
function doForm(f){
 	<c:choose>
		<c:when test="${editor eq 'crosseditor'}">	
			$("#clobCont").val(CrossEditor.GetBodyValue());
		</c:when>
		<c:otherwise>		
			$("#clobCont").val(CKEDITOR.instances["clobCont"].getData());
		</c:otherwise>
	</c:choose>

	$("#emailCont").val($("#emailCont1").val()+"@"+$("#emailCont2").val());
	$("#telCont").val($("#telCont1").val()+"-"+$("#telCont2").val()+"-"+$("#telCont3").val());
	
	for(i=0;i<$(f).find("tr").length;i++){
		var tr=$(f).find("tr");
		var is_required=tr.eq(i).find("span.required");
		var labelName=tr.eq(i).find("th").text().replace(is_required.text(),"");
		if(is_required.length > 0){
			var is_empty=false;
			tr.eq(i).find(":input").each(function(){
				if((!$(this).is("select") && $(this).val()=="") || ($(this).is("select") && $(this).find("option[value!='0']").length>0) && $(this).val()=="0"){
					is_empty=true;
				}
			});
			if(is_empty){
				alert(labelName+"을(를) 입력해 주세요");
				return false;
			}
		}
	}
	var tmp = $("#clobCont").val().replace(/[<][^>]*[>]/gi, ""); //태그제거
	tmp = tmp.replace(/\s/gi,"");  //공백제거
	tmp = tmp.replace(/&nbsp;/gi,"");  //공백제거
	if(tmp==""){
		alert("내용을(를) 입력해 주세요");
		return false;
	}
	if($("#actionkey").val() == "regist") {	
	    $(".modalContent #PledgeVo").attr("action", "PledgeReg.do");
	}else if($("#actionkey").val() == "modify") {
    	$(".modalContent #PledgeVo").attr("action", "PledgeMod.do");
	}
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->
</head>
<body>
	<!-- ============================== 모달영역 ============================== -->
	<div class="modalTitle">
		<h3>공약은행 <c:if test="${PledgeVo.actionKey eq 'regist'}">등록</c:if><c:if test="${PledgeVo.actionKey eq 'modify'}">수정</c:if></h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<fmt:setLocale value="ko_kr"/>
		<form:form commandName="PledgeVo" id="PledgeVo" name="PledgeVo" method="post" enctype="multipart/form-data" action="/boffice/pledge/PledgeReg.do" onsubmit="return doForm(this);">
		<input type="hidden" id="actionkey" name="actionKey" value="<c:out value="${PledgeVo.actionKey}"/>" />
		<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${PledgeVo.pageIndex}"/>" />
		<input type="hidden" id="pageUnit" name="pageUnit" value="<c:out value="${PledgeVo.pageUnit}"/>" />
		<input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value="${PledgeVo.searchCondition}"/>" />
		<input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value="${PledgeVo.searchKeyword}"/>" />
		<input type="hidden" id="searchStartDate" name="searchStartDate" value="<c:out value="${PledgeVo.searchStartDate}"/>" />
		<input type="hidden" id="searchEndDate" name="searchEndDate" value="<c:out value="${PledgeVo.searchEndDate}"/>" />
		<input type="hidden" id="plIdx" name="plIdx" value="<c:out value="${!empty PledgeVo.plIdx ? PledgeVo.plIdx : 0}" />"/>
		<input type="hidden" id="searchPlWiwid1" name="searchPlWiwid1" value="<c:out value="${PledgeVo.searchPlWiwid1}"/>" />
		<input type="hidden" id="searchPlWiwid2" name="searchPlWiwid2" value="<c:out value="${PledgeVo.searchPlWiwid2}"/>" />
		<input type="hidden" id="searchCateCont" name="searchCateCont" value="<c:out value="${PledgeVo.searchCateCont}"/>" />
		<div class="tableBox">
		<table class="form">
			<caption>공약은행 등록/수정 폼</caption>
			<colgroup>
				<col style="width:15%;">
				<col>
			</colgroup>
			<tbody>
				<tr><!-- 제목 -->
					<th scope="row"><span class='required'>*</span><label for="subCont">제목</label></th>
					<td>
						<input type="text" id="subCont" name="subCont" value="<c:out value="${PledgeVo.subCont}"/>" class="w100p" title="제목" />
					</td>
				</tr>
				<tr><!-- 공약구분 -->
					<th scope="row"><span class='required'>*</span><label for="subCont">공약구분</label></th>
					<td>
						<form:select path="cateCont" class="w100">
							<form:option value="0" label="선택"/>
							<c:forEach items="${plCateList}" var="result" >
								<form:option value="${result.code}" label="${result.codeName}"/>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr><!-- 지역 -->
					<th scope="row"><span class='required'>*</span><label for="subCont">지역</label></th>
					<td>
						<form:select path="plWiwId1" class="w100">
							<form:option value="0" label="선택"/>
							<c:forEach items="${plWiwidList1}" var="result" >
								<form:option value="${result.wiwId}" label="${result.wiwName}"/>
							</c:forEach>
						</form:select>
						<form:select path="plWiwId2" class="w100">
							<form:option value="0" label="선택"/>
							<c:forEach items="${plWiwidList2}" var="result" >
								<form:option value="${result.wiwId}" label="${result.wiwName}"/>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr><!-- 작성자 -->
					<th scope="row"><span class='required'>*</span><label for="regName">작성자</label></th>
					<td>
						<c:set var="regName" value="${PledgeVo.actionKey eq 'regist'? SesUserName : PledgeVo.regName}"/>
						<input type="hidden" id="regName" name="regName" value="<c:out value="${regName}"/>" class="w100p" title="작성자" />
						<c:out value="${regName}"/>
					</td>
				</tr>
				<tr><!-- 아이디 -->
					<th scope="row"><span class='required'>*</span><label for="regId">아이디</label></th>
					<td>
						<c:set var="regId" value="${PledgeVo.actionKey eq 'regist'?SesUserId:PledgeVo.regId}"/>
						<c:out value="${regId}"/>
					</td>
				</tr>
				<tr><!-- 연락처 -->
					<th scope="row"><span class='required'>*</span><label for="telCont1">연락처</label></th>
					<td>
						<c:set var="telArr" value="${cmm:split(PledgeVo.telCont, '-', 3)}" />
						<label for="telCont1" class="hidden">전화 앞번호</label> 
						<input type="text" id="telCont1" name="telCont1" class="onlyNum" value="<c:out value="${fn:length(telArr) > 0 ? telArr[0] : ''}"/>" maxlength="3" size="5" title="전화 앞번호" /> -
						<label for="telCont2" class="hidden">전화 앞번호</label> 
						<input type="text" id="telCont2" name="telCont2" class="onlyNum" value="<c:out value="${fn:length(telArr) > 0 ? telArr[1] : ''}"/>" maxlength="4" size="5" title="전화 중간번호" /> -
						<label for="telCont3" class="hidden">전화 앞번호</label> 
						<input type="text" id="telCont3" name="telCont3" class="onlyNum" value="<c:out value="${fn:length(telArr) > 0 ? telArr[2] : ''}"/>" maxlength="4" size="5" title="전화 뒷번호" />
						<input type="hidden" id="telCont" name="telCont" value="<c:out value="${PledgeVo.telCont}"/>" />
					</td>
				</tr>
				<tr><!-- 이메일 주소 -->
					<th scope="row"><span class='required'>*</span><label for="emailCont1">이메일 주소</label></th>
					<td>
						<c:set var="emailArr" value="${cmm:split(PledgeVo.emailCont, '@', 2)}" />
						<c:set var="emailCont1" value="${fn:length(emailArr) > 0 ? emailArr[0] : ''}" />
						<c:set var="emailCont2" value="${fn:length(emailArr) > 0 ? emailArr[1] : ''}" />
						<input type="text" id="emailCont1" name="emailCont1" value="<c:out value="${emailCont1}"/>" size="10" title="이메일" /> @ 
						<input type="text" id="emailCont2" name="emailCont2" value="<c:out value="${emailCont2}"/>" size="10" title="이메일" />
						<select id="selEmail" name="selEmail" class="w100">
							<option value="">직접입력</option>
							<c:forEach items="${emailList}" var="result" >
								<option value="${result.codeName}"<c:if test="${result.codeName==emailCont2}"> selected</c:if>>${result.codeName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="emailCont" name="emailCont" value="<c:out value="${PledgeVo.emailCont}"/>" />
					</td>
				</tr>
				<c:forEach begin="0" varStatus="status" end="${PledgeVo.maxFileCnt-1}">
				<tr>
					<c:if test="${status.first}">
					<th scope="row" rowspan="<c:out value="${PledgeVo.maxFileCnt}" />"><label for="emailCont1">첨부파일</label></th>
					</c:if>
					<td>
						<c:if test="${fn:length(fileList) > status.index }">
						<p>
							<a href="/common/pledge/Download.do?plIdx=<c:out value="${fileList[status.index].plIdx}"/>&streFileNm=<c:out value="${fileList[status.index].streFileNm}"/>" class="file">
								<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileList[status.index].fileExtsn}"/>.gif" alt="<c:out value="${fileList[status.index].fileExtsn}"/> 아이콘" /> <c:out value="${fileList[status.index].orignlFileNm}" />  [<c:out value="${bbs:byteCalculation(fileList[status.index].fileSize)}" />]
							</a>
							<a href="#" data-plIdx="<c:out value="${fileList[status.index].plIdx}"/>" data-streFileNm="<c:out value="${fileList[status.index].streFileNm}"/>" class="btn_inline btn_delFile">삭제</a>
						</p> 
						</c:if>
						<c:if test="${fn:length(fileList) <= status.index }">
						<input type="file" id="dataFile<c:out value="${status.index}"/>" name="dataFile<c:out value="${status.index}"/>" class="w200" title="첨부파일<c:out value="${status.index}"/>" />
						<c:if test="${status.last}">
							<br /><span class="captionText">※파일 1개당 최대 용량은<c:out value="${PledgeVo.fileMaxSize}"/>MB 입니다.</span>
						</c:if> 
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<tr><!-- 내용 -->
					<td colspan="2">
						<textarea id="clobCont" name="clobCont" style="width:100%;height:300px;">${PledgeVo.clobCont}</textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<input type="submit" value="확인" class="btn_m on">
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
	</form:form>
</div>
</body>
</html>