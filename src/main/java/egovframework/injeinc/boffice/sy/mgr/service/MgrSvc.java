package egovframework.injeinc.boffice.sy.mgr.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;


public interface MgrSvc {
	
	
	/** 리스트 조회 */
	public List retrieveListMgr(MgrVo vo) throws Exception;
	
	/** 총 건수 조회 */
	public int retrievePagMgr(MgrVo vo) throws Exception;
	
	/** 아이디중복체크 */
	public HashMap<String, Object> retriveMgrAx(HashMap<String, String> param) throws Exception;
	
	/** 관리자매뉴Tree */
	public HashMap<String, Object> retriveTreeListMgr(HashMap<String, String> param) throws Exception;
	
	/** 관리자매뉴권한체크 저장*/
	public HashMap<String, Object> registTreeMgr(HashMap<String, Object> paramObject) throws Exception;
	
	 /** 등록 */
	public void registMgr(MgrVo vo) throws Exception;
	
	public void registAllMgr(MgrVo vo) throws Exception;
	
	/** 상세내용 조회 */
	public MgrVo retrieveMgr(MgrVo vo) throws Exception;
	
	/** 상세내용 조회(수정시) */
	public MgrVo retrieveMgrUser(MgrVo vo) throws Exception;
	
	/** 수정 */
	public void updateMgr(MgrVo vo) throws Exception;

    /** 삭제 */
	public void removeMgr(MgrVo vo) throws Exception;
	
	/** 관리자 패스워드 일치 여부 체크 */
	public boolean isMatchMgrPwd(MgrVo mgrVO) throws Exception;
	
}