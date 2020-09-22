<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 설문관리
- 파일명 : poll_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
var modalActionType = "";
$(function(){
	//버튼 이벤트
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		modalActionType = "delete";
		$(".modalContent #PollListVo").submit();
	});
	
	$(".onlynum").keyup(function(){
		var regexp = /[^0-9]/g;
		var v = $(this).val();
		if(regexp.test(v)) {
			alert("숫자만 입력가능 합니다.");
			$(this).val(v.replace(regexp, ""));
		}
	});
});

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	if(modalActionType == "delete"){
		f.action = "<c:url value='/boffice/ex/poll/PollListRmv.do'/>";
	}else if($("#actionkey").val() == "regist") {
		f.action = "<c:url value='/boffice/ex/poll/PollListReg.do'/>";
	}else if($("#actionkey").val() == "modify") {
		f.action = "<c:url value='/boffice/ex/poll/PollListMod.do'/>";
	}
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>
		설문관리
		<c:if test="${PollListVo.actionkey eq 'regist'}">
		등록
		</c:if>
		<c:if test="${PollListVo.actionkey eq 'modify'}">
		수정
		</c:if>
	</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="PollListVo" name="PollListVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="pageIndex" />
	<form:hidden path="searchUse" />
	<form:hidden path="plIdx" />
	<form:hidden path="actionkey" />
	<c:choose>
	    <c:when test="${param.plIdx eq 'new'}"><input type="hidden" name="doAction" value="reg"></c:when>
	    <c:otherwise><input type="hidden" name="doAction" value="mod"></c:otherwise>
	</c:choose>
	<div class="tableBox">
		<table class="form">
			<caption>설문조사 입력/수정</caption>
			<colgroup>
				<col class="w100">
				<col>
				<col class="w100">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="plTitle">제목</label></th>
					<td colspan="3"><form:input path="plTitle" class="required w100p" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="plGuide">안내문</label></th>
					<td colspan="3"><form:textarea path="plGuide" class="w100p" rows="8"/></td>
				</tr>
				<tr>
					<th scope="row"><label for="siteCd">사이트 코드</label></th>
					<td colspan="3">
						<c:if test="${SesUserPermCd eq '05010000'}">
						<select id="siteCd" name="siteCd" class="required">
							<option value="">사이트 선택</option>
							<c:forEach var="siteInfo" items="${siteList}">
								<option value="<c:out value="${siteInfo.siteCd}"/>" <c:if test="${PollListVo.siteCd eq siteInfo.siteCd}">selected="selected"</c:if>><c:out value="${siteInfo.siteNm}"/></option>
							</c:forEach>
						</select>
						</c:if>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="plCdidx">담당부서</label></th>
					<td colspan="3">
						<c:if test="${SesUserPermCd eq '05010000'}">
						<select id="plCdidx" name="plCdidx" class="required">
							<option value="">담당부서 선택</option>
							<c:forEach var="departInfo" items="${departList }">
							<c:if test="${departInfo.cdDepstep2 eq '00' }">
								<option value="<c:out value="${departInfo.cdIdx}"/>" <c:if test="${PollListVo.plCdidx eq departInfo.cdIdx}">selected="selected"</c:if>><c:out value="${departInfo.cdSubject}"/></option>
							</c:if>
							<c:if test="${departInfo.cdDepstep2 ne '00' }">
							<option value="<c:out value="${departInfo.cdIdx}"/>" <c:if test="${PollListVo.plCdidx eq departInfo.cdIdx}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${departInfo.cdSubject}"/></option>
							</c:if>
							</c:forEach>
						</select>
						</c:if>
						<c:if test="${SesUserPermCd ne '05010000'}">
							<c:out value="${SesUserName}"/>"<input type="hidden" id="plCdidx" name="plCdidx" value="<c:out value="${SesUserDeptCd}"/>" />
						</c:if>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="plManagerName">담당자</label></th>
					<td><form:input path="plManagerName" class="required w100" /></td>
					<th scope="row"><label for="plManagerTel1">담당자연락처</label></th>
					<td>
						<form:input path="plManagerTel1" size="4" maxlength="4" class="required onlynum w50"/> -
						<label for="plManagerTel2" class="hidden">중간자리</label>
						<form:input path="plManagerTel2" size="4" maxlength="4" class="required onlynum w60"/> -
						<label for="plManagerTel3" class="hidden">중간자리</label> 
						<form:input path="plManagerTel3" size="4" maxlength="4" class="required onlynum w60"/>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="plStartDate1">설문조사기간</label></th> <!-- [퍼블리싱] 재수정 필요 20180123 --> 
					<td colspan="3">
						<form:input path="plStartDate1" size="10" class="useDatepicker required" readonly="true" />		 
						<form:select path="plStartDate2">
						<c:forTokens items="${hourVar}" delims="," var="value">
							<form:option value="${value}" label="${value}" />
						</c:forTokens>
						</form:select><label for="plStartDate2">시</label>
						<form:select path="plStartDate3">
						<c:forTokens items="${minuteVar}" delims="," var="value">
							<form:option value="${value}" label="${value}" />
						</c:forTokens>
						</form:select><label for="plStartDate3">분</label>	
						 ~ 
						<label for="plEndDate1" class="hidden">조사마감기간</label> 
						<form:input path="plEndDate1" size="10" class="useDatepicker required"  readonly="true" />
						<form:select path="plEndDate2">
						<c:forTokens items="${hourVar}" delims="," var="value">
							<form:option value="${value}" label="${value}" />
						</c:forTokens>
						</form:select><label for="plEndDate2">시</label>
						<form:select path="plEndDate3">
						<c:forTokens items="${minuteVar}" delims="," var="value">
							<form:option value="${value}" label="${value}" />
						</c:forTokens>
						</form:select><label for="plEndDate3">시</label>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="plNumber">참여인원</label></th>
					<td>
						<form:input path="plNumber" size="4" class="onlynum" />
						(숫자만 입력 가능합니다.)
					</td>
					<th scope="row">인증방법</th>
					<td>
						<form:radiobutton path="plAuthType" value="N" label="없음" />
						<form:radiobutton path="plAuthType" value="I" label="IP" />
						<form:radiobutton path="plAuthType" value="A" label="본인확인" />
						<form:radiobutton path="plAuthType" value="L" label="로그인" /><br />
						<span class="captionText">※ 없음을 선택하면 무한 중복 답변 가능함</span>
					</td>
				</tr>
				<tr>
					<th scope="row">전화번호</th>
					<td>
						<form:radiobutton path="plTelYn" value="Y" label="사용" />
						<form:radiobutton path="plTelYn" value="N" label="미사용" />
					</td>
					<th scope="row">핸드폰번호</th>
					<td>
						<form:radiobutton path="plHpYn" value="Y" label="사용" />
						<form:radiobutton path="plHpYn" value="N" label="미사용" />
					</td>
				</tr>
				<tr>
					<th scope="row">주소</th>
					<td>
						<form:radiobutton path="plAddrYn" value="Y" label="사용" />
						<form:radiobutton path="plAddrYn" value="N" label="미사용" />
					</td>
					<th scope="row">이메일</th>
					<td>
						<form:radiobutton path="plEmailYn" value="Y" label="사용" />
						<form:radiobutton path="plEmailYn" value="N" label="미사용" />
					</td>
				</tr>
				<tr>
					<th scope="row">설문결과<br />공개여부</th>
					<td colspan="3">
						<form:radiobutton path="plResultOpenYn" value="Y" label="공개" />
						<form:radiobutton path="plResultOpenYn" value="N" label="비공개" />
					</td>
				</tr>
				<c:if test="${PollListVo.actionkey eq 'modify'}">
				<tr>
					<th scope="row">진행형태</th>
					<td colspan="3">
						<form:radiobutton path="plUse" value="Y" label="진행중" />
						<form:radiobutton path="plUse" value="N" label="마감" />
					</td>
				</tr>
				<tr>
					<th scope="row">등록일</th>
					<td><c:out value="${PollListVo.regDt}" /></td>
					<th scope="row">수정일</th>
					<td><c:out value="${PollListVo.modDt}" /></td>
				</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<c:if test="${PollListVo.actionkey eq 'regist'}">
		<input type="submit" value="확인" class="btn_m on">
		</c:if>
		<c:if test="${PollListVo.actionkey eq 'modify'}">
		<input type="submit" value="수정" class="btn_m on">
		<a href="#" class="btn_m btn_caption btn_del">삭제</a>
		</c:if>
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
	
	</form:form>
</div>
<!-- ============================== //모달영역 ============================== -->
