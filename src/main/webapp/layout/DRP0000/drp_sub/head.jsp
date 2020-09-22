<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>  
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:set var="arrMenuPath" value="${fn:split(sessionScope['ssMenuPath'],'>')}"/>
<title>${arrMenuPath[1]}&gt; ${arrMenuPath[2]} &gt; ${arrMenuPath[3]}</title>

<!-- 페이스북 공유하기 Open Graph Tag -->
<meta property="og:title" content="${arrMenuPath[1]}&gt; ${arrMenuPath[2]} &gt; ${arrMenuPath[3]} " />
<meta property="og:description" content="test" />
<meta property="og:site_name" content="사이트명 ">
<meta property="og:image" content="http://106.251.236.2:7891/images/drp/images/main_visuall_bg.png" />
<meta property="og:image:width" content="1200" />
<meta property="og:image:height" content="627" />
<meta property="og:url" content="http://106.251.236.2:7891${ssMenuUrl}" />
<meta property="og:type" content="website" />

<link rel="stylesheet" href="/css/drp/style.css"> 
<script src="/js/drp/jquery.min.js"></script>
<script src="/js/drp/jquery.plugin.js"></script>
<script src="/js/drp/common.js"></script>

<!-- 카카오스토리 공유하기 -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

</head>
<!-- webapp/layout/DRP0000/drp_sub/head.jsp -->
