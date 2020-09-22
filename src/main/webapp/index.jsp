<%@page import="java.net.URLEncoder"%>
<%@page import="com.ibm.icu.impl.duration.impl.DataRecord.ECountVariant"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이지프레임구청 홈페이지 프로젝트</title>
<style>
@charset "utf-8";

/* Reset */
*{-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(255,255,255,0)}
body,p,h1,h2,h3,h4,h5,h6,ul,ol,li,dl,dt,dd,table,th,td,form,fieldset,legend,input,textarea,button,select{margin:0;padding:0}
body,input,textarea,button,table,h1,h2,h3,h4,h5,h6{font-family:'Nanum Gothic','Malgun Gothic','Sans-serif','sans-serif','Dotum';font-size:14px;color:#222}
select{font-size:12px;color:#222}
ul,ol,li{list-style:none}
img,fieldset{border:0px none;}
img,input,select,button,label,textarea{vertical-align:middle;}
label{position:relative;}
i,em,address{font-style:normal;}
table{border-spacing:0;table-layout:fixed;}
a{text-decoration:none;color:#222;}
a:hover{text-decoration:none;}
article,aside,hgroup,header,footer,figure,figcaption,nav,section{display:block;}
.clear{display:block;height:0px;font-size:0px;font-size:0em;line-height:0px;content:'';zoom:1;overflow:hidden;clear:both;}
caption, .soundOnly, .hidden{display:block !important;position:absolute !important;top:-9999px !important;}
iframe{margin:0;padding:0;width:100%;border:none;}
pre{line-height:120%;}
.noBr{text-overflow:ellipsis;white-space:nowrap;overflow:hidden;}
#loading{display:none;position:fixed;top:0;left:0;right:0;bottom:0;background:rgba(0,0,0,0.6);z-index:999;}
#overlay{display:none;position:fixed;top:0;left:0;right:0;bottom:0;background:rgba(0,0,0,0.6);z-index:999;}

/* Size */
.w10{width:10px}.w20{width:20px}.w30{width:30px}.w40{width:40px}.w50{width:50px}.w60{width:60px}.w70{width:70px}.w80{width:80px}.w90{width:90px}.w100{width:100px}.w110{width:110px}.w120{width:120px}.w130{width:130px}.w140{width:140px}.w150{width:150px}.w160{width:160px}.w170{width:170px}.w180{width:180px}.w190{width:190px}.w200{width:200px}.w210{width:210px}.w220{width:220px}.w230{width:230px}.w240{width:240px}.w250{width:250px}.w260{width:260px}.w270{width:270px}.w280{width:280px}.w290{width:290px}.w300{width:300px}.w310{width:310px}.w320{width:320px}.w330{width:330px}.w340{width:340px}.w350{width:350px}.w360{width:360px}.w370{width:370px}.w380{width:380px}.w390{width:390px}.w400{width:400px}.w410{width:410px}.w420{width:420px}.w430{width:430px}.w440{width:440px}.w450{width:450px}.w460{width:460px}.w470{width:470px}.w480{width:480px}.w490{width:490px}.w500{width:500px}.w510{width:510px}.w520{width:520px}.w530{width:530px}.w540{width:540px}.w550{width:550px}.w560{width:560px}.w570{width:570px}.w580{width:580px}.w590{width:590px}.w600{width:600px}.w610{width:610px}.w620{width:620px}.w630{width:630px}.w640{width:640px}.w650{width:650px}.w660{width:660px}.w670{width:670px}.w680{width:680px}.w690{width:690px}.w700{width:700px}
.w15{width:15px}.w25{width:25px}.w35{width:35px}.w45{width:45px}.w55{width:55px}.w65{width:65px}.w75{width:75px}.w85{width:85px}.w90{width:95px}
.w5p{width:5%}.w10p{width:10%}.w15p{width:15%}.w20p{width:20%}.w25p{width:25%}.w30p{width:30%}.w35p{width:35%}.w40p{width:40%}.w45p{width:45%}.w48p{width:48%}.w49p{width:49%}.w50p{width:50%}.w55p{width:55%}.w60p{width:60%}.w65p{width:65%}.w70p{width:70%}.w75p{width:75%}.w80p{width:80%}.w85p{width:85%}.w90p{width:90%}.w95p{width:95%}.w100p{width:99.9%}
.h10{height:10px}.h20{height:20px}.h30{height:30px}.h40{height:40px}.h50{height:50px}.h60{height:60px}.h70{height:70px}.h80{height:80px}.h90{height:90px}.h100{height:100px}.h110{height:110px}.h120{height:120px}.h130{height:130px}.h140{height:140px}.h150{height:150px}.h160{height:160px}.h170{height:170px}.h180{height:180px}.h190{height:190px}.h200{height:200px}.h210{height:210px}.h220{height:220px}.h230{height:230px}.h240{height:240px}.h250{height:250px}.h260{height:260px}.h270{height:270px}.h280{height:280px}.h290{height:290px}.h300{height:300px}.h310{height:310px}.h320{height:320px}.h330{height:330px}.h340{height:340px}.h350{height:350px}.h360{height:360px}.h370{height:370px}.h380{height:380px}.h390{height:390px}.h400{height:400px}.h410{height:410px}.h420{height:420px}.h430{height:430px}.h440{height:440px}.h450{height:450px}.h460{height:460px}.h470{height:470px}.h480{height:480px}.h490{height:490px}.h500{height:500px}.h510{height:510px}.h520{height:520px}.h530{height:530px}.h540{height:540px}.h550{height:550px}.h560{height:560px}.h570{height:570px}.h580{height:580px}.h590{height:590px}.h600{height:600px}.h610{height:610px}.h620{height:620px}.h630{height:630px}.h640{height:640px}.h650{height:650px}.h660{height:660px}.h670{height:670px}.h680{height:680px}.h690{height:690px}.h700{height:700px}
.indent1{text-indent:15px}.indent2{text-indent:30px}.indent3{text-indent:45px}.indent4{text-indent:60px}

body{padding:30px;}
.admin{display:inline-block;border:1px solid #ccc;padding:10px 20px;border-radius:5px;}
.admin:hover{background:#f4f4f4;color:#194780;}
.site-wrap{float:left;width:25%;}
.site-wrap h2{font-size:24px;margin:20px 0;}
.site-wrap ul{list-style:nowral;}
.site-wrap ul li{margin-bottom:10px;}
.site-wrap a{color:#555;}
.site-wrap a:hover{text-decoration:underline;color:#194780;}
</style>
</head>
<body>
<br/>
<!-- <a href="/boffice/login/Login.do" target="_blank" class="admin"/>통합관리자로그인</a><br/><br/> -->

<div class="site-wrap">
	 <h2>사이트</h2>
	 <ul>
	<!-- 	<li><a href="/site/demo/main.do" target="_blank">데모</a></li>
		<li><a href="/site/yangcheon/main.do" target="_blank">양천구청</a></li> -->
		<li><a href="/site/DRP0000/main.do" target="_blank">디지털역기능</a></li>
	 </ul>
</div>

<div style="clear:both;"></div>

	  
	  

</body>
</html>
