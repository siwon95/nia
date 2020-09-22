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
<title>게시판메뉴관리</title>
<style type="text/css">
	#jstree {width:100%; height:606px; margin-bottom:1px; overflow-y:scroll;}
</style>
<script type="text/javascript">
//<![CDATA[
	function doJsTreeOpen() {
		
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/sy/board/CmsBbsListAx.do'/>",
			data : "",
			dataType : "json",
			success:function(data) {
				
				if(data.result) {
					
					$('#jstree').jstree({
						"core" : {
							"data" : data.boardList
							,"check_callback": true
						}
						,"plugins" : ["dnd", "search"]
					}).bind("loaded.jstree",function(event,data) {
						
					}).bind("select_node.jstree", function (event, data) {
						doTextBind(data);	
					}).bind("move_node.jstree",function(event,data) {
	
					}).bind("search.jstree",function(event,data){
						var tree = $('#jstree').jstree(true);
						var sel = tree.select_node(data.res[0]);
					});
					
					$('#jstree').jstree("open_all");
				
				}else{
					alert(data.message);
				}
			
			},
			error: function(xhr, status, error) {
				alert(xhr+"=="+status+"=="+error);
			}
		});
	}

	function doTextBind(data){
		if(data.node.id != null || data.node.id != "") {
			$("#nodeId").val(data.node.id);
			if(data.node.original.position == 0 || data.node.original.position == 1){
				$("#nodeId").attr("disabled",true);
			}else{
				$("#nodeId").attr("disabled",false);
			}
		}else{
			$("#nodeId").val("");
		}
		
		$("#parentId").val(data.node.parent);
		$("#text").val(data.node.text);
		$("#cbDepth").val(data.node.original.cbDepth);
		
		if(data.node.original.cbIdx == null) {
			$("#cbIdx").val("자동설정");
		}else {
			$("#cbIdx").val(data.node.original.cbIdx);
		}

		if(data.node.original.publicYn == null) {
			data.node.original.publicYn = "N";
			$("#publicYn").prop("checked", false);
		}else {
			if(data.node.original.publicYn == "Y") {
				$("#publicYn").prop("checked", true);
			}else{
				$("#publicYn").prop("checked", false);
			}
		}

		if(data.node.original.cbUse == null) {
			data.node.original.cbUse = "Y";
			$("input[name='cbUse']:radio[value='Y']").prop('checked', true);
		}else {
			if(data.node.original.cbUse == "Y") {
				$("input[name='cbUse']:radio[value='Y']").prop('checked', true);
			}else{
				$("input[name='cbUse']:radio[value='N']").prop('checked', true);
			}
		}
	}
	
	function doCreate() {
		var tree = $('#jstree').jstree(true);
		var selected_node = tree.get_selected();

		if(!selected_node.length) {
			alert("메뉴를 선택해주십시오.");
			return;
		}
		
		var depth = Number($("#cbDepth").val())+1;
		tree.deselect_node(selected_node);
		selected_node = selected_node[0];
		
		var new_selected_node = tree.create_node(selected_node);
		
		tree.select_node(new_selected_node);
		
		if(new_selected_node) {
			tree.edit(new_selected_node);
		}

		$("#cbIdx").val("자동설정");
		$("input[name='cbUse']:radio[value='Y']").prop('checked', true);
		
		$("#cbDepth").val(depth);
		
		var jstEdit = $.jstree.reference('#jstree');
		var node = jstEdit.get_node(new_selected_node);
		node.original.cbDepth= depth;
	}

	function doDelete() {
		var tree = $('#jstree').jstree(true);
		var selected_node = tree.get_selected();
		
		if(!selected_node.length) {
			alert("삭제할 대상이 없습니다.");
			return;
		}
		
		tree.delete_node(selected_node);

		$("#nodeId").val("");
		$("#parentId").val("");
		$("#text").val("");
		$("#cbIdx").val("자동설정");
		$("#publicYn").prop("checked", false);
		$("input[name='cbUse']:radio[value='Y']").prop('checked', true);
	}

	function doNodeAdd(val) {
		
		var tree = $('#jstree').jstree(true);
		var selected_node = tree.get_selected();
		selected_node = selected_node[0];

		if(val == "id") {
			
			if(doCheckId()) {
				return;
			}else{
				tree.set_id(selected_node, $("#nodeId").val());
			}
			
		}else{
			
			var jstEdit = $.jstree.reference('#jstree');
			tree.set_id(selected_node, $("#nodeId").val());
			tree.set_text(selected_node, $("#text").val());
			var node = jstEdit.get_node(selected_node);
			
			node.original.cbUse = $("input[name='cbUse']:checked").val();
			
			if($("#publicYn").is(':checked')) {
				node.original.publicYn = "Y";
			}else{
				node.original.publicYn = "N";
			}
			
		}
	}

	function doCheckId() {
		
		var v =$("#jstree").jstree(true).get_json('#', { 'flat': true });
		var count = 0;
		for(var i = 0; i < v.length; i++) {
			var id = v[i].id;
			if(id == $("#nodeId").val()) {
				return true;
			}
		}
		
		return false;
	}
	
	function doCmsBbsCntAx() {
		
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/sy/board/CmsBbsCntAx.do'/>",
			data : {"cbCd":$("#nodeId").val()},
			dataType : "json",
			success:function(data) {
				if(data.result) {
					if(data.resultCnt > 0) {
						alert("중복되는 아이디 입니다.");
					}else{
						alert("사용가능한 아이디입니다.");
					}
				}else{
					alert(data.message);
				}
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
	}
	
	function doSave() {
		if (!confirm("저장하시겠습니까?")) return;
		
		var nodeUpdates = [];
		
		var tree = $("#jstree").jstree(true).get_json('#', { 'flat': true });
			
		var jstEdit = $.jstree.reference('#jstree');
		
		for(var i = 0; i < tree.length; i++) {
			var node = jstEdit.get_node(tree[i].id);
			var cbIdx = node.original.cbIdx;
			var publicYn = node.original.publicYn;
			var cbUse = node.original.cbUse;
			var cbDepth = node.original.cbDepth;
			nodeUpdates.push({"id":node.id,"parent":node.parent,"text":node.text,"cbIdx":cbIdx,"publicYn":publicYn,"cbUse":cbUse,"cbDepth":cbDepth});
		}
		
		doSaveHierarchy(nodeUpdates);
	}

	function doSaveHierarchy(nodeUpdates) {
		
		var stringify = JSON.stringify(nodeUpdates);
		
		$.ajax({  
			type: "POST",
			url: "<c:url value='/boffice/sy/board/CmsBbsRegAx.do' />",
			data: stringify,
			dataType: "json",
			contentType : 'application/json; charset=utf-8',
			success: function(data) {
				alert("저장되었습니다.");
				location.reload(); 
			},
			error: function(req, status, error) {
				var str = req.status + " : " + status + " : " + error;
				alert(str);
			}
		});
		
	}
	
	function doSearchName() {
		var tree = $('#jstree').jstree(true);
		var sel_node = tree.get_selected();
		tree.deselect_node(sel_node); 
		$("#jstree").jstree("search", $("#searchName").val());
	}
	
	$(document).ready(function() {
		doJsTreeOpen();
	});
//]]>
</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;" >
<form:form commandName="MgrMenuVo" name="MgrMenuVo" method="post">

			<!-- 컨텐츠 영역 -->
			
			<div id="contents">
				<div class="f_left" style="width:26%;">
					<div>
						<input type="text" id="searchName" name="searchName" size="25"/>
						<button class="btn2 btn_input" onclick="doSearchName(); return false;"><i class="fa fa-search"></i> 검색</button>
					</div>
					<div class="linebox" style="padding:0;margin-top:10px;">
						<div id="jstree"></div>
					</div>
				</div>
				<div class="f_right" style="width:72%;">
					<div id="jstreeText">
						<table class="write" width="100%">
							<colgroup>
								<col width="25%" />
								<col width="*" />
							</colgroup>
							<tbody>
								<input type="hidden" name="cbDepth" id="cbDepth" />
								<tr>
									<td colspan="2"><b>게시판메뉴관리</b></td>
								</tr>
								<tr>
									<th><span class="red">*</span> <label for="nodeId">코드</label></th>
									<td>
										<input type="text" id="nodeId" name="nodeId" maxlength="8" onblur="doNodeAdd('id');" /> 
										<a href="javascript:doCmsBbsCntAx();" class="btn btn_input">중복체크</a>
									</td>
								</tr>
								<tr>
									<th><label for="parentId">부모코드</label></th>
									<td><input type="text" id="parentId" name="parentId" readonly /></td>
								</tr>
								<tr>
									<th><span class="red">*</span> <label for="text">게시판 이름</label></th>
									<td><input type="text" id="text" name="text" size="50" maxlength="51" onblur="doNodeAdd('text');" /></td>
								</tr>
								<tr>
									<th><span class="red">*</span> <label for="cbIdx">게시판 고유코드(cbIdx)</label></th>
									<td><input type="text" id="cbIdx" name="cbIdx" size="6" readonly="readonly" /> <span class="red">수정불가. 자동설정됩니다.</span></td>
								</tr>
								<tr>
									<th><label for="publicYn">공통권한</label></th>
									<td><input type="checkbox" id="publicYn" name="publicYn" onclick="doNodeAdd('publicYn');" /></td>
								</tr>
								<tr>
									<th>사용유무</th>
									<td>
										<input type="radio" id="cbUse1" name="cbUse" value="Y" onclick="doNodeAdd('cbUse');" /> <label for="cbUse1">사용</label>
										<input type="radio" id="cbUse2" name="cbUse" value="N" onclick="doNodeAdd('cbUse');" /> <label for="cbUse2">미사용</label>
									</td>
								</tr>
								<input type="hidden" name="cbDepth" id="cbDepth" value="" />
							</tbody>
						</table>
					</div>
					<div class="btn_zone">
						<a href="javascript:doCreate();" class="btn3">추가</a>
						<a href="javascript:doDelete();" class="btn2">삭제</a>
						<a href="javascript:doSave();" class="btn1">저장</a>
					</div>
				</div>
			</div>
			<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
