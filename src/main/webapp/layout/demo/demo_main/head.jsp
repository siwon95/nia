<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.injeinc.boffice.cn.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="org.jdom2.Element" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<c:if test="${!empty ssMetaTagConent}">
	<c:out value="${ssMetaTagConent}" escapeXml="false"/>
</c:if>
<title>
	<c:if test="${ssSortOrder ne '1000000000000'}">
		<c:if test="${empty ssMenuTitle}">
			<c:out value="${ssMenuNm}"/>
		</c:if>
		<c:if test="${!empty ssMenuTitle}">
			<c:out value="${ssMenuTitle}"/>
		</c:if>
		-
	</c:if>
	<c:out value="${ssSiteNm}"/>
</title>
<!-- Base -->
<script src="<c:url value='/js/jquery.min.js' />"></script>
<script src="<c:url value='/js/jquery-ui.min.js' />"></script>
<!-- //Base -->
<!-- Injeinc -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/injeinc/base.css' />" />
<script src="<c:url value='/js/injeinc/jquery.plugin.js' />"></script>
<script src="<c:url value='/js/injeinc/common.js' />"></script>
<!-- //Injeinc -->
<!-- Site -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/demo/base.css' />" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/demo/content.css' />" />
<script src="<c:url value='/js/demo/common.js' />"></script>
<script src="<c:url value='/js/demo/main.js' />"></script>
<!-- //Site -->