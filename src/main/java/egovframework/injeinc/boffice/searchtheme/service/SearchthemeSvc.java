package egovframework.injeinc.boffice.searchtheme.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.searchtheme.vo.SearchthemeVo;
import egovframework.injeinc.boffice.searchtheme.vo.SearchthemeVo;


public interface SearchthemeSvc {
	
	/** 리스트 조회 */
	public List retrieveListSearchtheme(SearchthemeVo searchthemeVO) throws Exception;
	
	/** 총 건수 조회 */
	public int retrievePagSearchtheme(SearchthemeVo searchthemeVO) throws Exception;
	
	/** 그룹 리스트 조회 */
	public List retrieveListGroupCd() throws Exception;
	
	/** 삭제 */
	public void removeSearchtheme(SearchthemeVo searchthemeVO) throws Exception;
	
	/** 상세조회 */
	public SearchthemeVo retrieveSearchtheme(SearchthemeVo searchthemeVO) throws Exception;
	
	/** 등록 */
	public void registSearchtheme(SearchthemeVo searchthemeVO) throws Exception;
	
	/** 사용여부 수정 */
	public void modifySearchthemeUseYn(HashMap<String, String> param) throws Exception;
	
	/** 수정 */
	public void modifySearchtheme(SearchthemeVo searchthemeVO) throws Exception;
	
	/** 파일저장명,원본명,경로 삭제(update) */
	public void removeSearchthemeFile(HashMap<String, String> param) throws Exception;
	
	/** 파일경로 조회 */
	public String retrieveFilePath(String stIdx) throws Exception;
}