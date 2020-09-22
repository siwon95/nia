package egovframework.injeinc.boffice.pledge.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.pledge.vo.PledgeVo;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("PledgeDao")
public class PledgeDao extends EgovAbstractMapper {

	@SuppressWarnings("rawtypes")
	public List selectListWiwid(int wiwParent) {
		return selectList("PledgeDao.selectListWiwid", wiwParent);
	}

	public void insertPledge(PledgeVo pledgeVo) {
		insert("PledgeDao.insertPledge", pledgeVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectPagePledge(PledgeVo pledgeVo) {
		return selectList("PledgeDao.selectPagePledge", pledgeVo);
	}
	
	public int selectTotalCntPledge(PledgeVo pledgeVo) {
		return selectOne("PledgeDao.selectTotalCntPledge", pledgeVo);
	}

	public PledgeVo selectPledge(PledgeVo pledgeVo) {
		return selectOne("PledgeDao.selectPledge", pledgeVo);
	}

	public void updatePledge(PledgeVo pledgeVo) {
		update("PledgeDao.updatePledge", pledgeVo);
	}
	public void deletePledge(PledgeVo pledgeVo) {
		delete("PledgeDao.deletePledge", pledgeVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListPledge(PledgeVo pledgeVo) {
		return selectList("PledgeDao.selectListPledge", pledgeVo);
	}
}