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
	<!-- 
	subCont    : 제목
	regDt 	   : 등록일자 
	ext1 	   : 담당기관
	countCont  : 조회수
	clobCont   : 글내용 
	-->
<h3>치유방법</h3>
	<div class="board_view">
		<div class="tit">
			<strong><c:out value="${fn:replace(fn:replace(fn:replace(detailMap.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/></strong>
			<span>
				<span><c:if test="${not empty detailMap.CENTER}"><c:out value="${detailMap.CENTER}" /></c:if></span>
				<span><c:if test="${not empty detailMap.regDt}"><c:out value="${detailMap.regDt}" /></c:if></span>
				<span><c:if test="${not empty detailMap.countCont}"><c:out value="${detailMap.countCont}" /></c:if></span>
			</span>
		</div>
		
		<div class="view_content">
			<c:if test="${not empty detailMap.clobCont}"><c:out value="${cmm:outClobCont(detailMap.clobCont)}" escapeXml="false" /></c:if>
		</div>
		
		<!-- 첨부파일관련 내용  -->
		<c:if test="${fileList[0].orignlFileNm ne null}">
			<div class="thubfile">
				<strong>첨부파일</strong>
				<div>
					<c:forEach items="${fileList}" var="fileList">		
						<c:if test="${fileList ne null }">
					 		<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" target="_blank" title="파일다운로드">
								<i class="<c:out value="${fileList.fileExtsn}"/>">
									<span class="soundOnly"><c:out value="${fileList.fileExtsn}"/>파일</span>
									<span><c:out value="${fileList.orignlFileNm }" /> </span>	
									<%-- [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />] --%>								
								</i>
							</a>
						</c:if>	
					</c:forEach>
				</div>
			</div>
		</c:if>
		
			<!-- 이전글, 다음글, 목록 버튼 -->
		<c:if test="${prevNextList.PREV_IDX ne null || prevNextList.NEXT_IDX ne null}">
			<div class="divGroup cols2 pre_next_view">
				<div class="pre_view">
					<c:choose>
					<c:when test="${prevNextList.NEXT_IDX ne null}">
						<strong>이전글</strong>
						<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.NEXT_IDX }&tgtTypeCd=${bbsFVo.tgtTypeCd }&searchKey=${bbsFVo.searchKey}&pageIndex=${changePage.NEXT_PAGE_NUM}'/>">
			                <c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.NEXT_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
			            </a>
					</c:when>
					<c:otherwise>
						<strong>이전글</strong>
						<a href="">이전글이 없습니다.</a>
					</c:otherwise>
					</c:choose>
				</div>
				<div class="next_view">
					<c:choose>
						<c:when test="${prevNextList.PREV_IDX ne null}">
							<strong>다음글</strong>
							<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.PREV_IDX }&tgtTypeCd=${bbsFVo.tgtTypeCd }&searchKey=${bbsFVo.searchKey}&pageIndex=${changePage.PREV_PAGE_NUM}'/>">
			                   <c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.PREV_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
			                </a>
						</c:when>
						<c:otherwise>
							<strong>다음글</strong>
							<a href="">다음글이 없습니다.</a>
						</c:otherwise>
					</c:choose>
				</div>
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
	</div>
	<div class="btnArea right">
		<a href="#" class="btn_l">목록</a>
	</div>
