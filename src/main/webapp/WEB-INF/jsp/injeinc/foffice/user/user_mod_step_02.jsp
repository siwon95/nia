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
    function doUserModForm(){
    	if($("input[name=cuPwd]").val().length == 0){
    		alert("비밀번호를 입력해주세요");
    		return;
    	}else{
    		document.UserFVo.action = "<c:url value='/site/seocho/foffice/user/User_Mod_Step_03.do'/>";
    		document.UserFVo.submit();
    	}
    }
//]]>
</script>

<div class="pw_check_form">
	<p>회원정보수정을 하시려면 비밀번호 <em class="orange">비밀번호 확인</em> 후 회원정보를 수정 하실 수 있습니다.</p>
	<form:form commandName="UserFVo" name="UserFVo" method="post" onsubmit="doUserModForm();">
	<form:hidden path="cuId" />
	<fieldset>
	<legend>비밀번호 확인</legend>
	
	<label for="cuPwd">비밀번호</label>
	<form:password path="cuPwd" id="cuPwd" />
	<span class="btns"><input type="submit" value="확인" class="type2"></span>
		
	</fieldset>
	</form:form>
</div>