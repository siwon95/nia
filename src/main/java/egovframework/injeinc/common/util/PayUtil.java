/*
 * Created on 2005. 2. 11.
 *
 */
package egovframework.injeinc.common.util;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PayUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PayUtil.class);
	
	public static String getPayTypeName(String payType) {
		String payTypeName = null;
		
    	if("SC0010".equals(payType)){
    		payTypeName = "신용카드";
    	}else if("SC0030".equals(payType)){
    		payTypeName = "계좌이체";
    	}else if("SC0040".equals(payType)){
    		payTypeName = "무통장";
    	}else if("SC0060".equals(payType)){
    		payTypeName = "휴대폰";
    	}else if("SC0070".equals(payType)){
    		payTypeName = "유선전화결제";
    	}else if("SC0090".equals(payType)){
    		payTypeName = "OK캐쉬백";
    	}else if("SC0111".equals(payType)){
    		payTypeName = "문화상품권";
    	}else if("SC0112".equals(payType)){
    		payTypeName = "게임문화상품권";
    	}else if("SC0113".equals(payType)){
    		payTypeName = "도서문화상품권";
    	}else if("SC0220".equals(payType)){
    		payTypeName = "모바일T머니";
    	}
        return payTypeName;
    }
}