package egovframework.injeinc.boffice.main.popupZone.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.boffice.main.popupZone.vo.MainPopupZoneVo;

public interface MainPopupZoneSvc {
	
	public void registMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception;
	public void modifyMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception;
	public void modifyMainPopupZoneMzUse(MainPopupZoneVo mainPopupZoneVo) throws Exception;
	public void removeMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception;
	public MainPopupZoneVo retrieveMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception;
	public Map<String, Object> retrievePagMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception;
	public int retrieveMainPopupZoneMaxSort() throws Exception;
	public void modifyMainPopupZoneSortUp(MainPopupZoneVo mainPopupZoneVo) throws Exception;
	public void modifyMainPopupZoneSortDown(MainPopupZoneVo mainPopupZoneVo) throws Exception;
	
}