package egovframework.injeinc.foffice.ex.board.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.injeinc.foffice.ex.board.service.ContentMinwonResultFSvc;
import egovframework.injeinc.foffice.ex.board.vo.ContentMinwonResultFVo;
import egovframework.injeinc.common.util.EgovStringUtil;

public class ContentMinwonResultUtil {
	
	@SuppressWarnings("rawtypes")
	public static List getMinwonResultList(int bcIdx, int cbIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		ContentMinwonResultFSvc contentMinwonResultFSvc = (ContentMinwonResultFSvc)wContext.getBean("ContentMinwonResultFSvc");
		
		List resultList = null;
		
		if(bcIdx > 0 && cbIdx > 0) {
			ContentMinwonResultFVo contentMinwonResultFVo = new ContentMinwonResultFVo();
			contentMinwonResultFVo.setBcIdx(bcIdx);
			contentMinwonResultFVo.setCbIdx(cbIdx);
			resultList = contentMinwonResultFSvc.retrieveListContentMinwonResultF(contentMinwonResultFVo);
		}
		
		return resultList;
	}
	
	public static ContentMinwonResultFVo getMinwonResultTotal(int bcIdx, int cbIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		ContentMinwonResultFSvc contentMinwonResultFSvc = (ContentMinwonResultFSvc)wContext.getBean("ContentMinwonResultFSvc");
		
		ContentMinwonResultFVo result = null;
		
		if(bcIdx > 0 && cbIdx > 0) {
			ContentMinwonResultFVo contentMinwonResultFVo = new ContentMinwonResultFVo();
			contentMinwonResultFVo.setBcIdx(bcIdx);
			contentMinwonResultFVo.setCbIdx(cbIdx);
			result = contentMinwonResultFSvc.retrieveListContentMinwonResultFTotal(contentMinwonResultFVo);
		}
		
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getMinwonFileList(int bcIdx, int cbIdx, String mcIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		ContentMinwonResultFSvc contentMinwonResultFSvc = (ContentMinwonResultFSvc)wContext.getBean("ContentMinwonResultFSvc");
		
		List resultList = null;
		mcIdx = EgovStringUtil.isNullToString(mcIdx);
		
		if(bcIdx > 0 && cbIdx > 0 && !mcIdx.equals("")) {
			ContentMinwonResultFVo contentMinwonResultFVo = new ContentMinwonResultFVo();
			contentMinwonResultFVo.setBcIdx(bcIdx);
			contentMinwonResultFVo.setCbIdx(cbIdx);
			contentMinwonResultFVo.setMcIdx(mcIdx);
			resultList = contentMinwonResultFSvc.retrieveListContentFileMinwon(contentMinwonResultFVo);
		}
		
		return resultList;
	}
	
	public static String getMinwonResultDeadLine(int bcIdx, int cbIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		ContentMinwonResultFSvc contentMinwonResultFSvc = (ContentMinwonResultFSvc)wContext.getBean("ContentMinwonResultFSvc");
		
		String result = "";
		
		if(bcIdx > 0 && cbIdx > 0) {
			ContentMinwonResultFVo contentMinwonResultFVo = new ContentMinwonResultFVo();
			contentMinwonResultFVo.setBcIdx(bcIdx);
			contentMinwonResultFVo.setCbIdx(cbIdx);
			result = contentMinwonResultFSvc.retrieveListContentMinwonResultFDeadLine(contentMinwonResultFVo);
		}
		
		return result;
	}
	
	public static ContentMinwonResultFVo getMinwonResultDelay(int bcIdx, int cbIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		ContentMinwonResultFSvc contentMinwonResultFSvc = (ContentMinwonResultFSvc)wContext.getBean("ContentMinwonResultFSvc");
		
		ContentMinwonResultFVo result = null;
		
		if(bcIdx > 0 && cbIdx > 0) {
			ContentMinwonResultFVo contentMinwonResultFVo = new ContentMinwonResultFVo();
			contentMinwonResultFVo.setBcIdx(bcIdx);
			contentMinwonResultFVo.setCbIdx(cbIdx);
			result = contentMinwonResultFSvc.retrieveListContentMinwonResultFDelay(contentMinwonResultFVo);
		}
		
		return result;
	}
}