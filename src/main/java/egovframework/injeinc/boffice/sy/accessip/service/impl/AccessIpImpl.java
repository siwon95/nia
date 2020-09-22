package egovframework.injeinc.boffice.sy.accessip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.accessip.dao.AccessIpDao;
import egovframework.injeinc.boffice.sy.accessip.service.AccessIpSvc;
import egovframework.injeinc.boffice.sy.accessip.vo.AccessIpVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("AccessIpSvc")
public class AccessIpImpl extends EgovAbstractServiceImpl implements AccessIpSvc {

	/** ID Generation */
    @Resource(name="AccessIpIdGnrService")    
    private EgovIdGnrService egovIdGnrService;
    
    @Resource(name="AccessIpDao")
	private AccessIpDao accessIpDao;

	
	public void registAccessIp(AccessIpVo accessIpVo) throws Exception {
		
		if(accessIpVo != null){
			accessIpVo.setAi_idx(egovIdGnrService.getNextStringId());
		}
		accessIpDao.insertAccessIp(accessIpVo);
		
	}

	
	public List retrieveListAccessIp(AccessIpVo accessIpVo) throws Exception {
		return accessIpDao.selectListAccessIp(accessIpVo);
	}

	
	public int retrievePagAccessIp(AccessIpVo accessIpVo) throws Exception {
		return accessIpDao.selectPagAccessIp(accessIpVo);
	}

	
	public AccessIpVo retrieveAccessIp(AccessIpVo accessIpVo) throws Exception {
		return accessIpDao.selectAccessIp(accessIpVo);
	}

	
	public void modifyAccessIp(AccessIpVo accessIpVo) throws Exception {
		accessIpDao.updateAccessIp(accessIpVo);
	}

	
	public void removeAccessIp(AccessIpVo accessIpVo) throws Exception {
		accessIpDao.updateAccessIpForDelete(accessIpVo);
	}

	
	public List<AccessIpVo> retrieveAllListAccessIp() throws Exception {
		return accessIpDao.selectAllListAccessIp();
	}
}