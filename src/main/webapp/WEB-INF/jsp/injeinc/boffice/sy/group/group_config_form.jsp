<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 회원그룹관리
- 파일명 : group_config_form.jsp
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
	
	f.action = "/boffice/sy/group/GroupConfig_Reg.do";
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->
<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>회원그룹 등록</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="GroupConfigVo" name="GroupConfigVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="pageIndex"/>
	<form:hidden path="gcIdx"/>
	<form:hidden path="searchCondition"/>
	<form:hidden path="searchKeyword"/>
	<div class="tableBox">
		<table class="form">
			<caption>회원그룹관리 입력</caption>
			<colgroup>
				<col class="w100">
				<col>						
			</colgroup>
			<tbody>
				<tr>
					<th>
						<span class="required">*</span>
						<label for="gcName">그룹명</label>
					</th>
					<td><form:input path="gcName" size="34" class="required w100p"/></td>
				</tr>
				<tr>
					<th>
						<span class="required">*</span>
						<label for="gcDesc">그룹 소개</label>
					</th>
					<td><form:input path="gcDesc" size="80" class="required w100p"/></td>
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
