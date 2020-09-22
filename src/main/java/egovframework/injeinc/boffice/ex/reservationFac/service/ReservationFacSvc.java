package egovframework.injeinc.boffice.ex.reservationFac.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacTeamVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacTimeVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ReservationFacSvc {

	/**
     * 예약등록
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void registReservationFac(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약등록(추가항목)
     * @param ReservationAddItemVo
     * @throws Exception
     */
	public void registReservationFacAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception;
	
	
	public void registReservationFacTime(ReservationFacTimeVo reservationFacTimeVo) throws Exception;
	
	/**
     * 예약수정
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void modifyReservationFac(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약삭제
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void removeReservationFac(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약상세
     * @param ReservationRentIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexVo retrieveReservationFac(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/**
     * 추첨방법조회
     * @param ReservationRentIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public String retrieveReservationFacLotUse(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/**
     * 추가항목 수
     * @param ReservationRentIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public String retrieveReservationFacAddCnt(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/**
     * 페이징
     * @param ReservationRentIndexVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationFac(ReservationIndexVo reservationIndexVo) throws Exception;
	
	
	
	public Map<String, Object> retrievePagArtReservationFac(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목 리스트
     * @param ReservationRentIndexVo
     * @return List<ReservationAddItemVo>
     * @throws Exception
     */
	public List<ReservationAddItemVo> retrieveListReservationFacAddItem(ReservationIndexVo reservationIndexVo) throws Exception;
	
	public ReservationAddItemVo retrievReservationFacAddItemOne(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목삭제
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void removeReservationFacAddItem(ReservationIndexVo reservationIndexVo) throws Exception;
	
	
	public void removeReservationFacTime(ReservationIndexVo reservationIndexVo) throws Exception;
	
	public  List<ReservationFacTimeVo> retrieveReservationFacUserTimeInfo(ReservationFacVo reservationFacVo) throws Exception;
	
	public  List<ReservationFacTimeVo> retrieveReservationFacUserTimeInfo2(ReservationFacVo reservationFacVo) throws Exception;

	/**
     * 예약접수자 리스트
     * @param ReservationRentIndexVo
     * @return List<ReservationUserInfoVo>
     * @throws Exception
     */
	public List<ReservationUserInfoVo> retrieveListReservationFacUserInfo(ReservationFacVo reservationFacVo) throws Exception;
	
	public List<ReservationUserInfoVo> retrieveListReservationFacReturnFee(ReservationFacVo reservationFacVo) throws Exception;

	/**
     * 예약 달력
     * @param ReservationFacVo
     * @return List
     * @throws Exception
     */
	public List retrieveListReservationFacCal(ReservationFacVo reservationFacVo) throws Exception;
	
	
	
	public  List<ReservationFacTimeVo> retrieveReservationFacTimeInfo(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/**
     * 예약 달력 사용자 시설
     * @param ReservationFacVo
     * @return List
     * @throws Exception
     */
	public List retrieveListReservationFacCalUser(ReservationFacVo reservationFacVo) throws Exception;



	/**
     * 접수자등록
     * @param reservationUserInfoVo
     * @throws Exception
     */
	public void registReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	
	public ReservationUserInfoVo retrieveReservationFacUserInfoReturnFee(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자답변등록
     * @param reservationUserInfoVo
     * @throws Exception
     */
	public void registReservationFacUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception;

	/**
     * 접수자 상세
     * @param ReservationUserInfoRentVo
     * @return ReservationUserInfoVo
     * @throws Exception
     */
	public ReservationUserInfoVo retrieveReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자수정
     * @param ReservationUserInfoRentVo
     * @throws Exception
     */
	public void modifyReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 파일업로드
     * @param ReservationRentIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
    public ReservationIndexVo uploadFile(Map<String, MultipartFile> files, ReservationIndexVo reservationIndexVo) throws Exception;

    /**
     * 신청자 추첨
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void updateReservationFacLot(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 접수자삭제
     * @param ReservationUserInfoRentVo
     * @throws Exception
     */
	public void removeReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	
	public void modReservationFacUserConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 예약 확정자 수
     * @param ReservationFacVo
     * @throws Exception
     */
	public int retrieveReservationFacConfirmCnt(ReservationFacVo reservationFacVo) throws Exception;

	/**
     * 시설예약 확정내역
     * @param ReservationFacVo
     * @return List
     * @throws Exception
     */
	public List retrieveListReservationFacFix(ReservationFacVo reservationFacVo) throws Exception;
	
	
	public List retrieveListAnotherFac() throws Exception;

	/**
     * 중복 시설예약 수
     * @param ReservationUserInfoRentVo
     * @throws Exception
     */
	public int retrieveReservationFacUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	/** 시설예약 상세페이지 */
	public ReservationIndexVo retrieveReservationFacView(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/** 온라인선착순 모집에 관한 접수카운트*/
	public List retrieveItemCountList(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception;
	
	public  List<ReservationFacTimeVo> retrieveReservationFacUserTimeInfo3(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	public  List<ReservationFacTimeVo> retrieveReservationFacUserTimeInfo4(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	public Map<String, Object> retrievePagFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception;
	
	public EgovMap retrieveFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception;
	
	public int retrieveUserckCnt(ReservationFacTeamVo reservationFacTeamVo) throws Exception;
	
	public int retrieveTeamCkCnt(ReservationFacTeamVo reservationFacTeamVo) throws Exception;
	
	public void createReservationFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception;
	
	public void modReservationFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception;
}
