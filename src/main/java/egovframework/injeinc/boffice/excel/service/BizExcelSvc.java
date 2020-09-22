package egovframework.injeinc.boffice.excel.service;

import java.util.List;

public interface BizExcelSvc {

	/** 리스트 조회 */
	public List selectList(String nmSpace,Object obj) throws Exception;

}
