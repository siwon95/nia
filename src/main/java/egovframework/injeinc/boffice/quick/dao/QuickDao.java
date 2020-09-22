package egovframework.injeinc.boffice.quick.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.quick.vo.QuickVo;

@Repository("QuickDao")
public class QuickDao extends EgovAbstractMapper {

	public void insertQuick(QuickVo quickVO) throws Exception {
		insert("QuickDao.insertQuick", quickVO);
	}
	
	public void updateQuick(QuickVo quickVO) throws Exception {
		update("QuickDao.updateQuick", quickVO);
	}
	
	public void updateQuickEtcInfo(QuickVo quickVO) throws Exception {
		update("QuickDao.updateQuickEtcInfo", quickVO);
	}
	
	public void deleteQuick(QuickVo quickVO) throws Exception {
		delete("QuickDao.deleteQuick", quickVO);
	}
	
	public QuickVo selectQuick(QuickVo quickVO) throws Exception {
		return (QuickVo)selectOne("QuickDao.selectQuick", quickVO);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectPagQuick(QuickVo quickVO) throws Exception {
		return selectList("QuickDao.selectPagQuick", quickVO);
	}
	
	public int selectQuickCnt(QuickVo quickVO) throws Exception {
		return (Integer)selectOne("QuickDao.selectQuickCnt", quickVO);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListQuick(QuickVo quickVO) throws Exception {
		return selectList("QuickDao.selectListQuick", quickVO);
	}
	
	public int selectQuickMaxSort(QuickVo quickVo) throws Exception {
		return (Integer)selectOne("QuickDao.selectQuickMaxSort", quickVo);
	}
	
	public void updateQuickCqlSort(QuickVo quickVo) throws Exception {
		update("QuickDao.updateQuickCqlSort", quickVo);
	}
	
	public void updateQuickCqlSortChange(QuickVo quickVo) throws Exception {
		update("QuickDao.updateQuickCqlSortChange", quickVo);
	}
	
	public void updateQuickCqlSortReAlign(QuickVo quickVo) throws Exception {
		update("QuickDao.updateQuickCqlSortReAlign", quickVo);
	}
}