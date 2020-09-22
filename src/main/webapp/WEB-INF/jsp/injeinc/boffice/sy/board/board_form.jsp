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
<script type="text/javascript">

function dojsTreeOpen(){
	
	 $.ajax({
			url : "<c:url value='/boffice/sy/board/Board_FormAx.do'/>"
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
				    ,"plugins" : ["dnd","wholerow","search"]
				 				   
				}).bind("loaded.jstree",function(event,data){
					$(this).jstree("open_all");
				}).bind("select_node.jstree", function (event, data) {
					//console.log(data);
					
// 					var level = data.node.parents.length;
// 					document.BoardVo.level.value = level;
// 					if(level == "1" || level == "2"){
// 						document.getElementById("testDiv").style.display = "none";
// 					}else{
// 						document.getElementById("testDiv").style.display = "block";
// 					}
					var gbn = "";
					if(data.node.id == "j1_1"){
						gbn = "C";
					}else{
						//gbn = "U";
						gbn = "U";
					}
					
					if(data.node.parent == "#"){
						doTextBindVal();
					}else{
						doTextBind(data, gbn);
					}
				}).bind("move_node.jstree",function(event,data){

				}).bind("search.jstree",function(event,data){
					var ref = $('#jstree').jstree(true);
					var sel = ref.select_node(data.res[0]);
					
				});
				
	 		},error : function(code, msg, error) {
	 				var str = code.status + " : " + msg + " : " + error;
	 				//console.log(str);
	 		}
	});
}

function doTextBindVal(){
	document.BoardVo.cbIdx.value = "";
	document.BoardVo.cbCd.value = "";
	document.BoardVo.cbUprCd.value = "";
	document.BoardVo.cbName.value = "";
	document.BoardVo.mgrUrl.value = "";
	document.BoardVo.usrUrl.value = "";
	$("#btIdx").val("0");
}

function doTextBind(data, gbn){
	document.BoardVo.flag.value = gbn;
	if(gbn == "U"){
		selectView(data.node.id);
// 		selectView2(data.node.id);
	} else {
		document.BoardVo.cbCd.value = data.node.id;
		document.BoardVo.cbUprCd.value = data.node.parent;
		document.BoardVo.cbName.value = data.node.text;	
	}
	
	if(data.node.original.useYn == null){
		data.node.original.useYn = "";
	}else{
		document.BoardVo.useYn.value = data.node.original.useYn;
	}
	
	if(data.node.original.cbIdx == null){
		data.node.original.cbIdx = "";
	}else{
		//document.BoardVo.cbIdx.value = data.node.original.cbIdx;
		document.getElementById("cbIdxVal").value = data.node.original.cbIdx;
	}
}

function selectView(cbCd){
	var param = "";
	param = "cbCd=" + cbCd;
	$.ajax({
		url : "<c:url value='/boffice/sy/board/Board_ViewAx.do'/>"
		, data : param
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			
			var rows = data.treeList;
			
			for (var i=0; i < rows.length; i++) {
				var row = data.treeList[i];
				
				var cbIdx   			= row.cbIdx;
				var cbCd    			= row.cbCd;
				var cbUprCd 			= row.cbUprCd;
				var cbName  			= row.cbName;
				var mgrUrl  			= row.mgrUrl;
				var usrUrl  			= row.usrUrl;
				var ordNo   			= row.ordNo;
				var useYn   			= row.useYn;
				var bbsTempletGbn		= row.bbsTempletGbn;
				var bbsTempletNo        = row.bbsTempletNo;
				var bbsTempletFileName	= row.bbsTempletFileName;	
				var bbsApprYn		    = row.bbsApprYn;
				var categoryUseYn		= row.categoryUseYn;
				var bbsFileCnt		    = row.bbsFileCnt;
				var listGbn   			= row.listGbn;
				var readGbn   			= row.readGbn;
				var writeGbn  			= row.writeGbn;
				var modGbn    			= row.modGbn;
				var delGbn    			= row.delGbn;
				var answerGbn 			= row.answerGbn;
				var btIdx     			= row.btIdx;
				var mwRKd               = row.mwRKd;
				var itemCode			= row.itemCode;
				var mListYn				= row.mListYn;
				var editorYn			= row.editorYn;
				
				$("#cbIdx").val(cbIdx);
				$("#cbCd").val(cbCd);
				$("#cbUprCd").val(cbUprCd);
				$("#cbName").val(cbName);
				$("#mgrUrl").val(mgrUrl);
				$("#usrUrl").val(usrUrl);
				$("#ordNo").val(ordNo);
				
				if(mListYn == "Y"){
					$("#mListYn").prop("checked", true);
					$("#mListYn").val("Y");
				}else if(mListYn == "N" || mListYn == "" || mListYn == null){
					$("#mListYn").prop("checked", false);
					$("#mListYn").val("N");
				}
				
				if(editorYn == "Y"){
					$("#editorYn").prop("checked", true);
					$("#editorYn").val("Y");
				}else if(editorYn == "N" || editorYn == "" || editorYn == null){
					$("#editorYn").prop("checked", false);
					$("#editorYn").val("N");
				}
				
				if(useYn == "Y"){
					$("#useYnY").prop("checked", true);
				}else{
					$("#useYnN").prop("checked", true);
				}
				
				if(bbsTempletGbn == "16050100"){
					document.getElementById("bbsTempletGbn1").checked = true;
				}else if(bbsTempletGbn == "16050200"){
					document.getElementById("bbsTempletGbn2").checked = true;
				}else if(bbsTempletGbn == "16050300"){
					document.getElementById("bbsTempletGbn3").checked = true;
				}else if(bbsTempletGbn == "16050400"){
					document.getElementById("bbsTempletGbn4").checked = true;
				}else if(bbsTempletGbn == "16050500"){
					document.getElementById("bbsTempletGbn5").checked = true;
				}else if(bbsTempletGbn == "16050600"){
					document.getElementById("bbsTempletGbn6").checked = true;
				}else if(bbsTempletGbn == "16050700"){
					document.getElementById("bbsTempletGbn7").checked = true;
				}
				
				//$("#bbsTempletGbn").val(bbsTempletGbn);
				document.getElementById("bbsTempletGbnVal").value = bbsTempletGbn;
				
				doDisplaySelect(bbsTempletGbn);
				if(mwRKd == "16040100"){
					$("#mwRKdA").prop("checked", true);
				}else{
					$("#mwRKdB").prop("checked", true);
				}
				
				$("#bbsTempletNo").val(bbsTempletNo);
				$("#bbsTempletFileName").val(bbsTempletFileName);	
				
				if(bbsApprYn == "1"){
					$("#bbsApprYn1").prop("checked", true);
				}else{
					$("#bbsApprYn2").prop("checked", true);
				}
				
				if(categoryUseYn == "Y"){
					$("#categoryUseYnY").prop("checked", true);
				}else{
					$("#categoryUseYnN").prop("checked", true);
				}
				$("#bbsFileCnt").val(bbsFileCnt);
				$("#listGbn").val(listGbn);
				$("#readGbn").val(readGbn);
				$("#writeGbn").val(writeGbn);
				$("#modGbn").val(modGbn);
				$("#delGbn").val(delGbn);
				$("#answerGbn").val(answerGbn);
				$("#btIdx").val(btIdx);
				
				doCateDivDis(categoryUseYn);
				doCategoryList(cbIdx);
				selectView2(cbIdx);
				doBbsTempletSelect(cbIdx, bbsTempletGbn);
			}
 		},error : function(code, msg, error) {
 				var str = code.status + " : " + msg + " : " + error;
 				//console.log(str);
 		}
			
	});
}

function selectView2(cbIdx){
	var param = "";
	param = "cbIdx=" + cbIdx;
	
	//tr 초기화
	$("table[id=dynatable]").find("tr[id=tr_added]").remove();
	
	$.ajax({
		url : "<c:url value='/boffice/sy/board/Board_ViewListAx.do'/>"
		, data : param
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			
			var rows = data.rowDataList;
			
			for (var i=0; i < rows.length; i++) {
				var row = data.rowDataList[i];
				
				var labelOrdNo       = row.labelOrdNo;
				var contentMapping   = row.contentMapping;
				var contentMappingL  = row.contentMappingL;
				var labelName        = row.labelName;
				var labelPropGbn     = row.labelPropGbn;
				var labelCompYn      = row.labelCompYn;
				var labelProvSize    = row.labelProvSize;
				var searchLabelUseYn = row.searchLabelUseYn;
				var searchClobYn     = row.searchClobYn;
				var webUseYn         = row.webUseYn;
				var mobileUseYn      = row.mobileUseYn;
				var itemCode		 = row.itemCode;
				
				var master = $("#dynatable");
				var prot = master.find("tr[id=prototype]").clone();
				var rowNo = $("#dynatable tr:last").find("input[name=row_no]:text").attr("value");
				rowNo++;
				
				prot.attr("id", "tr_added");
				prot.attr("style", ""); //프로토타입 css속성을 제거하여 hidden 속성을 제거한다.
				
				prot.find("input[name=row_no]").attr("value", labelOrdNo);
				prot.find("input[name=cud]").attr("value", "C");

				if(contentMapping.substr(0, 3) == "EXT"){
					prot.find("input[name=selfLabelCode]").attr("disabled",false);
					prot.find("input[name=selfLabelCode]").attr("value", labelName);
					prot.find("select[name=contentMapping]").val(contentMapping.substr(0, 3));
					if(contentMappingL != null || contentMappingL != ""){
						prot.find("select[name=contentMapping]").attr(contentMappingL);
					}
				}else{
					
					prot.find("input[name=selfLabelCode]").attr("value", labelName);
					
					prot.find("select[name=contentMapping]").val(contentMapping);
					if(contentMappingL != null || contentMappingL != ""){
						prot.find("select[name=contentMapping]").attr(contentMappingL);
					}
				}
				
				prot.find("input[name=labelName]").attr("value", labelName);				
				prot.find("input[name=contentMappingL]").attr("value", contentMappingL);				
				prot.find("select[name=labelPropGbn]").val(labelPropGbn);
				prot.find("select[name=itemCodeSelectList]").val(itemCode);
				
				prot.find("input[name=labelProvSize]").attr("value", labelProvSize);
				
				if(labelCompYn == "Y"){
					prot.find("input[name=labelCompYn]:checkbox").prop("checked", true);
				}else{
					prot.find("input[name=labelCompYn]:checkbox").prop("checked", false);
				}
				
				if(webUseYn == "Y"){
					prot.find("input[name=webUseYn]:checkbox").prop("checked", true);
				}else{
					prot.find("input[name=webUseYn]:checkbox").prop("checked", false);
				}
				
				if(mobileUseYn == "Y"){
					prot.find("input[name=mobileUseYn]:checkbox").prop("checked", true);
				}else{
					prot.find("input[name=mobileUseYn]:checkbox").prop("checked", false);
				}
				
				if(searchLabelUseYn == "Y"){
					prot.find("input[name=searchLabelUseYn]:checkbox").prop("checked", true);
				}else{
					prot.find("input[name=searchLabelUseYn]:checkbox").prop("checked", false);
				}
				master.find("tbody").append(prot);	
			}
 		},error : function(code, msg, error) {
 				var str = code.status + " : " + msg + " : " + error;
 				//console.log(str);
 		}
	});
}

function doCreate() {
	
	var level = document.BoardVo.level.value;
	
	if(level > 2){
		alert("게시판을 생성 할 수 없습니다.");
		return;
	}
	
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
	
	document.BoardVo.cbIdx.value = "";
	document.BoardVo.cbCd.value = "";
	
	document.BoardVo.cbName.value = "";
	document.BoardVo.mgrUrl.value = "";
	document.BoardVo.usrUrl.value = "";
	document.BoardVo.flag.value = "C";
	document.BoardVo.cbCd.focus();
	
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
		sel = ref.get_selected();
	if(!sel.length) { return false; }

	ref.delete_node(sel);
	
	var nodeUpdates = [];
	
	if (!confirm("삭제하시겠습니까")) {
		return;
	}	
	jstEdit = $.jstree.reference('#jstree');
	 
	 var nodes = [];            
    $('#jstree ul.jstree-container-ul li').each(function(){
		nodes.push($(this).attr('id'));            
	});
    doDelBbs(sel);
   
//     for(var i=0;i<nodes.length;i++){
//    	 var node = jstEdit.get_node(nodes[i]);
//    	 if(node.parent == "#"){
//    		 node.parent = 0;
//    	 }
//    	 nodeUpdates.push({"id":node.id,"parent":node.parent,"text":node.text});            
//     }
    

    //dosaveHiearchy(nodeUpdates);
    
}


function doSave() {
	
	if (!confirm("저장하시겠습니까")) {
		
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
    	 //var mgrurl = node.original.mgrUrl;
    	 var useYn = node.original.useYn;
    	 var cbIdx = node.original.cbIdx;
		     	
    	 nodeUpdates.push({"id":node.id,"parent":node.parent,"text":node.text,"useYn":useYn,"cbIdx":cbIdx});            
    }
    
 	doSaveHierarchy(nodeUpdates);
   
}

function doSaveHierarchy(node){
	var stringify = JSON.stringify(node);

	$.ajax({  
        type: "POST",
        url: "<c:url value='/boffice/sy/board/BoardOrd_FormAx.do'/>",
        data: stringify,
        dataType: "json",
        contentType : 'application/json; charset=utf-8',
        success: function(data) {
        	location.reload(); 

        },
        error: function(req, status, error) {
        	var str = req.status + " : " + status + " : " + error;
        	//alert(str); //수정해야함
        }
    });
	
}

function doDelBbs(id){
	alert(id);
	var param = "cbCd=" + id;
	$.ajax({  
		 type: "POST",
		 url: "<c:url value='/boffice/sy/board/Board_DelAx.do'/>",
		 data: param,
		 type : "POST",
		 dataType : "json",
		 async : false,
		 success : function(data) {
			 location.reload(); 
		 },error : function(code, msg, error) {
 				var str = code.status + " : " + msg + " : " + error;
 				//console.log(str);
 		}
	});
}

// 카테고리 저장
function doCreateCategory(){
	
	if(document.BoardVo.categoryName.value == '' || document.BoardVo.categoryName.value == null){
		alert("카테고리를 입력하세요.");
		document.BoardVo.categoryName.focus();
		return;
	}
	
	var categoryName = "categoryName=" + document.BoardVo.categoryName.value;
	var cbIdx = "cbIdx=" + document.BoardVo.cbIdx.value;
	
	$.ajax({  
		type: "POST",
		url: "<c:url value='/boffice/sy/board/Board_CategoryFormAx.do'/>",
		data: categoryName + "&" + cbIdx,
		dataType: "json",
		async : false ,
		success: function(data) {
			doCategoryList(document.BoardVo.cbIdx.value);
			document.BoardVo.categoryName.value = "";
			alert("카테고리가 저장되었습니다.");
		},
		error: function(req, status, error) {
			var str = req.status + " : " + status + " : " + error;
			alert(str);
		}
	});
}


// 카테고리 리스트
function doCategoryList(id){
	var param = "cbIdx=" + id;
	
	$.ajax({
		url : "<c:url value='/boffice/sy/board/Category_ListAx.do'/>"
		, data : param
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			
			var output = "";
			$(data.CategoryList).each(function(index, element){
				output += "<li>" + element.categoryName + "&nbsp;&nbsp;<a href='javascript:doCategoryRmv("+element.cbIdx+", "+element.categoryCode+");'>삭제</a></li>";
			});
			
            $('#categoryDiv').html(output);
 		},error : function(code, msg, error) {
 				var str = code.status + " : " + msg + " : " + error;
 				//console.log(str);
 		}
			
	});
}

// 카테고리 삭제
function doCategoryRmv(cbIdx, categoryCode){

	var cbIdx = "cbIdx=" + cbIdx;
	var categoryCode = "categoryCode=" + categoryCode;
	var param = cbIdx + "&" + categoryCode;
	
	$.ajax({  
		type: "POST",
		url: "<c:url value='/boffice/sy/board/Board_CategoryRmvAx.do'/>",
		data: param,
		dataType: "json",
		async : false ,
		success: function(data) {
			var cbIdx = document.BoardVo.cbIdx.value;
			doCategoryList(cbIdx);
			alert("카테고리가 삭제되었습니다.");
		},
		error: function(req, status, error) {
			var str = req.status + " : " + status + " : " + error;
			alert(str);
		}
	});
	
}

//템플릿 파일 저장
function doTempletSave(){
	
	var formData = new FormData();
    formData.append("cbIdx", document.getElementById("cbIdx").value);
    formData.append("bbsTempletGbn", document.getElementById("bbsTempletGbnVal").value);
    formData.append("uploadFile",$("#uploadFile")[0].files[0]);
    formData.append("templetName",document.getElementById('templetName').value);
	$.ajax({
		url : "<c:url value='/boffice/sy/board/Board_TempletRegAx.do'/>"
		, data : formData
		, type : "POST"
		, processData: false
     	, contentType: false
		, success : function(data) {
			doBbsTempletSelect(data.sCbIdx, data.sBbsTempletGbn);
			alert("템플릿파일이 저장되었습니다.");
 		},error : function(code, msg, error) {
 				var str = code.status + " : " + msg + " : " + error;
 				//console.log(str);
 		}
			
	});
	
}

// 템플릿파일 조회
function doBbsTempletSelect(cbIdx, bbsTempletGbn){
	var param = "";
    param = "cbIdx=" + cbIdx + "&" + "bbsTempletGbn=" + bbsTempletGbn;

	$.ajax({
		url : "<c:url value='/boffice/sy/board/BbsTempletFile_ListAx.do'/>"
		, data : param
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			var btIdx = $("#btIdx").val();
			
			var output = "";
			output += "<option value='0'>::: 선택 :::</option>";
			$(data.BbsTempletList).each(function(index, element){
				output += "<option value=" + element.btIdx + ">" + element.fileName + "</option>";
				//alert(element.cbIdx);
			});
			
	        $('#bbsTemplet').html(output);
	        document.getElementById("templetName").value = "";
			document.getElementById("uploadFile").value = "";
			$('#bbsTemplet').val(btIdx);
			},error : function(code, msg, error) {
					var str = code.status + " : " + msg + " : " + error;
					//console.log(str);
			}
			
	});
}

// 저장
function doMgrForm(){
// 	var bbsCodeCheck = document.getElementById("bbsCodeCheck").value;
// 	if(bbsCodeCheck == "N"){
// 		alert("코드 중복 체크를 하세요.");
// 		document.BoardVo.cbCd.focus();
// 		return;
// 	}else if(bbsCodeCheck == "Y"){
		var t_len = $('#dynatable tr[id=tr_added]').length;
		for (var i=0; i < t_len; i++) {
			var cud			 = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=cud]");
			var row_no		 = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=row_no]:text");
			var labelName    = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=labelName]:text");
			
			var contentMapping = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("select[name=contentMapping]");
			var selfLabelCode = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=selfLabelCode]:text");
			var labelName = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=labelName]:hidden");
			
			
			var labelPropGbn = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("select[name=labelPropGbn]");
			var labelProvSize = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("select[name=labelProvSize]");
			
			var webUseYn = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=webUseYn]:checkbox");
			var webUseYnValue = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=webUseYnValue]:hidden");
			var webUseYnChecked = webUseYn.is(":checked");
			
			var mobileUseYn = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=mobileUseYn]:checkbox");
			var mobileUseYnValue = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=mobileUseYnValue]:hidden");
			var mobileUseYnChecked = mobileUseYn.is(":checked");
			
			var searchLabelUseYn = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=searchLabelUseYn]:checkbox");
			var searchLabelUseYnValue = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=searchLabelUseYnValue]:hidden");
			var searchLabelUseYnChecked = searchLabelUseYn.is(":checked");
			
			var labelCompYn = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=labelCompYn]:checkbox");
			var labelCompYnValue = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=labelCompYnValue]:hidden");
			var labelCompYnChecked = labelCompYn.is(":checked");
			
			
			labelName.val(selfLabelCode.val());
//	 		if(contentMapping.val() == "EXT"){
//	 			labelName.val(selfLabelCode.val());
//	 		}
			
			if(webUseYnChecked){
				webUseYnValue.val("Y");
			}else{
				webUseYnValue.val("N");
			}
			
			if(mobileUseYnChecked){
				mobileUseYnValue.val("Y");
			}else{
				mobileUseYnValue.val("N");
			}
			
			if(searchLabelUseYnChecked){
				searchLabelUseYnValue.val("Y");
			}else{
				searchLabelUseYnValue.val("N");
			}
			
			if(labelCompYnChecked){
				labelCompYnValue.val("Y");
			}else{
				labelCompYnValue.val("N");
			}
			
		}//for
		
		
		document.BoardVo.action = "/boffice/sy/board/Board_Reg.do";
		document.BoardVo.submit();
// 	}
	
}

// 삭제
// function doBoardRmv(){
// 	document.BoardVo.action = "<c:url value='/boffice/sy/board/Board_Rmv.do' />";
// 	document.BoardVo.submit();
// }

function doCateDivDis(val){
	
	if(val == "Y"){
		document.getElementById("cateDiv").style.display = "block";
	}else{
		document.getElementById("cateDiv").style.display = "none";
	}
}


function doDisplaySelect(val) {
	if (val == "16050700") {
		$("#minwonSelect").css("display", "");
	} else {
		$("#minwonSelect").css("display", "none");
	}
}

function doLabelCodeSelect(selectValue, row){
	var tRow = row.parentElement.parentElement.rowIndex - 2;
	var t_len = $('#dynatable tr[id=tr_added]').length;
	var cnt = 0;
	
	
	for(var i = 0; i < t_len; i++){
		var iRow = parseInt(i) + 1;
		
		var contentMapping = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("select[name=contentMapping]");
		var contentMappingText = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("select[name=contentMapping] option:selected").text();
		var contentMappingValue = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("select[name=contentMapping] option:selected").val();
		var contentMappingCamelValue = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("select[name=contentMapping] option:selected").attr("camel");
		var selfLabelCode = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=selfLabelCode]:text");
		var labelName = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=labelName]:hidden");
		var contentMappingL = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=contentMappingL]:hidden");
		
// 		var inHtml = "";
// 		var d1 = document.getElementById("labelPropGbn");
// 		if(iRow == tRow){
// 			if(contentMappingValue == 'SUB_CONT'){
// 				alert(contentMappingValue);
// 				//inHtml += "<select name='labelPropGbn' id='labelPropGbn'>";
// 				inHtml += "<option value=''>TextBox</option>";
// 				inHtml += "<option value=''>TextArea</option>";
// 				//inHtml += "</select>";
// 				alert(inHtml);
// 				d1.innerHTML = inHtml;
// 			}
// 		}
		
		
		
// 		alert("contentMappingValue = " + contentMappingValue);
// 		alert("selectValue = " + selectValue);
		
// 		if(contentMappingValue == selectValue){
// 			alert("222222");
// 		}
		
		if(contentMappingValue == 'EXT'){
			cnt++;
		}
		
		labelName.val(contentMappingText);
		contentMappingL.val(contentMappingCamelValue);
		
		
		if(contentMapping.val() == "EXT"){
// 			selfLabelCode.val("직접입력");
// 			selfLabelCode.attr("disabled",false);
// 			selfLabelCode.focus();
		}else{
// 			selfLabelCode.val("");
// 			selfLabelCode.attr("disabled",false);
		}
	}
	
// 	$("#dynatable tr[id=tr_added]:eq("+ t_len +")").find("input[name=selfLabelCode]:text").val(contentMappingText);
// 	$("#dynatable tr[id=tr_added]:eq("+ t_len +")").find("input[name=selfLabelCode]:text").focus();
	
	if(cnt > 10){
		alert("직접입력은 10개까지만 가능합니다.");
		
	}
	
}

function doSelectItem(value){
	
	if(value == "16020700" || value == "16020800" || value == "16020900"){
		
		$.ajax({
			url : "<c:url value='/boffice/sy/board/BbsSelectItem_ListAx.do'/>"
			, data : ""
			, type : "POST"
			, dataType : "json"
			, async : false
			, success : function(data) {
// 				$("#itemCodeSelectList").css("display", "");
				var output = "";
				$(data.BbsSelectItemList).each(function(index, element){
					output += "<option value=" + element.code + ">" + element.codeName + "</option>";
				});
		        $('#itemCodeSelectList').html(output);
				},error : function(code, msg, error) {
						var str = code.status + " : " + msg + " : " + error;
						//console.log(str);
				}
				
		});
	}
	
}

function doCodeSearch(){
	var cbCd = document.BoardVo.cbCd.value;
	var cbUprCd = document.BoardVo.cbUprCd.value;
	
	$.ajax({
		url : "<c:url value='/boffice/sy/board/BbsCode_CheckAx.do'/>"
		, data : $("#BoardVo").serialize()
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			if(data.bbsCodeCheck.length > 0){
				alert("중복된 코드가 있습니다.");
				document.getElementById("cbCd").value = "";
				document.BoardVo.cbCd.focus();
				return;
			}else{
				alert("코드를 사용 할 수 있습니다.");
				document.getElementById("bbsCodeCheck").value = "Y";
			}
		},error : function(code, msg, error) {
				var str = code.status + " : " + msg + " : " + error;
				//console.log(str);
		}
			
	});
	
}

function doMListYn(){
	var mListYn = $("input[name=mListYn]:checkbox");
	var mListYnChecked = mListYn.is(":checked");
	if(mListYnChecked){
		document.BoardVo.mListYn.value = "Y";
	}else{
		document.BoardVo.mListYn.value = "N";
	}
}

function doEditorYn(){
	var editorYn = $("input[name=editorYn]:checkbox");
	var editorYnChecked = editorYn.is(":checked");
	if(editorYnChecked){
		document.BoardVo.editorYn.value = "Y";
	}else{
		document.BoardVo.editorYn.value = "N";
	}
}

</script>

<script type="text/javascript">
/**
 * 페이지의 요소가 준비되면 함수구동
 */
$(document).ready(function() {
	
	//동적테이블 row 추가
	$("#dynatable").on("click", "input[name=add_btn]", function(e) {
		//테이블의 총 row 개수
		var id = $('#dynatable tr').length;
		var master = $(this).parents("#dynatable");
		var prot = master.find("tr[id=prototype]").clone();
		var rowNo = $("#dynatable tr:last").find("input[name=row_no]:text").attr("value");
		
		rowNo++;
		
		if(29 < rowNo){
			alert("더이상 추가 할 수 없습니다.");
			return;
		}else{
			prot.attr("id", "tr_added");
			prot.attr("style", ""); //프로토타입 css속성을 제거하여 hidden 속성을 제거한다.
			
			prot.find("input[name=cud]").attr("value", "C");
			prot.find("input[name=row_no]:text").attr("value", rowNo);
	
			master.find("tbody").append(prot);
		}
	});//add

	//동적테이블 row 삭제 
	$("#dynatable").on("click", "input[name=remove_btn]", function(e) {		
	
		var cud = $(this).parents("tr").find("input[name=cud]").attr("value");
		
		if (cud == "C") {
			//추가된 row 이면 row를 삭제한다.
			$(this).parents("tr").remove();
			
		} else if (cud == "D") {
			//조회된 row 가 삭제 'D' 상태이면  cud를 'U' 로 변경하고 버튼명을 '삭제'로 변경한다..
			$(this).parents("tr").css("background-color","");
			$(this).parents("tr").find("input[name=cud]").attr("value","U");
			$(this).parents("tr").find("input[name=remove_btn]").attr("value","삭제");
			
		} else {
			//조회된 row 가 삭제 'U' 상태이면  cud를 'D' 로 변경하고 버튼명을 '삭제'로 변경한다..
			$(this).parents("tr").css("background-color","#D5D5D5");
			$(this).parents("tr").find("input[name=cud]").attr("value","D");
			$(this).parents("tr").find("input[name=remove_btn]").attr("value","취소");
		}
	});
	
	//동적테이블 cud변경
	$("#dynatable").on("click", "input[name=xxx_check]", function(e) {
		if ( $(this).parents("tr").find("input[name=xxx_check]:checkbox").is(":checked") == true ) {
		} else {
		}
	});	
});

/**
 * 페이지의 모든 요소(이미지)가 로딩시 구동
 */
 $(window).load(function() {
		dojsTreeOpen();
		
// 		cm_combo_cmm("#labelCode", '16030000');
		cm_combo_cmm("#labelPropGbn", '16020000');
		cm_combo_cmm("#itemCodeSelectList", '25000000');
		
		
		
		cm_combo_cmm("#listGbn",   '16010000');
		cm_combo_cmm("#readGbn",   '16010000');
		cm_combo_cmm("#writeGbn",  '16010000');
		cm_combo_cmm("#modGbn",    '16010000');
		cm_combo_cmm("#delGbn",    '16010000');
		cm_combo_cmm("#answerGbn", '16010000');
		cm_combo_cmm("#bbsFileCnt", '10000000');
	});
	
	
	function doJstreeSearch(){
		 var ref = $('#jstree').jstree(true);
		 var sel_node = ref.get_selected();
		 ref.deselect_node(sel_node); 
		 var text = document.BoardVo.search.value;
		 var v = $("#jstree").jstree("search", text);
		 
	}
	
	
</SCRIPT>
<style type="text/css">
 #jstree {
 	width:100%;
	 height:606px;
	 margin-bottom:1px;
	 overflow:scroll;
 }
 
 #button_location {
 	float:left;
 	position: absolute;
 	
 }
 #jstreeText{float:left;margin-left:50px;}
</style>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="BoardVo" name="BoardVo" method="post" enctype="multipart/form-data" >
<input type="hidden" name="level" id="level" />
<input type="hidden" id="flag" name="flag" />
<input type="hidden" id="btIdx" name="btIdx" />
<input type="hidden" id="bbsTempletGbnVal" name="bbsTempletGbnVal" />

<input type="hidden" id="bbsCodeCheck" name="bbsCodeCheck" value="N" />

<form:hidden path="cbIdx" />
<%-- <form:hidden path="mListYn" /> --%>
<form:hidden path="ordNo" />
<form:hidden path="pageIndex" />
<form:hidden path="parent" />
<%-- <form:hidden path="cbCd" />
<form:hidden path="cbUprCd" />
<form:hidden path="flag" /> --%>

<!-- 컨텐츠 영역 -->
<!-- <div id="contents" style="position:relative;"> -->
<!-- 	<div id="jstree"></div> -->
<div id="contents">
	<div class="f_left w30 linebox pdt10">
		<div id="jstree" ></div>
	</div>
	<div style="position:absolute; top: 840px;">
		<a href="javascript:doCreate();" class="btn2">추가</a>
		<a href="javascript:doDelete();" class="btn2">삭제</a>
		<a href="javascript:doSave();" class="btn1">메뉴순서저장</a>
	</div>
	<div class="f_right w65">
		<div id="jstreeText">
			<table summary="" class="write">
				<caption></caption>
				<colgroup>
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<tr>
					<th colspan="2">::: 게시판 기본설정</th>
				</tr>
				<tr>
				<th><label for="search">항목검색</label></th>
				<td><input type="text" name="search" id="search"/>&nbsp;<a href="javascript:doJstreeSearch();" class="btn1">검색</a></td>
				</tr>
				<tr>
					<th><label for="cbCd">코드</label></th>
					<td><form:input path="cbCd" id="cbCd" maxlength="8"/>&nbsp;<a href="javascript:doCodeSearch();" class="btn1">코드체크</a></td>
				</tr>
				<tr>
					<th><label for="cbUprCd">부모코드</label></th>
					<td><form:input path="cbUprCd" id="cbUprCd" maxlength="8" /></td>
				</tr>
				<tr>
					<th><label for="cbIdxVal">게시판 고유코드(cbIdx)</label></th>
					<td><input type="text" id="cbIdxVal" name="cbIdxVal"  maxlength="8"/></td>
				</tr>
				<tr>
					<th><label for="cbName">게시판 이름</label></th>
					<td><form:input path="cbName" id="cbName" maxlength="51" size="70"/></td>
				</tr>
				<tr>
					<th>사용여부</th>
					<td>
						<input type="radio" id="useYnY" name="useYn" value="Y" checked="checked" /> <label for="useYnY">사용</label>
						<input type="radio" id="useYnN" name="useYn" value="N" /> <label for="useYnN">중지</label>
					</td>
				</tr>
			</table>
			<!-- <div id="testDiv" style="display: none;"> -->
			<table class="write">
				<caption></caption>
				<colgroup>
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<tr>
					<th><label for="mgrUrl">관리자 URL</label></th>
					<td>
						<form:input path="mgrUrl" id="mgrUrl" size="70" />
					</td>
				</tr>
				<tr>
					<th><label for="usrUrl">사용자 URL</label></th>
					<td>
						<form:input path="usrUrl" id="usrUrl" size="70" />
					</td>
				</tr>
				<tr>
					<th>게시판 템플릿</th>
					<td>
						<input type="radio" id="bbsTempletGbn1" name="bbsTempletGbn" value="16050100" onclick="javascript:doDisplaySelect(this.value);" /> <label for="bbsTempletGbn1">일반게시판</label>
						<input type="radio" id="bbsTempletGbn2" name="bbsTempletGbn" value="16050200" onclick="javascript:doDisplaySelect(this.value);" /> <label for="bbsTempletGbn2">포토(리스트)</label>
						<input type="radio" id="bbsTempletGbn3" name="bbsTempletGbn" value="16050300" onclick="javascript:doDisplaySelect(this.value);" /> <label for="bbsTempletGbn3">포토(앨범)</label>
						<input type="radio" id="bbsTempletGbn4" name="bbsTempletGbn" value="16050400" onclick="javascript:doDisplaySelect(this.value);" /> <label for="bbsTempletGbn4">동영상</label>
						<input type="radio" id="bbsTempletGbn5" name="bbsTempletGbn" value="16050500" onclick="javascript:doDisplaySelect(this.value);" /> <label for="bbsTempletGbn5">Q&A</label>
						<input type="radio" id="bbsTempletGbn6" name="bbsTempletGbn" value="16050600" onclick="javascript:doDisplaySelect(this.value);" /> <label for="bbsTempletGbn6">목록게시판</label>
						<input type="radio" id="bbsTempletGbn7" name="bbsTempletGbn" value="16050700" onclick="javascript:doDisplaySelect(this.value);" /> <label for="bbsTempletGbn7">민원게시판</label>
					</td>
				</tr>
				<tr>
					<th>게시글 승인</th>
					<td>
						<input type="radio" id="bbsApprYn1" name="bbsApprYn" value="1" /> <label for="bbsApprYn1">즉시 승인</label>
						<input type="radio" id="bbsApprYn2" name="bbsApprYn" value="2" /> <label for="bbsApprYn2">관리자가 인증</label>
					</td>
				</tr>
				<tr>
					<th>권한 설정</th>
					<td>
						<label for="listGbn">목록</label>	<form:select path="listGbn">
								<form:option value="">::: 선택 :::</form:option>
							</form:select>
						<label for="listGbn">읽기</label>	<form:select path="readGbn">
								<form:option value="">::: 선택 :::</form:option>
							</form:select>
						<label for="listGbn">쓰기</label>	<form:select path="writeGbn">
								<form:option value="">::: 선택 :::</form:option>
							</form:select>
							<br/><br/>
						<label for="listGbn">수정</label>	<form:select path="modGbn">
								<form:option value="">::: 선택 :::</form:option>
							</form:select>
						<label for="listGbn">삭제</label>	<form:select path="delGbn">
								<form:option value="">::: 선택 :::</form:option>
							</form:select>
						<label for="listGbn">답변</label>	<form:select path="answerGbn">
								<form:option value="">::: 선택 :::</form:option>
							</form:select>
					</td>
				</tr>
				<tr>
					<th><label for="categoryUseYnY">카테고리</label></th>
					<td>
						<div style="width: 30%; float: left;">
							<input type="radio" id="categoryUseYnY" name="categoryUseYn" value="Y" onClick="javascript:doCateDivDis(this.value);" /> 사용
							<input type="radio" id="categoryUseYnN" name="categoryUseYn" value="N" onClick="javascript:doCateDivDis(this.value);" /> 중지
						</div>
						<div id="cateDiv" style="float:right; width:70%;display: none;">
							<input type="text" id="categoryName" name="categoryName" />
							<a href="javascript:doCreateCategory();" class="btn2">저장</a>
							<ul id="categoryDiv"></ul>
						</div>
					</td>
				</tr>
				<tr>
					<th><label for="bbsFileCnt">첨부파일</label></th>
					<td>
						<form:select path="bbsFileCnt">
							<form:option value="">::: 선택 :::</form:option>
						</form:select>
						개 (0은 미사용,  1개당 10MB / 최대10개 / 최대 50MB)					
					</td>
				</tr>
				<tr>
					<th><label for="mListYn">모바일 리스트 설정</label></th>
					<td>
						<input type="checkbox" id="mListYn" name="mListYn" onClick="javascript:doMListYn(this);" /> 사용
					</td>
				</tr>
				<tr>
					<th><label for="editorYn">에디터 설정</label></th>
					<td>
						<input type="checkbox" id="editorYn" name="editorYn" onClick="javascript:doEditorYn(this);" /> 사용
					</td>
				</tr>
				<tr id="minwonSelect" style="display: none;">
					<th><label for="mwRKdA">민원기능</label></th>
					<td>
						<input type="radio" id="mwRKdA" name="mwRKd" value="16040100" checked="checked" />고정형
						<input type="radio" id="mwRKdB" name="mwRKd" value="16040200" /> 부서/담당 지정												
					</td>
				</tr>
			</table>
			
			<table class="write" id="dynatable" width="100%">
				<tr>
					<th colspan="8">::: 게시판 항목/속성 설정</th>
				</tr>
				<tr>
					<th>번호<input type="button" value="추가" name="add_btn" id="add_btn" /></th>
					<th>필드명</th>
					<th>설정</th>
					<th>가로목록<br/>크기</th>
					<th>웹리스트<br/>사용</th>
					<th>모바일<br/>리스트<br/>사용</th>
					<th>검색항목<br/>사용</th>
					<th>필수입력</th>
				</tr>
				<tr id="prototype" style="display:none;">
					<td>
						<input type="button" value="취소" name="remove_btn" id="remove_btn"/>
						<input type="text" name="row_no" id="row_no" value="0" size="1" readonly="readonly"/>
						<input type="hidden" name="cud" id="cud" size="1" value="P"/>
					</td>
					<td>
						<select name="contentMapping" id="contentMapping" onchange="javascript:doLabelCodeSelect(this.value, this);">
							<option value="">선택하세요</option>
							<option value="NO_CONT" camel="noCont">글번호</option>
							<option value="CATE_CONT" camel="cateCont">카테고리</option>
							<option value="SUB_CONT" camel="subCont">제목</option>
							<option value="NAME_CONT" camel="nameCont">성명</option>
							<!-- <option value="JUMIN_CONT" camel="juminCont">주민번호</option> -->
							<option value="ADDR_CONT" camel="addrCont">주소</option>
							<option value="EMAIL_CONT" camel="emailCont">이메일</option>
							<option value="TEL_CONT" camel="telCont">전화번호</option>
							<option value="PHONE_CONT" camel="phoneCont">이동전화</option>
							<option value="REGDT_CONT" camel="regdtCont">등록일</option>
							<option value="COUNT_CONT" camel="countCont">조회수</option>
							<option value="CLOB_CONT" camel="clobCont">내용</option>
							<!-- <option value="PW_CONT" camel="pwCont">비밀번호</option> -->
							<option value="M_LINK_CONT" camel="mLinkCont">동영상링크</option>
							<option value="THUMNAIL_CONT" camel="thumnailCont">썸네일</option>
							<option value="CAPTION_CONT" camel="captionCont">자막</option>
							<option value="SUB_LINK_CONT" camel="subLinkCont">제목링크</option>
							<option value="MW_OPENYN_CONT" camel="mwOpenynCont">공개여부</option>
							<option value="SHORT_CUT" camel="shortCut">바로가기</option>
							<!-- 2020.08.04 공통코드 추가 ajhwan -->
							<option value="DEPTH_1" camel="depth1">대분류</option>
							<option value="AREA" camel="area">지역</option>
							<option value="TARGET" camel="target">대상</option>
							<option value="TYPE" camel="type">유형</option>
							<option value="CENTER" camel="center">기관</option>
							<!-- 2020.08.05 공통코드 추가 이솔이 -->
							<option value="TO_DATE" camel="toDate">기관</option>
							<option value="FORM_DATE" camel="formDate">기관</option>
							
							<option value="ANS_YN_CONT" camel="ansYnCont">답변여부</option>
							<option value="ANS_COMP_CONT" camel="ansCompCont">답변완료통보</option>
							<option value="MW_STATUS_CONT" camel="mwStatusCont">진행상태</option>
							<option value="EXT" camel="ext">직접입력</option>
						</select>
						<input type="text" name="selfLabelCode" id="selfLabelCode" size="3" />
						<input type="hidden" name="labelName" id="labelName" />
						<input type="hidden" name="contentMappingL" id="contentMappingL" />
					</td>
					<td>
						<select name="labelPropGbn" id="labelPropGbn">
							<option value="">선택하세요</option>
						</select>
						<select name="itemCodeSelectList" id="itemCodeSelectList">
							<option value="">선택하세요</option>
						</select>
						
					</td>
					<td>
						<input type="text" name="labelProvSize" id="labelProvSize" size="1"/>
					</td>
					<td>
						<input type="checkbox" id="webUseYn" name="webUseYn" />
						<input type="hidden" id="webUseYnValue" name="webUseYnValue" />
					</td>
					<td>
						<input type="checkbox" id="mobileUseYn" name="mobileUseYn" />
						<input type="hidden" id="mobileUseYnValue" name="mobileUseYnValue" />
					</td>
					<td>
						<input type="checkbox" id="searchLabelUseYn" name="searchLabelUseYn" />
						<input type="hidden" id="searchLabelUseYnValue" name="searchLabelUseYnValue" />
					</td>
					<td>
						<input type="checkbox" name="labelCompYn" id="labelCompYn" />
						<input type="hidden" name="labelCompYnValue" id="labelCompYnValue" />
					</td>
				</tr>
			</table>
			
			<table summary="" class="write">
				<tr>
					<th colspan="5">::: 상세페이지 템플릿 선택</th>
				</tr>
				<tr>
					<th><label for="search">템플릿 선택</label></th>
					<td colspan="4">
						<select id="bbsTemplet" name="bbsTemplet" >
						</select>
					</td>
				</tr>
				<tr>
					<th><label for="search">템플릿명</label></th>
					<td colspan="4">
						<input type="text" name="templetName" id="templetName" />
						<input type="file" name="uploadFile" id="uploadFile" />
						<a href="javascript:doTempletSave();" class="btn2">템플릿 저장</a>
					</td>
				</tr>
			</table>
				
			<!-- 버튼 -->
			<div id="menu" class="btn_zone">
				<a href="javascript:doMgrForm();" class="btn2">게시판 정보저장</a>
			</div>
			<!-- //버튼 -->
		</div>
	</div>
</div>
<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
