package egovframework.injeinc.boffice.ex.bbs.boardAdmin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import egovframework.injeinc.boffice.ex.bbs.boardAdmin.dao.BoardAdminDao;
import egovframework.injeinc.boffice.ex.bbs.boardAdmin.service.BoardAdminSvc;
import egovframework.injeinc.boffice.ex.bbs.boardAdmin.vo.BoardAdminVo;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("BoardAdminSvc")
public class BoardAdminImpl extends EgovAbstractServiceImpl implements BoardAdminSvc {
	
    @Resource(name="BoardAdminDao")
    private BoardAdminDao boardAdminDao;
    
    @Autowired
	private DataSourceTransactionManager transactionManager;
    
    /** first 검색 selectBox 조회 */
    public List retrieveFirstSelect(BoardAdminVo boardAdminVO) throws Exception {
    	return boardAdminDao.retrieveFirstSelect(boardAdminVO);
    }
    
    /** second 검색 selectBox 조회 */
    public HashMap<String, Object> retrieveSecondSelect(HashMap<String, String> param) throws Exception {
  		HashMap<String, Object> output = new HashMap<String, Object>();
  		
  		try {

  			ArrayList<Object> listDao = (ArrayList<Object>) boardAdminDao.retrieveSecondSelect(param);
  	  		
  			output.put("secondSelectList", listDao);
			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", Message.getMessage("301.code"));
				output.put("SVC_MSGE", Message.getMessage("301.message"));
  				return output;				
  			}
			
  		} catch (RuntimeException e) {
  			output.put("SVC_CODE", Message.getMessage("901.code"));
			output.put("SVC_MSGE", Message.getMessage("901.message"));
  		} catch (Exception e) {
  			output.put("SVC_CODE", Message.getMessage("901.code"));
  			output.put("SVC_MSGE", Message.getMessage("901.message"));
  		}
  		
  		return output;
  	}
    
    /** 검색 selectBox Time 조회 */
    public List retrieveTimeSelect(BoardAdminVo boardAdminVO) throws Exception {
    	return boardAdminDao.retrieveTimeSelect(boardAdminVO);
    }
    
    /** 게시판 정보 조회 */
	public BoardAdminVo boardInfo(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.boardInfo(boardAdminVO);
	}
    
    /** 게시판 라벨 리스트 조회 */
	public List boardLabelList(BoardAdminVo boardAdminVO) throws Exception {
	  return boardAdminDao.boardLabelList(boardAdminVO);
	}
	
	/** 게시판 컨텐츠  조회할 목록 조회*/
	public List boardContMappList(BoardAdminVo boardAdminVO) throws Exception {
	  return boardAdminDao.boardContMappList(boardAdminVO);
	}
	
	/** 게시판 검색 항목 조회 */
	public List boardSearchList(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.boardSearchList(boardAdminVO);
	}
	
	/** 게시판 컨텐츠  조회*/
	public List boardContentList(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.boardContentList(boardAdminVO);
	}

	/** 민원 게시판 컨텐츠  조회*/
	public List minwonContentList(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.minwonContentList(boardAdminVO);
	}
	
	/** 민원게시판 민원 조회*/
	public List minwonReList(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.minwonReList(boardAdminVO);
	}
	
	/** 민원게시판 항목 조회*/
	public List itemList(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.itemList(boardAdminVO);
	}
	
	/** 게시판 컨텐츠  갯수*/
	public int boardcontentCnt(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.boardcontentCnt(boardAdminVO);
	}
	
	/** 민원게시판 컨텐츠  갯수*/
	public int minwonContentCnt(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.minwonContentCnt(boardAdminVO);
	}
	
	/** 게시판 글 삭제 */
    public void removeBoard(BoardAdminVo boardAdminVO) throws Exception {
    	boardAdminDao.deleteBoard(boardAdminVO);
    }

    /** 게시판 글 승인 */
    public void modifyBoard(BoardAdminVo boardAdminVO) throws Exception {
    	boardAdminDao.updateBoard(boardAdminVO);
    }
    
    /** 상세내용 조회 */
  	public List retrieveBoard(BoardAdminVo boardAdminVO) throws Exception {
//  		BoardAdminVo result = boardAdminDao.selectBoard(boardAdminVO);
//  		if (result == null) {
//  			result = new BoardAdminVo();
//  		}		
//  		return result;
  		return boardAdminDao.selectBoard(boardAdminVO);
  	}

  	/** 상세내용 조회 */
  	public Map boardDetail(BoardAdminVo boardAdminVO) throws Exception {
  		return boardAdminDao.boardDetail(boardAdminVO);
  	}
  	
  	/** 게시판 파일 조회*/
	public List boardFileList(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.boardFileList(boardAdminVO);
	}
	
	/** 게시판 이미지 파일 조회*/
	public List boardFileImgList(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.boardFileImgList(boardAdminVO);
	}
  	
  	/** 상세내용 파일 조회 */
  	/*public List retrieveBoardFile(BoardAdminVo boardAdminVO) throws Exception {
  		return boardAdminDao.selectBoardFile(boardAdminVO);
  	}*/
  	
  	/** 상세내용 파일 조회 */
  	public List<BoardAdminVo> retrieveBoardFile(Map<String, Object> param) throws Exception {
  		return boardAdminDao.selectBoardFile(param);
  	}
  	
  	/** 상세내용 조회수 update */
  	public void boardCountUpdate(BoardAdminVo boardAdminVO) throws Exception {
  		boardAdminDao.updateboardCount(boardAdminVO);
  	}
  	
  	/** 게시판 글쓰기 카테고리 조회 */
  	public List retrieveBoardlabelPropGbnList(BoardAdminVo boardAdminVO) throws Exception {
  		return boardAdminDao.boardlabelPropGbnList(boardAdminVO);
  	}
  	
  	/** 게시판 글쓰기 화면생성 */
  	public List retrieveBoardCategoryList(BoardAdminVo boardAdminVO) throws Exception {
  		return boardAdminDao.selectBoardCategoryList(boardAdminVO);
  	}
  	
  	/** bcIdx 생성 */
    public Integer registBcIdxNo(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.selectBcIdxNo(boardAdminVO);
    }

    /** cbIdx select */
    public String boardCbIdxSelect(String ssName) throws Exception {
    	return boardAdminDao.boardCbIdxSelect(ssName);
    }
  	
  	/** 게시판글 저장 */
	public void boardContentInsert(BoardAdminVo boardAdminVO) throws Exception {
		String clobCont = "";
		if(boardAdminVO != null){
			clobCont = EgovStringUtil.isNullToString(boardAdminVO.getClobCont());
			if(clobCont != null){
				if(!"".equals(clobCont) ){
					boardAdminVO.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
				}
			}
		}
		boardAdminDao.boardContentInsert(boardAdminVO);
	}
	
	/** 게시글 수정 */
	public void boardContentUpdate(BoardAdminVo boardAdminVO) throws Exception {
		String clobCont = ""; 
		if(boardAdminVO != null){
			clobCont = EgovStringUtil.isNullToString(boardAdminVO.getClobCont());
			if(clobCont != null){
				if(!"".equals(clobCont) ){
					boardAdminVO.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
				}
			}
		}
		boardAdminDao.boardContentUpdate(boardAdminVO);
		
	}

	/** 게시판글 저장 */
	public void boardContentMinwonResultInsert(BoardAdminVo boardAdminVO) throws Exception {
		boardAdminDao.boardContentMinwonResultInsert(boardAdminVO);
	}
	
	/** 게시판 첨부파일 저장 */
	public void boardRegistFiles(HttpServletRequest request, BoardAdminVo boardAdminVO) throws Exception {
		
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		final List<MultipartFile> files = multiRequest.getFiles("fileTagId");
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String fileStreCours = Message.getMessage("board.file.upload.path");
		String bcfolder = "";
		if(boardAdminVO != null){
			bcfolder = Integer.toString(boardAdminVO.getCbIdx());
		}
		fileStreCours = fileStreCours + bcfolder + "/";
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
				
				if(boardAdminVO != null){	
					boardAdminVO.setFileStreCours(fileStreCours);
					boardAdminVO.setStreFileNm(streFileNm);
					boardAdminVO.setOrignlFileNm(orignlFileNm);
					boardAdminVO.setFileExtsn(fileExtsn.toLowerCase());
					boardAdminVO.setFileSize(fileSize);
				}
				
				file.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours + streFileNm)));
				boardAdminDao.createFile(boardAdminVO);
			}
		}
	}
	
	/** 게시글 수정 상세 조회*/
	public Map boardContentDetail(BoardAdminVo boardAdminVO) throws Exception {
		return boardAdminDao.boardContentDetail(boardAdminVO);
	}
	
	/** 게시글 공지설정여부*/
	public void modifyBoardNoti(BoardAdminVo boardAdminVO) throws Exception {
		boardAdminDao.updateBoardNoti(boardAdminVO);
	}

	/** 게시글 승인 */
	public void modifyBoardApprYn(BoardAdminVo boardAdminVO) throws Exception {
		boardAdminDao.updateBoardApprYn(boardAdminVO);
	}

	/** 게시글 승인 */
	public void modifyBoardmwDelYn(BoardAdminVo boardAdminVO) throws Exception {
		boardAdminDao.modifyBoardmwDelYn(boardAdminVO);
	}
	
	/** 게시글 민원 삭제 사유리스트 */
	public List retrieveContentDelRsn() throws Exception {
		return boardAdminDao.retrieveContentDelRsn();
	}
    
	/** 게시글 민원 삭제 */
	public void removeMw(Map param)throws Exception{
		boardAdminDao.removeMw(param);
	}
	
	/** 게시글 민원 복구 */
	public void recoveryMw(Map param)throws Exception{
		boardAdminDao.recoveryMw(param);
	}
	
	/** 게시글 이관 */
	public void registTrans(Map param)throws Exception{
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			boardAdminDao.modifyTrans(param);
			boardAdminDao.registTrans(param);
			boardAdminDao.registFileTrans(param);
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		
		transactionManager.commit(status);
		
		
		
	}
	
    /** 페이징 조회 */
//    public int retrievePagBoard(BoardAdminVo boardAdminVO) throws Exception {
//  		return boardAdminDao.selectPagBoard(boardAdminVO);
//    }
    
    /** 목록을 조회한다. */
//    public List retrieveListBoardAdmin(BoardAdminVo boardAdminVO) throws Exception {
//        return boardAdminDao.retireveListBoardAdmin(boardAdminVO);
//    }
    
}