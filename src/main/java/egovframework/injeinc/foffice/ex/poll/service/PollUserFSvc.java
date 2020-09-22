package egovframework.injeinc.foffice.ex.poll.service;

import egovframework.injeinc.foffice.ex.poll.vo.PollUserFVo;

public interface PollUserFSvc {
	
	public String registPollUserF(PollUserFVo pollUserFVo) throws Exception;
	public int retrievePollUserFCnt(PollUserFVo pollUserFVo) throws Exception;
	public int retrievePollUserFTotCnt(PollUserFVo pollUserFVo) throws Exception;
	
}