package egovframework.injeinc.common.social.kakao.security;

import org.springframework.social.security.provider.OAuth2AuthenticationService;

import egovframework.injeinc.common.social.kakao.api.Kakao;
import egovframework.injeinc.common.social.kakao.connect.KakaoConnectionFactory;

public class KakaoAuthenticationService extends OAuth2AuthenticationService<Kakao> {
	public KakaoAuthenticationService(String clientId) {
		super(new KakaoConnectionFactory(clientId));
	}
}
