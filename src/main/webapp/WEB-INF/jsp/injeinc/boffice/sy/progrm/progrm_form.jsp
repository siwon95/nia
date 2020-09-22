<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 프로그램
- 파일명 : progrmRegist.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
var chkProgramNameDup = 0;

$(function(){
	//버튼이벤트
	$(".btn_programSearch").click(function(e){
		e.preventDefault();
		window.open("<c:url value='/boffice_noDeco/sy/progrm/ProgramListSearch.do'/>",'','scrollbars=yes,width=600,height=600');
	});
	$(".btn_programCheck").click(function(e){
		e.preventDefault();
		if ($("#progrmFileNm").val() == ""){
			alert("프로그램명을 입력해 주세요.");
			$("#progrmFileNm").focus();
			return;
		}
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/sy/progrm/ProgramDupChkAx.do'/>",
			data : "progrmFileNm=" + $("#progrmFileNm").val(),
			dataType : "json",
			success:function(data) {
				if(data.result > 0) {
					chkProgramNameDup = 0;
					alert('이미 등록된 프로그램명입니다.\n다른 프로그램명을 입력해주세요.');
				}else{
					chkProgramNameDup = 1;
					alert('사용가능한 프로그램명입니다.');
				}
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
	});
	
	//프로그램 파일명
	$("input[name=progrmFileNm]").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			var inputVal = $(this).val();
		    $(this).val(inputVal.replace(/[^a-zA-Z0-9-_]/g,''));
		}
	});
	$("input[name=progrmFileNm]").blur(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			var inputVal = $(this).val();
		    $(this).val(inputVal.replace(/[^a-zA-Z0-9-_]/g,''));
		}
	});
});

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	if(chkProgramNameDup == "0"){
		alert('프로그램명 중복체크를 해주세요.');
		return false;
	}
	
	f.action = "<c:url value='/boffice_noDeco/sy/progrm/ProgramRegist.do' />";
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>프로그램 등록</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="progrmVo" name="progrmVo" method="post" onsubmit="return doForm(this);">
	<input type="hidden" name="cmd" value="<c:out value='insert'/>" />
	<div class="tableBox">
		<table class="form">
			<caption>프로그램 입력폼</caption>
			<colgroup>
				<col class="w20p">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th><label for="progrmGrpNm"><span class="required">*</span>그룹명</label></th>
					<td>
						<form:input path="progrmGrpNm" maxlength="160" class="required w150" />
						<a href="#" class="btn_inline btn_programSearch" title="새창열림">검색</a>
					</td>
				</tr>
				<tr>
					<th><label for="progrmFileNm"><span class="required">*</span>프로그램명</label></th>
					<td>
						<form:input path="progrmFileNm" maxlength="50" class="required w150" />
						<a href="#" class="btn_inline btn_programCheck">중복체크</a>
					</td>
				</tr>
				<tr>
					<th><label for="progrmKoreanNm"><span class="required">*</span>프로그램명(한글)</label></th>
					<td><form:input path="progrmKoreanNm" maxlength="60" class="required w150" /></td>
				</tr>
				<tr>
					<th><label for="url"><span class="required">*</span>URL</label></th>
					<td><form:input path="url" maxlength="160" class="required w100p" /></td>
				</tr>
				<tr>
					<th><label for="progrmDc">설명</label></th>
					<td><form:textarea path="progrmDc" class="required w100p" rows="7" /></td>
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
<!-- ============================== //모달영역 ============================== -->
