<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%
request.setCharacterEncoding("utf-8");
response.addHeader("Content-Disposition", "attachment;filename=test.xls");

%>
<html>
<head>
<link href="<c:url value='/css/admin.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/board.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/layout.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
<body>
<form:form commandName="MgrVo" name="MgrVo" method="post" >
<div id="contents">
				<fieldset class="date_search">
				    <select id="test_combo" name="test_combo">
							<option value="">선택하세요</option>
					</select>
					<span class="sfont">
					<form:input path="searchId" size="30"/>
					<a href="javascript:doMgrList();" class="btn2">조회</a>
					</span>
				</fieldset>
				
				<table summary="" class="list1">
					<caption></caption>
					<colgroup>
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="13%" />
						<col width="13%" />
						<col width="13%" />
						<col width="*" />
					</colgroup>
					<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>비빌번호</th>
						<th>이름</th>
						<th>상태</th>
						<th>부서</th>
						<th>등록일자</th>
						<th>변경일자</th>
						<th>권한</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList}" var="result" varStatus="status">
					<tr>
						<td><c:out value="${status.count}" /></td>
						<td class="sbj"><c:out value="${result.mgrId}" /></td>
						<td><c:out value="${result.mgrPw}" /></td>
						<td><c:out value="${result.mgrName}" /></td>
						<td><c:out value="${result.statusCd}" /></td>
						<td><c:out value="${result.mgrDept}" /></td>
						<td><c:out value="${result.regDt}" /></td>
						<td><c:out value="${result.modDt}" /></td>
						<td></td>
					</tr>
					</c:forEach>
				
					</tbody>
				</table>
				
				<!--paginate -->
				<div class="paginate">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doMgrPag" />
				</div>
				<!--//paginate -->
				
				<!-- 버튼 -->
				<div class="btn_zone">
				<a href="javascript:doMgrForm();" class="btn2">등록</a>
				</div>
				<!-- //버튼 -->
		
			</div>
</form:form>
</body>
</head>
</html>

