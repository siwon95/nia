package egovframework.injeinc.common.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.imoxion.sensrelay.client.ImRelayClient;
import com.imoxion.sensrelay.client.ImRelayClientProc;

import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.mail.service.MailSvc;
import egovframework.injeinc.boffice.ex.mail.vo.MailVo;

public class MailUtil {

	public boolean send(MailVo mailVo) throws Exception {
		
		boolean result = false;
		
		String subject	= mailVo == null ? "" : mailVo.getSubject();		//제목
		String to		= mailVo == null ? "" : mailVo.getTo();			//수신자 이름
		String receipt	= mailVo == null ? "" : mailVo.getReceipt();		//수신자 이메일
		String body		= mailVo == null ? "" : mailVo.getBody();			//본문정보
		
		if(!subject.equals("") && !to.equals("") && !receipt.equals("") && !body.equals("")) {

			String platform	= EgovProperties.getProperty("Globals.PLATFORM");
			
			if(platform != null){
				if(platform.equals("service")) {
				
					String from = "inje@inje.go.kr";
		
					ImRelayClientProc r = new ImRelayClientProc();
					r.setClient_key("");
					r.setFrom(from);
					r.setSubject(subject);
					r.setTo(to);
					r.addReceipt(receipt);
					r.setBody(body);
		
					ImRelayClient rClient = r.doSend();
		
					String resultStatus = rClient.getResultStatus();
					String resultMsg = rClient.getResultMsg();
		
					if(resultStatus.equals("00")) {
						
						result = true;
						String key = rClient.getKey();
						String clientKey = rClient.getClientKey();
						
						//System.out.println("메일전송결과 성공===>key:"+key);
						//System.out.println("메일전송결과 성공===>clientKey:"+clientKey);
						if(mailVo != null){
							mailVo.setKey(key);
							mailVo.setClientKey(clientKey);
							mailVo.setFrom(from);
						}
		
						HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
						HttpSession session = request.getSession();
						ServletContext conext = session.getServletContext();
						WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
						MailSvc mailSvc = (MailSvc)wContext.getBean("MailSvc");
						
						mailSvc.registMail(mailVo);
						
					}else{
						
						//System.out.println("메일전송결과 실패===>resultStatus:"+resultStatus);
						//System.out.println("메일전송결과 실패===>resultMsg:"+resultMsg);
						
					}
					
				}else{
					
					result = true;
				}
				
			}else{
				
				//System.out.println(DateUtil.getCurrentDatetime()+" 메일전송결과 실패===>필요한 정보가 없습니다.");
				
			}
		}
		
		return result;
	}
}