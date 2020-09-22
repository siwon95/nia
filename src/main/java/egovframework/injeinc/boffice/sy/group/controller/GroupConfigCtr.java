package egovframework.injeinc.boffice.sy.group.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.group.service.GroupConfigSvc;
import egovframework.injeinc.boffice.sy.group.vo.GroupConfigVo;
import egovframework.injeinc.boffice.sy.group.vo.UserGroupVo;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GroupConfigCtr extends CmmLogCtr {
	
	@Resource(name="GroupConfigSvc")
	private GroupConfigSvc groupConfigSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	//회원그룹관리 리스트
	@RequestMapping(value="/boffice/sy/group/GroupConfig_List.do")
	public String userGroupList(HttpServletRequest request,
			@ModelAttribute("GroupConfigVo") GroupConfigVo groupConfigVo,
			ModelMap model) throws Exception {
		
		debugLog("searchKeyword : " + groupConfigVo.getSearchKeyword());
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(groupConfigVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(groupConfigVo.getPageUnit());
		paginationInfo.setPageSize(groupConfigVo.getPageSize());
		
		groupConfigVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		groupConfigVo.setLastIndex(paginationInfo.getLastRecordIndex());
		groupConfigVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = groupConfigSvc.retrievePagGroupConfig(groupConfigVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		List<UserVo> retrieveListUser = groupConfigSvc.retrieveListGroupConfig(groupConfigVo);
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("resultList", retrieveListUser);
		}

		return "injeinc/boffice/sy/group/group_config_list";
	}
	//회원구룹 삭제
	@RequestMapping(value="/boffice/sy/group/GroupConfig_Rmv.do")
	public String groupConfigRmv(HttpServletRequest request,
			@ModelAttribute("GroupConfigVo") GroupConfigVo groupConfigVo,
			ModelMap model) throws Exception {
		
		groupConfigSvc.removeGroupConfig(groupConfigVo.getGcIdx());
		
		String SVC_MSGE = Message.getMessage("501.message"); //등록 성공
		return alert("/boffice/sy/group/GroupConfig_List.do", SVC_MSGE, model);
	}
	
	//회원그룹관리 등록폼
	@RequestMapping(value="/boffice_noDeco/sy/group/GroupConfig_Form.do")
	public String groupConfigForm(HttpServletRequest request,
			@ModelAttribute("GroupConfigVo") GroupConfigVo GroupConfigVo,
			ModelMap model) throws Exception {
		if(model != null){
			model.addAttribute("menuGubun", GroupConfigVo.getGcMenu());
		}
		
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		return "injeinc/boffice/sy/group/group_config_form";
	}
	
	//회원그룹관리 저장
	@RequestMapping(value="/boffice/sy/group/GroupConfig_Reg.do")
	public String groupConfigReg(HttpServletRequest request,
			@ModelAttribute("GroupConfigVo") GroupConfigVo groupConfigVo,
			ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		String regId = (String)ses.getAttribute("SesUserId");
		groupConfigVo.setRegId(regId);
		groupConfigSvc.registGroupConfig(groupConfigVo);
		
		String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
		return alert("/boffice/sy/group/GroupConfig_List.do", SVC_MSGE, model);
	}
	
	//회원그룹관리 수정
	@RequestMapping(value="/boffice/sy/group/GroupConfig_Mod.do")
	public String groupConfigMod(HttpServletRequest request,
			@ModelAttribute("GroupConfigVo") GroupConfigVo groupConfigVo,
			ModelMap model) throws Exception {
		
		//System.out.println("groupConfig : " + groupConfigVo.getGcDesc() + "END");
		HttpSession ses = request.getSession();
		String modId = (String)ses.getAttribute("SesUserId");
		groupConfigVo.setModId(modId);
		groupConfigSvc.modifyGroupConfig(groupConfigVo);
		
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		return alert("/boffice/sy/group/GroupConfig_List.do", SVC_MSGE, model);
	}
	
	//회원그룹관리 상세
	@RequestMapping(value="/boffice_noDeco/sy/group/GroupConfig_View.do")
	public String groupConfigView(HttpServletRequest request,
			@ModelAttribute("GroupConfigVo") GroupConfigVo groupConfigVo,
			@ModelAttribute("UserGroupVo") UserGroupVo userGroupVo,
			ModelMap model) throws Exception {
		
		debugLog("groupConfigVo : " + groupConfigVo);
		debugLog("userGroupVo : " + userGroupVo);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userGroupVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(userGroupVo.getPageUnit());
		paginationInfo.setPageSize(userGroupVo.getPageSize());
		
		userGroupVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userGroupVo.setLastIndex(paginationInfo.getLastRecordIndex());
		userGroupVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		userGroupVo.setGcIdx(groupConfigVo.getGcIdx());
		
		//int totCnt = groupConfigSvc.selectUserGroupListTotCnt(Integer.parseInt(groupConfigVo.getGcIdx()));
		int totCnt = groupConfigSvc.retrievePagtUserGroupMember(Integer.parseInt(groupConfigVo.getGcIdx()));//회원그룹 상세 페이지 회원 리스트
		paginationInfo.setTotalRecordCount(totCnt);
		
		GroupConfigVo view = groupConfigSvc.retrieveGroupConfig(Integer.parseInt(groupConfigVo.getGcIdx()));
		List<UserGroupVo> resultList = groupConfigSvc.retrieveListUserGroupMember(userGroupVo);
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("GroupConfigVo", view);
			model.addAttribute("resultList", resultList);
			model.addAttribute("paginationInfo", paginationInfo);
		}
		return "injeinc/boffice/sy/group/group_config_view";
	}
	
	//회원그룹 회원삭제
	@RequestMapping(value="/boffice/sy/group/UserGroup_RmvAx.do")
	public void userGroup_RmvAx(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("GroupConfigVo") GroupConfigVo GroupConfigVo,
			@ModelAttribute("UserGroupVo") UserGroupVo userGroupVo,
			ModelMap model) throws Exception {
		int cuIdx = Integer.parseInt(EgovStringUtil.nullConvert(request.getParameter("cuIdx")));
		debugLog("cuIdx : " + cuIdx);
		
		try {
			String cuId = EgovStringUtil.nullConvert(request.getParameter("cuId"));
			if(groupConfigSvc.userGroupRmvAx(cuIdx) != 1) {
				throw new Exception();
			}
			
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", "true");
			jsonView.render(jsonMap, request, response);
		} catch(Exception e) {
			errorLog("error:"+e);
		}
	}
	
	//그룹회원 리스트 팝업
	@RequestMapping(value="/boffice/sy/group/UserGroupAddList.do")
	public String userPopupList(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
		String gcIdx = EgovStringUtil.nullConvert(request.getParameter("gcIdx"));
		debugLog("UserVo : "  + userVo);
		userVo.setGcIdx(gcIdx);
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(userVo.getPageUnit());
		paginationInfo.setPageSize(userVo.getPageSize());
		
		userVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userVo.setLastIndex(paginationInfo.getLastRecordIndex());
		userVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = groupConfigSvc.retrievePagUserGroupAdd(userVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		//UserDao.selecPagtUserGroup
		List<UserVo> retrieveListUser = groupConfigSvc.retrieveListUserGroupAdd(userVo);
		if(model != null){
			model.addAttribute("gcIdx", gcIdx);
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("resultList", retrieveListUser);
		}
		return "injeinc/boffice/sy/group/user_group_add_list";
	}
	
/*	//회원그룹 회원 추가
	@RequestMapping(value="/boffice/sy/user/User_Add.do")
	public String userAdd(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			@RequestParam("gcIdx") String gcIdx,
			ModelMap model) throws Exception {
		
		String SVC_MSGE = Message.getMessage("201.message"); //등록 성공]
		return alert(request.getContextPath()+"/boffice/sy/user/User_PopupList.do", SVC_MSGE, model);
	}*/
	
	//회원그룹 회원 추가
	@RequestMapping(value="/boffice/sy/group/UserGroupAddRegAx.do")
	public void UserGroupAddRegAx(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();

		String gcIdx = EgovStringUtil.nullConvert(request.getParameter("gcIdx"));
		String cuIdx = EgovStringUtil.nullConvert(request.getParameter("cuIdx"));
		
		HttpSession ses = request.getSession();
		String regId = (String)ses.getAttribute("SesUserId");
		
		if(cuIdx != null){
			String[] cuIdx2 = cuIdx.split(",");
			
			if(cuIdx2 != null){
				for(int i=0;i<cuIdx2.length;i++){
					param.put("idx"+i,cuIdx2[i]);
				}
				param.put("idxlen", cuIdx2.length);
			}
		}
		
		param.put("regId",regId);
		param.put("gcIdx",gcIdx);
															
		HashMap<String, Object> serviceMap = groupConfigSvc.userGroupAddRegAx(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		
		jsonView.render(jsonMap, request, response);	
			
	}
	
	
}
