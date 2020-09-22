package egovframework.injeinc.common.social.naver.api;

import org.springframework.social.ApiBinding;

import egovframework.injeinc.common.social.naver.api.abstracts.UserOperation;

public interface Naver extends ApiBinding {
	UserOperation userOperation();
}
