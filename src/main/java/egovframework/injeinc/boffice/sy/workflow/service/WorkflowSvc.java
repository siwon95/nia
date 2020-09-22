package egovframework.injeinc.boffice.sy.workflow.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowReqDetailVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowReqVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowStepVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowVo;

/**
 * 워크 플로우 서비스
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
public interface WorkflowSvc {

	/**
	 * 워크 플로우 요청 상세 맵 반환
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowReqMap(WorkflowReqVo inReqVo) throws Exception;


	/**
	 * 워크 플로우 요청 상세 맵 반환 (답변자 정보 포함)
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowReqMapWithAnswerer(WorkflowReqVo inReqVo) throws Exception;

	/**
	 * 특정 워크 플로우 단계의 다음 단계 정보 조회
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public WorkflowStepVo selectWorkflowNextStepCharger(String workflowId, String workflowStep, Map<String, String> roleInfo) throws Exception;

	/**
	 * 특정 워크 플로우 단계의 담당자 역할ID 조회
	 * @param workflowStepId
	 * @return
	 * @throws Exception
	 */
	public String selectWorkflowStepChargerRoleId(WorkflowStepVo inVo) throws Exception;

	/**
	 * 특정 워크 플로우 단계에 권한이 있는지 여부 반환
	 * @param workflowId
	 * @param workflowStep
	 * @return
	 * @throws Exception
	 */
	public boolean isStepAuthority(String workflowId, String workflowStep) throws Exception;

	/**
	 * 워크 플로우 스텝 리스트 조회
	 * @param inVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowStepList(String workflowId) throws Exception;

	/**
	 * 워크 플로우 스텝 리스트 조회(workflow id가 복수개인 경우)
	 * @param inVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowStepList(List<String> workflowIds) throws Exception;

	/**
	 * 현재 상태 이상이고 권한이 있는 단계 리스트 조회
	 * @param workflowId
	 * @param curStatusCode
	 * @param userRoleId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowStepList(String workflowId, String curStatusCode, Map<String, String> roleInfo) throws Exception;
	
	/**
	 * 권한이 있는 다음 단계 워크플로우 리스트 조회
	 * @param workflowId
	 * @param curStatusCode
	 * @param userRoleId
	 * @return
	 * @throws Exception
	 */
	public List<WorkflowStepVo> selectWorkflowNextStepList(String workflowId, String curStatusCode, Map<String, String> roleInfo) throws Exception;
	
	/**
	 * 다음단계 워크플로우 조회
	 * @param workflowId
	 * @param curStatusCode
	 * @param userRoleId
	 * @return
	 * @throws Exception
	 */
	public WorkflowStepVo selectWorkflowNextStep(String workflowId, String curStatusCode, Map<String, String> roleInfo) throws Exception;
	
	/**
	 * 작업자 조회
	 * @param coneUserVO
	 * @return
	 * @throws Exception
	 */
	//public List<ConeUserVO> selectOpertorList(ConeUserVO coneUserVO) throws Exception;
	
	/**
	 * 작업자 조회 건수
	 * @param coneUserVO
	 * @return
	 * @throws Exception
	 */
	//public int selectOpertorListCnt(ConeUserVO coneUserVO) throws Exception;

	/**
	 * 워크 플로우 요청 현재 상태코드 변경
	 * @param inReqVo
	 * @throws Exception
	 */
	public boolean saveWorkflowReqCurStatusCode(WorkflowReqVo inReqVo) throws Exception;

	/**
	 * 워크 플로우 요청 리스트 조회
	 * DETAIL정보를 리스트 형식이 아닌 요청맵 안에 컬럼형식으로 만들어 반환
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowReqMapList(WorkflowReqVo inReqVo) throws Exception;

	/**
	 * 워크 플로우 요청 리스트 조회
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowReqList(WorkflowReqVo inReqVo) throws Exception;

	/**
	 * 워크 플로우 요청 상세조회
	 * map에 req, detail, progress를 담아 반환
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public WorkflowReqVo selectWorkflowReq(WorkflowReqVo inReqVo) throws Exception;


	/**
	 * 최근 워크 플로우 요청 1건
	 * map에 req, detail, progress를 담아 반환
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public WorkflowReqVo selectLatestWorkflowReq(WorkflowReqVo inReqVo) throws Exception;



	/**
	 * 워크플로우 요청 등록
	 * @param inReqVo
	 * @param inDetailList
	 * @throws Exception
	 */
	public void saveWorkflowReq(WorkflowReqVo inReqVo, List<WorkflowReqDetailVo> inDetailList) throws Exception;


	/* ******************** 워크플로우 관리(신청/승인 프로세스) ******************** */

	/**
	 * 워크플로우 관리(신청/승인 프로세스) 관리 리스트
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectReqApprProcList(WorkflowVo workflowVO)  throws Exception;

	/**
	 * 워크플로우 관리(신청/승인 프로세스) 관리 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public WorkflowVo selectReqApprProc(WorkflowVo workflowVO)  throws Exception;


	/**
	 *워크플로우 관리(신청/승인 프로세스) 스텝 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<WorkflowStepVo> selectReqApprProcStepList(WorkflowVo workflowVO)  throws Exception;

	/**
	 * 워크플로우 관리(신청/승인 프로세스) ID 중복체크
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int selectWorkFlowIdChk(WorkflowVo workflowVO)  throws Exception;


	/**
	 * 워크플로우 관리(신청/승인 프로세스) 등록/수정
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public void saveReqApprProc(WorkflowVo workflowVO)  throws Exception;

	/**
	 * 워크플로우 관리(신청/승인 프로세스) 삭제 (+상세/단계 삭제)
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int deleteReqApprProc(WorkflowVo workflowVO)  throws Exception;

	/* ************************************************************ */
	/**
	 * 버튼권한
	 * @return
	 * @throws Exception
	 */
	public List<WorkflowStepVo> selectWorkflowStepRole() throws Exception;

	public List<WorkflowStepVo> selectWorkflowStepRole(WorkflowStepVo workflowStepVo) throws Exception;

	/**
	 * 버튼권한(캐시 리로드)
	 * @return
	 * @throws Exception
	 */
	public List<WorkflowStepVo> selectWorkflowStepRoleReloadCache() throws Exception;

	/**
	 * 워크 플로우 스텝 리스트 조회
	 * @param inVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowStepProgressList(String workflowId,String curStatusCode) throws Exception ;

	/**
	 * 워크 플로우 스텝별 권한 보유여부
	 * @param workflowId, curStatusCode
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isContainsWorkflowStepAuth(String workflowId, String curStatusCode) throws Exception ;

	/**
	 * 요청자 정보
	 * @return
	 * @throws Exception
	 */
	//public UserVO selectReqUserInfo(String reqUserId) throws Exception;



	/**
	 * 워크플로우 관리(신청/승인 프로세스) 다음단계 존재여부
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int selectNextStepCnt(WorkflowReqVo workflowReqVO)  throws Exception;
  
	/**
	 * 요청자의 최신 워크플로우 아이디 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public String selectMaxWorkflowReqIdByReqUserId(String workflowId, String reqUserId) throws Exception;


}
