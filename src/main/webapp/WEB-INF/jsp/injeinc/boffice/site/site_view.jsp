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
<title>Basic Board List</title>
<script type="text/javascript">
//<![CDATA[
	var chkreg = "";
	
	function doSiteList() {
		var frm = document.SiteVo;
		frm.action = "<c:url value='/site/Site_List.do' />";
		frm.submit();
	}
	
	function doSiteForm() {
		var frm = document.SiteVo;
		frm.siteMenu.value = "update";
		frm.action = "<c:url value='/site/Site_Form.do' />";
		frm.submit();
	}
	
	function doSiteRmv() {
		var frm = document.SiteVo;
		frm.action = "/site/Site_Rmv.do";
		frm.submit();	
	}
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
<form:form commandName="SiteVo" name="SiteVo" method="post">
	<form:hidden path="pageIndex" />
	<form:hidden path="siteIdx" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" />
	<form:hidden path="siteMenu" />
	<!-- 컨텐츠 영역 -->
	<div id="contents">

		<table summary="" class="write">
			<caption></caption>
			<colgroup>
				<col width="15%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th>사이트 명</th>
					<td><c:out value="${SiteVo.siteNm}" /></td>
				</tr>
				<tr>
					<th>사이트 코드</th>
					<td><c:out value="${SiteVo.siteCd}" /></td>
				</tr>
				<tr>
					<th>사이트 도메인</th>
					<td><c:out value="${SiteVo.siteDomain}" /></td>
				</tr>
				<tr>
					<th>사이트 유형</th>
					<c:forEach var="codeList" items="${codeList}">
						<c:if test="${codeList.code eq SiteVo.siteKdCd}">
							<td><c:out value="${codeList.codeName}" /></td>
						</c:if>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		<!-- 버튼 -->
		<div class="btn_zone">
			<a href="javascript:doSiteForm();" class="btn1">수정</a>
			<a href="javascript:doSiteRmv();" class="btn1">삭제</a>
			<a href="javascript:doSiteList();" class="btn2">취소</a>
		</div>
		<!-- //버튼 -->

	</div>
	<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>