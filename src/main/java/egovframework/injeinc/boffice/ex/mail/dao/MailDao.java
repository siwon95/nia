package egovframework.injeinc.boffice.ex.mail.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.mail.vo.MailVo;

@Repository("MailDao")
public class MailDao extends EgovAbstractMapper {
	
	public void createMail(MailVo mailVo) throws Exception {
		insert("MailDao.insertMail", mailVo);
	}
	
	public MailVo selectMail(MailVo mailVo) throws Exception {
		return (MailVo)selectOne("MailDao.selectMail", mailVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagMail(MailVo mailVo) throws Exception {
		return selectList("MailDao.selectPagMail", mailVo);
	}
	
	public int selectMailCnt(MailVo mailVo) throws Exception {
		return (Integer)selectOne("MailDao.selectMailCnt", mailVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListMail(MailVo mailVo) throws Exception {
		return selectList("MailDao.selectListMail", mailVo);
	}
	
}