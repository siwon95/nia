package egovframework.injeinc.foffice.ex.bbs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsCustomFVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("BbsCustomFDao")
public class BbsCustomFDao extends EgovAbstractMapper {
	
	public BbsCustomFVo selectBbsCustomF(BbsCustomFVo bbsCustomFVo) throws Exception {
		return (BbsCustomFVo)selectOne("BbsCustomFDao.selectBbsCustomF", bbsCustomFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagBbsCustomF(BbsCustomFVo bbsCustomFVo) throws Exception {
		return selectList("BbsCustomFDao.selectPagBbsCustomF", bbsCustomFVo);
	}
	
	public int selectBbsCustomFCnt(BbsCustomFVo bbsCustomFVo) throws Exception {
		return (Integer)selectOne("BbsCustomFDao.selectBbsCustomFCnt", bbsCustomFVo);
	}
	
	public EgovMap selectBbsCustomFPrevNext(BbsCustomFVo bbsCustomFVo) throws Exception {
		return (EgovMap)selectOne("BbsCustomFDao.selectBbsCustomFPrevNext", bbsCustomFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListBbsCustomFLatest(BbsCustomFVo bbsCustomFVo) throws Exception {
		return selectList("BbsCustomFDao.selectListBbsCustomFLatest", bbsCustomFVo);
	}
	
	public BbsCustomFVo selectListBbsCustomFLatestOne(BbsCustomFVo bbsCustomFVo) throws Exception {
		return (BbsCustomFVo)selectOne("BbsCustomFDao.selectListBbsCustomFLatestOne", bbsCustomFVo);
	}

	public void insertBbsCustomFIdea(BbsCustomFVo bbsCustomFVo) throws Exception {
		insert("BbsCustomFDao.insertBbsCustomFIdea", bbsCustomFVo);
	}

	public void updateBbsCustomFCount(BbsCustomFVo bbsCustomFVo) throws Exception {
		update("BbsCustomFDao.updateBbsCustomFCount", bbsCustomFVo);
	}
	
}