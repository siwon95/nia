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

</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
	<table summary="구청장에게바란다 만족도조사" border="1">
		<caption>구청장에게바란다 만족도조사</caption>
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
</body>
</html>
