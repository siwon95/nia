package egovframework.injeinc.boffice.sy.progrm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.cmm.ComDefaultVO;
import egovframework.injeinc.boffice.sy.progrm.dao.ProgrmDao;
import egovframework.injeinc.boffice.sy.progrm.service.ProgrmSvc;
import egovframework.injeinc.boffice.sy.progrm.vo.ProgrmVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("progrmSvc")
public class ProgrmImpl extends EgovAbstractServiceImpl implements ProgrmSvc {

	@Resource(name="progrmDao")
    private ProgrmDao progrmDao;


	/**
	 * 프로그램 상세정보를 조회
	 * @param vo ComDefaultVO
	 * @return ProgrmVo 
	 * @exception Exception
	 */
	public ProgrmVo selectProgrm(ComDefaultVO vo) throws Exception{
         	return progrmDao.selectProgrm(vo);
	}
	/**
	 * 프로그램 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List 
	 * @exception Exception
	 */
	public List selectProgrmList(ComDefaultVO vo) throws Exception {
   		return progrmDao.selectProgrmList(vo);
	}
	/**
	 * 프로그램목록 총건수를 조회한다.
	 * @param vo  ComDefaultVO
	 * @return Integer
	 * @exception Exception
	 */
    public int selectProgrmListTotCnt(ComDefaultVO vo) throws Exception {
        return progrmDao.selectProgrmListTotCnt(vo);
	}
	/**
	 * 프로그램 정보를 등록
	 * @param vo ProgrmVo
	 * @exception Exception
	 */
	public void insertProgrm(ProgrmVo vo) throws Exception {
    	progrmDao.insertProgrm(vo);
	}

	/**
	 * 프로그램 정보를 수정
	 * @param vo ProgrmVo
	 * @exception Exception
	 */
	public void updateProgrm(ProgrmVo vo) throws Exception {
    	progrmDao.updateProgrm(vo);
	}

	/**
	 * 프로그램 정보를 삭제
	 * @param vo ProgrmVo
	 * @exception Exception
	 */
	public void deleteProgrm(ProgrmVo vo) throws Exception {
    	progrmDao.deleteProgrm(vo);
	}
	
	/**
	 * 프로그램 중복 건수를 조회한다.
	 * @param vo  ProgrmVo
	 * @return Integer
	 * @exception Exception
	 */
    public int selectProgrmDupCnt(ProgrmVo vo) throws Exception {
        return progrmDao.selectProgrmDupCnt(vo);
	}
	
    /**
	 * 프로그램 그룹을 조회한다.
	 * @param String url
	 * @return String 
	 * @exception Exception
	 */
	public String selectProgrmGrp(String url) throws Exception{
         	return (String)progrmDao.selectProgrmGrp(url);
	}

}