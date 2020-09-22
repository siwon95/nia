package egovframework.injeinc.boffice.ex.sns.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.Count;
import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.sns.dao.SnsDao;
import egovframework.injeinc.boffice.ex.sns.service.SnsSvc;
import egovframework.injeinc.boffice.ex.sns.vo.SnsContentsVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsHashtagVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsProhibitWordVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsSearchKeywordVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("SnsSvc")
public class SnsImpl extends EgovAbstractServiceImpl implements SnsSvc {

	@Resource(name = "SnsDao")
	private SnsDao socialDao;
	
	@Resource(name = "prohibitWordService")
	private EgovIdGnrService prohibitWordService;

	@Resource(name = "hashtagService")
	private EgovIdGnrService hashtagService;

	@Resource(name = "searchKeywordService")
	private EgovIdGnrService searchKeywordService;

	
	public List<SnsContentsVo> retrieveListSnsContents(SnsContentsVo snsContentsVo) throws Exception {
		return socialDao.selectListSnsContents(snsContentsVo);
	}

	
	public List<SnsContentsVo> getSnsListForValidation(
			SnsContentsVo snsContentsVo) throws Exception {
		return socialDao.selectSnsListForValidation(snsContentsVo);
	}

	
	public List<SnsHashtagVo> getSnsHashtagList(
			SnsHashtagVo snsHashtagVo) throws Exception {
		return socialDao.selectSnsHashtagList(snsHashtagVo);
	}

	
	public List<SnsProhibitWordVo> getSnsProhibitWordList(
			SnsProhibitWordVo snsProhibitWordVo) throws Exception {
		return socialDao.selectSnsProhibitWordList(snsProhibitWordVo);
	}

	
	public int getSnsListCntForValidation(SnsContentsVo snsContentsVo) throws Exception {
		return socialDao.selectSnsListCntForValidation(snsContentsVo);
	}

	
	public int getSnsHashtagListCnt(SnsHashtagVo snsHashtagVo) throws Exception {
		return socialDao.selectSnsHashtagListCnt(snsHashtagVo);
	}

	
	public int getSnsProhibitWordListCnt(SnsProhibitWordVo snsProhibitWordVo) throws Exception {
		return socialDao.selectSnsProhibitWordListCnt(snsProhibitWordVo);
	}

	
	public void updateSnsByUseYn(SnsContentsVo snsContentsVo) throws Exception {
		System.out.println("####" + snsContentsVo.getSnsIdx());
		String[] snsIdxs = snsContentsVo.getSnsIdx().split("\\|");
		
		System.out.println("####" + snsIdxs.length);
		for (String snsIdx : snsIdxs) {
			if(!"".equals(snsIdx)){
				snsContentsVo.setSnsIdx(snsIdx);
				socialDao.updateSnsByUseYn(snsContentsVo);
			}
		}
	}

	
	public void deleteProhibitWord(SnsProhibitWordVo snsProhibitWordVo) throws Exception {
		socialDao.updateProhibitWordForDelete(snsProhibitWordVo);
	}

	
	public void insertProhibitWord(SnsProhibitWordVo snsProhibitWordVo) throws Exception {
		snsProhibitWordVo.setPwIdx(prohibitWordService.getNextStringId());
		socialDao.insertProhibitWord(snsProhibitWordVo);
	}

	
	public void deleteHashtag(SnsHashtagVo snsHashtagVo) throws Exception {
		socialDao.updateHashtagForDelete(snsHashtagVo);
	}
	
	
	public void insertHashtag(SnsHashtagVo snsHashtagVo) throws Exception {
		snsHashtagVo.setHsIdx(hashtagService.getNextStringId());
		socialDao.insertHashtag(snsHashtagVo);
	}

	
	public List<SnsSearchKeywordVo> getSnsSearchKeywordList(
			SnsSearchKeywordVo snsSearchKeywordVo) throws Exception {
		return socialDao.selectSnsSearchKeywordList(snsSearchKeywordVo);
	}

	
	public int getSnsSearchKeywordListCnt(SnsSearchKeywordVo snsSearchKeywordVo)
			throws Exception {
		return socialDao.selectSnsSearchKeywordListCnt(snsSearchKeywordVo);
	}

	
	public void insertSearchKeyword(SnsSearchKeywordVo snsSearchKeywordVo)
			throws Exception {
		snsSearchKeywordVo.setSkIdx(searchKeywordService.getNextStringId());
		socialDao.insertSearchKeyword(snsSearchKeywordVo);
	}

	
	public void deleteSearchKeyword(SnsSearchKeywordVo snsSearchKeywordVo)
			throws Exception {
		socialDao.updateSearchKeywordForDelete(snsSearchKeywordVo);
	}

	
	public boolean selectBannendWordList(String value) throws Exception {
		
		int isBannendWord = socialDao.selectBannendWordList(value);
			
		if(isBannendWord != 0){				
			return true;
			}
			return false;
		}	
}