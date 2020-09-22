<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 권한설정
- 파일명 : role_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 수정중
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<c:import url="/layout/cms/head.jsp" ></c:import>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	
	//전체선택
    $("input[name=checkAll]").click(function() {
		if($(this).is(":checked"))
			$("input[name^=checkItem]").prop("checked", true);
		else
			$("input[name^=checkItem]").prop("checked", false);
    });
	
    $(".btn_selected").click(function(e){
    	e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		opener.fn_roleAdd(trIdx);
		
	    window.close();
	});
	
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
<div class="modalWrap popup">
	<div class="modalTitle">
		<h3>권한선택</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<div class="tableBox">
			<table class="list useBtn">
				<caption>관리자 목록</caption>
				<colgroup>
					<%-- <col class="w50"> --%>
					<col class="w30p">
					<col>
					<col class="w70">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">권한명</th>
						<th scope="col">권한ID</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value="${result.roleIdx}"/>|<c:out value="${result.roleName}"/>">
						<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
						<td class="left"><c:out value="${result.roleName}" /></td>
						<td class="left"><c:out value="${result.roleIdx}" /></td>
						<td><a href="#" class="btn_inline btn_selected">선택</a></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="2" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>	
		</div>
		<div class="btnArea">
			<input type="submit" value="확인" class="btn_m on">
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
	</div>


</body>
</html>
