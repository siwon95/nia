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
<script src="<c:out value="${pageContext.request.contextPath}"/>/script/web/common.js"></script>
<script type="text/javascript">
//<![CDATA[
	var chkreg = "";
	
	function doSiteList() {
		var frm = document.SiteVo;
		frm.action = "<c:url value='/site/Site_List.do' />";
		frm.submit();
	}
	
	function doSiteReg() {
		
		var frm = document.SiteVo;
		var test = "<c:out value='${menuGubun}'/>";
		alert(test);
		
		if (cm_check_empty($('input[name=siteNm]').attr("title"), $('input[name=siteNm]')) == false) {
			return;
		}
		
		if (cm_check_empty($('input[name=siteCd]').attr("title"), $('input[name=siteCd]')) == false) {
			return;
		}
		
		if (cm_check_empty($('input[name=siteDomain]').attr("title"), $('input[name=siteDomain]')) == false) {
			return;
		}
		
		if (cm_check_empty($('select[name=siteKdCd]').attr("title"), $('select[name=siteKdCd]')) == false) {
			return;
		}
		
		if(test == "form") {
			frm.action = "/site/Site_Reg.do";	
		}else if(test == "update"){
			frm.action = "/site/Site_Mod.do";
		}
		
		frm.submit();
	}
	
	function doSiteMod() {
		var frm = document.SiteVo;
		frm.action = "/site/Site_Mod.do";
		frm.submit();
	}
	
	/* function submitAction(){
		var action = "<c:out value='${SiteVo.siteMenu}'/>";
		switch(action) {
			case 'reg' : doSiteReg(); break;
			case 'update' : doSiteMod(); break;
		}
	} */
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
<form:form commandName="SiteVo" name="SiteVo" method="post">
	<form:hidden path="pageIndex" />
	<form:hidden path="siteIdx" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" />
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
					<th><label for="siteNm">사이트 명</label></th>
					<td><form:input path="siteNm" size="20" title="사이트 명" /></td>
				</tr>
				<tr>
					<th><label for="siteCd">사이트 코드</label></th>
					<td><form:input path="siteCd" size="20" title="사이트 코드" /></td>
				</tr>
				<tr>
					<th><label for="siteDomain">사이트 도메인</label></th>
					<td><form:input path="siteDomain" size="20" title="사이트 도메인" /></td>
				</tr>
				<tr>
					<th><label for="siteKdCd">사이트 유형</label></th>
					<td>
						<form:select path="siteKdCd" title="사이트 유형">
							<form:option value="" label="--선택하세요--" />
							<form:options items="${codeList}" itemValue="code" itemLabel="codeName" />
						</form:select>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- 버튼 -->
		<div class="btn_zone">
			<a href="javascript:doSiteReg();" class="btn1">저장</a>
			<a href="javascript:doSiteList();" class="btn2">취소</a>
		</div>
		<!-- //버튼 -->

	</div>
	<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>