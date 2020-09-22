package egovframework.injeinc.common.social.vo;

import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class SocialVo extends ComDefaultVO {
	
	public static final String SOCIAL_TYPE_NAVER = "naver";
	public static final String SOCIAL_TYPE_KAKAO = "kakao";
	public static final String SOCIAL_TYPE_FACEBOOK = "facebook";
	public static final String SOCIAL_TYPE_TWITTER = "twitter";
	
	private String socialType;
	private String socialId;
	private String nickName;
	private String profileImage;
	private String accessToken;
	private String refreshToken;
	private AccessToken accesstokenT;
	private RequestToken requesttoken;
	private String menuUrl;
	private String commentCont;
	private String regdt;
	private String scidx;
	private String mode;
	
	
	public RequestToken getRequesttoken() {
		return requesttoken;
	}

	public void setRequesttoken(RequestToken requesttoken) {
		this.requesttoken = requesttoken;
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getScidx() {
		return scidx;
	}

	public void setScidx(String scidx) {
		this.scidx = scidx;
	}

	public String getRegdt() {
		return regdt;
	}

	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getCommentCont() {
		return commentCont;
	}

	public void setCommentCont(String commentCont) {
		this.commentCont = commentCont;
	}
	
	
	public AccessToken getAccesstokenT() {
		return accesstokenT;
	}

	public void setAccesstokenT(AccessToken accesstokenT) {
		this.accesstokenT = accesstokenT;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSocialType() {
		return socialType;
	}

	public void setSocialType(String socialType) {
		this.socialType = socialType;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

			
}
