package egovframework.injeinc.boffice.ex.reservationHealth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.EgovMessageSource;
import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.EgovFileMngUtil;
import egovframework.cmm.service.FileVO;
import egovframework.injeinc.boffice.ex.reservationHealth.dao.ReservationHealthDao;
import egovframework.injeinc.boffice.ex.reservationHealth.service.ReservationHealthSvc;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationAddHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationAddNodateVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationHealthDayVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationHealthUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationIndexHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationItemHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationUserInfoHealthVo;

@Service("ReservationHealthSvc")
public class ReservationHealthImpl implements ReservationHealthSvc{

	@Resource(name = "ReservationHealthDao")
	private ReservationHealthDao reservationHealthDao;

    // 파일정보 관리
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService egovFileMngService;

    // 파일 처리 관련 유틸리티
    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil egovFileMngUtil;
    
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
     * 예약등록
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void registReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		reservationHealthDao.createReservationHealth(reservationIndexVo);
	}

	/**
     * 조회수 증가
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void modifyReservationHealthReadCnt(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		reservationHealthDao.updateReservationHealthReadCnt(reservationIndexVo);
	}

	/**
     * 운영불가(휴무)일등록
     * @param reservationAddItemVo
     * @throws Exception
     */
	public void registReservationAddNodate(ReservationAddNodateVo reservationAddNodateVo) throws Exception{
		reservationHealthDao.createReservationAddNodate(reservationAddNodateVo);
	}
	
	/**
	 * 요일별 회차등록
	 * @param reservationAddHealthVo
	 * @throws Exception
	 */
	public void registReservationAddWeek(ReservationAddHealthVo reservationAddHealthVo) throws Exception{
		reservationHealthDao.createReservationAddWeek(reservationAddHealthVo);
	}
	
	/**
	 * 운영불가(휴무)일 전체삭제
	 * @param ReservationIndexHealthVo
	 * @throws Exception
	 */
	public void removeReservationHealthNodate(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception{
		reservationHealthDao.deleteReservationHealtNodate(reservationIndexHealthVo);
	}
	
	/**
     * 요일별 회차 삭제
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void removeReservationHealthAddWeek(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception{
		reservationHealthDao.deleteReservationHealthAddWeek(reservationIndexHealthVo);
	}
	
	/**
     * 예약수정
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void modifyReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		reservationHealthDao.updateReservationHealth(reservationIndexVo);
	}
	
	/**
     * 예약등록(추가항목2) 입력
     * @param reservationItemVo
     * @throws Exception
     */
	public void registReservationHealthItem(ReservationItemHealthVo reservationItemVo) throws Exception{
		reservationHealthDao.createReservationHealthItem(reservationItemVo);
	}
	
	/**
     * 요일별회차 리스트
     * @param ReservationIndexHealthVo
     * @return List<ReservationAddItemVo>
     * @throws Exception
     */
	public List<ReservationAddHealthVo> retrieveListReservationHealthWeek(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		return reservationHealthDao.selectListReservationHealthWeek(reservationIndexVo);
	}
	
	/**
	 * 운영불가(휴무)일 리스트
	 * @param ReservationIndexHealthVo
	 * @return List<ReservationAddItemVo>
	 * @throws Exception
	 */
	public List<ReservationAddHealthVo> retrieveListReservationHealtNodate(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		return reservationHealthDao.selectListReservationHealthNodate(reservationIndexVo);
	}
	
	/**
     * 예약등록(추가항목2) 리스트
     * @param ReservationIndexHealthVo
     * @return List<ReservationItemVo>
     * @throws Exception
     */
	public List<ReservationItemHealthVo> retrieveListReservationHealthItem(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception{
		return reservationHealthDao.selectListReservationHealthItem(reservationIndexHealthVo);
	}
	
	/**
     * 예약등록(추가항목2) 삭제
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void removeReservationHealthItem(ReservationIndexHealthVo reservationIndexHealthVo) throws Exception{
		reservationHealthDao.deleteReservationHealthItem(reservationIndexHealthVo);
	}
	
	/**
     * 예약상세
     * @param ReservationIndexHealthVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexHealthVo retrieveReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		return reservationHealthDao.selectReservationHealth(reservationIndexVo);
	}
	
	/**
	 * 예약 달력
	 * @param ReservationHealthDayVo
	 * @return List<ReservationHealthDayVo>
	 * @throws Exception
	 */
	public List retrieveListReservationHealthCal(ReservationHealthDayVo reservationHealthDayVo) throws Exception{
		return reservationHealthDao.selectListReservationHealthCal(reservationHealthDayVo);
	}
	
	/**
	 * 신청가능일자 구분
	 * @param ReservationHealthDayVo
	 * @return List<ReservationHealthDayVo>
	 * @throws Exception
	 */
	public List retrieveListReservationHealthDate(ReservationHealthDayVo reservationHealthDayVo) throws Exception{
		return reservationHealthDao.selectListReservationHealthDate(reservationHealthDayVo);
	}
	
	/**
     * 예약일자별 진료신청자 리스트
     * @param ReservationIndexHealthVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrieveUserHealthList(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationUserInfoHealthVo> result = reservationHealthDao.selectReservationUserHealthList(reservationUserInfoHealthVo);
		int cnt = reservationHealthDao.selectReservationUserHealthCnt(reservationUserInfoHealthVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	/**
	 * 요일별 회차리스트
	 * @param param
	 * @return List<ReservationHealthDayVo>
	 * @throws Exception
	 */
	public HashMap<String, Object> retrieveWeekHealthList(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		try {
			//목록
			ArrayList<Object> listDao = (ArrayList<Object>) reservationHealthDao.selectWeekHealthListAx(param);
			output.put("weekHealthDataList", listDao);
			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", egovMessageSource.getMessage("301.code"));
				output.put("SVC_MSGE", egovMessageSource.getMessage("301.message"));
				return output;				
			}
			
			
		} catch (RuntimeException e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		} catch (Exception e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		}
		
		return output;
	}

	/**
	 * 요일별 회차 카운트 리스트
	 * @param param
	 * @return List<ReservationHealthDayVo>
	 * @throws Exception
	 */
	public HashMap<String, Object> retrieveUserHealthCnt(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		try {
			//목록
			ArrayList<Object> listDao = (ArrayList<Object>) reservationHealthDao.selectUserHealthCntAx(param);
			output.put("userHealthDataList", listDao);
			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", egovMessageSource.getMessage("301.code"));
				output.put("SVC_MSGE", egovMessageSource.getMessage("301.message"));
				return output;				
			}
			
			
		} catch (RuntimeException e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		} catch (Exception e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		}
		
		return output;
	}
	
	/**
     * 접수자 등록
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void registReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception{
		reservationHealthDao.createReservationHealthUserInfo(reservationUserInfoHealthVo);
	}

	/**
     * 접수자 답변 등록
     * @param ReservationHealthUserAnswerVo
     * @throws Exception
     */
	public void registReservationHealthUserAnswer(ReservationHealthUserAnswerVo reservationHealthUserAnswerVo) throws Exception{
		reservationHealthDao.createReservationHealthUserAnswer(reservationHealthUserAnswerVo);
	}
	
	/**
     * 회차별 정원 카운트
     * @param ReservationIndexHealthVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public int retrieveReservationHealthUserCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception{
		return reservationHealthDao.selectReservationHealthUserCnt(reservationUserInfoHealthVo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	

	/**
     * 예약삭제
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void removeReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		reservationHealthDao.deleteReservationHealth(reservationIndexVo);
	}

	
	
	/**
     * 페이징
     * @param ReservationIndexHealthVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationHealth(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationIndexHealthVo> result = reservationHealthDao.selectPagReservationHealth(reservationIndexVo);
		int cnt = reservationHealthDao.selectReservationHealthCnt(reservationIndexVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
     * 예약접수자 리스트
     * @param ReservationIndexHealthVo
     * @return List<ReservationUserInfoVo>
     * @throws Exception
     */
	public List<ReservationUserInfoHealthVo> retrieveListReservationHealthUserInfo(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		return reservationHealthDao.selectListReservationHealthUserInfo(reservationIndexVo);
	}

	

	/**
     * 접수자 상세
     * @param ReservationUserInfoHealthVo
     * @return ReservationUserInfoVo
     * @throws Exception
     */
	public ReservationUserInfoHealthVo retrieveReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception{
		return reservationHealthDao.selectReservationHealthUserInfo(reservationUserInfoHealthVo);
	}

	public int retrieveReservationHealthUserInfoDupCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception{
		return reservationHealthDao.selectReservationHealthUserInfoDupCnt(reservationUserInfoHealthVo);
	}

	public int retrieveReservationHealthRedundancyDupCnt(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception{
		return reservationHealthDao.selectReservationHealthRedundancyDupCnt(reservationUserInfoHealthVo);
	}

	/**
     * 접수자수정
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void modifyReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception{
		reservationHealthDao.updateReservationHealthUserInfo(reservationUserInfoHealthVo);
	}

	/**
     * 접수자답변삭제
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void removeReservationHealthUserAnswer(ReservationUserInfoHealthVo reservationUserInfoHealthVo) throws Exception{
		reservationHealthDao.deleteReservationHealthUserAnswer(reservationUserInfoHealthVo);
	}

	 /** 파일업로드 */
    public ReservationIndexHealthVo uploadFile(Map<String, MultipartFile> files, ReservationIndexHealthVo reservationIndexVo) throws Exception {
    	// 첨부파일
		List<FileVO> fvoList = null;
		String riImageFileId = "";
		if(reservationIndexVo != null){
			riImageFileId = reservationIndexVo.getRiImageFileId();
		}
	    if(files != null){
			if (!files.isEmpty()) {
				// 첨부파일 업로드
	    	    Map<String, MultipartFile> file = new HashMap<String, MultipartFile>();
	    	    MultipartFile uploadFile = files.get("riImageFile");
	    	    if (uploadFile.getSize() > 0) {
	    	    	file.put("riImageFile", files.get("riImageFile"));
		    	    if ("".equals(riImageFileId)) {
		    	    	// 파일등록
		    	    	fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, "", "reservationHealth.file.upload.path");
		    	    	riImageFileId = egovFileMngService.insertFileInfs(fvoList);
		    	    } else {
		    	    	// 파일수정
		    	    	FileVO fvo = new FileVO();
			    		fvo.setAtchFileId(riImageFileId);
			    		fvo.setFileSn("0");
			    		fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, riImageFileId, "reservationHealth.file.upload.path");
			    		egovFileMngService.deleteFileInf(fvo);
					    egovFileMngService.updateFileInfs(fvoList);
		    	    }
	    	    }
		    }
	    }
	    if(reservationIndexVo != null){
	    	reservationIndexVo.setRiImageFileId(riImageFileId);
	    }
    	return reservationIndexVo;
    }

    /**
     * 신청자 추첨
     * @param ReservationIndexHealthVo
     * @throws Exception
     */
	public void updateReservationHealthLot(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		reservationHealthDao.updateReservationHealthLot(reservationIndexVo);
	}
	
	public void updateReservationHealthLotTemp(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		reservationHealthDao.updateReservationHealthLotTemp(reservationIndexVo);
	}

	/**
     * 접수자 삭제
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void removeReservationHealthUserInfo(ReservationUserInfoHealthVo reservationUserInfoVo) throws Exception{
		reservationHealthDao.deleteReservationHealthUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자 추첨확정
     * @param ReservationUserInfoHealthVo
     * @throws Exception
     */
	public void updateReservationHealthUserInfoConfirm(ReservationUserInfoHealthVo reservationUserInfoVo) throws Exception{
		reservationHealthDao.updateReservationHealthUserInfoConfirm(reservationUserInfoVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception {
		return reservationHealthDao.selectDeptCode();
	}
	
	public List<String> retrieveListReservationHealthRandom(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		return reservationHealthDao.selectListReservationHealthRandom(reservationIndexVo);
	}
	
	public List<String> retrieveListReservationHealthRandomTemp(ReservationIndexHealthVo reservationIndexVo) throws Exception{
		return reservationHealthDao.selectListReservationHealthRandomTemp(reservationIndexVo);
	}

	public int retrieveReservationHealthCntForLot(ReservationUserInfoHealthVo reservationUserInfoVo) throws Exception{
		return reservationHealthDao.selectReservationHealthCntForLot(reservationUserInfoVo);
	}

}
