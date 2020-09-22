package egovframework.injeinc.boffice.pledge.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import egovframework.injeinc.boffice.pledge.vo.PledgeVo;

public interface PledgeSvc {
	@SuppressWarnings("rawtypes")
	public List retrieveListWiwid(int wiwParent) throws Exception;
	
	public int registpledge(HttpServletRequest request, PledgeVo pledgeVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrievePagePledge(PledgeVo pledgeVo) throws Exception;
	
	public int retrieveTotalCntPledge(PledgeVo pledgeVo) throws Exception;

	public PledgeVo retrievePledge(PledgeVo pledgeVo) throws Exception;

	public void modifyPledge(HttpServletRequest request, PledgeVo pledgeVo) throws Exception;

	public void removePledge(PledgeVo pledgeVo) throws Exception;

	@SuppressWarnings("rawtypes")
	public List retrieveListPledge(PledgeVo pledgeVo) throws Exception;

}