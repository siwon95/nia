<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 사이트관리
- 파일명 : site_form.jsp
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
		$(".modalContent #SiteVo").submit();
	});
	$(".btn_overlapCheck").click(function(e){
	e.preventDefault();
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/cn/site/site_cnt_ax.do'/>",
			data : {"siteCd":$("#siteCd").val()},
			dataType : "json",
			success:function(data) {
				if(data.result) {
					if(data.resultCnt > 0) {
						$("#jungbok_yn").val("N");
						$("#siteCd").val("");
						alert("중복되는 아이디 입니다.");
					}else{
						$("#jungbok_yn").val("Y");
						alert("사용가능한 아이디입니다.");
					}
				}else{
					alert(data.message);
				}
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
	});
	$("#siteCd").keypress(function(){
		$("#jungbok_yn").val("N");
	});
});

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}

	var test = "<c:out value='${menuGubun}'/>";
	var ajaxParam = $(f).serializeArray();
	var ajaxOption = {};
	ajaxOption.dataType = "text";
	if(modalActionType == "delete"){
		f.action = "<c:url value='/boffice/cn/site/site_rmv.do'/>";
	}else if(test == "form"){
		if($("#jungbok_yn").val()!="Y"){
			alert("사이트코드 중복체크를 해주세요");
			return false;
		}
		f.action = "<c:url value='/boffice/cn/site/site_reg.do'/>";
	}else if(test == "update"){
		if($("#jungbok_yn").val()!="Y"){
			alert("사이트코드 중복체크를 해주세요");
			return false;
		}
		f.action = "<c:url value='/boffice/cn/site/site_mod.do'/>";
	}
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<c:choose>
	<c:when test="${menuGubun eq 'form'}">
		<c:set var="gubunTxt" value="등록" />
		<c:set var="jungbokYn" value="N" /> 
	</c:when>
	<c:when test="${menuGubun eq 'update'}">
		<c:set var="gubunTxt" value="수정" /> 
		<c:set var="jungbokYn" value="Y" /> 
	</c:when>
	<c:otherwise>
		<c:set var="jungbokYn" value="N" />
	</c:otherwise>
</c:choose>
<div class="modalTitle">
	<h3>
		사이트 <c:out value="${gubunTxt}" />
	</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="SiteVo" name="SiteVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="pageIndex" />
	<form:hidden path="siteIdx" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" />
	<form:hidden path="siteMenu" />
	<input type="hidden" id="jungbok_yn" name="jungbok_yn" value="<c:out value='${jungbokYn}' />" />
	<div class="tableBox">
		<table class="form">
			<caption>사이트정보입력</caption>
			<colgroup>
				<col class="w20p">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th><label for="siteNm">사이트 명</label></th>
					<td><form:input path="siteNm" class="required w100p" /></td>
				</tr>
				<tr>
					<th><label for="siteCd">사이트 코드</label></th>
					<td>
						<c:if test="${menuGubun eq 'form'}"><form:input path="siteCd" class="required w85p" /> <input type="button" value="중복체크" class="btn_inline btn_overlapCheck" /></c:if>
						<c:if test="${menuGubun eq 'update'}"><form:input path="siteCd" class="required w100p" readonly="true" /></c:if>
					</td>
				</tr>
				<tr>
					<th><label for="sitePath">사이트 경로</label></th>
					<td>
						<form:input path="sitePath" class="required w100p" />
						/[사이트경로]/[사이트코드]/이하에 컨텐츠 소스가 생성됨
					</td>
				</tr>
				<tr>
					<th><label for="siteDomain">사이트 도메인</label></th>
					<td><form:input path="siteDomain" class="required w100p" /></td>
				</tr>
				<tr>
					<th><label for="siteKdCd">사이트 유형</label></th>
					<td>
						<form:select path="siteKdCd" class="required w100p">
							<form:option value="" label="선택해주세요." />
							<form:options items="${codeList}" itemValue="code" itemLabel="codeName" />
						</form:select>
					</td>
				</tr>
				<tr>
					<th><label for=>사이트 이미지</label></th>
					<td><input type="file" name="" class="w200"> (준비중)</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<c:choose>
			<c:when test="${menuGubun eq 'form'}">
				<input type="submit" value="확인" class="btn_m on">
				<a href="#" class="btn_m btn_modalClose">취소</a>
			</c:when>
			<c:when test="${menuGubun eq 'update'}">
				<input type="submit" value="수정" class="btn_m on">
				<a href="#" class="btn_m btn_caption btn_del">삭제</a>
			</c:when>
		</c:choose>
	</div>
	</form:form>
</div>
<!-- ============================== //모달영역 ============================== -->
