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

$(document).ready(function() {
	 $.ajax({
		type: "GET",
		url: "<c:url value='/site/${strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : {"cmmCode": 'DRP_TARGET',"cmmCode2th":'DRP_TYPE'},
		dataType : "json",
		success:function(data){
			if(data.result){
    			$.each(data.cmmCode2th, function (index, item) {
    				var searchType = '${bbsFVo.searchType}';
    				if(searchType !=item.code){
    					$("#searchType").append("<option value='"+item.code+"'>"+item.codeName+"</option>");		
    				}else{
    					$("#searchType").append("<option value='"+item.code+"'selected>"+item.codeName+"</option>");
    				}    				
        		});
    			$.each(data.cmmCode, function (index, item) {
    				var searchTarget = '${bbsFVo.searchTarget}';
    				if(searchTarget !=item.code){
    					$("#searchTarget").append("<option value='"+item.code+"'>"+item.codeName+"</option>");		
    				}else{
    					$("#searchTarget").append("<option value='"+item.code+"'selected>"+item.codeName+"</option>");
    				}
        		});
    		}
		 },
        error: function(xhr,status,error){
        	alert(status);
        }
	 });
	
});

//페이징처리
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
	document.bbsFVo.submit();
}
	
</script>
<body>
	<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<form:hidden path="mode" />
		
	 <!-- 여기부터 -->
		<h3>상담안내·신청</h3>
			<div class="board_list ">
				<div class="board_search">
					<fieldset>
					<legend>상담안내·신청</legend>
						<ul>
							<li>
								<label for="아이디값1">대상</label>
								<form:select title="조건선택" path="searchTarget" class="w100">
									<form:option value="" label="전체"/>
									<c:forEach items="${CmmCode}" var="result" >
										<form:option value="${result.code}" label="${result.codeName}"/>
									</c:forEach> 
								</form:select>	
							</li>
							<li>
								<label for="아이디값1">유형</label>
								<form:select path="searchType" class="w100">
									<form:option value="" label="전체"/>
									<c:forEach items="${CmmCode}" var="result" >		
										<form:option value="${result.code}" label="${result.codeName}"/>
									</c:forEach>
								</form:select>
							</li>
							<li>
								<label for="아이디값2">검색어</label>
								<form:select title="조건선택" path="tgtTypeCd" class="w100">
									<form:option value="ALL" label="전체" />
									<form:option value="SUB_CONT" label="제목" />
									<form:option value="CLOB_CONT" label="내용" />
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
				
				<div class="div_table b_type1_2">
					<div class="div_caption">상담안내·신청</div>
					<div class="div_table-column-group">
						<div class="div_col" style="width:8%"></div>
						<div class="div_col" style="width:38%"></div>
						<div class="div_col" style="width:13%"></div>
						<div class="div_col" style="width:11%"></div>
						<div class="div_col" style="width:10%"></div>
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
							<div class="div_td left"><c:out value="${contentList.SUB_CONT}" /></div>
							<div class="div_td"><c:out value="${contentList.CENTER}" /></div>
							<div class="div_td"><c:out value="${contentList.TYPE}" /></div>
							<fmt:parseDate value="${contentList.EXT3}" var="dateFmt" pattern="yyyyMMdd" />
							<fmt:formatDate value="${dateFmt}" var="dateFmtEXT3" pattern="yyyy.MM.dd"/>
							<div class="div_td">${dateFmtEXT3}</div>
							<div class="div_td"><c:out value="${contentList.TARGET}" /></div>
							<div class="div_td">
								<c:if test="${contentList.SHORT_CUT ne ''}">
									<a href="<c:out value="${contentList.SHORT_CUT}" />" target="_blank" title="새창열림" class="baro" >바로가기</a>
								</c:if>
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
		<!-- 풋터영역 -->