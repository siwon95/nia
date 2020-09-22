<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<div id="header">
	<h1><a href="/boffice/sy/mgr/MgrMain.do?cmm_code=MM010000"><img src="/images/cms/logo.png" alt="콘텐츠 통합관리 솔루션 EASYFRAME"></a></h1>
	<a href="#gnb" class="btn_gnbOpen">메뉴열기</a>
	<!-- 상단메뉴영역 -->
	<div id="gnb">
		<ul>
			<c:set var="menuList" value="${naviTitle.menuNavigationCd}" />
			<c:forEach items="${SesTopMenuList}" var="menu1" varStatus="menu1_status">
				<c:if test="${menu1.MM_DEPTH == 1 && menu1.SAVE_CHK eq 'Y'}">	
					<c:set var="menu1_mmcd" value="${menu1.MM_CD}" />
					<c:set var="menu1_cnt" value="${menu1_cnt+1}" />
					<li <c:if test="${fn:indexOf(menuList,menu1_mmcd)>-1 || (menuList == null && menu1_cnt == 1)}"> class="active"</c:if>>
						<a href="<c:out value="${menu1.MGR_URL }"/>">
							<c:if test="${menu1_mmcd eq 'MM000000' }"><img src="/images/cms/icon_monitor.png" alt=""></c:if>
							<c:if test="${menu1_mmcd eq 'ES000000' }"><img src="/images/cms/icon_content.png" alt=""></c:if>
							<c:if test="${menu1_mmcd eq 'BB000000' }"><img src="/images/cms/icon_board.png" alt=""></c:if>
							<c:if test="${menu1_mmcd eq 'CA000000' }"><img src="/images/cms/icon_manage.png" alt=""></c:if>
							<c:if test="${menu1_mmcd eq 'US000000' }"><img src="/images/cms/icon_member.png" alt=""></c:if>
							<c:out value="${menu1.MM_NAME}" />
						</a>	
						<c:set var="menu2_cnt" value="0" />
						<c:forEach items="${SesTopMenuList}" var="menu2" varStatus="menu2_status">
							<c:if test="${menu2.MM_DEPTH == 2 && menu1.MM_CD == menu2.MM_UPR_CD && menu2.SAVE_CHK eq 'Y'}">
								<c:set var="menu2_mmcd" value="${menu2.MM_CD}" />
								<c:set var="menu2_cnt" value="${menu2_cnt+1}" />
								<c:if test="${menu2_cnt == 1}">
									<ul>
								</c:if>
								<li <c:if test="${fn:indexOf(menuList,menu2_mmcd)>-1}"> class="active"</c:if>>
									<a href="<c:out value="${menu2.MGR_URL }"/>"><c:out value="${menu2.MM_NAME}" /></a>
									<c:set var="menu3_cnt" value="0" />
									<c:forEach items="${SesTopMenuList}" var="menu3" varStatus="menu3_status">
										<c:if test="${menu3.MM_DEPTH == 3 && menu2.MM_CD == menu3.MM_UPR_CD  && menu3.SAVE_CHK eq 'Y'}">
											<c:set var="menu3_mmcd" value="${menu3.MM_CD}" />
											<c:set var="menu3_cnt" value="${menu3_cnt+1}" />
											<c:if test="${menu3_cnt == 1}">
												<ul>
											</c:if>
											<li <c:if test="${fn:indexOf(menuList,menu3_mmcd)>-1}"> class="active"</c:if>>
												<a href="<c:out value="${menu3.MGR_URL }"/>"><c:out value="${menu3.MM_NAME}" /></a>
											</li>
										</c:if>
									</c:forEach>
									<c:if test="${menu3_cnt > 0}">
										</ul>
									</c:if>
							</c:if>
						</c:forEach>
						<c:if test="${menu2_cnt > 0}">
								</li>
							</ul>
						</c:if>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<!-- //상단메뉴영역 -->
	<div id="topNav">
		<a href="#" class="btn_alarmToggle">
			<span class="soundOnly">알림메세지</span><em><c:out value="${publishReqCnt }"/></em>
		</a>
		<div class="alarmList">
			<h6>공지사항</h6>
			<ul class="notice">
				<li><a href="#컨텐츠관리">
					<c:out value="${publishReqCnt }"/>건의 발행대기 컨텐츠가 있습니다.
				</a></li>
				<li class="empty">공지사항이 없습니다.</li>
			</ul>
			<h6>알림메세지</h6>
			<ul class="message">
				<c:forEach items="${contentProgressList }" var="result" varStatus="status">
					<li>
						<a href="<c:url value="/boffice/cn/menu/menu_list.do?siteCd=${result.siteCd }&menuCode=${result.menuCode }"/>">
						<b>컨텐츠가 <c:out value="${result.progressStatusNm }"/> 되었습니다.</b>
						<c:out value="${result.sitePath }"/>
						</a>
					</li>
				</c:forEach>
				<c:if test="${fn:length(contentProgressList) == 0}">
					<li class="empty">메세지가 없습니다.</li>
				</c:if>
			</ul>
		</div>
		<span class="user">
			<img src="/images/cms/icon_user.png" alt="">
			<b><c:out value="${SesUserId}"/>님</b>
			<a href="#" id="btn_logout">로그아웃</a>
		</span>
	</div>
</div>