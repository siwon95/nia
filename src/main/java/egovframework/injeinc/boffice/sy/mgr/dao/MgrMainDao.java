package egovframework.injeinc.boffice.sy.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainVo;

@Repository("MgrMainDao")
public class MgrMainDao extends EgovAbstractMapper {
	
	public List selectMgrMainMinwonList(MgrMainVo mgrMainVo) throws Exception {
		return selectList("MgrMainDao.selectMgrMainMinwonList", mgrMainVo);
	}
	
	public List selectMgrMainBoardList(MgrMainVo mgrMainVo) throws Exception {
		return selectList("MgrMainDao.selectMgrMainBoardList", mgrMainVo);
	}
	
	public List selectMgrMainCommExpandList(MgrMainVo mgrMainVo) throws Exception {
		return selectList("MgrMainDao.selectMgrMainCommExpandList", mgrMainVo);
	}
	
	public List selectMgrMainCommEventList(MgrMainVo mgrMainVo) throws Exception {
		return selectList("MgrMainDao.selectMgrMainCommEventList", mgrMainVo);
	}
	
	public int selectMgrMainMinwonCount(MgrMainVo mgrMainVo) throws Exception {
		return (Integer)selectOne("MgrMainDao.selectMgrMainMinwonCount", mgrMainVo);
	}
	
	public int selectMgrMainBoardCount(MgrMainVo mgrMainVo) throws Exception {
		return (Integer)selectOne("MgrMainDao.selectMgrMainBoardCount", mgrMainVo);
	}
	
	public int selectMgrMainCommCount(MgrMainVo mgrMainVo) throws Exception {
		return (Integer)selectOne("MgrMainDao.selectMgrMainCommCount", mgrMainVo);
	}
	
}