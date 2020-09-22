
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Basic Board List</title>
<link href="<c:url value='/css/admin.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/board.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/layout.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery.timepicker.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.min.css' />" rel="stylesheet" type="text/css" />

<script type="text/javascript">
var reg_id = /^[a-z][a-z0-9_-]{3,11}$/;

//리스트
function doUserCheckPop() {
	document.UserVo.pageIndex.value = 1;	
	document.UserVo.action = "<c:url value='/boffice/sy/user/User_CheckPop.do' />";
	document.UserVo.submit();
}

//페이징처리 
function doUserCheckPopPag(pageIndex) {
	document.UserVo.pageIndex.value = pageIndex;
	document.UserVo.action = "<c:url value='/boffice/sy/user/User_CheckPop.do' />";
	document.UserVo.submit();
}

//창 닫기
function popClose() {
	self.close();
}

//id 부모창에 추가
function setCuIdValue(cuId) {
	if(confirm(cuId + "를 아이디로 사용하시겠습니까?")) {
		opener.setCuIdValue(cuId);
		self.close();
	}
}

$(window).load(function() {
	<c:if test="${not empty useCheck}">
		alert('<c:out value="${useCheck}"/>');
	</c:if>
});
</script>
<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}

</style>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">  
<form:form commandName="UserVo" name="UserVo" method="post" >
<form:hidden path="pageIndex" />
<form:hidden path="searchCondition" value="id"/>
	<!-- 컨텐츠 영역 -->
			<div id="contents">
			<table summary="" class="view1">
			<tr>
				<td>
					아이디
				</td>
				<td>
					<span class="sfont">
					<form:input path="searchKeyword" size="12" maxlength="12"/>
					<a href="javascript:doUserCheckPop();" class="btn2">조회</a>
					</span>
				</td>
			</tr>			
		</table>
				
				<table summary="" class="list1">
					<caption></caption>
					<colgroup>
						<col width="10%" />
						<col width="*%" />
					</colgroup>
					<thead>
						<tr>
							<th>아이디</th>
							<th>본인인증키</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${resultList}" var="result" varStatus="status">
						<tr>
							<td><a href="javascript:setCuIdValue('<c:out value="${result.cuId}" />')"><c:out value="${result.cuId}" /></a></td>
							<td><c:out value="${result.ownAuth}" /></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				
				<!--paginate -->
				<div class="paginate">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doUserPag" />
				</div>
				<!--//paginate -->
								
				<!-- 버튼 -->
				<div class="btn_zone">
					<a href="#" onclick="javascript:popClose();" class="btn2">닫기</a>
				<!-- //버튼 -->
		
			</div>
			<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
