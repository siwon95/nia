<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script>

function dofindID(){
	var emailchk = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	if(!$("#cuName").val()){    
		alert("이름을 입력해 주세요");
		$("#cuName").focus();
	}else if(!$("#email").val()){
		alert("이메일주소를 입력해 주세요");
		$("#email").focus();
	}else if(!emailchk.test($("#email").val())) {
		alert("이메일 주소를 바르게 입력해주세요");
		$("#email").focus();
		return false;
	}else{
		document.dform.action = "/site/DRP0000/user/User_Id_Search_Step_02.do"; 
		document.dform.submit();
	}
}

</script>


<form method="post" id="dform" name="dform" >	
		
	<input type="hidden" name="EncodeData" value="${enc_data}"/>
	<input type="hidden" name="m" value="checkplusSerivce">		
	<!-- 업체에서 응답받기 원하는 데이타를 설정하기 위해 사용할 수 있으며, 인증결과 응답시 해당 값을 그대로 송신합니다.
    	 해당 파라미터는 추가하실 수 없습니다. -->
	<input type="hidden" name="param_r1" value="">
	<input type="hidden" name="param_r2" value="">
	<input type="hidden" name="param_r3" value="">
	<input type="hidden" id="formType" name="formType" value="findId" />
		

   
<!-- 여기부터 -->
		<h3>아이디 찾기</h3>
		<div class="member_info_bg id_info_bg">
			<div>
				<strong>회원님의 아이디를 찾기 위해서는</strong>
				<span>아아핀 인증 또는 휴대전화 인증 중 선택하여 본인인증을 완료하여 주시기 바랍니다.</span>
			</div>
		</div>

		<div class="id_find degital_find">
			<span>등록시 입력한 이름과 이메일 주소를 입력한 뒤 아래 본인인증을 진행해 주십시오.</span>
			<form>
				<fieldset>
					<legend>아이디찾기</legend>
					<div>
						<label fore="아이디값1">이름</label>
						<input type="text" id="cuName" placeholder="이름" name="cuName" />
					</div>
					<div>
						<label fore="아이디값2">이메일주소</label>
						<input type="text" id="email" placeholder="이메일주소" name="email" />
					</div>
				</fieldset>
			</form>
		</div>

		<div class="divGroup certification">
			<div class="cer_sms w100p">
				<strong>휴대전화 인증</strong>
				<span>
					본인 명의의 휴대전화로 
					간단하고 빠른 로그인을 하실 수 있습니다.
				</span>
				<div class="btnArea">
					<input type="submit" class="btn_l" value="휴대폰 인증하기" onclick="dofindID();">
				</div>

			</div>
		</div>
		<!-- 여기까지 -->
</form>				