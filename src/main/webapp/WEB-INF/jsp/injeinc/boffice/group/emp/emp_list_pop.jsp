<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 직원정보관리
- 파일명 : mgr_authority_pop.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<c:import url="/layout/cms/head.jsp" ></c:import>
<script>
function doEmpForm(e,idx,ceIdx) {
	if(ceIdx == 'd99999999'){
		alert("미등록 사용자입니다.");
		return;
	}
	var x;
	var y;
	if (idx == "" ){
		idx = "new";
		x = empForm.style.posLeft = event.x - 600 + document.body.scrollLeft;
		y = empForm.style.posTop = event.y + 50 + document.body.scrollTop;
	} else {
		x = e.pageX ? e.pageX : document.documentElement.scrollLeft+event.clientX; 
		y = e.pageY ? e.pageY : document.documentElement.scrollTop+event.clientY; 
	}
	empForm.style.left = x+'px'; 
	empForm.style.top = y+'px';

	var empForm_text ;
	empForm_text =  '<div id="ifTb" style="border:1px solid #D8D8D6;"><iframe src="/boffice_noDeco/group/emp/Emp_Form.do?ceIdx='+idx+'" width="603" height="350" border="0" frameborder="0" scrolling="yes"></iframe></div>';
	empForm.innerHTML=empForm_text ;

}	

function doEmpList(form) {
	var f = form;
	f.action = "<c:url value='/boffice_noDeco/group/emp/Emp_List.do' />";
	if(f.srchAll.checked == true){
		f.srchAll.value = 'Y';
		f.selBox.value = 'dd';
	}else{
		f.srchAll.value = 'N';
	}
	if ((f.selBox.value == "dd" || f.selBox.value == "") &&  f.srchAll.value == 'N') {
		alert("부서를 선택하시거나 전체검색을 체크 해주세요.");
	} else {
		f.submit();
	}
}

function doCheckMod(form) {
	var f = form;
	if (f.ceUse.value =="Y") {
		f.ceUse.value = "N";
	}else {
		f.ceUse.value = "Y";
	}
	f.submit();
}

function doJozicCheckMod(form) {
	var f = form;
	f.actionKey.value = "jUpdate";
	if (f.siteViewYn.value =="Y") {
		f.siteViewYn.value = "N";
	}else {
		f.siteViewYn.value = "Y";
	}
	f.submit();
}

function modifyStepUp(thisform,step) {
	var f = thisform;
	f.action = "/emp/EmpStep_Mod.do?step="+step;
	f.submit();
}

function modifyStepDown(thisform,step) {
	var f = thisform;
	f.action = "/emp/EmpStep_Mod.do?step="+step;
	f.submit();
}

function doTableHide(){
	document.all.ifTb.style.display = "none";
}

function doEmpMod(form){
	form.action  = "/emp/NoEmp_Mod.do";
	form.submit();
}

function fEmpSelect(inName,inChargerId,inTel,inFax,inCdIdx,inDepartment){
	$("#chargerId",opener.document).val(inChargerId);
	$("#chargeDeptCode",opener.document).val(inCdIdx);
	$("#idChargeName",opener.document).html(inName);
	$("#idChargeTel",opener.document).html(inTel);
	$("#idChargeFax",opener.document).html(inFax);
	$("#idChargeDepart",opener.document).html(inDepartment);
	window.close();
}
</script>
</head>
<body>

<!-- ============================== 모달영역 ============================== -->
<div class="modalWrap popup">
	<div class="modalTitle">
		<h3>담당자 선택</h3>
		<a href="#" class="btn_modalClose">닫기</a>
	</div>
	<div class="modalContent">
		<div id="empForm" style="position:absolute; left:1; top:1px; width:100; height:100px; z-index:1; border-width:1px; border-style:none;"></div>
		<iframe name="ckFrame" src="" width="800" height="200" border="0" style="display:none;"></iframe>
		<form name="srchForm" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<input type="hidden" name="pageIndex" value="<c:out value='${empVo.pageIndex}' />">
		<div class="tableBox">
			<table class="form">
				<colgroup>
					<col style="width:15%;">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th><label for="selBox">부서선택</label></th>
						<td>
							<select name="selBox" onchange="javascript:doEmpList(this.form)">
								<option value="dd" <c:if test="${param.selBox eq 'dd'}">selected="selected"</c:if>>부서선택</option>
								<option value="">==============</option>
								<option value="d999999" <c:if test="${param.selBox eq 'd999999'}">selected="selected"</c:if>>※ 미등록 (*)</option>
								<c:forEach items="${selectList}" var="result" varStatus="status">
									<option value="<c:out value="${result.cdIdx }"/>" <c:if test="${result.cdIdx == param.selBox }">selected="selected"</c:if>>
										<c:choose>
											<c:when test="${result.cdDepstep2 ne '00' && result.cdDepstep3 eq '00'}">
												&nbsp;&nbsp;&nbsp;
											</c:when>
											<c:when test="${result.cdDepstep2 ne '00' && result.cdDepstep3 ne '00'}">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</c:when>
										</c:choose>
										<c:out value="${result.cdSubject }" /><c:if test="${result.empCnt ne '0'}">(<c:out value="${result.empCnt }" />)</c:if>
								</c:forEach>
							</select>		
						</td>
					</tr>
					<tr>
						<th><label for="searchCondition">검색어</label></th>
						<td>
							<input type="checkbox" name="srchAll" value="<c:out value='${param.srchAll}' />" <c:if test="${param.srchAll eq 'Y'}" >checked="checked"</c:if> >전체검색 | 
							이름 <input type="text" name="ceName" value="<c:out value='${param.ceName }' />" >
							<button class="btn_inline btn_search" onClick="javascript:doEmpList(this.form); return false;">검색</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		
		<div class="tableBox">
			<table class="list">
			<colgroup>
				<col style="width:60px;">
				<col>
				<col>
				<col>
				<col>
				<col>
				<col>
				<col>
				<col style="width:60px;">
				<col style="width:60px;">
			</colgroup>
			<thead>
				<tr>
					<th>순위</th>
					<th>이름</th>
					<th>메일ID</th>
					<th>내선번호</th>
					<th>팩스</th>
					<th>국</th>
					<th>과</th>
					<th>팀</th> 
					<th>직위</th> 
					<th>사용</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(resultList) == 0 }">
					<tr>
						<td colspan="10" class="empty">부서를 선택해주세요</td>
					<tr>
				</c:if>
				<c:forEach items="${resultList }" var="result" varStatus="status">
					<tr bgcolor="#FFFFFF" <c:if test="${resultList[status.count].ceDepstep3 ne resultList[status.count-1].ceDepstep3}" >style="border-bottom:3pt solid black;"</c:if>>
						<td><c:out value="${(paginationInfo.totalRecordCount+1)-(status.count+(paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)}" /></td>
						<td ><a href="#none" onclick="javascript:doEmpForm(event,'<c:out value="${result.ceIdx}" />','<c:out value="${result.ceCdIdx}" />')"><c:out value="${result.ceName}" /></a></td>
						<td><c:out value="${result.ceMailid}" /></td>
						<td><c:out value="${result.ceTel}" /></td>
						<td><c:out value="${result.cdFax}" /></td>
						<td><c:out value="${result.ceDepstep1}" /></td>
						<td><c:out value="${result.ceDepstep2}" /></td>
						<td><c:out value="${result.ceDepstep3}" /></td> 
						<td><c:out value="${result.ceDepstep4}" /></td>
						<td><input type="button" value="선택" class="btn_inline" onclick="fEmpSelect('<c:out value="${result.ceName}" />','<c:out value="${result.ceIdx}" />','<c:out value="${result.ceTel}" />','<c:out value="${result.cdFax}" />','<c:out value="${result.cdIdx}" />','<c:out value="${result.ceDepstep1}" /><c:if test="${fn:replace(result.ceDepstep2,'null','') ne ''}"> &gt; <c:out value="${result.ceDepstep2}" /></c:if><c:if test="${fn:replace(result.ceDepstep3,'null','') ne ''}"> &gt; <c:out value="${result.ceDepstep3}" /></c:if>')"/></td>  
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>
	</div>
</div>
<!-- ============================== //모달영역 ============================== -->
</body>
</html>