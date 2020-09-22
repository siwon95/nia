package egovframework.injeinc.foffice.ex.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;

@Repository("BbsFDao")
public class BbsFDao extends EgovAbstractMapper {
	
	/** 아이디중복체크 */
	public BbsFVo selectLogin(HashMap<String, String> param) throws Exception {
		return  (BbsFVo)selectOne("BbsFDao.selectLogin", param);
	}
	
	/** 공통 게시판 공지사항,이미지게시판 글 내용 조회 */
	@SuppressWarnings("rawtypes")
	public List boardCommonAllList(BbsFVo bbsFVo) throws Exception {
		return selectList("BbsFDao.boardCommonAllList", bbsFVo);
	}
	
	/** 공통 게시판 공지사항,이미지게시판 글 내용 조회 */
	@SuppressWarnings("rawtypes")
	public List boardCommonList(BbsFVo bbsFVo) throws Exception {
		return selectList("BbsFDao.boardCommonList", bbsFVo);
	}
	
	public List boardNewRecord(String cbIdx) throws Exception {
		return selectList("BbsFDao.boardNewRecord", cbIdx);
	}
	
	public List boardNewRecordCate(BbsFVo bbsFVo) throws Exception {
		return selectList("BbsFDao.boardNewRecordCate", bbsFVo);
	}
	
	public List boardNewRecordMulti(BbsFVo bbsFVo) throws Exception {
		return selectList("BbsFDao.boardNewRecordMulti", bbsFVo);
	}
	
	public List boardContentNewRecord(String cbIdx) throws Exception {
		return selectList("BbsFDao.boardContentNewRecord", cbIdx);
	}
	
	public List boardContentNewRecordMulti(BbsFVo bbsFVo) throws Exception {
		return selectList("BbsFDao.boardContentNewRecordMulti", bbsFVo);
	}
	
	public List boardNewRecordFile(String cbIdx) throws Exception {
		return selectList("BbsFDao.boardNewRecordFile", cbIdx);
	}
	
	/** 게시판 라벨 리스트 조회*/
	@SuppressWarnings("rawtypes")
	public List boardLabelList(BbsFVo bbsFVo) throws Exception {
		return selectList("BbsFDao.selectBoardLabelList", bbsFVo);
	}

	/** 게시판 컨텐츠  조회할 목록 조회*/
	@SuppressWarnings("rawtypes")
	public List boardContMappList(BbsFVo bbsFVo) throws Exception {
		return selectList("BbsFDao.selectBoardContMappList", bbsFVo);
	}

	/** 게시판 컨텐츠  조회*/
    @SuppressWarnings("rawtypes")
	public List boardContentList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectBoardContentList", bbsFVo);
    }
    
    /** 게시판 컨텐츠  갯수*/
    public int boardcontentCnt(BbsFVo bbsFVo) throws Exception {
    	return (Integer)selectOne("BbsFDao.selectBoardContentCnt", bbsFVo);
    }
    
    /** 게시판 등록,수정 컨텐츠 구분 코드 조회*/
    @SuppressWarnings("rawtypes")
	public List boardlabelPropGbnList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectboardlabelPropGbnList", bbsFVo);
    }
    
    /** 게시판 정보 조회 */
    public BbsFVo boardInfo(BbsFVo bbsFVo) throws Exception {
    	return (BbsFVo)selectOne("BbsFDao.selectBoardInfo", bbsFVo);
    }
    
    /** 게시판 글 저장 */
    public void boardContentInsert(BbsFVo bbsFVo) throws Exception {
    	insert("BbsFDao.InsertBoardContent", bbsFVo);
    }
	
	/** 게시글 상세 조회 */
    @SuppressWarnings("rawtypes")
	public List boardContentDetail(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectContentDetail", bbsFVo);
    }
    
	/** 조회수  */
    public void boardCountUpdate(BbsFVo bbsFVo) throws Exception {
		update("BbsFDao.UpdateBoardCount", bbsFVo);
    }
	
	/** 게시글 다음글 */
    @SuppressWarnings("rawtypes")
	public Map boardPrevNext(BbsFVo bbsFVo) throws Exception {
		return (Map)selectOne("BbsFDao.selectBoardPrevNext", bbsFVo);
    }
	
	/** 게시글 수정 상세 조회 */
    @SuppressWarnings("rawtypes")
	public Map boardUpdateDetail(BbsFVo bbsFVo) throws Exception {
    	return (Map)selectOne("BbsFDao.selectBoardUpdateDetail", bbsFVo);
    }
    
    /** 게시글 답글 상세 조회 */
    @SuppressWarnings("rawtypes")
	public Map boardReDetail(BbsFVo bbsFVo) throws Exception {
    	return (Map)selectOne("BbsFDao.selectBoardReDetail", bbsFVo);
    }
	
	/** 게시글 민원 답변 */
    @SuppressWarnings("rawtypes")
	public Map selectBoardMinwonDetail(BbsFVo bbsFVo) throws Exception {
    	return (Map)selectOne("BbsFDao.selectBoardMinwonDetail", bbsFVo);
    }
	
	
	/** 게시글 수정  */
    public void boardContentUpdate(BbsFVo bbsFVo) throws Exception {
		update("BbsFDao.boardContentUpdate", bbsFVo);
    }
	
	/** 게시글 삭제  */
    public void boardContentDelete(BbsFVo bbsFVo) throws Exception {
		update("BbsFDao.boardContentDelete", bbsFVo);
    }
	
	/** 게시판 카테고리 조회 */
    @SuppressWarnings("rawtypes")
	public List boardCategoryList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectboardCategoryList", bbsFVo);
    }
	
	/** 게시판 검색 항목 조회 */
    @SuppressWarnings("rawtypes")
	public List boardSearchList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectboardSearchList", bbsFVo);
    }
    
    /** 전체조회 20200831-전진형 통합검색 전체조회 */
    @SuppressWarnings("rawtypes")
	public List boardAllSearchList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectboardAllSearchList", bbsFVo);
    }
    
    /** 전체조회 20200831-전진형 통합검색 전체카운트조회 */
    @SuppressWarnings("rawtypes")
	public List boardAllSearchCntList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectboardAllSearchCntList", bbsFVo);
    }
	
	/** 게시판 첨부파일 저장 */
	public void createFile(BbsFVo bbsFVo) throws Exception{
		insert("BbsFDao.insertFile", bbsFVo);
	}
	
	/** bcIdx select */
  	public int selectBcIdxNo() throws Exception {
  		return  (Integer)selectOne("BbsFDao.selectBcIdxNo", null);
  	}
  	
  	/** 게시판 카테고리 조회 */
    @SuppressWarnings("rawtypes")
	public List boardFileList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectFileList", bbsFVo);
    }
	
	/** 파일 다운로드 */
    @SuppressWarnings("rawtypes")
	public Map boardFileDown(Map param) throws Exception {
    	return (Map)selectOne("BbsFDao.selectFileDown", param);
    }
	
	/** 파일 다운로드 */
    public BbsFVo selectThumbNail(BbsFVo BbsFVo) throws Exception {
    	return (BbsFVo)selectOne("BbsFDao.selectThumbNail", BbsFVo);
    }
	
	/** 파일 삭제대상 조회 */
    @SuppressWarnings("rawtypes")
	public List selectBoardFiles(Map param) throws Exception {
    	return selectList("BbsFDao.selectFileDown", param);
    }
	
	/** 파일 삭제대상 조회 */
    @SuppressWarnings("rawtypes")
	public void deleteBoardFiles(Map param) throws Exception {
//    	delete("BbsFDao.deleteBoardFiles", param);
    	update("BbsFDao.deleteBoardFiles", param);
    }
	
    /** 게시판 이미지 파일 조회 */
    @SuppressWarnings("rawtypes")
	public List boardFileImgList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectImgFileList", bbsFVo);
    }
	
	/** 민원 게시판 컨텐츠  조회*/
	/*@SuppressWarnings("unchecked")
    public List minwonContentList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectMinwonContentList", bbsFVo);
    }*/
    
	/** 민원게시판 컨텐츠  갯수*/
	/*@SuppressWarnings("unchecked")
    public int minwonContentCnt(BbsFVo bbsFVo) throws Exception {
    	return (Integer)select("BbsFDao.selectMinwonContentCnt", bbsFVo);
    }*/
	
	/** 민원 게시판 민원  조회*/
    @SuppressWarnings("rawtypes")
	public List minwonReList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectMinwonReList", bbsFVo);
    }
	
	/** 인바라 게시판 최종답변  조회*/
    @SuppressWarnings("rawtypes")
	public List minwonReTotalList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectMinwonReTotalList", bbsFVo);
    }
	
	/** 부서지정이 되었는지 체크 */
	public int selectMinwonDeptCount(BbsFVo bbsFVo) throws Exception {
    	return (Integer)selectOne("BbsFDao.selectMinwonDeptCount", bbsFVo);
    }
	
	/** 인바라 게시판 답변 갯수*/
    public int minwonReCnt(BbsFVo bbsFVo) throws Exception {
    	return (Integer)selectOne("BbsFDao.selectMinwonReCnt", bbsFVo);
    }
	
	/** 인바라 게시판 최종답변 Code*/
	public String minwonReAuditCode(BbsFVo bbsFVo) throws Exception {
		return (String)selectOne("BbsFDao.selectMinwonReAuditCode", bbsFVo);
	}
	
	/** 인바라 통합답변 사용안함 & 1개부서 일경우*/
    @SuppressWarnings("rawtypes")
	public List minwonReDeptList(BbsFVo bbsFVo) throws Exception {
    	return selectList("BbsFDao.selectMinwonReDeptList", bbsFVo);
    }
	
	/** 민원 게시판 항목  조회*/
	@SuppressWarnings("rawtypes")
	public List itemList(BbsFVo bbsFVo) throws Exception {
		return selectList("BbsFDao.selectItemList", bbsFVo);
	}
	
	/** 공지,공고/공시 서브도메인 CODE_NAME */
	public String selectDeptCodeName(String code) throws Exception {
		return (String)selectOne("BbsFDao.selectDeptCodeName", code);
	}
	
	/** 인재에 바란다 만족도 조사 */
	@SuppressWarnings("rawtypes")
	public void updateBoardMcSurvey(Map param) throws Exception {
		update("BbsFDao.updateBoardMcSurvey", param);
	}
	
	/** 인재메인(이번주영상) */
	public BbsFVo selectBoardVideo(BbsFVo bbsFVo) throws Exception {
		return (BbsFVo)selectOne("BbsFDao.selectBoardVideo", bbsFVo);
	}
	
	/** 인재메인(은희씨와의한컷) */
	public BbsFVo selectBoardPhoto(BbsFVo bbsFVo) throws Exception {
		return (BbsFVo)selectOne("BbsFDao.selectBoardPhoto", bbsFVo);
	}
	
	/** 본인게시물 확인 */
	public String selectRegId(HashMap<Object, Object> map) throws Exception {
		return (String)selectOne("BbsFDao.selectRegId", map);
	}

	/** 게시판 카테고리 조회2 */
    @SuppressWarnings("rawtypes")
	public List boardFileList2(BbsFVo bbsFVo) throws Exception{
		return selectList("BbsFDao.selectFileList2", bbsFVo);
	}
    
    @SuppressWarnings("rawtypes")
	public String selectRssUseYn(String cbIdx) throws Exception{
		return (String) selectOne("BbsFDao.selectRssUseYn", cbIdx);
	}
    
    
    /** mypage category list */
    @SuppressWarnings("rawtypes")
	public List selectMyBbs(String regId) throws Exception{
		return selectList("BbsFDao.selectMyBbs", regId);
	}
    
    @SuppressWarnings("rawtypes")
   	public int bbsRecommentCount(BbsFVo bbsFVo) throws Exception{
   		return (Integer) selectOne("BbsFDao.bbsRecommentCount", bbsFVo);
   	}
    
    /** 게시판 글 추천 저장 */
    public void bbsRecommentInsert(BbsFVo bbsFVo) throws Exception {
    	insert("BbsFDao.bbsRecommentInsert", bbsFVo);
    }
    
    /** 최근 추가된 게시판조회 */
    @SuppressWarnings("rawtypes")
    public List boardNewContentList() throws Exception {
    	return selectList("BbsFDao.boardNewContentList");
    }
    
    /** 최근 추가된 공지게시판조회 */
    @SuppressWarnings("rawtypes")
    public List boardNewNoticeList() throws Exception {
    	return selectList("BbsFDao.boardNewNoticeList");
    }
    
    /*    @SuppressWarnings("rawtypes")
	public Map seleteGubboardDelete(Map param) throws Exception{
      	return (Map)select("BbsFDao.seleteGubboardDelete", param);
	}*/


	public BbsFVo seleteGubboardDelete1(BbsFVo bbsFVo) throws Exception{
		return (BbsFVo)selectOne("BbsFDao.seleteGubboardDelete1", bbsFVo);
	}

	public void updateboardContentWithdrawal(BbsFVo bbsFVo) throws Exception {
		update("BbsFDao.updateboardContentWithdrawal", bbsFVo);
	}
	
	@SuppressWarnings("rawtypes")
   	public int boardMediaPlayCnt(BbsFVo bbsFVo) throws Exception{
   		return (Integer) selectOne("BbsFDao.selectBoardMediaPlayCnt", bbsFVo);
   	}
	
	public Map boardMediaPlayInsert(BbsFVo bbsFVo) throws Exception {
    	return (Map)selectOne("BbsFDao.selectBoardMediaPlayInsert", bbsFVo);
    }
	
	
	@SuppressWarnings("rawtypes")
	public Map boardMediaPlayInfo(BbsFVo bbsFVo) throws Exception {
    	return (Map)selectOne("BbsFDao.selectBoardMediaPlayInfo", bbsFVo);
    }

	/*20200910*/
	@SuppressWarnings("rawtypes")
	public Map selectNewPageIndex(BbsFVo bbsFVo) throws Exception {
    	return (Map)selectOne("BbsFDao.selectNewPageIndex", bbsFVo);
    }
	
}