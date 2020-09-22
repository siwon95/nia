package egovframework.injeinc.foffice.ex.emp.service;

import java.util.List;

import egovframework.injeinc.boffice.group.emp.vo.EmpVo;



public interface EmpFSvc {
	
	/** 부서 리스트 조회 */
	public List retrieveListDepartment() throws Exception;
	
	/** 리스트 조회 */
	public List retrieveListEmp(EmpVo empVo) throws Exception;
	
	/** 부서 조회 */
	public EmpVo retrieveCdCode(EmpVo empVo) throws Exception;
	
}