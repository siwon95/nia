package egovframework.injeinc.foffice.rss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.rss.vo.RssVo;

@Repository("RssDao")
public class RssDao extends EgovAbstractMapper {
	
	public List selectListEdu(int pLimit) throws Exception {
		return selectList("RssDao.selectListEdu", pLimit);
	}
	
	public List selectListNotice(int pLimit) throws Exception {
		return selectList("RssDao.selectListNotice", pLimit);
	}
	
	public List selectListGonggo(int pLimit) throws Exception {
		return selectList("RssDao.selectListGonggo", pLimit);
	}
	
	public List selectListEvent(int pLimit) throws Exception {
		return selectList("RssDao.selectListEvent", pLimit);
	}
	
	public List selectListGubo(int pLimit) throws Exception {
		return selectList("RssDao.selectListGubo", pLimit);
	}
	
	public List selectListHealthNotice(int pLimit) throws Exception {
		return selectList("RssDao.selectListHealthNotice", pLimit);
	}
	
	public List selectListDh(int pLimit) throws Exception {
		return selectList("RssDao.selectListDh", pLimit);
	}
	
	public List selectListNews(int pLimit) throws Exception {
		return selectList("RssDao.selectListNews", pLimit);
	}
	
}