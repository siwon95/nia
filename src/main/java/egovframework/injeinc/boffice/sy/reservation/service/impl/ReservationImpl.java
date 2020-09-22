
package egovframework.injeinc.boffice.sy.reservation.service.impl;


import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.reservation.dao.ReservationDao;
import egovframework.injeinc.boffice.sy.reservation.service.ReservationSvc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * ReservationImpl
 * 2015.06.05 : LDY
 */

@Service("ReservationSvc")
public class ReservationImpl extends EgovAbstractServiceImpl implements
	ReservationSvc {
	
    @Resource(name="ReservationDao")
    private ReservationDao reservationDao;
    
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	public HashMap<String, Object> retriveListReservation(HashMap<String, String> param) throws Exception {
  		HashMap<String, Object> output = new HashMap<String, Object>();
  		
  		try {
  			//목록
  			
  			ArrayList<Object> listDao = (ArrayList<Object>) reservationDao.selectReservation(param);
  		
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
	
	public HashMap<String, Object> registTreeReservation(HashMap<String, Object> paramObject) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> param = new HashMap<String, Object>();

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			int cnt = paramObject == null ? 0 : (Integer) paramObject.get("objsize");
			
				reservationDao.deleteReservation(param);
				
				for(int i=0;i<cnt;i++){
					param.put("sid",paramObject.get("id_"+i));
					param.put("sparent",paramObject.get("parent_"+i));
					param.put("caName",paramObject.get("text_"+i));
					param.put("sposition",paramObject.get("position_"+i));
					param.put("mgrUrl",paramObject.get("mgrUrl_"+i));
					param.put("usrUrl",paramObject.get("usrUrl_"+i));
					param.put("useYn",paramObject.get("useYn_"+i));
					param.put("regId",paramObject.get("regid"));
					boolean reSult = false;
					reSult = reservationDao.createReservation(param);
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
	
	/** 아이디중복체크 */
	public HashMap<String, Object> retriveReservationAx(HashMap<String, String> param)
			throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();

		// 목록
		ArrayList<Object> listDao = (ArrayList<Object>) reservationDao.selectReservationAx(param);
		output.put("rowDataList", listDao);
		if (listDao.size() == 0) {
			output.put("ReservationAxValue", "코드를 사용할수있습니다.");
			return output;
		} else {
			output.put("ReservationAxValue", "코드 중복");
			return output;
		}

	}
}