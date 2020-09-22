package egovframework.injeinc.foffice.ex.bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.injeinc.common.files.vo.CmmFilesVo;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;


/**
 * 공통로그인 서비스
 * @author 공통서비스 개발팀 이동열
 */

@Service
public interface BbsFSvc {
	
	/** 공통 게시판 공지사항,이미지게시판 글 내용 조회 */
	public List boardCommonAllList(BbsFVo bbsFVo) throws Exception;
	
	public List boardCommonList(BbsFVo bbsFVo) throws Exception;
	
	public List boardNewRecord(String cbIdx) throws Exception;
	
	public List boardNewRecordCate(BbsFVo bbsFVo) throws Exception;
	
	public List boardNewRecordNoCache(String cbIdx) throws Exception;
	
	public List boardNewRecordMultiNoCache(BbsFVo bbsFVo) throws Exception;
	
	public List boardContentNewRecordNoCache(String cbIdx) throws Exception;
	
	public List boardContentNewRecordMultiNoCache(BbsFVo bbsFVo) throws Exception;
	
	public List boardNewRecordFileNoCache(String cbIdx) throws Exception;
	
	/** 게시판 라벨 리스트 조회 */
	public List boardLabelList(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 컨텐츠  조회할 목록 조회*/
	public List boardContMappList(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 컨텐츠  조회*/
	public List boardContentList(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 컨텐츠  갯수*/
	public int boardcontentCnt(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 등록,수정 컨텐츠 구분 코드 조회*/
	public List boardlabelPropGbnList(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 정보 조회 */
	public BbsFVo boardInfo(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판글 저장 조회 */
	public void boardContentInsert(HttpServletRequest request, BbsFVo bbsFVo) throws Exception;
	
	/** 게시글 상세 조회*/
	public List boardContentDetail(BbsFVo bbsFVo) throws Exception;
	
	/** 조회수 */
	public void boardCountUpdate(BbsFVo bbsFVo) throws Exception;
	
	/** 이전글 다음글*/
	public Map boardPrevNextList(BbsFVo bbsFVo) throws Exception;
	
	/** 게시글 수정 상세 조회*/
	public Map boardUpdateDetail(BbsFVo bbsFVo) throws Exception;
	
	/** 게시글 답글 조회*/
	public Map boardReDetail(BbsFVo bbsFVo) throws Exception;
	
	/** 게시글 수정 */
	public void boardContentUpdate(HttpServletRequest request, BbsFVo bbsFVo) throws Exception;
	
	/** 게시글 수정 */
	public void boardContentDelete(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 카테고리 조회*/
	public List boardCategoryList(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 검색 항목 조회 */
	public List boardSearchList(BbsFVo bbsFVo) throws Exception;
	
	/** 전체조회 20200831-전진형 통합검색 전체조회*/
	public List boardAllSearchList(BbsFVo bbsFVo) throws Exception;
	
	/** 전체조회 20200831-전진형 통합검색 전체카운트조회 */
	public List boardAllSearchCntList(BbsFVo bbsFVo) throws Exception;
	
	/** bcIdx 생성 */
	public int registBcIdxNo() throws Exception;
	
	/** 게시판 파일 조회 */
	public List boardFileList(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 파일 조회 */
	public BbsFVo retrieveThumbNail(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 파일 다운로드 */
	public Map boardFileDown(Map param) throws Exception;
	
	/** 게시판 파일 업로드 */
	public void boardRegistFiles(HttpServletRequest request, BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 파일삭제 */
	public void boardRemoveFiles(Map param) throws Exception;
	
	/** 게시판 이미지 파일 조회 */
	public List boardFileImgList(BbsFVo bbsFVo) throws Exception;
	
	/** 게시판 컨텐츠  조회*/
	/*public List minwonContentList(BbsFVo bbsFVo) throws Exception;*/
	
	/** 민원게시판 컨텐츠  갯수*/
	/*public int minwonContentCnt(BbsFVo bbsFVo) throws Exception;*/
	
	/** 부서지정이 되었는지 체크 */
	public int retrieveMinwonDeptCount(BbsFVo bbsFVo) throws Exception;
	
	/** 민원게시판 민원  조회*/
	public List minwonReList(BbsFVo bbsFVo) throws Exception;
	
	/** 인바라 게시판 최종답변  조회*/
	public List minwonReTotalList(BbsFVo bbsFVo) throws Exception;
	
	/** 인바라 게시판 답변 갯수*/
    public int minwonReCnt(BbsFVo bbsFVo) throws Exception;
    
    /** 인바라 게시판 최종답변 Code*/
    public String minwonReAuditCode(BbsFVo bbsFVo) throws Exception;
    
    /** 인바라 통합답변 사용안함 & 1개부서 일경우*/
	public List minwonReDeptList(BbsFVo bbsFVo) throws Exception;
	
	/** 민원게시판 항목  조회*/
	public List itemList(BbsFVo bbsFVo) throws Exception;
	
	/** 공지,공고/공시 서브도메인 CODE_NAME */
	public String deptCodeName(String code) throws Exception;
	
	/** 구청장에 바란다 만족도 조사  */
	public void boardMcSurvey(Map param) throws Exception;
	
	/** 민원 답변 */
	public Map boardMinwonDetail(BbsFVo bbsFVo) throws Exception;
	
	/** 구청장실 메인(이번주영상) */
	public BbsFVo boardVideo(BbsFVo bbsFVo) throws Exception;
	
	/** 구청장실 메인(은희씨와의한컷) */
	public BbsFVo boardPhoto(BbsFVo bbsFVo) throws Exception;
	
	/** 본인 게시물확인 */
	public String retrieveRegId(HashMap<Object, Object> map) throws Exception;

	/** 게시판 조회2 */
	public List boardFileList2(BbsFVo bbsFVo) throws Exception;

	public String selectRssUseYn(String cbIdx) throws Exception;
	
	public List selectMyBbs(String regId) throws Exception;
	
	/** 인바라 삭제
	 * @return */
	/*public Map gubboardDelete(Map param) throws Exception;*/

	public BbsFVo gubboardDelete(BbsFVo bbsFVo) throws Exception;

	/** 인바라 취하 */

	public void boardContentWithdrawal(BbsFVo bbsFVo) throws Exception;
	
	public int bbsRecommentCount(BbsFVo bbsFVo) throws Exception;
	
	public void bbsRecommentInsert(BbsFVo bbsFVo) throws Exception;
	
	public List boardNewContentList() throws Exception;
	
	public List boardNewNoticeList() throws Exception;
	
	public int boardMediaPlayCnt(BbsFVo bbsFVo) throws Exception;
	
	public void boardMediaPlayInsert(BbsFVo bbsFVo) throws Exception;
	
	public Map boardMediaPlayInfo(BbsFVo bbsFVo) throws Exception;
	
	/*20200910 page index test*/
	public Map selectNewPageIndex(BbsFVo bbsFVo) throws Exception;
}