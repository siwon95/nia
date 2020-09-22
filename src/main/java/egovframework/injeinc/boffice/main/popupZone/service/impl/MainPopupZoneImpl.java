package egovframework.injeinc.boffice.main.popupZone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.main.popupZone.dao.MainPopupZoneDao;
import egovframework.injeinc.boffice.main.popupZone.service.MainPopupZoneSvc;
import egovframework.injeinc.boffice.main.popupZone.vo.MainPopupZoneVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("MainPopupZoneSvc")
public class MainPopupZoneImpl extends EgovAbstractServiceImpl implements MainPopupZoneSvc {

	@Resource(name = "MainPopupZoneDao")
	private MainPopupZoneDao mainPopupZoneDao;
	
	@Resource(name = "mainPopupZoneIdGnrService")
	private EgovIdGnrService idgenService;

	public void registMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		
		String mzIdx = idgenService.getNextStringId();
		if(mainPopupZoneVo != null){
			mainPopupZoneVo.setMzIdx(mzIdx);
			mainPopupZoneVo.setMzSort(mainPopupZoneDao.selectMainPopupZoneMaxSort()+1);
		}
		mainPopupZoneDao.createMainPopupZone(mainPopupZoneVo);
	}

	public void modifyMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		
		MainPopupZoneVo resultVo = mainPopupZoneDao.selectMainPopupZone(mainPopupZoneVo);
		
		if(mainPopupZoneVo.getMzUse().equals("N") && resultVo.getMzUse().equals("Y")) {
			mainPopupZoneDao.updateMainPopupZoneMzSortReAlign(resultVo);
			mainPopupZoneVo.setMzSort(9999);
		}else if(!mainPopupZoneVo.getMzUse().equals("N") && resultVo.getMzUse().equals("N")) {
			mainPopupZoneVo.setMzSort(mainPopupZoneDao.selectMainPopupZoneMaxSort()+1);
		}
		
		mainPopupZoneDao.updateMainPopupZone(mainPopupZoneVo);
	}

	public void modifyMainPopupZoneMzUse(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		
		if(mainPopupZoneVo.getMzUse().equals("N")) {
			MainPopupZoneVo resultVo = mainPopupZoneDao.selectMainPopupZone(mainPopupZoneVo);
			mainPopupZoneDao.updateMainPopupZoneMzSortReAlign(resultVo);
			mainPopupZoneVo.setMzSort(9999);
		}
		
		mainPopupZoneDao.updateMainPopupZoneMzUse(mainPopupZoneVo);
	}

	public void removeMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		
		MainPopupZoneVo resultVo = mainPopupZoneDao.selectMainPopupZone(mainPopupZoneVo);
		mainPopupZoneDao.updateMainPopupZoneMzSortReAlign(resultVo);
		mainPopupZoneDao.deleteMainPopupZone(mainPopupZoneVo);
	}

	public MainPopupZoneVo retrieveMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		return mainPopupZoneDao.selectMainPopupZone(mainPopupZoneVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		
		List<MainPopupZoneVo> result = mainPopupZoneDao.selectPagMainPopupZone(mainPopupZoneVo);
		int cnt = mainPopupZoneDao.selectMainPopupZoneCnt(mainPopupZoneVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		return mainPopupZoneDao.selectListMainPopupZone(mainPopupZoneVo);
	}

	public int retrieveMainPopupZoneMaxSort() throws Exception {
		return mainPopupZoneDao.selectMainPopupZoneMaxSort();
	}

	public void modifyMainPopupZoneSortUp(MainPopupZoneVo mainPopupZoneVo) throws Exception {		
		int mzSort = mainPopupZoneVo == null ? 0 : mainPopupZoneVo.getMzSort();
		int targetSort = mzSort - 1;
		if(mainPopupZoneVo != null){
			mainPopupZoneVo.setChangeSort(mzSort);
			mainPopupZoneVo.setMzSort(targetSort);
		}
		mainPopupZoneDao.updateMainPopupZoneMzSortChange(mainPopupZoneVo);
		mainPopupZoneDao.updateMainPopupZoneMzSort(mainPopupZoneVo);
		
	}

	public void modifyMainPopupZoneSortDown(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		
		int mzSort = mainPopupZoneVo == null ? 0 : mainPopupZoneVo.getMzSort();
		int targetSort = mzSort + 1;
		if(mainPopupZoneVo != null){
			mainPopupZoneVo.setChangeSort(mzSort);
			mainPopupZoneVo.setMzSort(targetSort);
		}
		mainPopupZoneDao.updateMainPopupZoneMzSortChange(mainPopupZoneVo);
		mainPopupZoneDao.updateMainPopupZoneMzSort(mainPopupZoneVo);
		
	}
}