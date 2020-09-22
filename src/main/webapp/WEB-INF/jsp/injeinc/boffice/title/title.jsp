<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 컨텐츠 타이틀 영역
- 파일명 : title.jsp
- 작성일 : 2018-01-19
- 작성자 : 김근 차장
-------------------------------------------------------------- --%>
<div class="sectionTitle">
	<h2><c:out value="${naviTitle.mmName }"/></h2>
	<div class="titleNav">
		<ul></ul>
	</div>
</div>