package egovframework.injeinc.common.social.kakao.controller;

import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.social.kakao.api.Kakao;
import egovframework.injeinc.common.social.kakao.api.KakaoTalkProfile;
import egovframework.injeinc.common.social.kakao.api.MyStory;
import egovframework.injeinc.common.social.kakao.api.impl.KakaoTemplate;
import egovframework.injeinc.common.social.kakao.connect.KakaoConnectionFactory;
import egovframework.injeinc.common.social.vo.SocialVo;

@Controller
public class KakaoCtr extends CmmLogCtr {
	
	static final String KAKAO_REST_API = EgovProperties.getProperty("kakao.restapi.key");
	static final String KAKAO_ADMIN_KEY = EgovProperties.getProperty("kakao.admin.key");
	static final String KAKAO_REDIRECT_URL = EgovProperties.getProperty("kakao.redirect.uri");
	
	@Inject
	private ConnectionRepository connectionRepository;
	
	@RequestMapping(value="/auth/kakao")
	public String auth(HttpServletRequest request,
								ModelMap model) throws Exception{
		
		KakaoConnectionFactory connectionFactory = new KakaoConnectionFactory(KAKAO_REST_API);
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri(KAKAO_REDIRECT_URL);
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
		
		return "redirect:" + authorizeUrl;
	}

	@RequestMapping(value="/common/social/kakao/callback")
	public String kakao(String code, HttpServletRequest request,
			ModelMap model) throws Exception{
		
		KakaoConnectionFactory connectionFactory = new KakaoConnectionFactory(KAKAO_REST_API);
		OAuth2Operations oauthOperation = connectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperation.exchangeForAccess(code, KAKAO_REDIRECT_URL, null);
		Long expireTime = accessGrant.getExpireTime();

		String accessToken = accessGrant.getAccessToken();
		String refreshToken = accessGrant.getRefreshToken();
		
		debugLog("#########accessToken="+ accessToken);
		debugLog("#########refreshToken="+ refreshToken);

		//if (expireTime != null && expireTime < System.currentTimeMillis()) {
		//	accessGrant = oauthOperation.refreshAccess(refreshToken, null);
		//	accessToken = accessGrant.getAccessToken();
		//	refreshToken = accessGrant.getRefreshToken();
		//	debugLog("accessToken is expired. refresh token = {"+accessToken+"}");
		//}
		
		
		
		
		//Kakao kakao = new KakaoTemplate(accessToken, EgovProperties.getProperty("kakao.admin.key"));
		//egovframework.injeinc.common.social.kakao.api.UserOperation userOperation;
		//userOperation = kakao.userOperation();
		//KakaoTalkProfile kakaoProfile = kakao.talkOperation().getUserProfile();
		SocialVo socialVo = new SocialVo();
		socialVo.setSocialType(SocialVo.SOCIAL_TYPE_KAKAO);
		//socialVo.setSocialId(String.valueOf(userOperation.getProfileId()));
		//socialVo.setNickName(kakaoProfile.getNickName());
		//socialVo.setProfileImage(kakaoProfile.getProfileImageURL());
		socialVo.setAccessToken(accessToken);
		socialVo.setRefreshToken(refreshToken);		
		HttpSession session = request.getSession();		
		session.setAttribute("socialInfo", socialVo);
		
//		Kakao kakao = new KakaoTemplate(accessToken, ADMIN_KEY);
//
//		List<MyStory> myStories = kakao.storyOperation().myStories(null);
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			debugLog(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(myStories));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//글쓰기
//		StoryNotePosting storyNotePosting = new StoryNotePosting();
//		StringBuilder sbNoteContent = new StringBuilder("Kakao rest api library 개발 테스트(spring social kakao).\r\n")
//			.append("\r\n테스트 시간 : ").append(DateUtil.getCurrentDate());
//		storyNotePosting.setContent(sbNoteContent.toString());
//		
//		StoryPostingResult notePostingResult = kakao.storyOperation().postNote(storyNotePosting);
//		debugLog(String.format("** posting article id : %s", notePostingResult.getId()));
		
		return "injeinc/common/social/callback";
	}
	
	/** 카카오스토리 테스트 */
	@RequestMapping(value="/common/kakao/kakao2.do")
	public String kakao2(String code, HttpServletRequest request,
			ModelMap model) throws Exception{
		Kakao kakao = new KakaoTemplate("MLdJg4LbcDxpsKxYd8Xb2oeWaGFc2LKoWFG3qKwQQI4AAAFWEHDqvw", KAKAO_ADMIN_KEY);
		List<MyStory> myStories = kakao.storyOperation().myStories(null);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			debugLog(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(myStories));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "injeinc/common/sns/kakao/kakao";
	}
}
