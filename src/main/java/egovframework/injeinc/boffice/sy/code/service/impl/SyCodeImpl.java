package egovframework.injeinc.boffice.sy.code.service.impl;

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
import egovframework.injeinc.boffice.sy.code.dao.SyCodeDao;
import egovframework.injeinc.boffice.sy.code.service.SyCodeSvc;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
 

/**
 * DeptImpl
 * 2015.06.05 : LDY
 */

@Service("SyCodeSvc")
public class SyCodeImpl extends EgovAbstractServiceImpl implements
	SyCodeSvc {
	
    @Resource(name="SyCodeDao")
    private SyCodeDao syCodeDao;
    
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
    

    public HashMap<String, Object> retriveListCode(HashMap<String, String> param) throws Exception {
  		HashMap<String, Object> output = new HashMap<String, Object>();
  		
  		try {
  			//목록
  			
  			ArrayList<Object> listDao = (ArrayList<Object>) syCodeDao.selectCode(param);
  		
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
    

    
     public HashMap<String, Object> registAllCode(HashMap<String, Object> paramObject) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			int cnt = paramObject == null ? 0 : (Integer) paramObject.get("objsize");
			
			syCodeDao.deleteAllCode(param);
				for(int i=0;i<cnt;i++){
					param.put("sid",paramObject.get("id_"+i));
					param.put("sparent",paramObject.get("parent_"+i));
					param.put("stext",paramObject.get("text_"+i));
					param.put("sposition",paramObject.get("position_"+i));
					param.put("suseyn",paramObject.get("useryn_"+i));
					boolean reSult = false;
					reSult = syCodeDao.createCode(param);
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

 	/** 팝업존 코드 리스트*/
	public List<CmmCodeVo> selectPopupZoneCodeList(CmmCodeVo popupZoneCode) throws Exception {
		return syCodeDao.selectPopupZoneCodeList(popupZoneCode) ;
	}


	/** 하나의 코드를 등록*/
	
	public void registCode(CmmCodeVo cmmCodeVo) throws Exception {
		
		int ordNo = syCodeDao.selectLastCodeOrdNoByCodeUpr(cmmCodeVo);
		ordNo++;
		
		syCodeDao.updateCodeOrdNoPlusAfterOrdNo(ordNo);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("sid",cmmCodeVo.getCode());
		param.put("sparent",cmmCodeVo.getCodeUpr());
		param.put("stext",cmmCodeVo.getCodeName());
		param.put("sposition",ordNo);
		param.put("suseyn",cmmCodeVo.getUseYn());
		
		boolean reSult = false;
		reSult = syCodeDao.createCode(param);
		
		
	}

	
	public void modifyCode(CmmCodeVo cmmCodeVo) throws Exception {
		syCodeDao.updateCode(cmmCodeVo);
	}

	
	public void removeCode(CmmCodeVo cmmCodeVo) throws Exception {
		int ordNo = syCodeDao.selectNowOrdNo(cmmCodeVo);
		syCodeDao.deleteCode(cmmCodeVo);
		syCodeDao.updateCodeOrdNoMinusAfterOrdNo(ordNo);
	}



	
	public void modifyCodeOrdNo(CmmCodeVo cmmCodeVo, String ordType) throws Exception {
		if("UP".equals(ordType)){
			syCodeDao.updateCodeOrdNoForUp(cmmCodeVo);
		}else if("DOWN".equals(ordType)){
			syCodeDao.updateCodeOrdNoForDown(cmmCodeVo);
		}
		
	}
    
}