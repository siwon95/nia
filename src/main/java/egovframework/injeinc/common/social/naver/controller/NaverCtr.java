package egovframework.injeinc.common.social.naver.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.social.naver.api.Naver;
import egovframework.injeinc.common.social.naver.api.NaverOAuth2ApiBinding;
import egovframework.injeinc.common.social.naver.api.abstracts.UserOperation;
import egovframework.injeinc.common.social.naver.connect.NaverConnectionFactory;
import egovframework.injeinc.common.social.vo.SocialVo;

@Controller
public class NaverCtr extends CmmLogCtr {
	
	static final String NAVER_CLIENT_ID = EgovProperties.getProperty("naver.client.id");
	static final String NAVER_CLIENT_SECRET = EgovProperties.getProperty("naver.client.secret");
	static final String NAVER_REDIRECT_URL = EgovProperties.getProperty("naver.rediretc.url");
	
	
	@Inject
	private ConnectionRepository connectionRepository;
	
	@RequestMapping(value="/auth/naver")
	public String login(HttpServletRequest request,
								ModelMap model) throws Exception{
		
		NaverConnectionFactory connectionFactory = new NaverConnectionFactory(NAVER_CLIENT_ID, NAVER_CLIENT_SECRET);
		
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri(NAVER_REDIRECT_URL);
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
		
		return "redirect:" + authorizeUrl;
		
	}

	@RequestMapping(value="/common/social/naver/callback")
	public String callback(HttpServletRequest request, String code,
			ModelMap model) throws Exception{
		
		NaverConnectionFactory connectionFactory = new NaverConnectionFactory(NAVER_CLIENT_ID, NAVER_CLIENT_SECRET);
		OAuth2Operations oauthOperation = connectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperation.exchangeForAccess(code, NAVER_REDIRECT_URL, null);
		
		Long expireTime = accessGrant.getExpireTime();

		String accessToken = accessGrant.getAccessToken();
		String refreshToken = accessGrant.getRefreshToken();
		
		System.out.println("expireTime : "+expireTime);
		System.out.println("currentTime: "+System.currentTimeMillis());
		
		//if (expireTime != null && expireTime < System.currentTimeMillis()) {
		//	accessGrant = oauthOperation.refreshAccess(refreshToken, null);
		//	accessToken = accessGrant.getAccessToken();
		//	refreshToken = accessGrant.getRefreshToken();
		//	debugLog("accessToken is expired. refresh token = {"+accessToken+"}");
		//}
		
		//Naver naver = new NaverOAuth2ApiBinding(accessToken);
		//UserOperation userOperation = naver.userOperation();
		SocialVo socialVo = new SocialVo();
		socialVo.setSocialType(SocialVo.SOCIAL_TYPE_NAVER);
		//socialVo.setSocialId(userOperation.getId());
		//socialVo.setNickName(userOperation.getNickname());
		//socialVo.setProfileImage(userOperation.getProfile_image());
		socialVo.setAccessToken(accessToken);
		socialVo.setRefreshToken(refreshToken);
		
		HttpSession session = request.getSession();		
		session.setAttribute("socialInfo",socialVo);

		return "injeinc/common/social/callback";
	}
}
