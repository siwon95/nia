package egovframework.injeinc.boffice.sy.mgr.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;

@Repository("MgrDao")
public class MgrDao extends EgovAbstractMapper {
	

    /** 글 목록을 조회한다.*/
    public List selectListMgr(MgrVo mgrVO) throws Exception {
        return selectList("MgrDao.selectListMgr", mgrVO);
    }
    
    /** 총 건수 조회 */
   	public int selectPagMgr(MgrVo mgrVO) throws Exception {
   		return (Integer)selectOne("MgrDao.selectPagMgr", mgrVO);
   	}
   	
   	/** 아이디중복체크 */
	@SuppressWarnings("unchecked")
	public List<Object> selectMgrAx(HashMap<String, String> param) throws Exception {
		return (List<Object>) selectList("MgrDao.selectMgrAx", param);
	}
	
	/** 관리자 트리 */
	@SuppressWarnings("unchecked")
	public List<Object> selectListTreeMgr(HashMap<String, String> param) throws Exception {
		return (List<Object>) selectList("MgrDao.selectListTreeMgr", param);
	}
	
	/** 트리권한저장 */
	public boolean createTreeMgr(HashMap<String, Object> paramObject) throws Exception{
		Integer nResult = 0;
		nResult = update("MgrDao.insertTreeMgr", paramObject);
		return nResult > 0;
	}
	
	/** 트리권한삭제 */
	public void deleteTreeMgr(HashMap<String, Object> paramObject) throws Exception {
		delete("MgrDao.deleteTreeMgr", paramObject);
	}	
	  
    /** 등록 */
  	public void createMgr(MgrVo mgrVO) throws Exception {
  		insert("MgrDao.insertMgr", mgrVO);
  	}
  	
  	 /** 등록 */
  	public void createAllMgr(MgrVo mgrVO) throws Exception {
  		insert("MgrDao.insertAllMgr", mgrVO);
  	}
  	
  	/** 상세내용 조회 */
	public MgrVo selectMgr(MgrVo mgrVO) throws Exception {
		return (MgrVo)selectOne("MgrDao.selectMgr", mgrVO);
	}
	
	/** 상세내용 조회(수정시) */
	public MgrVo selectMgrUser(MgrVo mgrVO) throws Exception {
		return (MgrVo)selectOne("MgrDao.selectMgrUser", mgrVO);
	}
	
	 /** 수정 */
	public void updateMgr(MgrVo mgrVO) throws Exception {
		update("MgrDao.updateMgr", mgrVO);
	}
	
	/** 삭제 */
	public void deleteMgr(MgrVo mgrVO) throws Exception {
		delete("MgrDao.deleteMgr", mgrVO);
	}

	public int selectMatchMgrPwdByMgrIdAndMgrPw(MgrVo mgrVO) throws Exception {
		return (Integer) selectOne("MgrDao.selectMatchMgrPwdByMgrIdAndMgrPw", mgrVO);
	}
   	
}