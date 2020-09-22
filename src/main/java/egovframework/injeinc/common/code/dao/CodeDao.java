package egovframework.injeinc.common.code.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

//import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * <PRE>
 * 1. FileName : CodeDao.java
 * 2. Package  : egovframework.injeinc.common.code.dao;
 * 3. Comment  : 
 * 4. Programmer   : jsh
 * 5. Date   : 2014. 5. 24.
 * </PRE>
 */
@Repository("CodeDao")
public class CodeDao extends EgovAbstractMapper {
	
	/**
	 * 콤보데이터 
	 * 
	 * @param paramVo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> selectCodeAll(HashMap<String, String> param) throws Exception {
		return selectList("code.selectCodeAll", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selectCommonCode(HashMap<String, String> param) throws Exception {
		return selectList("code.selectCommonCode", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selectDeptCodeAll(HashMap<String, String> param) throws Exception {
		return selectList("code.selectDeptCodeAll", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selectPgCodeAll(HashMap<String, String> param) throws Exception {
		return selectList("code.selectPgCodeAll", param);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object> selectCodeByCodeGroup(HashMap<String, String> param) throws Exception {
		return selectList("code.selectCodeByCodeGroup", param);
	}
	
}