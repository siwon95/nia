package egovframework.cmm.interceptors;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import egovframework.injeinc.boffice.cn.menu.service.UserMenuContentsSvc;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuContentsVo;
import egovframework.injeinc.boffice.sy.menu.service.MgrMenuSvc;
import egovframework.injeinc.boffice.sy.mgr.service.MgrListSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;
import egovframework.injeinc.boffice.sy.progrm.service.ProgrmSvc;
import egovframework.injeinc.boffice.title.service.TitleSvc;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service
public class SessionHandlerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(SessionHandlerInterceptor.class);
	
	@Resource(name = "TitleSvc")
	private TitleSvc titleSvc;
	
	@Resource(name = "progrmSvc")
    private ProgrmSvc progrmSvc;
	
	@Resource(name = "MgrMenuSvc")
	private MgrMenuSvc mgrMenuSvc;
	
	@Resource(name = "MgrListSvc")
	private MgrListSvc mgrListSvc;
	
	@Resource(name = "UserMenuContentsSvc")
	private UserMenuContentsSvc userMenuContentsSvc;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hadler) throws Exception {

		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();

		String progrmGrp = progrmSvc.selectProgrmGrp(uri);
		String cmm_code = mgrMenuSvc.selectMgrMenuCode(progrmGrp);
		
		if(cmm_code == null){
			cmm_code = "";
		}
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(6000);
		session.setAttribute("AuthorityCode", cmm_code);
		
		EgovMap eMap = titleSvc.retriveTitle(cmm_code);
		request.setAttribute("naviTitle", eMap);
		
		String SesUserId = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		String SesUserPermCd = (String) WebUtils.getSessionAttribute(request, "SesUserPermCd");
		String SesUserRoleIdx = (String) WebUtils.getSessionAttribute(request, "SesUserRoleIdx");
		
		UserMenuContentsVo userMenuContentsVo = new UserMenuContentsVo();
		userMenuContentsVo.setSearchCondition(SesUserPermCd);
		userMenuContentsVo.setSearchKeyword(SesUserRoleIdx);
		int publishReqCnt = userMenuContentsSvc.selectPublishReqContentsCount(userMenuContentsVo);
		
		List contentProgressList = userMenuContentsSvc.selectUserContentsProgressList(userMenuContentsVo);
		
		request.setAttribute("publishReqCnt", publishReqCnt);
		request.setAttribute("contentProgressList", contentProgressList);
		
		if (url.indexOf("Login.do") >= 0
				|| url.indexOf("LoginList.do") >= 0
				|| url.indexOf("LoginTestList.do") >= 0
				|| url.indexOf("LoginSessionEmpty.do") >= 0
				|| url.indexOf("LoginSessionEmptyAx.do") >= 0
				|| url.indexOf("LoginSessionEmptyPop.do") >= 0
				|| url.indexOf("LoginSessionEmptyParking.do") >= 0
				|| url.indexOf("MgrMain.do") >= 0
				|| url.indexOf("Logout.do") >= 0
				) {
			return true;
		}
		
		if(EgovStringUtil.isEmpty(SesUserId)) {
			if (url.indexOf("Ax.do") >= 0) {
				logger.debug("web Ajax check"+request.getContextPath());
				response.sendRedirect("/login/LoginSessionEmptyAx.do");
				return false;
			}else if (url.indexOf("Pop.do") >= 0) {
				logger.debug("web .do check"+request.getContextPath());
				response.sendRedirect("/login/LoginSessionEmptyPop.do");
				return false;
			}else{
				logger.debug("web session check"+request.getContextPath());
				response.sendRedirect("/login/LoginSessionEmpty.do");
				return false;
			}
		}
		
		if(!SesUserPermCd.equals("05010000")){
			MgrVo mgrVo = new MgrVo();
			
			mgrVo.setSearchId(SesUserId);
			mgrVo.setMgrMenu(cmm_code);
			
			int cnt = 0;
			
			if(!cmm_code.equals("")){
				cnt = mgrListSvc.retrieveAuthMgrMenuListCnt(mgrVo);
			}
			
			if(!cmm_code.equals("") && cnt < 1){
				response.sendRedirect("/login/LoginSessionAuthEmpty.do");
				return false;
			}
			
		}
		
		return true;
	}

}
