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
 
//페이징
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
	document.bbsFVo.submit();
}

//상세페이지
function doBbsFView(bcIdx) {
	var cbIdx = "<c:out value='${bbsFVo.cbIdx}' />";
	var pageIndex = "<c:out value='${paginationInfo.currentPageNo}' />";
	document.bbsFVo.bcIdx.value = bcIdx;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx="+cbIdx+"'/>";
	document.bbsFVo.submit();
}

</script>
<body>
	<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);">
		<form:hidden path="bcIdx" />
		<form:hidden path="mode" /> 
		<form:hidden path="pageIndex" />
	 <!-- 여기부터 -->
		<h3>상담자료</h3>
			<div class="board_list">
				<div class="board_search">
					<fieldset>
					<legend>상담자료</legend>
						<ul>
							<li>
								<label for="아이디값1">제작년도</label>
								<form:select path="searchYear">
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
							<li>
								<label for="아이디값2">검색어</label>
								<form:select title="조건선택" path="tgtTypeCd" class="w100">
									<form:option value="ALL" label="전체" />
									<form:option value="SUB_CONT" label="제목" />
									<form:option value="CLOB_CONT" label="내용" />
									<form:option value="CENTER" label="담당기관"/>
								</form:select>
							</li>
						</ul>
						<div class="w280">
							<label for="txt1">검색어 입력</label>
							<input type="text" name="searchKey" id="txt1" value="<c:out value='${bbsFVo.searchKey }'/>" class="w400" title="검색어입력" placeholder="검색어를 입력해주세요">
							<input type="submit" value="검색">
						</div>
					</fieldset>
				</div>
				
				<div style="clear:both"></div>

				<span class="count_total">총 <em><fmt:formatNumber value="${contentCnt}"></fmt:formatNumber></em>건　<em><c:out value="${paginationInfo.currentPageNo}"/></em>/<c:out value="${paginationInfo.totalPageCount}"/> 페이지</span>
				
				<div class="div_table b_type3">
					<div class="div_caption">상담자료</div>
					<div class="div_table-column-group">
						<div class="div_col" style="width:8%"></div>
						<div class="div_col" style="width:44%"></div>
						<div class="div_col" style="width:15%"></div>
						<div class="div_col" style="width:13%"></div>
						<div class="div_col" style="width:10%"></div>
						<div class="div_col" style="width:10%"></div>
					</div>
					
					<div class="div_thead">
						<div class="div_tr">
							<c:forEach items="${labelList}" var="labelList">
							<div class="div_th"><c:out value="${labelList.labelName}" /></div>
							</c:forEach>
						</div>
					</div>
					
					<div class="div_tbody">
						<!-- 게시글이 없을 경우 -->
						<c:if test="${contentCnt <= 0 }">
							<div class="data_none">등록된 내용이 없습니다..</div>
						</c:if>
						
						<c:forEach items="${contentList}" var="contentList" varStatus="status">
						<div class="div_tr">
							<div class="div_td"><c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></div>
							<div class="div_td left">
								<a href="javascript:doBbsFView('<c:out value="${contentList.BC_IDX }"/>');">
									<c:out value="${contentList.SUB_CONT}" />
								</a>
							</div>
							<div class="div_td"><c:out value="${contentList.CENTER}" /></div>
							<fmt:parseDate value="${contentList.EXT2}" var="dateFmt" pattern="yyyy-MM-dd" />
							<fmt:formatDate value="${dateFmt}" var="dateFmtEXT2" pattern="yyyy.MM.dd"/>
							<div class="div_td">${dateFmtEXT2}</div>
							<div class="div_td"><c:out value="${contentList.COUNT_CONT}" /></div>
							<div class="div_td">
								<c:if test="${contentList.FILE_YN eq 'Y'}"><span class="thumb"></span></c:if>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
				
				<!-- 페이징 -->
				<div class="paging">
					<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
				</div>
			</div>
	</form:form>
		<!-- 풋터영역 -->