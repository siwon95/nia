package egovframework.injeinc.boffice.ex.sns.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.ex.sns.vo.SnsCollectVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsContentsVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsHashtagVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsProhibitWordVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsSearchKeywordVo;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("SnsDao")
public class SnsDao extends EgovAbstractMapper{
	
	public List<SnsContentsVo> selectListSnsContents(SnsContentsVo snsContentsVo) throws Exception {
		return selectList("selectListSnsContents", snsContentsVo);
	}

	public void insertSnsCollect(List<SnsCollectVo> list, String isValid, String siteDomain) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		for(SnsCollectVo snsCollectVo : list) {
			if("N".equals(isValid)){ // 검증단계가 없다면 무조건 공개
				snsCollectVo.setUseYn("Y");
			}
			snsCollectVo.setSiteDomain(siteDomain);
			sqlSession.insert("insertSnsContents", snsCollectVo);
		}
		
		sqlSession.commit();
		sqlSession.close();
	}

	public void updateSnsProhibitWord() throws Exception {
		update("updateSnsProhibitWord");
	}

	public List<SnsContentsVo> selectSnsListForValidation(SnsContentsVo snsContentsVo) throws Exception {
		return selectList("selectSnsListForValidation",snsContentsVo);
	}

	public List<SnsHashtagVo> selectSnsHashtagList(SnsHashtagVo snsSearchKeywordVo) throws Exception {
		return selectList("selectSnsHashtagList",snsSearchKeywordVo);
	}

	public List<SnsProhibitWordVo> selectSnsProhibitWordList(SnsProhibitWordVo snsProhibitWordVo) throws Exception {
		return selectList("selectSnsProhibitWordList",snsProhibitWordVo);
	}

	public int selectSnsListCntForValidation(SnsContentsVo snsContentsVo) throws Exception {
		return (Integer) selectOne("selectSnsListCntForValidation", snsContentsVo);
	}

	public int selectSnsHashtagListCnt(SnsHashtagVo snsHashtagVo) throws Exception {
		return (Integer) selectOne("selectSnsHashtagListCnt", snsHashtagVo);
	}

	public int selectSnsProhibitWordListCnt(SnsProhibitWordVo snsProhibitWordVo) throws Exception {
		return (Integer) selectOne("selectSnsProhibitWordListCnt", snsProhibitWordVo);
	}

	public void updateSnsByUseYn(SnsContentsVo snsContentsVo) throws Exception {
		update("updateSnsByUseYn", snsContentsVo);
	}

	public void updateSnsContentsHashtag() throws Exception {
		update("updateSnsContentsHashtag");
	}

	public List<SnsSearchKeywordVo> selectSearchKeywordListForContents() throws Exception {
		return selectList("selectSearchKeywordListForContents");
	}

	public List<SnsSearchKeywordVo> selectSearchKeywordListForUser() throws Exception {
		return selectList("selectSearchKeywordListForUser");
	}

	public void updateSnsHashtagForSnsCnt() throws Exception {
		update("updateSnsHashtagForSnsCnt");
	}

	public void updateProhibitWordForDelete(SnsProhibitWordVo snsProhibitWordVo) throws Exception {
		update("updateProhibitWordForDelete", snsProhibitWordVo);
	}

	public void insertProhibitWord(SnsProhibitWordVo snsProhibitWordVo) throws Exception {
		insert("insertProhibitWord", snsProhibitWordVo);
	}

	public void updateHashtagForDelete(SnsHashtagVo snsHashtagVo) throws Exception {
		update("updateHashtagForDelete", snsHashtagVo);
	}

	public void insertHashtag(SnsHashtagVo snsHashtagVo) throws Exception {
		insert("insertHashtag", snsHashtagVo);
	}

	public List<SnsSearchKeywordVo> selectSnsSearchKeywordList(SnsSearchKeywordVo snsSearchKeywordVo) throws Exception {
		return selectList("selectSnsSearchKeywordList",snsSearchKeywordVo);
	}

	public int selectSnsSearchKeywordListCnt(
			SnsSearchKeywordVo snsSearchKeywordVo) throws Exception {
		return (Integer) selectOne("selectSnsSearchKeywordListCnt", snsSearchKeywordVo);
	}

	public void insertSearchKeyword(SnsSearchKeywordVo snsSearchKeywordVo) throws Exception {
		insert("insertSearchKeyword", snsSearchKeywordVo);
	}

	public void updateSearchKeywordForDelete(
			SnsSearchKeywordVo snsSearchKeywordVo) throws Exception {
		update("updateSearchKeywordForDelete", snsSearchKeywordVo);
	}

	public int selectBannendWordList(String value) throws Exception {
		return (Integer)selectOne("selectBannendWordList", value);
	}
	
	
}