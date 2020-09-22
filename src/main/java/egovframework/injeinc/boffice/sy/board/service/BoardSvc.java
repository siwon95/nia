package egovframework.injeinc.boffice.sy.board.service;

import java.util.List;

import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.board.vo.ConfigPropertyVo;

public interface BoardSvc {
	/** CONFIG_PROPERTY */
	@SuppressWarnings("rawtypes")
	public List retrieveListConfigProperty(ConfigPropertyVo configPropertyVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListConfigPropertyTemplet(ConfigPropertyVo configPropertyVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListBbsCategory(BoardVo boardVo) throws Exception;
	
	/** RSS 게시판 종합 정보 */
	public void registBoard(BoardVo boardVo, List<ConfigPropertyVo> propertyList) throws Exception;
	public BoardVo retrieveBoard(BoardVo boardVo) throws Exception;

	/** RSS 사용자 페이지 */
	public List<BoardVo> retrieveRssUrlCheck(BoardVo boardVo) throws Exception;


}