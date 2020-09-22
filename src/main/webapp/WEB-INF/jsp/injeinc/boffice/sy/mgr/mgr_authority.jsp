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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>관리자 권한 설정</title>
<style type="text/css">
 	#jstree { width:100%; height:500px; margin-bottom:1px; overflow-y:scroll;}
</style>
<link href="<c:url value='/css/admin.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/board.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/site/mnt/layout.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery.timepicker.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/js/vakata-jstree-5bece58/dist/themes/default/style.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/new_mnt.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.1.10.2.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/vakata-jstree-5bece58/dist/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/vakata-jstree-5bece58/dist/jstree.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/vakata-jstree-5bece58/dist/jstree.js' />"></script>
<script type="text/javascript">
//<![CDATA[
	function changeType() {
			
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/sy/mgr/MgrAuthorityListAx.do'/>",
			data : {"searchType":$("#searchType").val(),"roleIdx":$("#roleIdx").val()},
			dataType : "json",
			success:function(data) {
				
				if(data.result) {
					
					$('#jstree').jstree("destroy").empty();
					
					$('#jstree').jstree({
						"core" : {
							"data" : data.authorityList
							,"check_callback": true
						}
						,"plugins" : ["checkbox"]
					});
					
					$("#jstree").jstree("open_all");
						
					var tree = $('#jstree').jstree(true);
					var all_node = tree.get_json('#', { 'flat': true });
					
					for(var i=0;i<data.authorityList.length;i++) {
						var authorityInfo = data.authorityList[i];
						if(authorityInfo.saveChk == "Y"){
							tree.check_node(all_node[i]);
						}
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
		
		if(!confirm("저장하시겠습니까")) return;
		
		var nodeUpdates = [];
		
		var tree = $("#jstree").jstree("get_checked", true);
			
		var jstEdit = $.jstree.reference('#jstree');
		
		for(var i = 0; i < tree.length; i++) {
			var node = jstEdit.get_node(tree[i].id);
			var idx = node.original.idx;
			if(i<3){
				alert(tree[i].id);
				alert(idx);
			}
			nodeUpdates.push(idx);
		}
		
		doSaveHierarchy(nodeUpdates);
		
	}
	
	function doSaveHierarchy(nodeUpdates) {
		
		var stringify = JSON.stringify(nodeUpdates);
		
		$.ajax({  
			type: "POST",
			url: "<c:url value='/boffice/sy/mgr/MgrAuthorityRegAx.do' />",
			data: {"searchType":$("#searchType").val(),"roleIdx":$("#roleIdx").val(),"checkList":stringify},
			dataType: "json",
			success: function(data) {
				
				alert(data.message);
				
				if(data.result) {
					changeType();
				}
				
			},
			error: function(req, status, error) {
				var str = req.status + " : " + status + " : " + error;
				alert(str);
			}
		});
		
	}
	
	$(document).ready(function() {
		changeType();
	});
//]]>
</script>
</head>
<body style="background:none;">
	<div id="contents">
		<form:form commandName="RoleVo"  name="RoleVo" method="post">
		<form:hidden path="roleIdx" />
			<!-- 왼쪽영역 -->
			<div class="f_left w45 linebox" style="border-top:none;">
				<div id="jstreeText">
					<table class="write" width="100%"> 
					<tr>
						<td><label for="searchType">관리자권한선택</label></td>
						<td>
							<select id="searchType" name="searchType" onchange="changeType();">
								<option value="menu">관리자메뉴권한</option>
								<option value="board">게시판권한</option>
							</select>
						</td>
					</tr>
					</table>
				</div>
				<div id="jstree">
					
				
				</div>
			</div>
			<!-- 오른쪽영역 -->
			<div class="f_right w50">
				<table summary="관리자정보" class="write">
					<caption>관리자정보</caption>
					<colgroup>
						<col width="40%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th>권한명</th>
							<td><c:out value="${RoleVo.roleName}" /></td>
						</tr>
						<tr>
							<th>사용 여부</th>
							<td><c:out value="${RoleVo.useYn}" /></td>
						</tr>
					</tbody>
				</table>
				<!-- 버튼 -->
				<div class="btn_zone">
					<a href="javascript:doSave();" class="btn2">저장</a>
				</div>
				<!-- //버튼 -->
			</div>
		</form:form>
	</div>
<script type="text/javascript">
//<![CDATA[
	function changeType() {
			
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/sy/mgr/MgrAuthorityListAx.do'/>",
			data : {"searchType":$("#searchType").val(),"roleIdx":$("#roleIdx").val()},
			dataType : "json",
			success:function(data) {
				
				if(data.result) {
					
					$('#jstree').jstree("destroy").empty();
					
					$('#jstree').jstree({
						"core" : {
							"data" : data.authorityList
							,"check_callback": true
						}
						,"plugins" : ["checkbox"]
					});
					
					$("#jstree").jstree("open_all");
						
					var tree = $('#jstree').jstree(true);
					var all_node = tree.get_json('#', { 'flat': true });
					
					for(var i=0;i<data.authorityList.length;i++) {
						var authorityInfo = data.authorityList[i];
						if(authorityInfo.saveChk == "Y"){
							tree.check_node(all_node[i]);
						}
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
//]]>
</script>
</body>
</html>
