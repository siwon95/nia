package egovframework.injeinc.boffice.ex.reservationEtc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.EgovFileMngUtil;
import egovframework.cmm.service.FileListVO;
import egovframework.cmm.service.FileVO;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserFileVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationEtc.dao.ReservationEtcDao;
import egovframework.injeinc.boffice.ex.reservationEtc.service.ReservationEtcSvc;

@Service("ReservationEtcSvc")
public class ReservationEtcImpl implements ReservationEtcSvc{

	@Resource(name = "ReservationEtcDao")
	private ReservationEtcDao reservationEtcDao;

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
	public void registReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEtcDao.createReservationEtc(reservationIndexVo);
	}

	/**
     * 조회수 증가
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationEtcReadCnt(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEtcDao.updateReservationEtcReadCnt(reservationIndexVo);
	}

	/**
     * 예약등록(추가항목1)
     * @param reservationAddItemVo
     * @throws Exception
     */
	public void registReservationEtcAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception{
		reservationEtcDao.createReservationEtcAddItem(reservationAddItemVo);
	}

	/**
     * 예약등록(추가항목2)
     * @param reservationItemVo
     * @throws Exception
     */
	public void registReservationEtcItem(ReservationItemVo reservationItemVo) throws Exception{
		reservationEtcDao.createReservationEtcItem(reservationItemVo);
	}

	/**
     * 예약수정
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEtcDao.updateReservationEtc(reservationIndexVo);
	}

	/**
     * 예약삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEtcDao.deleteReservationEtc(reservationIndexVo);
	}

	/**
     * 예약상세
     * @param ReservationIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexVo retrieveReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEtcDao.selectReservationEtc(reservationIndexVo);
	}
	
	/**
     * 페이징
     * @param ReservationIndexVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationEtc(ReservationIndexVo reservationIndexVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationIndexVo> result = reservationEtcDao.selectPagReservationEtc(reservationIndexVo);
		int cnt = reservationEtcDao.selectReservationEtcCnt(reservationIndexVo);

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
	public List<ReservationAddItemVo> retrieveListReservationEtcAddItem(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEtcDao.selectListReservationEtcAddItem(reservationIndexVo);
	}

	/**
     * 추가항목 리스트
     * @param ReservationIndexVo
     * @return List<ReservationItemVo>
     * @throws Exception
     */
	public List<ReservationItemVo> retrieveListReservationEtcItem(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEtcDao.selectListReservationEtcItem(reservationIndexVo);
	}

	/**
     * 추가항목삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEtcAddItem(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEtcDao.deleteReservationEtcAddItem(reservationIndexVo);
	}

	/**
     * 추가항목삭제2
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEtcItem(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEtcDao.deleteReservationEtcItem(reservationIndexVo);
	}

	/**
     * 예약접수자 리스트
     * @param ReservationIndexVo
     * @return List<ReservationUserInfoVo>
     * @throws Exception
     */
	public List<ReservationUserInfoVo> retrieveListReservationEtcUserInfo(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEtcDao.selectListReservationEtcUserInfo(reservationIndexVo);
	}

	/**
     * 접수자 등록
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void registReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEtcDao.createReservationEtcUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자 답변 등록
     * @param ReservationUserAnswerVo
     * @throws Exception
     */
	public void registReservationEtcUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception{
		reservationEtcDao.createReservationEtcUserAnswer(reservationUserAnswerVo);
	}

	/**
     * 접수자 상세
     * @param ReservationUserInfoVo
     * @return ReservationUserInfoVo
     * @throws Exception
     */
	public ReservationUserInfoVo retrieveReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEtcDao.selectReservationEtcUserInfo(reservationUserInfoVo);
	}

	public int retrieveReservationEtcUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEtcDao.selectReservationEtcUserInfoDupCnt(reservationUserInfoVo);
	}

	public int retrieveReservationEtcRedundancyDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEtcDao.selectReservationEtcRedundancyDupCnt(reservationUserInfoVo);
	}

	/**
     * 접수자수정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void modifyReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEtcDao.updateReservationEtcUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자답변삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationEtcUserAnswer(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEtcDao.deleteReservationEtcUserAnswer(reservationUserInfoVo);
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
		    	    	fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, "", "reservation.file.upload.path");
		    	    	riImageFileId = egovFileMngService.insertFileInfs(fvoList);
		    	    } else {
		    	    	// 파일수정
		    	    	FileVO fvo = new FileVO();
			    		fvo.setAtchFileId(riImageFileId);
			    		fvo.setFileSn("0");
			    		fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, riImageFileId, "reservation.file.upload.path");
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
    
	public FileListVO uploadUserFile(
			Map<String, MultipartFile> files,
			ReservationUserFileVo reservationUserFileVo) throws Exception {

		List<FileVO> fileVoList = null; //egov에서 제공한 fileVo
		String atchFileId = ""; // 파일식별자
		
		/* 파일객체가 null이면 리턴 */
		if(files == null){
			return null;
		}
		
		fileVoList = egovFileMngUtil.parseFileInf(files, "RUF_", 0, "", "reservationEtcUser.file.upload.path");
    	atchFileId = egovFileMngService.insertFileInfs(fileVoList);
	    
    	FileListVO fileListVO = new FileListVO();
    	fileListVO.setAtchFileId(atchFileId);
    	fileListVO.setFileVoList(fileVoList);
    	
    	return fileListVO;
		
//		if(reservationUserFileVo != null){
//			filePk = reservationUserFileVo.getRufIdx();
//		}
//		
//		
//		
//		if (!files.isEmpty()) {
//			// 첨부파일 업로드
//    	    
//			MultipartFile uploadFile = files.get("file_1");
//    	    if (uploadFile.getSize() > 0) {
//    	    	files.put("file_1", files.get("file_1"));
//	    	    if ("".equals(filePk)) {
//	    	    	// 파일등록
//	    	    	fileVoList = egovFileMngUtil.parseFileInf(files, "RUF_", 0, "", "reservationEtc.user.file.upload.path");
//	    	    	
//	    	    	System.out.println("#####################");
//	    	    	System.out.println("#####################");
//	    	    	System.out.println("#####################fvoList=" + fileVoList);
//	    	    	System.out.println("#####################fvoList=" + fileVoList.toString());
//	    	    	
//	    	    	filePk = egovFileMngService.insertFileInfs(fileVoList);
//	    	    	System.out.println("#####################");
//	    	    	System.out.println("#####################rufIdx" + filePk);
//	    	    	System.out.println("#####################");
//	    	    } else {
//	    	    	// 파일수정
//	    	    	FileVO fvo = new FileVO();
//		    		fvo.setAtchFileId(filePk);
//		    		fvo.setFileSn("0");
//		    		fileVoList = egovFileMngUtil.parseFileInf(files, "RUF_", 0, filePk, "rreservationEtc.user.file.upload.path");
//		    		egovFileMngService.deleteFileInf(fvo);
//				    egovFileMngService.updateFileInfs(fileVoList);
//	    	    }
//    	    }
//	    }
//
//	    if(reservationUserFileVo != null){
//	    	reservationUserFileVo.setRufIdx(filePk);
//	    }
    	
	}
    
    /**
     * 신청자 추첨
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void updateReservationEtcLot(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEtcDao.updateReservationEtcLot(reservationIndexVo);
	}
	
	public void updateReservationEtcLotTemp(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEtcDao.updateReservationEtcLotTemp(reservationIndexVo);
	}

	/**
     * 접수자 삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationEtcUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEtcDao.deleteReservationEtcUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자 추첨확정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void updateReservationEtcUserInfoConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEtcDao.updateReservationEtcUserInfoConfirm(reservationUserInfoVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception {
		return reservationEtcDao.selectDeptCode();
	}
	
	public List<String> retrieveListReservationEtcRandom(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEtcDao.selectListReservationEtcRandom(reservationIndexVo);
	}
	
	public List<String> retrieveListReservationEtcRandomTemp(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEtcDao.selectListReservationEtcRandomTemp(reservationIndexVo);
	}

	public int retrieveReservationEtcCntForLot(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEtcDao.selectReservationEtcCntForLot(reservationUserInfoVo);
	}

	public void insertReservationEtcUserFile(FileVO fileVo, String atchFileId, String riIdx, String rtIdx, String ruIdx) throws Exception {
		
		ReservationUserFileVo reservationUserFileVo = new ReservationUserFileVo();
		reservationUserFileVo.setRiIdx(riIdx);
		reservationUserFileVo.setRtIdx(rtIdx);
		reservationUserFileVo.setRuIdx(ruIdx);
		reservationUserFileVo.setAtchFileId(atchFileId);
		reservationUserFileVo.setFileSn(fileVo.getFileSn());
		reservationUserFileVo.setOrignlFileNm(fileVo.getOrignlFileNm());
		reservationUserFileVo.setStreFileNm(fileVo.getStreFileNm());
		
		reservationEtcDao.insertReservationEtcUserFile(reservationUserFileVo);
		
	}

	public List retrieveListSuervisionOrg(boolean isAdmin,
			List<String> mgrSiteCdList) throws Exception {
		return reservationEtcDao.selectListSuervisionOrg(isAdmin, mgrSiteCdList);
	}
	
	public List retrieveListSuervisionOrgF() throws Exception {
		return reservationEtcDao.selectListSuervisionOrgF();
	}

	

}
