package egovframework.injeinc.boffice.sy.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.sy.board.vo.EzBbsTempletVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("EzBbsTempletDao") 
public class EzBbsTempletDao extends EgovAbstractMapper {

	/** 등록 */
	public void insertEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		insert("EzBbsTempletDao.insertEzBbsTemplet", vo);
	}

	/** 수정 */
	public void modifyEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		update("EzBbsTempletDao.modifyEzBbsTemplet", vo);
	}

	/** 삭제 */
	public void deleteEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		delete("EzBbsTempletDao.deleteEzBbsTemplet", vo);
	}

	/** 총 건수 조회 */
	public int selectTotalCountEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		return (Integer)selectOne("EzBbsTempletDao.selectTotalCountEzBbsTemplet", vo);
	}

	/** 페이지 리스트 */
	public List selectPageEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		return selectList("EzBbsTempletDao.selectPageEzBbsTemplet", vo);
	}

	/** 상세정보 */
	public EzBbsTempletVo selectEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		return (EzBbsTempletVo)selectOne("EzBbsTempletDao.selectEzBbsTemplet", vo);
	}
}