package egovframework.injeinc.boffice.sy.userlog.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;
import egovframework.injeinc.boffice.sy.userlog.service.UserLogSvc;
import egovframework.injeinc.boffice.sy.userlog.vo.UserLogVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class UserLogCtr extends CmmLogCtr{
	@Resource(name="UserLogSvc")
	private UserLogSvc userLogSvc;
	
	@RequestMapping(value="/boffice/sy/userlog/UserLog_List.do")
	public String userLogList(HttpServletRequest request,
			@ModelAttribute("UserLogVo") UserLogVo userLogVo,
			ModelMap model) throws Exception {
		debugLog("===" +userLogVo.getLoginSucYn());
		debugLog("===" +userLogVo.getPageUnit());
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userLogVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(userLogVo.getPageUnit());
		paginationInfo.setPageSize(userLogVo.getPageSize());
		
		userLogVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userLogVo.setLastIndex(paginationInfo.getLastRecordIndex());
		userLogVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = userLogSvc.selectUserLogListTotCnt(userLogVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		List<UserLogVo> retrieveListUser = userLogSvc.retrieveListUserLog(userLogVo);
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("resultList", retrieveListUser);
		}
		return "injeinc/boffice/sy/userlog/userlog_list";
	}
	
	//일괄삭제
	@RequestMapping(value="/boffice/sy/userlog/doUserLogCheckRmv.do")
	public String userLogCheckRmv(HttpServletRequest request,
			@ModelAttribute("UserLogVo") UserLogVo userLogVo,
			ModelMap model) throws Exception {
/*		debugLog("RequestParam : " + ulIdx);
		String temp = request.getParameter("ulIdx");
		debugLog("udIdx : " + temp);*/
		debugLog("vo : " + userLogVo.getUlIdx());
		String temp = userLogVo.getUlIdx();
		String ulIdx[] = temp.split(",");
		debugLog("ulIdx : " + ulIdx.length);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		for(int i=0; i < ulIdx.length; i++) {
			param.put("ulIdx"+i, ulIdx[i]);
		}
		
		userLogSvc.userLogCheckRmv(param);
		String SVC_MSGE = Message.getMessage("501.message"); //삭제 성공
		return alert("/boffice/sy/userlog/UserLog_List.do", SVC_MSGE, model);
	}
	
	//삭제
	@RequestMapping(value="/boffice/sy/userlog/doUserLogRmv.do")
	public String userLogRmv(HttpServletRequest request,
			@ModelAttribute("UserLogVo") UserLogVo userLogVo,
			ModelMap model) throws Exception {
		userLogSvc.userLogRmv(Integer.parseInt(userLogVo.getUlIdx()));
		String SVC_MSGE = Message.getMessage("501.message"); //삭제 성공
		return alert("/boffice/sy/userlog/UserLog_List.do", SVC_MSGE, model);		
	}
	
	//DB정리(10일전 성공 실패 기록 삭제)
	@RequestMapping(value="/boffice/sy/userlog/UserLog_Clear.do")
	public String userLogClear(HttpServletRequest request,
			@ModelAttribute("UserLogVo") UserLogVo userLogVo,
			ModelMap model) throws Exception {
		userLogSvc.userLogClear();
		String SVC_MSGE = Message.getMessage("501.message"); //삭제 성공
		return alert("/boffice/sy/userlog/UserLog_List.do", SVC_MSGE, model); 
	}
	
}
