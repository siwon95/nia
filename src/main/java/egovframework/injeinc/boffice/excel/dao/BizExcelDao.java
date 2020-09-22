package egovframework.injeinc.boffice.excel.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("BizExcelDAO")
public class BizExcelDao extends EgovAbstractMapper {

    /** 리스트 조회 */
	@SuppressWarnings("unchecked")
	public List selectBizList(String nmSpace,Object obj) throws Exception {
		return selectList(nmSpace, obj);
	}

}
