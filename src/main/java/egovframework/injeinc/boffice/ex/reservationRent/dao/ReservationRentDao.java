package egovframework.injeinc.boffice.ex.reservationRent.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationRent.vo.ReservationRentVo;

@Repository("ReservationRentDao")
public class ReservationRentDao  extends EgovAbstractMapper {

	public void createReservationRent(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationRentDao.insertReservationRent", reservationIndexVo);
	}

	public void createReservationRentAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception {
		insert("ReservationRentDao.insertReservationRentAddItem", reservationAddItemVo);
	}

	public void updateReservationRent(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationRentDao.updateReservationRent", reservationIndexVo);
	}

	public void deleteReservationRent(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationRentDao.deleteReservationRent", reservationIndexVo);
	}

	public ReservationIndexVo selectReservationRent(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationRentDao.selectReservationRent", reservationIndexVo);
	}
	
	public String selectReservationRentLotUse(ReservationIndexVo reservationIndexVo) throws Exception{
		return (String)selectOne("ReservationRentDao.selectReservationRentLotUse", reservationIndexVo);
	}
	
	public ReservationIndexVo selectReservationMyFac(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationRentDao.selectReservationMyFac", reservationIndexVo);
	}

	public List<ReservationIndexVo> selectPagReservationRent(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationRentDao.selectPagReservationRent", reservationIndexVo);
	}

	public List<ReservationIndexVo> selectPagReservationMyFac(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationRentDao.selectPagReservationMyFac", reservationIndexVo);
	}

	public List<ReservationAddItemVo> selectListReservationRentAddItem(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationRentDao.selectListReservationRentAddItem", reservationIndexVo);
	}
	
	public List<ReservationUserInfoVo> selectReservationRentUserList(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return selectList("ReservationRentDao.selectReservationRentUserInfoList", reservationUserInfoVo);
	}
	
	public int selectReservationRentCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationRentDao.selectReservationRentCnt", reservationIndexVo);
	}

	public int selectReservationMyFacCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationRentDao.selectReservationMyFacCnt", reservationIndexVo);
	}

	public void deleteReservationRentAddItem(ReservationIndexVo reservationIndexVo) throws Exception {
		delete("ReservationRentDao.deleteReservationRentAddItem", reservationIndexVo);
	}

	public List<ReservationUserInfoVo> selectListReservationRentUserInfo(ReservationRentVo reservationRentVo) throws Exception {
		return selectList("ReservationRentDao.selectListReservationRentUserInfo", reservationRentVo);
	}
	
	public int selectListReservationRentUserInfoCnt(ReservationRentVo reservationRentVo) throws Exception {
		return (Integer)selectOne("ReservationRentDao.selectListReservationRentUserInfoCnt", reservationRentVo);
	}
	
	public List<ReservationUserInfoVo> selectListReservationRentFUserInfo(ReservationRentVo reservationRentVo) throws Exception {
		return selectList("ReservationRentDao.selectListReservationRentFUserInfo", reservationRentVo);
	}
	
	public int selectListReservationRentFUserInfoCnt(ReservationRentVo reservationRentVo) throws Exception {
		return (Integer)selectOne("ReservationRentDao.selectListReservationRentFUserInfoCnt", reservationRentVo);
	}
	
	public List selectListReservationRentCal(ReservationRentVo reservationRentVo) throws Exception {
		return selectList("ReservationRentDao.selectListReservationRentCal", reservationRentVo);
	}

	public void createReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		insert("ReservationRentDao.insertReservationRentUserInfo", reservationUserInfoVo);
	}

	public void createReservationRentUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception {
		insert("ReservationRentDao.insertReservationRentUserAnswer", reservationUserAnswerVo);
	}

	public ReservationUserInfoVo selectReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (ReservationUserInfoVo)selectOne("ReservationRentDao.selectReservationRentUserInfo", reservationUserInfoVo);
	}

	public void updateReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationRentDao.updateReservationRentUserInfo", reservationUserInfoVo);
	}
	
	public void updateReservationRentUserInfo2(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationRentDao.updateReservationRentUserInfo2", reservationUserInfoVo);
	}

	public void updateReservationRentLot(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationRentDao.updateReservationRentRuLotResult", reservationIndexVo);
	}

	public void deleteReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationRentDao.deleteReservationRentUserInfo", reservationUserInfoVo);
	}

	public int selectReservationRentConfirmCnt(ReservationRentVo reservationRentVo) throws Exception {
		return (Integer)selectOne("ReservationRentDao.selectReservationRentConfirmCnt", reservationRentVo);
	}

	public List selectListReservationRentFix(ReservationRentVo reservationRentVo) throws Exception {
		return selectList("ReservationRentDao.selectListReservationRentFix", reservationRentVo);
	}

	public int selectReservationRentUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (Integer)selectOne("ReservationRentDao.selectReservationRentUserInfoDupCnt", reservationUserInfoVo);
	}
	
	/** 시설예약 상세페이지 */
	public ReservationIndexVo selectReservationRentView(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationRentDao.selectReservationRentView", reservationIndexVo);
	}
	
	/** 온라인선착순모집에 관한 접수카운트 */
	public List selectItemCountList(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return selectList("ReservationRentDao.selectItemCountList", reservationUserInfoVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List selectDeptCode() throws Exception {
		return selectList("ReservationRentDao.selectDeptCode","");
	}
	
	public void updateReservationRentUserConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationRentDao.updateReservationRentUserConfirm", reservationUserInfoVo);
	}

}
