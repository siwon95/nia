package egovframework.injeinc.boffice.main.top.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.main.top.vo.TopImagesVo;


public interface TopImagesSvc {
	
	/** 리스트 조회 */
	public List retrieveListTopImages(TopImagesVo topImagesVO) throws Exception;
	
	/** 총 건수 조회 */
	public int retrievePagTopImages(TopImagesVo topImagesVO) throws Exception;
	
	/** 등록 */
	public void registTopImages(TopImagesVo topImagesVO) throws Exception;
	
	/** 상세조회 */
	public TopImagesVo retrieveTopImages(TopImagesVo topImagesVO) throws Exception;
	
	/** 파일경로조회 */
	public String retrieveFilePath(String tiIdx) throws Exception;
	
	/** 파일경로조회 */
	public void removeTopImagesFile(HashMap<String, String> param) throws Exception;
	
	/** 수정 */
	public void modifyTopImages(TopImagesVo topImagesVO) throws Exception;
	
	/** 삭제 */
	public void removeTopImages(TopImagesVo topImagesVO) throws Exception;
	
	/** 메인화면 리스트 조회 */
	public List retrieveListTopImagesMain() throws Exception;
	
	/** 조회수 증가 */
	public void modifyTopImagesViewCnt(String tiIdx) throws Exception;
	
}