package egovframework.injeinc.boffice.sy.mgr.dao;


import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainConfigVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("MgrMainConfigDao")
public class MgrMainConfigDao extends EgovAbstractMapper {
	
	public void createMgrMainConfig(MgrMainConfigVo mgrMainConfigVo) throws Exception {
		insert("MgrMainConfigDao.insertMgrMainConfig", mgrMainConfigVo);
	}
	
	public void updateMgrMainConfig(MgrMainConfigVo mgrMainConfigVo) throws Exception {
		update("MgrMainConfigDao.updateMgrMainConfig", mgrMainConfigVo);
	}
	
	public MgrMainConfigVo selectMgrMainConfig(MgrMainConfigVo mgrMainConfigVo) throws Exception {
		return (MgrMainConfigVo)selectOne("MgrMainConfigDao.selectMgrMainConfig", mgrMainConfigVo);
	}
	
}