package egovframework.cmm.interceptors;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.injeinc.boffice.ex.sns.service.SnsSvc;
import egovframework.injeinc.boffice.title.service.TitleSvc;


@Service
public class BannedWordsInterceptor extends HandlerInterceptorAdapter{

private static final Logger logger = Logger.getLogger(BannedWordsInterceptor.class);
	
@Resource(name = "TitleSvc")
private TitleSvc titleSvc;

@Resource(name = "SnsSvc")
private SnsSvc snsSvc;

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hadler) throws Exception {

	StringBuffer url = request.getRequestURL();
	
	//소셜 댓글 욕설 방지
	if (url.indexOf("/common/social/comment") >= 0) {
			
		Enumeration eNames = request.getParameterNames();
    	if (eNames.hasMoreElements()) {
    		String title = "Parameters";
    		Map entries = new TreeMap();
    	
    		while (eNames.hasMoreElements()) {
    			String name = (String) eNames.nextElement();
    			if("commentCont".equals(name)){
    				String[] values = request.getParameterValues(name);
    				String value = values[0];
    				
    				boolean isBannendWord = snsSvc.selectBannendWordList(value); //욕설 관련된 서비스 태우기
    				
    				if(isBannendWord){    			
    					
    					PrintWriter BannendWord = response.getWriter();
    					BannendWord.write("{\"errorMsg\":\"bannendWord\"}");
    					BannendWord.flush();
    					BannendWord.close();
    					return false;
    					}
    				}
    			}
    		}	
		}
	
	return true;
	
	}
}

