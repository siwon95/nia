package egovframework.injeinc.boffice.hot.manage.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.hot.manage.vo.HotManageVo;

@Repository("HotManageDao")
public class HotManageDao extends EgovAbstractMapper {
	

    /** 글 목록을 조회한다.*/
    public List selectListHotManage(HotManageVo hotManageVO) throws Exception {
        return selectList("HotManageDao.selectListHotManage", hotManageVO);
    }
	
    /** 총 건수 조회 */
   	public int selectPagHotManage(HotManageVo hotManageVO) throws Exception {
   		return (Integer)selectOne("HotManageDao.selectPagHotManage", hotManageVO);
   	}
   	
   	/** 등록 */
   	public void insertHotManage(HotManageVo hotManageVO) throws Exception {
   		insert("HotManageDao.insertHotManage", hotManageVO);
   	}
   	
   	/** 수정 */
   	public void updateHotManage(HotManageVo hotManageVO) throws Exception {
   		update("HotManageDao.updateHotManage", hotManageVO);
   	}
   	
   	/** 정렬 순서 수정(미사용으로 변경할시) */
   	public void updateHlSort(String hlSort) throws Exception {
   		update("HotManageDao.updateHlSort", hlSort);
   	}
   	
   	/** 상세 조회 */
    public HotManageVo selectFile(HotManageVo hotManageVO) throws Exception {
        return (HotManageVo)selectOne("HotManageDao.selectFile", hotManageVO);
    }
    
    /** 최대하위 step 조회 */
   	public HotManageVo selectMaxStep(HotManageVo hotManageVO) throws Exception {
   		return (HotManageVo)selectOne("HotManageDao.selectMaxStep", hotManageVO);
   	}
   	
   	/** 최소상위 step 조회 */
   	public HotManageVo selectMinStep(HotManageVo hotManageVO) throws Exception {
   		return (HotManageVo)selectOne("HotManageDao.selectMinStep", hotManageVO);
   	}
   	
   	/** 순서 수정(화살표 클릭시) */
   	public void updateStep(HotManageVo hotManageVO) throws Exception {
   		update("HotManageDao.updateStep", hotManageVO);
   	}
   	
   	/** 삭제 */
   	public void deleteHotManage(HotManageVo hotManageVO) throws Exception {
   		update("HotManageDao.deleteHotManage", hotManageVO);
   	}
   	
   	/** 메인화면 리스트 조회*/
    public List selectListHotManageMain() throws Exception {
        return selectList("HotManageDao.selectListHotManageMain", null);
    }
   	
}