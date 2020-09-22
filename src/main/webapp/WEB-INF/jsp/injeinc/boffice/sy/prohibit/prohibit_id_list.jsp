<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 금지아이디관리
- 파일명 : prohibit_id_list.jsp
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
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	//버튼이벤트
    $(".btn_add").click(function(e){
    	e.preventDefault();
    	var ajaxParam = {"piIdx":""};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
			var result= data.result;
			$("#actionkey").val(result.actionkey);
			$("#piIdx").val(result.piIdx);
			$("#piId").val(result.piId);
			modalOpen("#divProhibitIdForm");
    	};
    	ajaxAction("<c:url value='/boffice/sy/prohibit/ProhibitIdFormAx.do'/>", ajaxParam, ajaxOption);
    });
    $(".btn_mod").click(function(e){
    	e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
    	var ajaxParam = {"piIdx":trIdx};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
			var result= data.result;
			$("#actionkey").val(result.actionkey);
			$("#piIdx").val(result.piIdx);
			$("#piId").val(result.piId);
			modalOpen("#divProhibitIdForm");
    	};
    	ajaxAction("<c:url value='/boffice/sy/prohibit/ProhibitIdFormAx.do'/>", ajaxParam, ajaxOption);
    });
    $(".btn_del").click(function(e){
    	e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = {"piIdx":trIdx};
		ajaxActionMessage("<c:url value='/boffice/sy/prohibit/ProhibitIdRmvAx.do'/>", ajaxParam, '', true);
    });
});

function doForm(f) {
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	var ajaxUrl = "";
	if($("#actionkey").val() == "regist") {
		ajaxUrl = "<c:url value='/boffice/sy/prohibit/ProhibitIdRegAx.do' />";
	}else if($("#actionkey").val() == "modify") {
		ajaxUrl = "<c:url value='/boffice/sy/prohibit/ProhibitIdModAx.do' />";
	}
	var ajaxParam = $(f).serializeArray();
	ajaxActionMessage(ajaxUrl, ajaxParam, '', true);
	return false;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section">
		<form:form commandName="ProhibitIdVo" name="ProhibitIdVo" method="post">
		</form:form>
		<div class="tableTitle">
			<!-- <div class="btnArea left">
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div> -->
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list">
				<caption>금지아이디관리</caption>
				<colgroup>
					<!-- <col class="w50"> -->
					<col class="w50">
					<col>
					<col>
					<col>
					<col>
					<col>
					<col class="w120">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">순번</th>
						<th scope="col">아이디</th>
						<th scope="col">등록일</th>
						<th scope="col">등록아이디</th>
						<th scope="col">수정일</th>
						<th scope="col">수정아이디</th>
						<th scope="col">수정</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value="${result.piIdx}" />">
						<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
						<td><c:out value="${status.count}" /></td>
						<td><c:out value="${result.piId}" /></td>
						<td><c:out value="${result.regDt}" /></td>
						<td><c:out value="${result.regId}" /></td>
						<td><c:out value="${result.modDt}" /></td>
						<td><c:out value="${result.modId}" /></td>
						<td>
							<a href="#" class="btn_inline btn_mod">수정</a>
							<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="7" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>	
	</div>
	
<!-- ============================== 모달영역 ============================== -->
<div id="divProhibitIdForm" class="modalWrap small">
	<div class="modalTitle">
		<h3>금지아이디 등록/수정</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<form id="saveFrm" name="saveFrm" method="post" onsubmit="return doForm(this);">
		<input type="hidden" id="piIdx" name="piIdx" />
		<input type="hidden" id="actionkey" name="actionkey" />
		<div class="tableBox">
			<table class="form"> 
				<caption>금지아이디 입력/수정</caption>
				<colgroup>
					<col class="w100">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="piId">아이디</label></th>
						<td><input type="text" id="piId" name="piId" size="20" class="required w200" value=""></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<input type="submit" value="확인" class="btn_m on">
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
		</form>
	</div>
</div>

</body>
</html>
