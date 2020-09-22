<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<% 
	String libnm = request.getParameter("libnm");
	String author = request.getParameter("author");
	String pubnm = request.getParameter("pubnm");
	String pubyear = request.getParameter("pubyear");
	String regno = request.getParameter("regno");
	String signno = request.getParameter("signno");
	String state = request.getParameter("state");
	String place = request.getParameter("place");
	String bookname = request.getParameter("bookname");
	if(state.equals("소장중")){
		state = "대출가능";
	}
	Calendar cal = Calendar.getInstance ( );//오늘 날짜를 기준으루..	

	String systdate = cal.get ( cal.YEAR )+"년 "+cal.get ( cal.MONTH+1 )+"월 "+cal.get ( cal.DATE )+"일";
%>
<head>
<meta http-equiv='Content-Type' content='text/html;charset=EUC-KR' />
<title>도서찾기영수증 인쇄</title>
</head>

<body>

<table border='0' cellspacing='0' cellpadding='0' style='width:260px;'>
<tr><td><font style='font-size:8pt'><b><%=systdate%> <%=libnm%></b></font></td></tr>
<tr><td><font style='font-size:15pt'>&nbsp;</font></td></tr>
<tr><td><font style='font-size:15pt'><b><%=bookname%></b></font></td></tr>
<tr><td><font style='font-size:15pt'>&nbsp;</font></td></tr>
<tr><td><font style='font-size:9pt'>-------------------------------------------</font></td></tr>
<tr><td><font style='font-size:9pt'>저자 : <%=author%> 지음</font></td></tr>
<tr><td><font style='font-size:9pt'>발행처 : <%=pubnm%></font></td></tr>
<tr><td><font style='font-size:9pt'>발행년도 : <%=pubyear%></font></td></tr>
<tr><td><font style='font-size:9pt'>등록번호 : <%=regno%></font></td></tr>
<tr><td><font style='font-size:9pt'>-------------------------------------------</font></td></tr>
<tr><td><font style='font-size:9pt'>청구기호 : <%=signno%></font></td></tr>
<tr><td><font style='font-size:9pt'>소장처 : <%=libnm%>/<%=place%></font></td></tr>
<tr><td><font style='font-size:9pt'>도서상태 : <%=state%></font></td></tr>
<tr><td><font style='font-size:9pt'>-------------------------------------------</font></td></tr>

</table>
</body>
</html>
