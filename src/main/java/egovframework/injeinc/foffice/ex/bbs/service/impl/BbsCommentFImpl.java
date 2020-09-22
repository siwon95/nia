package egovframework.injeinc.foffice.ex.bbs.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.googlecode.ehcache.annotations.Cacheable;

import egovframework.cmm.EgovMessageSource;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.board.dao.BbsCommentDao;
import egovframework.injeinc.boffice.ex.board.service.BbsCommentSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsCommentVo;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.bbs.dao.BbsCommentFDao;
import egovframework.injeinc.foffice.ex.bbs.dao.BbsFDao;
import egovframework.injeinc.foffice.ex.bbs.service.BbsCommentFSvc;
import egovframework.injeinc.foffice.ex.bbs.service.BbsFSvc;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsCommentFVo;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 공통로그인 서비스 
 * @author 공통서비스 개발팀 이동열
 */


@Service("BbsCommentFSvc")
public class BbsCommentFImpl extends EgovAbstractServiceImpl implements BbsCommentFSvc {
	@Resource(name = "BbsCommentFDao")
	private BbsCommentFDao bbsCommentFDao;
	@Override
	public void registBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception {
		bbsCommentFDao.insertBbsComment(bbsCommentFVo);
	}
	@Override
	public List<BbsCommentFVo> retrieveListComment(BbsCommentFVo bbsCommentFVo) throws Exception {
		return bbsCommentFDao.selectListComment(bbsCommentFVo);
	}
	@Override
	public int selectAllCommentTotCnt(BbsCommentFVo bbsCommentFVo) throws Exception {
		return bbsCommentFDao.selectAllCommentTotCnt(bbsCommentFVo);
	}
	
	@Override
	public void modifyBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception {
		bbsCommentFDao.updateBbsComment(bbsCommentFVo);
	}
	
	@Override
	public BbsCommentFVo retrieveBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception {
		return bbsCommentFDao.selectBbsComment(bbsCommentFVo);
	}
	
	@Override
	public void removeBbsComment(BbsCommentFVo bbsCommentFVo) throws Exception {
		bbsCommentFDao.deleteBbsComment(bbsCommentFVo);
	}
}