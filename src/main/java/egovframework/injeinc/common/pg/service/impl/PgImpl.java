package egovframework.injeinc.common.pg.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.cmm.EgovMessageSource;
import egovframework.injeinc.common.pg.dao.PgDao;
import egovframework.injeinc.common.pg.service.PgSvc;
import egovframework.injeinc.common.pg.vo.PgVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("PgSvc")
public class PgImpl extends EgovAbstractServiceImpl implements PgSvc {

	@Resource(name = "PgDao")
	private PgDao pgDao;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	@SuppressWarnings("rawtypes")
	public List retrieveListPg(PgVo pgVo) throws Exception {
		return pgDao.selectListPg(pgVo);
	}

	public int retrievePagPg(PgVo pgVo) throws Exception {
		return pgDao.selectPagPg(pgVo);
	}

	public void registPg(PgVo pgVo) throws Exception {
		pgDao.insertPg(pgVo);
	}

	public void registMgrPg(PgVo pgVo) throws Exception {
		pgDao.insertMgrPg(pgVo);
	}

	public int retrieveCstMidCnt(HashMap<String, String> param) throws Exception {
		return pgDao.selectCstMidCnt(param);
	}

	public void modifyPg(PgVo pgVo) throws Exception {
		pgDao.updatePg(pgVo);
	}

	public PgVo retrievePg(PgVo pgVo) throws Exception {
		return pgDao.selectPg(pgVo);
	}

	public PgVo retrieveMidPg(PgVo pgVo) throws Exception {
		return pgDao.selectMidPg(pgVo);
	}

	public PgVo retrieveCmmPg(PgVo pgVo) throws Exception {
		return pgDao.selectCmmPg(pgVo);
	}

	public int retrieveConfPathCnt(HashMap<String, String> param) throws Exception {
		return pgDao.selectConfPathCnt(param);
	}

	public void removePg(PgVo pgVo) throws Exception {
		pgDao.deletePg(pgVo);
	}

	public void updatePgMgr(PgVo pgVo) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			pgDao.updatePgMgr(pgVo);
		} catch (RuntimeException e) {
			transactionManager.rollback(status);
			throw e;
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
	}

	public void updatePgMissMgr(PgVo pgVo) throws Exception {
		pgDao.updatePgMissMgr(pgVo);
	}

	
	public List<PgVo> retrievePgByMertCodeAx(HashMap<String, String> param) throws Exception {
		return pgDao.selectPgByMertCodeAx(param);
	}

}