package egovframework.injeinc.common.pg.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.common.pg.vo.PgVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * <PRE>
 * 1. FileName : EduDao.java
 * 2. Package  : egovframework.injeinc.common.edu.dao;
 * 3. Comment  : 
 * 4. Programmer   : ldy
 * 5. Date   : 2015.05.08
 * </PRE>
 */
@Repository("PgDao")
public class PgDao extends EgovAbstractMapper {

	/** pg 리스트 조회 */
	@SuppressWarnings("rawtypes")
	public List selectListPg(PgVo pgVo) throws Exception {
		return selectList("PgDao.selectListPg", pgVo);
	}

	/** 총건수 조회 */
	public int selectPagPg(PgVo pgVo) throws Exception {
		return (Integer) selectOne("PgDao.selectPagPg", pgVo);
	}

	/** 등록 */
	public void insertPg(PgVo pgVo) throws Exception {
		insert("PgDao.insertPg", pgVo);
	}

	/** PG관리 등록 */
	public void insertMgrPg(PgVo pgVo) throws Exception {
		insert("PgDao.insertMgrPg", pgVo);
	}

	/** 아이디 조회 */
	public int selectCstMidCnt(HashMap<String, String> param) throws Exception {
		return (Integer) selectOne("PgDao.selectCstMidCnt", param);
	}

	/** 수정 */
	public void updatePg(PgVo pgVo) throws Exception {
		update("PgDao.updatePg", pgVo);
	}

	/** 상세조회 */
	public PgVo selectPg(PgVo pgVo) throws Exception {
		return (PgVo) selectOne("PgDao.selectPg", pgVo);
	}

	/** 상세조회 */
	public PgVo selectMidPg(PgVo pgVo) throws Exception {
		return (PgVo) selectOne("PgDao.selectMidPg", pgVo);
	}

	/** 상세조회 */
	public PgVo selectCmmPg(PgVo pgVo) throws Exception {
		return (PgVo) selectOne("PgDao.selectCmmPg", pgVo);
	}

	/** path 조회 */
	public int selectConfPathCnt(HashMap<String, String> param) throws Exception {
		return (Integer) selectOne("PgDao.selectConfPathCnt", param);
	}

	/** 삭제 */
	public void deletePg(PgVo pgVo) throws Exception {
		delete("PgDao.deletePg", pgVo);
	}

	/** 수정 */
	public void updatePgMgr(PgVo pgVo) throws Exception {
		update("PgDao.updatePgMgr", pgVo);
	}

	/** 수정 */
	public void updatePgMissMgr(PgVo pgVo) throws Exception {
		update("PgDao.updatePgMissMgr", pgVo);
	}

	public List<PgVo> selectPgByMertCodeAx(HashMap<String, String> param) throws Exception {
		return selectList("PgDao.selectPgByMertCodeAx", param);
	}

}