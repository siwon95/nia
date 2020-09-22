package egovframework.injeinc.boffice.ex.umsLog.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.umsLog.vo.UmsLogVo;
import egovframework.injeinc.boffice.ex.umsLog.service.UmsLogSvc;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.common.util.UmsUtil;
import egovframework.injeinc.common.vo.UmsVo;

@Controller
public class UmsLogCtr extends CmmLogCtr {
	
	@Resource(name = "UmsLogSvc")
	private UmsLogSvc umsLogSvc;
	
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
	
	@RequestMapping("/boffice_noDeco/ex/umsLog/UmsLogForm.do")
	public String UmsLogForm(@ModelAttribute("UmsLogVo") UmsLogVo umsLogVo, ModelMap model) throws Exception {
		
		model.addAttribute("SiteCdList", cmmCodeSvc.retrieveListCmmCode("32000000"));
		
		return "injeinc/boffice/ex/umsLog/ums_log_form";
	}
	
	@RequestMapping("/boffice/ex/umsLog/UmsLogReg.do")
	public String UmsLogReg(HttpServletRequest request, @ModelAttribute("UmsLogVo") UmsLogVo umsLogVo, @ModelAttribute("UmsVo") UmsVo umsVo, ModelMap model) throws Exception {
		
//		UmsUtil umsUtil = new UmsUtil(); 
//		boolean result = umsUtil.send(umsVo);

		String SVC_MSGE = "";
/*		if(result) {
			SVC_MSGE = Message.getMessage("100.message");
		}else{
			SVC_MSGE = "문자전송 실패";
		}*/
		return alert("/boffice/ex/umsLog/UmsLogList.do", SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice_noDeco/ex/umsLog/UmsLogView.do")
	public String UmsLogView(HttpServletRequest request, @ModelAttribute("UmsLogVo") UmsLogVo umsLogVo, ModelMap model) throws Exception {
		
		UmsLogVo result = umsLogSvc.retrieveUmsLog(umsLogVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/umsLog/UmsLogList.do", SVC_MSGE, model);
		}
		
		result.setPageIndex(umsLogVo.getPageIndex());
		result.setPageUnit(umsLogVo.getPageUnit());
		result.setSearchCondition(umsLogVo.getSearchCondition());
		result.setSearchKeyword(umsLogVo.getSearchKeyword());
		result.setSearchSiteCd(umsLogVo.getSearchSiteCd());
		result.setSearchStartDt(umsLogVo.getSearchStartDt());
		result.setSearchEndDt(umsLogVo.getSearchEndDt());
		model.addAttribute("UmsLogVo", result);
		
		model.addAttribute("SiteCdList", cmmCodeSvc.retrieveListCmmCode("32000000"));
		
		return "injeinc/boffice/ex/umsLog/ums_log_view";
	}
	
	@RequestMapping("/boffice/ex/umsLog/UmsLogList.do")
	public String UmsLogList(HttpServletRequest request, @ModelAttribute("UmsLogVo") UmsLogVo UmsLogVo, ModelMap model) throws Exception {
		
		String SesUserPermCd = (String) WebUtils.getSessionAttribute(request, "SesUserPermCd");
		if(!SesUserPermCd.equals("05010000")) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert("", SVC_MSGE, model);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(UmsLogVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(UmsLogVo.getPageUnit());
		paginationInfo.setPageSize(UmsLogVo.getPageSize());
		
		UmsLogVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		UmsLogVo.setLastIndex(paginationInfo.getLastRecordIndex());
		UmsLogVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = umsLogSvc.retrievePagUmsLog(UmsLogVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("SiteCdList", cmmCodeSvc.retrieveListCmmCode("32000000"));
		
		return "injeinc/boffice/ex/umsLog/ums_log_list";
	}
	
	@RequestMapping("/boffice/ex/umsLog/UmsLogListTa.do")
	public String UmsLogListTa(HttpServletRequest request, @ModelAttribute("UmsLogVo") UmsLogVo UmsLogVo, ModelMap model) throws Exception {
		
		UmsLogVo.setSearchSiteCd("320000TA");
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(UmsLogVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(UmsLogVo.getPageUnit());
		paginationInfo.setPageSize(UmsLogVo.getPageSize());
		
		UmsLogVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		UmsLogVo.setLastIndex(paginationInfo.getLastRecordIndex());
		UmsLogVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = umsLogSvc.retrievePagUmsLog(UmsLogVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("SiteCdList", cmmCodeSvc.retrieveListCmmCode("32000000"));
		
		return "injeinc/boffice/ex/umsLog/ums_log_list_ta";
	}
	
	@RequestMapping("/boffice/ex/umsLog/UmsLogViewTa.do")
	public String UmsLogViewTa(HttpServletRequest request, @ModelAttribute("UmsLogVo") UmsLogVo umsLogVo, ModelMap model) throws Exception {
		
		UmsLogVo result = umsLogSvc.retrieveUmsLog(umsLogVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/umsLog/UmsLogListTa.do", SVC_MSGE, model);
		}
		
		result.setPageIndex(umsLogVo.getPageIndex());
		result.setPageUnit(umsLogVo.getPageUnit());
		result.setSearchCondition(umsLogVo.getSearchCondition());
		result.setSearchKeyword(umsLogVo.getSearchKeyword());
		result.setSearchSiteCd(umsLogVo.getSearchSiteCd());
		result.setSearchStartDt(umsLogVo.getSearchStartDt());
		result.setSearchEndDt(umsLogVo.getSearchEndDt());
		model.addAttribute("UmsLogVo", result);
		
		model.addAttribute("SiteCdList", cmmCodeSvc.retrieveListCmmCode("32000000"));
		
		return "injeinc/boffice/ex/umsLog/ums_log_view_ta";
	}
	
	@RequestMapping("/boffice/ex/umsLog/UmsLogListHs.do")
	public String UmsLogListHs(HttpServletRequest request, @ModelAttribute("UmsLogVo") UmsLogVo UmsLogVo, ModelMap model) throws Exception {
		
		UmsLogVo.setSearchSiteCd("320000HS");
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(UmsLogVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(UmsLogVo.getPageUnit());
		paginationInfo.setPageSize(UmsLogVo.getPageSize());
		
		UmsLogVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		UmsLogVo.setLastIndex(paginationInfo.getLastRecordIndex());
		UmsLogVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = umsLogSvc.retrievePagUmsLog(UmsLogVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("SiteCdList", cmmCodeSvc.retrieveListCmmCode("32000000"));
		
		return "injeinc/boffice/ex/umsLog/ums_log_list_hs";
	}
	
	@RequestMapping("/boffice/ex/umsLog/UmsLogViewHs.do")
	public String UmsLogViewHs(HttpServletRequest request, @ModelAttribute("UmsLogVo") UmsLogVo umsLogVo, ModelMap model) throws Exception {
		
		UmsLogVo result = umsLogSvc.retrieveUmsLog(umsLogVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/umsLog/UmsLogListHs.do", SVC_MSGE, model);
		}
		
		result.setPageIndex(umsLogVo.getPageIndex());
		result.setPageUnit(umsLogVo.getPageUnit());
		result.setSearchCondition(umsLogVo.getSearchCondition());
		result.setSearchKeyword(umsLogVo.getSearchKeyword());
		result.setSearchSiteCd(umsLogVo.getSearchSiteCd());
		result.setSearchStartDt(umsLogVo.getSearchStartDt());
		result.setSearchEndDt(umsLogVo.getSearchEndDt());
		model.addAttribute("UmsLogVo", result);
		
		model.addAttribute("SiteCdList", cmmCodeSvc.retrieveListCmmCode("32000000"));
		
		return "injeinc/boffice/ex/umsLog/ums_log_view_hs";
	}
}