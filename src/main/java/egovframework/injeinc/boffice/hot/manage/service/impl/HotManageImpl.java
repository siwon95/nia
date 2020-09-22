package egovframework.injeinc.boffice.hot.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.injeinc.boffice.hot.manage.dao.HotManageDao;
import egovframework.injeinc.boffice.hot.manage.service.HotManageSvc;
import egovframework.injeinc.boffice.hot.manage.vo.HotManageVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("HotManageSvc")
public class HotManageImpl extends EgovAbstractServiceImpl implements
        HotManageSvc {
	
    /** ID Generation */
    @Resource(name="hotManageIdService")    
    private EgovIdGnrService hotManageIdService;
    
    @Resource(name="HotManageDao")
    private HotManageDao hotManageDao;
    
	@Autowired
	private DataSourceTransactionManager transactionManager;

	public List retrieveListHotManage(HotManageVo hotManageVO) throws Exception {
		return hotManageDao.selectListHotManage(hotManageVO);
	}

	public int retrievePagHotManage(HotManageVo hotManageVO) throws Exception {
		return hotManageDao.selectPagHotManage(hotManageVO);
	}

	public void registHotManage(HotManageVo hotManageVO) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			if(hotManageVO != null){
				hotManageVO.setHlIdx(hotManageIdService.getNextStringId());
			}
			hotManageDao.insertHotManage(hotManageVO);
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
	}

	public void modifyHotManage(HotManageVo hotManageVO) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			if(hotManageVO.getUseYn().equals("N")){
				hotManageDao.updateHlSort(hotManageVO.getHlSort());		//순서바꾸기
				
				hotManageVO.setHlSort("0");	//바꾼후 0으로 set
			}
			
			hotManageDao.updateHotManage(hotManageVO);
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
	}

	public HotManageVo retrieveFile(HotManageVo hotManageVO) throws Exception {
		return hotManageDao.selectFile(hotManageVO);
	}

	public HotManageVo retrieveMinStep(HotManageVo hotManageVO)
			throws Exception {
		return hotManageDao.selectMinStep(hotManageVO);
	}

	public HotManageVo retrieveMaxStep(HotManageVo hotManageVO)
			throws Exception {
		return hotManageDao.selectMaxStep(hotManageVO);
	}

	public void modifyStep(HotManageVo hotManageVO) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			hotManageDao.updateStep(hotManageVO);
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		
		transactionManager.commit(status);
	}

	public void removeHotManage(HotManageVo hotManageVO) throws Exception {
		hotManageDao.deleteHotManage(hotManageVO);
	}

	public List retrieveListHotManageMain() throws Exception {
		return hotManageDao.selectListHotManageMain();
	}
	
}