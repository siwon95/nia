package egovframework.injeinc.boffice.sy.mgr.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.injeinc.boffice.sy.mgr.service.MgrListSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrListVo;
import egovframework.injeinc.common.util.EgovStringUtil;

public class MgrListUtil {
	
	public static MgrListVo getMgrListByName(String mgrName) throws Exception {
		
		MgrListVo returnVo = null;
		
		if(!EgovStringUtil.isEmpty(mgrName)) {
			
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			ServletContext conext = session.getServletContext();
			WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
			MgrListSvc mgrListSvc = (MgrListSvc)wContext.getBean("MgrListSvc");
			
			returnVo = mgrListSvc.retrieveMgrListByName(mgrName);
		}
		
		return returnVo;
		
	}
}