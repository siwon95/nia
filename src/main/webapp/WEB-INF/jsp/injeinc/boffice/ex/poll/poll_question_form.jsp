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
- 제목 : 설문관리
- 파일명 : poll_question_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	checkType();
	
	//버튼이벤트
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		$("#PollQuestionVo").attr("action", "/boffice/ex/poll/PollQuestionRmv.do").submit();
	});
	$(".btn_sortUp").click(function(e){
		e.preventDefault();
		var $obj = $(this).parent().parent();
		var $objItem = $obj.find("input[name='pqItemArr']");
		var objItemValue = $objItem.val();
		var $prev = $obj.prev();
		var $prevItem = $prev.find("input[name='pqItemArr']");
		var prevItemValue = $prevItem.val();
		if(prevItemValue == undefined) {
			alert("더이상 순위를 올릴 수 없습니다.");
		}else{
			$objItem.val(prevItemValue);
			$prevItem.val(objItemValue);
		}
	});
	$(".btn_sortDown").click(function(e){
		e.preventDefault();
		var $obj = $(this).parent().parent();
		var $objItem = $obj.find("input[name='pqItemArr']");
		var objItemValue = $objItem.val();
		var $next = $obj.next();
		var $nextItem = $next.find("input[name='pqItemArr']");
		var nextItemValue = $nextItem.val();
		if(nextItemValue == undefined) {
			alert("더이상 순위를 내릴 수 없습니다.");
		}else{
			$objItem.val(nextItemValue);
			$nextItem.val(objItemValue);
		}
	});
	
	//입력이벤트
	$("#pqType").change(function(){
		checkType();
		changeNumberAlphabat();
	});
	$("#pqItemCnt").change(function(){
		var $trList = $("#itemTable > tbody > tr");
		var currCnt = $trList.length;
		var changeCnt = $("#pqItemCnt").val();
		if(changeCnt > currCnt) {
			for(var i = currCnt; i < changeCnt; i++) {
				var html = "\n<tr>";
				if($("#pqType").val() == "checkbox") {
					html += "\n\t<td scope=\"row\">"+alphabat.substring(i, i+1)+"</td>";
				}else{
					html += "\n\t<td scope=\"row\">"+(i+1)+"</td>";
				}
					html += "\n\t<td>";
					html += "\n\t\t<input type=\"text\" name=\"pqItemArr\" value=\"\" style=\"width:90%;\"/>";
					html += "\n\t</td>";
					html += "\n\t<td>";
					html += "\n\t\t<a href=\"#\" class=\"btn_s btn_sortUp\">▲</a>";
					html += "\n\t\t<a href=\"#\" class=\"btn_s btn_sortDown\">▼</a>";
					html += "\n\t</td>";
					html += "</tr>";
				$("#itemTable > tbody").append(html);
			}
		}else{
			for(var i = changeCnt; i < currCnt; i++) {
				$trList.eq(i).remove();
			}
		}
	});
});

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	if(!checkItemArr()) {
		alert("항목을 입력해 주십시오.");
		return false;
	}
	
	if($("#actionkey").val() == "regist") {
		f.action = "/boffice/ex/poll/PollQuestionReg.do";
	}else if($("#actionkey").val() == "modify") {
		f.action = "/boffice/ex/poll/PollQuestionMod.do";
	}
	return true;
}

function checkItemArr(){
	var pqType = $("#pqType").val();
	if(pqType.indexOf("text") > -1 || pqType.indexOf("title") > -1) {
		return true;
	}else{
		var itemArr = $("input[name='pqItemArr']");
		for(var i=0; i < itemArr.length; i++) {
			if(itemArr.eq(i).val() == "") {
				itemArr.eq(i).focus();
				return false;
			}
		}
		return true;
	}
}

function checkType(){
	var pqType = $("#pqType").val();
	if(pqType == "radio" || pqType == "checkbox") {
		$("#checkTr").css("display", "table-row");
		$("#etcTr").css("display", "table-row");
		$("#itemCntTr").css("display", "table-row");
		$("#itemListTr").css("display", "table-row");
		$("#itemDirectionTr").css("display", "table-row");
	}else if(pqType == "selectbox") {
		$("#checkTr").css("display", "table-row");
		$("#etcTr").css("display", "table-row");
		$("#itemCntTr").css("display", "table-row");
		$("#itemListTr").css("display", "table-row");
		$("#itemDirectionTr").css("display", "none");
	}else if(pqType == "text" || pqType == "textarea") {
		$("#checkTr").css("display", "table-row");
		$("#etcTr").css("display", "none");
		$("#itemCntTr").css("display", "none");
		$("#itemListTr").css("display", "none");
		$("#itemDirectionTr").css("display", "none");
	}else if(pqType == "title") {
		$("#checkTr").css("display", "none");
		$("#etcTr").css("display", "none");
		$("#itemCntTr").css("display", "none");
		$("#itemListTr").css("display", "none");
		$("#itemDirectionTr").css("display", "none");
	}else{
		alert("오류 : type을 알수가 없습니다.");
	}
}

var alphabat = "abcdefghijklmnopqrstuvwxyz";
function changeNumberAlphabat(){
	var $trList = $("#itemTable > tbody > tr");
	if($("#pqType").val() == "checkbox") {
		for(var i = 0; i < $trList.length; i++) {
			var $td = $trList.eq(i).find("td:first");
			var number = $td.text();
			$td.html(alphabat.substring(number-1, number));
		}
	}else{
		for(var i = 0; i < $trList.length; i++) {
			var $td = $trList.eq(i).find("td:first");
			var character = $td.text();
			character = character.replace(/ /g, "");
			character = $.trim(character);
			if(alphabat.indexOf(character) > -1) {
				$td.text(alphabat.indexOf(character)+1);
			}
		}
	}
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>질문 등록/수정</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="PollQuestionVo" name="PollQuestionVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCdIdx" />
	<form:hidden path="searchUse" />
	<form:hidden path="plIdx" />
	<form:hidden path="actionkey" />
	<form:hidden path="pqIdx" />
	<div class="tableBox">
		<table class="form">
			<caption>설문조사 질문 입력/수정</caption>
			<colgroup>
				<col class="w100">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="pqTitle">제목</label></th>
					<td><form:input path="pqTitle" class="required w100p" /></td>
				</tr>
				<tr id="detailTr">
					<th scope="row"><label for="pqDetail">상세설명</label></th>
					<td><form:textarea path="pqDetail" class="w100p" rows="5"/></td>
				</tr>
				<tr>
					<th scope="row"><label for="pqType">입력형식</label></th>
					<td>
						<c:set var="pqType" value="${PollQuestionVo.pqType}" />
						<form:select path="pqType">
							<form:option value="radio" label="객관식(단수선택)-라디오버튼형식" />
							<form:option value="selectbox" label="객관식(단수선택)-셀렉트박스형식" />
							<form:option value="checkbox" label="객관식(복수선택)-체크박스형식" />
							<form:option value="text" label="주관식(한줄입력)" />
							<form:option value="textarea" label="주관식(여러줄입력)" />
							<form:option value="title" label="제목(그룹용)" />
						</form:select>
					</td>
				</tr>
				<tr id="checkTr">
					<th scope="row">필수입력</th>
					<td>
						<form:radiobutton path="pqCheck" value="Y" label="필수" />
						<form:radiobutton path="pqCheck" value="N" label="선택" />
					</td>
				</tr>
				<tr id="etcTr">
					<th scope="row">기타입력</th>
					<td>
						<form:radiobutton path="pqEtc" value="Y" label="사용" />
						<form:radiobutton path="pqEtc" value="N" label="미사용" />
						<span class="captionText">(사용하실 경우는 마지막항목을 기타 등으로 입력하시면 됩니다.)</span>
					</td>
				</tr>
				<tr id="itemCntTr">
					<th scope="row"><label for="pqItemCnt">객관식 항목수</label></th>
					<td>
						<c:set var="pqItemCnt" value="${PollQuestionVo.pqItemCnt}" />
						<select name="pqItemCnt" id="pqItemCnt">
						<c:forEach begin="2" varStatus="status" end="26">
							<option value="<c:out value="${status.index}"/>" <c:if test="${status.index == pqItemCnt}">selected="selected"</c:if>><c:out value="${status.index}"/></option>
						</c:forEach>
						</select> 개
					</td>
				</tr>
				<tr id="itemListTr">
					<th scope="row">객관식 입력사항</th>
					<td>
						<table id="itemTable" class="list">
							<colgroup>
								<col class="w60">
								<col>
								<col class="w100">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">항목</th>
									<th scope="col">순위변경</th>
								</tr>
							</thead>
							<tbody>
							<c:set var="pqItemList" value="${!empty PollQuestionVo.pqItemList ? cmm:split(PollQuestionVo.pqItemList, '｜', pqItemCnt):'' }" />
							<c:forEach begin="0" varStatus="status" end="${pqItemCnt-1}">
								<tr>	
									<td scope="row">
										<c:if test="${pqType eq 'checkbox'}"><c:out value="${cmm:changeNumberAlphabat(status.count)}"/></c:if>
										<c:if test="${pqType ne 'checkbox'}"><c:out value="${status.count}"/></c:if>
									</td>
									<td><input type="text" name="pqItemArr" value="<c:out value="${fn:length(pqItemList) > status.index ? pqItemList[status.index]:''}"/>" class="w100p" /></td>
									<td>
										<a href="#" class="btn_s btn_sortUp">▲</a>
										<a href="#" class="btn_s btn_sortDown">▼</a>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<tr id="itemDirectionTr">
					<th scope="row"><label for="pqItemDirection">항목방향</label></th>
					<td>
						<form:radiobutton path="pqItemDirection" value="R" label="가로" />
						<form:radiobutton path="pqItemDirection" value="C" label="세로" />
					</td>
				</tr>
				<c:if test="${PollQuestionVo.actionkey eq 'modify'}">
				<tr>
					<th scope="row">등록일</th>
					<td><c:out value="${PollQuestionVo.regDt}" /></td>
				</tr>
				<tr>
					<th scope="row">수정일</th>
					<td><c:out value="${PollQuestionVo.modDt}" /></td>
				</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<input type="submit" value="<c:out value="${PollQuestionVo.actionkey eq 'regist' ? '입력':'수정'}"/>" class="btn_m on">
		<c:if test="${PollQuestionVo.actionkey eq 'modify'}">
		<a href="javascript:doPollQuestionRmv();" class="btn_inline btn_caption btn_del">삭제</a>
		</c:if>
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
	</form:form>
</div>
<!-- ============================== //모달영역 ============================== -->

