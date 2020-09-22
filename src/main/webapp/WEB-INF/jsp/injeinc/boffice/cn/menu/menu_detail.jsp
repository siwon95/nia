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
- 파일명 : menu_detail.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<c:import url="/layout/cms/head.jsp" ></c:import>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
var siteCd = '<c:out value="${MenuVo.siteCd}"/>';
var menuCode = '<c:out value="${MenuVo.menuCode}"/>';
var metaTagValueList=new Array();
<c:forEach items="${metaTagValueList}" var="metaTagValue" >
	metaTagValueList.push({"codeUpr":"${metaTagValue.codeUpr}","codeName":"${metaTagValue.codeName}"});
</c:forEach>
$(function(){
	if('<c:out value="${MenuVo.menuType}"/>' == 'link'){
		$("input[name=userMenuSubUrl]").attr("disabled",true);
		$("input[name=userMenuSubTitle]").attr("disabled",true);
	}
	
	//버튼이벤트
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(confirm("<spring:message code="common.delete.msg" />")){
			$("#actionType").val("del");
			$("#MenuVo").submit();
		}
	});
	$("#btn_contentForm").click(function(e){
		e.preventDefault();
		var modal_id = "modal_contentForm";
		var modal_url = "/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do";
		var modal_param = {"siteCd":siteCd,"menuCode":menuCode};
		var modal_class = "wide"; //wide, small
		parent.ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$("#btn_contentHistory").click(function(e){
		e.preventDefault();
		var modal_id = "modal_contentHistory";
		var modal_url = "/boffice_noDeco/cn/menu/User_Menu_Contents_List.do";
		var modal_param = {"siteCd":siteCd,"menuCode":menuCode};
		var modal_class = ""; //wide, small
		parent.ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$("#btn_satisfactionList").click(function(e){
		e.preventDefault();
		var modal_id = "modal_satisfactionList";
		var modal_url = "/boffice_noDeco/cn/menu/User_Menu_Satisfaction_List.do";
		var modal_param = {"siteCd":siteCd,"menuCode":menuCode};
		var modal_class = ""; //wide, small
		parent.ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_empSelect").click(function(e){ //담당자선택
		e.preventDefault();
		window.open("/boffice_noDeco/group/emp/Emp_List.do","winPopEmpList","width=1000,height=500,scrollbars=yes");
	});
	$(".btn_boardSet").click(function(e){ //게시판설정
		e.preventDefault();
		var treeLocationId=$("#bbsList option:selected").attr("data-cbcd");
		window.open("/boffice/sy/board/BoardList.do?cmm_code=BB100000&mgrSiteCd=<c:out value="${MenuVo.siteCd}"/>&treeLocationId="+treeLocationId);
	});
	$(".btn_boardContent").click(function(e){ //게시물관리
		e.preventDefault();
		var treeLocationId=$("#bbsList option:selected").attr("data-cbcd");
		window.open("/boffice/ex/board/BbsContentList.do?mgrSiteCd=<c:out value="${MenuVo.siteCd}"/>&treeLocationId="+treeLocationId);
	});
	
	//입력이벤트
	$("input[name='menuType']").click(function(){
		var menuTypeNo = parseInt($(this).attr("id").replace("menuType",""));
		switch(menuTypeNo){
			case '1':
				$("#headCon").hide();
				$("input[name=userMenuSubUrl]").attr("disabled",true);
				$("input[name=userMenuSubTitle]").attr("disabled",true);
				break;
			case '2':
				$("#headCon").hide();
				$("input[name=userMenuSubUrl]").attr("disabled",false);
				$("input[name=userMenuSubTitle]").attr("disabled",false);
				break;
			case '3':
				$("#headCon").show();
				$("input[name=userMenuSubUrl]").attr("disabled",false);
				$("input[name=userMenuSubTitle]").attr("disabled",false);
				break;
		}
		$("#viewContentType1, #viewContentType2, #viewContentType3").hide();
		$("#viewContentType"+menuTypeNo).show();
	});
	$("#bbsList").change(function(){
		if($(this).val() == ""){ // 직접입력
			$("#userMenuUrl").val("");
			$("input[name='userMenuSubUrl']").first().val("");
			$("input[name='userMenuSubTitle']").first().val("");
		}else{
			var siteCd = $("input[name='siteCd']").val();
			$("#userMenuUrl").val('/site/'+siteCd+'/ex/bbs/List.do?cbIdx='+$(this).val());
			$("input[name='userMenuSubUrl']").first().val('/site/'+siteCd+'/ex/bbs/View.do?cbIdx='+$(this).val());
			$("input[name='userMenuSubTitle']").first().val("상세보기");
			$("input[name='userMenuSubUrl']:eq(1)").val('/site/'+siteCd+'/ex/bbs/Regist.do?cbIdx='+$(this).val());
			$("input[name='userMenuSubTitle']:eq(1)").val("글쓰기");
		}
	});
	$("input[name='menuType']").each(function(idx, element){
		if(element.checked){
			if(element.value == "program" && $("#userMenuUrl").val().indexOf("/ex/bbs/List.do?cbIdx=") != -1){
				siteCd = $("#userMenuUrl").val().substring($("#userMenuUrl").val().indexOf("cbIdx=")+6);
				$("#bbsList").val(siteCd);
			}
		}
	});
	if($("#userMenuUrl").val()){
		var siteUrl = window.location.href.split("/");
		$(".btn_windowPopup").attr("data-url",siteUrl[0]+"//"+siteUrl[2]+$("#userMenuUrl").val());	
	}else{
		$(".previewBox").hide();
	}
	$("select[name='metaProperty']").change(function(){
		metaTagSelectChange(this);
	});
	metaTagSelectChangeAll();
});

/*var addCount = 0;
function subUrlAdd(){
	addCount++;
	var subUrlTmp = "";
	subUrlTmp="<input name=\"userMenuSubUrl\" type=\"text\" size=\"60\"/>";
	subUrlTmp+="<span id=\"subMenuAdd_"+(addCount+1)+"\"  style=\"padding-top:3px\"></span>";
	document.getElementById("subMenuAdd_"+addCount).innerHTML = subUrlTmp;
	
}
function subCount(){
	alert(document.getElementsByName("userMenuSubUrl").length);
}*/

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	var actionType = $("#actionType").val();
	
	if(actionType == "mod"){
		$("#MenuVo").attr("action", "/boffice/cn/menu/menu_mod.do");
	}else if(actionType == "del"){
		$("#MenuVo").attr("action", "/boffice_noDeco/cn/menu/menu_del.do");
	}
	return true;
}

function doFormAdd(f) {
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	f.action = "/boffice_noDeco/cn/menu/menu_add.do";
	return true;
}

function doListAdd(){
	$("#MenuAdd #menuNm").val("추가메뉴");
	$('#MenuAdd input:radio[name=sameLevel]:input[value=N]').attr("checked", true);
	$("#MenuAdd").submit();
}
function metaTagAdd(){
	var li=$("#metaTagList li").last().clone(true);
	li.find("input[type='text']").val("");
	li.find("select option").attr("selected",false);
	$("#metaTagList").append(li);
}
function metaTagRemove(t){
	if($("#metaTagList li").length <= 1){
		$("#metaTagList li").find("input[type='text']").val("");
		$("#metaTagList li").find("select option").attr("selected",false);
		return false;
	}
	$(t).closest("li").remove();
}
function metaTagSelectChangeAll(){
	for(i=0;i<$("#metaTagList li").length;i++){
		var t=$("#metaTagList li").eq(i).find("select[name='metaProperty']");
		metaTagSelectChange(t);
	}
}
function metaTagSelectChange(t){
	var codeUpr=$(t).find("option:selected").attr("attr-code");
	var metaValueSelect=$(t).closest("li").find("select[name='metaValue']");
	var metaValue=metaValueSelect.val();
	metaValueSelect.find("option:not(:first)").remove();
	$.each(metaTagValueList, function(key, value){
		var selected="";
		if(metaTagValueList[key].codeUpr==codeUpr){
			if(metaTagValueList[key].codeName==metaValue)
				selected=" selected"
			metaValueSelect.append("<option value='"+metaTagValueList[key].codeName+"'"+selected+">"+metaTagValueList[key].codeName+"</option>");
		}
	});
}
function subUrlDisabled(status){
	$("input[name='userMenuSubUrl'], input[name='userMenuSubTitle']").attr("disabled",status);
	if(status){
		$("input[name='userMenuSubUrl'], input[name='userMenuSubTitle']").val("");
	}
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<form:form commandName="MenuVo" id="MenuVo" name="MenuVo" method="post" action="/boffice/cn/menu/menu_mod.do" onsubmit="return doForm(this);">
	<form:hidden path="menuCode" />
	<form:hidden path="siteCd" />
	<form:hidden path="menuDepth" />
	<form:hidden path="sortOrder" />
	<form:hidden path="chargerId"/>
	<form:hidden path="chargeDeptCode" />
	<input type="hidden" name="actionType" id="actionType" value="mod"/>
	<c:set var="oldMenuDept" value="0"/>
	<c:set var="rMenuNm" value=""/>
	
	<div class="tableTitle">
		<span class="text">
			최종수정일 : <c:out value="${UserMenuContentsVo.modDt }"/> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			상태 : <c:out value="${UserMenuContentsVo.progressStatusNm }"/>
		</span>
		<div class="btnArea right">
			<div class="previewBox inline">
				<a href="#" class="btn_previewBoxOpen"><em>미리보기</em></a>
				<ul>
					<li class="item1"><a href="#" class="btn_windowPopup" data-url="" data-option="width=1200,height=800"><em>데스크톱</em></a></li>
					<li class="item2"><a href="#" class="btn_windowPopup" data-url="" data-option="width=768,height=700"><em>테블릿</em></a></li>
					<li class="item3"><a href="#" class="btn_windowPopup" data-url="" data-option="width=360,height=640"><em>모바일</em></a></li>
				</ul>
			</div>
			&nbsp;&nbsp;
			<input type="submit" value="저장" class="btn_inline on">
			<c:if test="${menuUnderCount eq '1'}">
				<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
			</c:if>
		</div>
	</div>
	<div class="tableBox">
		<table class="form">
			<caption></caption>
			<colgroup>
				<col class="w150">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th>메뉴 경로</th>
					<td>
						<c:out value="${MenuVo.menuPath}"/> 
					</td>
				</tr>
				<tr>
					<th><label for="menuNm">메뉴명</label></th>
					<td>
						<c:choose>
						<c:when test="${fn:trim(MenuVo.menuCode) eq '' || fn:trim(MenuVo.menuCode) eq '10000000000000000000000'}">
							<form:hidden path="menuNm" value="HOME"/>
							HOME
						</c:when>
						<c:otherwise>
							<form:input path="menuNm" class="required w200" /> (최대 50자)
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><label for="title">타이틀명</label></th>
					<td><form:input path="title" class="w200" /></td>
				</tr>
				<tr>
					<th><label for=headContent>상단 컨텐츠</label></th>
					<td><form:textarea path="headContent" title="상단 컨텐츠" rows="3" class="w100p"/></td>
				</tr>
				<tr>
					<th><label for="useYn">사용 여부</label></th>
					<td>
						<label><form:radiobutton path="useYn" value="Y"  title="사용 여부 사용"/> 사용</label> &nbsp;
		                <label><form:radiobutton path="useYn" value="N" title="사용 여부 미사용"/> 미사용</label>
					</td>
				</tr>
				<c:choose>
					<c:when test="${MenuVo.menuDepth=='1'}">
				<tr>
					<th><label for="showYn">Top메뉴표시</label></th>
					<td>
						<label><form:radiobutton path="showYn" value="Y"  title="표시"/> 표시</label> &nbsp;
		                <label><form:radiobutton path="showYn" value="N" title="미표시" /> 미표시</label>
					</td>
				</tr>
					</c:when>
					<c:otherwise>
				<form:hidden path="showYn" value="Y"/>
					</c:otherwise>
				</c:choose>
				<tr style="display:none">
					<th><label for="boonyaYn">분야별정보 여부</label></th>
					<td>
						<label><form:radiobutton path="boonyaYn" value="Y" /> 사용</label> &nbsp;
		                <label><form:radiobutton path="boonyaYn" value="N" /> 미사용</label>
					</td>
				</tr>
				<tr>
					<th><label for="menuType">메뉴유형</label></th>
					<td>
						<label><form:radiobutton path="menuType" value="link" onclick="subUrlDisabled(true);" /> 단순링크</label> &nbsp;
						<label><form:radiobutton path="menuType" value="contents" onclick="subUrlDisabled(false);" /> 컨텐츠</label> &nbsp;
						<label><form:radiobutton path="menuType" value="program" onclick="subUrlDisabled(false);" /> 게시판/프로그램</label>
					</td>
				</tr>
				<tr>
					<th>컨텐츠/프로그램관리</th>
					<td>
						<c:set var="viewContentType1Chk" value="none"/>
						<c:set var="viewContentType2Chk" value="none"/>
						<c:set var="viewContentType3Chk" value="none"/>
						<c:if test="${MenuVo.menuType eq 'link'}">
						<c:set var="viewContentType1Chk" value=""/>
						</c:if>
						<c:if test="${MenuVo.menuType eq 'contents'}">
						<c:set var="viewContentType2Chk" value=""/>
						</c:if>
						<c:if test="${MenuVo.menuType eq 'program'}">
						<c:set var="viewContentType3Chk" value=""/>
						</c:if>
						<span id="viewContentType1" style="display:<c:out value="${viewContentType1Chk}"/>">
							링크주소를 [사용자메뉴URL]에 넣어 주십시요!
						</span>
						<span id="viewContentType2" style="display:<c:out value="${viewContentType2Chk}"/>">
							<input type="button" value="컨텐츠내용관리" id="btn_contentForm" class="btn_inline" />
							<input type="button" value="컨텐츠이력관리" id="btn_contentHistory" class="btn_inline" />
						</span>
						<span id="viewContentType3" style="display:<c:out value="${viewContentType3Chk}"/>">
							<select id="bbsList" title="게시판선택">
								<option value="">URL직접입력</option>
								<c:forEach items="${treeBoardVoList}" var="treeBoardVo" >
									<c:if test="${treeBoardVo.cbIdx>0}">
										<option value="${treeBoardVo.cbIdx}" data-cbcd="${treeBoardVo.id}" <c:if test="${treeBoardVo.bbsYn eq 'Y'}">style="color:blue"</c:if>>
											<c:forEach begin="2" end="${treeBoardVo.cbDepth}">
												&nbsp;&nbsp;
											</c:forEach>
											${treeBoardVo.text}
										</option>
									</c:if>
								</c:forEach>
							</select>
							<a href="#" class="btn_inline btn_boardSet" target="_blank" title="새창열림">게시판설정</a>
							<a href="" class="btn_inline btn_boardContent" target="_blank" title="새창열림">게시물관리</a>
						</span>
					</td>
				</tr>
				<c:set var="adminUrlView" value="${viewContentType3Chk}"/>
				<c:if test="${MenuVo.menuCode eq '10000000000000000000000'}">
				<c:set var="adminUrlView" value="none"/>
				</c:if>
				<%-- <tr style="display:<c:out value="${adminUrlView}"/>">
					<th><label for="userMenuUrl">관리자URL</label></th>
					<td>
						<form:input path="adminMenuUrl" size="60" title="관리자URL"/> 
					</td>
				</tr> --%>
				<tr>
					<th><label for="userMenuUrl">사용자메뉴URL</label></th>
					<td>
						<form:input path="userMenuUrl" size="60" title="사용자메뉴URL"/>
					</td>
				</tr>
				<tr id="underUrl">
					<th><label for="userMenuSubUrl">하위URL</label></th>
					<td>
						<div class="menuUrlList">
							<c:set var="subMenuCount" value="-1"/>
							<c:forEach items="${menuSubList}" var="result2" varStatus="status2">
								<input name="userMenuSubUrl" type="text" size="60" value="<c:out value="${result2.subMenuUrl}"/>"/>&nbsp;<input type="text" name="userMenuSubTitle" maxlength="100" value="<c:out value="${result2.subMenuTitle}"/>" /><br />
								<c:if test="${status2.index==0}">
							<!-- 	<input type="button" value="추가" class="btn" style="
								cursor:pointer;" onclick="subUrlAdd()"/>
								<input type="button" value="확인" class="btn" style="
								cursor:pointer;" onclick="subCount()"/> -->
								</c:if>
								<c:set var="subMenuCount" value="${status2.index}"/>
							</c:forEach>
							<input id="userMenuSubUrl1" name="userMenuSubUrl" type="text" size="60" value=""/>&nbsp;
							<input type="text" id="userMenuSubTitle1" name="userMenuSubTitle" maxlength="100" /><br />
							<input id="userMenuSubUrl2" name="userMenuSubUrl" type="text" size="60" value=""/>&nbsp;
							<input type="text" id="userMenuSubTitle2" name="userMenuSubTitle" maxlength="100" /><br />
							<input id="userMenuSubUrl3" name="userMenuSubUrl" type="text" size="60" value=""/>&nbsp;
							<input type="text" id="userMenuSubTitle3" name="userMenuSubTitle" maxlength="100" />
							<span id="subMenuAdd_1"></span>
						</div>
					</td>
				</tr>
				<tr>
					<th><label for="linkType">링크유형</label></th>
					<td>
						<label><form:radiobutton path="linkType" value="normal"  title="기본화면"/> 기본화면</label> &nbsp;
						<label><form:radiobutton path="linkType" value="popup"  title="팝업(새창)"/> 팝업(새창)</label> &nbsp;
						<%-- <form:radiobutton path="linkType" value="layor"  title="레이어"/> 레이어 --%>
					</td>
				</tr>
				<tr>
					<th><label for="layoutCode">레이아웃선택</label></th>
					<td>
						<form:select path="layoutCode" title="레이아웃선택">
							<form:option value="" label="--레이아웃선택--" />
							<form:options items="${listLayout}" itemValue="layoutCode" itemLabel="layoutDesc" />
						</form:select>
					</td>
				</tr>
				<!--
				<tr>
					<th><label for="metaTagContent">메타태그</label></th>
					<td>
						<textarea id="metaTagContent" name="metaTagContent" title="메타태그 컨텐츠" rows="8" class="w100p">${MenuVo.metaTagContent}</textarea>
					</td>
				</tr>
				 -->
				<tr>
					<th><label for="mataTagName">메타태그</label></th>
					<td>
						<div>
							예시<br>
							&lt;meta name="Keywords" content="인재,인재아이엔씨,인재INC"&gt;<br>
							속성 : name 속성값 : Keywords 내용 : 인재,인재아이엔씨,인재INC
						</div>
						<ul id="metaTagList">
						<c:forEach items="${metaTagList}" var="result2">
							<li>
								속성 : 
								<select name="metaProperty">
								<option value="" attr-code="">선택</option>
								<c:forEach items="${metaTagPropertyList}" var="metaTagProperty" >
									<option value="${metaTagProperty.codeName}" attr-code="${metaTagProperty.code}"<c:if test="${result2.metaProperty eq metaTagProperty.codeName}"> selected</c:if>>${metaTagProperty.codeName}</option>
								</c:forEach>
								</select>
								속성값 : 
								<select name="metaValue">
								<option value="" attr-codeUpr="">선택</option>
								<c:forEach items="${metaTagValueList}" var="metaTagValue" >
									<option value="${metaTagValue.codeName}"<c:if test="${result2.metaValue eq metaTagValue.codeName}"> selected</c:if>>${metaTagValue.codeName}</option>
								</c:forEach>
								</select>
								내용 : <input name="metaCont" type="text" size="60" value="${result2.metaCont}" placeholder="내용을 넣어 주세요!"/>
								<input type="button" value="삭제" class="btn btn_ss"  onclick="metaTagRemove(this);"/>
							</li>
						</c:forEach>
						<c:if test="${fn:length(metaTagList)<=0}">
							<li>
								속성 : 
								<select name="metaProperty">
								<option value="" attr-code="">선택</option>
								<c:forEach items="${metaTagPropertyList}" var="metaTagProperty" >
									<option value="${metaTagProperty.codeName}" attr-code="${metaTagProperty.code}">${metaTagProperty.codeName}</option>
								</c:forEach>
								</select>
								속성값 : 
								<select name="metaValue">
								<option value="" attr-codeUpr="">선택</option>
								</select>
								내용 : <input name="metaCont" type="text" size="60" placeholder="내용을 넣어 주세요!"/>
								<input type="button" value="삭제" class="btn btn_ss"  onclick="metaTagRemove(this);"/>
							</li>
						</c:if>
						</ul>
						<input type="button" value="추가" class="btn btn_ss" onclick="metaTagAdd();"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="tableTitle">
		<h4>선택 옵션</h4>
	</div>
	<div class="tableBox">
		<table class="form">
			<caption></caption>
			<colgroup>
				<col style="width:150px;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th><label for="socialShowYn">소셜 공유</label></th>
					<td>
						<label><form:radiobutton path="socialShowYn" value="Y"  title="사용"/> 사용</label> &nbsp;
		                <label><form:radiobutton path="socialShowYn" value="N" title="미사용" /> 미사용</label>
					</td>
				</tr>
				<tr>
					<th><label for="satisfyShowYn">만족도 조사</label></th>
					<td>
						<label><form:radiobutton path="satisfyShowYn" value="Y"  title="사용"/> 사용</label> &nbsp;
		                <label><form:radiobutton path="satisfyShowYn" value="N" title="미사용" /> 미사용</label>
		                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${MenuVo.satisfyShowYn eq 'Y'}">
						<input type="button" id="btn_satisfactionList" class="btn_inline" value="만족도보기" />
						</c:if>
					</td>
				</tr>
				<tr>
					<th><label for="chargerShowYn">메뉴 담당자 정보</label></th>
					<td>
						<label><form:radiobutton path="chargerShowYn" value="Y"  title="메뉴담당자표시"/> 표시</label> &nbsp;
		                <label><form:radiobutton path="chargerShowYn" value="N" title="메뉴담당자미표시" /> 미표시</label>
		                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn_inline btn_empSelect" value="담당자선택" />
						<table class="form">
						<caption></caption>
						<colgroup>
							<col style="width:12%;">
							<col>
							<col style="width:16%;">
							<col style="width:24%;">
						</colgroup>
						<tbody>
							<tr>
								<th>부서</th>
								<td>
									<span id="idChargeDepart">
										<c:out value="${emp.ceDepstep1}"/>
										<c:if test="${fn:replace(emp.ceDepstep2,'null','') ne ''}">
											&gt; <c:out value="${emp.ceDepstep2}"/>
										</c:if>
										<c:if test="${fn:replace(emp.ceDepstep3,'null','') ne ''}">
											&gt; <c:out value="${emp.ceDepstep3}"/>
										</c:if>
									</span>
								</td>
								<th>담당자명</th>
								<td><span id="idChargeName"><c:out value="${emp.ceName}"/></span></td>
							</tr>
							<tr>
								<th>전화</th>
								<td><span id="idChargeTel"><c:out value="${emp.ceTel}"/></span></td>
								<th>팩스</th>
								<td><span id="idChargeFax"><c:out value="${emp.cdFax}"/></span></td>
							</tr>
						</tbody>
						</table>
						<form:checkbox path="orgTel" value="Y"  title="사용"/> 부서명만 표시하고 담당자명,전화,팩스는 미표시
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%-- <br /><br />
	<span style="font-weight:bold;" >부가정보</span><br/>
	<table class="form" >
		<caption></caption>
		<colgroup>
			<col style="width:150px;">
			<col style="width:650px;">
		</colgroup>
		<tbody>
			<tr>
				<th>본문제목이미지</th>
				<td><input type="file" name="menuTitleImg1" /></td>
			</tr>
			<tr>
				<th>메뉴ON이미지</th>
				<td><input name="menuOnImg1" type="file" /></td>
			</tr>
			<tr>
				<th>메뉴OFF이미지</th>
				<td><input name="menuOffImg1" type="file" /></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${MenuVo.regDt}</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${MenuVo.modDt}</td>
			</tr>
		</tbody>
	</table> --%>
	</form:form>

	<div class="soundOnly">
		<form:form commandName="MenuVo" id="MenuAdd" name="MenuAdd" method="post" onsubmit="return doFormAdd(this);">
		<form:hidden path="menuCode" />
		<form:hidden path="menuDepth" />
		<form:hidden path="siteCd"/>
		<form:hidden path="sortOrder" />
		<div class="tableTitle">
			<h4>메뉴추가</h4>
		</div>
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col style="width:150px">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th><label for="sameLevel">메뉴 위치</label></th>
						<td>
							<c:if test="${fn:trim(MenuVo.menuCode) ne '1000000000000'}">
							<label><form:radiobutton path="sameLevel" value="Y"  title="동일 위치에 추가"/> 동일 위치에 추가</label>
							</c:if>
							<label><form:radiobutton path="sameLevel" value="N"  title="하위에 추가"/> 하위에 추가</label>
						</td>
					</tr>
					<tr>
						<th><label for="menuNm">메뉴명</label></th>
						<td>
							<input name="menuNm" id="menuNm" type="text" class="required w200" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<input type="submit" value="추가" class="btn_m on btn_add">
		</div>
		</form:form>
	</div>
</div>
</body>
</html>
