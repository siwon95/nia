package egovframework.injeinc.foffice.ex.board.service;

import java.util.List;

import egovframework.injeinc.foffice.ex.board.vo.ContentMinwonResultFVo;

public interface ContentMinwonResultFSvc {
	
	@SuppressWarnings("rawtypes")
	public List retrieveListContentMinwonResultF(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception;
	public ContentMinwonResultFVo retrieveListContentMinwonResultFTotal(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception;
	public void modifyContentMinwonResultFSurvey(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListContentFileMinwon(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception;
	public String retrieveListContentMinwonResultFDeadLine(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception;
	public ContentMinwonResultFVo retrieveListContentMinwonResultFDelay(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception;
	
}