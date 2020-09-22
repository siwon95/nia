<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@page import="java.net.URLEncoder"%>
<%
request.setCharacterEncoding("utf-8");
String fileName = "웹로그현황(요약).xls";
fileName = URLEncoder.encode(fileName,"UTF-8");
response.addHeader("Content-Disposition", "attachment;filename="+ fileName);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<style type="text/css">
.box_l{margin:10px 10px 10px 0;width:45%;height:400px;border:1px solid #ddd;padding:10px;float:left;overflow:auto;}
.box_r{margin:10px 0;width:48%;height:400px;border:1px solid #ddd;padding:10px;float:right;overflow:auto;}
.box_chart{clear:both;border:1px solid #ddd;padding:10px;margin-bottom:10px;}
</style>

<!-- amcharts -->
<script type="text/javascript" src="<c:url value="/js/amcharts/amcharts.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/amcharts/serial.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/amcharts/themes/light.js"/>"></script>

<script type="text/javascript">
//<![CDATA[
       var chart = AmCharts.makeChart( "chartdiv", {
		  "type": "serial",
		  "theme": "light",
		  "dataProvider": [
<c:forEach items="${sitePageviewSummaryList}" var="result" varStatus="status">
	{"hour": "00 AM ~","viewcnt": <c:out value="${result.hitTime00}" />}
	, {"hour": "01 AM ~","viewcnt": <c:out value="${result.hitTime01}" />}	
	, {"hour": "02 AM ~","viewcnt": <c:out value="${result.hitTime02}" />}
	, {"hour": "03 AM ~","viewcnt": <c:out value="${result.hitTime03}" />}
	, {"hour": "04 AM ~","viewcnt": <c:out value="${result.hitTime04}" />}
	, {"hour": "05 AM ~","viewcnt": <c:out value="${result.hitTime05}" />}
	, {"hour": "06 AM ~","viewcnt": <c:out value="${result.hitTime06}" />}
	, {"hour": "07 AM ~","viewcnt": <c:out value="${result.hitTime07}" />}
	, {"hour": "08 AM ~","viewcnt": <c:out value="${result.hitTime08}" />}
	, {"hour": "09 AM ~","viewcnt": <c:out value="${result.hitTime09}" />}
	, {"hour": "10 AM ~","viewcnt": <c:out value="${result.hitTime10}" />}
	, {"hour": "11 AM ~","viewcnt": <c:out value="${result.hitTime11}" />}
	, {"hour": "12 AM ~","viewcnt": <c:out value="${result.hitTime12}" />}
	, {"hour": "13 PM ~","viewcnt": <c:out value="${result.hitTime13}" />}
	, {"hour": "14 PM ~","viewcnt": <c:out value="${result.hitTime14}" />}
	, {"hour": "15 PM ~","viewcnt": <c:out value="${result.hitTime15}" />}
	, {"hour": "16 PM ~","viewcnt": <c:out value="${result.hitTime16}" />}
	, {"hour": "17 PM ~","viewcnt": <c:out value="${result.hitTime17}" />}
	, {"hour": "18 PM ~","viewcnt": <c:out value="${result.hitTime18}" />}
	, {"hour": "19 PM ~","viewcnt": <c:out value="${result.hitTime19}" />}
	, {"hour": "20 PM ~","viewcnt": <c:out value="${result.hitTime20}" />}
	, {"hour": "21 PM ~","viewcnt": <c:out value="${result.hitTime21}" />}
	, {"hour": "22 PM ~","viewcnt": <c:out value="${result.hitTime22}" />}
	, {"hour": "23 PM ~","viewcnt": <c:out value="${result.hitTime23}" />}
</c:forEach>
	      ],
		  "gridAboveGraphs": true,
		  "startDuration": 1,
		  "graphs": [ {
			"balloonText": "[[category]]: <b>[[value]]</b>",
			"fillAlphas": 0.8,
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "viewcnt"
		  } ],
		  "chartCursor": {
			"categoryBalloonEnabled": false,
			"cursorAlpha": 0,
			"zoomable": false
		  },
		  "categoryField": "hour",
		  "categoryAxis": {
			"gridPosition": "start",
			"labelRotation" : 90,
			"gridAlpha": 0,
			"tickPosition": "start",
			"tickLength": 20
		  },
		  "export": {
			"enabled": true
		  }
		
		} );
	           
//]]>
</script>
<!-- //amcharts -->

<script type="text/javascript">
//<![CDATA[
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
//날짜검색용invalidator
function invalidateSearchDay(sdateStr, edateStr, limitDay) {
	var sdate = getDateObjectFromDashStr(sdateStr);
	var edate = getDateObjectFromDashStr(edateStr);
	var checkDayResult = compareIsPastDay(sdate , edate);
	var before7Day = getDateObjectOfPlusDay(edate , limitDay);
	var checkResult = compareIsPastDay(before7Day , sdate);
	if (checkDayResult < 0) {
	    alert('검색 종료일이 시작일보다 빠릅니다.');
	    return false;
	}
	if (checkResult < 0) {
	    alert(limitDay + ' 일이내로 검색하세요.');
	    return false;
	}
	return true;
}
function fn_search() {
	if (document.weblogVo.searchCondition.value == "1") {
		if (!invalidateSearchDay(document.weblogVo.searchStartDe.value, document.weblogVo.searchEndDe.value, 30)) {
			return;
		}
	}
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
$(window).load(function() {
	$("input[id=searchDe], input[id=searchDe]").datepicker();
	$("input[id=searchDe], input[id=searchDe]").mask("9999-99-99");
    $("input[id=searchDe], input[id=searchDe]").datepicker( "option", "dateFormat", "yy-mm-dd" );
	$("input[id=searchStartDe], input[id=searchStartDe]").datepicker();
	$("input[id=searchStartDe], input[id=searchStartDe]").mask("9999-99-99");
    $("input[id=searchStartDe], input[id=searchStartDe]").datepicker( "option", "dateFormat", "yy-mm-dd" );
	$("input[id=searchEndDe], input[id=searchEndDe]").datepicker();
	$("input[id=searchEndDe], input[id=searchEndDe]").mask("9999-99-99");
    $("input[id=searchEndDe], input[id=searchEndDe]").datepicker( "option", "dateFormat", "yy-mm-dd" );
});
//]]>
</script>

</head>

<body>
<div id="contents">


	
<form:form commandName="weblogVo" name="weblogVo" method="post" onsubmit="fn_search(); return false;">
	<!-- 검색 영역 -->
	
	<!-- //검색 영역 -->
</form:form>
	
	<div>
		<div class="box_l">
			<h4 style="padding-bottom:5px;">접속 IP수</h4>						
			<table summary="" class="write">
				<caption></caption>
				<colgroup>
					<col width="*" />
					<col width="50%" />	
				</colgroup>
				<thead>
				<tr>
					<th scope="col"><c:choose><c:when test="${'0' eq statVO.searchCondition}">일</c:when><c:when test="${'1' eq statVO.searchCondition}">기간</c:when><c:otherwise>날짜</c:otherwise></c:choose></th>
					<th scope="col">IP수</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${siteVisitrCoSummaryList}" var="result" varStatus="status">
				<tr>
					<td class="center">
						<c:out value="${result.occrrncDe }"/>						
					</td>
					<td class="center">
						<c:out value="${empty result.hit?'-':result.hit}" />
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(siteVisitrCoSummaryList) == 0}">
				<tr>
					<td colspan="2" class="center"><spring:message code="common.nodata.msg" /></td>
				</tr>
				</c:if>
				</tbody>
			</table>
			<br />	
			<h4 style="padding-bottom:5px;">방문수 (UV)</h4>						
			<table  summary="" class="write">
				<caption></caption>
				<colgroup>
					<col width="*" />
					<col width="50%" />	
				</colgroup>
				<thead>
				<tr>
					<th scope="col"><c:choose><c:when test="${'0' eq statVO.searchCondition}">일</c:when><c:when test="${'1' eq statVO.searchCondition}">기간</c:when><c:otherwise>날짜</c:otherwise></c:choose></th>
					<th scope="col">방문수</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${siteSessionVisitrCoSummaryList}" var="result" varStatus="status">
				<tr>
					<td class="center">
						<c:out value="${result.occrrncDe }"/>								
					</td>
					<td class="center">
						<c:out value="${empty result.hit?'-':result.hit}" />
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(siteSessionVisitrCoSummaryList) == 0}">
				<tr>
					<td colspan="2" class="center"><spring:message code="common.nodata.msg" /></td>
				</tr>
				</c:if>
				</tbody>
			</table>		
			<br />		
			<h4 style="padding-bottom:5px;">페이지뷰 (PV)</h4>						
			<table summary="" class="write">
				<caption></caption>
				<colgroup>
					<col width="*" />
					<col width="50%" />	
				</colgroup>
				<thead>
				<tr>
					<th scope="col"><c:choose><c:when test="${'0' eq statVO.searchCondition}">일</c:when><c:when test="${'1' eq statVO.searchCondition}">기간</c:when><c:otherwise>날짜</c:otherwise></c:choose></th>
					<th scope="col">페이지뷰</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${sitePageviewSummaryList}" var="result" varStatus="status">
				<tr>
					<td class="center">
						<c:out value="${result.occrrncDe }"/>						
					</td>
					<td class="center">
						<c:out value="${empty result.hit?'-':result.hit}" />
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(sitePageviewSummaryList) == 0}">
				<tr>
					<td colspan="2" class="center"><spring:message code="common.nodata.msg" /></td>
				</tr>
				</c:if>
				</tbody>
			</table>
			<br />	
		</div>
        <!-- Tree -->
        <div class="box_r">
        	<h4 style="padding-bottom:5px;">메뉴별 페이지뷰</h4>
			<c:set var="oldMenuDept" value="0"/>
			<div id="html" style="padding:5px;width:98%;">
			<c:forEach items="${siteMenuPageviewSummaryList}" var="result" varStatus="status">
				<c:choose>
					<c:when test="${result.menuDp > oldMenuDept || status.index==0}">
					<ul>						
						<li id="<c:out value="${result.sortOdr}"/>" >
							<c:out value="${result.menuNm}"/>&nbsp;<c:if test="${not empty result.hit }"><font color='red'>(<c:out value="${result.hit }"/>)</font></c:if>
						</c:when>
					<c:when test="${result.menuDp < oldMenuDept}">
						<c:forEach var="i" begin="1" end="${oldMenuDept-result.menuDp}">
							</li>								
						</ul>
						</c:forEach>
						</li>
					<li id="<c:out value="${result.sortOdr}"/>" >
						<c:out value="${result.menuNm}"/>&nbsp;<c:if test="${not empty result.hit }"><font color='red'>(<c:out value="${result.hit }"/>)</font></c:if>
					</c:when>
					<c:otherwise>
						</li>
						<li id="<c:out value="${result.sortOdr}"/>" ><c:out value="${result.menuNm}"/>&nbsp;<c:if test="${not empty result.hit }"><font color='red'>(<c:out value="${result.hit }"/>)</font></c:if>
					</c:otherwise>
				</c:choose>				
				<c:set var="oldMenuDept" value="${result.menuDp}"/>
			</c:forEach>
			<c:forEach var="i" begin="1" end="${oldMenuDept}">
			</li>
			</ul>
			</c:forEach>
			</div>
		</div>
		<!-- //Tree -->	
		<div id="chartdiv" style="width: 100%; height: 400px;"></div>							
	</div>				
</div>				
</body>
</html>
