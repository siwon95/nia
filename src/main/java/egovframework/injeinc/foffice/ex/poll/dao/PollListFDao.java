package egovframework.injeinc.foffice.ex.poll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.foffice.ex.poll.vo.PollListFVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("PollListFDao")
public class PollListFDao extends EgovAbstractMapper {
	
	public PollListFVo selectPollListF(PollListFVo pollListFVo) throws Exception {
		return (PollListFVo)selectOne("PollListFDao.selectPollListF", pollListFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListPollListF(PollListFVo pollListFVo) throws Exception {
		return selectList("PollListFDao.selectListPollListF", pollListFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListPollDepartF(PollListFVo pollListFVo) throws Exception {
		return selectList("PollListFDao.selectListPollDepartF", pollListFVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectPagPollList(PollListFVo pollListVo) throws Exception {
		return selectList("PollListFDao.selectPagPollList", pollListVo);
	}
	
	public int selectPollListCnt(PollListFVo pollListVo) throws Exception {
		return (Integer)selectOne("PollListFDao.selectPollListCnt", pollListVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectPagAllPollList(PollListFVo pollListVo) throws Exception {
		return selectList("PollListFDao.selectPagAllPollList", pollListVo);
	}
	
	public int selectAllPollListCnt(PollListFVo pollListVo) throws Exception {
		return (Integer)selectOne("PollListFDao.selectAllPollListCnt", pollListVo);
	}
	
}