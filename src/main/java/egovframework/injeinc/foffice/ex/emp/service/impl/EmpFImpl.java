package egovframework.injeinc.foffice.ex.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.group.emp.vo.EmpVo;
import egovframework.injeinc.foffice.ex.emp.dao.EmpFDao;
import egovframework.injeinc.foffice.ex.emp.service.EmpFSvc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("EmpFSvc")
public class EmpFImpl extends EgovAbstractServiceImpl implements
        EmpFSvc {
	
    
    @Resource(name="EmpFDao")
    private EmpFDao empFDao;
	
	public List retrieveListEmp(EmpVo empVo) throws Exception {
	    return empFDao.retireveListEmp(empVo);
	}
	
	public List retrieveListDepartment() throws Exception {
		return empFDao.selectListDepartment();
	}

	public EmpVo retrieveCdCode(EmpVo empVo) throws Exception {
		return empFDao.selectCdCode(empVo);
	}

}