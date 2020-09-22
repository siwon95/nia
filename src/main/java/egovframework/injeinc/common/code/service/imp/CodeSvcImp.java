package egovframework.injeinc.common.code.service.imp;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.cmm.EgovMessageSource;
import egovframework.injeinc.common.code.dao.CodeDao;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <PRE>
 * 1. FileName : CodeSvc.java
 * 2. Package  : egovframework.injeinc.common.code.service.imp;
 * 3. Comment  : 
 * 4. Programmer   : jsh
 * 5. Date   : 2014. 5. 24.
 * </PRE>
 */
@Service("CodeSvc")
public class CodeSvcImp extends EgovAbstractServiceImpl implements CodeSvc {
	
	@Autowired
	private CodeDao codeDao;
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	/** 공통 코드  매뉴 , BBS */
	public HashMap<String, Object> retrieveCodeAll(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		try {
			//목록
			ArrayList<Object> listDao = (ArrayList<Object>) codeDao.selectCodeAll(param);
			output.put("rowDataList", listDao);
			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", egovMessageSource.getMessage("301.code"));
				output.put("SVC_MSGE", egovMessageSource.getMessage("301.message"));
				return output;				
			}
			
//			output.put("SVC_CODE", egovMessageSource.getMessage("100.code"));
//			output.put("SVC_MSGE", egovMessageSource.getMessage("100.message"));
			
		} catch (RuntimeException e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		} catch (Exception e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		}
		
		return output;
	}
	
	/** 공통 코드 */
	public HashMap<String, Object> retrieveCommonCode(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		try {
			//목록
			ArrayList<Object> listDao = (ArrayList<Object>) codeDao.selectCommonCode(param);
			output.put("rowDataList", listDao);
			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", egovMessageSource.getMessage("301.code"));
				output.put("SVC_MSGE", egovMessageSource.getMessage("301.message"));
				return output;				
			}
			
//			output.put("SVC_CODE", egovMessageSource.getMessage("100.code"));
//			output.put("SVC_MSGE", egovMessageSource.getMessage("100.message"));
			
		} catch (RuntimeException e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		} catch (Exception e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		}
		
		return output;
	}
	
	/**
	 * 콤보데이터 서비스
	 * 
	 * @return
	 */
	public HashMap<String, Object> retrieveDeptCodeAll(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		try {
			//목록
			ArrayList<Object> listDao = (ArrayList<Object>) codeDao.selectDeptCodeAll(param);
			output.put("rowDataList", listDao);
			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", egovMessageSource.getMessage("301.code"));
				output.put("SVC_MSGE", egovMessageSource.getMessage("301.message"));
				return output;				
			}
		
		} catch (RuntimeException e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		} catch (Exception e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		}
		
		return output;
	}
	
	/**
	 * PG 전용 콤보데이터 서비스
	 * 
	 * @return
	 */
	public HashMap<String, Object> retrievePgCodeAll(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		try {
			//목록
			ArrayList<Object> listDao = (ArrayList<Object>) codeDao.selectPgCodeAll(param);
			output.put("rowDataList", listDao);
			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", egovMessageSource.getMessage("301.code"));
				output.put("SVC_MSGE", egovMessageSource.getMessage("301.message"));
				return output;				
			}
		
		} catch (RuntimeException e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		} catch (Exception e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		}
		
		return output;
	}
	
	/**
	 * 코드그룹
	 * 
	 * @return
	 */
	public HashMap<String, Object> retrieveCodeGroup(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		try {
			ArrayList<Object> listDao = (ArrayList<Object>) codeDao.selectCodeByCodeGroup(param);
			output.put("rowDataList", listDao);
			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", egovMessageSource.getMessage("301.code"));
				output.put("SVC_MSGE", egovMessageSource.getMessage("301.message"));
				return output;				
			}
			
			output.put("SVC_CODE", egovMessageSource.getMessage("100.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("100.message"));
		} catch (RuntimeException e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));			
		} catch (Exception e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		}
		
		return output;
	}
	
}