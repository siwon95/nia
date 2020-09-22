package egovframework.injeinc.boffice.sy.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.board.dao.CmsBbsDao;
import egovframework.injeinc.boffice.sy.board.service.CmsBbsSvc;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("CmsBbsSvc")
public class CmsBbsImpl extends EgovAbstractServiceImpl implements CmsBbsSvc {

	@Resource(name="CmsBbsDao")
	private CmsBbsDao cmsBbsDao;

	public void registCmsBbs(String userid, List<CmsBbsVo> list) throws Exception {
		CmsBbsVo cmsBbsVo = new CmsBbsVo();
		cmsBbsVo.setModId(userid);
		cmsBbsDao.deleteCmsBbs(cmsBbsVo);
		for(CmsBbsVo resultVo : list) {
			
			int count = cmsBbsDao.updateCmsBbs(resultVo);
			System.out.println("count =============== "  + count);
			if(count == 0) {
				cmsBbsDao.createCmsBbs(resultVo);
			}
		}
	}
	
	public CmsBbsVo selectCmsBbs(CmsBbsVo cmsBbsVo) throws Exception {
		return cmsBbsDao.selectCmsBbs(cmsBbsVo);
	}
	
	public List selectCmsBbsMypage(CmsBbsVo cmsBbsVo) throws Exception {
		return cmsBbsDao.selectCmsBbsMypage(cmsBbsVo);
	}

	public int retrieveCmsBbsCnt(CmsBbsVo cmsBbsVo) throws Exception {
		return cmsBbsDao.selectCmsBbsCnt(cmsBbsVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListCmsBbs() throws Exception {
		return cmsBbsDao.selectListCmsBbs();
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListCmsBbsSite(CmsBbsVo cmsBbsVo) throws Exception {
		return cmsBbsDao.selectListCmsBbsSite(cmsBbsVo);
	}
	
	public void createCmsBbs(CmsBbsVo cmsBbsVo) throws Exception {
		cmsBbsDao.createCmsBbs(cmsBbsVo);
	}
	
	public void updateCmsBbs(CmsBbsVo cmsBbsVo) throws Exception {
		cmsBbsDao.updateCmsBbs(cmsBbsVo);
	}
	
	public void deleteCmsBbsList(CmsBbsVo cmsBbsVo) throws Exception {
		cmsBbsDao.deleteCmsBbsList(cmsBbsVo);
	}
	
	public String retrieveSiteCd(String searchCbCd) throws Exception {
		return cmsBbsDao.retrieveSiteCd(searchCbCd);
	}
	
	public String retrieveGroup(String searchCbCd) throws Exception {
		return cmsBbsDao.retrieveGroup(searchCbCd);
	}
	
	public String retrieveCbIdx(String searchCbCd) throws Exception {
		return cmsBbsDao.retrieveCbIdx(searchCbCd);
	}
	
	public String retrieveGroupYn(String searchCbCd) throws Exception {
		return cmsBbsDao.retrieveGroupYn(searchCbCd);
	}

}