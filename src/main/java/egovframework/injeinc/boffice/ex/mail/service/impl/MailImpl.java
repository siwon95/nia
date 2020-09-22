package egovframework.injeinc.boffice.ex.mail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.mail.dao.MailDao;
import egovframework.injeinc.boffice.ex.mail.service.MailSvc;
import egovframework.injeinc.boffice.ex.mail.vo.MailVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("MailSvc")
public class MailImpl extends EgovAbstractServiceImpl implements MailSvc {

	@Resource(name = "MailDao")
	private MailDao mailDao;

	public void registMail(MailVo mailVo) throws Exception {
		mailDao.createMail(mailVo);
	}

	public MailVo retrieveMail(MailVo mailVo) throws Exception {
		return mailDao.selectMail(mailVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagMail(MailVo mailVo) throws Exception {
		
		List<MailVo> result = mailDao.selectPagMail(mailVo);
		int cnt = mailDao.selectMailCnt(mailVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListMail(MailVo mailVo) throws Exception {
		return mailDao.selectListMail(mailVo);
	}
	
}