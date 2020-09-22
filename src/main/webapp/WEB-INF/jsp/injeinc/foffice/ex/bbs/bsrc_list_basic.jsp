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
//페이징처리
function doBbsFPag(pageIndex) {
	var cbIdx = "<c:out value='${bbsFVo.cbIdx}' />";
	//document.bbsFVo.pageIndex.value = pageIndex;
	//document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=' />"+cbIdx;
	//document.bbsFVo.submit();
	location.href = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=' />"+cbIdx+"&pageIndex="+pageIndex;
}
</script>

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
<div class="tableBox">
	<table class="list">
		<caption><c:out value="${bbsFVo.cbName }" /> 목록</caption>
		<colgroup>
			<c:forEach items="${labelList}" var="labelList">
				<col width="<c:out value='${labelList.labelProvSize}' />%">
			</c:forEach>
		</colgroup>
		<thead>
			<tr>
				<c:forEach items="${labelList}" var="labelList">
					<th scope="col"><c:out value="${labelList.labelName}" /></th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:if test="${contentCnt <= 0 }">
				<tr>
					<td colspan="<c:out value="${fn:length(labelList)}" />" class="empty">등록된 내용이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${contentList}" var="contentList" varStatus="status">
				<tr class="<c:out value="${contentList.NOTI_YN eq 'Y'?'notice':''}"/>">
					<c:forEach items="${contMappList}" var="contMappList" >
						<c:choose>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'NO_CONT'}">
								<c:if test="${contentList.NOTI_YN eq 'Y'}"><td>공지</td></c:if>
								<c:if test="${contentList.NOTI_YN eq 'N'}"><td><c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></td></c:if>
							</c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'CATE_CONT'}"><td><c:out value="${contentList.CATE_NAME}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'SUB_CONT'}">
								<td class="title">
									<c:choose>
										<c:when test="${(contentList.OPEN_YN_CONT eq 'AD_Y' || contentList.OPEN_YN_CONT eq '21000100') && bbsFVo.dupcode ne contentList.DUPCODE}">
											<c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
											<span class="icon_bbsSecret">비밀글</span>
										</c:when>
										<c:otherwise>
											<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${contentList.CB_IDX }&bcIdx=${contentList.BC_IDX }' />" class="btn_bbsDetail">
												<c:set var="answerDepth" value="0"/>
												<c:if test="${contentList.ANSWER_DEPTH > 0}">
													<c:forEach begin="1" end="${contentList.ANSWER_DEPTH}" step="1">
														<c:set var="answerDepth" value="${answerDepth+1}"/>
													</c:forEach>
													<span class="depthStep<c:out value="${answerDepth}" escapeXml="false"/>" style="display:inline-block; width:<c:out value="${answerDepth*20}"/>px;"></span>
												</c:if>
												<c:out value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
												<c:if test="${bbsFVo.commentYn=='Y'}"><span>[<c:out value="${contentList.COMMENT_CNT}" />]</span></c:if>
											</a>
										</c:otherwise>
									</c:choose>
									<c:if test="${contentList.MW_NO_REPLY_YN eq 'N'}">															
										<c:set var="ansDayDeptYn" value="${contentList.ANS_DAY_DEPT_YN }" />
										<fmt:formatDate var="tday" value="${toDay}" pattern="yyyy.MM.dd" />
										<c:if test="${empty contentList.MC_DELAY_DAY && contentList.MC_END_DAY ne null}">
 											<c:out value="${contentList.MC_END_DAY}" />까지 답변 드리겠습니다.
										</c:if>
										<c:if test="${!empty contentList.MC_DELAY_DAY}">
											<c:out value="${contentList.MC_DELAY_DAY}" />
										</c:if>
									</c:if>
								</td>
							</c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'ADDR_CONT'}">
								<c:set var="arrAddr" value="${fn:split(contentList.ADDR_CONT,'_')}"/>
								<td><c:out value="${arrAddr[1]}" /> <c:out value="${arrAddr[2]}" /></td>
							</c:when>
							<%--  --%>
							<c:when test="${contMappList.LABEL_PROP_GBN eq '16020600' || contMappList.LABEL_PROP_GBN eq '16021300'}"><%-- 파일 다운로드 & 확장자 구분으로 이미지 뿌리기 시작--%>
								<td>
									<c:if test="${contentList.FILE_YN eq 'Y'}">
										<!-- <c:forEach items="${fileBoardList}" var="fileBoardList">
											<c:if test="${contentList.BC_IDX eq fileBoardList.bcIdx }">
												<a href="/common/board/Download.do?bcIdx=<c:out value="${fileBoardList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileBoardList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileBoardList.streFileNm}"/>" target="_blank" title="파일다운로드">
													<span class="icon_bbsFile ${fileBoardList.fileExtsn}"></span>
												</a>
											</c:if>
										</c:forEach> -->
										<span class="icon_bbsFile ${fileBoardList.fileExtsn}"></span>
									</c:if>
								</td>
							</c:when>
							<c:when test="${contMappList.LABEL_PROP_GBN eq '16021300'}">
								<td><c:if test="${contentList.FILE_YN eq 'Y'}"><span class="file">첨부파일</span></c:if></td>
							</c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'REG_DT'}"><td><c:out value="${contentList.REG_DT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'COUNT_CONT'}"><td><c:out value="${contentList.COUNT_CONT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'NAME_CONT'}"><td><c:out value="${contentList.NAME_CONT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EMAIL_CONT'}"><td><c:out value="${contentList.EMAIL_CONT}"  /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'TEL_CONT'}"><td><c:out value="${contentList.TEL_CONT}"  /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'PHONE_CONT'}"><td><c:out value="${contentList.PHONE_CONT}" /></td></c:when>
							<%--
							<c:when test="${contMappList.CONTENT_MAPPING eq 'OPEN_YN_CONT'}">
								<td>
									<c:if test="${contentList.OPEN_YN_CONT eq 'AD_Y'}">비공개</c:if>
									<c:if test="${contentList.OPEN_YN_CONT eq '21000200'}">공개</c:if>
									<c:if test="${contentList.OPEN_YN_CONT eq '21000100'}">비공개</c:if>
								</td>
							</c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'ANS_YN_CONT'}">
								<td>
									<c:if test="${contentList.ANS_YN_CONT eq '22000100' }">답변요청</c:if>
									<c:if test="${contentList.ANS_YN_CONT eq '22000200' }">답변 요청 없음</c:if>
								</td>
							</c:when>
							<c:when test="${contentList.contentMapping eq 'MW_STATUS_CONT'  }">
								<td>
									<c:if test="${contentList.dataContent eq '22000200'}">답변없음</c:if>
									<c:if test="${contentList.dataContent eq '20000500'}">답변완료</c:if>
									<c:if test="${contentList.dataContent eq '20000800'}">처리중</c:if>
								</td>
							</c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'CD_SUBJECT'}"><td><c:out value="${contentList.CD_SUBJECT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'CHARGE_NAME_CONT'}"><td><c:out value="${contentList.CHARGE_NAME_CONT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'CHARGE_TEL_CONT'}"><td><c:out value="${contentList.CHARGE_TEL_CONT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'M_LINK_CONT'}"><td><c:out value="${contentList.M_LINK_CONT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'THUMNAIL_CONT'}"><td><c:out value="${contentList.THUMNAIL_CONT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'SUB_LINK_CONT'}"><td><c:out value="${contentList.SUB_LINK_CONT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'ANS_COMP_CONT'}"><td><c:out value="${contentList.ANS_COMP_CONT}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT1'}"><td><c:out value="${contentList.EXT1}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT2'}"><td><c:out value="${contentList.EXT2}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT3'}"><td><c:out value="${contentList.EXT3}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT4'}"><td><c:out value="${contentList.EXT4}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT5'}"><td><c:out value="${contentList.EXT5}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT6'}"><td><c:out value="${contentList.EXT6}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT7'}"><td><c:out value="${contentList.EXT7}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT8'}"><td><c:out value="${contentList.EXT8}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT9'}"><td><c:out value="${contentList.EXT9}" /></td></c:when>
							<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT10'}"><td><c:out value="${contentList.EXT10}" /></td></c:when>
							--%>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="imagenew" jsFunction="doBbsFPag" />
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
