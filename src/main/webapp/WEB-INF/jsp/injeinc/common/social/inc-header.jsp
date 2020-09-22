<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	egovframework.injeinc.common.social.vo.SocialVo socialvo = (egovframework.injeinc.common.social.vo.SocialVo)request.getSession().getAttribute("socialInfo");
%>
<!Doctype html>
<html lang="ko">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
	<title>양천구청 스마트 홈페이지 레이아웃</title>

	<meta name="description" content="양천구 대표 홈페이지">
	<meta name="keywords" content="서울, 양천구, 양천구청">
	<meta name="author" content="Yangcheon-gu Office">

	<link rel="stylesheet" href="/css/yangcheon/global.css" />
	<link rel="stylesheet" href="/css/yangcheon/global-navigation.css" />
	<link rel="stylesheet" href="/css/yangcheon/subLayout.css" />

<!--[if lt IE 9]>
	<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->	
	<script type="text/javascript" src="/js/jquery/jquery.1.10.2.js"></script>
	<script src="/js/yangcheon/front.js"></script>
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
				url: "/common/social/loginResult.do",
				dataType: "json",
				success:function(data) {
					var result = data.resultVo;
					if(result.socialType == 'facebook'){
						$(".social-btn-facebook").attr("class","social-btn-facebook on");
						$(".social-btn-facebook").attr("href","javascript:void(0);");
						$(".social-btn-kakao").attr("style","display:none");
						$(".social-btn-twitter").attr("style","display:none");
						$(".social-btn-naver").attr("style","display:none");						
					}else if(result.socialType == 'kakao'){
						$(".social-btn-kakao").attr("class","social-btn-kakao on");
						$(".social-btn-kakao").attr("href","javascript:void(0);");
						$(".social-btn-facebook").attr("style","display:none");
						$(".social-btn-twitter").attr("style","display:none");
						$(".social-btn-naver").attr("style","display:none");
					}else if(result.socialType == 'naver'){
						$(".social-btn-naver").attr("class","social-btn-naver on");
						$(".social-btn-naver").attr("href","javascript:void(0);");
						$(".social-btn-kakao").attr("style","display:none");
						$(".social-btn-twitter").attr("style","display:none");
						$(".social-btn-facebook").attr("style","display:none");
					}else if(result.socialType == 'twitter'){
						$(".social-btn-twitter").attr("class","social-btn-twitter on");
						$(".social-btn-twitter").attr("href","javascript:void(0);");
						$(".social-btn-kakao").attr("style","display:none");
						$(".social-btn-facebook").attr("style","display:none");
						$(".social-btn-naver").attr("style","display:none");
					}
					$("#sociallogout").attr("style","diplay:");
					$(".reply-textarea").attr("placeholder","욕설 및 개인정보입력은 삭제될 수 있습니다.");							
					$('input[name="sociallogin"]').attr("value","Y");
					comment('R');
				},
				error: function(xhr,status,error) {
					alert("오류가 발생하였습니다.");
				}
			});
		}
		
		function delcomment(mode,scidx){
			if(confirm('댓글을 삭제하시겠습니까?')){
				comment(mode,scidx);
			}else{
				return false;
			}
		}
		
		function comment(mode,scidx){	
			var pageUrl = document.location; 
			pageUrl = new String(pageUrl);			
			pageUrl = pageUrl.replace('http://','');
			$('input[name="menuUrl"]').attr("value",pageUrl);
								
			if(mode == "D"){
				$('input[name="scidx"]').attr("value",scidx);
			}else if(mode == "M"){
				$('input[name="pageIndex"]').attr("value",Number($('input[name="pageIndex"]').attr("value"))+1);
			}
			$('input[name="mode"]').attr("value",mode);
			$.ajax({
				type: "POST",
				url: "/common/social/comment.do",
				data : $("#SocialVo").serialize(),
				dataType: "json",				
				success:function(data) {			
					var errorMsg = data.errorMsg;
					if(errorMsg == 'not login'){
						alert('로그인 후 이용하시기 바랍니다.');
						return false;
					}else if(errorMsg == 'no comment'){
						alert('댓글을 입력해 주세요.');
						return false;
					}
					var result = data.resultListVo;
					var pageIndex = data.pageIndex;
					var totcnt = data.totcnt;
					var socialtype = data.socialtype;
					var loginsid = data.loginsid;	
					$("#commentlist").empty();
					
					var tag = new StringBuffer();
					if(result != null && result.length > 0){
						tag.append("<article class='reply-list-container'>");
						tag.append("<p class='reply-list-numbers'><strong>"+totcnt+"</strong>개의 댓글</p>");
						tag.append("<div class='reply-list-wrap'>");		
						for(var i = 0; i < result.length;i++){
							tag.append("<div class='reply-box'>");
							tag.append("<p class='profile-photo'><img src='"+result[i].profileImage+"' alt='"+result[i].nickName+" 프로필 사진 이미지' /></p>");
							tag.append("<p class='writer-information'>");
							tag.append("<span class='name'>"+result[i].nickName+"</span>");
							tag.append("<span class='date'>"+result[i].regdt+"</span>");						
							tag.append("</p>");
							tag.append("<p class='reply-txt'>");							
							tag.append(result[i].commentCont.replaceAll("\n","<br>"));
							tag.append("</p>");
							if(loginsid == result[i].socialId){
								tag.append("<a href=\"javascript:delcomment('D','"+result[i].scidx+"')\" class='reply-delete' title='댓글 삭제하기'><img src='/images/yangcheon/common/btn-reply-delete.gif' alt='삭제' /></a>");	
							}							
							tag.append("</div>");					
						}
						tag.append("</div>");
						if(Number(totcnt) > Number(pageIndex)*5){
							tag.append("<a href=\"javascript:comment('M')\" class='btn btn-reply-more'>더보기</a>");
						}
						tag.append("</article>");
						
						$("#commentlist").append(tag.toString());
						$('textarea[name="commentCont"]').val("");	
						$('input[name="pageIndex"]').attr("value",pageIndex);
					}
					if(mode == "R" || mode == "M"){
						if(socialtype != null && socialtype != ""){
							if(socialtype == 'facebook'){
								$(".social-btn-facebook").attr("class","social-btn-facebook on");
								$(".social-btn-facebook").attr("href","javascript:void(0);");
								$(".social-btn-kakao").attr("style","display:none");
								$(".social-btn-twitter").attr("style","display:none");
								$(".social-btn-naver").attr("style","display:none");						
							}else if(socialtype == 'kakao'){
								$(".social-btn-kakao").attr("class","social-btn-kakao on");
								$(".social-btn-kakao").attr("href","javascript:void(0);");
								$(".social-btn-facebook").attr("style","display:none");
								$(".social-btn-twitter").attr("style","display:none");
								$(".social-btn-naver").attr("style","display:none");
							}else if(socialtype == 'naver'){
								$(".social-btn-naver").attr("class","social-btn-naver on");
								$(".social-btn-naver").attr("href","javascript:void(0);");
								$(".social-btn-kakao").attr("style","display:none");
								$(".social-btn-twitter").attr("style","display:none");
								$(".social-btn-facebook").attr("style","display:none");
							}else if(socialtype == 'twitter'){
								$(".social-btn-twitter").attr("class","social-btn-twitter on");
								$(".social-btn-twitter").attr("href","javascript:void(0);");
								$(".social-btn-kakao").attr("style","display:none");
								$(".social-btn-facebook").attr("style","display:none");
								$(".social-btn-naver").attr("style","display:none");
							}
							$("#sociallogout").attr("style","diplay:");
							$(".reply-textarea").attr("placeholder","욕설 및 개인정보입력은 삭제될 수 있습니다.");							
							$('input[name="sociallogin"]').attr("value","Y");
						}
					}else if(mode == "LO"){
						$("#sociallogout").attr("style","display:none");
						$(".social-btn-facebook").attr("class","social-btn-facebook");
						$(".social-btn-naver").attr("class","social-btn-naver");
						$(".social-btn-kakao").attr("class","social-btn-kakao");
						$(".social-btn-twitter").attr("class","social-btn-twitter");
						$(".social-btn-facebook").attr("href","javascript:loginFacebook()");
						$(".social-btn-naver").attr("href","javascript:loginNaver()");
						$(".social-btn-kakao").attr("href","javascript:loginKakao()");
						$(".social-btn-twitter").attr("href","javascript:loginTwitter()");
						$(".social-btn-facebook").attr("style","display:");
						$(".social-btn-kakao").attr("style","display:");
						$(".social-btn-twitter").attr("style","display:");
						$(".social-btn-naver").attr("style","display:");
					}					
				},
				error: function(xhr,status,error) {
					alert("오류가 발생하였습니다.");
				}
			});
		}
		
		
		
		var StringBuffer = function() {
		    this.buffer = new Array();
		};
		StringBuffer.prototype.append = function(str) {
		    this.buffer[this.buffer.length] = str;
		};
		StringBuffer.prototype.toString = function() {
		    return this.buffer.join("");
		};
		String.prototype.replaceAll = function(org, dest) {
		    return this.split(org).join(dest);
		}
		
		
		$(document).ready(function(){						
			comment('R');							
		});
	</script>
</head>
<body>

<div id="skipnavigation">
	<a href="#container">본문 바로가기</a>
</div>

<div id="layout-wrap">
<!-- ******************************************************************************
	Top Utility Menu
****************************************************************************** -->
	<div id="top-util">
		<!-- Top Left Utility Menu -->
		<ul class="top-left-util">
			<li><a href="http://www.ycc.go.kr/" target="_blank" class="top-left-util1">양천구의회</a></li>
			<li><a href="/health/health/main.do" target="_blank" class="top-left-util2">양천구보건소</a></li>
			<li><a href="http://www.ycs.or.kr/" target="_blank" class="top-left-util3">시설관리공단</a></li>
		</ul>
		<!-- // Top Left Utility Menu -->
	
		<!-- Top Right Utility Menu -->
		<ul class="top-right-util">
			<li><a href="#screen-zoomin" class="screen-zoomin">+</a><span class="screen-state">100%</span><a href="#" class="screen-zoomout">-</a></li>
			<li><a href="#member-login" class="member-login">로그인</a></li>
			<li><a href="#member-join" class="member-join">회원가입</a></li>
			<li class="language-select">
				<select class="language">
					<option>English</option>
					<option>中文</option>
				</select>
				<input type="submit" value="이동" />
			</li>
		</ul>
		<!-- // Top Right Utility Menu -->
	</div>

<!-- ******************************************************************************
	// Top Utility Menu
****************************************************************************** -->

<hr />

<!-- ******************************************************************************
	Right Floating Bar(Total Reservation & SNS)
****************************************************************************** -->

	<div id="total-reservation">
		<a href="#" class="total-reservation-trigger"><img src="/images/yangcheon/common/total-reservation.gif" alt="통합예약 SNS" /></a>
		<ul class="total-sns">
			<li><a href="#sns-twitter"><img src="/images/yangcheon/common/total-reservation-sns1.png" alt="트위터" /></a></li>
			<li><a href="#sns-facebook"><img src="/images/yangcheon/common/total-reservation-sns2.png" alt="페이스북" /></a></li>
			<li><a href="#sns-kakaostory"><img src="/images/yangcheon/common/total-reservation-sns3.png" alt="카카오스토리" /></a></li>
		</ul>
		<div id="reservation-box"></div>
	</div>

<!-- ******************************************************************************
	// Right Floating Bar(Total Reservation & SNS)
****************************************************************************** -->

<hr />

<!-- ******************************************************************************
	Global Navigation & Total Search 
****************************************************************************** -->

	<div id="header">
		<h1 class="logo"><a href="/html/index.jsp"><img src="/images/yangcheon/common/top-h1-logo.png" alt="양천구 로고" /></a></h1>
		<div class="header-banner">
			<span><img src="/images/yangcheon/banner/top-h1-banner.jpg" alt="8.15 광복절. 광복 71주년" /></span>
		</div>

		<!-- Global Navigation -->
		<nav id="global-navigation">
			<h1>주메뉴</h1>
			<ul class="gnb">
				<li class="dep1-item"><a href="#" class="dep1">통합민원</a>
					<div class="sub-gnb sgnb1">
						<div class="sub-gnb-container">
							<h2>통합민원</h2>
							<ul class="sub-gnb-list">
								<li>
									<h3>종합민원안내</h3>
									<ul class="sub-gnb-item">
										<li><a href="#">민원업무안내민원업무안내</a></li>
										<li><a href="#">민원서식</a></li>
										<li><a href="#">민원수수료</a></li>
										<li><a href="#">민원사무편람</a></li>
										<li><a href="#">무인민원발급기안내</a></li>
										<li><a href="#">행정서비스헌장</a></li>
										<li><a href="#">민원행정추진계획</a></li>
										<li><a href="#">민원서비스제도</a></li>
									</ul>
								</li>
								<li>
									<h3>분야별민원안내</h3>
									<ul class="sub-gnb-item">
										<li><a href="#">청소</a></li>
										<li><a href="#">자동차</a></li>
										<li><a href="#">환경</a></li>
										<li><a href="#">주택</a></li>
										<li><a href="#">재건축</a></li>
										<li><a href="#">민방위</a></li>
										<li><a href="#">여권</a></li>
										<li><a href="#">지방세인터넷납부</a></li>
									</ul>
								</li>
								<li>
									<h3>민원신청</h3>
									<ul class="sub-gnb-item">
										<li><a href="#">민원24</a></li>
										<li><a href="#">인터넷발급/신청</a></li>
									</ul>
								</li>
								<li>
									<h3>민원상담</h3>
									<ul class="sub-gnb-item">
										<li><a href="#">온라인민원상담</a></li>
										<li><a href="#">법률무료상담</a></li>
										<li><a href="#">민원FAQ</a></li>
									</ul>
								</li>
								<li>
									<h3>주민신고센터</h3>
									<ul class="sub-gnb-item">
										<li><a href="#">주민신고센터안내</a></li>
										<li><a href="#">재난위험신고</a></li>
										<li><a href="#">환경신문고</a></li>
										<li><a href="#">불합리규제신고</a></li>
										<li><a href="#">예산낭비신고</a></li>
										<li><a href="#">식품안전소비자신고</a></li>
										<li><a href="#">공직자부조리</a></li>
										<li><a href="#">하도급부조리</a></li>
										<li><a href="#">공익신고</a></li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</li>

				<li class="dep1-item"><a href="#" class="dep1">소통과 참여</a>
					<div class="sub-gnb sgnb2">
						<div class="sub-gnb-container">
							<h2>소통과 참여</h2>
							<ul class="sub-gnb-list">
									<li>
										<h3>종합민원안내</h3>
										<ul class="sub-gnb-item">
											<li><a href="#">민원업무안내</a></li>
											<li><a href="#">민원서식</a></li>
										</ul>
									</li>
									<li>
										<h3>종합민원안내</h3>
										<ul class="sub-gnb-item">
											<li><a href="#">민원업무안내</a></li>
											<li><a href="#">민원서식</a></li>
										</ul>
									</li>
									<li>
										<h3>종합민원안내</h3>
										<ul class="sub-gnb-item">
											<li><a href="#">민원업무안내</a></li>
											<li><a href="#">민원서식</a></li>
										</ul>
									</li>
									<li>
										<h3>종합민원안내</h3>
										<ul class="sub-gnb-item">
											<li><a href="#">민원업무안내</a></li>
											<li><a href="#">민원서식</a></li>
										</ul>
									</li>
								</ul>
							</div>
					</div>
				</li>
				<li class="dep1-item"><a href="#" class="dep1">행정공개</a>
					<div class="sub-gnb sgnb3">
						<div class="sub-gnb-container">
							<h2>행정공개</h2>
							<ul class="sub-gnb-list">
									<li>
										<h3>종합민원안내</h3>
										<ul class="sub-gnb-item">
											<li><a href="#">민원업무안내</a></li>
											<li><a href="#">민원서식</a></li>
											<li><a href="#">민원수수료</a></li>
											<li><a href="#">민원사무편람</a></li>
											<li><a href="#">무인민원발급기안내</a></li>
											<li><a href="#">행정서비스헌장</a></li>
											<li><a href="#">민원행정추진계획</a></li>
											<li><a href="#">민원서비스제도</a></li>
										</ul>
									</li>
								</ul>
							</div>
					</div>
				</li>
				<li class="dep1-item"><a href="#" class="dep1">양천소개</a>
					<div class="sub-gnb sgnb4">
						<div class="sub-gnb-container">
							<h2>양천소개</h2>
							<ul class="sub-gnb-list">
									<li>
										<h3>종합민원안내</h3>
										<ul class="sub-gnb-item">
											<li><a href="#">민원업무안내</a></li>
											<li><a href="#">민원서식</a></li>
											<li><a href="#">민원수수료</a></li>
											<li><a href="#">민원사무편람</a></li>
											<li><a href="#">무인민원발급기안내</a></li>
											<li><a href="#">행정서비스헌장</a></li>
											<li><a href="#">민원행정추진계획</a></li>
											<li><a href="#">민원서비스제도</a></li>
										</ul>
									</li>
								</ul>
							</div>
					</div>
				</li>
				<li class="dep1-item"><a href="#" class="dep1">분야별정보</a>
					<div class="sub-gnb sgnb5">
						<div class="sub-gnb-container">
							<h2>분야별정보</h2>
							<ul class="sub-gnb-list">
								<li><a href="#" class="sgnb5-1">복지</a></li>
								<li><a href="#"  class="sgnb5-2">교육/문화</a></li>
								<li><a href="#" class="sgnb5-3">교통&middot;주차</a></li>
								<li><a href="#" class="sgnb5-4">청소/위생</a></li>
								<li><a href="#" class="sgnb5-5">지역경제</a></li>
								<li><a href="#" class="sgnb5-6">일자리플러스센터</a></li>
								<li><a href="#" class="sgnb5-7">건축/지적</a></li>
								<li><a href="#" class="sgnb5-8">부동산</a></li>
								<li><a href="#" class="sgnb5-9">생활안전</a></li>
							</ul>
						</div>
					</div>
				</li>
			</ul>
		</nav>
		<!-- // Global Navigation -->
	
		<div id="total-menu">
			<a href="#total-menu" class="menu-trigger"><img src="/images/yangcheon/common/top-total-menu.gif" alt="전체메뉴 보기" /></a>
		</div>
	
		<!-- Total Search -->
		<div id="total-search-box">
			<select class="total-search">
				<option>통합검색</option>
			</select>
			<input type="text"  class="top-search-input" />
			<input type="image" src="/images/yangcheon/common/top-search-btn.gif" title="검색" class="top-search-btn" />
		</div>
		<!-- // Total Search -->

	</div>

<!-- ******************************************************************************
	// Global Navigation & Total Search
****************************************************************************** -->

<hr />

<!-- ******************************************************************************
	Left Navigation(Location Information) & Contents Container
****************************************************************************** -->
	<div class="visual-container-type1"></div><!-- Content Header Visual : Type Change to Menu -->

	<div id="wrap" class="wrap-type1">

		<!-- Left Navigation(Location Information) -->
		<div id="lnb" class="type1"><!-- Menu에 따라 Type 변경은 Class로 제어 -->
			<ul class="lnb-dep1">
				<li><span class="lnb-home"><img src="/images/yangcheon/common/lnb-home.gif" alt="Index" /></span></li>
				<li><a href="#" class="lnb-dep1">통합민원</a>
					<div class="lnb-sub lnb-dep1-sub">
						<ul>
							<li><a href="#" class="on">Dep1-1</a></li>
							<li><a href="#">Dep1</a></li>
							<li><a href="#">Dep1</a></li>
						</ul>
					</div>
				</li>
				<li><a href="#" class="lnb-dep2">종합민원안내</a>
					<div class="lnb-sub lnb-dep2-sub">
						<ul>
							<li><a href="#" class="on">Dep2-1</a></li>
							<li><a href="#">Dep2</a></li>
							<li><a href="#">Dep2</a></li>
						</ul>
					</div>
				</li>
				<li><a href="#" class="lnb-dep3">민원업무안내 민원업무안내</a>
					<div class="lnb-sub lnb-dep3-sub">
						<ul>
							<li><a href="#">Dep3</a></li>
							<li><a href="#" class="on">Dep3</a></li>
							<li><a href="#">Dep3</a></li>
						</ul>
					</div>
				</li>
			</ul>
		</div>
		<!-- // Left Navigation(Location Information) -->
	
		<div id="container">
		
			<!-- Container Header(Visual & Page Title) -->
			<div id="container-header">
				<h2>통합민원</h2>
			</div>
			<!-- // Container Header(Visual & Page Title) -->
		
			<!-- Contents Container -->
			<div id="container-contents">

				<!-- Tab Bar -->
				<div class="tab-container">

					<!-- type 2
					<ul class="col-type2">
						<li><a href="#" class="on">중장기민원행정추진종합계획</a></li>
						<li><a href="#">민원행정 및 제도개선 추진계획 민원행정 및 제도개선 추진계획</a></li>
					</ul>
					-->

				</div>
				<!-- // Tab Bar -->


				<article id="contents">

					<!-- Content Utility Menu Bar -->
					<ul class="content-util">
						<li><a href="#"><img src="/images/yangcheon/common/content-util-facebook.png" alt="페이스북" /></a></li>
						<li><a href="#"><img src="/images/yangcheon/common/content-util-twitter.png" alt="트위터" /></a></li>
						<li><a href="#"><img src="/images/yangcheon/common/content-util-kakaostory.png" alt="카카오스토리" /></a></li>
						<li><a href="#"><img src="/images/yangcheon/common/content-util-blog.png" alt="블로그" /></a></li>
						<li><a href="#"><img src="/images/yangcheon/common/content-util-print.png" alt="인쇄" /></a></li>
					</ul>
					<!-- // Content Utility Menu Bar -->

					<div class="content-information">
						<p class="content-information-icon"><img src="/images/yangcheon/common/cont-info-icon-type1-1.jpg" alt="" /></p>
						<div class="content-information-text">
							<h2>빠르고 정확한 민원발급 서비스제공</h2>
							<p>민원업무를 위해 양천구청을 방문하시는 분들께 빠르고 정확한 업무와 밝은 미소로 도움을 드리겠습니다. 가나다 색인을 통해 수수료를 확인하시고, 현금 또는 카드결제 가능합니다.</p>
						</div>
					</div>
<!-- Top Layout End -->