package egovframework.injeinc.boffice.sy.code.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;

@Repository("CmmCodeDao")
public class CmmCodeDao extends EgovAbstractMapper {
	
	public void createCmmCode(CmmCodeVo cmmCodeVo) throws Exception {
		insert("CmmCodeDao.insertCmmCode", cmmCodeVo);
	}
	
	public void deleteCmmCode(CmmCodeVo cmmCodeVo) throws Exception {
		delete("CmmCodeDao.deleteCmmCode", cmmCodeVo);
	}
	
	public CmmCodeVo selectCmmCode(String code) throws Exception {
		return (CmmCodeVo)selectOne("CmmCodeDao.selectCmmCode", code);
	}

	@SuppressWarnings("unchecked")
	public List<CmmCodeVo> selectListCmmCode(String codeUpr) throws Exception {
		return selectList("CmmCodeDao.selectListCmmCode", codeUpr);
	}

	@SuppressWarnings("unchecked")
	public List<CmmCodeVo> selectListSubCmmCode(String codeUpr) {
		return selectList("CmmCodeDao.selectListSubCmmCode", codeUpr);
	}

	public List selectListCmmCodeForResEtcCode(boolean isAdmin,List mgrSiteCdList) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("isAdmin", isAdmin);
		hashMap.put("mgrSiteCdList", mgrSiteCdList);
		return selectList("CmmCodeDao.selectListCmmCodeForResEtcCode", hashMap);
	}
	
}