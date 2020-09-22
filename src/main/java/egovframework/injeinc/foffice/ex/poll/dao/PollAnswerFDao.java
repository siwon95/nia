package egovframework.injeinc.foffice.ex.poll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.ex.poll.vo.PollAnswerFViewVo;
import egovframework.injeinc.foffice.ex.poll.vo.PollAnswerFVo;

@Repository("PollAnswerFDao")
public class PollAnswerFDao extends EgovAbstractMapper {
	
	public void createPollAnswerF(PollAnswerFVo pollAnswerFVo) throws Exception {
		insert("PollAnswerFDao.insertPollAnswerF", pollAnswerFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListPollAnswerF(PollAnswerFViewVo pollAnswerFViewVo) throws Exception {
		return selectList("PollAnswerFDao.selectListPollAnswerF", pollAnswerFViewVo);
	}
	
}