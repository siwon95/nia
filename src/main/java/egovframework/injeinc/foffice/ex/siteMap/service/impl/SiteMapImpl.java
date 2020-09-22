package egovframework.injeinc.foffice.ex.siteMap.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.foffice.ex.siteMap.dao.SiteMapDao;
import egovframework.injeinc.foffice.ex.siteMap.service.SiteMapSvc;
import egovframework.injeinc.foffice.ex.siteMap.vo.SiteMapVo;

@Service(value="SiteMapSvc")
public class SiteMapImpl implements SiteMapSvc{
	
	@Resource(name = "SiteMapDao")
	private SiteMapDao siteMapDao;
	
	public List retrieveListUserMenu(SiteMapVo vo) throws Exception {
		return siteMapDao.selectListUserMenu(vo);
	}
	
}
