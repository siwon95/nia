<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<jsp:include page="/layout/demo/demo_sub/head.jsp" flush="true"/>
<decorator:head />
<jsp:include page="/layout/demo/demo_sub/top.jsp" flush="true"/>
<decorator:body />
<jsp:include page="/layout/demo/demo_sub/bottom.jsp" flush="true"/>