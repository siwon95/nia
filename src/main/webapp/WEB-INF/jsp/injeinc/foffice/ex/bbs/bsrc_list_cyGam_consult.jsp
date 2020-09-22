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

//공동코드 ajax 
$(document).ready(function() {
	//대상
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : { "cmmCode" : 'DRP_TARGET' },
		dataType : "json",
		success:function(data){
			$.each(data.cmmCode, function (index, item) {
				var searchTarget = '${bbsFVo.searchTarget}';
				if (searchTarget != item.code) {
					$("#searchTarget").append("<option value='"+item.code+"'>"+item.codeName+"</option>");
						} else {
							$("#searchTarget").append("<option value='"+item.code+"'selected>"+item.codeName+"</option>");
						} 
    		});
		 },
		 
        error: function(xhr,status,error){
        	alert(status);
        }
	 });
	
	//유형
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : { "cmmCode" : 'DRP_TYPE' },
		dataType : "json",
		success:function(data){
			$.each(data.cmmCode, function (index, item) {
				var searchType = '${bbsFVo.searchType}';
				if (searchType != item.code) {
					$("#searchType").append("<option value='"+item.code+"'>"+item.codeName+"</option>");
						} else {
							$("#searchType").append("<option value='"+item.code+"'selected>"+item.codeName+"</option>");
						} 
    		});
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

	<h3>상담안내·신청</h3>
	<!-- 검색 bar -->
	<div class="board_list">
		<div class="board_search">
			<fieldset>
				<legend>상담안내·신청 게시물 검색</legend>
				<ul>
					<li>
						<label for="">대상</label>
						<form:select path="searchTarget">
							<form:option value="" label="전체"/>
						</form:select>
					</li>
					<li>				
						<label for="">유형</label>
						<form:select path="searchType">
							<form:option value="" label="전체"/>
						</form:select> 
					</li>
					<li>
						<label for="">검색어</label>	
					 	<form:select title="조건선택" path="tgtTypeCd" class="mr0">
					 		<form:option value="ALL" label="전체" />
					 		<form:option value="SUB_CONT" label="제목" />
							<form:option value="CENTER" label="담당기관" />
					 	</form:select>  
				 	</li>
				</ul>
			 	<div class="w280">
			 		<label for="txt1">검색어 입력</label>
					<input type="text" name="searchKey" id="txt1" class="w400" value="<c:out value='${bbsFVo.searchKey }' />" title="검색어입력" placeholder="검색어를 입력해주세요">
					<input type="submit" value="검색" >
				</div>
			</fieldset>
		</div>
		<div style="clear:both"></div>
		
		<!-- 페이지번호 및 전체 건수 조회 -->
		<span class="count_total">
			총 <em><fmt:formatNumber value="${contentCnt}" /></em>건,  
			<em><c:out value="${paginationInfo.currentPageNo}"/></em> / <c:out value="${paginationInfo.totalPageCount}"/>페이지
		</span>
		
		<div class="div_table">
			<div class="div_caption">상담안내·신청 게시물 목록</div>
				<div class="div_table-column-group">
					<div class="div_col" style="width:8%"></div>
					<div class="div_col" style="width:15%"></div>
					<div class="div_col" style="width:34%"></div>
					<div class="div_col" style="width:10%"></div>
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
					<!-- 조회내용이 없는경우 표시 -->
					<c:if test="${contentCnt <= 0 }">
						<div class="data_none">등록된 내용이 없습니다.</div>
					</c:if>
					<c:forEach items="${contentList}" var="contentList" varStatus="status">
						<div class="div_tr">
							<div class="div_td"><c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></div>
							<div class="div_td"><c:out value="${contentList.CENTER}" /></div>
							<div class="div_td left"><c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/></div>
							<div class="div_td"><c:out value="${contentList.TYPE}" /></div>
							<c:if test="${(contentList.TO_DATE != NULL && contentList.TO_DATE != '') || (contentList.FORM_DATE != NULL && contentList.FORM_DATE != '')}">
								<div class="div_td"><c:out value="${contentList.TO_DATE}" />~<c:out value="${contentList.FORM_DATE}" /></div>
							</c:if>
							<c:if test="${(contentList.TO_DATE == NULL || contentList.TO_DATE == '')&&(contentList.FORM_DATE != NULL || contentList.FORM_DATE != '')}">
								<div class="div_td"><c:out value="기관별상이" /></div>
							</c:if>
							<div class="div_td"><c:out value="${contentList.TARGET}" /></div>
							<div class="div_td">
								<a class="baro" title="새창" target="blank" href=<c:out value="${contentList.EXT4}"/>>바로가기</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="paging">
				<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
			</div>
		</div>
</form:form>
