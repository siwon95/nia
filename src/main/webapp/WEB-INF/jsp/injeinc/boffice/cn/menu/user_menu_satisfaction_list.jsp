<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<%-- ------------------------------------------------------------
- 제목 : 컨텐츠관리 > 만족도 보기
- 파일명 : user_menu_satisfaction_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	//버튼이벤트
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		document.myForm.idx.value = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = $(document.myForm).serializeArray();
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
    		var modal_id = "modal_satisfactionList";
    		var modal_url = "/boffice_noDeco/cn/menu/User_Menu_Satisfaction_List.do";
    		var modal_param = $(document.myForm).serializeArray();
    		var modal_class = ""; //wide, small
    		ajaxModal(modal_id, modal_url, modal_param, modal_class);
    	};
    	ajaxAction("<c:url value='/boffice/cn/menu/User_Menu_Satisfaction_Action.do' />", ajaxParam, ajaxOption);
	});
});

var searchFunc = function(f){
	var modal_id = "modal_satisfactionList";
	var modal_url = "/boffice_noDeco/cn/menu/User_Menu_Satisfaction_List.do";
	var modal_param = $(document.myForm).serializeArray();
	var modal_class = ""; //wide, small
	ajaxModal(modal_id, modal_url, modal_param, modal_class);
	return false;
};
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>만족도조사 결과보기</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form name="myForm" method="post" class="searchListPage" onsubmit="return doSearch(this);">
	<input type="hidden" name="siteCd" value="<c:out value="${param.siteCd}"/>"/>
	<input type="hidden" name="menuCode" value="<c:out value="${param.menuCode}"/>"/>
	<input type="hidden" name="idx"/>
	<input type="hidden" name="mode" value="satisfaction_delete"/>
	</form>
	
	<div class="tableBox">
		<table class="list">
			<caption>만족도조사목록</caption>
			<colgroup>
				<col class="w50">
				<col>
				<col class="w100">
				<col class="w100">
				<col class="w100">
				<col class="w70">
			</colgroup>
			<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>점수</th>
				<th>의견</th>
				<th>등록일</th>
				<th>삭제</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${satisfactionList}" var="result" varStatus="status">
				<tr data-idx="<c:out value="${result.IDX}"/>">
					<td>
						<c:out value="${(paginationInfo.totalRecordCount+1)-(status.count+(userMenuSatisfactionVo.pageIndex-1)*userMenuSatisfactionVo.recordCountPerPage)}" />
					</td>
					<td align="center"><c:out value="${result.USER_ID}"/></td>					
					<td><c:out value="${result.POINT}"/></td>
					<td><c:out value="${result.CONTENTS}"/></td>
					<td><c:out value="${result.REG_DT}"/></td>
					<td><a href="#" class="btn_inline btn_caption btn_del">삭제</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
	</div>
</body>
</html>
