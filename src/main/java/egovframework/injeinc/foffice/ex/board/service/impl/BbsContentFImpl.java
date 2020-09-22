package egovframework.injeinc.foffice.ex.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.injeinc.foffice.ex.board.dao.BbsContentFDao;
import egovframework.injeinc.foffice.ex.board.service.BbsContentFSvc;
import egovframework.injeinc.foffice.ex.board.vo.BbsContentFVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Service("BbsContentFSvc")
public class BbsContentFImpl extends EgovAbstractServiceImpl implements BbsContentFSvc {

	@Resource(name = "BbsContentFDao")
	private BbsContentFDao bbsContentFDao;

	@Resource(name = "bbsContentIdGnrService")
	private EgovIdGnrService idgenService;

	public int registBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {

		int bcIdx = idgenService.getNextIntegerId();
		if(bbsContentFVo != null){
			bbsContentFVo.setBcIdx(bcIdx);
		}
		
		int parentSeq = bbsContentFVo == null ? 0 : bbsContentFVo.getParentSeq();
		
		if(parentSeq > 0) {
			if(bbsContentFVo != null){
				bbsContentFVo.setAnswerStep(bbsContentFVo.getAnswerStep() + 1);
				bbsContentFVo.setAnswerDepth(bbsContentFVo.getAnswerDepth() + 1);
			}
			bbsContentFDao.updateBbsContentFStep(bbsContentFVo);
		}else {
			if(bbsContentFVo != null){
				bbsContentFVo.setParentSeq(bcIdx);
				bbsContentFVo.setAnswerStep(0);
				bbsContentFVo.setAnswerDepth(0);
			}
		}
		
		String clobCont = bbsContentFVo == null ? "" : bbsContentFVo.getClobCont();
		if(!clobCont.equals("")) {
			if(bbsContentFVo != null){
				bbsContentFVo.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
			}
		}
		
		bbsContentFDao.createBbsContentF(bbsContentFVo);
		return bcIdx;
	}

	public void modifyBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		
		String clobCont = bbsContentFVo == null ? "" : bbsContentFVo.getClobCont();
		if(!clobCont.equals("")) {
			if(bbsContentFVo != null){
				bbsContentFVo.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(clobCont),1000));
			}
		}
		
		bbsContentFDao.updateBbsContentF(bbsContentFVo);
	}

	public void removeBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		bbsContentFDao.deleteBbsContentF(bbsContentFVo);
	}

	public Map<String, Object> retrieveBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		Map<String, Object> map = null;
		
		map = bbsContentFDao.selectBbsContentF(bbsContentFVo);
		
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		
		List<BbsContentFVo> result = bbsContentFDao.selectPagBbsContentF(bbsContentFVo);
		int cnt = bbsContentFDao.selectBbsContentFCnt(bbsContentFVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListBbsContentF(BbsContentFVo bbsContentFVo) throws Exception {
		return bbsContentFDao.selectListBbsContentF(bbsContentFVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagBbsContentFMyMinwon(BbsContentFVo bbsContentFVo) throws Exception {
		
		List<BbsContentFVo> result = bbsContentFDao.selectPagBbsContentFMyMinwon(bbsContentFVo);
		int cnt = bbsContentFDao.selectBbsContentFMyMinwonCnt(bbsContentFVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	public void modifyBbsContentFCount(BbsContentFVo bbsContentFVo) throws Exception {
		bbsContentFDao.updateBbsContentFCount(bbsContentFVo);
	}
}