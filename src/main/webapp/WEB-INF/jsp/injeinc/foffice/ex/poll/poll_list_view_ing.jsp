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
    function doPollUserFForm(plIdx) {
		$("#plIdx").val(plIdx);
		var strDomain="<c:out value='${strDomain}' />";
		$("#PollListFVo").attr("action", "<c:url value='/site/${strDomain}/ex/poll/PollUserFForm.do' />").submit();
	}
    
	function doPollListFReport(plIdx) {
		$("#plIdx").val(plIdx);
		$("#PollListFVo").attr("action", "<c:url value='/site/${strDomain}/ex/poll/PollListFReportIng.do' />").submit();
	}
	
//]]>
</script>
<form:form commandName="PollListFVo" name="PollListFVo" method="post">
<form:hidden path="pageIndex" />
<form:hidden path="plIdx" />
<article class="content-row">	
	<div class="result-contents-container">
		<!--<h1>설문내용</h1>-->
		<div class="bbsView">
			<div class="bbsViewTitle">
				<b class="result-title"><c:out value="${PollListFVo.plTitle}" /></b>
				<ul>
					<li><c:out value="${fn:substring(PollListFVo.plStartDate, 0, 10)}" /> ~ <c:out value="${fn:substring(PollListFVo.plEndDate, 0, 10)}" /></li>
				</ul>
			</div>
			<div class="bbsViewContent">
				<div class="bbsViewEditor">
			    ${cmm:textAreaToHtml(PollListFVo.plGuide)}				
				</div>
			</div>
		</div>
	</div>
	
	<!-- 버튼 -->	
	
	<div class="board-foot-container">
		<div></div>
		<div class="btnArea">
			<c:if test="${PollListFVo.totalCnt <= PollListFVo.plNumber}">
				<fmt:parseDate var="plStartDate" value="${PollListFVo.plStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate var="plStartDate" value="${plStartDate}" pattern="yyyyMMddHHmmss"/>
				<fmt:parseDate var="plEndDate" value="${PollListFVo.plEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate var="plEndDate" value="${plEndDate}" pattern="yyyyMMddHHmmss"/>	
				<c:if test="${plStartDate <= nowDate && nowDate <= plEndDate}">
					<a href="#none" onclick="doPollUserFForm('<c:out value="${PollListFVo.plIdx}" />'); return false;" class="btn_l on w100 btn_bbsAnswer">설문하기</a>						
				</c:if>		
			</c:if>
			<c:if test="${PollListFVo.plResultOpenYn eq 'Y'}">
			<a href="#none" onclick="doPollListFReport('<c:out value="${PollListFVo.plIdx}" />');return false;" class="btn_l on w100 btn_bbsAnswer">결과보기</a>
			</c:if>
			<a href="/site/<c:out value='${strDomain}'/>/ex/poll/PollListI.do" class="btn_l w100 btn_bbsList">목록</a>
		</div>		
	</div>
	<!-- //버튼 -->
</article>
</form:form>