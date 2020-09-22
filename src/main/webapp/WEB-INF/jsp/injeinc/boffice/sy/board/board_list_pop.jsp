<!DOCTYPE html>
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
- 파일명 : board_list_pop.jsp
- 최종수정일 : 2020-03-24
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<c:import url="/layout/cms/head.jsp" ></c:import>

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
			}).bind("select_node.jstree", function (event, data) { //선택시
				if($('#reloadChk').val() == "N"){
					$("#copyCbIdx").val(data.node.original.cbIdx);
					$("#cbIdx").val("<c:out value='${param.cbIdx}'/>");
					$("#BoardVo").attr("action", "<c:url value='/boffice/sy/board/BoardSetPop.do' />").submit();
					window.close();
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
});

function doSelectNode(inId){
	$('#reloadChk').val("Y");
	var ajaxParam = {"mgrSiteCd":$("#mgrSiteCd").val()};
	var ajaxOption = {};
	ajaxOption.success = function(data){
		if(data.result) {
			var tree = $('#jstree').jstree(true);
			tree.settings.core.data = data.boardList;
			tree.refresh();
			tree.select_node(inId);
			$('#reloadChk').val("N");
		}
	};
	ajaxAction("<c:url value='/boffice/sy/board/CmsBbsListSiteAx.do'/>", ajaxParam, ajaxOption);
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
<!-- ============================== 모달영역 ============================== -->
<div class="modalWrap popup">
	<div class="modalTitle">
		<h3>불러올 게시판 선택</h3>
		<a href="#" class="btn_modalClose">닫기</a>
	</div>
	<div style="width:320px"></div>
	<form:form commandName="BoardVo" name="BoardVo" method="post" target="BoardSetFrame">
	<form:hidden path="cbIdx" value="${param.cbIdx}" />
	<form:hidden path="copyCbIdx" />
	<form:hidden path="mgrSiteCd" value="${param.mgrSiteCd}" />
	<input type="hidden" id="reloadChk" value="N"/>
	<div class="section divGroup leftTree">
		<div id="jstree"></div>
	</div>
	</form:form>
</div>
</body>
</html>
