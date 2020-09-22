package egovframework.injeinc.foffice.ex.bbs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.foffice.ex.bbs.vo.BbsCommentFVo;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("BbsCommentFDao")
public class BbsCommentFDao extends EgovAbstractMapper {
	public void insertBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception {
		insert("BbsCommentFDao.insertBbsComment", bbsCommentFVo);
	}

	public List<BbsCommentFVo> selectListComment(BbsCommentFVo bbsCommentFVo) {
		return selectList("BbsCommentFDao.selectListComment", bbsCommentFVo);
	}

	public int selectAllCommentTotCnt(BbsCommentFVo bbsCommentFVo) {
		return selectOne("BbsCommentFDao.selectAllCbCommentTotCnt", bbsCommentFVo);
	}

	public void updateBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception {
		update("BbsCommentFDao.updateBbsComment", bbsCommentFVo);
	}

	public BbsCommentFVo selectBbsComment(BbsCommentFVo bbsCommentFVo) {
		return selectOne("BbsCommentFDao.selectBbsComment", bbsCommentFVo);
	}

	public void deleteBbsComment(BbsCommentFVo bbsCommentFVo) {
		update("BbsCommentFDao.deleteBbsComment", bbsCommentFVo);
	}
}