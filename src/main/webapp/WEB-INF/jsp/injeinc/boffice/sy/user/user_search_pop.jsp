<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 설문조사 > 회원인증
- 파일명 : user_search_pop.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<c:import url="/layout/cms/head.jsp" ></c:import>
<script>
//창 닫기
function popClose() {
	self.close();
}
function doUserSearchByIdAndPwdAx() {
	if($("#cuId").val() == "") {
		alert("아이디를 입력해주십시오.");
		$("#cuId").focus();
		return;
	}
	if($("#cuPwd").val() == "") {
		alert("비밀번호를 입력해주십시오.");
		$("#cuPwd").focus();
		return;
	}

	$.ajax({
		type: "POST",
		url: "<c:url value='/boffice/sy/user/UserSearchByIdAndPwdAx.do'/>",
		data : {"cuId":$("#cuId").val(),"cuPwd":$("#cuPwd").val()},
		dataType : "json",
		success:function(data) {
			if(data.result) {
				var resultVo = data.resultVo;
				window.opener.writeUserInfo(resultVo.cuId, resultVo.cuName, resultVo.email, resultVo.zip, resultVo.addr1, resultVo.addr2, resultVo.telNum, resultVo.hpNum, resultVo.ownAuth);
				popClose();
			}else{
				alert(data.message);
			}

		},
		error: function(xhr, status, error) {
			alert(status);
		}
	});

}
</script>
</head>
<body>

<!-- ============================== 모달영역 ============================== -->
<div class="modalWrap popup">
	<div class="modalTitle">
		<h3>회원인증</h3>
		<a href="#" class="btn_modalClose">닫기</a>
	</div>
	<div class="modalContent">
		<div class="tableBox">
			<table summary="아이디와 비밀번호로 회원정보 검색" class="view">
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>
				<tbody>
					<tr>
						<th><label for="cuId">아이디</label></th>
						<td><input type="text" id="cuId" name="cuId" size="20" maxlength="50" value="" /></td>
					</tr>
					<tr>
						<th><label for="cuPwd">비밀번호</label></th>
						<td><input type="password" id="cuPwd" name="cuPwd" size="20" maxlength="50" value="" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea" style="text-align:center;">
			<a href="javascript:doUserSearchByIdAndPwdAx();" class="btn_m on">조회</a>
			<a href="javascript:popClose();" class="btn_m btn_modalClose">닫기</a>
		</div>
	</div>
</div>
<!-- ============================== //모달영역 ============================== -->
</body>
</html>