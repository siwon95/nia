<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<script type='text/javascript'>
//<![CDATA[
	
   if(confirm("스크랩 되었습니다. 마이페이지로 이동하시겠습니까?")){
	   location.href = "<c:out value="${returnUri}"/>".replace(/&amp;/gi,'&');
   }else{
	   history.back();
   }
  
   
	/* if ('<c:out value="${returnUri}"/>' != null && '<c:out value="${returnUri}"/>' != ""){
		history.back();
		
	} else {
		location.href = "<c:out value="${returnUri}"/>".replace(/&amp;/gi,'&');
		
	} */
//]]>
</script>
<title>알림글</title>
</head>
<body></body>
</html>