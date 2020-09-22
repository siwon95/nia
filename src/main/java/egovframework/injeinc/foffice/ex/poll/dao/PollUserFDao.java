package egovframework.injeinc.foffice.ex.poll.dao;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.ex.poll.vo.PollUserFVo;

@Repository("PollUserFDao")
public class PollUserFDao extends EgovAbstractMapper {
	
	public void createPollUserF(PollUserFVo pollUserFVo) throws Exception {
		insert("PollUserFDao.insertPollUserF", pollUserFVo);
	}
	
	public int selectPollUserFCnt(PollUserFVo pollUserFVo) throws Exception {
		return (Integer)selectOne("PollUserFDao.selectPollUserFCnt", pollUserFVo);
	}
	
	public int selectPollUserFTotCnt(PollUserFVo pollUserFVo) throws Exception {
		return (Integer)selectOne("PollUserFDao.selectPollUserFTotCnt", pollUserFVo);
	}
	
}