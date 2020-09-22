package egovframework.injeinc.boffice.ex.reservation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("ReservationEventDao")
public class ReservationEventDao  extends EgovAbstractMapper {

	public void createReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationEventDao.insertReservationEvent", reservationIndexVo);
	}

	public void updateReservationEventReadCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationEventDao.updateReservationEventReadCnt", reservationIndexVo);
	}

	public void createReservationEventAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception {
		insert("ReservationEventDao.insertReservationEventAddItem", reservationAddItemVo);
	}
	
	public void createReservationEventAddItem2(ReservationAddItemVo reservationAddItemVo) throws Exception {
		insert("ReservationEventDao.insertReservationEventAddItem2", reservationAddItemVo);
	}
		
	public void createReservationEventItem(ReservationItemVo reservationItemVo) throws Exception {
		insert("ReservationEventDao.insertReservationEventItem", reservationItemVo);
	}

	public void updateReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationEventDao.updateReservationEvent", reservationIndexVo);
	}

	public void deleteReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationEventDao.deleteReservationEvent", reservationIndexVo);
	}

	public ReservationIndexVo selectReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationEventDao.selectReservationEvent", reservationIndexVo);
	}
	
	public ReservationIndexVo selectReservationArt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationEventDao.selectReservationArt", reservationIndexVo);
	}
	
	public ReservationIndexVo selectReservationMyEvent(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationEventDao.selectReservationMyEvent", reservationIndexVo);
	}

	public List<ReservationIndexVo> selectPagReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectPagReservationEvent", reservationIndexVo);
	}
	
	public List<EgovMap> selectArtCalInfo(ReservationFacVo reservationFacVo) throws Exception {
		return selectList("ReservationEventDao.selectArtCalInfo", reservationFacVo);
	}
	
	public EgovMap selectPgInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (EgovMap)selectOne("ReservationEventDao.selectPgInfo", reservationUserInfoVo);
	}
	
	public List<EgovMap> selectArtCalInfoText(ReservationFacVo reservationFacVo) throws Exception {
		return selectList("ReservationEventDao.selectArtCalInfoText", reservationFacVo);
	}
	
	public List<ReservationIndexVo> selectPagReservationEventLib(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectPagReservationEventLib", reservationIndexVo);
	}
	
	public List<ReservationIndexVo> selectPagArtMovieList(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectPagReservationMovie", reservationIndexVo);
	}
	
	public List<ReservationIndexVo> selectPagArtAllList() throws Exception {
		return selectList("ReservationEventDao.selectPagArtAll");
	}
	
	public List<ReservationIndexVo> selectPagArtAllList2() throws Exception {
		return selectList("ReservationEventDao.selectPagArtAll2");
	}
	
	public List<ReservationIndexVo> selectArtReservationAll() throws Exception {
		return selectList("ReservationEventDao.selectArtReservationAll");
	}
	
	public List<ReservationIndexVo> selectPagArtReservationAll(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectPagArtReservationAll",reservationIndexVo);
	}
	
	public int selectPagArtReservationAllCnt() throws Exception {
		return (Integer)selectOne("ReservationEventDao.selectPagArtReservationAllCnt");
	}
	
	public int selectArtMovieListCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationEventDao.selectPagReservationMovieCnt", reservationIndexVo);
	}
	
	public int selectReservationEventCntLib(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationEventDao.selectReservationEventCntLib", reservationIndexVo);
	}
	
	public List<ReservationIndexVo> selectPagReservationMyEvent(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectPagReservationMyEvent", reservationIndexVo);
	}

	public List<ReservationAddItemVo> selectListReservationEventAddItem(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectListReservationEventAddItem", reservationIndexVo);
	}
	
	public List<ReservationAddItemVo> selectListReservationEventAddItem2(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectListReservationEventAddItem2", reservationIndexVo);
	}

	public List<ReservationItemVo> selectListReservationEventItem(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectListReservationEventItem", reservationIndexVo);
	}
	
	public List<ReservationIndexVo> selectListSuervisionOrg() throws Exception {
		return selectList("ReservationEventDao.selectListSuervisionOrg");
	}

	public int selectReservationEventCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationEventDao.selectReservationEventCnt", reservationIndexVo);
	}

	public int selectReservationMyEventCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationEventDao.selectReservationMyEventCnt", reservationIndexVo);
	}

	public void deleteReservationEventAddItem(ReservationIndexVo reservationIndexVo) throws Exception {
		delete("ReservationEventDao.deleteReservationEventAddItem", reservationIndexVo);
	}
	
	public void deleteReservationEventAddItem2(ReservationIndexVo reservationIndexVo) throws Exception {
		delete("ReservationEventDao.deleteReservationEventAddItem2", reservationIndexVo);
	}
	
	
	public void deleteReservationEventItem(ReservationIndexVo reservationIndexVo) throws Exception {
		delete("ReservationEventDao.deleteReservationEventItem", reservationIndexVo);
	}

	public List<ReservationUserInfoVo> selectListReservationEventUserInfo(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectListReservationEventUserInfo", reservationIndexVo);
	}
	
	public List<ReservationUserInfoVo> selectListReservationEventUserInfoReturnFee(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectListReservationEventUserInfoReturnFee", reservationIndexVo);
	}

	public void createReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		insert("ReservationEventDao.insertReservationEventUserInfo", reservationUserInfoVo);
	}

	public void createReservationEventUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception {
		insert("ReservationEventDao.insertReservationEventUserAnswer", reservationUserAnswerVo);
	}

	public ReservationUserInfoVo selectReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (ReservationUserInfoVo)selectOne("ReservationEventDao.selectReservationEventUserInfo", reservationUserInfoVo);
	}
	
	public ReservationUserInfoVo selectReservationEventUserInfoReturnFee(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (ReservationUserInfoVo)selectOne("ReservationEventDao.selectReservationEventUserInfoReturnFee", reservationUserInfoVo);
	}

	public int selectReservationEventUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (Integer)selectOne("ReservationEventDao.selectReservationEventUserInfoDupCnt", reservationUserInfoVo);
	}

	public int selectReservationEventRedundancyDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (Integer)selectOne("ReservationEventDao.selectReservationEventRedundancyDupCnt", reservationUserInfoVo);
	}

	public void updateReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationEventDao.updateReservationEventUserInfo", reservationUserInfoVo);
	}
	
	public boolean updateReservationEventPayInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		boolean returnbl = false;
		int resultInt = (Integer)update("ReservationEventDao.updateReservationPayInfo", reservationUserInfoVo);
		if(resultInt > 0){
			returnbl = true;		
		}
		return returnbl;
	}
	
	public String selectReservationEventUserPayStatus(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (String)selectOne("ReservationEventDao.selectReservationEventUserPayStatus", reservationUserInfoVo);
	}
	
	public void deleteReservationEventUserAnswer(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		delete("ReservationEventDao.deleteReservationEventUserAnswer", reservationUserInfoVo);
	}

	public void updateReservationEventLot(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationEventDao.updateReservationEventRuLotResult", reservationIndexVo);
	}

	public void updateReservationEventLotTemp(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationEventDao.updateReservationEventRuLotResultTemp", reservationIndexVo);
	}

	public void deleteReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationEventDao.deleteReservationEventUserInfo", reservationUserInfoVo);
	}

	public void updateReservationEventUserInfoConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationEventDao.updateReservationEventUserInfoConfirm", reservationUserInfoVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List selectDeptCode() throws Exception {
		return selectList("ReservationEventDao.selectDeptCode","");
	}

	@SuppressWarnings("unchecked")
	public List<String> selectListReservationEventRandom(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectListReservationEventRandom", reservationIndexVo);
	}

	@SuppressWarnings("unchecked")
	public List<String> selectListReservationEventRandomTemp(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationEventDao.selectListReservationEventRandomTemp", reservationIndexVo);
	}

	public int selectReservationEventCntForLot(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (Integer)selectOne("ReservationEventDao.selectReservationEventCntForLot", reservationUserInfoVo);
	}
}
