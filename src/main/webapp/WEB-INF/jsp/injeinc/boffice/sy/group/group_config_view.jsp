<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 회원그룹관리
- 파일명 : group_config_view.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	//버튼이벤트
	$(".btn_groupMemberAdd").click(function(e){
		e.preventDefault();
		var targetForm = document.form;
		doCommonPop(targetForm,'userGroupAdd', "<c:url value='/boffice/sy/group/UserGroupAddList.do'/>", 800, 600);
	});
	$(".btn_groupMemberDel").click(function(e){
    	e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = {"cuIdx":trIdx};
		ajaxActionMessage("<c:url value='/boffice/sy/group/UserGroup_RmvAx.do'/>", ajaxParam, '', true);
    });
});
//회원그룹관리 수정
function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	f.action = "/boffice/sy/group/GroupConfig_Mod.do";
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->
<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>회원그룹</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="GroupConfigVo" name="GroupConfigVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="pageIndex"/>
	<form:hidden path="gcIdx"/>
	<form:hidden path="searchCondition"/>
	<form:hidden path="searchKeyword"/>
	<div class="tableBox">
		<table class="form">
			<caption>회원그룹관리</caption>
			<colgroup>
				<col class="w100">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th>
						<span class="required">*</span>
						<label for="gcName">그룹명</label>
					</th>
					<td><form:input path="gcName" size="34" class="required w100p"/></td>
				</tr>
				<tr>
					<th>
						<span class="required">*</span>
						<label for="gcDesc">그룹 소개</label>
					</th>
					<td><form:input path="gcDesc" size="34" class="required w100p"/></td>
				</tr>					
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<input type="submit" value="확인" class="btn_m on"> 
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
	</form:form>
	
	<div class="tableTitle">
		<div class="btnArea left">
			총회원 : <c:out value="${totCnt}" />명
		</div>
		<div class="btnArea right">
			<a href="#" class="btn_inline btn_groupMemberAdd">회원추가</a>
		</div>
	</div>
	
	<form:form commandName="UserGroupVo" name="UserGroupVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
	<form:hidden path="pageIndex" />	
		<div class="tableBox">
			<table class="list">
				<caption></caption>
				<colgroup>
					<col class="w50">
					<col>
					<col>
					<col>
					<col>
					<col class="w80">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>가입일</th>
						<th>최종방문일</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList}" var="result" varStatus="status">
					<tr data-idx="<c:out value="${result.ugIdx}" />">
						<td><c:out value="${(totCnt+1)-(status.count+(UserGroupVo.pageIndex-1)*UserGroupVo.recordCountPerPage)}" /></td>						
						<td><c:out value="${result.cuId}" /></td>
						<td><c:out value="${result.cuName}" /></td>
						<td><c:out value="${result.regDt}" /></td>
						<td><c:out value="${result.lastLogDt}" /></td>
						<td><a href="#" class="btn_inline btn_caption btn_groupMemberDel">삭제</a></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="6" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>	
		</div>			
	</form:form>
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
	</div>
	<form id="form" name="form" method="post" class="hidden">
		<input type="hidden" id="searchKeyword" name="searchKeyword" value=""/>
		<input type="hidden" id="gcIdx" name="gcIdx" value="<c:out value="${GroupConfigVo.gcIdx}" />" />		
	</form>
</div>
