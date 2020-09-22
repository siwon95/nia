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
- 파일명 : group_dept_list.jsp
- 최종수정일 :  
- 상태 : 2차대상
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>부서 및 직원관리</title>
<script type="text/javascript">
function doGroupDeptReg(insertForm) {
	var f = insertForm;
	if (f.cdSubject.value == '') {
		alert("조직도에 등록하실  이름을 입력해주세요!");
		f.cdSubject.focus();
	}else if(f.name == 'depForm2' && f.cdDepstep1.value == '00'){
		alert("국 항목을 선택한 후 등록하십시오");
		return false;
	}else if(f.name == 'depForm3' && (f.cdDepstep1.value == '00' || f.cdDepstep2.value == '00')){
		alert("국, 과 항목을 선택한 후 등록하십시오");
		return false;
	}else {
		if(f.name == 'depForm1'){
			f.action = "/boffice/group/dept/GroupDept_Reg.do?subframe=1";
		}else if(f.name == 'depForm2'){
			f.action = "/boffice/group/dept/GroupDept_Reg.do?subframe=2";
		}else if(f.name == 'depForm3'){
			f.action = "/boffice/group/dept/GroupDept_Reg.do?subframe=3";
		}
		f.submit();
	}
}

function doGroupDeptMod(form) {
	var f = form;
	if (f.cdSubject.value == '') {
		alert("조직도에 등록하실  이름을 입력해주세요!");
		f.cdSubject.focus();
	} else {
		if(f.name == 'depForm1'){
			f.action = "/boffice/group/dept/GroupDeptSub_Mod.do?subframe=1";
		}else if(f.name == 'depForm2'){
			f.action = "/boffice/group/dept/GroupDeptSub_Mod.do?subframe=2";
		}else if(f.name == 'depForm3'){
			f.action = "/boffice/group/dept/GroupDeptSub_Mod.do?subframe=3";
		}
		f.submit();
	}
}

function doGroupDeptRmv(form) {
	var f = form;
	if (f.cdSubject.value == '') {
		alert("삭제하실 이름을 선택해주세요!");
		f.cdSubject.focus();
	} else {
		if(f.name == 'depForm1'){
			if(confirm("해당항목의 하위 항목들도 삭제됩니다. 삭제하시겠습니까?") == true){
				f.action = "/boffice/group/dept/GroupDept_Rmv.do?subframe=1";	
			}else{
				return false;
			}
		}else if(f.name == 'depForm2'){
			if(confirm("해당항목의 하위 항목들도 삭제됩니다. 삭제하시겠습니까?") == true){
				f.action = "/boffice/group/dept/GroupDept_Rmv.do?subframe=2";
			}else{
				return false;
			}
		}else if(f.name == 'depForm3'){
			if(confirm("삭제하시겠습니까?") == true){
				f.action = "/boffice/group/dept/GroupDept_Rmv.do?subframe=3";
			}else{
				return false;
			}
		}
		f.submit();
	}
}

function clearFrame(ic) {
	var sf = eval("subFrame" + ic)
	var sd = eval("document.depForm" + ic)

	sd.cdSubject.value = "";
	sf.history.go(0);

	ic++;
	for (var i = ic; i <= 4; i++) {
		var sf = eval("subFrame" + i)
		var sd = eval("document.depForm" + i)
		sf.location.href = "about:blank";
		sd.cdSubject.value = "";
	}
}
</script>
</head>
<body>
	<table class="form">
	<colgroup>
		<col style="width:170px;">
		<col style="width:170px;">
		<col style="width:170px;">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="col">국</th>
			<th scope="col">과</th>
			<th scope="col">팀</th>
			<th scope="col">정보</th>
		</tr>
		<tr>
			<td style="vertical-align:top;">
				<iframe name="subFrame1" id="subFrame1" style="width:100%;height:500px;border:0px none;" src="/boffice_noDeco/group/dept/GroupDeptSub_List.do?layout=<c:out value="${groupDeptVo.layout}"/>&amp;subframe=1"></iframe>
			</td>
			<td style="vertical-align:top;">
				<iframe name="subFrame2" id="subFrame2" style="width:100%;height:500px;border:0px none;" src="<c:if test="${param.cdCode != null && param.cdCode ne ''}">/boffice_noDeco/group/dept/GroupDeptSub_List.do?layout=<c:out value="${groupDeptVo.layout}"/>&amp;subframe=2&amp;cdCode=<c:out value="${param.cdCode }"/></c:if>"></iframe>
			</td>
			<td style="vertical-align:top;">
				<iframe name="subFrame3" id="subFrame3" style="width:100%;height:500px;border:0px none;" src="<c:if test="${param.cdCode2 != null && param.cdCode2 ne ''}">/boffice_noDeco/group/dept/GroupDeptSub_List.do?layout=<c:out value="${groupDeptVo.layout}"/>&amp;subframe=3&amp;cdCode=<c:out value="${param.cdCode2 }"/></c:if>"></iframe>
			</td>
			<td rowspan="2" style="vertical-align:top;">
				<iframe name="subFrame4" id="subFrame4" style="width:100%;height:590px;border:0px none;"></iframe>
			</td>
		</tr>
		<tr>
			<td class="center">
				<label for="cdSubject" class="hidden">국명</label>
				<form:form commandName="groupDeptVo" name="depForm1" id="depForm1" method="post">
				<form:hidden path="cdCode" value="d000000" />
				<form:hidden path="fCode" value="${param.cdCode }" />
				<form:hidden path="fCode2" value="${param.cdCode2 }" />
				<form:hidden path="fCode3" value="${param.cdCode3 }" />
				<form:hidden path="cdDepstep1" value="00" />
				<form:hidden path="cdDepstep2" value="00" />
				<form:hidden path="cdDepstep3" value="00" />
				<input type="hidden" name="cdDepstep" value="1" />
				<form:hidden path="cdUse" />
				<form:hidden path="cdIdx" />
				<%if((session.getAttribute("SesUserPermCd")+"").equals("05010000")){%>
				<form:input path="cdSubject" value="" style="width:80%;"/><br>
				<c:if test="${groupDeptVo.layout ne 'n' }">
				<input type="button" value="등록" class="btn_s on btn_regist" onClick="doGroupDeptReg(this.form)" />
				<input type="button" value="수정" class="btn_s on btn_modify" onClick="doGroupDeptMod(this.form)" />
				<input type="button" value="삭제" class="btn_s btn_caption btn_del" onClick="doGroupDeptRmv(this.form)" />
				</c:if>
				<%}else{%>
				<form:hidden path="cdSubject" value="" style="width:80%;"/>
				<%}%>
				</form:form>
			</td>
			<td class="center">
				<label for="cdSubject" class="hidden">과명</label>
				<form:form commandName="groupDeptVo" name="depForm2" id="depForm2" method="post" >
				<form:hidden path="cdCode" value="d000000" />
				<form:hidden path="fCode" value="${param.cdCode }" />
				<form:hidden path="fCode2" value="${param.cdCode2 }" />
				<form:hidden path="fCode3" value="${param.cdCode3 }" />
				<form:hidden path="cdDepstep1" value="00" />
				<form:hidden path="cdDepstep2" value="00" />
				<form:hidden path="cdDepstep3" value="00" />
				<input type="hidden" name="cdDepstep" value="2" />
				<form:hidden path="cdUse" />
				<form:hidden path="cdIdx" />
				<%if((session.getAttribute("SesUserPermCd")+"").equals("05010000")){%>
				<form:input path="cdSubject" value="" style="width:80%;"/><br>
				<c:if test="${groupDeptVo.layout ne 'n' }">
				<input type="button" value="등록" class="btn_s btn_regist" onClick="doGroupDeptReg(this.form)" />
				<input type="button" value="수정" class="btn_s btn_modify" onClick="doGroupDeptMod(this.form)" />
				<input type="button" value="삭제" class="btn_s btn_del" onClick="doGroupDeptRmv(this.form)" />
				</c:if>
				<%}else{%>
				<form:hidden path="cdSubject" value="" style="width:80%;"/>
				<%}%>
				</form:form>
			</td>
			<td class="center">
				<label for="cdSubject" class="hidden">팀명</label>
				<form:form commandName="groupDeptVo" name="depForm3" id="depForm3" method="post" >
				<form:hidden path="cdCode" value="d000000" />
				<form:hidden path="fCode" value="${param.cdCode }" />
				<form:hidden path="fCode2" value="${param.cdCode2 }" />
				<form:hidden path="fCode3" value="${param.cdCode3 }" />
				<form:hidden path="cdDepstep1" value="00" />
				<form:hidden path="cdDepstep2" value="00" />
				<form:hidden path="cdDepstep3" value="00" />
				<input type="hidden" name="cdDepstep" value="3" />
				<form:hidden path="cdUse" />
				<form:hidden path="cdIdx" />
				<%if((session.getAttribute("SesUserPermCd")+"").equals("05010000")){%>
				<form:input path="cdSubject" value="" style="width:80%;"/><br>
				<c:if test="${groupDeptVo.layout ne 'n' }">
				<input type="button" value="등록" class="btn_s btn_regist" onClick="doGroupDeptReg(this.form)" />
				<input type="button" value="수정" class="btn_s btn_modify" onClick="doGroupDeptMod(this.form)" />
				<input type="button" value="삭제" class="btn_s btn_del" onClick="doGroupDeptRmv(this.form)" />
				</c:if>
				<%}else{%>
				<form:hidden path="cdSubject" value="" style="width:80%;"/>
				<%}%>
				</form:form>
			</td>
		</tr>
	</tbody>
	</table>
</body>
</html>
