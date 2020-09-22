package egovframework.injeinc.common.social.kakao.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import egovframework.injeinc.common.social.kakao.api.Kakao;
import egovframework.injeinc.common.social.kakao.api.impl.KakaoTemplate;

public class KakaoServiceProvider extends AbstractOAuth2ServiceProvider<Kakao> {
	public KakaoServiceProvider(String clientId) {
		super(new KakaoOAuth2Template(clientId));
	}
	
	@SuppressWarnings("deprecation")
	public Kakao getApi(String accessToken) {
		return new KakaoTemplate(accessToken);
	}
}
