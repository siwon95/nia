package egovframework.injeinc.boffice.sy.progrm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.cmm.ComDefaultVO;
import egovframework.injeinc.boffice.sy.progrm.vo.ProgrmVo;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("progrmDao")
public class ProgrmDao extends EgovAbstractMapper {

	/**
	 * 프로그램 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List 
	 * @exception Exception
	 */

	public List selectProgrmList(ComDefaultVO vo) throws Exception{
		return selectList("progrmDao.selectProgrmList", vo);
	}

    /**
	 * 프로그램목록 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
    public int selectProgrmListTotCnt(ComDefaultVO vo) {
        return (Integer)selectOne("progrmDao.selectProgrmListTotCnt", vo);
    }	
	
	/**
	 * 프로그램 기본정보를 조회
	 * @param vo ComDefaultVO
	 * @return ProgrmVo 
	 * @exception Exception
	 */
	public ProgrmVo selectProgrm(ComDefaultVO vo)throws Exception{
		return (ProgrmVo)selectOne("progrmDao.selectProgrm", vo); 
	}

	/**
	 * 프로그램 기본정보 및 URL을 등록
	 * @param vo ProgrmVo
	 * @exception Exception
	 */
	public void insertProgrm(ProgrmVo vo){
		insert("progrmDao.insertProgrm", vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 수정
	 * @param vo ProgrmVo
	 * @exception Exception
	 */
	public void updateProgrm(ProgrmVo vo){
		update("progrmDao.updateProgrm", vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 삭제
	 * @param vo ProgrmVo
	 * @exception Exception
	 */
	public void deleteProgrm(ProgrmVo vo){
		delete("progrmDao.deleteProgrm", vo);
	}
	
	/**
	 * 프로그램 중복건수를 조회한다.
	 * @param vo ProgrmVo
	 * @return int
	 * @exception Exception
	 */
    public int selectProgrmDupCnt(ProgrmVo vo) {
        return (Integer)selectOne("progrmDao.selectProgrmDupCnt", vo);
    }	
    
    /**
	 * 프로그램 그룹정보 조회
	 * @param String url
	 * @return String 
	 * @exception Exception
	 */
	public String selectProgrmGrp(String url)throws Exception{
		return (String)selectOne("progrmDao.selectProgrmGrp", url); 
	}

}