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
<script type="text/javascript" src="<c:url value='/js/jquery.blockUI.js' />"></script>
<script type="text/javascript">

// 돌아가기
function doView(){
	history.back(-1);
}

function checkValue() {

	/* if($("#mcDeptNo1Cd").val()=="") {
		alert("부서장(과장)의 이름을 선택해주세요.");
		$("#mcDeptNo1Cd").focus();
		return false; */
	 
	/* }else if($("#mcUnitCd").val()=="") {
		alert("단위업무(1차)를 선택해주세요.");
		$("#mcUnitCd").focus();
		return false; */
	if($("#mcReplyer").val()=="") {
		alert("담당자를 입력해주세요.");
		$("#mcReplyer").focus();	
		return false;
	}else if($("#mcTel1").val()=="") {
		alert("전화번호를 입력해주세요.");
		$("#mcTel1").focus();
		return false;
	}else if($("#mcAreaCd").val()=="") {
		if(cm_check_empty("민원발생지역", $("#mcAreaCd")) == false) {return;}
		return false;
	}else if($("#mcFiledCd").val()=="") {
		if(cm_check_empty("민원분야", $("#mcFiledCd")) == false) {return;}
		return false;
	}else if($("#mcKdCd").val()=="") {
		if(cm_check_empty("민원유형", $("#mcKdCd")) == false) {return;}
		return false;
	}else if($("#mcResult").val()=="") {
		if(cm_check_empty("처리결과", $("#mcResult")) == false) {return;}
		return false;
	}else if($("#mcPointTxt").val()=="") {
		if(cm_check_empty("민원요지 및 공감표현", $("#mcPointTxt")) == false) {return;}
		return false;
	}else if($("#contentClob").val()=="") {
		if(cm_check_empty("답변내용", $("#contentClob")) == false) {return;}
		return false;
	}else{
		return true;
	}
}

// 답변 및 수정
function doReplInsert(gbnVal){
	if(checkValue()){
		document.GBoardAdminVo.gbnVal.value = gbnVal;
		document.GBoardAdminVo.action = "<c:url value='/boffice/ex/bbs/gBoardAdmin/gBoardLastRepl_Reg.do'/>";
		$.blockUI();
		document.GBoardAdminVo.submit();
	}
}

// 첨부파일 삭제
function doBbsFileRmvAx(cbIdx, bcIdx, mcIdx, fileNo){
	
	if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")){
		return;
	}
	var vHtml ="<input type='file' name='fileTagId' class='w90' id='fileTagId2'>"; 
	 $.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/gBoardAdmin/File_RmvAx.do'/>",
		data : {"cbIdx" : cbIdx
			   , "bcIdx" : bcIdx
			   , "fileNo" : fileNo
			   , "mcIdx" : mcIdx},
		dataType : "json",
		success:function(object){
			alert("삭제되었습니다.");
			$("#upFile"+fileNo).html( vHtml );
        	$("#fileRmv"+fileNo).hide();
		 },
        error: function(xhr,status,error){
        	alert(status);
        }
	});
}

function doFileMake(fileCnt){
	var vHtml ="";
	for(var i = 1; i <= parseInt(fileCnt); i++){
		vHtml += "<input type='file' name='fileTagId' class='w90' id='fileTagId'>";
	}
	$("#upFileSpan").html( vHtml );
}

</script>

<script type="text/javascript">
$(window).load(function() {
	var fileCnt = '1';
	doFileMake(fileCnt);
});

$(document).ready(function() {
	
	placeholder($("#mcPointTxt"), "/images/adm/placeholder1.gif"); //민원요지
	placeholder($("#contentClob"), "/images/adm/placeholder2.gif"); //답변내용
	placeholder($("#mcTel2"), "/images/adm/placeholder3.gif"); //전화번호

	$("#mcPointTxt").bind("focusin", function() {
		$(this).css("background", "none");
	}).bind("focusout", function() {
		placeholder($(this), "/images/adm/placeholder1.gif");
	});
	$("#contentClob").bind("focusin", function() {
		$(this).css("background", "none");
	}).bind("focusout", function() {
		placeholder($(this), "/images/adm/placeholder2.gif");
	});
	$("#mcTel2").bind("focusin", function() {
		$(this).css("background", "none");
	}).bind("focusout", function() {
		placeholder($(this), "/images/adm/placeholder3.gif");
	});
	
	$("#multiComplainClose").click(function(){
		if($(this).is(":checked")){
			var content = "안녕하십니까? 양천구청장입니다. ○○○님께서 보내주신 민원사항에 대하여 담당부서(<c:out value="${CMSUSER.deptNm}"/>)홈페이지에 자세한 안내 및 이해를 돕기 위한 자료가 준비되어 있습니다. 앞으로도 구정에 대한 많은 관심과 성원을 부탁 드리며, 더욱 열심히 일하는 양천구청이 되겠습니다. 감사합니다.";	
			$("#contentClob").val(content);
			$("#contentClob").focus();
		}else{
			$("#contentClob").val("");
		}		
	});
	
});

function placeholder(obj, image) {
	if(obj.val() == "") {
		obj.css("background", "url('"+image+"') 0px 0px no-repeat");
	}else{
		obj.css("background", "none");
	}
}
</script>

<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
</style>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 200px;">
<form:form commandName="GBoardAdminVo" name="GBoardAdminVo" method="post" enctype="multipart/form-data" >
<form:hidden path="cbIdx" />
<form:hidden path="bcIdx" />
<form:hidden path="mcIdx" />
<form:hidden path="gbnVal" />
<c:set var="chiefName" value="${chiefHistoryMap.CH_NAME}"/>
<div id="contents">
	<table summary="" class="write">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="85%" />
		</colgroup>
		
		<tbody>
			<tr>
				<th colspan="2">구청장에게 바란다 원글</th>
			</tr>
			<tr>
				<th>제목</th>
				<td><c:out value="${detailMap.subCont}"/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><c:out value="${detailMap.nameCont}"/></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><c:out value="${detailMap.clobCont}" escapeXml="false"/></td>
			</tr>
		</tbody>
	</table>
	
	<br/>	
	<div class="btn_zone">
		<a href="javascript:doView();" class="btn2">이전화면</a>
	</div>	
	<br/>
	
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
				<th colspan="4">민원답변등록</th>
			</tr>
			<tr>
				<th><label for="mcReplyer">부서</label></th>
				<td>
					<c:out value="${CMSUSER.deptNm}"/>
				</td>
				<td colspan="2">
					<c:choose>
						<c:when test="${gBoardAdminVo.modGbn eq 'I'}">
							<input type="checkbox" id="multiComplainClose" name="multiComplainClose" value="Y" /> 다수인 반복민원처리종결
						</c:when>
						<c:when test="${gBoardAdminVo.modGbn eq 'U'}">
							<c:if test="${detailMap.mwStatusCont eq '20001400'}">
								<input type="checkbox" value="Y" checked="checked" disabled="disabled"/> 다수인 반복민원처리종결
								<input type="hidden" name="multiComplainClose" value="Y" />
							</c:if> 
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th><label for="mcReplyer">담당자</label></th>
				<td>
					<c:choose>
						<c:when test="${gBoardAdminVo.modGbn eq 'I'}">
							<input type="text" id="mcReplyer" name="mcReplyer" value="<c:out value="${CMSUSER.userName}"/>" maxlength="14" />
						</c:when>
						<c:otherwise>
							<input type="text" id="mcReplyer" name="mcReplyer" value="<c:out value="${detailDepMap.mcReplyer}"/>" maxlength="14" />
						</c:otherwise>
					</c:choose>
				</td>
				<th><label for="mcTel1">전화번호</label></th>
				<td>
					<input type="text" id="mcTel1" name="mcTel1" value="<c:out value="${detailDepMap.mcTel1}"/>" maxlength="14" />
				</td>
			</tr>
			<%-- <tr>
				<th><label for="mcUnitCd">단위업무명1차</label></th>
				<td colspan="3">
					<select id="mcUnitCd" name="mcUnitCd" style="width: 200px;">
						<option value="">== 단위업무명 1차선택 ==</option>
						<c:forEach items="${selectDw1List}" var="selectDw1List">
						<option value="<c:out value="${selectDw1List.cdIdx}"/>" <c:if test="${detailDepMap.mcDeptCd eq selectDw1List.cdIdx}">selected="selected"</c:if> ><c:out value="${selectDw1List.cdSubject}"/></option>
						</c:forEach>
						<option value="기타" <c:if test="${detailDepMap.mcDeptCd eq '기타'}">selected="selected"</c:if>>기타</option>
					</select>
				</td>
			</tr> --%>
			<tr>
				<th><label for="mcAreaCd">민원발생지역</label></th>
				<td>
					<c:set var="dongVar">목1동,목2동,목3동,목4동,목5동,신월1동,신월2동,신월3동,신월4동,신월5동,신월6동,신월7동,신정1동,신정2동,신정3동,신정4동,신정6동,신정7동</c:set>
					<select id="mcAreaCd" name="mcAreaCd" style="width: 200px;">
						<option value="">== 선 택 ==</option>
						<c:forTokens items="${dongVar}" delims="," var="value">
						<option value="<c:out value="${value}"/>" <c:if test="${detailDepMap.mcAreaCd eq value}">selected="selected"</c:if>><c:out value="${value}"/></option>
						</c:forTokens>
						<option value="기타" <c:if test="${detailDepMap.mcAreaCd eq '기타'}">selected="selected"</c:if>>기타</option>
					</select>
				</td>
				<th><label for="mcFiledCd">민원분야</label></th>
				<%-- <td>
					<c:set var="categoryVar">건축/주택/도시계획,교통,토목/도로/건설관리/디자인,환경/청소,공원녹지,보건/복지/자원봉사,치수/하수도,경제/세무/재무,교육/문화/체육/홍보,기타</c:set>
					<select id="mcFiledCd" name="mcFiledCd" style="width: 200px;">
						<option value="">== 선 택 ==</option>
						<c:forTokens items="${categoryVar}" delims="," var="value">
						<option value="<c:out value="${value}"/>" <c:if test="${detailDepMap.mcFiledCd eq value}">selected="selected"</c:if>><c:out value="${value}"/></option>
						</c:forTokens>
					</select>
				</td> --%>
				<td>
				<select id="mcFiledCd" name="mcFiledCd" >
					<option value="">==선택==</option>
					<c:forEach var="gubara_Category" items="${gubara_Category}" varStatus="status">
					<option value="<c:out value="${gubara_Category.code}"/>"<c:if test="${detailDepMap.mcFiledCd eq gubara_Category.code}">selected="selected"</c:if>><c:out value="${gubara_Category.codeName}"/></option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<th><label for="mcKdCd">민원유형</label></th>
				<td>
					<c:set var="typeVar">제안(건의),시정(요구),문의(확인),감사(격려),하소연,이의(불만),불친절</c:set> 
					<select id="mcKdCd" name="mcKdCd" style="width: 200px;">
						<option value="">== 선 택 ==</option>
						<c:forTokens items="${typeVar}" delims="," var="value">
						<option value="<c:out value="${value}"/>" <c:if test="${detailDepMap.mcKdCd eq value}">selected="selected"</c:if>><c:out value="${value}"/></option>
						</c:forTokens>
					</select>
				</td>
				<th><label for="mcResult">처리결과</label></th>
				<td>
					<c:set var="endVar">완전해결,차선해결,일부해결,내부종결,이해설득,법령상불가,예산상불가,민형사상불가,이송이첩,기타</c:set>
					<select id="mcResult" name="mcResult" style="width: 200px;">
						<option value="">== 선 택 ==</option>
						<c:forTokens items="${endVar}" delims="," var="value">
						<option value="<c:out value="${value}"/>" <c:if test="${detailDepMap.mcResult eq value}">selected="selected"</c:if>><c:out value="${value}"/></option>
						</c:forTokens>
					</select>
				</td>
			</tr>
			<tr>
				<th><label for="mcDeptNo1Cd">인사말</label></th>
				<td colspan="3">안녕하십니까? 양천구청장 김수영입니다.</td>
			</tr>
			<tr>
				<th><label for="mcPointTxt">민원요지 및 공감표현 </th>
				<td colspan="3">
					<textarea id="mcPointTxt" name="mcPointTxt" style="width:99%;" rows="3"><c:out value="${detailDepMap.mcPointTxt}"/></textarea>
				</td>
			</tr>
			<tr>
				<th><label for="contentClob">답변내용</label></th>
				<td colspan="3">
					<textarea id="contentClob" name="contentClob" style="width:99%;" rows="7"><c:out value="${detailDepMap.contentClob}"/></textarea>
				</td>
			</tr>
			<tr>
				<th><label>끝인사</label></th>
				<td colspan="3">
					<p>자세한 사항은 아래로 연락주시면 정성을 다해 답변드리겠습니다.</p>
					<p>양천구청에 애정과 관심을 가져주심에 다시한번 감사드리며, 귀하의 가정에 건강과 행복이 가득하시기를 기원합니다.</p>
					<p style="text-align:right;">
						<c:choose>
							<c:when test="${gBoardAdminVo.modGbn eq 'I'}">
								<jsp:useBean id="toDay" class="java.util.Date" />
								<fmt:formatDate value="${toDay}" pattern="yyyy" />년 <fmt:formatDate value="${toDay}" pattern="MM" />월 <fmt:formatDate value="${toDay}" pattern="dd" />일<br/>양천구청장 드림
							</c:when>
							<c:otherwise>
								<c:set var="rDt" value="${fn:split(detailDepMap.regDt,'-')}" />
								<c:out value="${rDt[0]}"/>년 <c:out value="${rDt[1]}"/>월 <c:out value="${rDt[2]}"/>일<br/>양천구청장 드림
							</c:otherwise>
						</c:choose>
					</p>
				</td>
			</tr>
			<c:choose>
			<c:when test="${fn:length(detailDeptFileList) > 0}">
			<tr>
				<th><label>첨부파일</label></th>
				<td colspan="3">
					<c:forEach items="${detailDeptFileList}" var="detailDeptFileList">
					<c:if test="${detailDepMap.mcIdx eq detailDeptFileList.mcIdx}">
					<p class="uploaded_file" id="fileRmv<c:out value='${detailDeptFileList.fileNo}'/>">
					<a href="/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_fileDownload.do?bcIdx=<c:out value="${detailDeptFileList.bcIdx }" />&cbIdx=<c:out value="${detailDeptFileList.cbIdx }" />&fileNo=<c:out value="${detailDeptFileList.fileNo }" />" class="file">
					파일명 : <c:out value="${detailDeptFileList.orignlFileNm}" />&nbsp;&nbsp;&nbsp;[ <fmt:formatNumber value="${detailDeptFileList.fileSize}"></fmt:formatNumber> byte ]
					</a>
					<%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${detailDeptFileList.fileStreCours }' />&filename=<c:out value='${detailDeptFileList.streFileNm }' />" title="새창" class="view-direct">바로보기</a> --%>
					<a href="javascript:doBbsFileRmvAx('<c:out value="${detailDeptFileList.cbIdx}"/>','<c:out value="${detailDeptFileList.bcIdx}"/>','<c:out value="${detailDeptFileList.mcIdx}"/>','<c:out value="${detailDeptFileList.fileNo}"/>');" class="delete">삭제</a>
					<br/>
					</p>
					<span id="upFile<c:out value='${detailDeptFileList.fileNo}'/>"></span>
					</c:if>
					<c:if test="${detailDepMap.mcIdx ne detailDeptFileList.mcIdx}">
					<span id="upFileSpan"></span>
					</c:if>
					</c:forEach>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
			<tr>
				<th><label for="fileTagId">첨부파일</label></th>
				<td colspan="3">
					<span id="upFileSpan"></span>
				</td>
			</tr>
			</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<br/>	
	<div class="btn_zone">
		<c:choose>
		<c:when test="${gBoardAdminVo.modGbn eq 'I'}">
		<!-- <a href="javascript:doReplInsert('N');" class="btn2">민원 중간답변 등록</a> -->
		<a href="javascript:doReplInsert('Y');" class="btn2">확인</a>
		</c:when>
		<c:otherwise>
		<a href="javascript:doReplInsert('U');" class="btn2">확인</a>
		<a href="javascript:doView();" class="btn2">이전화면</a>
		</c:otherwise>
		</c:choose>
		
	</div>	
	<br/>
	
</div>
</form:form>
<!--  웹필터 수정 -->  
<%-- <%@ include file="/webfilter/inc/initCheckWebfilter.jsp"%> --%>
<!--  웹필터 수정 -->
</body>
</html>
