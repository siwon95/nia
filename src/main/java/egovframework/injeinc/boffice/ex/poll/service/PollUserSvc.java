package egovframework.injeinc.boffice.ex.poll.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.boffice.ex.poll.vo.PollUserVo;

public interface PollUserSvc {
	
	public String registPollUser(PollUserVo pollUserVo) throws Exception;
	public void modifyPollUser(PollUserVo pollUserVo) throws Exception;
	public void removePollUser(PollUserVo pollUserVo) throws Exception;
	public PollUserVo retrievePollUser(PollUserVo pollUserVo) throws Exception;
	public Map<String, Object> retrievePagPollUser(PollUserVo pollUserVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListPollUser(PollUserVo pollUserVo) throws Exception;
	public int retrievePollUserCnt(PollUserVo pollUserVo) throws Exception;
	
}