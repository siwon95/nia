package egovframework.injeinc.boffice.sy.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.board.dao.BoardDao;
import egovframework.injeinc.boffice.sy.board.service.BoardSvc;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.board.vo.ConfigPropertyVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("BoardSvc")
public class BoardImpl extends EgovAbstractServiceImpl implements BoardSvc {

	@Resource(name="BoardDao")
	private BoardDao boardDao;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListConfigProperty(ConfigPropertyVo configPropertyVo) throws Exception {
		return boardDao.selectListConfigProperty(configPropertyVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListConfigPropertyTemplet(ConfigPropertyVo configPropertyVo) throws Exception {
		return boardDao.selectListConfigPropertyTemplet(configPropertyVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListBbsCategory(BoardVo boardVo) throws Exception {
		return boardDao.selectListBbsCategory(boardVo);
	}
	
	/** 게시판 종합 정보 */
	public void registBoard(BoardVo boardVo, List<ConfigPropertyVo> propertyList) throws Exception {
		if(boardVo != null){
			if(boardVo.getBbsFileCnt() == 0){
				boardVo.setFileMaxSize("");
			}
		}
		int count1 = boardDao.updateBbsConfig(boardVo);
		if(count1 == 0) {
			if(!boardVo.getMode().equals("template")){
				boardDao.createBbsConfig(boardVo);
			}
		}
		
		int count2 = boardDao.updateBbsUsrConfig(boardVo);
		if(count2 == 0) {
			if(!boardVo.getMode().equals("template")){
				boardDao.createBbsUsrConfig(boardVo);
			}
		}
		
		ConfigPropertyVo configPropertyVo = new ConfigPropertyVo();
		if(boardVo != null){
			configPropertyVo.setCbIdx(boardVo.getCbIdx());
			configPropertyVo.setBbsTempletGbn(boardVo.getBbsTempletGbn());
		}
		
		if(boardVo.getMode().equals("template")){
			boardDao.deleteConfigPropertyTemplet(configPropertyVo);
			for(ConfigPropertyVo resultVo : propertyList) {
				resultVo.setBbsTempletGbn(boardVo.getBbsTempletGbn());
				boardDao.createConfigPropertyTemplet(resultVo);
			}
		}else{
			boardDao.deleteConfigProperty(configPropertyVo);
			for(ConfigPropertyVo resultVo : propertyList) {
				boardDao.createConfigProperty(resultVo);
			}
		}
	}
	
	public BoardVo retrieveBoard(BoardVo boardVo) throws Exception {
		return (BoardVo) boardDao.selectBoard(boardVo);
	}

	/** RSS 사용자 페이지 */
	public List<BoardVo>retrieveRssUrlCheck(BoardVo boardVo) throws Exception {
		return boardDao.selectRssUrlCheck(boardVo);
	}

}