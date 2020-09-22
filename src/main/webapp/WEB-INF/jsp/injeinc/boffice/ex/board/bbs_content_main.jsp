<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>게시판설정관리</title>
<style type="text/css">
	#jstree {width:100%; height:606px; margin-bottom:1px; overflow-y:scroll;}
</style>
<script type="text/javascript">
//<![CDATA[
	function doJsTreeOpen() {
		
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/sy/board/CmsBbsListSiteAx.do'/>",
			data : {"mgrSiteCd":$("#mgrSiteCd").val()},
			dataType : "json",
			success:function(data) {
				
				if(data.result) {
					$('#jstree').jstree({
						"core" : {
							"data" : data.boardList
							,"check_callback": true
						}
						,"plugins" : ["dnd","search"]
					}).bind("loaded.jstree",function(event,data) {
						//alert("ok");
						//var tree = $('#jstree').jstree(true);
						var tree = $('#jstree').jstree(true);
						//$('#jstree').jstree("open_all");
					//	$('#jstree').jstree("open_node","16000000");
						$('#jstree').jstree("open_node","s01");
						$('#jstree').jstree("select_node","s01");
					}).bind("select_node.jstree", function (event, data) {
						//alert(data.node.id);
						if($('#reloadChk').val() == "N"){
							doTextBind(data);
						}
						
					}).bind("move_node.jstree",function(event,data) {
	
					}).bind("search.jstree",function(event,data){
						var tree = $('#jstree').jstree(true);
						var sel = tree.select_node(data.res[0]);
					});
					
					//$('#jstree').jstree("open_all");
					
					//$('#jstree').jstree("open_node","16000000");
					//$('#jstree').jstree("select_node","16000000");
					
					
					
				}else{
					alert(data.message);
				}
			
			},
			error: function(xhr, status, error) {
				alert(xhr+"=="+status+"=="+error);
			}
		});
		
		
	}
	
	
	
	
	function doSelectNode(inId){
		$('#reloadChk').val("Y");
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/sy/board/CmsBbsListSiteAx.do'/>",
			data : {"mgrSiteCd":$("#mgrSiteCd").val()},
			dataType : "json",
			success:function(data) {
				if(data.result) {
					var tree = $('#jstree').jstree(true);
					tree.settings.core.data = data.boardList;
					tree.refresh();
					tree.select_node(inId);
					$('#reloadChk').val("N");
				}
			},
			error: function(xhr, status, error) {
				alert(xhr+"=="+status+"=="+error);
			}
		});
	}
	var linkChk = "no";
	function doTextBind(data){
		if(linkChk == "no"){
			$("#BoardVo").attr("action", "<c:url value='/boffice_noDeco/ex/board/BbsContentList.do' />").submit();
			linkChk = "yes";
		}else{
			$("#cbIdx").val(data.node.original.cbIdx);
			$("#BoardVo").attr("action", "<c:url value='/boffice_noDeco/ex/board/BbsContentLink.do' />").submit();
		}
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
	
	function boardSiteChage(){
		document.location.href="/boffice/ex/board/BbsContentMain.do?cmm_code=BB020000&mgrSiteCd="+$("#mgrSiteCd").val();		
	}
//]]>
</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;" >
<form:form commandName="BoardVo" name="BoardVo" method="post" target="BoardSetFrame">
<form:hidden path="cbIdx" />
<input type="hidden" id="reloadChk" value="N"/>
			<!-- 컨텐츠 영역 -->
			
			<div id="contents">
				<div class="f_left" style="width:26%;">
<div style="height:30px">
<c:set var="SesUserMgrSiteCd" value="${sessionScope['SesUserMgrSiteCd']}"/>
					<select name="mgrSiteCd" id="mgrSiteCd"  style="width:100%" onchange="boardSiteChage()">
<c:set var="siteList" value="${cmm:getSiteList()}" />
<c:forEach var="itemInfo" items="${siteList}" varStatus="status">
		<c:set var="mgrSiteCd" value=",${CmsBbsVo.mgrSiteCd},"/>
		<c:set var="selectSiteCd" value=",${itemInfo.siteCd},"/>
		<c:set var="tempSesUserMgrSiteCd" value=",${SesUserMgrSiteCd},"/>
			<c:if test="${fn:indexOf(tempSesUserMgrSiteCd,selectSiteCd)>-1 || fn:trim(sessionScope['SesUserPermCd']) eq '05010000'}">
						<option type="checkbox" value="<c:out value="${itemInfo.siteCd}"/>" <c:if test="${fn:trim(param.mgrSiteCd) eq itemInfo.siteCd}">selected="selected"</c:if>><c:out value="${itemInfo.siteNm}"/></option>
			</c:if>
</c:forEach>
					</select> 
</div>
					<div>
						<label for="searchName" class="hidden">검색어</label>
						<input type="text" id="searchName" name="searchName" size="25"/>
						<button class="btn2 btn_input" onclick="doSearchName(); return false;"><i class="fa fa-search"></i> 검색</button>
					</div>
					<div class="linebox" style="padding:0;margin-top:10px;">
						<div id="jstree"></div>
					</div>
				</div>
				<div class="f_right" style="width:72%;height:1200px;">
					<iframe id="BoardSetFrame" name="BoardSetFrame" src="<c:url value='/boffice_noDeco/ex/board/BbsContentList.do'/>?searchCbIdx=<c:out value="${fn:trim(param.cbIdx) eq ''?'0':param.cbIdx}"/>" style="width:100%;height:100%;border-width:0px;"></iframe>
				</div>
			</div>
			<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
