<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<c:set var="reUrl" value='<%=request.getParameter("reUrl") %>' />
<c:set var="yn" value='<%=request.getParameter("yn") %>' />

<script type="text/javascript">
//<![CDATA[
//gpin
function gpinAuth() {
	var wWidth = 360;
	var wHight = 120;
	
	var wX = (window.screen.width - wWidth) / 2;
	var wY = (window.screen.height - wHight) / 2;

	var w = window.open("/G-PIN/pin8520Requ.jsp", "gPinLoginWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);
	w.focus();
	
}

function mobileAuth() {
	var PAgree = open("/common/mobileAuth/mobile_auth.jsp", "CheckJN", "width=420, height=570, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=480, top=115");
	PAgree.focus();
}

function writeJN() {
	//처리
	if(document.LoginFVo.returnurl.value ==  null || document.LoginFVo.returnurl.value == ""){
		window.location.href = '/site/seocho/main.do';
	}else{
		window.location.href = document.LoginFVo.returnurl.value;
	}
}

$(document).ready(function(){

    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.

    var userInputId = getCookie("userInputId");

    $("input[name='cuId']").val(userInputId);  

    if($("input[name='cuId']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
        $("#idsavechk").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
    }

    $("#idsavechk").change(function(){ // 체크박스에 변화가 있다면,

        if($("#idsavechk").is(":checked")){ // ID 저장하기 체크했을 때,
            var userInputId = $("input[name='cuId']").val();
            setCookie("userInputId", userInputId, 30); // 30일 동안 쿠키 보관
        }else{ // ID 저장하기 체크 해제 시,
            deleteCookie("userInputId");
        }

    });

    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.

    $("input[name='id']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,

        if($("#idsavechk").is(":checked")){ // ID 저장하기를 체크한 상태라면,
            var userInputId = $("input[name='id']").val();
            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
        }
    });
	<c:if test="${yn eq 'n'}">
    	$("#cuId").focus();
    </c:if>
});

 

function setCookie(cookieName, value, exdays){

    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}

 

function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();

}

 

function getCookie(cookieName) {

    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);

}

function doLogin(){
	setCookie("userInputId", $("input[name='cuId']").val(), 30); // 30일 동안 쿠키 보관
	LoginFVo.action="/site/seocho/login/Login_List.do";
	LoginFVo.submit();
}

$(window).load(function() {
	if($("input[name=returnurl]").val() == ''){
		if(document.referrer.indexOf("Login") == -1 && document.referrer.indexOf("Logout") == -1 && document.referrer.indexOf("Step") == -1 && document.referrer.indexOf("User") == -1){
			$("input[name=returnurl]").val(document.referrer);
		}
	}
});
//]]>
</script>     

<form:form commandName="LoginFVo" name="LoginFVo" method="post" onsubmit="doLogin();">
<form:hidden path="returnurl" />
<fieldset>
<legend>회원로그인</legend>
<div class="login-wrap">
	<h4 class="hide">회원로그인</h4>
	<div class="guide">
		<p><em class="blue">관악구청</em>에 오신 것을 환영합니다.</p>
		<p>아직 회원이 아니세요? 관악구청 회원이 되시면 <br>교육,행사참여 등 다양한 관악구청의 혜택이 있습니다.</p>
	</div>
	<div class="login-form">
		<div class="input">
			<div class="row">
				<label for="cuId">아이디</label> <form:input path="cuId" id="cuId" />
			</div>
			<div class="row">
				<label for="cuPwd">비밀번호</label> <form:password path="cuPwd" id="cuPwd" />
			</div>
			<div class="save">
				<label><input type="checkbox" name="idsavechk" id="idsavechk"> 아이디저장</label>								
			</div>
		</div>
		<div class="command">
			<input type="submit" value="로그인" onclick="doLogin();">
		</div>				
	</div>
	<div class="login-guide">
		<div>
			<p>아이디와 비밀번호가 생각나지 않으세요?</p>
			<a href="/site/seocho/user/User_Id_Search_Step_01.do" >아이디 찾기</a>
			<a href="/site/seocho/user/User_Pwd_Search_Step_01.do">비밀번호 찾기</a>
		</div>
		<div>
			<p>아이디가 없으신 분은 회원가입을 해주세요.</p>
			<a href="/site/seocho/user/User_Step_01.do">회원가입</a>
		</div>
	</div>
</div><!-- //login-wrap -->

</fieldset>
</form:form>


<div class="login-wrap2">
	<h4>비회원로그인</h4>
	<div class="btn">
		<a href="javascript:mobileAuth();" title="새창">휴대폰인증</a>	
		<a href="javascript:gpinAuth();" title="새창">공공아이핀인증</a>	
	</div>
	<div class="login-guide">
		<ul>
			<li>비회원은 본인 확인 후 서비스를 이용하실 수 있습니다.</li>
		</ul>
	</div>
</div><!-- //login-wrap -->