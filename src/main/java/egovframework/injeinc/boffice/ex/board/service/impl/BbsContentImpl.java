package egovframework.injeinc.boffice.ex.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.board.dao.BbsContentDao;
import egovframework.injeinc.boffice.ex.board.service.BbsContentSvc;
import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("BbsContentSvc")
public class BbsContentImpl extends EgovAbstractServiceImpl implements BbsContentSvc {

	@Resource(name = "BbsContentDao")
	private BbsContentDao bbsContentDao;

	@Resource(name = "bbsContentIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name = "ContentFileSvc")
	private ContentFileSvc contentFileSvc;
	
	public int registBbsContent(HttpServletRequest request, BbsContentVo bbsContentVo) throws Exception {

		int bcIdx = idgenService.getNextIntegerId();
		int parentSeq = 0;
		
		if(bbsContentVo != null){
			bbsContentVo.setBcIdx(bcIdx);
			parentSeq = bbsContentVo.getParentSeq();
		}
		
		if(parentSeq > 0) {
			if(bbsContentVo != null){
				bbsContentVo.setAnswerStep(bbsContentVo.getAnswerStep() + 1);
				bbsContentVo.setAnswerDepth(bbsContentVo.getAnswerDepth() + 1);
			}
			bbsContentDao.updateBbsContentStep(bbsContentVo);
		}else {
			if(bbsContentVo != null){
				bbsContentVo.setParentSeq(bcIdx);
				bbsContentVo.setAnswerStep(0);
				bbsContentVo.setAnswerDepth(0);
			}
		}
		String clobCont = "";
		if(bbsContentVo != null){
			clobCont = bbsContentVo.getClobCont();
		}
		if(clobCont != null){
			if(!clobCont.equals("")) {
				if(bbsContentVo != null){
					bbsContentVo.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
				}
			}
		}
		
		bbsContentDao.createBbsContent(bbsContentVo);
		
		if(bcIdx > 0) {
			ContentFileVo contentFileVo = new ContentFileVo();
			contentFileVo.setBcIdx(bcIdx);
			contentFileVo.setCbIdx(bbsContentVo.getCbIdx());
			contentFileVo.setRegId((String)request.getAttribute("userid"));
			contentFileVo.setFileMaxSize((String)request.getAttribute("fileMaxSize"));
			contentFileSvc.registContentFile2(request, contentFileVo, bbsContentVo);									//첨부파일 등록
		}
		return bcIdx;
	}
	
	public int registBbsContentNofile(HttpServletRequest request, BbsContentVo bbsContentVo) throws Exception {

		int bcIdx = idgenService.getNextIntegerId();
		int parentSeq = 0;
		
		if(bbsContentVo != null){
			bbsContentVo.setBcIdx(bcIdx);
			parentSeq = bbsContentVo.getParentSeq();
		}
		
		if(parentSeq > 0) {
			if(bbsContentVo != null){
				bbsContentVo.setAnswerStep(bbsContentVo.getAnswerStep() + 1);
				bbsContentVo.setAnswerDepth(bbsContentVo.getAnswerDepth() + 1);
			}
			bbsContentDao.updateBbsContentStep(bbsContentVo);
		}else {
			if(bbsContentVo != null){
				bbsContentVo.setParentSeq(bcIdx);
				bbsContentVo.setAnswerStep(0);
				bbsContentVo.setAnswerDepth(0);
			}
		}
		String clobCont = "";
		if(bbsContentVo != null){
			clobCont = bbsContentVo.getClobCont();
		}
		if(clobCont != null){
			if(!clobCont.equals("")) {
				if(bbsContentVo != null){
					bbsContentVo.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
				}
			}
		}
		
		bbsContentDao.createBbsContent(bbsContentVo);

		return bcIdx;
	}

	public void modifyBbsContent(HttpServletRequest request, BbsContentVo bbsContentVo) throws Exception {
		String clobCont = "";
		if(bbsContentVo != null){
			clobCont = bbsContentVo.getClobCont();
		}
		if(clobCont != null){
			if(!clobCont.equals("")) {
				if(bbsContentVo != null){
					bbsContentVo.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
				}
			}
		}
		
		bbsContentDao.updateBbsContent(bbsContentVo);
		
		ContentFileVo contentFileVo = new ContentFileVo();
		contentFileVo.setBcIdx(bbsContentVo.getBcIdx());
		contentFileVo.setCbIdx(bbsContentVo.getCbIdx());
		contentFileVo.setRegId((String)request.getAttribute("userid"));
		contentFileVo.setFileMaxSize((String)request.getAttribute("fileMaxSize"));
		
		contentFileSvc.registContentFile2(request, contentFileVo , bbsContentVo);
	}

	public void removeBbsContent(BbsContentVo bbsContentVo) throws Exception {
		bbsContentDao.deleteBbsContent(bbsContentVo);
	}

	public Map<String, Object> retrieveBbsContent(BbsContentVo bbsContentVo) throws Exception {
		return bbsContentDao.selectBbsContent(bbsContentVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagBbsContent(BbsContentVo bbsContentVo) throws Exception {
		
		List<BbsContentVo> result = bbsContentDao.selectPagBbsContent(bbsContentVo);
		int cnt = bbsContentDao.selectBbsContentCnt(bbsContentVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@SuppressWarnings("unchecked")
	public int retrieveBbsContentCnt(BbsContentVo bbsContentVo) throws Exception {
		int cnt = bbsContentDao.selectBbsContentCnt(bbsContentVo);
		return cnt;
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListBbsContent(BbsContentVo bbsContentVo) throws Exception {
		return bbsContentDao.selectListBbsContent(bbsContentVo);
	}

	public void modifyBbsContentBcDelYn(BbsContentVo bbsContentVo) throws Exception {
		bbsContentDao.updateBbsContentBcDelYn(bbsContentVo);
	}

	public void modifyBbsContentDelRsnCd(BbsContentVo bbsContentVo) throws Exception {
		bbsContentDao.updateBbsContentDelRsnCd(bbsContentVo);
	}

	public void modifyBbsContentAnswer(BbsContentVo bbsContentVo) throws Exception {
		bbsContentDao.updateBbsContentAnswer(bbsContentVo);
	}

	public void modifyBbsContentCount(BbsContentVo bbsContentVo) throws Exception {
		bbsContentDao.updateBbsContentCount(bbsContentVo);
	}

	public void modifyBbsContentClobAutoMake(BbsContentVo bbsContentVo) throws Exception {
		String clobCont = "";
		if(bbsContentVo != null){
			clobCont = bbsContentVo.getClobCont();
			bbsContentVo.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
		}
		bbsContentDao.updateBbsContentClobAutoMake(bbsContentVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListBbsContentQnA(BbsContentVo bbsContentVo) throws Exception {
		return bbsContentDao.selectListBbsContentQnA(bbsContentVo);
	}

	
	/* RSS LIST */
	public List retrieveRssListBbsContent(BbsContentVo bbsContentVo) throws Exception {
		return bbsContentDao.setectRssListBbsContent(bbsContentVo);
	}

	/* RSS PAGE */
	public int retrievePagRssListBbsContent(BbsContentVo bbsContentVo) throws Exception {
		return bbsContentDao.selectPagRssListBbsContent(bbsContentVo);
	}
	
	
	public void updateBbsContentMove(BbsContentVo bbsContentVo) throws Exception {
		bbsContentDao.updateBbsContentMove(bbsContentVo);
	}
	
	public void insertBbsContentCopy(BbsContentVo bbsContentVo) throws Exception {
		int bcIdx = idgenService.getNextIntegerId();
		bbsContentVo.setNewBcIdx(bcIdx);
		bbsContentDao.insertBbsContentCopy(bbsContentVo);
	}
	
	@SuppressWarnings("unchecked")
	public String selectCmsbbsGroup(BbsContentVo bbsContentVo) throws Exception {
		String content = bbsContentDao.selectCmsbbsGroup(bbsContentVo);
		return content;
	}
	
	public void updateBbsContentUpDown(BbsContentVo bbsContentVo) throws Exception {
		int targetParentSeq = bbsContentDao.selectUpDownParentSeq(bbsContentVo);
		if(targetParentSeq!=0){
			int fromParentSeq = bbsContentDao.selectUpDownParentSeqFrom(bbsContentVo);
			bbsContentDao.sortMyParentSeqZero(bbsContentVo);
			
			bbsContentVo.setParentSeq(fromParentSeq);
			bbsContentDao.sortTargetParentSeq(bbsContentVo);
			
			bbsContentVo.setParentSeq(targetParentSeq);
			bbsContentDao.sortMyParentSeq(bbsContentVo);
		}
		//bbsContentDao.updateBbsContentUpDown(bbsContentVo);
	}

	public void boardUpdateStatusR(BbsContentVo bbsContentVo) throws Exception {
		bbsContentDao.updateBoardStatusR(bbsContentVo);
	}
}