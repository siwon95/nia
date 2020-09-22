<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.injeinc.boffice.cn.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="org.jdom2.Element" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="egovframework.injeinc.boffice.cn.common.UtilSvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="<c:url value='/js/injeinc/snsShare.js' />"></script>
<a href="#story" title="새창열림" class="btn_snsShare">카카오스토리</a>
<a href="#band" title="새창열림" class="btn_snsShare">네이버밴드</a>
<a href="#blog" title="새창열림" class="btn_snsShare">네이버포스트</a>
<a href="#facebook" title="새창열림" class="btn_snsShare">페이스북</a>
<a href="#twitter" title="새창열림" class="btn_snsShare">트위터</a>