<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%-- ------------------------------------------------------------
- 제목 : 관리메뉴설정
- 파일명 : menu_list.jsp
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
	
	/*CKEDITOR.replace('mmHelp', {
		"filebrowserUploadUrl":"/Ckeditor/File_Upload.do"
    });
	CKEDITOR.instances.mmHelp.on('change', function(e){
		doNodeAdd('mmHelp');
	});*/

	$.ajax({
		type: "POST",
		url: "<c:url value='/boffice/sy/menu/MgrMenuListAx.do'/>",
		data : "",
		dataType : "json",
		success:function(data){
			$('#jstree').jstree({
				"core" : {
					"data" : data.menuList
					,"check_callback": true
				}
				,"plugins" : ["dnd"]
			}).bind("loaded.jstree",function(event,data){
				$("#jstree").jstree("select_node", "ul > li:first");
				var Selectednode = $("#menuTree").jstree("get_selected");
				$("#jstree").jstree("open_node", Selectednode, false, true);
				
			}).bind("select_node.jstree", function (event, data){
				if(data.node.id != null || data.node.id != ""){
					$("#nodeId").val(data.node.id);
					if(data.node.original.position == 0){
						$("#nodeId").attr("disabled",true);
					}else{
						$("#nodeId").attr("disabled",false);
					}
				}else{
					$("#nodeId").val("");
				}
				$("#parentId").val(data.node.parent);
				$("#text").val(data.node.text);
				$("#mmDepth").val(data.node.original.mmDepth);
				$("#nodePath").text(data.node.original.mmPath);

				if(data.node.original.mmIdx == null){
					$("#mmIdx").val("자동설정");
					$(".mmHelpRow").hide();
				}else {
					$("#mmIdx").val(data.node.original.mmIdx);
					$("#mmHelpRow").show();
				}

				if(data.node.original.mgrUrl == null){
					data.node.original.mgrUrl = "";
					$("#mgrUrl").val("");
				}else {
					$("#mgrUrl").val(data.node.original.mgrUrl);
				}

				if(data.node.original.publicYn == null){
					data.node.original.publicYn = "N";
					$("#publicYn").prop("checked", false);
				}else {
					if(data.node.original.publicYn == "Y"){
						$("#publicYn").prop("checked", true);
					}else{
						$("#publicYn").prop("checked", false);
					}
				}
				
				if(data.node.parent == 'AD000000'){
					$("#mmImg").show();
					if(data.node.original.mmImg == '' || data.node.original.mmImg == null || data.node.original.mmImg == undefined){
						$("input:radio[name='mmImg']:radio[value='fa-desktop']").prop('checked', true);
					}else{
						$("input:radio[name='mmImg']:radio[value='"+data.node.original.mmImg+"']").prop('checked', true);
					}
				}else{
					$("#mmImg").hide();
					$("input:radio[name='mmImg']").removeAttr('checked');
				}
				
				$("input:checkbox[name='mmMgrSiteCd']").each(function(){
					if((data.node.original.mmMgrSiteCd+",").indexOf($(this).val()+",")>-1){
						$(this).prop('checked', true);
					}else{
						$(this).prop('checked', false);
					}
				});
				
				//매뉴얼
				/*if(data.node.original.mmHelp == null){
					CKEDITOR.instances.mmHelp.setData("");
				}else {
					CKEDITOR.instances.mmHelp.setData(data.node.original.mmHelp);
				}*/
				
			}).bind("move_node.jstree",function(event,data){

			}).bind("rename_node.jstree", function (event, data){
				$("#text").val(data.text);
			});
			$('#jstree').jstree("open_all");
		},
		error: function(xhr, status, error){
            //ajaxError(response);
		}
	});
	
	//버튼 이벤트
    $(".btn_add").click(function(e){
    	e.preventDefault();
    	var tree = $('#jstree').jstree(true);
    	var selected_node = tree.get_selected();
    	if(!selected_node.length){
    		alert("메뉴를 선택해주십시오.");
    		return;
    	}
    	var depth = Number($("#mmDepth").val())+1;
    	tree.deselect_node(selected_node);
    	selected_node = selected_node[0];
    	var	new_selected_node = tree.create_node(selected_node);
    	tree.select_node(new_selected_node);
    	if(new_selected_node){
    		tree.edit(new_selected_node);
    	}
		$(".mmHelpRow").hide();
    	$("#mmIdx").val("자동설정");
    	$("#mgrUrl").val("");
    	$("#mmDepth").val(depth);
    	var jstEdit = $.jstree.reference('#jstree');
    	var node = jstEdit.get_node(new_selected_node);
    	node.original.mmDepth= depth;
    });
    $(".btn_mod").click(function(e){
    	e.preventDefault();
    	if (!confirm("저장하시겠습니까?")){
    		return;
    	}
    	var nodeUpdates = [];
    	var tree = $("#jstree").jstree(true).get_json('#', { 'flat': true });
    	var jstEdit = $.jstree.reference('#jstree');
    	var msg="";
    	var chk=true;
    	for(var i = 0; i < tree.length; i++){
    		var node = jstEdit.get_node(tree[i].id);
    		var mmIdx = node.original.mmIdx;
    		var mgrurl = node.original.mgrUrl;
    		var publicYn = node.original.publicYn;
    		var mmImg = node.original.mmImg;			
    		var mmDepth = node.original.mmDepth;			
    		var mmMgrSiteCd = node.original.mmMgrSiteCd;
    		var mmHelp = node.original.mmHelp;

    		var text = node.original.text;
    		var idChk = node.original.idChk;
    		var mgrUrlChk = node.original.mgrUrlChk;
    		
    		var info="메뉴명 : "+text;
    		if(idChk=="X"){
    			msg+=info+"의 id중복체크를 해주세요!\n";
    			chk=false;
    		}
    		if(idChk=="N"){
    			msg+=info+" id가 중복입니다!\n";
    			chk=false;
    		}
    		if(mgrUrlChk=="X"){
    			msg+=info+"의 URL 중복체크를 해주세요!\n";
    			chk=false;
    		}
    		if(mgrUrlChk=="N"){
    			msg+=info+"URL이 중복입니다!\n";
    			chk=false;
    		}
    		
    		nodeUpdates.push({"id":node.id,"parent":node.parent,"text":node.text,"mmIdx":mmIdx,"mgrUrl":mgrurl,"publicYn":publicYn,"mmImg":mmImg,"mmDepth":mmDepth,"mmMgrSiteCd":mmMgrSiteCd,"mmHelp":mmHelp});
    	}
    	if(!chk){
    		alert(msg);
    		return;
    	}
    	doSaveHierarchy(nodeUpdates);
    });
    $(".btn_del").click(function(e){
    	e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
    	var tree = $('#jstree').jstree(true);
    	var selected_node = tree.get_selected();
    	if(!selected_node.length){
    		alert("삭제할 대상이 없습니다.");
    		return;
    	}
    	tree.delete_node(selected_node);
    	$("#nodeId").val("");
    	$("#parentId").val("");
    	$("#text").val("");
    	$("#mmIdx").val("자동설정");
    	$("#mgrUrl").val("");
    	$("#publicYn").prop("checked", false);
    });
    $(".btn_idCheck").click(function(e){
    	e.preventDefault();
    	
    	var tree = $('#jstree').jstree(true);
    	var selected_node = tree.get_selected();
    	selected_node = selected_node[0];
    	var jstEdit = $.jstree.reference('#jstree');
    	var node = jstEdit.get_node(selected_node);
    	var mmCd = $("#nodeId").val();
    	if(selected_node!=mmCd){
			alert("중복되는 아이디 입니다.");
			node.original.idChk="N";
		}else{
			alert("사용가능한 아이디입니다.");
			node.original.idChk="";
		}
    	
    	/*
    	var ajaxParam = {"mmCd":mmCd};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
    		if(data.result){
    			if(data.resultCnt > 0){
    				alert("중복되는 아이디 입니다.");
    			}else{
    				alert("사용가능한 아이디입니다.");
    			}
    		}else{
    			alert(data.message);
    		}
    	};
    	ajaxAction("<c:url value='/boffice/sy/menu/MgrMenuCntAx.do'/>", ajaxParam, ajaxOption);
    	*/
    });
    $(".btn_urlCheck").click(function(e){
    	e.preventDefault();
    	
    	var tree = $('#jstree').jstree(true);
    	var selected_node = tree.get_selected();
    	selected_node = selected_node[0];
    	var jstEdit = $.jstree.reference('#jstree');
    	var node = jstEdit.get_node(selected_node);
    	var mgrUrl = $("#mgrUrl").val();
    	if(node.original.mgrUrl!=mgrUrl){
			alert("중복되는 URL 입니다.");
			node.original.mgrUrlChk="N";
		}else{
			alert("사용가능한 URL 입니다.");
			node.original.mgrUrlChk="";
		}
    });
    $(".btn_programSearch").click(function(e){
    	e.preventDefault();
    	window.open("<c:url value='/boffice_noDeco/sy/progrm/ProgramListSearch.do'/>",'','scrollbars=yes,width=600,height=600');
    });
    $("#publicYn").click(function(){
    	doNodeAdd('publicYn');
    });
});

function doNodeAdd(val){
	var tree = $('#jstree').jstree(true);
	var selected_node = tree.get_selected();
	selected_node = selected_node[0];
	var jstEdit = $.jstree.reference('#jstree');
	var node = jstEdit.get_node(selected_node);
	if(val == "id"){
		if(selected_node == $("#nodeId").val())
			return;

		node.original.idChk = "X";
		if(doCheckId()){
			return;
		}else{
			tree.set_id(selected_node, $("#nodeId").val());
		}
	}else if(val == "mgrUrl"){
		if(node.original.mgrUrl == $("#mgrUrl").val())
			return;
		
		node.original.mgrUrlChk = "X";
		if(doCheckUrl()){
			return;
		}else{
			node.original.mgrUrl = $("#mgrUrl").val();
		}
	}else{
		tree.set_id(selected_node, $("#nodeId").val());
		tree.set_text(selected_node, $("#text").val());
		/*node.original.mmHelp = CKEDITOR.instances.mmHelp.getData();*/	
		if($("#publicYn").is(':checked')){
			node.original.publicYn = "Y";
		}else{
			node.original.publicYn = "N";
		}
		var inputSiteCd = "";
		node.original.mmImg = $("input:radio[name='mmImg']:checked").val();
		$("input:checkbox[name='mmMgrSiteCd']:checked").each(function(){
			inputSiteCd += $(this).val()+",";
		});
		inputSiteCd = inputSiteCd.substring(0,inputSiteCd.length-1);
		node.original.mmMgrSiteCd = inputSiteCd;
	}
}

function doCheckId(){
	var v =$("#jstree").jstree(true).get_json('#', { 'flat': true });
	var count = 0;
	for(var i = 0; i < v.length; i++){
		var id = v[i].id;
		if(id == $("#nodeId").val()){
			return true;
		}
	}
	return false;
}
function doCheckUrl(){
	var v =$("#jstree").jstree(true).get_json('#', { 'flat': true });
	var jstEdit = $.jstree.reference('#jstree');
	var count = 0;
	for(var i = 0; i < v.length; i++){
		var id = v[i].id;
		var node = jstEdit.get_node(id);
		if(node.original.mgrUrl == $("#mgrUrl").val()){
			return true;
		}
	}
	return false;
}

function doSaveHierarchy(nodeUpdates){
	for(var i = 0; i < nodeUpdates.length; i++){
		var node = nodeUpdates[i];
		var id = node.id;
		var text = node.text;
		if(id !="AD000000" && id.replace(/\s/gi, '') == ""){
			alert("관리자메뉴 하위 노드의 "+i+"번째 의 코드 값이 없습니다.");
			return;
		}
		if(id !="AD000000" && id.replace(/\s/gi, '').length < 8){
			alert(id+"코드 의 길이가 작습니다 max(8),현재("+id.replace(/\s/gi, '').length+")");
			return;
		}
		if(id !="AD000000" && text.replace(/\s/gi, '') == ""){
			alert("관리자메뉴 하위 노드의"+i+"번째 의"+id+"의 이름 값이 없습니다.");
			return;
		}
		if(id !="AD000000" && text.length > 50){
			alert(text +" 의 값의 길이가 넘습니다. 현재("+text.length+")");
			return;
		}
	}
	var ajaxParam = JSON.stringify(nodeUpdates);
	ajaxActionTreeMessage("<c:url value='/boffice/sy/menu/MgrMenuRegAx.do' />", ajaxParam, '저장되었습니다.', true);	
}

function doHelp(){
	var tempmmIdx = $("#mmIdx").val();
	$("#manualFormFrame").attr("src","/boffice_noDeco/sy/menu/MgrMenuHelp.do?mmIdx="+tempmmIdx);
	modalOpen("#modal_manualForm");
	/*var tempNodeId = $("#nodeId").val();
	var modal_id = "modal_help";
	var modal_url = "/boffice_noDeco/sy/menu/MgrMenuHelp.do";
	var modal_param = {"mmCd":tempNodeId};
	var modal_class = ""; //wide, small
	ajaxModal(modal_id, modal_url, modal_param, modal_class);*/
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<form:form commandName="MgrMenuVo" name="MgrMenuVo" method="post">
	<input type="hidden" name="mmDepth" id="mmDepth">
	
	<div class="section divGroup leftTree">
		<div class="left">
			<div class="treeTopBtn">
				<input type="button" value="하위메뉴추가" class="btn_inline btn_add">
				<a href="#" class="btn_inline on btn_mod">변경내용저장</a>
			</div>

			<div id="jstree"></div>
		</div>
		<div class="right">
			<div class="tableTitle">
				<div class="btnArea">
					<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
				</div>
			</div>
			<div class="tableBox">
				<table class="form">
					<colgroup>
						<col class="w15p">
						<col>
						<col class="w15p">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th>메뉴경로</th>
							<td colspan="3" id="nodePath"></td>
						</tr>
						<tr>
							<th><span class="required">*</span> <label for="nodeId">코드</label></th>
							<td>
								<input type="text" id="nodeId" name="nodeId" class="w150" maxlength="8" onblur="doNodeAdd('id');"> 
								<a href="#" class="btn_inline btn_idCheck">중복체크</a>
							</td>
							<th><label for="parentId">부모코드</label></th>
							<td><input type="text" id="parentId" name="parentId" class="w150" readonly></td>
						</tr>
						<tr>
							<th><span class="required">*</span> <label for="text">메뉴명</label></th>
							<td><input type="text" id="text" name="text" class="w150" maxlength="51" onblur="doNodeAdd('text');"></td>
							<th><span class="required">*</span> <label for="mmIdx">고유코드</label></th>
							<td><input type="text" id="mmIdx" name="mmIdx" class="w150" readonly></td>
						</tr>
						<tr>
							<th><span class="required">*</span> <label for="mgrUrl">연결설정(URL)</label></th>
							<td colspan="3">
								<input type="text" id="mgrUrl" name="mgrUrl" class="w300" maxlength="70" onblur="doNodeAdd('mgrUrl');">
								<button class="btn_inline btn_programSearch">검색</button>
								<a href="#" class="btn_inline btn_urlCheck">중복체크</a>
							</td>
						</tr>
						<tr id="mmImg">
							<th><span class="required">*</span> 메뉴아이콘</th>
							<td colspan="3" class="fa_width">
								<input type="radio" name="mmImg" id="fa-desktop" value="fa-desktop" onblur="doNodeAdd('mmImg');"/> <label for="fa-desktop"><i class="fa fa-desktop"></i></label>
								<input type="radio" name="mmImg" id="fa-users" value="fa-users" onblur="doNodeAdd('mmImg');"/> <label for="fa-users"><i class="fa fa-users"></i></label>
								<input type="radio" name="mmImg" id="fa-calendar-check-o" value="fa-calendar-check-o" onblur="doNodeAdd('mmImg');"/> <label for="fa-calendar-check-o"><i class="fa fa-calendar-check-o"></i></label>
								<input type="radio" name="mmImg" id="fa-cog" value="fa-cog" onblur="doNodeAdd('mmImg');"/> <label for="fa-cog"><i class="fa fa-cog"></i></label>
								<input type="radio" name="mmImg" id="fa-file-text-o" value="fa-file-text-o" onblur="doNodeAdd('mmImg');"/> <label for="fa-file-text-o"><i class="fa fa-file-text-o"></i></label>
								<input type="radio" name="mmImg" id="fa-bar-chart" value="fa-bar-chart" onblur="doNodeAdd('mmImg');"/> <label for="fa-bar-chart"><i class="fa fa-bar-chart"></i></label>
								<input type="radio" name="mmImg" id="fa-comments" value="fa-comments" onblur="doNodeAdd('mmImg');"/> <label for="fa-comments"><i class="fa fa-comments"></i></label>
								<br/>
								<input type="radio" name="mmImg" id="fa-database" value="fa-database" onblur="doNodeAdd('mmImg');"/> <label for="fa-database"><i class="fa fa-database"></i></label>
								<input type="radio" name="mmImg" id="fa-fax" value="fa-fax" onblur="doNodeAdd('mmImg');"/> <label for="fa-fax"><i class="fa fa-fax"></i></label>
								<input type="radio" name="mmImg" id="fa-folder-open-o" value="fa-folder-open-o" onblur="doNodeAdd('mmImg');"/> <label for="fa-folder-open-o"><i class="fa fa-folder-open-o"></i></label>
								<input type="radio" name="mmImg" id="fa-map" value="fa-map" onblur="doNodeAdd('mmImg');"/> <label for="fa-map"><i class="fa fa-map"></i></label>
								<input type="radio" name="mmImg" id="fa-mobile" value="fa-mobile" onblur="doNodeAdd('mmImg');"/> <label for="fa-mobile"><i class="fa fa-mobile"></i></label>
								<input type="radio" name="mmImg" id="fa-picture-o" value="fa-picture-o" onblur="doNodeAdd('mmImg');"/> <label for="fa-picture-o"><i class="fa fa-picture-o"></i></label>
								<input type="radio" name="mmImg" id="fa-search" value="fa-search" onblur="doNodeAdd('mmImg');"/> <label for="fa-search"><i class="fa fa-search"></i></label>
								<br/>
								<input type="radio" name="mmImg" id="fa-share-alt" value="fa-share-alt" onblur="doNodeAdd('mmImg');"/> <label for="fa-share-alt"><i class="fa fa-share-alt"></i></label>
								<input type="radio" name="mmImg" id="fa-sitemap" value="fa-sitemap" onblur="doNodeAdd('mmImg');"/> <label for="fa-sitemap"><i class="fa fa-sitemap"></i></label>
							</td>
						</tr>
						<tr>
							<th>공통메뉴</th>
							<td colspan="3"><input type="checkbox" id="publicYn" name="publicYn" class="useToggle"> <label for="publicYn">사용</label></td>
						</tr>
						<tr>
							<th>관리사이트</th>
							<td colspan="3">
								<c:set var="itemList" value="${cmm:getSiteList()}" />
								<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
								<c:set var="mgrSiteCd" value="${CmsBbsVo.mgrSiteCd},"/>
								<c:set var="selectSiteCd" value="${itemInfo.siteCd},"/>
									<input type="checkbox" id="mmMgrSiteCd_<c:out value="${itemInfo.siteCd}"/>" name="mmMgrSiteCd" value="<c:out value="${itemInfo.siteCd}"/>" onchange="doNodeAdd('mmMgrSiteCd')"/> 
									<label for="mmMgrSiteCd_<c:out value="${itemInfo.siteCd}"/>"><c:out value="${itemInfo.siteNm}"/></label>
								<c:if test="${(status.index+1) % 6 == 0}"><br/></c:if>
								</c:forEach>
							</td>
						</tr>
						<tr class="mmHelpRow">
							<th><label for="mmHelp">매뉴얼</label></th>
							<td colspan="3">
								<!-- <textarea id="mmHelp" name="mmHelp" class="w100p h250"><c:out value="${MgrMenuVo.mmHelp}"/></textarea> -->
								<a href="javascript:doHelp();" class="btn_inline btn_setting">매뉴얼 관리</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tableDesc">
				* 수정, 삭제를 한 후에 [저장]버튼을 누르셔야 변경하신 내용이 적용됩니다.
			</div>
		</div>
	</div>
	</form:form>
	
	<div id="modal_manualForm" class="modalWrap">
		<div class="modalTitle">
			<h3>매뉴얼 관리</h3>
			<a href="#" class="btn_modalClose">모달팝업닫기</a>
		</div>
		<div class="modalContent">
			<iframe id="manualFormFrame" style="width:100%;height:540px;border:0px none;"></iframe>
		</div>
	</div>
</body>
</html>