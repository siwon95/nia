<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="bbs" uri="http://cms.injeinc.co.kr/bbs"%>


<script>
//페이징처리
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
    document.bbsFVo.submit();
} 
$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : {"cmmCode" : 'DRP_LAW' },
		dataType : "json",
		success:function(data){
			$.each(data.cmmCode, function (index, item) {
				if(item.code == 'DRP_50006'){
					return false;
				}
				var searchLaw = '${bbsFVo.searchLaw}';
				if(searchLaw !=item.code){
					$("#depthButton").append("<li><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1162&searchLaw="+item.code+"'>"+item.codeName+"</a></li>");
				}else{
					$("#depthButton").append("<li class='active'><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1162&searchLaw="+item.code+"'>"+item.codeName+"</a></li>");
				} 
				
	    	});
			if($("#depthButton").children('.active').length>1){
				$("#depthButton").children('.active').first().removeClass("active");
				}
		 }, 
	       error: function(xhr,status,error){ 
	       	alert(status);
	       }
	 });
}); 
</script>

<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);">
<form:hidden path="pageIndex" />
<form:hidden path="bcIdx" />
<form:hidden path="mode" />
<!-- 여기부터 -->
	<h3>관련법령</h3>
	<div class="tabContent">
		<ul class="tabBar" id="depthButton">
		</ul>

		<div class="tabPage active">
			<ul class="related">
				<c:forEach items="${contentList}" var="contentList" varStatus="status">	
					<li>
						<button type="button"><em><c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></em><strong><c:out value="${contentList.SUB_CONT}" /></strong></button>
						<div><c:out value="${cmm:outClobCont(contentList.CLOB_CONT)}" escapeXml="false"/></div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
<!-- 여기까지 -->
</form:form>
