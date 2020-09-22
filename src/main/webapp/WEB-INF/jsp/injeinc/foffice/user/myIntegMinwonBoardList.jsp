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
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MyIntegMinwonBoardView.do' />";
	document.bbsFVo.submit();
}

//페이징처리 
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MyIntegMinwonBoardList.do' />";
	document.bbsFVo.submit();
}

//검색
function doSearch(){
	document.bbsFVo.pageIndex.value = 1;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MyIntegMinwonBoardList.do' />";
	document.bbsFVo.submit();
}

</script>
<div class="top-guide">
	<p>* 회원님이 신청한 민원을 확인합니다.</p>
</div>

<div class="board board-list">
	<p id="b_summary" class="hide">내가 신청한 민원글의 번호,게시판구분,제목,작성일,조회수,공개여부,진행상태 안내</p>
	<table class="list" aria-describedby="b_summary">
		<caption>나의민원 게시물 목록</caption>
		<colgroup>
			<col style="width:8%" class="none">
			<col style="width:15%" class="none">
			<col class="title">
			<col style="width:15%" class="none">
			<col style="width:12%" class="date">
			<col style="width:10%" class="none">
			<col style="width:10%" class="status">
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="none">번호</th>
				<th scope="col" class="none">게시판구분</th>
				<th scope="col">제목</th>
				<th scope="col">작성일</th>
				<th scope="col" class="none">조회수</th>
				<th scope="col" class="none">공개여부</th>
				<th scope="col">진행상태</th>								
			</tr>
		</thead>
		<tbody>
			<c:if test="${myIntegMinwonBoardCnt <= 0 }">
				<tr>
					<td colspan="7">등록된 내용이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${myIntegMinwonBoardList}" var="myIntegMinwonBoardList" varStatus="status">
				<tr>
					<td class="none"><c:out value="${(myIntegMinwonBoardCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></td>
					<td class="none"><c:out value="${myIntegMinwonBoardList.cbName }" /></td>
					<td class="title">
						<a href="javascript:doBbsFView('<c:out value="${myIntegMinwonBoardList.cbIdx }" />',<c:out value="${myIntegMinwonBoardList.bcIdx }" />);"><c:out value="${myIntegMinwonBoardList.subCont }" /></a>
						<c:if test="${myIntegMinwonBoardList.delRsnTxt ne null}">
							<p class="del"><c:out value="${myIntegMinwonBoardList.delRsnTxt}" /></p> 
						</c:if>
						<c:if test="${myIntegMinwonBoardList.mcEndDay ne null}">
							<p class="del"><c:out value="${myIntegMinwonBoardList.mcEndDay}" />까지 답변 드리겠습니다.</p> 
						</c:if>
						<c:if test="${myIntegMinwonBoardList.mcDelayDay ne null}">
							<p class="del"><c:out value="${myIntegMinwonBoardList.mcDelayDay }" />까지 답변 드리겠습니다.</p> 
						</c:if>
					</td>
					<td><c:out value="${myIntegMinwonBoardList.regDt }" /></td>
					<td class="none"><fmt:formatNumber value="${myIntegMinwonBoardList.countCont}"></fmt:formatNumber></td>
					
					<td class="none">
						<c:if test="${myIntegMinwonBoardList.mwOpenynCont eq 'AD_Y'}">
							<span class="adno-open">비공개</span>
						</c:if>
						<c:if test="${myIntegMinwonBoardList.mwOpenynCont eq '21000200'}">
							<span class="open">공개</span>
						</c:if>
						<c:if test="${myIntegMinwonBoardList.mwOpenynCont eq '21000100'}">
							<span class="no-open">비공개</span>
						</c:if>
					</td>
					<td>
						<c:if test="${myIntegMinwonBoardList.mwStatusCont eq '22000200'}">
							<span class="status-move">답변없음</span>
						</c:if>
						<c:if test="${myIntegMinwonBoardList.mwStatusCont eq '20000500'}">
							<span class="status-end">답변완료</span>
						</c:if>
						<c:if test="${myIntegMinwonBoardList.mwStatusCont eq '20000800'}">
							<span class="status-ing">처리중</span>
						</c:if>
					</td>								
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