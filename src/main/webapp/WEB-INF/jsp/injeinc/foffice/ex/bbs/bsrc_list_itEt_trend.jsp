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




//권한 체크
function doBoardAuthorChk(){
	return true;
}
//페이징처리
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
    document.bbsFVo.submit();
}

function onChange(){
	
	var searchYear = document.bbsFVo;
	var cbIdx = "<c:out value='${bbsFVo.cbIdx}' />";
	document.bbsFVo.pageIndex.value = "1";
	searchYear.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx='/>"+cbIdx;
	$(searchYear).submit();
}


</script>
<body>

<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);">
<form:hidden path="pageIndex" />
<form:hidden path="bcIdx" />
<form:hidden path="mode" />
	<h3><c:out value="${bbsFVo.cbName }" /></h3>
	
	<div class="board_list">
		<div class="board_search">
			<c:if test="${bbsFVo.categoryUseYn eq 'Y'}">
				<form>
					<fieldset>
						<legend>인터넷 윤리 동향 게시물 검색</legend>
						<ul>
							<li>
							<label for="아이디값1" class="soundOnly">제작년도</label>
								 <form:select path="searchYear" onchange="onChange(this);" class="mr0">
									<option value="">전체</option>
								    <c:set var="today" value="<%=new java.util.Date()%>" />
								    <fmt:formatDate value="${today}" pattern="yyyy" var="start"/> 
								    <c:forEach begin="0" end="10" var="idx" step="1">
								    
								    	<option value="<c:out value="${start - idx}" />"
								    		<c:if test="${(start - idx) == bbsFVo.searchYear}"> selected="selected"</c:if>><c:out value="${start - idx}" />
								    	</option>
								    </c:forEach>
								</form:select>
							</li>
						</ul>
					</fieldset>
				</form>	
			</c:if>
		</div>
	
		<div style="clear:both"></div>
		
		<span class="count_total">총 <em><fmt:formatNumber value="${contentCnt}"></fmt:formatNumber></em>건　<em><c:out value="${paginationInfo.currentPageNo}"/></em>/<c:out value="${paginationInfo.totalPageCount}"/> 페이지</span>
		<c:if test="${contentCnt <= 0 }">
			<ul class="trend_ethics">		
				<li>
					<div class="data_none">등록된 내용이 없습니다.</div>
				</li>
			</ul>
		</c:if>
		<c:forEach items="${contentList}" var="contentList" varStatus="status">
			<ul class="trend_ethics">
				
				<li>
					<div class="img">
						<img src="/common/files/ThumbCreateDown.do?opath=<c:out value="${contentList.LIST_IMG }"/>&twidth=276&theight=300&mode=web&fileExtsn=<c:out value="${contentList.FILE_EXTSN }"/>" alt="">
					</div>
					<div class="con">
						<strong>
							<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${contentList.CB_IDX }&bcIdx=${contentList.BC_IDX }&tgtTypeCd=${bbsFVo.tgtTypeCd }
							  						&searchKey=${bbsFVo.searchKey}&searchYear=${bbsFVo.searchYear}&pageIndex=${paginationInfo.currentPageNo }' />" class="btn_bbsDetail">
								<c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
							</a>
						</strong>
						<em>
							<c:out value="${contentList.REG_DT}" />
						</em>
						<div>
							<c:out value="${contentList.CLOB_CONT_SEARCH}" escapeXml="false" />
						</div>
						<c:if test="${contentList.STRE_FILE_NM ne null}">
							<a href="/common/board/Download.do?bcIdx=<c:out value="${contentList.BC_IDX}"/>&amp;cbIdx=<c:out value="${contentList.CB_IDX}"/>&amp;streFileNm=<c:out value="${contentList.STRE_FILE_NM}"/>" target="_blank" title="파일다운로드" class="pdf_down com_large"><span></span>첨부파일 다운로드</a>
						</c:if>
					</div>
				</li>
			</ul>
		</c:forEach>
	</div>	
		
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
	</div>
	
</form:form>
