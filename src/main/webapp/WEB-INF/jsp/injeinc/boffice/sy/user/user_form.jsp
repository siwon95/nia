<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 회원목록
- 파일명 : user_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
var reg_id = /^[a-z][a-z0-9_-]{3,11}$/;
var reg_pw = /^(?=.*[a-zA-Z])(?=.*[0-9]).{10,15}$/;
var email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;

//회원등록일 경우 id체크여부를 위한 변수
var idChk = "N";
var chkreg = "N";

$(function(){
	//버튼이벤트
	$(".btn_out").click(function(e){ //회원탈퇴
		e.preventDefault();
		$("#memberReason").show();
		$("#memberReason .memberReasonOut").show();
		$("#memberReason .memberReasonMod").hide();
		$("#memberReason input[name='mode']").val("delete");
	});
	$(".btn_reasonClose").click(function(e){
		e.preventDefault();
		$("#memberReason #confirmPwd").val("");
		$("#memberReason #changeReason").val("");
		$("#memberReason, #memberReason .memberReasonOut, #memberReason .memberReasonMod").hide();
		$("#memberReason input[name='mode']").val("");
	});
	$(".btn_reset").click(function(e){ //비밀번호 초기화
		e.preventDefault();
		var cuIdx = "<c:out value='${UserVo.cuIdx}'/>";
		var cuId = "<c:out value='${UserVo.cuId}'/>";
		var cuPwd = randomString();
		var cuName = '<c:out value="${UserVo.cuName}"/>';
		var hpNum = '<c:out value="${UserVo.hpNum1}"/>'+'<c:out value="${UserVo.hpNum2}"/>'+'<c:out value="${UserVo.hpNum3}"/>';
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/sy/user/User_PassModAx.do' />",
			data: "cuIdx=" + cuIdx + "&cuPwd=" + cuPwd + "&cuName=" + cuName + "&hpNum=" + hpNum + "&doSav=" + "<c:out value='${UserVo.cuId}'/>" + "&logKdCd=R" +"&cuId="+cuId, 
			dataType: "json",
			success:function(object) {
				alert(cuPwd+"으로 비밀번호가 초기화 되었습니다.");
				/* if(object.SMS == 'false'){
					alert("문자 전송에 실패하였습니다.\n관리자에게 문의해 주세요.");
				} */
			},
			error: function(xhr,status,error) {
				alert("비밀번호 초기화에 실패하였습니다.");
			}
		});
	});
	$(".btn_overlapCheck").click(function(e){ //회원탈퇴
		e.preventDefault();
		if(!$('input[name=cuId]').val() && $('input[name=cuId]').val() == "") {
			alert("아이디를 입력해주세요.");
			return false;
		}
		if($('input[name=cuId]').val() && !reg_id.test($('input[name=cuId]').val())) {
			alert("4-12자의 영문 소문자, 숫자만 가능하며 첫글자는 영문이어야 합니다.");
			return false;
		}
		var ajaxParam = {"cuId":$("input[id=cuId]").val()};
		var ajaxOption = {};
		ajaxOption.success = function(data){
			if(data.result == "true"){
				chkreg = "Y";
				idChk = "Y";
				alert("사용할 수 있는 아이디입니다.");	
			}else{
				chkreg = "N";
				idChk = "N";
				alert("사용할 수 없는 아이디입니다.");
			}
		};
		ajaxAction("<c:url value='/boffice/sy/user/User_ListAx.do' />", ajaxParam, ajaxOption);
	});
	$(".btn_findZip").click(function(e){ //비밀번호 초기화
		e.preventDefault();
		//var from = document.form;
		//doJusoPop(from);
		
		var protocol=location.protocol;
		var slashes=protocol.concat("//");
		var host=slashes.concat(window.location.hostname);
		var port=location.port;
		
		if (port != '80') {
			host = host+":"+port;
		}
		
		var offset = host + "/cmm/Juso_Set.do";
		var winUrl = "http://www.juso.go.kr/addrlink/addrLinkUrl.do";
		$('#returnUrl').val(offset);
		doCommonPop(form, "JusoPop", winUrl, 570, 420);		
		
	});
	
	//입력이벤트
	$("#cuId").change(function(){
		idChk = "N";
		chkreg = "N";
	});
	$('#email3').change(function() {
		if($(this).val() == "") {
			$('#email2').val("");
			$('#email2').attr('disabled',false);
		}else {
			$('#email2').val($(this).val());
			$('#email2').attr('disabled',true);
		}
	});
});

function doForm(f){	
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	var test = "<c:out value='${UserVo.userMenu}'/>";
	
 	//회원 등록일 경우
	if(test == "form") {
		if(reg_id.test($('input[name=cuId]').val()) == false) {
			alert("아이디는 4-12자의 영문 소문자, 숫자만 가능하며 첫글자는 영문이어야 합니다.");
			$('input[name=cuId]').focus();
			return false;
		}
		if(idChk != "Y" || chkreg !="Y"){
			alert("아이디 중복체크를 해주세요");
			return false;
		}
	}
	
	//회원 등록 또는 비밀번호란에 입력할 경우
	if(test == "form" || $('input[name=cuPwd]').val().length != 0) {
		if(!$('input[name=cuPwd]').val() || $('input[name=cuPwd]').val() == "") {	
			alert("비밀번호를 입력해주세요.");
			$('input[name=cuPwd]').focus();
			return false;
		}
		if(!$('input[name=cuPwdCheck]').val() || $('input[name=cuPwdCheck]').val() == "") {	
			alert("비밀번호확인를 입력해주세요.");
			$('input[name=cuPwd]').focus();
			return false;
		}
		if($('input[name=cuPwd]').val() != $('input[name=cuPwdCheck]').val()) {	
			alert("비밀번호와 비밀번호확인 값이 다릅니다.");
			$('input[name=cuPwd]').focus();
			return false;
		}
		if(!reg_pw.test($('input[name=cuPwd]').val())) {
			alert("비밀번호는 영문대소문자, 숫자, 숫자키에 매치되는 특수문자(10~15자리) 입니다.");
			$('input[name=cuPwd]').focus();
			return false;
		}
	}
	
 	$(".modalContent #UserVo #email").val($('input[name=email1]').val() + '@' + $('input[name=email2]').val());
	if(email.test($('input[name=email]').val()) == false) {
		alert("이메일이 올바르지 않습니다.");
		$('input[name=email]').focus();
		return false;
	}
	
	//전화번호
	var telNum = $('#telNum1').val() + '-' + $('#telNum2').val() + '-' + $('#telNum3').val();
	$('#telNum').val(telNum);
	
	//휴대폰번호
	var hpNum = $('#hpNum1').val() + '-' + $('#hpNum2').val() + '-' + $('#hpNum3').val();
	$('#hpNum').val(hpNum);
	
	if(f.logKdCd.value == "U"){
		f.action = "/boffice/sy/user/User_Mod.do";
	}else if(f.logKdCd.value == "D"){
		f.action = "/boffice/sy/user/User_Sav.do";
	}else if(test == "form"){
		f.doSav.value = $(".modalContent #UserVo #cuId").val();
		f.logKdCd.value= "I";
		f.action = "/boffice/sy/user/User_Reg.do";
	}else{
		$("#memberReason #confirmPwd").val("");
		$("#memberReason #changeReason").val("");
		$("#memberReason").show();
		$("#memberReason .memberReasonOut").hide();
		$("#memberReason .memberReasonMod").show();
		$("#memberReason input[name='mode']").val("modify");
		return false;
	}
	return true;
}

function setAddrValue(addr1, addr2, zip) {
	$("#zip").val(zip);
	$("#addr1").val(addr1);
	$("#addr2").val(addr2);
	$("#addr2").focus();
}

function randomString() {
	var numbers = "0123456789";
	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
	var randomstring = '';
	for (var i=0; i<4; i++) {//앞에2자리 숫자 랜덤 생성
		var rnum = Math.floor(Math.random() * chars.length);
		randomstring += chars.substring(rnum,rnum+1);
	}
	for (var i=0; i<6; i++) {//나머지6자리 문자 랜덤 생성
		var rnum = Math.floor(Math.random() * numbers.length);
		randomstring += numbers.substring(rnum,rnum+1);
	}
	return randomstring;
}

function doReason(f){
	var mgrPw = $("#confirmPwd").val();
	var mode = $("#memberReason input[name='mode']").val();
	if(!mgrPw){
		alert("비밀번호를 입력해 주세요.");
		$("#confirmPwd").focus();
		return false;
	}
	
	if(!$("#changeReason").val().replace(/(^\s*)|(\s*$)/gi, "")){
		alert("사유를 입력해 주세요.");
		$("#changeReason").focus();
		return false;
	}
	
	var ajaxParam = {"mgrId":'${SesUserId}',"mgrPw":mgrPw};
	var ajaxOption = {};
	ajaxOption.success = function(data){
		if(data.result){
			$(".modalContent #UserVo #reason").val($("#changeReason").val());
			$(".modalContent #UserVo #doSav").val("<c:out value='${UserVo.cuId}'/>");
			if(mode == "modify"){ //회원수정
				$(".modalContent #UserVo #logKdCd").val("U");
				$(".modalContent #UserVo").submit();
				//frm.submit();
			}else{ //회원탈퇴
				$(".modalContent #UserVo #logKdCd").val("D");
				$(".modalContent #UserVo").submit();
			}
		}else{
			alert('비밀번호가 일치하지 않습니다.');
		}
	};
	ajaxAction("<c:url value='/boffice/sy/mgr/IsMatchMgrPwd.do' />", ajaxParam, ajaxOption);

	/*	
	$(".modalContent #UserVo #reason").val($("#changeReason").val());
	$(".modalContent #UserVo #doSav").val("<c:out value='${UserVo.cuId}'/>");
	if(mode == "modify"){ //회원수정
		$(".modalContent #UserVo #logKdCd").val("U");
	}else{ //회원탈퇴
		$(".modalContent #UserVo #logKdCd").val("D");
	}
	
	//alert('submit');
	//$(".modalContent #UserVo").submit();
	
	return false;
	*/
}

$(document).ready(function() {

	$(".modal_content #telNum ").val('');
	$(".modal_content #hpNum ").val('');
	//document.UserVo.telNum.value = '';
	//document.UserVo.hpNum.value = '';
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>회원 등록/수정</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form id="form" name="form" method="post">
	<input type="hidden" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2"/>
	<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
	</form>
	<form:form commandName="UserVo" name="UserVo" method="post" onsubmit="return doForm(this);">
	<form:hidden path="pageIndex"/>
	<form:hidden path="cuIdx"/>
	<form:hidden path="searchCondition"/>
	<form:hidden path="searchKeyword"/>
	<form:hidden path="scEmailRcvChk"/>
	<form:hidden path="scSmsRcvYn"/>
	<form:hidden path="scRegDtSt"/>
	<form:hidden path="scRegDtEd"/>
	<form:hidden path="scLastLogDtSt"/>
	<form:hidden path="scLastLogDtEd"/>
	<form:hidden path="email"/>
	<form:hidden path="telNum"/>
	<form:hidden path="hpNum"/>
	<form:hidden path="reason"/>
	<input type="hidden" name="doSav" id="doSav" />
	<input type="hidden" name="logKdCd" id="logKdCd" />
	<div class="tableBox">
		<table class="form">
			<caption>회원목록</caption>
			<colgroup>
				<col class="w150">
				<col>
				<col class="w150">
				<col>					
			</colgroup>
			<tbody>
				<tr>
					<th><span class="required">*</span><label for="cuId">아이디</label></th>
					<td colspan="3">					
						<c:choose>
							<c:when test="${UserVo.userMenu == 'form'}">
								<form:input path="cuId" maxlength="12" class="required w100"/>
								<a href="#" class="btn_inline btn_overlapCheck">중복확인</a> 
								<span class="captionText">※ 4-12자의 영문 소문자, 숫자만 가능하며 첫글자는 영문이어야 합니다.</span>
							</c:when>
							<c:otherwise>
								<form:hidden path="cuId" />
								<c:out value="${UserVo.cuId}"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span><label for="cuPwd">비밀번호</label></th>
					<td colspan="3">
						<form:password path="cuPwd" maxlength="15" class="w150"/> 
						<span class="captionText">※ 영문 대소문자, 숫자, 숫자키에 매치되는 특수문자(10~15자리)</span>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span><label for="cuPwdCheck">비밀번호확인</label></th>
					<td colspan="3"><input type="password" id="cuPwdCheck" name="cuPwdCheck" maxlength="15" class="w150"/></td>						
				</tr>
				<tr>
					<th><span class="required">*</span><label for="cuName">이름</label></th>
					<td colspan="3">
						<c:choose>
							<c:when test="${UserVo.userMenu == 'form'}">
								<form:input path="cuName" maxlength="20" class="w100"/>
							</c:when>
							<c:otherwise>
								<c:out value="${UserVo.cuName}"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>주소</th>
					<td colspan="3">
						<label for="zip" class="hidden">우편번호</label>
						<form:input path="zip" size="4" readonly="true" class="required w60" /> 
						<a href="#" class="btn_inline btn_findZip">주소검색</a><br />
						<label for="addr1" class="hidden">기본주소</label>
						<form:input path="addr1" readonly="true" class="required w200" />
						<label for="addr2" class="hidden">상세주소</label> 
						<form:input path="addr2" class="required w200" />
					</td>				
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<label for="telNum1" class="hidden">전화번호앞자리</label>
						<form:input path="telNum1" size="3" maxlength="4" class="w50 onlyNum" /> -
						<label for="telNum2" class="hidden">전화번호중간자리</label>
						<form:input path="telNum2" size="4" maxlength="4" class="w60 onlyNum" /> -
						<label for="telNum3" class="hidden">전화번호끝자리</label>
						<form:input path="telNum3" size="4" maxlength="4" class="w60 onlyNum" />
					</td>
					<th><span class="required">*</span>핸드폰번호</th>						
					<td>
						<label for="hpNum1" class="hidden">핸드폰번호앞자리</label>
						<form:input path="hpNum1" size="3" maxlength="3" class="required w50 onlyNum"/> -
						<label for="hpNum2" class="hidden">핸드폰번호중간자리</label>
						<form:input path="hpNum2" size="4" maxlength="4" class="required w60 onlyNum"/> -
						<label for="hpNum3" class="hidden">핸드폰번호끝자리</label>
						<form:input path="hpNum3" size="4" maxlength="4" class="required w60 onlyNum"/>
					</td>						
				</tr>
				<tr>
					<th><span class="required">*</span>이메일</th>
					<td colspan="3">
						<label for="email1" class="hidden">이메일계정</label>
						<form:input path="email1" maxlength="15" class="required w150" /> @ 
						<label for="email2" class="hidden">이메일URL</label>
						<input type="text" id="email2" name="email2" maxlength="15" class="required w150" <c:if test="${!empty UserVo.email2}">value='<c:out value="${UserVo.email2}"/>'</c:if> <c:if test="${empty UserVo.email2}">value="메일주소선택"</c:if> disabled="disabled" />
						<label for="email3" class="hidden">이메일주소선택</label>
						<select id="email3" title="email3">
							<option value="메일주소선택" label="메일주소선택">
						       <c:forEach var="email" items="${emailList}">
						       		<option label="<c:out value="${email}"/>" value="<c:out value="${email}"/>">
						       </c:forEach>
							<option value="" label="직접입력">
						</select>							
					</td>
				</tr>					
				<tr>
					<th><span class="required">*</span>이메일 수신 여부</th>
					<td colspan="3">
						<c:choose>
						 	<c:when test="${UserVo.emailRcvChk eq null}">
							    <form:radiobutton path="emailRcvChk" value="Y" title="이메일 수신 여부"/> <label for="emailRcvChk1">예</label>
								<form:radiobutton path="emailRcvChk" value="N" title="이메일 수신 여부" checked="checked"/> <label for="emailRcvChk2">아니오</label>
							</c:when>
							<c:otherwise>
							    <form:radiobutton path="emailRcvChk" value="Y" title="이메일 수신 여부"/> <label for="emailRcvChk1">예</label>
							 	<form:radiobutton path="emailRcvChk" value="N" title="이메일 수신 여부"/> <label for="emailRcvChk2">아니오</label>
							</c:otherwise>
						</c:choose>
						&nbsp;&nbsp;&nbsp; 구에서 제공하는 행정정보(행사,소식 등) 안내를 이메일로 수신하시겠습니까?
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>SMS 수신 여부</th>
					<td colspan="3">
						<c:choose>
							<c:when test="${UserVo.smsRcvYn eq null}">
								<form:radiobutton path="smsRcvYn" value="Y" title="SMS 수신 여부"/> <label for="smsRcvYn1">예</label>
								<form:radiobutton path="smsRcvYn" value="N" title="SMS 수신 여부" checked="checked"/> <label for="smsRcvYn2">아니오</label>
							</c:when>
							<c:otherwise>
								<form:radiobutton path="smsRcvYn" value="Y" title="SMS 수신 여부"/> <label for="smsRcvYn1">예</label>
								<form:radiobutton path="smsRcvYn" value="N" title="SMS 수신 여부"/> <label for="smsRcvYn2">아니오</label>
							</c:otherwise>
						</c:choose>
						&nbsp;&nbsp;&nbsp; 구에서 제공하는 행정정보(행사,소식 등) 안내를 핸드폰 문자서비스로 수신하시겠습니까?		
					</td>
				</tr>
				<c:if test="${UserVo.userMenu != 'form'}">
				<tr>
					<th>최종비밀번호 변경일</th>
					<td colspan="3"><c:out value="${UserVo.pwdChgDt}"></c:out></td>
				</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<c:if test="${UserVo.userMenu != 'form'}">
			<a href="#" class="btn_m btn_out">회원 탈퇴</a>
			<a href="#" class="btn_m btn_reset">비밀번호초기화</a>
		</c:if>
		<input type="submit" value="확인" class="btn_m on">
	</div>
	</form:form>
	
	<div id="memberReason" style="display:none;">
		<div class="memberReasonOut" style="display:none;">
			<h5>회원탈퇴</h5>
			<p class="tableDesc">회원탈퇴 처리를 하시려면 비밀번호와 탈퇴사유를 입력해주세요.</p>
		</div>
		<div class="memberReasonMod" style="display:none;">
			<h5>회원정보수정</h5>
			<p class="tableDesc">회원정보를 수정하시려면 비밀번호와 수정사유를 입력해주세요.</p>
		</div>
		<!--<form id="confirmFrm" name="confirmFrm" method="post" onsubmit="return doReason(this);">-->
		<form id="confirmFrm" name="confirmFrm" method="post">
		<input type="hidden" name="mode" value="">
		<div class="tableBox">
			<table class="form">
				<caption>사유입력</caption>
				<colgroup>
					<col class="w100">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="confirmPwd">비밀번호</label></th>
						<td><input type="password" id="confirmPwd" maxlength="15" class="required w100" /></td>
					</tr>
					<tr>
						<th scope="row"><label for="changeReason">사유</label></th>
						<td><textarea rows="3" id="changeReason" class="required w100p"></textarea></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<input type="button" value="확인" class="btn_m on" onclick="doReason(confirmFrm)">
			<a href="#" class="btn_m btn_reasonClose">취소</a>
		</div>
		</form>
	</div>
</div>
	