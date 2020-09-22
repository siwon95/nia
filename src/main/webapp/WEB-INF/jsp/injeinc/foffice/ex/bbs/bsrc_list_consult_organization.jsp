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
 
//공동코드 ajax 
$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/${strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : {"cmmCode": 'DRP_AREA',"cmmCode2th":'DRP_CENTER'},
		dataType : "json",
		success:function(data){
			if(data.result){
    			$.each(data.cmmCode, function (index, item) {
    				var searchArea = '${bbsFVo.searchArea}';
    				if(searchArea !=item.code){
    					$("#searchArea").append("<option value='"+item.code+"'>"+item.codeName+"</option>");		
    				}else{
    					$("#searchArea").append("<option value='"+item.code+"'selected>"+item.codeName+"</option>");
    				}    				
        		});
    			$("#centerButton").append("<li class='active'><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1101'>전체</a></li>");
    			
    			$.each(data.cmmCode2th, function (index, item) {
    				var searchCenter = '${bbsFVo.searchCenter}'; 
    				
    				if(searchCenter !=item.code){
    					$("#centerButton").append("<li><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1101&searchCenter="+item.code+"'>"+item.codeName+"</a></li>");
    				}else{
    					$("#centerButton").append("<li class='active'><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1101&searchCenter="+item.code+"'>"+item.codeName+"</a></li>");
    				}    
        		});
    			if($("#centerButton").children('.active').length>1){
    				$("#centerButton").children('.active').first().removeClass("active");
 	  			}
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
		<form:hidden path="bcIdx" />
		<form:hidden path="mode" /> 
		<form:hidden path="pageIndex" />
		<input type="hidden" name="tgtTypeCd" value="CENTER"/>
		
	 <!-- 여기부터 -->
		<h3>관계기관</h3>
		<ul class="tabBar" id="centerButton">
		</ul>
		<div class="board_list">
			<div class="board_search">
				<fieldset>
				<ul>
					<li>
						<legend>관계기관</legend>
						<label for="아이디값1">지역</label>
						<form:select path="searchArea" class="w100">
							<form:option value="" label="선택"/>
							<c:forEach items="${CmmCode}" var="result" >		
								<form:option value="${result.code}" label="${result.codeName}"/>
							</c:forEach>
						</form:select>
					</li>
					<li>
						<label for="아이디값2">주소</label>
						<div class="w280">
							<label for="txt1">검색어 입력</label>
							<input type="text" name="searchKey" id="txt1" value="<c:out value='${bbsFVo.searchKey }'/>" class="w400" title="검색어입력" placeholder="검색어를 입력해주세요">
							<input type="submit" value="검색">
						</div>
					</li>
				</ul>
				</fieldset>
			</div>
			
			<div style="clear:both"></div>

			<span class="count_total">총 <em><fmt:formatNumber value="${contentCnt}"></fmt:formatNumber></em>건　<em><c:out value="${paginationInfo.currentPageNo}"/></em>/<c:out value="${paginationInfo.totalPageCount}"/> 페이지</span>
			
			<div class="div_table b_type4">
				<div class="div_caption">관계기관</div>
				<div class="div_table-column-group">
					<<div class="div_col" style="width:8%"></div>
					<div class="div_col" style="width:20%"></div>
					<div class="div_col" style="width:15%"></div>
					<div class="div_col" style="width:7%"></div>
					<div class="div_col" style="width:30%"></div>
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
						<div class="div_td left"><c:out value="${contentList.AREA}" /> <c:out value="${contentList.CENTER}" /></div>
						<div class="div_td"><c:out value="${contentList.DEPTH_1}" /></div>
						<div class="div_td"><c:out value="${contentList.AREA}" /></div>
						<c:set var="arrAddr" value="${fn:split(contentList.ADDR_CONT,'_')}"/>
						<div class="div_td"><c:out value="${arrAddr[1]}" /> <c:out value="${arrAddr[2]}" /></div>
						<div class="div_td"><c:out value="${contentList.TEL_CONT}" /></div>
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