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
- 제목 : 컨텐츠관리 > 컨텐츠 작업내역
- 파일명 : user_menu_contents_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	//버튼이벤트
	$(".btn_repair").click(function(e){
		e.preventDefault();
		if(!confirm("컨텐츠 내용을 가져오시겠습니까?")){
			return;
		}

		$("#UserMenuContentsVo #contentSeqNo").val($(this).parents("tr").eq(0).attr("data-idx"));
		$("#UserMenuContentsVo #repairYn").val("Y");
		var modal_id = "modal_contentForm";
		var modal_url = "/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do";
		var modal_param = $("#UserMenuContentsVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>컨텐츠이력관리</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="UserMenuContentsVo"  name="UserMenuContentsVo" method="post">
	<form:hidden path="siteCd" />
	<form:hidden path="menuCode" />
	<form:hidden path="contentSeqNo"/>
	<form:hidden path="repairYn"/>
	</form:form>
	<div class="tableDesc">
		*가져오기 화면에서 이전 컨텐츠로 복구가 가능합니다.
	</div>
	<div class="tableBox scrolled h400">
		<table class="list">
			<caption></caption>
			<colgroup>
				<col class="w50">
				<col>
				<col>
				<col>
				<col class="w80">
				<col class="w100">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>등록자</th>
					<th>발행신청일</th>
					<th>발행승인일</th>
					<th>상태</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${UserMenuContentsVoList}" var="result" varStatus="status">
				<tr data-idx="<c:out value="${result.contentSeqNo}" />">
					<td><c:out value="${status.count}" /></td>
					<td>
						<c:out value="${result.regNm}" />
					</td>
					<td><c:out value="${fn:substring(result.publishReqDt,0,10)}" /></td>
					<td><c:out value="${fn:substring(result.publishDt,0,10)}" /></td>
					<td>
						<c:out value="${result.progressStatusNm }"/>
					</td>
					<td><a href="#" class="btn_inline btn_repair">가져오기</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
