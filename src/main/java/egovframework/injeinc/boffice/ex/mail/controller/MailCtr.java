package egovframework.injeinc.boffice.ex.mail.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imoxion.sensrelay.client.ImRelayClient;
import com.imoxion.sensrelay.client.ImRelayClientProc;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.mail.vo.MailVo;
import egovframework.injeinc.boffice.ex.mail.service.MailSvc;

@Controller
public class MailCtr extends CmmLogCtr {
	
	@Resource(name = "MailSvc")
	private MailSvc mailSvc;
	
	@RequestMapping("/boffice_noDeco/ex/mail/MailForm.do")
	public String MailForm(@ModelAttribute("MailVo") MailVo mailVo, ModelMap model) throws Exception {
		return "injeinc/boffice/ex/mail/mail_form";
	}
	
	@RequestMapping("/boffice/ex/mail/MailReg.do")
	public String MailReg(HttpServletRequest request, @ModelAttribute("MailVo") MailVo mailVo, ModelMap model) throws Exception {
		
		String subject	= mailVo.getSubject();		//제목
		String to		= mailVo.getTo();			//수신자 이름
		String receipt	= mailVo.getReceipt();		//수신자 이메일
		String body		= mailVo.getBody();			//본문정보

		String SVC_MSGE = "";
		
		if(!subject.equals("") && !to.equals("") && !receipt.equals("") && !body.equals("")) {
			
			String from = "inje@inje.go.kr";

			ImRelayClientProc r = new ImRelayClientProc();
			r.setClient_key("5514ed4a3fefe7b904896177");
			r.setFrom(from);
			r.setSubject(subject);
			r.setTo(to);
			r.addReceipt(receipt);
			r.setBody(body);

			ImRelayClient rClient = r.doSend();

			String resultStatus = rClient.getResultStatus();
			String resultMsg = rClient.getResultMsg();

			if(resultStatus.equals("00")) {
				

				SVC_MSGE = Message.getMessage("100.message");
				String key = rClient.getKey();
				String clientKey = rClient.getClientKey();

//				String key = EgovFormBasedUUID.randomUUID()+"4596bcdf92462";
//				String clientKey = "5514ed4a3fefe7b904896177";
				
				mailVo.setKey(key);
				mailVo.setClientKey(clientKey);
				mailVo.setFrom(from);
				mailSvc.registMail(mailVo);
					
				System.out.println("메일전송결과 성공===>key:"+key);
				System.out.println("메일전송결과 성공===>clientKey:"+clientKey);
				
			}else{
				
				System.out.println("메일전송결과 실패===>resultStatus:"+resultStatus);
				System.out.println("메일전송결과 실패===>resultMsg:"+resultMsg);
				
			}
				
		}else{
			
			SVC_MSGE = "메일전송결과 실패===>필요한 정보가 없습니다.";
		
		}
		
		return alert("/boffice/ex/mail/MailList.do", SVC_MSGE, model); 
	}
	
	@RequestMapping("/boffice/ex/mail/MailList.do")
	public String MailList(@ModelAttribute("MailVo") MailVo mailVo, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mailVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(mailVo.getPageUnit());
		paginationInfo.setPageSize(mailVo.getPageSize());
		
		mailVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mailVo.setLastIndex(paginationInfo.getLastRecordIndex());
		mailVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = mailSvc.retrievePagMail(mailVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "injeinc/boffice/ex/mail/mail_list";
	}
}