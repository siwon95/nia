package egovframework.injeinc.foffice.pledge.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import egovframework.injeinc.foffice.pledge.vo.PledgeFVo;

public interface PledgeFSvc {
	@SuppressWarnings("rawtypes")
	public List retrieveListWiwid(int wiwParent) throws Exception;
	
	public int registpledge(HttpServletRequest request, PledgeFVo pledgeFVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrievePagePledge(PledgeFVo pledgeFVo) throws Exception;
	
	public int retrieveTotalCntPledge(PledgeFVo pledgeFVo) throws Exception;

	public PledgeFVo retrievePledge(PledgeFVo pledgeFVo) throws Exception;

	public void modifyPledge(HttpServletRequest request, PledgeFVo pledgeFVo) throws Exception;

	public void removePledge(PledgeFVo pledgeFVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListPledge(PledgeFVo pledgeFVo) throws Exception;

	public void PledgeCountUpdate(PledgeFVo result) throws Exception;

	public PledgeFVo retrievePledgeRecommend(PledgeFVo pledgeFVo) throws Exception;

	public void registPledgeRecommend(PledgeFVo pledgeFVo) throws Exception;

}