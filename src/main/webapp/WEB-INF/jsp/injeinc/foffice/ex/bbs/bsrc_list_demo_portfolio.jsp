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
//등록페이지
function doBbsFReg(gbn) {
 	doBoardAuthorChk(gbn);
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/Regist.do?cbIdx=' />"+cbIdx;
	document.bbsFVo.mode.value ="C";
	document.bbsFVo.submit();
}

//상세페이지
function doBbsFView(cbIdx, bcIdx, gbn){
	doBoardAuthorChk(gbn);
	//document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=' />"+cbIdx+"&bcIdx="+bcIdx;
	//document.bbsFVo.submit();
	location.href = "<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=' />"+cbIdx+"&bcIdx="+bcIdx;
}

//페이징처리
function doBbsFPag(pageIndex) {
	var cbIdx = "<c:out value='${bbsFVo.cbIdx}' />";
	//document.bbsFVo.pageIndex.value = pageIndex;
	//document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=' />"+cbIdx;
	//document.bbsFVo.submit();
	location.href = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=' />"+cbIdx+"&pageIndex="+pageIndex;
}

//검색
function doSearch(){
	document.bbsFVo.pageIndex.value = 1;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do' />";
	document.bbsFVo.submit();
}

//권한 체크
function doBoardAuthorChk(gbn){
 	 /* $.ajax({
 		type: "GET",
 		url: "<c:url value='/site/seocho/ex/bbs/BoardAuthorChkAx.do'/>",
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
 	 }); */
}
</script>
</head>
<body>
<form:form commandName="bbsFVo" name="bbsFVo" method="post">
<form:hidden path="pageIndex" />
<form:hidden path="cbIdx" />
<form:hidden path="bcIdx" />
<form:hidden path="mode" />
<input type="hidden" name="searchDateType" value="REG_DT"/>
<%-- <div>
	<fieldset>
		<legend>게시판 검색</legend>
		<select id="tgtTypeCd" name="tgtTypeCd" title="조건선택">
			<option value="SUB_CONT">제목</option>
		</select>
		<input type="text" name="searchKey" id="searchKey" value="<c:out value='${bbsFVo.searchKey }' />" title="검색어입력">
		<input type="submit" value="검색">
	</fieldset>
</div> --%>
<c:if test="${bbsFVo.socialYn=='Y'}">
	<jsp:include page="/layout/module/sns_share.jsp" flush="true"/>
</c:if>
<ul class="portfolioList">
	<c:if test="${contentCnt <= 0 }">
	<li class="empty">등록된 내용이 없습니다.</li>
	</c:if>
	<c:forEach items="${contentList}" var="contentList">
		<li><a href="#" onclick="doBbsFView('<c:out value="${contentList.CB_IDX }"/>', '<c:out value="${contentList.BC_IDX }"/>', '<c:out value="${bbsFVo.readGbn }"/>');return false;">
			<img src="/common/files/ThumbCreateDown.do?opath=<c:out value="${contentList.LIST_IMG }"/>&twidth=276&theight=300&mode=web" alt="섬네일이미지(<c:out value="${contentList.SUB_CONT}"/>)">
			<span>
				<b>
					<c:out value="${fn:substring(contentList.SUB_CONT,0,30)}" /><c:if test="${fn:length(contentList.SUB_CONT)>30}">...</c:if>
					<c:if test="${bbsFVo.commentYn=='Y'}">[<c:out value="${contentList.COMMENT_CNT}" />]</c:if>
				</b>
				<em>Client : <c:out value="${contentList.EXT1}" /></em>
			</span>
		</a></li>
	</c:forEach>
</ul>
<div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="imagenew" jsFunction="doBbsFPag" />
	<!-- <a href="?page=1" class="first">처음페이지</a>
	<a href="#" class="prev">이전페이지</a>
	<a href="?Page=1" class="active">1</a>
	<a href="?Page=2">2</a>
	<a href="?Page=3">3</a>
	<a href="?Page=4">4</a>
	<a href="?Page=5">5</a>
	<a href="?page=2" class="next">다음페이지</a>
	<a href="?page=8" class="last">마지막페이지</a> -->
</div>
</form:form>
</body>