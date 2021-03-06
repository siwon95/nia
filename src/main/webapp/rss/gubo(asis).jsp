<?xml version="1.0" encoding="euc-kr" ?>
<%@ page contentType="text/xml; charset=euc-kr" %>
<%@ include file="/common/commonStart.jsp" %>
<%@page import="pkg.bizclass.jsp.rss.MbRssCP"%>
<%

String pLimit = WebUtil.nvl(paramSet.inGet("pLimit")); 
String pContent = WebUtil.nvl(paramSet.inGet("pContent")); 
String pBoard = "서초구청 서초구보";
String pSite = "http://www.seocho.go.kr";
String pCode = "650";


if(pLimit.equals("")) {
	paramSet.inSet("pLimit","20");
}
if(pContent.equals("")) {
	pContent="N";
}

paramSet.inSet("ms_idx","S00000351");
MbRssCP mbRssCP = new MbRssCP();
paramSet=mbRssCP.getContentRSSList(paramSet);
Iterator iter=paramSet.getList("ResultRSSList").iterator();

%>
<rss version="2.0">
<channel>
	<title><![CDATA[<%=pBoard%>]]></title>
	<link><%=pSite%></link>
	<description><![CDATA[<%=pBoard%>]]></description>
	<departCode><![CDATA[<%=pCode%>]]></departCode>
<%
	BeanMb_rss beanMb_rss = new BeanMb_rss();
	while(iter.hasNext()) {
		beanMb_rss = (BeanMb_rss)iter.next();
%>	
	<item>
		<title><![CDATA[<%=beanMb_rss.getMd_title()%>]]></title>
		<link><![CDATA[http://www.seocho.go.kr/site/sd/page.jsp?code=sdf040000000&md=<%=beanMb_rss.getMd_idx().substring(3,9)%>&mode=view]]></link>
		<pubDate><![CDATA[<%=beanMb_rss.getMd_date()%>]]></pubDate>
		<id><![CDATA[<%=pCode+beanMb_rss.getMd_idx().substring(3,9)%>]]></id>
		<department><![CDATA[<%=beanMb_rss.getMd_writer()%>]]></department>
		<description>
			<![CDATA[<%if("Y".equals(pContent)) {%>
				<%=beanMb_rss.getMd_content_search()%>
			<%}%>]]>
		</description>
	</item>
<%}%>
</channel>
</rss>