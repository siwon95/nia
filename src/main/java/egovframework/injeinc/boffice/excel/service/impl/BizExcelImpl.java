package egovframework.injeinc.boffice.excel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.excel.dao.BizExcelDao;
import egovframework.injeinc.boffice.excel.service.BizExcelSvc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("BizExcelService")
public class BizExcelImpl extends EgovAbstractServiceImpl implements BizExcelSvc {

	@Resource(name = "BizExcelDAO")
    private BizExcelDao bizExcelDAO;


	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(BizExcelImpl.class.getClass());

	/** 리스트 조회 */
	public List selectList(String nmSpace,Object obj) throws Exception {
		List resultList = bizExcelDAO.selectBizList(nmSpace,obj);
		return resultList == null ? new ArrayList() : resultList;
	}

}
