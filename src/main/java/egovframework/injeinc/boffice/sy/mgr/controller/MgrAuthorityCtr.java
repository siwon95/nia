package egovframework.injeinc.boffice.sy.mgr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.sy.menu.service.MgrMenuSvc;
import egovframework.injeinc.boffice.sy.mgr.service.MgrAuthoritySvc;
import egovframework.injeinc.boffice.sy.mgr.service.MgrListSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrAuthorityVo;
import egovframework.injeinc.boffice.sy.mgr.vo.RoleVo;
import egovframework.injeinc.boffice.sy.mgr.vo.TreeAuthorityVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.common.code.service.CodeSvc;

@Controller
public class MgrAuthorityCtr extends CmmLogCtr {
	 
	@Resource(name = "MgrAuthoritySvc")
	private MgrAuthoritySvc mgrAuthoritySvc;
	 
	@Resource(name = "MgrListSvc")
	private MgrListSvc mgrListSvc;
	 
	@Resource(name = "MgrMenuSvc")
	private MgrMenuSvc mgrMenuSvc;
	
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	@Resource(name = "CodeSvc")
	private CodeSvc codeSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/boffice/sy/mgr/MgrAuthorityPop.do")
	public String MgrAuthorityPop(HttpServletRequest request, @ModelAttribute("RoleVo") RoleVo roleVo, @ModelAttribute("MgrAuthorityVo") MgrAuthorityVo mgrAuthorityVo, ModelMap model) throws Exception {
		
		String searchType = mgrAuthorityVo.getSearchType();
		String roleIdx = mgrAuthorityVo.getRoleIdx();
		
		ArrayList<TreeAuthorityVo> authorityList = null;
		
		RoleVo roleVo2 = new RoleVo();
		roleVo2 = mgrListSvc.retrieveRole(roleVo);
		
		SiteVo siteVo = new SiteVo();
		List<SiteVo> siteList = siteSvc.selectListSiteAll(siteVo);
		
		/* 일반권한 일경우 무조건 사이트 선택 시작*/
		/*String authtorSite = "";
		String mgrSiteCdQuery = "";
		
		
		int rowCount = 0;
		mgrSiteCdQuery += " AND (1=2";
		for(int i=0;i<siteList.size();i++){
			siteVo = (SiteVo)siteList.get(i);
			if((","+roleVo2.getMgrSiteCd()+",").indexOf(","+siteVo.getSiteCd()+",")>-1){

				mgrSiteCdQuery += " OR ','||MGR_SITE_CD||',' like '%,"+siteVo.getSiteCd()+",%' ";
				
				rowCount++;
			}
		}
		mgrSiteCdQuery += ")";
		
		System.out.println("mgrSiteCdQuery:"+mgrSiteCdQuery);
		mgrAuthorityVo.setMgrSiteCdQuery(mgrSiteCdQuery);*/
		/* 일반권한 일경우 무조건 사이트 선택 끝*/
		
		if(!searchType.equals("") && !roleIdx.equals("")) {
			if(searchType.equals("menu")) {
				authorityList = (ArrayList<TreeAuthorityVo>) mgrAuthoritySvc.retrieveListMgrAuthorityMenu(mgrAuthorityVo);
			}else if(searchType.equals("board")) {
				authorityList = (ArrayList<TreeAuthorityVo>) mgrAuthoritySvc.retrieveListMgrAuthorityBoard(mgrAuthorityVo);
			}else{
				authorityList = (ArrayList<TreeAuthorityVo>) mgrAuthoritySvc.retrieveListMgrAuthoritySite(mgrAuthorityVo);
			}
		}else{
		}
		
		HashMap<String, String> param2 = new HashMap<String, String>();
		param2.put("codeAxuse", "SUB_SITE_CODE");
		HashMap<String, Object> codeMap2 = codeSvc.retrieveCommonCode(param2);

		model.addAttribute("subSiteList", codeMap2.get("rowDataList"));
		model.addAttribute("RoleVo", roleVo2);
		model.addAttribute("authorityList", authorityList);
		model.addAttribute("siteList", siteList);
		
		return "injeinc/boffice/sy/mgr/mgr_authority_pop";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/sy/mgr/MgrAuthorityListAx.do")
	public String MgrAuthorityListAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrAuthorityVo") MgrAuthorityVo mgrAuthorityVo, ModelMap model) throws Exception {

		String searchType = mgrAuthorityVo.getSearchType();
		String roleIdx = mgrAuthorityVo.getRoleIdx();
		
		boolean result = false;
		String message = "";
		ArrayList<TreeAuthorityVo> authorityList = null;
		
		if(!searchType.equals("") && !roleIdx.equals("")) {
			
			if(searchType.equals("menu")) {
				authorityList = (ArrayList<TreeAuthorityVo>) mgrAuthoritySvc.retrieveListMgrAuthorityMenu(mgrAuthorityVo);
				result = true;
			}else if(searchType.equals("board")) {
				authorityList = (ArrayList<TreeAuthorityVo>) mgrAuthoritySvc.retrieveListMgrAuthorityBoard(mgrAuthorityVo);
				result = true;
			}else{
				message = "알수없는 타입입니다.";
			}
			
		}else{
			message = "필요한 정보가 없습니다.";
		}
		
		model.addAttribute("authorityList", authorityList);
		
		return "injeinc/boffice/sy/mgr/mgr_authority_pop";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/sy/mgr/MgrAuthorityList_Pop.do")
	public void MgrAuthorityList_Pop(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrAuthorityVo") MgrAuthorityVo mgrAuthorityVo, ModelMap model) throws Exception {

		String searchType = mgrAuthorityVo.getSearchType();
		String roleIdx = mgrAuthorityVo.getRoleIdx();
		
		boolean result = false;
		String message = "";
		ArrayList<TreeAuthorityVo> authorityList = null;
		
		if(!searchType.equals("") && !roleIdx.equals("")) {
			
			if(searchType.equals("menu")) {
				authorityList = (ArrayList<TreeAuthorityVo>) mgrAuthoritySvc.retrieveListMgrAuthorityMenu(mgrAuthorityVo);
				result = true;
			}else if(searchType.equals("board")) {
				authorityList = (ArrayList<TreeAuthorityVo>) mgrAuthoritySvc.retrieveListMgrAuthorityBoard(mgrAuthorityVo);
				result = true;
			}else{
				message = "알수없는 타입입니다.";
			}
			
		}else{
			message = "필요한 정보가 없습니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("message", message);
		jsonMap.put("authorityList", authorityList);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrAuthorityRegAx.do")
	public void MgrAuthorityRegAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrAuthorityVo") MgrAuthorityVo mgrAuthorityVo, @ModelAttribute("RoleVo") RoleVo roleVo) throws Exception {
		
		boolean result = false;
		String message = "";
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");

		String searchType = mgrAuthorityVo.getSearchType();
		String mgrIdx = mgrAuthorityVo.getRoleIdx();
		
		if(!searchType.equals("") && !mgrIdx.equals("")) {
		
			String checkList = EgovStringUtil.isNullToString(request.getParameter("checkList"));
			
			JSONArray jsonArray = (JSONArray)JSONValue.parse(checkList);
			
			List<MgrAuthorityVo> list = new ArrayList<MgrAuthorityVo>();
			
			for(int i = 0; i < jsonArray.size(); i++) {
				
				MgrAuthorityVo resultVo = new MgrAuthorityVo();
				resultVo.setRegId(userid);
				
				String idx = (String) jsonArray.get(i);
				resultVo.setMaMlidx(mgrIdx);
				resultVo.setMaType(searchType);
				if(idx == null){
					idx = "0";
				}
				resultVo.setMaPkidx(idx);
				
				list.add(resultVo);
			}
			
			mgrAuthoritySvc.registMgrAuthority(mgrAuthorityVo, list);

			mgrListSvc.modifyRoleMgrSiteCd(roleVo);
			
			result = true;
			message = Message.getMessage("100.message");
		
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
	}
	
	
}