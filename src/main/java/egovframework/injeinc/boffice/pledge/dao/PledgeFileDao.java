package egovframework.injeinc.boffice.pledge.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.pledge.vo.PledgeFileVo;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("PledgeFileDao")
public class PledgeFileDao extends EgovAbstractMapper {

	public void createPledgeFile(PledgeFileVo pledgeFileVo) {
		insert("PledgeFileDao.insertPledgeFile", pledgeFileVo);		
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListPledgeFile(PledgeFileVo pledgeFileVo) {
		return selectList("PledgeFileDao.selectListPledgeFile", pledgeFileVo);
	}

	public PledgeFileVo selectPledgeFile(PledgeFileVo pledgeFileVo) {
		return selectOne("PledgeFileDao.selectPledgeFile", pledgeFileVo);
	}

	public void deletePledgeFile(PledgeFileVo pledgeFileVo) {
		delete("PledgeFileDao.deletePledgeFile", pledgeFileVo);
	}
}