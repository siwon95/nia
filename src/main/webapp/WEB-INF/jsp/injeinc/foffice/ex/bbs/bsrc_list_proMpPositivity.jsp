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
	var searchForm = document.bbsFVo;
	$(".btn_bbsWrite").click(function(e){
		e.preventDefault();
		searchForm.action = "<c:url value='/site/${strDomain}/ex/bbs/Regist.do' />";
		searchForm.mode.value = "C";
		$(searchForm).submit();
	});

	<c:out value="${bbsFVo.readGbn }" />
	$(".btn_bbsDetail").click(function(e){
		var authResult = doBoardAuthorChk(gbn);
		if(!authResult){
			e.preventDefault();
			alert("권한이 없습니다.");
		}
	});
});

//권한 체크
function doBoardAuthorChk(){
	return true;
}
//페이징처리
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
    document.bbsFVo.submit();
}

/*조회조건 카테고리*/
$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : {"cmmCode" : 'DRP_1DEPTH' },
		dataType : "json",
		success:function(data){
			$("#depthButton").append("<li class='active'><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1172'>전체</a></li>");
			$.each(data.cmmCode, function (index, item) {
				if(item.code == 'DRP_50006'){
					return false;
				} 
				var searchDepth = '${bbsFVo.searchDepth}';
				if(searchDepth !=item.code){
					$("#depthButton").append("<li><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1172&searchDepth="+item.code+"'>"+item.codeName+"</a></li>");
				}else{
					$("#depthButton").append("<li class='active'><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1172&searchDepth="+item.code+"'>"+item.codeName+"</a></li>");
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
<body>

<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);">
<form:hidden path="pageIndex" />
<%-- <form:hidden path="cbIdx" /> --%>
<form:hidden path="bcIdx" />
<form:hidden path="mode" />

	<h3><c:out value="${bbsFVo.cbName }" /></h3>
	<ul id="depthButton" class="tabBar"></ul>
	
	<div class="board_list">
		<div class="board_search">
			<c:if test="${bbsFVo.categoryUseYn eq 'Y'}">
				<form>
					<fieldset>
						<legend>전문인력양성 게시물 검색</legend>
						<ul>
							<li>
								<label for="아이디값1" class="soundOnly">카테고리선택</label>
								<form:select  path="tgtTypeCd" class="mr0">
									<form:option value="ALL" label="전체" />
									<form:option value="SUB_CONT" label="교육명" />
									<form:option value="CENTER" label="담당기관" />
								</form:select>
							</li>
						</ul>	
						<div class="w280">
							<label for="txt1">검색어입력</label>
							<input type="text" name="searchKey" id="searchKey" value="<c:out value='${bbsFVo.searchKey}' />" class="w400" title="검색어입력" placeholder="검색어를 입력해주세요">
							<input type="submit" value="검색" title="검색">
						</div>
					</fieldset>
				</form>	
			</c:if>
		</div>
	
	
		<div style="clear:both"></div>
		
		<span class="count_total">총 <em><fmt:formatNumber value="${contentCnt}"></fmt:formatNumber></em>건　<em><c:out value="${paginationInfo.currentPageNo}"/></em>/<c:out value="${paginationInfo.totalPageCount}"/> 페이지</span>
		
		<div class="div_table">
			<div class="div_caption">전문인력양성 게시물 목록</div>
				<div class="div_table-column-group">
					<div class="div_col" style="width:8%"></div>
					<div class="div_col" style="width:15%"></div>
					<div class="div_col" style="width:44%"></div>
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
					<!-- 게시글이 없는경우 -->
					<c:if test="${contentCnt <= 0 }">
						<div class="data_none">등록된 내용이 없습니다.</div>
					</c:if>
					
					<!-- 
					조회목록 화면 
					NO_CONT			--번호
					EXT1			--구분
					SUB_CONT		--교육명
					CENTER			--담당기관	
					TO_DATE 		--신청기간
					COUNT_CONT		--조회수
					
					-->
					<c:forEach items="${contentList}" var="contentList" varStatus="status">
						<div class="div_tr">
							
							<div class="div_td"><c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></div>
							<div class="div_td"><c:out value="${contentList.PRO_MP_PSTVT}" /></div>
							<div class="div_td left">
								<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${contentList.CB_IDX }&bcIdx=${contentList.BC_IDX }&tgtTypeCd=${bbsFVo.tgtTypeCd }
														&searchKey=${bbsFVo.searchKey}&pageIndex=${paginationInfo.currentPageNo }&searchDepth=${bbsFVo.searchDepth}' />" class="btn_bbsDetail">
									<c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
								</a>
							</div>
							<div class="div_td"><c:out value="${contentList.CENTER}" /></div>
							<c:if test="${(contentList.TO_DATE != NULL && contentList.TO_DATE != '') || (contentList.FORM_DATE != NULL && contentList.FORM_DATE != '')}">
								<div class="div_td"><c:out value="${contentList.TO_DATE}" />~<c:out value="${contentList.FORM_DATE}" /></div>
							</c:if>
							<c:if test="${(contentList.TO_DATE == NULL || contentList.TO_DATE == '')&&(contentList.FORM_DATE != NULL || contentList.FORM_DATE != '')}">
								<div class="div_td"><c:out value="기관별상이" /></div>
							</c:if>
							<div class="div_td"><c:out value="${contentList.COUNT_CONT}" /></div>
						</div>
					</c:forEach>
				</div>
		</div>
	</div>	
		
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
	</div>
	
</form:form>
