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
	/* var cbIdx = "<c:out value='${bbsFVo.cbIdx}' />";
	location.href = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=' />"+cbIdx+"&pageIndex="+pageIndex; */
	
	document.bbsFVo.pageIndex.value = pageIndex;
    document.bbsFVo.submit();
}

/*조회조건 카테고리*/
$(document).ready(function() {

	$.ajax({
		type: "GET",
		url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : {"cmmCode" : 'DRP_ITEM' },
		dataType : "json",
		success:function(data){
		 	console.dir(data);
		 	$.each(data.cmmCode, function (index, item) {
		 		
		 		var tgtTypeCd = '${bbsFVo.tgtTypeCd}'
		 		
		 		if(tgtTypeCd != item.codeValue){
		 			$("#tgtTypeCd").append("<option value='"+item.codeValue+"'>"+item.codeName+"</option>");
		 		}else{
		 			$("#tgtTypeCd").append("<option value='"+item.codeValue+"'selected>"+item.codeName+"</option>");	
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
						<legend>치유·재활콘텐츠 게시물 검색</legend>
						<ul>
							<li>
								<label for="아이디값1" class="soundOnly">카테고리선택</label>
								<form:select  path="tgtTypeCd" class="mr0">
									<form:option value="ALL" label="전체" />
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
		
		<div class="div_table b_type5 m_date">
			<div class="div_caption"></div>
				<div class="div_table-column-group">
					<div class="div_col" style="width:8%"></div>
					<div class="div_col" style="width:59%"></div>
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
					SUB_CONT		--제목
					REG_DT			--등록일	
					COUNT_CONT		--조회수
					FILE_CONT 		--파일첨부
					-->
					<c:forEach items="${contentList}" var="contentList" varStatus="status">
						<div class="div_tr">
							
							<div class="div_td"><c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></div>
							<div class="div_td left">
								<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${contentList.CB_IDX }
																						&bcIdx=${contentList.BC_IDX }
																						&pageIndex=${paginationInfo.currentPageNo}
																						&tgtTypeCd=${bbsFVo.tgtTypeCd }
																						&searchKey=${bbsFVo.searchKey}' />"
																						class="btn_bbsDetail">
									<c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
								</a>
							</div>
							<div class="div_td"><c:out value="${contentList.REG_DT}" /></div>
							<div class="div_td"><c:out value="${contentList.COUNT_CONT}" /></div>
							<div class="div_td">
								<c:if test="${contentList.FILE_YN eq 'Y'}">
									<span class="thumb"></span>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
		</div>
	</div>	
		
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
	</div>
	
</form:form>
