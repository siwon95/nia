package egovframework.injeinc.foffice.ex.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.foffice.ex.bbs.dao.BbsCustomFDao;
import egovframework.injeinc.foffice.ex.bbs.service.BbsCustomFSvc;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsCustomFVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("BbsCustomFSvc")
public class BbsCustomFImpl extends EgovAbstractServiceImpl implements BbsCustomFSvc {

	@Resource(name = "BbsCustomFDao")
	private BbsCustomFDao bbsCustomFDao;

	@Resource(name = "bbsContentIdGnrService")
	private EgovIdGnrService idgenService;

	public BbsCustomFVo retrieveBbsCustomF(BbsCustomFVo bbsCustomFVo) throws Exception {
		bbsCustomFDao.updateBbsCustomFCount(bbsCustomFVo);
		bbsCustomFVo = bbsCustomFDao.selectBbsCustomF(bbsCustomFVo);
		if(bbsCustomFVo != null) {
			bbsCustomFVo.setCountCont(Integer.toString(Integer.parseInt(bbsCustomFVo.getCountCont())+1));
		}
		return bbsCustomFVo;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagBbsCustomF(BbsCustomFVo bbsCustomFVo) throws Exception {
		
		List<BbsCustomFVo> result = bbsCustomFDao.selectPagBbsCustomF(bbsCustomFVo);
		int cnt = bbsCustomFDao.selectBbsCustomFCnt(bbsCustomFVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public EgovMap retrieveBbsCustomFPrevNext(BbsCustomFVo bbsCustomFVo) throws Exception {
		return bbsCustomFDao.selectBbsCustomFPrevNext(bbsCustomFVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListBbsCustomFLatest(BbsCustomFVo bbsCustomFVo) throws Exception {
		return bbsCustomFDao.selectListBbsCustomFLatest(bbsCustomFVo);
	}

	public BbsCustomFVo retrieveListBbsCustomFLatestOne(BbsCustomFVo bbsCustomFVo) throws Exception {
		return bbsCustomFDao.selectListBbsCustomFLatestOne(bbsCustomFVo);
	}

	public int registBbsCustomFIdea(BbsCustomFVo bbsCustomFVo) throws Exception {
		int bcIdx = idgenService.getNextIntegerId();
		if(bbsCustomFVo != null){
			bbsCustomFVo.setBcIdx(bcIdx);
			bbsCustomFVo.setParentSeq(bcIdx);
		}
		bbsCustomFDao.insertBbsCustomFIdea(bbsCustomFVo);
		return bcIdx;
	}
}