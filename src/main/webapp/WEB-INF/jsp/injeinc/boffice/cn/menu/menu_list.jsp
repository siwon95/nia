<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 컨텐츠관리
- 파일명 : menu_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script src="/plugin/ckeditor/ckeditor.js"></script>
<script>
$(function(){
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>

	/* 트리 */
	$('#menuTree').css("display","").jstree({
		"core":{"check_callback":true},
	    "plugins":["dnd"] //, "plugins":["checkbox"]
	}).bind("loaded.jstree",function(event, data){ //로딩시
		$("#menuTree").jstree("select_node", "ul > li:first");
		var Selectednode = $("#menuTree").jstree("get_selected");
		$("#menuTree").jstree("open_node", Selectednode, false, true);
		
	}).bind("select_node.jstree", function(event, data){ //선택시
		if(data.node.id && data.node.id != 'j1_1'){
			var param = data.node.a_attr['data-param'].split(",");
			siteGo(param[0],param[1],param[2]);
		}
	
	}).bind("move_node.jstree",function(event,data){
		var instance = $('#menuTree').jstree(true);
		var pr = instance.get_node(data.node.parent);
		if(pr.id == "#" || (pr.id).replace("000000000000","") != (pr.id)){
			alert("TOP 메뉴로 이동 할수 없습니다!");
			document.location.reload();
			return;
		}
		if((data.node.id).replace("0000000000","") != (data.node.id)){
			alert("TOP 메뉴는 이동 할 수 없습니다!");
			document.location.reload();
			return;
		}
		var pr = instance.get_node(data.node.parent);
		document.myorder.preSortOrder.value = pr.id;
		document.myorder.moveOrderStep.value = data.position;
		if(data.position==0){
			document.myorder.moveSortOrder.value = "0";
		}else{
			document.myorder.moveSortOrder.value = pr.children[data.position-1];
		}
		document.myorder.nowSortOrder.value = data.node.id;
		var tempHtml = "<div id=\"html\" style=\"padding:5px;width:210px;border:1px solid #99BBE8\"><ul><li>재정렬 중입니다.<br /> 잠시만 기다려 주십시요!</li></ul></div>";
		document.getElementById("html").innerHTML=tempHtml;
		document.myorder.submit();
	});

	//버튼이벤트
	$(".btn_save").click(function(e){
		e.preventDefault();
		$(document.MenuVo).attr("action", "/boffice_noDeco/cn/menu/Menu_User_Create.do").submit();
	});
	$(".btn_saveAllSite").click(function(e){
		e.preventDefault();
		$(document.MenuVo).attr("action", "/boffice_noDeco/cn/menu/Menu_User_Create_All.do").submit();
	});
	$(".btn_saveAllMenu").click(function(e){
		e.preventDefault();
		if(confirm("전체컨텐츠를 반영하시겠습니까?")){
			document.location.href="/boffice/cn/menu/User_Menu_Contents_Create_File.do";
		}
	});
	$(".btn_add").click(function(e){
		e.preventDefault();
		if($("#siteCd").val() == ""){
			alert("사이트를 선택하여 주십시요.");
			$("#siteCd").focus();
			return;
		}
		$('#iMenuDetail').get(0).contentWindow.doListAdd();
	});
	
	//
	$("#siteCd").change(function(){
		siteGo($(this).val(),'','');
	});
});

function siteGo(inSiteCode,inMenuCode,adminMenuUrl){
	if(inMenuCode==""){
		document.location.href="menu_list.do?siteCd="+inSiteCode+"&menuCode="+inMenuCode;
	}else{
		$("#iMenuDetail").attr("src","/boffice_noDeco/cn/menu/menu_detail.do?siteCd="+inSiteCode+"&menuCode="+inMenuCode);
		$("#tapMenuUrl").val("/boffice_noDeco/cn/menu/menu_detail.do?siteCd="+inSiteCode+"&menuCode="+inMenuCode);
		$("#tapAdminUrl").val(adminMenuUrl);
	}
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<form name="myorder" method="post" action="/boffice/cn/menu/menu_order.do">
	<input type="hidden" name="preSortOrder" value=""/>
	<input type="hidden" name="moveOrderStep" value=""/>
	<input type="hidden" name="moveSortOrder" value=""/>
	<input type="hidden" name="nowSortOrder" value=""/>
	<input type="hidden" name="siteCd" value="<c:out value="${MenuVo.siteCd}"/>"/>
	<input type="hidden" name="menuCode" value="<c:out value="${MenuVo.menuCode}"/>"/>
	</form>
	
	<div class="section divGroup leftTree">
		<div class="left">
			<div class="siteSelect">
				<form:form commandName="MenuVo" id="MenuVoSearch" name="MenuVoSearch" method="post">
				<form:hidden path="menuCode" />
				<form:select path="siteCd" title="사이트선택" class="w100p">
					<form:option value="" label="사이트선택" />
					<form:options items="${listSiteAll}" itemValue="siteCd" itemLabel="siteNm" />
				</form:select>
				</form:form>
			</div>
			<div class="treeTopBtn">
				<input type="button" value="메뉴수정적용" class="btn_inline btn_save">
				<input type="button" value="하위메뉴추가" class="btn_inline btn_add">
				<%-- <c:if test="${param.mode eq 'all'}"> --%>
				<!-- <input type="button" value="전체사이트적용" class="btn_inline btn_saveAllSite"> -->
				<%-- <%
				java.net.InetAddress ip;
				ip = java.net.InetAddress.getLocalHost();
				if((ip.getHostAddress().equals("192.168.0.8") || ip.getHostAddress().equals("211.42.168.72") || ip.getHostAddress().equals("211.42.168.73")) && (session.getAttribute("SesUserId").toString()).equals("yangcheon")){
				%>
				<input type="button" value="전체컨텐츠반영" class="btn_inline btn_saveAllMenu">
				<%}%> --%>
			</div>
			<c:choose>
				<c:when test="${fn:trim(MenuVo.siteCd) eq ''}">
			<div id="menuTree" class="optionBar rows2">
				<ul>
					<li>사이트를 선택해주세요</li>
				</ul>	
			</div>
				</c:when>
				<c:otherwise>
			<form:form commandName="MenuVo" id="MenuVo" name="MenuVo" method="post">
			<form:hidden path="menuCode" />
			<form:hidden path="siteCd" />
			<form:hidden path="menuDepth" />
			<form:hidden path="sortOrder" />
			<form:hidden path="chargeDeptCode" />
			<c:set var="oldMenuDept" value="0"/>
			<c:set var="rMenuNm" value=""/>
			
			<div id="menuTree" class="optionBar rows2">
				<c:forEach items="${menuVoList}" var="result" varStatus="status">
					<c:set var="rMenuNm" value="${result.menuNm }"/>
					<c:set var="rMenuCode" value="${result.menuCode }"/>
					<c:set var="rSiteCd" value="${MenuVo.siteCd}"/>
					<c:set var="rMenuType" value="${result.menuType}"/>
					<c:set var="rUserMenuUrl" value=""/>
					<c:set var="rAdminUrl" value=""/>
					<c:choose>
						<c:when test="${result.menuType eq 'contents'}">
							<c:set var="rAdminUrl" value="/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=${rSiteCd}&menuCode=${result.menuCode}"/>
						</c:when>
						<c:when test="${result.menuType eq 'program'}">
							<c:if test="${fn:indexOf(result.userMenuUrl,'cbIdx')>-1}">
								<c:set var="arrUserMenuUrl" value="${fn:split(fn:replace(result.userMenuUrl,'/',''),'=')}"/>
								<c:set var="rAdminUrl" value="/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=${arrUserMenuUrl[1]}"/>
							</c:if>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${result.menuDepth > oldMenuDept || status.index==0}">
							<ul>
								<li id="<c:out value="${result.sortOrder}"/>"<c:if test="${param.menuCode eq rMenuCode}"> data-jstree='{"selected":true}'</c:if>>
									<a href="#" class="<c:if test="${result.publishYn eq 'Y' }">ing</c:if>" data-param="<c:out value="${rSiteCd}"/>,<c:out value="${rMenuCode}"/>,<c:out value="${rAdminUrl}"/>"><c:out value="${rMenuNm}"/></a>
						</c:when>
						<c:when test="${result.menuDepth < oldMenuDept}">
							<c:forEach var="i" begin="1" end="${oldMenuDept-result.menuDepth}">
								</li>								
							</ul>
							</c:forEach>
								</li>
								<li id="<c:out value="${result.sortOrder}"/>"<c:if test="${param.menuCode eq rMenuCode}"> data-jstree='{"selected":true}'</c:if>>
									<a href="#" class="<c:if test="${result.publishYn eq 'Y' }">ing</c:if>" data-param="<c:out value="${rSiteCd}"/>,<c:out value="${rMenuCode}"/>,<c:out value="${rAdminUrl}"/>"><c:out value="${rMenuNm}"/></a>
						</c:when>
						<c:otherwise>
								</li>
								<li id="<c:out value="${result.sortOrder}"/>"<c:if test="${param.menuCode eq rMenuCode}"> data-jstree='{"selected":true}'</c:if>>
									<a href="#" class="<c:if test="${result.publishYn eq 'Y' }">ing</c:if>" data-param="<c:out value="${rSiteCd}"/>,<c:out value="${rMenuCode}"/>,<c:out value="${rAdminUrl}"/>"><c:out value="${rMenuNm}"/></a>
						</c:otherwise>
					</c:choose>				
					<c:set var="oldMenuDept" value="${result.menuDepth}"/>
				</c:forEach>
				<c:forEach var="i" begin="1" end="${oldMenuDept}">
								</li>
							</ul>
				</c:forEach>
				</li>
				</ul>
			</div>
			</form:form>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="right">
			<c:choose>
				<c:when test="${fn:trim(MenuVo.siteCd) eq ''}">
				</c:when>
				<c:otherwise>
			<input type="hidden" name="tapMenuUrl" id="tapMenuUrl" value="/boffice_noDeco/cn/menu/menu_detail.do?siteCd=<c:out value="${MenuVo.siteCd}"/>&menuCode=<c:out value="${MenuVo.menuCode}"/>"/>
			<input type="hidden" name="tapAdminUrl" id="tapAdminUrl" value=""/>
			<%-- <span style="float:left;font-weight:bold;font-size:15px;padding-top:6px"><c:out value="${MenuVo.menuPath}"/></span> --%>
			<!-- <input type="button" id="btnMenu" value="메뉴관리 " class="btn_inline" onclick="tabMenuGo()"/> -->
			<!-- <input type="button" id="btnAdmin" value="컨텐츠관리" class="btn_inline" onclick="tabAdminGo()"/> -->
			<iframe name="iMenuDetail" id="iMenuDetail" style="width:100%;height:1100px" src="/boffice_noDeco/cn/menu/menu_detail.do?siteCd=<c:out value="${param.siteCd}"/>&menuCode=<c:out value="${param.menuCode}"/>"></iframe>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>
