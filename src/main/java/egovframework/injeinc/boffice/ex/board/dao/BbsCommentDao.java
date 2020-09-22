package egovframework.injeinc.boffice.ex.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.ex.board.vo.BbsCommentVo;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("BbsCommentDao")
public class BbsCommentDao extends EgovAbstractMapper {
	public void insertBbsComment(BbsCommentVo bbsCommentVo) throws Exception {
		insert("BbsCommentDao.insertBbsComment", bbsCommentVo);
	}

	public List<BbsCommentVo> selectListComment(BbsCommentVo bbsCommentVo) {
		return selectList("BbsCommentDao.selectListComment", bbsCommentVo);
	}

	public int selectAllCommentTotCnt(BbsCommentVo bbsCommentVo) {
		return selectOne("BbsCommentDao.selectAllCbCommentTotCnt", bbsCommentVo);
	}

	public void updateBbsComment(BbsCommentVo bbsCommentVo) throws Exception {
		update("BbsCommentDao.updateBbsComment", bbsCommentVo);
	}

	public BbsCommentVo selectBbsComment(BbsCommentVo bbsCommentVo) {
		return selectOne("BbsCommentDao.selectBbsComment", bbsCommentVo);
	}

	public void deleteBbsComment(BbsCommentVo bbsCommentVo) {
		update("BbsCommentDao.deleteBbsComment", bbsCommentVo);
	}
}
