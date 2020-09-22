package egovframework.injeinc.boffice.ex.board.dao;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;

@Repository("BbsContentDao")
public class BbsContentDao extends EgovAbstractMapper {
	
	public void createBbsContent(BbsContentVo bbsContentVo) throws Exception {
		insert("BbsContentDao.insertBbsContent", bbsContentVo);
	}
	
	public void updateBbsContent(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.updateBbsContent", bbsContentVo);
	}
	
	public void deleteBbsContent(BbsContentVo bbsContentVo) throws Exception {
		delete("BbsContentDao.deleteBbsContent", bbsContentVo);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBbsContent(BbsContentVo bbsContentVo) throws Exception {
		return (Map<String, Object>)selectOne("BbsContentDao.selectBbsContent", bbsContentVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagBbsContent(BbsContentVo bbsContentVo) throws Exception {
		return selectList("BbsContentDao.selectPagBbsContent", bbsContentVo);
	}
	
	public int selectBbsContentCnt(BbsContentVo bbsContentVo) throws Exception {
		return (Integer)selectOne("BbsContentDao.selectBbsContentCnt", bbsContentVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListBbsContent(BbsContentVo bbsContentVo) throws Exception {
		return selectList("BbsContentDao.selectListBbsContent", bbsContentVo);
	}
	
	public void updateBbsContentBcDelYn(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.updateBbsContentBcDelYn", bbsContentVo);
	}
	
	public void updateBbsContentDelRsnCd(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.updateBbsContentDelRsnCd", bbsContentVo);
	}
	
	public void updateBbsContentStep(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.updateBbsContentStep", bbsContentVo);
	}
	
	public void updateBbsContentAnswer(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.updateBbsContentAnswer", bbsContentVo);
	}
	
	public void updateBbsContentCount(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.updateBbsContentCount", bbsContentVo);
	}
	
	public void updateBbsContentClobAutoMake(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.updateBbsContentClobAutoMake", bbsContentVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListBbsContentQnA(BbsContentVo bbsContentVo) throws Exception {
		return selectList("BbsContentDao.selectListBbsContentQnA", bbsContentVo);
	}
	
	
	
	/* RSS LIST */
	public List setectRssListBbsContent(BbsContentVo bbsContentVo) throws Exception {
		return selectList("BbsContentDao.selectRssListBbsContent", bbsContentVo);
	}

	/* RSS PAGE */
	public int selectPagRssListBbsContent(BbsContentVo bbsContentVo) {
		return (Integer)selectOne("BbsContentDao.selectPagRssListBbsContent",bbsContentVo);
	}
	
	public void updateBbsContentMove(BbsContentVo bbsContentVo) throws Exception {
		insert("BbsContentDao.updateBbsContentMove", bbsContentVo);
	}
	
	public void insertBbsContentCopy(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.insertBbsContentCopy", bbsContentVo);
	}
	
	public String selectCmsbbsGroup(BbsContentVo bbsContentVo) throws Exception {
		return (String)selectOne("BbsContentDao.selectCmsbbsGroup", bbsContentVo);
	}
	
	public int selectUpDownParentSeq(BbsContentVo bbsContentVo) throws Exception {
		return (Integer)selectOne("BbsContentDao.selectUpDownParentSeq", bbsContentVo);
	}
	
	public int selectUpDownParentSeqFrom(BbsContentVo bbsContentVo) throws Exception {
		return (Integer)selectOne("BbsContentDao.selectUpDownParentSeqFrom", bbsContentVo);
	}
	
	public void sortMyParentSeqZero(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.sortMyParentSeqZero", bbsContentVo);
	}

	public void sortTargetParentSeq(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.sortTargetParentSeq", bbsContentVo);
	}
	
	public void sortMyParentSeq(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.sortMyParentSeq", bbsContentVo);
	}

	public void updateBoardStatusR(BbsContentVo bbsContentVo) throws Exception {
		update("BbsContentDao.updateBoardStatusR", bbsContentVo);
		
	}
	
}