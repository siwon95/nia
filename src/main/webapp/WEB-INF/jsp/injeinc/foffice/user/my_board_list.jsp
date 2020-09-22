<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">
$(document).ready(function() {

	$(".tab-cont").children("div").hide();
	$(".tab-cont").children("div:eq(0)").show();
	$("#left-bar-container").height($("#mid-container").outerHeight() + $("#mid-top-container").outerHeight() + $("#footer").outerHeight());
	
	$(".tab-btns li").click(function(){
		$(".tab-btns").children("li").removeClass("on");
		$(this).addClass("on");
		$(".tab-cont").children("div").hide();

		var tab_index = $(".tab-btns li").index(this);
		$(".tab-cont").children("div:eq("+tab_index+")").show();
		
		$("#left-bar-container").height($("#mid-container").outerHeight() + $("#mid-top-container").outerHeight() + $("#footer").outerHeight());
	});
	
});
</script>
	<script type="text/javascript">
//고시/공고, 공지 코드 값.
var deptCode = "";
deptCode = "";

//등록페이지
 function doBbsFReg() {
	/* doBoardAuthorChk(Gbn); */
	var cbIdx = "278";
	document.bbsFVo.action = "/site/yangcheon/ex/bbs/Regist.do?cbIdx="+cbIdx;
	document.bbsFVo.mode.value ="C";	
	document.bbsFVo.submit();
}


//상세페이지
function doBbsFView(siteCd,cbIdx, bcIdx, Gbn, parentSeq){
// 	doBoardAuthorChk(Gbn);
	document.bbsFVoView.cbIdx.value = cbIdx;
	document.bbsFVoView.bcIdx.value = bcIdx;
	document.bbsFVoView.parentSeq.value = parentSeq;

	document.bbsFVoView.action = "/site/"+siteCd+"/ex/bbs/View.do";
	document.bbsFVoView.submit();
}

//페이징처리
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
	document.bbsFVo.action = "<c:url value='/site/yangcheon/foffice/user/myBoardList.do' />";
	document.bbsFVo.submit();
}


//검색
function doSearch(){
	document.bbsFVo.pageIndex.value = 1;
	document.bbsFVo.action = "<c:url value='/site/yangcheon/foffice/user/myBoardList.do' />";
	document.bbsFVo.submit();
}

//권한 체크
/* function doBoardAuthorChk(gbn){
	 $.ajax({
		type: "GET",
		url: "/site/seocho/ex/bbs/BoardAuthorChkAx.do",
		data : {"gbn" : gbn},
		dataType : "json",
		success:function(data){
			if(!data.flag){
				if( data.msg != null && data.msg != "" ){
					alert(data.msg);
				}
				if( data.returnUrl != null && data.returnUrl != "" ){
					location.href = data.returnUrl;
				}else{
					return;
				}
			}
		 },
        error: function(xhr,status,error){
        	alert(status);
        }
	 });

} */

</script>
<script type="text/javascript">
function selected(obj) {
    // 인덱스값 가져오기
    //alert(obj.selectedIndex);
    // 텍스트값 가져오기
    //alert(obj[obj.selectedIndex].innerHTML);
    // 밸류값 체크하기
    //alert(obj[obj.selectedIndex].value);
    
   if(obj[obj.selectedIndex].value == 'CD_SUBJECT'){
	   
	   document.getElementById("caCdidx").style.display = "inline"

   }else if(obj[obj.selectedIndex].value == 'SUB_CONT'){
	
	   document.getElementById("caCdidx").style.display = "none"
	   document.getElementById("searchKey").style.display = "inline"
	   document.getElementById("searchKey").value=""	
   }
  

 }
</script>

<script type="text/javascript">

function checkemailaddy(){
        if (bbsFVo.caCdidx.value == '1') {

    		bbsFVo.searchKey.readonly = false;
    		bbsFVo.searchKey.value = '';
    		bbsFVo.searchKey.focus();
        }
        else {

        	bbsFVo.searchKey.readonly = true;
        	bbsFVo.searchKey.value = $.trim(bbsFVo.caCdidx.options[bbsFVo.caCdidx.selectedIndex].text);
         }
    }

</script>
<form name="bbsFVoView" method="get" >
	<input type="hidden" name="cbIdx" value="">
	<input type="hidden" name="bcIdx" value="">
	<input type="hidden" name="parentSeq" value="">
</form>
<form:form commandName="bbsFVo" name="bbsFVo" method="post" >
		<form:hidden path="pageIndex" />
		<%-- <form:hidden path="cbIdx" /> --%>
		<form:hidden path="mode" />
		<%-- <form:hidden path="bcIdx" /> --%>
<div class="mypage-wrap">
<h3 class="tit">내가 쓴 글보기</h3>
<br/>
	<!-- Board Top -->
		<div class="board-top-search">
			<ul class="board-top-information">
				<li><span>전체 : <em><fmt:formatNumber value="${contentCnt}"></fmt:formatNumber></em></span></li>
				<li>, <span><c:out value="${paginationInfo.currentPageNo}"/> / <c:out value="${paginationInfo.totalPageCount}"/> 페이지</span></li>
			</ul>					
			<div class="board-top-form">
			<%-- <c:forEach var="cmsList" items="${cmsList}" varStatus="status">
			sss
			</c:forEach> --%>
					<select name="cbIdx" class="board-top-select">
						<option value="0">전체</option>
				
<c:forEach var="bbsmycategory" items="${bbsmycategory}" varStatus="status">
	<option value="<c:out value="${bbsmycategory.CB_IDX}"/>" <c:if test="${bbsmycategory.CB_IDX eq fn:trim(param.cbIdx)}">selected</c:if>><c:out value="${bbsmycategory.BBS_PATH}"/></option>
</c:forEach>

					</select>
				
				
					<select id="tgtTypeCd" name="tgtTypeCd" title="조건선택" class="board-top-select" onchange="selected(this)">
	 							<option value="SUB_CONT" <c:if test="${bbsFVo.tgtTypeCd eq 'SUB_CONT'}">selected</c:if>>제목</option>
	 							<option value="CLOB_CONT" <c:if test="${bbsFVo.tgtTypeCd eq 'CLOB_CONT'}">selected</c:if>>내용</option>
					</select>
				
				
				
				<!-- // 검색 (부서별 검색 - 게시판 생성시 부서명 선택과 검색을 체크 하였을때) 시작-->
				
						
							
						
				
						
							
						
				
						
							
						
				
				<!-- // 검색 (부서별 검색 - 게시판 생성시 부서명 선택과 검색을 체크 하였을때) 끝-->
								
								
				<input type="text" name="searchKey" id="searchKey" value="${bbsFVo.searchKey}" class="board-top-text"  title="검색어입력" >
				<input type="button" class="btn btn-board-top-search" title="검색하기"  onclick="javascript:doSearch('278');" alt="검색" >
				
			
			</div>
		</div>
	<!-- // Board Top -->
	
	<!-- // Board center -->	
		<article class="board-row">
	<div class="mb-tb-style">
				<table class="mb-table">
				<caption>자유게시판 목록</caption>
				<colgroup>
						<col width="8%">
						<col width="*%">
						<col width="40%">
						<col width="15%">
				</colgroup>
				<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">게시판</th>
					<th scope="col">등록일</th>
				</tr>
				<tbody>
				<c:if test="${contentCnt <= 0 }">
					<tr>
						<td colspan="5">등록된 내용이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${contentList}" var="contentList" varStatus="status">
<c:set var="boardName" value=""/>
<c:set var="boardSiteCd" value=""/>
<c:forEach var="bbsmycategory" items="${bbsmycategory}" varStatus="status2">
	<c:if test="${bbsmycategory.CB_IDX eq contentList.CB_IDX}">
		<c:set var="boardName" value="${bbsmycategory.BBS_PATH}"/>
		<c:set var="boardSiteCd" value="${bbsmycategory.MGR_SITE_CD}"/>
	</c:if>
</c:forEach>
					<tr class="">
						<td class="num">
							<c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" />
						</td>
						<td class="subject">
							<a href="#view" onclick="doBbsFView('<c:out value="${boardSiteCd}"/>','<c:out value="${contentList.CB_IDX }" />','<c:out value="${contentList.BC_IDX }" />','<c:out value="${bbsFVo.readGbn }" />','<c:out value="${contentList.PARENT_SEQ }"/>');return false;" title="<c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" />번글" target="_blank">
								<c:out value="${answerDepth}" escapeXml="false"/>
								<c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/><!-- <img src="/images/yangcheon/common/post-new.gif" alt="신규게시글" class="post-new"/> -->
							</a>
						</td>
						<td><c:out value="${boardName}"/></td>
						<td><c:out value="${contentList.REG_DT}"/></td>
					</tr>
				</c:forEach>
			</tbody>
			</table>	
			</div>		
		</article>
	<!-- // Board center -->	

</div>
	
	<!-- // Board foot -->	
	<div class="board-foot-container">				
			
			<!-- Pagination -->
			<div class="pagination">			
				<ui:pagination paginationInfo="${paginationInfo}" type="imagenew" jsFunction="doBbsFPag" />
			</div>
			<!-- // Pagination -->
	
		</div>
	<!-- // Board foot -->

</form:form>
