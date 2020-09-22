<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 템플릿관리
- 파일명 : layout_list.jsp
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
		var modal_url = "<c:url value='/boffice_noDeco/cn/layout/layout_form.do' />";
		var modal_param = {"siteCd":$("#siteCd").val(), "layoutCode":$("#layoutCode").val(), "pageMode":"form"};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var paramIdx = $(this).parents("li").eq(0).attr("data-idx");
		var paramCd = $(this).parents("li").eq(0).attr("data-cd");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/cn/layout/layout_form.do' />";
		var modal_param = {"siteCd":paramCd, "layoutCode":paramIdx, "pageMode":"update"};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
});

function fn_template_open(siteCd,layoutCode,option) {
	$("#siteCd").val(siteCd);
	$("#layoutCode").val(layoutCode);
	
	window.open('about:blank', 'preivew', option);
	
	var frm = document.LayoutVo;
	frm.action = "/boffice/cn/layout/layout_preview.do"; 
	frm.method = "post";
	frm.target = "preivew";
	frm.submit();
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<form:form commandName="LayoutVo" name="LayoutVo" method="post">
	<form:hidden path="pageMode" />
	<form:hidden path="siteCd" />
	<form:hidden path="layoutCode" />
	</form:form>
	
	<div class="section">
		<ul class="thumbList">
			<c:forEach items="${listLayout}" var="result" varStatus="status">
			<li data-idx="<c:out value="${result.layoutCode}" />" data-cd="<c:out value="${result.siteCd}" />">
				<div class="thumbImg">
					<a href="#" class="btn_mod"><img src="/images/cms/sample/sample_demo.jpg" alt="데모템플릿 메인" onerror="this.style.display='none';"></a>
					<%-- <div class="previewBox">
						<a href="#" class="btn_previewBoxOpen"><em>미리보기</em></a>
						<ul>
							<li class="item1"><a href="javascript:fn_template_open('<c:out value="${result.siteCd}" />','<c:out value="${result.layoutCode}" />','width=1200,height=800')"><em>데스크톱</em></a></li>
							<li class="item2"><a href="javascript:fn_template_open('<c:out value="${result.siteCd}" />','<c:out value="${result.layoutCode}" />','width=768,height=700')"><em>테블릿</em></a></li>
							<li class="item3"><a href="javascript:fn_template_open('<c:out value="${result.siteCd}" />','<c:out value="${result.layoutCode}" />','width=360,height=640')"><em>모바일</em></a></li>
						</ul>
					</div> --%>
				</div>
				<div class="desc">
					<a href="#" class="btn_mod">
						<span class="subject"><c:out value="${result.siteNm}" /></span>
						<span><c:out value="${result.layoutDesc}" /></span>
					</a>
					<span class="soundOnly">
						경로 : <c:out value="${result.filePath}" />
						등록자 : <c:out value="${result.regId}" />
						등록일 : <c:out value="${fn:substring(result.regDt,0,10)}" />
					</span>
				</div>
			</li>
			</c:forEach>
			<li>
				<a href="#" class="btn_add">
					<div class="thumbImg add">신규 등록</div>
				</a>
			</li>
		</ul>
		<div class="paging"></div>
	</div>
	
</body>
</html>