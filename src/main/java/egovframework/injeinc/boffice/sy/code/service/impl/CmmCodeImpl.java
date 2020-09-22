package egovframework.injeinc.boffice.sy.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.code.dao.CmmCodeDao;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("CmmCodeSvc")
public class CmmCodeImpl extends EgovAbstractServiceImpl implements CmmCodeSvc {

	@Resource(name = "CmmCodeDao")
	private CmmCodeDao cmmCodeDao;
	
	public void registCmmCode(CmmCodeVo cmmCodeVo) throws Exception {
		cmmCodeDao.createCmmCode(cmmCodeVo);
	}

	public void removeCmmCode(CmmCodeVo cmmCodeVo) throws Exception {
		cmmCodeDao.deleteCmmCode(cmmCodeVo);
	}

	public CmmCodeVo retrieveCmmCode(String code) throws Exception {
		return cmmCodeDao.selectCmmCode(code);
	}

	public List<CmmCodeVo> retrieveListCmmCode(String codeUpr) throws Exception {
		return cmmCodeDao.selectListCmmCode(codeUpr);
	}
	
	public List<CmmCodeVo> retrieveListSubCmmCode(String codeUpr) throws Exception {
		return cmmCodeDao.selectListSubCmmCode(codeUpr);
	}
	
	public List retrieveListCmmCodeForResEtcCode(boolean isAdmin, List mgrSiteCdList) throws Exception {
		return cmmCodeDao.selectListCmmCodeForResEtcCode(isAdmin, mgrSiteCdList);
	}
	
}