package egovframework.injeinc.foffice.ex.reservationMain.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository(value="ReservationMainUserDao")
public class ReservationMainUserDao extends EgovAbstractMapper {
	
	/** 리스트 */
	public List selectListReservationMainImg() throws Exception{
		return selectList("ReservationMainUserDao.selectListReservationMainImg", "");
	}
	/** 예약리스트 */
	public List selectListReservationMainNew(HashMap<String, String> map) throws Exception {
		return selectList("ReservationMainUserDao.selectListReservationMainNew", map);
	}
	/** 동별메인 강좌/행사안내 리스트 */
	public List selectListDongMain(HashMap<String, String> map) throws Exception {
		return selectList("ReservationMainUserDao.selectListDongMain", map);
	}
	public List<EgovMap> selectTotalReservationPagMapList(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return selectList("ReservationMainUserDao.selectTotalReservationPagMapList", reservationUserInfoVo);
	}
	public int selectTotalReservationPagMapListCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (Integer) selectOne("ReservationMainUserDao.selectTotalReservationPagMapListCnt", reservationUserInfoVo);
	}

}
