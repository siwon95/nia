<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
  * Dept_List 
  * 개발일자: 2015.06.05 
  * 개발자 : 이동열
  * 개발내용 : 공통코드 트리 
  * 개발환경 : jstree 3.1.1
-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Basic Board List</title>
<script type="text/javascript">
var chknew = "";
var chkreg = "";
var rowData = "";
var nodeStatus = [];
var nodeStatusChk = [];
function doJsTreeOpen(){
	
	 $.ajax({
			url : "<c:url value='/boffice/sy/reservation/Reservation_ListAx.do'/>"
			, data : ""
			, type : "POST"
			, dataType : "json"
			, async : false
			, success : function(data) {
				
				
				$('#jstree').jstree({
					"core" : {
						
						 "data" : data.treeList
						 ,"check_callback": true
						            
					}
				    ,"plugins" : ["dnd","wholerow"]
				 				   
				}).bind("loaded.jstree",function(event,data){
					$(this).jstree("open_all");

				}).bind("select_node.jstree", function (event, data) {
					doTextBind(data);
 				}).bind("move_node.jstree",function(event,data){

				});
				
	 		},error : function(code, msg, error) {
	 				var str = code.status + " : " + msg + " : " + error;
	 				console.log(str);
	 		}
				
	});
}



function doCreate() {
	chknew = "new";
	var ref = $('#jstree').jstree(true);
	var sel_node = ref.get_selected();
	var	new_sel_node = "";

	if(!sel_node.length) { 
	   alert("메뉴를 선택해주십시오.");
	    return;
	}
	
	ref.deselect_node(sel_node); 
	
	sel_node = sel_node[0];
	
	new_sel_node = ref.create_node(sel_node);
	
	ref.select_node(new_sel_node); 

	
	if(new_sel_node) {
		ref.edit(new_sel_node);
	}
	
}

function doRename() {
	var ref = $('#jstree').jstree(true),
		sel = ref.get_selected();
	if(!sel.length) { return false; }
	sel = sel[0];
	ref.edit(sel);
}

function doDelete() {
	var ref = $('#jstree').jstree(true),
		sel = ref.get_selected(),
		sel2 = ref.get_selected();
	if(!sel.length) { return false; }
	var nodeDel = ref.get_node(sel);
	ref.delete_node(sel);
	
	document.ReservationVo.nodeId.value = "";
	document.ReservationVo.parentId.value ="";
	document.ReservationVo.text.value = "";
    document.ReservationVo.mgrUrl.value = "";
    document.ReservationVo.usrUrl.value = "";
    document.ReservationVo.useYn.value = "";
}


function doSave() {
	
	if (!confirm("저장하시겠습니까")) {
		
		return;
	}
	if(chknew == "new" && (chkreg == "" || chkreg == "N")){
		alert("코드 사용여부체크 하십시오");
		return;
	}
	var nodeUpdates = [];
	
	var v =$("#jstree").jstree(true).get_json('#', { 'flat': true });
		
	jstEdit = $.jstree.reference('#jstree');
	 
	var nodes = [];            
	for(var j=0;j<v.length;j++){
		 nodes.push(v[j].id);            
	}
     
    for(var i=0;i<nodes.length;i++){
    	 var node = jstEdit.get_node(nodes[i]);
    	 if(node.parent == "#"){
    		 node.parent = 0;
    	 }
    	 var mgrurl = node.original.mgrUrl;
    	 var usrurl = node.original.usrUrl;
    	 var useYn = node.original.useYn;
    	 nodeUpdates.push({"id":node.id,"parent":node.parent,"text":node.text,"mgrUrl":mgrurl,"usrUrl":usrurl,"useYn":useYn});            
    }
    
 	doSaveHierarchy(nodeUpdates);
}


function doSaveHierarchy(node){
	
	var v = node;
	for(var i=0;i<v.length;i++){
	   var id = v[i].id;
	   var text = v[i].text;
	   if(id !="RM000000" && id.replace(/\s/gi, '') == ""){
		   alert("예약관리 하위 노드의 "+i+"번째 의 코드 값이 없습니다.");
		   return;
	   }
	   
	   if(id !="RM000000" && id.replace(/\s/gi, '').length < 8){
		   alert(id+"코드 의 길이가 작습니다 max(8),현재("+id.replace(/\s/gi, '').length+")");
		   return;
	   }
	   
	   if(id !="RM000000" && text.replace(/\s/gi, '') == ""){
		   alert("예약관리 하위 노드의"+i+"번째 의"+id+"의 이름 값이 없습니다.");
		   return;
	   }
	   
	   if(id !="RM000000" && text.length > 50){
		   alert(text +" 의 값의 길이가 넘습니다. 현재("+text.length+")");
		   return;
	   }
	   
	   
	}
	
	var stringify = JSON.stringify(node);
	
	$.ajax({  
	            type: "POST",
	            url: "<c:url value='/boffice/sy/reservation/Reservation_FormAx.do'/>",
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
  
  document.ReservationVo.nodeId.value = data.node.id;
  document.ReservationVo.parentId.value = data.node.parent;
  document.ReservationVo.text.value = data.node.text;	
  
  if(data.node.original.mgrUrl == null){
	  data.node.original.mgrUrl = "";
  }else {
	  document.ReservationVo.mgrUrl.value = data.node.original.mgrUrl;
  }
  
  if(data.node.original.usrUrl == null){
	  data.node.original.usrUrl = "";
  }else {
	  document.ReservationVo.usrUrl.value = data.node.original.usrUrl;
  }
  
  if(data.node.original.useYn == null){
	  document.ReservationVo.useYn.value = "N";
	  data.node.original.useYn ="N";
  }else {
	  document.ReservationVo.useYn.value = data.node.original.useYn;
  }
    
  var chk = document.getElementById("useYn");
  if(document.ReservationVo.useYn.value =="Y"){
	  chk.checked =true;
  }else{
	  chk.checked =false;
  }
	  
  
  
}

function doCheckId(){
	
	var v =$("#jstree").jstree(true).get_json('#', { 'flat': true });
    var count = 0;
    for(var i=0;i<v.length;i++){
    	var id=v[i].id;
    	if(id == document.ReservationVo.nodeId.value){
    		count = count+1;
    		
    		if(count > 0){
    			return;
    		}
    	}
    }
    
    doNodeAdd();
}

function doNodeAdd(){
	
	var jstEdit = $.jstree.reference('#jstree');
	
	var ref = $('#jstree').jstree(true),
	sel = ref.get_selected();
	sel = sel[0];
	
	var node = jstEdit.get_node(sel); 
    console.log(node);
    
	node.id = document.ReservationVo.nodeId.value;
	node.text = document.ReservationVo.text.value;
	ref.set_id(sel,node.id);
	ref.set_text(sel,node.text);
	
	node.original.mgrUrl = document.ReservationVo.mgrUrl.value;
	node.original.usrUrl = document.ReservationVo.usrUrl.value;
	var chk = document.getElementById("useYn");
	if(chk.checked == true){
		node.original.useYn = "Y";
	}else{
		node.original.useYn = "N";
	}
	
	
}

function doListReservationAx() {
	if($('#nodeId').val() == ""){
		alert("코드를 입력해 주십시오.");
		return;
	}else{
		$.ajax({
			type: "GET",
			url: "<c:url value='/boffice/sy/reservation/Reservation_ViewAx.do'/>",
			data : "searchCode=" + $('#nodeId').val(),
			dataType : "json",
			success:function(object){
				chkreg = object.ReservationAxValue;
				if(chkreg.indexOf("중복") > 0){
					chkreg = "N";
				}else{
					chkreg = "Y";
				}
				alert(object.ReservationAxValue);
			
			 },
	        error: function(xhr,status,error){
	        	alert(status);
	        }
		});	
	}
			
}
</script>
<SCRIPT type="text/javascript">
/**
 * 페이지의 모든 요소(이미지)가 로딩시 구동
 */
$(window).load(function() {
	
	doJsTreeOpen();

});


</SCRIPT>
<style type="text/css">
 #jstree {
 	float:left;
 	/*
	 position:absolute;
	 left:10px;*/
	 width:300px;
	 height:500px;
	 margin-bottom:1px;
	 overflow-x:scroll;
	 overflow-y:scroll;
 }
 #jstreeText{float:left;margin-left:50px;}
</style>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;" >
<form:form commandName="ReservationVo" name="ReservationVo" >
<form:hidden path="nodeParam" />
	<!-- 컨텐츠 영역 -->

<div id="contents" style="position:relative;">
	<div id="jstree" ></div>
	<div id="jstreeText">
		<table class="write">
		<tr>
			<td colspan="2"><b>예약관리</b></td>
		</tr>
		<tr>
			<td>* <label for="nodeId">코드</label></td>
			<td><form:input path="nodeId" id="nodeId" maxlength="8" onblur="doCheckId();" onkeydown="javascript:chkreg='';chknew='new';"/>&nbsp;<input type="button" onclick="doListReservationAx()" value="사용여부체크" /></td>
		</tr>
		<tr>
			<td><label for="parentId">부모코드</label></td>
			<td><form:input path="parentId" id="parentId" readonly="true" /></td>
		</tr>
		<tr>
			<td>* <label for="text">이름</label></td>
			<td><form:input path="text" id="text" maxlength="51" onblur="doNodeAdd();"/></td>
		</tr>
		<tr>
			<td>* <label for="mgrUrl">관리자URL</label></td>
			<td><form:input path="mgrUrl" id="mgrUrl" maxlength="51" onblur="doNodeAdd();"/></td>
		</tr>
		<tr>
			<td><label for="usrUrl">사용자URL</label></td>
			<td><form:input path="usrUrl" id="usrUrl" maxlength="51" onblur="doNodeAdd();"/></td>
		</tr>
		<tr>
			<td>* <label for="useYn">사용</label></td>
			<td><input type="checkbox" id="useYn" onclick="doNodeAdd();"/> 사용</td>
		</tr>
		<tr>
			<td colspan="2">
					<a href="javascript:doCreate();" class="btn2">추가</a>
					<a href="javascript:doDelete();" class="btn2">삭제</a>
					<a href="javascript:doSave();" class="btn1">저장</a>
			</td>
		</tr>
		</table>
	</div>
</div>
<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
