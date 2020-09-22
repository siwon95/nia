<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.injeinc.boffice.cn.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="org.jdom2.Element" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="egovframework.injeinc.boffice.cn.common.UtilSvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/layout/demo/demo_main/head.jsp" flush="true"/>
<script>
var siteName = "...";
$(function(){
	$(".mainPopupWindow #btn_popupCloseToday").click(function(){
		setCookie(siteName, "done", 1);
		window.close();
	});
	$(".mainPopupWindow .btn_popupClose").click(function(){
		window.close();
	});
});
</script>

</head>
<body>

<div class="mainPopupWindow">
	<ul>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<li>
			<c:if test="${fn:length(result.mlLink) <= 0 }">
			<%-- <img src="<c:url value="/upload/mainLayerPopup/${result.mlFilename}" />" alt="<c:out value='${result.mlAlt}'/>" /> --%>
			<img src="/common/main/layerPopup/getImage.do?mlIdx=<c:out value="${result.mlIdx}" />" alt="<c:out value='${result.mlAlt}'/>" />
			</c:if>
			<c:if test="${fn:length(result.mlLink) > 0 }">
			<a href="<c:out value='${result.mlLink}'/>" target="_blank" title="새창열기">
				<%-- <img src="<c:url value="/upload/mainLayerPopup/${result.mlFilename}" />" alt="<c:out value='${result.mlAlt}'/>" /> --%>
				<img src="/common/main/layerPopup/getImage.do?mlIdx=<c:out value="${result.mlIdx}" />" alt="<c:out value='${result.mlAlt}'/>" />
			</a>
			</c:if>	
		</li>	
		</c:forEach>
	</ul>
	<div>
		<input type="checkbox" id="btn_popupCloseToday" value="1"> 
		<label for="btn_popupCloseToday">오늘 하루 그만보기</label>
		<a href="#" class="btn_popupClose">닫기</a>
	</div>
</div>

</body>
</html>
