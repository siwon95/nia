package egovframework.injeinc.foffice.ex.sns.service;

import java.util.List;

import egovframework.injeinc.foffice.ex.sns.vo.SnsContentsFVo;
import egovframework.injeinc.foffice.ex.sns.vo.SnsHashTagFVo;


public interface SnsFSvc {

	List<SnsContentsFVo> retrieveListSnsContents(SnsContentsFVo snsContentsFVo) throws Exception;

	List<SnsHashTagFVo> retrieveHashtagList() throws Exception;
	
//	public String registPollUserF(PollUserFVo pollUserFVo) throws Exception;
	
}