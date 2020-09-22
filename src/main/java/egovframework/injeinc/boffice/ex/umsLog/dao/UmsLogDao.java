package egovframework.injeinc.boffice.ex.umsLog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.umsLog.vo.UmsLogVo;

@Repository("UmsLogDao")
public class UmsLogDao extends EgovAbstractMapper {
	
	public void createUmsLog(UmsLogVo umsLogVo) throws Exception {
		insert("UmsLogDao.insertUmsLog", umsLogVo);
	}
	
	public UmsLogVo selectUmsLog(UmsLogVo umsLogVo) throws Exception {
		return (UmsLogVo)selectOne("UmsLogDao.selectUmsLog", umsLogVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectPagUmsLog(UmsLogVo umsLogVo) throws Exception {
		return selectList("UmsLogDao.selectPagUmsLog", umsLogVo);
	}
	
	public int selectUmsLogCnt(UmsLogVo umsLogVo) throws Exception {
		return (Integer)selectOne("UmsLogDao.selectUmsLogCnt", umsLogVo);
	}
	
}