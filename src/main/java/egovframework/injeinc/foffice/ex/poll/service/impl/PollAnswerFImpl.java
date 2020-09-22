package egovframework.injeinc.foffice.ex.poll.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.injeinc.foffice.ex.poll.dao.PollAnswerFDao;
import egovframework.injeinc.foffice.ex.poll.service.PollAnswerFSvc;
import egovframework.injeinc.foffice.ex.poll.vo.PollAnswerFViewVo;
import egovframework.injeinc.foffice.ex.poll.vo.PollAnswerFVo;

@Service("PollAnswerFSvc")
public class PollAnswerFImpl extends EgovAbstractServiceImpl implements PollAnswerFSvc {

	@Resource(name = "PollAnswerFDao")
	private PollAnswerFDao pollAnswerFDao;

	public void registPollAnswerF(PollAnswerFVo pollAnswerFVo) throws Exception {
		pollAnswerFDao.createPollAnswerF(pollAnswerFVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListPollAnswerF(PollAnswerFViewVo PollAnswerFViewVo) throws Exception {
		return pollAnswerFDao.selectListPollAnswerF(PollAnswerFViewVo);
	}
}