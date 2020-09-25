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
<c:set var="editor" value="${cmm:getGlobalProperties('editor')}"/>
<c:set var="editorYN" value="${BoardVo.editorYn}" />
<%-- ------------------------------------------------------------
- 제목 : 게시물관리
- 파일명 : bbs_content_view.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<c:choose>
	<c:when test="${editor eq 'crosseditor'}">
		<script src="/plugin/crosseditor/js/namo_scripteditor.js"></script>
	</c:when>
	<c:otherwise>
		<script src="/plugin/ckeditor/ckeditor.js"></script>
	</c:otherwise>
</c:choose>
<script>
var CrossEditor1;
var CrossEditor2;
var mutifileyn = false;
$(function() {	
	<c:forEach var="propertyInfo" items="${propertylist}">		
		<c:choose>			
			<c:when test="${propertyInfo.labelPropGbn eq '16021300'}">
				//$('#fileupload').attr('id','bbsFVo');				
			    mutifileyn = true;
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${editorYN eq 'Y'}">
		<c:choose>
			<c:when test="${editor eq 'crosseditor'}">
				if($("#clobCont").length > 0){
					CrossEditor1 = new NamoSE('clobCont');
					CrossEditor1.params.Width = "100%";
					CrossEditor1.params.UserLang = "auto";
					CrossEditor1.params.FullScreen = false;
					CrossEditor1.params.Css = "/css/"+$("#mgrSiteCd").val()+"/editor.css";
					CrossEditor1.params.Menu = false;
					CrossEditor1.params.ParentEditor = document.getElementById("clobContWrap");
					CrossEditor1.EditorStart();
				}
				if($("#clobCont2").length > 0){
					CrossEditor2 = new NamoSE('clobCont2');
					CrossEditor2.params.Width = "100%";
					CrossEditor2.params.UserLang = "auto";
					CrossEditor2.params.FullScreen = false;
					CrossEditor1.params.Css = "/css/"+$("#mgrSiteCd").val()+"/editor.css";
					CrossEditor2.params.Menu = false;
					CrossEditor2.params.ParentEditor = document.getElementById("clobContWrap2");
					CrossEditor2.EditorStart();
				}
				function OnInitCompleted(e){
					if($("#clobCont").length > 0){
						CrossEditor1.SetBodyValue(document.getElementById("clobCont").value);
					}
					if($("#clobCont2").length > 0){
						CrossEditor2.SetBodyValue(document.getElementById("clobCont2").value);
					}
				}
			</c:when>
			<c:otherwise>
				if($("#clobCont").length > 0){
					CKEDITOR.replace('clobCont');
				}
				if($("#clobCont2").length > 0){
					CKEDITOR.replace('clobCont2');
				}
			</c:otherwise>
		</c:choose>
	</c:if>
    
    if(mutifileyn){
    	$('.modalContent form[name=BbsContentVo]').attr('id','fileupload');
		$('.modalContent #fileupload').attr('name','fileupload');
		$('.modalContent #fileupload').attr('commandName','BbsContentVo');
		$('.modalContent #fileupload').attr('method','POST');
		$('.modalContent #fileupload').attr('enctype','multipart/form-data');
		$('.modalContent #fileupload').attr('action','/boffice/ex/board/File_Upload.do'); 
		
		var data = "<c:out value='${multijson}'/>";
		if(data != null && data != ""){
			data = data.replace(/&#039;/g, "'").replace(/&#034;/g, '"').replace(/quot;/g, '"').replace(/&lt;/g, "<").replace(/&gt;/g, ">");
			var arr = JSON.parse(data);
			list = data;		
		    //라인 추가
		    for(var i=0; i < arr.length;i++){    	
		    	$('.files').append(tmpl("template-download",arr[i]));	
		    	if(i == 0){
		    		$('#drag').attr("style","display:none");
		    	}
		    }
		}
    }else{    	
    	$('.modalContent #fileupload').attr('id','BbsContentVo');
		$(".modalContent #BbsContentVo").attr('name','BbsContentVo');
		$(".modalContent #BbsContentVo").attr('commandName','BbsContentVo');
		$(".modalContent #BbsContentVo").attr('method','POST');
		$(".modalContent #BbsContentVo").attr('enctype','multipart/form-data');
		$(".modalContent #BbsContentVo").attr('action','BbsContentReg.do'); 
    }
	
    //버튼이벤트
    $(".btn_searchNas").click(function(e){
    	e.preventDefault();
    	nasWin = window.open("/boffice_noDeco/ex/board/nasSearch.do","nasWin","width=800,height=800,scrollbars=yes");
    	nasWin.focus();
    });
    $(".btn_searchZip").click(function(e){
    	e.preventDefault();
    	$("#addrTarget").val($(this).attr("data-target"));
    	var url = "/common/addrSearch/jusoPopup.jsp";
    	var addr_pop = window.open(url,"pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
    	addr_pop.focus();
    });
    $(".btn_delFile").click(function(e){
    	e.preventDefault();
    	if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")){
    		return;
    	}
    	var ajaxParam = {"bcIdx":$(this).attr("data-bcIdx"),"cbIdx":$(this).attr("data-cbIdx"),"streFileNm":$(this).attr("data-streFileNm")};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
    		alert(data.message);
    		if(data.result) {
    			if(mutifileyn){
    				$("#fileupload").attr("action", "<c:url value='BbsContentForm.do' />").submit();
    			}else{
    				$(".modalContent #BbsContentVo").attr("action", "<c:url value='BbsContentForm.do' />").submit();
    			}
    		}
    	};
    	ajaxAction("<c:url value='/common/board/ContentFileRmvAx.do'/>", ajaxParam, ajaxOption);
    });
    
    //입력이벤트
    $("#caCdidx").change(function(){
		if($("#caCdidx").val() == '1') {
	    	$("#cdSubject").attr("readonly",false);
	    	$("#cdSubject").val('');
	    	$("#cdSubject").focus();
		}else{
			$("#cdSubject").attr("readonly",true);
			$("#cdSubject").val($.trim($("#caCdidx option:selected").text()));
		}
    });
});

function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
	setAddrValue(roadAddrPart1, addrDetail + " " + roadAddrPart2, zipNo);
}

function doForm(f){
 	<c:if test="${BoardVo.editorYn eq 'Y'}">
	 	<c:choose>
			<c:when test="${editor eq 'crosseditor'}">
				if(CrossEditor1.GetBodyValue() == ""){
					alert('내용을 입력해 주세요 !!');
					return false;
				}else{			
					$("#clobCont").val(CrossEditor1.GetBodyValue());
				}
			</c:when>
			<c:otherwise>
				if(CKEDITOR.instances["clobCont"].getData() == ""){
					alert('내용을 입력해 주세요 !!');
					return false;
				}else{			
					$("#clobCont").val(CKEDITOR.instances["clobCont"].getData());
				}
			</c:otherwise>
		</c:choose>
	</c:if>
	
	<c:forEach var="propertyInfo" items="${propertylist}">
	<c:set var="contentMapping" value="${propertyInfo.contentMapping}" />
	<c:set var="contentMappingL" value="${propertyInfo.contentMappingL}" />
	<c:set var="labelPropGbn" value="${propertyInfo.labelPropGbn}" />
	<c:set var="labelName" value="${propertyInfo.labelName}" />
	<c:set var="labelCompYn" value="${propertyInfo.labelCompYn}" />
	<c:if test="${contentMapping eq 'CLOB_CONT2' && labelPropGbn eq '16020200' && BoardVo.editorYn eq 'Y'}">
		<c:if test="${BoardVo.editorYn eq 'Y'}">
			<c:choose>
				<c:when test="${editor eq 'crosseditor'}">
					if(CrossEditor2.GetBodyValue() == ""){
						alert('내용을 입력해 주세요 !!');
						return false;
					}else{			
						$("#clobCont2").val(CrossEditor2.GetBodyValue());
					}
				</c:when>
				<c:otherwise>
					if(CKEDITOR.instances["clobCont2"].getData() == ""){
						alert('내용을 입력해 주세요 !!');
						return false;
					}else{			
						$("#clobCont2").val(CKEDITOR.instances["clobCont2"].getData());
					}
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:if>
	
	<c:if test="${labelPropGbn eq '16021000'}">
	if($("#caCdidx").val() == "") {
		alert("담당부서를 선택해 주십시오.");
		$("#caCdidx").focus();
		return;
	}
	</c:if> 
	
	<c:if test="${labelCompYn eq 'Y'}">
		<c:choose>
			<c:when test="${labelPropGbn eq '16020400'}">
			if($("#<c:out value="${contentMappingL}"/>1").val() == "" || $("#<c:out value="${contentMappingL}"/>2").val() == "") {
				alert("<c:out value="${labelName}"/>을 선택해 주세요");$("#<c:out value="${contentMappingL}"/>1").focus();return;
			}
			</c:when>
			<c:when test="${labelPropGbn eq '16020500'}">
			if($("#<c:out value="${contentMappingL}"/>1").val() == "" || $("#<c:out value="${contentMappingL}"/>2").val() == "" || $("#<c:out value="${contentMappingL}"/>3").val() == "") {
				alert("<c:out value="${labelName}"/>을 입력해 주세요");$("#<c:out value="${contentMappingL}"/>1").focus();return;
			}
			</c:when>
			<c:when test="${labelPropGbn eq '16020900'}">
			if($("input:checkbox[name='<c:out value="${contentMappingL}"/>Arr']:checked").length < 1) {
				$("input:checkbox[name='<c:out value="${contentMappingL}"/>Arr']").eq(0).focus();alert("<c:out value="${labelName}"/>을 입력해 주세요");return;
			}
			</c:when>
			<c:otherwise>
				if($("#<c:out value="${contentMappingL}"/>").val() == "") {
					alert("<c:out value="${labelName}"/>을 입력해 주세요");$("#<c:out value="${contentMappingL}"/>").focus();return;
				}
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:choose>
	<c:when test="${labelPropGbn eq '16020400'}">
		$("#<c:out value="${contentMappingL}"/>").val($("#<c:out value="${contentMappingL}"/>1").val()+"_"+$("#<c:out value="${contentMappingL}"/>2").val()+"_"+$("#<c:out value="${contentMappingL}"/>3").val());
	</c:when>
	<c:when test="${labelPropGbn eq '16020900'}">
		$("#<c:out value="${contentMappingL}"/>").val($("input[name='<c:out value="${contentMappingL}"/>Arr']:checkbox:checked").map(function () {return this.value;}).get());
	</c:when>
	</c:choose>
	</c:forEach>
	
	if($("#actionkey").val() == "regist") {	
		if(mutifileyn){    	
	    	if($(".template-upload").length > 1){
	    		$("#uploadbtn").click();
	    	}else{
	    		$('.modalContent #fileupload').attr('id','BbsContentVo');
	    		$(".modalContent #BbsContentVo").attr('name','BbsContentVo');
	    		$(".modalContent #BbsContentVo").attr('commandName','BbsContentVo');
	    		$(".modalContent #BbsContentVo").attr('method','POST');
	    		$(".modalContent #BbsContentVo").attr('enctype','multipart/form-data');
	    		$(".modalContent #BbsContentVo").attr('action','BbsContentReg.do');
	    	}    	
	    }else{
	    	$('.modalContent #fileupload').attr('id','BbsContentVo');
    		$(".modalContent #BbsContentVo").attr('name','BbsContentVo');
	    	$(".modalContent #BbsContentVo").attr("action", "BbsContentReg.do");
	    }
	}else if($("#actionkey").val() == "modify") {
		if(mutifileyn){
	    	if($(".template-upload").length > 1){
	    		$("#uploadbtn").click();
	    	}else{
	    		$('.modalContent #fileupload').attr('id','BbsContentVo');
	    		$(".modalContent #BbsContentVo").attr('name','BbsContentVo');
	    		$(".modalContent #BbsContentVo").attr('commandName','BbsContentVo');
	    		$(".modalContent #BbsContentVo").attr('method','POST');
	    		$(".modalContent #BbsContentVo").attr('enctype','multipart/form-data');
	    		$(".modalContent #BbsContentVo").attr('action','BbsContentMod.do'); 
	    	}    	
	    }else{
	    	$('.modalContent #fileupload').attr('id','BbsContentVo');
    		$(".modalContent #BbsContentVo").attr('name','BbsContentVo');
	    	$(".modalContent #BbsContentVo").attr("action", "BbsContentMod.do");
	    }
	}
	return true;
}

function doFormCopy(f){
	if(!f.newCbIdx.value || f.newCbIdx.value == ""){
		alert("이동/복사될 게시판을 선택해 주십시요!");
		return false;
	}
	if(!confirm("게시글이 이동/복사 됩니다. 실행하시겠습니까?")){
		return false;
	}
	return true;
}

function setAddrValue(addr1, addr2, zip) {
	var addrTarget = $("#addrTarget").val();
	$("#"+addrTarget+"1").val(zip);
	$("#"+addrTarget+"2").val(addr1);
	$("#"+addrTarget+"3").val(addr2);
	document.getElementById("addrCont").value = "("+zip+")_"+addr1+"_"+addr2;
}

<c:if test="${editor eq 'crosseditor'}">
	function OnInitCompleted(e){
		if($("#clobCont").length > 0){
			CrossEditor1.SetBodyValue(document.getElementById("clobCont").value);
		}
		if($("#clobCont2").length > 0){
			CrossEditor2.SetBodyValue(document.getElementById("clobCont2").value);
		}
	}
</c:if>
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>게시물 등록/수정</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<fmt:setLocale value="ko_kr"/>
	<form:form commandName="BbsContentVo" id="fileupload" name="fileupload" method="post" enctype="multipart/form-data" action="/boffice/ex/board/File_Upload.do" onsubmit="return doForm(this);">
	<input type="hidden" id="actionkey" name="actionkey" value="<c:out value="${BbsContentVo.actionkey}"/>" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${BbsContentVo.pageIndex}"/>" />
	<input type="hidden" id="pageUnit" name="pageUnit" value="<c:out value="${BbsContentVo.pageUnit}"/>" />
	<input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value="${BbsContentVo.searchCondition}"/>" />
	<input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value="${BbsContentVo.searchKeyword}"/>" />
	<input type="hidden" id="searchStartDate" name="searchStartDate" value="<c:out value="${BbsContentVo.searchStartDate}"/>" />
	<input type="hidden" id="searchEndDate" name="searchEndDate" value="<c:out value="${BbsContentVo.searchEndDate}"/>" />
	<input type="hidden" id="searchGroup" name="searchGroup" value="<c:out value="${BbsContentVo.searchGroup}" />"/>
	<input type="hidden" id="searchCbIdx" name="searchCbIdx" value="<c:out value="${BbsContentVo.searchCbIdx}" />"/>
	<input type="hidden" id="searchDelYn" name="searchDelYn" value="<c:out value="${BbsContentVo.searchDelYn}" />"/>
	<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value="${!empty BbsContentVo.bcIdx ? BbsContentVo.bcIdx : 0}" />"/>
	<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${BbsContentVo.cbIdx}" />"/>
	<input type="hidden" id="parentSeq" name="parentSeq" value="<c:out value="${!empty BbsContentVo.parentSeq ? BbsContentVo.parentSeq : 0}" />"/>
	<input type="hidden" id="answerStep" name="answerStep" value="<c:out value="${!empty BbsContentVo.answerStep ? BbsContentVo.answerStep : 0}" />"/>
	<input type="hidden" id="answerDepth" name="answerDepth" value="<c:out value="${!empty BbsContentVo.answerDepth ? BbsContentVo.answerDepth : 0}" />"/>
	<input type="hidden" id="addrTarget" name="addrTarget" value="" />
	<input type="hidden" name="tempname"/>
	<input type="hidden" name="tempsname"/>
	<input type="hidden" name="tempsize"/>
	<div class="tableBox">
		<table class="form">
			<caption>게시물 등록/수정 폼</caption>
			<colgroup>
				<col style="width:15%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">게시판</th>
					<td><c:out value="${fn:replace(fn:substring(BoardVo.cbPath,1,100), '>', ' > ')}" /></td>
				</tr>
				<c:forEach var="propertyInfo" items="${propertylist}">
				<c:set var="contentMapping" value="${propertyInfo.contentMapping}" />
				<c:set var="contentMappingL" value="${propertyInfo.contentMappingL}" />
				<c:set var="labelPropGbn" value="${propertyInfo.labelPropGbn}" />
				<c:set var="labelName" value="${propertyInfo.labelName}" />
				<c:set var="labelCompYn" value="${propertyInfo.labelCompYn}" />
				<c:set var="itemCode" value="${propertyInfo.itemCode}" />
				<c:set var="bbsContentValue" value="${(BbsContentVo.actionkey eq 'modify' || propertyInfo.contentMapping eq 'CATE_CONT') ? bbs:getValue(BbsContentVo, contentMappingL) : ''}" />
				<c:choose>
				<c:when test="${contentMapping eq 'NO_CONT'}">
				</c:when>
				<c:when test="${contentMapping eq 'MW_STATUS_CONT'}">
				</c:when>
				<c:when test="${contentMapping eq 'REG_DT'}">
				</c:when>
				<c:when test="${contentMapping eq 'COUNT_CONT'}">
				</c:when>
				<c:when test="${contentMapping eq 'FILE_CONT'}">
					<c:if test="${BoardVo.bbsFileCnt > 0}">
						<c:choose>
							<c:when test="${BbsContentVo.cbIdx eq '1189'}">
								<tr>
									<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if>썸네일</th>
									<td>
										<c:if test="${fn:length(fileList) > 0}">
											<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileList[0].bcIdx}"/>&cbIdx=<c:out value="${fileList[0].cbIdx}"/>&streFileNm=<c:out value="${fileList[0].streFileNm}"/>" class="file">
											<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileList[0].fileExtsn}"/>.gif" alt="<c:out value="${fileList[0].fileExtsn}"/> 아이콘" /> <c:out value="${fileList[0].orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList[0].fileSize)}" />]</a> 
											<a href="#" data-bcIdx="<c:out value="${fileList[0].bcIdx}"/>" data-cbIdx="<c:out value="${fileList[0].cbIdx}"/>" data-streFileNm="<c:out value="${fileList[0].streFileNm}"/>" class="btn_inline btn_delFile">삭제</a></p>
										</c:if>
										<c:if test="${fn:length(fileList) <= 0}">
											<input type="file" id="dataFile0" name="dataFile0" class="w200" title="첨부파일0" />
											<br /><span class="captionText">※파일 1개당 최대 용량은<c:out value="${BoardVo.fileMaxSize}"/>MB 입니다.</span> 
										</c:if>
									</td>
								</tr>
								<c:forEach begin="1" varStatus="status" end="${BoardVo.bbsFileCnt-1}">
									<tr>
										<c:if test="${status.first}">
										<th scope="row" rowspan="<c:out value="${BoardVo.bbsFileCnt-1}" />"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></th>
										</c:if>
										<td>
											<c:if test="${fn:length(fileList) > status.index}">
											<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileList[status.index].bcIdx}"/>&cbIdx=<c:out value="${fileList[status.index].cbIdx}"/>&streFileNm=<c:out value="${fileList[status.index].streFileNm}"/>" class="file">
											<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileList[status.index].fileExtsn}"/>.gif" alt="<c:out value="${fileList[status.index].fileExtsn}"/> 아이콘" /> <c:out value="${fileList[status.index].orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList[status.index].fileSize)}" />]</a> 
											<a href="#" data-bcIdx="<c:out value="${fileList[status.index].bcIdx}"/>" data-cbIdx="<c:out value="${fileList[status.index].cbIdx}"/>" data-streFileNm="<c:out value="${fileList[status.index].streFileNm}"/>" class="btn_inline btn_delFile">삭제</a></p>
											</c:if>
											<c:if test="${fn:length(fileList) <= status.index}">
											<input type="file" id="dataFile<c:out value="${status.index}"/>" name="dataFile<c:out value="${status.index}"/>" class="w200" title="첨부파일<c:out value="${status.index}"/>" />
											
											<c:if test="${status.last }">
												<br /><span class="captionText">※파일 1개당 최대 용량은<c:out value="${BoardVo.fileMaxSize}"/>MB 입니다.</span>
											</c:if> 
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach begin="0" varStatus="status" end="${BoardVo.bbsFileCnt-1}">
									<tr>
										<c:if test="${status.first}">
										<th scope="row" rowspan="<c:out value="${BoardVo.bbsFileCnt}" />"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></th>
										</c:if>
										<td>
											<!--<c:if test="${fn:length(fileList) > status.index && BoardVo.nasYn eq 'N'}">
											<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileList[status.index].bcIdx}"/>&cbIdx=<c:out value="${fileList[status.index].cbIdx}"/>&streFileNm=<c:out value="${fileList[status.index].streFileNm}"/>" class="file">
											<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileList[status.index].fileExtsn}"/>.gif" alt="<c:out value="${fileList[status.index].fileExtsn}"/> 아이콘" /> <c:out value="${fileList[status.index].orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList[status.index].fileSize)}" />]</a> 
											<a href="#" data-bcIdx="<c:out value="${fileList[status.index].bcIdx}"/>" data-cbIdx="<c:out value="${fileList[status.index].cbIdx}"/>" data-streFileNm="<c:out value="${fileList[status.index].streFileNm}"/>" class="btn_inline btn_delFile">삭제</a></p>
											</c:if>
											<c:if test="${fn:length(fileList) <= status.index || BoardVo.nasYn eq 'Y'}">
											<input type="file" id="dataFile<c:out value="${status.index}"/>" name="dataFile<c:out value="${status.index}"/>" class="w200" title="첨부파일<c:out value="${status.index}"/>" />
											
											<c:if test="${status.last }">
												<br /><span class="captionText">※파일 1개당 최대 용량은<c:out value="${BoardVo.fileMaxSize}"/>MB 입니다.</span>
											</c:if> 
											</c:if>
											-->
											<c:if test="${fn:length(fileList) > status.index}">
											<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileList[status.index].bcIdx}"/>&cbIdx=<c:out value="${fileList[status.index].cbIdx}"/>&streFileNm=<c:out value="${fileList[status.index].streFileNm}"/>" class="file">
											<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileList[status.index].fileExtsn}"/>.gif" alt="<c:out value="${fileList[status.index].fileExtsn}"/> 아이콘" /> <c:out value="${fileList[status.index].orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList[status.index].fileSize)}" />]</a> 
											<a href="#" data-bcIdx="<c:out value="${fileList[status.index].bcIdx}"/>" data-cbIdx="<c:out value="${fileList[status.index].cbIdx}"/>" data-streFileNm="<c:out value="${fileList[status.index].streFileNm}"/>" class="btn_inline btn_delFile">삭제</a></p>
											</c:if>
											<c:if test="${fn:length(fileList) <= status.index}">
											<input type="file" id="dataFile<c:out value="${status.index}"/>" name="dataFile<c:out value="${status.index}"/>" class="w200" title="첨부파일<c:out value="${status.index}"/>" />
											
											<c:if test="${status.last }">
												<br /><span class="captionText">※파일 1개당 최대 용량은<c:out value="${BoardVo.fileMaxSize}"/>MB 입니다.</span>
											</c:if> 
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:when>
				<c:when test="${contentMapping eq 'MULTI_FILE_CONT'}">						
				<tr>							
					<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></th>							
					<td>
						<div class="fileupload-buttonbar">
					        <div class="fileupload-buttons">
					            <!-- The fileinput-button span is used to style the file input field as button -->							                          
					            <input type="file" name="files[]" multiple>				               
					            <button type="submit" class="start" id="uploadbtn" style="display:none">저장</button>            
					            <button type="button" class="delete" style="display:none">삭제</button>
					            <input type="checkbox" class="toggle" style="display:none">
					            <!-- The global file processing state -->
					            <span class="fileupload-process"></span>
					        </div>
					        <!-- The global progress state -->
					        <div class="fileupload-progress fade" style="display:none">
					            <!-- The global progress bar -->
					            <div class="progress" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
					            <!-- The extended global progress state -->
					            <div class="progress-extended">&nbsp;</div>
					        </div>
					    </div>
					    <br />					    
					    <table role="presentation" id="filelist">
					    <thead>
						    <tr>
							    <th>파일명</th>
							    <th>용량</th>
							    <th>삭제</th>
						    </tr>
					    </thead>
					    <tbody class="files">
						    <tr class="template-upload fade" align="middle" id="drag">
						        <td colspan="3">
						            	drag&drop file
						        </td>        
						    </tr>
					    </tbody>
					    </table>										
		<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
	
    <tr class="template-upload fade" valign="top">        
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error" style="dispaly:none"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="start" disabled style="display:none">Start</button>
            {% } %}
            {% if (!i) { %}
                <button class="cancel">취소</button>
            {% } %}
        </td>
    </tr>
	
{% } %}
	</script>											
		<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade" valign="top">        
        <td>
            <p class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
            </p>
            
        </td>
        <td>
            <span class="size">{%=file.size%}</span>
        </td>
        <td>
            <button class="delete" data-type="text" data-url="/site/yangcheon/ex/bbs/File_RmvAx.do?cbIdx=<c:out value='${BbsContentVo.cbIdx}'/>&bcIdx=<c:out value='${BbsContentVo.bcIdx}'/>&fileNo={%=file.fileno%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>삭제</button>
            <input type="hidden" name="delete" value="1" class="toggle" align="absmiddle">
        </td>
    </tr>
{% } %}
	</script>
						<span class="fileupload-notice">※파일1개당 최대 10MB까지만 가능합니다</span>
					</td>
				</tr>											
				</c:when>
				
				<c:when test="${contentMapping eq 'CLOB_CONT' && labelPropGbn eq '16020200' && BoardVo.editorYn eq 'Y'}">
				<tr>
					<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
					<td>
						<div id="clobContWrap">
							<textarea id="clobCont" name="clobCont" style="width:100%;height:300px;">${bbsContentValue}</textarea>
						</div>
					</td>
				</tr>
				</c:when>
				
				<c:when test="${contentMapping eq 'CLOB_CONT2' && labelPropGbn eq '16020200' && BoardVo.editorYn eq 'Y'}">
				<tr>
					<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
					<td>
						<div id="clobContWrap2">
							<textarea id="clobCont2" name="clobCont2" style="width:100%;height:300px;">${bbsContentValue}</textarea>
						</div>
					</td>
				</tr>
					</c:when>
					<c:otherwise>
					<c:choose>
						<c:when test="${labelPropGbn eq '16020700'}">
						<tr><!--SELECTBOX -->
							<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
							<td>
								<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
								<select id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>">
								<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
								<option value="<c:out value="${itemInfo.code}"/>" <c:if test="${itemInfo.codeName eq bbsContentValue}">selected="selected"</c:if> ><c:out value="${itemInfo.codeName}"/></option>
								</c:forEach>
								</select>
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020800'}">
						<tr><!-- RADIOBOX -->
							<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
							<td>
								<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
								<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
								<input type="radio" id="<c:out value="${contentMappingL}"/><c:out value="${status.count}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${itemInfo.code}"/>" <c:if test="${itemInfo.code eq bbsContentValue}">checked="checked"</c:if> />
								<label for="<c:out value="${contentMappingL}"/><c:out value="${status.count}"/>"><c:out value="${itemInfo.codeName}"/></label> 
								</c:forEach>
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020900'}">
						<tr><!-- CHECKBOX-->
							<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${propertyInfo.labelName}" /></label></th>
							<td>
								<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
								<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
								<input type="checkbox" id="<c:out value="${contentMappingL}"/><c:out value="${status.count}"/>" name="<c:out value="${contentMappingL}"/>Arr" value="<c:out value="${itemInfo.code}"/>" <c:if test="${fn:indexOf(bbsContentValue, itemInfo.code) > -1}">checked="checked"</c:if> />
								<label for="<c:out value="${contentMappingL}"/><c:out value="${status.count}"/>"><c:out value="${itemInfo.codeName}"/></label> 
								</c:forEach>
								<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" />
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020200'}">
						<tr><!-- TEXTAREA -->
							<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
							<td><textarea rows="10" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" class="w100p"><c:out value="${bbsContentValue}"/></textarea></td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020500'}">
						
						<tr><!-- PHONE -->
							<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>1"><c:out value="${labelName}" /></label></th>
							<td>
								<c:set var="telArr" value="${cmm:split(bbsContentValue, '-', 3)}" />
								<input type="text" id="<c:out value="${contentMappingL}"/>1" name="<c:out value="${contentMappingL}"/>1" value="<c:out value="${fn:length(telArr) > 0 ? telArr[0] : ''}"/>" class="onlyNum" maxlength="4" size="5" title="전화 앞번호" /> -
								<label for="<c:out value="${contentMappingL}"/>2" class="hidden">전화 중간번호</label> 
								<input type='text' id="<c:out value="${contentMappingL}"/>2" name="<c:out value="${contentMappingL}"/>2" value="<c:out value="${fn:length(telArr) > 1 ? telArr[1] : ''}"/>" class="onlyNum" maxlength="4" size="5" title="전화 중간번호" /> -
								<label for="<c:out value="${contentMappingL}"/>3" class="hidden">전화 뒷번호</label> 
								<input type='text' id="<c:out value="${contentMappingL}"/>3" name="<c:out value="${contentMappingL}"/>3" value="<c:out value="${fn:length(telArr) > 2 ? telArr[2] : ''}"/>" class="onlyNum" maxlength="4" size="5" title="전화 뒷번호" />
								<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" />
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020400'}">
						<c:set var="addrArr" value="${cmm:split(bbsContentValue, '_', 3)}" />
						<tr><!-- ADDRESS -->
							<th scope="row" rowspan="3"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>1"><c:out value="${labelName}" /></label></th>
							<td>
								<input type="text" id="<c:out value="${contentMappingL}"/>1" name="<c:out value="${contentMappingL}"/>1" value="<c:out value="${fn:length(addrArr) > 0 ? addrArr[0] : ''}"/>" size="10" readonly="readonly" /> 
								<a href="#" class="btn_inline btn_searchZip" data-target="<c:out value="${contentMappingL}"/>" title="새창열림">우편번호검색</a>
								<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" />
							</td>
						</tr>
						<tr>
							<td><label for="<c:out value="${contentMappingL}"/>2" class="hidden">기본주소</label>
								<input type="text" id="<c:out value="${contentMappingL}"/>2" name="<c:out value="${contentMappingL}"/>2" value="<c:out value="${fn:length(addrArr) > 1 ? addrArr[1] : ''}"/>" class="w100p" title="기본주소" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td><label for="<c:out value="${contentMappingL}"/>3" class="hidden">상세주소</label>
								<input type="text" id="<c:out value="${contentMappingL}"/>3" name="<c:out value="${contentMappingL}"/>3" value="<c:out value="${fn:length(addrArr) > 2 ? addrArr[2] : ''}"/>" class="w100p" title="상세주소" />
							</td>
						</tr>
						</c:when>
						
						
		<%-- 2016_11_11 작업  슈퍼 관리자 일때는 selectbox 아닐때는 inputbox 작성자 일때(label) 시작 --%>								
						<c:when test="${labelPropGbn eq '16021000'}">
						<tr><!-- 이름 -->
							<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
						
		<%-- 슈퍼관리자 일때 (글쓰기/수정) - selectbox로 적용--%>	
						
						<c:if test="${BbsContentVo.actionkey eq 'regist' && SesUserPermCd eq '05010000' && labelName eq '담당부서'}">
							<td>
								<c:set var="caCdidx" value="${!empty BbsContentVo ? BbsContentVo.caCdidx:'' }" />
								<select id="caCdidx" name="caCdidx">
									<option value="">담당부서 선택</option>
									<c:forEach var="departInfo" items="${departList }">
										<c:if test="${departInfo.cdDepstep2 eq '00' }">
											<option value=""><c:out value="${departInfo.cdSubject}" /></option>
										</c:if>		
										<c:if test="${departInfo.cdDepstep2 ne '00' }">				
											<option value="<c:out value="${departInfo.cdIdx}"/>"
												<c:if test="${caCdidx eq departInfo.cdIdx}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<c:out value="${departInfo.cdSubject}" /></option>
										</c:if>					
									</c:forEach>	
									<c:out value="${departInfo.cdIdx}"/>
								</select>
								<input type="hidden" id="cdSubject" name="<c:out value="${contentMappingL}"/>"  value="<c:out value="${BbsContentVo.cdSubject}"/>" />
							</td>
						</c:if>
						
						<c:if test="${BbsContentVo.actionkey eq 'modify' && SesUserPermCd eq '05010000' && labelName eq '담당부서'}">
							<td>
								<c:set var="caCdidx" value="${!empty BbsContentVo ? BbsContentVo.caCdidx:'' }" />
								<select id="caCdidx" name="caCdidx">
									<option value="">담당부서 선택</option>
									<c:forEach var="departInfo" items="${departList }">
										<c:if test="${departInfo.cdDepstep2 eq '00' }">
											<option value=""><c:out value="${departInfo.cdSubject}" /></option>
										</c:if>		
										<c:if test="${departInfo.cdDepstep2 ne '00' }">				
											<option value="<c:out value="${departInfo.cdIdx}"/>"
												<c:if test="${caCdidx eq departInfo.cdIdx}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<c:out value="${departInfo.cdSubject}" /></option>
										</c:if>					
									</c:forEach>	
									<c:out value="${departInfo.cdIdx}"/>
								</select>
								<input type="hidden" id="cdSubject" name="<c:out value="${contentMappingL}"/>"  value="<c:out value="${BbsContentVo.cdSubject}"/>" />
							</td>
						</c:if>
						
		<%-- 슈퍼관리자 아닐때 (글쓰기/수정) - inputbox로 적용--%>  
						
						<c:if test="${BbsContentVo.actionkey eq 'regist' && SesUserPermCd eq '05020000' && labelName eq '담당부서' }">	
						 	<td>
							 	<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${BbsContentVo.actionkey eq 'regist' ? SesUserDeptNm : bbsContentValue}"/>" class="w15" <c:if test="${SesUserPermCd ne '05010000'}">readonly</c:if> />
							 	<span><c:out value="${BbsContentVo.actionkey eq 'regist' ? SesUserDeptNm : bbsContentValue}"/></span>
						 	</td>
						</c:if>	
							
							
						<c:if test="${BbsContentVo.actionkey eq 'modify' && SesUserPermCd eq '05020000' && labelName eq '담당부서' }">	
						 	<td>
							 	<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${BbsContentVo.actionkey eq 'modify' ? SesUserDeptNm : bbsContentValue}"/>" class="w15" <c:if test="${SesUserPermCd ne '05010000'}">readonly</c:if> />
							 	<span><c:out value="${BbsContentVo.actionkey eq 'modify' ? SesUserDeptNm : bbsContentValue}"/></span>
						 	</td>
						</c:if>
						
		<%-- 작성자일때 (글쓰기/수정) - inputbox로 적용--%>
		
								
						<c:if test="${BbsContentVo.actionkey eq 'regist' && (labelName eq '작성자' || labelName eq '이름')}">	
						 	<td>
							 	<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${BbsContentVo.actionkey eq 'regist' ? SesUserName : bbsContentValue}"/>" class="w15" <c:if test="${SesUserPermCd ne '05010000'}">readonly</c:if> />
							 	<span><c:out value="${BbsContentVo.actionkey eq 'regist' ? SesUserDeptNm : bbsContentValue}"/></span>
						 	</td>
						</c:if>	
							
							
						<c:if test="${BbsContentVo.actionkey eq 'modify' && (labelName eq '작성자' || labelName eq '이름')}">	
						 	<td>
							 	<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${BbsContentVo.actionkey eq 'regist' ? SesUserName : bbsContentValue}"/>" class="w15" <c:if test="${SesUserPermCd ne '05010000'}">readonly</c:if> />
							 	<span><c:out value="${BbsContentVo.actionkey eq 'modify' ? SesUserDeptNm : bbsContentValue}"/></span>
						 	</td>
						</c:if>
						
						</tr>
						</c:when>
		<%-- 작업  슈퍼 관리자 일때는 selectbox 아닐때는 inputbox 작성자 일때(label) 끝--%>									
		
				<c:when test="${detailList.contentMapping eq 'M_LINK_CONT'}">
				<tr><!-- UCC LINK  -->
					<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
					<td>
						<div class="view_contents">
							<div class="txt-area">
								<iframe width="640" height="390" src="https://www.youtube.com/embed/<c:out value="${bbs:getValue(BbsContentVo, 'mLinkCont')}"/>" frameborder="0" allowfullscreen></iframe>
								<textarea class="movie-txt" readonly rows="7"><c:out value="${bbs:getValue(BbsContentVo, 'captionCont')}"/></textarea>
							</div>
						</div>
					</td>
				</tr>
				</c:when>
				
				<c:when test="${labelPropGbn eq '16021400'}">
				<tr><!-- 날짜형식 -->
					<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
					<td><span class="cal_in"><input type="text" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" class="useDatepicker"/></span></td>
				</tr>
				</c:when>
				<c:when test="${labelPropGbn eq '16021500'}">
				<tr><!-- 키워드 -->
					<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
					<td><input type="text" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" class="<c:out value="${inputSize}"/>" />
						예) 할아버지,아동복지,양천공원(쉼표로분리)
					</td>
				</tr>
				</c:when>
				
				<c:when test="${labelPropGbn eq '16021600'}">	<!-- 부서 -->
				<tr>		
					<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
					<td>
						<c:choose>
						<c:when test="${SesUserPermCd eq '05010000'}">
							<c:set var="caCdidx" value="${BbsContentVo.caCdidx ne '' ? BbsContentVo.caCdidx:'' }" />
							<select id="caCdidx" name="caCdidx">
								<option value="">담당부서 선택</option>
								<c:forEach var="departInfo" items="${departList }">
									<c:choose>
									<c:when test="${BbsContentVo.cbIdx eq '356'}">
										<c:if test="${fn:indexOf(',목1동,목2동,목3동,목4동,목5동,신월1동,신월2동,신월3동,신월4동,신월5동,신월6동,신월7동,신정1동,신정2동,신정3동,신정4동,신정6동,신정7동,',departInfo.cdSubject)==-1}">
											<c:if test="${departInfo.cdDepstep2 eq '00' }">
												<option value=""><c:out value="${departInfo.cdSubject}" /></option>
											</c:if>		
											<c:if test="${departInfo.cdDepstep2 ne '00' }">				
												<option value="<c:out value="${departInfo.cdIdx}"/>"
													<c:if test="${caCdidx eq departInfo.cdIdx}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:out value="${departInfo.cdSubject}" /></option>
											</c:if>
										</c:if>
									</c:when>
									<c:otherwise>
										<c:if test="${departInfo.cdDepstep2 eq '00' }">
											<option value=""><c:out value="${departInfo.cdSubject}" /></option>
										</c:if>		
										<c:if test="${departInfo.cdDepstep2 ne '00' }">				
											<option value="<c:out value="${departInfo.cdIdx}"/>"
												<c:if test="${caCdidx eq departInfo.cdIdx}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<c:out value="${departInfo.cdSubject}" /></option>
										</c:if>
									</c:otherwise>
									</c:choose>			
								</c:forEach>	
								<c:out value="${departInfo.cdIdx}"/>
							</select>
							<input type="hidden" id="cdSubject" name="cdSubject" value="<c:out value="${BbsContentVo.cdSubject}"/>" />
						</c:when>
						<c:otherwise>
							<input type="hidden" id="caCdidx" name="caCdidx" value="<c:out value="${BbsContentVo.actionkey eq 'regist' ? SesUserDeptCd : bbsContentValue}"/>"/>
							<input type="hidden" id="cdSubject" name="cdSubject" value="<c:out value="${BbsContentVo.actionkey eq 'regist' ? SesUserDeptNm : bbsContentValue}"/>"/>
							<span><c:out value="${BbsContentVo.actionkey eq 'regist' ? SesUserDeptNm : bbsContentValue}"/></span>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				</c:when>
				
				<c:otherwise>
					<c:set var="inputSize" value="w50p"/>
					<c:choose>
						<c:when test="${contentMappingL eq 'subCont'}">
							<c:set var="inputSize" value="w100p"/>
						</c:when>
					</c:choose>
				<tr>
					<th scope="row"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><label for="<c:out value="${contentMappingL}"/>"><c:out value="${labelName}" /></label></th>
					<td><input type="text" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" class="<c:out value="${inputSize}"/>" /></td>
				</tr>
				</c:otherwise>
				
				</c:choose>
				</c:otherwise>
				</c:choose>
				</c:forEach>
				
				<c:if test="${BoardVo.cbIdx == 57 || BoardVo.cbIdx == 58 || BoardVo.cbIdx == 59}">
					<c:if test="${(SesUserPermCd eq '05010000' && BbsContentVo.actionkey eq 'modify') }">
						<c:set var="departName" value="${BbsContentVo.actionkey eq 'modify' ? bbs:getValue(BbsContentVo, 'nameCont') : SesUserDeptNm}" />
						<tr><!-- 공지사항, 행사소식, 고시공고 -->
							<th scope="row"><label for="placeType">게시 홈페이지</label></th>
							<td>
								<select id="placeType" name="placeType">
									<option value="Y" <c:if test="${BbsContentVo.placeType eq 'Y'}">selected="selected"</c:if>>[구청홈페이지, <c:out value="${departName}"/>]</option>
									<option value="A" <c:if test="${BbsContentVo.placeType eq 'A'}">selected="selected"</c:if>>[구청홈페이지]</option>
									<option value="B" <c:if test="${BbsContentVo.placeType eq 'B'}">selected="selected"</c:if>>[<c:out value="${departName}"/>]</option>
								</select> (게재하고자 하는 홈페이지를 선택해주세요)
							</td>
						</tr>
					</c:if>
				</c:if>
				
				<c:if test="${BoardVo.cbIdx == 82}">
					<tr>
						<th scope="row"><label for="clobAutoMakeYn">내용자동제작</label></th>
						<td><input type="checkbox" id="clobAutoMakeYn" name="clobAutoMakeYn" value="Y" /> 체크하면 기존 내용은 지워지고 첨부한 파일사진을 이용해서 내용이 새로 제작됩니다.</td>
					</tr>
				</c:if>
				
				<c:if test="${BoardVo.bbsApprYn == 2}">
					<tr>
						<th scope="row"><label for="apprYn">게시글승인</label></th>
						<td>
							<input type="radio" id="apprYn1" name="apprYn" value="Y" <c:if test="${BbsContentVo.apprYn eq 'Y'}">checked="checked"</c:if> /><label for="apprYn1">승인</label> 
							<input type="radio" id="apprYn2" name="apprYn" value="N" <c:if test="${BbsContentVo.apprYn eq 'N'}">checked="checked"</c:if> /><label for="apprYn2">미승인</label> 
						</td>
					</tr>
				</c:if>
				<!-- 
				<c:if test="${BoardVo.nasYn eq 'Y'}">
					<input type="hidden" name="nasFileCount" id="nasFileCount" value="0"/>
					<tr>
						<th scope="row"><label for="notiYn">NAS</label></th>
						<td>
							<div style="height:30px"><input type="button" value="NAS찾아보기" class="btn_inline btn_searchNas" /></div>
							<span id="nasFileTitle"></span>
							<input type="hidden" name="nasPath<c:out value="${status.index}"/>" id="nasPath<c:out value="${status.index}"/>"/>
						</td>
					</tr>
				</c:if>
				<c:if test="${labelPropGbn eq '16020600'}">
					<c:if test="${fn:length(fileList) > 0 && BoardVo.nasYn eq 'Y'}">
						<tr>
							<th scope="row"><label for="notiYn">첨부파일</label></th>
							<td>
								<c:forEach var="fileInfo" items="${fileList}">
								<div style="height:30px">
									<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileInfo.bcIdx}"/>&cbIdx=<c:out value="${fileInfo.cbIdx}"/>&streFileNm=<c:out value="${fileInfo.streFileNm}"/>" class="file">
									<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileInfo.fileExtsn}"/>.gif" alt="<c:out value="${fileInfo.fileExtsn}"/> 아이콘" /> <c:out value="${fileInfo.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileInfo.fileSize)}" />]</a> 
									<a href="#" data-bcIdx="<c:out value="${fileInfo.bcIdx}"/>" data-cbIdx="<c:out value="${fileInfo.cbIdx}"/>" data-streFileNm="<c:out value="${fileInfo.streFileNm}"/>" class="btn_inline btn_delFile">삭제</a></p>
								</div>
								</c:forEach>
							</td>
						</tr>
					</c:if>
				</c:if>
				 -->
				<tr>
					<th scope="row"><label for="notiYn">공지여부</label></th>
					<td><input type="checkbox" id="notiYn" name="notiYn" value="Y" <c:if test="${BbsContentVo.notiYn eq 'Y'}">checked="checked"</c:if> /> 상단고정으로 사용</td>
				</tr>
										
				<c:if test="${BoardVo.pushYn eq 'Y'}">							
					<tr>
						<th scope="row"><label for="pushYn">푸시 발송여부</label></th>
						<td><input type="checkbox" id="pushYn" name="pushYn" value="Y" <c:if test="${BbsContentVo.pushYn eq 'Y'}">checked="checked"</c:if> /></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<input type="submit" value="확인" class="btn_m on">
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
	</form:form>
	
	<%-- <c:if test="${BbsContentVo.bcIdx!=0}">
	<form id="BbsContentVo2" name="BbsContentVo2" action="BbsContentMove.do" method="post" onsubmit="return doFormCopy(this);">
	<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value="${!empty BbsContentVo.bcIdx ? BbsContentVo.bcIdx : 0}" />"/>
	<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${BbsContentVo.cbIdx}" />"/>
	<p class="required-guide"><span class="required">*</span> 복사된 게시물의 날짜는 현재를 기준으로 저장됩니다.</p>
	<table summary="게시물 상세보기" class="write">
	<caption>게시물 상세보기</caption>
	<colgroup>
		<col style="width:15%"/>
		<col style="width:85%"/>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row"><label for="notiYn">게시판복사</label></th>
			<td>
				<input type="radio" name="mode" value="move" checked/> 이동
				<input type="radio" name="mode" value="copy"/> 복사
				<select id="newCbIdx" name="newCbIdx" title="게시판선택">
					<option value="">대상게시판선택</option>
					<c:forEach  items="${treeBoardVoList}" var="treeBoardVo" >
						<option value="${treeBoardVo.cbIdx}" >
							<c:forEach begin="1" end="${treeBoardVo.cbDepth}">
								&nbsp;&nbsp;
							</c:forEach>
							${treeBoardVo.text}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		</tbody>
	</table>
	<div class="btnArea">
		<input type="submit" value="확인" class="btn_m on">
	</div>
	</form>
	</c:if> --%>
	
</div>
<!-- ============================== //모달영역 ============================== -->

