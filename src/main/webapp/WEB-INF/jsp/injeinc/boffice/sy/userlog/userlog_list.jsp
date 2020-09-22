<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 로그인 기록
- 파일명 : userlog_list.jsp
- 작성일 : 2018-01-31
- 작성자 : 김미영 대리
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<script type="text/javascript">
//페이징처리 
function doUserLogPag(pageIndex) {
	document.UserLogVo.pageIndex.value = pageIndex;
	document.UserLogVo.action = "<c:url value='/boffice/sy/userlog/UserLog_List.do' />";
	document.UserLogVo.submit();
}

//리스트
function doUserLogList(pageIndex) {
	if(pageIndex == null) {
		document.UserLogVo.pageIndex.value = 1;	
	} else {
		document.UserLogVo.pageIndex.value = pageIndex;
	}
	
	//var temp = $('input[name=scRegDtSt]').val().replace(/-/g, '');
	
	document.UserLogVo.action = "<c:url value='/boffice/sy/userlog/UserLog_List.do' />";
	document.UserLogVo.submit();
}

//삭제
function doUserLogRmv(ulIdx) {
	if(confirm("삭제 하시겠습니까?")) {
		document.UserLogVo.ulIdx.value = ulIdx;
	 	document.UserLogVo.action = "/boffice/sy/userlog/doUserLogRmv.do";
		document.UserLogVo.submit();		
	}
}

//일괄삭제
function doUserLogCheckRmv() {
	var ulIdx = new Array();
	var count = 0;
	
	$('input:checkbox[id="checkbox"]:checked').each(function(idx){
		ulIdx[idx] = this.value;
		count++;
	});
	
	if(count <= 0) {
		alert('선택된 회원이 없습니다.');
		return;
	}
	
	if(confirm("일괄삭제 하시겠습니까?")) {	
		document.UserLogVo.ulIdx.value = ulIdx;
	 	document.UserLogVo.action = "/boffice/sy/userlog/doUserLogCheckRmv.do";
		document.UserLogVo.submit();
	}
}

function doUserLogClear() {
	if(confirm("10일전까지 성공/실패 기록이 모두 삭제됩니다. \n계속 하시겠습니까?")) {
	 	document.UserLogVo.action = "/boffice/sy/userlog/UserLog_Clear.do";
		document.UserLogVo.submit();		
	}
}

/* function doGroupConfigRmv(gcIdx) {
	$.ajax({
		type: "GET",
		url: "<c:url value='/boffice/sy/user/GroupConfig_RmvtAx.do' />",
		data: "cuId=" + cuId,
		dataType: "json",
		success:function(object) {
			alert("사용할 수 있는 아이디입니다.");
		},
		error: function(xhr,status,error) {
			alert("이미 등록된 아이디 입니다.");
		}
	});
} */

$(function(){
    //전체선택 체크박스 클릭
	$("#allCheck").click(function(){
		//만약 전체 선택 체크박스가 체크된상태일경우
		if($("#allCheck").prop("checked")) {
			//해당화면에 전체 checkbox들을 체크해준다
			$("input[type=checkbox]").prop("checked",true);
		// 전체선택 체크박스가 해제된 경우
		} else {
			//해당화면에 모든 checkbox들의 체크를해제시킨다.
			$("input[type=checkbox]").prop("checked",false);
		}
	})
});
</script>
</head>
<body>
	<div class="section">
		<form:form commandName="UserLogVo" name="UserLogVo" method="post" class="searchListPage">
			<form:hidden path="pageIndex" />
			<form:hidden path="ulMenu" />
			<input type="hidden" name="ulIdx" id="ulIdx" />
			<div class="search">
				<table>
					<caption>검색테이블</caption>
					<tbody>
						<tr>
							<th scope="row"><label for="loginSucYn">상태</label></th>
							<td>
								<form:select path="loginSucYn">
									<form:option value="" label="전체"/>
									<form:option value="Y" label="성공"/>
									<form:option value="N" label="실패"/>
								</form:select>
							</td>
							<th scope="row"><label for="pageUnit">페이지수</label></th>
							<td>
								<form:select path="pageUnit">
									<form:option value="10" label="10"/>
									<form:option value="50" label="50"/>
									<form:option value="100" label="100"/>
								</form:select>						
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="searchCondition">검색</label></th>
							<td colspan="3">
								<form:select path="searchCondition">
									<form:option value="cuId" label="아이디"/>
									<form:option value="ip" label="아이피"/>
								</form:select>
								<label for="searchKeyword" class="hidden">검색어</label>
								<form:input path="searchKeyword" size="30"/>
							</td>
						</tr>
					</tbody>
				</table>
				<input type="submit" value="검색" class="btn_inline btn_search">
			</div>
		</form:form>
		<div class="tableTitle">
			<div class="btnArea right">
				<!-- <a href="#" class="btn_m">취소하기</a> -->
				<a href="javascript:doUserLogClear();" class="btn_m btn_focus">DB정리</a>
				<a href="javascript:doUserLogCheckRmv();" class="btn_m btn_delete">일괄삭제</a>
			</div>
		</div>
		<div class="tableBox">			
			<table class="list">
				<caption></caption>
				<colgroup>
					<col style="width:5%;">
					<col>
					<col style="width:170px;">
					<col style="width:100px;">
					<col>
					<col style="width:8%;">						
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" id="allCheck"/></th>
						<th scope="col">아이디</th>
						<th scope="col">아이피</th>
						<th scope="col">상태</th>
						<th scope="col">시간</th>
						<th scope="col">삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList}" var="result" varStatus="status">
					<tr>
						<td><input type="checkbox" name="checkbox" id="checkbox" value="<c:out value="${result.ulIdx}" />" /></td>
						<td><c:out value="${result.cuId}" /></td>
						<td><c:out value="${result.ip}" /></td>
						<td>
							<c:choose>
								<c:when test="${result.loginSucYn eq 'Y'}">성공</c:when>
								<c:when test="${result.loginSucYn eq 'N'}">실패</c:when>
							</c:choose>
						</td>
						<td><c:out value="${result.regDt}" /></td>
						<td><a href="javascript:doUserLogRmv('<c:out value="${result.ulIdx}" />');" class="btn_s btn_delete">삭제</a></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) eq 0}">
						<tr>
							<td colspan="6"  class="empty">검색결과가 없습니다.</td>						
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doUserLogPag" />
		</div>
	</div>
</body>
</html>
