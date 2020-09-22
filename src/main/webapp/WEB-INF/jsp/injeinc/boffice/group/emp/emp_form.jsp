<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 직원정보관리 > 등록
- 파일명 : emp_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	//버튼이벤트
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		var f = document.empVo;
		f.doAction.value = "del";
		f.action = "/emp/Emp_Rmv.do";
		f.submit();
	});
});

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	if(f.doAction.value == "reg"){
		f.action = "/boffice/group/emp/Emp_Reg.do";
	}else if(f.doAction.value == "del"){
		f.action = "/emp/Emp_Rmv.do";
	}else{
		f.action = "/boffice/group/emp/Emp_Mod.do";
	}
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>
		직원정보
		<c:choose>
		    <c:when test="${param.ceIdx eq 'new'}">등록</c:when>
		    <c:otherwise>수정</c:otherwise>
		</c:choose>
	</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="empVo" name="empVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="actionKey" />
	<form:hidden path="ceIdx" />
	<form:hidden path="ceCdIdx" />
	<form:hidden path="ceDepstep1"  />
	<form:hidden path="ceDepstep2"  />
	<form:hidden path="ceDepstep3"  />
	<c:choose>
	    <c:when test="${param.ceIdx eq 'new'}"><input type="hidden" name="doAction" value="reg"></c:when>
	    <c:otherwise><input type="hidden" name="doAction" value="mod"></c:otherwise>
	</c:choose>
	
	<div class="tableBox">
		<table class="form">
			<caption></caption>
			<colgroup>
				<col class="w20p">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th><label for="">부서</label></th>
					<td><c:out value="${step }"/></td>
				</tr>
				<tr>
					<th scope="row"><label for="ceName">이름</label></th>
					<td><form:input path="ceName" class="required w150" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="ceDepstep4">직위</label></th>
					<td>
						<form:select path="ceDepstep4" class="w150">
							<option value="주무관" <c:if test="${empVo.ceDepstep4 eq '주무관'}">selected="selected"</c:if>>주무관</option>
							<option value="팀장" <c:if test="${empVo.ceDepstep4 eq '팀장'}">selected="selected"</c:if>>팀장</option>
							<option value="과장" <c:if test="${empVo.ceDepstep4 eq '과장'}">selected="selected"</c:if>>과장</option>
						</form:select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="ceMailid">이메일</label></th>
					<td><form:input path="ceMailid" class="w100p" /></td>
				</tr>
				<tr>
					<th scope="row"><label for=ceTel>전화번호</label></th>
					<td><form:input path="ceTel" class="required w200" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="ceHp">휴대폰</label></th>
					<td><form:input path="ceHp" class="w200" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="ceName">업무내용</label></th>  
					<td><form:textarea path="ceWorks" class="required w100p h100" />
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<c:choose>
		    <c:when test="${param.ceIdx eq 'new'}">
				<input type="submit" value="확인" class="btn_m on">
				<a href="#" class="btn_m btn_modalClose">취소</a>
		    </c:when>
		    <c:otherwise>
				<input type="submit" value="수정" class="btn_m on">
				<a href="#" class="btn_m btn_caption btn_del">삭제</a>
		    </c:otherwise>
		</c:choose>
	</div>
	
	</form:form>
</div>
<!-- ============================== //모달영역 ============================== -->
