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
<%-- ------------------------------------------------------------
- 제목 : 게시물관리
- 파일명 : bbs_comment_list.jsp
- 최종수정일 : 2020-04-03
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	$(".btn_cm_del").click(function(e){
		e.preventDefault();
		var cmIdx = $(this).closest('tr').attr("data-idx");
		var form=$("#BbsCommentVo");
		form.find("#cmIdx").val(cmIdx);
		var action = "del";
		doCommentAction(form, action);
	});
	$(".btn_cm_mod, .btn_cm_reply").click(function(e){
		e.preventDefault();
		$("tr.comment_mod").remove();
		var tr = $(this).closest('tr');
		var cmIdx = tr.attr("data-idx");
		var form=$("#BbsCommentVo").clone(true);
		
		form.attr("id","BbsCommentVo"+cmIdx);
		form.attr("name","BbsCommentVo"+cmIdx);
		
		if($(this).hasClass("btn_cm_mod")===true){
			form.find("#cmIdx").val(cmIdx);
			form.find("#actionkey").val("mod");
			form.find(".btn_comment_add").val("수정");
			var cmcont=tr.find(".cmCont p").html();
			cmcont = cmcont.replace(/&nbsp;/g, " ").replace(/<br\s*[\/]?>/gi, "\n");
			cmcont = cmcont.replace(/&.*?;/gi, function (a) { return { '&quot;': '"', '&amp;': '&', '&lt;': '<', '&gt;': '>' }[a]; });
			form.find("#cmCont").val(cmcont);
		}else{
			form.find("#uprCmIdx").val(cmIdx);
			form.find("#actionkey").val("add");
			form.find(".btn_comment_add").val("답글");
		}
		
		tr.after(form);
		$(form).wrapAll("<tr class='comment_mod'><td colspan='5'></td></tr>");
	});
	$(".btn_cm_add").click(function(e){
		e.preventDefault();
		var form = $(this).closest('form');
		var action = form.find("#actionkey").val();
		if(form.find("#cmCont").val().replace(/\s|　/gi, "").length == 0){
			alert("댓글 내용을 입력해 주세요");
			return false;
		}
		if( form.find("#cmCont").val().length > 1000){
			alert("댓글은 1000자 미만으로 작성해주세요");
			return false;
		}
		doCommentAction(form, action);
	});
});
function doCommentAction(form,action){
	if(action==""){
		alert("잘못된 접근입니다.");
		return;
	}
	var ajaxUrl="";
	switch(action){
		case "add":
			ajaxUrl = "<c:url value='/boffice_noDeco/ex/board/BbsCommentRegAx.do' />"; break;
		case "del":
			ajaxUrl = "<c:url value='/boffice_noDeco/ex/board/BbsCommentRmvAx.do' />"; break;
		case "mod":
			ajaxUrl = "<c:url value='/boffice_noDeco/ex/board/BbsCommentModAx.do' />"; break;
	}
	var ajaxParam = form.serializeArray();
	var ajaxOption = {};
	ajaxOption.success = function(data){
		alert(data.message);
		if(data.result){
			commentList(ajaxParam);
		}
	}
	ajaxAction(ajaxUrl, ajaxParam, ajaxOption);
}
function doCommentPageMove(page){
	$("#BbsCommentVo #pageIndex").val(page);
	var ajaxParam = $("#BbsCommentVo").serializeArray();
	commentList(ajaxParam);
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->
<form id="BbsCommentVo" name="BbsCommentVo" method="post">
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value='${BbsCommentVo.pageIndex}' />" />
	<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value='${BbsCommentVo.bcIdx}' />" />
	<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value='${BbsCommentVo.cbIdx}' />" />
	<input type="hidden" id="cmIdx" name="cmIdx" value="0" />
	<input type="hidden" id="uprCmIdx" name="uprCmIdx" value="0" />
	<input type="hidden" id="actionkey" name="actionkey" value="add" />
	<textarea name="cmCont" id="cmCont" row="3" class="w100p"></textarea>
	<div class="btnArea right">
		<input type="button" value="입력" class="btn_inline btn_cm_add"/>
	</div>
</form>
<div>
	<div class="tableBox">
		<table class="list">
			<colgroup>
				<col class="w50">
				<col class="*">
				<col class="w100">
				<col class="w100">
				<col class="w150">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>내용</th>
					<th>날짜</th>
					<th>작성자</th>
					<th>관리</th>
				</tr>
			</thead>
			<c:forEach items="${resultList}" var="result" varStatus="status">
				<tr data-idx="<c:out value='${result.cmIdx}' />">
					<td><c:out value="${(paginationInfo.totalRecordCount+1)-(status.count+(paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)}" /></td>
					<td class="left cmCont">
						<p style="padding-left:<c:out value="${result.cmDepth*20}" />px;"><c:out value="${cmm:textAreaToHtml(result.cmCont)}" escapeXml="false" /></p>
					</td>
					<td><c:out value="${result.regDt}"/></td>
					<td><c:out value="${result.regName}"/></td>
					<td>
					<c:if test="${result.cmDelYn == 'N'}">
						<a href="#" class="btn_s btn_cm_mod">수정</a>
						<a href="#" class="btn_s btn_warning btn_cm_reply">답글</a>
						<a href="#" class="btn_s btn_caption btn_cm_del">삭제</a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList)==0}">
				<tr>
					<td colspan="5" class="empty"><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
		</table>
	</div>
</div>
<div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doCommentPageMove" />
</div>