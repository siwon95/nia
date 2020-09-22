package egovframework.injeinc.boffice.ex.poll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.poll.vo.PollQuestionVo;

@Repository("PollQuestionDao")
public class PollQuestionDao extends EgovAbstractMapper {
	
	public void createPollQuestion(PollQuestionVo pollQuestionVo) throws Exception {
		insert("PollQuestionDao.insertPollQuestion", pollQuestionVo);
	}
	
	public void updatePollQuestion(PollQuestionVo pollQuestionVo) throws Exception {
		update("PollQuestionDao.updatePollQuestion", pollQuestionVo);
	}
	
	public void deletePollQuestion(PollQuestionVo pollQuestionVo) throws Exception {
		delete("PollQuestionDao.deletePollQuestion", pollQuestionVo);
	}
	
	public PollQuestionVo selectPollQuestion(PollQuestionVo pollQuestionVo) throws Exception {
		return (PollQuestionVo)selectOne("PollQuestionDao.selectPollQuestion", pollQuestionVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListPollQuestion(PollQuestionVo pollQuestionVo) throws Exception {
		return selectList("PollQuestionDao.selectListPollQuestion", pollQuestionVo);
	}
	
	public int selectPollQuestionMaxSort(PollQuestionVo pollQuestionVo) throws Exception {
		return (Integer)selectOne("PollQuestionDao.selectPollQuestionMaxSort", pollQuestionVo);
	}
	
	public void updatePollQuestionPqSort(PollQuestionVo pollQuestionVo) throws Exception {
		update("PollQuestionDao.updatePollQuestionPqSort", pollQuestionVo);
	}
	
	public void updatePollQuestionPqSortChange(PollQuestionVo pollQuestionVo) throws Exception {
		update("PollQuestionDao.updatePollQuestionPqSortChange", pollQuestionVo);
	}
	
	public void updatePollQuestionPqSortReAlign(PollQuestionVo pollQuestionVo) throws Exception {
		update("PollQuestionDao.updatePollQuestionPqSortReAlign", pollQuestionVo);
	}
	
}