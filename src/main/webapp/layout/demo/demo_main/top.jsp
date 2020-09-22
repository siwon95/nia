<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.injeinc.boffice.cn.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="org.jdom2.Element" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="egovframework.injeinc.boffice.cn.common.UtilSvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="depth1" value="${fn:substring(ssSortOrder,1,3)}"/>
<c:set var="depth2" value="${fn:substring(ssSortOrder,3,5)}"/>
<c:set var="depth3" value="${fn:substring(ssSortOrder,5,7)}"/>
</head>
<body>
<div id="skipLink">
	<a href="#gnb">메뉴 바로가기</a>
	<a href="#container">컨텐츠 바로가기</a>
	<a href="#footer">하단 바로가기</a>
</div>
<div id="wrap" class="main">
	<!-- 상단 -->
	<div id="header">
		<div class="inner">
			<h1><a href="/"><img src="/images/demo/layout/logo.png" alt="인재아이엔씨"></a></h1>
			<!-- 상단 메뉴 -->
			<div id="gnb">
				<jsp:include page="/layout/module/gnb.jsp" flush="true"/>
			</div>
			<!-- //상단 메뉴 -->
			<span class="user">
				<img src="/images/cms/icon_user.png" alt="">
				<c:choose>
					<c:when test ="${!empty ss_id}">
						<b><c:out value="${ss_id}"/>님</b>
						<a href="#" id="btn_mod">마이페이지</a>
						<a href="#" id="btn_logout">로그아웃</a>
					</c:when>
					<c:otherwise><a href="#" id="btn_login">로그인</a></c:otherwise>
				</c:choose>
			</span>
			<a href="#" class="btn_navAllOpen">전체메뉴 열기/닫기</a>
		</div>
	</div>
	<!-- //상단 -->
	<!-- 전체 메뉴 -->
	<div id="navAll">
		<jsp:include page="/layout/module/gnb.jsp" flush="true"/>
	</div>
	<!-- //전체 메뉴 -->
	<!-- 컨테이너 -->
	<div id="container">
	<c:if test="${ssSocialShowYn eq 'Y' && ssMenuType ne 'program'}"><jsp:include page="/layout/module/sns_share.jsp" flush="true"/></c:if>