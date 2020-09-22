<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<jsp:useBean id="toDay" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Basic Board List</title>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/js/jquery/ui/i18n/jquery.ui.datepicker-ko.js" charset="UTF-8"></script>
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
	}
	
// 	document.getElementById("scRegDtSt").value = scRegDtSt; 
// 	document.getElementById("scRegDtEd").value = scRegDtEd;
	document.GBoardAdminVo.scRegDtSt.value = scRegDtSt; 
	document.GBoardAdminVo.scRegDtEd.value = scRegDtEd;
	document.getElementById("gbn").value = gbn;
	//doSearchList();
}

/* 키워드 전체 선택 시 inputbox clear */
function doSearchKeyWordClear(){
	document.getElementById("searchField").value = "";	
	document.getElementById("searchKeyWord").value = "";	
}

/* 조회버튼 검색 */
function doSearchList(){
	document.GBoardAdminVo.pageIndex.value = 1;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_list.do'/>";
	document.GBoardAdminVo.submit();
}

//페이징처리 
function doSitePag(pageIndex) {
	
	document.GBoardAdminVo.pageIndex.value = pageIndex;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_list.do'/>";
	document.GBoardAdminVo.submit();
}

/* Excel 다운로드 */
function doExcelForm(){
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_listExcel.do' />";
	document.GBoardAdminVo.target = "_self";
	document.GBoardAdminVo.submit();
}

/* 상세페이지 */
function doDetail(cbIdx, bcIdx){
	document.GBoardAdminVo.cbIdx.value = cbIdx;
	document.GBoardAdminVo.bcIdx.value = bcIdx;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do'/>";
	document.GBoardAdminVo.submit();
}

function checkAll() {
	if($("#chkAll").is(":checked")) {
		$("input[name=searchBcIdx]").prop("checked", true);
	}else{
		$("input[name=searchBcIdx]").prop("checked", false);
	}
}

function openDepartSelectForm() {
	
	if($("input[name='searchBcIdx']:checked").length == 0) {
		alert("선택한 대상이 없습니다."); return;
	}
	
	$("#divDepartSelect").dialog("open");
}

function doDepartSelect() {
	
	if($("#cdSelectBoxList1").val() == "") {
		alert("민원답변부서를 선택해주세요"); return;
	}
	
	var searchBcIdx = $("input[name='searchBcIdx']:checkbox:checked").map(function () {return this.value;}).get();
	var cdSelectBoxList = $("select[name='cdSelectBoxList']").map(function () {return this.value;}).get();
	
	$.ajax({
		type: "POST",
		url: "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardSelectDepartArr.do'/>",
		data : "searchBcIdx="+searchBcIdx+"&cdSelectBoxList="+cdSelectBoxList,
		dataType : "json",
		success:function(data) {
			alert(data.message);
			if(data.result) {
				doSearchList();
			}
		 },
		error: function(xhr,status,error){
			alert(status);
		}
	});
}

$(document).ready(function() {
	
	<c:if test="${RoleIdx eq 'RL00000022' && permCd eq '05010000'}">
		var gbn = "<c:out value='${gbn}'/>";
		var scRegDtSt = "<c:out value='${param.scRegDtSt}'/>";
		if(scRegDtSt == null || scRegDtSt == ''){
			if(gbn == null || gbn == ""){
				doDateSet("4");	
			}else{
				doDateSet(gbn);
			}	
		}
		
		
		//검색항목 날짜 셋팅
	
		$("input[id=scRegDtSt], input[id=scRegDtSt]").datepicker();
		$("input[id=scRegDtSt], input[id=scRegDtSt]").mask("9999-99-99");
	    $("input[id=scRegDtSt], input[id=scRegDtSt]").datepicker( "option", "dateFormat", "yy-mm-dd" );
	    $("input[id=scRegDtEd], input[id=scRegDtEd]").datepicker();
		$("input[id=scRegDtEd], input[id=scRegDtEd]").mask("9999-99-99");
	    $("input[id=scRegDtEd], input[id=scRegDtEd]").datepicker( "option", "dateFormat", "yy-mm-dd" );
    </c:if>
  	//검색항목 답변일자 셋팅
    $("input[id=schMbpERegDtSt], input[id=schMbpERegDtSt]").datepicker();
	$("input[id=schMbpERegDtSt], input[id=schMbpERegDtSt]").mask("9999-99-99");
    $("input[id=schMbpERegDtSt], input[id=schMbpERegDtSt]").datepicker( "option", "dateFormat", "yy-mm-dd" );
    $("input[id=schMbpERegDtEd], input[id=schMbpERegDtEd]").datepicker();
	$("input[id=schMbpERegDtEd], input[id=schMbpERegDtEd]").mask("9999-99-99");
    $("input[id=schMbpERegDtEd], input[id=schMbpERegDtEd]").datepicker( "option", "dateFormat", "yy-mm-dd" );

	$("#divDepartSelect").dialog({
		autoOpen: false,
		height: 260,
		width: 390,
		modal: true,
		buttons: {
			"부서지정": function() {
				doDepartSelect();
			},
			"닫기": function() {
				$(this).dialog("close");
			}
		}
	});
    
});
</script>
<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
	
	.ui-datepicker select.ui-datepicker-month, .ui-datepicker select.ui-datepicker-year {width:35%;}
</style>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="GBoardAdminVo" name="GBoardAdminVo" method="post" >
<form:hidden path="mode" />
<form:hidden path="pageIndex"/>
<%-- <form:hidden path="rowCnt"/> --%>
<form:hidden path="cbIdx"/>
<form:hidden path="bcIdx"/>
<form:hidden path="gbnVal"/>
<input type="hidden" id="gbn" name="gbn" />

	<!-- 컨텐츠 영역 -->
	<div class="board board-list">
			<div id="contents">
			<table class="write">
				<colgroup>
					<col width="11%" />
					<col width="39%" />
					<col width="11%" />
					<col width="39%" />
				</colgroup>
				<!-- 최고관리자 -->
				<%-- <c:if test="${deptCd ne '05010000'}"> 2016_11_03일  --%>
				
				<c:if test="${RoleIdx eq 'RL00000022' || permCd eq '05010000'}">
				<tr>
					<td><label for="scRegDtSt">검색기간</label></td>
					<td colspan="3">
						<%-- <form:input path="scRegDtSt" size="8" /> --%>
						<span class="cal_in" ><input type="text" id="scRegDtSt" name="scRegDtSt" size="8" value="<c:out value="${param.scRegDtSt}"/>" /></span>
						<%-- <select id="startTime" name="startTime" style="width: 80px;">
							<!-- <option value="">::선택::</option> -->
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="${timeSelect.colValue}" <c:if test="${timeSelect.colValue eq param.startTime}">selected="selected"</c:if>>${timeSelect.colValue}</option>
							</c:forEach>
						</select>시 --%>
						~
						<%-- <form:input path="scRegDtEd" size="8" /> --%>
						<span class="cal_in" ><input type="text" id="scRegDtEd" name="scRegDtEd" size="8" value="<c:out value="${param.scRegDtEd}"/>" /></span>
						<%-- <select id="endTime" name="endTime" style="width: 80px;">
							<!-- <option value="">::선택::</option> -->
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="${timeSelect.colValue}" <c:if test="${timeSelect.colValue eq param.endTime}">selected="selected"</c:if>>${timeSelect.colValue}</option>
							</c:forEach>
						</select>시 --%>
						&nbsp;<a href="javascript:doDateSet('0');" class="btn3">오늘</a>
						<a href="javascript:doDateSet('1');" class="btn3">1일</a>
						<a href="javascript:doDateSet('2');" class="btn3">3일</a>
						<a href="javascript:doDateSet('3');" class="btn3">1주일</a>
						<a href="javascript:doDateSet('4');" class="btn3">한달</a>
						<a href="javascript:doDateSet('5');" class="btn3">연초</a>
					</td>
				</tr>
				<tr>
					<td><label for="bcDelYn">상태</label></td>
					<td>
						<select id="bcDelYn" name="bcDelYn" style="width: 300px;">
							<option value="N" <c:if test="${param.bcDelYn eq 'N'}">selected="selected"</c:if>>일반게시물</option>
							<option value="Y" <c:if test="${param.bcDelYn eq 'Y'}">selected="selected"</c:if>>삭제게시물</option>
						</select>
					</td>
					<td><label for="rowCnt">페이지수</label></td>
					<td>
						<select id="rowCnt" name="rowCnt" style="width: 100px;">
							<option value="15" <c:if test="${param.rowCnt eq '15'}">selected="selected"</c:if>>15</option>
							<option value="30" <c:if test="${param.rowCnt eq '30'}">selected="selected"</c:if>>30</option>
							<option value="50" <c:if test="${param.rowCnt eq '50'}">selected="selected"</c:if>>50</option>
							<option value="100" <c:if test="${param.rowCnt eq '100'}">selected="selected"</c:if>>100</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label for="mwStatusCont">처리결과</label></td>
					<td>
						<select id="mwStatusCont" name="mwStatusCont" style="width: 300px;">
							<option value="" <c:if test="${param.mwStatusCont eq ''}">selected="selected"</c:if>>처리결과선택</option>
							<%-- <option value="20000900" <c:if test="${param.mwStatusCont eq '20000900'}">selected="selected"</c:if>>접수중</option> --%>
							<option value="20000600" <c:if test="${param.mwStatusCont eq '20000600'}">selected="selected"</c:if>>미지정</option>
							<option value="20000700" <c:if test="${param.mwStatusCont eq '20000700'}">selected="selected"</c:if>>부서확인</option>
							<option value="20000800" <c:if test="${param.mwStatusCont eq '20000800'}">selected="selected"</c:if>>답변처리중</option>
							<option value="20000900" <c:if test="${param.mwStatusCont eq '20000900'}">selected="selected"</c:if>>답변완료</option>
							<option value="20001000" <c:if test="${param.mwStatusCont eq '20001000'}">selected="selected"</c:if>>처리종결</option>
							<option value="20001100" <c:if test="${param.mwStatusCont eq '20001100'}">selected="selected"</c:if>>답변게재</option>
							<option value="20001200" <c:if test="${param.mwStatusCont eq '20001200'}">selected="selected"</c:if>>취하</option>
							<option value="20001300" <c:if test="${param.mwStatusCont eq '20001300'}">selected="selected"</c:if>>자유게시판이전</option>
							<option value="20001400" <c:if test="${param.mwStatusCont eq '20001400'}">selected="selected"</c:if>>다수인 반복민원처리종결</option>
							<%-- <option value="20001200" <c:if test="${param.mwStatusCont eq '20001200'}">selected="selected"</c:if>>답변완료</option> --%>
							<%-- <option value="22000200" <c:if test="${param.mwStatusCont eq '22000200'}">selected="selected"</c:if>>답변없음</option> --%>
							<%-- <option value="5" <c:if test="${param.mwStatusCont eq '5'}">selected="selected"</c:if>>통합답변미완료</option> --%>
						</select>
					</td>
					<td><label for="schMbpERegDtSt">답변일자</label></td>
					<td>
						<span class="cal_in" ><input type="text" id="schMbpERegDtSt" name="schMbpERegDtSt" value="<c:out value="${param.schMbpERegDtSt}"/>" size="8" title="시작일"/></span>
						<select id="schMbpERegDtStTime" name="schMbpERegDtStTime" style="width: 80px;">
							<!-- <option value="">::선택::</option> -->
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="<c:out value="${timeSelect.colValue}"/>" <c:if test="${timeSelect.colValue eq param.schMbpERegDtStTime}">selected="selected"</c:if>><c:out value="${timeSelect.colValue}"/></option>
							</c:forEach>
							
						</select>시
						~
						<span class="cal_in" ><input type="text" id="schMbpERegDtEd" name="schMbpERegDtEd" value="<c:out value="${param.schMbpERegDtEd}"/>" size="8" title="종료일"/></span>
						<select id="schMbpERegDtEdTime" name="schMbpERegDtEdTime" style="width: 80px;">
							<!-- <option value="">::선택::</option> -->
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="<c:out value="${timeSelect.colValue}"/>" <c:if test="${timeSelect.colValue eq param.schMbpERegDtEdTime}">selected="selected"</c:if>><c:out value="${timeSelect.colValue}"/></option>
							</c:forEach>
						</select>시
					</td>
				</tr>
				<tr>
					<td><label for="mcDeptCd">부서</label></td>
					<td>
						<select id="mcDeptCd" name="mcDeptCd" style="width: 300px;">
							<option value="">답변부서 선택</option>
							<c:forEach items="${cdSelectBoxList}" var="cdSelectBoxList">
								<option value="<c:out value="${cdSelectBoxList.cdIdx}"/>" <c:if test="${param.mcDeptCd eq cdSelectBoxList.cdSubject}">selected="selected"</c:if> >
									<c:if test="${cdSelectBoxList.cdDepstep2 ne '00'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
									<c:if test="${cdSelectBoxList.cdDepstep3 ne '00'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
									<c:out value="${cdSelectBoxList.cdSubject}"/>
								</option>
							</c:forEach>
						</select>
					</td>
					<td><label for="searchField">검색</label></td>
					<td>
						<select id="searchField" name="searchField" style="width: 100px;">
							<%-- <option value="" <c:if test="${param.searchField eq ''}">selected="selected"</c:if>>선택</option> --%>
							<option value="writer" <c:if test="${param.searchField eq 'writer'}">selected="selected"</c:if>>작성자</option>
							<option value="title" <c:if test="${param.searchField eq 'title'}">selected="selected"</c:if>>제목</option>
							<option value="content" <c:if test="${param.searchField eq 'content'}">selected="selected"</c:if>>글내용</option>
							<option value="chargeName" <c:if test="${param.searchField eq 'chargeName'}">selected="selected"</c:if>>담당자</option>
						</select>
						<input type="text" id="searchKeyWord" name="searchKeyWord" value="<c:out value="${param.searchKeyWord}"/>" onKeyPress="if(event.keyCode==13) doSearchList();" />
						<a href="javascript:doSearchList();" class="btn2"><i class="fa fa-search"></i>검색</a>
					</td>
				</tr>
				</c:if>
				<!-- 일반관리자 -->
				<%-- <c:if test="${deptCd ne '05020000'}"> 2016_11_03일  --%>
				<c:if test="${RoleIdx ne 'RL00000022' && permCd ne '05010000'}">
				<tr>
					<th><label for="rowCnt">페이지수</label></th>
					<td>
						<select id="rowCnt" name="rowCnt" style="width: 100px;">
							<option value="15" <c:if test="${param.rowCnt eq '15'}">selected="selected"</c:if>>15</option>
							<option value="30" <c:if test="${param.rowCnt eq '30'}">selected="selected"</c:if>>30</option>
							<option value="50" <c:if test="${param.rowCnt eq '50'}">selected="selected"</c:if>>50</option>
							<option value="100" <c:if test="${param.rowCnt eq '100'}">selected="selected"</c:if>>100</option>
						</select>
					</td>
					<th><label for="mwStatusCont">처리결과</label></th>
					<td>
						<select id="mwStatusCont" name="mwStatusCont" style="width: 300px;">
							<option value="" <c:if test="${param.mwStatusCont eq ''}">selected="selected"</c:if>>처리결과선택</option>
							<option value="20000900" <c:if test="${param.mwStatusCont eq '20000900'}">selected="selected"</c:if>>접수중</option>
							<option value="20000800" <c:if test="${param.mwStatusCont eq '20000800'}">selected="selected"</c:if>>처리중</option>
							<option value="20000500" <c:if test="${param.mwStatusCont eq '20000500'}">selected="selected"</c:if>>답변완료</option>
							<option value="22000200" <c:if test="${param.mwStatusCont eq '22000200'}">selected="selected"</c:if>>답변없음</option>
							<option value="5" <c:if test="${param.mwStatusCont eq '5'}">selected="selected"</c:if>>통합답변미완료</option>
						</select>
					</td>
				</tr>
				<tr>
					<th><label for="schMbpERegDtSt">답변일자</label></th>
					<td>
						<span class="cal_in" ><input type="text" id="schMbpERegDtSt" name="schMbpERegDtSt" value="<c:out value="${param.schMbpERegDtSt}"/>" size="8" title="시작일"/></span>
						<select id="schMbpERegDtStTime" name="schMbpERegDtStTime" style="width: 50px;">
							<!-- <option value="">::선택::</option> -->
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="<c:out value="${timeSelect.colValue}"/>" <c:if test="${timeSelect.colValue eq param.schMbpERegDtStTime}">selected="selected"</c:if>><c:out value="${timeSelect.colValue}"/></option>
							</c:forEach>
							
						</select>시
						~
						<span class="cal_in" ><input type="text" id="schMbpERegDtEd" name="schMbpERegDtEd" value="<c:out value="${param.schMbpERegDtEd}"/>" size="8" title="종료일"/></span>
						<select id="schMbpERegDtEdTime" name="schMbpERegDtEdTime" style="width: 50px;">
							<!-- <option value="">::선택::</option> -->
							<c:forEach items="${timeSelect}" var="timeSelect" varStatus="status">
								<option value="<c:out value="${timeSelect.colValue}"/>" <c:if test="${timeSelect.colValue eq param.schMbpERegDtEdTime}">selected="selected"</c:if>><c:out value="${timeSelect.colValue}"/></option>
							</c:forEach>
						</select>시
					</td>
					<th><label for="searchField">검색</label></th>
					<td>
						<select id="searchField" name="searchField" style="width: 100px;">
							<option value="" <c:if test="${param.searchField eq ''}">selected="selected"</c:if>>선택</option>
							<option value="writer" <c:if test="${param.searchField eq 'writer'}">selected="selected"</c:if>>작성자</option>
							<option value="title" <c:if test="${param.searchField eq 'title'}">selected="selected"</c:if>>제목</option>
							<option value="content" <c:if test="${param.searchField eq 'content'}">selected="selected"</c:if>>글내용</option>
							<option value="chargeName" <c:if test="${param.searchField eq 'chargeName'}">selected="selected"</c:if>>담당자</option>
						</select>
						<input type="text" id="searchKeyWord" name="searchKeyWord" value="<c:out value="${param.searchKeyWord}"/>" />
						<a href="javascript:doSearchList();" class="btn2 btn_input"><i class="fa fa-search"></i>검색</a>
					</td>
				</tr>
				</c:if>
				
			</table>
			
			<div style="width: 50%; height:30px; text-align:left; float:left;">
				총 <c:out value="${totCnt}"/>건
			</div>
			
			<table summary="" class="list1" width="100%">
				<caption></caption>
				<colgroup>
					<%--
					<col width="4%" />
					--%>
					<col width="3%" />
					<col width="5%" />
					<col width="*" />
					<col width="6%" />
					<col width="5%" />
					<col width="8%" />
					<col width="5%" />
					<col width="12%" />
					<col width="9%" />
					<col width="7%" />
				</colgroup>
				<thead>
					<tr>
						<%-- 
						<th><input type="checkbox" id="chkAll" name="chkAll" onclick="checkAll();"/></th>
						--%>
						<th>no</th>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>공개여부</th>
						<th>등록일</th>
						<th>담당부서</th>
						<th>담당자</th>
						<th>답변기한</th>
						<th>처리결과</th>
					</tr>
				</thead>
				<tbody>			
					<c:forEach items="${gubaraSelectList}" var="gubaraSelectList" varStatus="status">			
					<tr>
						<%-- 
						<td>
							<c:if test="${gubaraSelectList.mwStatusCont eq '20000600'}">
							<input type="checkbox" name="searchBcIdx" value="<c:out value="${gubaraSelectList.bcIdx}"/>" />
							</c:if>
							<c:if test="${gubaraSelectList.mwStatusCont ne '20000600'}">&nbsp;</c:if>
						</td>
						--%>
						<td><c:out value="${gubaraSelectList.rn}"/></td>
						<td><c:out value="${gubaraSelectList.bcIdx}"/></td>
						<td>
							<a href="javascript:doDetail('<c:out value="${gubaraSelectList.cbIdx}"/>','<c:out value="${gubaraSelectList.bcIdx}"/>');">
							<c:out value="${gubaraSelectList.subCont}"/>
							</a>
						</td>
						<td><c:out value="${gubaraSelectList.nameCont}"/></td>
						<td>
							<c:if test="${gubaraSelectList.openYnCont eq 'AD_Y'}">
								<span class="adno-open">비공개</span>
							</c:if>
							<c:if test="${gubaraSelectList.openYnCont eq '21000200'}">
							<span class="open">공개</span>
							</c:if>
							<c:if test="${gubaraSelectList.openYnCont eq '21000100'}">
								<span class="no-open">비공개</span>
							</c:if>
						</td>
						<td><c:out value="${gubaraSelectList.regDt}"/></td>
						<td>
							<c:set var="cdSubjectList" value="${fn:split(gubaraSelectList.cdSubject,',')}" />
							<c:forEach items="${cdSubjectList}" var="cdSubject">
								<c:forEach items="${cdSelectBoxList}" var="cdSelectBoxList">
									<c:if test="${cdSubject eq cdSelectBoxList.cdIdx}">
										<c:out value="${cdSelectBoxList.cdSubject}"/><br/>
									</c:if>
								</c:forEach>
							</c:forEach>
						</td>
						<td>
							<c:forEach items="${subList}" var="subList">
							<c:if test="${gubaraSelectList.bcIdx eq subList.bcIdx}">
							<c:choose>
							<c:when test="${subList.mcReplyer eq null || subList.mcReplyer eq ''}">
							<span style="color: red;">미지정<br/></span>
							</c:when>
							<c:otherwise>
							<c:out value="${subList.mcReplyer}"/><br/>
							</c:otherwise>
							</c:choose>
							
							</c:if>
							</c:forEach>
						</td>
						<td>
							<c:if test="${gubaraSelectList.mwNoReplyYn eq 'Y' || gubaraSelectList.ansYnCont eq '22000200'}">-</c:if>
							<c:if test="${gubaraSelectList.mwNoReplyYn eq 'N' && gubaraSelectList.ansYnCont eq '22000100'}">
							<c:forEach items="${subList}" var="subList">
								<c:set var="tempYn" value="${subList.tempYn }" />
								<fmt:formatDate var="tday" value="${toDay}" pattern="yyyy.MM.dd" />
								<c:if test="${gubaraSelectList.bcIdx eq subList.bcIdx}"><span <c:if test="${(tempYn eq 'N' || tempYn eq null) && subList.ymd2 <= tday }">style='color:red;'</c:if>><c:out value="${subList.ymd2}"/></span><br/></c:if>
							</c:forEach>
							</c:if>
						</td>
						<td>
							<c:choose>
								<%-- <c:when test="${gubaraSelectList.mwNoReplyYn eq 'Y'}"><span class="status-move">답변비대상</span></c:when>        
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000100'}"><span class="status-move">삭제</span></c:when>        
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000200'}"><span class="status-move">비공개처리</span></c:when>  
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000300'}"><span class="status-move">이관</span></c:when>        
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000400'}"><span class="status-move">답변없음</span></c:when>    
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000500'}"><span class="status-end">답변완료</span></c:when>     
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000600'}"><span class="status-end2">부서답변완료</span></c:when> 
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000600'}"><span class="status-end2">부서답변완료</span></c:when>
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000700'}"><span class="adno-open">중간답변</span></c:when>      
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000800'}"><span class="status-ing">처리중</span></c:when>       
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000900'}"><span class="status-re">접수중</span></c:when>          
								<c:when test="${gubaraSelectList.mwStatusCont eq '22000200'}"><span class="status-move">답변요청없음</span></c:when>--%>
								
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000600'}"><span class="status-re">미지정</span></c:when>
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000700'}"><span class="status-move">부서확인</span></c:when>
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000800'}"><span class="status-move">답변처리중</span></c:when>
								<c:when test="${gubaraSelectList.mwStatusCont eq '20000900'}"><span class="status-end">답변완료</span></c:when>
								<c:when test="${gubaraSelectList.mwStatusCont eq '20001000'}"><span class="status-end2">처리종결</span></c:when>
								<c:when test="${gubaraSelectList.mwStatusCont eq '20001100'}"><span class="status-move">답변게재</span></c:when>
								<c:when test="${gubaraSelectList.mwStatusCont eq '20001200'}"><span class="status-move">취하</span></c:when>
								<c:when test="${gubaraSelectList.mwStatusCont eq '20001300'}"><span class="status-move">자유게시판이전</span></c:when>
								<c:when test="${gubaraSelectList.mwStatusCont eq '20001400'}"><span class="status-move">반복처리종결</span></c:when>
								       
							</c:choose>
							<%-- <c:out value="${gubaraSelectList.mwStatusCont}"/> --%>
						</td>
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
			<div>
				<div class="f_left">
					<%--
					<c:if test="${RoleIdx eq 'RL00000022' || permCd eq '05010000'}">
					<a href="javascript:openDepartSelectForm();" class="btn2">일괄부서지정</a>
					</c:if>
					--%>
				</div>
				<div class="btn_zone">
					<a href="javascript:doExcelForm();" class="btn2">EXCEL로 저장</a>
					<!-- <a href="javascript:/cms/board_manager/council_bizworkslist.jsp" class="btn2">단위업무명관리</a> -->
				</div>
			</div>
				<!-- //버튼 -->
		</div>
		</div>
		<!-- //컨텐츠 영역 -->
</form:form>
<div id="divDepartSelect" title="부서 일괄 지정">
	<table summary="부서 일괄 지정" class="view">
		<colgroup>
			<col width="30%"/>
			<col width="70%"/>
		</colgroup>
		<tbody>
			<tr>
				<th rowspan="3">부서선택</th>
				<td>
					<select id="cdSelectBoxList1" name="cdSelectBoxList">
						<option value="">민원답변부서를 선택하세요</option>
						<c:forEach var="cdSelectBoxInfo" items="${cdSelectBoxList}">
							<option value="<c:out value="${cdSelectBoxInfo.cdIdx}"/>">
								<c:if test="${cdSelectBoxInfo.cdDepstep2 ne '00'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
								<c:out value="${cdSelectBoxInfo.cdSubject}"/>
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<select id="cdSelectBoxList2" name="cdSelectBoxList">
						<option value="">추가답변부서를 선택하세요</option>
						<c:forEach var="cdSelectBoxInfo" items="${cdSelectBoxList}">
							<option value="<c:out value="${cdSelectBoxInfo.cdIdx}"/>">
								<c:if test="${cdSelectBoxInfo.cdDepstep2 ne '00'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
								<c:out value="${cdSelectBoxInfo.cdSubject}"/>
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<select id="cdSelectBoxList1" name="cdSelectBoxList">
						<option value="">추가답변부서를 선택하세요</option>
						<c:forEach var="cdSelectBoxInfo" items="${cdSelectBoxList}">
							<option value="<c:out value="${cdSelectBoxInfo.cdIdx}"/>">
								<c:if test="${cdSelectBoxInfo.cdDepstep2 ne '00'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
								<c:out value="${cdSelectBoxInfo.cdSubject}"/>
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
