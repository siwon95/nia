package egovframework.injeinc.boffice.ex.snsLog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.snsLog.vo.SnsLogVo;

@Repository("SnsLogDao")
public class SnsLogDao extends EgovAbstractMapper {
	
	public void createSnsLog(SnsLogVo SnsLogVo) throws Exception {
		insert("SnsLogDao.insertSnsLog", SnsLogVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListSnsLog(SnsLogVo SnsLogVo) throws Exception {
		return selectList("SnsLogDao.selectListSnsLog", SnsLogVo);
	}
	
}