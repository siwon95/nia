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
<head>
<script>
var searchForm = document.bbsFVo;
$(function(){
	$(".btn_bbsWrite").click(function(e){
		e.preventDefault();
		searchForm.action = "<c:url value='/site/${strDomain}/ex/bbs/Regist.do?cbIdx=${bbsFVo.cbIdx}' />";
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
 	/* $.ajax({
 		type: "GET",
 		url: "<c:url value='/site/seocho/ex/bbs/BoardAuthorChkAx.do'/>",
 		data : {"gbn" : readGbn},
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
 	 }); */
}
</script>
</head>
<body>
<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);">
<form:hidden path="pageIndex" />
<form:hidden path="cbIdx" />
<form:hidden path="bcIdx" />
<form:hidden path="mode" />
<input type="hidden" name="searchDateType" value="REG_DT"/>
<div class="bbsSearch">
	<span class="total">
		전체 <b class="txtBlue"><fmt:formatNumber value="${contentCnt}"></fmt:formatNumber></b>건,  
		<b><c:out value="${paginationInfo.currentPageNo}"/></b> / <c:out value="${paginationInfo.totalPageCount}"/>페이지
	</span>
	<c:if test="${bbsFVo.categoryUseYn eq 'Y'}">
		<form:select title="조건선택" path="cateTypeCd" class="board-top-select">
			<option value="">선택하세요</option>
			<c:forEach items="${categoryList}" var="categoryList">
				<form:option value="${categoryList.CATEGORY_CODE}" label="${categoryList.CATEGORY_NAME}" />
			</c:forEach>
		</form:select>	
	</c:if>
	<input type="text" name="searchKey" id="searchKey" value="<c:out value='${bbsFVo.searchKey }' />" class="w400" title="검색어입력" placeholder="검색어를 입력해주세요">
	<input type="submit" value="검색" title="검색">
	<!-- <a href="#" class="btnAll">전체보기</a> -->
</div>
<c:if test="${bbsFVo.socialYn=='Y'}">
	<jsp:include page="/layout/module/sns_share.jsp" flush="true"/>
</c:if>
<c:if test="${bbsFVo.writeGbn ne '16010500'}"><%-- 글쓰기 권한이 모두인 경우 --%>
	<div class="btnArea right">
		<a href="#" class="btn_inline btn_bbsWrite">글쓰기</a>
	</div>
</c:if>
<div class="bbsListThumb cols4">
	<ul>
		<c:if test="${contentCnt <= 0 }">
			<li class="empty">등록된 내용이 없습니다.</li>
		</c:if>
		<c:forEach items="${contentList}" var="contentList" varStatus="status">
			<li>
				<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${contentList.CB_IDX }&bcIdx=${contentList.BC_IDX }' />" class="btn_bbsDetail">
					<span class="thumb"><img src="/common/files/ThumbCreateDown.do?opath=<c:out value="${contentList.LIST_IMG }"/>&twidth=276&theight=300&mode=web" alt="섬네일이미지(<c:out value="${contentList.SUB_CONT}"/>)"></span>
					<div class="desc">
						<c:if test="${not empty contentList.CATE_NAME}"><span class="category"><c:out value="${contentList.CATE_NAME}" /></span></c:if>
						<c:if test="${not empty contentList.SUB_CONT}">
							<span class="title"><c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/></span>
							<c:if test="${bbsFVo.commentYn=='Y'}">[<c:out value="${contentList.COMMENT_CNT}" />]</c:if>
						</c:if>
						<c:if test="${not empty contentList.NAME_CONT}"><span class="name"><c:out value="${contentList.NAME_CONT}" /></span></c:if>
					</div>
				</a>
			</li>
		</c:forEach>
	</ul>
</div>
<div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="imagenew" jsFunction="doPageMove" />
	<!-- <a href="#" class="first">처음페이지</a>
	<a href="#" class="prev">이전페이지</a>
	<a class="active">1<span class="soundOnly">페이지(현재페이지)</span></a>
	<a href="#" class="num">2<span class="soundOnly">페이지</span></a>
	<a href="#" class="num">3<span class="soundOnly">페이지</span></a>
	<a href="#" class="num">4<span class="soundOnly">페이지</span></a>
	<a href="#" class="num">5<span class="soundOnly">페이지</span></a>
	<a href="#" class="num">6<span class="soundOnly">페이지</span></a>
	<a href="#" class="num">7<span class="soundOnly">페이지</span></a>
	<a href="#" class="num">8<span class="soundOnly">페이지</span></a>
	<a href="#" class="num">9<span class="soundOnly">페이지</span></a>
	<a href="#" class="num">10<span class="soundOnly">페이지</span></a>
	<a href="#" class="next">다음페이지</a>
	<a href="#" class="last">마지막페이지</a> -->
</div>
</form:form>
</body>
