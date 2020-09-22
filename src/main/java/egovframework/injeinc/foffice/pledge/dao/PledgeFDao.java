package egovframework.injeinc.foffice.pledge.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.foffice.pledge.vo.PledgeFVo;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("PledgeFDao")
public class PledgeFDao extends EgovAbstractMapper {

	@SuppressWarnings("rawtypes")
	public List selectListWiwid(int wiwParent) {
		return selectList("PledgeFDao.selectListWiwid", wiwParent);
	}

	public void insertPledge(PledgeFVo pledgeFVo) {
		insert("PledgeFDao.insertPledge", pledgeFVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectPagePledge(PledgeFVo pledgeFVo) {
		return selectList("PledgeFDao.selectPagePledge", pledgeFVo);
	}
	
	public int selectTotalCntPledge(PledgeFVo pledgeFVo) {
		return selectOne("PledgeFDao.selectTotalCntPledge", pledgeFVo);
	}

	public PledgeFVo selectPledge(PledgeFVo pledgeFVo) {
		return selectOne("PledgeFDao.selectPledge", pledgeFVo);
	}

	public void updatePledge(PledgeFVo pledgeFVo) {
		update("PledgeFDao.updatePledge", pledgeFVo);
	}
	public void deletePledge(PledgeFVo pledgeFVo) {
		delete("PledgeFDao.deletePledge", pledgeFVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListPledge(PledgeFVo pledgeFVo) {
		return selectList("PledgeDao.selectListPledge", pledgeFVo);
	}

	public void updateCountPledge(PledgeFVo pledgeFVo) {
		update("PledgeFDao.updateCountPledge", pledgeFVo);
		
	}

	public PledgeFVo selectPledgeRecommend(PledgeFVo pledgeFVo) {
		return selectOne("PledgeFDao.selectPledgeRecommend", pledgeFVo);
	}

	public void insertPledgeRecommend(PledgeFVo pledgeFVo) {
		insert("PledgeFDao.insertPledgeRecommend", pledgeFVo);
		
	}
	public void updateRecommendPledge(PledgeFVo pledgeFVo) {
		update("PledgeFDao.updateRecommendPledge", pledgeFVo);
		
	}
}