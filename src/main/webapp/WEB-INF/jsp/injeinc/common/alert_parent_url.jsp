<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta charset="utf-8" />
<script>
alert("<c:out value="${alertMsg}"/>");
if('<c:out value="${returnUri}"/>' != null && '<c:out value="${returnUri}"/>' != ""){
	top.location.href = "<c:out value="${returnUri}"/>".replace(/&amp;/gi,'&');
}else{
	history.back();
}
</script>
</head>
<body>
</body>
</html>