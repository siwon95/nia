package egovframework.injeinc.common.social.naver.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

import egovframework.injeinc.common.social.naver.api.abstracts.UserOperation;
import egovframework.injeinc.common.social.naver.api.model.UserProfile;
import egovframework.injeinc.common.social.naver.api.model.UserProfileResponse;
import egovframework.injeinc.common.social.naver.api.util.NaverApi;

public class UserOperationImpl implements UserOperation {
	private static final Logger LOG = LoggerFactory.getLogger(UserOperationImpl.class);

	private boolean isAuthorized;
	private UserProfile userProfile;

	public UserOperationImpl(RestTemplate restTemplate, boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
		this.userProfile = restTemplate.getForObject(NaverApi.buildUserUri("/nidlogin/nid/getUserProfile.xml"), UserProfile.class);
	}

	private UserProfileResponse getUserProfile() {
		if (this.isAuthorized) {
			if (!"00".equals(this.userProfile.getResult().getResultcode())) {
				throw new InvalidAuthorizationException("naver", this.userProfile.getResult().getMessage());
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug("USER PROFILE RESULT: {}", this.userProfile.toJson(false));
			}
			return this.userProfile.getResponse();
		} else {
			throw new MissingAuthorizationException("naver");
		}
	}

	
	public String getId() {
		return getUserProfile().getId();
	}

	
	public String getNickname() {
		return getUserProfile().getNickname();
	}

	
	public String getName() {
		return getUserProfile().getName();
	}

	
	public String getEmail() {
		return getUserProfile().getEmail();
	}

	
	public String getGender() {
		return getUserProfile().getGender();
	}

	
	public String getAge() {
		return getUserProfile().getAge();
	}

	
	public String getBirthday() {
		return getUserProfile().getBirthday();
	}

	
	public String getProfile_image() {
		return getUserProfile().getProfile_image();
	}
}
