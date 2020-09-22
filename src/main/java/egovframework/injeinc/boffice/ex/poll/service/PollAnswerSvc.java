package egovframework.injeinc.boffice.ex.poll.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerViewVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerVo;

public interface PollAnswerSvc {
	
	public void registPollAnswer(PollAnswerVo pollAnswerVo) throws Exception;
	public void removePollAnswer(PollAnswerVo pollAnswerVo) throws Exception;
	public Map<String, Object> retrievePagPollAnswer(PollAnswerViewVo pollAnswerViewVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListPollAnswer(PollAnswerViewVo pollAnswerViewVo) throws Exception;
	public int retrievePollAnswerCnt(PollAnswerViewVo pollAnswerViewVo) throws Exception;
	
}