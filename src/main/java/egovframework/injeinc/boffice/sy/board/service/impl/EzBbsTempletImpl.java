package egovframework.injeinc.boffice.sy.board.service.impl;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.board.dao.EzBbsTempletDao;
import egovframework.injeinc.boffice.sy.board.service.EzBbsTempletSvc;
import egovframework.injeinc.boffice.sy.board.vo.EzBbsTempletVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("EzBbsTempletSvc")
public class EzBbsTempletImpl extends EgovAbstractServiceImpl implements EzBbsTempletSvc {
	@Resource(name="EzBbsTempletDao")
	EzBbsTempletDao ezBbsTempletDao;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	/** 리스트 조회 */
	public List selectPageEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		return ezBbsTempletDao.selectPageEzBbsTemplet(vo);
	}

	/** 총 건수 조회 */
	public int selectTotalCountEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		return ezBbsTempletDao.selectTotalCountEzBbsTemplet(vo);
	}

	/** 상세내용 조회 */
	public EzBbsTempletVo selectEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		return ezBbsTempletDao.selectEzBbsTemplet(vo);
	}

	/** 등록 */
	public void insertEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		ezBbsTempletDao.insertEzBbsTemplet(vo);
	}

	/** 수정 */
	public void modifyEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		ezBbsTempletDao.modifyEzBbsTemplet(vo);
	}

	/** 삭제 */
	public void deleteEzBbsTemplet(EzBbsTempletVo vo) throws Exception {
		ezBbsTempletDao.deleteEzBbsTemplet(vo);
	}
}
