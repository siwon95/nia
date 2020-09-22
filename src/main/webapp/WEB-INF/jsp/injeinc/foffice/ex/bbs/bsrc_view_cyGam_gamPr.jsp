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
$(function(){
	$(".btn_l").click(function(){
		var detailType = '${bbsFVo.detailType}';
		if (detailType=='ALL') {
			document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=1178' />";
			document.bbsFVo.submit();		
		}else{
			document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=${bbsFVo.cbIdx}' />";
			document.bbsFVo.submit();
		}
	});
});
</script>
<body>
<form:form commandName="bbsFVo" name="bbsFVo" method="post" >
<form:hidden path="searchKey" />
<form:hidden path="searchKey2" />
<form:hidden path="cdDepstep" />
<form:hidden path="pageIndex" />
<form:hidden path="tgtTypeCd" />
<form:hidden path="mode" />
<form:hidden path="parentSeq" />
<form:hidden path="answerDepth" />
<form:hidden path="answerStep" />
<form:hidden path="deptCode" />
<form:hidden path="cateTypeCd" />
<form:hidden path="fileMaxSize" />
<form:hidden path="gubPassword" />
</form:form>
<c:if test="${bbsFVo.socialYn=='Y'}">
	<jsp:include page="/layout/module/sns_share.jsp" flush="true"/>
</c:if>

<h3><c:out value="${bbsFVo.cbName }"/></h3>
	
	<div class="board_view">
			
		<div class="tit">
			<strong>
				<c:if test="${not empty detailMap.subCont}"><c:out value="${fn:replace(fn:replace(fn:replace(detailMap.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/></c:if>
			</strong>
			<span>
				<c:if test="${not empty detailMap.CENTER}"><span><c:out value="${detailMap.CENTER}" /></span></c:if> 
				<c:if test="${not empty detailMap.regDt}"><span><c:out value="${detailMap.regDt}" /></span></c:if> 
				<c:if test="${not empty detailMap.countCont}"><span>조회수 <c:out value="${detailMap.countCont}" /></span></c:if>
			</span>
		</div>
		
		<!-- 내용 -->
		<div class="view_content">
			<c:if test="${not empty detailMap.clobCont}">
					<c:out value="${cmm:outClobCont(detailMap.clobCont)}" escapeXml="false" />
			</c:if>
		</div>
	
		<c:if test="${prevNextList.PREV_IDX ne null || prevNextList.NEXT_IDX ne null}">
			<div class="divGroup cols2 pre_next_view">
				<c:if test="${prevNextList.NEXT_IDX ne null}">
					<div class="pre_view">
						<strong>이전글</strong>
						<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.NEXT_IDX }&pageIndex=${changePage.NEXT_PAGE_NUM }'/>">
							<c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.NEXT_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
						</a>
					</div>
				</c:if>
				<c:if test="${prevNextList.NEXT_IDX eq null}">
					<div class="pre_view">
						<strong>이전글</strong>
						<a>이전글이 없습니다.</a>
					</div>
				</c:if>
				
				<c:if test="${prevNextList.PREV_IDX ne null}">
					<div class="next_view">
						<strong>다음글</strong>
						<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.PREV_IDX }&pageIndex=${changePage.PREV_PAGE_NUM}'/>">
							<c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.PREV_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
						</a>
					</div>
				</c:if>
				<c:if test="${prevNextList.PREV_IDX eq null}">
					<div class="next_view">
						<strong>다음글</strong>
						<a>다음글이 없습니다.</a>
					</div>
				</c:if>
			</div>
			
		</c:if>
				
		<!-- 검색조건으로 게시글이1개일때 -->
		<c:if test="${prevNextList.PREV_IDX eq null and prevNextList.NEXT_IDX eq null}">
			<div class="divGroup cols2 pre_next_view">
					<div class="pre_view">
						<strong>이전글</strong>
						<a>이전글이 없습니다.</a>
					</div>
				
					<div class="next_view">
						<strong>다음글</strong>
						<a>다음글이 없습니다.</a>
					</div>
			</div>
		</c:if>
	
		<div class="btnArea right">
			<a href="#" class="btn_l">목록</a>
		</div>
		
	</div>
	
