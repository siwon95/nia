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

	var searchKind = '${bbsFVo.searchKind}';
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : {"cmmCode" : 'DRP_KINDS'},
		dataType : "json",
		success:function(data){
			$("#kindButton").append("<li class='active'><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1141'>전체</a></li>");
			$.each(data.cmmCode, function (index, item) {				
				if(searchKind !=item.code){
					$("#kindButton").append("<li><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1141&searchKind="+item.code+"'>"+item.codeName+"</a></li>");
				}else{ 
					$("#kindButton").append("<li class='active'><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1141&searchKind="+item.code+"'>"+item.codeName+"</a></li>");
				} 
			});
			if($("#kindButton").children('.active').length>1){
				$("#kindButton").children('.active').first().removeClass("active");
  			}
		 }, 
	       error: function(xhr,status,error){
	       	alert(status);
	       }
	 });
});

function checkId(regId) {
	var sessionId = "${ss_id}";
	if (sessionId !== regId) {
		event.preventDefault();
		alert("비밀글은 본인만 확인 할 수 있습니다.");
	}
}

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

		<h3>제안하기</h3>
		<ul id="kindButton"class="tabBar" ></ul>
	
		<div class="board_list">
			<div class="board_search">
				<fieldset>
					<legend>제안하기 게시물 검색</legend>
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
			</div>
			<div style="clear:both"></div>
			
			<!-- 페이지번호 및 전체 건수 조회 -->
			<span class="count_total">
				총 <em><fmt:formatNumber value="${contentCnt}" /></em>건,  
				<em><c:out value="${paginationInfo.currentPageNo}"/></em> / <c:out value="${paginationInfo.totalPageCount}"/>페이지
			</span>
			
			<div class="div_table b_type6">
				<div class="div_caption">제안하기 전체 게시물 목록</div>
				<div class="div_table-column-group">
					<div class="div_col" style="width:8%"></div>
					<div class="div_col" style="width:49%"></div>
					<div class="div_col" style="width:10%"></div>
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
					<c:if test="${contentCnt <= 0 }">
						<div class="data_none">등록된 내용이 없습니다.</div>
					</c:if>
					<c:forEach items="${contentList}" var="contentList" varStatus="status">
							<div class="div_tr">
								<div class="div_td"><c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></div>
					 			<div class="div_td left"><i>비밀글</i><a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${contentList.CB_IDX}&bcIdx=${contentList.BC_IDX }&pageIndex=${paginationInfo.currentPageNo}&cnt=${contentList.CNT}&parentSeq=${contentList.PARENT_SEQ}&tgtTypeCd=${bbsFVo.tgtTypeCd}&searchKey=${bbsFVo.searchKey}&searchKind=${bbsFVo.searchKind}&detailType=A' />"
					 																		class="btn_bbsDetail" onclick="checkId('${contentList.REG_ID}');">
																		<c:choose>
																			<c:when test="${contentList.REG_ID eq ss_id}">
																				<c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
																			</c:when>
																			<c:otherwise>
																				<c:out value="${fn:replace(fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;'),
																								fn:substring(
																								fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')
																								,2
																								,fn:length(fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')))
																								,'********')}" escapeXml="false"/>
																			</c:otherwise>
																		</c:choose>
																	</a>
								</div>
								<div class="div_td">
									<c:choose>
										<c:when test="${contentList.REG_ID eq ss_id}">
											<c:out value="${contentList.REG_ID}" />
										</c:when>
										<c:otherwise>
											<c:out value="${fn:replace(contentList.REG_ID,fn:substring(contentList.REG_ID,4,fn:length(contentList.REG_ID)),'******')}" />
										</c:otherwise>	
									</c:choose>
								</div>
								<div class="div_td"><c:out value="${contentList.REG_DT}" /></div>
								<div class="div_td"><c:out value="${contentList.COUNT_CONT}" /></div>
								<div class="div_td">
									<c:if test="${contentList.CNT eq '1'}">
										<span class="processing">처리중</span>
									</c:if>
									<c:if test="${contentList.CNT > '1'}">
										<span class="processing">처리완료</span>
									</c:if>
								</div>
							</div>
					</c:forEach>
				</div>
			</div>
			<div class="paging">
				<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
			</div>
			<div class="btnArea right"> 
				<a href="Regist.do?cbIdx=1141&mode=C" class="btn_l">등록</a>
			</div>
		</div>
</form:form>
