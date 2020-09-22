package egovframework.injeinc.boffice.ex.poll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.poll.vo.PollListVo;

@Repository("PollListDao")
public class PollListDao extends EgovAbstractMapper {
	
	public void createPollList(PollListVo pollListVo) throws Exception {
		insert("PollListDao.insertPollList", pollListVo);
	}
	
	public void updatePollList(PollListVo pollListVo) throws Exception {
		update("PollListDao.updatePollList", pollListVo);
	}
	
	public void updatePollListPlUse(PollListVo pollListVo) throws Exception {
		update("PollListDao.updatePollListPlUse", pollListVo);
	}
	
	public void deletePollList(PollListVo pollListVo) throws Exception {
		delete("PollListDao.deletePollList", pollListVo);
	}
	
	public PollListVo selectPollList(PollListVo pollListVo) throws Exception {
		return (PollListVo)selectOne("PollListDao.selectPollList", pollListVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagPollList(PollListVo pollListVo) throws Exception {
		return selectList("PollListDao.selectPagPollList", pollListVo);
	}
	
	public int selectPollListCnt(PollListVo pollListVo) throws Exception {
		return (Integer)selectOne("PollListDao.selectPollListCnt", pollListVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListPollList(PollListVo pollListVo) throws Exception {
		return selectList("PollListDao.selectListPollList", pollListVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListPollDepart(PollListVo pollListVo) throws Exception {
		return selectList("PollListDao.selectListPollDepart", pollListVo);
	}
	
}