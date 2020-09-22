package egovframework.injeinc.boffice.sy.group.service.impl;

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
import egovframework.injeinc.boffice.sy.group.dao.GroupConfigDao;
import egovframework.injeinc.boffice.sy.group.service.GroupConfigSvc;
import egovframework.injeinc.boffice.sy.group.vo.GroupConfigVo;
import egovframework.injeinc.boffice.sy.group.vo.UserGroupVo;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;

@Service("GroupConfigSvc")
public class GroupConfigImpl implements GroupConfigSvc {
	
	@Resource(name="GroupConfigDao")
	GroupConfigDao groupConfigDao;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	/** 리스트 조회 */
	public List retrieveListGroupConfig(GroupConfigVo vo) throws Exception {
		return groupConfigDao.selectListGroupConfig(vo);
	}
	
	/** 총 건수 조회 */
	public int retrievePagGroupConfig(GroupConfigVo vo) throws Exception {
		return groupConfigDao.selectPagGroupConfig(vo);
	}

	 /** 등록 */
	public void registGroupConfig(GroupConfigVo vo) throws Exception {
		groupConfigDao.createGroupConfig(vo);
	}
	
	/** 상세내용 조회 */
	public GroupConfigVo retrieveGroupConfig(int gcIdx) throws Exception {
		return groupConfigDao.selectGroupConfig(gcIdx); 
	}
	
	/** 수정 */
	public void modifyGroupConfig(GroupConfigVo vo) throws Exception {
		groupConfigDao.updateGroupConfig(vo);
	}

    /** 삭제 */
	public void removeGroupConfig(String gcIdx) throws Exception {
		groupConfigDao.deleteGroupConfig(gcIdx);
	}
	
	/** 그룹회원목록 총 건수 조회*/
	public int retrievePagtUserGroupMember(int gcIdx) throws Exception {
		return groupConfigDao.selectPagUserGroupMember(gcIdx);
	}
	
	/** 그룹회원목록 리스트 조회 */
	public List retrieveListUserGroupMember(UserGroupVo vo) throws Exception {
		return groupConfigDao.selectListUserGroupMember(vo);
	}
	
	/** 그룹회원 삭제 */
	public int userGroupRmvAx(int cuIdx) throws Exception {
		return groupConfigDao.userGroupRmvAx(cuIdx);
	}

	public int retrievePagUserGroupAdd(UserVo vo) throws Exception {
		return groupConfigDao.selectPagGroupAdd(vo);
	}

	public List retrieveListUserGroupAdd(UserVo vo) throws Exception {
		return groupConfigDao.selectListGroupAdd(vo); 
	}
	
	public HashMap<String, Object> userGroupAddRegAx(HashMap<String, Object> paramObject) throws Exception {
		
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
					reSult = groupConfigDao.userGroupAddRegAx(param);
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
	
	
	@SuppressWarnings("rawtypes")
	public List retrieveListGroupConfigAll() throws Exception {
		return groupConfigDao.selectListGroupConfigAll();
	}
}
