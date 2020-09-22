<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.injeinc.boffice.cn.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="org.jdom2.Element" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="egovframework.injeinc.boffice.cn.common.UtilSvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="menuPath" value="${sessionScope['ssMenuPath']}"/>
<c:set var="arrMenuPath" value="${fn:split(menuPath,'>')}"/>
<ul class="subLocation">
	<c:forEach items="${arrMenuPath}" var="menuPath" varStatus="g">
		<c:if test="${g.first}">
			<li class="home"><a href="/"><img src="/images/demo/layout/icon_pathHome.png" alt="홈"></a></li>
		</c:if>
		<c:if test="${!g.first}">
			<li><c:out value="${menuPath}"/></li>
		</c:if>
	</c:forEach>
</ul>