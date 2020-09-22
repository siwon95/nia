<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<link href="/css/admin.css" rel="stylesheet" type="text/css" />
<link href="/css/board.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.timepicker.css" rel="stylesheet" type="text/css" />
<link href="/css/style.min.css" rel="stylesheet" type="text/css" />
<link href="/js/vakata-jstree-5bece58/dist/themes/default/style.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/js/jquery/jquery.1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>인쇄 페이지</title>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	document.getElementById("boardPrint").innerHTML = window.opener.document.getElementById("boardPrint").innerHTML;
	
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
<body style="background:none;">
<!-- <input type="button" value="프린트 인쇄" onclick="doPrint();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="닫기" onclick="window.close();"/> -->
<div id="wrapper" style="min-width:0;padding:20px 10px;">
	<div id="right_area" style="width:auto;float:none;">
		<div class="board board-list" id="boardPrint">
			
		</div>
	</div>
</div>
</body>
</html>