package egovframework.injeinc.foffice.nia.search.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.foffice.nia.search.dao.SearchDao;
import egovframework.injeinc.foffice.nia.search.service.SearchSvc;
import egovframework.injeinc.foffice.nia.search.vo.SearchVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="SearchSvc")
public class SearchImpl extends EgovAbstractServiceImpl  implements SearchSvc{
	
	@Resource(name = "SearchDao")
	private SearchDao searchDao;
	
	@Override
	public Map<String, Object> searchList(SearchVO searchVo) throws Exception {
		
		List<SearchVO> result = searchDao.selectSearchList(searchVo);
		int cnt = searchDao.selectSearchCnt(searchVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
}
