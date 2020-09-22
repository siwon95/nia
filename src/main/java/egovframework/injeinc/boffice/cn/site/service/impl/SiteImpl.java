package egovframework.injeinc.boffice.cn.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.cn.site.dao.SiteDao;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("SiteSvc")
public class SiteImpl extends EgovAbstractServiceImpl implements SiteSvc {

	@Resource(name="SiteDao")
	SiteDao siteDao;
	
	/** 사이트 유형 조회 */
	public List selectComSiteCode(SiteVo siteVo) throws Exception {
		return siteDao.selectComSiteCode(siteVo);
	}
	
	/** 리스트 조회 */
	public List retrieveListSite(SiteVo siteVo) throws Exception {
		return siteDao.selectListSite(siteVo);
	}
	
	/** 총 리스트 조회 */
	public List selectListSiteAll(SiteVo siteVo) throws Exception {
		return siteDao.selectListSiteAll(siteVo);
	}
	
	/** 디폴트 사이트 */
	public SiteVo selectSiteDefault() throws Exception {
		return siteDao.selectSiteDefault();
	}
	
	/** 디폴트 사이트 */
	public SiteVo selectSiteInfo(SiteVo siteVo) throws Exception {
		return (SiteVo)siteDao.selectSiteInfo(siteVo);
	}

	/** 총 건수 조회 */
	public int selectSiteListTotCnt(SiteVo siteVo) throws Exception {

		return siteDao.selectSiteListTotCnt(siteVo);
	}

	/** 등록 */
	public void registSite(SiteVo siteVo) throws Exception {
		siteDao.createSite(siteVo);
	}
	
	///** 상세내용 조회 */
	public SiteVo retrieveSite(int siteIdx) throws Exception {
		return siteDao.selectSite(siteIdx);
	}
	
	/** 수정 */
	public void modifySite(SiteVo siteVo) throws Exception {
		siteDao.updateSite(siteVo);
	}
	
	/** 삭제 */
	public void removeSite(int siteIdx) throws Exception {
		siteDao.deleteSite(siteIdx);
	}
	
	/** 사이트코드 중복 조회 */
	public int retrieveSiteCnt(SiteVo siteVo) throws Exception {
		return siteDao.selectSiteCnt(siteVo);
	}
	
}