<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 기간별 접속통계
- 파일명 : webLogStatPeriod.jsp
- 작성일 : 2018-01-24
- 작성자 : 박수진 대리
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
// 2007-01-01 문자열을날짜객체로변환
function getDateObjectFromDashStr(dateStr) {
	var dateinfo = dateStr.split("-");
	return new Date(dateinfo[0] , dateinfo[1] -1 , dateinfo[2]);
}
//Date 객체를 2007-01-01 형식문자열로변환
function getDateStrFromDateObject(dateObject) {
	var str = null;
	var month = dateObject.getMonth();
	var day = dateObject.getDate();
	if (month <  10) {
		month = "0" + month;
	}
	
	if (day < 10) {
		day = "0" + day;
	}
	str = dateObject.getYear() + "-" + month + "-" + day;
	return str;
}
//두날짜사이의기간리턴edate - sdate  (sdate 보다edate가크면1 , 작으면-1 , 같으면0)
function compareIsPastDay(sdate, edate) {
	if (edate - sdate < 0) {
		return -1;
	} else if(edate - sdate == 0) {
		return 0 ;
	} else {
		 return 1;
	}     
}                
// 날짜조건계산후date 객체리턴
function getDateObjectOfPlusDay(targetDate, plusDayInt) {
	var newDate = new Date();
	var processTime = targetDate.getTime() - (parseInt(plusDayInt) * 24 * 60 * 60 * 1000);
	newDate.setTime(processTime);
	return newDate;
}
function fn_search() {
	document.weblogVo.action = "<c:url value='/boffice/stat/selectWebLogStatPeriod.do' />";
	document.weblogVo.submit();
}
function fn_excel_down(){
	document.weblogVo.action = "<c:url value='/boffice/stat/selectWebLogStatPeriodExcel.do' />";
    document.weblogVo.submit();
}
</script>

<script type="text/javascript" src="/plugin/c3chart/js/d3-3.5.6.min.js"></script>
<script type="text/javascript" src="/plugin/c3chart/js/c3.min.js"></script>
<link rel="stylesheet" type="text/css" href="/plugin/c3chart/css/c3.css" />
<script>
$(function(){
    var chartColumns = [];
    var chartXNames = [];
	var chartXNamesCount = 0;
    $(".divGroup > div").each(function(){
    	var chartColumnData = [];
    	chartColumnData.push($(this).children("h4").text());
    	if(chartXNamesCount == 0){
        	chartXNames.push('x');	
    	}
    	$(this).find("table tbody tr").each(function(){
        	if(chartXNamesCount == 0){
        		chartXNames.push($(this).find("td").eq(0).text());
        	}
    		chartColumnData.push(parseInt($(this).find("td").eq(1).text()));
    	});
    	if(chartXNamesCount == 0){
    		chartColumns.push(chartXNames);
    	}
    	if(chartColumnData.length > 0){
        	chartColumns.push(chartColumnData);	
    	}
    	chartXNamesCount = 1;
    });
    
    var chart = c3.generate({
    	bindto:'#chart1',
        data:{
	        x:'x',
			columns:chartColumns
        },
        axis:{
        	x:{
        		type:'category',
        		tick:{count:5}
        	}
        }
    });
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section">
	
		<form:form commandName="weblogVo" name="weblogVo" method="post" onsubmit="fn_search(); return false;">
			<div class="search">
				<div class="right">
					<fieldset> 
						<label for="searchSiteNo" class="hidden">검색사이트</label>
				   		<form:select path="searchSiteNo" title="검색사이트">
							<form:options items="${selectListSiteAll}" itemValue="siteCd" itemLabel="siteNm" />
						</form:select>	
						<label for="searchCondition" class="hidden">검색조건</label>
				   		<form:select path="searchCondition" title="검색조건">
							<form:option value="day">일별</form:option>
							<form:option value="month">월별</form:option>
							<form:option value="year">년도별</form:option>
						</form:select>
						<label for="searchStartDe" class="hidden">검색시작일</label>
						<span class="cal_in" ><form:input path="searchStartDe" title="검색시작일" readonly="true" class="dateFrom useDatepicker" /></span>
						~
						<label for="searchEndDe" class="hidden">검색종료일</label>
						<span class="cal_in" ><form:input path="searchEndDe" title="검색종료일" readonly="true" class="dateTo useDatepicker" /></span>		
						<input type="submit" value="검색" class="btn_inline">
					</fieldset>
				</div>
			</div>
		</form:form>
		
		<div id="chart1" class="chartWrap" style="height:300px;"></div>
		
		<div class="tableTitle">
			<div class="btnArea">
				<a href="javascript:fn_excel_down();" class="btn_inline btn_down">엑셀다운로드</a>
			</div>
		</div>
		<div class="divGroup cols3">
			<div>
				<div class="tableTitle">
					<h4>접속 IP수</h4>
				</div>
				<div class="tableBox">
					<table class="view chartDataTable">
						<caption></caption>
						<colgroup>
							<col width="*" />
							<col width="50%" />	
						</colgroup>
						<thead>
						<tr>
							<th scope="col"><c:choose><c:when test="${'day' eq statVO.searchCondition}">일</c:when><c:when test="${'month' eq statVO.searchCondition}">월</c:when><c:when test="${'year' eq statVO.searchCondition}">년도</c:when><c:otherwise>날짜</c:otherwise></c:choose></th>
							<th scope="col">IP수</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${siteVisitrCoPeriodList}" var="result" varStatus="status">
						<tr>
							<td class="center">
								<c:out value="${result.occrrncDe}" />
							</td>
							<td class="center">
								<c:out value="${empty result.rdcnt?'-':result.rdcnt}" />
							</td>
						</tr>
						</c:forEach>
						<c:if test="${fn:length(siteVisitrCoPeriodList) == 0}">
						<tr>
							<td colspan="2" class="center"><spring:message code="common.nodata.msg" /></td>
						</tr>
						</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<div>
				<div class="tableTitle">
					<h4>방문수 (UV)</h4>
				</div>
				<div class="tableBox">
					<table class="view chartDataTable">
						<caption></caption>
						<colgroup>
							<col width="*" />
							<col width="50%" />	
						</colgroup>
						<thead>
						<tr>
							<th scope="col"><c:choose><c:when test="${'day' eq statVO.searchCondition}">일</c:when><c:when test="${'month' eq statVO.searchCondition}">월</c:when><c:when test="${'year' eq statVO.searchCondition}">년도</c:when><c:otherwise>날짜</c:otherwise></c:choose></th>
							<th scope="col">방문수</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${siteSessionVisitrCoPeriodList}" var="result" varStatus="status">
						<tr>
							<td class="center">
								<c:out value="${result.occrrncDe}" />			
							</td>
							<td class="center">
								<c:out value="${empty result.rdcnt?'-':result.rdcnt}" />
							</td>
						</tr>
						</c:forEach>
						<c:if test="${fn:length(siteSessionVisitrCoPeriodList) == 0}">
						<tr>
							<td colspan="2" class="center"><spring:message code="common.nodata.msg" /></td>
						</tr>
						</c:if>
						</tbody>
					</table>	
				</div>
			</div>
			<div>
				<div class="tableTitle">
					<h4>페이지뷰 (PV)</h4>
				</div>
				<div class="tableBox">
					<table class="view chartDataTable">
						<caption></caption>
						<colgroup>
							<col width="*" />
							<col width="50%" />	
						</colgroup>
						<thead>
						<tr>
							<th scope="col"><c:choose><c:when test="${'day' eq statVO.searchCondition}">일</c:when><c:when test="${'month' eq statVO.searchCondition}">월</c:when><c:when test="${'year' eq statVO.searchCondition}">년도</c:when><c:otherwise>날짜</c:otherwise></c:choose></th>
							<th scope="col">페이지뷰</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${sitePageviewPeriodList}" var="result" varStatus="status">
						<tr>
							<td class="center">
								<c:out value="${result.occrrncDe}" />			
							</td>
							<td class="center">
								<c:out value="${empty result.rdcnt?'-':result.rdcnt}" />
							</td>
						</tr>
						</c:forEach>
						<c:if test="${fn:length(sitePageviewPeriodList) == 0}">
						<tr>
							<td colspan="2" class="center"><spring:message code="common.nodata.msg" /></td>
						</tr>
						</c:if>
						</tbody>
					</table>	
				</div>	
			</div>
		</div>
			
	</div>
		
</body>
</html>
