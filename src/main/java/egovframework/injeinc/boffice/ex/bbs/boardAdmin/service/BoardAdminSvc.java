package egovframework.injeinc.boffice.ex.bbs.boardAdmin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import egovframework.injeinc.boffice.ex.bbs.boardAdmin.vo.BoardAdminVo;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;


public interface BoardAdminSvc {
	
	/** 검색 first selectBox 조회 */
	public List retrieveFirstSelect(BoardAdminVo vo) throws Exception;
	
	/** 검색 second selectBox 조회 */
	public HashMap<String, Object> retrieveSecondSelect(HashMap<String, String> param) throws Exception;
	
	/** 검색 selectBox Time 조회 */
	public List retrieveTimeSelect(BoardAdminVo vo) throws Exception;
	
	/** 게시판 정보 조회 */
	public BoardAdminVo boardInfo(BoardAdminVo bbsFVo) throws Exception;
	
	/** 게시판 라벨 리스트 조회 */
	public List boardLabelList(BoardAdminVo vo) throws Exception;
	
	/** 게시판 컨텐츠  조회할 목록 조회*/
	public List boardContMappList(BoardAdminVo vo) throws Exception;
	
	/** 게시판 검색 항목 조회 */
	public List boardSearchList(BoardAdminVo vo) throws Exception;
	
	/** 게시판 컨텐츠  조회*/
	public List boardContentList(BoardAdminVo vo) throws Exception;

	/** 민원 게시판 컨텐츠  조회*/
	public List minwonContentList(BoardAdminVo vo) throws Exception;
	
	/** 민원게시판 민원  조회*/
	public List minwonReList(BoardAdminVo vo) throws Exception;
	
	/** 민원게시판 항목  조회*/
	public List itemList(BoardAdminVo vo) throws Exception;
	
	/** 게시판 컨텐츠  갯수*/
	public int boardcontentCnt(BoardAdminVo vo) throws Exception;
	
	/** 민원게시판 컨텐츠  갯수*/
	public int minwonContentCnt(BoardAdminVo vo) throws Exception;
	
	/** 게시판 글 삭제*/
	public void removeBoard(BoardAdminVo vo) throws Exception;

	/** 게시판 글 승인*/
	public void modifyBoard(BoardAdminVo vo) throws Exception;
	
	/** 상세내용 조회 */
	public List retrieveBoard(BoardAdminVo vo) throws Exception;

	/** 상세내용 조회 */
	public Map boardDetail(BoardAdminVo vo) throws Exception;
	
	/** 게시판 파일 조회 */
	public List boardFileList(BoardAdminVo vo) throws Exception;
	
	/** 게시판 이미지 파일 조회 */
	public List boardFileImgList(BoardAdminVo vo) throws Exception;

	/** 상세내용 파일 조회 */
//	public List retrieveBoardFile(BoardAdminVo vo) throws Exception;

	/** 상세내용 파일 다운로드 조회 */
	public List<BoardAdminVo> retrieveBoardFile(Map<String, Object> param) throws Exception;

	/** 상세내용 조회수 update */
	public void boardCountUpdate(BoardAdminVo vo) throws Exception;
	
	/** 게시판 글쓰기 화면생성 */
	public List retrieveBoardlabelPropGbnList(BoardAdminVo vo) throws Exception;
	
	/** 게시판 글쓰기 카테고리 조회 */
	public List retrieveBoardCategoryList(BoardAdminVo vo) throws Exception;
	
	/** bcIdx 생성 */
	public Integer registBcIdxNo(BoardAdminVo vo) throws Exception;

	/** cbIdx select */
	public String boardCbIdxSelect(String ssName) throws Exception;
	
	/** 게시판 글 저장 */
	public void boardContentInsert(BoardAdminVo vo) throws Exception;
	
	/** 게시글 수정 */
	public void boardContentUpdate(BoardAdminVo vo) throws Exception;

	/** 게시판 글 저장 */
	public void boardContentMinwonResultInsert(BoardAdminVo vo) throws Exception;
	
	/** 게시판 파일 업로드 */
	public void boardRegistFiles(HttpServletRequest request, BoardAdminVo vo) throws Exception;

	/** 게시글 수정 상세 조회*/
	public Map boardContentDetail(BoardAdminVo vo) throws Exception;
	
	/** 게시글 공지설정여부*/
	public void modifyBoardNoti(BoardAdminVo vo) throws Exception;

	/** 게시글 승인 */
	public void modifyBoardApprYn(BoardAdminVo vo) throws Exception;

	/** 게시글 승인 */
	public void modifyBoardmwDelYn(BoardAdminVo vo) throws Exception;
	
	/** 게시글 민원 삭제 사유리스트 */
	public List retrieveContentDelRsn() throws Exception;
	
	/** 게시글 민원 삭제 */
	public void removeMw(Map param)throws Exception;
	
	/** 게시글 민원 복구 */
	public void recoveryMw(Map param)throws Exception;
	
	/** 게시글 이관 */
	public void registTrans(Map param)throws Exception;
	
	
	/** 총 건수 조회 */
	//public int retrievePagBoard(BoardAdminVo vo) throws Exception;
	
	/** 리스트 조회 */
	//public List retrieveListBoardAdmin(BoardAdminVo vo) throws Exception;
}