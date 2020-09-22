package egovframework.injeinc.foffice.ex.poll.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.foffice.ex.poll.vo.PollListFVo;

public interface PollListFSvc {
	
	public PollListFVo retrievePollListF(PollListFVo pollListFVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListPollListF(PollListFVo pollListFVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListPollDepartF(PollListFVo pollListFVo) throws Exception;
	
	public Map<String, Object> retrievePagPollList(PollListFVo pollListVo) throws Exception;
	
	public Map<String, Object> retrievePagAllPollList(PollListFVo pollListVo) throws Exception;
	
}