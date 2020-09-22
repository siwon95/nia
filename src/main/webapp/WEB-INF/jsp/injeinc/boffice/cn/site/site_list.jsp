<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 사이트관리
- 파일명 : site_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>

	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/cn/site/site_form.do' />";
		var modal_param = {"pageIndex":$("#pageIndex").val(), "siteIdx":$("#siteIdx").val(), "siteKdCd":$("#siteKdCd").val(), "siteMenu":"form"};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var paramIdx = $(this).parents("li").eq(0).attr("data-idx");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/cn/site/site_form.do' />";
		var modal_param = {"pageIndex":$("#pageIndex").val(), "siteIdx":paramIdx, "siteKdCd":$("#siteKdCd").val(), "siteMenu":"update"};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_detail").click(function(e){
		e.preventDefault();
		var paramIdx = $(this).parents("li").eq(0).attr("data-idx");
		var modal_id = "modal_detail";
		var modal_url = "<c:url value='/boffice_noDeco/cn/site/site_view.do' />";
		var modal_param = {"pageIndex":$("#pageIndex").val(), "siteIdx":paramIdx, "siteKdCd":$("#siteKdCd").val(), "siteMenu":$("#siteKdCd").val()};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	
	<form:form commandName="SiteVo" name="SiteVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
	<form:hidden path="pageIndex" />
	<form:hidden path="siteIdx" />
	<form:hidden path="siteKdCd" />
	<form:hidden path="siteMenu" />
	<!-- <div class="search">
		<fieldset>
			<label for="searchCondition">사이트 유형</label>	
			<form:select path="searchCondition">
				<form:option value="" label="--선택하세요--" />
				<form:options items="${codeList}" itemValue="code" itemLabel="codeName" />
			</form:select>&nbsp;&nbsp;&nbsp;
			<label for="searchKeyword">사이트명</label>
			<form:input path="searchKeyword" size="30"/>
			<a href="javascript:doSiteList(1);" class="btn_inline btn_search">조회</a>
		</fieldset>
	</div> -->
	</form:form>

	<div class="section">
		<ul class="thumbList">
			<c:forEach items="${resultList}" var="result" varStatus="status">
			<li data-idx="<c:out value='${result.siteIdx}' />">
				<div class="thumbImg">
					<a href="#" class="btn_mod">
						<img src="#" alt="No Image" onerror="this.style.display='none';">
					</a>
					<div class="previewBox">
						<a href="#" class="btn_previewBoxOpen"><em>미리보기</em></a>
						<ul>
						<c:set var="siteDomain" value="${result.siteDomain}"/>
						<c:if test="${fn:indexOf(result.siteDomain,'http://') == -1 && fn:indexOf(result.siteDomain,'http://') == -1}">
							<c:set var="siteDomain" value="http://${siteDomain}"/>
						</c:if>
							<li class="item1"><a href="#" class="btn_windowPopup" data-url="<c:out value="${siteDomain}" />" data-option="width=1200,height=800" title="새창열림"><em>데스크톱</em></a></li>
							<li class="item2"><a href="#" class="btn_windowPopup" data-url="<c:out value="${siteDomain}" />" data-option="width=768,height=700" title="새창열림"><em>테블릿</em></a></li>
							<li class="item3"><a href="#" class="btn_windowPopup" data-url="<c:out value="${siteDomain}" />" data-option="width=360,height=640" title="새창열림"><em>모바일</em></a></li>
						</ul>
					</div>
				</div>
				<div class="desc">
					<span class="soundOnly">
						번호 : <c:out value="${status.count}" />
						사이트코드 : <c:out value="${result.siteCd}" />
						사이트유형 : <c:out value="${result.siteKdNm}" />
						등록자 : <c:out value="${result.regNm}" />
						등록일 : <c:out value="${result.regDt}" />
						변경일 : <c:out value="${result.modDt}" />
					</span>
					<a href="#" class="btn_mod">
						<span class="subject"><c:out value="${result.siteNm}" /></span>
						<span class="url"><c:out value="${result.siteDomain}" /></span>
					</a>
				</div>
				<div class="btnArea">
					<a href="/boffice/cn/menu/menu_list.do?siteCd=<c:out value="${result.siteCd}" />" class="btn_inline" target="_blank" title="새창열림">메뉴관리</a>
					<a href="/boffice/sy/board/BoardList.do?mgrSiteCd=<c:out value="${result.siteCd}" />" class="btn_inline" target="_blank" title="새창열림">게시판관리</a>
				</div>
			</li>
			</c:forEach>
			<li>
				<a href="#" class="btn_add">
					<div class="thumbImg add">신규 등록</div>
				</a>
			</li>
		</ul>
	</div>
</body>
</html>
