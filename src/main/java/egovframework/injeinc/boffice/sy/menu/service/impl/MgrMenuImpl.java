package egovframework.injeinc.boffice.sy.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.sy.menu.dao.MgrMenuDao;
import egovframework.injeinc.boffice.sy.menu.service.MgrMenuSvc;
import egovframework.injeinc.boffice.sy.menu.vo.MgrMenuVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MgrMenuSvc")
public class MgrMenuImpl extends EgovAbstractServiceImpl implements MgrMenuSvc {

	@Resource(name="MgrMenuDao")
	private MgrMenuDao mgrMenuDao;


	public void registMgrMenu(String userid, List<MgrMenuVo> list) throws Exception {
		MgrMenuVo mgrMenuVo = new MgrMenuVo();
		mgrMenuVo.setModId(userid);
		mgrMenuDao.deleteMgrMenu(mgrMenuVo);
		
		for(MgrMenuVo resultVo : list) {
			
			int count = mgrMenuDao.updateMgrMenu(resultVo);
			if(count == 0) {
				mgrMenuDao.createMgrMenu(resultVo);
			}
		}
	}
	
	public void updateMgrMenuHelp(String userid, MgrMenuVo mgrMenuVo) throws Exception {
		mgrMenuVo.setModId(userid);
		mgrMenuDao.updateMgrMenuHelp(mgrMenuVo);
	}

	public int retrieveMgrMenuCnt(MgrMenuVo mgrMenuVo) throws Exception {
		return mgrMenuDao.selectMgrMenuCnt(mgrMenuVo);
	}
	
	public MgrMenuVo retrieveMgrMenu(MgrMenuVo mgrMenuVo) throws Exception {
		return mgrMenuDao.selectMgrMenu(mgrMenuVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrMenu() throws Exception {
		return mgrMenuDao.selectListMgrMenu();
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrMenuTop(LoginVo loginVo) throws Exception {
		return mgrMenuDao.selectListMgrMenuTop(loginVo);
	}
	
	public String selectMgrMenuCode(String url) throws Exception {
		return (String)mgrMenuDao.selectMgrMenuCode(url);
	}
}