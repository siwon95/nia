package egovframework.injeinc.boffice.ex.reservationFac.dao;

import java.util.List;




import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacTeamVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacTimeVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacVo;

@Repository("ReservationFacDao")
public class ReservationFacDao  extends EgovAbstractMapper {

	public void createReservationFac(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationFacDao.insertReservationFac", reservationIndexVo);
	}

	public void createReservationFacAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception {
		insert("ReservationFacDao.insertReservationFacAddItem", reservationAddItemVo);
	}
	
	public void createReservationFacTime(ReservationFacTimeVo reservationFacTimeVo) throws Exception {
		insert("ReservationFacDao.insertReservationFacTime", reservationFacTimeVo);
	}

	public void updateReservationFac(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationFacDao.updateReservationFac", reservationIndexVo);
	}

	public void deleteReservationFac(ReservationIndexVo reservationIndexVo) throws Exception {
		update("ReservationFacDao.deleteReservationFac", reservationIndexVo);
	}

	public ReservationIndexVo selectReservationFac(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationFacDao.selectReservationFac", reservationIndexVo);
	}
	
	public String selectReservationFacLotUse(ReservationIndexVo reservationIndexVo) throws Exception{
		return (String)selectOne("ReservationFacDao.selectReservationFacLotUse", reservationIndexVo);
	}
	
	public String selectReservationFacAddCnt(ReservationIndexVo reservationIndexVo) throws Exception{
		return (String)selectOne("ReservationFacDao.selectReservationFacAddCnt", reservationIndexVo);
	}
	
	public ReservationIndexVo selectReservationMyFac(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationFacDao.selectReservationMyFac", reservationIndexVo);
	}

	public List<ReservationIndexVo> selectPagReservationFac(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationFacDao.selectPagReservationFac", reservationIndexVo);
	}
	
	public List<ReservationIndexVo> selectPagArtReservationFac(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationFacDao.selectPagArtReservationFac", reservationIndexVo);
	}
	
	public int selectArtReservationFacCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationFacDao.selectArtReservationFacCnt", reservationIndexVo);
	}

	public List<ReservationIndexVo> selectPagReservationMyFac(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationFacDao.selectPagReservationMyFac", reservationIndexVo);
	}

	public List<ReservationAddItemVo> selectListReservationFacAddItem(ReservationIndexVo reservationIndexVo) throws Exception {
		return selectList("ReservationFacDao.selectListReservationFacAddItem", reservationIndexVo);
	}
	
	public ReservationAddItemVo retrieveReservationFacAddItemOne(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationAddItemVo)selectOne("ReservationFacDao.selectListReservationFacAddItem", reservationIndexVo);
	}

	public int selectReservationFacCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationFacDao.selectReservationFacCnt", reservationIndexVo);
	}

	public int selectReservationMyFacCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return (Integer)selectOne("ReservationFacDao.selectReservationMyFacCnt", reservationIndexVo);
	}

	public void deleteReservationFacAddItem(ReservationIndexVo reservationIndexVo) throws Exception {
		delete("ReservationFacDao.deleteReservationFacAddItem", reservationIndexVo);
	}
	
	public void deleteReservationFacTime(ReservationIndexVo reservationIndexVo) throws Exception {
		delete("ReservationFacDao.deleteReservationFacTime", reservationIndexVo);
	}

	public List<ReservationUserInfoVo> selectListReservationFacUserInfo(ReservationFacVo reservationFacVo) throws Exception {
		return selectList("ReservationFacDao.selectListReservationFacUserInfo", reservationFacVo);
	}
	
	public List<ReservationUserInfoVo> selectListReservationFacUserInfoReturnFee(ReservationFacVo reservationFacVo) throws Exception {
		return selectList("ReservationFacDao.selectListReservationFacUserInfoReturnFee", reservationFacVo);
	}

	public List selectListReservationFacCal(ReservationFacVo reservationFacVo) throws Exception {
		return selectList("ReservationFacDao.selectListReservationFacCal3", reservationFacVo);
	}
	
	public List selectListReservationFacCalUser(ReservationFacVo reservationFacVo) throws Exception {
		return selectList("ReservationFacDao.selectListReservationFacCal4", reservationFacVo);
	}

	public void createReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		insert("ReservationFacDao.insertReservationFacUserInfo", reservationUserInfoVo);
	}

	public void createReservationFacUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception {
		insert("ReservationFacDao.insertReservationFacUserAnswer", reservationUserAnswerVo);
	}

	public ReservationUserInfoVo selectReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (ReservationUserInfoVo)selectOne("ReservationFacDao.selectReservationFacUserInfo", reservationUserInfoVo);
	}
	
	public ReservationUserInfoVo selectReservationFacUserInfoReturnFee(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (ReservationUserInfoVo)selectOne("ReservationFacDao.selectReservationFacUserInfoReturnFee", reservationUserInfoVo);
	}

	public void updateReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationFacDao.updateReservationFacUserInfo", reservationUserInfoVo);
	}
	
	public List<ReservationFacTimeVo> selectReservationFacTimeInfo(ReservationIndexVo reservationIndexVo) throws Exception {
		return  selectList("ReservationFacDao.selectReservationTimeInfo", reservationIndexVo);
	}
	
	public List<ReservationFacTimeVo> selectReservationFacUserTimeInfo(ReservationFacVo reservationFacVo) throws Exception {
		return selectList("ReservationFacDao.selectReservationUserTimeInfo", reservationFacVo);
	}
	
	public List<ReservationFacTimeVo> selectReservationFacUserTimeInfo2(ReservationFacVo reservationFacVo) throws Exception {
		return  selectList("ReservationFacDao.selectReservationUserTimeInfo2", reservationFacVo);
	}
	
	public List<ReservationFacTimeVo> selectReservationFacUserTimeInfo3(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return  selectList("ReservationFacDao.selectReservationUserTimeInfo3", reservationUserInfoVo);
	}
	
	public List<ReservationFacTimeVo> selectReservationFacUserTimeInfo4(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return  selectList("ReservationFacDao.selectReservationUserTimeInfo4", reservationUserInfoVo);
	}

	public void updateReservationFacLot(ReservationIndexVo reservationIndexVo) throws Exception {
		insert("ReservationFacDao.updateReservationFacRuLotResult", reservationIndexVo);
	}

	public void deleteReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationFacDao.deleteReservationFacUserInfo", reservationUserInfoVo);
	}
	
	public void updateReservationFacUserConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		update("ReservationFacDao.updateReservationFacUserConfirm", reservationUserInfoVo);
	}

	public int selectReservationFacConfirmCnt(ReservationFacVo reservationFacVo) throws Exception {
		return (Integer)selectOne("ReservationFacDao.selectReservationFacConfirmCnt", reservationFacVo);
	}
	
	public List selectListReservationFacFix(ReservationFacVo reservationFacVo) throws Exception {
		return selectList("ReservationFacDao.selectListReservationFacFix", reservationFacVo);
	}
	
	public List selectReservationFacAnotherFac() throws Exception {
		return selectList("ReservationFacDao.selectReservationFacAnotherFac");
	}

	public int selectReservationFacUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return (Integer)selectOne("ReservationFacDao.selectReservationFacUserInfoDupCnt", reservationUserInfoVo);
	}
	
	/** 시설예약 상세페이지 */
	public ReservationIndexVo selectReservationFacView(ReservationIndexVo reservationIndexVo) throws Exception {
		return (ReservationIndexVo)selectOne("ReservationFacDao.selectReservationFacView", reservationIndexVo);
	}
	
	/** 온라인선착순모집에 관한 접수카운트 */
	public List selectItemCountList(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return selectList("ReservationFacDao.selectItemCountList", reservationUserInfoVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List selectDeptCode() throws Exception {
		return selectList("ReservationFacDao.selectDeptCode","");
	}
	
	public List<EgovMap> selectPagFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception {
		return selectList("ReservationFacDao.selectPagFacTeam", reservationFacTeamVo);
	}
	
	public int selectPagFacTeamCnt(ReservationFacTeamVo reservationFacTeamVo) throws Exception {
		return (Integer)selectOne("ReservationFacDao.selectPagFacTeamCnt", reservationFacTeamVo);
	}
	
	public EgovMap selectFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception {
		return (EgovMap)selectOne("ReservationFacDao.selectFacTeam", reservationFacTeamVo);
	}
	
	public int selectUserckCnt(ReservationFacTeamVo reservationFacTeamVo) throws Exception {
		return (Integer)selectOne("ReservationFacDao.selectUserckCnt", reservationFacTeamVo);
	}
	
	public int selectTeamCkCnt(ReservationFacTeamVo reservationFacTeamVo) throws Exception {
		return (Integer)selectOne("ReservationFacDao.selectTeamCkCnt", reservationFacTeamVo);
	}
	
	public void insertReservationFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception {
		insert("ReservationFacDao.insertReservationFacTeam", reservationFacTeamVo);
	}
	
	public void updateReservationFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception {
		update("ReservationFacDao.updateReservationFacTeam", reservationFacTeamVo);
	}

}
