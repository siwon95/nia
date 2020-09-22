package egovframework.injeinc.boffice.ex.reservationEtc.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.service.FileListVO;
import egovframework.cmm.service.FileVO;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserFileVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;

public interface ReservationEtcSvc {

	/**
     * 예약등록
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void registReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 조회수 증가
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationEtcReadCnt(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약등록(추가항목1)
     * @param ReservationAddItemVo
     * @throws Exception
     */
	public void registReservationEtcAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception;

	/**
     * 예약등록(추가항목2)
     * @param ReservationItemVo
     * @throws Exception
     */
	public void registReservationEtcItem(ReservationItemVo reservationItemVo) throws Exception;

	/**
     * 예약수정
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약상세
     * @param ReservationIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexVo retrieveReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception;
	
	/**
     * 페이징
     * @param ReservationIndexVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목 리스트
     * @param ReservationIndexVo
     * @return List<ReservationAddItemVo>
     * @throws Exception
     */
	public List<ReservationAddItemVo> retrieveListReservationEtcAddItem(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목 리스트
     * @param ReservationIndexVo
     * @return List<ReservationItemVo>
     * @throws Exception
     */
	public List<ReservationItemVo> retrieveListReservationEtcItem(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEtcAddItem(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 추가항목삭제2
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEtcItem(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 예약접수자 리스트
     * @param ReservationIndexVo
     * @return List<ReservationUserInfoVo>
     * @throws Exception
     */
	public List<ReservationUserInfoVo> retrieveListReservationEtcUserInfo(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 접수자등록
     * @param reservationUserInfoVo
     * @throws Exception
     */
	public void registReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자답변등록
     * @param reservationUserInfoVo
     * @throws Exception
     */
	public void registReservationEtcUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception;

	/**
     * 접수자 상세
     * @param ReservationUserInfoVo
     * @return ReservationUserInfoVo
     * @throws Exception
     */
	public ReservationUserInfoVo retrieveReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 중복접수여부
     * @param ReservationUserInfoVo
     * @return int
     * @throws Exception
     */
	public int retrieveReservationEtcUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 중복접수여부
     * @param ReservationUserInfoVo
     * @return int
     * @throws Exception
     */
	public int retrieveReservationEtcRedundancyDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception;


	/**
     * 접수자수정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void modifyReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자답변삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationEtcUserAnswer(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

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
	public void updateReservationEtcLot(ReservationIndexVo reservationIndexVo) throws Exception;
	public void updateReservationEtcLotTemp(ReservationIndexVo reservationIndexVo) throws Exception;

	/**
     * 접수자삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	/**
     * 접수자 추첨확정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void updateReservationEtcUserInfoConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception;
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception;
	
	public List<String> retrieveListReservationEtcRandom(ReservationIndexVo reservationIndexVo) throws Exception;
	public List<String> retrieveListReservationEtcRandomTemp(ReservationIndexVo reservationIndexVo) throws Exception;
	public int retrieveReservationEtcCntForLot(ReservationUserInfoVo reservationUserInfoVo) throws Exception;

	public FileListVO uploadUserFile(Map<String, MultipartFile> files, ReservationUserFileVo reservationUserFileVo) throws Exception;

	public void insertReservationEtcUserFile(FileVO fileVo, String atchFileId, String riIdx, String rtIdx, String ruIdx) throws Exception;

	public List retrieveListSuervisionOrg(boolean isAdmin, List<String> mgrSiteCdList) throws Exception;
	
	public List retrieveListSuervisionOrgF() throws Exception;

}
