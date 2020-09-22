<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="aside">
	<div class="asideTitle">
		<h5>메뉴얼</h5>
	</div>
	<div class="asideContent">
		<c:out value="${naviTitle.mmHelp }" escapeXml="false"/>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>