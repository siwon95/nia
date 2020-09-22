package egovframework.injeinc.foffice.rss.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.foffice.rss.dao.RssDao;
import egovframework.injeinc.foffice.rss.service.RssSvc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("RssSvc")
public class RssImpl extends EgovAbstractServiceImpl implements RssSvc {

	@Resource(name = "RssDao")
	private RssDao rssDao;
	
	public List retrieveListEdu(int pLimit) throws Exception {
		return rssDao.selectListEdu(pLimit);
	}
	public List retrieveListNotice(int pLimit) throws Exception {
		return rssDao.selectListNotice(pLimit);
	}
	public List retrieveListGonggo(int pLimit) throws Exception {
		return rssDao.selectListGonggo(pLimit);
	}
	public List retrieveListEvent(int pLimit) throws Exception {
		return rssDao.selectListEvent(pLimit);
	}
	public List retrieveListGubo(int pLimit) throws Exception {
		return rssDao.selectListGubo(pLimit);
	}
	public List retrieveListHealthNotice(int pLimit) throws Exception {
		return rssDao.selectListHealthNotice(pLimit);
	}
	public List retrieveListDh(int pLimit) throws Exception {
		return rssDao.selectListDh(pLimit);
	}
	public List retrieveListNews(int pLimit) throws Exception {
		return rssDao.selectListNews(pLimit);
	}
	
}