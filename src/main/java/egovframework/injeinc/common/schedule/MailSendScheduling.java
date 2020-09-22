package egovframework.injeinc.common.schedule;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.ex.mail.vo.MailVo;
import egovframework.injeinc.boffice.sy.user.service.UserSvc;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;
import egovframework.injeinc.common.util.MailUtil;

@Service("MailSendScheduling")
public class MailSendScheduling extends CmmLogCtr {
	
	@Resource(name = "UserSvc")
	private UserSvc userSvc;
	
	@SuppressWarnings("unchecked")
	public void RePiAgreeMailSend() throws Exception {
		
		String strMsg = "<br/><br/><br/><br/>감사합니다."
				   + "<br/><br/><strong><a href='#'>홈페이지 재동의 바로가기</a></strong>";

		List<UserVo> rePiAgreeUserList = userSvc.getRePiAgreeUserList();
		
		for (UserVo userVo : rePiAgreeUserList) {
			MailVo mailVo = new MailVo();
			mailVo.setSubject("개인정보 2년 주기 재동의 안내");			//메일 제목
			mailVo.setTo(userVo.getCuName());			//수신자 이름
			mailVo.setReceipt(userVo.getEmail());		//수신자 이메일
			mailVo.setBody(strMsg);	//메일 내용

			MailUtil mailUtil = new MailUtil();
			mailUtil.send(mailVo);
			
			Thread.sleep(1000);
		}
	}
	
}
