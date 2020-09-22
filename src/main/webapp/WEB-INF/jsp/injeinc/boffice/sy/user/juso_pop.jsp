<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ page import="egovframework.injeinc.common.util.WebUtil" %>
<% 
//request.setCharacterEncoding("UTF-8");  //한글깨지면 주석제거
String inputYn = WebUtil.checkReq(request.getParameter("inputYn")); 
String roadFullAddr = WebUtil.checkReq(request.getParameter("roadFullAddr")); 
String roadAddrPart1 = WebUtil.checkReq(request.getParameter("roadAddrPart1")); 
String roadAddrPart2 = WebUtil.checkReq(request.getParameter("roadAddrPart2")); 
String engAddr = WebUtil.checkReq(request.getParameter("engAddr")); 
String jibunAddr = WebUtil.checkReq(request.getParameter("jibunAddr")); 
String zipNo = WebUtil.checkReq(request.getParameter("zipNo")); 
String addrDetail = WebUtil.checkReq(request.getParameter("addrDetail")); 
String admCd    = WebUtil.checkReq(request.getParameter("admCd"));
String rnMgtSn = WebUtil.checkReq(request.getParameter("rnMgtSn"));
String bdMgtSn  = WebUtil.checkReq(request.getParameter("bdMgtSn"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Basic Board List</title>
<script type="text/javascript">

function init(){
	opener.setAddrValue("<%=roadAddrPart1%>", "<%=addrDetail%> <%=roadAddrPart2%>", "<%=zipNo%>");
	window.close();
}

</script>
</head>
<body onload="init();">
</body>
</html>
