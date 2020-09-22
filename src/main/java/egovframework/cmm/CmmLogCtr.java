package egovframework.cmm;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;


@Controller
public abstract class CmmLogCtr {
	
	  private Logger logger = Logger.getLogger(getClass());

	protected void debugLog(String debugMsg){
		if(logger.isDebugEnabled()){
			logger.debug(debugMsg);
		}
	}

	protected void infoLog(String infoMsg){
		if(logger.isInfoEnabled()){
			logger.info(infoMsg);
		}
	}

	protected void warnLog(String warnMsg) {
		logger.warn(warnMsg);
	}

	protected void errorLog(String errMsg){
		logger.error(errMsg);
	}

	protected void errorLog(String errMsg, Throwable e){
		logger.error(errMsg, e);
	}

	protected void fatalLog(String fatalMsg) {
		logger.fatal(fatalMsg);
	}

	protected Map<String, String> getHeaders(HttpServletRequest request) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		Enumeration<?> eheaders = request.getHeaderNames();
		while (eheaders.hasMoreElements()) {
			String key = (String) eheaders.nextElement();
			String value = request.getHeader(key);
			headers.put(key, value);
		}
		return headers;
	}

	protected String getHeader(HttpServletRequest request, String headerName) throws Exception {
		return request.getHeader(headerName);
	}
	
	/**
	 * <pre>
	 * alert and go returnUri
	 * </pre>
	 * @param massage
	 * @param returnUri
	 * @return
	 * @throws Exception
	 */
	public String alert(String returnUri, String massage, ModelMap model) throws Exception {
		if(model != null){
			if(returnUri != null){
				model.addAttribute("returnUri", returnUri);
			}
			model.addAttribute("alertMsg", massage);
		}
		
		return "injeinc/common/alert";
	}
	
	public String alertParentUrl(String returnUri, String massage, ModelMap model) throws Exception {
		if(model != null){
			if(returnUri != null){
				model.addAttribute("returnUri", returnUri);
			}
			model.addAttribute("alertMsg", massage);
		}
		
		return "injeinc/common/alert_parent_url";
	}
	
	public String alertIntern(String returnUri, ModelMap model) throws Exception {
		if(model != null){
			if(returnUri != null){
				model.addAttribute("returnUri", returnUri);
			}
		}
		return "injeinc/common/alert_intern";
	}
	
	public String alertScrap(String returnUri, ModelMap model) throws Exception {
		if(model != null){
			if(returnUri != null){
				model.addAttribute("returnUri", returnUri);
			}
		}
		return "injeinc/common/alert_scrap";
	}
	
	/** 
	 * json type으로 리턴하기
	 * 
	 * ex)
	 * @RequestMapping(value="~~", produces="application/json;charset=UTF-8")
	 * @ResponseBody
	 * */
	public String returnJsonType(boolean isSuccess, String msg, Object data) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("isSuccess", isSuccess); // 성공여부
		obj.put("msg", msg); // 메시지
		obj.put("data", data); // 데이타
		return obj.toString();
	}
	
	/**
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
	public String getTimeStamp() {

		String rtnStr = null;
	
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";
		
	    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());

	    rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
    }
	
}
