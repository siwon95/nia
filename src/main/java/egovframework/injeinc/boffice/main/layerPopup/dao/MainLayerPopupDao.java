package egovframework.injeinc.boffice.main.layerPopup.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.main.layerPopup.vo.MainLayerPopupVo;

@Repository("MainLayerPopupDao")
public class MainLayerPopupDao extends EgovAbstractMapper {
	
	public void createMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		insert("MainLayerPopupDao.insertMainLayerPopup", mainLayerPopupVo);
	}
	
	public void updateMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		update("MainLayerPopupDao.updateMainLayerPopup", mainLayerPopupVo);
	}
	
	public void updateMainLayerPopupMlUse(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		update("MainLayerPopupDao.updateMainLayerPopupMlUse", mainLayerPopupVo);
	}
	
	public void deleteMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		delete("MainLayerPopupDao.deleteMainLayerPopup", mainLayerPopupVo);
	}
	
	public MainLayerPopupVo selectMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		return (MainLayerPopupVo)selectOne("MainLayerPopupDao.selectMainLayerPopup", mainLayerPopupVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagMainLayerPopup(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		return selectList("MainLayerPopupDao.selectPagMainLayerPopup", mainLayerPopupVo);
	}
	
	public int selectMainLayerPopupCnt(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		return (Integer)selectOne("MainLayerPopupDao.selectMainLayerPopupCnt", mainLayerPopupVo);
	}

	public MainLayerPopupVo selectMainLayerPopupOne(MainLayerPopupVo mainLayerPopupVo) throws Exception {
		return (MainLayerPopupVo)selectOne("MainLayerPopupDao.selectMainLayerPopupOne", mainLayerPopupVo);
	}
}