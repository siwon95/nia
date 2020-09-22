package egovframework.injeinc.boffice.group.emp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.injeinc.boffice.group.emp.dao.EmpDao;
import egovframework.injeinc.boffice.group.emp.service.EmpSvc;
import egovframework.injeinc.boffice.group.emp.vo.EmpTempVo;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("EmpSvc")
public class EmpImpl extends EgovAbstractServiceImpl implements
EmpSvc {
	
    @Resource(name="EmpDao")
    private EmpDao empDao;

    @Autowired
	private DataSourceTransactionManager transactionManager;
    
	public List retrieveListSbEmp(EmpVo empVO) throws Exception {
		return empDao.selectListSbEmp(empVO);
	}

	public List retrieveListEmp(EmpVo empVO) throws Exception {
		return empDao.selectListEmp(empVO);
	}

	public List retrieveListAllNoEmp(EmpVo empVO) throws Exception {
		return empDao.selectListAllNoEmp(empVO);
	}
	
	public int selectAllEmpListTotCnt(EmpVo empVO) throws Exception {
		return empDao.selectAllEmpListTotCnt(empVO);
	}
	
	public int selectEmpListTotCnt(EmpVo empVO) throws Exception {
		return empDao.selectEmpListTotCnt(empVO);
	}

	public void modifyUseCheck(EmpVo empVO) throws Exception {
		empDao.updateUseCheck(empVO);
	}

	public List retrieveListDepstep1() throws Exception {
		return empDao.selectListDepstep1();
	}

	public List retrieveListEmpDep2(HashMap<String, String> param) throws Exception {
		return empDao.selectListDepstep2(param);
	}
	
	public List retrieveListEmpDep3(HashMap<String, String> param) throws Exception {
		return empDao.selectListDepstep3(param);
	}

	public void registEmp(EmpVo empVO) throws Exception {
		empDao.insertEmp(empVO);
	}

	public String retrieveMaxCeIdx() throws Exception {
		return empDao.selectMaxCeIdx();
	}

	public String retrieveCdIdx(EmpVo empVO) throws Exception {
		return empDao.selectCdIdx(empVO);
	}

	public String retrieveMaxCeSort(EmpVo empVO) throws Exception {
		return empDao.selectMaxCeSort(empVO);
	}

	public EmpVo retrieveEmp(EmpVo empVO) throws Exception {
		return empDao.selectEmp(empVO);
	}

	public void modifyEmp(EmpVo empVO) throws Exception {
		empDao.updateEmp(empVO);
	}

	public void modifyEmpDetail(EmpVo empVO) throws Exception {
		empDao.updateEmpDetail(empVO);
	}

	public String retrieveMinUpCeSort(EmpVo empVO) throws Exception {
		return empDao.selectMinUpCeSort(empVO);
	}
	
	public String retrieveMaxDownCeSort(EmpVo empVO) throws Exception {
		return empDao.selectMaxDownCeSort(empVO);
	}

	public void modifyTempCeSort(EmpVo empVO) throws Exception {
		empDao.updateTempCeSort(empVO);
	}

	public void modifyUpCeSort(EmpVo empVO) throws Exception {
		empDao.updateUpCeSort(empVO);
	}

	public void modifyDownCeSort(EmpVo empVO) throws Exception {
		empDao.updateDownCeSort(empVO);
	}

	public void modifyNoEmp(String ceIdx) throws Exception {
		empDao.updateNoEmp(ceIdx);
	}

	public int retrieveMaxUserIdx() throws Exception {
		return empDao.selectMaxUserIdx();
	}

	public void registEmpTemp(EmpTempVo EmpTempVO) throws Exception {
		empDao.insertEmpTemp(EmpTempVO);
	}

	public int retrieveCeIdx(EmpTempVo EmpTempVO) throws Exception {
		return empDao.selectCeIdx(EmpTempVO);
	}

	public EmpTempVo retrieveEmpView(EmpVo EmpVO) throws Exception {
		return empDao.selectEmpView(EmpVO);
	}

	public List retrieveListEmpTemp(EmpTempVo EmpTempVO) throws Exception {
		return empDao.selectListEmpTemp(EmpTempVO);
	}

	public int retrievePagEmpTemp(EmpTempVo EmpTempVO) throws Exception {
		return empDao.selectPagEmpTemp(EmpTempVO);
	}

	public EmpTempVo retrieveEmpTemp(EmpTempVo EmpTempVO) throws Exception {
		return empDao.selectEmpTemp(EmpTempVO);
	}

	public EmpVo retrieveDeptEmp(EmpTempVo EmpTempVO) throws Exception {
		return empDao.selectDeptEmp(EmpTempVO);
	}

	public void registEmp2(EmpTempVo empTempVO) throws Exception {
		empDao.insertEmp2(empTempVO);
	}

	public void modifyEmpDetail(EmpTempVo empTempVO) throws Exception {
		empDao.updateEmpDetail(empTempVO);
	}

	public void modifyEmpTempInsertYn(EmpTempVo empTempVO) throws Exception {
		empDao.updateEmpTempInsertYn(empTempVO);
	}

	public void modifyEmpTempD(EmpTempVo empTempVO) throws Exception {
		empDao.updateEmpTempD(empTempVO);
	}
	
	public void modifyEmpTempRes(EmpTempVo empTempVO) throws Exception {
		empDao.updateEmpTempRes(empTempVO);
	}

	//empTemp 등록
	public void registEmpTempOutside(EmpTempVo empTempVO)
			throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		HashMap<String, String> paramSet = new HashMap<String, String>();
		
		try{
				
			paramSet.put("userIdx",Integer.toString(empTempVO.getUserIdx()));
			empDao.insertEmpTemp(paramSet);
				
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		
		transactionManager.commit(status);
		
	}
		
	//empTemp 일괄등록
	public String registEmpTempAll(EmpTempVo empTempVO)
			throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		int success=0;
		int insertNum=0;
		int updateNum=0;
		int fail=0;
		
		HashMap<String, String> paramSet = new HashMap<String, String>();
		String[] arrIdx = null;
		if(empTempVO != null){
			arrIdx = empTempVO.getIdxValues().split(",");
		}
		String resultTotal = "";
		try{
			for(int c=0; c<arrIdx.length;c++){
				
				paramSet.put("userIdx",arrIdx[c]);
				String updateORGTempResult = empDao.insertEmpTempAll(paramSet);
				
				if(updateORGTempResult.split("/")[0].equals("true")){
					success++;
					if(updateORGTempResult.split("/")[1].equals("insert")){
						insertNum++;
					}else if(updateORGTempResult.split("/")[1].equals("update")){
						updateNum++;
					}
				}else{
					fail++;
				} 
			}
			resultTotal = success+"/"+fail+"/"+insertNum+"/"+updateNum;
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		
		transactionManager.commit(status);
		
		return resultTotal;
	}

	public void removeEmpTempAll(EmpTempVo empTempVO) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		HashMap<String, String> paramSet = new HashMap<String, String>();
		String[] arrIdx = null;
		if(empTempVO != null){
			arrIdx = empTempVO.getIdxValues().split(",");
		}
		try{
			for(int c=0; c<arrIdx.length;c++){
				
				paramSet.put("userIdx",arrIdx[c]);
				empDao.deleteEmpTempAll(paramSet);
				
			}
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		
		transactionManager.commit(status);
		
	}
	
	public void modifyEmpTempAllRestore(EmpTempVo empTempVO) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		HashMap<String, String> paramSet = new HashMap<String, String>();
		String[] arrIdx = null;
		if(empTempVO != null){
			arrIdx = empTempVO.getIdxValues().split(",");
		}
		try{
			for(int c=0; c<arrIdx.length;c++){
				
				paramSet.put("userIdx",arrIdx[c]);
				empDao.updateEmpTempAllRestore(paramSet);
				
			}
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		
		transactionManager.commit(status);
		
	}
	
	public void modifyEmpTempAllMark(EmpTempVo empTempVO) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		HashMap<String, String> paramSet = new HashMap<String, String>();
		String[] arrIdx = null;
		if(empTempVO != null){
			arrIdx = empTempVO.getIdxValues().split(",");
		}
		try{
			for(int c=0; c<arrIdx.length;c++){
				
				paramSet.put("userIdx",arrIdx[c]);
				empDao.updateEmpTempAllMark(paramSet);
				
			}
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		
		transactionManager.commit(status);
		
	}

	public void modifyEmpTemp(EmpTempVo empTempVO) throws Exception {
		empDao.updateEmpTemp(empTempVO);
	}
	
	public EmpTempVo retrieveEmpTempDetail(int userIdx) throws Exception {
		return empDao.selectEmpTempDetail(userIdx);
	}
	
	public void insertGkEmployee(EmpVo empVO) throws Exception {
		empDao.insertGkEmployee(empVO);
	}
	public void deleteGkEmployee(EmpVo empVO) throws Exception {
		empDao.deleteGkEmployee(empVO);
	}
	
	public void insertRenewEmp(EmpVo empVO) throws Exception {
		empDao.insertRenewEmp(empVO);
	}
	
	public void updateRenewEmp(EmpVo empVO) throws Exception {
		empDao.updateRenewEmp(empVO);
	}
	
	public String selectDepartmentStep(EmpVo vo) throws Exception {
		return empDao.selectDepartmentStep(vo);
	}
	
	
}