<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%>
<% 
response.setHeader("Content-Disposition","attachment;filename=userstatistics_list.xls");

response.setHeader("Content-Description", "JSP Generated Data");
%>
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

<style type="text/css">
	.list1{ width:100%; margin-bottom:10px; border-left:1px solid #d1cdc9;}
	.list1 th{ border-top:2px solid #5a5a5a; font-size:95%; background:#f4f4f4; color:#444;}
	.list1 th.none{background:none;}
	
	.list1 th, .list1 td{padding:7px 10px; border-bottom:1px solid #d1cdc9; border-right:1px solid #d1cdc9; height:20px; min-height:20px; text-align:center}
	.list1 td.sbj{text-align:left; font-weight:bold;}
	.list1 td a.file{background:url(../images/adm/icon_file.gif) left no-repeat; padding-left:15px; text-decoration:underline; color:#4f4f4f; font-size:95%;}
	.list1 td span.cate1{ background:#d9d9d9; border:1px solid #bcbcbc; font-size:90%; letter-spacing:-1px; padding:2px;}
	.list1 td span.cate2{ background:#19abe2; border:1px solid #0d8ebe; font-size:90%; letter-spacing:-1px; padding:2px; color:#fff}
	
	.write{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.write thead th{text-align:center; color:#444;border-bottom:1px solid #5a5a5a;}
	.write th, .write td{padding:10px; border-bottom:1px solid #d1cdc9;}
	.write th{ font-size:95%; background:#f4f4f4; color:#717171; text-align:left; padding-left:20px;}
	.write td li{ margin:3px 0;}
	.write td li label{  font-size:95%; margin:0 10px 0 20px; background:url(../../images/adm/popup/bullet02.gif) left no-repeat; padding-left:5px;}
	
	.write td span.state1{ background:#fdb645; font-weight:bold; font-size:95%; color:#fff; padding:5px 8px}
	.write td textarea{width:98%;}
	.write td label{margin-right:20px;}
	.write .top_line{ border-top:2px solid #3d301f;}
</style>
</head>
<body>
	<div id="contents"><!-- 컨텐츠 영역 -->
	<table summary="" class="list1">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="25%" />
			<col width="25%" />
			<col width="25%" />						
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
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="result" varStatus="status">
		<tr>
			<td>
				<c:if test="${!status.last}">
					<c:choose>
					<c:when test="${!empty UserStatisticsVo.scMonth}">&nbsp;<c:out value="${UserStatisticsVo.scYear}" />-<c:out value="${UserStatisticsVo.scMonth}" />-<c:out value="${result.resultDate}" /> </c:when>
					<c:when test="${!empty UserStatisticsVo.scYear}">&nbsp;<c:out value="${UserStatisticsVo.scYear}" />-<c:out value="${result.resultDate}" /> </c:when>
					<c:otherwise><c:out value="${result.resultDate}" /></c:otherwise>
				</c:choose>
				</c:if>
				<c:if test="${status.last}">합계</c:if>
			</td>
			<td><c:out value="${result.memberCnt}" /></td>
			
		</tr>
			</c:forEach>
		</tbody>
	</table>
	</div><!-- //컨텐츠 영역 -->
</body>
</html>