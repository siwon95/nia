package egovframework.injeinc.boffice.sy.menu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.sy.menu.vo.MgrMenuVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("MgrMenuDao")
public class MgrMenuDao extends EgovAbstractMapper {
	
	public void createMgrMenu(MgrMenuVo mgrMenuVo) throws Exception {
		insert("MgrMenuDao.insertMgrMenu", mgrMenuVo);
	}
	
	public int updateMgrMenu(MgrMenuVo mgrMenuVo) throws Exception {
		return update("MgrMenuDao.updateMgrMenu", mgrMenuVo);
	}
	
	public int updateMgrMenuHelp(MgrMenuVo mgrMenuVo) throws Exception {
		return update("MgrMenuDao.updateMgrMenuHelp", mgrMenuVo);
	}
	
	public void deleteMgrMenu(MgrMenuVo mgrMenuVo) throws Exception {
		delete("MgrMenuDao.deleteMgrMenu", mgrMenuVo);
	}
	
	public int selectMgrMenuCnt(MgrMenuVo mgrMenuVo) throws Exception {
		return (Integer)selectOne("MgrMenuDao.selectMgrMenuCnt", mgrMenuVo);
	}
	
	public MgrMenuVo selectMgrMenu(MgrMenuVo mgrMenuVo) throws Exception {
		return (MgrMenuVo)selectOne("MgrMenuDao.selectMgrMenu", mgrMenuVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListMgrMenu() throws Exception {
		return selectList("MgrMenuDao.selectListMgrMenu", "");
	}

	@SuppressWarnings("rawtypes")
	public List selectListMgrMenuTop(LoginVo loginVo) throws Exception {
		return selectList("MgrMenuDao.selectListMgrMenuTop", loginVo);
	}
	
	public String selectMgrMenuCode(String url) throws Exception {
		return (String)selectOne("MgrMenuDao.selectMgrMenuCode", url);
	}
}