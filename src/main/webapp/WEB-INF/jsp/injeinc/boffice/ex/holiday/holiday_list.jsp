<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%-- ------------------------------------------------------------
- 제목 : 공휴일관리
- 파일명 : holiday_list.jsp
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
    	var ajaxParam = {"holDate":""};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
    		var resultVo = data.resultVo;
			$("#actionkey").val("regist");
			$("#holCode").val($("#searchHolCode").val());
			$("#divHolidayForm .btn_del").hide();
			modalOpen("#divHolidayForm");
    	};
    	ajaxAction("<c:url value='/boffice/ex/holiday/HolidayFormAx.do'/>", ajaxParam, ajaxOption);
    });
    $(".btn_mod").click(function(e){
    	e.preventDefault();
		var trIdx = $(this).attr("data-idx");
    	var ajaxParam = {"holDate":trIdx};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
    		console.log(data);
    		var resultVo = data.resultVo;
			$("#actionkey").val("modify");
			$("#holDate").val(resultVo.holDate);
			$("#oldHolDate").val(resultVo.holDate);
			$("#holName").val(resultVo.holName);					
			$("#holCode").val(resultVo.holCode);
			modalOpen("#divHolidayForm");
    	};
    	ajaxAction("<c:url value='/boffice/ex/holiday/HolidayFormAx.do'/>", ajaxParam, ajaxOption);
    });
    $(".btn_del").click(function(e){
    	e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		var trIdx = $("#holDate").val();
		var ajaxParam = {"holDate":trIdx};
		ajaxActionMessage("<c:url value='/boffice/ex/holiday/HolidayRmvAx.do'/>", ajaxParam, '', true);
    });
    
    //입력이벤트
    $("#searchYear").change(function(){
    	$("#HolidayVo").submit();
    });
    $("#searchHolCode").change(function(){					
    	$("#HolidayVo").submit();
    });
});

function doForm(f) {
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	var ajaxUrl = "";
	if($("#actionkey").val() == "regist") {
		ajaxUrl = "<c:url value='/boffice/ex/holiday/HolidayRegAx.do' />";
	}else if($("#actionkey").val() == "modify") {
		ajaxUrl = "<c:url value='/boffice/ex/holiday/HolidayModAx.do' />";
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
		<form:form commandName="HolidayVo" name="HolidayVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<tbody>
					<tr>
						<th scope="row"><label for="searchYear">연도선택</label></th>
						<td>
							<form:select path="searchYear">
								<form:option value="" label="전체" />
								<c:forEach var="yearInfo" items="${yearList}">
								<form:option value="${yearInfo}" label="${yearInfo}" />
								</c:forEach>
							</form:select>
							<!-- <select name="holCode" id="holCode" title="도메인 선택" onchange="chgholiday();" class="select-box select-box-txt">
								<option>직접입력</option>
							</select> -->
							<select id="searchHolCode" name="searchHolCode">
								<option value="">==선택==</option>
								<c:forEach var="holiday" items="${holiday}" varStatus="status">
								<option value="<c:out value="${holiday.code}"/>" <c:if test="${holiday.code eq param.searchHolCode}">selected</c:if>><c:out value="${holiday.codeName}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="tableTitle">
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
					
		<div class="tableBox">
			<table class="list">
				<caption>공휴일관리</caption>
				<colgroup>
					<col class="w100">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">연도</th>
						<th scope="col">날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="holYear" value=""/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<c:set var="tempYear" value="${fn:substring(result.holDate, 0, 4) }"/>
					<c:if test="${tempYear ne holYear }">
					<c:set var="holYear" value="${tempYear }"/>
					<c:if test="${!status.first }">
						</td>
					</tr>
					</c:if>
					<tr>
						<td class="holYear"><span><c:out value="${holYear }"/></span> 년</td>
						<td class="holDate left">
					</c:if>
							<a href="#" class="btn_inline btn_mod" data-idx="<c:out value="${result.holDate}" />"><c:out value="${result.holDate}" /></a>
					<c:if test="${status.last }">
						</td>
					</tr>
					</c:if>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="2" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>	
		</div>
		</form:form>
	</div>
		
<!-- ============================== 모달영역 ============================== -->
<div id="divHolidayForm" class="modalWrap small">
	<div class="modalTitle">
		<h3>
			공휴일관리 등록/수정
		</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<form id="saveFrm" name="saveFrm" method="post" onsubmit="return doForm(this);">
		<input type="hidden" id="actionkey" name="actionkey" />
		<div class="tableBox">
			<table class="form">
				<caption>공휴일 입력/수정</caption>
				<colgroup>
					<col class="w150">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="holCode">선택한 데이터</label></th>
						<td>
							<select id="holCode" name="holCode">
								<option>==선택==</option>
								<c:forEach var="holiday" items="${holiday}">
								<option value="<c:out value="${holiday.code}"/>"><c:out value="${holiday.codeName}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>		
					<tr>
						<th scope="row"><label for="holDate">날짜</label></th>
						<td>
							<input type="hidden" id="oldHolDate" name="oldHolDate" />
							<input type="text" id="holDate" name="holDate" size="10" maxlength="10" value="" class="useDatepicker" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="holName">휴일명</label></th>
						<td><input type="text" id="holName" name="holName" size="15" class="w100p" value=""></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<input type="submit" value="확인" class="btn_m on">
			<a href="#" class="btn_m btn_caption btn_del">삭제</a>
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
		</form>
	</div>
</div>

</body>
</html>
