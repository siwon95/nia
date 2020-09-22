package egovframework.injeinc.boffice.sy.user.controller;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.user.service.UserSvc;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.common.util.XecureUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class UserCtr extends CmmLogCtr {

	@Resource(name = "UserSvc")
	private UserSvc userSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	//회원관리 리스트
	@RequestMapping(value="/boffice/sy/user/User_List.do")
	public String userList(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(userVo.getPageUnit());
		paginationInfo.setPageSize(userVo.getPageSize());
		
		userVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userVo.setLastIndex(paginationInfo.getLastRecordIndex());
		userVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = userSvc.retrievePagUser(userVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		List<UserVo> retrieveListUser = userSvc.retrieveListUser(userVo);
		
		if(userVo.getLogKdCd() != null && !userVo.getLogKdCd().equals("")){
			//회원관리 로그 등록
			String ip = request.getHeader("X-FORWARDED-FOR");
			HttpSession ses = request.getSession();
		    if (ip == null){
		        ip = request.getRemoteAddr();
		        userVo.setIp(ip);
		        userVo.setCuId((String)ses.getAttribute("SesUserId"));
		    }
			userSvc.registUserControlLog(userVo);
		}
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("resultList", retrieveListUser);
		}
		return "injeinc/boffice/sy/user/user_list";
	}
	
	//회원상세
	@RequestMapping(value="/boffice_noDeco/sy/user/User_View.do")
	public String userView(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
		UserVo view = userSvc.retrieveUser(Integer.valueOf(userVo.getCuIdx()));
		
		String hpTemp = view.getHpNum();
		String telTemp = view.getTelNum();
		String[] hpArray = null;
		String[] telArray = null;
		
		if(hpTemp != null) {
			hpArray = hpTemp.split("-", 3);
			view.setHpNum1(hpArray[0]);
			view.setHpNum2(hpArray[1]);
			view.setHpNum3(hpArray[2]);	
		}
		
		if(telTemp != null) {
			telArray = telTemp.split("-", 3);
			view.setTelNum1(telArray[0]);
			view.setTelNum2(telArray[1]);
			view.setTelNum3(telArray[2]);
		}
		
		view.setSearchCondition(userVo.getSearchCondition());
		view.setSearchKeyword(userVo.getSearchKeyword());
		view.setPageIndex(userVo.getPageIndex());
		view.setScEmailRcvChk(userVo.getScEmailRcvChk());
		view.setScSmsRcvYn(userVo.getScSmsRcvYn());
		view.setScRegDtSt(userVo.getScRegDtSt());
		view.setScRegDtEd(userVo.getScRegDtEd());
		view.setScLastLogDtSt(userVo.getScLastLogDtSt());
		view.setScLastLogDtEd(userVo.getScLastLogDtEd());
		
		List<String> emailList = userSvc.selectEmailList();
		if(model != null){
			model.addAttribute("emailList",emailList);
			model.addAttribute("UserVo",view);
		}
		debugLog("toString : " + view);
		
		if(userVo.getLogKdCd() != null && !userVo.getLogKdCd().equals("")){
			//회원관리 로그 등록
			String ip = request.getHeader("X-FORWARDED-FOR");
			HttpSession ses = request.getSession();
		    if (ip == null){
		        ip = request.getRemoteAddr();
		        userVo.setIp(ip);
		        userVo.setCuId((String)ses.getAttribute("SesUserId"));
		    }
			userSvc.registUserControlLog(userVo);
		}
		
		return "injeinc/boffice/sy/user/user_form";
	}
	
	//회원관리 등록
	@RequestMapping(value="/boffice_noDeco/sy/user/User_Form.do")
	public String userForm(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
		List<String> emailList = userSvc.selectEmailList();
		if(model != null){
			model.addAttribute("emailList",emailList);
			model.addAttribute("menuGubun", userVo.getUserMenu());
		}
		return "injeinc/boffice/sy/user/user_form";
	}

	//회원관리 저장
	@RequestMapping(value="/boffice/sy/user/User_Reg.do")
	public String userReg(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        HttpSession ses = request.getSession();
        if (ip == null)
            ip = req.getRemoteAddr();
        
        userVo.setCuPwd(XecureUtil.DigestX64(userVo.getCuPwd()));
        userVo.setIp(ip);
        userVo.setRegId((String)ses.getAttribute("SesUserId"));
		userSvc.registUser(userVo);
		
		if(userVo.getLogKdCd() != null && !userVo.getLogKdCd().equals("")){
			//회원관리 로그 등록
			
	        userVo.setIp(ip);
	        userVo.setCuId((String)ses.getAttribute("SesUserId"));
			userSvc.registUserControlLog(userVo);
		}
		
		String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
		return alert("/boffice/sy/user/User_List.do", SVC_MSGE, model);
	}
	
	//회원관리 수정
	@RequestMapping(value="/boffice/sy/user/User_Mod.do")
	public String userMod(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		String modId = (String)ses.getAttribute("SesUserId");
		
		if(!userVo.getCuPwd().equals("")){
			userVo.setCuPwd(XecureUtil.DigestX64(userVo.getCuPwd()));
		}
		
		userVo.setModId(modId);//최종 수정자 id값
		userSvc.modifyUser(userVo);
		
		if(userVo.getLogKdCd() != null && !userVo.getLogKdCd().equals("")){
			//회원관리 로그 등록
			String ip = request.getHeader("X-FORWARDED-FOR");
		    if (ip == null){
		        ip = request.getRemoteAddr();
		        userVo.setIp(ip);
		        userVo.setCuId((String)ses.getAttribute("SesUserId"));
		    }
			userSvc.registUserControlLog(userVo);
		}
		
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		return "redirect:/boffice/sy/user/User_List.do";
	}
	
	//회원관리 아이디 중복확인
	@RequestMapping(value="/boffice/sy/user/User_ListAx.do")
	public void userListAx(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception{
		
		String cuId = EgovStringUtil.nullConvert(request.getParameter("cuId"));
		int result = userSvc.userIdCheckAx(cuId);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		if(result >= 1){
			jsonMap.put("result", "false");
		}else{
			jsonMap.put("result", "true");
		}
		jsonView.render(jsonMap, request, response);
		
	}
	
	//회원관리 비밀번호 초기화
	@RequestMapping(value="/boffice/sy/user/User_PassModAx.do")
	public void userPassModAx(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
			String cuIdx = EgovStringUtil.nullConvert(request.getParameter("cuIdx"));			
			String cuId = EgovStringUtil.nullConvert(request.getParameter("cuId"));			
			String cuPwd = EgovStringUtil.nullConvert(request.getParameter("cuPwd"));
			String cuName = EgovStringUtil.nullConvert(request.getParameter("cuName"));
			String hpNum = EgovStringUtil.nullConvert(request.getParameter("hpNum"));
			
			userVo.setCuIdx(cuIdx);
			userVo.setCuId(cuId);
			userVo.setCuPwd(XecureUtil.DigestX64(cuPwd));
			if(userSvc.userPassModAx(userVo) != 1) {
				throw new Exception();
			}
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", "true");
			
			if(request.getParameter("logKdCd") != null && !request.getParameter("logKdCd").equals("")){
				//회원관리 로그 등록
				userVo.setLogKdCd(EgovStringUtil.nullConvert(request.getParameter("logKdCd")));
				userVo.setDoSav(EgovStringUtil.nullConvert(request.getParameter("doSav")));
				
				String ip = request.getHeader("X-FORWARDED-FOR");
				HttpSession ses = request.getSession();
			    if (ip == null){
			        ip = request.getRemoteAddr();
			        userVo.setIp(ip);
			        userVo.setCuId((String)ses.getAttribute("SesUserId"));
			    }
				userSvc.registUserControlLog(userVo);
			}
			
			jsonView.render(jsonMap, request, response);
	}
	
/*	//그룹회원 리스트 팝업
	@RequestMapping(value="/boffice/sy/user/User_PopupList.do")
	public String userPopupList(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
		String gcIdx = request.getParameter("gcIdx");
		debugLog("UserVo : "  + userVo);
		userVo.setGcIdx(gcIdx);
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(userVo.getPageUnit());
		paginationInfo.setPageSize(userVo.getPageSize());
		
		userVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userVo.setLastIndex(paginationInfo.getLastRecordIndex());
		userVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = userSvc.selectUserGroupListTotCnt(userVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		//UserDao.selecPagtUserGroup
		List<UserVo> retrieveListUser = userSvc.retrievePagUserGroup(userVo);
		
		model.addAttribute("gcIdx", gcIdx);
		model.addAttribute("totCnt",totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("resultList", retrieveListUser);
		
		return "injeinc/boffice/sy/user/user_popuplist";
	}
	
	//회원그룹 회원 추가
	@RequestMapping(value="/boffice/sy/user/User_Add.do")
	public String userAdd(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			@RequestParam("gcIdx") String gcIdx,
			ModelMap model) throws Exception {
		
		String SVC_MSGE = Message.getMessage("201.message"); //등록 성공]
		return alert(request.getContextPath()+"/boffice/sy/user/User_PopupList.do", SVC_MSGE, model);
	}
	
	//회원그룹 회원 추가
	@RequestMapping(value="/boffice/sy/user/User_RegListAx.do")
	public void userRegListAx(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
	try {

		String gcIdx = request.getParameter("gcIdx");
		String cuIdx = request.getParameter("cuIdx");
		String[] cuIdx2 = cuIdx.split(",");
		
		HttpSession ses = request.getSession();
		String regId = (String)ses.getAttribute("SesUserId");
		
		for(int i=0;i<cuIdx2.length;i++){
			param.put("idx"+i,cuIdx2[i]);
		}
		param.put("regId",regId);
		param.put("gcIdx",gcIdx);
		param.put("idxlen", cuIdx2.length);
	
		HashMap<String, Object> serviceMap = userSvc.userRegListAx(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		
		jsonView.render(jsonMap, request, response);	
			
		} catch(Exception e) {
			errorLog("error:"+e);
		}		
	}
*/	
	//회원 아이디 중복 확인 팝업
	@RequestMapping(value="/boffice/sy/user/User_CheckPop.do")
	public String userCheckPopup(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
/*		int result = 0;
		
		if(userVo.getCuId() != null) {
			result = userSvc.userIdCheckAx(userVo.getCuId());
			debugLog("result Int 값 : " + result);
			if(result == 0) {
				model.addAttribute("useId", userVo.getCuId());
				model.addAttribute("useCheck","사용가능한 아이디 입니다.");
			}else {
				model.addAttribute("useCheck","이미 등록된 아이디 입니다.");
			}
		}*/
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(userVo.getPageUnit());
		paginationInfo.setPageSize(userVo.getPageSize());
		
		userVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userVo.setLastIndex(paginationInfo.getLastRecordIndex());
		userVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = userSvc.retrievePagUser(userVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		List<UserVo> retrieveListUser = userSvc.retrieveListUser(userVo);
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("resultList", retrieveListUser);
		}

		return "injeinc/boffice/sy/user/user_checkpop";
	}
	
	//주소팝업
	@RequestMapping(value="/boffice/sy/user/User_Popup.do")
	public String userPopup(HttpServletRequest request) {
		String inputYn = EgovStringUtil.nullConvert(request.getParameter("inputYn")); 
		String roadFullAddr = EgovStringUtil.nullConvert(request.getParameter("roadFullAddr")); 
		String roadAddrPart1 = EgovStringUtil.nullConvert(request.getParameter("roadAddrPart1")); 
		String roadAddrPart2 = EgovStringUtil.nullConvert(request.getParameter("roadAddrPart2")); 
		String engAddr = EgovStringUtil.nullConvert(request.getParameter("engAddr")); 
		String jibunAddr = EgovStringUtil.nullConvert(request.getParameter("jibunAddr")); 
		String zipNo = EgovStringUtil.nullConvert(request.getParameter("zipNo")); 
		String addrDetail = EgovStringUtil.nullConvert(request.getParameter("addrDetail")); 
		String admCd    = EgovStringUtil.nullConvert(request.getParameter("admCd"));
		String rnMgtSn = EgovStringUtil.nullConvert(request.getParameter("rnMgtSn"));
		String bdMgtSn  = EgovStringUtil.nullConvert(request.getParameter("bdMgtSn"));
		
		debugLog("1========>"+inputYn);
		debugLog("2========>"+roadFullAddr);
		debugLog("3========>"+roadAddrPart1);
		debugLog("4========>"+roadAddrPart2);
		debugLog("5========>"+engAddr);
		debugLog("6========>"+jibunAddr);
		debugLog("7========>"+zipNo);
		debugLog("8========>"+addrDetail);
		debugLog("9========>"+admCd);
		debugLog("10========>"+rnMgtSn);
		debugLog("11========>"+bdMgtSn);
	
		return "injeinc/boffice/sy/user/jusoPopup";
	}
	
	/** 아이디와 비번으로 회원정보 가져오기 */
	@RequestMapping(value="/boffice/user/UserSearchListPop.do")
	public String UserSearchListPop(HttpServletRequest request, @ModelAttribute("UserVo") UserVo userVo, ModelMap model) throws Exception {
		
		if(EgovStringUtil.isEmpty(userVo.getSearchCondition())) {
			userVo.setSearchCondition("2");
		}
		if(EgovStringUtil.isEmpty(userVo.getSearchKeyword())) {
			userVo.setSearchKeyword("none");
		}
		
		model.addAttribute("resultList", userSvc.retrieveListUserSearch(userVo));
		return "injeinc/boffice/sy/user/user_search_list_pop";
	}
	
	/** 아이디와 비번으로 회원정보 가져오기 */
	@RequestMapping(value="/user/UserSearchListPop2.do")
	public String UserSearchListPop2(HttpServletRequest request, @ModelAttribute("UserVo") UserVo userVo, ModelMap model) throws Exception {
		
		if(EgovStringUtil.isEmpty(userVo.getSearchCondition())) {
			userVo.setSearchCondition("2");
		}
		if(EgovStringUtil.isEmpty(userVo.getSearchKeyword())) {
			userVo.setSearchKeyword("none");
		}
		
		model.addAttribute("resultList", userSvc.retrieveListUserSearch(userVo));
		return "injeinc/boffice/sy/user/user_search_list_pop2";
	}

	/** 아이디와 비번으로 회원정보 가져오기 */
	@RequestMapping(value="/user/UserSearchPop.do")
	public String UserSearchPop(HttpServletRequest request,ModelMap model) throws Exception {
		return "injeinc/boffice/sy/user/user_search_pop";
	}
	 
	@RequestMapping(value="/boffice/sy/user/UserSearchByIdAndPwdAx.do")
	public void UserSearchByIdAndPwdAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("UserVo") UserVo userVo, ModelMap model) throws Exception {
			
		boolean result = false;
		String message = "";
		
		String cuId = userVo.getCuId();
		String cuPwd = userVo.getCuPwd();
		
		UserVo resultVo = null;
		
		if(!cuId.equals("") && !cuPwd.equals("")) {
		
			cuPwd = XecureUtil.DigestX64(cuPwd);
			userVo.setCuPwd(cuPwd);
			
			resultVo = userSvc.retrieveUserByIdAndPwd(userVo);
			
			if(resultVo != null) {
				result = true;
			}else{
				message = "조회 실패";
			}
		}else{
			message = "필요한 정보가 없습니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonMap.put("resultVo", resultVo);
		jsonView.render(jsonMap, request, response);
			
	}
	 
	@RequestMapping(value="/boffice/sy/user/UserSearchByDiAx.do")
	public void UserSearchByDiAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("UserVo") UserVo userVo, ModelMap model) throws Exception {
		boolean result = false;
		String message = "";
		
		String ownAuth = userVo.getOwnAuth();
		
		UserVo resultVo = null;
		
		if(!ownAuth.equals("")) {
			
			resultVo = userSvc.retrieveUserByDi(userVo);
			
			if(resultVo != null) {
				result = true;
			}else{
				message = "검색되는 회원이 없습니다.";
			}
		}else{
			message = "필요한 정보가 없습니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonMap.put("resultVo", resultVo);
		jsonView.render(jsonMap, request, response);
			
	}
	
	//회원 탈퇴처리
	@RequestMapping(value="/boffice/sy/user/User_Sav.do")
	public String userSavL(HttpServletRequest request,
			@ModelAttribute("UserVo") UserVo userVo,
			ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		debugLog("userVo.getCuIdx():"+userVo.getCuIdx());
		userVo.setIp(request.getRemoteAddr());
		debugLog("ip:"+request.getRemoteAddr());
		userSvc.saveUser(userVo);	//회원 탈퇴처리
		
		if(userVo.getLogKdCd() != null && !userVo.getLogKdCd().equals("")){
			//회원관리 로그 등록
			String ip = request.getHeader("X-FORWARDED-FOR");
			HttpSession ses = request.getSession();
		    if (ip == null){
		        ip = request.getRemoteAddr();
		        userVo.setIp(ip);
		        userVo.setCuId((String)ses.getAttribute("SesUserId"));
		    }
			userSvc.registUserControlLog(userVo);
			SVC_MSGE = Message.getMessage("502.message"); //탈퇴 성공
		}else{
			SVC_MSGE = Message.getMessage("901.message"); //오류 
		}
		
		return alert("/boffice/sy/user/User_List.do", SVC_MSGE, model);
	}
}
