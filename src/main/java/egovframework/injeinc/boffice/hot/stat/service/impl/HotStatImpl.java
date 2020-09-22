package egovframework.injeinc.boffice.hot.stat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.hot.stat.dao.HotStatDao;
import egovframework.injeinc.boffice.hot.stat.service.HotStatSvc;
import egovframework.injeinc.boffice.hot.stat.vo.HotStatVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("HotStatSvc")
public class HotStatImpl extends EgovAbstractServiceImpl implements
        HotStatSvc {
    
    @Resource(name="HotStatDao")
    private HotStatDao hotStatDao;

    public List retrieveListHotStatMonth(HotStatVo hotStatVO) throws Exception {
		return hotStatDao.selectListHotStatMonth(hotStatVO);
	}
    
	public List retrieveListHotStatDay(HotStatVo hotStatVO) throws Exception {
		return hotStatDao.selectListHotStatDay(hotStatVO);
	}
	
	public int retrievePagHotStat(HotStatVo hotStatVO) throws Exception {
		return hotStatDao.selectPagHotStat(hotStatVO);
	}

	public void modifyHotViewCnt(String hlIdx) throws Exception {
		hotStatDao.updateHotViewCnt(hlIdx);
	}
}