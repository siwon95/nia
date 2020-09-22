package egovframework.injeinc.common.social.naver.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

import egovframework.injeinc.common.social.naver.api.Naver;

public final class NaverConnectionFactory extends OAuth2ConnectionFactory<Naver> {
	public NaverConnectionFactory(final String clientId, final String clientSecret) {
		super("naver", new NaverServiceProvider(clientId, clientSecret), new NaverAdapter());
	}

	public static class NaverAuthenticationService extends OAuth2AuthenticationService<Naver> {
		public NaverAuthenticationService(final String clientId, final String clientSecret) {
			super(new NaverConnectionFactory(clientId, clientSecret));
		}
	}
}
