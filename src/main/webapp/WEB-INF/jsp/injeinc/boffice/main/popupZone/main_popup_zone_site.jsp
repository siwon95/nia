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
- 제목 : 팝업사이트관리
- 파일명 : main_popup_zone_site.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	//입력이벤트
	$("select[name='code']").change(function(){
		var modal_id = "modal_area";
		var modal_url = "<c:url value='/boffice_noDeco/main/popupZone/MainPopupZoneSite.do' />";
		var modal_param = $(".modalWrap #MainPopupZoneVo").serializeArray();
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$("#chkAll").click(function(){
		if($(this).is(":checked")) {
			$("input[name=codeValue]").prop("checked", true);
		}else{
			$("input[name=codeValue]").prop("checked", false);
		}
	});
});

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	f.action = "/boffice_noDeco/sy/code/Code_reg.do";
}
</script>
</head>
<body>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>사이트별 영역 관리</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="MainPopupZoneVo" name="MainPopupZoneVo" method="post" onsubmit="return doForm(this);">
	<!-- <input type="hidden" name="returnUrl" value="/boffice_noDeco/main/popupZone/MainPopupZoneSite.do"/> -->
	<input type="hidden" name="returnUrl" value="/boffice/main/popupZone/MainPopupZoneList.do"/>
	<input type="hidden" name="codeUpr" value="35000000"/>
	<div class="tableBox">
		<table class="form">
		<caption>사이트별 영역 수정</caption>
		<colgroup>
			<col style="width:15%;">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="gubun">구분</label></th>
				<td>
					<select name="code">
					<c:set var="code" value="35000100"/>
					<c:if test="${fn:trim(param.code) ne ''}">
						<c:set var="code" value="${param.code}"/>
					</c:if>
					<c:set var="itemList" value="${cmm:getCodeList('35000000')}" />
					<c:set var="codeName" value=""/>
					<c:set var="selectSiteCd" value=""/>
					<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
						<c:if test="${code eq itemInfo.code}">
							<c:set var="codeName" value="${itemInfo.codeName}"/>
							<c:set var="selectSiteCd" value="${fn:trim(itemInfo.codeValue)}"/>
						</c:if>
					<%-- <c:if test="${itemInfo.code eq fn:trim(MainPopupZoneVo.code)}"> --%>
						<option value="<c:out value="${itemInfo.code}"/>" <c:if test="${code eq itemInfo.code}">selected</c:if>><c:out value="${itemInfo.codeName}"/></option>
					<%-- </c:if> --%>
					</c:forEach>
					</select>
					<input type="hidden" name="codeName" value="${codeName}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<label for="mzSitecd">사이트선택</label>
				</th>
				<td>
					<input type="checkbox" id="chkAll" name="chkAll" /><label for="chkAll">전체선택</label><br />
					<c:forEach var="siteInfo" items="${siteList }" varStatus="status">
						<c:set var="siteCd" value="${siteInfo.siteCd}" />
						<label>
							<input type="checkbox" id="code_value_<c:out value="${status.index}"/>" name="codeValue" value="<c:out value="${siteInfo.siteCd}"/>" <c:if test="${fn:indexOf(selectSiteCd , siteCd) > -1 }">checked="checked"</c:if> /> <c:out value="${siteInfo.siteNm }" />
						</label>
					</c:forEach>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<div class="btnArea">
		<input type="submit" value="확인" class="btn_m on">
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
	</form:form>
</div>
<!-- ============================== //모달영역 ============================== -->
