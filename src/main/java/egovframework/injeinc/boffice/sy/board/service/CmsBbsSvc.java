package egovframework.injeinc.boffice.sy.board.service;

import java.util.List;

import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;

public interface CmsBbsSvc {
	
	public void registCmsBbs(String userid, List<CmsBbsVo> list) throws Exception;
	public int retrieveCmsBbsCnt(CmsBbsVo cmsBbsVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListCmsBbs() throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListCmsBbsSite(CmsBbsVo cmsBbsVo) throws Exception;
	

	public CmsBbsVo selectCmsBbs(CmsBbsVo cmsBbsVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List selectCmsBbsMypage(CmsBbsVo cmsBbsVo) throws Exception;
	
	public void createCmsBbs(CmsBbsVo cmsBbsVo) throws Exception;
	
	public void updateCmsBbs(CmsBbsVo cmsBbsVo) throws Exception;
	
	public void deleteCmsBbsList(CmsBbsVo cmsBbsVo) throws Exception;
	
	public String retrieveSiteCd(String searchCbCd) throws Exception;
	
	public String retrieveGroup(String searchCbCd) throws Exception;
	
	public String retrieveCbIdx(String searchCbCd) throws Exception;
	
	public String retrieveGroupYn(String searchCbCd) throws Exception;
}