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

//목록
function doList(){
	var detailType = '${bbsFVo.detailType}';
	if (detailType=='ALL') {
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=1178' />";
		document.bbsFVo.submit();		
	}else{
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=${bbsFVo.cbIdx}' />";
		document.bbsFVo.submit();
	}
}

//이전글 다음글
function doPreNextView(bcIdx,pageIndex) {
	var cbIdx = "<c:out value='${bbsFVo.cbIdx}'/>";
	
	document.bbsFVo.bcIdx.value = bcIdx;
	document.bbsFVo.pageIndex.value = pageIndex;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx='/>"+cbIdx;
	document.bbsFVo.submit(); 
}

</script>
<body>
	<form:form commandName="bbsFVo" name="bbsFVo" method="post" >
		<form:hidden path="searchKey" />
		<form:hidden path="pageIndex" />
		<form:hidden path="tgtTypeCd" />
		<form:hidden path="searchTarget" />
		<form:hidden path="searchYear" />
		<form:hidden path="searchContent" />
		<form:hidden path="bcIdx" />
	</form:form>

	<h3><c:out value="${bbsFVo.cbName }"/></h3>
	<div class="board_view">
		<div class="tit">
			<!-- 제목 -->
			<strong>
				<c:if test="${not empty detailMap.subCont}">
					<c:out value="${fn:replace(fn:replace(fn:replace(detailMap.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
				</c:if>
			</strong>
			<span>
				<!-- 대상 -->
				<c:if test="${not empty detailMap.TARGET}">
					<span><c:out value="${detailMap.TARGET}" /></span>
				</c:if>
				
				<!-- 제작일 -->
				<c:if test="${not empty detailMap.regDt}">
					<span><c:out value="${detailMap.regDt}" /></span>
				</c:if>
				
				<!-- 조회수 -->
				<c:if test="${not empty detailMap.countCont}">
					<span>조회수  <c:out value="${detailMap.countCont}" /></span>
				</c:if>
			</span>
		</div>
		
		<!-- 내용 -->
		<c:if test="${not empty detailMap.clobCont || not empty detailMap.clobCont2}">
			<c:if test="${not empty detailMap.clobCont}">
				<div class="view_content">
					<c:out value="${cmm:outClobCont(detailMap.clobCont)}" escapeXml="false" />
				</div>
			</c:if>
		</c:if>
		
		<!-- 첨부파일 -->
		<c:forEach items="${fileList}" var="fileList">		
			<c:if test="${fileList ne null }">
				<div class="thubfile">
					<strong>첨부파일</strong>
					<div>
						<span>
							<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" target="_blank" title="파일다운로드">
								<c:out value="${fileList.orignlFileNm }" />
							</a>
						</span>
					</div>
				</div>
			</c:if>
		</c:forEach>
	
		<!-- 이전글/다음글 -->
		<c:if test="${prevNextList.PREV_IDX ne null || prevNextList.NEXT_IDX ne null}">
			<div class="divGroup cols2 pre_next_view">
				<c:if test="${prevNextList.NEXT_IDX ne null}">
					<div class="pre_view">
						<strong>이전글</strong>
						<a href="javascript:doPreNextView('<c:out value="${prevNextList.NEXT_IDX }"/>','<c:out value="${changePage.NEXT_PAGE_NUM}"/>');">
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
						<a href="javascript:doPreNextView('<c:out value="${prevNextList.PREV_IDX }"/>','<c:out value="${changePage.PREV_PAGE_NUM}"/>');">
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
					
	</div>
	<div class="btnArea right">
		<a href="#" class="btn_l" onclick="doList()">목록</a>
	</div>
		<!-- 여기까지 -->
		
	</div>
</div>

		<!-- 풋터영역 -->