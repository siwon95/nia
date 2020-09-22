package egovframework.injeinc.foffice.ex.board.dao;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.ex.board.vo.BbsContentFVo;

@Repository("BbsContentFDao")
public class BbsContentFDao extends EgovAbstractMapper {
	
	public void createBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		insert("BbsContentFDao.insertBbsContentF", bbsContentFVo);
	}
	
	public void updateBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		update("BbsContentFDao.updateBbsContentF", bbsContentFVo);
	}
	
	public void deleteBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		delete("BbsContentFDao.deleteBbsContentF", bbsContentFVo);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		return (Map<String, Object>)selectOne("BbsContentFDao.selectBbsContentF", bbsContentFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		return selectList("BbsContentFDao.selectPagBbsContentF", bbsContentFVo);
	}
	
	public int selectBbsContentFCnt(BbsContentFVo bbsContentFVo) throws Exception {
		return (Integer)selectOne("BbsContentFDao.selectBbsContentFCnt", bbsContentFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		return selectList("BbsContentFDao.selectListBbsContentF", bbsContentFVo);
	}
	
	public void updateBbsContentFStep(BbsContentFVo bbsContentFVo) throws Exception {
		update("BbsContentFDao.updateBbsContentFStep", bbsContentFVo);
	}
	
	public void updateBbsContentFCount(BbsContentFVo bbsContentFVo) throws Exception {
		update("BbsContentFDao.updateBbsContentFCount", bbsContentFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagBbsContentFMyMinwon(BbsContentFVo bbsContentFVo) throws Exception {
		return selectList("BbsContentFDao.selectPagBbsContentFMyMinwon", bbsContentFVo);
	}
	
	public int selectBbsContentFMyMinwonCnt(BbsContentFVo bbsContentFVo) throws Exception {
		return (Integer)selectOne("BbsContentFDao.selectBbsContentFMyMinwonCnt", bbsContentFVo);
	}
	
}