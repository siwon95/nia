package egovframework.injeinc.foffice.ex.myscrap.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.foffice.ex.myscrap.vo.MyscrapVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;


@Repository("MyscrapUserDao")
public class MyscrapUserDao extends EgovAbstractMapper{

	public void insertMyscrap(MyscrapVO myscrapVO) throws Exception{
		insert("MyscrapUserDao.insertMyscrap", myscrapVO);
	}

	@SuppressWarnings("rawtypes")
	public List MyscrapUserList(MyscrapVO myscrapVO) throws Exception{
		return selectList("MyscrapUserDao.selectList", myscrapVO);
	}

	public int MyscrapUserListCnt(MyscrapVO myscrapVO) throws Exception{
		return (Integer)selectOne("MyscrapUserDao.selectListCnt", myscrapVO);
	}
	
	public int MyscrapUserDupCnt(MyscrapVO myscrapVO) throws Exception{
		return (Integer)selectOne("MyscrapUserDao.selectdupCnt", myscrapVO);
	}
	
	public void deleteMyscrap(MyscrapVO myscrapVO) throws Exception{
		delete("MyscrapUserDao.deleteMyscrap", myscrapVO);
		
	}

}
