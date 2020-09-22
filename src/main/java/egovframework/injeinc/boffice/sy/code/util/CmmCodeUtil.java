package egovframework.injeinc.boffice.sy.code.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.sy.mgr.service.MgrAuthoritySvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrAuthorityVo;
import egovframework.injeinc.boffice.main.layerPopup.vo.MainLayerPopupVo;
import egovframework.injeinc.boffice.main.popupZone.service.MainPopupZoneSvc;
import egovframework.injeinc.boffice.main.popupZone.vo.MainPopupZoneVo;
import egovframework.injeinc.foffice.user.service.UserFSvc;
import egovframework.injeinc.foffice.user.vo.UserFVo;
import egovframework.cmm.service.EgovProperties;

public class CmmCodeUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(CmmCodeUtil.class);
	
	public static String getCodeName(String code) {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		CmmCodeSvc cmmCodeSvc = (CmmCodeSvc)wContext.getBean("CmmCodeSvc");
		
		String result = "";
		
		if(!EgovStringUtil.isEmpty(code)) {
		
			try {
				CmmCodeVo cmmCodeVo = cmmCodeSvc.retrieveCmmCode(code);
				result = cmmCodeVo.getCodeName();
			} catch (RuntimeException e) {
				LOGGER.debug("IGNORED: " + e.getMessage());
			} catch (Exception e) {
				LOGGER.debug("IGNORED: " + e.getMessage());
			}
		}
		
		return result;
	}
	
	public static String getGlobalProperties(String code) {
		String rootPath = EgovProperties.getProperty(code);
		return rootPath;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getCodeList(String codeUpr) {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		CmmCodeSvc cmmCodeSvc = (CmmCodeSvc)wContext.getBean("CmmCodeSvc");
		
		List list = null;
		
		if(!EgovStringUtil.isEmpty(codeUpr)) {
		
			try {
				list = cmmCodeSvc.retrieveListCmmCode(codeUpr);
			} catch (RuntimeException e) {
				LOGGER.debug("IGNORED: " + e.getMessage());
			} catch (Exception e) {
				LOGGER.debug("IGNORED: " + e.getMessage());
			}
		}
		
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getSiteList() {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		SiteSvc siteSvc = (SiteSvc)wContext.getBean("SiteSvc");
		
		List list = null;
		
		
		try {
			SiteVo siteVo = new SiteVo();
			list = siteSvc.selectListSiteAll(siteVo);
		} catch (RuntimeException e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
		}
		
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public static int getAuthorlityCnt(String roleIdx, String maType,String maPkidx) {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		MgrAuthoritySvc mgrAuthoritySvc = (MgrAuthoritySvc)wContext.getBean("MgrAuthoritySvc");
		
		int cnt = 0;
		
		
		try {
			MgrAuthorityVo mgrAuthorityVo = new MgrAuthorityVo();
			mgrAuthorityVo.setRoleIdx(roleIdx);
			mgrAuthorityVo.setMaType(maType);
			mgrAuthorityVo.setMaPkidx(maPkidx);
			cnt = mgrAuthoritySvc.selectMgrAuthorityChk(mgrAuthorityVo);
		} catch (RuntimeException e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
		}
		
		return cnt;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getPopupList(String domain, String code){
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		MainPopupZoneSvc mainPopupZoneSvc = (MainPopupZoneSvc)wContext.getBean("MainPopupZoneSvc");
		
		int cnt = 0;
		
		List list = null;
		
		try {
			MainPopupZoneVo mainPopupZoneVo = new MainPopupZoneVo();
			mainPopupZoneVo.setSearchSiteCd(domain);
			mainPopupZoneVo.setCode(code);
			return mainPopupZoneSvc.retrieveListMainPopupZone(mainPopupZoneVo);
		} catch (RuntimeException e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
		}
		
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public static UserFVo getUserInfo(){
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		UserFSvc userFSvc = (UserFSvc)wContext.getBean("UserFSvc");
		
		UserFVo userFVo = new UserFVo();
		try {
			if((String)session.getAttribute("ss_id") !=null){
				String cuId = (String)session.getAttribute("ss_id");
				userFVo.setCuId(cuId);
				userFVo = (UserFVo)userFSvc.retrieveUserF(userFVo);
			}
			return userFVo;
		} catch (RuntimeException e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
		}
		
		return userFVo;
	}
}