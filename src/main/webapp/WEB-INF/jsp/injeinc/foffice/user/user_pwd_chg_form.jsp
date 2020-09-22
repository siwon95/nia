<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">
//비밀번호 9자 이상 영문, 숫자, 특수문자 조합
var pwchk = /^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()_+|\=-]).{9,}$/;

 function doUserPwdMod(){
	 
  	if(!$("#cuPwd").val()){    
  		alert("비밀번호를 입력해 주세요");
  		$("#cuPwd").focus();
  		return false;
  	}else if(!$("#newPwd").val()){
  		alert("새비밀번호를 입력해 주세요");
  		$("#newPwd").focus();
  		return false;
  	}else if(!$("#newPwd2").val()){
  		alert("새비밀번호 확인을 입력해 주세요");
  		$("#newPwd2").focus();
  		return false;
  	}else if (!pwchk.test($("#newPwd").val())) {
  		alert("비밀번호는 영문, 숫자, 특수문자 모두포함 9자 이상 으로 설정 바랍니다.");
  		$("#newPwd").focus();
  		return false;
	}else if($("#newPwd").val() != $("#newPwd2").val()){
  		alert("새비밀번호, 새비밀번호확인이 서로 맞지않습니다. 다시 입력해주세요");
  		$("#newPwd").focus();
  		return false;
  	} else{
	  	document.UserFVo.action = "/site/DRP0000/foffice/user/User_Pwd_Mod.do";
	  	document.UserFVo.submit();
  	} 
 }
</script>
<form:form commandName="UserFVo" name="UserFVo" method="post">
<input type="hidden" name="cuId" value="${ss_id}"/>
<input type="hidden" name="loginExclude" value="Y"/>
<!-- 여기부터 -->
	<h3>비밀번호 변경</h3>
	<div class="member_info_bg pw_info_bg">
		<div>
			<span>
				비밀번호는 주기적으로 바꾸어 사용하시는 것이 안전합니다.<br>
				비밀번호에 영문 대소문자, 숫자, 특수문자를 조합하시면 비밀번호 안전도가 높아져 도용의 위험이 줄어듭니다.
			</span>
		</div>
	</div>

	<div class="member_admin">
		<span>비밀번호를 변경하시려면 현재 비밀번호를 입력 후, 사용할 비밀번호를 입력하여 주십시오.</span>
			<fieldset>
				<legend>회원정보비밀번호변경관리</legend>
				<input type="password" placeholder="현재 비밀번호" id="cuPwd" name="cuPwd" /><br>
				<input type="password" placeholder="새비밀번호" id="newPwd" name="newPwd" /><br>
				<input type="password" placeholder="새비밀번호 확인" id="newPwd2" name="newPwd2" />
				<div class="btnArea">
					<input type="submit" class="xlarge w207 btn_l" value="확인" onclick="return doUserPwdMod()">
					<a href="/site/DRP0000/main.do" class="btn_l xlarge w207 btn_gray" >취소</a>
				</div>
			</fieldset>
	</div>
	<!-- 여기까지 -->
</form:form>	