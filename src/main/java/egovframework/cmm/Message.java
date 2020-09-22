package egovframework.cmm;

import java.util.Locale;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;

/**
 * <PRE>
 * 1. FileName : Message.java
 * 2. Package  : kr.co.onefirst.common.message
 * 3. Comment  : 
 * 4. Programmer : khw
 * 5. Date   : 2014. 6. 5.
 * </PRE>
 */
@Controller
public class Message {

	/**
	 * MessageSourceAccessor
	 */
	private static MessageSourceAccessor msAcc = null;

	public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
		Message.msAcc = msAcc;
	}

	/**
	 * KEY에 해당하는 메세지 반환
	 * 
	 * @param key
	 * @return
	 */
	public static String getMessage(String key) {
		return msAcc.getMessage(key, Locale.getDefault());
	}

	/**
	 * KEY에 해당하는 메세지 반환
	 * 
	 * @param key
	 * @param objs
	 * @return
	 */
	public static String getMessage(String key, Object[] objs) {
		return msAcc.getMessage(key, objs, Locale.getDefault());
	}
	
}