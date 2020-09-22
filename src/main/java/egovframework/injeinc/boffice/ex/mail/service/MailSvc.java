package egovframework.injeinc.boffice.ex.mail.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.boffice.ex.mail.vo.MailVo;

public interface MailSvc {
	
	public void registMail(MailVo mailVo) throws Exception;
	public Map<String, Object> retrievePagMail(MailVo mailVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListMail(MailVo mailVo) throws Exception;
	
}