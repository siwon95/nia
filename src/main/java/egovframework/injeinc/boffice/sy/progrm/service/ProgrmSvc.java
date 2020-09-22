package egovframework.injeinc.boffice.sy.progrm.service;

import java.util.List;

import egovframework.cmm.ComDefaultVO;
import egovframework.injeinc.boffice.sy.progrm.vo.ProgrmVo;

public interface ProgrmSvc {
	/**
	 * 프로그램 상세정보를 조회
	 * @param vo ComDefaultVO
	 * @return ProgrmVo 
	 * @exception Exception
	 */
	ProgrmVo selectProgrm(ComDefaultVO vo) throws Exception;
	/**
	 * 프로그램 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List 
	 * @exception Exception
	 */
	List selectProgrmList(ComDefaultVO vo) throws Exception;
	/**
	 * 프로그램목록 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	int selectProgrmListTotCnt(ComDefaultVO vo) throws Exception;	
	/**
	 * 프로그램 정보를 등록
	 * @param vo ProgrmVo
	 * @exception Exception
	 */
	void insertProgrm(ProgrmVo vo) throws Exception;

	/**
	 * 프로그램 정보를 수정
	 * @param vo ProgrmVo
	 * @exception Exception
	 */
	void updateProgrm(ProgrmVo vo) throws Exception;

	/**
	 * 프로그램 정보를 삭제
	 * @param vo ProgrmVo
	 * @exception Exception
	 */
	void deleteProgrm(ProgrmVo vo) throws Exception;

	/**
	 * 프로그램 중복 건수를 조회한다.
	 * @param vo ProgrmVo
	 * @return int
	 * @exception Exception
	 */
	int selectProgrmDupCnt(ProgrmVo vo) throws Exception;	
	
	/**
	 * 프로그램 그룹정보 조회
	 * @param String url
	 * @return String 
	 * @exception Exception
	 */
	String selectProgrmGrp(String url) throws Exception;
	
}