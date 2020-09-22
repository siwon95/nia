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
<%-- 컨텐츠 우측 include file --%>
<!-- webapp/layout/DRP0000/drp_sub/sub_right.jsp -->