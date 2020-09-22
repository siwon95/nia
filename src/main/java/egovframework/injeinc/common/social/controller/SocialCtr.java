package egovframework.injeinc.common.social.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javapns.Push;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jinstagram.Instagram;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.jinstagram.exceptions.InstagramException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.injeinc.common.social.kakao.api.Kakao;
import egovframework.injeinc.common.social.kakao.api.KakaoTalkProfile;
import egovframework.injeinc.common.social.kakao.api.impl.KakaoTemplate;
import egovframework.injeinc.common.social.naver.api.Naver;
import egovframework.injeinc.common.social.naver.api.NaverOAuth2ApiBinding;
import egovframework.injeinc.common.social.naver.api.abstracts.UserOperation;
import egovframework.injeinc.common.social.service.SocialSvc;
import egovframework.injeinc.common.social.vo.PushVo;
import egovframework.injeinc.common.social.vo.SocialVo;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Controller
public class SocialCtr extends CmmLogCtr {
	
	
	@Resource(name = "SocialSvc")
	private SocialSvc socialsvc;
	
	@Resource(name = "SocialCommentIdGnrService")
	private EgovIdGnrService socialCommentIdGnrService;
	
	@Resource(name = "pushIdGnrService")
	 private EgovIdGnrService pushIdGnrService;
	 
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping(value="/common/social/index")
	public String index(HttpServletRequest request,
								ModelMap model) throws Exception{
		
		return "injeinc/common/social/board";
	}

	@RequestMapping(value="/common/social/loginResult")
	public void loginResult(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception{
		
		HttpSession session = request.getSession();
		SocialVo socialVo = (SocialVo) session.getAttribute("socialInfo");		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("resultVo", socialVo);
		jsonView.render(jsonMap, request, response);
	}		
	
	@RequestMapping(value="/common/social/comment")
	public void Comment(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("SocialVo") SocialVo socialVo) throws Exception{		
		HttpSession session = request.getSession();
		String mode        = socialVo.getMode();				
		int pageIndex      = socialVo.getPageIndex();
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		SocialVo sessionVo = null;
		
		if(mode.equals("LO")){
			session.removeAttribute("socialInfo");			
		}else{
			sessionVo = (SocialVo) session.getAttribute("socialInfo");
		}
		
		
		SocialVo getInfo = getSocialInfo(sessionVo);

		if(mode != null && mode.equals("C")){
			if(getInfo != null && getInfo.getSocialId() != null && !socialVo.getCommentCont().equals("")){
				String scidx = socialCommentIdGnrService.getNextStringId();
				String commentCont = socialVo.getCommentCont();

				socialVo.setScidx(scidx);
				socialVo.setNickName(getInfo.getNickName());
				socialVo.setSocialId(getInfo.getSocialId());
				socialVo.setProfileImage(getInfo.getProfileImage());
				socialVo.setCommentCont(commentCont);
		
				socialsvc.registUserComment(socialVo);
			}					
		}else if(mode != null && mode.equals("D")){
			if(getInfo != null && getInfo.getSocialId() != null){
				socialsvc.deleteUserComment(socialVo);		
				
			}				
		}
				
		if(getInfo != null){
			jsonMap.put("socialtype", getInfo.getSocialType());
			jsonMap.put("loginsid", getInfo.getSocialId());
		}else{
			jsonMap.put("socialtype", "");
			jsonMap.put("loginsid", "");
		}
		socialVo.setFirstIndex(0);
		socialVo.setLastIndex(5*pageIndex);
		List<SocialVo> sociallist = socialsvc.getCommentList(socialVo);	
		int totcnt = socialsvc.getCommetnListCnt(socialVo);
		
		if(getInfo != null && getInfo.getSocialId() != null && (mode.equals("C") || mode.equals("D"))){
			jsonMap.put("errorMsg", "ok");
		}else if(mode.equals("R") || mode.equals("LO") || mode.equals("M")){
			jsonMap.put("errorMsg", "ok");
		}else{
			jsonMap.put("errorMsg", "not login");
		}
		

		if((socialVo.getCommentCont() == null  || socialVo.getCommentCont().equals("")) && (mode.equals("C")) && jsonMap.get("errorMsg").equals("ok")){
			jsonMap.put("errorMsg", "no comment");
		
		}
		
		
		jsonMap.put("pageIndex", pageIndex);
		jsonMap.put("pageIndex", pageIndex);
		jsonMap.put("totcnt", totcnt);
		jsonMap.put("resultListVo", sociallist);
		jsonView.render(jsonMap, request, response);
	}
	
	public SocialVo getSocialInfo(SocialVo socialvo)throws Exception{		
		if(socialvo != null && socialvo.getAccessToken() != null){
			if(socialvo.getSocialType().equals(SocialVo.SOCIAL_TYPE_FACEBOOK)){
				Facebook facebook = new FacebookTemplate(socialvo.getAccessToken());
				org.springframework.social.facebook.api.User user = facebook.userOperations().getUserProfile();
				String imgurl = fetchPictureUrl(user.getId(),ImageType.SQUARE);				
				socialvo.setSocialId(user.getId());
				socialvo.setNickName(user.getName());
				socialvo.setProfileImage(imgurl);
			}else if(socialvo.getSocialType().equals(SocialVo.SOCIAL_TYPE_KAKAO)){
				Kakao kakao = new KakaoTemplate(socialvo.getAccessToken(), EgovProperties.getProperty("kakao.admin.key"));
				egovframework.injeinc.common.social.kakao.api.UserOperation userOperation;
				userOperation = kakao.userOperation();
				KakaoTalkProfile kakaoProfile = kakao.talkOperation().getUserProfile();								
				socialvo.setSocialId(String.valueOf(userOperation.getProfileId()));
				socialvo.setNickName(kakaoProfile.getNickName());
				socialvo.setProfileImage(kakaoProfile.getProfileImageURL());
			}else if(socialvo.getSocialType().equals(SocialVo.SOCIAL_TYPE_NAVER)){
				Naver naver = new NaverOAuth2ApiBinding(socialvo.getAccessToken());
				UserOperation userOperation = naver.userOperation();								
				socialvo.setSocialId(userOperation.getId());
				socialvo.setNickName(userOperation.getNickname());
				socialvo.setProfileImage(userOperation.getProfile_image());
			}
		}		
		
		if(socialvo != null && socialvo.getAccesstokenT() != null){
			if(socialvo.getSocialType().equals(SocialVo.SOCIAL_TYPE_TWITTER)){
				Twitter twitter = new TwitterFactory().getInstance();
				twitter.setOAuthConsumer(EgovProperties.getProperty("twitter.consumer.key"), EgovProperties.getProperty("twitter.consumer.secret"));
				twitter.setOAuthAccessToken(socialvo.getAccesstokenT());
				User user = twitter.verifyCredentials();								
				socialvo.setSocialId(String.valueOf(user.getId()));
				socialvo.setNickName(user.getName());
				socialvo.setProfileImage(user.getBiggerProfileImageURL());
			}			
		}
		return socialvo;
	}
	
	public String fetchPictureUrl(String userId, ImageType imageType) {
		RestTemplate restTemplate = new RestTemplate();
	    URI uri = URIBuilder.fromUri("http://graph.facebook.com/" + userId + "/picture" +
	            "?type=" + imageType.toString().toLowerCase() + "&redirect=false").build();

	    JsonNode response = restTemplate.getForObject(uri, JsonNode.class);
	    return response.get("data").get("url").textValue();
	}
	
	static final String KAKAO_REST_API = EgovProperties.getProperty("kakao.restapi.key");
	static final String KAKAO_ADMIN_KEY = EgovProperties.getProperty("kakao.admin.key");
	
	static final String FACEBOOK_APP_TOKEN = EgovProperties.getProperty("facebook.app.token");
	static final String FACEBOOK_PAGE_ID = EgovProperties.getProperty("facebook.page.id");
	static final String FACEBOOK_MAYOR_APP_TOKEN = EgovProperties.getProperty("facebook.mayor.app.token");
	
	@RequestMapping(value="/common/social/postlist.do")
	public String postlist(HttpServletRequest request,
								ModelMap model) throws Exception{
		
		// 시작 시간
        long startTime = System.currentTimeMillis();
        
        
//		System.out.println("==============================페이스북");
//		/** facebook postlist */
//		Facebook facebook = new FacebookTemplate(FACEBOOK_APP_TOKEN);
//		PagingParameters pagedListParameters = new PagingParameters(5, null, null, null);
//		PagedList<Post> postList = facebook.feedOperations().getPosts(FACEBOOK_PAGE_ID, pagedListParameters);
//		System.out.println("##"+postList.size());
//		for (Post post : postList) {
//			System.out.println("####"+post.getMessage());
//		}

		System.out.println("==============================페이스북");
		/** facebook postlist */
		Facebook facebook = new FacebookTemplate("EAARncosyG7oBAOxdaaIYkezsTCcLN4yPcsFKfFZB6z4FiajODTpaEsnuNn9UYU4kIYeuLXsLLWw6T3SZC0FwO8ZAfV3WsyggJavQ5CZAxDP8FrYJVH2eZCA4bR4a1wZCWjhN05ndF3U9F3hJ4Aq7LG4a20zhBk5F8ZD");
		PagingParameters pagedListParameters = new PagingParameters(50, null, null, null);
		PagedList<Post> postList = facebook.feedOperations().getPosts("me", pagedListParameters);
		System.out.println("##"+postList.size());
		for (Post post : postList) {
			System.out.println("####"+post.getMessage());
		}
		
		// 종료 시간
        long endTime = System.currentTimeMillis();
        // 시간 출력
        System.out.println("##  시작시간 : " + new SocialCtr().formatTime(startTime));
        System.out.println("##  종료시간 : " + new SocialCtr().formatTime(endTime));
        System.out.println("##  소요시간(초.0f) : " + ( endTime - startTime )/1000.0f +"초");
		return "injeinc/common/social/postlist";
	}
	
	private void doMakeKakaoXML(HttpServletRequest request, String accessToken, String refreshToken, String expireTime) throws Exception {
		
		String filePath = "/WEB-INF/config/egovframework/social/";
		String fileName = "kakao.xml";
		
		String strContent="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		strContent+="<kakao>\n";
		strContent+="<accessToken>"+accessToken+"</accessToken>\n";
		strContent+="<refreshToken>"+refreshToken+"</refreshToken>\n";
		strContent+="<expireTime>"+expireTime+"</expireTime>\n";
		strContent+="</kakao>\n";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		Util util = new Util();
		
		String fileChk = util.fileWriteRootPath(request, filePath, fileName, strContent, rootPath);
		
	}
	
	private HashMap<String, String> buildKakaoXML() throws  XPathExpressionException, SAXException, IOException, ParserConfigurationException{
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
    	String filePath = "/WEB-INF/config/egovframework/social/";
		String fileName = "kakao.xml";
		
		// XML Document 객체 생성
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(EgovWebUtil.filePathBlackList(rootPath)+ filePath + fileName);
		
		// xpath 생성
        XPath xpath = XPathFactory.newInstance().newXPath();
        
        HashMap<String, String> result = new HashMap<String, String>();

        NodeList accessToken = (NodeList)xpath.evaluate("//kakao/accessToken", document, XPathConstants.NODESET);
		result.put("accessToken", accessToken.item(0).getTextContent());

		NodeList refreshToken = (NodeList)xpath.evaluate("//kakao/refreshToken", document, XPathConstants.NODESET);
		result.put("refreshToken", refreshToken.item(0).getTextContent());
		
		NodeList expireTime = (NodeList)xpath.evaluate("//kakao/expireTime", document, XPathConstants.NODESET);
		result.put("expireTime", expireTime.item(0).getTextContent());

		return result;
	}
	
	public String formatTime(long lTime) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(lTime);
        return (c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 " + c.get(Calendar.SECOND) + "." + c.get(Calendar.MILLISECOND) + "초");
    } 
	
	//youtube
	private static String getInputQuery() throws IOException {

        String inputQuery = "";

        System.out.print("Please enter a search term: ");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        inputQuery = bReader.readLine();

        if (inputQuery.length() < 1) {
            // Use the string "YouTube Developers Live" as a default.
            inputQuery = "YouTube Developers Live";
        }
        return inputQuery;
    }
	
	private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + 25 + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                System.out.println(rId.getVideoId() + " youtube " + singleVideo.getSnippet().getPublishedAt() + " " + singleVideo.getSnippet().getDescription() + " https://www.youtube.com/watch?v=" +  rId.getVideoId() + " "+ thumbnail.getUrl());
                System.out.println(" Video Id = https://www.youtube.com/watch?v=" +  rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }
	// cms.injeinc.go.kr/common/social/instagram.do
	@RequestMapping(value="/common/social/instagram.do")
	public String instagram(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception{
		
		InstagramService service = new InstagramAuthService()
        .apiKey("") // your_client_id
        .apiSecret("") // your_client_secret
        .callback("http://cms.injeinc.go.kr/common/social/instagram_callback.do") // your_callback_url
        .scope("public_content")
        .build();
		
		
		
		String authorizationUrl = service.getAuthorizationUrl();
		
		return "redirect:" + authorizationUrl;
	}
		
		@RequestMapping(value="/common/social/instagram_callback.do")
		public void instagram_callback(HttpServletRequest request,
				String code,
				HttpServletResponse response,
				ModelMap model) throws Exception{
			
			InstagramService service = new InstagramAuthService()
	        .apiKey("") // your_client_id
	        .apiSecret("") // your_client_secret
	        .callback("http://cms.injeinc.go.kr/common/social/instagram_callback.do") // your_callback_url
	        .scope("public_content")
	        .build();
			
			Verifier verifier = new Verifier(code);
			Token accessToken = service.getAccessToken(verifier);
			
			System.out.println("############accessToken=" + accessToken.getToken());
			System.out.println("############getSecret=" + accessToken.getSecret());
		}
		
	@RequestMapping(value="/common/social/instagram_test.do")
	public void instagram_test(HttpServletRequest request,
			String code,
			HttpServletResponse response,
			ModelMap model) throws Exception{
		
		Instagram instagram = new Instagram("530689886.c5f8d0e.d575dfdd0fc54b3689f7020b9072eade", null, "127.0.0.1");
		
		UserInfo userInfo = null;
		try {
			userInfo = instagram.getUserInfo("jqqaaa1092");
		} catch (InstagramException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserInfoData userData = userInfo.getData();
		System.out.println("id : " + userData.getId());
		System.out.println("first_name : " + userData.getFirstName());
		System.out.println("last_name : " + userData.getLastName());
		System.out.println("profile_picture : " + userData.getProfilePicture());
		System.out.println("website : " + userData.getWebsite());
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/common/push/recivePushInfo.do")
	public String PollListI(HttpServletRequest request, @ModelAttribute("PushVo") PushVo pushVo, ModelMap model) throws Exception {

		System.out.println("NEW : "+pushVo.getNewRegistkey());
		System.out.println("OLD : "+pushVo.getOldRegistkey());
		System.out.println("OS : "+pushVo.getOsType());
		if(pushVo.getOldRegistkey() != null && !pushVo.getOldRegistkey().equals("")){
			pushVo.setPiRegistrationId(pushVo.getOldRegistkey());
			socialsvc.deletePushInfo(pushVo);
		}
		if((pushVo.getNewRegistkey() != null && !pushVo.getNewRegistkey().equals(""))&&(pushVo.getOsType() != null && !pushVo.getOsType().equals(""))){
			String piIdx = pushIdGnrService.getNextStringId();			
			pushVo.setPiIdx(piIdx);
			pushVo.setPiRegistrationId(pushVo.getNewRegistkey());
			pushVo.setPiGubun(pushVo.getOsType());
			socialsvc.createUserPushInfo(pushVo);
		}				
		return "redirect:/site/injeinc/main.do";
	}	
	
}
