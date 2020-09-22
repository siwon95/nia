package egovframework.injeinc.boffice.searchtheme.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.searchtheme.dao.SearchthemeDao;
import egovframework.injeinc.boffice.searchtheme.service.SearchthemeSvc;
import egovframework.injeinc.boffice.searchtheme.vo.SearchthemeVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("SearchthemeSvc")
public class SearchthemeImpl extends EgovAbstractServiceImpl implements
        SearchthemeSvc {
    
    @Resource(name="SearchthemeDao")
    private SearchthemeDao searchthemeDao;
    
    /** ID Generation */
    @Resource(name = "searchthemeIdService")
    private EgovIdGnrService searchthemeIdService;
    
	@Autowired
	private DataSourceTransactionManager transactionManager;

	public List retrieveListSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
		return searchthemeDao.selectListSearchtheme(searchthemeVO);
	}

	public int retrievePagSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
		return searchthemeDao.selectPagSearchtheme(searchthemeVO);
	}
	
	public List retrieveListGroupCd() throws Exception {
		return searchthemeDao.selectListGroupCd();
	}
	
	public void registSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
		if(searchthemeVO != null){
			searchthemeVO.setStIdx(searchthemeIdService.getNextStringId());
		}
		searchthemeDao.insertSearchtheme(searchthemeVO);
	}

	public void removeSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
		searchthemeDao.deleteSearchtheme(searchthemeVO);
	}

	public SearchthemeVo retrieveSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
		return searchthemeDao.selectSearchtheme(searchthemeVO);
	}

	public void modifySearchthemeUseYn(HashMap<String, String> param) throws Exception {
		searchthemeDao.updateSearchthemeUseYn(param);
	}

	public void modifySearchtheme(SearchthemeVo searchthemeVO) throws Exception {
		searchthemeDao.updateSearchtheme(searchthemeVO);
	}

	public void removeSearchthemeFile(HashMap<String, String> param)
			throws Exception {
		searchthemeDao.deleteSearchthemeFile(param);
	}

	public String retrieveFilePath(String stIdx) throws Exception {
		return searchthemeDao.selectFilePath(stIdx);
	}

}