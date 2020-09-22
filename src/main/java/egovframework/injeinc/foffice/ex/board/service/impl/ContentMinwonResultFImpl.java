package egovframework.injeinc.foffice.ex.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.injeinc.foffice.ex.board.dao.ContentMinwonResultFDao;
import egovframework.injeinc.foffice.ex.board.service.ContentMinwonResultFSvc;
import egovframework.injeinc.foffice.ex.board.vo.ContentMinwonResultFVo;


@Service("ContentMinwonResultFSvc")
public class ContentMinwonResultFImpl extends EgovAbstractServiceImpl implements ContentMinwonResultFSvc {

	@Resource(name = "ContentMinwonResultFDao")
	private ContentMinwonResultFDao contentMinwonResultFDao;

	@SuppressWarnings("rawtypes")
	public List retrieveListContentMinwonResultF(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return contentMinwonResultFDao.selectListContentMinwonResultF(contentMinwonResultFVo);
	}

	public ContentMinwonResultFVo retrieveListContentMinwonResultFTotal(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return contentMinwonResultFDao.selectListContentMinwonResultFTotal(contentMinwonResultFVo);
	}

	public void modifyContentMinwonResultFSurvey(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		contentMinwonResultFDao.updateContentMinwonResultFSurvey(contentMinwonResultFVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListContentFileMinwon(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return contentMinwonResultFDao.selectListContentFileMinwon(contentMinwonResultFVo);
	}
	
	public String retrieveListContentMinwonResultFDeadLine(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return contentMinwonResultFDao.selectListContentMinwonResultFDeadLine(contentMinwonResultFVo);
	}
	
	public ContentMinwonResultFVo retrieveListContentMinwonResultFDelay(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return contentMinwonResultFDao.selectListContentMinwonResultFDelay(contentMinwonResultFVo);
	}
}