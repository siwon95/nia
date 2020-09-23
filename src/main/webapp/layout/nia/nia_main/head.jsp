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
<c:if test="${!empty ssSearchEngine}">
<meta name="keywords" content = "<c:out value='${ssSearchEngine}'/>" />
</c:if>
<c:if test="${!empty ssDescriptionContent}">
<meta name = "description" content="<c:out value='${ssDescriptionContent}'/>" />
</c:if>
<title>
	<c:if test="${ssSortOrder ne '1000000000000'}">
		<c:if test="${empty ssMenuTitle}">
			<c:out value="${ssMenuNm}"/>
	</c:if>
		<c:if test="${!empty ssMenuTitle}">
			<c:out value="${ssMenuTitle}"/>
		</c:if>
	</c:if>
	<c:out value="${ssSiteNm}"/>
</title>
<link href="/css/nia/font.css" rel="stylesheet" type="text/css">
<link href="/css/nia/style.css" rel="stylesheet" type="text/css">
<link href="/css/nia/base.css" rel="stylesheet" type="text/css">
<link href="/css/nia/new_base.css" rel="stylesheet" type="text/css">
<script src="/js/nia/common.js"></script>
<script src="/js/nia/jquery.min.js"></script>
<script src="/js/nia/jquery-ui.min.js"></script>
<script src="/js/nia/jquery.plugin.js"></script>
<script src="/js/injeinc/common.js"></script>