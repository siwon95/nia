package egovframework.injeinc.boffice.searchtheme.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.searchtheme.vo.SearchthemeVo;

@Repository("SearchthemeDao")
public class SearchthemeDao extends EgovAbstractMapper {
	

    /** 글 목록을 조회한다.*/
    public List selectListSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
        return selectList("SearchthemeDao.selectListSearchtheme", searchthemeVO);
    }
    
    /** 총 건수 조회 */
   	public int selectPagSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
   		return (Integer)selectOne("SearchthemeDao.selectPagSearchtheme", searchthemeVO);
   	}
   	
    /** 그룹 리스트를 조회한다.*/
    public List selectListGroupCd() throws Exception {
        return selectList("SearchthemeDao.selectListGroupCd", null);
    }
    
    /** 삭제*/
    public void deleteSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
        delete("SearchthemeDao.deleteSearchtheme", searchthemeVO);
    }
    
    /** 상세조회*/
    public SearchthemeVo selectSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
        return (SearchthemeVo)selectOne("SearchthemeDao.selectSearchtheme", searchthemeVO);
    }
    
    /** 등록*/
    public void insertSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
        insert("SearchthemeDao.insertSearchtheme", searchthemeVO);
    }
    
    /** 사용여부 체크*/
    public void updateSearchthemeUseYn(HashMap<String, String> param) throws Exception {
        update("SearchthemeDao.updateSearchthemeUseYn", param);
    }
    
    /** 수정*/
    public void updateSearchtheme(SearchthemeVo searchthemeVO) throws Exception {
        update("SearchthemeDao.updateSearchtheme", searchthemeVO);
    }
    
    /** 파일저장명,원본명,경로 삭제(update)*/
    public void deleteSearchthemeFile(HashMap<String, String> param) throws Exception {
    	update("SearchthemeDao.deleteSearchthemeFile", param);
    }
    
    /** 파일경로 조회*/
    public String selectFilePath(String stIdx) throws Exception {
    	return (String)selectOne("SearchthemeDao.selectFilePath", stIdx);
    }
    
}