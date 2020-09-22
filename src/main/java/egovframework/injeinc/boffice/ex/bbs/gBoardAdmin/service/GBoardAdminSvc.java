package egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.injeinc.boffice.ex.bbs.boardAdmin.vo.BoardAdminVo;
import egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.vo.GBoardAdminVo;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;


public interface GBoardAdminSvc {
	
	/** 부서 selectBox 조회 */
	public List cdSelectBox() throws Exception;
	
	/** 검색 selectBox Time 조회 */
	public List retrieveTimeSelect(GBoardAdminVo vo) throws Exception;
	
	/** 인바라 List 건수 */
	public int retrieveListGubaraCount(GBoardAdminVo vo) throws Exception;
	
	/** 인바라 List 조회 */
	public List retrieveListGubara(GBoardAdminVo vo) throws Exception;

	/** 인바라 Excel 조회 */
	public List retrieveListExcelGubara(GBoardAdminVo vo) throws Exception;

	/** 인바라 Sub List 조회 */
	public List retrieveSubListGubara(GBoardAdminVo vo) throws Exception;
	
	/** 상세내용 조회수 update */
	public void gubaraBoardCountUpdate(GBoardAdminVo vo) throws Exception;
	
	/** 인바라 첨부파일 갯수 */
	public Map gubaraBoardConfig(GBoardAdminVo vo) throws Exception;

	/** 상세내용 조회 */
	public Map gubaraBoardDetail(GBoardAdminVo vo) throws Exception;
	
	/** 인바라 File List 조회 */
	public List retrieveFileList(GBoardAdminVo vo) throws Exception;

	/** 인바라 답변 체크 */
	public int selectContentClobChk(GBoardAdminVo vo) throws Exception;

	/** 통합답변 조회 */
	public Map selectIntergResp(GBoardAdminVo vo) throws Exception;
	
	/** 게시판 파일 다운로드 */
	public Map boardFileDown(Map param) throws Exception;

	/** 상세내용 조회 이전글/다음글 */
	public Map gubaraBoardDetailPreNext(GBoardAdminVo vo) throws Exception;
	
	/** 상세내용 수정/삭제*/
	public void gubaraBoardUpdate(GBoardAdminVo vo) throws Exception;
	
	/** 상세내용 수정/삭제*/
	public void gubaraBoardUpdate2(GBoardAdminVo vo) throws Exception;
	
	/** 파일 업로드 */
	public void boardRegistFiles(HttpServletRequest request, GBoardAdminVo vo) throws Exception;
	
	/** 파일삭제 */
	public void boardRemoveFiles(Map param) throws Exception;
	
	/** 인바라게시물 자유게시판으로 이관  */
	public void trnasBoard(GBoardAdminVo vo) throws Exception;
	
	/** 인바라게시물 답변미대상민원 처리  */
	public void gubaraBoardNoAnswer(Map param) throws Exception;
	
	/** 인바라 게시글 복사할 게시판 대분류 리스트 */
	public List retrieveCopyBoardTgtGroup(GBoardAdminVo vo) throws Exception;
	
	/** 인바라 게시글 복사할 게시판 소분류 리스트 */
	public List retrieveCopyBoardTgtSecond(String cbUprCd) throws Exception;
	
	/** 인바라 게시글 복사할 게시판 소분류 리스트 */
	public void copyBoard(GBoardAdminVo vo) throws Exception;
	
	/** 인바라 답변 기한 연기 */
    public void gubaraBoardDelayDtMod(GBoardAdminVo vo) throws Exception;
    
	/** bcIdx 생성 */
	public String registBcIdxNo(String cbIdx) throws Exception;
	
	/** 담당부서 지정 */
	public void insertContentMinwonResult(GBoardAdminVo vo) throws Exception;

	/** 담당자 update */
	public void updateContentMinwonResult(GBoardAdminVo vo) throws Exception;

	/** 담당부서 조회 */
	public List retrieveDepList(GBoardAdminVo vo) throws Exception;
	
	/** 담당부서 조회_2 */
	public Map retrieveDepMap(GBoardAdminVo vo) throws Exception;
	
	/** 민원결과조회 */
	public Map selectMwResultMap(GBoardAdminVo vo) throws Exception;
	
	/** 담당부서 삭제 */
	public void deleteDep(GBoardAdminVo vo) throws Exception;

	/** 담당부서 삭제 */
	public void updateDep(GBoardAdminVo vo) throws Exception;
	
	/** 1, 2차대화 저장 */
	public void updateMcContentReg(GBoardAdminVo vo) throws Exception;

	/** 답변초기화 */
	public void updateReplDel(GBoardAdminVo vo) throws Exception;
	
	/** 부서장 select */
	public List selectReplDept(GBoardAdminVo vo) throws Exception;
	
	/** 부서장 select */
	public List selectReplDept2() throws Exception;

	/** 단위업무명1차 select */
	public List selectDw1() throws Exception;

	/** 역대 구청장 */
	public Map selectChiefHistory() throws Exception;

	/** 답변 저장 */
	public void boardLastReplReg(GBoardAdminVo vo) throws Exception;

	/** 일괄 답변 저장 */
	public void boardLastReplRegArr(GBoardAdminVo vo) throws Exception;

	/** 중간답변 부서 추가 */
	public void boardReplReg(GBoardAdminVo vo) throws Exception;
	
	/** 공통코드 조회 */
	public List retrieveListCode(String codeUpr) throws Exception;
	
	/** 통합답변 등록 */
	public void saveIntergResp(MultipartHttpServletRequest request, GBoardAdminVo vo) throws Exception;

	/** 통합답변 수정 */
	public void updateIntergResp(MultipartHttpServletRequest request, GBoardAdminVo vo) throws Exception;
	
	/** mcIdx 생성 */
	public String registMcIdxNo(GBoardAdminVo vo) throws Exception;
	
	/** 인바라게시글 상태값 변경 */
	public void updateGBoardStatus(GBoardAdminVo vo) throws Exception;
	
	/** 인바라 만족도 목록 */
    public List retrieveSurveyList(GBoardAdminVo gBoardAdminVO) throws Exception;
    
    /** 인바라 만족도 엑셀 */
    public List retrieveSurveyExcel(GBoardAdminVo gBoardAdminVO) throws Exception;
    
    /** 인바라 만족도 목록 총갯수 */
    public int retrievePagSurvey(GBoardAdminVo gBoardAdminVO) throws Exception;

    /** 인바라 testupdate*/
	public void updateGBoardCdSubject(GBoardAdminVo vo) throws Exception;

	/** 인바라 처리상태 */
	public void updateBoardStatus(GBoardAdminVo vo) throws Exception;

	public void updateAnsDeadlineDt(GBoardAdminVo gBoardAdminVo) throws Exception;
	
	
}