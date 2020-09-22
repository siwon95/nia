<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 회원목록
- 파일명 : user_view.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
- 상태 : 수정중
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
//<![CDATA[
var chkreg = "";

function doSiteList() {
	var frm = document.SiteVo;
	frm.action = "<c:url value='/boffice/cn/site/site_list.do' />";
	frm.submit();
}

function doSiteForm() {
	var frm = document.SiteVo;
	frm.siteMenu.value = "update";
	frm.action = "<c:url value='/boffice/cn/site/site_form.do' />";
	frm.submit();
}

function doSiteRmv() {
	var frm = document.SiteVo;
	frm.action = "/boffice/cn/site/site_rmv.do";
	frm.submit();	
}

//]]>
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
<form:form commandName="SiteVo" name="SiteVo" method="post" >
<form:hidden path="pageIndex" />
<form:hidden path="siteIdx" />
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="siteMenu" />

	<div class="section">
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col width="15%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">사이트 명</th>
						<td><c:out value="${SiteVo.siteNm}" /></td>
					</tr>
					<tr>
						<th scope="row">사이트 코드</th>
						<td><c:out value="${SiteVo.siteCd}" /></td>
					</tr>
					<tr>
						<th scope="row">사이트 도메인</th>
						<td><c:out value="${SiteVo.siteDomain}" /></td>
					</tr>
					<tr>
						<th scope="row">사이트 유형</th>
						<c:forEach var="codeList" items="${codeList}">
							<c:if test="${codeList.code eq SiteVo.siteKdCd}" >
								<td><c:out value="${codeList.codeName}" /></td>
							</c:if>
						</c:forEach>
					</tr>					
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<a href="javascript:doSiteForm();" class="btn_inline on">수정</a>
			<a href="javascript:doSiteRmv();" class="btn_inline btn_caption">삭제</a>					
			<a href="javascript:doSiteList();" class="btn_inline">목록</a>
		</div>
	</div>
	
</form:form>
</body>
</html>
