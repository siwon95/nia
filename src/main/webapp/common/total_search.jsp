<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo"%>
<%@page import="egovframework.injeinc.foffice.ex.dept.service.DeptFSvc"%>
<%@page import="java.util.List"%>
<script type="text/javascript">
//<![CDATA[
// 	function fTotalSearch(inValue) {
// 		document.search.collection.value=inValue;
// 	}
	
	function OpenSearch() {
		if($("#total-search-box #query").val() == ""){
			alert("검색어를 입력하여 주세요.");
			return false;
		}else{
			var gsWin = window.open("/site/search/main.jsp","winName", "resizable=yes menubar=yes status=yes toolbar=yes fullscreen=yes scrollbars=yes");
	        $("#total-search-box .frmTotalSearch").attr("action","/site/search/main.jsp");
	        $("#total-search-box .frmTotalSearch").attr("target","winName");
	        $("#total-search-box .frmTotalSearch").submit();
		}
	}
//]]>
</script>
<form name="search" class="frmTotalSearch" action="/site/search/main.jsp" method="POST" target="winName">
<!-- 	<input type="hidden" name="collection" value="ALL"> -->
				<select id="total-search-select" class="total-search"  name="collection" >
					<option value="ALL" selected>통합검색</option>
					<option value="MENU" >메뉴검색</option>
					<option value="EMPLOYEE" >직원/업무</option>
					<option value="MINWON" >민원사무편람</option>
					<option value="CONTENTS" >웹페이지</option>
					<option value="BBS" >게시판</option>
					<option value="PHOTO" >이미지</option>
					<option value="RESERVATION" >통합예약</option>
				</select>
			
			<input type="text" placeholder=""  name="query"  id="query"   class="top-search-input" />
			<input type="image" src="/images/yangcheon/common/top-search-btn.gif" onclick="OpenSearch();return false" title="검색" class="top-search-btn" />
</form>