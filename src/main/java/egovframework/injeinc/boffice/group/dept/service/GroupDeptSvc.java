package egovframework.injeinc.boffice.group.dept.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;

public interface GroupDeptSvc {
	/** 리스트 조회 */
	public List retrieveListGroupDeptSub(GroupDeptVo groupDeptVO) throws Exception;
	/** 수정(체크) */
	public void mofityGroupDept(GroupDeptVo groupDeptVO) throws Exception;
	/** 상위값 조회 */
	public String retrieveMaxCdDepStep(GroupDeptVo groupDeptVO) throws Exception;
	/** 하위값 조회 */
	public String retrieveMinCdDepStep(GroupDeptVo groupDeptVO) throws Exception;
	/** 수정(당하는 번호를 임의값으로) */
	public void modiftyTempCdDepStepList(GroupDeptVo groupDeptVO) throws Exception;
	/** 수정(첫번째 수정) */
	public void modiftyCdDepStepList(GroupDeptVo groupDeptVO) throws Exception;
	/** 수정(두번째 수정) */
	public void modiftyCdDepStep2List(GroupDeptVo groupDeptVO) throws Exception;
	/** 수정(cdCode 조합 수정) */
	public void modiftyCdCodeList(GroupDeptVo groupDeptVO) throws Exception;
	/** cdIdx Max값 조회*/
	public String retrieveMaxCdIdx(GroupDeptVo groupDeptVO) throws Exception;
	/** 등록*/
	public void registGroupDept(GroupDeptVo groupDeptVO) throws Exception;
	/** 수정(이름)*/
	public void mofityGroupDeptSub(GroupDeptVo groupDeptVO) throws Exception;
	/** 삭제*/
	public void removeGroupDept(GroupDeptVo groupDeptVO) throws Exception;
	/** 상세 조회*/
	public GroupDeptVo retrieveGroupDept(GroupDeptVo groupDeptVO) throws Exception;
	/** 상세 조회*/
	public GroupDeptVo retrieveGroupDeptLayout(HashMap<String, Object> param) throws Exception;
	/** 상세 수정*/
	public void modifyGroupDeptDetail(GroupDeptVo groupDeptVO) throws Exception;
	/** 직원 테이블 depstep 변경*/
	public void mofityGroupDeptStep(GroupDeptVo groupDeptVO) throws Exception;

	@SuppressWarnings("rawtypes")
	public List retrieveListDepartGroup() throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListCmsDepartGroup() throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListCmsDepartGroup2() throws Exception;
	
	/** 관리자 부서 리스트 */
	@SuppressWarnings("rawtypes")
	public List retrieveListCmsDepartGroup3() throws Exception;
	
	/** 등록*/
	public void insertEzDepartmentTemp(GroupDeptVo groupDeptVO) throws Exception;
	
	public void deleteEzDepartmentTemp(GroupDeptVo groupDeptVO) throws Exception;
	
	public void updateCdUseEzDepartment(GroupDeptVo groupDeptVO) throws Exception;
	
	public void insertEzDepartment(GroupDeptVo groupDeptVO) throws Exception;
	
	public void updateEzDepartment(GroupDeptVo groupDeptVO) throws Exception;
	
	/** 부서리스트(행정공표등록시) */
	public List<GroupDeptVo> retrieveListDepartment() throws Exception;
	
	
}