package egovframework.injeinc.boffice.ex.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.board.dao.BbsCommentDao;
import egovframework.injeinc.boffice.ex.board.service.BbsCommentSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsCommentVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("BbsCommentSvc")
public class BbsCommentImpl extends EgovAbstractServiceImpl implements BbsCommentSvc {
	@Resource(name = "BbsCommentDao")
	private BbsCommentDao bbsCommentDao;
	@Override
	public void registBbsComment(BbsCommentVo bbsCommentVo) throws Exception {
		bbsCommentDao.insertBbsComment(bbsCommentVo);
	}
	@Override
	public List<BbsCommentVo> retrieveListComment(BbsCommentVo bbsCommentVo) throws Exception {
		return bbsCommentDao.selectListComment(bbsCommentVo);
	}
	@Override
	public int selectAllCommentTotCnt(BbsCommentVo bbsCommentVo) throws Exception {
		return bbsCommentDao.selectAllCommentTotCnt(bbsCommentVo);
	}
	
	@Override
	public void modifyBbsComment(BbsCommentVo bbsCommentVo) throws Exception {
		bbsCommentDao.updateBbsComment(bbsCommentVo);
	}
	
	@Override
	public BbsCommentVo retrieveBbsComment(BbsCommentVo bbsCommentVo) throws Exception {
		return bbsCommentDao.selectBbsComment(bbsCommentVo);
	}
	
	@Override
	public void removeBbsComment(BbsCommentVo bbsCommentVo) throws Exception {
		bbsCommentDao.deleteBbsComment(bbsCommentVo);
	}
	
}