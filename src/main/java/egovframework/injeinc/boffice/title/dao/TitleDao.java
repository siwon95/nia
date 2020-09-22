package egovframework.injeinc.boffice.title.dao;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("TitleDao")
public class TitleDao extends EgovAbstractMapper {
	
	public EgovMap selectTitle(String cmmCode) throws Exception {
		return (EgovMap)selectOne("TitleDao.selectTitle", cmmCode);
	}
	
}