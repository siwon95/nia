<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1" />
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<!-- meta name="format-detection" content="telephone=no, address=no, email=no" / -->
	<meta http-equiv="Content-Language" content="ko">
	<meta property="product:availability"	content="oos">

	<title>양천구 통합검색</title>

	<meta name="description" content="양천구 통합검색 홈페이지">
	<meta name="keywords" content="서울, 양천구, 양천구청, 통합검색">
	<meta name="author" content="Yangcheon-gu Total Search">

	<link rel="stylesheet" href="/css/search/reset.css" />
	<link rel="stylesheet" href="/css/search/subLayout.css" />
	<link rel="stylesheet" href="/css/search/global-navigation.css" />
	<link rel="stylesheet" href="/css/search/board.css" />
	<link rel="stylesheet" href="/css/search/contents.css" />

<!--[if lt IE 9]>
	<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
	<script src="/js/jquery/jquery.1.10.2.js"></script>
	<script src="/js/jquery.matchHeight.js"></script>
	<script src="/js/device.js"></script>
	<script src="/js/search/front.js"></script>

</head>
<body>

<!-- ******************************************************************************
	Top Utility Menu
****************************************************************************** -->
	<div id="top-util">
		<!-- Top Left Utility Menu -->
		<ul class="top-left-util">
			<li class="top-left-util1"><a href="#양천구의회" target="_blank">양천구의회</a></li>
			<li class="top-left-util2"><a href="#양천구보건소" target="_blank">양천구보건소</a></li>
			<li class="top-left-util3"><a href="#시설관리공단" target="_blank">시설관리공단</a></li>
		</ul>
		<!-- // Top Left Utility Menu -->
	
		<!-- Top Right Utility Menu -->
		<ul class="top-right-util">
			<li class="member-login"><a href="#member-login">로그인</a></li>
			<li class="member-join"><a href="#member-join">회원가입</a></li>
			<li class="top-util-my-page"><a href="#my-page">마이페이지</a></li>
			<li class="top-util-sitemap"><a href="#sitemap">사이트맵</a></li>
			<li class="top-util-zooming"><a href="#screen-zoomin" class="screen-zoomin">+</a><span class="screen-state">100%</span><a href="#" class="screen-zoomout">-</a></li>
			<li class="language-select">
				<select class="language">
					<option>English</option>
					<option>中文</option>
				</select>
				<input type="submit" value="이동" />
			</li>
			<li class="top-util-popup-zone"><a href="#popup-zone"><img src="/images/lib/layout/btn-top-popupzone.gif" alt="팝업존열기" /></a></li>
		</ul>
		<!-- // Top Right Utility Menu -->
	</div>
<!-- ******************************************************************************
	// Top Utility Menu
****************************************************************************** -->

<!-- ******************************************************************************
	Left Banner Bar
****************************************************************************** -->
<nav id="left-bar-container">
	<h1 class="hidden">Quick Link</h1>
	<ul class="left-bar-list">
		<li class="item-weather">
			<div id="weather-box">
				<p class="weather-temp">26︎℃</p>
				<p class="weather-icon"><img src="/images/yangcheon/weather/icon1.png" alt="비온 뒤 맑음 이미지" /></p>
				<p class="weather-text">비온 뒤 맑음</p>
			</div>
		</li>
		<li class="item-1"><a href="#"><span class="hidden">양천구청 바로가기</span></a></li>
		<li class="item-2"><a href="#"><span class="hidden">양천구SNS</span></a></li>
		<li class="item-3"><a href="#"><span class="hidden">양천구인터넷방송</span></a></li>
		<li class="item-4"><a href="#"><span class="hidden">열린구청장실</span></a></li>
	</ul>
</nav>
<!-- ******************************************************************************
	// Left Banner Bar
****************************************************************************** -->


<div id="mid-top-container">
<!-- ******************************************************************************
	Middle Top Section
****************************************************************************** -->
	<div id="top-section">

		<div class="wide-fix">

			<h1 class="top-logo"><a href="/html/index.jsp"><img src="/images/lib/layout/top-h1-logo.png" alt="양천구 로고" /></a></h1>

			<fieldset class="total-search-fieldset">
				<legend>통합검색</legend>
				<label for="total-search-select hidden"><span class="hidden">통합검색</span></label>
				<select id="total-search-select" class="total-search-select">
					<option value="">통합검색</option>
					<option value="">메뉴검색</option>
					<option value="">직원/업무</option>
					<option value="">민원사무편람</option>
					<option value="">웹페이지</option>
					<option value="">게시판</option>
					<option value="">이미지</option>
					<option value="">통합예약</option>
				</select>
				<input type="text" placeholder="검색어를 입력하세요" title="검색어를 입력하세요." id="total-search-input" class="total-search-input" />
<!-- 자동완성 시작 -->
				<div class="auto-comp-wrap" style="display:block"> 
					<div class="auto-comp-box">
						<ul class="auto-comp-list">
							<!-- 추천단어가 없을 경우 -->
							<li class="non-comp">해당 단어로 시작하는 추천어가 없습니다.</li>

							<!-- 추천단어가 있을 경우 -->
							<li>
								<div class="comp-word"><a href="#"><span class="txt-point">복지</span>청소년과</a></div>
								<div class="comp-accuracy acc20"><span class="hidden">10~20%</span></div>
							</li>
							<li>
								<div class="comp-word"><a href="#"><span class="txt-point">복지</span>시설</a></div>
								<div class="comp-accuracy acc40"><span class="hidden">30~40%</span></div>
							</li>
							<li>
								<div class="comp-word"><a href="#"><span class="txt-point">복지</span>생활</a></div>
								<div class="comp-accuracy acc60"><span class="hidden">50~60%</span></div>
							</li>
							<li>
								<div class="comp-word"><a href="#"><span class="txt-point">복지</span>카드</a></div>
								<div class="comp-accuracy acc80"><span class="hidden">70~80%</span></div>
							</li>
							<li>
								<div class="comp-word"><a href="#"><span class="txt-point">복지</span></a></div>
								<div class="comp-accuracy acc100"><span class="hidden">90~100%</span></div>
							</li>
						</ul>

<!-- 추천단어가 있을 때만 보이기 시작 // 없을 땐 style="display: none" -->
						<div class="auto-comp-footer" style="">
							<a href="#" class="comp-more">끝단어 더보기</a>
							<a href="#" class="comp-off">기능끄기</a>
						</div>
<!-- 추천단어가 있을 때만 보이기  -->
					</div>				
				</div>
<!-- // 자동완성 끝 -->
				<input type="image" src="/images/search/layout/icon-search.png" title="검색하기" class="total-search-submit" />
				<span class="re-search-check"><input type="checkbox"><label for="">결과 내 재검색</label></span>
			</fieldset>
			<div class="search-recomm-word">
				<h2>추천검색어 : </h2>
				<ul>
					<!-- 추천검색어 시작 -->
					<li><a href="#">통합예약</a></li>
					<li><a href="#">통합예약</a></li>
					<li><a href="#">정보화교육</a></li>
					<li><a href="#">동주민센터</a></li>
					<li><a href="#">예방접종</a></li>
					<li><a href="#">여권</a></li>
					<!-- 추천검색어 시작 -->
				</ul>
			</div>

			<div class="mob-top-right">
				<a href="#mob-total-menu" class="mob-total-menu-trigger"><img src="/images/lib/layout/btn-mob-menu-trigger.gif" alt="전체메뉴 열기" /></a>
				<%@include file="/common/mobile_menu.jsp"%>
			</div>

		</div>

	</div>


	<div id="global-navigation-section">

		<div class="wide-fix">

			<nav class="global-navigation">

				<h1 class="hidden">Global Navigation Menu</h1>
				<!-- Global Navigation -->
				<ul class="gnb">
					<li class="gnb-dep1 gnb1"><a href="#" class="on">통합검색</a></li>
					<li class="gnb-dep1 gnb2"><a href="#" class="">메뉴검색</a></li>
					<li class="gnb-dep1 gnb3"><a href="#" class="">직원/업무</a></li>
					<li class="gnb-dep1 gnb4"><a href="#" class="">민원사무편람</a></li>
					<li class="gnb-dep1 gnb5"><a href="#" class="">웹페이지</a></li>
					<li class="gnb-dep1 gnb6"><a href="#" class="">게시판</a></li>
					<li class="gnb-dep1 gnb7"><a href="#" class="">이미지</a></li>
					<li class="gnb-dep1 gnb8"><a href="#" class="">통합예약</a></li>
				</ul>
				<!-- // Global Navigation -->
			</nav>

		</div>

	</div>
</div>

<div id="mid-container">
	<div class="wide-fix">
		<section class="content-wrap-container">

			<h1 class="page-title">통합검색</h1>

			<div id="search-information-container">
				<div class="search-information">
					<span class="search-word"><strong>‘ 복지라는 단어를 찾아주세요 ’</strong>에 대한 검색 결과</span>
					<span class="search-num">[ 총 <strong>32,565</strong>건 ]</span>
				</div>
				<div class="search-option-container">
					<a href="#search-option" class="btn-option">검색옵션</a>
				</div>
			</div>

			<div class="search-optional-container" style="display: none;">
				<div class="search-optional-1">
					<h2>검색정렬</h2>
					<a href="#" class="on">정확도</a>
					<a href="#">최신순</a>
				</div>
					
				<div class="search-optional-2">
					<h2>영역</h2>
					<a href="#" class="on">전체</a>
					<a href="#">제목</a>
					<a href="#">내용</a>
					<a href="#">작성자</a>
				</div>
					
				<div class="search-optional-3">
					<h2>검색기간</h2>
					<a href="#" class="on">전체</a>
					<a href="#">1주</a>
					<a href="#">1개월</a>
					<a href="#">1년</a>
					<span class="start-date-wrap">
						<input type="text" id="" maxlength="10" class="search-start-date" title="검색시작 년월일을 입력해주세요" />
						<a href="#calendar" class="calendar-start" title="달력에서 검색 시작일 선택"><img src="/images/search/layout/icon-calendar.gif" alt="달력 아이콘" /></a>
					</span>
					~
					<span class="end-date-wrap">
						<input type="text" id="" maxlength="10" class="search-end-date" title="검색종료 년월일을 입력해주세요" />
						<a href="#calendar" class="calendar-end" title="달력에서 검색 종료일 선택"><img src="/images/search/layout/icon-calendar.gif" alt="달력 아이콘" /></a>
					</span>
					<input type="submit" id="" class="btn-search-date" value="검색" title="검색하기" />
				</div>
			</div>
<!-- 검색 메인에서만 노출 시작 -->
			<div class="search-option-result">
				<ul class="search-option-list">
					<li><a href="#">메뉴검색 [100]</a></li>
					<li><a href="#">직원/업무 [25]</a></li>
					<li><a href="#">민원사무편람 [10]</a></li>
					<li><a href="#">웹페이지 [50]</a></li>
					<li><a href="#">게시판 [1200]</a></li>
					<li><a href="#">이미지 [5999]</a></li>
					<li><a href="#">통합예약 [300]</a></li>
				</ul>
			</div>
<!-- 검색 메인에서만 노출 끝 -->

			<article id="content-container">