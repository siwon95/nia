<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>social login test</title>
<script type="text/javascript" src="/js/jquery/jquery.1.10.2.js"></script>
<script type="text/javascript">
	function loginNaver(){
		var url = "/auth/naver.do";
		var width="690";
		var height="700";
		window.open(url, "naver login page", "width="+width+",height="+height+",scrollbars=yes");
	}
	
	function loginKakao(){
		var url = "/auth/kakao.do";
		var width="690";
		var height="700";
		window.open(url, "naver login page", "width="+width+",height="+height+",scrollbars=yes");
	}
	
	function loginFacebook(){
		var url = "/auth/facebook.do";
		var width="690";
		var height="700";
		window.open(url, "naver login page", "width="+width+",height="+height+",scrollbars=yes");
	}
	
	function loginTwitter(){
		var url = "/auth/twitter.do";
		var width="690";
		var height="700";
		window.open(url, "naver login page", "width="+width+",height="+height+",scrollbars=yes");
	}
	
	function loginResult(){
		
		$.ajax({
			type: "GET",
			url: "<c:url value='/common/social/loginResult.do'/>",
			dataType: "json",
			success:function(data) {
				var result = data.resultVo;
				$("#socialType").val(result.socialType);
				$("#socialId").val(result.socialId);
				$("#nickName").val(result.nickName);
				$("#profileImage").val(result.profileImage);
			},
			error: function(xhr,status,error) {
				alert("오류가 발생하였습니다.");
			}
		});
	}
</script>
</head>
<body>
	<h1>social login test</h1>
	
	<input type="button" value="naver" onclick="loginNaver()" />
	<input type="button" value="kakao" onclick="loginKakao()"/>
	<input type="button" value="facebook" onclick="loginFacebook()"/>
	<input type="button" value="twitter" onclick="loginTwitter()"/>
	<hr>
	<h2>로그인 결과</h2>
	<ol>
		<li>socialType : <input type="text" id="socialType" /></li>
		<li>socialId : <input type="text" id="socialId" /></li>
		<li>nickName : <input type="text" id="nickName" /></li>
		<li>profileImage : <input type="text" id="profileImage" /></li>
	</ol>
</body>
</html>