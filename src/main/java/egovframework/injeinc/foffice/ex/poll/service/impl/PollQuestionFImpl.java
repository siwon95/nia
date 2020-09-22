package egovframework.injeinc.foffice.ex.poll.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.injeinc.foffice.ex.poll.dao.PollQuestionFDao;
import egovframework.injeinc.foffice.ex.poll.service.PollQuestionFSvc;
import egovframework.injeinc.foffice.ex.poll.vo.PollQuestionFVo;

@Service("PollQuestionFSvc")
public class PollQuestionFImpl extends EgovAbstractServiceImpl implements PollQuestionFSvc {

	@Resource(name = "PollQuestionFDao")
	private PollQuestionFDao pollQuestionFDao;

	public PollQuestionFVo retrievePollQuestionF(PollQuestionFVo PollQuestionFVo) throws Exception {
		return pollQuestionFDao.selectPollQuestionF(PollQuestionFVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListPollQuestionF(PollQuestionFVo PollQuestionFVo) throws Exception {
		return pollQuestionFDao.selectListPollQuestionF(PollQuestionFVo);
	}
}