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
- 파일명 : main_layer_popup_zone_form.jsp
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
		$(".modalContent #MainLayerPopupVo").submit();
	});
});
function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	if(modalActionType == "delete"){
		f.action = "<c:url value='/boffice/main/layerPopup/MainLayerPopupRmv.do'/>";
	}else if($("#actionkey").val() == "regist") {
		if($("#dataFile").val() == "") {
			alert("파일을 선택해주십시오.");
			$("#dataFile").focus();
			return false;
		}
		f.action = "<c:url value='/boffice/main/layerPopup/MainLayerPopupReg.do'/>";
	}else if($("#actionkey").val() == "modify") {
		f.action = "<c:url value='/boffice/main/layerPopup/MainLayerPopupMod.do'/>";
	}
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>
		레이어팝업관리
		<c:choose>
		    <c:when test="${param.mlIdx eq 'new'}">등록</c:when>
		    <c:otherwise>수정</c:otherwise>
		</c:choose>
	</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="MainLayerPopupVo" name="MainLayerPopupVo" method="post" enctype="multipart/form-data"  onsubmit="return doForm(this);">
	<form:hidden path="pageIndex" />
	<form:hidden path="searchSiteCd" />
	<form:hidden path="searchUse" />
	<form:hidden path="searchKeyword" />
	<form:hidden path="actionkey" />
	<form:hidden path="mlIdx" />
	<form:hidden path="mlFilename" />
	<div class="tableBox">
		<table class="form">
			<caption>사이트레이어팝업 입력/수정</caption>
			<colgroup>
				<col class="w20p">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="mlTitle">제목</label></th>
					<td><form:input path="mlTitle" class="required w100p" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="mlSitecd">사이트선택</label></th>
					<td>
						<form:select path="mlSitecd">
							<c:forEach var="siteInfo" items="${siteList }">
								<form:option value="${siteInfo.siteCd}" label="${siteInfo.siteNm}" />
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr>
					<th scope="row">기간</th>
					<td>
						<label for="mlStartDate1" class="hidden">기간(부터)</label>
						<form:input path="mlStartDate1" class="useDatepicker dateFrom required" readonly="true" />
						<form:select path="mlStartDate2">
							<c:forTokens items="${hourVar}" delims="," var="value">
								<form:option value="${value}" label="${value}" />
							</c:forTokens>
						</form:select>
						<label for="mlStartDate2">시</label>
						<form:select path="mlStartDate3">
							<c:forTokens items="${minuteVar}" delims="," var="value">
								<form:option value="${value}" label="${value}" />
							</c:forTokens>
						</form:select>
						<label for="mlStartDate3">분</label> ~ 
						<label for="mlEndDate1" class="hidden">기간(까지)</label>
						<form:input path="mlEndDate1" class="useDatepicker dateFrom required" readonly="true" />
						<form:select path="mlEndDate2">
							<c:forTokens items="${hourVar}" delims="," var="value">
								<form:option value="${value}" label="${value}" />
							</c:forTokens>
						</form:select>
						<label for="mlEndDate2">시</label>
						<form:select path="mlEndDate3">
							<c:forTokens items="${minuteVar}" delims="," var="value">
								<form:option value="${value}" label="${value}" />
							</c:forTokens>
						</form:select>
						<label for="mlEndDate3">분</label>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="mlAlt">이미지 ALT</label></th>
					<td><form:textarea path="mlAlt" rows="5" class="required w100p" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="mlLink">링크</label></th>
					<td>
						<label for="mlTarget" class="hidden">링크타입</label>
						<form:select path="mlTarget">
							<form:option value="_blank" label="새창" />
							<form:option value="_self" label="현재창" />
						</form:select>
						<form:input path="mlLink" class="w300" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="mlStyle">스타일</label></th>
					<td><form:input path="mlStyle" size="100" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="dataFile">이미지 파일</label></th>
					<td>
						<input type="file" id="dataFile" name="dataFile" class="w300" />
						<c:if test="${MainLayerPopupVo.actionkey eq 'modify'}">
							<br /><img src="<c:url value="/upload/mainLayerPopup/${MainLayerPopupVo.mlFilename}" />" alt="첨부파일" style="max-width: 408px; max-height: 306px;" />
						</c:if>
					</td>
				</tr>
				<c:if test="${MainLayerPopupVo.actionkey eq 'modify'}">
					<tr>
						<th scope="row">진행형태</th>
						<td>
							<form:radiobutton path="mlUse" value="Y" label="사용중" />
							<form:radiobutton path="mlUse" value="N" label="종료" />
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<c:choose>
		    <c:when test="${MainLayerPopupVo.actionkey eq 'regist'}">
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
