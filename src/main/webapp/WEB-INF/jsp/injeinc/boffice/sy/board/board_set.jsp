<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%-- ------------------------------------------------------------
- 제목 : 게시판관리
- 파일명 : board_set.jsp
- 최종수정일 : 2019-04-29
- 상태 : 2차대상
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<c:import url="/layout/cms/head.jsp" ></c:import>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	<c:if test="${param.mode eq 'regEnd'}">
	parent.doSelectNode("<c:out value="${param.cbCd}"/>");
	parent.$("html, body").scrollTop(0);
	alert("게시판이 추가 되었습니다.\n상세정보를 입력해 주십시요!");
	</c:if>
	<c:if test="${not empty CmsBbsVo.cbName and not empty CmsBbsVo.cbCd}">
	var treeCurrentHtml = parent.$("#<c:out value="${CmsBbsVo.cbCd}"/> > a").html();
	var treeCurrentName = treeCurrentHtml.split("</i>")[1];
	parent.$("#<c:out value="${CmsBbsVo.cbCd}"/> > a").html(treeCurrentHtml.replace(treeCurrentName, "<c:out value="${CmsBbsVo.cbName}"/>"));
	</c:if>
	
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>

	//버튼이벤트
	$(".btn_save").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.save.msg" />")){
			return;
		}
		if($("#cbIdx").val() == 0){
			alert("게시판을 선택해주세요.");
			return;
		}
		$("#mode").val("board");
		$("#BoardVo").attr("action", "/boffice/sy/board/BoardSetReg.do").submit();
	});
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		if($("#cbIdx").val() == 0){
			alert("게시판을 선택해주세요.");
			return;
		}
		$("#mode").val("delete");
		$("#BoardVo").attr("action", "/boffice/sy/board/BoardSetReg.do").submit();
	});
	$(".btn_rowAdd").click(function(e){
		e.preventDefault();
		var clone = $("input[name='labelOrdNoArr']").eq(0).parent().parent().clone();
		$(this).parent().parent().before(clone);
		clone.find("input[type='text']").val("");
		clone.find("select").val("");
		clone.find("input[type='checkbox']").prop("checked", false);
	});
	$(document).on("click",".btn_rowDel",function(e){
		e.preventDefault();
		console.log($("#boardRow tbody tr").length);
		if($("#boardRow tbody tr").length < 3){
			alert("최소한 1개의 항목이 있어야 합니다.");
			return;
		}
		$(this).parent().parent().remove();
	});
	$(".btn_templateReset").click(function(e){
		e.preventDefault();
		document.location.href="/boffice/sy/board/BoardSetPop.do?cbIdx=<c:out value="${param.cbIdx}"/>&bbsTempletGbn="+document.BoardVo.bbsTempletGbn.value;
	});
	$(".btn_getCopy").click(function(e){
		e.preventDefault();
		if($("#cbIdx").val()==0 || $("#cbIdx").val()==""){
			alert("게시판을 선택해 주세요");
			return false;
		}
		window.open("/boffice_noDeco/sy/board/BoardList.do?mgrSiteCd=<c:out value="${param.mgrSiteCd}"/>&cbIdx=<c:out value="${param.cbIdx}"/>","winPopCmsBoard","width=320,height=600,scrollbars=yes");
	});
	$(".btn_overlapCheck").click(function(e){
		e.preventDefault();
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/sy/board/CmsBbsCntAx.do'/>",
			data : {"cbCd":$("#cbCd").val()},
			dataType : "json",
			success:function(data) {
				if(data.result) {
					if(data.resultCnt > 0) {
						$("#jungbok_yn").val("N");
						$("#cbCd").val("");
						alert("중복되는 아이디 입니다.");
					}else{
						$("#jungbok_yn").val("Y");
						alert("사용가능한 아이디입니다.");
					}
				}else{
					alert(data.message);
				}
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
	});
	$(".btn_boardContent").click(function(e){ //게시물관리
		e.preventDefault();
		window.open("/boffice/ex/board/BbsContentList.do?mgrSiteCd=<c:out value="${CmsBbsVo.mgrSiteCd}"/>&treeLocationId=<c:out value="${CmsBbsVo.cbCd}"/>");
	});
	
	//입력이벤트
	$(document).on("change","select[name='contentMappingArr']",function(){
		var objValue = $(this).val();
		var targetSelect = $(this).parent().next().children("select[name='labelPropGbnArr']");
		targetSelect.attr('disabled', 'true');
		switch(objValue){
			case 'NO_CONT': targetSelect.val(''); break;
			case 'REG_DT': targetSelect.val(''); break;
			case 'COUNT_CONT': targetSelect.val(''); break;
			case 'MW_STATUS_CONT': targetSelect.val(''); break;
			case 'SUB_CONT': targetSelect.val('16020100'); break;
			case 'CHARGE_NAME_CONT': targetSelect.val('16020100'); break;
			case 'NAME_CONT': targetSelect.val('16021000'); break;
			case 'CLOB_CONT': targetSelect.val('16020200'); break;
			case 'CAPTION_CONT': targetSelect.val('16020200'); break;
			case 'ADDR_CONT': targetSelect.val('16020400'); break;
			case 'EMAIL_CONT': targetSelect.val('16021200'); break;
			case 'TEL_CONT': targetSelect.val('16021100'); break;
			case 'PHONE_CONT': targetSelect.val('16020500'); break;
			case 'CHARGE_TEL_CONT': targetSelect.val('16020500'); break;
			case 'M_LINK_CONT': targetSelect.val('16020300'); break;
			case 'THUMNAIL_CONT': targetSelect.val('16020300'); break;
			case 'FILE_CONT': targetSelect.val('16020600'); break;
			case 'OPEN_YN_CONT': targetSelect.val('16020800'); break;
			case 'ANS_YN_CONT': targetSelect.val('16020800'); break;
			case 'ANS_COMP_CONT': targetSelect.val('16020800'); break;
			case 'CD_SUBJECT': targetSelect.val('16021600'); break;
			case 'SHORT_CUT': targetSelect.val('16020300'); break;
			case 'GUB_PASSWORD': targetSelect.val('16020110'); break;
			case 'CLOB_CONT2': targetSelect.val('16020200'); break;
			case 'CAPTION_CONT': targetSelect.val('16020200'); break;
			default: targetSelect.val('').removeAttr('disabled'); break;
		}
	});
});

function checkedArr(obj){
	var targetObjName = obj.attr("name")+"Arr";
	var targetObj = obj.parents("tr").eq(0).find("input[name='"+targetObjName+"']");
	if(obj.is(":checked")) {
		targetObj.val("Y");
	}else{
		targetObj.val("N");
	}
}

function doForm(f) {
	$("input[name='labelOrdNoArr']").each(function() {
		if($(this).val() == "") {
			alert("번호를 입력해 주세요");
			$(this).focus();
			return false;
		}
		checkedArr($(this).parents("tr").eq(0).find("input[name='webUseYn']"));
		checkedArr($(this).parents("tr").eq(0).find("input[name='mobileUseYn']"));
		checkedArr($(this).parents("tr").eq(0).find("input[name='searchLabelUseYn']"));
		checkedArr($(this).parents("tr").eq(0).find("input[name='labelCompYn']"));
		checkedArr($(this).parents("tr").eq(0).find("input[name='viewUseYn']"));
		checkedArr($(this).parents("tr").eq(0).find("input[name='regUseYn']"));
	});
	var checkObj = $("select[name='contentMappingArr']");
	for(i=0; i<checkObj.length; i++){
		var num = 0;
		for(j=0; j<checkObj.length; j++){
			if(checkObj.eq(i).val() == checkObj.eq(j).val()){
				num++;
			}
			if(num == 2){
				alert("중복된 컬럼이 존재합니다.");
				checkObj.eq(j).focus();
				return false;
			}
		}
	}
	
	$("select[name=labelPropGbnArr]").removeAttr('disabled');
	$("select[name=itemCodeArr]").removeAttr('disabled');
	return true;
}

var doubleSubmitFlag = false;
function doBoardAdd(f){	
	if($("#sameLevel1").prop("checked") == false && $("#sameLevel2").prop("checked") == false){
		alert("추가 옵션을 선택해 주십시요.");
		$("#sameLevel1").focus();
		return false;
	}
	if($("#groupYn1").prop("checked") == false && $("#groupYn2").prop("checked") == false){
		alert("타입을 선택해 주십시요.");
		$("#groupYn1").focus();
		return false;
	}
	if($("#cbCd").val()=="") {
		alert("코드를 입력해주세요.");
		$("#cbCd").focus();	
		return false;
	}
	if($("#jungbok_yn").val()!="Y"){
		alert("코드 중복체크를 확인해주십시오");
		$(".btn_overlapCheck").focus();	
		return false;
	}
	if(doubleSubmitFlag){
		alert("게시판 추가중입니다.");
		return false;
	}else{
		doubleSubmitFlag = true;
		if(confirm("추가하시겠습니까?")){
			return true;
		}
	}
	
	
	
	
	
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<c:set var="mgrSiteCd" value="${fn:trim(param.mgrSiteCd)}"/>
	<c:choose>
		 <c:when test="${CmsBbsVo.groupYn ne 'Y'}">
		 	<c:set var="baordTypeTxt" value="게시판"/>
		 </c:when>
		 <c:otherwise>
		 	<c:set var="baordTypeTxt" value="그룹"/>
		 </c:otherwise>
	</c:choose>
	<div class="tableTitle">
		<div class="btnArea">
			<div class="previewBox inline">
				<a href="#" class="btn_previewBoxOpen"><em>미리보기</em></a>
				<ul>
					<li class="item1"><a href="#" class="btn_windowPopup" data-url="/site/<c:out value="${mgrSiteCd}"/>/ex/bbs/List.do?cbIdx=<c:out value="${BoardVo.cbIdx}"/>" data-option="width=1200,height=800"><em>데스크톱</em></a></li>
					<li class="item2"><a href="#" class="btn_windowPopup" data-url="/site/<c:out value="${mgrSiteCd}"/>/ex/bbs/List.do?cbIdx=<c:out value="${BoardVo.cbIdx}"/>" data-option="width=768,height=700"><em>테블릿</em></a></li>
					<li class="item3"><a href="#" class="btn_windowPopup" data-url="/site/<c:out value="${mgrSiteCd}"/>/ex/bbs/List.do?cbIdx=<c:out value="${BoardVo.cbIdx}"/>" data-option="width=360,height=640"><em>모바일</em></a></li>
				</ul>
			</div>
			&nbsp;&nbsp;
			<a href="#" class="btn_inline btn_boardContent">게시물 관리</a>
			<a href="#" class="btn_inline btn_getCopy">설정 불러오기</a>
			<a href="#" class="btn_inline btn_templateReset">초기화</a>
			<a href="#" class="btn_inline on btn_save">저장</a>
			<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
		</div>
	</div>
	
	<form:form commandName="BoardVo"  name="BoardVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="cbIdx" />
	<form:hidden path="mListYn" value="N"/>
	<form:hidden path="modGbn" value="16010200"/>
	<form:hidden path="delGbn" value="16010200"/>
	<input type="hidden" name="mode" id="mode" value="board"/>
	<input type="hidden" name="mgrSiteCd" value="${mgrSiteCd}"/>
	
	<div class="tableBox">
		<table class="form">
			<caption><c:out value="${baordTypeTxt}" /> 상세 설정</caption>
			<colgroup>
				<col style="width:15%;">
				<col style="width:35%;">
				<col style="width:15%;">
				<col style="width:35%;">
			</colgroup>
			<tbody>
				<%-- <tr>
					<th>관리사이트</th>
					<td colspan="3">
						
						<c:set var="siteList" value="${cmm:getSiteList()}" />
						<c:forEach var="itemInfo" items="${siteList}" varStatus="status">
						<c:set var="tempMgrSiteCd" value=",${mgrSiteCd},"/>
						<c:set var="selectSiteCd" value=",${itemInfo.siteCd},"/>
						<c:if test="${fn:indexOf(tempMgrSiteCd,selectSiteCd)>-1}">
							<c:out value="${itemInfo.siteNm}"/>
						</c:if>
						</c:forEach>
						<input type="hidden" name="mgrSiteCd" value="${mgrSiteCd}"/>
					</td>
				</tr> --%>
				<tr>
					<th><c:out value="${baordTypeTxt}" /> 고유코드</th>
					<td>
						<span class="required"><b><c:out value="${BoardVo.cbIdx}"/></b></span> (코드: <b><c:out value="${CmsBbsVo.cbCd}"/></b>)
					</td>
					<th><c:out value="${baordTypeTxt}" /> 이름</th>
					<td><%-- <strong><c:out value="${CmsBbsVo.cbName}"/></strong> --%>
						<input type="text" name="cbName" value="<c:out value="${CmsBbsVo.cbName}"/>"/>
					</td>
				</tr>
				<tr>
					<th>공통권한</th>
					<td>
						<input type="checkbox" name="publicYn" id="publicYn" value="Y" <c:out value="${CmsBbsVo.publicYn eq 'Y'?'checked':''}"/>/> 사용
					</td>
					<th>사용유무</th>
					<td>
						<input type="radio" name="useYn" id="useYn1" value="Y" <c:out value="${CmsBbsVo.useYn eq 'Y'?'checked':''}"/>/>사용
						<input type="radio" name="useYn" id="useYn2" value="N" <c:out value="${CmsBbsVo.useYn eq 'N'?'checked':''}"/>/>미사용
					</td>
				</tr>
				<tr>
					<th><label for="bbsTempletGbn"><c:out value="${baordTypeTxt}" /> 템플릿</label></th>
					<td>
						<form:select path="bbsTempletGbn">
							<c:forEach var="templetInfo" items="${templetList}">
							<form:option value="${templetInfo.code}" label="${templetInfo.codeName}" />
							</c:forEach>
						</form:select>
					</td>
					<th><label for="bbsFileCnt">첨부파일</label></th>
					<td>
						<form:select path="bbsFileCnt">
							<c:forEach begin="0" end="10" varStatus="status">
							<form:option value="${status.index}" label="${status.index}" />
							</c:forEach>
						</form:select> 개
						/ <label for="fileMaxSize">파일최대용량 :</label> 
						<form:select path="fileMaxSize">
							<c:forEach items="${fileMaxSize }" var="fileMaxSize" varStatus="status">
								<form:option value="${fileMaxSize.codeName}"><c:out value="${fileMaxSize.codeName }"/>MB</form:option>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<%-- <tr id="minwonSelect">
					<th>민원기능</th>
					<td colspan="3">
						<form:radiobutton path="mwRKd" value="16040100" label="고정형" />
						<form:radiobutton path="mwRKd" value="16040200" label="부서/담당 지정" />
					</td>
				</tr> --%>
				<tr>
					<th><c:out value="${baordTypeTxt}" /> 승인 설정</th>
					<td>
						<form:radiobutton path="bbsApprYn" value="2" label="사용" />
						<form:radiobutton path="bbsApprYn" value="1" label="미사용" />
					</td>
					<%-- <th>모바일 리스트 설정</th>
					<td>
						<form:radiobutton path="mListYn" value="Y" label="사용" />
						<form:radiobutton path="mListYn" value="N" label="미사용" />
					</td> --%>
					<th>에디터 설정</th>
					<td>
						<form:radiobutton path="editorYn" value="Y" label="사용" />
						<form:radiobutton path="editorYn" value="N" label="미사용" />
					</td>
				</tr>
				<tr>
					<th>익명 설정</th>
					<td>
						<form:radiobutton path="anonymityYn" value="Y" label="사용" />
						<form:radiobutton path="anonymityYn" value="N" label="미사용" />
					</td>
					<th>관리자 답글기능</th>
					<td>
						<form:radiobutton path="replyYn" value="Y" label="사용" />
						<form:radiobutton path="replyYn" value="N" label="미사용" />
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						<form:radiobutton path="categoryUseYn" value="Y" label="사용" onclick="changeCategory();" />
						<form:radiobutton path="categoryUseYn" value="N" label="미사용" onclick="changeCategory();" />
					</td>
					<th>RSS<!-- <span class="required">(URL 수정불가)</span> --></th>
					<td>
						<form:radiobutton path="rssUseYn" value="Y" label="사용"  />
						<form:radiobutton path="rssUseYn" value="N" label="미사용"  />														
				 		<%-- <input id="rssUrl" name="rssUrl" type="text" size="50" value="http://localhost:8080/rss/yangcheon/board/${BoardVo.cbIdx}.do" readonly="readonly" /> --%>	
					</td>
				</tr>
				<tr>
					<th>썸네일사이즈</th>
					<td>
						가로 <form:input path="thumbnailWidth" label="썸네일가호" size="4"/>px ×
						세로 <form:input path="thumbnailHeight" label="썸네일세로" size="4"/>px
					</td>
					<th>페이지당개수</th>
					<td>
						<form:input path="pageCount" size="4"/>줄
					</td>
				</tr>
				<tr>
					<th>공유 설정</th>
					<td>
						<form:radiobutton path="socialYn" value="Y" label="사용"  />
						<form:radiobutton path="socialYn" value="N" label="미사용"  />
					</td>
					<th>댓글 설정</th>
					<td>
						<form:radiobutton path="commentYn" value="Y" label="사용"  />
						<form:radiobutton path="commentYn" value="N" label="미사용"  />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="tableTitle">
		<h4>권한 설정</h4>
	</div>
	<div class="tableBox">
		<table class="list">
		<thead>
			<tr>
				<th>목록</th>
				<th>읽기</th>
				<th>쓰기</th>
				<!-- <th width="16%">수정</th>
				<th width="16%">삭제</th> -->
				<th width="*">답변(사용자)</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<label for="listGbn" class="soundOnly">목록</label>
					<form:select path="listGbn" class="w90">
						<c:forEach var="gbnInfo" items="${gbnList}">
							<c:if test="${gbnInfo.code ne '16010600' && gbnInfo.code ne '16010600' }">
								<form:option value="${gbnInfo.code}" label="${gbnInfo.codeName}" />
							</c:if>
						</c:forEach>
						<%-- <c:forEach var="gbnInfo2" items="${gbnList2}">
							<c:if test="${gbnInfo.code ne '16010600' }">
								<form:option value="${gbnInfo2.code}" label="${gbnInfo2.codeName}" />
							</c:if>
						</c:forEach> --%>
					</form:select>
				</td>
				<td>
					<label for="readGbn" class="soundOnly">읽기</label>
					<form:select path="readGbn" class="w90">
						<c:forEach var="gbnInfo" items="${gbnList}">
							<c:if test="${gbnInfo.code ne '16010600' && gbnInfo.code ne '16010600' }">
								<form:option value="${gbnInfo.code}" label="${gbnInfo.codeName}" />
							</c:if>
						</c:forEach>
						<%-- <c:forEach var="gbnInfo2" items="${gbnList2}">
							<c:if test="${gbnInfo.code ne '16010600' }">
								<form:option value="${gbnInfo2.code}" label="${gbnInfo2.codeName}" />
							</c:if>
						</c:forEach> --%>
					</form:select>
				</td>
				<td>
					<label for="writeGbn" class="soundOnly">쓰기</label>
					<form:select path="writeGbn" class="w90">
						<c:forEach var="gbnInfo" items="${gbnList}">
							<c:if test="${gbnInfo.code ne '16010200' && gbnInfo.code ne '16010600' }">
								<form:option value="${gbnInfo.code}" label="${gbnInfo.codeName}" />
							</c:if>
						</c:forEach>
						<%-- <c:forEach var="gbnInfo2" items="${gbnList2}">
							<c:if test="${gbnInfo.code ne '16010200'}">
								<form:option value="${gbnInfo2.code}" label="${gbnInfo2.codeName}" />
							</c:if>
						</c:forEach> --%>
					</form:select>
				</td>
				<%-- <td>
					<form:select path="modGbn" class="w90">
						<c:forEach var="gbnInfo" items="${gbnList}">
						<form:option value="${gbnInfo.code}" label="${gbnInfo.codeName}" />
						</c:forEach>
						<c:forEach var="gbnInfo2" items="${gbnList2}">
						<form:option value="${gbnInfo2.code}" label="${gbnInfo2.codeName}" />
						</c:forEach>
					</form:select>
				</td>
				<td>
					<form:select path="delGbn" class="w90">
						<c:forEach var="gbnInfo" items="${gbnList}">
						<form:option value="${gbnInfo.code}" label="${gbnInfo.codeName}" />
						</c:forEach>
						<c:forEach var="gbnInfo2" items="${gbnList2}">
						<form:option value="${gbnInfo2.code}" label="${gbnInfo2.codeName}" />
						</c:forEach>
					</form:select>
				</td> --%>
				<td>
					<label for="answerGbn" class="soundOnly">답변(사용자)</label>
					<form:select path="answerGbn" class="w90">
						<c:forEach var="gbnInfo" items="${gbnList}">	
							<c:if test="${gbnInfo.code ne '16010600' }">
								<form:option value="${gbnInfo.code}" label="${gbnInfo.codeName}" />
							</c:if>
						</c:forEach>
						<%-- <c:forEach var="gbnInfo2" items="${gbnList2}">
							<form:option value="${gbnInfo2.code}" label="${gbnInfo2.codeName}" />
						</c:forEach> --%>
					</form:select>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	<div class="tableTitle">
		<h4>항목 및 속성 설정</h4>
	</div>
	<div class="tableBox">
		<table class="list" id="boardRow">
			<colgroup>
				<col style="width:60px;">
				<col>
				<col>
				<col>
				<col>
				<col style="width:50px;">
				<col>
				<col style="width:50px;">
				<col style="width:50px;">
				<col style="width:50px;">
				<col style="width:50px;">
				<col style="width:50px;">
				<col>
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2">번호</th>
					<th rowspan="2">필드명</th>
					<th rowspan="2">컬럼선택</th>
					<th colspan="2">설정</th>
					<th colspan="4">목록</th>
					<th colspan="2">등록</th>
					<th rowspan="2">보기</th>
					<th rowspan="2">삭제</th>
				</tr>
				<tr>
					<th>형식</th>
					<th>아이템</th>
					<th>사용</th>
					<th>넓이</th>
					<th>모바일</th>
					<th>검색</th>
					<th>사용</th>
					<th>필수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="configPropertyInfo" items="${configPropertyList}" varStatus="status">
				<tr>
					<td>
						<label for="labelOrdNoArr<c:out value="${status.index}"/>" class="soundOnly">번호</label>
						<input type="text" name="labelOrdNoArr" id="labelOrdNoArr<c:out value="${status.index}"/>" value="<c:out value='${configPropertyInfo.labelOrdNo}' />" class="w100p">
					</td>
					<td>
						<label for="labelNameArr<c:out value="${status.index}"/>" class="soundOnly">필드명</label>
						<input type="text" name="labelNameArr" id="labelNameArr<c:out value="${status.index}"/>" value="<c:out value='${configPropertyInfo.labelName}' />" class="w100p">
					</td>
					<td>
						<label for="contentMappingArr<c:out value="${status.index}"/>" class="soundOnly">컬럼선택</label>
						<select name="contentMappingArr" id="contentMappingArr<c:out value="${status.index}"/>" class="w100p">
							<option value="">선택</option>
							<option value="NO_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'NO_CONT'}">selected="selected"</c:if>>글번호</option>
							<option value="SUB_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'SUB_CONT'}">selected="selected"</c:if>>제목</option>
							<option value="CLOB_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'CLOB_CONT'}">selected="selected"</c:if>>내용</option>
							<option value="CLOB_CONT2" <c:if test="${configPropertyInfo.contentMapping eq 'CLOB_CONT2'}">selected="selected"</c:if>>내용2</option>
							<option value="NAME_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'NAME_CONT'}">selected="selected"</c:if>>성명</option>
							<option value="ADDR_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'ADDR_CONT'}">selected="selected"</c:if>>주소</option>
							<option value="EMAIL_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'EMAIL_CONT'}">selected="selected"</c:if>>이메일</option>
							<option value="TEL_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'TEL_CONT'}">selected="selected"</c:if>>전화번호</option>
							<option value="PHONE_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'PHONE_CONT'}">selected="selected"</c:if>>이동전화</option>
							<option value="REG_DT" <c:if test="${configPropertyInfo.contentMapping eq 'REG_DT'}">selected="selected"</c:if>>등록일</option>
							<option value="M_LINK_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'M_LINK_CONT'}">selected="selected"</c:if>>동영상링크</option>
							<option value="THUMNAIL_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'THUMNAIL_CONT'}">selected="selected"</c:if>>썸네일</option>
							<option value="FILE_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'FILE_CONT'}">selected="selected"</c:if>>첨부파일</option>
							<option value="MULTI_FILE_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'MULTI_FILE_CONT'}">selected="selected"</c:if>>멀티파일</option>
							<option value="CAPTION_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'CAPTION_CONT'}">selected="selected"</c:if>>자막</option>
							<%-- <option value="SUB_LINK_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'SUB_LINK_CONT'}">selected="selected"</c:if>>제목링크</option>  --%>
							<option value="OPEN_YN_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'OPEN_YN_CONT'}">selected="selected"</c:if>>공개여부</option>
							<option value="ANS_YN_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'ANS_YN_CONT'}">selected="selected"</c:if>>답변여부</option>
							<option value="ANS_COMP_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'ANS_COMP_CONT'}">selected="selected"</c:if>>답변완료통보</option>
							<option value="CHARGE_NAME_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'CHARGE_NAME_CONT'}">selected="selected"</c:if>>담당자이름</option>
							<option value="CHARGE_TEL_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'CHARGE_TEL_CONT'}">selected="selected"</c:if>>담당자연락처</option>
							<option value="CATE_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'CATE_CONT'}">selected="selected"</c:if>>카테고리</option>
							<%-- <option value="DEPARTMENT" <c:if test="${configPropertyInfo.contentMapping eq 'DEPARTMENT'}">selected="selected"</c:if>>부서명</option> --%>
							<option value="CD_SUBJECT" <c:if test="${configPropertyInfo.contentMapping eq 'CD_SUBJECT'}">selected="selected"</c:if>>부서명</option>
							<option value="EXT1" <c:if test="${configPropertyInfo.contentMapping eq 'EXT1'}">selected="selected"</c:if>>EXT1</option>
							<option value="EXT2" <c:if test="${configPropertyInfo.contentMapping eq 'EXT2'}">selected="selected"</c:if>>EXT2</option>
							<option value="EXT3" <c:if test="${configPropertyInfo.contentMapping eq 'EXT3'}">selected="selected"</c:if>>EXT3</option>
							<option value="EXT4" <c:if test="${configPropertyInfo.contentMapping eq 'EXT4'}">selected="selected"</c:if>>EXT4</option>
							<option value="EXT5" <c:if test="${configPropertyInfo.contentMapping eq 'EXT5'}">selected="selected"</c:if>>EXT5</option>
							<option value="EXT6" <c:if test="${configPropertyInfo.contentMapping eq 'EXT6'}">selected="selected"</c:if>>EXT6</option>
							<option value="EXT7" <c:if test="${configPropertyInfo.contentMapping eq 'EXT7'}">selected="selected"</c:if>>EXT7</option>
							<option value="EXT8" <c:if test="${configPropertyInfo.contentMapping eq 'EXT8'}">selected="selected"</c:if>>EXT8</option>
							<option value="EXT9" <c:if test="${configPropertyInfo.contentMapping eq 'EXT9'}">selected="selected"</c:if>>EXT9</option>
							<option value="EXT10" <c:if test="${configPropertyInfo.contentMapping eq 'EXT10'}">selected="selected"</c:if>>EXT10</option>
							<option value="COUNT_CONT" <c:if test="${configPropertyInfo.contentMapping eq 'COUNT_CONT'}">selected="selected"</c:if>>조회수</option>
							<option value="SHORT_CUT" <c:if test="${configPropertyInfo.contentMapping eq 'SHORT_CUT'}">selected="selected"</c:if>>바로가기</option>
							<option value="GUB_PASSWORD" <c:if test="${configPropertyInfo.contentMapping eq 'GUB_PASSWORD'}">selected="selected"</c:if>>비밀번호</option>
							<!-- 2020.08.04 공통코드 추가 ajhwan -->
							<option value="DEPTH_1" <c:if test="${configPropertyInfo.contentMapping eq 'DEPTH_1'}">selected="selected"</c:if>>대분류</option>
							<option value="AREA" <c:if test="${configPropertyInfo.contentMapping eq 'AREA'}">selected="selected"</c:if>>지역</option>
							<option value="TARGET" <c:if test="${configPropertyInfo.contentMapping eq 'TARGET'}">selected="selected"</c:if>>대상</option>
							<option value="TYPE" <c:if test="${configPropertyInfo.contentMapping eq 'TYPE'}">selected="selected"</c:if>>유형</option>
							<option value="CENTER" <c:if test="${configPropertyInfo.contentMapping eq 'CENTER'}">selected="selected"</c:if>>기관</option>
							<!-- 2020.08.05 공통코드 추가 이솔이 -->
							<option value="TO_DATE" <c:if test="${configPropertyInfo.contentMapping eq 'TO_DATE'}">selected="selected"</c:if>>시작일</option>
							<option value="FORM_DATE" <c:if test="${configPropertyInfo.contentMapping eq 'FORM_DATE'}">selected="selected"</c:if>>종료일</option>
							<!-- 2020.08.12 공통코드 추가 이솔이 -->
							<option value="CONTENTS" <c:if test="${configPropertyInfo.contentMapping eq 'CONTENTS'}">selected="selected"</c:if>>컨텐츠</option>
							<option value="PRO_MP_PSTVT" <c:if test="${configPropertyInfo.contentMapping eq 'PRO_MP_PSTVT'}">selected="selected"</c:if>>전문인력양성</option>
							<option value="LAW" <c:if test="${configPropertyInfo.contentMapping eq 'LAW'}">selected="selected"</c:if>>관련법령</option>
						</select>
					</td>
					<td>
						<label for="labelPropGbnArr<c:out value="${status.index}"/>" class="soundOnly">그룹</label>
						<select name="labelPropGbnArr" id="labelPropGbnArr<c:out value="${status.index}"/>" class="w100p">
							<option value="">선택</option>
							<c:forEach var="propertyInfo" items="${propertyList}">
							<option value="<c:out value="${propertyInfo.code}"/>" <c:if test="${propertyInfo.code eq configPropertyInfo.labelPropGbn}">selected="selected"</c:if>><c:out value="${propertyInfo.codeName}"/></option>
							</c:forEach>
						</select>
					</td>
					<td>
						<label for="itemCodeArr<c:out value="${status.index}"/>" class="soundOnly">아이템코드</label>
						<select name="itemCodeArr" id="itemCodeArr<c:out value="${status.index}"/>" class="w100p">
							<option value="">선택</option>
							<c:forEach var="itemInfo" items="${itemList}">
							<option value="<c:out value="${itemInfo.code}"/>" <c:if test="${itemInfo.code eq configPropertyInfo.itemCode}">selected="selected"</c:if>><c:out value="${itemInfo.codeName}"/></option>
							</c:forEach>
						</select>
						
					</td>
					<td>
						<label for="webUseYn<c:out value="${status.index}"/>" class="soundOnly">웹</label>
						<input type="checkbox" name="webUseYn" id="webUseYn<c:out value="${status.index}"/>" <c:if test="${configPropertyInfo.webUseYn eq 'Y'}">checked="checked"</c:if> />
						<input type="hidden" name="webUseYnArr" value="<c:out value="${configPropertyInfo.webUseYn}"/>" />
					</td>
					<td>
						<label for="labelProvSizeArr<c:out value="${status.index}"/>" class="soundOnly">넓이</label>
						<input type="text" name="labelProvSizeArr" id="labelProvSizeArr<c:out value="${status.index}"/>" value="<c:out value='${configPropertyInfo.labelProvSize}' />" class="w100p">
					</td>
					<td>
						<label for="mobileUseYn<c:out value="${status.index}"/>" class="soundOnly">모바일</label>
						<input type="checkbox" name="mobileUseYn" id="mobileUseYn<c:out value="${status.index}"/>" <c:if test="${configPropertyInfo.mobileUseYn eq 'Y'}">checked="checked"</c:if> />
						<input type="hidden" name="mobileUseYnArr" value="<c:out value="${configPropertyInfo.mobileUseYn}"/>" />
					</td>
					<td>
						<label for="searchLabelUseYn<c:out value="${status.index}"/>" class="soundOnly">검색</label>
						<input type="checkbox" name="searchLabelUseYn" id="searchLabelUseYn<c:out value="${status.index}"/>" value="Y" <c:if test="${configPropertyInfo.searchLabelUseYn eq 'Y'}">checked="checked"</c:if> />
						<input type="hidden" name="searchLabelUseYnArr" value="<c:out value="${configPropertyInfo.searchLabelUseYn}"/>" />
					</td>
					<td>
						<label for="regUseYn<c:out value="${status.index}"/>" class="soundOnly">등록</label>
						<input type="checkbox" name="regUseYn" id="regUseYn<c:out value="${status.index}"/>" <c:if test="${configPropertyInfo.regUseYn eq 'Y'}">checked="checked"</c:if> />
						<input type="hidden" name="regUseYnArr" value="<c:out value="${configPropertyInfo.regUseYn}"/>" />
					</td>
					<td>
						<label for="labelCompYn<c:out value="${status.index}"/>" class="soundOnly">필수</label>
						<input type="checkbox" name="labelCompYn" id="labelCompYn<c:out value="${status.index}"/>" value="Y" <c:if test="${configPropertyInfo.labelCompYn eq 'Y'}">checked="checked"</c:if> />
						<input type="hidden" name="labelCompYnArr" value="<c:out value="${configPropertyInfo.labelCompYn}"/>" />
					</td>
					<td>
						<label for="viewUseYn<c:out value="${status.index}"/>" class="soundOnly">사용</label>
						<input type="checkbox" name="viewUseYn" id="viewUseYn<c:out value="${status.index}"/>" value="Y" <c:if test="${configPropertyInfo.viewUseYn eq 'Y'}">checked="checked"</c:if> />
						<input type="hidden" name="viewUseYnArr" value="<c:out value="${configPropertyInfo.viewUseYn}"/>" />
					</td>
					<td><a href="#" class="btn_ss btn_rowDel">삭제</a></td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(configPropertyList) == 0}">
				<tr>
					<td>
						<label for="labelOrdNoArr" class="soundOnly">번호</label>
						<input type="text" name="labelOrdNoArr" id="labelOrdNoArr" class="w100p" value="1">
					</td>
					<td>
						<label for="labelNameArr" class="soundOnly">필드명</label>
						<input type="text" name="labelNameArr" id="labelNameArr" class="w100p" value="SAMPLE">
					</td>
					<td>
						<label for="contentMappingArr" class="soundOnly">컬럼선택</label>
						<select name="contentMappingArr" id="contentMappingArr" class="w100p">
							<option value="">선택</option>
							<option value="NO_CONT">글번호</option>
							<option value="CATE_CONT">카테고리</option>
							<option value="SUB_CONT">제목</option>
							<option value="NAME_CONT">성명</option>
							<option value="ADDR_CONT">주소</option>
							<option value="EMAIL_CONT">이메일</option>
							<option value="TEL_CONT">전화번호</option>
							<option value="PHONE_CONT">이동전화</option>
							<option value="REG_DT">등록일</option>
							<option value="COUNT_CONT">조회수</option>
							<option value="CLOB_CONT">내용</option>
							<option value="CLOB_CONT2">내용2</option>
							<option value="M_LINK_CONT">동영상링크</option>
							<option value="THUMNAIL_CONT">썸네일</option>
							<option value="CAPTION_CONT">자막</option>
							<option value="SUB_LINK_CONT">제목링크</option>
							<option value="OPEN_YN_CONT">공개여부</option>
							<option value="ANS_YN_CONT">답변여부</option>
							<option value="ANS_COMP_CONT">답변완료통보</option>
							<option value="MW_STATUS_CONT">진행상태</option>
							<!-- <option value="DEPARTMENT">부서명</option> -->
							<option value="CD_SUBJECT">부서명</option>
							<option value="EXT1">EXT1</option>
							<option value="EXT2">EXT2</option>
							<option value="EXT3">EXT3</option>
							<option value="EXT4">EXT4</option>
							<option value="EXT5">EXT5</option>
							<option value="EXT6">EXT6</option>
							<option value="EXT7">EXT7</option>
							<option value="EXT8">EXT8</option>
							<option value="EXT9">EXT9</option>
							<option value="EXT10">EXT10</option>
							<option value="FILE_CONT">첨부파일</option>
							<option value="TARGET">대상</option>
							<option value="TYPE">유형</option>
							<option value="DEPTH_1">대분류</option>
							<option value="AREA">지역</option>
							<!-- 2020.08.05 공통코드 추가 이솔이 -->
							<option value="TO_DATE">시작일</option>
							<option value="FORM_DATE">종료일</option>
							<option value="MULTI_FILE_CONT">멀티파일</option>
							<option value="CHARGE_NAME_CONT">담당자이름</option>
							<option value="CHARGE_TEL_CONT">담당자연락처</option>
							<option value="SHORT_CUT">바로가기</option>
							<option value="GUB_PASSWORD">비밀번호</option>
						</select>
					</td>
					<td>
						<label for="labelPropGbnArr" class="soundOnly">그룹</label>
						<select name="labelPropGbnArr" id="labelPropGbnArr" class="w100p">
							<option value="">선택</option>
							<c:forEach var="propertyInfo" items="${propertyList}">
							<option value="<c:out value="${propertyInfo.code}"/>"><c:out value="${propertyInfo.codeName}"/></option>
							</c:forEach>
						</select>
					</td>
					<td>
						<label for="itemCodeArr" class="soundOnly">아이템코드</label>
						<select name="itemCodeArr" id="itemCodeArr" class="w100p">
							<option value="">선택</option>
							<c:forEach var="itemInfo" items="${itemList}">							
							<option value="<c:out value="${itemInfo.code}"/>"><c:out value="${itemInfo.codeName}"/></option>
							</c:forEach>
						</select>
					</td>
					<td>
						<label for="webUseYn" class="soundOnly">웹</label>
						<input type="checkbox" name="webUseYn" id="webUseYn" />
						<input type="hidden" name="webUseYnArr" value="<c:out value="${configPropertyInfo.webUseYn}"/>" />
					</td>
					<td>
						<label for="labelProvSizeArr" class="soundOnly">넓이</label>
						<input type="text" name="labelProvSizeArr" id="labelProvSizeArr" value="<c:out value='${configPropertyInfo.labelProvSize}' />" class="w100p">
					</td>
					<td>
						<label for="mobileUseYn" class="soundOnly">모바일</label>
						<input type="checkbox" name="mobileUseYn" id="mobileUseYn" />
						<input type="hidden" name="mobileUseYnArr" value="<c:out value="${configPropertyInfo.mobileUseYn}"/>" />
					</td>
					<td>
						<label for="searchLabelUseYn" class="soundOnly">검색</label>
						<input type="checkbox" name="searchLabelUseYn" id="searchLabelUseYn" value="Y" />
						<input type="hidden" name="searchLabelUseYnArr" value="<c:out value="${configPropertyInfo.searchLabelUseYn}"/>" />
					</td>
					<td>
						<label for="regUseYn" class="soundOnly">등록</label>
						<input type="checkbox" name="regUseYn" id="regUseYn" value="Y" />
						<input type="hidden" name="regUseYnArr" value="<c:out value="${configPropertyInfo.regUseYn}"/>" />
					</td>
					<td>
						<label for="labelCompYn" class="soundOnly">필수</label>
						<input type="checkbox" name="labelCompYn" id="labelCompYn" value="Y" />
						<input type="hidden" name="labelCompYnArr" value="<c:out value="${configPropertyInfo.labelCompYn}"/>" />
					</td>
					<td>
						<label for="viewUseYn" class="soundOnly">보기</label>
						<input type="checkbox" name="viewUseYn" id="viewUseYn" value="Y" />
						<input type="hidden" name="viewUseYnArr" value="<c:out value="${configPropertyInfo.viewUseYn}"/>" />
					</td>
					<td><a href="#" class="btn_s btn_rowDel">삭제</a></td>
				</tr>
				</c:if>
				<tr>
					<td colspan="13"><a href="#" class="btn_inline btn_rowAdd">항목추가</a></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<!-- 
		<a href="javascript:doBoardSetReg('template');" class="btn_m btn_save">템플릿설정저장</a>
		* [템플릿설정저장]기능은 상위 [게시판 템플릿]의 데이터로 저장되어 신규 게시판 생성시 자동 세팅됨
		-->
		<a href="#" class="btn_m on btn_save">저장</a>
		<a href="#" class="btn_m btn_caption btn_del">삭제</a>
	</div>
	</form:form>
	
	<div class="tableTitle">
		<h4>게시판 추가</h4>
	</div>
	<form name="formbbs" method="post" action="/boffice/sy/board/CmsBbsReg.do" onsubmit="return doBoardAdd(this);">
	<input type="hidden" name="cbIdx" value="<c:out value="${BoardVo.cbIdx}"/>"/>
	<input type="hidden" name="jungbok_yn" id="jungbok_yn" value="N"/>
	<input type="hidden" name="mgrSiteCd" value="${mgrSiteCd}"/>
	<div class="tableBox">
		<table class="form">
			<caption>게시판 추가</caption>
			<colgroup>
				<col style="width:15%;">
				<col style="width:35%;">
				<col style="width:15%;">
				<col style="width:35%;">
			</colgroup>
			<tbody>
				<tr>
					<th><span class="required">*</span> 추가옵션</th>
					<td>
						<input name="sameLevel" title="동일 위치에 추가" id="sameLevel1" type="radio" value="Y"> <label for="sameLevel1">동일 위치에 추가</label> &nbsp;&nbsp;
						<c:if test="${CmsBbsVo.groupYn ne 'N'}">
						&nbsp;&nbsp;<input name="sameLevel" title="하위에 추가" id="sameLevel2" type="radio" value="N"> <label for="sameLevel2">하위에 추가</label>
						</c:if>
					</td>
					<th><span class="required">*</span> 타입선택</th>
					<td>
						<input name="groupYn" title="게시판" id="groupYn1" type="radio" value="N"> <label for="groupYn1">게시판</label> &nbsp;&nbsp;
						<input name="groupYn" title="그룹" id="groupYn2" type="radio" value="Y"> <label for="groupYn2">그룹</label>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span> <label for="cbCd">코드</label></th>
					<td>
						<input type="text" id="cbCd" name="cbCd" maxlength="20" class="required w100" /> 
						<a href="#" class="btn_inline btn_overlapCheck">중복체크</a>
					</td>
					<th><span class="required">*</span> <label for="cbName">게시판 이름</label></th>
					<td><input type="text" id="cbName" name="cbName" maxlength="51" class="required w100p" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<input type="submit" value="추가" class="btn_m on">
	</div>
	</form>
</body>
</html>