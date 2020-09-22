package egovframework.injeinc.boffice.sy.accessip.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.accessip.vo.AccessIpVo;
import egovframework.injeinc.boffice.sy.prohibit.vo.ProhibitIdVo;

@Repository("AccessIpDao")
public class AccessIpDao extends EgovAbstractMapper {

	public void insertAccessIp(AccessIpVo accessIpVo) throws Exception {
		insert("AccessIpDao.insertAccessIp", accessIpVo);
	}

	public int selectPagAccessIp(AccessIpVo accessIpVo) {
		return (Integer)selectOne("AccessIpDao.selectPagAccessIp", accessIpVo);
	}

	public List selectListAccessIp(AccessIpVo accessIpVo) {
		return selectList("AccessIpDao.selectListAccessIp", accessIpVo);
	}

	public AccessIpVo selectAccessIp(AccessIpVo accessIpVo) {
		return (AccessIpVo)selectOne("AccessIpDao.selectAccessIp", accessIpVo);
	}

	public void updateAccessIp(AccessIpVo accessIpVo) {
		update("AccessIpDao.updateAccessIp", accessIpVo);
	}

	public void updateAccessIpForDelete(AccessIpVo accessIpVo) {
		delete("AccessIpDao.updateAccessIpForDelete",accessIpVo);
	}

	public List selectAllListAccessIp() {
		return selectList("AccessIpDao.selectAllListAccessIp", null);
	}
}