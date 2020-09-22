package egovframework.injeinc.boffice.ex.bbs.boardAdmin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.bbs.boardAdmin.vo.BoardAdminVo;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrVo;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;

@Repository("BoardAdminDao")
public class BoardAdminDao extends EgovAbstractMapper {
    
    /** first 검색 selectBox 조회 */
    public List retrieveFirstSelect(BoardAdminVo boardAdminVO) throws Exception {
        return selectList("BoardAdminDao.firstSelectBoardAdmin", boardAdminVO);
    }
    
    /** second 검색 selectBox 조회 */
    @SuppressWarnings("unchecked")
	public List<Object> retrieveSecondSelect(HashMap<String, String> param) throws Exception {
		return (List<Object>) selectList("BoardAdminDao.secondSelectBoardAdmin", param);
	}

    /** 검색 selectBox Time 조회 */
    @SuppressWarnings("unchecked")
    public List retrieveTimeSelect(BoardAdminVo boardAdminVO) throws Exception {
    	return selectList("BoardAdminDao.selectBoardSearchTime", boardAdminVO);
    }
    
    /** 게시판 정보 조회 */
	@SuppressWarnings("unchecked")
    public BoardAdminVo boardInfo(BoardAdminVo boardAdminVO) throws Exception {
    	return (BoardAdminVo)selectOne("BoardAdminDao.selectBoardInfo", boardAdminVO);
    }
    
    /** 게시판 라벨 리스트 조회*/
    public List boardLabelList(BoardAdminVo boardAdminVO) throws Exception {
        return selectList("BoardAdminDao.selectBoardLabelList", boardAdminVO);
    }
    
    /** 게시판 컨텐츠  조회할 목록 조회*/
    public List boardContMappList(BoardAdminVo boardAdminVO) throws Exception {
        return selectList("BoardAdminDao.selectBoardContMappList", boardAdminVO);
    }
    
    /** 게시판 검색 항목 조회 */
	@SuppressWarnings("unchecked")
    public List boardSearchList(BoardAdminVo boardAdminVO) throws Exception {
    	return selectList("BoardAdminDao.selectboardSearchList", boardAdminVO);
    }
    
    /** 게시판 컨텐츠  조회*/
    public List boardContentList(BoardAdminVo boardAdminVO) throws Exception {
    	return selectList("BoardAdminDao.selectBoardContentList", boardAdminVO);
    }

    /** 민원 게시판 컨텐츠  조회*/
    public List minwonContentList(BoardAdminVo boardAdminVO) throws Exception {
    	return selectList("BoardAdminDao.selectMinwonContentList", boardAdminVO);
    }
    
    /** 민원 게시판 민원  조회*/
	@SuppressWarnings("unchecked")
    public List minwonReList(BoardAdminVo boardAdminVO) throws Exception {
    	return selectList("BoardAdminDao.selectMinwonReList", boardAdminVO);
    }
	
	/** 민원 게시판 항목  조회*/
	@SuppressWarnings("unchecked")
	public List itemList(BoardAdminVo boardAdminVO) throws Exception {
		return selectList("BoardAdminDao.selectItemList", boardAdminVO);
	}
    
    /** 게시판 컨텐츠  갯수*/
    public int boardcontentCnt(BoardAdminVo boardAdminVO) throws Exception {
    	return (Integer)selectOne("BoardAdminDao.selectBoardContentCnt", boardAdminVO);
    }
    
    /** 민원게시판 컨텐츠  갯수*/
	@SuppressWarnings("unchecked")
    public int minwonContentCnt(BoardAdminVo boardAdminVO) throws Exception {
    	return (Integer)selectOne("BoardAdminDao.selectMinwonContentCnt", boardAdminVO);
    }
    
    /** 게시판 글 삭제 */
  	public void deleteBoard(BoardAdminVo boardAdminVO) throws Exception {
  		update("BoardAdminDao.deleteBoard", boardAdminVO);
  	}

  	/** 게시판 글 승인 */
  	public void updateBoard(BoardAdminVo boardAdminVO) throws Exception {
  		update("BoardAdminDao.updateBoard", boardAdminVO);
  	}
  	
  	/** 상세내용 조회 */
	public List selectBoard(BoardAdminVo boardAdminVO) throws Exception {
		return selectList("BoardAdminDao.selectBoardView", boardAdminVO);
	}

	/** 상세내용 조회 */
	public Map boardDetail(BoardAdminVo boardAdminVO) throws Exception {
		return (Map)selectOne("BoardAdminDao.selectboardDetail", boardAdminVO);
	}
	
	/** 게시판 파일 조회 */
	@SuppressWarnings("unchecked")
    public List boardFileList(BoardAdminVo boardAdminVO) throws Exception {
    	return selectList("BoardAdminDao.selectFileList", boardAdminVO);
    }
	
	/** 게시판 이미지 파일 조회 */
	@SuppressWarnings("unchecked")
    public List boardFileImgList(BoardAdminVo boardAdminVO) throws Exception {
    	return selectList("BoardAdminDao.selectImgFileList", boardAdminVO);
    }

	/** 상세내용 파일 조회 */
	/*public List selectBoardFile(BoardAdminVo boardAdminVO) throws Exception {
		return selectList("BoardAdminDao.selectBoardFileView", boardAdminVO);
	}*/
	
	/** 상세내용 파일 조회 */
	public List<BoardAdminVo> selectBoardFile(Map<String, Object> param) throws Exception {
		return selectList("BoardAdminDao.selectBoardFileView", param);
	}

	/** 상세내용 조회수 update */
	public void updateboardCount(BoardAdminVo boardAdminVO) throws Exception {
		update("BoardAdminDao.updateboardCount", boardAdminVO);
	}
	
	/** 게시판 글쓰기 화면생성 */
	public List boardlabelPropGbnList(BoardAdminVo boardAdminVO) throws Exception {
		return selectList("BoardAdminDao.selectboardlabelPropGbnList", boardAdminVO);
	}
	
	/** 게시판 글쓰기 카테고리 조회 */
	public List selectBoardCategoryList(BoardAdminVo boardAdminVO) throws Exception {
		return selectList("BoardAdminDao.selectBoardCategoryList", boardAdminVO);
	}
	
	/** bcIdx 생성 */
  	public Integer selectBcIdxNo(BoardAdminVo boardAdminVO) throws Exception {
  		return  (Integer)selectOne("BoardAdminDao.selectBcIdxNo", boardAdminVO);
  	}

  	/** cbIdx select */
  	public String boardCbIdxSelect(String ssName) throws Exception {
  		return  (String)selectOne("BoardAdminDao.boardCbIdxSelect", ssName);
  	}
	
	/** 게시판 글 저장 */
	@SuppressWarnings("unchecked")
    public void boardContentInsert(BoardAdminVo boardAdminVO) throws Exception {
    	insert("BoardAdminDao.insertBoardContent", boardAdminVO);
    }
	
	/** 게시글 수정  */
	@SuppressWarnings("unchecked")
    public void boardContentUpdate(BoardAdminVo boardAdminVO) throws Exception {
		update("BoardAdminDao.boardContentUpdate", boardAdminVO);
    }

	/** 게시판 글 저장 */
	@SuppressWarnings("unchecked")
	public void boardContentMinwonResultInsert(BoardAdminVo boardAdminVO) throws Exception {
		insert("BoardAdminDao.boardContentMinwonResultInsert", boardAdminVO);
	}
	
	/** 게시판 첨부파일 저장 */
	public void createFile(BoardAdminVo boardAdminVO) throws Exception{
		insert("BoardAdminDao.insertFile", boardAdminVO);
	}
	
	/** 게시글 수정 상세 조회 */
	@SuppressWarnings("unchecked")
    public Map boardContentDetail(BoardAdminVo boardAdminVO) throws Exception {
    	return (Map)selectOne("BoardAdminDao.selectContentDetail", boardAdminVO);
    }

	/** 게시글 수정 상세 조회 */
	@SuppressWarnings("unchecked")
	public void updateBoardNoti(BoardAdminVo boardAdminVO) throws Exception {
		update("BoardAdminDao.updateBoardNoti", boardAdminVO);
	}

	/** 게시글 승인 */
	@SuppressWarnings("unchecked")
	public void updateBoardApprYn(BoardAdminVo boardAdminVO) throws Exception {
		update("BoardAdminDao.updateBoardApprYn", boardAdminVO);
	}

	/** 게시글 승인 */
	@SuppressWarnings("unchecked")
	public void modifyBoardmwDelYn(BoardAdminVo boardAdminVO) throws Exception {
		update("BoardAdminDao.modifyBoardmwDelYn", boardAdminVO);
	}
	
	/** 게시글 민원 삭제 사유리스트 */
	@SuppressWarnings("unchecked")
	public List retrieveContentDelRsn() throws Exception {
		return selectList("BoardAdminDao.selectContentDelRsn","");
	}
	
	/** 게시글 민원 삭제 */
	public void removeMw(Map param)throws Exception{
		update("BoardAdminDao.updateMwDel", param);
	}
	
	/** 게시글 민원 복구 */
	public void recoveryMw(Map param)throws Exception{
		update("BoardAdminDao.updateMwRecovery", param);
	}
	
	/** 게시글 이관 상태변경 */
	public void modifyTrans(Map param)throws Exception{
		update("BoardAdminDao.updateTrans", param);
	}
	/** 게시판 글 이관 */
	@SuppressWarnings("unchecked")
    public void registTrans(Map param) throws Exception {
    	insert("BoardAdminDao.insertTrans", param);
    }
	/** 게시글 이관글 파일 등록 */
	@SuppressWarnings("unchecked")
	public void registFileTrans(Map param) throws Exception {
		insert("BoardAdminDao.insertFileTrans", param);
	}
    
    /** 총 건수 조회 */
//   	public int selectPagBoard(BoardAdminVo boardAdminVO) throws Exception {
//   		return (Integer)select("BoardAdminDao.selectPagBoard", boardAdminVO);
//   	}
    
    /** 글 목록을 조회한다.*/
//    public List retireveListBoardAdmin(BoardAdminVo boardAdminVO) throws Exception {
//    	return selectList("BoardAdminDao.selectListBoardAdmin", boardAdminVO);
//    }
    
	
}