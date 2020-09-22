<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<!-- // Contents End -->
<c:if test="${requestScope['ssSortOrder'] ne '1000000000000'}">

<!-- ******************************************************************************
		// START 담당부서표시 및 만족도 조사
	****************************************************************************** -->
	<%@include file="/common/satisfy.jsp"%>
<!-- ******************************************************************************
			// END 담당부서표시 및 만족도 조사
		****************************************************************************** -->
	
	<!-- ******************************************************************************
			// START 소셜댓글
		****************************************************************************** -->	
<form:form commandName="SocialVo" name="SocialVo" method="post">
<input type="hidden" name="sociallogin">
<input type="hidden" name="menuUrl">
<input type="hidden" name="mode" id="socailmode">
<input type="hidden" name="scidx">
<input type="hidden" name="pageIndex" value="1">
	
<c:if test="${requestScope['ssSocialShowYn'] eq 'Y'}">	
	<section id="replay-container">
		<!-- Reply Header & SNS Login -->
		<div class="social-title">
			<h1><span>소셜로그인 후</span> 댓글을 이용하실 수 있습니다.</h1>
			<div class="social-btn-container">
				<a href="javascript:loginFacebook();" class="social-btn-facebook" title="facebook 로그인 새창 이동"><span>Facebook</span></a>
				<a href="javascript:loginTwitter();" class="social-btn-twitter" title="Twitter 로그인 새창 이동"><span>Twitter</span></a>
				<a href="javascript:loginKakao();" class="social-btn-kakao" title="Kakao 로그인 새창 이동"><span>Kakao</span></a>
				<a href="javascript:loginNaver();" class="social-btn-naver" title="Naver 로그인 새창 이동"><span>Naver</span></a>
			</div>
		</div>
		
		<!-- // Reply Header & SNS Login -->

		<!-- Reply Input Form-->
		
		<div class="reply-form">
			
			<fieldset>
			<legend>댓글작성 양식</legend>
			<textarea name="commentCont" title="댓글을 입력해주세요." class="reply-textarea"  placeholder="로그인 후 댓글 입력이 가능합니다." /></textarea>
			<input type="button" value="등록" title="댓글등록" class="btn btn-reply-submit" onclick="comment('C');" />
			</fieldset>
		
		</div>

		<div class="board-foot-container" id="sociallogout" style="display:none">
			<div class="btn-area">
				<div class="btn-left"> 
						<a href="javascript:comment('LO');" class="btn btn-type1">로그아웃</a>								
				</div>
			</div>
		</div>
		<!-- // Reply Input Form -->

		<!-- Reply List Container // 댓글이 없으면 Box 자체가 보이지 않음  -->
		<div id="commentlist">						
		</div>
		<!-- // Reply List Container -->
		

	</section>
</c:if>
</form:form>
<!-- ******************************************************************************
			// START 소셜댓글
		****************************************************************************** -->

				</article>
<!-- Bottom Layout Start -->
			</div>
			<!-- // Contents Container -->
		</div>
	</div>
	
<!-- ******************************************************************************
	//Left Navigation(Location Information) & Contents Container
****************************************************************************** -->
</c:if>

<!-- ******************************************************************************
	Left Floating Bar
****************************************************************************** -->
	<div id="left-floationg-bar">

		<!-- Weather Information -->
		<%@include file="/common/weather.jsp"%>
		<!-- // Weather Information -->
	
		<!-- Left Quick Bar -->
		<div id="quick-bar">
			<ul>
				<li><a href="/site/yangcheon/08/10805010000002016112201.jsp"><img src="/images/yangcheon/common/quick-bar1.gif" alt="어르신" /><span>어르신</span></a></li>
				<li><a href="/site/yangcheon/08/10805020000002016112201.jsp"><img src="/images/yangcheon/common/quick-bar2.gif" alt="장애인" /><span>장애인</span></a></li>
				<li><a href="/site/yangcheon/08/10805030000002016112201.jsp"><img src="/images/yangcheon/common/quick-bar3.gif" alt="여성" /><span>여성</span></a></li>
				<li><a href="/site/yangcheon/08/10805040000002016112201.jsp"><img src="/images/yangcheon/common/quick-bar4.gif" alt="어린이" /><span>어린이</span></a></li>
				<li><a href="/site/yangcheon/08/10805050000002016112201.jsp"><img src="/images/yangcheon/common/quick-bar5.gif" alt="사업자" /><span>사업자</span></a></li>
			</ul>
		</div>
		<!-- // Left Quick Bar -->

	</div>
<!-- ******************************************************************************
	// Left Floating Bar
****************************************************************************** -->

<!-- ******************************************************************************
	Footer
****************************************************************************** -->

	<div id="footer">

		<!-- Family Site Container -->
		<div class="family-site-container">
			<%-- <jsp:include page="/common/family_site.jsp" flush="true"/> --%>
			<%@include file="/common/family_site.jsp"%>
		</div> 
		<!-- // Family Site Container -->

		<!-- Footer Menu -->
		<nav id="footer-menu">
			<ul>
				<li class="foot-menu1"><a href="/site/yangcheon/04/10404010300002016091211.jsp">구청오시는길</a></li>
				<li class="foot-menu2"><a href="/site/yangcheon/08/10801000000002016111110.jsp">이용약관</a></li>
				<li class="foot-menu3"><a href="/site/yangcheon/08/10802000000002016111110.jsp">개인정보취급방침</a></li>
				<li class="foot-menu4"><a href="/site/yangcheon/08/10804000000002016111110.jsp">이메일무단수집거부</a></li>
				<li class="foot-menu5"><a href="/site/yangcheon/08/10806000000002016111105.jsp">저작권정책</a></li>
				<li class="foot-menu6"><a href="/site/yangcheon/ex/bbs/List.do?cbIdx=887">홈페이지개선사항제안</a></li>
			</ul>
		</nav>
		<!-- // Footer Menu -->


		<div id="footer-address">
			<!-- Yangcheon-gu Office Address -->
			<address>
				<p><span class="display-mode">우)08095</span> <span class="display-mode">서울특별시 양천구 목동동로 105(신정동) 양천구청</span><span class="disappear"> / </span><span class="display-mode">TEL. 02)2602-3114(120 다산콜센터로 연결)</span></p>
				<p><span class="display-mode">Fax. 02)2648-8168 (종합상황실)</span><span class="disappear"> / </span><span class="display-mode">02)2620-3000(야간, 공휴일 / 당직실)</span></p>
				<p>Copyright 2016. Yangcheon-gu office all rights reserved.</p>
			</address>
			<!-- // Yangcheon-gu Office Address -->
		
			<!-- Web Accessibility certification image -->
			<p class="accessibility-mark"><img src="/images/yangcheon/common/wa_logo.gif" alt="웹접근성인증로고" /></p>
			<!-- //Web Accessibility certification image -->
		
		</div>

	</div>

<!-- ******************************************************************************
	// Footer
****************************************************************************** -->
</div>

</body>
</html>
<jsp:include page="/common/siteStates.do" flush="true"/>