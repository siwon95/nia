package egovframework.injeinc.foffice.ex.reservationMain.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.foffice.ex.reservationMain.dao.ReservationMainUserDao;
import egovframework.injeinc.foffice.ex.reservationMain.service.ReservationMainUserSvc;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service(value="ReservationMainUserSvc")
public class ReservationMainUserImpl implements ReservationMainUserSvc{
	
	@Resource(name = "ReservationMainUserDao")
	private ReservationMainUserDao reservationMainUserDao;
	
	/** 통합예약 서브메인 이미지리스트 */
	public List retrieveListReservationMainImg() throws Exception {
		return reservationMainUserDao.selectListReservationMainImg();
	}
	
	/** 통합예약 서브메인 리스트*/
	public List retrieveListReservationMainNew(HashMap<String, String> map) throws Exception {
		return reservationMainUserDao.selectListReservationMainNew(map);
	}
	
	/** 동별메인 강좌/행사안내 리스트 */
	public List retrieveListDongMain(HashMap<String, String> map) throws Exception {
		return reservationMainUserDao.selectListDongMain(map);
	}

	
	public List<EgovMap> selectTotalReservationPagMapList(ReservationUserInfoVo reservationUserInfoVo)throws Exception {
		return reservationMainUserDao.selectTotalReservationPagMapList(reservationUserInfoVo);
	}

	
	public int selectTotalReservationPagMapListCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return reservationMainUserDao.selectTotalReservationPagMapListCnt(reservationUserInfoVo);
	}

}
