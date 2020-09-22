package egovframework.injeinc.foffice.ex.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.ex.board.vo.ContentMinwonResultFVo;

@Repository("ContentMinwonResultFDao")
public class ContentMinwonResultFDao extends EgovAbstractMapper {

	@SuppressWarnings("rawtypes")
	public List selectListContentMinwonResultF(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return selectList("ContentMinwonResultFDao.selectListContentMinwonResultF", contentMinwonResultFVo);
	}

	public ContentMinwonResultFVo selectListContentMinwonResultFTotal(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return (ContentMinwonResultFVo)selectOne("ContentMinwonResultFDao.selectListContentMinwonResultFTotal", contentMinwonResultFVo);
	}
	
	public void updateContentMinwonResultFSurvey(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		update("ContentMinwonResultFDao.updateContentMinwonResultFSurvey", contentMinwonResultFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListContentFileMinwon(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return selectList("ContentMinwonResultFDao.selectListContentFileMinwon", contentMinwonResultFVo);
	}

	public String selectListContentMinwonResultFDeadLine(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return (String)selectOne("ContentMinwonResultFDao.selectContentMinwonResultFDeadLine", contentMinwonResultFVo);
	}

	public ContentMinwonResultFVo selectListContentMinwonResultFDelay(ContentMinwonResultFVo contentMinwonResultFVo) throws Exception {
		return (ContentMinwonResultFVo)selectOne("ContentMinwonResultFDao.selectContentMinwonResultFDelay", contentMinwonResultFVo);
	}
	
}