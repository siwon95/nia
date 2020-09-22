package egovframework.injeinc.foffice.ex.emp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;

@Repository("EmpFDao")
public class EmpFDao extends EgovAbstractMapper {
	
    /** 직원 조회*/
    public List retireveListEmp(EmpVo empVo) throws Exception {
        return selectList("EmpFDao.selectListEmp", empVo);
    }
    
    /** 부서 조회*/
    public List selectListDepartment() throws Exception {
        return selectList("EmpFDao.selectListDepartment", null);
    }
    
    /** 부서 조회*/
    public EmpVo selectCdCode(EmpVo empVo) throws Exception {
    	return (EmpVo)selectOne("EmpFDao.selectCdCode", empVo);
    }
}