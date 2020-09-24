package egovframework.injeinc.foffice.nia.search.service;

import java.util.Map;
import egovframework.injeinc.foffice.nia.search.vo.SearchVO;

public interface SearchSvc {
	
	public Map<String, Object> searchList(SearchVO searchVo) throws Exception;

}
