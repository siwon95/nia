<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%-- ------------------------------------------------------------
- 제목 : 전체 레이아웃
- 파일명 : main.jsp
- 작성일 : 2018-01-18
- 작성자 : 김근 차장
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1" />
<c:import url="/layout/cms/head.jsp" ></c:import>
<!-- Page Head -->
<decorator:head />
<!-- //Page Head -->
</head>
<body>
<div id="wrap">
	<!-- 상단영역 -->
	<c:import url="/layout/cms/header.jsp" ></c:import>
	<!-- //상단영역 -->
	<div id="container">
		
		<!-- 컨텐츠영역 -->
		<div class="content">
			<!-- 컨텐츠 타이틀 -->
			<c:import url="/boffice/Title.do" ></c:import>
			<!-- //컨텐츠 타이틀 -->
			
			<decorator:body />
		</div>
		<!-- //컨텐츠 영역 -->

		<!-- 메뉴얼영역 -->
		<c:import url="/layout/cms/manual.jsp" ></c:import>
		<!-- //메뉴얼영역 -->
	</div>
	<!-- 하단영역 -->
	<c:import url="/layout/cms/footer.jsp" ></c:import>
	<!-- //하단영역 -->
</div>
<div id="loading"><div></div></div>
<div id="overlay"></div>
</body>
</html>