<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<c:set var="editor" value="${cmm:getGlobalProperties('editor')}"/>
<%
String editorYN = "Y";
%>
<%-- ------------------------------------------------------------
- 제목 : 컨텐츠관리 > 컨텐츠관리
- 파일명 : user_menu_contents_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<c:choose>
	<c:when test="${editor eq 'crosseditor'}">
		<script src="/plugin/crosseditor/js/namo_scripteditor.js"></script>
	</c:when>
	<c:otherwise>
		<script src="/plugin/ckeditor/ckeditor.js"></script>
	</c:otherwise>
</c:choose>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
var CrossEditor;
$(function(){
	//버튼이벤트
	$(".btn_publication").click(function(e){
		e.preventDefault();
		if(!confirm("발행하시겠습니까?")){
			return;
		}
		
		<c:choose>
			<c:when test="${editor eq 'crosseditor'}">
				$("#bodyContent").val(CrossEditor.GetBodyValue());
			</c:when>
			<c:otherwise>
				$("#bodyContent").val(CKEDITOR.instances["bodyContent"].getData());
			</c:otherwise>
		</c:choose>
		
		var requiredCheck = formRequiredCheck(document.UserMenuContentsVo);
		if(requiredCheck == false){
			return false;
		}

		var ajaxOption = {};
		var ajaxParam = $("#UserMenuContentsVo").serializeArray();
		ajaxActionMessage("/boffice/cn/menu/User_Menu_Contents_Pub.do", ajaxParam, '발행되었습니다.', false, ajaxOption);
		modalClose();
	});
	$(".btn_publication_req").click(function(e){
		e.preventDefault();
		if(!confirm("발행신청 하시겠습니까?")){
			return;
		}
		
		<c:choose>
			<c:when test="${editor eq 'crosseditor'}">
				$("#bodyContent").val(CrossEditor.GetBodyValue());
			</c:when>
			<c:otherwise>
				$("#bodyContent").val(CKEDITOR.instances["bodyContent"].getData());
			</c:otherwise>
		</c:choose>
		$("#progressStatus").val("publish_req");
		
		var requiredCheck = formRequiredCheck(document.UserMenuContentsVo);
		if(requiredCheck == false){
			return false;
		}

		var ajaxOption = {};
		var ajaxParam = $("#UserMenuContentsVo").serializeArray();
		ajaxActionMessage("/boffice/cn/menu/User_Menu_Contents_Mod.do", ajaxParam, '발행신청 되었습니다.', false, ajaxOption);
		modalClose();
	});
	$(".btn_publication_cancel").click(function(e){
		e.preventDefault();
		if(!confirm("발행취소 하시겠습니까?")){
			return;
		}
		
		<c:choose>
			<c:when test="${editor eq 'crosseditor'}">
				$("#bodyContent").val(CrossEditor.GetBodyValue());
			</c:when>
			<c:otherwise>
				$("#bodyContent").val(CKEDITOR.instances["bodyContent"].getData());
			</c:otherwise>
		</c:choose>
		$("#progressStatus").val("publish_cancel");
		
		var requiredCheck = formRequiredCheck(document.UserMenuContentsVo);
		if(requiredCheck == false){
			return false;
		}

		var ajaxOption = {};
		var ajaxParam = $("#UserMenuContentsVo").serializeArray();
		ajaxActionMessage("/boffice/cn/menu/User_Menu_Contents_Mod.do", ajaxParam, '발행취소 되었습니다.', false, ajaxOption);
		modalClose();
	});
	
	$(document).on("click",".btn_templateToggle",function(e){
		e.preventDefault();
		$(".editorTemplate").toggleClass("active");
	});
	$(document).on("click",".editorTemplate > ul > li > a",function(e){
		e.preventDefault();
		//var editorId = $(this).parents(".editorBox").eq(0).children("textarea").attr("id"); //path="bodyContent"
		var editorId = $(this).parents(".editorBox").find("#editorWrap").children("textarea").attr("id"); //path="bodyContent"
		var editorAddHtml = $(this).children("textarea").val();
		<c:choose>
			<c:when test="${editor eq 'crosseditor'}">
				editorLib.cross.insert(editorId, editorAddHtml);
			</c:when>
			<c:otherwise>
				editorLib.ck.insert(editorId, editorAddHtml);
			</c:otherwise>
		</c:choose>
	});
});

function doContentForm(f){
	<c:choose>
		<c:when test="${editor eq 'crosseditor'}">
		$("#bodyContent").val(CrossEditor.GetBodyValue());
		</c:when>
		<c:otherwise>
			$("#bodyContent").val(CKEDITOR.instances["bodyContent"].getData());
		</c:otherwise>
	</c:choose>
	
	var requiredCheck = formRequiredCheck(document.UserMenuContentsVo);
	if(requiredCheck == false){
		return false;
	}
	
	var progressStatus = "<c:out value="${UserMenuContentsVo.progressStatus }"/>";
	
	var message;
	if(progressStatus == 'publish_complete'){
		message = "수정되었습니다.";
	}else{
		message = "등록되었습니다.";
	}
	
	var ajaxParam = $("#UserMenuContentsVo").serializeArray();
	var ajaxOption = {};
	ajaxOption.dataType = "text";
	if(f.actionType.value == "reg"){
		ajaxActionMessage("/boffice/cn/menu/User_Menu_Contents_Reg.do", ajaxParam, message, false, ajaxOption);
	}else if(f.actionType.value == "mod"){
		ajaxActionMessage("/boffice/cn/menu/User_Menu_Contents_Mod.do", ajaxParam, message, false, ajaxOption);
	}
	modalClose();
	return false;
}

<c:choose>
<c:when test="${editor eq 'crosseditor'}">
	CrossEditor = new NamoSE('bodyContent');
	CrossEditor.params.Width = "100%";
	CrossEditor.params.UserLang = "auto";
	CrossEditor.params.FullScreen = false;
	CrossEditor.params.Css = "/css/"+$("#siteCd").val()+"/editor.css";
	CrossEditor.params.Menu = false;
	CrossEditor.params.ParentEditor = document.getElementById("editorWrap");
	CrossEditor.EditorStart();
	function OnInitCompleted(e){
		CrossEditor.SetBodyValue(document.getElementById("bodyContent").value);
	}
</c:when>
<c:otherwise>
	CKEDITOR.replace('bodyContent',{
		"contentsCss":[
			"/css/"+$("#siteCd").val()+"/editor.css",
			"/css/cms/template.css"
		],
		"height":300
	});
</c:otherwise>
</c:choose>
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>컨텐츠내용관리</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="UserMenuContentsVo" name="UserMenuContentsVo" method="post" onsubmit="return doContentForm(this);">
		<form:hidden path="siteCd" />
		<form:hidden path="menuCode" />
		<form:hidden path="contentSeqNo" />
		<form:hidden path="sitePath"/>
		<form:hidden path="progressStatus"/>
		<c:choose>
			<c:when test="${UserMenuContentsVo.progressStatus == null || UserMenuContentsVo.progressStatus eq 'publish_complete' }">
				<input type="hidden" name="actionType" value="reg">
			</c:when>
			<c:otherwise>
				<input type="hidden" name="actionType" value="mod">
			</c:otherwise>
		</c:choose>
		<ul class="stepBar">
			<li class="<c:if test="${UserMenuContentsVo.progressStatus == null || UserMenuContentsVo.progressStatus eq ''}">active</c:if>">STEP1 신규등록</li>
			<li class="<c:if test="${UserMenuContentsVo.progressStatus eq 'content_reg' || UserMenuContentsVo.progressStatus eq 'publish_cancel' }">active</c:if>">STEP2 수정완료</li>
			<li class="<c:if test="${UserMenuContentsVo.progressStatus eq 'publish_req' }">active</c:if>">STEP3 발행신청</li>
			<li class="last <c:if test="${UserMenuContentsVo.progressStatus eq 'publish_complete' }">active</c:if>">STEP4 발행승인/완료</li>
		</ul>
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col class="w100">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<td colspan="2">
							<div id="synapEditor"></div>
							<div class="editorBox">
								<div id="editorWrap">
									<form:textarea path="bodyContent" title="BODY 컨텐츠" style="width:100%;height:400px;" />
								</div>
								<div class="editorTemplate">
									<a href="#" class="btn_templateToggle">템플릿목록 열고닫기</a>
									<ul>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail1.png" alt="이미지+텍스트" />
											<textarea class="soundOnly">
<!-- template1 -->
<div class="templateSection">
	<strong class="mainTitle">이미지영역 타이틀 이미지영역 타이틀 (이미지 사이즈 : 230*300)</strong>
	<div class="imgCon left">
		<img src="/images/cms/template/img_small_ex.png" alt="">
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	</div>
</div>
<!-- //template1 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail2.png" alt="텍스트+이미지" />
											<textarea class="soundOnly">
<!-- template2 -->
<div class="templateSection">
	<strong class="mainTitle">타이틀 타이틀 타이틀 타이틀 타이틀 (이미지 사이즈 : 230*300)</strong>
	<div class="imgCon right">
		<span class="img"><img src="/images/cms/template/img_small_ex.png" alt=""></span>
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	</div>
</div>
<!-- //template2 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail3.png" alt="테이블" />
											<textarea class="soundOnly">
<!-- template3 -->
<div class="templateSection">
	<strong class="mainTitle">타이틀 타이틀 타이틀 타이틀 타이틀</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="tblTitle">
		<b class="title">테이블타이틀</b>
		<span class="desc">테스트</span>
		<span class="right">테스트</span>
	</div>
	<div class="tblBox">
		<table class="colStyle">
			<caption>테이블 제목</caption>
			<colgroup>
				<col style="width:25%;">
				<col style="width:25%;">
				<col style="width:25%;">
				<col style="width:25%;">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">가로형 테이블</th>
					<th scope="col">가로형 테이블</th>
					<th scope="col">가로형 테이블</th>
					<th scope="col">가로형 테이블</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>테스트 테스트 테스트 테스트</td>
					<td>테스트 테스트 테스트 테스트</td>
					<td>테스트 테스트 테스트 테스트</td>
					<td>테스트 테스트 테스트 테스트</td>
				</tr>
				<tr>
					<td>테스트 테스트 테스트 테스트</td>
					<td>테스트 테스트 테스트 테스트</td>
					<td>테스트 테스트 테스트 테스트</td>
					<td>테스트 테스트 테스트 테스트</td>
				</tr>
				<tr>
					<td>테스트 테스트 테스트 테스트</td>
					<td>테스트 테스트 테스트 테스트</td>
					<td>테스트 테스트 테스트 테스트</td>
					<td>테스트 테스트 테스트 테스트</td>
				</tr>
			</tbody>
		</table>
		<ul class="conList star">
			<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
			<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
		</ul>
	</div>
	<div class="tblBox">
		<table class="rowStyle">
			<caption>테이블 제목</caption>
			<colgroup>
				<col style="width:20%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">세로형 테이블</th>
					<td>테스트 테스트 테스트 테스트</td>
				</tr>
				<tr>
					<th scope="row">세로형 테이블</th>
					<td>테스트 테스트 테스트 테스트</td>
				</tr>
				<tr>
					<th scope="row">세로형 테이블</th>
					<td>테스트 테스트 테스트 테스트</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!-- //template3 -->

											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail4.png" alt="이미지 리스트" />
											<textarea class="soundOnly">
<!-- template4 -->
<div class="templateSection">
	<strong class="mainTitle">타이틀 타이틀 타이틀 타이틀 타이틀</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="imgList">
		<div><img src="/images/cms/template/img_small_ex.png" alt=""><br />이미지 사이즈 : 230*300</div>
		<div><img src="/images/cms/template/img_small_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_small_ex.png" alt=""><br />이미지 설명</div>
	</div>
	<div class="imgList cols2">
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 사이즈 : 550*360</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
	</div>
	<div class="imgList cols3">
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 사이즈 : 550*360</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
	</div>
	<div class="imgList cols4">
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 사이즈 : 550*360</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
	</div>
	<div class="imgList cols5">
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 사이즈 : 550*360</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
	</div>
	<div class="imgList cols6">
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 사이즈 : 550*360</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
		<div><img src="/images/cms/template/img_ex.png" alt=""><br />이미지 설명</div>
	</div>
</div>
<!-- //template4 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail5.png" alt="컨텐츠 리스트" />
											<textarea class="soundOnly">
<!-- template5 -->
<div class="templateSection">
	<strong class="mainTitle">타이틀 타이틀 타이틀 타이틀</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<b class="subTitle">서브타이틀 서브타이틀 서브타이틀</b>
	<ul class="conList dot">
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
	</ul>
	<b class="subTitle">서브타이틀 서브타이틀 서브타이틀</b>
	<ul class="conList line">
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
	</ul>
	<b class="subTitle">서브타이틀 서브타이틀 서브타이틀</b>
	<ul class="conList star">
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
		<li>리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트 리스트</li>
	</ul>
	<b class="subTitle">서브타이틀 서브타이틀 서브타이틀</b>
	<ul class="conList dot">
		<li><b>리스트</b>
			<ul class="conList line">
				<li>리스트 속 리스트
					<ul class="conList star">
						<li>리스트 속 리스트 속 리스트</li>
						<li>리스트 속 리스트 속 리스트</li>
						<li>리스트 속 리스트 속 리스트</li>
					</ul>
				</li>
				<li>리스트 속 리스트</li>
				<li>리스트 속 리스트</li>
			</ul><br />
		</li>
		<li><b>리스트</b>
			<ul class="conList line">
				<li>리스트 속 리스트
					<ul class="conList star">
						<li>리스트 속 리스트 속 리스트</li>
						<li>리스트 속 리스트 속 리스트</li>
						<li>리스트 속 리스트 속 리스트</li>
					</ul>
				</li>
				<li>리스트 속 리스트</li>
				<li>리스트 속 리스트</li>
			</ul>
		</li>
	</ul>
</div>
<!-- //template5 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail6.png" alt="연혁" />
											<textarea class="soundOnly">
<!-- template6 -->
<div class="templateSection">
	<strong class="mainTitle">타이틀 타이틀 타이틀 타이틀</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<b class="subTitle">서브타이틀 서브타이틀 서브타이틀</b>
	<div class="timeList">
		<dl>
			<dt>2020</dt>
			<dd>
				<ul class="conList">
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
				</ul>
			</dd>
			<dt>2019</dt>
			<dd>
				<ul class="conList">
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
				</ul>
			</dd>
			<dt>2018</dt>
			<dd>
				<ul class="conList">
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
				</ul>
			</dd>
			<dt>2017</dt>
			<dd>
				<ul class="conList">
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
				</ul>
			</dd>
			<dt>2016</dt>
			<dd>
				<ul class="conList">
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					<li><b class="time">01.01</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
				</ul>
			</dd>
		</dl>
	</div>
</div>
<!-- //template6 -->								
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail7.png" alt="안내영역" />
											<textarea class="soundOnly">
<!-- template7 -->
<div class="templateSection">
	<strong class="mainTitle">안내영역 타이틀 안내영역 타이틀 안내영역 타이틀 (아이콘 사이즈 : 100*100)</strong>
	<div class="guideBox iconRight">
		<img src="/images/cms/template/icon_ex.png" alt="">
		테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트<br /><br />테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
	</div>
	<div class="guideBox iconLeft">
		<img src="/images/cms/template/icon_ex.png" alt="">
		<b class="title">인재아이엔씨 인재아이엔씨</b>
		테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트<br /><br />
		<a href="#" class="btnBasic">버튼</a>
	</div>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
</div>
<!-- //template7 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail8.png" alt="스텝영역" />
											<textarea class="soundOnly">
<!-- template8 -->
<div class="templateSection">
	<strong class="mainTitle">스텝영역 타이틀</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="stepList">
		<ul>
			<li class="item1"><span><b>타이틀</b><br />테스트 테스트</span></li>
			<li class="item2"><span><b>타이틀</b><br />테스트 테스트</span></li>
			<li class="item3"><span><b>타이틀</b><br />테스트 테스트</span></li>
			<li class="item4"><span><b>타이틀</b><br />테스트 테스트</span></li>
			<li class="item5"><span><b>타이틀</b><br />테스트 테스트</span></li>
			<li class="item6"><span><b>타이틀</b><br />테스트 테스트</span></li>
		</ul>
	</div>
	<div class="stepList cols2">
		<ul>
			<li class="item1"><span><b>타이틀</b><br />테스트 테스트</span></li>
			<li class="item2"><span><b>타이틀</b><br />테스트 테스트</span></li>
		</ul>
	</div>
	<div class="stepList cols3">
		<ul>
			<li class="item1"><span><b>타이틀</b><br />테스트 테스트</span></li>
			<li class="item2"><span><b>타이틀</b><br />테스트 테스트<br />테스트 테스트</span></li>
			<li class="item3"><span><b>타이틀</b><br />테스트 테스트<br />테스트 테스트</span></li>
		</ul>
	</div>
	<div class="stepList cols4">
		<ul>
			<li class="item1"><span>테스트 테스트</span></li>
			<li class="item2"><span>테스트 테스트</span></li>
			<li class="item3"><span>테스트 테스트</span></li>
			<li class="item4"><span>테스트 테스트</span></li>
		</ul>
	</div>
	<div class="stepList cols5">
		<ul>
			<li class="item1"><span>테스트 테스트</span></li>
			<li class="item2"><span>테스트 테스트</span></li>
			<li class="item3"><span>테스트 테스트</span></li>
			<li class="item4"><span>테스트 테스트</span></li>
			<li class="item5"><span>테스트 테스트</span></li>
		</ul>
	</div>
	<div class="stepList cols6">
		<ul>
			<li class="item1"><span>테스트 테스트</span></li>
			<li class="item2"><span>테스트 테스트</span></li>
			<li class="item3"><span>테스트 테스트</span></li>
			<li class="item4"><span>테스트 테스트</span></li>
			<li class="item5"><span>테스트 테스트</span></li>
			<li class="item6"><span>테스트 테스트</span></li>
		</ul>
	</div>
</div>
<!-- //template8 -->											
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail9.png" alt="설명영역 가로형" />
											<textarea class="soundOnly">
<!-- template9 -->
<div class="templateSection">
	<strong class="mainTitle">설명영역 타이틀</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="descRowList">
		<ul>
			<li>
				<b>타이틀 타이틀</b>
				<div>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</li>
			<li>
				<b>타이틀 타이틀</b>
				<div>
					<ul class="conList dot">
						<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
						<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
						<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					</ul>
				</div>
			</li>
			<li>
				<b>타이틀 타이틀 타이틀 타이틀 타이틀 타이틀 타이틀 타이틀</b>
				<div>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</li>
		</ul>
	</div>
</div>
<!-- //template9 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail10.png" alt="설명영역 세로형" />
											<textarea class="soundOnly">
<!-- template10 -->
<div class="templateSection">
	<strong class="mainTitle">설명영역 타이틀</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="descColList">
		<ul>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">
					<ul class="conList dot">
						<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
						<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					</ul>
				</div>
			</div></li>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">
					<ul class="conList dot">
						<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
						<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					</ul>
				</div>
			</div></li>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">
					<ul class="conList dot">
						<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
						<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
					</ul>
				</div>
			</div></li>
		</ul>
	</div>

	<div class="descColList cols2">
		<ul>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
		</ul>
	</div>

	<div class="descColList cols3">
		<ul>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
		</ul>
	</div>

	<div class="descColList cols4">
		<ul>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
			<li><div>
				<span class="title"><b>타이틀 타이틀 타이틀 타이틀</b></span>
				<div class="con">테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</div>
			</div></li>
		</ul>
	</div>
</div>
<!-- //template10 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail11.png" alt="아이콘영역 (아이콘 + 타이틀)" />
											<textarea class="soundOnly">
<!-- template11 -->
<div class="templateSection">
	<strong class="mainTitle">아이콘영역 타이틀 (아이콘 사이즈 : 100*100)</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<b class="subTitle">아이콘 + 타이틀</b>
	<div class="iconList colsAuto">
		<ul>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
		</ul>
	</div>
	<div class="iconList cols4">
		<ul>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀 타이틀 타이틀 타이틀 타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀 타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀 타이틀 타이틀 타이틀 타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
		</ul>
	</div>
	<div class="iconList cols5">
		<ul>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
		</ul>
	</div>
	<div class="iconList cols6">
		<ul>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
			<li><img src="/images/cms/template/icon_ex.png" alt=""><br />타이틀</li>
		</ul>
	</div>
</div>
<!-- //template11 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail12.png" alt="아이콘영역 (아이콘 + 설명)" />
											<textarea class="soundOnly">
<!-- template12 -->
<div class="templateSection">
	<strong class="mainTitle">아이콘영역 타이틀 (아이콘 사이즈 : 100*100)</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<b class="subTitle">아이콘 + 설명</b>
	<div class="iconDescList cols2">
		<div>
			<span class="icon"><img src="/images/cms/template/icon_ex.png" alt=""></span>
			<ul class="conList dot">
				<li>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트</li>
			</ul>
		</div>
		<div>
			<span class="icon"><img src="/images/cms/template/icon_ex.png" alt=""></span>
			<ul class="conList dot">
				<li>테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트</li>
			</ul>
		</div>
	</div>

	<div class="iconDescList cols3">
		<div>
			<span class="icon"><img src="/images/cms/template/icon_ex.png" alt=""></span>
			<ul class="conList dot">
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
			</ul>
		</div>
		<div>
			<span class="icon"><img src="/images/cms/template/icon_ex.png" alt=""></span>
			<ul class="conList dot">
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
			</ul>
		</div>
		<div>
			<span class="icon"><img src="/images/cms/template/icon_ex.png" alt=""></span>
			<ul class="conList dot">
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
			</ul>
		</div>
	</div>

	<div class="iconDescList cols4">
		<div>
			<span class="icon"><img src="/images/cms/template/icon_ex.png" alt=""></span>
			<ul class="conList dot">
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
			</ul>
		</div>
		<div>
			<span class="icon"><img src="/images/cms/template/icon_ex.png" alt=""></span>
			<ul class="conList dot">
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
			</ul>
		</div>
		<div>
			<span class="icon"><img src="/images/cms/template/icon_ex.png" alt=""></span>
			<ul class="conList dot">
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
			</ul>
		</div>
		<div>
			<span class="icon"><img src="/images/cms/template/icon_ex.png" alt=""></span>
			<ul class="conList dot">
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
				<li>테스트 테스트 테스트 테스트 테스트 테스트</li>
			</ul>
		</div>
	</div>
</div>
<!-- //template12 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail13.png" alt="다운로드 영역" />
											<textarea class="soundOnly">
<!-- template13 -->
<div class="templateSection">
	<strong class="mainTitle">다운로드영역 타이틀</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="downCon">
		<b class="subTitle">다운로드 타이틀</b>
		<a href="#" class="btnDown">다운로드</a>
	</div>
	<div class="downCon">
		<b class="subTitle">다운로드 타이틀</b><br />
		<a href="#" class="btnDown">다운로드</a>
	</div>
	<div class="downCon">
		<b class="subTitle">다운로드 타이틀 (아이콘 사이즈 : 100*100)</b>
		<img src="/images/cms/template/icon_ex.png" alt="">
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<a href="#" class="btnDown">다운로드</a>
	</div>
</div>
<!-- //template13 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail14.png" alt="다운로드 영역(리스트)" />
											<textarea class="soundOnly">
<!-- template14 -->
<div class="templateSection">
	<strong class="mainTitle">리스트 다운로드영역 타이틀 (아이콘 사이즈 : 100*100)</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="downList">
		<img src="/images/cms/template/icon_ex.png" alt="">
		<ul>
			<li><a href="#">
				<b>인재아이엔씨</b><br />테스트 테스트 테스트 테스트 테스트
			</a></li>
			<li><a href="#">
				<b>인재아이엔씨</b><br />테스트 테스트 테스트 테스트 테스트 테스트
			</a></li>
		</ul>
	</div>
</div>
<!-- //template14 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail15.png" alt="다운로드 영역(매뉴얼)" />
											<textarea class="soundOnly">
<!-- template15 -->
<div class="templateSection">
	<strong class="mainTitle">매뉴얼 다운로드영역 타이틀 (이미지 사이즈 : 120*150)</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="docDownList cols2">
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<span class="con"><b>타이틀</b>테스트 테스트<br /><a href="#" class="btnDown">다운로드</a></span>
		</div>
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<span class="con"><b>타이틀</b>테스트 테스트<br /><a href="#" class="btnDown">다운로드</a></span>
		</div>
	</div>
	<div class="docDownList cols3">
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<span class="con"><b>타이틀</b>테스트 테스트<br /><a href="#" class="btnDown">다운로드</a></span>
		</div>
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<span class="con"><b>타이틀</b>테스트 테스트<br /><a href="#" class="btnDown">다운로드</a></span>
		</div>
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<span class="con"><b>타이틀</b>테스트 테스트<br /><a href="#" class="btnDown">다운로드</a></span>
		</div>
	</div>
	<div class="docDownList cols4">
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<span class="con"><b>타이틀</b>테스트 테스트<br /><a href="#" class="btnDown">다운로드</a></span>
		</div>
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<span class="con"><b>타이틀</b>테스트 테스트<br /><a href="#" class="btnDown">다운로드</a></span>
		</div>
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<span class="con"><b>타이틀</b>테스트 테스트<br /><a href="#" class="btnDown">다운로드</a></span>
		</div>
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<span class="con"><b>타이틀</b>테스트 테스트<br /><a href="#" class="btnDown">다운로드</a></span>
		</div>
	</div>
</div>
<!-- //template15 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail16.png" alt="설명 영역(이미지 + 타이틀  + 설명)" />
											<textarea class="soundOnly">
<!-- template16 -->
<div class="templateSection">
	<strong class="mainTitle">설명영역 타이틀 (이미지 사이즈 : 120*150 *이미지 사이즈 맞춰야함)</strong>
	<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="imgDescList">
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<strong>타이틀 타이틀 타이틀 타이틀</strong>
			<ul>
				<li><b>타이틀</b><span>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</span></li>
				<li><b>타이틀 타이틀</b><span>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</span></li>
				<li><b>타이틀</b><span>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</span></li>
			</ul>
		</div>
		<div>
			<img src="/images/cms/template/img_mini_ex.png" alt="">
			<strong>타이틀 타이틀 타이틀 타이틀</strong>
			<ul>
				<li><b>타이틀</b><span>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</span></li>
				<li><b>타이틀</b><span>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</span></li>
				<li><b>타이틀</b><span>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</span></li>
			</ul>
		</div>
	</div>
</div>
<!-- //template16 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail17.png" alt="설명 영역(col)" />
											<textarea class="soundOnly">
<!-- template17 -->
<div class="templateSection">
	<strong class="mainTitle">설명영역 타이틀 (이미지 사이즈 : 120*150)</strong>
	<div class="imgTitleCon">
		<img src="/images/cms/template/img_mini_ex.png" alt="">
		<span><b>타이틀 타이틀 타이틀 타이틀</b>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</span>
	</div>
	<div class="descColCon">
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
		<p>인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</p>
		<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	</div>
</div>
<!-- //template17 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail18.png" alt="정보 목록(circle)" />
											<textarea class="soundOnly">
<!-- template18 -->
<div class="templateSection">
	<strong class="mainTitle">정보영역 타이틀 (이미지 사이즈 : 120*150)</strong>
	<p>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</p>
	<div class="infoCircleList">
		<ul>
			<li><span>인재아이엔씨</span></li>
			<li><span>인재아이엔씨 인재아이엔씨</span></li>
			<li><span>인재아이엔씨 인재아이엔씨 인재아이엔씨</span></li>
		</ul>
	</div>
</div>
<!-- //template18 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail19.png" alt="개인정보처리방침" />
											<textarea class="soundOnly">
<!-- template19 -->
<div class="templateSection">
	<strong class="mainTitle">약관영역 타이틀</strong>
	<div class="guideBox">
		테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
	</div>
	<dl class="privacyCon">
		<dt>제1조 타이틀 타이틀 타이틀 타이틀 타이틀 타이틀</dt>
		<dd>테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</dd>
		<dt>제2조 타이틀 타이틀 타이틀 타이틀 타이틀 타이틀</dt>
		<dd>
			<ol>
				<li>1. 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
					<ul>
						<li>- 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</li>
						<li>- 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</li>
					</ul>
				</li>
				<li>2. 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
					<ol>
						<li>① 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</li>
						<li>② 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</li>
					</ol>
				</li>
				<li>3. 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트</li>
			</ol>
		</dd>
		<dt>제3조 타이틀 타이틀 타이틀 타이틀 타이틀 타이틀</dt>
		<dd>
			<div class="tblBox">
				<table class="colStyle">
					<caption>타이틀 타이틀</caption>
					<colgroup>
						<col><col>
					</colgroup>
					<thead>
						<tr>
							<th scope="col">테스트 테스트 테스트 테스트</th>
							<th scope="col">테스트 테스트 테스트 테스트</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>테스트 테스트 테스트 테스트</td>
							<td>테스트 테스트 테스트 테스트</td>
						</tr>
						<tr>
							<td>테스트 테스트 테스트 테스트</td>
							<td>테스트 테스트 테스트 테스트</td>
						</tr>
						<tr>
							<td>테스트 테스트 테스트 테스트</td>
							<td>테스트 테스트 테스트 테스트</td>
						</tr>
					</tbody>
				</table>
			</div>
		</dd>
		<dt>제4조 타이틀 타이틀 타이틀 타이틀 타이틀 타이틀</dt>
		<dd>
			<ul>
				<li>- 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
					<ul>
						<li>- 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</li>
						<li>- 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</li>
					</ul>
				</li>
				<li>- 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
					<ol>
						<li>① 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</li>
						<li>② 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨 인재아이엔씨</li>
					</ol>
				</li>
			</ul>
		</dd>
	</dl>
</div>
<!-- //template19 -->
											</textarea>
										</a></li>
										<li><a href="#">
											<img src="/images/cms/template/img_thumbnail20.png" alt="사이트맵" />
											<textarea class="soundOnly">
<!-- template20 -->
<div class="templateSection">
	<strong class="mainTitle">사이트맵 타이틀</strong>
	<div class="sitemapCon">
		<ul>
			<li><a href="#">1 타이틀</a>
				<ul>
					<li><a href="#">1-1 타이틀</a>
						<ul>
							<li><a href="#">1-1-1 타이틀</a></li>
							<li><a href="#">1-1-2 타이틀</a></li>
							<li><a href="#">1-1-3 타이틀</a></li>
						</ul>
					</li>
					<li><a href="#">1-2 타이틀</a>
						<ul>
							<li><a href="#">1-2-1 타이틀</a></li>
							<li><a href="#">1-2-2 타이틀</a></li>
							<li><a href="#">1-2-3 타이틀</a></li>
						</ul>
					</li>
				</ul>
			</li>
			<li><a href="#">2 타이틀</a>
				<ul>
					<li><a href="#">2-1 타이틀</a>
						<ul>
							<li><a href="#">2-1-1 타이틀</a></li>
							<li><a href="#">2-1-2 타이틀</a></li>
							<li><a href="#">2-1-3 타이틀</a></li>
						</ul>
					</li>
					<li><a href="#">2-2 타이틀</a>
						<ul>
							<li><a href="#">2-2-1 타이틀</a></li>
							<li><a href="#">2-2-2 타이틀</a></li>
							<li><a href="#">2-2-3 타이틀</a></li>
						</ul>
					</li>
				</ul>
			</li>
			<li><a href="#">3 타이틀</a>
				<ul>
					<li><a href="#">3-1 타이틀</a>
						<ul>
							<li><a href="#">3-1-1 타이틀</a></li>
							<li><a href="#">3-1-2 타이틀</a></li>
							<li><a href="#">3-1-3 타이틀</a></li>
						</ul>
					</li>
					<li><a href="#">3-2 타이틀</a>
						<ul>
							<li><a href="#">3-1-1 타이틀</a></li>
							<li><a href="#">3-1-2 타이틀</a></li>
							<li><a href="#">3-1-3 타이틀</a></li>
						</ul>
					</li>
				</ul>
			</li>
			<li><a href="#">4 타이틀</a>
				<ul>
					<li><a href="#">4-1 타이틀</a>
						<ul>
							<li><a href="#">4-1-1 타이틀</a></li>
						</ul>
					</li>
					<li><a href="#">4-2 타이틀</a>
						<ul>
							<li><a href="#">4-1-1 타이틀</a></li>
						</ul>
					</li>
				</ul>
			</li>
			<li><a href="#">5 타이틀</a></li>
			<li><a href="#">6 타이틀</a></li>
		</ul>
	</div>
</div>
<!-- //template20 -->
											</textarea>
										</a></li>
									</ul>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea divGroup cols2">
			<div class="left">
				<a href="#" class="btn_m btn_templateToggle">템플릿 보기</a>
			</div>
			<div class="right">
				<c:choose>
					<c:when test="${UserMenuContentsVo.progressStatus eq 'content_reg' || UserMenuContentsVo.progressStatus eq 'publish_cancel'}">
						<a href="#" class="btn_m on btn_publication_req">발행신청</a>
						&nbsp;&nbsp;&nbsp;
						<input type="submit" value="수정" class="btn_m on">
						<a href="#" class="btn_m btn_modalClose">취소</a>
					</c:when>
					<c:when test="${UserMenuContentsVo.progressStatus eq 'publish_req' && publishAuthYn eq 'Y' }">
						<a href="#" class="btn_m on btn_publication">발행승인</a> 
						<a href="#" class="btn_m on btn_publication_cancel">발행취소</a>
						&nbsp;&nbsp;&nbsp;
						<input type="submit" value="수정" class="btn_m on">
						<a href="#" class="btn_m btn_modalClose">취소</a>
					</c:when>
					<c:when test="${UserMenuContentsVo.progressStatus eq 'publish_complete'}">
						<input type="submit" value="수정" class="btn_m on">
						<a href="#" class="btn_m btn_modalClose">취소</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="등록" class="btn_m on">
						<a href="#" class="btn_m btn_modalClose">취소</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</div>
<script>
//var se = new SynapEditor("synapEditor", synapEditorConfig);
</script>



