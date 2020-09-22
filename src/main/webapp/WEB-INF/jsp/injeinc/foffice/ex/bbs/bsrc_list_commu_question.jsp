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
//페이징처리
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
    document.bbsFVo.submit();
} 
</script>

<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);">
<form:hidden path="pageIndex" />
<form:hidden path="bcIdx" />
<form:hidden path="mode" />		
	
	<h3>자주묻는질문</h3>
	<div class="board_list">
		<div class="board_search">
			<form>
				<fieldset>
					<legend>자주묻는질문 게시물 검색</legend>
					<ul>
						<li>
							<label for="아이디값1" class="soundOnly">카테고리선택</label>
						 	<form:select title="조건선택" path="tgtTypeCd" class="mr0">
						 		<form:option value="ALL" 	   label="전체" />
						 		<form:option value="SUB_CONT"  label="제목" />
								<form:option value="CLOB_CONT" label="내용" />
						 	</form:select>
				 		</li>  
					</ul>
				 	
				 	<div>
				 		<label for="txt1">검색어 입력</label>
						<input type="text" name="searchKey" id="txt1" value="<c:out value='${bbsFVo.searchKey }' />" title="검색어입력" placeholder="검색어를 입력해주세요">
						<input type="submit" value="검색" >
					</div>
				</fieldset> 
			</form>
		</div>
		<div style="clear:both"></div>
		
		<span class="count_total">총 <em><fmt:formatNumber value="${contentCnt}" /></em>건의 자주묻는질문(FAQ)이 있습니다.</span>
		
		<ul class="qna">
			<c:choose>
				<c:when test="${contentCnt != 0 }">
					<c:forEach items="${contentList}" var="contentList" varStatus="status">				
						<li>
							<button type="button"><em>Q<label class="soundOnly">질문</label></em><strong><c:out value="${contentList.SUB_CONT}" /></strong></button>
							<div>
								<em>A<label class="soundOnly">답변</label></em>
								<span><c:out value="${cmm:outClobCont(contentList.CLOB_CONT)}" escapeXml="false"/></span>
							</div>
						</li>
					</c:forEach>	
				</c:when>
				<c:otherwise>
						<div class="data_none">등록된 내용이 없습니다.</div>
				</c:otherwise>
			</c:choose>
				
		</ul>
	</div>
</form:form>
