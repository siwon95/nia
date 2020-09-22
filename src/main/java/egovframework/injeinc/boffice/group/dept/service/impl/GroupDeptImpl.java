package egovframework.injeinc.boffice.group.dept.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.cmm.Message;
import egovframework.injeinc.boffice.group.dept.dao.GroupDeptDao;
import egovframework.injeinc.boffice.group.dept.service.GroupDeptSvc;
import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("GroupDeptSvc")
public class GroupDeptImpl extends EgovAbstractServiceImpl implements
GroupDeptSvc {
    
    @Resource(name="GroupDeptDao")
    private GroupDeptDao groupDeptDao;

	public List retrieveListGroupDeptSub(GroupDeptVo groupDeptVO) throws Exception {
		return groupDeptDao.selectListGroupDeptSub(groupDeptVO);
	}
	
	public void mofityGroupDept(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateGroupDept(groupDeptVO);
	}

	public String retrieveMaxCdDepStep(GroupDeptVo groupDeptVO)
			throws Exception {
		return groupDeptDao.selectMaxCdDepStep(groupDeptVO);
	}

	public String retrieveMinCdDepStep(GroupDeptVo groupDeptVO)
			throws Exception {
		return groupDeptDao.selectMinCdDepStep(groupDeptVO);
	}
	
	public void modiftyTempCdDepStepList(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateTempCdDepStepList(groupDeptVO);
	}
 
	public void modiftyCdDepStepList(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateCdDepStepList(groupDeptVO);
	}
	
	public void modiftyCdDepStep2List(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateCdDepStep2List(groupDeptVO);
	}

	public void modiftyCdCodeList(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateCdCodeList(groupDeptVO);
	}

	public String retrieveMaxCdIdx(GroupDeptVo groupDeptVO) throws Exception {
		return groupDeptDao.selectMaxCdIdx(groupDeptVO);
	}

	public void registGroupDept(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.insertGroupDept(groupDeptVO);
	}

	public void mofityGroupDeptSub(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateGroupDeptSub(groupDeptVO);
	}

	public void removeGroupDept(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.deleteGroupDept(groupDeptVO);
	}

	public GroupDeptVo retrieveGroupDept(GroupDeptVo groupDeptVO)
			throws Exception {
		return groupDeptDao.selectGroupDept(groupDeptVO);
	}

	public void modifyGroupDeptDetail(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateGroupDeptDetail(groupDeptVO);
	}

	public void mofityGroupDeptStep(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateGroupDeptStep(groupDeptVO);
	}

	public GroupDeptVo retrieveGroupDeptLayout(HashMap<String, Object> param)
			throws Exception {
		return groupDeptDao.selectGroupDeptLayout(param);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListDepartGroup() throws Exception {
		return groupDeptDao.selectListDepartGroup();
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListCmsDepartGroup() throws Exception {
		return groupDeptDao.selectListCmsDepartGroup();
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListCmsDepartGroup2() throws Exception {
		return groupDeptDao.selectListCmsDepartGroup2();
	}
	
	public void insertEzDepartmentTemp(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.insertEzDepartmentTemp(groupDeptVO);
	}
	
	public void deleteEzDepartmentTemp(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.deleteEzDepartmentTemp(groupDeptVO);
	}
	
	public void updateCdUseEzDepartment(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateCdUseEzDepartment(groupDeptVO);
	}
	
	public void insertEzDepartment(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.insertEzDepartment(groupDeptVO);
	}
	
	public void updateEzDepartment(GroupDeptVo groupDeptVO) throws Exception {
		groupDeptDao.updateEzDepartment(groupDeptVO);
	}

	
	public List<GroupDeptVo> retrieveListDepartment() throws Exception {
		return groupDeptDao.selectListDepartment();
	}

	
	/** 부서 리스트 */
	@SuppressWarnings("rawtypes")
	public List retrieveListCmsDepartGroup3() throws Exception {
		return groupDeptDao.selectListCmsDepartGroup3();
	}
	
}