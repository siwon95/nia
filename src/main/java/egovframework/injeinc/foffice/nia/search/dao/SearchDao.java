package egovframework.injeinc.foffice.nia.search.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.nia.search.vo.SearchVO;

@Repository(value="SearchDao")
public class SearchDao extends EgovAbstractMapper {
	
	public List selectSearchList(SearchVO searchVo) throws Exception {
		return null;//selectList("SearchDao.selectSearchList", searchVo);
	}
	
	public int selectSearchCnt(SearchVO searchVo) throws Exception {
		return 0;//(Integer)selectOne("SearchDao.selectSearchCnt", searchVo);
	}

}
