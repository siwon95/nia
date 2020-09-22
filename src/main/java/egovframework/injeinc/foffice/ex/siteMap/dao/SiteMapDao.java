package egovframework.injeinc.foffice.ex.siteMap.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.ex.siteMap.vo.SiteMapVo;

@Repository(value="SiteMapDao")
public class SiteMapDao extends EgovAbstractMapper {
	
	public List selectListUserMenu(SiteMapVo vo) throws Exception {
		return selectList("SiteMapDao.selectListUserMenu", vo);
	}

}
