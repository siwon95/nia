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
	var checkAll = '${bbsFVo.checkAll}';
	if (!checkAll) {
		$(".paging").hide();		
	}else{
		$(".type2").hide();
		$(".btn_bg1").hide();
		$(".paging").show();
	}
});


$(function(){
	 $(".btn_bg1").click(function(e){
		e.preventDefault();
		$(".type2").hide();
		$(".btn_bg1").hide();
		$(".paging").show();
	});
});



//페이징처리
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
	document.bbsFVo.checkAll.value = "Y";
    document.bbsFVo.submit();
} 
</script>
<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);" action="/site/DRP0000/ex/bbs/List.do">
<form:hidden path="pageIndex" />
<form:hidden path="checkAll" />
<form:hidden path="bcIdx" />
<form:hidden path="mode" />

			<h3>통합자료검색</h3>
			<div class="total_search">
				<div>
					<input type="text" name="searchKey" value="<c:out value='${bbsFVo.searchKey}' />" placeholder="검색어를 입력하세요">
					<input type="hidden" name="cbIdx" value="<c:out value='1178'/>">
					<input type="submit" value="검색">
				</div>
			</div>
			<div class="search_title">
				<h4>게시판</h4> - <b><em>${bbsFVo.searchKey}</em>에 대한 검색 결과 입니다.
				<c:choose>
					<c:when test="${empty allSearchCntList[0].NOTICE_CNT}">
						<em>0건</em></b>
					</c:when>
					<c:otherwise>
						<em>${allSearchCntList[0].NOTICE_CNT}건</em></b>
					</c:otherwise>
				</c:choose>
			</div>
				<ul class="search_result">
					<c:if test="${allSearchCntList[0].NOTICE_CNT == '0' || empty allSearchCntList[0].NOTICE_CNT}">
						<li class="result_none">
							검색된 게시물이 없습니다.
						</li>
					</c:if>
					<c:forEach items="${allSearchList}" var="allList" varStatus="status">
						<c:if test="${allList.CB_IDX eq '1095' || allList.CB_IDX eq '1099' || allList.CB_IDX eq '1100' || allList.CB_IDX eq '1102' || allList.CB_IDX eq '1103' || allList.CB_IDX eq '1104' || allList.CB_IDX eq '1129' || allList.CB_IDX eq '1130' || allList.CB_IDX eq '1119' || allList.CB_IDX eq '1115' || allList.CB_IDX eq '1126' || allList.CB_IDX eq '1139'}">								
							<li id="type1">
								<dl>
									<dt>
										<em><c:out value="${allList.CB_NAME}"/></em>
										<c:set var="conSearchKey" value="${'<mark>'.concat(bbsFVo.searchKey).concat('</mark>')}"></c:set>
										<c:set var="repSubCont" value="${fn:replace(allList.SUB_CONT,bbsFVo.searchKey,conSearchKey)}"></c:set>
										<c:choose>
											<c:when test="${allList.CB_IDX eq '1099'}">
												<strong>${repSubCont}</strong>
											</c:when>
											<c:otherwise>
												<c:url value="/site/${strDomain}/ex/bbs/View.do" var="url">
													<c:param name="cbIdx" value="${allList.CB_IDX }"/>
													<c:param name="bcIdx" value="${allList.BC_IDX }"/>
													<c:param name="searchKey" value="${bbsFVo.searchKey}"/>
													<c:param name="detailType" value="ALL"/>
												</c:url>
												<a href="${url}"><strong>${repSubCont}</strong></a>												
											</c:otherwise>												
										</c:choose>
										<span><fmt:formatDate value="${allList.REG_DT}" pattern="yyyy-MM-dd" type="date"/></span>
									</dt>
									<dd>
										<c:out value="${cmm:outClobCont(allList.CLOB_CONT)}" escapeXml="false" />
									</dd>
									<dd>
										<c:if test="${allList.FILE_EXTSN ne null }">
											<br>
									 		<a href="/common/board/Download.do?bcIdx=<c:out value="${allList.BC_IDX}"/>&amp;cbIdx=<c:out value="${allList.CB_IDX}"/>&amp;streFileNm=<c:out value="${allList.STRE_FILE_NM}"/>" target="_blank" title="파일다운로드">
												<i class="<c:out value="${allList.FILE_EXTSN}"/>">
													<span><c:out value="${allList.ORIGNL_FILE_NM}" />  [<c:out value="${bbs:byteCalculation(allList.FILE_SIZE)}" />]</span>									
												</i>
											</a>
										</c:if>
									</dd>
								</dl>
							</li>
						</c:if>
					</c:forEach>
				</ul>
				<div class="btnArea right">
					<a href="#n" class="btn_l large btn_bg1">검색결과 더보기</a>
				</div>
				<div class="paging">
					<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
				</div>
				
				<div class="type2">
					<div class="search_title">
						<h4>FAQ</h4> - <b><em>${bbsFVo.searchKey}</em>에 대한 검색 결과 입니다. 
						<c:choose>
							<c:when test="${empty allSearchCntList[2].NOTICE_CNT}">
								<em>0건</em></b>
							</c:when>
							<c:otherwise>
								<em>${allSearchCntList[2].NOTICE_CNT}건</em></b>
							</c:otherwise>
						</c:choose>
					</div>
					<ul class="qna">
						<c:if test="${allSearchCntList[2].NOTICE_CNT == '0' || empty allSearchCntList[2].NOTICE_CNT}">
							<div class="bbsListFaq">
								<dt style="display:none;"></dt>
								<dd class="empty">등록된 내용이 없습니다.</dd>
							</div>	
						</c:if>
						<c:forEach items="${allSearchList}" var="allList" varStatus="status" >
							<c:if test="${allList.CB_IDX eq '1140'}">
								<li>
									<button type="button" class="related_plus related_minus"><strong><em>Q<label class="soundOnly">질문</label></em>${allList.SUB_CONT}</strong></button>
									<div style="display: none;">
										<em>A<label class="soundOnly">답변</label></em>
										<span><c:out value="${cmm:outClobCont(allList.CLOB_CONT)}" escapeXml="false" /></span>
									</div>
								</li> 		
							</c:if>	
						</c:forEach>
					</ul>
					<div class="search_title">
						<h4>관계기관</h4> - <b><em>${bbsFVo.searchKey}</em>에 대한 검색 결과 입니다. 
						<c:choose>
							<c:when test="${empty allSearchCntList[1].NOTICE_CNT}">
								<em>0건</em></b>
							</c:when>
							<c:otherwise>
								<em>${allSearchCntList[1].NOTICE_CNT}건</em></b>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="div_table">
						<div class="div_caption">관계기관/센터 게시물 목록</div>
						<div class="div_table-column-group">
								<div class="div_col" style="width:8%"></div>
								<div class="div_col" style="width:10%"></div>
								<div class="div_col" style="width:15%"></div>
								<div class="div_col" style="width:10%"></div>
								<div class="div_col" style="width:37%"></div>
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
							<c:if test="${allSearchCntList[1].NOTICE_CNT == '0' || empty allSearchCntList[1].NOTICE_CNT}">
								<div class="data_none">
									검색된 게시물이 없습니다.
								</div>
							</c:if>
							<c:set var="index" value="1"/>
							<c:forEach items="${allSearchList}" var="allList" varStatus="status">
								<c:if test="${allList.CB_IDX eq '1101' || allList.CB_IDX eq '1105' || allList.CB_IDX eq '1107' || allList.CB_IDX eq '1118' || allList.CB_IDX eq '1148' || allList.CB_IDX eq '1134' || allList.CB_IDX eq '1127'}">
									<div class="div_tr">
										<div class="div_td"><c:out value="${index}" /></div>
										<div class="div_td"><c:out value="${allList.CENTER}" /></div>
										<div class="div_td"><c:out value="${allList.DEPTH_1}" /></div>
										<div class="div_td"><c:out value="${allList.AREA}" /></div>
										<div class="div_td"><c:out value="${allList.ADDR_CONT}" /></div>
										<div class="div_td"><c:out value="${allList.TEL_CONT}" /></div>
										<div class="div_td">
											<a class="baro" href=<c:out value="${allList.EXT4}"/>>바로가기</a>
										</div>
									</div>
									<c:set var="index" value="${index+1}"/>
								</c:if>	
							</c:forEach>
						</div>	
					</div>
				</div>
</form:form>
