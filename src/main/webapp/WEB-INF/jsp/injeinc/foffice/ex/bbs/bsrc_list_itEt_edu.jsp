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
	if(document.bbsFVo.abType.value == "L"){
		$(".edu_thumnail").hide();
		$(".trend_ethics").show();
		$(".btn_list").addClass("on");

	}else{
		$(".trend_ethics").hide();
		$(".edu_thumnail").show();
		$(".btn_thumnail").addClass("on");
	}
	
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
	
	 $(".btn_thumnail").click(function(e){
		
		e.preventDefault();
		$(".trend_ethics").hide();
		$(".edu_thumnail").show();
		$(".btn_thumnail").addClass("on");
		$(".btn_list").removeClass("on");
	});
	 
	$(".btn_list").click(function(e){
		
		 e.preventDefault();
		$(".edu_thumnail").hide();
		$(".trend_ethics").show(); 
		$(".btn_list").addClass("on");
		$(".btn_thumnail").removeClass("on");
	});
	
});
	
	
	
//권한 체크
function doBoardAuthorChk(){
	return true;
}
//페이징처리
function doBbsFPag(pageIndex) {
	/* var cbIdx = "<c:out value='${bbsFVo.cbIdx}' />";
	location.href = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=' />"+cbIdx+"&pageIndex="+pageIndex; */
	
	document.bbsFVo.pageIndex.value = pageIndex;
    document.bbsFVo.submit();
}

</script>
<body>

<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);">
<form:hidden path="pageIndex" />
<form:hidden path="bcIdx" />
<form:hidden path="mode" />
<form:hidden path="abType" />

	<h3><c:out value="${bbsFVo.cbName }" /></h3>
	<div class="board_list">
		<div class="list_thumnail">
			<a href="#n" class="btn_thumnail"><span>교육콘텐츠 목록형으로</span></a>
			<a href="#n" class="btn_list"><span>교육콘텐츠 썸네일형으로</span></a>
		</div>
		<div style="clear:both"></div>

		<span class="count_total">총 <em><fmt:formatNumber value="${contentCnt}"></fmt:formatNumber></em>건　<em><c:out value="${paginationInfo.currentPageNo}"/></em>/<c:out value="${paginationInfo.totalPageCount}"/> 페이지</span>
		
		<div class="edu_thumnail">
			<ul>
				<c:forEach items="${contentList}" var="contentList" varStatus="status">
					<li>
						<div>
							<div class="img">
								<img src="/common/files/ThumbCreateDown.do?opath=<c:out value="${contentList.LIST_IMG }"/>&twidth=276&theight=300&mode=web" alt="" width="276px" height="300px">
							</div>
							<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${contentList.CB_IDX }&bcIdx=${contentList.BC_IDX }&pageIndex=${paginationInfo.currentPageNo }
												&abType=' />" class="btn_bbsDetail">
								<c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
							</a>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		
			<ul class="trend_ethics">
				<c:forEach items="${contentList}" var="contentList" varStatus="status">
					<li>
						<div class="img">
							<img src="/common/files/ThumbCreateDown.do?opath=<c:out value="${contentList.LIST_IMG }"/>&twidth=276&theight=300&mode=web&fileExtsn=<c:out value="${contentList.FILE_EXTSN }"/>" alt="">
						</div>
						<div class="con">
							<strong>
								<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${contentList.CB_IDX }&bcIdx=${contentList.BC_IDX }&pageIndex=${paginationInfo.currentPageNo }
							  						&abType=L' />" class="btn_bbsDetail">
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
				</c:forEach>
			</ul>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
		</div>
	</div>
</form:form>
