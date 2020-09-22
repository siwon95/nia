<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 웹로그현황(요약)
- 파일명 : webLogStatSummary.jsp
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
	document.weblogVo.action = "<c:url value='/boffice/stat/selectWebLogStatSummary.do' />";
	document.weblogVo.submit();
}
function fn_excel_down(){
	document.weblogVo.action = "<c:url value='/boffice/stat/selectWebLogStatSummaryExcel.do' />";
    document.weblogVo.submit();
}	
$(function(){
	$('#searchCondition').change(function(){
		if ($("#searchCondition").val() == "1") {
			$("#span_searchCondition").hide();
			$("#span_searchCondition1").show();
		} else {
			$("#span_searchCondition").show();
			$("#span_searchCondition1").hide();
		}
	});
	$('#html').jstree({
		"core" : {
			"check_callback" : true
		},
	    "plugins" : [ "themes", "html_data"  ]		 
	})
	$('#html').jstree("open_all");
});
$(document).ready(function(){
	if ($("#searchCondition").val() == "1") {
		$("#span_searchCondition").hide();
		$("#span_searchCondition1").show();
	} else {
		$("#span_searchCondition").show();
		$("#span_searchCondition1").hide();
	}
});
</script>

<script type="text/javascript" src="/plugin/c3chart/js/d3-3.5.6.min.js"></script>
<script type="text/javascript" src="/plugin/c3chart/js/c3.min.js"></script>
<link rel="stylesheet" type="text/css" href="/plugin/c3chart/css/c3.css" />
<script>
$(function(){
    var chart = c3.generate({
    	bindto:'#chart1',
        data:{
	        x:'x',
			columns:[
				['x', '접속 IP수', '방문수 (UV)', '페이지뷰 (PV)'],
				[
					$("#chartDataTable tbody td").eq(0).text(),
					parseInt($("#chartDataTable tbody td").eq(1).text()),
					parseInt($("#chartDataTable tbody td").eq(2).text()),
					parseInt($("#chartDataTable tbody td").eq(3).text())
				]
			],
	        type: 'bar'
        },
        axis:{
        	x:{
        		type:'category'
        	}
        },
        bar:{
        	width:{
        		ratio:0.3
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
							<form:option value="0">일별</form:option>
							<form:option value="1">기간별</form:option>
						</form:select>
						<span id="span_searchCondition">
						<label for="searchDe" class="hidden">검색일</label>
						<span class="cal_in" ><form:input path="searchDe" title="검색일" readonly="true" size="10" class="useDatepicker" /></span>
						</span>			
						<span id="span_searchCondition1">
						<label for="searchStartDe" class="hidden">검색시작일</label>
						<span class="cal_in" ><form:input path="searchStartDe" title="검색시작일" readonly="true" size="10" class="dateFrom useDatepicker" /></span>
						~
						<label for="searchEndDe" class="hidden">검색종료일</label>
						<span class="cal_in" ><form:input path="searchEndDe" title="검색종료일" readonly="true" size="10" class="dateFrom useDatepicker" /></span>		
						</span>
						<input type="submit" value="검색" class="btn_inline">
					</fieldset>
				</div>
			</div>
		</form:form>
		
		<div id="chart1" class="chartWrap" style="height:300px;"></div>
	
		<div class="divGroup cols2">
			<div>
				<div class="tableTitle">
					<h4>접속 IP수</h4>
					<div class="btnArea">
						<a href="javascript:fn_excel_down();" class="btn_inline btn_down">엑셀다운로드</a>
					</div>
				</div>
				<div class="tableBox">
					<table class="view" id="chartDataTable">
						<caption></caption>
						<colgroup>
							<col width="40%" />
							<col width="20%" />
							<col width="20%" />
							<col width="20%" />						
						</colgroup>
						<thead>
							<tr>
								<th scope="col">일자</th>
								<th scope="col">접속 IP수</th>
								<th scope="col">방문수 (UV)</th>
								<th scope="col">페이지뷰 (PV)</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${siteVisitrCoSummaryList}" var="result" varStatus="status">
								<td class="center">
									<c:out value="${result.occrrncDe }"/>						
								</td>
								<td class="center">
									<c:out value="${empty result.rdcnt?'-':result.rdcnt}" />
								</td>
								</c:forEach>
								<c:forEach items="${siteSessionVisitrCoSummaryList}" var="result" varStatus="status">
								<%-- <td class="center">
									<c:out value="${result.occrrncDe}" />			
								</td> --%>
								<td class="center">
									<c:out value="${empty result.rdcnt?'-':result.rdcnt}" />
								</td>
								</c:forEach>
								<c:forEach items="${sitePageviewSummaryList}" var="result" varStatus="status">
								<%-- <td class="center">
									<c:out value="${result.occrrncDe}" />			
								</td> --%>
								<td class="center">
									<c:out value="${empty result.rdcnt?'-':result.rdcnt}" />
								</td>
								</c:forEach>
								<c:if test="${fn:length(siteVisitrCoSummaryList) == 0}">
								<tr>
									<td colspan="4" class="center"><spring:message code="common.nodata.msg" /></td>
								</tr>
								</c:if>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div>
				<div class="tableTitle">
					<h4>메뉴별 페이지뷰</h4>
				</div>
	        	<!-- Tree -->
	        	<div class="treeWrap">
					<c:set var="oldMenuDept" value="0"/>
					<div id="html" style="padding:5px;width:98%;">
					<c:forEach items="${siteMenuPageviewSummaryList}" var="result" varStatus="status">
						<c:choose>
							<c:when test="${result.menuDepth > oldMenuDept || status.index==0}">
							<ul>						
								<li id="<c:out value="${result.sortOdr}"/>" >
									<c:out value="${result.menuNm}"/>&nbsp;<c:if test="${not empty result.rdcnt }"><font color='red'>(<c:out value="${result.rdcnt }"/>)</font></c:if>
								</c:when>
							<c:when test="${result.menuDepth < oldMenuDept}">
								<c:forEach var="i" begin="1" end="${oldMenuDept-result.menuDepth}">
									</li>								
								</ul>
								</c:forEach>
								</li>
							<li id="<c:out value="${result.sortOdr}"/>" >
								<c:out value="${result.menuNm}"/>&nbsp;<c:if test="${not empty result.rdcnt }"><font color='red'>(<c:out value="${result.rdcnt }"/>)</font></c:if>
							</c:when>
							<c:otherwise>
								</li>
								<li id="<c:out value="${result.sortOdr}"/>" ><c:out value="${result.menuNm}"/>&nbsp;<c:if test="${not empty result.rdcnt }"><font color='red'>(<c:out value="${result.rdcnt }"/>)</font></c:if>
							</c:otherwise>
						</c:choose>				
						<c:set var="oldMenuDept" value="${result.menuDepth}"/>
					</c:forEach>
					<c:forEach var="i" begin="1" end="${oldMenuDept}">
					</li>
					</ul>
					</c:forEach>
					</div>
				</div>
				<!-- //Tree -->
			</div>
		</div>
			
	</div>
		
</body>
</html>