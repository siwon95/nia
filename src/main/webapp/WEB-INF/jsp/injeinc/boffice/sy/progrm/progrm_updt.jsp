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
- 파일명 : progrmUpdt.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
var actionKey = "";
$(function(){
	<c:if test="${fn:contains(progrmVo.progrmFileNm, 'getContents_')}">
	$('#progrmFileNm, #progrmKoreanNm, #url, #progrmDc').attr('readonly', true);
	</c:if>
	
	//버튼이벤트
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		actionKey = "delete";
    	$("#progrmVo").submit();
	});
});

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	if(actionKey == "delete"){
		f.action = "<c:url value='/boffice/sy/progrm/ProgramDelete.do' />";
	}else{
		f.action = "<c:url value='/boffice/sy/progrm/ProgramUpdt.do' />";
	}
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>프로그램 수정</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="progrmVo" method="post" onsubmit="return doForm(this);">
	<input type="hidden" name="cmd" value="<c:out value='update'/>" />
	<input type="hidden" name="tmp_SearchElementName" value="" />
	<input type="hidden" name="pageIndex" value="<c:out value='${param.pageIndex}'/>"/>
	<input type="hidden" name="searchCondition" value="<c:out value='${param.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${param.searchKeyword}'/>"/>
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
						<c:out value="${progrmVo.progrmGrpNm }"/>
						<form:hidden path="progrmGrpNm" maxlength="160" />
					</td>
				</tr>
				<tr>
					<th><label for="progrmFileNm"><span class="required">*</span>프로그램명</label></th>
					<td>
						<c:out value="${progrmVo.progrmFileNm }"/>
						<form:hidden path="progrmFileNm" maxlength="50" />
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
		<input type="submit" value="수정" class="btn_m on">
		<a href="#" class="btn_m btn_caption btn_del">삭제</a>
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
	</form:form>
</div>
<!-- ============================== //모달영역 ============================== -->
