package egovframework.injeinc.boffice.ex.poll.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.poll.service.PollAnswerSvc;
import egovframework.injeinc.boffice.ex.poll.service.PollUserSvc;
import egovframework.injeinc.boffice.ex.poll.service.PollListSvc;
import egovframework.injeinc.boffice.ex.poll.service.PollQuestionSvc;
import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerViewVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollUserVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollListVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollQuestionVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class PollUserCtr extends CmmLogCtr {
	
	@Resource(name = "PollListSvc")
	private PollListSvc pollListSvc;
	
	@Resource(name = "PollUserSvc")
	private PollUserSvc pollUserSvc;
	
	@Resource(name = "PollQuestionSvc")
	private PollQuestionSvc pollQuestionSvc;
	
	@Resource(name = "PollAnswerSvc")
	private PollAnswerSvc pollAnswerSvc;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice/ex/poll/PollUserView.do")
	public String PollUserView(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, @ModelAttribute("PollUserVo") PollUserVo pollUserVo, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, @ModelAttribute("PollAnswerViewVo") PollAnswerViewVo pollAnswerViewVo, ModelMap model) throws Exception {

		pollListVo = pollListSvc.retrievePollList(pollListVo);
		
		if(pollListVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		model.addAttribute("PollListVo", pollListVo);
		
		PollUserVo result = pollUserSvc.retrievePollUser(pollUserVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		
		result.setPageIndex(pollUserVo.getPageIndex());
		result.setSearchUse(pollUserVo.getSearchUse());
		result.setPlIdx(pollUserVo.getPlIdx());
		result.setPageSubIndex(pollUserVo.getPageSubIndex());
		result.setSearchSubCondition(pollUserVo.getSearchSubCondition());
		result.setSearchSubKeyword(pollUserVo.getSearchSubKeyword());
		
		model.addAttribute("PollUserVo", result);
		
		List questionList = pollQuestionSvc.retrieveListPollQuestion(pollQuestionVo);
		model.addAttribute("questionList", questionList);
		
		List answerList = pollAnswerSvc.retrieveListPollAnswer(pollAnswerViewVo);
		model.addAttribute("answerList", answerList);
		
		return "injeinc/boffice/ex/poll/poll_user_view";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice/ex/poll/PollUserForm.do")
	public String PollUserForm(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, @ModelAttribute("PollUserVo") PollUserVo pollUserVo, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, @ModelAttribute("PollAnswerViewVo") PollAnswerViewVo pollAnswerViewVo, ModelMap model) throws Exception {

		pollListVo = pollListSvc.retrievePollList(pollListVo);
		
		if(pollListVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		model.addAttribute("PollListVo", pollListVo);
		
		PollUserVo result = pollUserSvc.retrievePollUser(pollUserVo);
		
		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(pollUserVo.getPageIndex());
			result.setSearchCdIdx(pollUserVo.getSearchCdIdx());
			result.setSearchUse(pollUserVo.getSearchUse());
			result.setPlIdx(pollUserVo.getPlIdx());
			result.setPageSubIndex(pollUserVo.getPageSubIndex());
			result.setSearchSubCondition(pollUserVo.getSearchSubCondition());
			result.setSearchSubKeyword(pollUserVo.getSearchSubKeyword());
			
			if(pollListVo.getPlTelYn().equals("Y")) {
				String puTel = result.getPuTel();
				String[] telArray = puTel.split("-", 3);
				if(telArray.length > 0) {
					result.setPuTel1(telArray[0]);
				}
				if(telArray.length > 1) {
					result.setPuTel2(telArray[1]);
				}
				if(telArray.length > 2) {
					result.setPuTel3(telArray[2]);
				}
			}
			
			if(pollListVo.getPlHpYn().equals("Y")) {
				String puHp = result.getPuHp();
				String[] hpArray = puHp.split("-", 3);
				if(hpArray.length > 0) {
					result.setPuHp1(hpArray[0]);
				}
				if(hpArray.length > 1) {
					result.setPuHp2(hpArray[1]);
				}
				if(hpArray.length > 2) {
					result.setPuHp3(hpArray[2]);
				}
			}
			
			if(pollListVo.getPlEmailYn().equals("Y")) {
				String puEmail = result.getPuEmail();
				String[] emailArray = puEmail.split("@", 2);

				if(emailArray.length > 0) {
					result.setPuEmail1(emailArray[0]);
				}
				if(emailArray.length > 1) {
					result.setPuEmail2(emailArray[1]);
				}
			}
			
			model.addAttribute("PollUserVo", result);
			
			List answerList = pollAnswerSvc.retrieveListPollAnswer(pollAnswerViewVo);
			model.addAttribute("answerList", answerList);
			
		}else{
			pollUserVo.setActionkey("regist");
		}
		
		List questionList = pollQuestionSvc.retrieveListPollQuestion(pollQuestionVo);
		model.addAttribute("questionList", questionList);
		
		return "injeinc/boffice/ex/poll/poll_user_form";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/ex/poll/PollUserReg.do")
	public String PollUserReg(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, @ModelAttribute("PollUserVo") PollUserVo pollUserVo, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, ModelMap model) throws Exception {

		pollListVo = pollListSvc.retrievePollList(pollListVo);
		
		if(pollListVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert("/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		
		if(pollListVo.getPlAuthType().equals("N") || pollListVo.getPlAuthType().equals("I")) {
			pollUserVo.setPuName("");
			pollUserVo.setPuId(request.getRemoteAddr());
			pollUserVo.setRegDi(request.getRemoteAddr());
		}
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pollUserVo.setPuPlidx(pollUserVo.getPlIdx());
		pollUserVo.setRegId(userid);

		if(pollListVo.getPlTelYn().equals("Y")) {
			pollUserVo.setPuTel(pollUserVo.getPuTel1()+"-"+pollUserVo.getPuTel2()+"-"+pollUserVo.getPuTel3());
		}

		if(pollListVo.getPlHpYn().equals("Y")) {
			pollUserVo.setPuHp(pollUserVo.getPuHp1()+"-"+pollUserVo.getPuHp2()+"-"+pollUserVo.getPuHp3());
		}

		if(pollListVo.getPlEmailYn().equals("Y")) {
			pollUserVo.setPuEmail(pollUserVo.getPuEmail1()+"@"+pollUserVo.getPuEmail2());
		}
		
		String puIdx = pollUserSvc.registPollUser(pollUserVo);

		if(!puIdx.equals("")) {

			////////////////////////////////////////답변저장시작/////////////////////////////////////
			
			List<PollQuestionVo> questionList = pollQuestionSvc.retrieveListPollQuestion(pollQuestionVo);
			
			PollAnswerVo pollAnswerVo = new PollAnswerVo();
			
			pollAnswerVo.setPaPuidx(puIdx);

			for(PollQuestionVo quesionVo : questionList) {

				String pqIdx = quesionVo.getPqIdx();
				String pqType = quesionVo.getPqType();
				String pqEtc = quesionVo.getPqEtc();
				

				pollAnswerVo.setPaPqidx(pqIdx);
				pollAnswerVo.setPaAnswer("");
				pollAnswerVo.setPaText("");

				if(pqType.equals("radio")) {
					
					pollAnswerVo.setPaAnswer(EgovStringUtil.isNullToString(request.getParameter(pqIdx)));

					if(pqEtc.equals("Y")) {
						pollAnswerVo.setPaText(EgovStringUtil.isNullToString(request.getParameter(pqIdx+"_etc")));
					}

				}else if(pqType.equals("selectbox")) {
					
					pollAnswerVo.setPaAnswer(EgovStringUtil.isNullToString(request.getParameter(pqIdx)));

				}else if(pqType.equals("checkbox")) {

					String[] answerArray = request.getParameterValues(pqIdx);

					if(answerArray != null) {
						StringBuffer sb = new StringBuffer();
						for(int i = 0; i < answerArray.length ; i++) {
							if(i > 0) {
								sb.append("｜");
							}
							sb.append(answerArray[i]);
						}
						
						pollAnswerVo.setPaAnswer(sb.toString());
						
					}else{
						pollAnswerVo.setPaAnswer("");
					}

					if(pqEtc.equals("Y")) {
						pollAnswerVo.setPaText(EgovStringUtil.isNullToString(request.getParameter(pqIdx+"_etc")));
					}

				}else if(pqType.equals("text") || pqType.equals("textarea")) {

					pollAnswerVo.setPaText(EgovStringUtil.isNullToString(request.getParameter(pqIdx)));

				}

				pollAnswerSvc.registPollAnswer(pollAnswerVo);
			}
			
			////////////////////////////////////////답변저장종료/////////////////////////////////////
		}
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pollUserVo.getPageIndex());
		addParam.append("&searchCdIdx=").append(pollUserVo.getSearchCdIdx());
		addParam.append("&searchUse=").append(pollUserVo.getSearchUse());
		addParam.append("&plIdx=").append(pollUserVo.getPlIdx());
		addParam.append("&pageSubIndex=").append(pollUserVo.getPageSubIndex());
		addParam.append("&searchCondition=").append(pollUserVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pollUserVo.getSearchKeyword());
		
		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/poll/PollUserList.do"+addParam.toString(), SVC_MSGE, model); 
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/ex/poll/PollUserMod.do")
	public String PollUserMod(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, @ModelAttribute("PollUserVo") PollUserVo pollUserVo, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, ModelMap model) throws Exception {

		pollListVo = pollListSvc.retrievePollList(pollListVo);
		
		if(pollListVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert("/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pollUserVo.setModId(userid);

		if(pollListVo.getPlTelYn().equals("Y")) {
			pollUserVo.setPuTel(pollUserVo.getPuTel1()+"-"+pollUserVo.getPuTel2()+"-"+pollUserVo.getPuTel3());
		}

		if(pollListVo.getPlHpYn().equals("Y")) {
			pollUserVo.setPuHp(pollUserVo.getPuHp1()+"-"+pollUserVo.getPuHp2()+"-"+pollUserVo.getPuHp3());
		}

		if(pollListVo.getPlEmailYn().equals("Y")) {
			pollUserVo.setPuEmail(pollUserVo.getPuEmail1()+"@"+pollUserVo.getPuEmail2());
		}
		
		pollUserSvc.modifyPollUser(pollUserVo);
		
		String puIdx = pollUserVo.getPuIdx();

		////////////////////////////////////////답변저장시작/////////////////////////////////////
		
		List<PollQuestionVo> questionList = pollQuestionSvc.retrieveListPollQuestion(pollQuestionVo);
		
		PollAnswerVo pollAnswerVo = new PollAnswerVo();
		
		pollAnswerVo.setPaPuidx(puIdx);
		
		pollAnswerSvc.removePollAnswer(pollAnswerVo);

		for(PollQuestionVo quesionVo : questionList) {

			String pqIdx = quesionVo.getPqIdx();
			String pqType = quesionVo.getPqType();
			String pqEtc = quesionVo.getPqEtc();
			

			pollAnswerVo.setPaPqidx(pqIdx);
			pollAnswerVo.setPaAnswer("");
			pollAnswerVo.setPaText("");

			if(pqType.equals("radio")) {
				
				pollAnswerVo.setPaAnswer(EgovStringUtil.isNullToString(request.getParameter(pqIdx)));

				if(pqEtc.equals("Y")) {
					pollAnswerVo.setPaText(EgovStringUtil.isNullToString(request.getParameter(pqIdx+"_etc")));
				}

			}else if(pqType.equals("selectbox")) {
				
				pollAnswerVo.setPaAnswer(EgovStringUtil.isNullToString(request.getParameter(pqIdx)));

			}else if(pqType.equals("checkbox")) {

				String[] answerArray = request.getParameterValues(pqIdx);

				if(answerArray != null) {
					StringBuffer sb = new StringBuffer();
					for(int i = 0; i < answerArray.length ; i++) {
						if(i > 0) {
							sb.append("｜");
						}
						sb.append(answerArray[i]);
					}
					
					pollAnswerVo.setPaAnswer(sb.toString());
					
				}else{
					pollAnswerVo.setPaAnswer("");
				}

				if(pqEtc.equals("Y")) {
					pollAnswerVo.setPaText(EgovStringUtil.isNullToString(request.getParameter(pqIdx+"_etc")));
				}

			}else if(pqType.equals("text") || pqType.equals("textarea")) {

				pollAnswerVo.setPaText(EgovStringUtil.isNullToString(request.getParameter(pqIdx)));

			}

			pollAnswerSvc.registPollAnswer(pollAnswerVo);
		}
		
		////////////////////////////////////////답변저장종료/////////////////////////////////////
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pollUserVo.getPageIndex());
		addParam.append("&searchCdIdx=").append(pollUserVo.getSearchCdIdx());
		addParam.append("&searchUse=").append(pollUserVo.getSearchUse());
		addParam.append("&plIdx=").append(pollUserVo.getPlIdx());
		addParam.append("&pageSubIndex=").append(pollUserVo.getPageSubIndex());
		addParam.append("&searchCondition=").append(pollUserVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pollUserVo.getSearchKeyword());
		addParam.append("&puIdx=").append(pollUserVo.getPuIdx());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert(request.getContextPath()+"/boffice/ex/poll/PollUserView.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/ex/poll/PollUserRmv.do")
	public String PollUserRmv(HttpServletRequest request, @ModelAttribute("PollUserVo") PollUserVo pollUserVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pollUserVo.setModId(userid);
		
		pollUserSvc.removePollUser(pollUserVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pollUserVo.getPageIndex());
		addParam.append("&searchCdIdx=").append(pollUserVo.getSearchCdIdx());
		addParam.append("&searchUse=").append(pollUserVo.getSearchUse());
		addParam.append("&plIdx=").append(pollUserVo.getPlIdx());
		addParam.append("&pageSubIndex=").append(pollUserVo.getPageSubIndex());
		addParam.append("&searchCondition=").append(pollUserVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pollUserVo.getSearchKeyword());
		
		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/ex/poll/PollUserList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/ex/poll/PollUserList.do")
	public String PollUserList(HttpServletRequest request, @ModelAttribute("PollListVo") PollListVo pollListVo, @ModelAttribute("PollUserVo") PollUserVo pollUserVo, ModelMap model) throws Exception {

		PollListVo result = pollListSvc.retrievePollList(pollListVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert("/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		
		model.addAttribute("PollListVo", result);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pollUserVo.getPageSubIndex());
		paginationInfo.setRecordCountPerPage(pollUserVo.getPageSubUnit());
		paginationInfo.setPageSize(pollUserVo.getPageSubSize());
		
		pollUserVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pollUserVo.setLastIndex(paginationInfo.getLastRecordIndex());
		pollUserVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		String SesUserPermCd = (String) WebUtils.getSessionAttribute(request, "SesUserPermCd");
		String SesUserDeptCd = (String) WebUtils.getSessionAttribute(request, "SesUserDeptCd");
		
		if(SesUserPermCd.equals("05010000")) {
			
		}else{
			pollUserVo.setSearchCdIdx(SesUserDeptCd);
		}
		
		Map<String, Object> map = pollUserSvc.retrievePagPollUser(pollUserVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "injeinc/boffice/ex/poll/poll_user_list";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/boffice/ex/poll/PollUserListExcel.do")
	public String PollUserListExcel(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PollListVo") PollListVo pollListVo, @ModelAttribute("PollQuestionVo") PollQuestionVo pollQuestionVo, @ModelAttribute("PollUserVo") PollUserVo pollUserVo, ModelMap model) throws Exception {

		pollListVo = pollListSvc.retrievePollList(pollListVo);
		
		if(pollListVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/boffice/ex/poll/PollList.do", SVC_MSGE, model);
		}
		model.addAttribute("PollListVo", pollListVo);
		
		List<PollUserVo> resultList = pollUserSvc.retrieveListPollUser(pollUserVo); 
		model.addAttribute("resultList", resultList);
		
		List questionList = pollQuestionSvc.retrieveListPollQuestion(pollQuestionVo);
		model.addAttribute("questionList", questionList);
		
		String fileName = "설문조사결과_"+DateUtil.getCurrentDate("yyyyMMdd")+".xls";
		
		String encodedFilename = URLEncoder.encode(fileName, "UTF-8");
		
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="+encodedFilename);
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setContentType("application/vnd.ms-excel");
		
		return "injeinc/boffice/ex/poll/poll_user_list_excel";
	}
}