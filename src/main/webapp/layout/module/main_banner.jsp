<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.injeinc.boffice.cn.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="org.jdom2.Element" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="egovframework.injeinc.boffice.cn.common.UtilSvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="mainBanner">
	<ul>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<li>
			<c:if test="${fn:length(result.hlLink) <= 0 }">
			<%-- <img src="<c:url value="/upload/mainLayerPopup/${result.hlFilename}" />" alt="<c:out value='${result.hlAlt}'/>" /> --%>
			<img src="/common/main/layerPopup/getImage.do?hlIdx=<c:out value="${result.hlIdx}" />" alt="<c:out value='${result.hlAlt}'/>" />
			</c:if>
			<c:if test="${fn:length(result.hlLink) > 0 }">
			<a href="<c:out value='${result.hlLink}'/>" target="_blank" title="새창열기">
				<%-- <img src="<c:url value="/upload/mainLayerPopup/${result.hlFilename}" />" alt="<c:out value='${result.hlAlt}'/>" /> --%>
				<img src="/common/main/layerPopup/getImage.do?hlIdx=<c:out value="${result.hlIdx}" />" alt="<c:out value='${result.hlAlt}'/>" />
			</a>
			</c:if>	
		</li>	
		</c:forEach>
	</ul>
</div>