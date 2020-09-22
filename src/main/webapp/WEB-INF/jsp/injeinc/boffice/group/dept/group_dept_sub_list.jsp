<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 부서및직원관리
- 파일명 : group_dept_sub_list.jsp
- 작성일 : 2018-01-19
- 작성자 : 김근 차장
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<c:import url="/layout/cms/head.jsp" ></c:import>
<script type="text/javascript">
//상세보기및 하위트리 뿌리기
function doGroupDeptView(formName,frameNo,idx,key,goMod,cdCode) {
	fs = eval("document."+formName);
	ft = eval("window.parent.depForm"+frameNo);
	if(frameNo == 1){
		ft.fCode.value =	cdCode;
		ft.fCode2.value = '';
		ft.fCode3.value = '';
	}else if(frameNo == 2){
		ft.fCode.value = parent.depForm1.fCode.value;
		ft.fCode2.value =	cdCode;
		ft.fCode3.value = '';
	}else if(frameNo == 3){
		ft.fCode.value = parent.depForm1.fCode.value;
		ft.fCode2.value = parent.depForm2.fCode2.value;
		ft.fCode3.value =	cdCode;
	}
	frameNo++;
	ft2 = eval("parent.depForm"+frameNo);
	ff = "subFrame"+frameNo;
	ft.cdDepstep1.value = fs.cdDepstep1.value;
	ft.cdDepstep2.value = fs.cdDepstep2.value;
	ft.cdDepstep3.value = fs.cdDepstep3.value;
	ft.cdUse.value = key;
	ft.cdCode.value = cdCode;
	ft.cdSubject.value = fs.cdSubject.value;
	ft.cdIdx.value = fs.cdIdx.value;
	var frameNum;
	frameNum = frameNo - 1;
	if (goMod == "Chk") {
		ft.action="/boffice/group/dept/GroupDept_Mod.do?subframe="+frameNum+"&mod=chk";
		ft.submit(); 
	}else if (goMod == "Up") {
		ft.action="/boffice/group/dept/GroupDept_Mod.do?subframe="+frameNum+"&mod=up";
		ft.submit(); 
	}else if (goMod == "Down") {
		ft.action="/boffice/group/dept/GroupDept_Mod.do?subframe="+frameNum+"&mod=down";
		ft.submit(); 
	}else{
		if (frameNo < 4) {
			if(frameNo == 2){
				parent.$("#subFrame3").attr("src","about:blank");
			}
			parent.$("#"+ff).attr("src","<c:url value='/boffice_noDeco/group/dept/GroupDeptSub_List.do?layout=${groupDeptVo.layout}&subframe="+frameNo+"&cdCode="+cdCode+ "' />");
			ft2.cdDepstep1.value = fs.cdDepstep1.value;
			ft2.cdDepstep2.value = fs.cdDepstep2.value;
			ft2.cdDepstep3.value = fs.cdDepstep3.value;
		}
		ff = "subFrame4";
		parent.$("#"+ff).attr("src","<c:url value='/boffice_noDeco/group/dept/GroupDept_View.do?layout=${groupDeptVo.layout}&cdIdx="+idx+"' />");
	}
}

function checkUse(formName,frameNo,key,idx,cdCode) {

	if (key =="Y") { key = "N"; } else { key = "Y"; }
	var goMod = "Chk";
	doGroupDeptView(formName,frameNo,idx,key,goMod,cdCode);
	
}

function modifyStepUp(formName,frameNo,key,idx,cdCode) {
	if(eval("document."+formName).seq.value=="0" || eval("document."+formName).seq.value=="01"){
		alert("첫번째 입니다.");
		return false;
	}
	var goMod = "Up";
	doGroupDeptView(formName,frameNo,idx,key,goMod,cdCode);
}

function modifyStepDown(formName,frameNo,key,idx,cdCode) {
	if(eval("document."+formName).seq.value=="1" || eval("document."+formName).seq.value=="01"){
		alert("마지막째 입니다.");
		return false;
	}
	var goMod = "Down";
	doGroupDeptView(formName,frameNo,idx,key,goMod,cdCode);
}

$(document).ready(function () {
	$('.tColor').click(function(){
    	 $(".tColor").css("background-color", "#FFFFFF");
         $(this).css("background-color", "#DBE8EC");                                 
     });
});
</script>
</head>
<body>
	<table>
	<colgroup>
		<col style="width:20px;">
		<col>
		<col style="width:55px;">
	</colgroup>
	<tbody>
	<c:forEach items="${resultList }" var="result" varStatus="status">
		<tr>
    	<form:form commandName="groupDeptVo" name="form_${result.cdIdx}">
    		<input type="hidden" name="seq" value="<c:if test="${status.first }">0</c:if><c:if test="${status.last }">1</c:if>" />
			<form:hidden path="subframe" value="${param.subframe}" />
			<form:hidden path="cdDepstep1" value="${result.cdDepstep1}" />
			<form:hidden path="cdDepstep2" value="${result.cdDepstep2}" />      	
			<form:hidden path="cdDepstep3" value="${result.cdDepstep3}" />      	
			<form:hidden path="cdSubject" value="${result.cdSubject}" />     	
			<form:hidden path="cdIdx" value="${result.cdIdx}" />    
			<td>
				<input type="checkbox" onclick="javascript:checkUse('form_<c:out value="${result.cdIdx}" />','<c:out value="${param.subframe}" />','<c:out value="${result.cdUse}" />','<c:out value="${result.cdIdx}" />','<c:out value="${result.cdCode}" />')" <c:if test="${result.cdUse eq 'Y'}">checked="checked"</c:if>>
			</td>
			<td class="tColor"><a href="javascript:doGroupDeptView('form_<c:out value="${result.cdIdx}" />','<c:out value="${param.subframe}" />','<c:out value="${result.cdIdx}" />','<c:out value="${result.cdUse}" />','','<c:out value="${result.cdCode}" />')"><c:out value="${result.cdSubject}" /></a></td>
			<td class="right">
				<c:if test="${groupDeptVo.layout ne 'n' }">
					<input type="button" value="▲" class="btn_ss btn_up noText" onclick="javascript:modifyStepUp('form_<c:out value="${result.cdIdx}" />','<c:out value="${param.subframe}" />','<c:out value="${result.cdUse}" />','<c:out value="${result.cdIdx}" />','<c:out value="${result.cdCode}" />')">
					<input type="button" value="▼" class="btn_ss btn_down noText" onclick="javascript:modifyStepDown('form_<c:out value="${result.cdIdx}" />','<c:out value="${param.subframe}" />','<c:out value="${result.cdUse}" />','<c:out value="${result.cdIdx}" />','<c:out value="${result.cdCode}" />')">
				</c:if>
			</td>
		</form:form>
		</tr>
	</c:forEach>
	</table>
</body>
</html>
