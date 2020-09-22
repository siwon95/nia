package egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.dao.GBoardAdminDao;
import egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.service.GBoardAdminSvc;
import egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.vo.GBoardAdminVo;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("GBoardAdminSvc")
public class GBoardAdminImpl extends EgovAbstractServiceImpl implements GBoardAdminSvc {
	private static final Logger LOGGER = LoggerFactory.getLogger(GBoardAdminImpl.class);
    
    @Resource(name = "bbsContentIdGnrService")
	private EgovIdGnrService idgenService;
    
    @Resource(name="GBoardAdminDao")
    private GBoardAdminDao gBoardAdminDao;
    
    @Autowired
	private DataSourceTransactionManager transactionManager;
    
    /** 부서 selectBox 조회 */
    public List cdSelectBox() throws Exception {
    	return gBoardAdminDao.cdSelectBox();
    }
    
    /** 검색 selectBox Time 조회 */
    public List retrieveTimeSelect(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.retrieveTimeSelect(gBoardAdminVO);
    }
    
    /** 인바라 List 건수 */
    public int retrieveListGubaraCount(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.selectListGubaraCount(gBoardAdminVO);
    }
    
    /** 인바라 List 조회 */
    public List retrieveListGubara(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.retrieveListGubara(gBoardAdminVO);
    }

    /** 인바라 Excel 조회 */
    public List retrieveListExcelGubara(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.retrieveListExcelGubara(gBoardAdminVO);
    }

    /** 인바라 Sub List 조회 */
    public List retrieveSubListGubara(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.retrieveSubListGubara(gBoardAdminVO);
    }
    
    /** 상세내용 조회수 update */
  	public void gubaraBoardCountUpdate(GBoardAdminVo gBoardAdminVO) throws Exception {
  		gBoardAdminDao.gubaraBoardCountUpdate(gBoardAdminVO);
  	}
    
  	/** 인바라 첨부파일 갯수 */
  	public Map gubaraBoardConfig(GBoardAdminVo gBoardAdminVO) throws Exception {
  		return gBoardAdminDao.gubaraBoardConfig(gBoardAdminVO);
  	}

  	/** 상세내용 조회 */
  	public Map gubaraBoardDetail(GBoardAdminVo gBoardAdminVO) throws Exception {
  		return gBoardAdminDao.gubaraBoardDetail(gBoardAdminVO);
  	}
  	
  	/** 인바라 File List 조회 */
    public List retrieveFileList(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.retrieveFileList(gBoardAdminVO);
    }

    /** 인바라 답변 체크 */
    public int selectContentClobChk(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.selectContentClobChk(gBoardAdminVO);
    }
    
    /** 통합답변 조회 */
	public Map selectIntergResp(GBoardAdminVo gBoardAdminVO) throws Exception {
		return (Map)gBoardAdminDao.selectIntergResp(gBoardAdminVO);
	}

	/** 게시판 파일 다운로드 */
	public Map boardFileDown(Map param) throws Exception {
		return gBoardAdminDao.boardFileDown(param);
	}

  	/** 상세내용 조회 이전글/다음글 */
  	public Map gubaraBoardDetailPreNext(GBoardAdminVo gBoardAdminVO) throws Exception {
  		return gBoardAdminDao.gubaraBoardDetailPreNext(gBoardAdminVO);
  	}
  	
  	/** 상세내용 수정/삭제 */
  	public void gubaraBoardUpdate(GBoardAdminVo gBoardAdminVO) throws Exception {
  		gBoardAdminDao.gubaraBoardUpdate(gBoardAdminVO);
  	}
  	
  	/** 상세내용 수정/삭제 */
  	public void gubaraBoardUpdate2(GBoardAdminVo gBoardAdminVO) throws Exception {
  		gBoardAdminDao.gubaraBoardUpdate2(gBoardAdminVO);
  	}
  	
  	/** 게시판 첨부파일 저장 */
	public void boardRegistFiles(HttpServletRequest request, GBoardAdminVo gBoardAdminVO) throws Exception{
	
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		final List<MultipartFile> files = multiRequest.getFiles("fileTagId");
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String fileStreCours = Message.getMessage("board.file.upload.path");
		String cbFolder = "";
		
		if(gBoardAdminVO != null){
			cbFolder = Integer.toString(gBoardAdminVO.getCbIdx());
		}
		fileStreCours = fileStreCours + cbFolder + "/";
		File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours));
		if (!saveFolder.exists() || saveFolder.isFile()){
		    saveFolder.mkdirs();
		}
		for(MultipartFile file : files){
			if(!file.isEmpty()){            
				String orignlFileNm = file.getOriginalFilename();
				String fileSize = Integer.toString((int)file.getSize());
				String fileExtsn = orignlFileNm.substring(orignlFileNm.lastIndexOf(".") + 1, orignlFileNm.length());
				String streFileNm = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExtsn;	// 전자정부 UTIL 방법
				
				if(gBoardAdminVO != null){
					gBoardAdminVO.setFileStreCours(fileStreCours);
					gBoardAdminVO.setStreFileNm(streFileNm);
					gBoardAdminVO.setOrignlFileNm(orignlFileNm);
					gBoardAdminVO.setFileExtsn(fileExtsn.toLowerCase());
					gBoardAdminVO.setFileSize(fileSize);
				}
				
				// 정상파일일 경우 기존프로세스 
				file.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours + streFileNm)));
				gBoardAdminDao.createFile(gBoardAdminVO);
				
			}
		}
}

  	/** 게시판 파일 삭제 */
	public void boardRemoveFiles(Map param) throws Exception {
		List result = gBoardAdminDao.selectBoardFiles(param);
		
		if(result != null || result.size() > 0) {
			//throw new Exception("Delete File is null");
			String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
			
			gBoardAdminDao.deleteBoardFiles(param);
			
			Map resultMap = null;
			for(int nLoop=0; nLoop<result.size(); nLoop++){
				resultMap = (Map)result.get(nLoop);
				String fileStreCours = (String)resultMap.get("FILE_STRE_COURS");
				String streFileNm = (String)resultMap.get("STRE_FILE_NM");
				
				File fileDelete = new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours + streFileNm));
				
				fileDelete.delete();
					
			}
		}
		
	}
	
  	/** 인바라게시물관리 상세 > 자유게시판,보건소민원 이동  */
  	public void trnasBoard(GBoardAdminVo gBoardAdminVO) throws Exception {
  		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
  		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
  		TransactionStatus status = transactionManager.getTransaction(def);
  		try{
//  			List result = gBoardAdminDao.selectGBoardFiles(gBoardAdminVO);
//
//			if(result != null || result.size() > 0) {
//				//throw new Exception("Delete File is null");
//				String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
//				
//				gBoardAdminDao.deleteGBoardFiles(gBoardAdminVO);
//				
//				GBoardAdminVo resultVo = null;
//				for(int nLoop=0; nLoop<result.size(); nLoop++){
//					resultVo = (GBoardAdminVo)result.get(nLoop);
//					String fileStreCours = resultVo.getFileStreCours();
//					String streFileNm = resultVo.getStreFileNm();
//					
//					File fileDelete = new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours + streFileNm));
//					
//					fileDelete.delete();
//				}
//			}
			
			gBoardAdminVO.setTransBcIdx(idgenService.getNextStringId());
			gBoardAdminDao.insertBoardTrans(gBoardAdminVO);
			/* 자유게시판 이관상태로 변경*/
			gBoardAdminDao.updateBoardTrans(gBoardAdminVO);
	  		//gBoardAdminDao.deleteBoardTrans(gBoardAdminVO);
	  		
			gBoardAdminVO.setCopyBcIdx(gBoardAdminVO.getTransBcIdx());
			gBoardAdminVO.setCopyCbIdx("278");
			gBoardAdminDao.insertCopyBoardFiles(gBoardAdminVO);
  		}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
  		}catch(Exception e){
  			transactionManager.rollback(status);
  			throw e;
  		}
  	}
  	
  	/** 인바라게시물 답변미대상민원 처리  */
  	public void gubaraBoardNoAnswer(Map param) throws Exception {
  		gBoardAdminDao.updateGBoardNoAnswer(param);
  	}
    
  	/** 인바라 게시글 복사할 게시판 대분류 리스트 */
    public List retrieveCopyBoardTgtGroup(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.selectCopyBoardTgtGroup(gBoardAdminVO);
    }
    
    /** 인바라 게시글 복사할 게시판 소분류 리스트 */
    public List retrieveCopyBoardTgtSecond(String cbUprCd) throws Exception {
    	return gBoardAdminDao.selectCopyBoardTgtSecond(cbUprCd);
    }
    
    /** 인바라 게시글 복사 */
    public void copyBoard(GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
  		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
  		TransactionStatus status = transactionManager.getTransaction(def);
  		try{
	    	gBoardAdminDao.insertCopyBoard(gBoardAdminVO);
	    	//gBoardAdminDao.insertCopyBoardFiles(gBoardAdminVO);
  		}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
  		}catch(Exception e){
  			transactionManager.rollback(status);
  			throw e;
  		}
    }
    
    /** 인바라 답변 기한 연기 */
    public void gubaraBoardDelayDtMod(GBoardAdminVo gBoardAdminVO) throws Exception {
    	gBoardAdminDao.updateGBoardDelayDt(gBoardAdminVO);
    }
    
    /** bcIdx 생성 */
    public String registBcIdxNo(String cbIdx) throws Exception {
    	return gBoardAdminDao.selectBcIdxNo(cbIdx);
    }
    
    /** 담당부서 지정 */
    public void insertContentMinwonResult(GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
  		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
  		TransactionStatus status = transactionManager.getTransaction(def);
  		try{
  			gBoardAdminDao.insertContentMinwonResult(gBoardAdminVO);
  		}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
  		}catch(Exception e){
  			transactionManager.rollback(status);
  			throw e;
  		}
    }

    /** 담당자 update */
    public void updateContentMinwonResult(GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		gBoardAdminDao.updateContentMinwonResult(gBoardAdminVO);
    	}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		throw e;
    	}
    }

    /** 담당부서 조회 */
    public List retrieveDepList(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.selectDepList(gBoardAdminVO);
    }

    /** 담당부서 조회_2 */
    public Map retrieveDepMap(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.selectDepMap(gBoardAdminVO);
    }
    
    /** 민원결과조회 */
    public Map selectMwResultMap(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return (Map)gBoardAdminDao.selectMwResultMap(gBoardAdminVO);
    }
    
    /** 담당부서 삭제 */
    public void deleteDep(GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		gBoardAdminDao.deleteDep(gBoardAdminVO);
    	}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		throw e;
    	}
    }

    /** 담당부서 삭제 */
    public void updateDep(GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		gBoardAdminDao.updateDep(gBoardAdminVO);
    	}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		throw e;
    	}
    }

    /** 1, 2차대화 저장 */
    public void updateMcContentReg(GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		gBoardAdminDao.updateMcContentReg(gBoardAdminVO);
    	}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		throw e;
    	}
    }

    /** 답변초기화 */
    public void updateReplDel(GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		gBoardAdminDao.updateReplDel(gBoardAdminVO);
    	}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		throw e;
    	}
    }
    
    /** 부서장 select */
    public List selectReplDept(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.selectReplDept(gBoardAdminVO);
    }
    
    /** 부서장 select */
    public List selectReplDept2() throws Exception {
    	return gBoardAdminDao.selectReplDept2();
    }

    /** 단위업무명1차 select */
    public List selectDw1() throws Exception {
    	return gBoardAdminDao.selectDw1();
    }

    /** 역대 구청장 */
    public Map selectChiefHistory() throws Exception {
    	return (Map)gBoardAdminDao.selectChiefHistory();
    }

    /** 답변 저장 */
    public void boardLastReplReg(GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		gBoardAdminDao.updateLastReplReg(gBoardAdminVO);
    	}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		throw e;
    	}
    }

    /** 일괄 답변 저장 */
    public void boardLastReplRegArr(GBoardAdminVo gBoardAdminVO) throws Exception {
    	gBoardAdminDao.updateLastReplRegArr(gBoardAdminVO);
    }
    
    /** 중간답변 부서 추가 */
	public void boardReplReg(GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		gBoardAdminDao.insertBoardReplReg(gBoardAdminVO);
    	}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		throw e;
    	}
    }
    
    /** 공통코드 조회 */
    public List retrieveListCode(String codeUpr) throws Exception {
    	return gBoardAdminDao.selectCode(codeUpr);
    		
    }
    
    /** 통합답변 등록 */
    public void saveIntergResp(MultipartHttpServletRequest request, GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		boardRegistFiles(request, gBoardAdminVO);
    		gBoardAdminDao.insertIntergResp(gBoardAdminVO);
    		gBoardAdminDao.updateGBoardStatus(gBoardAdminVO);
    	}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		throw e;
    	}
    }

    /** 통합답변 수정 */
    public void updateIntergResp(MultipartHttpServletRequest request, GBoardAdminVo gBoardAdminVO) throws Exception {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		boardRegistFiles(request, gBoardAdminVO);
    		gBoardAdminDao.updateIntergResp(gBoardAdminVO);
    	}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		throw e;
    	}
    }
    
    /** McIdx 생성 */
    public String registMcIdxNo(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return gBoardAdminDao.selectMcIdxNo(gBoardAdminVO);
    }
    
    /** 인바라게시글 상태값 변경 */
	public void updateGBoardStatus(GBoardAdminVo gBoardAdminVO) throws Exception{
   		gBoardAdminDao.updateGBoardStatus(gBoardAdminVO);
	}
	
	/** 인바라 만족도 목록 */
    public List retrieveSurveyList(GBoardAdminVo gBoardAdminVO) throws Exception{
    	return gBoardAdminDao.selectSurveyList(gBoardAdminVO);
    }
    
    /** 인바라 만족도 엑셀 */
    public List retrieveSurveyExcel(GBoardAdminVo gBoardAdminVO) throws Exception{
    	return gBoardAdminDao.selectSurveyExcel(gBoardAdminVO);
    }
    
    /** 인바라 만족도 목록 총갯수 */
    public int retrievePagSurvey(GBoardAdminVo gBoardAdminVO) throws Exception{
    	return gBoardAdminDao.selectPagSurvey(gBoardAdminVO);
    }

	public void updateGBoardCdSubject(GBoardAdminVo gBoardAdminVO) throws Exception {
		gBoardAdminDao.updateGBoardCdSubject(gBoardAdminVO);
		
	}

	/** 인바라 처리상황*/
	public void updateBoardStatus(GBoardAdminVo gBoardAdminVo) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
  		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
  		TransactionStatus status = transactionManager.getTransaction(def);
  		try{
  			gBoardAdminDao.updateBoardStatus(gBoardAdminVo);
  		}catch(RuntimeException e){
    		transactionManager.rollback(status);
    		throw e;
  		}catch(Exception e){
  			transactionManager.rollback(status);
  			throw e;
  		}
		
	}

	public void updateAnsDeadlineDt(GBoardAdminVo gBoardAdminVo)
			throws Exception {
		gBoardAdminDao.updateAnsDeadlineDt(gBoardAdminVo);
	}
	
}