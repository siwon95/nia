package egovframework.injeinc.boffice.ex.reservation.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ReservationEventSvc {

	/**
     * 예약등록
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void registReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 조회수 증가
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationEventReadCnt(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약등록(추가항목1)
     * @param ReservationAddItemVo
     * @throws Exception
     */
	public void registReservationEventAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception;
	
	
	public void registReservationEventAddItem2(ReservationAddItemVo reservationAddItemVo) throws Exception;

	/**
     * 예약등록(추가항목2)
     * @param ReservationItemVo
     * @throws Exception
     */
	public void registReservationEventItem(ReservationItemVo reservationItemVo) throws Exception;

	/**
     * 예약수정
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약상세
     * @param ReservationIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexVo retrieveReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception;
	
	
	public ReservationIndexVo retrieveReservationArt(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/**
     * 페이징
     * @param ReservationIndexVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception;
	
	public Map<String, Object> retrievePagReservationEventLib(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목 리스트
     * @param ReservationIndexVo
     * @return List<ReservationAddItemVo>
     * @throws Exception
     */
	public List<ReservationAddItemVo> retrieveListReservationEventAddItem(ReservationIndexVo reservationIndexVo) throws Exception;
	
	
	public List<ReservationAddItemVo> retrieveListReservationEventAddItem2(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목 리스트
     * @param ReservationIndexVo
     * @return List<ReservationItemVo>
     * @throws Exception
     */
	public List<ReservationItemVo> retrieveListReservationEventItem(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEventAddItem(ReservationIndexVo reservationIndexVo) throws Exception;
	
	
	public void removeReservationEventAddItem2(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목삭제2
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEventItem(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약접수자 리스트
     * @param ReservationIndexVo
     * @return List<ReservationUserInfoVo>
     * @throws Exception
     */
	public List<ReservationUserInfoVo> retrieveListReservationEventUserInfo(ReservationIndexVo reservationIndexVo) throws Exception;
	
	
	
	public List<ReservationUserInfoVo> retrieveListReservationEventUserInfoReturnFee(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 접수자등록
     * @param reservationUserInfoVo
     * @throws Exception
     */
	public void registReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자답변등록
     * @param reservationUserInfoVo
     * @throws Exception
     */
	public void registReservationEventUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception;

	/**
     * 접수자 상세
     * @param ReservationUserInfoVo
     * @return ReservationUserInfoVo
     * @throws Exception
     */
	public ReservationUserInfoVo retrieveReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	
	public ReservationUserInfoVo retrieveReservationEventUserInfoReturnFee(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	
	
	public ReservationUserInfoVo retrievePayResult(ReservationUserInfoVo reservationUserInfoVo,HttpServletRequest request) throws Exception;
	
	public boolean modReservationEventReturn(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 중복접수여부
     * @param ReservationUserInfoVo
     * @return int
     * @throws Exception
     */
	public int retrieveReservationEventUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 중복접수여부
     * @param ReservationUserInfoVo
     * @return int
     * @throws Exception
     */
	public int retrieveReservationEventRedundancyDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception;


	/**
     * 접수자수정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void modifyReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자답변삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationEventUserAnswer(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

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
	public void updateReservationEventLot(ReservationIndexVo reservationIndexVo) throws Exception;
	public void updateReservationEventLotTemp(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 접수자삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자 추첨확정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void updateReservationEventUserInfoConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception;
	
	public List<String> retrieveListReservationEventRandom(ReservationIndexVo reservationIndexVo) throws Exception;
	public List<String> retrieveListReservationEventRandomTemp(ReservationIndexVo reservationIndexVo) throws Exception;
	public int retrieveReservationEventCntForLot(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	public List<ReservationIndexVo> retrieveListSuervisionOrg() throws Exception;
	public Map<String, Object> retrievePagArtMovie(ReservationIndexVo reservationIndexVo) throws Exception;
	public List<ReservationIndexVo> selectPagArtAllList() throws Exception;
	public List<ReservationIndexVo> selectPagArtAllList2() throws Exception;
	public List<EgovMap> selectArtCalInfo(ReservationFacVo reservationFacVo) throws Exception;
	public List<EgovMap> selectArtCalInfoText(ReservationFacVo reservationFacVo) throws Exception;
	public Map<String, Object> retrievePagArtReservation(ReservationIndexVo reservationIndexVo) throws Exception;
	public List<ReservationIndexVo> selectArtReservationAll() throws Exception;
	public EgovMap selectPgInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
}
