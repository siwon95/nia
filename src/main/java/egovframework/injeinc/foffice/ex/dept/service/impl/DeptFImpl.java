package egovframework.injeinc.foffice.ex.dept.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.googlecode.ehcache.annotations.Cacheable;
import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;
import egovframework.injeinc.foffice.ex.dept.dao.DeptFDao;
import egovframework.injeinc.foffice.ex.dept.service.DeptFSvc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("DeptFSvc")
public class DeptFImpl extends EgovAbstractServiceImpl implements
        DeptFSvc {
	
    
    @Resource(name="DeptFDao")
    private DeptFDao deptFDao;
	
	public List retrieveListEmp(GroupDeptVo groupDeptVo) throws Exception {
	    return deptFDao.selectListEmp(groupDeptVo);
	}
	
	@Cacheable(cacheName = "menuCache")
	public List retrieveListDepartment() throws Exception {
		return deptFDao.selectListDepartment();
	}

	public GroupDeptVo retrieveDepartment(GroupDeptVo groupDeptVo) throws Exception {
		return deptFDao.selectDepartment(groupDeptVo);
	}

	public String retrieveCdCode(String cdIdx) throws Exception {
		return deptFDao.selectCdCode(cdIdx);
	}
	
	public List selectOrgMap() throws Exception {
		return deptFDao.selectOrgMap();
	}


}