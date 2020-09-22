package egovframework.injeinc.boffice.quick.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.quick.dao.QuickDao;
import egovframework.injeinc.boffice.quick.service.QuickSvc;
import egovframework.injeinc.boffice.quick.vo.QuickVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("QuickSvc")
public class QuickImpl extends EgovAbstractServiceImpl implements QuickSvc {

	@Resource(name="QuickDao")
	private QuickDao quickDao;
	
	@Resource(name="quickIdService")
	private EgovIdGnrService quickIdService;
	
	public void registQuick(QuickVo quickVo) throws Exception {
		if(quickVo != null){
			quickVo.setCqlIdx(quickIdService.getNextStringId());
		}
		
		if(quickVo.getCqlUse().equals("Y")) {
			quickVo.setCqlSort(quickDao.selectQuickMaxSort(quickVo)+1);
		}else {
			quickVo.setCqlSort(0);
		}
		
		quickDao.insertQuick(quickVo);
	}
	
	public void modifyQuick(QuickVo quickVo) throws Exception {
		QuickVo resultVo = quickDao.selectQuick(quickVo);
		
		if(!quickVo.getCqlUse().equals(resultVo.getCqlUse())) {

			if(quickVo.getCqlUse().equals("Y")) {
				quickVo.setCqlSort(quickDao.selectQuickMaxSort(quickVo)+1);
			}else if(quickVo.getCqlUse().equals("N")) {
				quickDao.updateQuickCqlSortReAlign(resultVo);
				quickVo.setCqlSort(0);
			}
			
		}else if(!quickVo.getCqlCode().equals(resultVo.getCqlCode())) {

			if(quickVo.getCqlUse().equals("Y")) {
				quickDao.updateQuickCqlSortReAlign(resultVo);
				quickVo.setCqlSort(quickDao.selectQuickMaxSort(quickVo)+1);
			}else{
				quickVo.setCqlSort(resultVo.getCqlSort());
			}
			
		}else{
			quickVo.setCqlSort(resultVo.getCqlSort());
		}
		
		quickDao.updateQuick(quickVo);
	}
	
	public void modifyQuickEtcInfo(QuickVo quickVo) throws Exception {
		if(quickVo.getCqlUse().equals("Y")) {
			QuickVo resultVo = quickDao.selectQuick(quickVo);
			quickVo.setCqlCode(resultVo.getCqlCode());
		}else if(quickVo.getCqlUse().equals("N")) {
			QuickVo resultVo = quickDao.selectQuick(quickVo);
			quickDao.updateQuickCqlSortReAlign(resultVo);
		}
		quickDao.updateQuickEtcInfo(quickVo);
	}
	
	public void removeQuick(QuickVo quickVo) throws Exception {
		
		if(quickVo.getCqlUse().equals("Y")) {
			quickDao.updateQuickCqlSortReAlign(quickVo);
		}
		
		quickDao.deleteQuick(quickVo);
	}

	public QuickVo retrieveQuick(QuickVo quickVo) throws Exception {
		return quickDao.selectQuick(quickVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagQuick(QuickVo quickVo) throws Exception {
		
		List<QuickVo> result = quickDao.selectPagQuick(quickVo);
		int cnt = quickDao.selectQuickCnt(quickVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));
		

		return map;
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListQuick(QuickVo quickVo) throws Exception {
		return quickDao.selectListQuick(quickVo);
	}

	public int retrieveQuickMaxSort(QuickVo quickVo) throws Exception {
		return quickDao.selectQuickMaxSort(quickVo);
	}

	public void modifyQuickSortUp(QuickVo quickVo) throws Exception {
		
		int cqlSort = quickVo == null ? 0 : quickVo.getCqlSort();
		int targetSort = cqlSort - 1;
		if(quickVo != null){
			quickVo.setChangeSort(cqlSort);
			quickVo.setCqlSort(targetSort);
		}
		quickDao.updateQuickCqlSortChange(quickVo);
		quickDao.updateQuickCqlSort(quickVo);
		
	}

	public void modifyQuickSortDown(QuickVo quickVo) throws Exception {
		int cqlSort = quickVo == null ? 0 : quickVo.getCqlSort();		
		int targetSort = cqlSort + 1;
		if(quickVo != null){
			quickVo.setChangeSort(cqlSort);
			quickVo.setCqlSort(targetSort);
		}
		quickDao.updateQuickCqlSortChange(quickVo);
		quickDao.updateQuickCqlSort(quickVo);
		
	}
}