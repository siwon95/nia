<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 웹로그현황(오늘)
- 파일명 : webLogStatToday.jsp
- 작성일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
function fn_search() {
	document.weblogVo.action = "<c:url value='/boffice/stat/selectWebLogStatToday.do' />";
	document.weblogVo.submit();
}
$(function(){ 
	$('#html').jstree({
		"core" : {
			"check_callback" : true
		},
	    "plugins" : [ "themes", "html_data"  ]		 
	})
	$('#html').jstree("open_all");
    
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
						<input type="submit" value="검색" class="btn_inline">
					</fieldset>
				</div>
			</div>
		</form:form>
		
		<div class="divGroup cols2">
			<div>	
				<div id="chart1" class="chartWrap" style="height:300px;"></div>
				<br />
						
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
								<c:forEach items="${siteVisitrCoTodayList}" var="result" varStatus="status">
								<td class="center">
									<c:out value="${result.occrrncDe}" />			
								</td>
								<td class="center">
									<c:out value="${empty result.rdcnt?'-':result.rdcnt}" />
								</td>
								</c:forEach>
								<c:forEach items="${siteSessionVisitrCoTodayList}" var="result" varStatus="status">
								<%-- <td class="center">
									<c:out value="${result.occrrncDe}" />			
								</td> --%>
								<td class="center">
									<c:out value="${empty result.rdcnt?'-':result.rdcnt}" />
								</td>
								</c:forEach>
								<c:forEach items="${sitePageviewTodayList}" var="result" varStatus="status">
								<%-- <td class="center">
									<c:out value="${result.occrrncDe}" />			
								</td> --%>
								<td class="center">
									<c:out value="${empty result.rdcnt?'-':result.rdcnt}" />
								</td>
								</c:forEach>
								<c:if test="${fn:length(siteVisitrCoTodayList) == 0}">
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
					<c:forEach items="${siteMenuPageviewTodayList}" var="result" varStatus="status">
						<c:choose>
							<c:when test="${result.menuDepth > oldMenuDept || status.index==0}">
							<ul>
								<li id="<c:out value="${result.sortOdr}"/>" >
									<c:out value="${result.menuNm}"/>&nbsp;<c:if test="${not empty result.rdcnt }"><font color='red'>(<c:out value="${result.rdcnt}"/>)</font></c:if>
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
