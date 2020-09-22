package egovframework.injeinc.boffice.sy.mgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.mgr.dao.MgrDao;
import egovframework.injeinc.boffice.sy.mgr.service.MgrSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("MgrSvc")
public class MgrImpl extends EgovAbstractServiceImpl implements
        MgrSvc {
	
    /** ID Generation */
    @Resource(name="egovIdGnrService")    
    private EgovIdGnrService egovIdGnrService;
    
    @Resource(name="MgrDao")
    private MgrDao mgrDao;
 
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
  /** 목록을 조회한다. */
  public List retrieveListMgr(MgrVo mgrVO) throws Exception {
      return mgrDao.selectListMgr(mgrVO);
  }
  
  /** 페이징 조회 */
  public int retrievePagMgr(MgrVo mgrVO) throws Exception {
		return mgrDao.selectPagMgr(mgrVO);
  }
    
  /** 아이디중복체크 */
  public HashMap<String, Object> retriveMgrAx(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		//목록
		ArrayList<Object> listDao = (ArrayList<Object>) mgrDao.selectMgrAx(param);
		output.put("rowDataList", listDao);
		if(listDao.size() == 0) {
			output.put("MgrAxValue", Message.getMessage("615.message"));
			return output;				
		}else{
			output.put("MgrAxValue", Message.getMessage("614.message"));
			return output;
		}
			
	}
  
  
  /** 관리자 트리 */
  public HashMap<String, Object> retriveTreeListMgr(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		try {
  			//목록
			String treeDataUse = param == null ? "" : param.get("treeUse");
			String regId = param == null ? "" : param.get("regId");
			String mgrIdx = param == null ? "" : param.get("mgrIdx");
			if(param != null){
				param.put("treeDataUse", treeDataUse);
				param.put("regId", regId);
				param.put("mgrIdx", mgrIdx);
			}
  			ArrayList<Object> listDao = (ArrayList<Object>) mgrDao.selectListTreeMgr(param);
  			
  			output.put("treeList", listDao);
  			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", Message.getMessage("301.code"));
				output.put("SVC_MSGE", Message.getMessage("301.message"));
  				return output;				
  			}
			
			output.put("SVC_CODE", Message.getMessage("100.code"));
			output.put("SVC_MSGE", Message.getMessage("100.message"));
  			
		} catch (RuntimeException e) {
			output.put("SVC_CODE", Message.getMessage("901.code"));
			output.put("SVC_MSGE", Message.getMessage("901.message"));
  		} catch (Exception e) {  			
  			output.put("SVC_CODE", Message.getMessage("901.code"));
			output.put("SVC_MSGE", Message.getMessage("901.message"));
  		}
  		
  		return output;
	}
  
  /** 트리권한 저장*/
  public HashMap<String, Object> registTreeMgr(HashMap<String, Object> paramObject) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> param = new HashMap<String, Object>();
		HashMap<String, Object> param2 = new HashMap<String, Object>();
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		 
		try {
			int cnt = paramObject ==  null ? 0 : (Integer) paramObject.get("objsize");
			//paramObject
			param2.put("mgrKey", paramObject.get("mgrKey_"+0));
			param2.put("bumoKey", paramObject.get("bumoKey_"+0));
			mgrDao.deleteTreeMgr(param2);
			
			egovLogger.debug("cnt========>"+paramObject.get("objsize"));
			  
				for(int i=0;i<cnt;i++){
					egovLogger.debug("cnt========>"+i);
					param.put("regId",paramObject.get("regId_"+i));
					param.put("mgrKey",paramObject.get("mgrKey_"+i));
					param.put("bumoKey",paramObject.get("bumoKey_"+i));
					param.put("sid",paramObject.get("id"+"_"+i));
					boolean reSult = false;
					reSult = mgrDao.createTreeMgr(param);
				}
		
			
			output.put("SVC_CODE", Message.getMessage("100.code"));
			output.put("SVC_MSGE", Message.getMessage("100.message"));
		} catch (RuntimeException e) {
			transactionManager.rollback(status);
			
			output.put("SVC_CODE", Message.getMessage("901.code"));
			output.put("SVC_MSGE", Message.getMessage("901.message"));
			return output;
		} catch (Exception e) {
			transactionManager.rollback(status);
			
			output.put("SVC_CODE", Message.getMessage("901.code"));
			output.put("SVC_MSGE", Message.getMessage("901.message"));
			return output;
		}
		
		transactionManager.commit(status);
		
		return output;
	}   

    /** 등록 */
    public void registMgr(MgrVo mgrVO) throws Exception {
		mgrDao.createMgr(mgrVO);
    }
    
    /** 등록 */
    public void registAllMgr(MgrVo mgrVO) throws Exception {
		mgrDao.createAllMgr(mgrVO);
    }
  
	  /** 상세내용 조회 */
	public MgrVo retrieveMgr(MgrVo mgrVO) throws Exception {
		MgrVo result = mgrDao.selectMgr(mgrVO);
		if (result == null) {
			result = new MgrVo();
		}		
		return result;
	}
	
	  /** 상세내용 조회 */
	public MgrVo retrieveMgrUser(MgrVo mgrVO) throws Exception {
		MgrVo result = mgrDao.selectMgrUser(mgrVO);
		if (result == null) {
			result = new MgrVo();
		}		
		return result;
	}
	 /** 수정 */
	public void updateMgr(MgrVo mgrVO) throws Exception {
		mgrDao.updateMgr(mgrVO);
	}
	
	/** 삭제 */
	public void removeMgr(MgrVo mgrVO) throws Exception {
		mgrDao.deleteMgr(mgrVO);
	}

	public boolean isMatchMgrPwd(MgrVo mgrVO) throws Exception {
		int cnt = mgrDao.selectMatchMgrPwdByMgrIdAndMgrPw(mgrVO);
		
		if(cnt > 0){
			return true;
		}
		
		return false;
	}

	
}