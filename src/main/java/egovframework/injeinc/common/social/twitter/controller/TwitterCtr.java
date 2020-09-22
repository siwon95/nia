package egovframework.injeinc.common.social.twitter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.social.vo.SocialVo;

@Controller
public class TwitterCtr extends CmmLogCtr {
	
	/**
	 * property 에서 불러 오는걸로 변경
	 */
	private final String CONSUMER_KEY = EgovProperties.getProperty("twitter.consumer.key");
	private final String CONSUMER_SECRET_KEY = EgovProperties.getProperty("twitter.consumer.secret");
	private final String TWITTER_REDIRECT_URL = EgovProperties.getProperty("twitter.rediretc.url");
	private Twitter twitter;
	
	
	public void getFaceBookClientInstance(String accessToken) {
		try {
			twitter = (Twitter) new TwitterFactory().getInstance();
		} catch (Exception e) {
			System.out.println("facebook Instance Error ,, please again from the beginning OAuth certification...");
		}
	}
	
	public String facebookSignin() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@RequestMapping(value="/auth/twitter")
	public String facebookSignin(HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		Twitter twitter = new TwitterFactory().getInstance();
		//twitter로 접근한다.
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET_KEY);
		//성공시 requestToken에 해당정보를 담겨져온다.
		RequestToken requestToken =  twitter.getOAuthRequestToken(TWITTER_REDIRECT_URL);		
		//requestToken 을 반드시 세션에 담아주어야 한다.
		SocialVo socialVo = new SocialVo();
		socialVo.setRequesttoken(requestToken);
		request.getSession().setAttribute("socialInfo", socialVo);
		String authorizeUrl = requestToken.getAuthorizationURL();  //접속할 url값이 넘어온다.
		requestToken.getToken(); //token값을 가져온다.
		requestToken.getTokenSecret(); //token Secret값을 가져온다.
		
		return "redirect:" + authorizeUrl;				
	}
	
	@RequestMapping(value="/common/social/twitter/callback")
	public String getFacebookClientAccessToken(String oauth_verifier,String oauth_token, HttpServletRequest request,
			ModelMap model) throws Exception {
		
		
		
		AccessToken accesstoken = new AccessToken(oauth_token, oauth_verifier);
		Twitter twitter = new TwitterFactory().getInstance();			
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET_KEY); //저장된 consumerKey, consumerSecret
		//AccessToken accessToken = null;			
		List<Status> statuses;
		
		//트위터 로그인 연동시 담은 requestToken 의 세션값을 가져온다.
		SocialVo socialvo = (SocialVo)request.getSession().getAttribute("socialInfo");			
		accesstoken = twitter.getOAuthAccessToken(socialvo.getRequesttoken(), oauth_verifier);			
		twitter.setOAuthAccessToken(accesstoken);
		//User user = twitter.verifyCredentials();
		System.out.println("accessToken : "+accesstoken);
		//statuses =  twitter.getHomeTimeline();
		
		//SocialVo socialVo = new SocialVo();
		socialvo.setSocialType(SocialVo.SOCIAL_TYPE_TWITTER);
		//socialVo.setSocialId(String.valueOf(user.getId()));
		//socialVo.setNickName(user.getName());
		//socialVo.setProfileImage(user.getBiggerProfileImageURL());
		socialvo.setAccesstokenT(accesstoken);		
		
		HttpSession session = request.getSession();		
		session.setAttribute("socialInfo",socialvo);	
		
		return "injeinc/common/social/callback";		
	}
}
