package egovframework.injeinc.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softforum.xdbe.XdbHelper;

import egovframework.cmm.service.EgovProperties;

public class XecureUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(XecureUtil.class);
	
	public static String EncodeX64(String str) {
		str = XecureDB("encrypt", str);
		return str;
	}

	public static String DecodeX64(String str) {
		str = XecureDB("decrypt", str);
		return str;
	}
	
	public static String DigestX64(String str) {
		str = XecureDB("digest", str);
		return str;
	}
	
	public static String XecureDB(String command, String input) { 
		

		String platform	= EgovProperties.getProperty("Globals.PLATFORM");
		
		XdbHelper tXdbHelper_N = null;
		if(platform != null){
			if(platform.equals("service")) {
			
				// XdbHelper 객체생성
				tXdbHelper_N = new XdbHelper();
		
				// 정책서버에 접속 (일반암호화 정책)
				tXdbHelper_N.Connect("pool1", "scw_db", "scw_owner", "scw_table", "normal");
				
			}
		}

		String returnStr = "";
		
		try {
			
			if(EgovStringUtil.isEmpty(input)) {
				return returnStr;
			}
			
			if(command.equals("encrypt")) {
				if(platform != null){
					if(platform.equals("service")) {
						returnStr = tXdbHelper_N.Encrypt(input);
					}else{
						if(input != null){
							returnStr = new String(EgovFileScrty.encodeBinary(input.getBytes()));
						}
					}
				}
				
			}else if(command.equals("decrypt")) {
				if(platform != null){
					if(platform.equals("service")) {
						returnStr = tXdbHelper_N.Decrypt(input);
					}else{
						returnStr = new String(EgovFileScrty.decodeBinary(input));
					}
				}
			}else if(command.equals("digest")) {
				if(platform != null){
					if(platform.equals("service")) {
						returnStr = tXdbHelper_N.Hash(6,input);
					}else{
						returnStr = EgovFileScrty.encryptPassword(input);
					}
				}
			}else {
				
			}
			
		}catch (RuntimeException e) {
			LOGGER.debug("IGNORED: "+e.getMessage());
		}catch (Exception e) {
			LOGGER.debug("IGNORED: "+e.getMessage());
		}
		
		return returnStr;
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.parseInt("09"));
		
	}
}
