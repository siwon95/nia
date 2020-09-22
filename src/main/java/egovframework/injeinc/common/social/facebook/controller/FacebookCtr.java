package egovframework.injeinc.common.social.facebook.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.social.vo.SocialVo;

@Controller
public class FacebookCtr extends CmmLogCtr {
	
	/**
	 * property 에서 불러 오는걸로 변경
	 */
	private final String APP_API_KEY = EgovProperties.getProperty("facebook.app.id");
	private final String APP_SECRET_KEY = EgovProperties.getProperty("facebook.app.secret");
	private final String FACEBOOK_REDIRECT_URL = EgovProperties.getProperty("facebook.rediretc.url");
	private String GRAPH_API_URL = "http://graph.facebook.com/";
	private Facebook facebook;
	
	
	/*public FacebookClinet(String accessToken) {
		System.out.println("=======================FacebookClinet instance============================");
		if(accessToken != null && !accessToken.equals("")) {
			this.getFaceBookClientInstance(accessToken);
		} else {
			System.out.println("This class is no Instance Valiable.....  Plaze check access token...");
		}
	}*/
	
	public void getFaceBookClientInstance(String accessToken) {
		try {
			facebook = new FacebookTemplate(accessToken);
		} catch (Exception e) {
			System.out.println("facebook Instance Error ,, please again from the beginning OAuth certification...");
		}
	}
	
	public String facebookSignin() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@RequestMapping(value="/auth/facebook")
	public String facebookSignin(HttpServletResponse response) throws Exception {
		
		FacebookConnectionFactory fcf = new FacebookConnectionFactory(APP_API_KEY, APP_SECRET_KEY);
		OAuth2Operations oauthOperations = fcf.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri(FACEBOOK_REDIRECT_URL);
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
		
		return "redirect:" + authorizeUrl;				
	}

	
	@RequestMapping(value="/common/social/facebook/callback")
	public String getFacebookClientAccessToken(String code, HttpServletRequest request,
			ModelMap model) throws Exception {
		
		FacebookConnectionFactory fcf = new FacebookConnectionFactory(APP_API_KEY, APP_SECRET_KEY);
		OAuth2Operations oauthOperation = fcf.getOAuthOperations();
		AccessGrant accessGrant = oauthOperation.exchangeForAccess(code, FACEBOOK_REDIRECT_URL, null);
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
		
	    //Facebook facebook = new FacebookTemplate(accessToken);
		//User user = facebook.userOperations().getUserProfile();

		//String imgurl = fetchPictureUrl(user.getId(),ImageType.SQUARE);
	
		SocialVo socialVo = new SocialVo();
		socialVo.setSocialType(SocialVo.SOCIAL_TYPE_FACEBOOK);
		//socialVo.setSocialId(user.getId());
		//socialVo.setNickName(user.getName());
		//socialVo.setProfileImage(imgurl);
		socialVo.setAccessToken(accessToken);
		socialVo.setRefreshToken(refreshToken);
		
		HttpSession session = request.getSession();		
		session.setAttribute("socialInfo",socialVo);

	    // 페이스북 템플릿을 사용하기 위한 인스턴스 생성 Spring Social  
	    // this.getFaceBookClientInstance(accesstoken);
	    
	    // 글등록
	    // this.ischoolAppfirstPosting(accesstoken , response);
	    // return "redirect"+url;
	    
	    return "injeinc/common/social/callback";
	}
	
	
	
	public String fetchPictureUrl(String userId, ImageType imageType) {
		RestTemplate restTemplate = new RestTemplate();
	    URI uri = URIBuilder.fromUri(GRAPH_API_URL + userId + "/picture" +
	            "?type=" + imageType.toString().toLowerCase() + "&redirect=false").build();

	    JsonNode response = restTemplate.getForObject(uri, JsonNode.class);
	    return response.get("data").get("url").textValue();
	}

	public void storeAccessToken(HttpServletRequest request,String accessToken,String refreshToken) throws Exception {
		// TODO Auto-generated method stub		
		request.getSession().setAttribute("socialType", "facebook");
		request.getSession().setAttribute("accessToken", accessToken);
		request.getSession().setAttribute("refreshToken", refreshToken);
		System.out.println("storeAccessToken :: " + accessToken);
	}
	
	@RequestMapping(value = "/social/facebook/posting")
	public void posting(String message , String accessToken, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(accessToken == null){
			
			accessToken = (String) request.getSession().getAttribute("SOCIAL_FACEBOOK_ACCESS_TOKEN");
			
			if (accessToken == null){
				
				//throw new Exception("");
				this.facebookSignin(response);
				System.out.println("movement this.facebookSignin method....");
				
			} 
			
		}
		
		
		Facebook facebook = new FacebookTemplate(accessToken);
		FacebookLink link = new FacebookLink("http://www.springsource.org/spring-social", 
		        "Spring Social", 
		        "The Spring Social Project", 
		        "Spring Social is an extension to Spring to enable applications to connect with service providers.");
		//facebook.feedOperations().updateStatus("Spring Social is an extension to Spring to enable applications to connect with service providers");
		//facebook.feedOperations().updateStatus("App delete Test, API문서랑 달라요, 포스팅 메소드에 인자하나가 비어 있는데요. 어떻게 된거죠  http://static.springsource.org/spring-social-facebook/docs/1.0.x/reference/html/apis.html#facebook-status");
		//facebook.feedOperations().postLink("post link", link);
		facebook.feedOperations().postLink("I'm trying out Spring Social!", link);
	}

	public void ischoolAppfirstPosting(String accessToken, HttpServletResponse response) throws Exception {
		
		Facebook facebook = new FacebookTemplate(accessToken);
		User user = facebook.userOperations().getUserProfile();
		String ssss= facebook.getBaseGraphApiUrl();
		String myid = user.getId();
		String myname = user.getName();
		String img_url = user.getLink();
		
		System.out.println("my id : "+myid);
		System.out.println("ssss  : "+ssss);
		System.out.println("myname : "+myname);
		System.out.println("imgurl : "+img_url);
		
		FacebookLink link = new FacebookLink("http://ischooldev.sigongmedia.com/sbms/user/editor/EditorPage.do", 
		        "Spring Social", 
		        "The Spring Social Project", 
		        "Spring Social is an extension to Spring to enable applications to connect with service providers.");
		facebook.feedOperations().postLink(myname + "님께서 ischool App을 사용 중입니다.", link);
		
		
		//TODO :: resonse 에 javascript 삽입하여 창 닫기 .
		
	}
	
	public void posting(String message, FacebookLink link) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void posting(String message) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@RequestMapping(value = "/common/facebook/facebook.do")
	public String facebook(HttpServletRequest request, ModelMap model)
			throws Exception {

		// Connection<Facebook> connection =
		// connectionRepository.findPrimaryConnection(Facebook.class);
		// Facebook facebook = connection != null ? connection.getApi() : null;
		Facebook facebook = new FacebookTemplate(
				"EAAMmeD2YUBkBAFMaZCWIkg0LOHDMIPj2dQDx1PDyYgTEnYGtjiAuFYc5ArhmkzZAamiInxvMiLDGKUCp2TqUkQ05EwxrVIAtZAiZB3DJC9lScS9p2D6qk665KqZAaGZAXs5fU8cCuktvACx1cZBh8MncNgIAQyXSpFDPLDjkk7AaQZDZD");
		User profile = facebook.userOperations().getUserProfile();

		String myId = profile.getId();
		String myName = profile.getName();
		String myEmail = profile.getEmail();
		String myGender = profile.getGender();
		
		debugLog(myId);
		debugLog(myName);
		debugLog(myEmail);
		debugLog(myGender);
		
		List<String> friends = facebook.friendOperations().getFriendIds();
		for (String friend : friends) {
			debugLog(friend);
		}
		
		return "injeinc/common/social/facebook/facebook";
	}

}
