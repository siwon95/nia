package egovframework.injeinc.boffice.sy.workflow.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowReqDetailVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowReqProgressVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowReqVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowStepVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * 워크 플로우 요청 서비스 구현체
 * @since	2015. 11. 3.
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *	  수정일		  수정자						수정내용
 *  ----------------	 ----------	  --------------------------------------------
 *  
 *  </pre>
 */
@Repository("WorkflowDao")
public class WorkflowDao extends EgovAbstractMapper {

	public WorkflowReqVo selectWorkflowReq(WorkflowReqVo inReqVo) throws Exception {
		return (WorkflowReqVo)selectOne("WorkflowReq.selectWorkflowReq", inReqVo);
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkflowReqDetailVo> selectWorkflowReqDetail(WorkflowReqVo reqVo) throws Exception {
		return selectList("WorkflowReq.selectWorkflowReqDetail", reqVo);
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkflowReqProgressVo> selectWorkflowReqProgress(WorkflowReqVo inReqVo) throws Exception {
		return selectList("WorkflowReq.selectWorkflowReqProgress", inReqVo);
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkflowReqProgressVo> selectWorkflowReqProgressWithAnswerer(WorkflowReqVo inReqVo) throws Exception {
		return selectList("WorkflowReq.selectWorkflowReqProgress", inReqVo);
	}
	
	public WorkflowStepVo selectWorkflowNextStepCharger(WorkflowStepVo inVo) throws Exception {
		return (WorkflowStepVo)selectOne("Workflow.selectWorkflowNextStepCharger", inVo);
	}
	
	public String selectWorkflowStepChargerRoleId(WorkflowStepVo inVo) throws Exception {
		return (String)selectOne("Workflow.selectWorkflowStepChargerRoleId", inVo);
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkflowStepVo> selectWorkflowStepList(WorkflowStepVo inVo) throws Exception {
		return selectList("Workflow.selectWorkflowStepList", inVo);
	}
	
	public int selectWorkflowStepListCnt(WorkflowStepVo inVo) throws Exception {
		return (Integer)selectOne("Workflow.selectWorkflowStepListCnt", inVo);
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkflowStepVo> selectWorkflowNextStepList(WorkflowStepVo inVo) throws Exception {
		return selectList("Workflow.selectWorkflowNextStepList", inVo);
	}
	
	public WorkflowStepVo selectWorkflowNextStep(WorkflowStepVo inVo) throws Exception {
		return (WorkflowStepVo)selectOne("Workflow.selectWorkflowNextStep", inVo);
	}
	
	public int updateWorkflowReqCurStatusCode(WorkflowReqVo inReqVo) throws Exception {
		return (Integer)update("WorkflowReq.updateWorkflowReqCurStatusCode", inReqVo);
	}
	
	public void insertWorkFlowReqProgress(WorkflowReqProgressVo progressVo) throws Exception {
		insert("WorkflowReq.insertWorkFlowReqProgress", progressVo);
	}

	@SuppressWarnings("unchecked")
	public List<WorkflowReqVo> selectWorkflowReqList(WorkflowReqVo inReqVo) throws Exception {
		return selectList("Workflow.selectWorkflowReqList", inReqVo);
	}

	public int selectWorkflowReqListCnt(WorkflowReqVo inReqVo) throws Exception {
		return (Integer)selectOne("Workflow.selectWorkflowReqListCnt", inReqVo);
	}
	
	public WorkflowReqVo selectLatestWorkflowReq(WorkflowReqVo inReqVo) throws Exception {
		return (WorkflowReqVo)selectOne("Workflow.selectLatestWorkflowReq", inReqVo);
	}
	
	public WorkflowStepVo selectCurStatus(HashMap<String,String> paramMap) throws Exception {
		return (WorkflowStepVo)selectOne("Workflow.selectCurStatus", paramMap);
	}
	
	public void insertWorkFlowReq(WorkflowReqVo inReqVo) throws Exception {
		insert("WorkflowReq.insertWorkFlowReq", inReqVo);
	}
	
	public void insertWorkFlowReqDetail(WorkflowReqDetailVo detailVo) throws Exception {
		insert("WorkflowReq.insertWorkFlowReqDetail", detailVo);
	}

	@SuppressWarnings("unchecked")
	public List<WorkflowVo> selectReqApprProcList(WorkflowVo workflowVo) throws Exception {
		return selectList("Workflow.selectReqApprProcList", workflowVo);
	}

	public int selectReqApprProcListCnt(WorkflowVo workflowVo) throws Exception {
		return (Integer)selectOne("Workflow.selectReqApprProcListCnt", workflowVo);
	}
	
	public WorkflowVo selectReqApprProc(WorkflowVo workflowVo) throws Exception {
		return (WorkflowVo)selectOne("Workflow.selectReqApprProc", workflowVo);
	}
	
	public int selectWorkFlowIdChk(WorkflowVo workflowVo) throws Exception {
		return (Integer)selectOne("Workflow.selectWorkFlowIdChk", workflowVo);
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkflowStepVo> selectReqApprProcStepList(WorkflowVo workflowVo) throws Exception {
		return selectList("Workflow.selectReqApprProcStepList", workflowVo);
	}
	
	public void mergeReqApprProc(WorkflowVo workflowVo) throws Exception {
		update("Workflow.mergeReqApprProc", workflowVo);
	}
	
	public void updateReqApprProcStepAllUseYn(WorkflowVo workflowVo) throws Exception {
		update("Workflow.updateReqApprProcStepAllUseYn", workflowVo);
	}
	
	public int selectReqApprProcStepCnt(WorkflowStepVo workflowStepVo) throws Exception {
		return (Integer)selectOne("Workflow.selectReqApprProcStepCnt", workflowStepVo);
	}

	public void insertReqApprProcStep(WorkflowStepVo workflowStepVo) throws Exception {
		insert("Workflow.insertReqApprProcStep", workflowStepVo);
	}
	
	public void updateReqApprProcStep(WorkflowStepVo workflowStepVo) throws Exception {
		update("Workflow.updateReqApprProcStep", workflowStepVo);
	}
	
	public void updateReqApprProcUseYn(WorkflowVo workflowVo) throws Exception {
		update("Workflow.updateReqApprProcUseYn", workflowVo);
	}
	
	public int selectReqApprProcDeleteYn(WorkflowVo workflowVo) throws Exception {
		return (Integer)selectOne("Workflow.selectReqApprProcDeleteYn", workflowVo);
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkflowStepVo> selectWorkflowStepRole() throws Exception {
		return (List<WorkflowStepVo>)list("Workflow.selectWorkflowStepRole", "");
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkflowStepVo> selectWorkflowStepRoleByWorkflow(WorkflowStepVo workflowStepVo) throws Exception {
		return (List<WorkflowStepVo>)list("Workflow.selectWorkflowStepRoleByWorkflow", workflowStepVo);
	}
	
	public WorkflowStepVo selectWorkflowFirstStep(WorkflowStepVo workflowStepVo) throws Exception {
		return (WorkflowStepVo)selectOne("Workflow.selectWorkflowFirstStep", workflowStepVo);
	}
	
	public int selectNextStepCnt(WorkflowReqVo workflowReqVo) throws Exception {
		return (Integer)selectOne("WorkflowReq.selectNextStepCnt", workflowReqVo);
	}
	
	public String selectMaxWorkflowReqIdByReqUserId(HashMap<String,Object> paramMap) throws Exception {
		return (String)selectOne("Workflow.selectMaxWorkflowReqIdByReqUserId", paramMap);
	}
	
	/* ******************** 워크플로우 관리(신청/승인 프로세스) ******************** */

	



	/* ************************************************************ */



	/*public UserVO selectReqUserInfo(String reqUserId) throws Exception {
		String accountId = dao.selectOne("WorkflowReq.selectReqUserInfo", reqUserId);

		UserVO sUserVO = new UserVO();
		sUserVO.setAccountId(accountId);

		UserVO userVO = dao.selectOne("User.getUserInfo", sUserVO);

		return userVO;
	}
*/

}
