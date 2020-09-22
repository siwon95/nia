<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.injeinc.common.cms.EzDecoratorMapper" %>
<%@ page import="egovframework.injeinc.boffice.cn.menu.vo.MenuVo" %>
<%@ page import="egovframework.injeinc.boffice.cn.common.Util" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%-- <%@ page import="net.sf.ehcache.Cache" %>
<%@ page import="net.sf.ehcache.CacheManager" %>
<%@ page import="net.sf.ehcache.Element" %> --%>
<%@ page import="egovframework.injeinc.boffice.cn.common.Util" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%
MenuVo menuVo = EzDecoratorMapper.getMenuDecorator(request, "");
//String menuLayout = (menuVo.getLayoutUrl()+"").replaceAll("null","");

String menuLayout = menuVo.getLayoutUrl() != null ? (menuVo.getLayoutUrl()+"").replaceAll("null","") : "/WEB-INF/jsp/injeinc/site/DRP0000/drp_sub.jsp";



/*
CacheManager cacheManager = null;
cacheManager = CacheManager.create();
*/

session.setAttribute("ssSortOrder",menuVo.getSortOrder());
session.setAttribute("ssMenuUrl",menuVo.getUserMenuUrl());
session.setAttribute("ssMenuPath",menuVo.getMenuPath());
session.setAttribute("ssSiteNm",menuVo.getSiteNm());
session.setAttribute("ssMenuNm",menuVo.getMenuNm());
session.setAttribute("ssMenuTitle",(menuVo.getTitle()+"").replaceAll("null",""));
session.setAttribute("ssMenuCode", menuVo.getMenuCode());
session.setAttribute("ssSiteCode", menuVo.getSiteCd());
session.setAttribute("ssSocialShowYn", menuVo.getSocialShowYn());
session.setAttribute("ssMenuType", menuVo.getMenuType());
session.setAttribute("ssMetaTagConent", menuVo.getMetaTagContent());

String reqUri = request.getRequestURI()+"";
String siteCd = Util.getSiteCode(request);
String menuPath = menuVo.getMenuPath();

if(menuVo.getSortOrder()!=null){
	request.setAttribute("ssSortOrder",menuVo.getSortOrder());
}
if(menuVo.getUserMenuUrl()!=null){
	request.setAttribute("ssMenuUrl",menuVo.getUserMenuUrl());
}
if(menuVo.getMenuPath()!=null){
	request.setAttribute("ssMenuPath",menuVo.getMenuPath());
}
if(menuVo.getSiteNm()!=null){
	request.setAttribute("ssSiteNm",menuVo.getSiteNm());
}
if(menuVo.getMenuNm()!=null){
	request.setAttribute("ssMenuNm",menuVo.getMenuNm());
}
if(menuVo.getTitle()!=null){
	request.setAttribute("ssMenuTitle",(menuVo.getTitle()+"").replaceAll("null",""));
}
if(menuVo.getMenuCode()!=null){
	request.setAttribute("ssMenuCode", menuVo.getMenuCode());
}
if(menuVo.getSiteCd()!=null){
	request.setAttribute("ssSiteCode", menuVo.getSiteCd());
}
if(menuVo.getHeadContent()!=null){
	request.setAttribute("ssHeadContent", menuVo.getHeadContent().replaceAll("null","").replaceAll("\n","<br/>"));
}
if(menuVo.getDeptInfo()!=null){
	request.setAttribute("ssDeptInfo", menuVo.getDeptInfo().replaceAll("null",""));
}
if(menuVo.getSatisfyShowYn()!=null){
	request.setAttribute("ssSatisfyShowYn", menuVo.getSatisfyShowYn().replaceAll("null",""));
}
if(menuVo.getSocialShowYn()!=null){
	request.setAttribute("ssSocialShowYn", menuVo.getSocialShowYn().replaceAll("null",""));
}
if(menuVo.getChargerShowYn()!=null){
	request.setAttribute("ssChargerShowYn", menuVo.getChargerShowYn().replaceAll("null",""));
}
if(menuVo.getMenuType()!=null){
	request.setAttribute("ssMenuType", menuVo.getMenuType().replaceAll("null",""));
}
if(menuVo.getMetaTagContent()!=null){
	request.setAttribute("ssMetaTagConent", menuVo.getMetaTagContent().replaceAll("null",""));
}
%>


<c:set var="menuLayout" value="<%=menuLayout%>"/>
<c:choose>
<c:when test="${fn:trim(menuLayout) ne ''}">
    <jsp:include page="${menuLayout}" flush="true"/>
</c:when>
<c:otherwise>
<decorator:head />
<decorator:body />
</c:otherwise>
</c:choose>