package egovframework.injeinc.boffice.ex.reservationFac.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.EgovFileMngUtil;
import egovframework.cmm.service.FileVO;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationFac.dao.ReservationFacDao;
import egovframework.injeinc.boffice.ex.reservationFac.service.ReservationFacSvc;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacTeamVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacTimeVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("ReservationFacSvc")
public class ReservationFacImpl implements ReservationFacSvc{

	@Resource(name = "ReservationFacDao")
	private ReservationFacDao reservationFacDao;

    // 파일정보 관리
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService egovFileMngService;

    // 파일 처리 관련 유틸리티
    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil egovFileMngUtil;

	/**
     * 예약등록
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void registReservationFac(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationFacDao.createReservationFac(reservationIndexVo);
	}

	/**
     * 예약등록(추가항목1)
     * @param reservationAddItemVo
     * @throws Exception
     */
	public void registReservationFacAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception{
		reservationFacDao.createReservationFacAddItem(reservationAddItemVo);
	}
	
	public void registReservationFacTime(ReservationFacTimeVo reservationFacTimeVo) throws Exception{
		reservationFacDao.createReservationFacTime(reservationFacTimeVo);
	}

	/**
     * 예약수정
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void modifyReservationFac(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationFacDao.updateReservationFac(reservationIndexVo);
	}

	/**
     * 예약삭제
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void removeReservationFac(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationFacDao.deleteReservationFac(reservationIndexVo);
	}

	/**
     * 예약상세
     * @param ReservationRentIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexVo retrieveReservationFac(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationFacDao.selectReservationFac(reservationIndexVo);
	}
	
	/**
     * 추첨방법조회
     * @param ReservationRentIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public String retrieveReservationFacLotUse(ReservationIndexVo reservationIndexVo) throws Exception {
		return reservationFacDao.selectReservationFacLotUse(reservationIndexVo);
	}
	
	/**
     * 추가항목 수
     * @param ReservationRentIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public String retrieveReservationFacAddCnt(ReservationIndexVo reservationIndexVo) throws Exception {
		return reservationFacDao.selectReservationFacAddCnt(reservationIndexVo);
	}
	
	public  List<ReservationFacTimeVo> retrieveReservationFacTimeInfo(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationFacDao.selectReservationFacTimeInfo(reservationIndexVo);
	}
	
	public  List<ReservationFacTimeVo> retrieveReservationFacUserTimeInfo(ReservationFacVo reservationFacVo) throws Exception{
		return reservationFacDao.selectReservationFacUserTimeInfo(reservationFacVo);
	}
	
	public  List<ReservationFacTimeVo> retrieveReservationFacUserTimeInfo2(ReservationFacVo reservationFacVo) throws Exception{
		return reservationFacDao.selectReservationFacUserTimeInfo2(reservationFacVo);
	}
	
	public  List<ReservationFacTimeVo> retrieveReservationFacUserTimeInfo3(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationFacDao.selectReservationFacUserTimeInfo3(reservationUserInfoVo);
	}
	
	public  List<ReservationFacTimeVo> retrieveReservationFacUserTimeInfo4(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationFacDao.selectReservationFacUserTimeInfo4(reservationUserInfoVo);
	}

	/**
     * 페이징
     * @param ReservationRentIndexVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationFac(ReservationIndexVo reservationIndexVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationIndexVo> result = reservationFacDao.selectPagReservationFac(reservationIndexVo);
		int cnt = reservationFacDao.selectReservationFacCnt(reservationIndexVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	/**
     * 페이징
     * @param ReservationRentIndexVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagArtReservationFac(ReservationIndexVo reservationIndexVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationIndexVo> result = reservationFacDao.selectPagArtReservationFac(reservationIndexVo);
		int cnt = reservationFacDao.selectArtReservationFacCnt(reservationIndexVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
     * 추가항목 리스트
     * @param ReservationRentIndexVo
     * @return List<ReservationAddItemVo>
     * @throws Exception
     */
	public List<ReservationAddItemVo> retrieveListReservationFacAddItem(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationFacDao.selectListReservationFacAddItem(reservationIndexVo);
	}
	
	public ReservationAddItemVo retrievReservationFacAddItemOne(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationFacDao.retrieveReservationFacAddItemOne(reservationIndexVo);
	}

	/**
     * 추가항목삭제
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void removeReservationFacAddItem(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationFacDao.deleteReservationFacAddItem(reservationIndexVo);
	}
	
	public void removeReservationFacTime(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationFacDao.deleteReservationFacTime(reservationIndexVo);
	}

	/**
     * 예약접수자 리스트
     * @param ReservationRentIndexVo
     * @return List<ReservationUserInfoVo>
     * @throws Exception
     */
	public List<ReservationUserInfoVo> retrieveListReservationFacUserInfo(ReservationFacVo reservationFacVo) throws Exception{
		return reservationFacDao.selectListReservationFacUserInfo(reservationFacVo);
	}
	
	public List<ReservationUserInfoVo> retrieveListReservationFacReturnFee(ReservationFacVo reservationFacVo) throws Exception{
		return reservationFacDao.selectListReservationFacUserInfoReturnFee(reservationFacVo);
	}

	/**
     * 예약달력
     * @param ReservationFacVo
     * @return List<ReservationFacVo>
     * @throws Exception
     */
	public List retrieveListReservationFacCal(ReservationFacVo reservationFacVo) throws Exception{
		return reservationFacDao.selectListReservationFacCal(reservationFacVo);
	}
	
	/**
     * 예약달력
     * @param ReservationFacVo
     * @return List<ReservationFacVo>
     * @throws Exception
     */
	public List retrieveListReservationFacCalUser(ReservationFacVo reservationFacVo) throws Exception{
		return reservationFacDao.selectListReservationFacCalUser(reservationFacVo);
	}

	/**
     * 접수자 등록
     * @param ReservationUserInfoRentVo
     * @throws Exception
     */
	public void registReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationFacDao.createReservationFacUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자 답변 등록
     * @param ReservationUserAnswereeeVo
     * @throws Exception
     */
	public void registReservationFacUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception{
		reservationFacDao.createReservationFacUserAnswer(reservationUserAnswerVo);
	}

	/**
     * 접수자 상세
     * @param ReservationUserInfoRentVo
     * @return ReservationUserInfoVo
     * @throws Exception
     */
	public ReservationUserInfoVo retrieveReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationFacDao.selectReservationFacUserInfo(reservationUserInfoVo);
	}
	
	public ReservationUserInfoVo retrieveReservationFacUserInfoReturnFee(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationFacDao.selectReservationFacUserInfoReturnFee(reservationUserInfoVo);
	}

	/**
     * 접수자수정
     * @param ReservationUserInfoRentVo
     * @throws Exception
     */
	public void modifyReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationFacDao.updateReservationFacUserInfo(reservationUserInfoVo);
	}

	 /** 파일업로드 */
    public ReservationIndexVo uploadFile(Map<String, MultipartFile> files, ReservationIndexVo reservationIndexVo) throws Exception {
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
		    	    	fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, "", "reservationFac.file.upload.path");
		    	    	riImageFileId = egovFileMngService.insertFileInfs(fvoList);
		    	    } else {
		    	    	// 파일수정
		    	    	FileVO fvo = new FileVO();
			    		fvo.setAtchFileId(riImageFileId);
			    		fvo.setFileSn("0");
			    		fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, riImageFileId, "reservationFac.file.upload.path");
			    		egovFileMngService.deleteFileInf(fvo);
					    egovFileMngService.updateFileInfs(fvoList);
		    	    }
	    	    }
		    }
	    }
		reservationIndexVo.setRiImageFileId(riImageFileId);
    	return reservationIndexVo;
    }

    /**
     * 신청자 추첨
     * @param ReservationRentIndexVo
     * @throws Exception
     */
	public void updateReservationFacLot(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationFacDao.updateReservationFacLot(reservationIndexVo);
	}

	/**
     * 접수자 삭제
     * @param ReservationUserInfoRentVo
     * @throws Exception
     */
	public void removeReservationFacUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationFacDao.deleteReservationFacUserInfo(reservationUserInfoVo);
	}
	
	public void modReservationFacUserConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationFacDao.updateReservationFacUserConfirm(reservationUserInfoVo);
	}
	
	/**
     * 예약 확정자 수
     * @param ReservationFacVo
     * @throws Exception
     */
	public int retrieveReservationFacConfirmCnt(ReservationFacVo reservationFacVo) throws Exception{
		return reservationFacDao.selectReservationFacConfirmCnt(reservationFacVo);
	}

	public List retrieveListReservationFacFix(ReservationFacVo reservationFacVo) throws Exception{
		return reservationFacDao.selectListReservationFacFix(reservationFacVo);
	}
	
	public List retrieveListAnotherFac() throws Exception{
		return reservationFacDao.selectReservationFacAnotherFac();
	}

	public int retrieveReservationFacUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationFacDao.selectReservationFacUserInfoDupCnt(reservationUserInfoVo);
	}
	
	/** 시설예약 상세페이지 */
	public ReservationIndexVo retrieveReservationFacView(ReservationIndexVo reservationIndexVo) throws Exception {
		return reservationFacDao.selectReservationFacView(reservationIndexVo);
	}
	
	/** 온라인선착순 모집에 관한 접수카운트*/
	public List retrieveItemCountList(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return reservationFacDao.selectItemCountList(reservationUserInfoVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception {
		return reservationFacDao.selectDeptCode();
	}
	
	public Map<String, Object> retrievePagFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<EgovMap> result = reservationFacDao.selectPagFacTeam(reservationFacTeamVo);
		int cnt = reservationFacDao.selectPagFacTeamCnt(reservationFacTeamVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public EgovMap retrieveFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception{
		return reservationFacDao.selectFacTeam(reservationFacTeamVo);
	}
	
	public int retrieveUserckCnt(ReservationFacTeamVo reservationFacTeamVo) throws Exception{
		return reservationFacDao.selectUserckCnt(reservationFacTeamVo);
	}
	
	public int retrieveTeamCkCnt(ReservationFacTeamVo reservationFacTeamVo) throws Exception{
		return reservationFacDao.selectTeamCkCnt(reservationFacTeamVo);
	}
	
	public void createReservationFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception{
		reservationFacDao.insertReservationFacTeam(reservationFacTeamVo);
	}
	
	public void modReservationFacTeam(ReservationFacTeamVo reservationFacTeamVo) throws Exception{
		reservationFacDao.updateReservationFacTeam(reservationFacTeamVo);
	}

}
