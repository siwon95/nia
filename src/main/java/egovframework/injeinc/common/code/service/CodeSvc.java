package egovframework.injeinc.common.code.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

/**
 * <PRE>
 * 1. FileName : CodeSvc.java
 * 2. Package  : egovframework.injeinc.common.code.service;
 * 3. Comment  : 
 * 4. Programmer   : jsh
 * 5. Date   : 2014. 5. 24.
 * </PRE>
 */
@Service
public interface CodeSvc {
	
	
	/**
	 * 콤보데이터 서비스
	 * 
	 * @return
	 */
	public HashMap<String, Object> retrieveCodeAll(HashMap<String, String> param) throws Exception;
	
	public HashMap<String, Object> retrieveCommonCode(HashMap<String, String> param) throws Exception;
	
	public HashMap<String, Object> retrieveDeptCodeAll(HashMap<String, String> param) throws Exception;
	
	public HashMap<String, Object> retrievePgCodeAll(HashMap<String, String> param) throws Exception;
	
	/**
	 * 코드그룹
	 * 
	 * @return
	 */
	public HashMap<String, Object> retrieveCodeGroup(HashMap<String, String> param) throws Exception;
	
}