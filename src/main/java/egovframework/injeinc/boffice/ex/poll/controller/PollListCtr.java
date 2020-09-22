package egovframework.injeinc.boffice.ex.poll.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.ex.poll.service.PollAnswerSvc;
import egovframework.injeinc.boffice.ex.poll.service.PollListSvc;
import egovframework.injeinc.boffice.ex.poll.service.PollQuestionSvc;
import egovframework.injeinc.boffice.ex.poll.service.PollUserSvc;
import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerViewVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollListVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollQuestionVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollUserVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class PollListCtr extends CmmLogCtr {
	
	@Resource(name = "PollListSvc")
	private PollListSvc pollListSvc;
	
	@Resource(name = "PollUserSvc")
	private PollUserSvc pollUserSvc;
	
	@Resource(name = "PollQuestionSvc")
	private PollQuestionSvc pollQuestionSvc;
	
	@Resource(name = "PollAnswerSvc")
	private PollAnswerSvc pollAnswerSvc;
	
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice_noDeco/ex/poll/PollListForm.do")
	public String PollListForm(@ModelAttribute("PollListVo") PollListVo pollListVo, ModelMap model) throws Exception {
		
		PollListVo result = pollListSvc.retrievePollList(pollListVo);
		
		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(pollListVo.getPageIndex());
			result.setSearchCdIdx(pollListVo.getSearchCdIdx());
			result.setSearchUse(pollListVo.getSearchUse());
			
			String plManagerTel = result.getPlManagerTel();
			String[] telArray = plManagerTel.split("-", 3);
			result.setPlManagerTel1(telArray[0]);
			result.setPlManagerTel2(telArray[1]);
			result.setPlManagerTel3(telArray[2]);
			
			String plStartDate = result.getPlStartDate();
			result.setPlStartDate1(plStartDate.substring(0, 10));
			result.setPlStartDate2(plStartDate.substring(11, 13));
			result.setPlStartDate3(plStartDate.substring(14, 16));
			
			String plEndDate = result.getPlEndDate();
			result.setPlEndDate1(plEndDate.substring(0, 10));
			result.setPlEndDate2(plEndDate.substring(11, 13));
			result.setPlEndDate3(plEndDate.substring(14, 16));
			
			model.addAttribute("PollListVo", result);
			
		}else{
			pollListVo.setActionkey("regist");
			pollListVo.setPlAuthType("N");
			pollListVo.setPlAddrYn("N");
			pollListVo.setPlTelYn("N");
			pollListVo.setPlHpYn("N");
			pollListVo.setPlEmailYn("N");
			pollListVo.setPlNumber(0);
			pollListVo.setPlResultOpenYn("N");
			pollListVo.setPlManagerTel1("02");
			pollListVo.setPlStartDate2("09");
			pollListVo.setPlEndDate2("18");
		}
		SiteVo siteVo = new SiteVo();
		List siteList = siteSvc.selectListSiteAll(siteVo);
		model.addAttribute("siteList", siteList);
		
		List departList = pollListSvc.retrieveListPollDepart(pollListVo);
		model.addAttribute("departList", departList);
		
		model.addAttribute("hourVar", "00,01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
		model.addAttribute("minuteVar", "00,10,20,30,40,50");
		
		return "injeinc/boffice/ex/poll/poll_form";
	}
	
	@RequestMapping("/boffice/ex/poll/PollListReg.do")
	public String PollListReg(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pollListVo.setRegId(userid);
		pollListVo.setPlManagerTel(pollListVo.getPlManagerTel1()+"-"+pollListVo.getPlManagerTel2()+"-"+pollListVo.getPlManagerTel3());
		pollListVo.setPlStartDate(pollListVo.getPlStartDate1()+" "+pollListVo.getPlStartDate2()+":"+pollListVo.getPlStartDate3()+":00");
		pollListVo.setPlEndDate(pollListVo.getPlEndDate1()+" "+pollListVo.getPlEndDate2()+":"+pollListVo.getPlEndDate3()+":00");
		
		pollListSvc.registPollList(pollListVo);
		
		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/poll/PollList.do", SVC_MSGE, model); 
	}
	
	@RequestMapping("/boffice/ex/poll/PollListMod.do")
	public String PollListMod(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, ModelMap model) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pollListVo.setModId(userid);
		pollListVo.setPlManagerTel(pollListVo.getPlManagerTel1()+"-"+pollListVo.getPlManagerTel2()+"-"+pollListVo.getPlManagerTel3());
		pollListVo.setPlStartDate(pollListVo.getPlStartDate1()+" "+pollListVo.getPlStartDate2()+":"+pollListVo.getPlStartDate3()+":00");
		pollListVo.setPlEndDate(pollListVo.getPlEndDate1()+" "+pollListVo.getPlEndDate2()+":"+pollListVo.getPlEndDate3()+":00");
		
		pollListSvc.modifyPollList(pollListVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pollListVo.getPageIndex());
		addParam.append("&searchCdIdx=").append(pollListVo.getSearchCdIdx());
		addParam.append("&searchUse=").append(pollListVo.getSearchUse());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/ex/poll/PollList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/ex/poll/PollListRmv.do")
	public String PollListRmv(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pollListVo.setModId(userid);
		
		pollListSvc.removePollList(pollListVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pollListVo.getPageIndex());
		addParam.append("&searchCdIdx=").append(pollListVo.getSearchCdIdx());
		addParam.append("&searchUse=").append(pollListVo.getSearchUse());
		
		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/ex/poll/PollList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice/ex/poll/PollList.do")
	public String PollList(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, ModelMap model) throws Exception {

		String SesUserPermCd = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "SesUserPermCd"));
		String SesUserDeptCd = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "SesUserDeptCd"));
		
		if(SesUserPermCd.equals("05010000")) {
			
		}else{
			pollListVo.setSearchCdIdx(SesUserDeptCd);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pollListVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(pollListVo.getPageUnit());
		paginationInfo.setPageSize(pollListVo.getPageSize());
		
		pollListVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pollListVo.setLastIndex(paginationInfo.getLastRecordIndex());
		pollListVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = pollListSvc.retrievePagPollList(pollListVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		List departList = pollListSvc.retrieveListPollDepart(pollListVo);
		model.addAttribute("departList", departList);
		
		SiteVo siteVo = new SiteVo();
		List siteList = siteSvc.selectListSiteAll(siteVo);
		model.addAttribute("siteList", siteList);
		
		return "injeinc/boffice/ex/poll/poll_list";
	}
	
	@RequestMapping("/boffice/ex/poll/PollListPlUseChangeAx.do")
	public void PollListPlUseChangeAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PollListVo") PollListVo pollListVo) throws Exception {
		
			String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
			pollListVo.setModId(userid);
			
			String plIdx = pollListVo.getPlIdx();
			String plUse = pollListVo.getPlUse();
			
			boolean result = false;
			String message = "";
			
			if(!plIdx.equals("") && !plUse.equals("")) {
				pollListSvc.modifyPollListPlUse(pollListVo);
				message = "수정에 성공하였습니다.";
				result = true;
			}else{
				message = "필요한 자료가 없습니다.";
			}

			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", result);
			jsonMap.put("message", message);
			jsonView.render(jsonMap, request, response);
			
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice/ex/poll/PollListReport.do")
	public String PollListReport(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, ModelMap model) throws Exception {

		pollListVo = pollListSvc.retrievePollList(pollListVo);
		
		if(pollListVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		model.addAttribute("PollListVo", pollListVo);
		
		PollQuestionVo PollQuestionVo = new PollQuestionVo();
		PollQuestionVo.setPlIdx(pollListVo.getPlIdx());
		
		List resultList = pollQuestionSvc.retrieveListPollQuestion(PollQuestionVo); 
		model.addAttribute("resultList", resultList);
		
		PollUserVo pollUserVo = new PollUserVo();
		pollUserVo.setPlIdx(pollListVo.getPlIdx());
		model.addAttribute("resultTotal", pollUserSvc.retrievePollUserCnt(pollUserVo));
		
		return "injeinc/boffice/ex/poll/poll_list_report";
	}
	
	@RequestMapping("/boffice/ex/poll/PollListTextReport.do")
	public String PollListTextReport(HttpServletRequest request, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, @ModelAttribute("PollAnswerViewVo") PollAnswerViewVo pollAnswerViewVo, ModelMap model) throws Exception {

		PollQuestionVo result = pollQuestionSvc.retrievePollQuestion(pollQuestionVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
			
		model.addAttribute("PollQuestionVo", result);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pollAnswerViewVo.getPageSubIndex());
		paginationInfo.setRecordCountPerPage(pollAnswerViewVo.getPageSubUnit());
		paginationInfo.setPageSize(pollAnswerViewVo.getPageSubSize());
		
		pollAnswerViewVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pollAnswerViewVo.setLastIndex(paginationInfo.getLastRecordIndex());
		pollAnswerViewVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = pollAnswerSvc.retrievePagPollAnswer(pollAnswerViewVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "injeinc/boffice/ex/poll/poll_list_text_report";
	}
}