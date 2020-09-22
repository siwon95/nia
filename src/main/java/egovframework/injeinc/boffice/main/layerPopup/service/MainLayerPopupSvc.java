package egovframework.injeinc.boffice.main.layerPopup.service;

import java.util.Map;

import egovframework.injeinc.boffice.main.layerPopup.vo.MainLayerPopupVo;

public interface MainLayerPopupSvc {
	
	public void registMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception;
	public void modifyMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception;
	public void modifyMainLayerPopupMlUse(MainLayerPopupVo mainLayerPopupVo) throws Exception;
	public void removeMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception;
	public MainLayerPopupVo retrieveMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception;
	public Map<String, Object> retrievePagMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception;
	public MainLayerPopupVo retrieveMainLayerPopupOne(MainLayerPopupVo mainLayerPopupVo) throws Exception;
	
}