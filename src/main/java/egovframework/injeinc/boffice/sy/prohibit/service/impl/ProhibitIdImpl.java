package egovframework.injeinc.boffice.sy.prohibit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.prohibit.dao.ProhibitIdDao;
import egovframework.injeinc.boffice.sy.prohibit.service.ProhibitIdSvc;
import egovframework.injeinc.boffice.sy.prohibit.vo.ProhibitIdVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("ProhibitIdSvc")
public class ProhibitIdImpl extends EgovAbstractServiceImpl implements ProhibitIdSvc {

	@Resource(name="ProhibitIdDao")
	private ProhibitIdDao prohibitIdDao;
	
	@Resource(name="prohibitIdIdGnrService")
	private EgovIdGnrService prohibitIdIdService;
	
	public void registProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		if(prohibitIdVo != null){
			prohibitIdVo.setPiIdx(prohibitIdIdService.getNextStringId());
		}
		prohibitIdDao.insertProhibitId(prohibitIdVo);
	}
	
	public void modifyProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		prohibitIdDao.updateProhibitId(prohibitIdVo);
	}
	
	public void removeProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		prohibitIdDao.deleteProhibitId(prohibitIdVo);
	}

	public ProhibitIdVo retrieveProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		return prohibitIdDao.selectProhibitId(prohibitIdVo);
	}

	public int retrieveProhibitIdCnt(ProhibitIdVo prohibitIdVo) throws Exception {
		return prohibitIdDao.selectProhibitIdCnt(prohibitIdVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		return prohibitIdDao.selectListProhibitId(prohibitIdVo);
	}
}