package egovframework.injeinc.boffice.sy.mgr.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.group.dept.service.GroupDeptSvc;
import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.sy.mgr.service.MgrListSvc;
import egovframework.injeinc.boffice.sy.mgr.service.MgrMainConfigSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrListVo;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainConfigVo;
import egovframework.injeinc.boffice.sy.mgr.vo.RoleVo;
import egovframework.injeinc.boffice.sy.prohibit.service.ProhibitIdSvc;
import egovframework.injeinc.boffice.sy.prohibit.vo.ProhibitIdVo;
import egovframework.injeinc.boffice.sy.user.service.UserSvc;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.user.service.UserFSvc;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;

@Controller
public class MgrListCtr extends CmmLogCtr {
	 
	@Resource(name = "MgrListSvc")
	private MgrListSvc mgrListSvc;
	
	@Resource(name = "GroupDeptSvc")
	private GroupDeptSvc groupDeptSvc;
	
	@Resource(name = "ProhibitIdSvc")
	private ProhibitIdSvc prohibitIdSvc;
	
	@Resource(name = "MgrMainConfigSvc")
	private MgrMainConfigSvc mgrMainConfigSvc;

	@Resource(name = "UserSvc")
	private UserSvc userSvc;
	
	@Resource(name = "UserFSvc")
	private UserFSvc userFSvc;
	
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	@Autowired
	private CodeSvc codeSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/boffice/sy/mgr/MgrListFormAx.do")
	public void MgrListFormAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrListVo") MgrListVo mgrListVo, ModelMap model) throws Exception {

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		MgrListVo resultVo = mgrListSvc.retrieveMgrList(mgrListVo);
		
		if(resultVo != null) {
			resultVo.setActionkey("modify");
			jsonMap.put("resultVo", resultVo);
		}else{
			mgrListVo.setActionkey("regist");
			mgrListVo.setMgrIdx("");
			mgrListVo.setMgrId("");
			mgrListVo.setMgrPw("");
			mgrListVo.setMgrName("");
			mgrListVo.setMgrEmail("");
			mgrListVo.setPermCd("");
			mgrListVo.setDeptCd("");
			mgrListVo.setBasicYn("N");
			mgrListVo.setMgrUse("Y");
			jsonMap.put("resultVo", mgrListVo);
		}
		
		jsonMap.put("result", true);
		jsonMap.put("message", "성공");
		
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/sy/mgr/RoleListFormAx.do")
	public void RoleListFormAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("RoleVo") RoleVo roleVo, ModelMap model) throws Exception {

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		RoleVo resultVo = mgrListSvc.retrieveRole(roleVo);
				
		if(resultVo != null) {
			resultVo.setActionkey("modify");
			jsonMap.put("resultVo", resultVo);
		}else{
			roleVo.setActionkey("regist");
			roleVo.setRoleIdx("");
			roleVo.setRoleName("");
			roleVo.setPermCd("");
			roleVo.setUseYn("Y");
			roleVo.setPublishAuthYn("N");
			jsonMap.put("resultVo", roleVo);
		}
		
		jsonMap.put("result", true);
		jsonMap.put("message", "성공");
		
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrListRegAx.do")
	public void MgrListRegAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrListVo") MgrListVo mgrListVo, @ModelAttribute("MgrMainConfigVo") MgrMainConfigVo mgrMainConfigVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		String mgrId = mgrListVo.getMgrId();
		String mgrPw = mgrListVo.getMgrPw();
		String mgrName = mgrListVo.getMgrName();
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {
			
			if(!mgrId.equals("") && !mgrPw.equals("") && !mgrName.equals("")) {

				mgrListVo.setRegId(userid);
				String mgrIdx=mgrListSvc.registMgrList(mgrListVo);
				mgrMainConfigVo.setMgrId(mgrId);
				mgrMainConfigVo.setRegId(userid);
				mgrMainConfigVo.setMgrIdx(mgrIdx);
				mgrMainConfigSvc.registMgrMainConfig(mgrMainConfigVo);
				String SVC_MSGE = Message.getMessage("100.message");
				message = SVC_MSGE;
				result = true;
				
			}else{
				message = "필요한 정보가 부족합니다..";
			}
		}else{
			message = "잘못된 접근입니다.";
		}
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/sy/mgr/RoleListRegAx.do")
	public void RoleListRegAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("RoleVo") RoleVo roleVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		String roleName = roleVo.getRoleName();
		boolean result = false;
		String message = "";
		String actionkey = EgovStringUtil.nullConvert(request.getParameter("actionkey"));
		
		if(!EgovStringUtil.isEmpty(userid)) {
			
			if(!roleName.equals("") && !roleName.equals("")) {
				
				if(actionkey.equals("regist")){
					roleVo.setRegId(userid);
					mgrListSvc.registRole(roleVo);
				}else if(actionkey.equals("modify")){
					roleVo.setModId(userid);
					mgrListSvc.modifyRole(roleVo);
				}
				String SVC_MSGE = Message.getMessage("100.message");
				message = SVC_MSGE;
				result = true;
				
			}else{
				message = "필요한 정보가 부족합니다..";
			}
		}else{
			message = "잘못된 접근입니다.";
		}
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
	
	
	@RequestMapping("/boffice/sy/mgr/MgrListModAx.do")
	public void MgrListModAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrListVo") MgrListVo mgrListVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		String mgrPw = mgrListVo.getMgrPw();
		String mgrName = mgrListVo.getMgrName();
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {
			
			MgrListVo resultVo = mgrListSvc.retrieveMgrList(mgrListVo);
			
			if(resultVo != null) {
				
				if(!mgrPw.equals("") && !mgrName.equals("")) {

					mgrListVo.setModId(userid);
					mgrListSvc.modifyMgrList(mgrListVo);
					String SVC_MSGE = Message.getMessage("401.message");
					message = SVC_MSGE;
					result = true;
				
				}else{
					message = "필요한 정보가 부족합니다..";
				}
				
			}else{
				message = "잘못된 접근입니다.";
			}
		}else{
			message = "잘못된 접근입니다.";
		}
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrListRmvAx.do")
	public void MgrListRmvAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrListVo") MgrListVo mgrListVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {
			
			mgrListVo = mgrListSvc.retrieveMgrList(mgrListVo);
			
			if(mgrListVo != null) {

				mgrListVo.setModId(userid);
				mgrListSvc.removeMgrList(mgrListVo);
				message = Message.getMessage("501.message");
				result = true;
			}else{
				message = "잘못된 접근입니다.";
			}
			
		}else{
			message = "잘못된 접근입니다.";
		}
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrList.do")
	public String MgrList(@ModelAttribute("MgrListVo") MgrListVo mgrListVo, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mgrListVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(mgrListVo.getPageUnit());
		paginationInfo.setPageSize(mgrListVo.getPageSize());
		
		mgrListVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mgrListVo.setLastIndex(paginationInfo.getLastRecordIndex());
		mgrListVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//if(mgrListVo.getSearchDeptCd() == null || mgrListVo.getSearchDeptCd().equals("")){
			//mgrListVo.setSearchDeptCd("D000000");
		//}
		
		Map<String, Object> map = mgrListSvc.retrievePagMgrList(mgrListVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		SiteVo siteVo = new SiteVo();
		List<SiteVo> siteList = siteSvc.selectListSiteAll(siteVo);
		
		RoleVo roleVo = new RoleVo();
		roleVo.setRoleIdx("");
		
		Map<String, Object> map2 = mgrListSvc.retrieveRoleMap(roleVo);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("roleList", map2.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("siteList", siteList);
		

		model.addAttribute("departList", groupDeptSvc.retrieveListDepartGroup());
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("codeAxuse", "05000000");
		HashMap<String, Object> codeMap = codeSvc.retrieveCommonCode(param);
		model.addAttribute("codeList", codeMap.get("rowDataList"));
		
		HashMap<String, String> param2 = new HashMap<String, String>();
		param2.put("codeAxuse", "SUB_SITE_CODE");
		HashMap<String, Object> codeMap2 = codeSvc.retrieveCommonCode(param2);
		model.addAttribute("subSiteList", codeMap2.get("rowDataList"));
		
		return "injeinc/boffice/sy/mgr/mgr_list";
	}
	
	@RequestMapping("/boffice/sy/mgr/RoleList.do")
	public String RoleList(@ModelAttribute("RoleVo") RoleVo roleVo, ModelMap model) throws Exception {
		
		/*
		String searchUse = roleVo.getSearchUse();
		
		if(searchUse.equals("")) {
			roleVo.setSearchUse("Y");
		}
		*/
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(roleVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(roleVo.getPageUnit());
		paginationInfo.setPageSize(roleVo.getPageSize());
		
		roleVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		roleVo.setLastIndex(paginationInfo.getLastRecordIndex());

		roleVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = mgrListSvc.retrievePagRole(roleVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));

		model.addAttribute("paginationInfo", paginationInfo);
	/*	

		model.addAttribute("departList", groupDeptSvc.retrieveListCmsDepartGroup());
*/			
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("codeAxuse", "05000000");

		HashMap<String, Object> codeMap = codeSvc.retrieveCommonCode(param);
		model.addAttribute("codeList", codeMap.get("rowDataList"));

		return "injeinc/boffice/sy/mgr/role_list";
	}
	
	@RequestMapping("/boffice_noDeco/sy/mgr/RoleListPop.do")
	public String RoleListPop(@ModelAttribute("RoleVo") RoleVo roleVo, ModelMap model) throws Exception {
		
		Map<String, Object> map = mgrListSvc.retrieveRoleMap(roleVo); 
		
		
		model.addAttribute("resultList", map.get("resultList"));
		
		return "injeinc/boffice/sy/mgr/role_list_pop";
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrListModEtcInfoAx.do")
	public void MgrListModEtcInfoAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrListVo") MgrListVo mgrListVo) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mgrListVo.setModId(userid);
		
		String mgrIdx = mgrListVo.getMgrIdx();
		String type = EgovStringUtil.nullConvert(request.getParameter("type"));
		String value = EgovStringUtil.nullConvert(request.getParameter("value"));
		
		boolean result = false;
		String message = "";
		
		if(!mgrIdx.equals("") && !EgovStringUtil.isEmpty("type") && !EgovStringUtil.isEmpty("value")) {
			if(type.equals("basic")) {
				mgrListVo.setBasicYn(value);
			}else if(type.equals("use")) {
				mgrListVo.setMgrUse(value);
			}else if(type.equals("roleChange")) {
				mgrListVo.setRoleIdx(value);
			}
			
			mgrListSvc.modifyMgrListEtcInfo(mgrListVo);
			
			message = "수정 성공";
			result = true;
			
		}else{
			message = "필요한 자료가 없습니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
		
	@RequestMapping("/boffice/sy/mgr/MgrRoleUseYnModAx.do")
	public void MgrRoleUseYnModAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("RoleVo") RoleVo roleVo) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		roleVo.setModId(userid);
		
		String roleIdx = roleVo.getRoleIdx();
		String value = EgovStringUtil.nullConvert(request.getParameter("value"));
		roleVo.setUseYn(value);
		boolean result = false;
		String message = "";
		
		if(!roleIdx.equals("") && !EgovStringUtil.isEmpty("value")) {
			
			mgrListSvc.modifyMgrRoleUseYn(roleVo);
			
			message = "수정 성공";
			result = true;
			
		}else{
			message = "필요한 자료가 없습니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
		
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrRolePublishAuthYnModAx.do")
	public void MgrRolePublishAuthYnModAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("RoleVo") RoleVo roleVo) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		roleVo.setModId(userid);
		
		String roleIdx = roleVo.getRoleIdx();
		String value = EgovStringUtil.nullConvert(request.getParameter("value"));
		roleVo.setPublishAuthYn(value);
		boolean result = false;
		String message = "";
		
		if(!roleIdx.equals("") && !EgovStringUtil.isEmpty("value")) {
			
			mgrListSvc.modifyMgrRolePublishAuthYn(roleVo);
			
			message = "수정 성공";
			result = true;
			
		}else{
			message = "필요한 자료가 없습니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
		
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrListCheckIdAx.do")
	public void MgrListCheckIdAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrListVo") MgrListVo mgrListVo) throws Exception {
		
		String mgrId = mgrListVo.getMgrId();
		
		boolean result = false;
		String message = "";
		
		if(!mgrId.equals("")) {
			
			int count = mgrListSvc.retrieveMgrListCntCheckId(mgrListVo);

			ProhibitIdVo prohibitIdVo = new ProhibitIdVo();
			prohibitIdVo.setPiId(mgrId);
			count += prohibitIdSvc.retrieveProhibitIdCnt(prohibitIdVo);
			
			
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("cuId", mgrId);
			
			if(count > 0) {
				message = "이미 사용중인 아이디 입니다.";
			}else{
				message = "사용가능한 아이디 입니다.";
				result = true;
			}
			
		}else{
			message = "필요한 자료가 없습니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
		
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrMyForm.do")
	public String MgrMyForm(HttpServletRequest request, ModelMap model) throws Exception {
		
		String SesMgrIdx = (String) WebUtils.getSessionAttribute(request, "SesMgrIdx");
		
		MgrListVo mgrListVo = new MgrListVo();
		mgrListVo.setMgrIdx(SesMgrIdx);

		MgrListVo resultVo = mgrListSvc.retrieveMgrList(mgrListVo);
		
		if(resultVo == null) {
			String SVC_MSGE = "잘못된 접근입니다."; 
			return alert("/boffice/login/Login.do", SVC_MSGE, model);
		}

		model.addAttribute("MgrListVo", resultVo);
		
		return "injeinc/boffice/sy/mgr/mgr_my_form";
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrListModMyInfo.do")
	public String MgrListModMyInfo(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrListVo") MgrListVo mgrListVo, ModelMap model) throws Exception {

		String SesMgrIdx = (String) WebUtils.getSessionAttribute(request, "SesMgrIdx");
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		String mgrPw = mgrListVo.getMgrPw();
			
		if(!EgovStringUtil.isEmpty(SesMgrIdx) && !EgovStringUtil.isEmpty(userid) && !mgrPw.equals("")) {

			mgrListVo.setMgrIdx(SesMgrIdx);
			mgrListVo.setModId(userid);
			mgrListSvc.modifyMgrListMyInfo(mgrListVo);
			
		}else{
			String SVC_MSGE = "잘못된 접근입니다."; 
			return alert("/boffice/login/Login.do", SVC_MSGE, model);
		}

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/sy/mgr/MgrMyForm.do", SVC_MSGE, model);
		
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrRoleRmvAx.do")
	public void MgrRoleRmvAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("RoleVo") RoleVo roleVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {
			
			
			if(roleVo.getRoleIdx() != null) {

				mgrListSvc.removeRole(roleVo);
				message = Message.getMessage("501.message");
				result = true;
			}else{
				message = "잘못된 접근입니다.";
			}
			
		}else{
			message = "잘못된 접근입니다.";
		}
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
}