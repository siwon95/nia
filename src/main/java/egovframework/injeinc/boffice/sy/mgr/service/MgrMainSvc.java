package egovframework.injeinc.boffice.sy.mgr.service;

import java.util.List;

import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainVo;

public interface MgrMainSvc {
	public List retrieveMgrMainMinwonList(MgrMainVo mgrMainVo) throws Exception;
	public List retrieveMgrMainBoardList(MgrMainVo mgrMainVo) throws Exception;
	public List retrieveMgrMainCommExpandList(MgrMainVo mgrMainVo) throws Exception;
	public List retrieveMgrMainCommEventList(MgrMainVo mgrMainVo) throws Exception;
	public int retrieveMgrMainMinwonCount(MgrMainVo mgrMainVo) throws Exception;
	public int retrieveMgrMainBoardCount(MgrMainVo mgrMainVo) throws Exception;
	public int retrieveMgrMainCommCount(MgrMainVo mgrMainVo) throws Exception;
}

