package egovframework.injeinc.boffice.ex.reservationMain.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.injeinc.boffice.ex.reservationMain.vo.ReservationMainVo;

@Repository(value="ReservationMainDao")
public class ReservationMainDao extends EgovAbstractMapper {
	
	/** 리스트 */
	public List selectListReservationMainImg(ReservationMainVo vo) throws Exception{
		return selectList("ReservationMainDao.selectListReservationMainImg", vo);
	}
	/** 리스트 총 갯수 */
	public int selectPagReservationMainImg(ReservationMainVo vo) throws Exception {
		return (Integer)selectOne("ReservationMainDao.selectPagReservationMainImg", vo);
	}
	/** 상세조회 */
	public ReservationMainVo selectReservationMainImg(ReservationMainVo vo) throws Exception {
		return (ReservationMainVo)selectOne("ReservationMainDao.selectReservationMainImg", vo);
	}
	/** 등록 */
	public void insertReservationMainImg(ReservationMainVo vo) throws Exception {
		insert("ReservationMainDao.insertReservationMainImg", vo);
	}
	/** 수정 */
	public void updateReservationMainImg(ReservationMainVo vo) throws Exception {
		update("ReservationMainDao.updateReservationMainImg", vo);
	}
	/** 삭제 */
	public void deleteReservationMainImg(ReservationMainVo vo) throws Exception {
		update("ReservationMainDao.deleteReservationMainImg", vo);
	}
	public List<EgovMap> selectReservationList(HashMap<String, Object> paramMap) throws Exception {
		return selectList("ReservationMainDao.selectReservationList", paramMap);
	}

}
