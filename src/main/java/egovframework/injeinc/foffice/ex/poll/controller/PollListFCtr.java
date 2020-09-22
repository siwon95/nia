package egovframework.injeinc.foffice.ex.poll.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.foffice.ex.poll.vo.PollUserFVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.poll.service.PollAnswerFSvc;
import egovframework.injeinc.foffice.ex.poll.service.PollListFSvc;
import egovframework.injeinc.foffice.ex.poll.service.PollQuestionFSvc;
import egovframework.injeinc.foffice.ex.poll.service.PollUserFSvc;
import egovframework.injeinc.foffice.ex.poll.vo.PollAnswerFViewVo;
import egovframework.injeinc.foffice.ex.poll.vo.PollListFVo;
import egovframework.injeinc.foffice.ex.poll.vo.PollQuestionFVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class PollListFCtr extends CmmLogCtr {
	
	@Resource(name = "PollListFSvc")
	private PollListFSvc pollListFSvc;
	
	@Resource(name = "PollQuestionFSvc")
	private PollQuestionFSvc pollQuestionFSvc;
	
	@Resource(name = "PollAnswerFSvc")
	private PollAnswerFSvc pollAnswerFSvc;
	
	@Resource(name = "PollUserFSvc")
	private PollUserFSvc pollUserFSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/poll/PollListF.do")
	public String PollListF(HttpServletRequest request, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {

		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pollListFVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(pollListFVo.getPageUnit());
		paginationInfo.setPageSize(pollListFVo.getPageSize());
		
		pollListFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pollListFVo.setLastIndex(paginationInfo.getLastRecordIndex());
		pollListFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		pollListFVo.setSearchUse("Y");
		Map<String, Object> map = pollListFSvc.retrievePagPollList(pollListFVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		List departList = pollListFSvc.retrieveListPollDepartF(pollListFVo);
		model.addAttribute("departList", departList);
		
		String nowDate = DateUtil.getCurrentDate("yyyyMMddHHmmss");
		model.addAttribute("nowDate", nowDate);
		
		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/ex/poll/poll_list_ing";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/poll/PollListI.do")
	public String PollListI(HttpServletRequest request, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {

		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pollListFVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(pollListFVo.getPageUnit());
		paginationInfo.setPageSize(pollListFVo.getPageSize());
		
		pollListFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pollListFVo.setLastIndex(paginationInfo.getLastRecordIndex());
		pollListFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		pollListFVo.setSearchUse("Y");
		Map<String, Object> map = pollListFSvc.retrievePagPollList(pollListFVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		List departList = pollListFSvc.retrieveListPollDepartF(pollListFVo);
		model.addAttribute("departList", departList);
		
		String nowDate = DateUtil.getCurrentDate("yyyyMMddHHmmss");
		model.addAttribute("nowDate", nowDate);
		
		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/ex/poll/poll_list_ing";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/poll/PollListE.do")
	public String PollListE(HttpServletRequest request, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {

		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pollListFVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(pollListFVo.getPageUnit());
		paginationInfo.setPageSize(pollListFVo.getPageSize());
		
		pollListFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pollListFVo.setLastIndex(paginationInfo.getLastRecordIndex());
		pollListFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		pollListFVo.setSearchUse("N");
		Map<String, Object> map = pollListFSvc.retrievePagPollList(pollListFVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		List departList = pollListFSvc.retrieveListPollDepartF(pollListFVo);
		model.addAttribute("departList", departList);
		
		String nowDate = DateUtil.getCurrentDate("yyyyMMddHHmmss");
		model.addAttribute("nowDate", nowDate);

		model.addAttribute("strDomain", strDomain);

		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/ex/poll/poll_list_end";
	}
	
	@RequestMapping("/site/{strDomain}/ex/poll/PollListFResult.do")
	public String PollListFResult(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, ModelMap model) throws Exception {
		
		pollListFVo = pollListFSvc.retrievePollListF(pollListFVo);
		String reUrl = request.getHeader("referer");
		if(pollListFVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		
		model.addAttribute("PollListFVo", pollListFVo);
		
		String plAuthType = pollListFVo.getPlAuthType();
		
		if(plAuthType.equals("A") || plAuthType.equals("L")) {
			
			String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
			String ss_level = (String) WebUtils.getSessionAttribute(request, "ss_level");

			if(plAuthType.equals("L")) {
				
				if(EgovStringUtil.isEmpty(ss_level) || !ss_level.equals("7")) {
					return alert("/site/"+strDomain+"/login/Login.do?reUrl="+reUrl, "로그인이 필요합니다.", model);
				}
				
			}else if(plAuthType.equals("A")) {
				
				if(EgovStringUtil.isEmpty(ss_dupkey)) {
					return alert("/site/{strDomain}/login/NonMember.do?reUrl="+reUrl, "본인확인 및 로그인이 필요합니다.", model);
				}
				
			}
			
		}

		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/ex/poll/poll_list_result";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/poll/PollListFReportIng.do")
	public String PollListFReportIng(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, @ModelAttribute("PollQuestionFVo") PollQuestionFVo pollQuestionFVo, @ModelAttribute("PollUserFVo") PollUserFVo pollUserFVo, ModelMap model) throws Exception {

		pollListFVo = pollListFSvc.retrievePollListF(pollListFVo);
		String reUrl = request.getHeader("referer");
		if(pollListFVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		
		model.addAttribute("PollListFVo", pollListFVo);
		
		String plAuthType = pollListFVo.getPlAuthType();
		
		if(plAuthType.equals("A") || plAuthType.equals("L")) {
			
			String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
			String ss_level = (String) WebUtils.getSessionAttribute(request, "ss_level");

			if(plAuthType.equals("L")) {
				
				if(EgovStringUtil.isEmpty(ss_level) || !ss_level.equals("7")) {
					return alert("/site/"+strDomain+"/login/Login.do?reUrl="+reUrl, "로그인이 필요합니다.", model);
				}
				
			}else if(plAuthType.equals("A")) {
				
				if(EgovStringUtil.isEmpty(ss_dupkey)) {
					return alert("/site/{strDomain}/login/NonMember.do?reUrl="+reUrl, "본인확인 및 로그인이 필요합니다.", model);
				}
				
			}
			
		}
		
		List resultList = pollQuestionFSvc.retrieveListPollQuestionF(pollQuestionFVo);
		model.addAttribute("resultList", resultList);
		
		model.addAttribute("resultTotal", pollUserFSvc.retrievePollUserFTotCnt(pollUserFVo));

		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/ex/poll/poll_list_report_ing";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/poll/PollListFReportEnd.do")
	public String PollListFReportEnd(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, @ModelAttribute("PollQuestionFVo") PollQuestionFVo pollQuestionFVo, @ModelAttribute("PollUserFVo") PollUserFVo pollUserFVo, ModelMap model) throws Exception {

		pollListFVo = pollListFSvc.retrievePollListF(pollListFVo);
		
		if(pollListFVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		String reUrl = request.getHeader("referer");
		model.addAttribute("PollListFVo", pollListFVo);
		
		String plAuthType = pollListFVo.getPlAuthType();
		
		if(plAuthType.equals("A") || plAuthType.equals("L")) {
			
			String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
			String ss_level = (String) WebUtils.getSessionAttribute(request, "ss_level");

			if(plAuthType.equals("L")) {
				
				if(EgovStringUtil.isEmpty(ss_level) || !ss_level.equals("7")) {
					return alert("/site/"+strDomain+"/login/Login.do?reUrl="+reUrl, "로그인이 필요합니다.", model);
				}
				
			}else if(plAuthType.equals("A")) {
				
				if(EgovStringUtil.isEmpty(ss_dupkey)) {
					return alert("/site/{strDomain}/login/NonMember.do?reUrl="+reUrl, "본인확인 및 로그인이 필요합니다.", model);
				}
				
			}
			
		}
		
		List resultList = pollQuestionFSvc.retrieveListPollQuestionF(pollQuestionFVo);
		model.addAttribute("resultList", resultList);
		
		model.addAttribute("resultTotal", pollUserFSvc.retrievePollUserFTotCnt(pollUserFVo));

		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/ex/poll/poll_list_report_end";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/poll/PollListFViewIng.do")
	public String PollListFViewIng(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, @ModelAttribute("PollQuestionFVo") PollQuestionFVo pollQuestionFVo, @ModelAttribute("PollUserFVo") PollUserFVo pollUserFVo, ModelMap model) throws Exception {

		String plIdx = request.getParameter("plIdx");
		pollListFVo.setPlIdx(plIdx);
		pollListFVo = pollListFSvc.retrievePollListF(pollListFVo);
		System.out.println("URL : "+request.getHeader("referer"));
		String reUrl = request.getHeader("referer");
		/**
		if(pollListFVo == null || pollListFVo.getPlAuthType().equals("N")) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		**/
		if(pollListFVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(reUrl, SVC_MSGE, model);
		}
		
		model.addAttribute("PollListFVo", pollListFVo);
		
		String plAuthType = pollListFVo.getPlAuthType();
		
		if(plAuthType.equals("A") || plAuthType.equals("L")) {
			
			String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
			String ss_level = (String) WebUtils.getSessionAttribute(request, "ss_level");

			if(plAuthType.equals("L")) {
				
				if(EgovStringUtil.isEmpty(ss_level) || !ss_level.equals("7")) {
					return alert("/site/"+strDomain+"/login/Login.do?reUrl="+reUrl, "로그인이 필요합니다.", model);
				}
				
			}else if(plAuthType.equals("A")) {
				
				if(EgovStringUtil.isEmpty(ss_dupkey)) {
					return alert("/site/{strDomain}/login/NonMember.do?reUrl="+reUrl, "본인확인 및 로그인이 필요합니다.", model);
				}
				
			}
			
		}
		
		String nowDate = DateUtil.getCurrentDate("yyyyMMddHHmmss");
		model.addAttribute("nowDate", nowDate);
		List resultList = pollQuestionFSvc.retrieveListPollQuestionF(pollQuestionFVo);
		model.addAttribute("resultList", resultList);
		
		model.addAttribute("resultTotal", pollUserFSvc.retrievePollUserFTotCnt(pollUserFVo));

		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/ex/poll/poll_list_view_ing";
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/poll/PollListFViewEnd.do")
	public String PollListFViewEnd(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, @ModelAttribute("PollQuestionFVo") PollQuestionFVo pollQuestionFVo, @ModelAttribute("PollUserFVo") PollUserFVo pollUserFVo, ModelMap model) throws Exception {

		String plIdx = request.getParameter("plIdx");
		pollListFVo.setPlIdx(plIdx);
		pollListFVo = pollListFSvc.retrievePollListF(pollListFVo);
		String reUrl = request.getHeader("referer");
		/**
		if(pollListFVo == null || pollListFVo.getPlAuthType().equals("N")) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		**/
		if(pollListFVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		
		model.addAttribute("PollListFVo", pollListFVo);
		
		String plAuthType = pollListFVo.getPlAuthType();
		
		if(plAuthType.equals("A") || plAuthType.equals("L")) {
			
			String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
			String ss_level = (String) WebUtils.getSessionAttribute(request, "ss_level");

			if(plAuthType.equals("L")) {
				
				if(EgovStringUtil.isEmpty(ss_level) || !ss_level.equals("7")) {
					return alert("/site/"+strDomain+"/login/Login.do?reUrl="+reUrl, "로그인이 필요합니다.", model);
				}
				
			}else if(plAuthType.equals("A")) {
				
				if(EgovStringUtil.isEmpty(ss_dupkey)) {
					return alert("/site/{strDomain}/login/NonMember.do?reUrl="+reUrl, "본인확인 및 로그인이 필요합니다.", model);
				}
				
			}
			
		}
		
		List resultList = pollQuestionFSvc.retrieveListPollQuestionF(pollQuestionFVo);
		model.addAttribute("resultList", resultList);
		
		model.addAttribute("resultTotal", pollUserFSvc.retrievePollUserFTotCnt(pollUserFVo));

		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/ex/poll/poll_list_view_end";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/poll/PollListFTextReportAx.do")
	public void PollListFTextReportAx(HttpServletRequest request, HttpServletResponse response, @PathVariable("strDomain") String strDomain, @ModelAttribute("PollQuestionFVo") PollQuestionFVo pollQuestionFVo, @ModelAttribute("PollAnswerFViewVo") PollAnswerFViewVo pollAnswerFViewVo, ModelMap model) throws Exception {

		boolean result = false;
		String message = "";
		List answerList = null;
		
		pollQuestionFVo = pollQuestionFSvc.retrievePollQuestionF(pollQuestionFVo);
		
		if(pollQuestionFVo != null) {
			
			PollListFVo pollListFVo = new PollListFVo();
			pollListFVo.setPlIdx(pollQuestionFVo.getPqPlidx());
			pollListFVo = pollListFSvc.retrievePollListF(pollListFVo);
		
			if(pollListFVo == null) {
				message = "잘못된 접근입니다.";
			}else if(pollListFVo.getPlResultOpenYn().equals("N")) {
				message = "비공개 설문입니다.";
			}else{
				answerList = pollAnswerFSvc.retrieveListPollAnswerF(pollAnswerFViewVo);
				result = true;
			}
			
		}else{
			message = "잘못된 접근입니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonMap.put("questionInfo", pollQuestionFVo);
		jsonMap.put("answerList", answerList);
		jsonMap.put("strDomain", strDomain);
		jsonView.render(jsonMap, request, response);
		
	}
}