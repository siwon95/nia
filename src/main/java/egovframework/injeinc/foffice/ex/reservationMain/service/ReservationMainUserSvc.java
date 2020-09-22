package egovframework.injeinc.foffice.ex.reservationMain.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ReservationMainUserSvc {
	
	/** 통합예약 서브메인 이미지리스트 */
	public List retrieveListReservationMainImg() throws Exception;
	
	/** 통합예약 서브메인 예약목록 */
	public List retrieveListReservationMainNew(HashMap<String, String> map) throws Exception;
	
	/** 동별메인 강좌/행사안내 리스트 */
	public List retrieveListDongMain(HashMap<String, String> map) throws Exception;
	
	/** 마이페이지*/
	public List<EgovMap> selectTotalReservationPagMapList(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	public int selectTotalReservationPagMapListCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

}
