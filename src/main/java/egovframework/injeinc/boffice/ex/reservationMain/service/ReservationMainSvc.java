package egovframework.injeinc.boffice.ex.reservationMain.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.ex.reservationMain.vo.ReservationMainVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ReservationMainSvc {
	
	/** 리스트 */
	public List retrieveListReservationMainImg(ReservationMainVo vo) throws Exception;
	/** 리스트 총 갯수 */
	public int retrievePagReservationMainImg(ReservationMainVo vo) throws Exception;
	/** 상세조회 */
	public ReservationMainVo retrieveReservationMainImg(ReservationMainVo vo) throws Exception;
	/** 등록 */
	public String registReservationMainImg(ReservationMainVo vo) throws Exception;
	/** 수정 */
	public void modifyReservationMainImg(ReservationMainVo vo) throws Exception;
	/** 삭제 */
	public void deleteReservationMainImg(ReservationMainVo vo) throws Exception;
	
	/** 대표홈페이지 통합예약 목록 */
	public List<EgovMap> selectReservationList(HashMap<String, Object> paramMap) throws Exception;

}
