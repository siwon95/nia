package egovframework.injeinc.foffice.ex.siteMap.service;

import java.util.List;

import egovframework.injeinc.foffice.ex.siteMap.vo.SiteMapVo;

public interface SiteMapSvc {
	
	public List retrieveListUserMenu(SiteMapVo vo) throws Exception;

}
