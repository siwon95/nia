<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<script type="text/javascript">
var chkreg = "";

function doMgrList() {
	document.MgrVo.mgrDept.value = "";
	document.MgrVo.permCd.value = "";
	document.MgrVo.statusCd.value = "";
	document.MgrVo.action = "<c:url value='/boffice/sy/mgr/Mgr_List.do' />";
	document.MgrVo.submit();
}

function doMgrReg(){
	
	var test = "<c:out value='${menuGubun}'/>";
	
	if(test == "update"){
		chkreg = "Y";
	}
	
	if(chkreg == "N"){
		alert("아이디 중복체크 를 하여주십시오.");
		return;
	}
	
	if($('#mgrPw').val() == ""){
		alert("비밀번호를 확인해주십시오.");
		return;
	}
	
	if($('#mgrName').val() == ""){
		alert("관리자이름을 확인해주십시오.");
		return;
	}
	
	if($("#test_combo option:selected").val() == "0"){
		alert("관리자 등급코드 를 선택해주십시오.");
		return;
	}
	
	if($("#test_combo2 option:selected").val() == "0"){
		alert("관리자 부서 를 선택해주십시오.");
		return;
	}
	
	if($("#test_combo3 option:selected").val() == "0"){
		alert("관리자 상태 를 선택해주십시오.");
		return;
	}
	
	if($("#bsYn option:selected").val() == "0"){
		alert("대표 여부 를 선택해주십시오.");
		return;
	}
	
	
	
	document.MgrVo.permCd.value = $("#test_combo option:selected").val();
	document.MgrVo.mgrDept.value = $("#test_combo2 option:selected").val();
	document.MgrVo.statusCd.value = $("#test_combo3 option:selected").val();
	
	if(test == "form"){
		document.MgrVo.action = "/boffice/sy/mgr/Mgr_Reg.do";
		document.MgrVo.submit();
	}else if(test == "update"){
		document.MgrVo.sampleId.value = "<c:out value='${MgrVo.mgrId}'/>";
		document.MgrVo.action = "/boffice/sy/mgr/Mgr_Mod.do";
		document.MgrVo.submit();
	}
	
}


function doListMgrAx() {
	if($('#mgrId').val() == ""){
		alert("아이디 를 입력해 주십시오.");
		return;
	}else{
		$.ajax({
			type: "GET",
			url: "<c:url value='/boffice/sy/mgr/Mgr_ListAx.do'/>",
			data : "searchCode=" + $('#mgrId').val(),
			dataType : "json",
			success:function(object){
				chkreg = object.MgrAxValue;
				if(chkreg.indexOf("중복") > 0){
					chkreg = "N";
				}else{
					chkreg = "Y";
				}
				alert(object.MgrAxValue);
			
			 },
	        error: function(xhr,status,error){
	        	alert(status);
	        }
		});	
	}
			
}

$(window).load(function() {
	
	cm_combo_dept("#test_combo2");
	cm_combo_cmm("#test_combo","05000000");
	cm_combo_cmm("#test_combo3","03000000");
	
	var test_combo  = 	'<c:out value="${MgrVo.permCd}" />';
    $("#test_combo").val(test_combo).attr("selected","selected");
    var test_combo2  = 	'<c:out value="${MgrVo.mgrDept}" />';
    $("#test_combo2").val(test_combo2).attr("selected","selected");
    var test_combo3  = 	'<c:out value="${MgrVo.statusCd}" />';
    $("#test_combo3").val(test_combo3).attr("selected","selected");
});
</script>
</head>
<body>
	<form:form commandName="MgrVo" name="MgrVo" method="post" >
	<form:hidden path="sampleId" />
	<form:hidden path="pageIndex" />
	<form:hidden path="mgrMenu" />
	<form:hidden path="statusCd" />
	<form:hidden path="mgrDept" />
	<form:hidden path="permCd" />
	<form:hidden path="regId" value="${SesUserId}"/>
	<table class="form">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="*" />
		</colgroup>
		<tbody>
		<tr>
			<th scope="row"><label for="mgrId">관리자 아이디</label></th>
			<td>
			<c:set var="test" value="${menuGubun}"/>
			<c:if test="${test == 'update' }">
			<c:out value="${MgrVo.mgrId}"/>
			</c:if>
			<c:if test="${test == 'form'}">
			<form:input path="mgrId" size="20" maxlength="15"/>
			<a href="javascript:doListMgrAx();" class="btn_inline btn_check">중복확인</a>
			</c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="mgrPw">관리자 비밀번호</label></th>
			<td><form:input path="mgrPw" size="20" maxlength="15"/></td>
		</tr>
		<tr>
			<th scope="row"><label for="mgrName">관리자 이름</label></th>
			<td><form:input path="mgrName" size="20" maxlength="15"/></td>
		</tr>
		<tr>
			<th scope="row"><label for="test_combo">관리자등급코드</label></th>
			<td>
				<select id="test_combo" name="test_combo">
					<option value="0">선택하세요</option>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="test_combo2">관리자 부서</label></th>
			<td>
			<select id="test_combo2" name="test_combo2">
				<option value="0">선택하세요</option>
			</select>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="test_combo3">관리자 상태</label></th>
			<td>
			<select id="test_combo3" name="test_combo3">
				<option value="0">선택하세요</option>
			</select>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="bsYn">대표 여부</label></th>
			<td>
			<select id="bsYn" name="bsYn">
				<option value="0">선택하세요</option>
				<option value="Y" <c:if test="${MgrVo.bsYn eq 'Y' }">selected="selected"</c:if>>Y</option>
				<option value="N" <c:if test="${MgrVo.bsYn eq 'N' }">selected="selected"</c:if>>N</option>
			</select>
			</td>
		</tr>
		</tbody>
	</table>
	<div class="btnArea">
		<a href="javascript:doMgrReg();" class="btn_m btn_focus">저장</a>
		<a href="javascript:doMgrList();" class="btn_m">취소</a>
	</div>
	</form:form>
</body>
</html>
