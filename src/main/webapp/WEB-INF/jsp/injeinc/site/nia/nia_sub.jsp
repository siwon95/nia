<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<jsp:include page="/layout/nia/nia_sub/head.jsp" flush="true"/>
<decorator:head />
<jsp:include page="/layout/nia/nia_sub/top.jsp" flush="true"/>
<%-- sub template로 사용시 left메뉴 사용가능 --%>
<%-- <jsp:include page="/layout/nia/nia_sub/sub_left.jsp" flush="true"/> --%>
<decorator:body />
<%-- sub template로 사용시 right메뉴 사용가능 --%>
<%-- <jsp:include page="/layout/nia/nia_sub/sub_right.jsp" flush="true"/> --%>
<jsp:include page="/layout/nia/nia_sub/bottom.jsp" flush="true"/>