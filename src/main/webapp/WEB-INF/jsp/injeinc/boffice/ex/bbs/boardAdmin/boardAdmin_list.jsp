<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Basic Board List</title>
<script type="text/javascript">

/* 날짜 체크 return */
function doDateCheck(data){
	var str = "";
	if(data < 10){ 
		data = "0" + data;
	}
	str = data;
	return str;
}

/* 날짜 체크 */
function doDateSet(gbn){
	
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();
	var scRegDtSt = "";
	var scRegDtEd = "";
	
	if(gbn == '0'){ //오늘
		scRegDtSt = year + "-" + doDateCheck(month) + "-" + doDateCheck(day); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '1'){ //1일
		scRegDtSt = year + "-" + doDateCheck(month) + "-" + doDateCheck((day - 1)); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '2'){ //3일
		scRegDtSt = year + "-" + doDateCheck(month) + "-" + doDateCheck((day - 3)); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '3'){ //1주일
		scRegDtSt = year + "-" + doDateCheck(month) + "-" + doDateCheck((day - 7)); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '4'){ //한달
		scRegDtSt = year + "-" + doDateCheck((month - 1)) + "-" + doDateCheck(day); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '5'){ //연초
		scRegDtSt = year + "-" + "01" + "-" + "01"; 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '6'){
		scRegDtSt = "2007" + "-" + "01" + "-" + "01"; 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}
	
	document.getElementById("scRegDtSt").value = scRegDtSt; 
	document.getElementById("scRegDtEd").value = scRegDtEd;
	document.getElementById("gbn").value = gbn;
	//doSearchList();
}

/* 검색범위 second selectBox 조회 */
function doSecondSelect(val){
	var param = "cbUprCd=" + val;
	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/boardAdmin/secondSelectBox_list.do'/>"
		, data : param
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			var searchBbsValue = document.getElementById("searchBbsValue").value;
			var output = "";
			output += "<option value=''>:: 선택 ::</option>";
			$(data.secondSelectList).each(function(index, element){
				output += "<option value="+element.cbCd+" camel="+element.cbIdx+" temgbn="+element.bbsTempletGbn+" <c:if test='${"+element.cbCd+" eq "+searchBbsValue+" }'>selected='selected'</c:if>>"+ element.cbName +"</option>";
			});
			$('#searchBbs').html(output);
			$('#searchBbs').val(searchBbsValue);
		},error : function(code, msg, error) {
 				var str = code.status + " : " + msg + " : " + error;
 				console.log(str);
 		}
	});
}

/* second selectbox의  camel값 cbIdx에 담기*/
function doSelectBbs(){
	var cbIdxValue = $("select[name=searchBbs] option:selected").attr("camel");
	var bbsTempletGbnValue = $("select[name=searchBbs] option:selected").attr("temgbn");
	
	document.getElementById("cbIdxVal").value = cbIdxValue;
	document.getElementById("cbIdx").value = cbIdxValue;
	document.getElementById("bbsTempletGbn").value = bbsTempletGbnValue;
// 	if (bbsTempletGbnValue == "16050700") {
// 		$("#minDisplay").css("display", "");
// 	} else {
// 		$("#minDisplay").css("display", "none");
// 	}
}

/* 키워드 전체 선택 시 inputbox clear */
function doSearchKeyWordClear(val){
	if(val == null || val == ""){
		document.getElementById("searchKeyWord").value = "";	
	}
}

/* 조회버튼 검색 */
function doSearchList(){
	
	if(document.BoardAdminVo.searchBbsGroup.value == null || document.BoardAdminVo.searchBbsGroup.value == ''){
		alert("검색범위를 선택해주세요.");
		return;
	}else if(document.BoardAdminVo.searchBbs.value == null || document.BoardAdminVo.searchBbs.value == ''){
		alert("검색범위를 선택해주세요.");
		return;
	}
	
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_list.do'/>";
	document.BoardAdminVo.submit();
}

//페이징처리 
function doSitePag(pageIndex) {
	
	document.BoardAdminVo.pageIndex.value = pageIndex;
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_list.do'/>";
	document.BoardAdminVo.submit();
}

/* 행 갯수 검색 */
function doSearchRowCntList(val){
	document.getElementById("rowCntValue").value = val;
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_list.do'/>";
	document.BoardAdminVo.submit();
}

/* 상세페이지 */
function fnDetail(cbIdx, bcIdx){
	document.getElementById("searchBbsGroupText").value = $("#searchBbsGroup option:selected").text();
	document.getElementById("searchBbsText").value = $("#searchBbs option:selected").text();
	document.BoardAdminVo.bcIdx.value = bcIdx;
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_view.do'/>";
	document.BoardAdminVo.submit();
}

/* 게시물 전체선택 */
function doAllChk(){
	var checkObj = $("input[type='checkbox']");
	if(checkObj.length == $("input[type='checkbox']:checked").length){
		checkObj.prop("checked", false);
	}else{
		checkObj.prop("checked", true);
	}
}

/* 선택 게시물 삭제 및 승인 */
function doChkForm(gbn){
	//var rows = document.getElementById("contentCntVal").value;
	var rows = document.getElementById("rowCntValue").value;
	var result = "";
	
	if(gbn == "D"){
		result = confirm("선택한 글을 삭제하시겠습니까?");	
	}else if(gbn == "C"){
		result = confirm("선택한 글을 승인 하시겠습니까?");
	}
	
	if(result){
		var idxValue = "";
		var flag = false;
		for(i = 1; i <= rows; i++){
			if(eval("document.BoardAdminVo.chknum_"+i) != undefined){
				if(eval("document.BoardAdminVo.chknum_"+i).checked == true){
					document.getElementById("idxValue").value += eval("document.BoardAdminVo.chknum_"+i).value+",";
					flag = true;
				}
			}
		}
		if(flag){
			document.getElementById("chkGbn").value = gbn;
			document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_Mod.do'/>";
			document.BoardAdminVo.submit();
		}else{
			if(gbn == "D"){
				alert("삭제할 글이 없습니다.");
			}else if(gbn == "C"){
				alert("승인할 글이 없습니다.");
			}
			return;
		}
	}
}

/* Excel 다운로드 */
function doExcelForm(){
	//var thisForm = document.getElementById('form');
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_listExcel.do' />";
	document.BoardAdminVo.target = "_self";
	document.BoardAdminVo.submit();
}

/* 글쓰기 페이지 이동 */
function doCeateForm(){
	
	if(document.BoardAdminVo.searchBbsGroup.value == null || document.BoardAdminVo.searchBbsGroup.value == ''){
		alert("검색범위(게시판)를 선택해주세요.");
		return;
	}else if(document.BoardAdminVo.searchBbs.value == null || document.BoardAdminVo.searchBbs.value == ''){
		alert("검색범위(게시판)를 선택해주세요.");
		return;
	}
	
	document.getElementById("searchBbsGroupText").value = $("#searchBbsGroup option:selected").text();
	document.getElementById("searchBbsText").value = $("#searchBbs option:selected").text();
	document.BoardAdminVo.mode.value ="C";
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_RegForm.do'/>";
	document.BoardAdminVo.submit();
}

/* 키워드 전체 선택 시 inputbox clear */
function doSearchKeyWordClear(){
	document.getElementById("tgtTypeCd").value = "";	
	document.getElementById("searchKey").value = "";	
}

$(window).load(function() {
	
	var searchBbsGroup = '<c:out value='${searchBbsGroup}'/>';
	var searchBbs = '<c:out value='${searchBbs}'/>';
	if(searchBbs == null || searchBbs == ""){
		document.getElementById("searchBbsValue").value = "0";
	}else{
		document.getElementById("searchBbsGroupValue").value = searchBbsGroup;
		document.getElementById("searchBbsValue").value = searchBbs;
		doSecondSelect(searchBbsGroup);
	}
	
	var gbn = "<c:out value='${gbn}'/>";
	if(gbn == null || gbn == ""){
		doDateSet("6");	
	}else{
		doDateSet(gbn);
	}
	
	doSelectBbs();

	$("input[id=scRegDtSt], input[id=scRegDtSt]").datepicker();
	$("input[id=scRegDtSt], input[id=scRegDtSt]").mask("9999-99-99");
    $("input[id=scRegDtSt], input[id=scRegDtSt]").datepicker( "option", "dateFormat", "yy-mm-dd" );
    $("input[id=scRegDtEd], input[id=scRegDtEd]").datepicker();
	$("input[id=scRegDtEd], input[id=scRegDtEd]").mask("9999-99-99");
    $("input[id=scRegDtEd], input[id=scRegDtEd]").datepicker( "option", "dateFormat", "yy-mm-dd" );
    
});
</script>
<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
</style>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="BoardAdminVo" name="BoardAdminVo" method="post" >
<form:hidden path="pageIndex"/>

<input type="hidden" name="searchBbsGroupValue" id="searchBbsGroupValue" />
<input type="hidden" name="searchBbsValue" id="searchBbsValue" />
<input type="hidden" name="rowCntValue" id="rowCntValue" value="<c:out value="${rowCnt}"/>" />
<input type="hidden" name="contentCntVal" id="contentCntVal" value="<c:out value="${contentCnt}"/>" />
<input type="hidden" name="cbIdxVal" id="cbIdxVal" value="<c:out value="${cbIdx}"/>" />
<input type="hidden" name="cbIdx" id="cbIdx" value="0"/>
<input type="hidden" name="bcIdx" id="bcIdx" value="0"/>
<input type="hidden" name="bbsTempletGbn" id="bbsTempletGbn" />
<input type="hidden" name="idxValue" id="idxValue" />
<input type="hidden" name="gbn" id="gbn" />
<input type="hidden" name="chkGbn" id="chkGbn" />
<input type="hidden" name="searchBbsGroupText" id="searchBbsGroupText" />
<input type="hidden" name="searchBbsText" id="searchBbsText" />
<input type="hidden" name="webUseYn" id="webUseYn" value="Y" />
<input type="hidden" name="bbsFileCnt" id="bbsFileCnt" value="<c:out value="${bbsFileCnt}"/>" />
<%-- <form:hidden path="searchBbsGroup" />
<form:hidden path="searchBbs" /> --%>
<form:hidden path="mode" />

	<!-- 타이틀 -->
			<div class="title">
				<h2>통합게시물관리 &gt;</h2>
				<h3>통합게시물관리 리스트</h3>
			</div>
	<!-- 컨텐츠 영역 -->
	<div class="board board-list">
			<div id="contents">
			<table class="view1">
				<colgroup>
					<col width="12%" />
					<col width="*" />
				</colgroup>
				<tr>
					<td>검색기간</td>
					<td>
						<span class="cal_in" ><input id="scRegDtSt" name="scRegDtSt" size="8" title="시작일"/></span>
						<%-- <select name="startTime" id="startTime" style="width: 80px;">
							<!-- <option value="">:: 선택 ::</option> -->
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="${timeSelect.colValue}" <c:if test="${timeSelect.colValue eq param.startTime}">selected="selected"</c:if>>${timeSelect.colValue}</option>
							</c:forEach>
						</select>시 --%>
						~
						<span class="cal_in" ><input id="scRegDtEd" name="scRegDtEd" size="8" title="종료일"/></span>
						<%-- <select name="endTime" id="endTime" style="width: 80px;">
							<!-- <option value="">:: 선택 ::</option> -->
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="${timeSelect.colValue}" <c:if test="${timeSelect.colValue eq param.endTime}">selected="selected"</c:if>>${timeSelect.colValue}</option>
							</c:forEach>
						</select>시 --%>
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
							<option value="" <c:if test="${param.searchBbsGroup eq ''}">selected="selected"</c:if>>::: 선택하세요 :::</option>
							<c:forEach items="${firstSelectBox}" var="firstSelectBox" varStatus="status">
							<option value="<c:out value="${firstSelectBox.cbCd}" />" <c:if test="${firstSelectBox.cbCd eq searchBbsGroup}">selected="selected"</c:if>><c:out value="${firstSelectBox.cbName}" /></option>
							</c:forEach>
						</select>
						<select name="searchBbs" id="searchBbs" onchange="javascript:doSelectBbs();" style="width: 200px;">
						</select>
					</td>
				</tr>
				<tr>
					<td>게시물 선택</td>
					<td>
						<select name="bcDelYn" id="bcDelYn" >
							<option value="N" <c:if test="${param.bcDelYn eq 'N'}">selected="selected"</c:if>>일반게시물</option>
							<option value="Y" <c:if test="${param.bcDelYn eq 'Y'}">selected="selected"</c:if>>삭제게시물</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>키워드</td>
					<td>
						<c:if test="${searchList[0].CONTENT_MAPPING ne null}">
							<form:select title="조건선택" path="tgtTypeCd" style="width:100px;">
								<option value="">::선택::</option>
		 						<c:forEach items="${searchList}" var="searchList">
		 							<form:option value="${searchList.CONTENT_MAPPING}" label="${searchList.LABEL_NAME}" />
		 						</c:forEach>
							</form:select>
						</c:if>
						<input type="text" name="searchKey" id="searchKey" value="<c:out value="${searchKey}"/>" class="w30" title="검색어입력"/>
						<a href="javascript:doSearchList();" class="btn1">조회</a>
						<a href="javascript:doSearchKeyWordClear();" class="btn1">검색어삭제</a>
					</td>
				</tr>
			</table>
			
			<c:choose>
			<c:when test="${labelList eq '[]'}">
			<table summary="" class="list1" width="100%">
				<tr>
					<th>검색범위를 선택해주세요.</th>
				</tr>
			</table>
			</c:when>
			<c:otherwise>
			<div style="width: 50%; height:30px; text-align:left; float:left;">
				총 <c:out value="${contentCnt}"/>건
			</div>
			<div style="width: 50%; height: 30px; text-align: right; float: right;">
				<select name="rowCnt" id="rowCnt" onchange="javascript:doSearchRowCntList(this.value);">
					<%-- <option value="5" <c:if test="${rowCnt eq '5'}">selected="selected"</c:if>>5개씩</option> --%>
					<option value="10" <c:if test="${rowCnt eq '10'}">selected="selected"</c:if>>10개씩</option>
					<option value="30" <c:if test="${rowCnt eq '30'}">selected="selected"</c:if>>30개씩</option>
					<option value="50" <c:if test="${rowCnt eq '50'}">selected="selected"</c:if>>50개씩</option>
					<option value="100" <c:if test="${rowCnt eq '100'}">selected="selected"</c:if>>100개씩</option>
				</select>
			</div>
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
					<th><input type="checkbox" name="xxx_check_all" id="xxx_check_all" onclick="javascript:doAllChk();" /></th>
					<c:forEach items="${labelList}" var="labelList">
						<th><c:out value="${labelList.labelName}" /></th>
					</c:forEach>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${contentList}" var="contentList" varStatus="status">
					<tr class="<c:out value="${contentList.NOTI_YN eq 'Y'?'notice':'none'}"/>">
						<td align="center">
						<%-- || <c:out value="${contentList.BC_IDX}" /> ||
						|| <c:out value="${contentList.NO_CONT}" /> || --%>
							<input type="checkbox" name="chknum_<c:out value="${contentList.NO_CONT}" />" id="chknum" value="<c:out value="${contentList.BC_IDX}"/>" />
							<input type="hidden" name="apprYnVal_<c:out value="${contentList.NO_CONT}" />" id="apprYnVal_<c:out value="${contentList.NO_CONT}" />" value="<c:out value="${contentList.APPR_YN}"/>"/>
						</td>
						<c:forEach items="${contMappList}" var="contMappList">
								<c:choose>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'NO_CONT'}">
									<td align="center">
									<a href="javascript:fnDetail('<c:out value="${contentList.CB_IDX }"/>','<c:out value="${contentList.BC_IDX }"/>')" title="<c:out value="${(contentCnt+1)-(status.count+(BoardAdminVo.pageIndex-1)*BoardAdminVo.recordCountPerPage)}" />번글">
									<%-- <c:out value="${contentList.NO_CONT}" /> --%>
									<c:out value="${(contentCnt+1)-(status.count+(BoardAdminVo.pageIndex-1)*BoardAdminVo.recordCountPerPage)}" />
									</a>
									</td>
									</c:when>
								    <c:when test="${contMappList.CONTENT_MAPPING eq 'CATE_CONT'}"><td align="center"><c:out value="${contentList.CATE_NAME}" /></td></c:when>
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
											<a href="javascript:fnDetail('<c:out value="${contentList.CB_IDX }"/>','<c:out value="${contentList.BC_IDX }"/>')" title="<c:out value="${contentList.NO_CONT }"/>번글">
												<c:out value="${contentList.SUB_CONT}"/>
											</a>
										</td>
									</c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'NAME_CONT'}"><td class="none"><c:out value="${contentList.NAME_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'JUMIN_CONT'}"><td class="none"><c:out value="${contentList.JUMIN_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'ADDR_CONT'}"><td class="none"><c:out value="${contentList.ADDR_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EMAIL_CONT'}"><td class="none"><c:out value="${contentList.EMAIL_CONT}"  /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'TEL_CONT'}"><td class="none"><c:out value="${contentList.TEL_CONT}"  /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'PHONE_CONT'}"><td class="none"><c:out value="${contentList.PHONE_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'COUNT_CONT'}"><td class="none"><c:out value="${contentList.COUNT_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'CLOB_CONT'}"><td class="none"><c:out value="${contentList.CLOB_CONT}" /></td></c:when>
									<%-- <c:when test="${contMappList.CONTENT_MAPPING eq 'PW_CONT'}"><td class="none"><c:out value="${contentList.PW_CONT}" /></td></c:when> --%>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'M_LINK_CONT'}"><td class="none"><c:out value="${contentList.M_LINK_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'THUMNAIL_CONT'}"><td class="none"><c:out value="${contentList.THUMNAIL_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'SUB_LINK_CONT'}"><td class="none"><c:out value="${contentList.SUB_LINK_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'MW_OPENYN_CONT'}">
										<td class="none">
											<c:if test="${contentList.MW_OPENYN_CONT eq 'AD_Y'}">
												<span class="adno-open">비공개</span>
											</c:if>
											<c:if test="${contentList.MW_OPENYN_CONT eq '21000200'}">
												<span class="open">공개</span>
											</c:if>
											<c:if test="${contentList.MW_OPENYN_CONT eq '21000100'}">
												<span class="no-open">비공개</span>
											</c:if>
										</td>
									</c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'ANS_YN_CONT'}">
										<td class="none">
											<c:if test="${contentList.ANS_YN_CONT eq '22000100' }">
												답변요청
											</c:if>
											<c:if test="${contentList.ANS_YN_CONT eq '22000200' }">
												답변 필요 없음
											</c:if>
										</td>
									</c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'MW_STATUS_CONT'}">
										<td class="none">
											<c:if test="${contentList.MW_STATUS_CONT eq '20000100'}">
												<span class="status-move">삭제</span>
											</c:if>
											<c:if test="${contentList.MW_STATUS_CONT eq '20000200'}">
												<span class="status-move">비공개처리</span>
											</c:if>
											<c:if test="${contentList.MW_STATUS_CONT eq '20000300'}">
												<span class="status-move">이관</span>
											</c:if>
											<c:if test="${contentList.MW_STATUS_CONT eq '20000400'}">
												<span class="status-move">답변없음</span>
											</c:if>
											<c:if test="${contentList.MW_STATUS_CONT eq '20000500'}">
												<span class="status-end">답변완료</span>
											</c:if>
											<c:if test="${contentList.MW_STATUS_CONT eq '20000600'}">
												<span class="status-move">부서답변완료</span>
											</c:if>
											<c:if test="${contentList.MW_STATUS_CONT eq '20000700'}">
												<span class="adno-open">중간답변</span>
											</c:if>
											<c:if test="${contentList.MW_STATUS_CONT eq '20000800'}">
												<span class="status-ing">처리중</span>
											</c:if>
											<c:if test="${contentList.MW_STATUS_CONT eq '20000900'}">
												<span class="status-re">접수중</span>
											</c:if>
										</td>
									</c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'ANS_YN_CONT'}"><td class="none"><c:out value="${contentList.ANS_YN_CONT}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'ANS_COMP_CONT'}"><td class="none"><c:out value="${contentList.ANS_COMP_CONT}" /></td></c:when>
									<c:when test="${contMappList.LABEL_PROP_GBN eq '16020600'}"><td class="none"><c:if test="${contentList.FILE_YN eq 'Y'}"><span class="file"></span></c:if></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT1'}"><td class="none"><c:out value="${contentList.EXT1}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT2'}"><td class="none"><c:out value="${contentList.EXT2}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT3'}"><td class="none"><c:out value="${contentList.EXT3}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT4'}"><td class="none"><c:out value="${contentList.EXT4}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT5'}"><td class="none"><c:out value="${contentList.EXT5}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT6'}"><td class="none"><c:out value="${contentList.EXT6}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT7'}"><td class="none"><c:out value="${contentList.EXT7}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT8'}"><td class="none"><c:out value="${contentList.EXT8}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT9'}"><td class="none"><c:out value="${contentList.EXT9}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT10'}"><td class="none"><c:out value="${contentList.EXT10}" /></td></c:when>
									<c:when test="${contMappList.CONTENT_MAPPING eq 'REG_DT'}"><td class="none"><c:out value="${contentList.REG_DT}" /></td></c:when>
									<%-- <c:when test="${contMappList.CONTENT_MAPPING eq 'NAME_CONT'}"><td align="center"><c:out value="${contentList.NAME_CONT}" /></td></c:when>
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
									<c:when test="${contMappList.CONTENT_MAPPING eq 'EXT10'}"><td align="center"><c:out value="${contentList.EXT10}" /></td></c:when>--%>
								</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<!--paginate -->
			<div class="paginate">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doSitePag" />
			</div>
			<!--//paginate -->
			<!-- 버튼 -->
			<div class="btn_zone">
				<a href="javascript:doChkForm('D');" class="btn2">선택한 글 삭제</a>
				<a href="javascript:doChkForm('C');" class="btn2">승인</a>
				<a href="javascript:doExcelForm();" class="btn2">EXCEL로 저장</a>
				<a href="javascript:doCeateForm();" class="btn2">글쓰기</a>
			</div>
			<!-- //버튼 -->
			</c:otherwise>
			</c:choose>
		</div>
		</div>
		<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
