package egovframework.injeinc.boffice.ex.poll.controller;

import java.util.HashMap;
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
import egovframework.injeinc.boffice.ex.poll.service.PollListSvc;
import egovframework.injeinc.boffice.ex.poll.service.PollQuestionSvc;
import egovframework.injeinc.boffice.ex.poll.vo.PollListVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollQuestionVo;
import egovframework.injeinc.common.util.DateUtil;

@Controller
public class PollQuestionCtr extends CmmLogCtr {
	
	@Resource(name = "PollListSvc")
	private PollListSvc pollListSvc;
	
	@Resource(name = "PollQuestionSvc")
	private PollQuestionSvc pollQuestionSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/boffice_noDeco/ex/poll/PollQuestionForm.do")
	public String PollQuestionForm(@ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, ModelMap model) throws Exception {
		
		PollQuestionVo result = pollQuestionSvc.retrievePollQuestion(pollQuestionVo);
		
		if(result != null) {
			
			result.setActionkey("modify");
			result.setPageIndex(pollQuestionVo.getPageIndex());
			result.setSearchCdIdx(pollQuestionVo.getSearchCdIdx());
			result.setSearchUse(pollQuestionVo.getSearchUse());
			result.setPlIdx(pollQuestionVo.getPlIdx());
			
			if(result.getPqItemCnt() == 0) {
				result.setPqItemCnt(4);
			}
			
			model.addAttribute("PollQuestionVo", result);
			
		}else{
			
			pollQuestionVo.setActionkey("regist");
			pollQuestionVo.setPqCheck("Y");
			pollQuestionVo.setPqEtc("N");
			pollQuestionVo.setPqItemCnt(4);
			pollQuestionVo.setPqItemDirection("R");
			
		}
		
		return "injeinc/boffice/ex/poll/poll_question_form";
	}
	
	@RequestMapping("/boffice/ex/poll/PollQuestionReg.do")
	public String PollQuestionReg(HttpServletRequest request, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pollQuestionVo.setRegId(userid);

		String[] pqItemArr = request.getParameterValues("pqItemArr");

		if(pqItemArr != null) {
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < pqItemArr.length ; i++) {
				if(i > 0) {
					sb.append("｜");
				}
				sb.append(pqItemArr[i]);
			}
			
			pollQuestionVo.setPqItemList(sb.toString());
		}else{
			pollQuestionVo.setPqItemList("");
		}

		String pqType = pollQuestionVo.getPqType();

		if(pqType.equals("radio") || pqType.equals("checkbox")) {
			
		}else if(pqType.equals("selectbox")) {
			pollQuestionVo.setPqItemDirection("R");
		}else if(pqType.equals("text") || pqType.equals("textarea")) {
			pollQuestionVo.setPqEtc("N");
			pollQuestionVo.setPqItemCnt(0);
			pollQuestionVo.setPqItemList("");
			pollQuestionVo.setPqItemDirection("R");
		}else if(pqType.equals("title")) {
			pollQuestionVo.setPqCheck("N");
			pollQuestionVo.setPqEtc("N");
			pollQuestionVo.setPqItemCnt(0);
			pollQuestionVo.setPqItemList("");
			pollQuestionVo.setPqItemDirection("R");
		}
		
		pollQuestionVo.setPqPlidx(pollQuestionVo.getPlIdx());
		
		int pqSort = pollQuestionSvc.retrievePollQuestionMaxSort(pollQuestionVo);
		pollQuestionVo.setPqSort(pqSort+1);
		
		pollQuestionSvc.registPollQuestion(pollQuestionVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pollQuestionVo.getPageIndex());
		addParam.append("&searchCdIdx=").append(pollQuestionVo.getSearchCdIdx());
		addParam.append("&searchUse=").append(pollQuestionVo.getSearchUse());
		addParam.append("&plIdx=").append(pollQuestionVo.getPlIdx());
		
		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/poll/PollQuestionList.do"+addParam.toString(), SVC_MSGE, model); 
	}
	
	@RequestMapping("/boffice/ex/poll/PollQuestionMod.do")
	public String PollQuestionMod(HttpServletRequest request, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, ModelMap model) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pollQuestionVo.setModId(userid);

		String[] pqItemArr = request.getParameterValues("pqItemArr");

		if(pqItemArr != null) {
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < pqItemArr.length ; i++) {
				if(i > 0) {
					sb.append("｜");
				}
				sb.append(pqItemArr[i]);
			}
			
			pollQuestionVo.setPqItemList(sb.toString());
		}else{
			pollQuestionVo.setPqItemList("");
		}

		String pqType = pollQuestionVo.getPqType();

		if(pqType.equals("radio") || pqType.equals("checkbox")) {
			
		}else if(pqType.equals("selectbox")) {
			pollQuestionVo.setPqItemDirection("R");
		}else if(pqType.equals("text") || pqType.equals("textarea")) {
			pollQuestionVo.setPqEtc("N");
			pollQuestionVo.setPqItemCnt(0);
			pollQuestionVo.setPqItemList("");
			pollQuestionVo.setPqItemDirection("R");
		}else if(pqType.equals("title")) {
			pollQuestionVo.setPqCheck("N");
			pollQuestionVo.setPqEtc("N");
			pollQuestionVo.setPqItemCnt(0);
			pollQuestionVo.setPqItemList("");
			pollQuestionVo.setPqItemDirection("R");
		}
		
		pollQuestionSvc.modifyPollQuestion(pollQuestionVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pollQuestionVo.getPageIndex());
		addParam.append("&searchCdIdx=").append(pollQuestionVo.getSearchCdIdx());
		addParam.append("&searchUse=").append(pollQuestionVo.getSearchUse());
		addParam.append("&plIdx=").append(pollQuestionVo.getPlIdx());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/ex/poll/PollQuestionList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/ex/poll/PollQuestionRmv.do")
	public String PollQuestionRmv(HttpServletRequest request, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pollQuestionVo.setModId(userid);
		
		PollQuestionVo resultVo = pollQuestionSvc.retrievePollQuestion(pollQuestionVo);
		
		if(resultVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert("/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		
		pollQuestionSvc.removePollQuestion(resultVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pollQuestionVo.getPageIndex());
		addParam.append("&searchCdIdx=").append(pollQuestionVo.getSearchCdIdx());
		addParam.append("&searchUse=").append(pollQuestionVo.getSearchUse());
		addParam.append("&plIdx=").append(pollQuestionVo.getPlIdx());
		
		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/ex/poll/PollQuestionList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice/ex/poll/PollQuestionList.do")
	public String PollQuestionList(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, ModelMap model) throws Exception {
		
		PollListVo result = pollListSvc.retrievePollList(pollListVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		
		model.addAttribute("PollListVo", result);
		
		String plStartDate = result.getPlStartDate();
		String nowDate = DateUtil.getCurrentDate("yyyyMMddHHmmss");
		
		boolean modifyYn = true;
		
		if(Long.parseLong(plStartDate.replace("-", "").replace(" ", "").replace(":", "")) <= Long.parseLong(nowDate)) {
			modifyYn = false;
		}
		
		pollQuestionVo.setModifyYn(modifyYn);
		
		List resultList = pollQuestionSvc.retrieveListPollQuestion(pollQuestionVo); 
		model.addAttribute("resultList", resultList);
		
		return "injeinc/boffice/ex/poll/poll_question_list";
	}
	
	@RequestMapping("/boffice/ex/poll/PollQuestionSortUpAx.do")
	public void PollQuestionSortUpAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo) throws Exception {
		
			PollQuestionVo resultVo = pollQuestionSvc.retrievePollQuestion(pollQuestionVo);
			
			boolean result = false;
			String message = "";
			
			if(resultVo != null) {
				int pqSort = resultVo.getPqSort();
				
				if(pqSort > 1) {
					pollQuestionSvc.modifyPollQuestionSortUp(resultVo);
					message = "수정에 성공하였습니다.";
					result = true;
				}else{
					message = "더 이상 순위를 올릴 수 없습니다.";
				}
			}else{
				message = "잘못된 접근가 없습니다.";
			}

			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", result);
			jsonMap.put("message", message);
			jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/ex/poll/PollQuestionSortDownAx.do")
	public void PollQuestionSortDownAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo) throws Exception {
		
			PollQuestionVo resultVo = pollQuestionSvc.retrievePollQuestion(pollQuestionVo);
			
			boolean result = false;
			String message = "";
			
			if(resultVo != null) {
				int pqSort = resultVo.getPqSort();
				int maxSort = pollQuestionSvc.retrievePollQuestionMaxSort(resultVo);
				
				if(maxSort > pqSort) {
					pollQuestionSvc.modifyPollQuestionSortDown(resultVo);
					message = "수정에 성공하였습니다.";
					result = true;
				}else{
					message = "더 이상 순위를 내릴 수 없습니다.";
				}
			}else{
				message = "잘못된 접근가 없습니다.";
			}

			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", result);
			jsonMap.put("message", message);
			jsonView.render(jsonMap, request, response);
			
	}
}