package egovframework.injeinc.boffice.ex.poll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerViewVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerVo;

@Repository("PollAnswerDao")
public class PollAnswerDao extends EgovAbstractMapper {
	
	public void createPollAnswer(PollAnswerVo pollAnswerVo) throws Exception {
		insert("PollAnswerDao.insertPollAnswer", pollAnswerVo);
	}
	
	public void deletePollAnswer(PollAnswerVo pollAnswerVo) throws Exception {
		delete("PollAnswerDao.deletePollAnswer", pollAnswerVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagPollAnswer(PollAnswerViewVo pollAnswerViewVo) throws Exception {
		return selectList("PollAnswerDao.selectPagPollAnswer", pollAnswerViewVo);
	}
	
	public int selectPollAnswerCnt(PollAnswerViewVo pollAnswerViewVo) throws Exception {
		return (Integer)selectOne("PollAnswerDao.selectPollAnswerCnt", pollAnswerViewVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListPollAnswer(PollAnswerViewVo pollAnswerViewVo) throws Exception {
		return selectList("PollAnswerDao.selectListPollAnswer", pollAnswerViewVo);
	}
	
}