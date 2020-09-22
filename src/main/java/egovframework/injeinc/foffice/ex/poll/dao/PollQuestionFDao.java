package egovframework.injeinc.foffice.ex.poll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.ex.poll.vo.PollQuestionFVo;

@Repository("PollQuestionFDao")
public class PollQuestionFDao extends EgovAbstractMapper {
	
	public PollQuestionFVo selectPollQuestionF(PollQuestionFVo pollQuestionFVo) throws Exception {
		return (PollQuestionFVo)selectOne("PollQuestionFDao.selectPollQuestionF", pollQuestionFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListPollQuestionF(PollQuestionFVo pollQuestionFVo) throws Exception {
		return selectList("PollQuestionFDao.selectListPollQuestionF", pollQuestionFVo);
	}
	
}