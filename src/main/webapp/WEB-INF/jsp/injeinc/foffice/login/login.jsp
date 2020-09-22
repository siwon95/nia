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
		window.location.href = '/site/${strDomain}/main.do';
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
	if($("#cuId").val() == ""){
		alert('아이디를 입력해주세요.');
		$("#cuId").focus();
		return false;
	}
	if($("#cuPwd").val() == ""){
		alert('비밀번호를 입력해주세요.');
		$("#cuPwd").focus();
		return false;
	}
	setCookie("userInputId", $("input[name='cuId']").val(), 30); // 30일 동안 쿠키 보관
	LoginFVo.action="/site/${strDomain}/login/Login_List.do";
	LoginFVo.submit();
}

$(window).load(function() {
	if($("input[name=returnurl]").val() == ''){
		if(document.referrer.indexOf("Login") == -1 && document.referrer.indexOf("Logout") == -1 && document.referrer.indexOf("Step") == -1 && document.referrer.indexOf("User") == -1){
			$("input[name=returnurl]").val(document.referrer);
		}
	}
	
	$("#cuPwd").keydown(function(key) {
		if (key.keyCode == 13) {
			doLogin();
		}
	});	
});
//]]>
</script>    
<form:form commandName="LoginFVo" name="LoginFVo" method="post">
<form:hidden path="returnurl" /> 
<div class="login-tab-container">
						<ul>
							<li class="item1"><a href="/site/<c:out value="${strDomain}"/>/login/Login.do" class="on">로그인</a></li>
							<li class="item2"><a href="/site/<c:out value="${strDomain}"/>/user/User_Id_Search_Step_01.do" >아이디찾기</a></li>
							<li class="item3"><a href="/site/<c:out value="${strDomain}"/>/user/User_Pwd_Search_Step_01.do">비밀번호찾기</a></li>
						</ul>
					</div>
					
					<div class="login-container">
<fieldset>
<legend>로그인양식</legend>
						<ul>
							<li class="login-id"><label for="login-id-input">아이디 : </label><form:input path="cuId" id="cuId" class="login-id-input" placeholder="아이디를 입력하세요"/></li>
							<li class="login-pw"><label for="login-pass-input">비밀번호 : </label><form:password path="cuPwd" id="cuPwd" class="login-pass-input" placeholder="비밀번호를 입력하세요"/></li>
							<li class="login-btn"><input type="button" value="로그인"  class="btn login-submit" onclick="doLogin();"/></li>
						</ul>
</fieldset>
					</div>


					<!-- div class="simple-login-container">
						<h2>쇼설계정으로 로그인 하신 후 양천구청 아이디와 연결하시면 더욱 간편하게 사이트를 이용할 수 있습니다.</h2>
						<ul class="simple-login-list">
							<li><span>간편로그인</span></li>
							<li><a href="#" class="simple-login-facebook"><span>Facebook</span></a></li>
							<li><a href="#" class="simple-login-twitter"><span>Twitter</span></a></li>
							<li><a href="#" class="simple-login-kakaoTalk"><span>Kakao Talk</span></a></li>
							<li><a href="#" class="simple-login-naver"><span>Naver</span></a></li>
						</ul>
					</div-->

					<div class="join-induce">
						<h2>아직 회원이 아니신가요?</h2>
						<a href="/site/<c:out value="${strDomain}"/>/user/User_Step_01.do" class="btn join-induce-btn">회원가입</a>					
					</div>
</form:form>					