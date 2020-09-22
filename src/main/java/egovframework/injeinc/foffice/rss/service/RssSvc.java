package egovframework.injeinc.foffice.rss.service;

import java.util.List;

public interface RssSvc {
	
	public List retrieveListEdu(int pLimit) throws Exception;
	public List retrieveListNotice(int pLimit) throws Exception;
	public List retrieveListGonggo(int pLimit) throws Exception;
	public List retrieveListEvent(int pLimit) throws Exception;
	public List retrieveListGubo(int pLimit) throws Exception;
	public List retrieveListHealthNotice(int pLimit) throws Exception;
	public List retrieveListDh(int pLimit) throws Exception;
	public List retrieveListNews(int pLimit) throws Exception;
	
}