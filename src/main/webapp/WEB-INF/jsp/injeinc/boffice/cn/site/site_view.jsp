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
- 파일명 : site_view.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice/cn/site/site_form.do' />";
		var modal_param = {"pageIndex":$("#pageIndex").val(), "siteIdx":$("#siteIdx").val(), "searchCondition":$("#searchCondition").val(), "searchKeyword":$("#searchKeyword").val(), "siteMenu":$("#siteMenu").val()};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
    	var ajaxParam = {"pageIndex":$("#pageIndex").val(), "siteIdx":$("#siteIdx").val(), "searchCondition":$("#searchCondition").val(), "searchKeyword":$("#searchKeyword").val(), "siteMenu":$("#siteMenu").val()};
		ajaxActionMessage("<c:url value='/boffice/cn/site/site_rmv.do'/>", ajaxParam, '', true);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section">
		<form:form commandName="SiteVo" name="SiteVo" method="post" >
		<form:hidden path="pageIndex" />
		<form:hidden path="siteIdx" />
		<form:hidden path="searchCondition" />
		<form:hidden path="searchKeyword" />
		<form:hidden path="siteMenu" />
		
		<div class="tableBox">
			<table class="form">
				<caption>사이트정보</caption>
				<colgroup>
					<col class="w20p">
					<col>
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
						<th scope="row">사이트 경로</th>
						<td><c:if test="${fn:trim(SiteVo.sitePath) ne ''}">/<c:out value="${SiteVo.sitePath}" /></c:if>/<c:out value="${SiteVo.siteCd}" />/</td>
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
			<a href="#" class="btn_m on btn_mod">수정</a>
			<a href="#" class="btn_m btn_caption btn_del">삭제</a>
		</div>
		
		</form:form>
</body>
</html>
