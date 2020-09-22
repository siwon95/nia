package egovframework.injeinc.boffice.sy.prohibit.service;

import java.util.List;

import egovframework.injeinc.boffice.sy.prohibit.vo.ProhibitIdVo;

public interface ProhibitIdSvc {
	
	public void registProhibitId(ProhibitIdVo prohibitIdVO) throws Exception;
	public void modifyProhibitId(ProhibitIdVo prohibitIdVO) throws Exception;
	public void removeProhibitId(ProhibitIdVo prohibitIdVO) throws Exception;
	public ProhibitIdVo retrieveProhibitId(ProhibitIdVo prohibitIdVo) throws Exception;
	public int retrieveProhibitIdCnt(ProhibitIdVo prohibitIdVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListProhibitId(ProhibitIdVo prohibitIdVO) throws Exception;
	
}