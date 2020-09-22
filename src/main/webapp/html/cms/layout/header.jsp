<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>CMS v1</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge1">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="/css/cms/base.css" />
<link rel="stylesheet" type="text/css" href="/css/cms/content.css" />
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/cms/lib.js"></script>
<script type="text/javascript" src="/js/cms/common.js"></script>
</head>
<body>
<div id="wrap">
	<!-- 상단영역 -->
	<div id="header">
		<h1><a href="#"><img src="/images/cms/logo.png" alt="콘텐츠 통합관리 솔루션 EASYFRAME"></h1>
		<a href="#gnb" class="btn_gnbOpen">메뉴열기</a>
		<div id="gnb">
			<ul>
				<li>
					<a href="#"><img src="/images/cms/icon_monitor.png" alt=""> 시스템관리</a>
					<ul>
						<li class="active"><a href="#">부서및직원관리</a>
							<ul>
								<li class="active"><a href="/html/cms/system_departmen.jsp">부서관리</a></li>
								<li><a href="/html/cms/system_staff.jsp">직원정보관리</a></li>
							</ul>
						</li>
						<li><a href="#">운영자관리</a>
							<ul>
								<li><a href="/html/cms/system_manager.jsp">운영자관리</a></li>
								<li><a href="/html/cms/system_menu.jsp">관리메뉴관리</a></li>
								<li><a href="/html/cms/system_auth.jsp">권한관리</a></li>
								<li><a href="/html/cms/system_connect.jsp">접속허용설정</a></li>
							</ul>
						</li>
						<li><a href="/html/cms/system_code.jsp">공통코드</a></li>
					</ul>
				</li>
				<li class="active">
					<a href="/html/cms/content_menu.jsp"><img src="/images/cms/icon_content.png" alt=""> 컨텐츠관리</a>
					<ul>
						<li><a href="/html/cms/content_site.jsp">사이트관리</a></li>
						<li class="active"><a href="/html/cms/content_menu.jsp">컨텐츠관리</a></li>
						<li><a href="/html/cms/content_template.jsp">템플릿관리</a></li>
					</ul>
				</li>
				<li>
					<a href="/html/cms/board_management.jsp"><img src="/images/cms/icon_board.png" alt=""> 게시판관리</a>
					<ul>
						<li class="active"><a href="/html/cms/board_management.jsp">게시판관리</a></li>
						<li><a href="/html/cms/board_template.jsp">게시판 템플릿관리</a></li>
						<li><a href="/html/cms/board_posting.jsp">게시물관리</a></li>
					</ul>
				</li>
				<li>
					<a href="#"><img src="/images/cms/icon_manage.png" alt=""> 운영관리</a>
					<ul>
						<li class="active"><a href="/html/cms/manage_popup.jsp">팝업/배너 관리</a>
							<ul>
								<li class="active"><a href="/html/cms/manage_popup.jsp">팝업관리</a></li>
								<li><a href="/html/cms/manage_banner.jsp">배너관리</a></li>
							</ul>
						</li>
						<li><a href="/html/cms/manage_upload.jsp">파일업로드관리</a></li>
						<li><a href="/html/cms/manage_status_site.jsp">접속통계</a>
							<ul>
								<li><a href="/html/cms/manage_status_site.jsp">사이트별 접속통계</a></li>
								<li><a href="/html/cms/manage_status_menu.jsp">메뉴별 접속통계</a></li>
							</ul>
						</li>
						<li><a href="/html/cms/manage_poll.jsp">설문조사</a></li>
					</ul>
				</li>
				<li>
					<a href="#"><img src="/images/cms/icon_member.png" alt=""> 회원관리</a>
					<ul>
						<li><a href="/html/cms/member_group.jsp">회원그룹관리</a></li>
						<li class="active"><a href="/html/cms/member_list.jsp">회원목록</a></li>
						<li><a href="/html/cms/member_status.jsp">회원가입통계</a></li>
						<li><a href="/html/cms/member_log.jsp">로그인 기록</a></li>
					</ul>
				</li>
				<li><a href="#">기타관리</a>
					<ul>
						<li><a href="/html/cms/etc_holiday.jsp">공휴일관리</a></li>
						<li class="active"><a href="/html/cms/etc_banid.jsp">금지아이디관리</a></li>
						<li><a href="/html/cms/etc_email.jsp">메일발송관리</a></li>
						<li><a href="/html/cms/etc_sms.jsp">문자발송관리</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div id="topNav">
			<a href="#" class="btn_alarmToggle">
				<span class="soundOnly">알림메세지</span><em>8</em>
			</a>
			<div class="alarmList">
				<h6>공지사항</h6>
				<ul class="notice">
					<li class="empty">공지사항이 없습니다.</li>
				</ul>
				<h6>알림메세지</h6>
				<ul class="message">
					<li class="empty">메세지가 없습니다.</li>
				</ul>
			</div>
			<span class="user">
				<img src="/images/cms/icon_user.png" alt="">
				<b>easyframe님</b>
				<a href="#" id="btn_logout">로그아웃</a>
			</span>
		</div>
		<!-- //상단메뉴영역 -->
	</div>
	<!-- //상단영역 -->
	<!-- 컨테이너영역 -->
	<div id="container">