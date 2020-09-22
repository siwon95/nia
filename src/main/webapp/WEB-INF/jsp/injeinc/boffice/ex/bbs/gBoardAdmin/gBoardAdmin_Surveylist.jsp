<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>구청장에게바란다 만족도조사</title>

<script type="text/javascript">
//<![CDATA[
function doSurveyPag(pageIndex) {
	document.GBoardAdminVo.pageIndex.value = pageIndex;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_Surveylist.do' />";
	document.GBoardAdminVo.submit();
}          
function doSearchListSurvey() {
	var scSDate = $("#scSDate").val();
	var scEDate = $("#scEDate").val();
	if(scEDate != ""){
		if(scSDate > scEDate){
			alert("검색 시작날짜보다 종료날짜가 더 이전 날짜 입니다. 다시 선택해주세요.");
			$("#scEDate").focus();
			return;
		}
	}
	document.GBoardAdminVo.pageIndex.value = 1;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_Surveylist.do' />";
	document.GBoardAdminVo.submit();
}          
function doSearchRemove() {
	$("#scSDate").val("");
	$("#scEDate").val("");
	document.GBoardAdminVo.pageIndex.value = 1;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_Surveylist.do' />";
	document.GBoardAdminVo.submit();
}          

function doExcel() {
	document.GBoardAdminVo.pageIndex.value = 1;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_SurveyExcel.do' />";
	document.GBoardAdminVo.submit();
}
           
$(window).ready(function(){
	
	// 달력
    $("input[id=scSDate], input[id=scSDate]").datepicker();
	$("input[id=scSDate], input[id=scSDate]").mask("9999-99-99");
    $("input[id=scSDate], input[id=scSDate]").datepicker( "option", "dateFormat", "yy-mm-dd" );
    $("input[id=scEDate], input[id=scEDate]").datepicker();
	$("input[id=scEDate], input[id=scEDate]").mask("9999-99-99");
    $("input[id=scEDate], input[id=scEDate]").datepicker( "option", "dateFormat", "yy-mm-dd" );
    
    //페이지수
    $("#pageUnit").val("<c:out value='${GBoardAdminVo.pageUnit }'/>");
});
//]]>
</script>

</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form id="GBoardAdminVo" name="GBoardAdminVo" method="post">
<input type="hidden" id="pageIndex" name="pageIndex" />
	<!-- 컨텐츠 영역 -->
	<div id="contents">
		<table summary="" class="write">
			<caption></caption>
			<colgroup>
				<col width="4%" />
				<col width="35%" />
				<col width="35%" />
			</colgroup>
			<tbody>
				<tr>
					<th><label for="scSDate">날짜선택</label></th>
					<td>
						<span class="cal_in" ><input type="text" id="scSDate" name="scSDate" maxlength="10" size="15" value="<c:out value='${GBoardAdminVo.scSDate }'/>" readonly="readonly"/></span>
						~
						<span class="cal_in" ><input type="text" id="scEDate" name="scEDate" maxlength="10" size="15" value="<c:out value='${GBoardAdminVo.scEDate }'/>" readonly="readonly"/></span>
						<a href="javascript:doSearchListSurvey();" class="btn2 btn_input"><i class="fa fa-search"></i> 검색</a>
						<a href="javascript:doSearchRemove();" class="btn3 btn_input">초기화</a>
					</td>
				</tr>
				<tr>
					<th><label for="pageUnit">페이지수</label></th>
					<td>
						<select id="pageUnit" name="pageUnit" onchange="doSearchListSurvey();">
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="50">50</option>
							<option value="100">100</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
		
		<!-- 버튼 -->
		<div class="btn_zone">
			<a href="javascript:doExcel();" class="btn2">엑셀다운</a>
		</div>
		<!-- //버튼 -->

		<table summary="" class="list1">
			<caption></caption>
			<colgroup>
				<col width="5%" />
				<col width="30%" />
				<col width="5%" />
				<col width="15%" />
				<col width="10%" />
				<col width="5%" />
				<col width="30%" />
			</colgroup>
			<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>담당부서</th>
				<th>만족도</th>
				<th>의견</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultList }" varStatus="status">
					<tr>
						<td><c:out value="${(totCnt+1)-(status.count+(GBoardAdminVo.pageIndex-1)*GBoardAdminVo.recordCountPerPage)}" /></td>	
						<td><c:out value="${result.subCont }" /></td>	
						<td><c:out value="${result.nameCont }" /></td>				
						<td><c:out value="${result.mcSurveyRegDt }" /></td>				
						<td><c:out value="${result.mcDeptCd }" /></td>				
						<td><c:out value="${result.mcSurvey }" /></td>				
						<td><c:out value="${result.mcSurveyDesc }" /></td>				
					</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="7">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<!--paginate -->
			<div class="paginate">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doSurveyPag" />
			</div>
			<!--//paginate -->
	</div>
	<!-- //컨텐츠 영역 -->
</form>
</body>
</html>
