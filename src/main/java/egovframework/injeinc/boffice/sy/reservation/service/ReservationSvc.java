package egovframework.injeinc.boffice.sy.reservation.service;

import java.util.HashMap;

public interface ReservationSvc {
	/** 트리  */
	public HashMap<String, Object> retriveListReservation(HashMap<String, String> param) throws Exception;
	
	public HashMap<String, Object> registTreeReservation(HashMap<String, Object> paramObject) throws Exception;
	
	/** 코드중복체크 */
	public HashMap<String, Object> retriveReservationAx(HashMap<String, String> param) throws Exception;
}