package egovframework.injeinc.boffice.ex.reservationHealth.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationAddHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationAddNodateVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationHealthDayVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationHealthUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationIndexHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationItemHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationUserInfoHealthVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("ReservationHealthDao")
public class ReservationHealthDao  extends EgovAbstractMapper {
	
	//예약등록
	public void createReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		insert("ReservationHealthDao.insertReservationHealth", reservationIndexVo);
	}
	
	//조회수증가
	public void updateReservationHealthReadCnt(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		update("ReservationHealthDao.updateReservationHealthReadCnt", reservationIndexVo);
	}
	
	//운영불가(휴무)일등록
	public void createReservationAddNodate(ReservationAddNodateVo reservationAddNodateVo) throws Exception {
		insert("ReservationHealthDao.insertReservationAddNodate", reservationAddNodateVo);
	}
	
	//요일별 회차등록
	public void createReservationAddWeek(ReservationAddHealthVo reservationAddHealthVo) throws Exception {
		insert("ReservationHealthDao.insertReservationAddWeek", reservationAddHealthVo);
	}
	
	//운영불가(휴무)일 전체삭제
	public void deleteReservationHealtNodate(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception {
		delete("ReservationHealthDao.deleteReservationHealthNodate", reservationIndexHealthVo);
	}
	
	//요일별 회차 삭제
	public void deleteReservationHealthAddWeek(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception {
		delete("ReservationHealthDao.deleteReservationHealthAddWeek", reservationIndexHealthVo);
	}
	
	//예약수정
	public void updateReservationHealth(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception {
		update("ReservationHealthDao.updateReservationHealth", reservationIndexHealthVo);
	}
	
	//예약등록(추가항목2) 입력
	public void createReservationHealthItem(ReservationItemHealthVo reservationItemHealthVo) throws Exception {
		insert("ReservationHealthDao.insertReservationHealthItem", reservationItemHealthVo);
	}
	
	//요일별 회차리스트
	public List<ReservationAddHealthVo> selectListReservationHealthWeek(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception {
		return selectList("ReservationHealthDao.selectListReservationHealthWeek", reservationIndexHealthVo);
	}
	
	//운영불가(휴무)일 리스트
	public List<ReservationAddHealthVo> selectListReservationHealthNodate(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception {
		return selectList("ReservationHealthDao.selectListReservationHealthNodate", reservationIndexHealthVo);
	}
	
	//예약등록(추가항목)2 릭스트
	public List<ReservationItemHealthVo> selectListReservationHealthItem(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception {
		return selectList("ReservationHealthDao.selectListReservationHealthItem", reservationIndexHealthVo);
	}
	
	//예약등록(추가항목)2 삭제
	public void deleteReservationHealthItem(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception {
		delete("ReservationHealthDao.deleteReservationHealthItem", reservationIndexHealthVo);
	}
	
	//예약 상세
	public ReservationIndexHealthVo selectReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		return (ReservationIndexHealthVo)selectOne("ReservationHealthDao.selectReservationHealth", reservationIndexVo);
	}
	
	//예약 달력
	public List selectListReservationHealthCal(ReservationHealthDayVo reservationHealthDayVo) throws Exception {
		return selectList("ReservationHealthDao.selectListReservationHealthCal", reservationHealthDayVo);
	}
	
	//신청가능일자 구분
	public List selectListReservationHealthDate(ReservationHealthDayVo reservationHealthDayVo) throws Exception {
		return selectList("ReservationHealthDao.selectListReservationHealthDate", reservationHealthDayVo);
	}

	//예약일자별 진료예약자 리스트
	@SuppressWarnings("unchecked")
	public List<ReservationUserInfoHealthVo> selectReservationUserHealthList(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		return (List<ReservationUserInfoHealthVo>)list("ReservationHealthDao.selectReservationUserHealthList", reservationUserInfoHealthVo);
	}
	//예약일자별 진료예약자 카운트
	public int selectReservationUserHealthCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		return (Integer)selectOne("ReservationHealthDao.selectReservationUserHealthCnt", reservationUserInfoHealthVo);
	}

	
	//요일별 회차 리스트
	@SuppressWarnings("unchecked")
	public List<Object> selectWeekHealthListAx(HashMap<String, String> param) throws Exception {
		return (List<Object>) selectList("ReservationHealthDao.selectWeekHealthListAx", param);
	}
	
	//요일별 회차 리스트
	@SuppressWarnings("unchecked")
	public List<Object> selectUserHealthCntAx(HashMap<String, String> param) throws Exception {
		return (List<Object>) selectList("ReservationHealthDao.selectUserHealthCntAx", param);
	}
	
	//접수자 답변등록
	public void createReservationHealthUserAnswer(ReservationHealthUserAnswerVo reservationHealthUserAnswerVo) throws Exception {
		insert("ReservationHealthDao.insertReservationHealthUserAnswer", reservationHealthUserAnswerVo);
	}
	
	//예약 상세
	public int selectReservationHealthUserCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		return (Integer) selectOne("ReservationHealthDao.retrieveReservationHealthUserCnt", reservationUserInfoHealthVo);
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	

	public void deleteReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		update("ReservationHealthDao.deleteReservationHealth", reservationIndexVo);
	}

	
	public ReservationIndexHealthVo selectReservationMyHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		return (ReservationIndexHealthVo)selectOne("ReservationHealthDao.selectReservationMyHealth", reservationIndexVo);
	}

	public List<ReservationIndexHealthVo> selectPagReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		return (List<ReservationIndexHealthVo>)list("ReservationHealthDao.selectPagReservationHealth", reservationIndexVo);
	}

	public List<ReservationIndexHealthVo> selectPagReservationMyHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		return (List<ReservationIndexHealthVo>)list("ReservationHealthDao.selectPagReservationMyHealth", reservationIndexVo);
	}

	

	

	public int selectReservationHealthCnt(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationHealthDao.selectReservationHealthCnt", reservationIndexVo);
	}

	public int selectReservationMyHealthCnt(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationHealthDao.selectReservationMyHealthCnt", reservationIndexVo);
	}

	public void deleteReservationHealthAddItem(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		delete("ReservationHealthDao.deleteReservationHealthAddItem", reservationIndexVo);
	}

	

	public List<ReservationUserInfoHealthVo> selectListReservationHealthUserInfo(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		return (List<ReservationUserInfoHealthVo>)list("ReservationHealthDao.selectListReservationHealthUserInfo", reservationIndexVo);
	}

	public void createReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		insert("ReservationHealthDao.insertReservationHealthUserInfo", reservationUserInfoHealthVo);
	}

	

	public ReservationUserInfoHealthVo selectReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		return (ReservationUserInfoHealthVo)selectOne("ReservationHealthDao.selectReservationHealthUserInfo", reservationUserInfoHealthVo);
	}

	public int selectReservationHealthUserInfoDupCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		return (Integer)selectOne("ReservationHealthDao.selectReservationHealthUserInfoDupCnt", reservationUserInfoHealthVo);
	}

	public int selectReservationHealthRedundancyDupCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		return (Integer)selectOne("ReservationHealthDao.selectReservationHealthRedundancyDupCnt", reservationUserInfoHealthVo);
	}

	public void updateReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		update("ReservationHealthDao.updateReservationHealthUserInfo", reservationUserInfoHealthVo);
	}

	public void deleteReservationHealthUserAnswer(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		delete("ReservationHealthDao.deleteReservationHealthUserAnswer", reservationUserInfoHealthVo);
	}

	public void updateReservationHealthLot(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		insert("ReservationHealthDao.updateReservationHealthRuLotResult", reservationIndexVo);
	}

	public void updateReservationHealthLotTemp(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		insert("ReservationHealthDao.updateReservationHealthRuLotResultTemp", reservationIndexVo);
	}

	public void deleteReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		update("ReservationHealthDao.deleteReservationHealthUserInfo", reservationUserInfoHealthVo);
	}

	public void updateReservationHealthUserInfoConfirm(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		update("ReservationHealthDao.updateReservationHealthUserInfoConfirm", reservationUserInfoHealthVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List selectDeptCode() throws Exception {
		return selectList("ReservationHealthDao.selectDeptCode","");
	}

	@SuppressWarnings("unchecked")
	public List<String> selectListReservationHealthRandom(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		return (List<String>)list("ReservationHealthDao.selectListReservationHealthRandom", reservationIndexVo);
	}

	@SuppressWarnings("unchecked")
	public List<String> selectListReservationHealthRandomTemp(ReservationIndexHealthVo reservationIndexVo) throws Exception {
		return (List<String>)list("ReservationHealthDao.selectListReservationHealthRandomTemp", reservationIndexVo);
	}

	public int selectReservationHealthCntForLot(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception {
		return (Integer)selectOne("ReservationHealthDao.selectReservationHealthCntForLot", reservationUserInfoHealthVo);
	}
}
