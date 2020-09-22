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
<%-- ------------------------------------------------------------
- 제목 : 게시물관리
- 파일명 : bbs_content_list_set.jsp
- 최종수정일 : 2020-03-30
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<c:import url="/layout/cms/head.jsp" ></c:import>
<!-- 페이지 전용 스타일/스크립트 -->
<script>
function ajaxModal(modal_id, modal_url, modal_param, modal_class){
	parent.ajaxModal(modal_id, modal_url, modal_param, modal_class);
}
$(function(){	
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		if($("#searchCbIdx").val() == "") {
			alert("글을 작성하실 게시판을 먼저 선택해 주세요");
			$("#searchCbIdx").focus();
			return;
		}
		$("#BbsContentVo #cbIdx").val($("#searchCbIdx").val());
		$("#BbsContentVo #bcIdx").val(0);
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_noDeco/ex/board/BbsContentForm.do' />";
		var modal_param = $("#BbsContentVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		$("#BbsContentVo #cbIdx").val($(this).parents("tr").eq(0).attr("data-cbIdx"));
		$("#BbsContentVo #bcIdx").val($(this).parents("tr").eq(0).attr("data-bcIdx"));
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/ex/board/BbsContentForm.do' />";
		var modal_param = $("#BbsContentVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_detail").click(function(e){
		e.preventDefault();
		$("#BbsContentVo #cbIdx").val($(this).parents("tr").eq(0).attr("data-cbIdx"));
		$("#BbsContentVo #bcIdx").val($(this).parents("tr").eq(0).attr("data-bcIdx"));
		var modal_id = "modal_detail";
		var modal_url = "<c:url value='/boffice_noDeco/ex/board/BbsContentView.do' />";
		var modal_param = $("#BbsContentVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_listAction").click(function(e){
		e.preventDefault();
		if($("#searchDelYn").val() == "N") {
			if(!confirm("일괄삭제하시겠습니까?")){
				return;
			}
		}else{
			if(!confirm("일괄복원하시겠습니까?")){
				return;
			}
		}
		if($("input[name='searchBcIdx']:checked").length == 0) {
			alert("선택한 대상이 없습니다.");
			return;
		}
		$("#BbsContentVo").attr("target","_parent");
		$("#BbsContentVo").attr("action", "<c:url value='BbsContentModBcDelYn.do' />").submit();
	});
	$(".btn_excel").click(function(e){
		e.preventDefault();
		$("#BbsContentVo").attr("action", "<c:url value='BbsContentListExcel.do' />").submit();
	});
	$(".btn_submit").click(function(e){
		e.preventDefault();
		$("#BbsContentVo").attr("action", "<c:url value='BbsContentListPop.do' />").submit();
	});
	$(".btn_sortUp").click(function(e){
		e.preventDefault();
		$("#BbsContentVo #cbIdx").val($(this).parents("tr").eq(0).attr("data-cbIdx"));
		$("#BbsContentVo #bcIdx").val($(this).parents("tr").eq(0).attr("data-bcIdx"));
		$("#BbsContentVo #mode").val("up");
		$("#BbsContentVo").attr("action", "/boffice_noDeco/ex/board/BbsContentSort.do").submit();
	});
	$(".btn_sortDown").click(function(e){
		e.preventDefault();
		$("#BbsContentVo #cbIdx").val($(this).parents("tr").eq(0).attr("data-cbIdx"));
		$("#BbsContentVo #bcIdx").val($(this).parents("tr").eq(0).attr("data-bcIdx"));
		$("#BbsContentVo #mode").val("down");
		$("#BbsContentVo").attr("action", "/boffice_noDeco/ex/board/BbsContentSort.do").submit();
	});
	
	//입력이벤트
	/*$("#BbsContentVo select, #BbsContentVo input").change(function(){
		if($(this).attr("name") == 'searchGroup'){
			$("#searchCbIdx").val("");
		}
		if($(this).attr("name") == 'siteCd'){
			$("#searchCbIdx").val("");
			$("#searchGroup").val("");
		}
		$("#BbsContentVo").submit();
	});*/
});

function checkAll() {
	if($("#chkAll").is(":checked")) {
		$("input[name=searchBcIdx]").prop("checked", true);
	}else{
		$("input[name=searchBcIdx]").prop("checked", false);
	}
}


</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
<form:form commandName="BbsContentVo" name="BbsContentVo" action="BbsContentListPop.do" method="post" class="searchListPage" onsubmit="return doSearch(this);">
	<form:hidden path="pageIndex" />
	<input type="hidden" id="cbIdx" name="cbIdx" value="0" />
	<input type="hidden" id="bcIdx" name="bcIdx" value="0" />
	<input type="hidden" id="delRsnCd" name="delRsnCd" value=""/>
	<input type="hidden" id="mode" name="mode" value=""/>
	<input type="hidden" name="returnurl" value="/boffice/ex/board/BbsContentListPop.do?mgrSiteCd=<c:out value="${param.mgrSiteCd}"/>&searchCbCd=<c:out value="${param.searchCbCd}"/>&pageIndex=<c:out value="${BbsContentVo.pageIndex}"/>"/>
	<input type="hidden" id="searchCbCd" name="searchCbCd" value="<c:out value="${param.searchCbCd}"/>"/>
	<input type="hidden" id="mgrSiteCd" name="mgrSiteCd" value="<c:out value="${param.mgrSiteCd}"/>"/>
	
	<c:set var="siteCd" value="${param.SiteCd}"/>
	<c:set var="tempSiteCd" value=""/>
	<c:forEach var="searchGroupInfo" items="${searchGroupList}">
		<c:if test="${searchGroupInfo.cbDepth eq '1'}">
			<c:set var="tempSiteCd" value="${searchGroupInfo.cbCd}"/>
		</c:if>
		<c:if test="${BbsContentVo.searchGroup eq searchGroupInfo.cbCd}">
			<c:set var="siteCd" value="${tempSiteCd}"/>
		</c:if>
	</c:forEach>
	
	<select name="siteCd" title="검색그룹" style="display:none">
		<option value="" label="전체보기">
		<c:forEach var="searchGroupInfo" items="${searchGroupList}">
				<c:if test="${searchGroupInfo.cbDepth eq '1'}">
				<option value="${searchGroupInfo.cbCd}" <c:if test="${siteCd eq searchGroupInfo.cbCd}">selected</c:if>>
					<c:forEach begin="2" end="${searchGroupInfo.cbDepth}">
						&nbsp;&nbsp;
					</c:forEach>
					${searchGroupInfo.cbName}
				</option>
				</c:if>
		</c:forEach>
	</select>
	
	<form:select path="searchGroup" title="검색그룹" style="display:none">
		<form:option value="" label="전체보기" />
		<c:set var="groupYn" value=""/>
		<c:set var="oldSiteCd" value=""/>
		<c:if test="${fn:trim(siteCd) ne ''}">
			<c:forEach var="searchGroupInfo" items="${searchGroupList}">
			<c:if test="${siteCd eq searchGroupInfo.cbCd && searchGroupInfo.cbDepth eq '1'}">
				<c:set var="groupYn" value="Y"/>
			</c:if>
			<c:if test="${siteCd ne searchGroupInfo.cbCd && searchGroupInfo.cbDepth eq '1'}">
				<c:set var="groupYn" value="N"/>
			</c:if>
			<c:if test="${groupYn eq 'Y' && searchGroupInfo.cbDepth ne '1'}">
				<option value="${searchGroupInfo.cbCd}" <c:if test="${BbsContentVo.searchGroup eq searchGroupInfo.cbCd}">selected</c:if>>
					<c:forEach begin="2" end="${searchGroupInfo.cbDepth}">
						&nbsp;&nbsp;
					</c:forEach>
					${searchGroupInfo.cbName}
				</option>
			</c:if>
			</c:forEach>
		</c:if>
	</form:select>
	
	<form:select path="searchCbIdx" title="게시판종류" style="display:none">
		<form:option value="" label="전체보기" />
		<c:forEach var="searchCbIdxInfo" items="${searchCbIdxList}">
			<c:if test="${searchCbIdxInfo.cbIdx ne 414 }">
				<form:option value="${searchCbIdxInfo.cbIdx}" label="${searchCbIdxInfo.cbName}" />
			</c:if>
		</c:forEach>
	</form:select>
						
	<div class="search">
		<table>
			<caption>검색테이블</caption>
			<tbody>
			
				<tr>
					<th scope="row">기간</th>
					<td>
						<label for="searchStartDate" class="hidden">기간(부터)</label>
						<form:input path="searchStartDate" size="10" class="useDatepicker dateFrom" /> ~ 
						<label for="searchEndDate" class="hidden">기간(까지)</label>
						<form:input path="searchEndDate" size="10" class="useDatepicker dateTo" />
					</td>
					<th scope="row"><label for="searchDelYn">상태</label></th>
					<td>
						<form:select path="searchDelYn">
							<form:option value="N" label="일반게시물" />
							<form:option value="Y" label="삭제게시물" />
						</form:select>
					</td>
					<th scope="row"><label for="searchKeyword">검색어</label></th>
					<td>
						<label for="searchCondition" class="hidden">검색구분</label>
						<form:select path="searchCondition">
							<form:option value="1" label="제목" />
							<form:option value="2" label="작성자" />
							<form:option value="3" label="내용" />
						</form:select>
						<form:input path="searchKeyword" class="w150" />
					</td>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="검색" class="btn_inline btn_search btn_submit">
	</div>
	
	<c:if test="${BbsContentVo.searchCbIdx != 280 || (BbsContentVo.searchCbIdx == 280 && SesUserPermCd eq '05010000')}">
	<div class="tableTitle">
		<div class="btnArea left">
			<c:if test="${SesUserPermCd eq '05010000'}">
				<a href="#" class="btn_inline btn_listAction<c:out value="${BbsContentVo.searchDelYn eq 'N' ? '' : ''}"/>"><c:out value="${BbsContentVo.searchDelYn eq 'N' ? '선택삭제' : '선택복원'}"/></a>
			</c:if>
		</div>
		<div class="btnArea">
			<a href="#" class="btn_inline on btn_excel">엑셀다운로드</a>
			<c:if test="${!empty BbsContentVo.searchCbIdx && BbsContentVo.groupYn ne 'Y'}">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</c:if>
			<label for="pageUnit" class="hidden">페이지수</label>
			<form:select path="pageUnit">
				<form:option value="10" label="10페이지" />
				<form:option value="20" label="20페이지" />
				<form:option value="30" label="30페이지" />
				<form:option value="50" label="50페이지" />
				<form:option value="100" label="100페이지" />
			</form:select>
		</div>
	</div>
	</c:if>
	
	<div class="tableBox">
		<table class="list">
			<caption>게시물 리스트</caption>
			<colgroup>
				<col class="w50">
				<col class="w50">
				<col class="w100">
				<col>
				<col class="w80">
				<col class="w80">
				<col class="w60">
				<col class="w60">
				<col class="w70">
				<col class="w150">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" id="chkAll" name="chkAll" onclick="checkAll();"/></th>
					<th scope="col">순번</th>
					<th scope="col">게시판</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">등록일</th>
					<c:choose>
						<c:when test="${BbsContentVo.searchCbIdx eq '931'}">
							<th scope="col">진행상태</th>
						</c:when>
						<c:otherwise>
							<th scope="col">조회수</th>
						</c:otherwise>
					</c:choose>
					<th scope="col">첨부파일</th>
					<th scope="col">정렬</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
				<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr data-cbIdx="<c:out value="${result.cbIdx}"/>" data-bcIdx="<c:out value="${result.bcIdx}"/>">
					<td><input type="checkbox" name="searchBcIdx" value="<c:out value="${result.bcIdx}"/>" /></td>
					<td><c:out value="${n - status.index}" /></td>
					<td><c:out value="${result.cbName}" /></td>
					<td class="left">
						<c:if test="${empty result.delRsnTxt}">
							<c:if test="${result.apprYn eq 'N'}"><strong class="red">[승인대기]</strong></c:if>
							<c:if test="${result.bcDelYn eq 'Y'}"><strong class="red">[삭제게시물]</strong></c:if>
							<c:if test="${result.notiYn eq 'Y'}"><strong class="red">[공지]</strong></c:if>
							<c:if test="${result.ansYnCont eq '22000100'}">
								<c:if test="${result.ansRYn eq 'N'}"><strong class="red">[<c:if test="${result.bbsTempletGbn eq 'bbs_minwon'}"><c:out value="${result.ansWriter} " /></c:if>접수중]</strong></c:if>
								<c:if test="${result.ansRYn eq 'Y'}"><strong class="blue">[<c:if test="${result.bbsTempletGbn eq 'bbs_minwon'}"><c:out value="${result.ansWriter} " /></c:if>답변완료]</strong></c:if>
								<c:if test="${result.ansRYn eq 'C'}"><strong class="red">[<c:if test="${result.bbsTempletGbn eq 'bbs_minwon'}"><c:out value="${result.ansWriter} " /></c:if>처리중]</strong></c:if>
							</c:if>
						</c:if>
						<c:if test="${result.answerDepth > 0}">
							<c:forEach varStatus="status" begin="1" end="${result.answerDepth}" step="1">
								&nbsp;&nbsp;&nbsp;
							</c:forEach>
							<span class="reply">답변</span>
						</c:if>
						<c:if test="${!empty result.delRsnTxt}">
							<strike><c:out value="${result.subCont}" /></strike>
							<p class="del" style="font-weight:400;color:#999999;"><c:out value="${result.delRsnTxt }" /></p>
						</c:if>
						<c:if test="${empty result.delRsnTxt}">
							<c:choose>
								<c:when test="${result.subCont ne ''}">
									<c:out value="${result.subCont}" />								
								</c:when>
								<c:otherwise>
									<c:out value="${result.center.concat(' - ').concat(result.addrCont)}" />
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${result.commentCnt > 0}">
							<span style="color:#ea002e;">[<c:out value="${result.commentCnt}" />]</span>
						</c:if>
						&nbsp;
					</td>
					<td><c:out value="${result.regId}" /></td>
					<td>
						<c:out value="${result.regDt}" />
					</td>
					<c:choose>
						<c:when test="${BbsContentVo.searchCbIdx eq '931'}">
							<td>
								<c:choose>
									<c:when test="${result.ext1 eq 'progress_status_01'}">
										요청
									</c:when>
									<c:when test="${result.ext1 eq 'progress_status_02'}">
										<span style="color:blue">진행중</span>
									</c:when>
									<c:when test="${result.ext1 eq 'progress_status_03'}">
										<span style="color:green">완료</span>
									</c:when>
								</c:choose>
							</td>
						</c:when>
						<c:otherwise>
							<td><c:out value="${result.countCont}" /></td>
						</c:otherwise>
					</c:choose>
					<td><c:if test="${result.fileCnt > 0}"><img src="/images/cms/icon_file.gif" alt="첨부파일"/></c:if></td>
					<td>
						<c:if test="${searchGroup.searchKeyword ne ''}">
							<c:if test="${!empty BbsContentVo.searchCbIdx}">
								<c:if test="${(n - status.index)<paginationInfo.totalRecordCount}">
									<a href="#" class="btn_ss btn_sortUp">▲</a>
								</c:if>
								<c:if test="${(n - status.index)>1}">
									<a href="#" class="btn_ss btn_sortDown">▼</a>
								</c:if>
							</c:if>
						</c:if>
					</td>
					<td>
						<a href="#" class="btn_inline btn_mod">수정</a>
						<a href="#" class="btn_inline btn_detail">상세보기</a>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="10" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
	</div>
</form:form>
</body>
</html>
