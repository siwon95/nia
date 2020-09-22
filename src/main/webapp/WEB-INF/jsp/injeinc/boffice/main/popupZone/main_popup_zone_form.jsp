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
- 제목 : 배너관리
- 파일명 : main_popup_zone_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
var modalActionType = "";
$(function(){
	//버튼이벤트
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		modalActionType = "delete";
		$(".modalContent #MainPopupZoneVo").submit();
	});
	$("#chkAll").click(function(){
		if($(this).is(":checked")) {
			$("input[name=mzSitecd]").prop("checked", true);
		}else{
			$("input[name=mzSitecd]").prop("checked", false);
		}
	});
});

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	if($("input[name=mzSitecd]").is(":checked") == false){
		alert("적용할 사이트를 선택해 주십시요!");
		$("input[name=mzSitecd]")[0].focus();
		return false;
	}
	if($("#mzSitecd").val() == "") {
		alert("사이트를 지정해주십시오.");
		$("#mzSitecd").focus();
		return false;
	}
	
	if(modalActionType == "delete"){
		f.action = "<c:url value='/boffice/main/popupZone/MainPopupZoneRmv.do'/>";
	}else if($("#actionkey").val() == "regist") {
		if($("#dataFile").val() == "") {
			alert("파일을 선택해주십시오.");
			$("#dataFile").focus();
			return false;
		}
		f.action = "<c:url value='/boffice/main/popupZone/MainPopupZoneReg.do'/>";
	}else if($("#actionkey").val() == "modify") {
		f.action = "<c:url value='/boffice/main/popupZone/MainPopupZoneMod.do'/>";
	}
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>
		배너관리
		<c:choose>
		    <c:when test="${param.mzIdx eq 'new'}">등록</c:when>
		    <c:otherwise>수정</c:otherwise>
		</c:choose>
	</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="MainPopupZoneVo" name="MainPopupZoneVo" method="post" enctype="multipart/form-data" onsubmit="return doForm(this);">
	<form:hidden path="pageIndex" />
	<form:hidden path="searchSiteCd" />
	<form:hidden path="searchUse" />
	<form:hidden path="searchKeyword" />
	<form:hidden path="actionkey" />
	<form:hidden path="mzIdx" />
	<form:hidden path="mzFilename" />
	<c:choose>
	    <c:when test="${param.mzIdx eq 'new'}"><input type="hidden" name="doAction" value="reg"></c:when>
	    <c:otherwise><input type="hidden" name="doAction" value="mod"></c:otherwise>
	</c:choose>
	<div class="tableBox">
		<table class="form">
			<caption>배너 입력/수정</caption>
			<colgroup>
				<col class="w150">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="gubun">구분</label></th>
					<td>
						<c:set var="itemList" value="${cmm:getCodeList('35000000')}" />
						<c:set var="popWidth" value="290"/>
						<c:set var="popHeight" value="150"/>
						<c:set var="codeValue" value=""/>
						<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
						<c:if test="${itemInfo.code eq fn:trim(MainPopupZoneVo.code)}">
							<input type="hidden" name="code" value="<c:out value="${itemInfo.code}"/>"/>
							<c:set var="arrCode" value="${fn:split(itemInfo.codeName,'_')}"/>
							<c:set var="arrSize" value="${fn:split(arrCode[1],'X')}"/>
							<c:set var="popWidth" value="${arrSize[0]}"/>
							<c:set var="popHeight" value="${arrSize[1]}"/>
							<c:out value="${arrCode[0]}"/>
							<c:set var="codeValue" value="${itemInfo.codeValue}"/>
						</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="mzTitle">제목</label></th>
					<td><form:input path="mzTitle" size="100" class="required w100p" /></td>
				</tr>
				<tr>
					<th scope="row">
						<label for="mzSitecd">사이트선택</label>
					</th>
					<td>
						<input type="checkbox" id="chkAll" name="chkAll"> 전체선택<br />
						<c:forEach var="siteInfo" items="${siteList }">
							<c:if test="${fn:indexOf(codeValue , siteInfo.siteCd) > -1 }">
							<c:set var="siteCd" value="｜${siteInfo.siteCd}｜" />
							<label>
								<input type="checkbox" id="mzSitecd" name="mzSitecd" value="<c:out value="${siteInfo.siteCd}"/>" <c:if test="${fn:indexOf(MainPopupZoneVo.mzSitecd , siteCd) > -1 }">checked="checked"</c:if> /> <c:out value="${siteInfo.siteNm }" />
							</label>
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="mzStartDate1">기간</label></th>
					<td>
						<span class="cal_in"><form:input path="mzStartDate1" size="10" class="useDatepicker dateFrom required" readonly="true" /></span>
						<form:select path="mzStartDate2">
							<c:forTokens items="${hourVar}" delims="," var="value">
								<form:option value="${value}" label="${value}" />
							</c:forTokens>
						</form:select>
						<label for="mzStartDate2">시</label>
						<form:select path="mzStartDate3">
							<c:forTokens items="${minuteVar}" delims="," var="value">
								<form:option value="${value}" label="${value}" />
							</c:forTokens>
						</form:select>
						<label for="mzStartDate3">분</label>
						<label for="mzEndDate1">까지</label>~
						<span class="cal_in"><form:input path="mzEndDate1" size="10" class="useDatepicker dateTo required" readonly="true" /></span>
						<form:select path="mzEndDate2">
							<c:forTokens items="${hourVar}" delims="," var="value">
								<form:option value="${value}" label="${value}" />
							</c:forTokens>
						</form:select>
						<label for="mzEndDate2">시</label>
						<form:select path="mzEndDate3">
							<c:forTokens items="${minuteVar}" delims="," var="value">
								<form:option value="${value}" label="${value}" />
							</c:forTokens>
						</form:select>
						<label for="mzEndDate3">분</label>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="mzAlt">이미지 ALT</label></th>
					<td><form:textarea path="mzAlt" rows="3" class="required w100p" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="mzLink">링크</label></th>
					<td>
						<form:select path="mzTarget">
							<form:option value="_blank" label="새창" />
							<form:option value="_self" label="현재창" />
						</form:select>
						<form:input path="mzLink" class="w300" />
					</td>
				</tr>
				<tr>
					<th scope="row">
						<label for="dataFile">이미지 파일</label><br />
						(<c:out value="${popWidth}"/>px × <c:out value="${popHeight}"/>px)
					</th>
					<td>
						<input type="file" id="dataFile" name="dataFile" class="w300" />
						<c:if test="${MainPopupZoneVo.actionkey eq 'modify'}">
							<br /><img src="<c:url value="/common/files/ImageDownload.do?filePath=/upload/mainPopupZone/${MainPopupZoneVo.mzFilename}" />" alt="첨부파일" style="max-width:200px;max-height:200px;" />
						</c:if>
					</td>
				</tr>
				<tr>
					<th scope="row">사용여부</th>
					<td>
						<form:radiobutton path="mzUse" value="A" label="상시게시" />
						<form:radiobutton path="mzUse" value="Y" label="사용중" />
						<form:radiobutton path="mzUse" value="N" label="종료" />
					</td>
				</tr>
				<!--
				<c:if test="${MainPopupZoneVo.actionkey eq 'modify'}">
				<tr>
					<th scope="row">등록일</th>
					<td><c:out value="${MainPopupZoneVo.regDt}" /></td>
				</tr>
				<tr>
					<th scope="row">수정일</th>
					<td><c:out value="${MainPopupZoneVo.modDt}" /></td>
				</tr>
				</c:if>
				-->
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<c:choose>
		    <c:when test="${param.mzIdx eq 'new'}">
				<input type="submit" value="확인" class="btn_m on">
				<a href="#" class="btn_m btn_modalClose">취소</a>
		    </c:when>
		    <c:otherwise>
				<input type="submit" value="수정" class="btn_m on">
				<a href="#" class="btn_m btn_caption btn_del">삭제</a>
				<a href="#" class="btn_m btn_modalClose">취소</a>
		    </c:otherwise>
		</c:choose>
	</div>
	</form:form>
</div>
<!-- ============================== //모달영역 ============================== -->
