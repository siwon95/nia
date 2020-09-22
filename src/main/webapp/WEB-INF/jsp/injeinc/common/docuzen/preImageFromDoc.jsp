<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0, user-scalable=yes">
<title>관악구 파일 미리보기</title>
<link rel="stylesheet" type="text/css" href="/css/common/common.css">
<script type="text/javascript" src="/js/jquery/jquery.1.10.2.js"></script>
<script>
$(window).load(function(){
	var origin_w = $('.preview-img').width();
	$('.preview-img').css({'width':'100%', 'max-width':origin_w});
});
</script>
<style>
body{text-align:center;}
</style>
</head>
<body>
<c:if test="${strStatus == 0}">
<c:forEach var="fileName" items="${fileNameList }">
<img src="http://211.241.36.51:8080/ctrl/result.aspx?fileName=<c:out value='${fileName}' />" alt="파일 미리보기" class="preview-img"/><br/>
</c:forEach>
</c:if>
<c:if test="${strStatus != 0}">
<script type="text/javascript">
	//<![CDATA[
		alert('문서변환에 실패했습니다.<c:out value="${strStatus == 0}"/>');
		window.close();
	//]]>
</script>
</c:if>
</body>
</html>


