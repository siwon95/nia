<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="egovframework.injeinc.foffice.rss.controller.RssCtr"%>
<%@page import="egovframework.injeinc.common.util.WebUtil"%>
<%@page import="egovframework.injeinc.foffice.rss.vo.RssVo"%>
<%@page import="java.util.List"%>
    
<%
String pLimit = WebUtil.checkReq(request.getParameter("pLimit")); 
String pContent = WebUtil.checkReq(request.getParameter("pContent")); 
String pBoard = "서초구청 공지사항";
String pSite = "http://www.seocho.go.kr";
String pCode = "650";
if(pLimit != null){
	if(pLimit.equals("")) {
		pLimit = "20";
	}
}
if(pContent != null){
	if(pContent.equals("")) {
		pContent="N";
	}
}

List list = null;
list =  RssCtr.noticeList(pLimit);
%>
<rss version="2.0">
<channel>
  <title><![CDATA[<%=pBoard%>]]></title>
  <link><%=pSite%></link>
  <description><![CDATA[<%=pBoard%>]]></description>
 <departCode><![CDATA[<%=pCode%>]]></departCode>
<%
	RssVo rssVo = new RssVo();
	for(int i=0; i<list.size(); i++){
		rssVo = (RssVo)list.get(i);
%>	<item>
		<title><![CDATA[<%=WebUtil.checkReq(rssVo.getTitle())%>]]></title>
		<link><![CDATA[http://www.seocho.go.kr/site/seocho/ex/bbs/View.do?cbIdx=<%=rssVo.getCbIdx()%>&bcIdx=<%=rssVo.getBcIdx()%>]]></link>
		<pubDate><![CDATA[<%=WebUtil.checkReq(rssVo.getRegDt())%>]]></pubDate>
		<id><![CDATA[<%=rssVo.getCbIdx()%>]]></id>
		<department><![CDATA[<%=WebUtil.checkReq(rssVo.getDept())%>]]></department>
		<description><![CDATA[<%if("Y".equals(pContent)) {%><%=WebUtil.checkReq(rssVo.getContent())%><%}%>]]></description>
	</item>
<%}%>
</channel>
</rss>
