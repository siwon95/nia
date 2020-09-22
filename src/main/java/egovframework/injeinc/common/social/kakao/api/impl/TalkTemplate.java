package egovframework.injeinc.common.social.kakao.api.impl;

import org.springframework.web.client.RestTemplate;

import egovframework.injeinc.common.social.kakao.api.KakaoTalkProfile;
import egovframework.injeinc.common.social.kakao.api.TalkOperation;

public class TalkTemplate extends AbstractKakaoOperations implements TalkOperation {
	private final RestTemplate restTemplate;
	
	public TalkTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(isAuthorized);
		this.restTemplate = restTemplate;
	}

	public KakaoTalkProfile getUserProfile() {
		requireAuthorization();
		return restTemplate.getForObject(buildApiUri("/v1/api/talk/profile"), KakaoTalkProfile.class);
	}
}
