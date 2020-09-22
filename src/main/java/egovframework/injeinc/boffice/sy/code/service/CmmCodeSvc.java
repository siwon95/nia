package egovframework.injeinc.boffice.sy.code.service;

import java.util.List;

import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;

public interface CmmCodeSvc {
	
	public void registCmmCode(CmmCodeVo cmmCodeVo) throws Exception;
	public void removeCmmCode(CmmCodeVo cmmCodeVo) throws Exception;
	public CmmCodeVo retrieveCmmCode(String code) throws Exception;
	@SuppressWarnings("rawtypes")
	public List<CmmCodeVo> retrieveListCmmCode(String codeUpr) throws Exception;
	@SuppressWarnings("rawtypes")
	public List<CmmCodeVo> retrieveListSubCmmCode(String codeUpr) throws Exception;
	public List retrieveListCmmCodeForResEtcCode(boolean isAdmin, List mgrSiteCdList) throws Exception;
	
}