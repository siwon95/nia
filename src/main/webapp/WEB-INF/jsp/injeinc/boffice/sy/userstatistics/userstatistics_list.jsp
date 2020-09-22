<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 회원가입통계
- 파일명 : userstatistics_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<link rel="stylesheet" type="text/css" href="/js/c3chart/c3.css" />
<script src="/js/c3chart/d3-3.5.6.min.js"></script>
<script src="/js/c3chart/c3.min.js"></script>
<script>
var actionType;

$(function(){
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	//차트
	var chartData = {
        x: 'x',
        columns: [
            ['x'],
            ['계']
        ]
    };
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<c:if test="${!status.last}">
	chartData.columns[0].push("<c:out value="${result.resultDate}" />");
	chartData.columns[1].push(parseInt("<c:out value="${result.resultDate}" />"));
	</c:if>
	</c:forEach>
	
	var chart = c3.generate({
		bindto:'#chart',
		data:chartData,
	    axis:{x:{label:''}}
	});

	//연월선택 제어
	$('#scYear').change(function() {
		if($(this).val() == "") {
			$('#scMonth').val("");
			$('#scMonth').attr('disabled','true');
		}else {
			//alert($(this).val());
			$('#scMonth').removeAttr('disabled');
		}
		
		if($('#scMonth').val() == null) {
			$('#scMonth option:eq(0)').attr("selected", "selected");
		}
	});
	/* $('#scMonth').change(function() {
		if($('#scYear').val() == null) {
			alert();	
		}
	}); */
});

function doUserStatisticsList(f) {
	if(actionType == "excel"){
		f.action = "<c:url value='/boffice/sy/userstatistics/UserSta_Excel.do' />";
	}else{
		f.action = "<c:url value='/boffice/sy/userstatistics/UserStatistics_List.do' />";
	}
	return true;
}

function excelDown(){
	actionType = "excel";
	$("#UserStatisticsVo").submit();
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section">
		<form:form commandName="UserStatisticsVo" name="UserStatisticsVo" method="post" onsubmit="return doUserStatisticsList(this);">
		<div class="search">
			<div class="right">
				<label for="scYear">연월선택</label>
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<c:set var="currYear"><fmt:formatDate value="${now}" pattern="yyyy" /></c:set>
				<form:select path="scYear" class="w70">
					<form:option value="" label="전체"/>
					
					<c:forEach var="i" begin="0" end="${currYear-2010}" step="1">
					<form:option value="${currYear-i}" label="${currYear-i}년"/>
					</c:forEach>
				</form:select>
				<form:select path="scMonth" class="w70">
					<form:option value="" label="전체"/>
					<form:option value="01" label="01월"/>
					<form:option value="02" label="02월"/>
					<form:option value="03" label="03월"/>
					<form:option value="04" label="04월"/>
					<form:option value="05" label="05월"/>
					<form:option value="06" label="06월"/>
					<form:option value="07" label="07월"/>
					<form:option value="08" label="08월"/>
					<form:option value="09" label="09월"/>
					<form:option value="10" label="10월"/>
					<form:option value="11" label="11월"/>
					<form:option value="12" label="12월"/>								
				</form:select>
				<input type="submit" value="검색" class="btn_inline btn_search">
			</div>
		</div>
		</form:form>
		
		<div id="chart" class="chartWrap"></div>
		
		<div class="btnArea right">
			<a href="javascript:excelDown();" class="btn_inline btn_down">엑셀다운로드</a>
		</div>
		
		<div class="tableBox">
			<table class="list">
				<caption></caption>
				<colgroup>
					<col class="w100">
					<col>
					<%-- <col>
					<col> --%>						
				</colgroup>
				<thead>
					<tr>
						<th>
							<c:choose>
								<c:when test="${!empty UserStatisticsVo.scMonth}">일별</c:when>
								<c:when test="${!empty UserStatisticsVo.scYear}">월별</c:when>
								<c:otherwise>연도별</c:otherwise>
							</c:choose>
						</th>
						<th>회원가입자수</th>
						<!-- <th>구민</th>
						<th>구민&SMS동의</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList}" var="result" varStatus="status">
					<tr>
						<td>
							<c:if test="${!status.last}"><c:out value="${result.resultDate}" /></c:if>
							<c:if test="${status.last}">합계</c:if>
						</td>
						<td><c:out value="${result.memberCnt}" /></td>
						<%-- <td><c:out value="${result.guminCnt}" /></td>
						<td><c:out value="${result.guminAndSmsCnt}" /></td> --%>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
