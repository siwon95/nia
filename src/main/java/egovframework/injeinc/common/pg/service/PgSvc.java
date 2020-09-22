package egovframework.injeinc.common.pg.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.common.pg.vo.PgVo;

public interface PgSvc {
	/** 리스트 조회 */
	@SuppressWarnings("rawtypes")
	public List retrieveListPg(PgVo pgVo) throws Exception;

	/** 총 건수 조회 */
	public int retrievePagPg(PgVo pgVo) throws Exception;

	/** 등록 */
	public void registPg(PgVo pgVo) throws Exception;

	/** PG관리 등록 */
	public void registMgrPg(PgVo pgVo) throws Exception;

	/** 아이디 조회 */
	public int retrieveCstMidCnt(HashMap<String, String> param) throws Exception;

	/** path 조회 */
	public int retrieveConfPathCnt(HashMap<String, String> param) throws Exception;

	/** 수정 */
	public void modifyPg(PgVo pgVo) throws Exception;

	/** 상세조회 */
	public PgVo retrievePg(PgVo pgVo) throws Exception;

	/** 상점아이디,mertKey 조회 */
	public PgVo retrieveMidPg(PgVo pgVo) throws Exception;

	/** 상점아이디로 PG관리 공통코드 조회 */
	public PgVo retrieveCmmPg(PgVo pgVo) throws Exception;

	/** 삭제 */
	public void removePg(PgVo pgVo) throws Exception;

	/** 수정 */
	public void updatePgMgr(PgVo pgVo) throws Exception;

	public void updatePgMissMgr(PgVo pgVo) throws Exception;

	public List<PgVo> retrievePgByMertCodeAx(HashMap<String, String> param) throws Exception;

}