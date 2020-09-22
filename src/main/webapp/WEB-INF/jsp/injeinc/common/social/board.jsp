<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<jsp:include page="inc-header.jsp" flush="false" />
<!-- Contents Start -->






<!-- ******************************************************************************
	Reply Container
****************************************************************************** -->
<form:form commandName="SocialVo" name="SocialVo" method="post">
<input type="hidden" name="sociallogin">
<input type="hidden" name="menuUrl">
<input type="hidden" name="mode">
<input type="hidden" name="scidx">
<input type="hidden" name="pageIndex" value="1">
<h3>SNS 간편로그인 &amp; 댓글</h3>
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
</form:form>
<!-- ******************************************************************************
	// Reply Container
****************************************************************************** -->











<!-- // Contents End -->
<jsp:include page="inc-footer.jsp" flush="false" />