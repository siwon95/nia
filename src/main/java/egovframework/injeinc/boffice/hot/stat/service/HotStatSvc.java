package egovframework.injeinc.boffice.hot.stat.service;

import java.util.List;

import egovframework.injeinc.boffice.hot.stat.vo.HotStatVo;


public interface HotStatSvc {
	
	/** 월별 리스트 조회 */
	public List retrieveListHotStatMonth(HotStatVo hotStatVO) throws Exception;
	
	/** 일별 리스트 조회 */
	public List retrieveListHotStatDay(HotStatVo hotStatVO) throws Exception;
	
	/** 목록 개수 조회 */
	public int retrievePagHotStat(HotStatVo hotStatVO) throws Exception;
	
	/** 빠른서비스 조회수 증가 */
	public void modifyHotViewCnt(String hlIdx) throws Exception;
}