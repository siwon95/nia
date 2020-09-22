<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<head>
<script type="text/javascript">
//<![CDATA[
	function doPollListFReport() {
		$("#PollListFVo").attr("action", "<c:url value='/site/${strDomain}/ex/poll/PollListFReport.do' />").submit();
	}
	
//]]>
</script>
</head>
<body>
<form:form commandName="PollListFVo" name="PollListFVo" method="post">
<form:hidden path="plIdx" />

<div class="poll-complete">
	<p><em class="blue">설문</em>에 참여해 주셔서 감사합니다. </p>
	<p>귀중한 설문 자료는 더욱 발전하는 관악구청에 바탕이 되도록 하겠습니다. <br>감사합니다. </p>
</div>	
<div class="btns">
	<c:if test="${PollListFVo.plResultOpenYn eq 'Y'}">
	<a href="<c:url value='/site/${strDomain}/ex/poll/PollListFReport.do' />?plIdx=<c:out value='${PollListFVo.plIdx}' />" class="type2" onclick="doPollListFReport();return false;">설문결과 보기</a>
	</c:if>
	<a href="<c:url value='/site/${strDomain}/ex/poll/PollListF.do' />" class="type1">목록</a>
</div>

</form:form>
</body>