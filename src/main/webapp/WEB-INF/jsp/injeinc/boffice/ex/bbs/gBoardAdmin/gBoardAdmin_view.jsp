<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Basic Board List</title>
<script type="text/javascript">

/* 이전글/다음글 */
function doPrevNext(cbIdx, bcIdx, gbn){
	if(bcIdx == null || bcIdx == ''){
		if(gbn == 'P'){
			alert("이전글이 없습니다.");
			return;
		}else if(gbn == 'N'){
			alert("다음글이 없습니다.");
			return;
		}
	}
	
	document.GBoardAdminVo.target = "_self";
	document.GBoardAdminVo.cbIdx.value = cbIdx;
	document.GBoardAdminVo.bcIdx.value = bcIdx;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do'/>";
	document.GBoardAdminVo.submit();
}

/* 목록 */
function doList(){
	document.GBoardAdminVo.target = "_self";
	document.GBoardAdminVo.mcDeptCd.value = "";
	location.href = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_list.do'/>";

	//document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_list.do'/>";
	//document.GBoardAdminVo.submit();
}

/* 수정/삭제 */
function doMod(cbIdx, bcIdx, modGbn){
	
	var msg = "";
	
	if( modGbn == 'D' ){
		msg = "정말 삭제 하시겠습니까?\n[확인]을 누르시면 삭제됩니다.";
		if(!confirm(msg)){
			return;
		}
	}
	
	document.GBoardAdminVo.target = "_self";
	document.GBoardAdminVo.modGbn.value = modGbn;
	document.GBoardAdminVo.cbIdx.value = cbIdx;
	document.GBoardAdminVo.bcIdx.value = bcIdx;

	if(modGbn == 'U'){
		document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_modForm.do'/>";
	}else if(modGbn == 'D'){
		document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_mod.do'/>";	
	}
	document.GBoardAdminVo.submit();
	
}

//자유게시판,보건소민원 이동
function doBoardTrans(transCbIdx, openYn){
	var msg = "";
	if(transCbIdx == '278'){
		msg = "자유게시판";
	}else if(transCbIdx == '87'){
		msg = "보건소민원";
	}
	
	if(openYn == '21000100'){
		if(!confirm("비공개 게시물은 "+msg+" 이동시  노출되지 않아\n2차 민원 발생 요지가 될 수 있습니다.\n"+msg+"(으)로 이동하시겠습니까?")){
			return;
		}
	}
	document.GBoardAdminVo.target = "_self";
	document.GBoardAdminVo.transCbIdx.value = transCbIdx;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoard_boardTrans.do'/>";
	document.GBoardAdminVo.submit();
}

//답변비대상민원
function doNoAnswer(cbIdx, bcIdx){
	
	if(!confirm("답변미대상민원으로 강제 처리 하시겠습니까?")){
		return;
	}
	 $.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoard_NoAnswerAx.do'/>",
		data : {"cbIdx" : cbIdx
			   , "bcIdx" : bcIdx},
		dataType : "json",
		success:function(object){
			alert("답변미대상민원 처리 되었습니다.");
			document.GBoardAdminVo.submit();
			//location.reload();
		 },
        error: function(xhr,status,error){
        	alert(status);
        }
	});
	
}

// 담당부서지정
function doOpenPop(){
	var url = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardDep_Pop.do' />";
	var width="500";
	var height="200";
	var form = document.GBoardAdminVo;
	doCommonPop(form,'popup',url,width,height);
}

//답변 연기
function doAnswerDelay(cbIdx, bcIdx, mcIdx){
	var url = "<c:url value='/boffice_nodeco/ex/bbs/gBoardAdmin/gBoardAnswerDelay_Pop.do' />";
	var width="350";
	var height="250";
	var form = document.GBoardAdminVo;
	
	document.GBoardAdminVo.cbIdx.value = cbIdx;
	document.GBoardAdminVo.bcIdx.value = bcIdx;
	document.GBoardAdminVo.mcIdx.value = mcIdx;
	
	doCommonPop(form,'popup',url,width,height);
}

//통합답변 입력/수정
function doIntergRespPop(cbIdx, bcIdx, mcIdx, gbnVal){
	//alert(cbIdx+" "+bcIdx+" "+mcIdx+" "+gbnVal);
	var url = "<c:url value='/boffice_nodeco/ex/bbs/gBoardAdmin/gBoardIntergResp_Pop.do' />";
	var width="700";
	var height="380";
	var form = document.GBoardAdminVo;
	document.GBoardAdminVo.cbIdx.value = cbIdx;
	document.GBoardAdminVo.bcIdx.value = bcIdx;
	document.GBoardAdminVo.mcIdx.value = mcIdx;
	document.GBoardAdminVo.gbnVal.value = gbnVal;
	doCommonPop(form,'popup',url,width,height);
}

// 담당자명 저장
function doDepReplyer(cbIdx, bcIdx, mcIdx, mcReplyer){
	
	var url = "<c:url value='/boffice_nodeco/ex/bbs/gBoardAdmin/gBoardDepReplyer_RegPop.do' />";
	var width="510";
	var height="310";
	var form = document.GBoardAdminVo;
	document.GBoardAdminVo.cbIdx.value = cbIdx;
	document.GBoardAdminVo.bcIdx.value = bcIdx;
	document.GBoardAdminVo.mcIdx.value = mcIdx;
	document.GBoardAdminVo.mcReplyer.value = mcReplyer;
	doCommonPop(form,'popup',url,width,height);
	
}

// 부서삭제
function doDepDel(cbIdx, bcIdx, mcIdx){
	if(!confirm("부서지정을 삭제하시겠습니까?\n[확인]을 누르시면 삭제됩니다.")){
		return;
	}
	var param = "cbIdx="+cbIdx+"&bcIdx="+bcIdx+"&mcIdx="+mcIdx;
	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardDep_Del.do'/>"
		, data : param
		, type : "POST"
		, dataType: "json"
		, async : false
		, success: function(data) {
			alert("답변이 삭제되었습니다.");
			//document.GBoardAdminVo.submit();
			location.reload();
 		},error : function(code, msg, error) {
 			alert(status);
 		}
			
	});
}

// 부서변경
function doDepUpdate(cbIdx, bcIdx, mcIdx, mcDeptCd){
	var url = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardUpdateDep_Pop.do' />";
	var width="500";
	var height="200";
	var form = document.GBoardAdminVo;
	document.GBoardAdminVo.cbIdx.value = cbIdx;
	document.GBoardAdminVo.bcIdx.value = bcIdx;
	document.GBoardAdminVo.mcIdx.value = mcIdx;
	document.GBoardAdminVo.mcDeptCd.value = mcDeptCd;
	doCommonPop(form,'popup',url,width,height);
}

/* 파일 다운로드 */
function doFileDownLoad(fileNo){
	document.GBoardAdminVo.target = "_self";
	document.GBoardAdminVo.fileNo.value = fileNo;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_fileDownload.do'/>";
	document.GBoardAdminVo.submit();
}

/* 이관 */
function doTrans(cbIdx,bcIdx){
	$.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/boardAdmin/TransAx.do'/>",
		data : {"cbIdx" : cbIdx
			   , "bcIdx" : bcIdx
			   , "transCbIdx" : "3"
			   },
		dataType : "json",
		success:function(object){
			alert("이관 되었습니다.");
			document.GBoardAdminVo.submit();
			//location.reload();
		 },
	    error: function(xhr,status,error){
	    	alert(status);
	    }
	});
}

/* 복사 */
function doCopy(){
	var url = "<c:url value='/boffice_nodeco/ex/bbs/gBoardAdmin/gBoardCopy_Pop.do' />";
	var width="350";
	var height="250";
	var form = document.GBoardAdminVo;
	doCommonPop(form,'popup',url,width,height);
}

/* 인쇄 */
function doPrint(gbnVal){
	//window.print();
	
	document.GBoardAdminVo.gbnVal.value = gbnVal;
	
	var url = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_viewPop.do'/>";
	var width="800";
	var height="700";
	var form = document.GBoardAdminVo;
	doCommonPop(form,'popup',url,width,height);
	
// 	document.GBoardAdminVo.gbnVal.value = gbnVal;
// 	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do'/>";
// 	document.GBoardAdminVo.target = "_self";
// 	document.GBoardAdminVo.submit();
	
}

//인쇄하기
function doGboardPrint(){
	//window.print();
	
	var url = "/common/print.jsp";
	var width="690";
	var height="700";
	window.open(url, "인쇄페이지", "width="+width+",height="+height+",scrollbars=yes");
	
// 	document.GBoardAdminVo.gbnVal.value = gbnVal;
// 	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do'/>";
// 	document.GBoardAdminVo.target = "_self";
// 	document.GBoardAdminVo.submit();
	
}

// 1,2 차대화 저장
function doMcContent(cbIdx, bcIdx, mcIdx, gbn, mod){
	
	if(mod == 'U'){
		if(gbn == 'B'){
			$("#McBContent_1_"+mcIdx).css("display", "none");
			$("#McBContent_2_"+mcIdx).css("display", "");
		}else{
			$("#McAContent_1_"+mcIdx).css("display", "none")
			$("#McAContent_2_"+mcIdx).css("display", "");
		}
	}else{
		
		var mcBSender = $("#mcBSender_"+mcIdx).val();
		var mcBTxt = $("#mcBTxt_"+mcIdx).val();
		var mcASender = $("#mcASender_"+mcIdx).val();
		var mcATxt = $("#mcATxt_"+mcIdx).val();
		
		var msg = "";
		if(gbn == 'B'){
			if( cm_is_empty(mcBSender) ){
				alert("1차 발신자를 입력해 주세요.");
				$("#mcBSender_"+mcIdx).focus;
				return;
			}else if( cm_is_empty(mcBTxt) ){
				alert("1차 대화내역을 입력해 주세요.");
				$("#mcBTxt_"+mcIdx).focus;
				return;
			}
		}else{
			if( cm_is_empty(mcASender) ){
				alert("2차 발신자를 입력해 주세요.");
				$("#mcASender_"+mcIdx).focus;
				return;
			}else if( cm_is_empty(mcATxt) ){
				alert("2차 대화내역을 입력해 주세요.");
				$("#mcATxt_"+mcIdx).focus;
				return;
			}	
		}
		
		if(gbn == 'B'){
			msg = "1차";
		}else{
			msg == "2차";
		}
		
		if(!confirm(msg + "저장하시겠습니까?")){
			return;
		}
		
		document.GBoardAdminVo.cbIdx.value = cbIdx;
		document.GBoardAdminVo.bcIdx.value = bcIdx;
		document.GBoardAdminVo.mcIdx.value = mcIdx;
		document.GBoardAdminVo.gbnVal.value = gbn;
		
		$.ajax({
			url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardMcContent_Reg.do'/>"
			, data : $("#GBoardAdminVo").serialize()
			, type : "POST"
			, dataType: "json"
			, async : false
			, success: function(data) {
				alert(msg + "대화가 저장되었습니다.");
				document.GBoardAdminVo.submit();
				location.reload();
				//location.reload();
	 		},error : function(code, msg, error) {
	 			alert(status);
	 		}
				
		});
	}
	
}

// 답변초기화
function doReplClear(cbIdx, bcIdx, mcIdx){
	if(!confirm("답변을 초기화 하시겠습니까?\n[확인]을 누르시면 삭제됩니다.")){
		return;
	}
	
	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardRepl_Del.do'/>"
		, data : "cbIdx="+cbIdx+"&bcIdx="+bcIdx+"&mcIdx="+mcIdx
		, type : "POST"
		, dataType: "json"
		, async : false
		, success: function(data) {
			alert("답변이 초기화되었습니다.");
			//document.GBoardAdminVo.submit();
			location.reload();
 		},error : function(code, msg, error) {
 			alert(status);
 		}
			
	});
}


// 답변등록
function doDepReplInsert(cbIdx, bcIdx, mcIdx, mcDeptCd, modGbn){
	document.GBoardAdminVo.target = "_self";
	document.GBoardAdminVo.cbIdx.value = cbIdx;
	document.GBoardAdminVo.bcIdx.value = bcIdx;
	document.GBoardAdminVo.mcIdx.value = mcIdx;
	document.GBoardAdminVo.mcDeptCd.value = mcDeptCd;
	document.GBoardAdminVo.modGbn.value = modGbn;
	document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardRepl_Reg.do'/>";
	document.GBoardAdminVo.submit();
}

// function doIntergResp(){
// 	var url = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardIntergResp_Pop.do' />";
// 	var width="500";
// 	var height="200";
// 	var form = document.GBoardAdminVo;
// 	document.GBoardAdminVo.cbIdx.value = cbIdx;
// 	document.GBoardAdminVo.bcIdx.value = bcIdx;
// 	document.GBoardAdminVo.mcIdx.value = mcIdx;
// 	document.GBoardAdminVo.mcDeptCd.value = mcDeptCd;
// 	doCommonPop(form,'popup',url,width,height);
// }


//구바라 처리상태
function doGboardHandling(cbIdx, bcIdx, mcIdx, mwStatusCont){

	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_status.do'/>"
		, data : "cbIdx="+cbIdx+"&bcIdx="+bcIdx+"&mcIdx="+mcIdx+"&mwStatusCont="+$("input:radio[name='mwStatusCont']:checked").val()
		, type : "POST"
		, dataType: "json"
		, async : false
		, success: function(data) {
			alert("상태변경되었습니다.");
			$("#GBoardAdminVo").submit();
 		},error : function(code, msg, error) {
 			alert(status);
 		}

			
	});
}

function changeAnsDeadlineDt(cbIdx, bcIdx){

	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/changeAnsDeadlineDt.do'/>"
		, data : "cbIdx="+cbIdx+"&bcIdx="+bcIdx+"&ansDeadlineDt="+$("#ansDeadlineDt").val()
		, type : "POST"
		, dataType: "json"
		, async : false
		, success: function(data) {
			alert("답변처리일자가 변경되었습니다.");
			$("#GBoardAdminVo").submit();
 		},error : function(code, msg, error) {
 			alert(status);
 		}

			
	});
}

function doDeptConfirm(cbIdx, bcIdx){
	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_status.do'/>"
		, data : "cbIdx="+cbIdx+"&bcIdx="+bcIdx+"&mwStatusCont=20000800"
		, type : "POST"
		, dataType: "json"
		, async : false
		, success: function(data) {
			alert("부서확인이 되었습니다.");
			$("#GBoardAdminVo").submit();
 		},error : function(code, msg, error) {
 			alert(status);
 		}

			
	});	
}

/* 부서지정 */
function doSave(cbIdx, bcIdx){
	
	var depCbIdx = $("#cdSelectBoxList_1").val();
	
	if( cm_is_empty(depCbIdx) ){
		alert("담당부서를 지정해 주세요.");
		$("#cdSelectBoxList_1").focus;
		return;
	}
	
	$("#cbIdx").val(cbIdx);
	$("#bcIdx").val(bcIdx);
	
	$.ajax({
		url : "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardDep_RegPop.do'/>"
		//, data : param
		, data : $("#GBoardAdminVo").serialize()
		, type : "POST"
		, dataType : "json"
		, success : function(data) {
			alert("담당부서가 지정되었습니다.");
			$("#GBoardAdminVo").submit();
 		},error : function(code, msg, error) {
 			alert(status);
 		}
			
	});
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	$("#mcReplyerU").hide();
// 	$("#McAContent_2").hide();

	$("input[id=ansDeadlineDt], input[id=ansDeadlineDt]").datepicker();
	$("input[id=ansDeadlineDt], input[id=ansDeadlineDt]").mask("9999-99-99");
    $("input[id=ansDeadlineDt], input[id=ansDeadlineDt]").datepicker( "option", "dateFormat", "yy-mm-dd" );
});

</script>

<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
</style>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="GBoardAdminVo" name="GBoardAdminVo" method="post" >
<form:hidden path="cbIdx" />
<form:hidden path="bcIdx" />
<form:hidden path="mcIdx" />
<form:hidden path="gbnVal" />
<form:hidden path="mcDeptCd" />
<form:hidden path="mcReplyer" />
<form:hidden path="modGbn" />
<form:hidden path="transCbIdx" />


<input type="hidden" id="openYnCont" name="openYnCont" value="<c:out value="${detailMap.openYnCont}"/>" />
<input type="hidden" id="mode" name="mode" value="<c:out value="${param.mode}"/>" />

<c:set var="chiefName" value=""/>
<div id="contents">
	<div class="title1">민원 상세보기</div>
	<table summary="" class="write">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="35%" />
		</colgroup>
		
		<tbody>
			<tr>
				<th>제목</th>
				<td colspan="3"><c:out value="${detailMap.subCont}"/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><c:out value="${detailMap.nameCont}"/></td>
				<th>이메일</th>
				<td><c:out value="${detailMap.emailCont}"/></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>
				<c:out value="${detailMap.telCont1}"/> - <c:out value="${detailMap.telCont2}"/> - <c:out value="${detailMap.telCont3}"/>
				</td>
				<th>휴대폰</th>
				<td>
				<c:out value="${detailMap.phoneCont1}"/> - <c:out value="${detailMap.phoneCont2}"/> - <c:out value="${detailMap.phoneCont3}"/>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td colspan="3">
					<c:if test="${detailMap.addrCont1 ne '00000' }"><c:out value="${detailMap.addrCont1}"/>&nbsp;</c:if>
					<c:out value="${detailMap.addrCont2}"/>&nbsp;<c:out value="${detailMap.addrCont3}"/>
				</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td><c:out value="${detailMap.regDt}"/></td>
				<th>수정일</th>
				<td><c:out value="${detailMap.modDt}"/></td>
			</tr>
			<tr>
				<th>요청사항</th>
				<td colspan="3"><c:out value="${detailMap.ext1}"/></td>			
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><c:out value="${cmm:outClobCont(detailMap.clobCont)}" escapeXml="false"/></td>			
			</tr>
			<c:if test="${detailFileList ne null || detailFileList ne ''}">
			<tr>
			<th>첨부파일</th>
			<td colspan="3">
				<c:forEach items="${detailFileList}" var="detailFileList">
				<c:if test="${detailFileList.mcIdx eq null}">
				<a href="/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_fileDownload.do?bcIdx=<c:out value="${detailFileList.bcIdx }" />&cbIdx=<c:out value="${detailFileList.cbIdx }" />&fileNo=<c:out value="${detailFileList.fileNo }" />" class="file">
				파일명 : <c:out value="${detailFileList.orignlFileNm}" />&nbsp;&nbsp;&nbsp;[ <fmt:formatNumber value="${detailFileList.fileSize}"></fmt:formatNumber> byte ]
				</a>
				<%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${detailFileList.fileStreCours }' />&filename=<c:out value='${detailFileList.streFileNm }' />" title="새창" class="view-direct">바로보기</a> --%>
				<br/>
				</c:if>
				</c:forEach>
			</td>
		</tr>
		</c:if>
		<c:choose>
			<c:when test="${RoleIdx eq 'RL00000022' || permCd eq '05010000'}">
				<c:if test="${detailMap.mwStatusCont ne '20001300'}">
					<tr>
						<th align="center"><h3><label for="cdSelectBoxList_1">담당부서 지정</label></h3></th>
						<td colspan="3">
							<c:set var="cdSubjectList" value="${fn:split(detailMap.cdSubject,',')}" />
							<c:forEach var="cdSubject" begin="1" end="5" varStatus="status">
								<c:set var="selectCdSubject" value="" />
								<c:if test="${fn:length(cdSubjectList) >= status.count}">
									<c:set var="selectCdSubject" value="${cdSubjectList[status.count -1]}" />
								</c:if>
								<select id="cdSelectBoxList_${status.count}" name="cdSelectBoxList" style="width: 150px;" onchange="chgholiday();">
									<option value="">민원 답변부서 선택</option>
									<c:forEach items="${cdSelectBoxList}" var="cdSelectBoxList">
										<option value="<c:out value="${cdSelectBoxList.cdIdx}"/>" <c:if test="${cdSelectBoxList.cdIdx eq selectCdSubject}">selected="selected"</c:if>>
											<c:out value="${cdSelectBoxList.cdSubject}"/>
										</option>
									</c:forEach>
								</select>
							</c:forEach>
							<a href="javascript:doSave('<c:out value="${gBoardAdminVo.cbIdx }"/>', '<c:out value="${gBoardAdminVo.bcIdx}"/>');" class="btn2">부서지정</a>
						</td>
					</tr>
				 </c:if>
				<tr>
					<th >답변처리일자</th>
					<td colspan="3">
						<c:choose>
							<c:when test="${detailMap.mwStatusCont ne '20001300' && detailMap.mwStatusCont ne '20001000' && detailMap.mwStatusCont ne '20000900'}">
								<input type="text" name="ansDeadlineDt" id="ansDeadlineDt" value="<c:out value="${detailMap.ansDeadlineDt }"/>"/>
								<a href="javascript:changeAnsDeadlineDt('<c:out value="${gBoardAdminVo.cbIdx }"/>', '<c:out value="${gBoardAdminVo.bcIdx}"/>');" class="btn2">답변일자변경</a>
							</c:when>
							<c:otherwise>
								<c:out value="${detailMap.ansDeadlineDt }"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<th >답변처리상태</th>
					<td colspan="3">
						<input type="radio" id="mwStatusCont1" name="mwStatusCont" value="20000600" title="미지정" class="btn-cc-r" <c:if test="${detailMap.mwStatusCont eq '20000600' }">checked="checked"</c:if>/><label for="">미지정</label>&nbsp;&nbsp; 
						<input type="radio" id="mwStatusCont2" name="mwStatusCont" value="20000700" title="부서확인" class="btn-cc-r" <c:if test="${detailMap.mwStatusCont eq '20000700' }">checked="checked"</c:if>/><label for="">부서확인</label>&nbsp;&nbsp; 
						<input type="radio" id="mwStatusCont3" name="mwStatusCont" value="20000800" title="답변처리중" class="btn-cc-r" <c:if test="${detailMap.mwStatusCont eq '20000800' }">checked="checked"</c:if>/><label for="">답변처리중</label> 
						<input type="radio" id="mwStatusCont7" name="mwStatusCont" value="20001100" title="답변게재" class="btn-cc-r" <c:if test="${detailMap.mwStatusCont eq '20001100' }">checked="checked"</c:if>/><label for="">답변게재</label> 
						<input type="radio" id="mwStatusCont4" name="mwStatusCont" value="20000900" title="답변완료" class="btn-cc-r" <c:if test="${detailMap.mwStatusCont eq '20000900' }">checked="checked"</c:if>/><label for="">답변완료</label> 
						<input type="radio" id="mwStatusCont5" name="mwStatusCont" value="20001000" title="처리종결" class="btn-cc-r" <c:if test="${detailMap.mwStatusCont eq '20001000' }">checked="checked"</c:if>/><label for="">처리종결</label> 
						<input type="radio" id="mwStatusCont6" name="mwStatusCont" value="20001300" title="자유게시판이전" class="btn-cc-r" <c:if test="${detailMap.mwStatusCont eq '20001300' }">checked="checked"</c:if>/><label for="">자유게시판이전</label>
						<input type="radio" id="mwStatusCont6" name="mwStatusCont" value="20001400" title="다수인 반복민원처리종결" class="btn-cc-r" <c:if test="${detailMap.mwStatusCont eq '20001400' }">checked="checked"</c:if>/><label for="">다수인 반복민원처리종결</label>
						<%-- 민원인만 취하가능
						<input type="radio" id="mwStatusCont8" name="mwStatusCont" value="20001200" title="답변게재" class="btn-cc-r" <c:if test="${detailMap.mwStatusCont eq '20001200' }">checked="checked"</c:if>/><label for="">취하</label>
						 --%> 
						<a href="javascript:doGboardHandling('<c:out value="${gBoardAdminVo.cbIdx}"/>','<c:out value="${gBoardAdminVo.bcIdx}"/>','<c:out value="${gBoardAdminVo.mcIdx}"/>','<c:out value="${gBoardAdminVo.mwStatusCont}"/>');" class="btn1">처리상황변경</a>
					</td>
				</tr>	
			</c:when>
			<c:otherwise>
				<tr>
					<th align="center"><h3><label for="cdSelectBoxList_1">담당부서</label></h3></th>
					<td colspan="3">
						<c:set var="cdSubjectList" value="${fn:split(detailMap.cdSubject,',')}" />
						<c:forEach items="${cdSubjectList}" var="cdSubject" varStatus="status">
							<c:forEach items="${cdSelectBoxList}" var="cdSelectBoxList">
								<c:if test="${cdSubject eq cdSelectBoxList.cdIdx}">
									<c:if test="${status.count != 1}">,</c:if>
									<c:out value="${cdSelectBoxList.cdSubject}"/>
								</c:if>
							</c:forEach>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th >답변처리일자</th>
					<td><c:out value="${detailMap.ansDeadlineDt }"/></td>
					<th >답변처리상태</th>
					<td>
						<c:if test="${detailMap.mwStatusCont eq '20000600' }">미지정</c:if>
						<c:if test="${detailMap.mwStatusCont eq '20000700' }">부서확인</c:if>
						<c:if test="${detailMap.mwStatusCont eq '20000800' }">답변처리중</c:if>
						<c:if test="${detailMap.mwStatusCont eq '20001100' }">답변게재</c:if>
						<c:if test="${detailMap.mwStatusCont eq '20000900' }">답변완료</c:if>
						<c:if test="${detailMap.mwStatusCont eq '20001000' }">처리종결</c:if>
						<c:if test="${detailMap.mwStatusCont eq '20001300' }">자유게시판이전</c:if>
						<c:if test="${detailMap.mwStatusCont eq '20001400' }">다수인 반복민원처리종결</c:if>
					</td>
				</tr>	
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>

	<div class="btn_zone">
		<!-- 이전다음글 -->	
		<%-- <a href="javascript:doPrevNext('<c:out value="${gBoardAdminVo.cbIdx }"/>', '<c:out value="${detailPreNextMap.PREV_IDX }"/>','P');" class="btn2">이전글</a>
		<a href="javascript:doPrevNext('<c:out value="${gBoardAdminVo.cbIdx }"/>', '<c:out value="${detailPreNextMap.NEXT_IDX }"/>','N');" class="btn2">다음글</a> --%>
		<!-- //이전다음글 -->	
		<div class="left">
			<a href="javascript:doGboardPrint();" class="btn2">인쇄</a>
			<c:if test="${RoleIdx eq 'RL00000022' || permCd eq '05010000'}">
			<%-- 	<a href="javascript:doMod('<c:out value="${gBoardAdminVo.cbIdx }"/>', '<c:out value="${gBoardAdminVo.bcIdx}"/>', 'U');" class="btn2">수정</a> --%>
				<a href="javascript:doMod('<c:out value="${gBoardAdminVo.cbIdx }"/>', '<c:out value="${gBoardAdminVo.bcIdx}"/>', 'D');" class="btn2">삭제</a>
			</c:if>
			<a href="javascript:doList();" class="btn2">목록</a>
		</div>
		<c:choose>
		<c:when test="${RoleIdx eq 'RL00000022' || permCd eq '05010000'}">
			<!-- <a href="javascript:doCopy();" class="btn1">복사</a> -->
			<%-- <a href="javascript:doBoardTrans('87','<c:out value="${detailMap.mwOpenynCont}"/>');" class="btn1">보건소민원이동</a> --%>
			<%-- <c:if test="${detailMap.mwNoReplyYn eq 'N'}">
			<a href="javascript:doNoAnswer('<c:out value="${gBoardAdminVo.cbIdx }" />', '<c:out value="${gBoardAdminVo.bcIdx }" />');" class="btn1">답변비대상민원</a>
			</c:if> --%>
		</c:when>
		<c:when test="${fn:indexOf(detailMap.cdSubject, CMSUSER.deptCd) != -1}">
			<c:if test="${detailMap.mwStatusCont eq '20000700' }">
				<!--  부서확인 -->
				<span class="red">※ 부서확인 후 답변등록이 가능합니다.</span>
				<a href="javascript:doDeptConfirm('<c:out value="${gBoardAdminVo.cbIdx}"/>','<c:out value="${gBoardAdminVo.bcIdx}"/>');" class="btn2">부서확인</a>
			</c:if>
			<c:if test="${detailMap.mwStatusCont eq '20001100' || detailMap.mwStatusCont eq '20000800' || detailMap.mwStatusCont eq '20001400'}">
				<c:if test="${fn:length(detailDepList) == 0 }">
					<a href="javascript:doDepReplInsert('<c:out value="${gBoardAdminVo.cbIdx}"/>','<c:out value="${gBoardAdminVo.bcIdx}"/>','','','I');" class="btn1" >답변등록</a>
				</c:if>
				<c:if test="${fn:length(detailDepList) > 0 }">
					<c:forEach items="${detailDepList}" var="detailDepList">
						<a href="javascript:doDepReplInsert('<c:out value="${detailDepList.cbIdx}"/>','<c:out value="${detailDepList.bcIdx}"/>','<c:out value="${detailDepList.mcIdx}"/>','<c:out value="${detailDepList.mcDeptCd}"/>','U');" class="btn1" >답변수정</a>
					</c:forEach>
				</c:if>
			</c:if>
		</c:when>
		</c:choose>
	</div>	
	<br/>
	
	<c:choose>
	<c:when test="${contentClobCnt <= 0}">
	<div class="btn_zone">
		<c:if test="${fn:length(detailDepList) > 1 }">
			<%-- <c:if test="${deptCd eq 'D31400610000'}"> --%>
			<c:if test="${RoleIdx eq 'RL00000022' || permCd eq '05010000'}">
				<%-- <a href="javascript:doIntergRespPop('<c:out value="${gBoardAdminVo.cbIdx }"/>', '<c:out value="${gBoardAdminVo.bcIdx}"/>', '<c:out value="${gBoardAdminVo.mcIdx}"/>','I');" class="btn2">통합답변작성</a> --%>
			</c:if>		
		</c:if>
	</div>
	</c:when>
	<c:otherwise>
	<br/>
	<table summary="" class="write">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="85%" />
		</colgroup>
		<tbody>
		<tr>
			<th>답변상태</th>
			<td><c:out value="${selectIntergRespMap.auditName}"/></td>
		</tr>
		<tr>
			<th>통합요지</th>
			<td><c:out value="${selectIntergRespMap.mcPointTxt}"/></td>
		</tr>
		<tr>
			<th>통합답변</th>
			<td><c:out value="${selectIntergRespMap.contentClob}"/></td>
		</tr>
		<c:if test="${fn:length(detailDeptFileList) > 0}">
		<tr>
			<th width="15%">첨부파일</th>
			<td width="85%" colspan="3"><!-- 왜 안되냐고.... -->
				<c:forEach items="${detailDeptFileList}" var="detailDeptFileList">
				<c:if test="${selectIntergRespMap.mcIdx eq detailDeptFileList.mcIdx}">
				<a href="/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_fileDownload.do?bcIdx=<c:out value="${detailDeptFileList.bcIdx }" />&cbIdx=<c:out value="${detailDeptFileList.cbIdx }" />&fileNo=<c:out value="${detailDeptFileList.fileNo }" />&mcIdx=<c:out value="${detailDeptFileList.mcIdx }" />" class="file">
				파일명 : <c:out value="${detailDeptFileList.orignlFileNm}" />&nbsp;&nbsp;&nbsp;[ <fmt:formatNumber value="${detailDeptFileList.fileSize}"></fmt:formatNumber> byte ]
				</a>
				<%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${detailDeptFileList.fileStreCours }' />&filename=<c:out value='${detailDeptFileList.streFileNm }' />" title="새창" class="view-direct">바로보기</a> --%>
				<br/>
				</c:if>
				</c:forEach>
			</td>
		</tr>
		
		</c:if>
		</tbody>
	</table>
	<br/>
	<div class="btn_zone">
		<c:choose>
		<%-- <c:when test="${selectIntergRespMap.auditName ne '' || selectIntergRespMap.auditName ne null}"> --%>
		<c:when test="${selectIntergRespMap.auditName ne '' || selectIntergRespMap.auditName ne null}">
		<a href="javascript:doIntergRespPop('<c:out value="${selectIntergRespMap.cbIdx }"/>', '<c:out value="${selectIntergRespMap.bcIdx}"/>', '<c:out value="${selectIntergRespMap.mcIdx}"/>', 'U');" class="btn2">통합답변수정</a>
		</c:when>
		<c:otherwise>
		<%-- <a href="javascript:doIntergRespPop('${selectIntergRespMap.cbIdx }', '${selectIntergRespMap.bcIdx}', '${selectIntergRespMap.mcIdx}','I');" class="btn2">통합답변작성</a> --%>
		</c:otherwise>
		</c:choose>
	</div>
	</c:otherwise>
	</c:choose>
	
	<!-- 담당부서 -->
	<c:if test="${( RoleIdx eq 'RL00000022' || permCd eq '05010000' || fn:indexOf(detailMap.cdSubject, CMSUSER.deptCd) != -1 ) && detailMap.mwStatusCont ne '20000600' && detailMap.mwStatusCont ne '20000700'}">
	<div class="title1">민원답변 상세보기</div>
	<c:forEach items="${detailDepList}" var="detailDepList">
	<%-- <c:if test="${deptCd eq 'D31400610000' || detailDepList.mcDeptCd eq deptNm}"> 구바라 담당자와 부서지정 받은 담당자 일때--%>
	<table summary="" class="write">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="35%" />
		</colgroup>
		<tbody>
		<tr>
			<th>부서</th>
			<td>${detailDepList.mcDeptName}</td>
			<td colspan="2">
				<c:choose>
					<c:when test="${detailMap.mwStatusCont eq '20001400' }">
						<input type="checkbox" value="Y" checked="checked" disabled="disabled"/> 다수인 반복민원처리종결
					</c:when>
					<c:otherwise>
						<input type="checkbox" value="Y" disabled="disabled"/> 다수인 반복민원처리종결
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			 <th width="15%">담당자</th>
			<td>
				<c:choose>
				<c:when test="${fn:length(detailDepList.mcReplyer) > 0}">
					<c:out value="${detailDepList.mcReplyer}"/>
				</c:when>
				<c:otherwise>
					<!-- 담당자명을 입력해주세요.	 -->				
				</c:otherwise>
				</c:choose>
				<span style="margin-left: 25%;">
				<c:if test="${RoleIdx eq 'RL00000022' || permCd eq '05010000'}">
				<%-- 
				<a href="javascript:doAnswerDelay('<c:out value="${detailDepList.cbIdx}"/>','<c:out value="${detailDepList.bcIdx}"/>','<c:out value="${detailDepList.mcIdx}"/>');" class="btn1" style="float: right">답변기한연기</a>
				--%>
				<%-- <a href="javascript:doReplClear('<c:out value="${detailDepList.cbIdx}"/>','<c:out value="${detailDepList.bcIdx}"/>','<c:out value="${detailDepList.mcIdx}"/>');" class="btn1">답변초기화</a> --%>
				<%-- <a href="javascript:doDepDel('<c:out value="${detailDepList.cbIdx}"/>','<c:out value="${detailDepList.bcIdx}"/>','<c:out value="${detailDepList.mcIdx}"/>');" class="btn1">부서삭제</a> --%>
				<%-- <a href="javascript:doDepUpdate('<c:out value="${detailDepList.cbIdx}"/>','<c:out value="${detailDepList.bcIdx}"/>','<c:out value="${detailDepList.mcIdx}"/>','<c:out value="${detailDepList.mcDeptCd}"/>');" class="btn1">부서변경</a> --%>
				</c:if>
				<%-- <c:if test="${RoleIdx eq 'RL00000022' || detailDepList.mcDeptCd eq deptNm}"> --%>
				<c:if test="${detailDepList.mcDeptCd eq CMSUSER.deptCd}">
				<%-- <a href="javascript:doDepReplyer('<c:out value="${detailDepList.cbIdx}"/>','<c:out value="${detailDepList.bcIdx}"/>','<c:out value="${detailDepList.mcIdx}"/>','<c:out value="${detailDepList.mcReplyer}"/>');" class="btn1">담당자등록</a> --%>
				</span>
				</c:if>
			</td>
			<th>전화번호</th>
			<td><c:out value="${detailDepList.mcTel1}"/></td>
		</tr>
		<tr>
			<th>민원발생지역</th>
			<td>${detailDepList.mcAreaCd}</td>
			<th>민원분류</th>
			<td><c:out value="${detailDepList.mcFiledCd}"/></td>
		</tr>
		<tr>
			<th>민원유형</th>
			<td>${detailDepList.mcKdCd}</td>
			<th>처리결과</th>
			<td><c:out value="${detailDepList.mcResult}"/></td>
		</tr>
		<c:choose>
		<c:when test="${fn:length(detailDepList.contentClob) > 0}">
		<c:if test="${fn:length(detailDepList.mcDeptHist) > 0}">
		<tr>
			<th width="15%">부서지정이력</th>
			<td width="85%" colspan="3"><c:out value="${detailDepList.mcDeptHist}"/></td>
		</tr>
		</c:if>
		<tr>
			<th>인사말</th>
			<td colspan="3">안녕하십니까? 양천구청장 김수영입니다.</td>
		</tr>
		<tr>
			<th>민원요지 및 공감 표현</th>
			<td colspan="3"><c:out value="${detailDepList.mcPointTxt}"/></td>
		</tr>
		<tr>
			<th>답변내용</th>
			<td colspan="3">
				<c:out value="${cmm:outClobCont(detailDepList.contentClob)}" escapeXml="false"/>
			</td>
		</tr>
		<tr>
			<th>끝인사</th>
			<td colspan="3">
				<c:set var="rDt" value="${fn:split(fn:substring(detailDepList.regDt,0,10),'-')}" />
				<p>자세한 사항은 아래로 연락주시면 정성을 다해 답변드리겠습니다.</p>
				<p>양천구청에 애정과 관심을 가져주심에 다시한번 감사드리며, 귀하의 가정에 건강과 행복이 가득하시기를 기원합니다.</p>
				<p style="text-align:right;"><c:out value="${rDt[0]}"/>년 <c:out value="${rDt[1]}"/>월 <c:out value="${rDt[2]}"/>일<br/>양천구청장 드림</p>
			</td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:if test="${fn:length(detailDepList.mcDeptHist) > 0}">
		<tr>
			<th>부서지정이력</th>
			<td colspan="3"><c:out value="${detailDepList.mcDeptHist}"/></td>
		</tr>
		</c:if>
		<tr>
			<th>담당부서</th>
			<%-- <td colspan="3">${detailDepList.mcDeptName}</td> --%>
			<td colspan="3"><c:out value="${detailDepList.mcDeptName}"/></td>
		</tr>
		<tr>
			<th>상태</th>
			<td colspan="3">
				처리중입니다. 답변기한은 <b><c:out value="${detailDepList.replRegDt}"/></b> 입니다.<br/>
				<c:if test="${fn:length(detailDepList.mcDelayDay) > 0}">
				<c:out value="${detailDepList.mcDelayRsn}"/> 등으로 <b><c:out value="${detailDepList.mcDelayDay}" /></b> 까지 답변기한이 연기되었습니다.
				</c:if>
			</td>
		</tr>
		</c:otherwise>
		</c:choose>
		<c:if test="${fn:length(detailDepList.contentClob) > 0}">
		<tr>
			<th width="15%">첨부파일</th>
			<td width="85%" colspan="3"><!-- 왜 안되냐고.... -->
				<c:forEach items="${detailDeptFileList}" var="detailDeptFileList">
				<c:if test="${detailDepList.mcIdx eq detailDeptFileList.mcIdx}">
				<a href="/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_fileDownload.do?bcIdx=<c:out value="${detailDeptFileList.bcIdx }" />&cbIdx=<c:out value="${detailDeptFileList.cbIdx }" />&fileNo=<c:out value="${detailDeptFileList.fileNo }" />&mcIdx=<c:out value="${detailDeptFileList.mcIdx }" />" class="file">
				파일명 : <c:out value="${detailDeptFileList.orignlFileNm}" />&nbsp;&nbsp;&nbsp;[ <fmt:formatNumber value="${detailDeptFileList.fileSize}"></fmt:formatNumber> byte ]
				</a>
				<%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${detailDeptFileList.fileStreCours }' />&filename=<c:out value='${detailDeptFileList.streFileNm }' />" title="새창" class="view-direct">바로보기</a> --%>
				<br/>
				</c:if>
				</c:forEach>
			</td>
		</tr>
		</c:if>
		<tr>
			<th>등록일자</th>
			<td><c:out value="${detailDepList.regDt}"/></td>
			<th>수정일자</th>
			<td><c:out value="${detailDepList.modDt}"/></td>
		</tr>
		</tbody>
	</table>
	</c:forEach>
	</c:if>
</div>
</form:form>
</body>
</html>
