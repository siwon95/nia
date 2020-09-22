package egovframework.injeinc.boffice.ex.umsLog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.umsLog.dao.UmsLogDao;
import egovframework.injeinc.boffice.ex.umsLog.service.UmsLogSvc;
import egovframework.injeinc.boffice.ex.umsLog.vo.UmsLogVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("UmsLogSvc")
public class UmsLogImpl extends EgovAbstractServiceImpl implements UmsLogSvc {

	@Resource(name = "UmsLogDao")
	private UmsLogDao umsLogDao;

	@Resource(name = "umsLogIdGnrService")
	private EgovIdGnrService idgenService;


	public UmsLogVo retrieveUmsLog(UmsLogVo umsLogVo) throws Exception {
		return umsLogDao.selectUmsLog(umsLogVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagUmsLog(UmsLogVo umsLogVo) throws Exception {
		
		List<UmsLogVo> result = umsLogDao.selectPagUmsLog(umsLogVo);
		int cnt = umsLogDao.selectUmsLogCnt(umsLogVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
}