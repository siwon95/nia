package egovframework.injeinc.boffice.ex.reservationRent.service.impl;

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
import egovframework.injeinc.boffice.ex.reservationRent.dao.ReservationRentDao;
import egovframework.injeinc.boffice.ex.reservationRent.service.ReservationRentSvc;
import egovframework.injeinc.boffice.ex.reservationRent.vo.ReservationRentVo;

@Service("ReservationRentSvc")
public class ReservationRentImpl implements ReservationRentSvc{

	@Resource(name = "ReservationRentDao")
	private ReservationRentDao reservationRentDao;

    // 파일정보 관리
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService egovFileMngService;

    // 파일 처리 관련 유틸리티
    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil egovFileMngUtil;

	/**
     * 예약등록
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void registReservationRent(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationRentDao.createReservationRent(reservationIndexVo);
	}

	/**
     * 예약등록(추가항목1)
     * @param reservationAddItemVo
     * @throws Exception
     */
	public void registReservationRentAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception{
		reservationRentDao.createReservationRentAddItem(reservationAddItemVo);
	}

	/**
     * 예약수정
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationRent(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationRentDao.updateReservationRent(reservationIndexVo);
	}

	/**
     * 예약삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationRent(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationRentDao.deleteReservationRent(reservationIndexVo);
	}

	/**
     * 예약상세
     * @param ReservationIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexVo retrieveReservationRent(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationRentDao.selectReservationRent(reservationIndexVo);
	}
	
	/**
     * 추첨방법조회
     * @param ReservationIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public String retrieveReservationRentLotUse(ReservationIndexVo reservationIndexVo) throws Exception {
		return reservationRentDao.selectReservationRentLotUse(reservationIndexVo);
	}
	
	/**
     * 페이징
     * @param ReservationIndexVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationRent(ReservationIndexVo reservationIndexVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationIndexVo> result = reservationRentDao.selectPagReservationRent(reservationIndexVo);
		int cnt = reservationRentDao.selectReservationRentCnt(reservationIndexVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
     * 추가항목 리스트
     * @param ReservationIndexVo
     * @return List<ReservationAddItemVo>
     * @throws Exception
     */
	public List<ReservationAddItemVo> retrieveListReservationRentAddItem(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationRentDao.selectListReservationRentAddItem(reservationIndexVo);
	}

	
	public List<ReservationUserInfoVo> retrieveReservationRentUserList(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationRentDao.selectReservationRentUserList(reservationUserInfoVo);
	}
	/**
     * 추가항목삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationRentAddItem(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationRentDao.deleteReservationRentAddItem(reservationIndexVo);
	}

	/**
     * 예약접수자 리스트
     * @param ReservationIndexVo
     * @return List<ReservationUserInfoVo>
     * @throws Exception
     */
	public Map<String, Object> retrieveListReservationRentUserInfo(ReservationRentVo reservationRentVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationUserInfoVo> result = reservationRentDao.selectListReservationRentUserInfo(reservationRentVo);
		int cnt = reservationRentDao.selectListReservationRentUserInfoCnt(reservationRentVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public Map<String, Object> retrieveListReservationRentFUserInfo(ReservationRentVo reservationRentVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationUserInfoVo> result = reservationRentDao.selectListReservationRentFUserInfo(reservationRentVo);
		int cnt = reservationRentDao.selectListReservationRentFUserInfoCnt(reservationRentVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	
	/**
     * 예약달력
     * @param ReservationRentVo
     * @return List<ReservationRentVo>
     * @throws Exception
     */
	public List retrieveListReservationRentCal(ReservationRentVo reservationRentVo) throws Exception{
		return reservationRentDao.selectListReservationRentCal(reservationRentVo);
	}

	/**
     * 접수자 등록
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void registReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationRentDao.createReservationRentUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자 답변 등록
     * @param ReservationUserAnswerVo
     * @throws Exception
     */
	public void registReservationRentUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception{
		reservationRentDao.createReservationRentUserAnswer(reservationUserAnswerVo);
	}

	/**
     * 접수자 상세
     * @param ReservationUserInfoVo
     * @return ReservationUserInfoVo
     * @throws Exception
     */
	public ReservationUserInfoVo retrieveReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationRentDao.selectReservationRentUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자수정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void modifyReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationRentDao.updateReservationRentUserInfo(reservationUserInfoVo);
	}
	
	public void modifyReservationRentUserInfo2(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationRentDao.updateReservationRentUserInfo2(reservationUserInfoVo);
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
		    	    	fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, "", "reservationRent.file.upload.path");
		    	    	riImageFileId = egovFileMngService.insertFileInfs(fvoList);
		    	    } else {
		    	    	// 파일수정
		    	    	FileVO fvo = new FileVO();
			    		fvo.setAtchFileId(riImageFileId);
			    		fvo.setFileSn("0");
			    		fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, riImageFileId, "reservationRent.file.upload.path");
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
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void updateReservationRentLot(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationRentDao.updateReservationRentLot(reservationIndexVo);
	}

	/**
     * 접수자 삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationRentUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationRentDao.deleteReservationRentUserInfo(reservationUserInfoVo);
	}

	/**
     * 예약 확정자 수
     * @param ReservationRentVo
     * @throws Exception
     */
	public int retrieveReservationRentConfirmCnt(ReservationRentVo reservationRentVo) throws Exception{
		return reservationRentDao.selectReservationRentConfirmCnt(reservationRentVo);
	}

	public List retrieveListReservationRentFix(ReservationRentVo reservationRentVo) throws Exception{
		return reservationRentDao.selectListReservationRentFix(reservationRentVo);
	}

	public int retrieveReservationRentUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationRentDao.selectReservationRentUserInfoDupCnt(reservationUserInfoVo);
	}
	
	/** 시설예약 상세페이지 */
	public ReservationIndexVo retrieveReservationRentView(ReservationIndexVo reservationIndexVo) throws Exception {
		return reservationRentDao.selectReservationRentView(reservationIndexVo);
	}
	
	/** 온라인선착순 모집에 관한 접수카운트*/
	public List retrieveItemCountList(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return reservationRentDao.selectItemCountList(reservationUserInfoVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception {
		return reservationRentDao.selectDeptCode();
	}
	
	public void modReservationRentUserConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationRentDao.updateReservationRentUserConfirm(reservationUserInfoVo);
	}

}
