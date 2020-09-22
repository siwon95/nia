package egovframework.injeinc.foffice.user.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.injeinc.foffice.user.dao.UserFDao;
import egovframework.injeinc.foffice.user.service.UserFSvc;
import egovframework.injeinc.foffice.user.vo.UserFVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("UserFSvc")
public class UserFImpl extends EgovAbstractServiceImpl implements UserFSvc {
	
	@Resource(name="UserFDao")
	private UserFDao userFDao;
	
	/** ID Generation */
    @Resource(name = "userFIdService")
    private EgovIdGnrService userFIdService;
    
    @Autowired
	private DataSourceTransactionManager transactionManager;
    
	public int retrieveUserFCnt(String dupkey) throws Exception {
		return userFDao.selectUserFCnt(dupkey);
	}

	public int retrieveCuIdCnt(HashMap<String, String> cuId) throws Exception {
		return userFDao.selectCuIdCnt(cuId);
	}
	
	public int retrieveCuIdCnt2(HashMap<String, String> cuId) throws Exception {
		return userFDao.selectCuIdCnt2(cuId);
	}

	public void registUserF(UserFVo userFVO) throws Exception {
		if(userFVO != null){
			userFVO.setCuIdx(userFIdService.getNextStringId());
		}
		userFDao.insertUserF(userFVO);
	}

	public String retrieveUserFPwd(UserFVo userFVO) throws Exception {
		return userFDao.selectUserFPwd(userFVO);
	}
	
	public String retrieveUserFPwd2(UserFVo userFVO) throws Exception {
		return userFDao.selectUserFPwd2(userFVO);
	}

	public UserFVo retrieveUserF(UserFVo userFVO) throws Exception {
		return userFDao.selectUserF(userFVO);
	}
	
	public List<UserFVo> retrieveUserListF(UserFVo userFVO) throws Exception {
		return userFDao.selectUserListF(userFVO);
	}

	public void removeUserF(UserFVo userFVO) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			
			userFDao.insertUserFDrop2(userFVO);	//등록
			
			userFDao.deleteUserF(userFVO);	//삭제
			
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		
		transactionManager.commit(status);
	}
	
	public void removeOtherUserF(UserFVo userFVO) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			
			userFDao.insertUserFDrop2(userFVO);	//등록
			
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		
		transactionManager.commit(status);
	}

	public void modifyUserF(UserFVo userFVO) throws Exception {
		userFDao.updateUserF(userFVO);
	}
	
	public void modifyUserFSelectConf(UserFVo userFVO) throws Exception{
		userFDao.updateUserFSelectConf(userFVO);
	}

	public void registUserFDrop(UserFVo userFVO) throws Exception {
		userFDao.insertUserFDrop(userFVO);
	}

	public void modifyUserFPwd(UserFVo userFVO) throws Exception {
		userFDao.updateUserFPwd(userFVO);
	}
	
	public void modifyUserFPwdmodify(UserFVo userFVO) throws Exception {
		userFDao.updateUserFPwdmodify(userFVO);
	}

	/*public String retrieveUserFId(String ownAuth) throws Exception {
		return userFDao.selectUserFId(ownAuth);
	}*/
	
	/*2020.09.08 이솔이*/
	public UserFVo retrieveUserFId(UserFVo userFVO) throws Exception {
		return userFDao.selectUserFId(userFVO);
	}
	
	public List retrieveUserFIds(String ownAuth) throws Exception{
		return userFDao.selectUserFIds(ownAuth);
	}
	
	/** 마이페이지 > 나의게시글 리스트  */
	public List retrieveMyIntegBoardList(BbsFVo bbsFVo) throws Exception {
		return userFDao.selectMyIntegBoardList(bbsFVo);
	}
	
	/** 마이페이지 > 나의게시글 리스트 갯수  */
	public int retrieveMyIntegBoardCnt(BbsFVo bbsFVo) throws Exception {
		return userFDao.selectMyIntegBoardCnt(bbsFVo);
	}
	
	/** 마이페이지 > 나의민원 리스트  */
	public List retrieveMyIntegMinwonBoardList(BbsFVo bbsFVo) throws Exception{
		return userFDao.selectMyIntegMinwonBoardList(bbsFVo);
	}
	
	/** 마이페이지 > 나의민원 리스트 갯수  */
	public int retrieveMyIntegMinwonBoardCnt(BbsFVo bbsFVo) throws Exception {
		return userFDao.retrieveMyIntegMinwonBoardCnt(bbsFVo);
	}

	/** 마이페이지 > 마일리지현황 리스트  */
	public List retrieveListMileage(UserFVo userFVo) throws Exception {
		return userFDao.selectListMileage(userFVo);
	}
	
	/** 마이페이지 > 마일리지현황 리스트카운트  */
	public int retrieveListMileageCnt(String id) throws Exception {
		return userFDao.selectListMileageCnt(id);
	}
	
	public int retrieveListSingoCnt(String ownauth) throws Exception{
		return userFDao.selectListSingoCnt(ownauth);
	}
	
	public int retrieveListScrapCnt(String cuId) throws Exception{
		return userFDao.selectListScrapCnt(cuId);
	}
	
	public int retrieveListReserveCnt(String ownauth) throws Exception{
		return userFDao.selectListReserveCnt(ownauth);
	}
	
/*	public int retrieveListPollCnt(String ownauth) throws Exception{
		return userFDao.selectListPollCnt(ownauth);
	}*/
	
	/*2020.09.08 이솔이 */
	public int retrieveListPollCnt(UserFVo userFVo) throws Exception{
		return userFDao.selectListPollCnt(userFVo);
	}
	
	public int retrieveLimmitDt(String cuId) throws Exception{
		return userFDao.selectLimmitDt(cuId);
	}
	//** 공통코드 이메일 값 가져오기 */
	public List selectEmailList() throws Exception {
		return userFDao.selectEmailList();
	}
}