package egovframework.injeinc.common.social.kakao.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import egovframework.injeinc.common.social.kakao.api.Kakao;

public class KakaoConnectionFactory extends OAuth2ConnectionFactory<Kakao>{
	public KakaoConnectionFactory(String clientId) {
		super("kakao", new KakaoServiceProvider(clientId), new KakaoAdapter());
	}
}
