package egovframework.injeinc.boffice.ex.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.injeinc.boffice.ex.board.vo.BbsCommentVo;

public interface BbsCommentSvc {
	public void registBbsComment(BbsCommentVo bbsCommentVo) throws Exception;

	public List<BbsCommentVo> retrieveListComment(BbsCommentVo bbsCommentVo) throws Exception;

	public int selectAllCommentTotCnt(BbsCommentVo bbsCommentVo) throws Exception;

	public void modifyBbsComment(BbsCommentVo bbsCommentVo) throws Exception;

	public BbsCommentVo retrieveBbsComment(BbsCommentVo bbsCommentVo) throws Exception;

	public void removeBbsComment(BbsCommentVo bbsCommentVo) throws Exception;

}
