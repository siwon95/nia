<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="egovframework.injeinc.foffice.rss.controller.RssCtr"%>
<%@page import="egovframework.injeinc.common.util.WebUtil"%>
<%@page import="egovframework.injeinc.boffice.ex.board.vo.BbsContentVo"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.util.List"%>
    
<%
String pLimit = WebUtil.checkReq(request.getParameter("pLimit")); 
String pContent = WebUtil.checkReq(request.getParameter("pContent"));
String pBoard = "양천구청";
String pSite = "http://localhost:8080/";
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



List<BbsContentVo> list = (List<BbsContentVo>)request.getAttribute("rssBoardList");
String strDomain = (String)request.getAttribute("strDomain");
BbsContentVo bbsContentVo = new BbsContentVo(); 
for(int i=0; i<list.size(); i++){
bbsContentVo = (BbsContentVo)list.get(i);
}

%>


<rss version="2.0">
<channel>
<%%>
  <title><![CDATA[<%=pBoard%>]]> <![CDATA[<%=WebUtil.checkReq(bbsContentVo.getCbName())%>]]></title>
  <link><%=pSite%></link>
  <description><![CDATA[<%=pBoard%>]]></description>
  <departCode><![CDATA[<%=pCode%>]]></departCode>
<%
	for(int i=0; i<list.size(); i++){
		bbsContentVo = (BbsContentVo)list.get(i);
%>	
	<item>
		<title><![CDATA[<%=WebUtil.checkReq(bbsContentVo.getSubCont())%>]]></title>
		<link><![CDATA[http://cms.yangcheon.go.kr/site/<%=strDomain%>/ex/bbs/View.do?cbIdx=<%=bbsContentVo.getCbIdx()%>&bcIdx=<%=bbsContentVo.getBcIdx()%>]]></link>
		<pubDate><![CDATA[<%=WebUtil.checkReq(bbsContentVo.getRegDt())%>]]></pubDate>
		<id><![CDATA[<%=bbsContentVo.getCbIdx()%>]]></id>
		<%-- <department><![CDATA[<%=WebUtil.checkReq(bbsContentVo.getDept())%>]]></department>
		<description><![CDATA[<%if("Y".equals(pContent)) {%><%=WebUtil.checkReq(bbsContentVo.getContent())%><%}%>]]></description> --%>
	</item>
<%}%>
</channel>
</rss>
