package egovframework.injeinc.common.social.kakao.api.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import egovframework.injeinc.common.social.kakao.api.ForApns;
import egovframework.injeinc.common.social.kakao.api.ForGcm;
import egovframework.injeinc.common.social.kakao.api.PushOperation;
import egovframework.injeinc.common.social.kakao.api.PushToken;

public class PushTemplate extends AbstractKakaoOperations implements PushOperation {
	private final RestTemplate adminRestTemplate;
	
	public PushTemplate(RestTemplate adminRestTemplate, boolean isAuthorized) {
		super(isAuthorized);
		this.adminRestTemplate = adminRestTemplate;
	}
	
	public String register(String uuid, String deviceId, String pushType, String pushToken) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("uuid", uuid);
		parameters.set("device_id", deviceId);
		parameters.set("push_type", pushType);
		parameters.set("push_token", pushToken);
		
		return adminRestTemplate.postForObject(buildApiUri("/v1/push/register"), parameters, String.class);
	}
	
	public List<PushToken> tokens(String uuid) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("uuid", uuid);
		
		PushToken[] arrPushToken = adminRestTemplate.getForObject(buildApiUri("/v1/push/tokens", parameters), PushToken[].class);
		
		return Arrays.asList(arrPushToken);
	}
	
	public void deregister(String uuid, String deviceId, String pushType) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("uuid", uuid);
		parameters.set("device_id", deviceId);
		parameters.set("push_type", pushType);
		
		adminRestTemplate.postForLocation(buildApiUri("/v1/push/deregister"), parameters);
	}
	
	public void send(List<String> uuids, ForApns forApns, ForGcm forGcm) {
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.set("uuids", uuids);
		if (forApns != null) {
			if (forApns.isEmpty()) {
				parameters.set("for_apns", new HashMap<String, String>());
			} else {
				parameters.set("for_apns", forApns);
			}
		}
		if (forGcm != null) {
			if (forGcm.isEmpty()) {
				parameters.set("for_gcm", new HashMap<String, String>());
			} else {
				parameters.set("for_gcm", forGcm);
			}
		}
		
		adminRestTemplate.postForLocation(buildApiUri("/v1/push/send"), parameters);
	}
}
