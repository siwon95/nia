package egovframework.injeinc.boffice.sy.board.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.board.vo.BbsCategoryVo;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.board.vo.ConfigPropertyVo;

@Repository("BoardDao")
public class BoardDao extends EgovAbstractMapper {
	
	public void createBbsConfig(BoardVo boardVo) throws Exception {
		insert("BoardDao.insertBbsConfig", boardVo);
	}
	
	public int updateBbsConfig(BoardVo boardVo) throws Exception {
		return update("BoardDao.updateBbsConfig", boardVo);
	}
	
	public void createBbsUsrConfig(BoardVo boardVo) throws Exception {
		insert("BoardDao.insertBbsUsrConfig", boardVo);
	}
	
	public int updateBbsUsrConfig(BoardVo boardVo) throws Exception {
		return update("BoardDao.updateBbsUsrConfig", boardVo);
	}
	
	public void createConfigProperty(ConfigPropertyVo configPropertyVo) throws Exception {
		insert("BoardDao.insertConfigProperty", configPropertyVo);
	}
	
	public void deleteConfigProperty(ConfigPropertyVo configPropertyVo) throws Exception {
		delete("BoardDao.deleteConfigProperty", configPropertyVo);
	}
	
	public void createConfigPropertyTemplet(ConfigPropertyVo configPropertyVo) throws Exception {
		insert("BoardDao.insertConfigPropertyTemplet", configPropertyVo);
	}
	
	public void deleteConfigPropertyTemplet(ConfigPropertyVo configPropertyVo) throws Exception {
		delete("BoardDao.deleteConfigPropertyTemplet", configPropertyVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListConfigProperty(ConfigPropertyVo configPropertyVo) throws Exception {
		return selectList("BoardDao.selectListConfigProperty", configPropertyVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListConfigPropertyTemplet(ConfigPropertyVo configPropertyVo) throws Exception {
		return selectList("BoardDao.selectListConfigPropertyTemplet", configPropertyVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListBbsCategory(BoardVo boardVo) throws Exception {
		return selectList("BoardDao.selectListBbsCategory", boardVo);
	}
	
	public BoardVo selectBoard(BoardVo boardVo) throws Exception {
		return (BoardVo) selectOne("BoardDao.selectBoard", boardVo);
	}

	/** RSS 사용자 페이지 */
	public List<BoardVo> selectRssUrlCheck(BoardVo boardVo) throws Exception {
		return selectList("BoardDao.selectRssUrlCheck", boardVo);
	}
}