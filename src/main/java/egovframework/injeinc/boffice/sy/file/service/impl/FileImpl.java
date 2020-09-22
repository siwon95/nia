package egovframework.injeinc.boffice.sy.file.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.file.dao.FileDao;
import egovframework.injeinc.boffice.sy.file.service.FileSvc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("FileSvc")
public class FileImpl extends EgovAbstractServiceImpl implements FileSvc {
	
    @Resource(name="FileDao")
    private FileDao fileDao;
    
    @Autowired
	private DataSourceTransactionManager transactionManager;
    
    /**
     * 파일 업로드
     */
    public HashMap<String, Object> registFile(HashMap<String, Object> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
					
			boolean reSult = false;
			reSult = fileDao.createFile(param);
				
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
    
}