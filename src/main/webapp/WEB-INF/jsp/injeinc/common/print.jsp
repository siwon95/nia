<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String contentType = "contents";
String sitePath = "yangcheon";
if(request.getParameter("contentType")!=null){
	String tempContentType = request.getParameter("contentType");
	if(!tempContentType.trim().equals("")){
		contentType = tempContentType;
	}
}

if(request.getParameter("sitePath")!=null){
	String tempSitePath = request.getParameter("sitePath");
	if(!tempSitePath.trim().equals("")){
		sitePath = tempSitePath;
	}
}
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<script type="text/javascript" src="/js/jquery/jquery.1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery.timepicker.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.min.css' />" rel="stylesheet" type="text/css" />


<%-- 	<link rel="stylesheet" href="/css/<%=sitePath%>/global.css" />
	<link rel="stylesheet" href="/css/<%=sitePath%>/global-navigation.css" />
	<link rel="stylesheet" href="/css/<%=sitePath%>/subLayout.css" />
	<link rel="stylesheet" href="/css/<%=sitePath%>/m1.css" />
	<link rel="stylesheet" href="/css/<%=sitePath%>/m2.css" />
	<link rel="stylesheet" href="/css/<%=sitePath%>/m3.css" />
	<link rel="stylesheet" href="/css/<%=sitePath%>/m4.css" />
	<link rel="stylesheet" href="/css/<%=sitePath%>/m5.css" /> --%>
<!--[if lt IE 9]>
	<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.timepicker.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	document.write(window.opener.document.head.innerHTML.replace(/script/gi, "a"));
</script>
<title>인쇄 페이지</title>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	document.getElementById("container-contents").innerHTML = window.opener.document.getElementById("<%=contentType%>").innerHTML;
	$(".noprint").css("display","none");
});
window.print();
/*
function doPrint(){
	 document.body.innerHTML = document.getElementById("printDiv").innerHTML;
}
*/
//]]>
</script>
</head>
<body>
<br/>
<!-- <input type="button" value="프린트 인쇄" onclick="doPrint();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="닫기" onclick="window.close();"/> -->
<div class="content-area">
	<article id="contents">
<div id="printDiv" style="margin:0 20px;text-align:left;">
	<div id="container-contents">
	
	</div>
</div>
	
	<div style="width:100%;text-align:center;padding-top:10px">
		<a href="javascript:self.close();" class="btn btn-type2">인쇄창닫기</a>
	</div>
	</article>
</div>
</body>
</html>