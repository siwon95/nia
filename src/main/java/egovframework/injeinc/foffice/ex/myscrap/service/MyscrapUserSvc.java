package egovframework.injeinc.foffice.ex.myscrap.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import egovframework.injeinc.foffice.ex.myscrap.vo.MyscrapVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


public interface MyscrapUserSvc{

	public void insertMyscrapUser(MyscrapVO myscrapVO) throws Exception;

	public Map<String, Object> selectMyscrapUserList(MyscrapVO myscrapVO) throws Exception;

	public void deleteMyscrap(MyscrapVO myscrapVO) throws Exception;
	
	public int selectdupCnt(MyscrapVO myscrapVO) throws Exception;
		

}
