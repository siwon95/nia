package egovframework.injeinc.boffice.sy.reservation.dao;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * DeptDao
 * 2015.06.05 : LDY
 */


@Repository("ReservationDao")
public class ReservationDao extends EgovAbstractMapper {
	
	/** 트리 */
	@SuppressWarnings("unchecked")
	public List<Object> selectReservation(HashMap<String, String> param) throws Exception {
		return (List<Object>) selectList("ReservationDao.selectReservation", param);
	}

	/**
	 * 입력
	 * 
	 * @param paramObject
	 * @return
	 */
	public boolean createReservation(HashMap<String, Object> paramObject) throws Exception{
		Integer nResult = 0;
		nResult = update("ReservationDao.insertReservation", paramObject);
		return nResult > 0;
	}
	
	/** 삭제 */
	public void deleteReservation(HashMap<String, Object> paramObject) throws Exception {
		delete("ReservationDao.deleteReservation", paramObject);
	}
	
	/** 코드중복체크 */
	@SuppressWarnings("unchecked")
	public List<Object> selectReservationAx(HashMap<String, String> param) throws Exception {
		return (List<Object>) selectList("ReservationDao.selectReservationAx", param);
	}
}