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
- 제목 : 권한설정
- 파일명 : mgr_authority_pop.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<c:import url="/layout/cms/head.jsp" ></c:import>
<script type="text/javascript">
$(function(){ 
	/* 트리 */
	$('#jstree1').jstree({
		 "checkbox": { "three_state": true},
	    "plugins" : [ "checkbox", "html_data"  ]
	}).bind("select_node.jstree",function(event,data){
	}).bind("deselect_node.jstree",function(event,data){
	});
	$('#jstree1').jstree("open_all");
	var tree = $('#jstree1').jstree(true);
	var all_node = tree.get_json('#', { 'flat': true });
	var length = ("<c:out value='${fn:length(authorityList)}'/>");
	var authorityList = "<c:out value='${authorityList}'/>";
	<c:forEach items='${authorityList }' var='result' varStatus='status'>
		var authorityInfo = "<c:out value='${result.saveChk}' />";
		var status = "<c:out value='${status.index}' />";
		
		if(authorityInfo == "Y"){
			tree.check_node(all_node[status]);
		}else{
			tree.uncheck_node(all_node[status]);
		}
	</c:forEach>
	$("#roading").css("display","none");
	$("#jstree1").css("display","block");
	
	//버튼이벤트
	$(".btn_save").click(function(e){
		e.preventDefault();
		doForm();
	});
	
	//입력이벤트
	$("#searchSite").change(function(){
		changeSearchSite();
	});
});

function doForm(){
	var nodeUpdates = [];
	var tree = $("#jstree1").jstree("get_checked", true);
	var jstEdit = $.jstree.reference('#jstree');
	for(var i = 0; i < tree.length; i++) {
		//var node = jstEdit.get_node(tree[i].id);
		var idx = tree[i].id;
		nodeUpdates.push(idx);
	};
	$('.jstree-undetermined').each(function() { //중간 체크일경우 체크라고 봄
		var idx = $(this).parent().parent().attr("id");
		nodeUpdates.push(idx);
	});
	var inputSiteCd = "";
	$("input[name='mgrSiteCd']:checked").each(function() {
		inputSiteCd += $(this).val()+",";
	});
	inputSiteCd = inputSiteCd.substring(0,inputSiteCd.length-1);
	var stringify = JSON.stringify(nodeUpdates);
	$.ajax({  
		type: "POST",
		url: "<c:url value='/boffice/sy/mgr/MgrAuthorityRegAx.do' />",
		data: {"searchType":$("#searchType").val(),"roleIdx":$("#roleIdx").val(),"checkList":stringify,"mgrSiteCd":inputSiteCd},
		dataType: "json",
		success: function(data) {
			alert(data.message);
		},
        error: function(response){
            //ajaxError(response);
        	console.log(response);
        }
	});
	$("#RoleVo").attr("action","/boffice/sy/mgr/MgrAuthorityPop.do").submit();
}

function menuChange(inNum){
	if(inNum==4){
		$("#btn1").attr("class","btn0 btn_input");
		$("#btn2").attr("class","btn0 btn_input");
		$("#btn3").attr("class","btn0 btn_input");
		$("#btn4").attr("class","btn3 btn_input");
		$("#view1").css("display","none");
		$("#view2").css("display","block");
	}else{
		$("#view1").css("display","block");
		$("#view2").css("display","none");
		if(inNum==1){
			$("#searchType").val("menu");
		}else if(inNum==2){
			$("#searchType").val("board");
		}else if(inNum==3){
			changeSearchSite();
		}
		$("#RoleVo").attr("action","/boffice/sy/mgr/MgrAuthorityPop.do").submit();
	}
}

function changeSearchSite(){
	$("#searchType").val($("#searchSite").val());
	$("#RoleVo").attr("action","/boffice/sy/mgr/MgrAuthorityPop.do").submit();
}
</script>
</head>
<body>
	<form:form commandName="RoleVo" id="RoleVo" name="RoleVo" method="post">
	<form:hidden path="roleIdx" />
	<input type="hidden" id="searchType" name="searchType" value="${fn:trim(param.searchType) eq ''?'menu':param.searchType}"/>
	<div class="section">
		<div class="divGroup">
			<div class="w40p">
				<div class="tableBox">
					<table class="view">
						<caption>권한선택 입력폼</caption>
						<colgroup>
							<col class="w100">
							<col> 
						</colgroup>
						<tbody>
							<tr>
								<th>권한명</th>
								<td><c:out value="${RoleVo.roleName}" /></td>
							</tr>
							<tr>
								<th>관리사이트</th>
								<td>
									<c:set var="RoleSiteCd" value=",${RoleVo.mgrSiteCd},"/>
									<c:forEach var="siteList" items="${siteList}" varStatus="status">
										<c:set var="siteCd" value=",${siteList.siteCd},"/>
										<input type="checkbox" name="mgrSiteCd" value="<c:out value="${siteList.siteCd}"/>" <c:if test="${fn:indexOf(RoleSiteCd,siteCd)>-1}">checked</c:if>>
										<c:out value="${siteList.siteNm}"/><br />
									</c:forEach>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btnArea">
					<a href="#" class="btn_m on btn_save">저장</a>
				</div>
			</div>
			<div class="w60p">
				<div id="jstreeText">
					<input type="button" id="btn1" onclick="menuChange(1)" class="btn_inline <c:out value="${param.searchType eq 'menu'?' on':''}"/>" value="관리자 "/>
					<input type="button" id="btn3" onclick="menuChange(3)" class="btn_inline <c:out value="${param.searchType ne 'menu' && param.searchType ne 'board'?' on':''}"/>" value="사용자"/>
					<input type="button" id="btn2" onclick="menuChange(2)" class="btn_inline <c:out value="${param.searchType eq 'board'?' on':''}"/>" value="게시판"/>
					<input type="button" id="btn4" onclick="menuChange(4)" class="btn_inline " value="기타"/>
				</div>
				<br />
				<!-- 관리자 메뉴 시작 -->
				<div id="view1">
					<c:set var="RoleSiteCd" value=",${RoleVo.mgrSiteCd},"/>
					<select id="searchSite" style="width:100%<c:if test="${fn:trim(param.searchType) eq 'menu' || fn:trim(param.searchType) eq 'board'}">;display:none</c:if>">
					<c:forEach var="siteList" items="${siteList}" varStatus="status">
						<c:set var="siteCd" value=",${siteList.siteCd},"/>
						<c:if test="${fn:indexOf(RoleSiteCd,siteCd)>-1}">
							<option value="<c:out value="${siteList.siteCd}"/>" <c:if test="${fn:trim(param.searchType) eq siteList.siteCd}">selected</c:if>><c:out value="${siteList.siteNm}"/></option>
						</c:if>
					</c:forEach>
					</select>
					<div id="roading" style="width:100%;border:1px #DDDDDD solid;text-align:center;height:50px;font-weight:bold;">
						<br/>로 딩 중
					</div>
					<div id="jstree1" class="roleTree" style="display:none">
						<c:set var="oldMenuDept" value="0"/>
						<c:forEach items="${authorityList }" var="result" varStatus="status">
							<c:choose>
								<c:when test="${result.depth == 0 || result.depth > oldMenuDept}">
									<ul>
										<li id="<c:out value="${result.idx}"/>" ><c:out value="${result.text }" />
								</c:when>
								<c:when test="${result.depth < oldMenuDept}">
									<c:forEach begin="1" end="${oldMenuDept - result.depth}" >
									</li>
										</ul>	
									</c:forEach>
									</li>
									<li id="<c:out value="${result.idx}"/>"><c:out value="${result.text }" />
								</c:when>
								<c:otherwise>
								</li>
									<li id="<c:out value="${result.idx}"/>">
										<c:out value="${result.text }" />
								</c:otherwise>
							</c:choose>
							<c:set var="oldMenuDept" value="${result.depth }"/>
						</c:forEach>
						<c:forEach var="i" begin="1" end="${oldMenuDept}">
								</li>
							</ul>
						</c:forEach>
					</div>
				</div>
				<!-- 관리자 메뉴 끝 -->
				<!-- 강좌 시작 -->
				<div id="view2" style="display:none">
					<div id="jstree2">
						<h4>강좌권한</h4>
						<div>
							<c:set var="authCount" value="${cmm:getAuthorlityCnt(RoleVo.roleIdx,'menu','117')}" />
							<c:choose>
								<c:when test="${authCount>0}">
									<c:set var="RoleSiteCd" value=",${RoleVo.mgrSiteCd},"/>
									<c:set var="itemList" value="${cmm:getCodeList('LC_ORG_CODE')}" />
									<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
										<c:set var="siteCd" value=",${itemInfo.code},"/>
										<input type="checkbox" name="mgrSiteCd" value="<c:out value="${itemInfo.code}"/>" <c:if test="${fn:indexOf(RoleSiteCd,siteCd)>-1}">checked</c:if>/> 
										<c:out value="${itemInfo.codeName}"/>
										<c:if test="${(status.index+1) % 3 == 0}"><br /></c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
									강좌권한은 
									<br/><b>[관리자메뉴 &gt; 통합예약 &gt; 통합강좌예약관리]</b>에<br/> 
									권한설정이 되어 있어야 합니다.
								</c:otherwise>
							</c:choose>
						</div>
						<br />
						<h4>기타예약권한</h4>
						<div>
							<h5>통합기타예약관리</h5>
							<c:set var="authCount" value="${cmm:getAuthorlityCnt(RoleVo.roleIdx,'menu','179')}" />
							<c:choose>
								<c:when test="${authCount>0}">
									<c:set var="RoleSiteCd" value=",${RoleVo.mgrSiteCd},"/>
									<c:set var="itemList" value="${cmm:getCodeList('RES_ETC_CODE')}" />
									<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
										<c:set var="siteCd" value=",${itemInfo.code},"/>
										<input type="checkbox" name="mgrSiteCd" value="<c:out value="${itemInfo.code}"/>" <c:if test="${fn:indexOf(RoleSiteCd,siteCd)>-1}">checked</c:if>/> 
										<c:out value="${itemInfo.codeName}"/>
										<c:if test="${(status.index+1) % 3 == 0}"><br /></c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
									통합기타예약관리는 
									<br/><b>[관리자메뉴 &gt; 통합예약 &gt; 통합기타예약관리]</b>에<br/> 
									권한설정이 되어 있어야 합니다.
								</c:otherwise>
							</c:choose>
							<br />
							<h5>안양천체육시설권한</h5>
							<c:set var="authCount" value="${cmm:getAuthorlityCnt(RoleVo.roleIdx,'menu','232')}" />
							<c:choose>
								<c:when test="${authCount>0}">
									<c:set var="RoleSiteCd" value=",${RoleVo.mgrSiteCd},"/>
									<input type="checkbox" name="mgrSiteCd" value="ycsfac" <c:if test="${fn:indexOf(RoleSiteCd,'ycsfac')>-1}">checked</c:if>/> 안양천체육시설
								</c:when>
								<c:otherwise>
									안양천체육시설권한은 
									<br/><b>[관리자메뉴 &gt; 통합예약 &gt; 통합시설예약관리&gt; 시설예약관리]</b>에<br/> 
									권한설정이 되어 있어야 합니다.
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<!-- 강좌 끝 -->
			</div>
		</div>
	</div>
	</form:form>
</body>
</html>
