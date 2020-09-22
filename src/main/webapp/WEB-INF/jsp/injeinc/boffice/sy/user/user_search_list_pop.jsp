<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>아이디또는 이름으로 회원 찾기</title>
<link href="<c:url value='/css/admin.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/board.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/layout.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery.timepicker.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.min.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.1.10.2.js' />"></script>
<script type="text/javascript">
//<![CDATA[

	function doSearch() {

		if($("#searchKeyword").val() == "") {
			alert("검색어를 입력해주십시오.");
			$("#searchKeyword").focus();
			return;
		}
		
		$("#UserVo").attr("action", "<c:url value='/boffice/user/UserSearchListPop.do' />").submit();
		
	}
	
	function selectUser(cuId) {
		window.opener.focus();
		window.opener.selectUser(cuId);
	}

	//창 닫기
	function popClose() {
		self.close();
	}
//]]>
</script>
</head>
<body style="background:none;">
<form:form commandName="UserVo" name="UserVo" method="post" >
	<div id="contents">
	
		<table summary="아이디와 비밀번호로 회원정보 검색" class="view">
			<colgroup>
				<col width="20%"/>
				<col width="80%"/>
			</colgroup>
			<tbody>
				<th scope="row"><label for="searchCondition">검색</label></th>
				<td>
					<form:select path="searchCondition">
						<form:option value="1" label="아이디" />
						<form:option value="2" label="이름" />
					</form:select>
					<span class="sfont">
					<label for="searchKeyword" class="hidden">검색어</label>
					<form:input path="searchKeyword" size="30"/>
					<button class="btn2 btn_input" onclick="doSearch(); return false;"><i class="fa fa-search"></i> 검색</button>
					</span>
				</td>
			</tbody>
		</table>
				
		<table summary="회원 목록" class="list1">
			<caption>회원 목록</caption>
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="35%" />
				<col width="15%" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">연번</th>
					<th scope="col">아이디</th>
					<th scope="col">이름</th>
					<th scope="col">선택</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td><c:out value="${status.count}" /></td>
					<td><c:out value="${result.cuId}" /></td>
					<td><c:out value="${result.cuName}" /></td>
					<td><a href="javascript:selectUser('<c:out value="${result.cuId}"/>');" class="btn">선택</a></td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="4"><spring:message code="common.nodata.msg" /></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		
		<div class="btn_zone" style="text-align:center;">
			<a href="javascript:popClose();" class="btn1">닫기</a>
		</div>
	</div>
</form:form>
</body>
</html>
