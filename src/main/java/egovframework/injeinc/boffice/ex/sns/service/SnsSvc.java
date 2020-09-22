package egovframework.injeinc.boffice.ex.sns.service;

import java.util.List;

import egovframework.injeinc.boffice.ex.sns.vo.SnsContentsVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsProhibitWordVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsHashtagVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsSearchKeywordVo;
import egovframework.injeinc.foffice.ex.sns.vo.SnsContentsFVo;


public interface SnsSvc {

	List<SnsContentsVo> retrieveListSnsContents(SnsContentsVo snsContentsVo) throws Exception;

	List<SnsContentsVo> getSnsListForValidation(SnsContentsVo snsContentsVo) throws Exception;

	List<SnsHashtagVo> getSnsHashtagList(SnsHashtagVo snsSearchKeywordVo) throws Exception;

	List<SnsProhibitWordVo> getSnsProhibitWordList(SnsProhibitWordVo snsProhibitWordVo) throws Exception;

	int getSnsListCntForValidation(SnsContentsVo snsContentsVo) throws Exception;

	int getSnsHashtagListCnt(SnsHashtagVo snsSearchKeywordVo) throws Exception;

	int getSnsProhibitWordListCnt(SnsProhibitWordVo snsProhibitWordVo) throws Exception;

	void updateSnsByUseYn(SnsContentsVo snsContentsVo) throws Exception;

	void deleteProhibitWord(SnsProhibitWordVo snsProhibitWordVo) throws Exception;

	void insertProhibitWord(SnsProhibitWordVo snsProhibitWordVo) throws Exception;

	void insertHashtag(SnsHashtagVo snsHashtagVo) throws Exception;

	void deleteHashtag(SnsHashtagVo snsHashtagVo) throws Exception;

	List<SnsSearchKeywordVo> getSnsSearchKeywordList(SnsSearchKeywordVo snsSearchKeywordVo) throws Exception;

	int getSnsSearchKeywordListCnt(SnsSearchKeywordVo snsSearchKeywordVo) throws Exception;

	void insertSearchKeyword(SnsSearchKeywordVo snsSearchKeywordVo) throws Exception;

	void deleteSearchKeyword(SnsSearchKeywordVo snsSearchKeywordVo) throws Exception;

	boolean selectBannendWordList(String value) throws Exception;
	
}
