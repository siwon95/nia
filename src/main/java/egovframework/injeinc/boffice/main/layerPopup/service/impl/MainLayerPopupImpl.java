package egovframework.injeinc.boffice.main.layerPopup.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.main.layerPopup.dao.MainLayerPopupDao;
import egovframework.injeinc.boffice.main.layerPopup.service.MainLayerPopupSvc;
import egovframework.injeinc.boffice.main.layerPopup.vo.MainLayerPopupVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("MainLayerPopupSvc")
public class MainLayerPopupImpl extends EgovAbstractServiceImpl implements MainLayerPopupSvc {

	@Resource(name = "MainLayerPopupDao")
	private MainLayerPopupDao MainLayerPopupDao;
	
	@Resource(name = "mainLayerPopupIdGnrService")
	private EgovIdGnrService idgenService;

	public void registMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		
		String emIdx = idgenService.getNextStringId();
		if(mainLayerPopupVo != null){
			mainLayerPopupVo.setMlIdx(emIdx);
		}
		MainLayerPopupDao.createMainLayerPopup(mainLayerPopupVo);
	}

	public void modifyMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		MainLayerPopupDao.updateMainLayerPopup(mainLayerPopupVo);
	}

	public void modifyMainLayerPopupMlUse(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		MainLayerPopupDao.updateMainLayerPopupMlUse(mainLayerPopupVo);
	}

	public void removeMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		MainLayerPopupDao.deleteMainLayerPopup(mainLayerPopupVo);
	}

	public MainLayerPopupVo retrieveMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		return MainLayerPopupDao.selectMainLayerPopup(mainLayerPopupVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		
		List<MainLayerPopupVo> result = MainLayerPopupDao.selectPagMainLayerPopup(mainLayerPopupVo);
		int cnt = MainLayerPopupDao.selectMainLayerPopupCnt(mainLayerPopupVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	public MainLayerPopupVo retrieveMainLayerPopupOne(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		return MainLayerPopupDao.selectMainLayerPopupOne(mainLayerPopupVo);
	}
}