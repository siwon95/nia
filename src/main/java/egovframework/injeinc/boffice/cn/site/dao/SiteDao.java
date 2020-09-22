package egovframework.injeinc.boffice.cn.site.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("SiteDao") 
public class SiteDao extends EgovAbstractMapper {
	
    public List selectComSiteCode(SiteVo siteVo) throws Exception {
        return selectList("SiteDao.selectComSiteCode", siteVo);
    }
    
    public List selectListSite(SiteVo siteVo) throws Exception {
        return selectList("SiteDao.selectListSite", siteVo);
    }
    
    public List selectListSiteAll(SiteVo siteVo) throws Exception {
        return selectList("SiteDao.selectListSiteAll",siteVo);
    }
    
    public SiteVo selectSiteDefault() throws Exception {
    	return (SiteVo)selectOne("SiteDao.selectSiteDefault");
    }
    
    public SiteVo selectSiteInfo(SiteVo siteVo) throws Exception {
    	return (SiteVo)selectOne("SiteDao.selectSiteInfo",siteVo);
    }
    
    /** 총 건수 조회 */
   	public int selectSiteListTotCnt(SiteVo siteVo) throws Exception {
   		return (Integer)selectOne("SiteDao.selectSiteListTotCnt", siteVo);
   	}
   	
   	/** 아이디중복체크 */
   	public List selectMgrIdCheck(SiteVo siteVo) throws Exception {
   		return selectList("MntDAO.selectMgrIdCheck", siteVo); 
   	}
    
    /** 등록 */
  	public void createSite(SiteVo siteVo) throws Exception {
  		insert("SiteDao.insertSite", siteVo);
  	}
  	
  	/** 상세내용 조회 */
	public SiteVo selectSite(int siteIdx) throws Exception {
		SiteVo siteVo = (SiteVo)selectOne("SiteDao.viewSite", siteIdx);
		return siteVo;
	}
	
	 /** 수정 */
	public void updateSite(SiteVo siteVo) throws Exception {
		update("SiteDao.updateSite", siteVo);
	}
	
	/** 삭제 */
	public void deleteSite(int siteIdx) throws Exception {
		delete("SiteDao.deleteSite", siteIdx);
	}

	public int selectSiteCnt(SiteVo siteVo) {
		return (Integer)selectOne("SiteDao.selectSiteCnt", siteVo);
	}
}