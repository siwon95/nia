package egovframework.injeinc.common.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jinstagram.Instagram;
import org.jinstagram.entity.users.feed.UserFeed;
import org.jinstagram.entity.users.feed.UserFeedData;
import org.jinstagram.exceptions.InstagramException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import twitter4j.FilterQuery;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.sns.dao.SnsDao;
import egovframework.injeinc.boffice.ex.sns.vo.SnsCollectVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsSearchKeywordVo;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

@Service("SnsRecentlyCollectScheduling")
public class SnsRecentlyCollectScheduling extends CmmLogCtr {
	
	@Resource(name = "SnsDao")
    private SnsDao snsDao;

	/** 최근 sns 데이터 가져오기 */
	public void SnsRecentlyCollectMethod() throws Exception {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
		debugLog("========== 최근 sns 데이터 가져오기(" + currentDate + ") ========== ");
		
		String siteDomain = "";
		
		/* 특정 사용자의 SNS가져오기 */
		List<SnsSearchKeywordVo> searchKeyForUserList = snsDao.selectSearchKeywordListForUser();
		for (SnsSearchKeywordVo searchKeywordVo : searchKeyForUserList) {
			if("youtube".equals(searchKeywordVo.getSnsType()) || "all".equals(searchKeywordVo.getSnsType())){
				List<SnsCollectVo> channelYoutubeList = collectChannelYoutube(searchKeywordVo.getKeyword());
				snsDao.insertSnsCollect(channelYoutubeList, searchKeywordVo.getIsValid(),siteDomain);
			}
			
			if("twitter".equals(searchKeywordVo.getSnsType()) || "all".equals(searchKeywordVo.getSnsType())){
				List<SnsCollectVo> userTwitterList = collectUserTwitter(searchKeywordVo.getKeyword());
				snsDao.insertSnsCollect(userTwitterList, searchKeywordVo.getIsValid(),siteDomain);
			}
		}
		
		/* 검색 키워드의 SNS가져오기 */
		List<SnsSearchKeywordVo> searchKeyForContentsList = snsDao.selectSearchKeywordListForContents();
		for (SnsSearchKeywordVo searchKeywordVo : searchKeyForContentsList) {
			if("youtube".equals(searchKeywordVo.getSnsType()) || "all".equals(searchKeywordVo.getSnsType())){
				// youtube 검색어로 동영상정보 가져오기
				List<SnsCollectVo> publicTwitterList = collectPublicTwitter(searchKeywordVo.getKeyword());
				snsDao.insertSnsCollect(publicTwitterList, searchKeywordVo.getIsValid(),siteDomain);
			}
			
			if("twitter".equals(searchKeywordVo.getSnsType()) || "all".equals(searchKeywordVo.getSnsType())){
				// twitter 검색어로 트윗정보 가져오기
				List<SnsCollectVo> publicYoutubeList = collectPublicYoutube(searchKeywordVo.getKeyword());
				snsDao.insertSnsCollect(publicYoutubeList, searchKeywordVo.getIsValid(),siteDomain);
			}
//			List<SnsCollectVo> publicInstagramList = collectPublicInstagram(keyword);
		}
		
		/* 금지어 업데이트*/
		snsDao.updateSnsProhibitWord();
		/* 해시태그가 컨텐츠에 포함된 경우 업데이트*/
		snsDao.updateSnsContentsHashtag();
		/* 해시태그가 달린 SNS 수 업데이트 */
		snsDao.updateSnsHashtagForSnsCnt();
		
		siteDomain = "mayor";
		List<SnsCollectVo> facebookPostList = collectFacebookPost(siteDomain);
		snsDao.insertSnsCollect(facebookPostList, "N",siteDomain);
		
		List<SnsCollectVo> naverBlogPostList = collectNaverBlogList(siteDomain);
		snsDao.insertSnsCollect(naverBlogPostList, "N",siteDomain);
//		collectOwnTwitter();
//		collectOwnFacebook();
//		collectOwnKakaoStory();
	
	}

	private List<SnsCollectVo> collectNaverBlogList(String siteDomain) throws ParseException {
		
		List<SnsCollectVo> resultList = new ArrayList<SnsCollectVo>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;
		NodeList nodeList;
		
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse("http://blog.rss.naver.com/kimsy2014.xml");
			
			XPath xpath = XPathFactory.newInstance().newXPath();
//			String expression = "//rss/channel/item/title";
//			System.out.println(expression);
//			nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
//			for (int i = 0; i < nodeList.getLength(); i++) {
//			    System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
//			}
			
			int itemCnt = doc.getElementsByTagName("item").getLength();
			for (int i = 0; i < itemCnt; i++) {
				
				SnsCollectVo snsCollectVo = new SnsCollectVo();
				
	            snsCollectVo.setSnsType("blog");
	            snsCollectVo.setSnsRegid("kimsy2014");
	            snsCollectVo.setSnsRegnm("김수영");
	            snsCollectVo.setSnsProfileimg("http://blogpfthumb.phinf.naver.net/20140805_46/kimsy2014_1407196556779JaFkc_JPEG/%C5%A9%B1%E2%BA%AF%C8%AF_%BC%AD%BF%EF%BE%E7%C3%B5%B1%B8_%B1%E8%BC%F6%BF%B5%B1%B8%C3%BB%C0%E5%B4%D4_%C4%B3%B8%AE%C4%BF%C3%B3.jpg?type=m2");
	            
				String expression = "//rss/channel/item["+(i+1)+"]/guid";
				System.out.println(expression);
				nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
				for (int j = 0; j < nodeList.getLength(); j++) {
					snsCollectVo.setSnsIdx(nodeList.item(j).getFirstChild().getNodeValue()); 
				}

//				expression = "//rss/channel/item["+(i+1)+"]/author";
//				System.out.println(expression);
//				nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
//				for (int j = 0; j < nodeList.getLength(); j++) {
//					System.out.println(nodeList.item(j).getFirstChild().getNodeValue()); 
//				}
				
				expression = "//rss/channel/item["+(i+1)+"]/pubDate";
				System.out.println(expression);
				nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
				for (int j = 0; j < nodeList.getLength(); j++) {
					
					Date time = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss +0900", Locale.ENGLISH).parse(nodeList.item(j).getFirstChild().getNodeValue());
					String rssFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(time);
					snsCollectVo.setSnsRegdt(rssFormat); 
				}

				expression = "//rss/channel/item["+(i+1)+"]/link";
				System.out.println(expression);
				nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
				for (int j = 0; j < nodeList.getLength(); j++) {
					snsCollectVo.setSnsLinkurl(nodeList.item(j).getFirstChild().getNodeValue()); 
				}

				expression = "//rss/channel/item["+(i+1)+"]/description";
				System.out.println(expression);
				nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
				for (int j = 0; j < nodeList.getLength(); j++) {
					snsCollectVo.setSnsDesc(nodeList.item(j).getFirstChild().getNodeValue()); 
				}
				
				resultList.add(snsCollectVo);
				
			}
			
			
			
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	private List<SnsCollectVo> collectFacebookPost(String siteDomain) {
		
		List<SnsCollectVo> resultList = new ArrayList<SnsCollectVo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		/** facebook postlist */
		Facebook facebook = new FacebookTemplate("EAARncosyG7oBAOxdaaIYkezsTCcLN4yPcsFKfFZB6z4FiajODTpaEsnuNn9UYU4kIYeuLXsLLWw6T3SZC0FwO8ZAfV3WsyggJavQ5CZAxDP8FrYJVH2eZCA4bR4a1wZCWjhN05ndF3U9F3hJ4Aq7LG4a20zhBk5F8ZD");
		PagingParameters pagedListParameters = new PagingParameters(100, null, null, null);
		PagedList<Post> postList = facebook.feedOperations().getPosts("me", pagedListParameters);
		System.out.println("##"+postList.size());
		for (Post post : postList) {
			//System.out.println("####"+post.getMessage());
			
			SnsCollectVo snsCollectVo = new SnsCollectVo();
            snsCollectVo.setSnsIdx(post.getId());
            snsCollectVo.setSnsType("facebook");
            snsCollectVo.setSnsRegdt(String.valueOf(sdf.format(post.getCreatedTime())));
            snsCollectVo.setSnsRegid(post.getFrom().getId());
            snsCollectVo.setSnsRegnm(post.getFrom().getName());
            snsCollectVo.setSnsProfileimg("https://graph.facebook.com/"+post.getFrom().getId()+"/picture");
            snsCollectVo.setSnsDesc(post.getMessage());
            snsCollectVo.setSnsLinkurl("https://facebook.com/"+post.getId());
            snsCollectVo.setSnsThumnail(post.getPicture());
            resultList.add(snsCollectVo);
		}
		
		return resultList;
	}

	private List<SnsCollectVo> collectPublicInstagram(String keyword) {
//530689886.c5f8d0e.d575dfdd0fc54b3689f7020b9072eade 20160823 15:01
		Instagram instagram = new Instagram("530689886.c5f8d0e.d575dfdd0fc54b3689f7020b9072eade", null, "127.0.0.1");
		// aaa6c90ac40547a391e8ad15b1174144
		
		
//		String tagName = "행복해";
//		TagMediaFeed mediaFeed = null;
//		try {
//			mediaFeed = instagram.getRecentMediaTags(tagName);
//		} catch (InstagramException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		List<MediaFeedData> mediaFeeds = mediaFeed.getData();
//		System.out.println("mediaFeeds size=" + mediaFeeds.size());
//		for (MediaFeedData mediaData : mediaFeeds) {
//		    System.out.println("id : " + mediaData.getId());
//		    System.out.println("created time : " + mediaData.getCreatedTime());
//		    System.out.println("link : " + mediaData.getLink());
//		    System.out.println("tags : " + mediaData.getTags().toString());
//		    System.out.println("filter : " + mediaData.getImageFilter());
//		    System.out.println("type : " + mediaData.getType());
//
//		    System.out.println("-- Comments --");
//		    Comments comments = mediaData.getComments();
//
//		    System.out.println("-- Caption --");
//		    Caption caption = mediaData.getCaption();
//
//		    System.out.println("-- Likes --");
//		    Likes likes = mediaData.getLikes();
//
//		    System.out.println("-- Images --");
//		    Images images = mediaData.getImages();
//
//		    ImageData lowResolutionImg = images.getLowResolution();
////		    ImageData highResolutionImg = images.getHighResolution();
//		    ImageData thumbnailImg = images.getThumbnail();
//
//		    Location location = mediaData.getLocation();
//		    System.out.println();
//		}
//		
		
		
		/////////////////////////////////////////////////////////////
		
		
	    
	    String instagram_endpoint = "https://api.instagram.com/v1/users/jqqaaa1092/relationship?access_token=530689886.c5f8d0e.d575dfdd0fc54b3689f7020b9072eade";
		
		String jsonResponse = "";

		HttpClient client = new DefaultHttpClient();
		
		System.out.println("EndPoint: " + instagram_endpoint);
		
		HttpGet request = new HttpGet(instagram_endpoint);
		System.out.println("URI: " + request.getURI());
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuffer result = new StringBuffer();
		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		
		
		UserFeed feed = null;
		try {
			feed = instagram.getUserFollowedByList("jqqaaa1092");
		} catch (InstagramException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<UserFeedData> users = feed.getUserList();
		System.out.println(users.size());
		return null;
	}

	private List<SnsCollectVo> collectPublicYoutube(String keyword) {
		
		YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName(EgovProperties.getProperty("youtube.project.id")).build();
        
		String queryTerm = keyword;
		
        YouTube.Search.List search = null;
		
        try {
			search = youtube.search().list("id,snippet");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // https://console.developers.google.com/project/_/apiui/credential YOUR_SIMPLE_API_KEY_HERE
        String apiKey = EgovProperties.getProperty("youtube.server.api.key");
        search.setKey(apiKey);
        search.setQ(queryTerm);
        search.setType("video");
        
        search.setFields("items(id/kind,id/videoId,snippet/publishedAt,snippet/channelId,snippet/channelTitle,snippet/title,snippet/description,snippet/thumbnails/medium/url,snippet/thumbnails/medium/width,snippet/thumbnails/medium/height)");
        search.setMaxResults((long) 50);
        
        search.setOrder("date");
        //search.setEventType("completed");
        
        SearchListResponse searchResponse = null;
		try {
			searchResponse = search.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        List<SearchResult> searchResultList = searchResponse.getItems();
        List<SnsCollectVo> resultList = null;
        if (searchResultList != null) {
        	resultList= prettyYoutubePrint(searchResultList.iterator(), queryTerm, youtube);
        }
        
		return resultList;
	}

	private List<SnsCollectVo> collectChannelYoutube(String channelId) {
		System.out.println("##########youtube channelId="+channelId);
		YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName(EgovProperties.getProperty("youtube.project.id")).build();
        
		
        YouTube.Search.List search = null;
		
        try {
			search = youtube.search().list("id,snippet");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // https://console.developers.google.com/project/_/apiui/credential YOUR_SIMPLE_API_KEY_HERE
        String apiKey = EgovProperties.getProperty("youtube.server.api.key");
        search.setKey(apiKey);
        //search.setQ(queryTerm);
        search.setType("video");
        search.setChannelId(channelId);
        search.setFields("items(id/kind,id/videoId,snippet/publishedAt,snippet/channelId,snippet/channelTitle,snippet/title,snippet/description,snippet/thumbnails/medium/url,snippet/thumbnails/medium/width,snippet/thumbnails/medium/height)");
        search.setMaxResults((long) 50);
        
        search.setOrder("date");
        //search.setEventType("completed");
        
        SearchListResponse searchResponse = null;
		try {
			searchResponse = search.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        List<SearchResult> searchResultList = searchResponse.getItems();
        List<SnsCollectVo> resultList = null;
        if (searchResultList != null) {
        	resultList= prettyYoutubePrint(searchResultList.iterator(), channelId, youtube);
        }
        
		return resultList;
	}

	private List<SnsCollectVo> collectPublicTwitter(String keyword) {
		
		List<SnsCollectVo> resultList = new ArrayList<SnsCollectVo>();
		
		AccessToken accesstoken = new AccessToken("167199054-tZL9ClLI5yfospj4YCj1UO3HaUR9P49TrGAkNE1E", "FEuR6sSE8NJ0R0uTK7Y8v4FBFYV9FZtbPx8mFhhnac");
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(EgovProperties.getProperty("twitter.consumer.key"), EgovProperties.getProperty("twitter.consumer.secret"));
		twitter.setOAuthAccessToken(accesstoken);
		
		
		/*
		 * 
		 * https://twitter.com/search?f=tweets&vertical=default&q=test
		 * 
		 * \"파리공원\"
		 * */
		String searchKeyword = keyword;
		
		try {
			searchKeyword = URLEncoder.encode(searchKeyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Query query = new Query(searchKeyword);
		query.setLang("ko");
		query.setLocale("ko");
		query.setCount(100);
		query.setResultType(ResultType.recent);
		QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Status> list = result.getTweets();
		
		if (list == null || list.size() == 0) {
            System.out.println(" There aren't any results for your query. (twitter)");
        }
		
		for (Status status : list) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

			SnsCollectVo snsCollectVo = new SnsCollectVo();
            snsCollectVo.setSnsIdx(String.valueOf(status.getId()));
            snsCollectVo.setSnsType("twitter");
            snsCollectVo.setSnsRegdt(String.valueOf(sdf.format(status.getCreatedAt())));
            snsCollectVo.setSnsRegid(String.valueOf(status.getUser().getId()));
            snsCollectVo.setSnsRegnm(status.getUser().getScreenName());
            snsCollectVo.setSnsProfileimg(status.getUser().getProfileImageURL());
            snsCollectVo.setSnsDesc(status.getText());
            snsCollectVo.setSnsLinkurl("https://twitter.com/"+String.valueOf(status.getUser().getId())+"/status/" +  status.getId());
            snsCollectVo.setSnsKeyword(keyword);
            
            MediaEntity[] mediaEntities = status.getMediaEntities();
            if(mediaEntities != null && mediaEntities.length != 0){
            	snsCollectVo.setSnsThumnail(mediaEntities[0].getMediaURL());
            	snsCollectVo.setSnsTNHeight(mediaEntities[0].getSizes().get(1).getHeight());
            	snsCollectVo.setSnsTNWidth(mediaEntities[0].getSizes().get(1).getWidth());
            	System.out.println(" snsthumnail=" +snsCollectVo.getSnsThumnail());
            	System.out.println(" snsthumnail=" +mediaEntities[0].getSizes().get(1).getHeight());
            	System.out.println(" snsthumnail=" +mediaEntities[0].getSizes().get(1).getWidth());
            }
            
            resultList.add(snsCollectVo);
            
			
			System.out.println(" snsIdx=" +  status.getId());
            System.out.println(" snsType=twitter");
            System.out.println(" snsRegdt=" + String.valueOf(sdf.format(status.getCreatedAt())));
            System.out.println(" snsRegid=" + status.getUser().getId());
            System.out.println(" snsRegNm=" + status.getUser().getScreenName());
            System.out.println(" snsProfileimg=" + status.getUser().getProfileImageURL());
            System.out.println(" snsDesc=" + status.getText());
            System.out.println(" snsLinkurl=https://twitter.com/"+String.valueOf(status.getUser().getId())+"/status/" +  status.getId());
            System.out.println("\n-------------------------------------------------------------\n");
            
            
//            HashtagEntity[] hashtagEntities = status.getHashtagEntities();
            
            //System.out.println(" status=" + status.toString());
		}
		
		return resultList;
	}

	private List<SnsCollectVo> collectUserTwitter(String userid) {
		
		List<SnsCollectVo> resultList = new ArrayList<SnsCollectVo>();
		
		AccessToken accesstoken = new AccessToken("167199054-tZL9ClLI5yfospj4YCj1UO3HaUR9P49TrGAkNE1E", "FEuR6sSE8NJ0R0uTK7Y8v4FBFYV9FZtbPx8mFhhnac");
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(EgovProperties.getProperty("twitter.consumer.key"), EgovProperties.getProperty("twitter.consumer.secret"));
		twitter.setOAuthAccessToken(accesstoken);
		
		ResponseList<Status> result = null;
		try {
			result = twitter.getUserTimeline(userid);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (result == null || result.size() == 0) {
			System.out.println(" There aren't any results for your query. (twitter)");
		}
		
		for (Status status : result) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			
			SnsCollectVo snsCollectVo = new SnsCollectVo();
			snsCollectVo.setSnsIdx(String.valueOf(status.getId()));
			snsCollectVo.setSnsType("twitter");
			snsCollectVo.setSnsRegdt(String.valueOf(sdf.format(status.getCreatedAt())));
			snsCollectVo.setSnsRegid(String.valueOf(status.getUser().getId()));
			snsCollectVo.setSnsRegnm(status.getUser().getScreenName());
			snsCollectVo.setSnsProfileimg(status.getUser().getProfileImageURL());
			snsCollectVo.setSnsDesc(status.getText());
			snsCollectVo.setSnsLinkurl("https://twitter.com/"+String.valueOf(status.getUser().getId())+"/status/" +  status.getId());
//			snsCollectVo.setSnsKeyword(keyword);
			
			MediaEntity[] mediaEntities = status.getMediaEntities();
			if(mediaEntities != null && mediaEntities.length != 0){
				snsCollectVo.setSnsThumnail(mediaEntities[0].getMediaURL());
				snsCollectVo.setSnsTNHeight(mediaEntities[0].getSizes().get(1).getHeight());
				snsCollectVo.setSnsTNWidth(mediaEntities[0].getSizes().get(1).getWidth());
				System.out.println(" snsthumnail=" +snsCollectVo.getSnsThumnail());
				System.out.println(" snsthumnail=" +mediaEntities[0].getSizes().get(1).getHeight());
				System.out.println(" snsthumnail=" +mediaEntities[0].getSizes().get(1).getWidth());
			}
			
			resultList.add(snsCollectVo);
			
			
			System.out.println(" snsIdx=" +  status.getId());
			System.out.println(" snsType=twitter");
			System.out.println(" snsRegdt=" + String.valueOf(sdf.format(status.getCreatedAt())));
			System.out.println(" snsRegid=" + status.getUser().getId());
			System.out.println(" snsRegNm=" + status.getUser().getScreenName());
			System.out.println(" snsProfileimg=" + status.getUser().getProfileImageURL());
			System.out.println(" snsDesc=" + status.getText());
			System.out.println(" snsLinkurl=https://twitter.com/"+String.valueOf(status.getUser().getId())+"/status/" +  status.getId());
			System.out.println("\n-------------------------------------------------------------\n");
			
			
//            HashtagEntity[] hashtagEntities = status.getHashtagEntities();
			
			//System.out.println(" status=" + status.toString());
		}
		
		return resultList;
	}
	private List<SnsCollectVo> collectPublicTwitterStreaming() {
		
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(EgovProperties.getProperty("twitter.consumer.key"));
        cb.setOAuthConsumerSecret(EgovProperties.getProperty("twitter.consumer.secret"));
        cb.setOAuthAccessToken("167199054-tZL9ClLI5yfospj4YCj1UO3HaUR9P49TrGAkNE1E");
        cb.setOAuthAccessTokenSecret("FEuR6sSE8NJ0R0uTK7Y8v4FBFYV9FZtbPx8mFhhnac");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        StatusListener listener = new StatusListener() {

            
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            
            public void onStatus(Status status) {
                User user = status.getUser();
                
                // gets Username
                String username = status.getUser().getScreenName();
                System.out.println(username);
                String profileLocation = user.getLocation();
                System.out.println(profileLocation);
                long tweetId = status.getId(); 
                System.out.println(tweetId);
                String content = status.getText();
                System.out.println(content +"\n");

            }

            
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        
        FilterQuery fq = new FilterQuery();
        
        String searchKeyword = null;
		try {
			searchKeyword = URLEncoder.encode("부산", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        String keywords[] = {"리트윗"};

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq); 
		
		return null;
	}
	
	
	private List<SnsCollectVo> prettyYoutubePrint(Iterator<SearchResult> iteratorSearchResults, String query, YouTube youtube) {
		
		List<SnsCollectVo> resultList = new ArrayList<SnsCollectVo>();
		
        System.out.println("\n=============================================================");
        System.out.println("   First " + 25 + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");
        /*
         * sns_idx varchar2(20), -- sns식별자 ( sns에서 제공하는 고유아이디)
sns_type varchar2(10), -- sns타입 twitter facebook youtube instagram kakaoStory
sns_regdt varchar2(14), -- sns 실제 작성일자
sns_desc  varchar2(200), -- sns 간략한 내용 
sns_linkurl varchar2(200), -- sns 연결링크
sns_thumnail varchar2(200), -- sns 썸네일 링크
use_yn char(1), -- Y : 사용 N : 미사용 null : 확인전
regdt varchar2(14),
moddt varchar2(14),
regid varchar2(20),
modid varchar2(20),
constraint "sns_contents_pk"  primary key(sns_idx,sns_type) 

해시태그 필요
         * */
        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();
            
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getMedium();
                
                
                String from = singleVideo.getSnippet().getPublishedAt().toString();
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.'000Z'");
                java.util.Date to = null;
                try {
					to = transFormat.parse(from);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                SimpleDateFormat transFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
                
                SnsCollectVo snsCollectVo = new SnsCollectVo();
                snsCollectVo.setSnsIdx(rId.getVideoId());
                snsCollectVo.setSnsType("youtube");
                snsCollectVo.setSnsRegdt(transFormat2.format(to));
                snsCollectVo.setSnsRegid(singleVideo.getSnippet().getChannelId());
                snsCollectVo.setSnsRegnm(singleVideo.getSnippet().getChannelTitle());
//                snsCollectVo.setSnsDesc(singleVideo.getSnippet().getDescription());
                snsCollectVo.setSnsDesc(singleVideo.getSnippet().getTitle());
                snsCollectVo.setSnsLinkurl("https://www.youtube.com/watch?v=" +  rId.getVideoId());
                snsCollectVo.setSnsThumnail(thumbnail.getUrl());
                System.out.println("#########" + singleVideo.getSnippet());
                snsCollectVo.setSnsTNHeight(thumbnail.getHeight().intValue());
                snsCollectVo.setSnsTNWidth(thumbnail.getWidth().intValue());
                snsCollectVo.setSnsKeyword(query);
                resultList.add(snsCollectVo);
                
                System.out.println(" snsIdx=" +  rId.getVideoId());
                System.out.println(" snsType=youtube");
                System.out.println(" snsRegdt=" + snsCollectVo.getSnsRegdt());
                System.out.println(" snsRegid=" + singleVideo.getSnippet().getChannelId());
                System.out.println(" snsRegNm=" + singleVideo.getSnippet().getChannelTitle());
                System.out.println(" snsProfileimg=" + singleVideo.getSnippet().getPublishedAt());
                System.out.println(" snsTitle=" + singleVideo.getSnippet().getTitle());
                System.out.println(" snsDesc=" + singleVideo.getSnippet().getDescription());
                System.out.println(" snsLinkurl=https://www.youtube.com/watch?v=" +  rId.getVideoId());
                System.out.println(" snsThumnail=" + thumbnail.getUrl());
                System.out.println(" singleVideo=" + singleVideo);
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
        
        return resultList;
    }
	
	
	private List<SnsCollectVo> prettyYoutubePrint2(List<Channel> channelResultList ,
			String queryTerm, YouTube youtube) {
		
		

            Channel channel = channelResultList.get(0);
            System.out.println("###" + channel.getId());
            if (true) {
                
                SnsCollectVo snsCollectVo = new SnsCollectVo();
                System.out.println("#########" + channel.getSnippet());
                System.out.println(" singleVideo=" + channel);
                System.out.println("\n-------------------------------------------------------------\n");
            }
		
		return null;
	}
	
	
	public static void main(String[] args) throws ParseException {
		
		
		SnsRecentlyCollectScheduling snsCollect = new SnsRecentlyCollectScheduling();
		
		
		Date time = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss +0900", Locale.ENGLISH).parse("Thu, 27 Oct 2016 15:51:28 +0900");
		  String rssFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);

		System.out.println(rssFormat);
		
	}
	
}
