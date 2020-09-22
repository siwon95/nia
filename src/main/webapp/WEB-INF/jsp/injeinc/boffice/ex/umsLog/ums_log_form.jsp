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
- 제목 : UMS전송기록
- 파일명 : ums_log_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
function doForm(f) {
	var requiredCheck = formRequiredCheck(document.UmsLogVo);
	if(requiredCheck == false){
		return false;
	}
	f.action = "/boffice/ex/umsLog/UmsLogReg.do";
}
</script>
<!-- //페이지 전용 스타일/스크립트 --> 
<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>UMS 전송 등록/수정</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="UmsLogVo" name="UmsLogVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="pageIndex" />
	<form:hidden path="pageUnit" />
	<form:hidden path="searchSiteCd" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" />
	<form:hidden path="searchStartDt" />
	<form:hidden path="searchEndDt" />
	<div class="tableBox">
		<table class="form">
			<caption>UMS 전송</caption>
			<colgroup>
				<col class="w30p">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="siteCd">카테고리</label></th>
					<td>
						<select id="siteCd" name="siteCd" class="w100">
							<option value="">선택안함</option>
							<c:forEach var="SiteCdInfo" items="${SiteCdList}">
							<option value="<c:out value="${SiteCdInfo.code}"/>"><c:out value="${SiteCdInfo.codeName}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><span class="required">*</span><label for="sendType">발송구분</label></th>
					<td>
						<select id="sendType" name="sendType" class="w100">
							<option value="sms">sms</option>
							<option value="mms">mms</option>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><span class="required">*</span><label for="subject">제목</label></th>
					<td><input type="text" id="subject" name="subject" value="TEST" class="required w100"></td>
				</tr>
				<tr>
					<th scope="row"><span class="required">*</span><label for="address">수신자명 수신자번호</label></th>
					<td><input type="text" id="address" name="address" value="" class="required w100"> ※ 수신자명 수신자번호, 수신자번호는 숫자만으로구성</td>
				</tr>
				<tr>
					<th scope="row"><span class="required">*</span><label for="ums_content">내용</label></th>
					<td><textarea id="ums_content" name="content" data-limitByte="80">테스트 문자 입니다.</textarea></td>
				</tr>
				<tr>
					<th scope="row"><span class="required">*</span><label for="callbackNo">회신번호</label></th>
					<td><input type="text" id="callbackNo" name="callbackNo" value="01087890829" class="required w100"> ※ 회신번호는 숫자만으로 구성</td>
				</tr>
				<tr>
					<th scope="row"><span class="required">*</span><label for="worktypeNm">발송업무구분명</label></th>
					<td><input type="text" id="worktypeNm" name="worktypeNm" value="0200000000" class="required w100"></td>
				</tr>
				<tr>
					<th scope="row"><span class="required">*</span><label for="scheduleType">예약발송</label></th>
					<td>
						<select id="scheduleType" name="scheduleType" class="w100">
							<option value="0">즉시발송</option>
							<option value="1">예약발송</option>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><span class="required">*</span><label for="scheduleStime">예약발송시 발송년월일시분</label></th>
					<td><input type="text" id="scheduleStime" name="scheduleStime" value="" class="required w100"> ※ 예약발송시 발송년월일시분, 200901020304</td>
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
