package egovframework.injeinc.boffice.pledge.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.injeinc.boffice.pledge.vo.PledgeFileVo;

public interface PledgeFileSvc {

	void registPledgeFile(HttpServletRequest request, PledgeFileVo pledgeFileVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	List retrieveListPledgeFile(PledgeFileVo pledgeFileVo) throws Exception;

	PledgeFileVo retrievePledgeFile(PledgeFileVo pledgeFileVo) throws Exception;

	void removeContentFile(PledgeFileVo pledgeFileVo) throws Exception;
	
}