package egovframework.injeinc.boffice.ex.poll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.poll.vo.PollUserVo;

@Repository("PollUserDao")
public class PollUserDao extends EgovAbstractMapper {
	
	public void createPollUser(PollUserVo pollUserVo) throws Exception {
		insert("PollUserDao.insertPollUser", pollUserVo);
	}
	
	public void updatePollUser(PollUserVo pollUserVo) throws Exception {
		update("PollUserDao.updatePollUser", pollUserVo);
	}
	
	public void deletePollUser(PollUserVo pollUserVo) throws Exception {
		delete("PollUserDao.deletePollUser", pollUserVo);
	}
	
	public PollUserVo selectPollUser(PollUserVo pollUserVo) throws Exception {
		return (PollUserVo)selectOne("PollUserDao.selectPollUser", pollUserVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagPollUser(PollUserVo pollUserVo) throws Exception {
		return selectList("PollUserDao.selectPagPollUser", pollUserVo);
	}
	
	public int selectPollUserCnt(PollUserVo pollUserVo) throws Exception {
		return (Integer)selectOne("PollUserDao.selectPollUserCnt", pollUserVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListPollUser(PollUserVo pollUserVo) throws Exception {
		return selectList("PollUserDao.selectListPollUser", pollUserVo);
	}
	
}