package egovframework.injeinc.boffice.sy.mgr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.sy.mgr.service.MgrSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class MgrCtr extends CmmLogCtr{
	
	@Resource(name = "MgrSvc")
	private MgrSvc mgrSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	
	 @RequestMapping(value = "/boffice/sy/mgr/Mgr_Excel_Down.do")
		public String mgrExcelTest(
				HttpServletRequest request,HttpServletResponse response, @ModelAttribute("MgrVo") MgrVo mgrVO
				, ModelMap model) throws Exception {
	    
	    	
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mgrVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(mgrVO.getPageUnit());
			paginationInfo.setPageSize(mgrVO.getPageSize());
			
			mgrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mgrVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mgrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			int totCnt = mgrSvc.retrievePagMgr(mgrVO); 
			paginationInfo.setTotalRecordCount(totCnt);
			List retrieveListMgr = mgrSvc.retrieveListMgr(mgrVO);
			if(model != null){
				model.addAttribute("paginationInfo", paginationInfo);
				model.addAttribute("resultList", retrieveListMgr);
			}
			return "injeinc/boffice/sy/mgr/excel";		
	 	}
	 
	 
	@RequestMapping(value = "/boffice/sy/mgr/Mgr_Main.do")
	public String mgrMain(
			HttpServletRequest request,HttpServletResponse response, @ModelAttribute("LoginVo") LoginVo loginVo
			, ModelMap model) throws Exception {
	
		return "injeinc/common/sample";		
 	}

	
	/** 관리자등록 메인 리스트*/
	@RequestMapping(value= "/boffice/sy/mgr/Mgr_List.do")
	public String mgrList(
			HttpServletRequest request, @ModelAttribute("MgrVo") MgrVo mgrVO
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mgrVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mgrVO.getPageUnit());
		paginationInfo.setPageSize(mgrVO.getPageSize());
		
		mgrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mgrVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mgrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = mgrSvc.retrievePagMgr(mgrVO); 
		paginationInfo.setTotalRecordCount(totCnt);
		List retrieveListMgr = mgrSvc.retrieveListMgr(mgrVO);
		if(model != null){
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("resultList", retrieveListMgr);
			model.addAttribute("MgrVo", mgrVO);
		}
		return "injeinc/boffice/sy/mgr/mgr_list";		
 	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/sy/mgr/Mgr_ListAx.do")
	public void mgrListAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String searchCode = EgovStringUtil.nullConvert(request.getParameter("searchCode"));	
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("mgrId", searchCode);
		
		HashMap<String, Object> serviceMap = mgrSvc.retriveMgrAx(param);
		
		ArrayList<CodeVo> rowDataList = (ArrayList<CodeVo>) serviceMap.get("rowDataList");
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("MgrAxValue", serviceMap.get("MgrAxValue"));
		jsonMap.put("rowDataList", rowDataList);
		
		jsonView.render(jsonMap, request, response);
			
	}
	
	
    /** 관리자 등록 */
	@RequestMapping(value= "/boffice/sy/mgr/Mgr_Form.do")
	public String mgrForm(
			HttpServletRequest request, @ModelAttribute("MgrVo") MgrVo mgrVO
			, ModelMap model) throws Exception {
		// 샘플 상위분류코드 리스트 추가
		if(model != null){
			model.addAttribute("menuGubun", mgrVO.getMgrMenu());
		}
		if(mgrVO.getMgrMenu().equals("update"))
		{
			if(!mgrVO.getMgrIdchk().equals(""))
			{
				mgrVO.setMgrId(mgrVO.getMgrIdchk());
				MgrVo result = mgrSvc.retrieveMgrUser(mgrVO);
				if(model != null){
					model.addAttribute("MgrVo", result);
				}
			}
		}
		
		return "injeinc/boffice/sy/mgr/mgr_form";		
 	}
	
	
	/** 본인 관리자 정보 수정 */
	@RequestMapping(value= "/boffice/sy/mgr/Mgr_My_Form.do")
	public String mgrMyForm(
			HttpServletRequest request, @ModelAttribute("MgrVo") MgrVo mgrVO
			, ModelMap model) throws Exception {
		// 샘플 상위분류코드 리스트 추가
		HttpSession ses = request.getSession();
		debugLog("ses:"+ses.getAttribute("SesUserId"));
		mgrVO.setMgrId((String)ses.getAttribute("SesUserId"));
		
		MgrVo result = mgrSvc.retrieveMgrUser(mgrVO);
		if(model != null){
			model.addAttribute("MgrVo", result);
		}
		return "injeinc/boffice/sy/mgr/mgr_my_form";		
 	}
	
	
	 /** 관리자 등록 액션  */
	@RequestMapping(value= "/boffice/sy/mgr/Mgr_Reg.do")
		public String mgrReg(
				HttpServletRequest request, @ModelAttribute("MgrVo") MgrVo mgrVO
				, ModelMap model) throws Exception {
		/*
		String permissionUse = mgrVO.getPermCd();
		//05010000 (최고관리자) 
		if(permissionUse.equals("05010000")){
			mgrSvc.registAllMgr(mgrVO);	
		}else{
			mgrSvc.registMgr(mgrVO);	
		}
		*/

		mgrSvc.registMgr(mgrVO);
		
		String SVC_MSGE = Message.getMessage("100.message"); //인서트성공
		return alert("/boffice/sy/mgr/Mgr_List.do", SVC_MSGE, model); 		
	 }
	
	
	 /** 관리자 등록 뷰  */
	@RequestMapping(value= "/boffice/sy/mgr/Mgr_View.do")
	public String mgrView(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrVo") MgrVo mgrVO
			, ModelMap model) throws Exception {
	    	HttpSession ses = request.getSession();
	    	
	    	mgrVO.setMgrId(mgrVO.getMgrIdchk());
	    	MgrVo result = mgrSvc.retrieveMgr(mgrVO);
	    	if(model != null){
	    		model.addAttribute("mgrVO", result);
	    	}
		
		return "injeinc/boffice/sy/mgr/mgr_view";		
	}
	
	
	 /** 관리자 등록 뷰  */
	@RequestMapping(value= "/boffice/sy/mgr/Mgr_Authority.do")
	public String mgrAuthority(
			HttpServletRequest request, @ModelAttribute("MgrVo") MgrVo mgrVO
			, ModelMap model) throws Exception {
		
			mgrVO.setMgrId(mgrVO.getMgrIdchk());
			MgrVo result = mgrSvc.retrieveMgr(mgrVO);
			debugLog("1"+result.getMgrIdx());
			if(model != null){
				model.addAttribute("mgrKey", result.getMgrIdx());
				model.addAttribute("mgrKeyId", result.getMgrId());
				model.addAttribute("mgrVO", result);
			}
		
		return "injeinc/boffice/sy/mgr/mgr_authority";		
	}
	
	/** 트리권한목록조회 */
	@RequestMapping(value = "/boffice/sy/mgr/Mgr_TreeListAx.do")
	public void mgrTreeListAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String treeUse = EgovStringUtil.nullConvert(request.getParameter("treeUse"));
		String regId = EgovStringUtil.nullConvert(request.getParameter("mgrKeyId"));
		String mgrIdx = EgovStringUtil.nullConvert(request.getParameter("mgrKey"));
		debugLog(" request.getParameter()"+ request.getParameter("mgrKey"));
		HttpSession ses = request.getSession();

		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("treeUse", treeUse);
		param.put("mgrIdx", mgrIdx);
		param.put("regId", (String)ses.getAttribute("SesUserId"));
		
		HashMap<String, Object> serviceMap = mgrSvc.retriveTreeListMgr(param);
		List<Object> rowDataList = (List<Object>) serviceMap.get("treeList");
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		jsonMap.put("rowDataList", rowDataList);
		jsonMap.put("treeList", rowDataList);
		jsonView.render(jsonMap, request, response);
			
	}
	
	/** 트리권한저장 */
	@RequestMapping(value="/boffice/sy/mgr/Mgr_TreeFromAx.do",method=RequestMethod.POST)
	@ResponseBody
	public void mgrTreeFormAx(HttpServletRequest request, HttpServletResponse response,@RequestBody String tree) throws Exception {
		infoLog("mgrTreeFormAx");
		HttpSession ses = request.getSession();
		HashMap<String,Object> param = new HashMap<String,Object> ();
			
		JSONArray arrobj = (JSONArray)JSONValue.parse(tree);
	    
		debugLog("arrobj.size()==>"+arrobj.size());
		
		for(int i=0;i<arrobj.size();i++){
			JSONObject jsonobj = (JSONObject) arrobj.get(i);
			
			
		    param.put("regId_"+i, ses.getAttribute("SesUserId"));
		    param.put("mgrKey_"+i,jsonobj.get("mgrKey"));
		    param.put("bumoKey_"+i,jsonobj.get("bumoKey"));
		    param.put("id_"+i, jsonobj.get("id"));
		    
		    debugLog(i+"==>"+param.get("regId_"+i) );
			debugLog(i+"==>"+param.get("mgrKey_"+i));
			debugLog(i+"==>"+param.get("bumoKey_"+i));
			debugLog(i+"==>"+param.get("id_"+i));
		}
		
		param.put("objsize", arrobj.size());
	
		
		HashMap<String, Object> serviceMap = mgrSvc.registTreeMgr(param);
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
	
		jsonView.render(jsonMap, request, response);	
		
	}
	
	 /** 관리자 등록 삭제  */
	@RequestMapping(value= "/boffice/sy/mgr/Mgr_Rmv.do")
	public String mgrRmv(
			HttpServletRequest request, @ModelAttribute("MgrVo") MgrVo mgrVO
			, ModelMap model) throws Exception {
		
		mgrVO.setMgrId(mgrVO.getMgrIdchk());
		mgrSvc.removeMgr(mgrVO);
		
		String SVC_MSGE = Message.getMessage("501.message"); //삭제
		return alert("/boffice/sy/mgr/Mgr_List.do", SVC_MSGE, model);		
	}
	
	
	/** 관리자 등록 수정 */
	@RequestMapping(value= "/boffice/sy/mgr/Mgr_Mod.do")
	public String mgrMod(
			HttpServletRequest request,HttpServletResponse response,@ModelAttribute("MgrVo") MgrVo mgrVO
			, ModelMap model) throws Exception {
		mgrVO.setMgrId(mgrVO.getSampleId());
		HttpSession ses = request.getSession();
		String param = "";
		mgrSvc.updateMgr(mgrVO);
		
		MgrVo result = mgrSvc.retrieveMgr(mgrVO);
		model.addAttribute("mgrVO", result);
		mgrSvc.updateMgr(mgrVO);
		String returnUrl = "";
		
		if(request.getParameter("type") == null || request.getParameter("type").equals("")){
			returnUrl = "injeinc/boffice/sy/mgr/mgr_view";		
		}else{
			String SVC_MSGE = Message.getMessage("401.message"); //수정
			returnUrl = alert("/boffice/sy/mgr/Mgr_My_Form.do", SVC_MSGE, model);
		}
		
		return returnUrl;			
	}
	
	// 관리자 패스워드 일치 여부 확인
	@RequestMapping(value="/boffice/sy/mgr/IsMatchMgrPwd.do")
	public void IsMatchMgrPwd(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("MgrVo") MgrVo mgrVO,
			ModelMap model) throws Exception {
		
			
			boolean isMgrPwdMatch = mgrSvc.isMatchMgrPwd(mgrVO);
			
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", isMgrPwdMatch);
			
			
			jsonView.render(jsonMap, request, response);
	}
	
}