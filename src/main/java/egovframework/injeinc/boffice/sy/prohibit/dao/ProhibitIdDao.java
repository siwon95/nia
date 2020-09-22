package egovframework.injeinc.boffice.sy.prohibit.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.prohibit.vo.ProhibitIdVo;

@Repository("ProhibitIdDao")
public class ProhibitIdDao extends EgovAbstractMapper {

	public void insertProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		insert("ProhibitIdDao.insertProhibitId", prohibitIdVo);
	}
	
	public void updateProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		update("ProhibitIdDao.updateProhibitId", prohibitIdVo);
	}
	
	public void deleteProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		delete("ProhibitIdDao.deleteProhibitId", prohibitIdVo);
	}
	
	public ProhibitIdVo selectProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		return (ProhibitIdVo)selectOne("ProhibitIdDao.selectProhibitId", prohibitIdVo);
	}
	
	public int selectProhibitIdCnt(ProhibitIdVo prohibitIdVo) throws Exception {
		return (Integer)selectOne("ProhibitIdDao.selectProhibitIdCnt", prohibitIdVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListProhibitId(ProhibitIdVo prohibitIdVo) throws Exception {
		return selectList("ProhibitIdDao.selectListProhibitId", prohibitIdVo);
	}
}