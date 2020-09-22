<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="bbs" uri="http://cms.injeinc.co.kr/bbs"%>
<%-- ------------------------------------------------------------
- 제목 : 게시물관리
- 파일명 : bbs_content_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<c:set var="searchCbCd" value="${empty param.searchCbCd?param.treeLocationId:param.searchCbCd}" />
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
	var sel_id="<c:out value='${searchCbCd}' />";
	ajaxOption.success = function(data){
		if(data.result) {
			for(i=0; i<data.boardList.length; i++){
				if(parseInt(data.boardList[i].bbsCnt) > 0){
					data.boardList[i].text = data.boardList[i].text + '<span class="count">('+data.boardList[i].bbsCnt+')</span>';	
				} 
			}
			//게시판 자동선택
			if(sel_id == ""){
				for(var i=0;i<data.boardList.length;i++){
					if(data.boardList[i].cbIdx!=0 && data.boardList[i].type=="default"){
						sel_id= data.boardList[i].id;
						break;
					}
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
				<c:if test="${empty param.treeLocationId}">
				$('#jstree').jstree("select_node",sel_id);
				$("#searchCbCd").val(sel_id);
				</c:if>
				$("#BbsContentVo").attr("action","<c:url value='/boffice/ex/board/BbsContentListPop.do' />?mgrSiteCd="+$("#mgrSiteCd").val()).submit();
			}).bind("activate_node.jstree", function(e,d){ //선택시
				if(d.node.id == '16000000'){
					$("#jstree").jstree("open_node", d.node.id);
				}else if($("#searchCbCd").val() != d.node.id){
					$("#searchCbCd").val(d.node.id);
					$("#BbsContentVo").attr("action","<c:url value='/boffice/ex/board/BbsContentListPop.do' />?mgrSiteCd="+$("#mgrSiteCd").val()).submit();
				}
			}).bind("search.jstree",function(e,d){ //검색시
			});
		}else{
			alert(data.message);
		}
	};
	ajaxAction("<c:url value='/boffice/sy/board/CmsBbsListSiteAx.do'/>", ajaxParam, ajaxOption);
	
	$("#mgrSiteCd").change(function(){
		document.location.href="/boffice/ex/board/BbsContentList.do?mgrSiteCd="+$("#mgrSiteCd").val();		
	});
	$("#searchName").keypress(function(e){
        if(e.which == 13){
        	e.preventDefault();
    		treeSearch();
        }
    });
});

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

	<div class="section divGroup leftTree">
		<div class="left">
			<form:form commandName="BbsContentVo" name="BbsContentVo" action="BbsContentListSet.do" method="post" class="searchListPage" target="BbsContentListSetFrame" onsubmit="return doSearch(this);">
			<input type="hidden" name="returnurl" value="/boffice/ex/board/BbsContentListPop.do?mgrSiteCd=<c:out value="${param.mgrSiteCd}"/>&searchCbCd=<c:out value="${searchCbCd}"/>&pageIndex=<c:out value="${BbsContentVo.pageIndex}"/>"/>
			<input type="hidden" id="searchCbCd" name="searchCbCd" value="<c:out value="${searchCbCd}"/>"/>
			<form:hidden path="pageIndex" />
			<form:hidden path="pageUnit" />
			<form:hidden path="searchCondition" />
			<form:hidden path="searchKeyword" />
			<form:hidden path="searchStartDate" />
			<form:hidden path="searchEndDate" />
			<form:hidden path="searchGroup" />
			<form:hidden path="searchCbIdx" />
			<form:hidden path="searchDelYn" />
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
				<button class="btn_inline btn_search" onclick="treeSearch(); return false;">검색</button>
			</div>
			<div id="jstree" class="optionBar rows2"></div>
			</form:form>
		</div>
		
		<div class="right">
			<iframe id="BbsContentListSetFrame" name="BbsContentListSetFrame" style="width:100%;height:1100px"></iframe>
		</div>
	</div>
	
</body>
</html>
