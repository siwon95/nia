package egovframework.injeinc.boffice.ex.poll.service;

import java.util.List;

import egovframework.injeinc.boffice.ex.poll.vo.PollQuestionVo;

public interface PollQuestionSvc {
	
	public void registPollQuestion(PollQuestionVo pollQuestionVo) throws Exception;
	public void modifyPollQuestion(PollQuestionVo pollQuestionVo) throws Exception;
	public void removePollQuestion(PollQuestionVo pollQuestionVo) throws Exception;
	public PollQuestionVo retrievePollQuestion(PollQuestionVo pollQuestionVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListPollQuestion(PollQuestionVo pollQuestionVo) throws Exception;
	public int retrievePollQuestionMaxSort(PollQuestionVo pollQuestionVo) throws Exception;
	public void modifyPollQuestionSortUp(PollQuestionVo pollQuestionVo) throws Exception;
	public void modifyPollQuestionSortDown(PollQuestionVo pollQuestionVo) throws Exception;
	
}