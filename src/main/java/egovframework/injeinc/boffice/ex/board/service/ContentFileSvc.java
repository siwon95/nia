package egovframework.injeinc.boffice.ex.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;

public interface ContentFileSvc {
	
	public void registContentFile2(HttpServletRequest request, ContentFileVo contentFileVo ,BbsContentVo bbsContentVo) throws Exception;
	public void registContentFile(HttpServletRequest request, ContentFileVo contentFileVo) throws Exception;
	public void removeContentFile(ContentFileVo contentFileVo) throws Exception;
	public ContentFileVo retrieveContentFile(ContentFileVo contentFileVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListContentFile(ContentFileVo ContentFileVo) throws Exception;
	public ContentFileVo retrieveListContentFileOne(ContentFileVo ContentFileVo) throws Exception;
	public void moveContentFile(ContentFileVo delFileVo, int cbIdx, int bcIdx) throws Exception;
}