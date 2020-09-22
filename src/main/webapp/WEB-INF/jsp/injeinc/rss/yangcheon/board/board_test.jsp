<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<head>

<script type="text/javascript">

	function is_ie() {
		if (navigator.userAgent.toLowerCase().indexOf("chrome") != -1)
			return false;
		if (navigator.userAgent.toLowerCase().indexOf("msie") != -1)
			return true;
		if (navigator.userAgent.toLowerCase().indexOf("windows nt") != -1)
			return true;
		return false;
	}

	function copy_to_clipboard(str) {
		if (is_ie()) {
			window.clipboardData.setData("Text", str);
			alert("복사되었습니다.");
			return;
		}
		prompt("Ctrl+C를 눌러 복사하세요.", str);
	}
</script>

</head>

<body>

		<c:forEach var="rssUrlCheck" items="${rssUrlCheck}">
			<ul>
				<li>				
					<c:out value="${rssUrlCheck.cbName}"></c:out> : <c:out value="${rssUrlCheck.rssUrl}"></c:out>	
						
						<a href="#" onclick="copy_to_clipboard('<c:out value="${rssUrlCheck.rssUrl}"></c:out>');">
							<img src="img/data/event/event_0321/img01_btn.gif" width="30" height="30" border="0" />복사이미지
						</a>				 	
				</li>
			</ul>				
		</c:forEach>
		
</body>
</html>