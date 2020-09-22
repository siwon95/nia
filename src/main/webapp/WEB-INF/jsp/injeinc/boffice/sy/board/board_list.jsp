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
- 제목 : 게시판관리
- 파일명 : board_list.jsp
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

	/* 트리 */
	var ajaxParam = {"mgrSiteCd":$("#mgrSiteCd").val()};
	var ajaxOption = {};
	ajaxOption.success = function(data){
		if(data.result) {
			var sel_id=0;
			for(var i=0;i<data.boardList.length;i++){
				//게시판 자동선택
				if(data.boardList[i].cbIdx!=0 && data.boardList[i].type=="default"){
					sel_id= data.boardList[i].id;
					break;
				}
			}
			$('#jstree').jstree({
				"core":{"data":data.boardList, "check_callback":true},
				"default":{draggable:false},
				"plugins":["search","types"],
	            "types": {
	                "group": {
	                    'icon': 'jstree-folder'
	                },
	                "default": {
	                    'icon': 'jstree-file'
	                }
	            }
			}).bind("loaded.jstree",function(event,data) { //로딩시
				$('#jstree').jstree("select_node",sel_id);
				$("#BoardVo").attr("action", "<c:url value='/boffice/sy/board/BoardSetPop.do' />").submit();
				//$('#jstree').jstree("select_node",$("#jstree li").eq(0).attr("id"));
			}).bind("select_node.jstree", function (event, data) { //선택시
				if($('#reloadChk').val() == "N"){
					$("#cbIdx").val(data.node.original.cbIdx);
					$("#BoardVo").attr("action", "<c:url value='/boffice/sy/board/BoardSetPop.do' />").submit();
				}
			}).bind("search.jstree",function(event,data){
				var tree = $('#jstree').jstree(true);
				var sel = tree.select_node(data.res[0]);
			});
		}else{
			alert(data.message);
		}
	};
	ajaxAction("<c:url value='/boffice/sy/board/CmsBbsListSiteAx.do'/>", ajaxParam, ajaxOption);
	
	//버튼이벤트
	$(".btn_search").click(function(e){
		e.preventDefault();
		treeSearch();
	});
	
	//입력이벤트
	$("#mgrSiteCd").change(function(){
		document.location.href="/boffice/sy/board/BoardList.do?mgrSiteCd="+$("#mgrSiteCd").val();		
	});
	$("#searchName").keypress(function(e){
        if(e.which == 13){
        	e.preventDefault();
    		treeSearch();
        }
    });
});
function doSelectNode(inId){
	$('#reloadChk').val("Y");
	var ajaxParam = {"mgrSiteCd":$("#mgrSiteCd").val()};
	var ajaxOption = {};
	ajaxOption.success = function(data){
		if(data.result) {
			var tree = $('#jstree').jstree(true);
			tree.settings.core.data = data.boardList;
			var sel_node = tree.get_selected();
			tree.deselect_node(sel_node);
			$('#jstree').one("refresh.jstree", function (e, data) {
		    	tree.select_node(inId);
		    }).jstree(true).refresh();
			//tree.refresh();
			//tree.select_node(inId);	
			$('#reloadChk').val("N");
		}
	};
	ajaxAction("<c:url value='/boffice/sy/board/CmsBbsListSiteAx.do'/>", ajaxParam, ajaxOption);
}

function treeSearch(){
	var tree = $('#jstree').jstree(true);
	var sel_node = tree.get_selected();
	tree.deselect_node(sel_node); 
	$("#jstree").jstree("search", $("#searchName").val());		
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<form:form commandName="BoardVo" name="BoardVo" method="post" target="BoardSetFrame">
	<form:hidden path="cbIdx" />
	<input type="hidden" id="reloadChk" value="N"/>
	<div class="section divGroup leftTree">
		<div class="left">
			<div class="siteSelect">
				<c:set var="SesUserMgrSiteCd" value="${sessionScope['SesUserMgrSiteCd']}"/>
				<select name="mgrSiteCd" id="mgrSiteCd" class="w100p">
				<c:set var="siteList" value="${cmm:getSiteList()}" />
				<c:forEach var="itemInfo" items="${siteList}" varStatus="status">
					<c:set var="mgrSiteCd" value=",${CmsBbsVo.mgrSiteCd},"/>
					<c:set var="selectSiteCd" value=",${itemInfo.siteCd},"/>
					<c:set var="tempSesUserMgrSiteCd" value=",${SesUserMgrSiteCd},"/>
						<c:if test="${fn:indexOf(tempSesUserMgrSiteCd,selectSiteCd)>-1 || fn:trim(sessionScope['SesUserPermCd']) eq '05010000'}">
							<option value="<c:out value="${itemInfo.siteCd}"/>" <c:if test="${fn:trim(param.mgrSiteCd) eq itemInfo.siteCd}">selected="selected"</c:if>><c:out value="${itemInfo.siteNm}"/></option>
						</c:if>
				</c:forEach>
				</select>
			</div>
			<div class="treeSearch">
				<input type="text" id="searchName" name="searchName" class="w150">
				<a href="#" class="btn_inline btn_search">검색</a>
			</div>
			<div id="jstree" class="optionBar rows2"></div>
		</div>
		
		<div class="right">
			<iframe id="BoardSetFrame" name="BoardSetFrame" style="width:100%;height:1100px"></iframe>
		</div>
	</div>
	</form:form>
</body>
</html>
