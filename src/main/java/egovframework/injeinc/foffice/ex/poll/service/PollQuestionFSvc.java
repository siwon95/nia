package egovframework.injeinc.foffice.ex.poll.service;

import java.util.List;

import egovframework.injeinc.foffice.ex.poll.vo.PollQuestionFVo;

public interface PollQuestionFSvc {
	public PollQuestionFVo retrievePollQuestionF(PollQuestionFVo pollQuestionFVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListPollQuestionF(PollQuestionFVo pollQuestionFVo) throws Exception;
	
}