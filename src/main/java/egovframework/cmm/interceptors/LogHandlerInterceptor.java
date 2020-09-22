package egovframework.cmm.interceptors;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Service
public class LogHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = Logger.getLogger(LogHandlerInterceptor.class);
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        /*controller 이벤트 호출 전*/
		
		if (logger.isDebugEnabled()) {
			HandlerMethod handler = (HandlerMethod) obj;
			
			if(handler.getMethod().getName().indexOf("Layout") == -1){
				logger.debug("======================================          START         ======================================");
	        	logger.debug(" Request URI      \t:  " + request.getRequestURI());
	        	logger.debug(" Request Ctroller \t:  " + handler.getBean().getClass().getName());
	        	logger.debug(" Request AccessIp \t:  " + request.getRemoteAddr());
	        	
	        	Enumeration eNames = request.getParameterNames();
	        	if (eNames.hasMoreElements()) {
	        		String title = "Parameters";
	        		Map entries = new TreeMap();
	        		while (eNames.hasMoreElements()) {
	        			String name = (String) eNames.nextElement();
	        			String[] values = request.getParameterValues(name);
	        			if (values.length > 0) {
	        				String value = values[0];
	        				for (int i = 1; i < values.length; i++) {
	        					value += "," + values[i];
	        				}
	        				logger.debug(" Request Param   \t:  [" + name +"]=" + value);
	        			}
	        		}
	        	}
			}
			
        }
        return super.preHandle(request, response, obj);
    }
     
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView) throws Exception {
        /* controller 호출 후 view 페이지 출력 전 */
    	if (logger.isDebugEnabled()) {
    		HandlerMethod handler = (HandlerMethod) obj;
			
			if(handler.getMethod().getName().indexOf("Layout") == -1){
				if(modelAndView != null){
	        		logger.debug(" Response ViewName \t:  " + modelAndView.getViewName());
	        	}
	        	logger.debug("======================================           END          ======================================\n");
			}
    		
        }
    
    }
    
//    @Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) throws Exception {
//    	/* afterCompletion : controller + view 페이지 모두 출력 후 */
//    	if (logger.isDebugEnabled()) {
//        }
//    }
    
}
