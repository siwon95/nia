package egovframework.injeinc.foffice.ex.sns.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.foffice.ex.sns.vo.SnsContentsFVo;
import egovframework.injeinc.foffice.ex.sns.vo.SnsHashTagFVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("SnsFDao")
public class SnsFDao extends EgovAbstractMapper {

	public List<SnsContentsFVo> selectListSnsContents(SnsContentsFVo snsContentsFVo) throws Exception {
		return selectList("SnsFDao.selectListSnsContents", snsContentsFVo);
	}

	public List<SnsHashTagFVo> selectHashtagList() throws Exception {
		return selectList("SnsFDao.selectHashtagList");
	}

	public void updateHashtgForViewCnt(String searchHashTag) {
		update("SnsFDao.updateHashtgForViewCnt", searchHashTag);
	}
	
//	public PollQuestionFVo selectPollQuestionF(PollQuestionFVo pollQuestionFVo) throws Exception {
//		return (PollQuestionFVo)select("PollQuestionFDao.selectPollQuestionF", pollQuestionFVo);
//	}
//
//	@SuppressWarnings("rawtypes")
//	public List selectListPollQuestionF(PollQuestionFVo pollQuestionFVo) throws Exception {
//		return selectList("PollQuestionFDao.selectListPollQuestionF", pollQuestionFVo);
//	}
	
}