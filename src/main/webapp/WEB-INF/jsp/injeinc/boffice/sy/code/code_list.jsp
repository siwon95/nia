<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%
String searchNodeId = "00000000";

if(request.getParameter("nodeId") != null){
	String tempSearchNodeId = "";
	tempSearchNodeId = request.getParameter("nodeId").replaceAll("null","");
	if(!tempSearchNodeId.trim().equals("")){
		searchNodeId = tempSearchNodeId;
	}
}
%>
<%-- ------------------------------------------------------------
- 제목 : 공통코드
- 파일명 : code_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
 $(function(){
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	doJsTreeOpen();
	
	//버튼이벤트
	$(".btn_add").click(function(e){
    	e.preventDefault();
		modalOpen("#modal_codeRegist");
	});
	$(".btn_del").click(function(e){
    	e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		$("#SyCodeVo input[name='mode']").val("delete");
		$("#SyCodeVo").submit();
	});
});
 
function doJsTreeOpen(){
	 $.ajax({
		url : "<c:url value='/boffice/sy/code/Code_ListAx.do'/>"
		, data : ""
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			$('#jstree').jstree({
				"core":{"data":data.treeList, "check_callback":true},
				"default":{draggable:false} //,"plugins" : ["dnd","wholerow","search"]			   
			}).bind("loaded.jstree",function(event,data){
				$('#jstree').jstree("open_node","<%=searchNodeId%>");
				$('#jstree').jstree("select_node","<%=searchNodeId%>");
			}).bind("select_node.jstree", function (event, data) {
				doTextBind(data);
			}).bind("search.jstree",function(event,data){
				var ref = $('#jstree').jstree(true);
				var sel = ref.select_node(data.res[0]);
			});
 		},error : function(code, msg, error) {
            //ajaxError(response);
 		}		
	});
}

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	if(f.cmmCode.idChk.value == "N"){
		alert("코드 중복 체크를 해주십시요!");
		return false;
	}
	return true;
}

function doCheckId(){
	document.cmmCode.idChk.value="N";
	var nodeUpdates = [];
	var v =$("#jstree").jstree(true).get_json('#', { 'flat': true });
	 jstEdit = $.jstree.reference('#jstree');
	var nodes = [];            
	for(var j=0;j<v.length;j++){
		nodes.push(v[j].id);
	}
	var codeValue = document.cmmCode.code.value;
	if(codeValue==""){
		alert("코드값을 넣어 주십시요!");
		document.cmmCode.code.focus();
		return;
	}
	var idChk = "Y";
    for(var i=0;i<nodes.length;i++){
    	var node = jstEdit.get_node(nodes[i]);
    	if(node.parent == "#"){
    		node.parent = 0;
    	}
    	if(codeValue == node.id){
    		idChk="N";
    	}
    }
    if(idChk == "N"){
		alert("이미 존재하는 코드 입니다!");
		document.cmmCode.idChk.value = "N";
		return;
    }else{
    	alert("사용가능한 코드 입니다.");
    	document.cmmCode.idChk.value = "Y";
    	return;
    }
}

function doSaveHierarchy(node){
	var v =$("#jstree").jstree(true).get_json('#', { 'flat': true });
	var nodes = [];            
	for(var j=0;j<v.length;j++){
		var node2 = v[j].id;
		var nodetext = v[j].text;
		if(nodetext!="공통코드" && node2 ==""){
			alert(nodetext+"의 코드값이 없습니다.");
			return;
		}else if(nodetext!="공통코드" && nodetext ==""){
			alert(nodetext+"의 코드명이 없습니다.");
			return;
		}
	}
	var stringify = JSON.stringify(node);
	$.ajax({  
	    type: "POST",
	    url: "<c:url value='/boffice/sy/code/Code_FormAx.do'/>",
	    data: stringify,
	    dataType: "json",
	    contentType : 'application/json; charset=utf-8',
	    success: function(data) {
	     location.reload();
	
	    },
	    error: function(req, status, error) {
	    	var str = req.status + " : " + status + " : " + error;
	    	alert(str);
	    }
	});
}

function doTextBind(data){
	document.SyCodeVo.nodeId.value = data.node.id;
	document.SyCodeVo.parentId.value = data.node.parent;
	document.SyCodeVo.text.value = data.node.text;
	document.cmmCode.codeUpr.value = data.node.id;
}

function doNodeAdd(){
	var ref = $('#jstree').jstree(true),
	sel = ref.get_selected();
	sel = sel[0];
	ref.set_id(sel,document.SyCodeVo.nodeId.value);
	ref.set_text(sel,document.SyCodeVo.text.value);
}

function doJstreeSearch(){
	 var ref = $('#jstree').jstree(true);
	 var sel_node = ref.get_selected();
	 ref.deselect_node(sel_node); 
	 var text = document.SyCodeVo.search.value;
	 var v = $("#jstree").jstree("search", text);
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section divGroup leftTree">
		<div class="left">
			<div class="treeTopBtn">
				<input type="button" value="하위코드추가" class="btn_inline btn_add">
			</div>
			<div id="jstree"></div>
		</div>
		<div class="right">
			<form:form commandName="SyCodeVo" name="SyCodeVo" action="/boffice/sy/code/Code_Save.do">
			<form:hidden path="useYn" />
			<input type="hidden" name="mode" value="modify"/>
			<input type="hidden" name="position"/>
			<div class="tableTitle">
				<div class="btnArea">
					<input type="submit" value="저장" class="btn_inline on btn_mod">
					<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
				</div>
			</div>
			<div class="tableBox">
				<table class="form">
					<caption></caption>
					<colgroup>
						<col class="w15p">
						<col>
					</colgroup>
					<tbody>
						<!-- <tr>
							<th scope="row">주</th>
							<td><input type="text" name="search" id="search"/><a href="javascript:doJstreeSearch();" class="btn1">검색</a></td>
						</tr> -->
						<tr>
							<th scope="row"><label for="nodeId">코드</label></th>
							<!-- <a href="javascript:doCheckId();" class="btn2">중복체크</a> -->
							<td><form:input path="nodeId" id="nodeId" maxlength="50" readonly="true" cssClass="w150" /></td>
						</tr>
						<tr>
							<th scope="row"><label for="parentId">부모코드</label></th>
							<td><form:input path="parentId" id="parentId" maxlength="50" readonly="true" cssClass="w150" /></td>
						</tr>
						<tr>
							<th scope="row"><label for="text">코드명</label></th>
							<td><form:input path="text" id="text" maxlength="51" onblur="doNodeAdd();" class="required w150" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			</form:form>
		</div>
	</div>
	
<!-- ============================== 모달영역 ============================== -->
<div id="modal_codeRegist" class="modalWrap">
	<div class="modalTitle">
		<h3>코드추가</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<form name="cmmCode" method="post" action="/boffice/sy/code/Code_Save.do" onsubmit="return doForm(this);">
		<input type="hidden" name="idChk" value="N"/>
		<input type="hidden" name="mode" value="write"/>
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col class="w15p">
					<col>
				</colgroup>
				<tbody>
					<!-- <tr>
						<th scope="row">주</th>
						<td><input type="text" name="search" id="search"/><a href="javascript:doJstreeSearch();" class="btn1">검색</a></td>
					</tr> -->
					<tr>
						<th scope="row"><label for="codeUpr">부모코드</label></th>
						<td><input type="text" id="codeUpr" name="codeUpr" maxlength="50" readonly="readonly"/></td>
					</tr>
					<tr>
						<th scope="row"><label for="code">코드</label></th>
						<td><input type="text" name="code" id="code" maxlength="50"/>
							 <a href="javascript:doCheckId();" class="btn_inline btn_check">중복체크</a>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="codeName">코드명</label></th>
						<td><input type="text" name="codeName" id="codeName" maxlength="51"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<input type="submit" value="확인" class="btn_m on">
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
		</form>
	</div>
</div>
<!-- ============================== //모달영역 ============================== -->

</body>
</html>
