package egovframework.injeinc.boffice.ex.reservationEtc.dao;

import java.util.HashMap;
import java.util.List;







import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserFileVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;

@Repository("ReservationEtcDao")
public class ReservationEtcDao  extends EgovAbstractMapper {

	public void createReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationEtcDao.insertReservationEtc", reservationIndexVo);
	}

	public void updateReservationEtcReadCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationEtcDao.updateReservationEtcReadCnt", reservationIndexVo);
	}

	public void createReservationEtcAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception {
		insert("ReservationEtcDao.insertReservationEtcAddItem", reservationAddItemVo);
	}

	public void createReservationEtcItem(ReservationItemVo reservationItemVo) throws Exception {
		insert("ReservationEtcDao.insertReservationEtcItem", reservationItemVo);
	}

	public void updateReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationEtcDao.updateReservationEtc", reservationIndexVo);
	}

	public void deleteReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationEtcDao.deleteReservationEtc", reservationIndexVo);
	}

	public ReservationIndexVo selectReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationEtcDao.selectReservationEtc", reservationIndexVo);
	}
	public ReservationIndexVo selectReservationMyEtc(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationEtcDao.selectReservationMyEtc", reservationIndexVo);
	}

	public List<ReservationIndexVo> selectPagReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEtcDao.selectPagReservationEtc", reservationIndexVo);
	}

	public List<ReservationIndexVo> selectPagReservationMyEtc(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEtcDao.selectPagReservationMyEtc", reservationIndexVo);
	}

	public List<ReservationAddItemVo> selectListReservationEtcAddItem(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEtcDao.selectListReservationEtcAddItem", reservationIndexVo);
	}

	public List<ReservationItemVo> selectListReservationEtcItem(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEtcDao.selectListReservationEtcItem", reservationIndexVo);
	}

	public int selectReservationEtcCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationEtcDao.selectReservationEtcCnt", reservationIndexVo);
	}

	public int selectReservationMyEtcCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationEtcDao.selectReservationMyEtcCnt", reservationIndexVo);
	}

	public void deleteReservationEtcAddItem(ReservationIndexVo reservationIndexVo) throws Exception {
		delete("ReservationEtcDao.deleteReservationEtcAddItem", reservationIndexVo);
	}

	public void deleteReservationEtcItem(ReservationIndexVo reservationIndexVo) throws Exception {
		delete("ReservationEtcDao.deleteReservationEtcItem", reservationIndexVo);
	}

	public List<ReservationUserInfoVo> selectListReservationEtcUserInfo(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEtcDao.selectListReservationEtcUserInfo", reservationIndexVo);
	}

	public void createReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		insert("ReservationEtcDao.insertReservationEtcUserInfo", reservationUserInfoVo);
	}

	public void createReservationEtcUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception {
		insert("ReservationEtcDao.insertReservationEtcUserAnswer", reservationUserAnswerVo);
	}

	public ReservationUserInfoVo selectReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (ReservationUserInfoVo)selectOne("ReservationEtcDao.selectReservationEtcUserInfo", reservationUserInfoVo);
	}

	public int selectReservationEtcUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (Integer)selectOne("ReservationEtcDao.selectReservationEtcUserInfoDupCnt", reservationUserInfoVo);
	}

	public int selectReservationEtcRedundancyDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (Integer)selectOne("ReservationEtcDao.selectReservationEtcRedundancyDupCnt", reservationUserInfoVo);
	}

	public void updateReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationEtcDao.updateReservationEtcUserInfo", reservationUserInfoVo);
	}

	public void deleteReservationEtcUserAnswer(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		delete("ReservationEtcDao.deleteReservationEtcUserAnswer", reservationUserInfoVo);
	}

	public void updateReservationEtcLot(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationEtcDao.updateReservationEtcRuLotResult", reservationIndexVo);
	}

	public void updateReservationEtcLotTemp(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationEtcDao.updateReservationEtcRuLotResultTemp", reservationIndexVo);
	}

	public void deleteReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationEtcDao.deleteReservationEtcUserInfo", reservationUserInfoVo);
	}

	public void updateReservationEtcUserInfoConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationEtcDao.updateReservationEtcUserInfoConfirm", reservationUserInfoVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List selectDeptCode() throws Exception {
		return selectList("ReservationEtcDao.selectDeptCode","");
	}

	@SuppressWarnings("unchecked")
	public List<String> selectListReservationEtcRandom(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEtcDao.selectListReservationEtcRandom", reservationIndexVo);
	}

	@SuppressWarnings("unchecked")
	public List<String> selectListReservationEtcRandomTemp(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEtcDao.selectListReservationEtcRandomTemp", reservationIndexVo);
	}

	public int selectReservationEtcCntForLot(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (Integer)selectOne("ReservationEtcDao.selectReservationEtcCntForLot", reservationUserInfoVo);
	}

	public void insertReservationEtcUserFile(ReservationUserFileVo reservationUserFileVo) throws Exception {
		insert("ReservationEtcDao.insertReservationEtcUserFile", reservationUserFileVo);
	}

	public List selectListSuervisionOrg(boolean isAdmin, List<String> mgrSiteCdList) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isAdmin", isAdmin);
		paramMap.put("mgrSiteCdList", mgrSiteCdList);
		return selectList("ReservationEtcDao.selectListSuervisionOrg", paramMap);
	}
	
	public List selectListSuervisionOrgF() throws Exception {		
		return selectList("ReservationEtcDao.selectListSuervisionOrgF", null);
	}

}
