package egovframework.injeinc.boffice.main.top.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.main.top.vo.TopImagesVo;

@Repository("TopImagesDao")
public class TopImagesDao extends EgovAbstractMapper {
	

    /** 글 목록을 조회한다.*/
    public List selectListTopImages(TopImagesVo topImagesVO) throws Exception {
        return selectList("TopImagesDao.selectListTopImages", topImagesVO);
    }
    
    /** 총 건수 조회 */
   	public int selectPagTopImages(TopImagesVo topImagesVO) throws Exception {
   		return (Integer)selectOne("TopImagesDao.selectPagTopImages", topImagesVO);
   	}
    
   	/** 등록 */
   	public void insertTopImages(TopImagesVo topImagesVO) throws Exception {
   		insert("TopImagesDao.insertTopImages", topImagesVO);
   	}
   	
   	/** 상세조회 */
   	public TopImagesVo selectTopImages(TopImagesVo topImagesVO) throws Exception {
   		return (TopImagesVo)selectOne("TopImagesDao.selectTopImages", topImagesVO);
   	}
   	
   	/** 파일저장명,원본명,경로 삭제(update)*/
    public void deleteTopImagesFile(HashMap<String, String> param) throws Exception {
    	update("TopImagesDao.deleteTopImagesFile", param);
    }
    
    /** 파일경로 조회*/
    public String selectFilePath(String tiIdx) throws Exception {
    	return (String)selectOne("TopImagesDao.selectFilePath", tiIdx);
    }
    
    /** 수정*/
    public void updateTopImages(TopImagesVo topImagesVO) throws Exception {
    	update("TopImagesDao.updateTopImages", topImagesVO);
    }
    
    /** 삭제*/
    public void deleteTopImages(TopImagesVo topImagesVO) throws Exception {
    	delete("TopImagesDao.deleteTopImages", topImagesVO);
    }
    
    /** 메인화면 이미지 리스트 조회*/
    public List selectListTopImagesMain() throws Exception {
        return selectList("TopImagesDao.selectListTopImagesMain", null);
    }
    
    /** 조회수 증가*/
    public void updateTopImagesViewCnt(String tiIdx) throws Exception {
    	update("TopImagesDao.updateTopImagesViewCnt", tiIdx);
    }
    
    /** 조회수 증가*/
    public void updateHotViewCnt(String tiIdx) throws Exception {
    	update("TopImagesDao.updateTopImagesViewCnt", tiIdx);
    }
    
}