package egovframework.injeinc.boffice.main.popupZone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.main.popupZone.vo.MainPopupZoneVo;

@Repository("MainPopupZoneDao")
public class MainPopupZoneDao extends EgovAbstractMapper {
	
	public void createMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		insert("MainPopupZoneDao.insertMainPopupZone", mainPopupZoneVo);
	}
	
	public void updateMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		update("MainPopupZoneDao.updateMainPopupZone", mainPopupZoneVo);
	}
	
	public void updateMainPopupZoneMzUse(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		update("MainPopupZoneDao.updateMainPopupZoneMzUse", mainPopupZoneVo);
	}
	
	public void deleteMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		delete("MainPopupZoneDao.deleteMainPopupZone", mainPopupZoneVo);
	}
	
	public MainPopupZoneVo selectMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		return (MainPopupZoneVo)selectOne("MainPopupZoneDao.selectMainPopupZone", mainPopupZoneVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		return selectList("MainPopupZoneDao.selectPagMainPopupZone", mainPopupZoneVo);
	}
	
	public int selectMainPopupZoneCnt(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		return (Integer)selectOne("MainPopupZoneDao.selectMainPopupZoneCnt", mainPopupZoneVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListMainPopupZone(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		return selectList("MainPopupZoneDao.selectListMainPopupZone", mainPopupZoneVo);
	}
	
	public int selectMainPopupZoneMaxSort() throws Exception {
		return (Integer)selectOne("MainPopupZoneDao.selectMainPopupZoneMaxSort", "");
	}
	
	public void updateMainPopupZoneMzSort(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		update("MainPopupZoneDao.updateMainPopupZoneMzSort", mainPopupZoneVo);
	}
	
	public void updateMainPopupZoneMzSortChange(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		update("MainPopupZoneDao.updateMainPopupZoneMzSortChange", mainPopupZoneVo);
	}
	
	public void updateMainPopupZoneMzSortReAlign(MainPopupZoneVo mainPopupZoneVo) throws Exception {
		update("MainPopupZoneDao.updateMainPopupZoneMzSortReAlign", mainPopupZoneVo);
	}
}