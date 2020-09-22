package egovframework.injeinc.boffice.ex.reservationMain.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.reservationMain.dao.ReservationMainDao;
import egovframework.injeinc.boffice.ex.reservationMain.service.ReservationMainSvc;
import egovframework.injeinc.boffice.ex.reservationMain.vo.ReservationMainVo;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service(value="ReservationMainSvc")
public class reservationMainImpl implements ReservationMainSvc {
	
	@Autowired
	private ReservationMainDao reservationMainDao;
	
	@Resource(name = "reservationMainIdGnrService")
	private EgovIdGnrService reservationMainIdGnrService;
	
	/** 리스트 */
	public List retrieveListReservationMainImg(ReservationMainVo vo) throws Exception {
		return reservationMainDao.selectListReservationMainImg(vo);
	}
	/** 리스트 총 갯수 */
	public int retrievePagReservationMainImg(ReservationMainVo vo) throws Exception {
		return reservationMainDao.selectPagReservationMainImg(vo);
	}
	/** 상세조회 */
	public ReservationMainVo retrieveReservationMainImg(ReservationMainVo vo) throws Exception {
		return reservationMainDao.selectReservationMainImg(vo);
	}
	/** 등록 */
	public String registReservationMainImg(ReservationMainVo vo) throws Exception {
		if(vo != null){
			vo.setRmiIdx(reservationMainIdGnrService.getNextStringId());
		}
		reservationMainDao.insertReservationMainImg(vo);
		String rmiIdx = "";
		if(vo != null){
			rmiIdx = vo.getRmiIdx();
		}
		return rmiIdx;
	}
	/** 수정 */
	public void modifyReservationMainImg(ReservationMainVo vo) throws Exception {
		reservationMainDao.updateReservationMainImg(vo);
	}
	/** 삭제 */
	public void deleteReservationMainImg(ReservationMainVo vo) throws Exception {
		reservationMainDao.deleteReservationMainImg(vo);
	}
	
	public List<EgovMap> selectReservationList(HashMap<String, Object> paramMap) throws Exception {
		return reservationMainDao.selectReservationList(paramMap);
	}

}
