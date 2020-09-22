package egovframework.cmm.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



@Service
public class SessionHandlerSiteInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(SessionHandlerSiteInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hadler) throws Exception {

		StringBuffer url = request.getRequestURL();
		String reUrl = "";
		
		if(url.indexOf("/reservation/foffice") >= 0){
			//throw new JsonTypeException("욕설방지");
			
			if (request.getSession().getAttribute("ss_id") == null && request.getSession().getAttribute("nss_id") == null) {
			//일반적인 체크
			reUrl = request.getRequestURI();
			String qString = request.getQueryString();
			
			System.out.println("-----getRequestURI-----"+reUrl);
			System.out.println("-----referer-----"+request.getHeader("referer"));

			if(qString != null){
				qString = qString.replaceAll("&", "%26");
			}

			if (qString != null && !qString.equals("")) {
				reUrl = reUrl + "%3F" + qString;
			}
			logger.debug("ss_id  session check"+request.getContextPath());
			response.sendRedirect("/site/injeinc/login/NonMember_Empty.do?reUrl="+reUrl);
			return false;
			}

		}
		
			if(url.indexOf("/reservation/foffice") < 0 && url.indexOf("/foffice") >= 0){
				//throw new JsonTypeException("욕설방지");
				
				if (request.getSession().getAttribute("ss_id") == null) {
				//일반적인 체크
				reUrl = request.getRequestURI();
				String qString = request.getQueryString();
				
				System.out.println("-----getRequestURI-----"+reUrl);
				System.out.println("-----referer-----"+request.getHeader("referer"));

				if(qString != null){
					qString = qString.replaceAll("&", "%26");
				}

				if (qString != null && !qString.equals("")) {
					reUrl = reUrl + "%3F" + qString;
				}
				logger.debug("ss_id  session check"+request.getContextPath());
				response.sendRedirect("/site/injeinc/login/Login_Session_Empty.do?reUrl="+reUrl);
				return false;
				}

			}
						
		return true;

    }//if

}
