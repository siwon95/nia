package egovframework.injeinc.foffice.ex.dept.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;

@Repository("DeptFDao")
public class DeptFDao extends EgovAbstractMapper {
	
    /** 부서 조회*/
    public List selectListDepartment() throws Exception {
        return selectList("DeptFDao.selectListDepartment", null);
    }
    
    /** 직원 조회*/
    public List selectListEmp(GroupDeptVo groupDeptVo) throws Exception {
        return selectList("DeptFDao.selectListEmp", groupDeptVo);
    }
    
    /** 부서 상세 조회*/
    public GroupDeptVo selectDepartment(GroupDeptVo groupDeptVo) throws Exception {
    	return (GroupDeptVo)selectOne("DeptFDao.selectDepartment", groupDeptVo);
    }
    
    /** 부서 코드 조회*/
    public String selectCdCode(String cdIdx) throws Exception {
    	return (String)selectOne("DeptFDao.selectCdCode", cdIdx);
    }
    
    /** 부서 코드 조회*/
    public List selectOrgMap() throws Exception {
    	return selectList("DeptFDao.selectOrgMap", null);
    }
}