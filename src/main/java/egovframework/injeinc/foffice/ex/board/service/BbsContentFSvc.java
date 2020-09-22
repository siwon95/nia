package egovframework.injeinc.foffice.ex.board.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.foffice.ex.board.vo.BbsContentFVo;

public interface BbsContentFSvc {
	
	public int registBbsContentF(BbsContentFVo bbsContentFVo) throws Exception;
	public void modifyBbsContentF(BbsContentFVo bbsContentFVo) throws Exception;
	public void removeBbsContentF(BbsContentFVo bbsContentFVo) throws Exception;
	public Map<String, Object> retrieveBbsContentF(BbsContentFVo bbsContentFVo) throws Exception;
	public Map<String, Object> retrievePagBbsContentF(BbsContentFVo bbsContentFVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListBbsContentF(BbsContentFVo bbsContentFVo) throws Exception;
	public Map<String, Object> retrievePagBbsContentFMyMinwon(BbsContentFVo bbsContentFVo) throws Exception;
	public void modifyBbsContentFCount(BbsContentFVo bbsContentFVo) throws Exception;
}