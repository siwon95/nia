<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v2.6&appId=886722801455129";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

	<div class="fb-login-button" data-max-rows="1" data-size="medium"
		data-show-faces="false" data-auto-logout-link="false"></div>
		
		
	<a class="ax-item" onclick="document.linkedin.submit();"><i class="axi axi-linkedin-square"></i> LinkedIn</a>
	<a class="ax-item" onclick="document.facebook.submit();"><i class="axi axi-facebook-square"></i> Facebook</a>
	<a class="ax-item" onclick="document.twitter.submit();"><i class="axi axi-twitter-square"></i> Twitter</a>
	<a class="ax-item" onclick="document.kakao.submit();"><i class="axi axi-ion-chatbubble"></i> Kakao</a>
	<a class="ax-item" onclick="document.github.submit();"><i class="axi axi-github-square"></i> Github</a>
	
	<form action="/snsLogin/facebook.do" name="facebook">
		<input type="hidden" name="scope" value="email,user_friends"/>
	</form>
	<form action="/auth/linkedin" name="linkedin">
	</form>
	<form action="/snsLogin/twitter" name="twitter">
		<input type="hidden" name="scope" value="email"/>
	</form>
	<form action="/snsLogin/kakao.do" name="kakao">
	</form>
	<form action="/auth/github" name="github">
		<input type="hidden" name="scope" value="email"/>
	</form>
	
</body>
</html>