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
<title>Basic Board List</title>
<script type="text/javascript">
//<![CDADA[
	//등록페이지
	function doSiteForm() {
		document.SiteVo.siteMenu.value = "form";
		document.SiteVo.action = "<c:url value='/site/Site_Form.do' />";
		document.SiteVo.submit();
	}
	
	//뷰페이지
	function doSiteView(idx){
		document.SiteVo.siteIdx.value = idx;
		document.SiteVo.action = "<c:url value='/site/Site_View.do' />";
		document.SiteVo.submit();
	}
	
	//페이징처리 
	function doSitePag(pageIndex) {
		document.SiteVo.pageIndex.value = pageIndex;
		document.SiteVo.action = "<c:url value='/site/Site_List.do' />";
		document.SiteVo.submit();
	}
	
	//리스트
	function doSiteList(pageIndex) {
		if(pageIndex == null) {
			document.SiteVo.pageIndex.value = 1;	
		} else {
			document.SiteVo.pageIndex.value = pageIndex;
		}
		
		document.SiteVo.action = "<c:url value='/site/Site_List.do' />";
		document.SiteVo.submit();
	}
	
	$(document).ready(function(){
		<c:if test="${not empty message}">
		alert("<spring:message code="${message}" />");
		</c:if>
		<c:if test="${not empty param.message}">
		alert("<spring:message code="${param.message}" />");
		</c:if>
	});
	
	$(window).load(function() {
		cm_combo("#codeList", "<c:out value="${pageContext.request.contextPath}"/>");
		
	});
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
<form:form commandName="SiteVo" name="SiteVo" method="post">
	<form:hidden path="pageIndex" />
	<form:hidden path="siteIdx" />
	<form:hidden path="siteKdCd" />
	<form:hidden path="siteMenu" />
	<!-- 컨텐츠 영역 -->
	<div id="contents">
		<fieldset class="date_search">
			<label for="siteKdCd">사이트 유형</label>
			<form:select path="searchCondition">
				<form:option value="" label="--선택하세요--" />
				<form:options items="${codeList}" itemValue="code"	itemLabel="codeName" />
			</form:select>
			<span class="sfont">
				<label for="siteNm">사이트명</label>
				<form:input	path="searchKeyword" size="30" />
				<a	href="javascript:doSiteList(1);" class="btn2">조회</a>
			</span>
		</fieldset>

		<table summary="" class="list1">
			<caption></caption>
			<colgroup>
				<col width="7%" />
				<col width="18%" />
				<col width="10%" />
				<col width="*" />
				<col width="13%" />
				<col width="13%" />
				<col width="8%" />
				<col width="8%" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>사이트명</th>
					<th>사이트코드</th>
					<th>사이트도메인</th>
					<th>사이트 유형</th>
					<th>등록자</th>
					<th>등록일자</th>
					<th>변경일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resultList}" var="result" varStatus="status">
					<tr>
						<td><c:out value="${status.count}" /></td>
						<td class="sbj"><a href="javascript:doSiteView('<c:out value="${result.siteIdx}" />');"><c:out value="${result.siteNm}" /></a></td>
						<td><c:out value="${result.siteCd}" /></td>
						<td><c:out value="${result.siteDomain}" /></td>
						<td><c:out value="${result.siteKdNm}" /></td>
						<td><c:out value="${result.regNm}" /></td>
						<td><c:out value="${result.regDt}" /></td>
						<td><c:out value="${result.modDt}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!--paginate -->
		<div class="paginate">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doSitePag" />
		</div>
		<!--//paginate -->

		<!-- 버튼 -->
		<div class="btn_zone">
			<!-- 				<a href="" class="btn1">취소하기</a> -->
			<a href="javascript:doSiteForm();" class="btn2">등록</a>
		</div>
		<!-- //버튼 -->

	</div>
	<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>