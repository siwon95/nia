package egovframework.injeinc.boffice.group.dept.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;

@Repository("GroupDeptDao") 
public class GroupDeptDao extends EgovAbstractMapper {
	
	/** 리스트 조회 */
	public List selectListGroupDeptSub(GroupDeptVo groupDeptVO) throws Exception {
		return selectList("GroupDeptDao.selectListGroupDeptSub", groupDeptVO);
	}
	/** 수정(체크) */
	public void updateGroupDept(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateGroupDept", groupDeptVO);
	}
	/** 상위값 구하기 */
	public String selectMaxCdDepStep(GroupDeptVo groupDeptVO) throws Exception {
		return (String)selectOne("GroupDeptDao.selectMaxCdDepStep", groupDeptVO);
	}
	/** 하위값 구하기 */
	public String selectMinCdDepStep(GroupDeptVo groupDeptVO) throws Exception {
		return (String)selectOne("GroupDeptDao.selectMinCdDepStep", groupDeptVO);
	}
	/** 수정(당하는 번호를 임의값으로) */
	public void updateTempCdDepStepList(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateTempCdDepStepList", groupDeptVO);
	}
	/** 수정(첫번째 수정) */
	public void updateCdDepStepList(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateCdDepStepList", groupDeptVO);
	}
	/** 수정(두번째 수정) */
	public void updateCdDepStep2List(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateCdDepStep2List", groupDeptVO);
	}
	/** 수정(cdCode 조합) */
	public void updateCdCodeList(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateCdCodeList", groupDeptVO);
	}
	/** cdIdx 최대값 조회 */
	public String selectMaxCdIdx(GroupDeptVo groupDeptVO) throws Exception {
		return (String)selectOne("GroupDeptDao.selectMaxCdIdx", groupDeptVO);
	}
	/** 등록 */
	public void insertGroupDept(GroupDeptVo groupDeptVO) throws Exception {
		insert("GroupDeptDao.insertGroupDept", groupDeptVO);
	}
	/** 수정(이름) */
	public void updateGroupDeptSub(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateGroupDeptSub", groupDeptVO);
	}
	/** 삭제 */
	public void deleteGroupDept(GroupDeptVo groupDeptVO) throws Exception {
		delete("GroupDeptDao.deleteGroupDept", groupDeptVO);
	}
	/** 상세 조회*/
	public GroupDeptVo selectGroupDept(GroupDeptVo groupDeptVO) throws Exception {
		return (GroupDeptVo)selectOne("GroupDeptDao.selectGroupDept", groupDeptVO);
	}
	/** 상세 조회*/
	public GroupDeptVo selectGroupDeptLayout(HashMap<String, Object> param) throws Exception {
		return (GroupDeptVo)selectOne("GroupDeptDao.selectGroupDeptLayout", param);
	}
	/** 상세 수정*/
	public void updateGroupDeptDetail(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateGroupDeptDetail", groupDeptVO);
	}
	/** 직원테이블 depstep 수정*/
	public void updateGroupDeptStep(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateGroupDeptStep", groupDeptVO);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListDepartGroup() throws Exception {
		return selectList("GroupDeptDao.selectListDepartGroup", "");
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListCmsDepartGroup() throws Exception {
		return selectList("GroupDeptDao.selectListCmsDepartGroup", "");
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListCmsDepartGroup2() throws Exception {
		return selectList("GroupDeptDao.selectListCmsDepartGroup2", "");
	}
	/** 등록 */
	public void insertEzDepartmentTemp(GroupDeptVo groupDeptVO) throws Exception {
		insert("GroupDeptDao.insertEzDepartmentTemp", groupDeptVO);
	}
	
	/** 삭제 */
	public void deleteEzDepartmentTemp(GroupDeptVo groupDeptVO) throws Exception {
		delete("GroupDeptDao.deleteEzDepartmentTemp", groupDeptVO);
	}
	
	/** 갱신에서 빠진 부서  제외*/
	public void updateCdUseEzDepartment(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateCdUseEzDepartment", groupDeptVO);
	}
	
	/** 신규 부서  삽입*/
	public void insertEzDepartment(GroupDeptVo groupDeptVO) throws Exception {
		insert("GroupDeptDao.insertEzDepartment", groupDeptVO);
	}
	
	/** 부서 갱신*/
	public void updateEzDepartment(GroupDeptVo groupDeptVO) throws Exception {
		update("GroupDeptDao.updateEzDepartment", groupDeptVO);
	}
	
	/** 부서리스트 조회 */
	public List<GroupDeptVo> selectListDepartment() throws Exception {
		return selectList("GroupDeptDao.selectListDepartment", "");
	}
	
	/** 부서 리스트 */
	@SuppressWarnings("rawtypes")
	public List selectListCmsDepartGroup3() throws Exception {
		return selectList("GroupDeptDao.selectListCmsDepartGroup3", "");
	}
}