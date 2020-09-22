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
	var cbIdx = "<c:out value='${bbsFVo.cbIdx}' />";
	location.href = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=' />"+cbIdx+"&pageIndex="+pageIndex;
}

/*조회조건 카테고리*/
$(document).ready(function() {

	$.ajax({
		type: "GET",
		url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : {"cmmCode" : 'DRP_AREA' },
		dataType : "json",
		success:function(data){
		 	console.dir(data);
			$.each(data.cmmCode, function (index, item) {
				
				var searchArea = '${bbsFVo.searchArea}'
				
				if(searchArea !=item.code){
					$("#searchArea").append("<option value='"+item.code+"'>"+item.codeName+"</option>");
				}else{
					$("#searchArea").append("<option value='"+item.code+"'selected>"+item.codeName+"</option>");	
				}
				
    		});
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
	<div class="board_list">
	
		<div class="board_search">
			<c:if test="${bbsFVo.categoryUseYn eq 'Y'}">
				<form>
					<fieldset>
						<legend>관계기관/센터 전체 게시물 검색</legend>
						<ul>
							<li>
								<label for="아이디값1">지역</label>
								<form:select path="searchArea" class="mr0">
									<form:option value="ALL" label="전체"/>
								</form:select>
							</li>
						</ul>
						<div class="w280">
							<label for="txt1">주소</label>
							<input type="text" name="searchKey" id="searchKey" value="<c:out value='${bbsFVo.searchKey}' />" class="w400" title="검색어입력" placeholder="주소 검색어를 입력해주세요">
							<input type="submit" value="검색" title="검색">
						</div>
					</fieldset>
				</form>
			</c:if>
		</div>
		
		<div style="clear:both"></div>
		
		<span class="count_total">총 <em><fmt:formatNumber value="${contentCnt}"></fmt:formatNumber></em>건　<em><c:out value="${paginationInfo.currentPageNo}"/></em>/<c:out value="${paginationInfo.totalPageCount}"/> 페이지</span>
		
		<div class="div_table b_type4">
			<div class="div_caption"></div>
				<div class="div_table-column-group">
					<div class="div_col" style="width:8%"></div>
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
					
					<!-- 게시글이 없는경우 -->
					<c:if test="${contentCnt <= 0 }">
						<div class="data_none">등록된 내용이 없습니다.</div>
					</c:if>
					
					<!-- 
					조회목록 화면 
					NO_CONT		--번호
					EXT1		--시설명
					DEPTH_1		--대분류
					AREA		--지역
					ADDR_CONT	--주소	
					TEL_CONT	--전화번호
					SHORT_CUT	--바로가기
					-->
					<c:forEach items="${contentList}" var="contentList" varStatus="status">
						<div class="div_tr">
							
							<div class="div_td"><c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></div>
							<div class="div_td"><c:out value="${contentList.CENTER}" /></div>
							<div class="div_td"><c:out value="${contentList.DEPTH_1}" /></div>
							<div class="div_td"><c:out value="${contentList.AREA}" /></div>
							<div class="div_td"><c:out value="${contentList.ADDR_CONT}" /></div>
							<div class="div_td"><c:out value="${contentList.TEL_CONT}" /></div>
							<div class="div_td"><a href=<c:out value="${contentList.SHORT_CUT}" /> title="새창" target=""blank class="baro">바로가기</a></div>
						</div>
					</c:forEach>
				</div>
		</div>
	
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
	</div>
	</div>
	
</form:form>
