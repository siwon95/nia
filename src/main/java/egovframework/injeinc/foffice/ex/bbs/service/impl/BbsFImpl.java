package egovframework.injeinc.foffice.ex.bbs.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.googlecode.ehcache.annotations.Cacheable;

import egovframework.cmm.EgovMessageSource;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.bbs.dao.BbsFDao;
import egovframework.injeinc.foffice.ex.bbs.service.BbsFSvc;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import scala.collection.parallel.ParIterableLike.Forall;

/**
 * 공통로그인 서비스 
 * @author 공통서비스 개발팀 이동열
 */


@Service("BbsFSvc")
public class BbsFImpl extends EgovAbstractServiceImpl implements BbsFSvc {

	/** ID Generation */
    @Resource(name="bbsContentIdGnrService")    
    private EgovIdGnrService egovIdGnrService;
    
    @Resource(name="BbsFDao")
    private BbsFDao bbsFDao;
    
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Autowired
	private DataSourceTransactionManager transactionManager;
    
	  	/** 공통 게시판 공지사항,이미지게시판 글 내용 조회 */
		@SuppressWarnings("rawtypes")
		public List boardCommonAllList(BbsFVo bbsFVo) throws Exception {
		  return bbsFDao.boardCommonAllList(bbsFVo);
		}
		
		/** 공통 게시판 공지사항,이미지게시판 글 내용 조회 */
		@SuppressWarnings("rawtypes")
		public List boardCommonList(BbsFVo bbsFVo) throws Exception {
		  return bbsFDao.boardCommonList(bbsFVo);
		}
		
		@Cacheable(cacheName = "mainCache")
		public List boardNewRecord(String cbIdx) throws Exception {
		  return bbsFDao.boardNewRecord(cbIdx);
		}
		
		@Cacheable(cacheName = "mainCache")
		public List boardNewRecordCate(BbsFVo bbsFVo) throws Exception {
		  return bbsFDao.boardNewRecordCate(bbsFVo);
		}
		
		@SuppressWarnings("rawtypes")
		public List boardNewRecordNoCache(String cbIdx) throws Exception {
		  return bbsFDao.boardNewRecord(cbIdx);
		}
		
		@SuppressWarnings("rawtypes")
		public List boardNewRecordMultiNoCache(BbsFVo bbsFVo) throws Exception {
		  return bbsFDao.boardNewRecordMulti(bbsFVo);
		}
		
		@SuppressWarnings("rawtypes")
		public List boardContentNewRecordNoCache(String cbIdx) throws Exception {
		  return bbsFDao.boardContentNewRecord(cbIdx);
		}
		
		@SuppressWarnings("rawtypes")
		public List boardContentNewRecordMultiNoCache(BbsFVo bbsFVo) throws Exception {
		  return bbsFDao.boardContentNewRecordMulti(bbsFVo);
		}
		
		@SuppressWarnings("rawtypes")
		public List boardNewRecordFileNoCache(String cbIdx) throws Exception {
		  return bbsFDao.boardNewRecordFile(cbIdx);
		}
	  
	  	/** 게시판 라벨 리스트 조회 */
		@SuppressWarnings("rawtypes")
		public List boardLabelList(BbsFVo bbsFVo) throws Exception {
		  return bbsFDao.boardLabelList(bbsFVo);
		}
		
		/** 게시판 컨텐츠  조회할 목록 조회*/
		@SuppressWarnings("rawtypes")
		public List boardContMappList(BbsFVo bbsFVo) throws Exception {
		  return bbsFDao.boardContMappList(bbsFVo);
		}
		
		/** 게시판 컨텐츠  조회*/
		@SuppressWarnings("rawtypes")
		public List boardContentList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardContentList(bbsFVo);
		}
		
		/** 게시판 컨텐츠  갯수*/
		public int boardcontentCnt(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardcontentCnt(bbsFVo);
		}
		
		/** 게시판 등록,수정 컨텐츠 구분 코드 조회*/
		@SuppressWarnings("rawtypes")
		public List boardlabelPropGbnList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardlabelPropGbnList(bbsFVo);
		}
		
		/** 게시판 정보 조회 */
		public BbsFVo boardInfo(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardInfo(bbsFVo);
		}
		/** 최근 추가된 게시판 정보 조회 */
		@SuppressWarnings("rawtypes")
		public List boardNewContentList() throws Exception {
			return bbsFDao.boardNewContentList();
		}
		/** 최근 추가된 공지게시판 정보 조회 */
		@SuppressWarnings("rawtypes")
		public List boardNewNoticeList() throws Exception {
			return bbsFDao.boardNewNoticeList();
		}
		/** 게시판글 저장 조회 */
		public void boardContentInsert(HttpServletRequest request, BbsFVo bbsFVo) throws Exception {
			if(bbsFVo != null){
				bbsFVo.setBcIdx(egovIdGnrService.getNextIntegerId());
			}
  			
  			String clobCont = bbsFVo == null ? "" : EgovStringUtil.isNullToString(bbsFVo.getClobCont());
  			
  			if(!"".equals(clobCont) ){
  				if(bbsFVo != null){
  					bbsFVo.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
  				}
  			}
  			
  			bbsFDao.boardContentInsert(bbsFVo);
  			System.out.println("bbsFVo() : "+bbsFVo);
  			System.out.println("bbsFVo.getTempname() : "+bbsFVo.getTempname());
  			if(bbsFVo.getTempname() != null && !bbsFVo.getTempname().equals("")){
  				boardRegistMultiFiles(request, bbsFVo);
  			}else{
  				boardRegistFiles(request, bbsFVo);
  			}
		}
		
		/** 게시글 상세 조회*/
		@SuppressWarnings("rawtypes")
		public List boardContentDetail(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardContentDetail(bbsFVo);
		}
		
		/** 조회수 */
		public void boardCountUpdate(BbsFVo bbsFVo) throws Exception {
			 bbsFDao.boardCountUpdate(bbsFVo);
		}
		
		/** 이전글 다음글 */
		@SuppressWarnings("rawtypes")
		public Map boardPrevNextList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardPrevNext(bbsFVo);
		}

		/** 게시글 수정 상세 조회*/
		@SuppressWarnings("rawtypes")
		public Map boardUpdateDetail(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardUpdateDetail(bbsFVo);
		}
		
		/** 게시글 답글 조회*/
		@SuppressWarnings("rawtypes")
		public Map boardReDetail(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardReDetail(bbsFVo);
		}

		/** 게시글 수정 */
		public void boardContentUpdate(HttpServletRequest request, BbsFVo bbsFVo) throws Exception {
  			String clobCont = bbsFVo == null ? "" : EgovStringUtil.isNullToString(bbsFVo.getClobCont());
  			
  			if(!"".equals(clobCont) ){
  				if(bbsFVo != null){
  					bbsFVo.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
  				}
  			}
  			
  			bbsFDao.boardContentUpdate(bbsFVo);
  			
  			
  			if(bbsFVo.getTempname() != null && !bbsFVo.getTempname().equals("")){
  				boardRegistMultiFiles(request, bbsFVo);
  			}else{
  				boardRegistFiles(request, bbsFVo);
  			}
  			
  			//boardRegistFiles(request, bbsFVo);			
  			
		}
		
		/** 게시글 삭제 */
		public void boardContentDelete(BbsFVo bbsFVo) throws Exception {
			bbsFDao.boardContentDelete(bbsFVo);
		}
		
		/** 게시판 카테고리 조회*/
		@SuppressWarnings("rawtypes")
		public List boardCategoryList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardCategoryList(bbsFVo);
		}
		
		/** 게시판 검색 항목 조회 */
		@SuppressWarnings("rawtypes")
		public List boardSearchList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardSearchList(bbsFVo);
		}
		
		/** 게시판 첨부파일 저장 */
		public void boardRegistFiles(HttpServletRequest request, BbsFVo bbsFVo) throws Exception {
			
			final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			
			final List<MultipartFile> files = multiRequest.getFiles("fileTagId");
			
			String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
			String fileStreCours = Message.getMessage("board.file.upload.path");
			String cbFolder = bbsFVo == null ? "" : Integer.toString(bbsFVo.getCbIdx());
			
			fileStreCours = fileStreCours + cbFolder + "/";
			File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours));
			  if (!saveFolder.exists() || saveFolder.isFile()){
				  saveFolder.mkdirs();
			  }			
			for(MultipartFile file : files){
				if(!file.isEmpty()){            
					String orignlFileNm = file.getOriginalFilename();
					String fileSize = Integer.toString((int)file.getSize());
					System.out.println("getFileMaxSize : "+bbsFVo.getFileMaxSize());
					if((int)file.getSize() > Integer.parseInt(bbsFVo.getFileMaxSize()) * 1024 * 1024){				//파일 용량제한 초과 확인
						Map FileRmvMap = new HashMap<String, String>();
						FileRmvMap.put("bcIdx", bbsFVo.getBcIdx());
						FileRmvMap.put("cbIdx", bbsFVo.getCbIdx());
						FileRmvMap.put("regId", bbsFVo.getRegId());
						boardRemoveFiles(FileRmvMap);
						throw new FileUploadException();
					}else{
						String fileExtsn = orignlFileNm.substring(orignlFileNm.lastIndexOf(".") + 1, orignlFileNm.length());
						String streFileNm = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExtsn;	// 전자정부 UTIL 방법
						if(bbsFVo != null){ 
							bbsFVo.setFileStreCours(fileStreCours);
							bbsFVo.setStreFileNm(streFileNm);
							bbsFVo.setOrignlFileNm(orignlFileNm);
							bbsFVo.setFileExtsn(fileExtsn.toLowerCase());
							bbsFVo.setFileSize(fileSize);
						}
						
						file.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours + streFileNm)));
						bbsFDao.createFile(bbsFVo);
						//ftp 업로드
	//					ftpClient.put(webRootPath + fileStreCours, streFileNm, rootPath + fileStreCours, streFileNm);
					}
				}
			}
			
		}
		
		/** 게시판 첨부파일 저장 */
		public void boardRegistMultiFiles(HttpServletRequest request, BbsFVo bbsFVo) throws Exception {
						
			String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
			String fileStreCours = Message.getMessage("board.file.upload.path");
			String cbFolder = bbsFVo == null ? "" : Integer.toString(bbsFVo.getCbIdx());
			
			fileStreCours = fileStreCours + cbFolder + "/";
			File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours));
			  if (!saveFolder.exists() || saveFolder.isFile()){
				  saveFolder.mkdirs();
			  }
			System.out.println(bbsFVo.getTempname());
			System.out.println(bbsFVo.getTempsname());
			System.out.println(bbsFVo.getTempsize());
			
			String tempname[] = bbsFVo.getTempname().split("[|]");
			String tempsname[] = bbsFVo.getTempsname().split("[|]");
			String tempsize[] = bbsFVo.getTempsize().split("[|]");
			System.out.println("----------");
			for(int i=0; i < tempname.length; i++){				
				File tempfile = new File(rootPath+fileStreCours+tempsname[i]);
				String fileExtsn = tempsname[i].substring(tempsname[i].lastIndexOf(".") + 1, tempsname[i].length());
				if(Integer.parseInt(tempsize[i]) > Integer.parseInt(bbsFVo.getFileMaxSize()) * 1024 * 1024){
					Map FileRmvMap = new HashMap<String, String>();
					FileRmvMap.put("bcIdx", bbsFVo.getBcIdx());
					FileRmvMap.put("cbIdx", bbsFVo.getCbIdx());
					FileRmvMap.put("regId", bbsFVo.getRegId());
					boardRemoveFiles(FileRmvMap);
					throw new FileUploadException();
				}else{
					if(bbsFVo != null){ 
						System.out.println("-----------------------");
						bbsFVo.setFileStreCours(fileStreCours);
						bbsFVo.setStreFileNm(tempsname[i]);
						bbsFVo.setOrignlFileNm(tempname[i]);
						bbsFVo.setFileExtsn(fileExtsn.toLowerCase());
						bbsFVo.setFileSize(tempsize[i]);
						System.out.println("-----------------------");
					}		
					tempfile.renameTo(new File(rootPath + fileStreCours + tempsname[i]));
					System.out.println("-----------------------");
					bbsFDao.createFile(bbsFVo);
					System.out.println("-----------------------");
				}				
			}						
		}
		
		/** 게시판 파일 삭제 */
		@SuppressWarnings({ "rawtypes", "null" })
		public void boardRemoveFiles(Map param) throws Exception {
			List result = bbsFDao.selectBoardFiles(param);
			
			if(result != null || result.size() > 0) {
				//throw new Exception("Delete File is null");
				String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
				
				bbsFDao.deleteBoardFiles(param);
				
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

		 /** cbIdx select */
	    public int registBcIdxNo() throws Exception {
			return bbsFDao.selectBcIdxNo();
	    }

	    /** 게시판 파일 조회*/
		public List boardFileList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardFileList(bbsFVo);
		}
		
		/** 썸네일 이미지 조회 */
		public BbsFVo retrieveThumbNail(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.selectThumbNail(bbsFVo);
		}
		
		/** 게시판 파일 다운로드 */
		public Map boardFileDown(Map param) throws Exception {
			return bbsFDao.boardFileDown(param);
		}
		
		/** 게시판 이미지 파일 조회*/
		public List boardFileImgList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardFileImgList(bbsFVo);
		}
		
		/** 게시판 컨텐츠  조회할 목록 조회*/
		/*public List minwonContentList(BbsFVo bbsFVo) throws Exception {
		  return bbsFDao.minwonContentList(bbsFVo);
		}*/
		
		/** 민원게시판 컨텐츠  갯수*/
		/*public int minwonContentCnt(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.minwonContentCnt(bbsFVo);
		}*/
		
		/** 부서지정이 되었는지 체크 */
		public int retrieveMinwonDeptCount(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.selectMinwonDeptCount(bbsFVo);
		}
		
		/** 민원게시판 민원 조회*/
		public List minwonReList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.minwonReList(bbsFVo);
		}
		
		/** 인바라 게시판 최종답변  조회*/
		public List minwonReTotalList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.minwonReTotalList(bbsFVo);
		}
		
		/** 인바라 게시판 답변 갯수*/
	    public int minwonReCnt(BbsFVo bbsFVo) throws Exception {
	    	return bbsFDao.minwonReCnt(bbsFVo);
	    }
	    
	    /** 인바라 게시판 최종답변 Code*/
	    public String minwonReAuditCode(BbsFVo bbsFVo) throws Exception {
	    	return bbsFDao.minwonReAuditCode(bbsFVo);
	    }
	    
	    /** 인바라 통합답변 사용안함 & 1개부서 일경우*/
		public List minwonReDeptList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.minwonReDeptList(bbsFVo);
		}
		
		/** 민원게시판 항목 조회*/
		public List itemList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.itemList(bbsFVo);
		}
		
		/** 공지,공고/공시 서브도메인 CODE_NAME */
		public String deptCodeName(String code) throws Exception {
			return bbsFDao.selectDeptCodeName(code);
		}
		
		/** 구청장에 바란다 만족도 조사  */
		public void boardMcSurvey(Map param) throws Exception{
			bbsFDao.updateBoardMcSurvey(param);
		}
		
		/** 민원답변 */
		public Map boardMinwonDetail(BbsFVo bbsFVo) throws Exception{
			return bbsFDao.selectBoardMinwonDetail(bbsFVo);
		}
		
		/** 구청장메인(이번주영상) */
		public BbsFVo boardVideo(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.selectBoardVideo(bbsFVo);
		}
		
		/** 구청장메인(은희씨와의 한컷)*/
		public BbsFVo boardPhoto(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.selectBoardPhoto(bbsFVo);
		}

		/** 본인게시물 확인*/
		public String retrieveRegId(HashMap<Object, Object> map) throws Exception {
			return bbsFDao.selectRegId(map);
		}

		 /** 게시판 파일 조회2*/
		public List boardFileList2(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardFileList2(bbsFVo);
		}
		
		public String selectRssUseYn(String cbIdx) throws Exception{
			return bbsFDao.selectRssUseYn(cbIdx);
		}

		/** 게시판 검색 항목 조회 */
		@SuppressWarnings("rawtypes")
		public List selectMyBbs(String regId) throws Exception {
			return bbsFDao.selectMyBbs(regId);
		}
		
		/** 전체조회 20200831-전진형 통합검색 전체조회 */
		@SuppressWarnings("rawtypes")
		public List boardAllSearchList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardAllSearchList(bbsFVo);
		}
		/** 전체조회 20200831-전진형 통합검색 전체카운트조회 */
		@SuppressWarnings("rawtypes")
		public List boardAllSearchCntList(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardAllSearchCntList(bbsFVo);
		}
		
		public int bbsRecommentCount(BbsFVo bbsFVo) throws Exception{
			return bbsFDao.bbsRecommentCount(bbsFVo);
		}
		
		public void bbsRecommentInsert(BbsFVo bbsFVo) throws Exception{
			bbsFDao.bbsRecommentInsert(bbsFVo);
		}
		
		/** 인바라 삭제*/
	/*	@SuppressWarnings("rawtypes")
		public Map gubboardDelete(Map param) throws Exception {
			return bbsFDao.seleteGubboardDelete(param);
		}*/

		public BbsFVo gubboardDelete(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.seleteGubboardDelete1(bbsFVo);
		}

		public void boardContentWithdrawal(BbsFVo bbsFVo) throws Exception {
			bbsFDao.updateboardContentWithdrawal(bbsFVo);
		}
		@SuppressWarnings("rawtypes")
		public int boardMediaPlayCnt(BbsFVo bbsFVo) throws Exception{
			return bbsFDao.boardMediaPlayCnt(bbsFVo);
		}
		
		public void boardMediaPlayInsert(BbsFVo bbsFVo) throws Exception{
			bbsFDao.boardMediaPlayInsert(bbsFVo);
		}
		@SuppressWarnings("rawtypes")
		public Map boardMediaPlayInfo(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.boardMediaPlayInfo(bbsFVo);
		}	
		
		/*20200910 page index test*/
		@SuppressWarnings("rawtypes")
		public Map selectNewPageIndex(BbsFVo bbsFVo) throws Exception {
			return bbsFDao.selectNewPageIndex(bbsFVo);
		}	
}