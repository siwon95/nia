package egovframework.injeinc.foffice.ex.dept.service;

import java.util.List;

import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;



public interface DeptFSvc {
	
	/** 부서 조회 */
	public List retrieveListDepartment() throws Exception;
	
	/** 리스트 조회 */
	public List retrieveListEmp(GroupDeptVo groupDeptVo) throws Exception;
	
	/** 부서상세 조회 */
	public GroupDeptVo retrieveDepartment(GroupDeptVo groupDeptVo) throws Exception;
	
	/** 부서 코드 조회 */
	public String retrieveCdCode(String cdIdx) throws Exception;
	
	/** 조직도 */
	public List selectOrgMap() throws Exception;
	
}