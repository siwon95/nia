
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 회원목록
- 파일명 : user_list.jsp
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
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/sy/user/User_Form.do' />";
		$("#UserVo #userMenu").val("form");
		$("#UserVo #doSav").val("");
		$("#UserVo #logKdCd").val("");
		$("#UserVo #cuIdx").val("");
		var modal_param = $("#UserVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var trId = $(this).parents("tr").eq(0).attr("data-id");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/sy/user/User_View.do' />";
		$("#UserVo #userMenu").val("");
		$("#UserVo #doSav").val(trId);
		$("#UserVo #logKdCd").val("V");
		$("#UserVo #cuIdx").val(trIdx);
		var modal_param = $("#UserVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
});

var searchFunc = function(f){
	f.doSav.value = f.searchKeyword.value;
	f.logKdCd.value= "S";
	
	if($('input[id=scRegDtSt]').val() != '' || $('input[id=scRegDtEd]').val() != '') {
		if($('input[id=scRegDtSt]').val() == '') {
			alert('가입일을 입력해 주셔야 합니다.');
			$('input[id=scRegDtSt]').focus();
			return false;
		} else if($('input[id=scRegDtEd]').val() == ''){
			alert('가입일을 입력해 주셔야 합니다.');
			$('input[id=scRegDtEd]').focus();
			return false;
		}
	}
	
	if($('input[id=scLastLogDtSt]').val() != '' || $('input[id=scLastLogDtEd]').val() != '') {
		if($('input[id=scLastLogDtSt]').val() == '') {
			alert('최종방문일을 입력해 주셔야 합니다.');
			$('input[id=scLastLogDtSt]').focus();
			return false;
		} else if($('input[id=scLastLogDtEd]').val() == ''){
			alert('최종방문일을 입력해 주셔야 합니다.');
			$('input[id=scLastLogDtEd]').focus();
			return false;
		}
	}
};
</script>
<!-- //페이지 전용 스타일/스크립트 -->
</head>
<body>
	<div class="section">
		<form:form commandName="UserVo" name="UserVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<form:hidden path="cuIdx" />
		<form:hidden path="userMenu" />
		<input type="hidden" name="doSav" />
		<input type="hidden" name="logKdCd" />
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<tbody>
					<tr>
						<th scope="col">가입일</th>
						<td>
							<label for="scRegDtSt" class="hidden">가입일(부터)</label>
							<form:input path="scRegDtSt" class="useDatepicker dateFrom" /> ~
							<label for="scRegDtEd" class="hidden">가입일(까지)</label>
							<form:input path="scRegDtEd" class="useDatepicker dateTo" />
						</td>
						<th scope="col">최종방문일</th>
						<td>
							<label for="scLastLogDtSt" class="hidden">최종방문일(부터)</label>
							<form:input path="scLastLogDtSt" class="useDatepicker dateFrom" /> ~
							<label for="scLastLogDtEd" class="hidden">최종방문일(까지)</label>
							<form:input path="scLastLogDtEd" class="useDatepicker dateTo" />			
						</td>
					</tr>
					<tr>
						<th scope="col"><label for="scEmailRcvChk1">이메일수신</label></th>
						<td>
							<form:radiobutton path="scEmailRcvChk" id="scEmailRcvChk1" value=""/><label for="scEmailRcvChk1">전체</label> 
							<form:radiobutton path="scEmailRcvChk" id="scEmailRcvChk2" value="Y" /><label for="scEmailRcvChk2">동의</label> 
							<form:radiobutton path="scEmailRcvChk" id="scEmailRcvChk3" value="N" /><label for="scEmailRcvChk3">거부</label> 
						</td>
						<th scope="col"><label for="scSmsRcvYn1">SMS수신</label></th>
						<td>
							<form:radiobutton path="scSmsRcvYn" id="scSmsRcvYn1" value=""/><label for="scSmsRcvYn1">전체</label> 
							<form:radiobutton path="scSmsRcvYn" id="scSmsRcvYn2" value="Y" /><label for="scSmsRcvYn2">동의</label> 
							<form:radiobutton path="scSmsRcvYn" id="scSmsRcvYn3" value="N" /><label for="scSmsRcvYn3">거부</label> 
						</td>
						<th scope="col"><label for="searchCondition">검색어</label></th>
						<td>
							<form:select path="searchCondition" >
								<form:option value="" label="전체"/>
								<form:option value="name" label="이름"/>
								<form:option value="id" label="아이디"/>
								<form:option value="hp" label="핸드폰번호"/>
							</form:select>
							<label for="searchKeyword" class="hidden">검색어</label>
							<form:input path="searchKeyword" size="30"/>
							 <!-- (핸드폰번호 선택시 '-' 를 빼고 입력) -->
						</td>
					</tr>	
				</tbody>		
			</table>
			<input type="submit" value="검색" class="btn_inline btn_search">
		</div>
		</form:form>
		
		<div class="tableTitle">
			<!-- <div class="btnArea left">
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div> -->
			<div class="btnArea right">
				<a href="#" class="btn_inline btn_batch">일괄등록</a>
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list">
				<caption>회원목록</caption>
				<colgroup>
					<!-- <col class="w50"> -->
					<col class="w50">
					<col class="w90">
					<col class="w90">
					<col>
					<col>
					<col class="w150">
					<col class="w150">
					<col class="w80">
					<col class="w80">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">순번</th>
						<th scope="col">아이디</th>
						<th scope="col">이름</th>
						<th scope="col">전화번호</th>
						<th scope="col">핸드폰번호</th>
						<th scope="col">가입일</th>
						<th scope="col">최종방문일</th>
						<th scope="col">일괄등록</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList}" var="result" varStatus="status">
					<tr data-idx="<c:out value="${result.cuIdx}" />" data-id="<c:out value="${result.cuId}" />">
						<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
						<td><c:out value="${(totCnt+1)-(status.count+(UserVo.pageIndex-1)*UserVo.recordCountPerPage)}" /></td>						
						<td><c:out value="${result.cuId}" /></td>
						<td><c:out value="${result.cuName}" /></td>
						<td><c:out value="${result.telNum}" /></td>
						<td><c:out value="${result.hpNum}" /></td>
						<td><c:out value="${result.regDt}" /></td>
						<td><c:out value="${result.lastLogDt}" /></td>
						<td>일괄등록칸</td>
						<td>
							<a href="#" class="btn_inline btn_mod">수정</a>
							<!-- <a href="#" class="btn_inline btn_caption btn_del">삭제</a> --><!-- 기능추가 --> 
						</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) eq 0}">
					<tr>
						<td colspan="8"  class="empty">검색결과가 없습니다.</td>						
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>
	</div>
</body>
</html>
