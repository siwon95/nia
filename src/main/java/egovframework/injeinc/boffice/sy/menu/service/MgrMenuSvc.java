package egovframework.injeinc.boffice.sy.menu.service;

import java.util.List;

import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.sy.menu.vo.MgrMenuVo;

public interface MgrMenuSvc {
	
	public void registMgrMenu(String userid, List<MgrMenuVo> list) throws Exception;
	public void updateMgrMenuHelp(String userid, MgrMenuVo mgrMenuVo) throws Exception;
	public int retrieveMgrMenuCnt(MgrMenuVo mgrMenuVo) throws Exception;
	public MgrMenuVo retrieveMgrMenu(MgrMenuVo mgrMenuVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrMenu() throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListMgrMenuTop(LoginVo loginVo) throws Exception;
	public String selectMgrMenuCode(String url) throws Exception;
}