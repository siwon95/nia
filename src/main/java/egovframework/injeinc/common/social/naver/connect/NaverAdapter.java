package egovframework.injeinc.common.social.naver.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

import egovframework.injeinc.common.social.naver.api.Naver;
import egovframework.injeinc.common.social.naver.api.abstracts.UserOperation;

public final class NaverAdapter implements ApiAdapter<Naver> {
	public void setConnectionValues(final Naver naver, final ConnectionValues values) {
		final UserOperation userOperation = naver.userOperation();
		values.setProviderUserId(userOperation.getId());
		values.setProfileUrl("");
		values.setDisplayName(userOperation.getNickname());
		values.setImageUrl(userOperation.getProfile_image());
	}

	public UserProfile fetchUserProfile(final Naver naver) {
		return new UserProfileBuilder()
				.setEmail(naver.userOperation().getEmail())
				.setName(naver.userOperation().getName())
				.setUsername(naver.userOperation().getNickname())
				.build();
	}

	
	public boolean test(final Naver api) {
		return false;
	}

	
	public void updateStatus(final Naver api, final String message) {
	}
}
