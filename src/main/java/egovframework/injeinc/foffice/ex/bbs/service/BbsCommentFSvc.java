package egovframework.injeinc.foffice.ex.bbs.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.boffice.ex.board.vo.BbsCommentVo;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsCommentFVo;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsCustomFVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface BbsCommentFSvc {
	public void registBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception;

	public List<BbsCommentFVo> retrieveListComment(BbsCommentFVo bbsCommentFVo) throws Exception;

	public int selectAllCommentTotCnt(BbsCommentFVo bbsCommentFVo) throws Exception;

	public void modifyBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception;

	public BbsCommentFVo retrieveBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception;

	public void removeBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception;
}