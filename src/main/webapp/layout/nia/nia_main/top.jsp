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
<div id="wrap">
	<!-- //header -->
	<div id="header" class="main">
		<div class="inner">
			<h1><a href="/site/nia/main.do"><img src="/images/nia/common/logo.jpg" alt="디지털배움터"></a></h1>
			<!-- //gnb -->
			<div id="gnb">
				<ul class="depth1">
					<%
					String strSortOrder = "", siteCd = "", sortOrder = "", showYn = "", linkType = "", activeClass = "";
					strSortOrder = request.getAttribute("ssSortOrder").toString();
					siteCd = Util.getSiteCode(request).toString();
					ServletContext conext = session.getServletContext();
					WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
					UtilSvc utilSvc = (UtilSvc)wContext.getBean("UtilSvc");
					List<Element> menuList = utilSvc.makeMenuXml(siteCd);
					for(Element element:menuList){
						List<Element> menuList1 = element.getChildren("child-menu").get(0).getChildren();  
						for(Element element1:menuList1){
							sortOrder = (element1.getChild("sort-order").getText().toString()).trim();
							showYn = (element1.getChild("show-yn").getText().toString()).trim();
							if(showYn.equals("Y")){
								activeClass = "";
								if(strSortOrder.substring(0,3).equals(sortOrder.substring(0,3))){
									activeClass = " class=\"active\"";
								}
					%>
						<li><a href="<%=element1.getChild("user-menu-url").getText()%>"><%=element1.getChild("menu-nm").getText()%></a></li>
					<% } } } %>
				</ul>
			</div>
		</div>
	</div>
	<!-- //header -->