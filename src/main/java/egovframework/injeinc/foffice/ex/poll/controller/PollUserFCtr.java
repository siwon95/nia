package egovframework.injeinc.foffice.ex.poll.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.foffice.ex.poll.service.PollAnswerFSvc;
import egovframework.injeinc.foffice.ex.poll.service.PollUserFSvc;
import egovframework.injeinc.foffice.ex.poll.service.PollListFSvc;
import egovframework.injeinc.foffice.ex.poll.service.PollQuestionFSvc;
import egovframework.injeinc.foffice.ex.poll.vo.PollAnswerFViewVo;
import egovframework.injeinc.foffice.ex.poll.vo.PollAnswerFVo;
import egovframework.injeinc.foffice.ex.poll.vo.PollUserFVo;
import egovframework.injeinc.foffice.ex.poll.vo.PollListFVo;
import egovframework.injeinc.foffice.ex.poll.vo.PollQuestionFVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class PollUserFCtr extends CmmLogCtr {
	
	@Resource(name = "PollListFSvc")
	private PollListFSvc pollListFSvc;
	
	@Resource(name = "PollUserFSvc")
	private PollUserFSvc pollUserFSvc;
	
	@Resource(name = "PollQuestionFSvc")
	private PollQuestionFSvc pollQuestionFSvc;
	
	@Resource(name = "PollAnswerFSvc")
	private PollAnswerFSvc pollAnswerFSvc;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/poll/PollUserFForm.do")
	public String PollUserFForm(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, @ModelAttribute("PollUserFVo") PollUserFVo pollUserFVo, @ModelAttribute("PollQuestionFVo") PollQuestionFVo pollQuestionFVo, @ModelAttribute("PollAnswerFViewVo") PollAnswerFViewVo pollAnswerFViewVo, ModelMap model) throws Exception {
		System.out.println("plidx : "+pollListFVo.getPlIdx());
		pollListFVo = pollListFSvc.retrievePollListF(pollListFVo);
		
		if(pollListFVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		model.addAttribute("PollListFVo", pollListFVo);
		
		int plNumber =  pollListFVo.getPlNumber();
		int totalCnt =  pollListFVo.getTotalCnt();
		
		if(plNumber < totalCnt) {
			String SVC_MSGE = "설문이 마감되었습니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		
		String plStartDate = pollListFVo.getPlStartDate();
		String plEndDate = pollListFVo.getPlEndDate();
		String nowDate = DateUtil.getCurrentDate("yyyyMMddHHmmss");
		String reUrl = request.getHeader("referer");
		Long longPlStartDate = Long.parseLong(plStartDate.replace("-", "").replace(" ", "").replace(":", ""));
		Long longPlEndDate = Long.parseLong(plEndDate.replace("-", "").replace(" ", "").replace(":", ""));
		Long longNowDate = Long.parseLong(nowDate);
		
		if(longNowDate < longPlStartDate || longPlEndDate < longNowDate) {
			String SVC_MSGE = "설문참여 기간이 아닙니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		
		String plAuthType = pollListFVo.getPlAuthType();

		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		String ss_level = (String) WebUtils.getSessionAttribute(request, "ss_level");
		String ss_numgubun = (String) WebUtils.getSessionAttribute(request, "ss_numgubun");
		
		if(plAuthType.equals("L")) {
			
			if(EgovStringUtil.isEmpty(ss_level) || !ss_level.equals("7")) {
				return alert("/site/"+strDomain+"/login/Login.do?reUrl="+reUrl, "로그인이 필요합니다.", model);
			}
			
		}else if(plAuthType.equals("A")) {
			
			if(EgovStringUtil.isEmpty(ss_dupkey)) {
				return alert("/site/"+strDomain+"/login/NonMember.do?reUrl="+reUrl, "본인확인 및 로그인이 필요합니다.", model);
			}
			
		}

		if(plAuthType.equals("L") || plAuthType.equals("A")) {
			
			pollUserFVo.setRegDi(ss_dupkey);
			int count = pollUserFSvc.retrievePollUserFCnt(pollUserFVo);
			
			if(count > 0) {
				String SVC_MSGE = "이 설문에 이미 참여 하셨습니다.";
				return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
				
			}
			
		}else if(plAuthType.equals("I")) {
			
			pollUserFVo.setRegDi(request.getRemoteAddr());
			int count = pollUserFSvc.retrievePollUserFCnt(pollUserFVo);
			
			if(count > 0) {
				String SVC_MSGE = "이 설문에 이미 참여 하셨습니다.";
				return alert(request.getContextPath()+"/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
				
			}
			
		}
		
		String cuid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		if(cuid != null && !cuid.equals("")) {

			if(pollListFVo.getPlAddrYn().equals("Y")) {

				String ss_zip = (String) WebUtils.getSessionAttribute(request, "ss_zip");
				String ss_addr1 = (String) WebUtils.getSessionAttribute(request, "ss_addr1");
				String ss_addr2 = (String) WebUtils.getSessionAttribute(request, "ss_addr2");
				
				pollUserFVo.setPuZip(ss_zip);
				pollUserFVo.setPuAddr1(ss_addr1);
				pollUserFVo.setPuAddr2(ss_addr2);
				
			}			
			if(pollListFVo.getPlTelYn().equals("Y")) {

				String ss_tel = (String) WebUtils.getSessionAttribute(request, "ss_tel");
				if(ss_numgubun.equals("H") || ss_numgubun.equals("C")){
					ss_tel = EgovStringUtil.isNullToString(ss_tel);
					String[] telArray = ss_tel.split("-", 3);
					if(telArray.length > 0) {
						pollUserFVo.setPuTel1(telArray[0]);
					}
					if(telArray.length > 1) {
						pollUserFVo.setPuTel2(telArray[1]);
					}
					if(telArray.length > 2) {
						pollUserFVo.setPuTel3(telArray[2]);
					}
				}								
			}

			if(pollListFVo.getPlHpYn().equals("Y")) {
				
				String ss_hp = (String) WebUtils.getSessionAttribute(request, "ss_tel");
				if(ss_numgubun.equals("M")){
					ss_hp = EgovStringUtil.isNullToString(ss_hp);
					String[] hpArray = ss_hp.split("-", 3);
					if(hpArray.length > 0) {
						pollUserFVo.setPuHp1(hpArray[0]);
					}
					if(hpArray.length > 1) {
						pollUserFVo.setPuHp2(hpArray[1]);
					}
					if(hpArray.length > 2) {
						pollUserFVo.setPuHp3(hpArray[2]);
					}
				}								
			}

			if(pollListFVo.getPlEmailYn().equals("Y")) {
				
				String ss_email = (String) WebUtils.getSessionAttribute(request, "ss_email");
				ss_email = EgovStringUtil.isNullToString(ss_email);
				String[] emailArray = ss_email.split("@", 2);
				if(emailArray.length > 0) {
					pollUserFVo.setPuEmail1(emailArray[0]);
				}
				if(emailArray.length > 1) {
					pollUserFVo.setPuEmail2(emailArray[1]);
				}
				
			}
			
		}

		if(pollListFVo.getPlAddrYn().equals("Y") || pollListFVo.getPlTelYn().equals("Y") || pollListFVo.getPlHpYn().equals("Y") || pollListFVo.getPlEmailYn().equals("Y")) {
			pollUserFVo.setAgreeYn(true);
		}
		
		List questionList = pollQuestionFSvc.retrieveListPollQuestionF(pollQuestionFVo);
		model.addAttribute("strDomain", strDomain);
		model.addAttribute("ss_numgubun", ss_numgubun);
		model.addAttribute("PollUserFVo", pollUserFVo);
		model.addAttribute("questionList", questionList);
		
		return "injeinc/foffice/ex/poll/poll_user_form";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/site/{strDomain}/ex/poll/PollUserFReg.do")
	public String PollUserFReg(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("PollListFVo") PollListFVo pollListFVo, @ModelAttribute("PollUserFVo") PollUserFVo pollUserFVo, @ModelAttribute("PollQuestionFVo") PollQuestionFVo pollQuestionFVo, ModelMap model) throws Exception {

		pollListFVo = pollListFSvc.retrievePollListF(pollListFVo);
		
		if(pollListFVo == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert("/site/"+strDomain+"/ex/poll/PollListF.do", SVC_MSGE, model);
		}
		model.addAttribute("PollListFVo", pollListFVo);
		
		String plAuthType = pollListFVo.getPlAuthType();

		String ss_id = (String) WebUtils.getSessionAttribute(request, "ss_id");
		String ss_name = (String) WebUtils.getSessionAttribute(request, "ss_name");
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		String ss_level = (String) WebUtils.getSessionAttribute(request, "ss_level");
		String reUrl = request.getHeader("referer");
		if(plAuthType.equals("N") || plAuthType.equals("I")) {

			if(EgovStringUtil.isEmpty(ss_dupkey)) {
				pollUserFVo.setPuName("");
				pollUserFVo.setPuId(request.getRemoteAddr());
				pollUserFVo.setRegId(request.getRemoteAddr());
				pollUserFVo.setRegDi(request.getRemoteAddr());
			}else{
				pollUserFVo.setPuName(ss_name);
				pollUserFVo.setPuId(ss_id);
				pollUserFVo.setRegId(ss_id);
				pollUserFVo.setRegDi(ss_dupkey);
			}
			
		}else if(plAuthType.equals("A") || plAuthType.equals("L")) {

			if(plAuthType.equals("L")) {
				
				if(EgovStringUtil.isEmpty(ss_level) || !ss_level.equals("7")) {
					String SVC_MSGE = "로그인을 하셔야 참여가능합니다.";
					return alert("/site/${strDomain}/login/Login.do?reUrl="+reUrl, "로그인이 필요합니다.", model);
				}
				
			}else if(plAuthType.equals("A")) {
				
				if(EgovStringUtil.isEmpty(ss_dupkey)) {
					return alert("/site/{strDomain}/login/NonMember.do?reUrl="+reUrl, "본인확인 및 로그인이 필요합니다.", model);
				}
				
			}

			pollUserFVo.setPuId(ss_id);
			pollUserFVo.setPuName(ss_name);
			pollUserFVo.setRegId(ss_id);
			pollUserFVo.setRegDi(ss_dupkey);
			
		}

		if(pollListFVo.getPlTelYn().equals("Y")) {
			pollUserFVo.setPuTel(pollUserFVo.getPuTel1()+"-"+pollUserFVo.getPuTel2()+"-"+pollUserFVo.getPuTel3());
		}

		if(pollListFVo.getPlHpYn().equals("Y")) {
			pollUserFVo.setPuHp(pollUserFVo.getPuHp1()+"-"+pollUserFVo.getPuHp2()+"-"+pollUserFVo.getPuHp3());
		}

		if(pollListFVo.getPlEmailYn().equals("Y")) {
			pollUserFVo.setPuEmail(pollUserFVo.getPuEmail1()+"@"+pollUserFVo.getPuEmail2());
		}

		pollUserFVo.setPuPlidx(pollUserFVo.getPlIdx());
		
		String puIdx = pollUserFSvc.registPollUserF(pollUserFVo);

		if(!puIdx.equals("")) {

			////////////////////////////////////////답변저장시작/////////////////////////////////////
			
			List<PollQuestionFVo> questionList = pollQuestionFSvc.retrieveListPollQuestionF(pollQuestionFVo);
			
			PollAnswerFVo pollAnswerFVo = new PollAnswerFVo();
			
			pollAnswerFVo.setPaPuidx(puIdx);

			for(PollQuestionFVo quesionVo : questionList) {

				String pqIdx = quesionVo.getPqIdx();
				String pqType = quesionVo.getPqType();
				String pqEtc = quesionVo.getPqEtc();
				

				pollAnswerFVo.setPaPqidx(pqIdx);
				pollAnswerFVo.setPaAnswer("");
				pollAnswerFVo.setPaText("");

				if(pqType.equals("radio")) {
					
					pollAnswerFVo.setPaAnswer(EgovStringUtil.isNullToString(request.getParameter(pqIdx)));

					if(pqEtc.equals("Y")) {
						pollAnswerFVo.setPaText(EgovStringUtil.isNullToString(request.getParameter(pqIdx+"_etc")));
					}

				}else if(pqType.equals("selectbox")) {
					
					pollAnswerFVo.setPaAnswer(EgovStringUtil.isNullToString(request.getParameter(pqIdx)));

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
						
						pollAnswerFVo.setPaAnswer(sb.toString());
						
					}else{
						pollAnswerFVo.setPaAnswer("");
					}

					if(pqEtc.equals("Y")) {
						pollAnswerFVo.setPaText(EgovStringUtil.isNullToString(request.getParameter(pqIdx+"_etc")));
					}

				}else if(pqType.equals("text") || pqType.equals("textarea")) {

					pollAnswerFVo.setPaText(EgovStringUtil.isNullToString(request.getParameter(pqIdx)));

				}

				pollAnswerFSvc.registPollAnswerF(pollAnswerFVo);
			}
			
			////////////////////////////////////////답변저장종료/////////////////////////////////////
		}
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?plIdx=").append(pollListFVo.getPlIdx());
		
		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/site/"+strDomain+"/ex/poll/PollListI.do"+addParam.toString(), SVC_MSGE, model);
	}
}