<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
<script type="text/javascript">
//<![CDATA[
	var pwchk1 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-]).{10,15}$/;
	var pwchk2 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{10,15}$/;
	var pwchk3 = /^(?=.*[!@#$%^*+=-])(?=.*[0-9]).{10,15}$/;
    //수정
    function doUserPwdMod(){
    	//현재 비밀번호
    	if(cm_check_empty($('input[id=cuPwd]').attr("title"), $('input[id=cuPwd]')) == false) {
    		return;
    	}
    	
    	//새비밀번호
    	if(cm_check_empty($('input[id=newpwd]').attr("title"), $('input[id=newpwd]')) == false) {
    		return;
    	}
    	
    	//새비밀번호 확인
    	if(cm_check_empty($('input[id=newpwd2]').attr("title"), $('input[id=newpwd2]')) == false) {
    		return;
    	}
    	
		 if(!pwchk1.test($("input[name=newpwd]").val()) && !pwchk2.test($("input[name=newpwd]").val()) && !pwchk3.test($("input[name=newpwd]").val())){
    		alert("새 비밀번호는 영문, 숫자, 특수문자 중 2가지 이상 혼합하여 최소10자~ 15자로 설정 바랍니다.");
    		$("input[name=newpwd]").focus();
    		return;
    	}
		 
		if($("input[name=newpwd]").val() != $("input[name=newpwd2]").val()){
    		alert("새 비밀번호와 새 비밀번호 확인이 서로 일치하지 않습니다. 다시 작성 해주시기 바랍니다.");
    		$("input[name=newpwd2]").val('');
    		$("input[name=newpwd2]").focus();
    		return;
    	}
		
    	if(confirm("변경 하시겠습니까?")){
	    	document.UserFVo.action = "/site/seocho/foffice/user/User_Pwd_Mod.do";
	    	document.UserFVo.submit();
    	}
    }
    
    //취소
	function doCancel(){
		window.location.href="<c:url value='/site/seocho/foffice/user/User_Cancel.do'/>";
	}
//]]>
</script>
<form:form commandName="UserFVo" name="UserFVo" method="post">
<form:hidden path="cuId" value="${cuId}"/>
<form:hidden path="type" value="${type}"/>
<fieldset>
<legend>비밀번호 변경</legend>

<div class="pw_change">
	<div class="guide">
		<p><em class="orange"><c:out value="${name }" /></em>회원님의 개인정보 보호를 위하여 정기적으로 비밀번호를 변경하여 주시기 바랍니다.</p>
		<dl>
			<dt>※ 새 비밀번호 설정 주의 사항</dt>
			<dd>변경된 비밀번호는 관악구청 패밀리 사이트에도 적용 되며, 새 비밀번호 입력시 현재의 비밀번호와 동일하거나 
		추정 가능한 비밀번호 (즉, 본인의 주민번호, 휴대전화/일반전화 등…)를 비밀번호로 사용 할 경우 회원님의 개인정보 
		보호에 문제가 발생 할 수 있습니다.</dd>
		</dl>
	</div>
	<div class="change_form">
		<div class="row">
			<label for="cuPwd">현재 비밀번호</label>
			<form:password path="cuPwd" id="cuPwd" title="현재 비밀번호"/>
		</div>
		<div class="row">
			<label for="newpwd">새 비밀번호</label>
			<form:password path="newpwd" id="newpwd" title="새 비밀번호"/> <span>영문, 숫자, 특수문자 중 2가지 이상 혼합하여 최소10자~ 15자로 설정 바랍니다.</span>
		</div>
		<div class="row">
			<label for="newpwd2">새 비밀번호 확인</label>
			<input type="password" name="newpwd2" id="newpwd2" title="새 비밀번호 확인"> <span>비밀번호를 다시한번 입력해 주십시오</span>
		</div>						
	</div>	
</div>
<div class="btns">
	<input type="button" value="등록" class="type2" onclick="doUserPwdMod();">
	<input type="button" value="취소" class="type1" onclick="doCancel();">
</div>

</fieldset>
</form:form>	