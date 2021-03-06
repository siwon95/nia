<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%
request.setCharacterEncoding("utf-8");
response.addHeader("Content-Disposition", "attachment;filename=test.xls");

%>
<html>
<head>
<link href="<c:url value='/css/admin.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/board.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/layout.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery.timepicker.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.min.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
</style>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="BoardAdminVo" name="BoardAdminVo" method="post" >
<input type="hidden" name="searchBbsGroupValue" id="searchBbsGroupValue" />
<input type="hidden" name="searchBbsValue" id="searchBbsValue" />
<input type="hidden" name="rowCntValue" id="rowCntValue" value="<c:out value="${rowCnt}"/>" />
<input type="hidden" name="contentCntVal" id="contentCntVal" value="<c:out value="${contentCnt}"/>" />
<input type="hidden" name="cbIdx" id="cbIdx" value="<c:out value="${cbIdx}"/>" />
<input type="hidden" name="bcIdx" id="bcIdx" />
<input type="hidden" name="bbsTempletGbn" id="bbsTempletGbn" value="<c:out value="${bbsTempletGbn}"/>" />
<input type="hidden" name="idxValue" id="idxValue" />
<input type="hidden" name="gbn" id="gbn" />
<input type="hidden" name="searchBbsGroupText" id="searchBbsGroupText" />
<input type="hidden" name="searchBbsText" id="searchBbsText" />
<input type="hidden" name="bbsFileCnt" id="bbsFileCnt" value="<c:out value="${bbsFileCnt}"/>" />
<%-- <form:hidden path="searchBbsGroup" />
<form:hidden path="searchBbs" /> --%>
<form:hidden path="mode" />
<form:hidden path="pageIndex"/>
	<!-- 컨텐츠 영역 -->
			<div id="contents">
			<%-- <table class="view1">
				<colgroup>
					<col width="12%" />
					<col width="*" />
				</colgroup>
				<tr>
					<td>검색기간</td>
					<td>
						<input id="scRegDtSt" name="scRegDtSt" size="7" title="시작일"/>
						<select name="startTime" id="startTime" style="width: 80px;">
							<option value="">:: 선택 ::</option>
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="${timeSelect.colValue}" <c:if test="${timeSelect.colValue eq startTime}">selected="selected"</c:if>>${timeSelect.colValue}</option>
							</c:forEach>
							
						</select>시
						~
						<input id="scRegDtEd" name="scRegDtEd" size="7" title="종료일"/>
						<select name="endTime" id="endTime" style="width: 80px;">
							<option value="">:: 선택 ::</option>
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="${timeSelect.colValue}" <c:if test="${timeSelect.colValue eq endTime}">selected="selected"</c:if>>${timeSelect.colValue}</option>
							</c:forEach>
						</select>시
						<a href="javascript:doDateSet('0');" class="btn1">오늘</a>
						<a href="javascript:doDateSet('1');" class="btn1">1일</a>
						<a href="javascript:doDateSet('2');" class="btn1">3일</a>
						<a href="javascript:doDateSet('3');" class="btn1">1주일</a>
						<a href="javascript:doDateSet('4');" class="btn1">한달</a>
						<a href="javascript:doDateSet('5');" class="btn1">연초</a>
					</td>
				</tr>
				<tr>
					<td>검색범위</td>
					<td>
						<select name="searchBbsGroup" id="searchBbsGroup" onchange="javascript:doSecondSelect(this.value);" style="width: 200px;">
							<option value="">::: 선택하세요 :::</option>
							<c:forEach items="${firstSelectBox}" var="firstSelectBox" varStatus="status">
							<option value="<c:out value="${firstSelectBox.cbCd}" />" <c:if test="${firstSelectBox.cbCd eq searchBbsGroup}">selected="selected"</c:if>><c:out value="${firstSelectBox.cbName}" /></option>
							</c:forEach>
						</select>
						<select name="searchBbs" id="searchBbs" onchange="javascript:doSelectBbs();" style="width: 200px;">
							<option>::: 선택하세요 :::</option>
						</select>
					</td>
				</tr>
				<tr id="minDisplay" style="display: none;">
					<td>부서</td>
					<td>
						<select name="searchBbsGroup" id="searchBbsGroup" onchange="javascript:doSecondSelect(this.value);" style="width: 200px;">
							<option value="">::: 선택하세요 :::</option>
						</select>
						상태 : 
						<select name="searchBbsGroup" id="searchBbsGroup" onchange="javascript:doSecondSelect(this.value);" style="width: 200px;">
							<option value="">::: 선택하세요 :::</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>키워드</td>
					<td>
						<select name="searchCondition" id="searchCondition" onchange="javascript:doSearchKeyWordClear(this.value);">
							<option value="" title="전체" <c:if test="${searchCondition eq ''}">selected="selected"</c:if>>전체</option>
							<option value="sub" title="제목" <c:if test="${searchCondition eq 'sub'}">selected="selected"</c:if>>제목</option>
							<option value="id" title="작성자" <c:if test="${searchCondition eq 'id'}">selected="selected"</c:if>>작성자</option>
						</select>
						<span class="sfont">
							<input type="text" name="searchKeyWord" id="searchKeyWord" size="30" value="<c:out value="${searchKeyWord}"/>"/>
							<a href="javascript:doSearchList();" class="btn2">조회</a>
						</span>
						<c:if test="${searchList[0].CONTENT_MAPPING ne null}">
							<form:select title="조건선택" path="tgtTypeCd">
			 						<c:forEach items="${searchList}" var="searchList">
			 							<form:option value="${searchList.CONTENT_MAPPING}" label="${searchList.LABEL_NAME}" />
			 						</c:forEach>
							</form:select>
						</c:if>
						<input type="text" name="searchKey" id="searchKey" value="<c:out value="${searchKey}"/>" class="w30" title="검색어입력">
						<a href="javascript:doSearchList();" class="btn1">조회</a>
					</td>
				</tr>			
			</table> --%>
			
			<c:choose>
			<c:when test="${labelList eq '[]'}">
			<!-- <table summary="" class="list1" width="100%">
				<tr>
					<th>검색범위를 선택해주세요.</th>
				</tr>
			</table> -->
			</c:when>
			<c:otherwise>
			<%-- <div style="width: 50%; height:30px; text-align:left; float:left;">
				총 <c:out value="${contentCnt}"/>건
			</div> --%>
			<%-- <div style="width: 50%; height: 30px; text-align: right; float: right;">
				<select name="rowCnt" id="rowCnt" onchange="javascript:doSearchRowCntList(this.value);">
					<option value="5" <c:if test="${rowCnt eq '5'}">selected="selected"</c:if>>5개씩</option>
					<option value="10" <c:if test="${rowCnt eq '10'}">selected="selected"</c:if>>10개씩</option>
					<option value="30" <c:if test="${rowCnt eq '30'}">selected="selected"</c:if>>30개씩</option>
					<option value="50" <c:if test="${rowCnt eq '50'}">selected="selected"</c:if>>50개씩</option>
					<option value="100" <c:if test="${rowCnt eq '100'}">selected="selected"</c:if>>100개씩</option>
				</select>
			</div> --%>
			<br/>
			<table summary="" class="list1" width="100%">
				<caption></caption>
				<colgroup>
					<col width="5%" />
					<c:forEach items="${labelList}" var="labelList">
						<col style="width:<c:out value='${labelList.labelProvSize}' />%"></col>
					</c:forEach>
				</colgroup>
				<thead>
				<tr>
					<th><!-- <input type="checkbox" name="xxx_check_all" id="xxx_check_all" onclick="javascript:doAllChk();" /> --></th>
					<c:forEach items="${labelList}" var="labelList">
						<th><c:out value="${labelList.labelName}" /></th>
					</c:forEach>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${contentList}" var="contentList">
					<tr class="<c:out value="${contentList.NOTI_YN eq 'Y'?'notice':'none'}"/>">
						<td align="center">
							<%-- <input type="checkbox" name="chknum_<c:out value="${contentList.NO_CONT}" />" id="chknum" value="<c:out value="${contentList.BC_IDX}"/>" />
							<input type="hidden" name="apprYnVal_<c:out value="${contentList.NO_CONT}" />" id="apprYnVal_<c:out value="${contentList.NO_CONT}" />" value="<c:out value="${contentList.APPR_YN}"/>"/> --%>
						</td>
						<c:forEach items="${contMappList}" var="contMappList">
								<c:choose>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'NO_CONT'}"><td align="center"><c:out value="${contentList.NO_CONT}" /></td></c:when>
								    <c:when test="${contMappList.CONTENT_MAPPING eq 'CATE_CONT'}"><td align="center"><c:out value="${contentList.CATE_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'SUB_CONT'}">
										<td align="left">
											<c:if test="${contentList.APPR_YN eq 'N'}">
											<font color="red"><b>[승인대기]</b></font>
											</c:if>
											<c:if test="${contentList.MW_STATUS_YN eq 'N'}">
											<font color="blue"><b>[삭제게시물]</b></font>
											</c:if>
											<c:if test="${contentList.NOTI_YN eq 'Y'}">
											<font color="blue"><b>[공지]</b></font>
											</c:if>
											<%-- <a href="javascript:fnDetail(${contentList.CB_IDX },${contentList.BC_IDX })" title="${contentList.NO_CONT }번글"> --%>
												<c:out value="${fn:replace(contentList.SUB_CONT, '|', '<span class='reply'>답변</span>')}"/>
											<!-- </a> -->
										</td>
									</c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'NAME_CONT'}"><td align="center"><c:out value="${contentList.NAME_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'JUMIN_CONT'}"><td align="center"><c:out value="${contentList.JUMIN_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'ADDR_CONT'}"><td align="center"><c:out value="${contentList.ADDR_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EMAIL_CONT'}"><td align="center"><c:out value="${contentList.EMAIL_CONT}"  /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'TEL_CONT'}"><td align="center"><c:out value="${contentList.TEL_CONT}"  /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'PHONE_CONT'}"><td align="center"><c:out value="${contentList.PHONE_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'COUNT_CONT'}"><td align="center"><c:out value="${contentList.COUNT_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'CLOB_CONT'}"><td align="center"><c:out value="${contentList.CLOB_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'M_LINK_CONT'}"><td align="center"><c:out value="${contentList.M_LINK_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'THUMNAIL_CONT'}"><td align="center"><c:out value="${contentList.THUMNAIL_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'SUB_LINK_CONT'}"><td align="center"><c:out value="${contentList.SUB_LINK_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'MW_OPENYN_CONT'}"><td align="center"><c:out value="${contentList.MW_OPENYN_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'ANS_YN_CONT'}"><td align="center"><c:out value="${contentList.ANS_YN_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'ANS_COMP_CONT'}"><td align="center"><c:out value="${contentList.ANS_COMP_CONT}" /></td></c:when>
									<c:when test="${contMappList.LABEL_PROP_GBN eq '16020600'}"><td class="none"><c:if test="${contentList.FILE_YN eq 'Y'}"><span class="file"></span></c:if></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT1'}"><td align="center"><c:out value="${contentList.EXT1}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT2'}"><td align="center"><c:out value="${contentList.EXT2}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT3'}"><td align="center"><c:out value="${contentList.EXT3}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT4'}"><td align="center"><c:out value="${contentList.EXT4}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT5'}"><td align="center"><c:out value="${contentList.EXT5}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT6'}"><td align="center"><c:out value="${contentList.EXT6}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT7'}"><td align="center"><c:out value="${contentList.EXT7}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT8'}"><td align="center"><c:out value="${contentList.EXT8}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT9'}"><td align="center"><c:out value="${contentList.EXT9}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT10'}"><td align="center"><c:out value="${contentList.EXT10}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'REGDT_CONT'}"><td align="center"><c:out value="${contentList.REGDT_CONT}" /></td></c:when>
								</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<!--paginate -->
			<%-- <div class="paginate">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doSitePag" />
			</div> --%>
			<!--//paginate -->
			<!-- 버튼 -->
			<!-- <div class="btn_zone">
				<a href="javascript:doChkForm('D');" class="btn2">선택한 글 삭제</a>
				<a href="javascript:doChkForm('C');" class="btn2">승인</a>
				<a href="javascript:doExcelForm();" class="btn2">EXCEL로 저장</a>
				<a href="javascript:doCeateForm();" class="btn2">글쓰기</a>
			</div> -->
			<!-- //버튼 -->
			</c:otherwise>
			</c:choose>
		</div>
		<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
