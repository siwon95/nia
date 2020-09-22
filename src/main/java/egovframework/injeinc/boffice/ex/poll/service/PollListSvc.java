package egovframework.injeinc.boffice.ex.poll.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.boffice.ex.poll.vo.PollListVo;

public interface PollListSvc {
	
	public void registPollList(PollListVo pollListVo) throws Exception;
	public void modifyPollList(PollListVo pollListVo) throws Exception;
	public void modifyPollListPlUse(PollListVo pollListVo) throws Exception;
	public void removePollList(PollListVo pollListVo) throws Exception;
	public PollListVo retrievePollList(PollListVo pollListVo) throws Exception;
	public Map<String, Object> retrievePagPollList(PollListVo pollListVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListPollList(PollListVo pollListVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListPollDepart(PollListVo pollListVo) throws Exception;
	
}	