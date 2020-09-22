package egovframework.injeinc.foffice.ex.bbs.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.foffice.ex.bbs.vo.BbsCustomFVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface BbsCustomFSvc {
	
	public BbsCustomFVo retrieveBbsCustomF(BbsCustomFVo bbsCustomFVo) throws Exception;
	public Map<String, Object> retrievePagBbsCustomF(BbsCustomFVo bbsCustomFVo) throws Exception;
	public EgovMap retrieveBbsCustomFPrevNext(BbsCustomFVo bbsCustomFVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListBbsCustomFLatest(BbsCustomFVo bbsCustomFVo) throws Exception;
	public BbsCustomFVo retrieveListBbsCustomFLatestOne(BbsCustomFVo bbsCustomFVo) throws Exception;
	public int registBbsCustomFIdea(BbsCustomFVo bbsCustomFVo) throws Exception;
	
}