package egovframework.injeinc.boffice.quick.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.boffice.quick.vo.QuickVo;

public interface QuickSvc {
	
	public void registQuick(QuickVo quickVO) throws Exception;
	public void modifyQuick(QuickVo quickVO) throws Exception;
	public void modifyQuickEtcInfo(QuickVo quickVO) throws Exception;
	public void removeQuick(QuickVo quickVO) throws Exception;
	public QuickVo retrieveQuick(QuickVo quickVo) throws Exception;
	public Map<String, Object> retrievePagQuick(QuickVo quickVO) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListQuick(QuickVo quickVO) throws Exception;
	public int retrieveQuickMaxSort(QuickVo quickVO) throws Exception;
	public void modifyQuickSortUp(QuickVo quickVO) throws Exception;
	public void modifyQuickSortDown(QuickVo quickVO) throws Exception;
	
}