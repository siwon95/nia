package egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.vo.GBoardAdminVo;

@Repository("GBoardAdminDao")
public class GBoardAdminDao extends EgovAbstractMapper {
	
	/** 부서 selectBox 조회 */
	public List cdSelectBox() throws Exception {
		return selectList("GBoardAdminDao.cdSelectBox", null);
	}
	
	/** 검색 selectBox Time 조회 */
    @SuppressWarnings("unchecked")
    public List retrieveTimeSelect(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return selectList("GBoardAdminDao.selectBoardSearchTime", gBoardAdminVO);
    }

	/** list count */
	public int selectListGubaraCount(GBoardAdminVo gBoardAdminVO)
			throws Exception {
		return (Integer) selectOne("GBoardAdminDao.gubaraSelectListCount",
				gBoardAdminVO);
	}

	/** 인바라 List 조회 */
	public List retrieveListGubara(GBoardAdminVo gBoardAdminVO) throws Exception {
		return selectList("GBoardAdminDao.gubaraSelectList", gBoardAdminVO);
	}
	
	/** 인바라 Excel 조회 */
	public List retrieveListExcelGubara(GBoardAdminVo gBoardAdminVO) throws Exception {
		return selectList("GBoardAdminDao.gubaraSelectExcelList", gBoardAdminVO);
	}

	/** 인바라 Sub List 조회 */
	public List retrieveSubListGubara(GBoardAdminVo gBoardAdminVO) throws Exception {
		return selectList("GBoardAdminDao.gubaraSubSelectList", gBoardAdminVO);
	}
	
	/** 상세내용 조회수 update */
	public void gubaraBoardCountUpdate(GBoardAdminVo gBoardAdminVO)
			throws Exception {
		update("GBoardAdminDao.gubaraBoardCountUpdate", gBoardAdminVO);
	}
	
	/** 인바라게시물 자유게시판,보건소민원으로 이관  */
	@SuppressWarnings("unchecked")
    public void updateBoardTrans(GBoardAdminVo gBoardAdminVO) throws Exception {
		update("GBoardAdminDao.updateBoardTrans", gBoardAdminVO);
    }
	/** 인바라게시물 자유게시판,보건소민원으로 이관으로 인한 댓글 삭제 */
    public void deleteBoardTrans(GBoardAdminVo gBoardAdminVO) throws Exception {
    	delete("GBoardAdminDao.deleteBoardTrans", gBoardAdminVO);
    }
    
    /** 인바라게시물 파일삭제 대상 리스트 */
    public List selectGBoardFiles(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return selectList("GBoardAdminDao.selectGBoardFiles", gBoardAdminVO);
    }
    
    /** 인바라게시물 파일 리스트 */
    public void deleteGBoardFiles(GBoardAdminVo gBoardAdminVO) throws Exception {
    	delete("GBoardAdminDao.deleteGBoardFiles", gBoardAdminVO);
    }
    
    /** 인바라게시물 답변미대상민원 처리  */
    public void updateGBoardNoAnswer(Map param) throws Exception {
    	update("GBoardAdminDao.updateGBoardNoAnswer", param);
    }
    
    /** 인바라 게시글 복사할 게시판 대분류 리스트 */
    public List selectCopyBoardTgtGroup(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return selectList("GBoardAdminDao.selectCopyBoardTgtGroup", gBoardAdminVO);
    }
    
    /** 인바라 게시글 복사할 게시판 소분류 리스트 */
    public List selectCopyBoardTgtSecond(String cbUprCd) throws Exception {
    	return selectList("GBoardAdminDao.selectCopyBoardTgtSecond", cbUprCd);
    }
    
    /** 인바라 게시글 복사 */
    public void insertCopyBoard(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.insertCopyBoard", gBoardAdminVO);
    }
    
    /** 인바라 게시글 파일 복사 */
    public void insertCopyBoardFiles(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.insertCopyBoardFiles", gBoardAdminVO);
    }
    
    /** 인바라 답변 기한 연기 */
    public void updateGBoardDelayDt(GBoardAdminVo gBoardAdminVO) throws Exception {
    	update("GBoardAdminDao.updateGBoardDelayDt", gBoardAdminVO);
    }
    
    /** bcIdx 생성 */
    public String selectBcIdxNo(String cbIdx) throws Exception {
    	return (String)selectOne("GBoardAdminDao.selectBcIdxNo", cbIdx);
    }
    
    /** 인바라 첨부파일 갯수 */
	public Map gubaraBoardConfig(GBoardAdminVo gBoardAdminVO) throws Exception {
		return (Map) selectOne("GBoardAdminDao.gubaraBoardConfig", gBoardAdminVO);
	}

	/** 상세내용 조회 */
	public Map gubaraBoardDetail(GBoardAdminVo gBoardAdminVO) throws Exception {
		return (Map) selectOne("GBoardAdminDao.gubaraBoardDetail", gBoardAdminVO);
	}

	/** 인바라 File List 조회 */
	public List retrieveFileList(GBoardAdminVo gBoardAdminVO) throws Exception {
		return selectList("GBoardAdminDao.gubaraBoardFile", gBoardAdminVO);
	}

	/** 인바라 답변 체크 */
	public int selectContentClobChk(GBoardAdminVo gBoardAdminVO) throws Exception {
		return (Integer)selectOne("GBoardAdminDao.selectContentClobChk", gBoardAdminVO);
	}

	/** 통합답변 조회 */
	public Map selectIntergResp(GBoardAdminVo gBoardAdminVO) throws Exception {
		return (Map) selectOne("GBoardAdminDao.selectIntergResp", gBoardAdminVO);
	}

	/** 파일 다운로드 */
	@SuppressWarnings("unchecked")
	public Map boardFileDown(Map param) throws Exception {
		return (Map) selectOne("GBoardAdminDao.selectFileDown", param);
	}
	
	/** 상세내용 조회 이전글/다음글 */
	public Map gubaraBoardDetailPreNext(GBoardAdminVo gBoardAdminVO)
			throws Exception {
		return (Map) selectOne("GBoardAdminDao.gubaraBoardDetailPreNext",
				gBoardAdminVO);
	}

	/** 상세내용 수정 */
	public void gubaraBoardUpdate(GBoardAdminVo gBoardAdminVO) throws Exception {
		update("GBoardAdminDao.gubaraBoardUpdate", gBoardAdminVO);
	}
	
	public void gubaraBoardUpdate2(GBoardAdminVo gBoardAdminVO) throws Exception {
		update("GBoardAdminDao.gubaraBoardUpdate2", gBoardAdminVO);
	}
	
	/** 게시판 첨부파일 저장 */
	public void createFile(GBoardAdminVo gBoardAdminVO) throws Exception{
		insert("GBoardAdminDao.insertFile", gBoardAdminVO);
	}
	
	/** 파일 삭제대상 조회 */
	@SuppressWarnings("unchecked")
    public List selectBoardFiles(Map param) throws Exception {
    	return selectList("GBoardAdminDao.selectFileDown", param);
    }
	
	/** 파일 삭제 */
    public void deleteBoardFiles(Map param) throws Exception {
    	delete("GBoardAdminDao.deleteBoardFiles", param);
    }
    
    /** 담당부서 지정 */
    public void insertContentMinwonResult(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.insertContentMinwonResult", gBoardAdminVO);
    }

    /** 담당자 update */
    public void updateContentMinwonResult(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.updateContentMinwonResult", gBoardAdminVO);
    }

    /** 담당부서 조회 */
    public List selectDepList(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return selectList("GBoardAdminDao.selectDepList", gBoardAdminVO);
    }

    /** 담당부서 조회_2 */
    public Map selectDepMap(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return (Map) selectOne("GBoardAdminDao.selectDepMap", gBoardAdminVO);
    }
    
    /** 민원결과조회 */
	public Map selectMwResultMap(GBoardAdminVo gBoardAdminVO) throws Exception {
		return (Map) selectOne("GBoardAdminDao.selectMwResultMap", gBoardAdminVO);
	}
    
    /** 담당부서 삭제 */
    public void deleteDep(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.deleteDep", gBoardAdminVO);
    }
    
    /** 담당부서 변경 */
    public void updateDep(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.updateDep", gBoardAdminVO);
    }
    
    /** 공통코드 조회 */
    public List selectCode(String codeUpr) throws Exception {
    	return selectList("GBoardAdminDao.selectCode", codeUpr);
    }
    
    /** 통합답변 등록 */
    public void insertIntergResp(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.insertIntergResp", gBoardAdminVO);
    }

    /** 통합답변 수정 */
    public void updateIntergResp(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.updateIntergResp", gBoardAdminVO);
    }
    
    /** mcIdx 생성 */
    public String selectMcIdxNo(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return (String)selectOne("GBoardAdminDao.selectMcIdxNo", gBoardAdminVO);
    }
    
    /** 인바라게시글 상태값 변경 */
    public void updateGBoardStatus(GBoardAdminVo gBoardAdminVO) throws Exception {
    	update("GBoardAdminDao.updateGBoardStatus", gBoardAdminVO);
    }

    /** 1, 2차대화 저장 */
    public void updateMcContentReg(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.updateMcContentReg", gBoardAdminVO);
    }

    /** 답변초기화 */
    public void updateReplDel(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.updateReplDel", gBoardAdminVO);
    }
    
    /** 부서장 select */
    public List selectReplDept(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return selectList("GBoardAdminDao.selectReplDept", gBoardAdminVO);
    }
    
    /** 부서장 select */
    public List selectReplDept2() throws Exception {
    	return selectList("GBoardAdminDao.selectReplDept2", "");
    }

    /** 단위업무명1차 select */
    public List selectDw1() throws Exception {
    	return selectList("GBoardAdminDao.selectDw1", null);
    }

    /** 역대구청장 */
    public Map selectChiefHistory() throws Exception {
    	return (Map) selectOne("GBoardAdminDao.selectChiefHistory", null);
    }

    /** 답변 저장 */
    public void updateLastReplReg(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.updateLastReplReg", gBoardAdminVO);
    }

    /** 답변 저장 */
    public void updateLastReplRegArr(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.updateLastReplRegArr", gBoardAdminVO);
    }

    /** 중간답변 부서 추가 */
    public void insertBoardReplReg(GBoardAdminVo gBoardAdminVO) throws Exception {
    	insert("GBoardAdminDao.insertBoardReplReg", gBoardAdminVO);
    }
    
    /** 인바라 만족도 목록 */
    public List selectSurveyList(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return selectList("GBoardAdminDao.selectSurveyList", gBoardAdminVO);
    }
    
    /** 인바라 만족도 엑셀 */
    public List selectSurveyExcel(GBoardAdminVo gBoardAdminVO) throws Exception {
    	return selectList("GBoardAdminDao.selectSurveyExcel", gBoardAdminVO);
    }
    
    /** 인바라 만족도 목록 총갯수 */
    public int selectPagSurvey(GBoardAdminVo gBoardAdminVO) throws Exception{
    	return (Integer)selectOne("GBoardAdminDao.selectPagSurve\1y", gBoardAdminVO);
    }

	public void updateGBoardCdSubject(GBoardAdminVo gBoardAdminVO) {
		update("GBoardAdminDao.updateGBoardCdSubject", gBoardAdminVO);
		
	}

	public void updateBoardStatus(GBoardAdminVo gBoardAdminVo) throws Exception{
		update("GBoardAdminDao.updateBoardStatus", gBoardAdminVo);
		
	}

	public void insertBoardTrans(GBoardAdminVo gBoardAdminVO) {
		insert("GBoardAdminDao.insertBoardTrans", gBoardAdminVO);
	}

	public void updateAnsDeadlineDt(GBoardAdminVo gBoardAdminVo) {
		update("GBoardAdminDao.updateAnsDeadlineDt", gBoardAdminVo);
	}

}