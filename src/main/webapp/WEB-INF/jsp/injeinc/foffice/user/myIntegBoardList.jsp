<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript">
//상세페이지
function doBbsFView(cbIdx, bcIdx){
	document.bbsFVo.cbIdx.value = cbIdx;
	document.bbsFVo.bcIdx.value = bcIdx;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MyIntegBoardView.do' />";
	document.bbsFVo.submit();
}

//페이징처리 
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MyIntegBoardList.do' />";
	document.bbsFVo.submit();
}

//검색
function doSearch(){
	document.bbsFVo.pageIndex.value = 1;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MyIntegBoardList.do' />";
	document.bbsFVo.submit();
}

</script>
<div class="top-guide">
	<p>* 회원님이 작성한 게시글을 확인합니다.</p>
</div>

<div class="board board-list">
	<p id="b_summary" class="hide">내가 작성한 글의 번호,게시판구분,제목,작성일,조회수 안내</p>
	<table class="list" aria-describedby="b_summary">
		<caption>나의민원 게시물 목록</caption>
		<colgroup>
			<col style="width:8%" class="none">
			<col style="width:15%" class="none">
			<col class="title">
			<col style="width:12%" class="date">
			<col style="width:10%" class="none">
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="none">번호</th>
				<th scope="col" class="none">게시판구분</th>
				<th scope="col">제목</th>
				<th scope="col">작성일</th>
				<th scope="col" class="none">조회수</th>							
			</tr>
		</thead>
		<tbody>
			<c:if test="${myIntegBoardCnt <= 0 }">
				<tr>
					<td colspan="5">등록된 내용이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${myIntegBoardList}" var="myIntegBoardList" varStatus="status">
				<tr>
					<td class="none"><c:out value="${(myIntegBoardCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></td>
					<td class="none"><c:out value="${myIntegBoardList.cbName }" /></td>
					<td class="title"><a href="javascript:doBbsFView('<c:out value="${myIntegBoardList.cbIdx }" />',<c:out value="${myIntegBoardList.bcIdx }" />);"><c:out value="${myIntegBoardList.subCont }" /></a></td>
					<td><c:out value="${myIntegBoardList.regDt }" /></td>
					<td class="none"><c:out value="${myIntegBoardList.countCont }" /></td>							
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form:form commandName="bbsFVo" name="bbsFVo" method="post" >
		<form:hidden path="pageIndex" />
		<form:hidden path="cbIdx" />
		<form:hidden path="bcIdx" />
		<!--paginate -->
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doBbsFVoFPag" />
		</div>
		<!--//paginate -->
		
		<div class="board-search">
			<form>
				<fieldset>
				<legend>게시판검색</legend>
				<select name="tgtTypeCd" title="조건선택">
					<option value="SUB_CONT">제목</option>
					<option value="CLOB_CONT">내용</option>
				</select>
				<input type="text" name="searchKey" id="searchKey" class="w30" value='<c:out value="${bbsFVo.searchKey }" />' title="검색어입력">
				<input type="image" src="/images/seocho/board/btn_search.gif" alt="검색" onclick="javascript:doSearch();">
				</fieldset>
			</form>
		</div><!-- //search -->
	</form:form>
	
</div><!-- //board -->