package egovframework.injeinc.boffice.ex.reservationRent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationRent.vo.ReservationRentVo;

public interface ReservationRentSvc {

	/**
     * 예약등록
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void registReservationRent(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약등록(추가항목)
     * @param ReservationAddItemVo
     * @throws Exception
     */
	public void registReservationRentAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception;

	/**
     * 예약수정
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationRent(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationRent(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약상세
     * @param ReservationIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexVo retrieveReservationRent(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/**
     * 추첨방법조회
     * @param ReservationIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public String retrieveReservationRentLotUse(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/**
     * 페이징
     * @param ReservationIndexVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationRent(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목 리스트
     * @param ReservationIndexVo
     * @return List<ReservationAddItemVo>
     * @throws Exception
     */
	public List<ReservationAddItemVo> retrieveListReservationRentAddItem(ReservationIndexVo reservationIndexVo) throws Exception;
	
	
	
	public List<ReservationUserInfoVo> retrieveReservationRentUserList(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	/**
     * 추가항목삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationRentAddItem(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약접수자 리스트
     * @param ReservationIndexVo
     * @return List<ReservationUserInfoVo>
     * @throws Exception
     */
	public Map<String, Object> retrieveListReservationRentUserInfo(ReservationRentVo reservationRentVo) throws Exception;
	
	
	
	public Map<String, Object> retrieveListReservationRentFUserInfo(ReservationRentVo reservationRentVo) throws Exception;


	/**
     * 예약 달력
     * @param ReservationRentVo
     * @return List
     * @throws Exception
     */
	public List retrieveListReservationRentCal(ReservationRentVo reservationRentVo) throws Exception;


	/**
     * 접수자등록
     * @param reservationUserInfoVo
     * @throws Exception
     */
	public void registReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자답변등록
     * @param reservationUserInfoVo
     * @throws Exception
     */
	public void registReservationRentUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception;

	/**
     * 접수자 상세
     * @param ReservationUserInfoVo
     * @return ReservationUserInfoVo
     * @throws Exception
     */
	public ReservationUserInfoVo retrieveReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자수정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void modifyReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	public void modifyReservationRentUserInfo2(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 파일업로드
     * @param ReservationIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
    public ReservationIndexVo uploadFile(Map<String, MultipartFile> files, ReservationIndexVo reservationIndexVo) throws Exception;

    /**
     * 신청자 추첨
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void updateReservationRentLot(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 접수자삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 예약 확정자 수
     * @param ReservationRentVo
     * @throws Exception
     */
	public int retrieveReservationRentConfirmCnt(ReservationRentVo reservationRentVo) throws Exception;

	/**
     * 시설예약 확정내역
     * @param ReservationRentVo
     * @return List
     * @throws Exception
     */
	public List retrieveListReservationRentFix(ReservationRentVo reservationRentVo) throws Exception;

	/**
     * 중복 시설예약 수
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public int retrieveReservationRentUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	/** 시설예약 상세페이지 */
	public ReservationIndexVo retrieveReservationRentView(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/** 온라인선착순 모집에 관한 접수카운트*/
	public List retrieveItemCountList(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception;
	
	public void modReservationRentUserConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
}
