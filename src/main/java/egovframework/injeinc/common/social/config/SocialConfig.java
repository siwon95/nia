package egovframework.injeinc.common.social.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.social.facebook.controller.PostToWallAfterConnectInterceptor;
import egovframework.injeinc.common.social.kakao.connect.KakaoConnectionFactory;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;

    
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer,
            Environment environment) {
        
        connectionFactoryConfigurer.addConnectionFactory(
            new FacebookConnectionFactory(
        		EgovProperties.getProperty("facebook.app.id"),
        		EgovProperties.getProperty("facebook.app.secret")
//                environment.getProperty("facebook.app.id"), 
//                environment.getProperty("facebook.app.secret")
            )
        );
//        connectionFactoryConfigurer.addConnectionFactory(
//            new GitHubConnectionFactory(
//                environment.getProperty("github.client.id"),
//                environment.getProperty("github.client.secret")
//            )
//        );
        connectionFactoryConfigurer.addConnectionFactory(
            new TwitterConnectionFactory(
//                environment.getProperty("twitter.consumer.key"), 
//                environment.getProperty("twitter.consumer.secret")
						EgovProperties.getProperty("twitter.consumer.key"),
						EgovProperties.getProperty("twitter.consumer.secret")
            )
        );
        
        connectionFactoryConfigurer.addConnectionFactory(
        		new KakaoConnectionFactory(
        				EgovProperties.getProperty("kakao.restApiKey")
        		)
        );
        
        //TODO NAVER BLOG ??
    }

    
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }
    
    
    public UsersConnectionRepository getUsersConnectionRepository(
            ConnectionFactoryLocator connectionFactoryLocator) {
        
        JdbcUsersConnectionRepository repository =  new JdbcUsersConnectionRepository(
            dataSource,connectionFactoryLocator,Encryptors.noOpText());
        repository.setConnectionSignUp(new ImplicitConnectionSignup());
        return repository;
    }
// Implicit Connection Signup
    private static class ImplicitConnectionSignup implements ConnectionSignUp {
        
        public String execute(Connection<?> connection) {
            return connection.getKey().getProviderUserId();
        }
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
            ConnectionRepository connectionRepository) {
    	ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
		connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
		//connectController.addInterceptor(new TweetAfterConnectInterceptor());
		return connectController;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator,
            UsersConnectionRepository usersConnectionRepository) {
        return new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
    }
    
    @Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public Facebook facebook(ConnectionRepository repository) {
		Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
		return connection != null ? connection.getApi() : null;
	}
    
    @Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}
}

