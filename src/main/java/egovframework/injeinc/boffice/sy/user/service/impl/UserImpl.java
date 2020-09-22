package egovframework.injeinc.boffice.sy.user.service.impl;

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
import egovframework.injeinc.boffice.sy.user.dao.UserDao;
import egovframework.injeinc.boffice.sy.user.service.UserSvc;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("UserSvc")
public class UserImpl extends EgovAbstractServiceImpl implements UserSvc {
	
	@Resource(name="UserDao")
	UserDao userDao;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	/** 리스트 조회 */
	public List retrieveListUser(UserVo vo) throws Exception {
		return userDao.selectListUser(vo);
	}
	
	/** 총 건수 조회 */
	public int retrievePagUser(UserVo vo) throws Exception {
		return userDao.selectPagUser(vo);
	}
	
	public void registUser(UserVo vo) throws Exception {
		userDao.createUser(vo);
		userDao.insertUserHistory(vo);
	}

	/** 상세내용 조회 */
	public UserVo retrieveUser(int cuIdx) throws Exception {
		return userDao.selectUser(cuIdx);
	}

	/** 수정 */
	public void modifyUser(UserVo vo) throws Exception {
		userDao.updateUser(vo);
		userDao.insertUserHistory(vo);
	}

	/** 삭제 */
	public void saveUser(UserVo vo) throws Exception {
		userDao.insertUserHistory(vo);
		userDao.saveUser(vo);	//탈퇴처리
	}

	/** 아이디 중복 확인 */
	public int userIdCheckAx(String cuId) throws Exception {
		return (Integer) userDao.userIdCheckAx(cuId);
	}
	
	/** 비밀번호 초기화 */
	public int userPassModAx(UserVo vo) throws Exception {
		int result = (Integer) userDao.userPassModAx(vo);
		userDao.insertUserHistory(vo);
		return result;
	}
	
	/** 회원그룹 리스트 */
	public List retrievePagUserGroup(UserVo vo) throws Exception {
		return userDao.retrievePagUserGroup(vo);
	}
	
	/** 회원그룹 총 건수 조회 */
	public int selectUserGroupListTotCnt(UserVo vo) throws Exception {
		return userDao.selectUserGroupListTotCnt(vo);
	}
	
	/** 회원그룹 회원 추가 
	 * @throws Exception */
	public HashMap<String, Object> userRegListAx(HashMap<String, Object> paramObject) throws Exception {
		
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			int cnt = paramObject == null ? 0 : (Integer) paramObject.get("idxlen");
				
			for(int i=0;i<cnt;i++){
					param.put("cuIdx",paramObject.get("idx"+i));
					param.put("regId",paramObject.get("regId"));
					param.put("gcIdx",paramObject.get("gcIdx"));
					boolean reSult = false;
					reSult = userDao.userRegListAx(param);
			}
		
			
			output.put("SVC_CODE", Message.getMessage("100.code"));
			output.put("SVC_MSGE", Message.getMessage("100.message"));
			
		} catch (RuntimeException e) {
			transactionManager.rollback(status);
			
			output.put("SVC_CODE", Message.getMessage("901.code"));
			output.put("SVC_MSGE", Message.getMessage("901.message"));
			return output;
		} catch (Exception e) {
			transactionManager.rollback(status);
			
			output.put("SVC_CODE", Message.getMessage("901.code"));
			output.put("SVC_MSGE", Message.getMessage("901.message"));
			return output;
		}
		
		transactionManager.commit(status);
		
		return output;
	}
	
	
	//** 공통코드 이메일 값 가져오기 */
	public List selectEmailList() throws Exception {
		return userDao.selectEmailList();
	}

	/** 아이디와 비번으로 회원정보 가져오기 */
	public UserVo retrieveUserByIdAndPwd(UserVo userVo) throws Exception {
		return userDao.selectUserByIdAndPwd(userVo);
	}

	/** DI로 회원정보 가져오기 */
	public UserVo retrieveUserByDi(UserVo userVo) throws Exception {
		return userDao.selectUserByDi(userVo);
	}

	public void registUserControlLog(UserVo userVo) throws Exception {
		userDao.insertUserControlLog(userVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListUserSearch(UserVo userVo) throws Exception {
		return userDao.selectListUserSearch(userVo);
	}

	
	public List<UserVo> getRePiAgreeUserList() throws Exception {
		return userDao.selectRePiAgreeUserList();
	}

}
