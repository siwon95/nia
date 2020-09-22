<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 이메밀전송기록
- 파일명 : mail_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	f.action = "/boffice/ex/mail/MailReg.do";
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->
<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>메일 발송 등록</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="MailVo" name="MailVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="pageIndex" />
	<div class="tableBox">	
		<table class="form">
			<caption>이메일 발송 테스트</caption>
			<colgroup>
				<col class="w150">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="subject">제목</label></th> 
					<td><form:input path="subject" size="80" class="required w100p"/></td>
				</tr>
				<tr>
					<th scope="row"><label for="to">수신자 이름</label></th>
					<td><form:input path="to" size="20" class="required w150"/></td>
				</tr>
				<tr>
					<th scope="row"><label for="receipt">수신자 이메일</label></th>
					<td><form:input path="receipt" size="30" class="required w100p"/></td>
				</tr>
				<tr>
					<th scope="row"><label for="body">본문정보</label></th>
					<td><form:textarea path="body" class="w100p" rows="3"/></td>
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
