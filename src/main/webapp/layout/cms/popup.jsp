<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>통합관리시스템</title>
<link href="<c:url value='/css/site/mnt/board.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/layout.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/site/cmm/jquery.1.10.2.js' />"></script>

<!-- page head start -->
<decorator:head />
<!-- //page head end -->
</head>
<body>

	<!-- 업무 영역 -->
	<decorator:body />
	<!-- //업무 영역 -->
	
</body>
</html>