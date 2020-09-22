<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="poll" uri="http://cms.injeinc.co.kr/poll"%>

<script type="text/javascript">
//<![CDATA[

	function doPollListFReport(plIdx) {
		$("#plIdx").val(plIdx);
		$("#PollListFVo").attr("action", "<c:url value='/site/${strDomain}/ex/poll/PollListFReportEnd.do' />").submit();
	}
	
//]]>
</script>
<form:form commandName="PollListFVo" name="PollListFVo" method="post">
<form:hidden path="pageIndex" />
<form:hidden path="plIdx" />
<article class="content-row">	
	<div class="result-contents-container">
		<h1>설문내용</h1>
		<div class="result-wrap">
			<div class="result-title-contents">
				<h2 class="result-title"><c:out value="${PollListFVo.plTitle}" /></h2>
				<p class="result-days"><c:out value="${fn:substring(PollListFVo.plStartDate, 0, 10)}" /> ~ <c:out value="${fn:substring(PollListFVo.plEndDate, 0, 10)}" /></p>
			</div>
			<div class="result-contents">
			    ${cmm:textAreaToHtml(PollListFVo.plGuide)}				
			</div>
		</div>
	</div>
	
	<!-- 버튼 -->	
	
	<div class="board-foot-container">
		<div></div>
		<div class="btn-right">
			<c:if test="${PollListFVo.plResultOpenYn eq 'Y'}">
			<a href="#none" onclick="doPollListFReport('<c:out value="${PollListFVo.plIdx}" />');return false;" class="btn btn-type2">결과보기</a>
			</c:if>
			<a href="/site/<c:out value='${strDomain}' />/ex/poll/PollListE.do" class="btn btn-type2">목록</a>
		</div>		
	</div>
	<!-- //버튼 -->
</article>
</form:form>