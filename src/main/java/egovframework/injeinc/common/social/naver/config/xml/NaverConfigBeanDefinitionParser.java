package egovframework.injeinc.common.social.naver.config.xml;

import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.security.provider.SocialAuthenticationService;

import egovframework.injeinc.common.social.naver.config.support.NaverApiHelper;
import egovframework.injeinc.common.social.naver.connect.NaverConnectionFactory;

public final class NaverConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {
	public NaverConfigBeanDefinitionParser() {
		super(NaverConnectionFactory.class, NaverApiHelper.class);
	}

	@Override
	protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
		return NaverConnectionFactory.NaverAuthenticationService.class;
	}

	@Override
	protected BeanDefinition getConnectionFactoryBeanDefinition(String appId, String appSecret, Map<String, Object> allAttributes) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder
				.genericBeanDefinition(NaverConnectionFactory.class)
				.addConstructorArgValue(appId);
		return builder.getBeanDefinition();
	}
}
