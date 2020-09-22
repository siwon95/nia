package egovframework.injeinc.boffice.cn.site.service;

import java.util.List;

import egovframework.injeinc.boffice.cn.site.vo.SiteVo;

public interface SiteSvc {
	/** 사이트 유형 조회 */
	public List selectComSiteCode(SiteVo vo) throws Exception;
    
	/** 리스트 조회 */
	public List retrieveListSite(SiteVo vo) throws Exception;
	
	/** 총 리스트 조회 */
	public List selectListSiteAll(SiteVo vo) throws Exception;
	
	/** 디폴트 사이트 */
	public SiteVo selectSiteDefault() throws Exception;
	
	/** 디폴트 사이트 */
	public SiteVo selectSiteInfo(SiteVo vo) throws Exception;
	
	/** 총 건수 조회 */
	public int selectSiteListTotCnt(SiteVo vo) throws Exception;

	 /** 등록 */
	public void registSite(SiteVo vo) throws Exception;
	
	/** 상세내용 조회 */
	public SiteVo retrieveSite(int siteIdx) throws Exception;
	
	/** 수정 */
	public void modifySite(SiteVo vo) throws Exception;

    /** 삭제 */
	public void removeSite(int siteIdx) throws Exception;
	
	/** 사이트코드 중복 조회 */
	public int retrieveSiteCnt(SiteVo siteVo) throws Exception;
}