package egovframework.injeinc.foffice.ex.poll.service;

import java.util.List;

import egovframework.injeinc.foffice.ex.poll.vo.PollAnswerFViewVo;
import egovframework.injeinc.foffice.ex.poll.vo.PollAnswerFVo;

public interface PollAnswerFSvc {
	
	public void registPollAnswerF(PollAnswerFVo pollAnswerFVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListPollAnswerF(PollAnswerFViewVo pollAnswerFViewVo) throws Exception;
	
}