package egovframework.injeinc.boffice.ex.reservationHealth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationAddHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationAddNodateVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationHealthDayVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationHealthUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationIndexHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationItemHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationUserInfoHealthVo;

public interface ReservationHealthSvc {

	/**
     * 예약등록
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void registReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception;

	/**
     * 조회수 증가
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void modifyReservationHealthReadCnt(ReservationIndexHealthVo reservationIndexVo) throws Exception;

	/**
     * 운영불가(휴무)일등록
     * @param ReservationAddNodateVo
     * @throws Exception
     */
	public void registReservationAddNodate(ReservationAddNodateVo reservationAddNodateVo) throws Exception;
	
	/**
	 * 요일별 회차등록
	 * @param ReservationAddNodateVo
	 * @throws Exception
	 */
	public void registReservationAddWeek(ReservationAddHealthVo reservationAddHealthVo) throws Exception;

	/**
	 * 운영불가(휴무)일 전체삭제
	 * @param ReservationIndexHealthVo
	 * @throws Exception
	 */
	public void removeReservationHealthNodate(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception;
	
	/**
     * 요일별 회차 삭제
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void removeReservationHealthAddWeek(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception;
	
	/**
     * 예약수정
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void modifyReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception;
	
	
	/**
     * 예약등록(추가항목2) 입력
     * @param ReservationItemHealthVo
     * @throws Exception
     */
	public void registReservationHealthItem(ReservationItemHealthVo reservationItemVo) throws Exception;
	
	/**
     * 요일별 회차 리스트
     * @param ReservationIndexHealthVo
     * @return List<ReservationAddItemVo>
     * @throws Exception
     */
	public List<ReservationAddHealthVo> retrieveListReservationHealthWeek(ReservationIndexHealthVo reservationIndexVo) throws Exception;
	
	/**
	 * 운영불가(휴무)일 리스트
	 * @param ReservationIndexHealthVo
	 * @return List<ReservationAddItemVo>
	 * @throws Exception
	 */
	public List<ReservationAddHealthVo> retrieveListReservationHealtNodate(ReservationIndexHealthVo reservationIndexVo) throws Exception;

	/**
     * 예약등록(추가항목2) 리스트
     * @param ReservationIndexHealthVo
     * @return List<ReservationItemVo>
     * @throws Exception
     */
	public List<ReservationItemHealthVo> retrieveListReservationHealthItem(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception;
	
	/**
     * 예약등록(추가항목2) 삭제
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void removeReservationHealthItem(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception;

	/**
     * 예약상세
     * @param ReservationIndexHealthVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexHealthVo retrieveReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception;
	
	/**
	 * 예약달력
	 * @param ReservationHealthDayVo
	 * @return List<ReservationHealthDayVo>
	 * @throws Exception
	 */
	public List retrieveListReservationHealthCal(ReservationHealthDayVo reservationHealthDayVo) throws Exception;
	
	/**
	 * 신청가능일자 구분
	 * @param ReservationHealthDayVo
	 * @return List<ReservationHealthDayVo>
	 * @throws Exception
	 */
	public List retrieveListReservationHealthDate(ReservationHealthDayVo reservationHealthDayVo) throws Exception;
	
	/**
	 * 예약일자별 진료예약자 리스트
	 * @param param
	 * @return List<ReservationHealthDayVo>
	 * @throws Exception
	 */
	public Map<String, Object> retrieveUserHealthList(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;
	
	/**
	 * 요일별 회차 목록
	 * @param param
	 * @return List<ReservationHealthDayVo>
	 * @throws Exception
	 */
	public HashMap<String, Object> retrieveWeekHealthList(HashMap<String, String> param) throws Exception;
	
	/**
     * 접수자삭제
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void removeReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;
	
	/**
	 * 요일별 회차 인원카운트
	 * @param param
	 * @return List<ReservationHealthDayVo>
	 * @throws Exception
	 */
	public HashMap<String, Object> retrieveUserHealthCnt(HashMap<String, String> param) throws Exception;
	
	/**
     * 접수자등록
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void registReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;

	/**
     * 접수자답변등록
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void registReservationHealthUserAnswer(ReservationHealthUserAnswerVo reservationHealthUserAnswerVo) throws Exception;

	/**
     * 회차별 정원 카운트
     * @param ReservationIndexHealthVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public int retrieveReservationHealthUserCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
     * 예약삭제
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void removeReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception;

	
	
	/**
     * 페이징
     * @param ReservationIndexHealthVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception;

	/**
     * 예약접수자 리스트
     * @param ReservationIndexHealthVo
     * @return List<ReservationUserInfoHealthVo>
     * @throws Exception
     */
	public List<ReservationUserInfoHealthVo> retrieveListReservationHealthUserInfo(ReservationIndexHealthVo reservationIndexVo) throws Exception;

	
	/**
     * 접수자 상세
     * @param ReservationUserInfoHealthVo
     * @return ReservationUserInfoHealthVo
     * @throws Exception
     */
	public ReservationUserInfoHealthVo retrieveReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;

	/**
     * 중복접수여부
     * @param ReservationUserInfoHealthVo
     * @return int
     * @throws Exception
     */
	public int retrieveReservationHealthUserInfoDupCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;

	/**
     * 중복접수여부
     * @param ReservationUserInfoHealthVo
     * @return int
     * @throws Exception
     */
	public int retrieveReservationHealthRedundancyDupCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;


	/**
     * 접수자수정
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void modifyReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;

	/**
     * 접수자답변삭제
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void removeReservationHealthUserAnswer(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;

	/**
     * 파일업로드
     * @param ReservationIndexHealthVo
     * @return ReservationIndexVo
     * @throws Exception
     */
    public ReservationIndexHealthVo uploadFile(Map<String, MultipartFile> files, ReservationIndexHealthVo reservationIndexVo) throws Exception;

    /**
     * 신청자 추첨
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void updateReservationHealthLot(ReservationIndexHealthVo reservationIndexVo) throws Exception;
	public void updateReservationHealthLotTemp(ReservationIndexHealthVo reservationIndexVo) throws Exception;

	

	/**
     * 접수자 추첨확정
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void updateReservationHealthUserInfoConfirm(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception;
	
	public List<String> retrieveListReservationHealthRandom(ReservationIndexHealthVo reservationIndexVo) throws Exception;
	public List<String> retrieveListReservationHealthRandomTemp(ReservationIndexHealthVo reservationIndexVo) throws Exception;
	public int retrieveReservationHealthCntForLot(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception;

}
