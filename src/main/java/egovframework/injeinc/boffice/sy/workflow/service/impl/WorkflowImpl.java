package egovframework.injeinc.boffice.sy.workflow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.sy.workflow.dao.WorkflowDao;
import egovframework.injeinc.boffice.sy.workflow.service.WorkflowSvc;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowReqDetailVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowReqProgressVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowReqVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowStepVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowVo;
import egovframework.injeinc.common.util.StringUtils;


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
@Service("workflowService")
public class WorkflowImpl implements WorkflowSvc {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowImpl.class);

	@Resource(name="WorkflowDao")
	private WorkflowDao workflowDao;


	/**
	 * 워크 플로우 요청 상세 맵 반환
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectWorkflowReqMap(WorkflowReqVo inReqVo) throws Exception {
		// 워크 플로우 요청 리스트 조회
		WorkflowReqVo reqVo = workflowDao.selectWorkflowReq(inReqVo);

		// PFL_WORKFLOW_REQ_DETAIL 조회
		List<WorkflowReqDetailVo> detailList = workflowDao.selectWorkflowReqDetail(reqVo);

		Map<String, Object> resultMap = BeanUtils.describe(reqVo);

		// Detail을 요청Map의 필드에 담는다.
		for(WorkflowReqDetailVo detailVo: detailList) {
			String key = detailVo.getFieldId();

			if("reqUserId".equals(key) || "reqUserName".equals(key) || "insttId".equals(key) || !resultMap.containsKey(key) || StringUtils.isEmpty((String)resultMap.get(key))) {
				resultMap.put(key, detailVo.getReqContents());
			}
		}

		// 요청 상태
		List<WorkflowReqProgressVo> progressList = workflowDao.selectWorkflowReqProgress(inReqVo);

		// 마지막 진행상황 데이터
		if(progressList != null && !progressList.isEmpty()) {
			WorkflowReqProgressVo progVo = progressList.get(0);
			// 처리내용
			resultMap.put("processContents", progVo.getReqContents()); // 처리내용
			resultMap.put("processCreateDate", progVo.getRegDt()); // 처리일시
			resultMap.put("processCreateNm", progVo.getCreateNm()); // 처리자명
			resultMap.put("processCreateId", progVo.getRegId()); // 처리자명
			resultMap.put("chargerName", progVo.getChargerName());
			resultMap.put("chargerDeptNm", progVo.getChargerDeptNm());
			resultMap.put("chargerOfficeNo", progVo.getChargerOfficeNo());
			resultMap.put("chargerEmail2", progVo.getChargerEmail2());
			resultMap.put("statusYmd", progVo.getStatusYmd());
		}


		LOGGER.debug("reqMap : {}", resultMap);

		return resultMap;
	}

	/**
	 * 워크 플로우 요청 상세 맵 반환 (답변자 정보포함)
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectWorkflowReqMapWithAnswerer(WorkflowReqVo inReqVo) throws Exception {
		// 워크 플로우 요청 리스트 조회
		WorkflowReqVo reqVo = workflowDao.selectWorkflowReq(inReqVo);

		// PFL_WORKFLOW_REQ_DETAIL 조회
		List<WorkflowReqDetailVo> detailList = workflowDao.selectWorkflowReqDetail(reqVo);

		Map<String, Object> resultMap = BeanUtils.describe(reqVo);

		// Detail을 요청Map의 필드에 담는다.
		for(WorkflowReqDetailVo detailVo: detailList) {
			String key = detailVo.getFieldId();

			if("reqUserId".equals(key) || "reqUserName".equals(key) || "insttId".equals(key) || !resultMap.containsKey(key) || StringUtils.isEmpty((String)resultMap.get(key))) {
				resultMap.put(key, detailVo.getReqContents());
			}
		}

		// 요청 상태
		List<WorkflowReqProgressVo> progressList = workflowDao.selectWorkflowReqProgressWithAnswerer(inReqVo);

		// 마지막 진행상황 데이터
		if(progressList != null && !progressList.isEmpty()) {
			WorkflowReqProgressVo progVo = progressList.get(0);
			// 처리내용
			resultMap.put("resourceName", progVo.getResourceName());
			resultMap.put("processContents", progVo.getReqContents());
			resultMap.put("chargerName", progVo.getChargerName());
			resultMap.put("chargerDeptNm", progVo.getChargerDeptNm());
			resultMap.put("chargerOfficeNo", progVo.getChargerOfficeNo());
			resultMap.put("statusYmd", progVo.getStatusYmd());

		}


		LOGGER.debug("reqMap : {}", resultMap);

		return resultMap;
	}

	/**
	 * 특정 워크 플로우 단계의 다음 단계 정보 조회
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public WorkflowStepVo selectWorkflowNextStepCharger(String workflowId, String workflowStep, Map<String, String> roleInfo) throws Exception {
		WorkflowStepVo inVo = new WorkflowStepVo();
		inVo.setWorkflowId(workflowId);
		inVo.setWorkflowStep(workflowStep);

		ArrayList<String> roleIdList = null;

		if(roleInfo != null && !roleInfo.isEmpty()) {
			roleIdList = new ArrayList<String>();

			for(String key : roleInfo.keySet()) {
				roleIdList.add(key);
			}

			inVo.setRoleIdList(roleIdList);
		}

		return workflowDao.selectWorkflowNextStepCharger(inVo);
	}

	/**
	 * 특정 워크 플로우 단계의 담당자역할ID 조회
	 * @param workflowStepId
	 * @return
	 * @throws Exception
	 */
	public String selectWorkflowStepChargerRoleId(WorkflowStepVo inVo) throws Exception {
		return workflowDao.selectWorkflowStepChargerRoleId(inVo);
	}

	/**
	 * 특정 워크 플로우 단계에 권한이 있는지 여부 반환
	 * @param workflowId
	 * @param workflowStep
	 * @return
	 * @throws Exception
	 */
	public boolean isStepAuthority(String workflowId, String workflowStep) throws Exception {
		WorkflowStepVo inVo = new WorkflowStepVo();
		inVo.setWorkflowId(workflowId);
		inVo.setWorkflowStep(workflowStep);

		String chargerRoleId = workflowDao.selectWorkflowStepChargerRoleId(inVo);

		//return userSession.isRole(chargerRoleId);
		return false;
	}

	/**
	 * 워크 플로우 스텝 리스트 조회
	 * @param inVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowStepList(String workflowId) throws Exception {
		WorkflowStepVo inVo = new WorkflowStepVo();
		inVo.setWorkflowId(workflowId);
		List<WorkflowStepVo> resultList = workflowDao.selectWorkflowStepList(inVo);
		int count = workflowDao.selectWorkflowStepListCnt(inVo);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultList", resultList);
		resultMap.put("resultCnt", count);

		return resultMap;
	}

	/**
	 * 워크 플로우 스텝 리스트 조회(workflow id가 복수개인 경우)
	 * @param inVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowStepList(List<String> workflowIdList) throws Exception {
		WorkflowStepVo inVo = new WorkflowStepVo();
		inVo.setWorkflowIdList(workflowIdList);
		List<WorkflowStepVo> resultList = workflowDao.selectWorkflowStepList(inVo);
		int count = workflowDao.selectWorkflowStepListCnt(inVo);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultList", resultList);
		resultMap.put("resultCnt", count);
		
		return resultMap;
	}

	/**
	 * 워크 플로우 스텝 리스트 조회
	 * @param inVo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> selectWorkflowStepProgressList(String workflowId,String curStatusCode) throws Exception {
		WorkflowStepVo inVo = new WorkflowStepVo();
		inVo.setWorkflowId(workflowId);
		//의도적인 null처리
		inVo.setCurStatusCode(null);
		inVo.setRoleIdList(null);
		List<WorkflowStepVo> workflowStepList = workflowDao.selectWorkflowStepList(inVo);

		List<WorkflowStepVo> resultList = new ArrayList<WorkflowStepVo>();

		Map<String,String> stepsMap = new HashMap<String,String>();		 //check the step list
		String curStepOrder = "1";
		for (WorkflowStepVo workflowStepVo : workflowStepList) {

			//Next Step 이있는 경우와 마지막 step만 progress map 에 추가
			if (workflowStepVo.getNextWorkflowStep() != null && !"".equals(workflowStepVo.getNextWorkflowStep())) {
				if (!stepsMap.containsKey(workflowStepVo.getWorkflowStep()) || 
						!"Added".equals(stepsMap.get(workflowStepVo.getWorkflowStep()) )) {
					stepsMap.put(workflowStepVo.getWorkflowStep(),"Added");
				}

				stepsMap.put(workflowStepVo.getWorkflowStep(),"Added");
				stepsMap.put(workflowStepVo.getNextWorkflowStep(),"Required");
			} else if (stepsMap.containsKey(workflowStepVo.getWorkflowStep()) && "Required".equals(stepsMap.get(workflowStepVo.getWorkflowStep()) ) ){

				stepsMap.put(workflowStepVo.getWorkflowStep(),"Added");
			}
		  //현재 Step은 무조건 넣어준다. 
			if (curStatusCode!=null && curStatusCode.equals(workflowStepVo.getWorkflowStep())) {
				curStepOrder = workflowStepVo.getWorkflowStepOrder() ; 
				stepsMap.put(workflowStepVo.getWorkflowStep(),"Added");

			}
		}

		Set set = stepsMap.keySet();
		Iterator itr = set.iterator();
		while(itr.hasNext()) 
			LOGGER.debug("Pre Steps :: {}", itr.next());


		LOGGER.debug("Check............... curStatusCode : {}", curStatusCode);
		boolean isCurLastStep = false;
		int iCurStepOrder = new Integer(curStepOrder).intValue() ;
		if(curStatusCode !=null && !"".equals(curStatusCode)) { //동일 Step Order 가 있을때 현재 step 남기고 나머지 삭제
			for (WorkflowStepVo stepOrderVo : workflowStepList) {

				if (curStatusCode.equals(stepOrderVo.getWorkflowStep() )) { // 현재 Step 이 마지막 Step 일때  
					if (stepOrderVo.getNextWorkflowStep() == null || "".equals(stepOrderVo.getNextWorkflowStep())) { 
						isCurLastStep = true;
					}
				}
				//동일 Step Order 가 있을때 현재 step 남기고 나머지 삭제
				if (!curStatusCode.equals(stepOrderVo.getWorkflowStep() )) {
					if (curStepOrder.equals(stepOrderVo.getWorkflowStepOrder())) {
						stepsMap.remove(stepOrderVo.getWorkflowStep());
					}
				} 

				//현재 Step 이 마지막 step 일때 현재 Step보다 큰 Step Order data 삭제			  
				if (isCurLastStep && (new Integer(stepOrderVo.getWorkflowStepOrder()).intValue() > iCurStepOrder)) {
					stepsMap.remove(stepOrderVo.getWorkflowStep());

				}
			}
		}
		Set set2 = stepsMap.keySet();
		Iterator itr2 = set2.iterator();
		while(itr2.hasNext()) 
			LOGGER.debug("Steps :: {}", itr2.next());

		//resultList 구성
		for (WorkflowStepVo workflowStepVo : workflowStepList) {
			if(stepsMap.containsKey(workflowStepVo.getWorkflowStep())) {
				resultList.add(workflowStepVo);
			}
		}
		int count = 0;
		if (!resultList.isEmpty()) {
			count = resultList.size();
		}

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultList", resultList);
		resultMap.put("resultCnt", count);
		resultMap.put("curStepOrder", curStepOrder);

		return resultMap;
	}

	/**
	 * 현재 상태 이상이고 권한이 있는 단계 리스트 조회
	 * @param workflowId
	 * @param curStatusCode
	 * @param userRoleId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowStepList(String workflowId, String curStatusCode, Map<String, String> roleInfo) throws Exception {
		WorkflowStepVo inVo = new WorkflowStepVo();
		inVo.setWorkflowId(workflowId);
		inVo.setCurStatusCode(curStatusCode);

		ArrayList<String> roleIdList = null;

		if(roleInfo != null && !roleInfo.isEmpty()) {
			roleIdList = new ArrayList<String>();

			for(String key : roleInfo.keySet()) {
				roleIdList.add(key);
			}

			inVo.setRoleIdList(roleIdList);
		} else { 
			LOGGER.debug("Workflow Step 권한 체크를 위한 RoleInfo 가 null입니다.");
		}

		List<WorkflowStepVo> resultList = workflowDao.selectWorkflowStepList(inVo);
		int count = workflowDao.selectWorkflowStepListCnt(inVo);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultList", resultList);
		resultMap.put("resultCnt", count);

		return resultMap;
	}
	
	/**
	 * 권한이 있는 다음 단계 워크플로우 리스트 조회
	 * @param workflowId
	 * @param curStatusCode
	 * @param userRoleId
	 * @return
	 * @throws Exception
	 */
	public List<WorkflowStepVo> selectWorkflowNextStepList(String workflowId, String curStatusCode, Map<String, String> roleInfo) throws Exception {
		WorkflowStepVo inVo = new WorkflowStepVo();
		inVo.setWorkflowId(workflowId);
		inVo.setCurStatusCode(curStatusCode);
		
		ArrayList<String> roleIdList = null;
		
		if(roleInfo != null && !roleInfo.isEmpty()) {
			roleIdList = new ArrayList<String>();
			
			for(String key : roleInfo.keySet()) {
				roleIdList.add(key);
			}
			
			inVo.setRoleIdList(roleIdList);
		} else { 
			LOGGER.debug("Workflow Step 권한 체크를 위한 RoleInfo 가 null입니다.");
		}
		
		List<WorkflowStepVo> resultList = workflowDao.selectWorkflowNextStepList(inVo);
		
		return resultList;
	}
	
	/**
	 * 권한이 있는 다음 단계 워크플로우 조회
	 * @param workflowId
	 * @param curStatusCode
	 * @param userRoleId
	 * @return
	 * @throws Exception
	 */
	public WorkflowStepVo selectWorkflowNextStep(String workflowId, String curStatusCode, Map<String, String> roleInfo) throws Exception {
		WorkflowStepVo inVo = new WorkflowStepVo();
		inVo.setWorkflowId(workflowId);
		inVo.setCurStatusCode(curStatusCode);
		
		ArrayList<String> roleIdList = null;
		
		if(roleInfo != null && !roleInfo.isEmpty()) {
			roleIdList = new ArrayList<String>();
			
			for(String key : roleInfo.keySet()) {
				roleIdList.add(key);
			}
			
			inVo.setRoleIdList(roleIdList);
		} else { 
			LOGGER.debug("Workflow Step 권한 체크를 위한 RoleInfo 가 null입니다.");
		}
		
		WorkflowStepVo result = workflowDao.selectWorkflowNextStep(inVo);
		
		return result;
	}
	
	/**
	 * 작업자 조회
	 * @param coneUserVO
	 * @return
	 * @throws Exception
	 */
/*	public List<ConeUserVO> selectOpertorList(ConeUserVO coneUserVO) throws Exception{
		List<ConeUserVO> resultList = workflowDao.selectList("Workflow.selectOpertorList", coneUserVO);
		
		return resultList;
	}*/
	
	/**
	 * 작업자 조회 건수
	 * @param coneUserVO
	 * @return
	 * @throws Exception
	 */
	/*public int selectOpertorListCnt(ConeUserVO coneUserVO) throws Exception{
		int count = workflowDao.selectOne("Workflow.selectOpertorListCnt", coneUserVO);
		
		return count;
	}*/

	/**
	 * 워크 플로우 요청 현재 상태코드 변경 (CONE 포탈)
	 * @param inReqVo
	 * @throws Exception
	 */
	public boolean saveWorkflowReqCurStatusCode(WorkflowReqVo inReqVo) throws Exception {
		// PFL_WORKFLOW_REQ 테이블 업데이트
		if(workflowDao.updateWorkflowReqCurStatusCode(inReqVo) > 0) {
			// PFL_WORKFLOW_REQ_PROGRESS(워크플로우 요청진행상황) 테이블
			WorkflowReqProgressVo progressVo = new WorkflowReqProgressVo();

			progressVo.setWorkflowReqId(inReqVo.getWorkflowReqId());
			// 상태코드
			progressVo.setStatusCode(inReqVo.getCurStatusCode());
			// 담당자
			//progressVo.setCharger(inReqVo.getCreateId());
			// 사용여부
			progressVo.setUseYn("Y");
			// 생성자ID(처리자)
			//progressVo.setCreateId(inReqVo.getCreateId());
			//progressVo.setCreateIp(inReqVo.getCreateIp());
			// 반려사유
			progressVo.setReqContents(inReqVo.getReqContents());

			workflowDao.insertWorkFlowReqProgress(progressVo);

			return true;
		}

		return false;
	}

	/**
	 * 워크 플로우 요청 리스트 조회
	 * DETAIL정보를 리스트 형식이 아닌 요청맵 안에 컬럼형식으로 만들어 반환
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectWorkflowReqMapList(WorkflowReqVo inReqVo) throws Exception {
		// 워크 플로우 요청 리스트 조회
		LOGGER.debug("===============================");
		LOGGER.debug(inReqVo.toString());
		LOGGER.debug("===============================");
		List<WorkflowReqVo> reqList = workflowDao.selectWorkflowReqList(inReqVo);

		// 결과 리스트
		List<Map<String, Object>> reqMapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> reqMap = null;

		// 워크 플로우 요청 상세 리스트
		List<WorkflowReqDetailVo> detailList = null;

		for(WorkflowReqVo reqVo : reqList) {
			// PFL_WORKFLOW_REQ_DETAIL 조회
			detailList = workflowDao.selectWorkflowReqDetail(reqVo);

			// 워크 플로우 요청 Bean을 Map으로 변환한다.
			reqMap = BeanUtils.describe(reqVo);

			// 현재 상태에 따른 요청취소 버튼 표시여부
			if(!"RECEIPT".equals(reqVo.getCurStatusCode())) {
				reqMap.put("isHiddenCancelButton", "Y");
			}

			// Detail을 요청Map의 필드에 담는다.
			for(WorkflowReqDetailVo detailVo: detailList) {
				String key = detailVo.getFieldId();

				if(!reqMap.containsKey(key) || StringUtils.isEmpty((String)reqMap.get(key))) {
					reqMap.put(key, detailVo.getReqContents());
				}
			}

			reqMapList.add(reqMap);
		}
		//log.info("reqMapList : {}", reqMapList);

		int totalCnt = workflowDao.selectWorkflowReqListCnt(inReqVo);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultList", reqMapList);
		resultMap.put("totalCnt", totalCnt);

		return resultMap;
	}

	/**
	 * 워크 플로우 요청 리스트 조회
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWorkflowReqList(WorkflowReqVo inReqVo) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		// PFL_WORKFLOW_REQ 조회
		List<WorkflowReqVo> reqList = workflowDao.selectWorkflowReqList(inReqVo);

		List<WorkflowReqDetailVo> detailList = null;

		for(WorkflowReqVo reqVo : reqList) {
			// 현재 상태에 따른 요청취소 버튼 표시여부
			if(!"REQ".equals(reqVo.getCurStatusCode())) {
				reqVo.setIsHiddenCancelButton("Y");
			}

			// PFL_WORKFLOW_REQ_DETAIL 조회
			detailList = workflowDao.selectWorkflowReqDetail(reqVo);

			reqVo.setDetailList(detailList);
		}

		int totalCnt = workflowDao.selectWorkflowReqListCnt(inReqVo);

		resultMap.put("resultList", reqList);
		resultMap.put("totalCnt", totalCnt);

		return resultMap;
	}

	/**
	 * 워크 플로우 요청 상세조회
	 * map에 req, detail, progress를 담아 반환
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public WorkflowReqVo selectWorkflowReq(WorkflowReqVo inReqVo) throws Exception {
		WorkflowReqVo reqVo = workflowDao.selectWorkflowReq(inReqVo);

		if(reqVo != null) {
			List<WorkflowReqDetailVo> detailList = workflowDao.selectWorkflowReqDetail(inReqVo);
			HashMap<String, Object> detailMap = new HashMap<String, Object>();

			// VO의 값들을 화면에서 사용하기 편하게 Map으로 변환한다.
			for(WorkflowReqDetailVo detailVo : detailList) {
				detailMap.put(detailVo.getFieldId(), detailVo.getReqContents());
			}

			List<WorkflowReqProgressVo> progressList = workflowDao.selectWorkflowReqProgress(inReqVo);

			reqVo.setDetailList(detailList);
			reqVo.setProgressList(progressList);

			// 마지막 진행상황 데이터
//			if(progressList != null && progressList.size() > 0) {
//				resultMap.put("lastProgress", progressList.get(0));
//			}
		}

		return reqVo;
	}

	/**
	 * 최근 워크 플로우 요청 조회 1건
	 * map에 req, detail, progress를 담아 반환
	 * @param inReqVo
	 * @return
	 * @throws Exception
	 */
	public WorkflowReqVo selectLatestWorkflowReq(WorkflowReqVo inReqVo) throws Exception {
		WorkflowReqVo reqVo = workflowDao.selectLatestWorkflowReq(inReqVo);

		return reqVo;
	}

	/**
	 * 워크 플로우 요청 등록
	 * @param inReqVo
	 * @param inDetailList
	 * @throws Exception
	 */
	public void saveWorkflowReq(WorkflowReqVo inReqVo, List<WorkflowReqDetailVo> inDetailList) throws Exception {
		LOGGER.debug("reqVo : {}", inReqVo);
		/*if(userSession.getUserId() != null &&  !"".equals(userSession.getUserId())) {
			inReqVo.setInsttId(userSession.getInsttId());
			inReqVo.setReqUserId(userSession.getUserId());
			inReqVo.setCreateId(userSession.getUserId());
		} else {
			if (inReqVo.getReqUserId() == null ) inReqVo.setReqUserId("Anonymous");
			if (inReqVo.getCreateId() == null ) inReqVo.setCreateId("Anonymous");
		}*/
	 
		//step #1 workflow step select
		HashMap<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("workflowId", inReqVo.getWorkflowId());
		paramMap.put("workflowStepOrder","1");
		WorkflowStepVo workflowStep = workflowDao.selectCurStatus(paramMap);

		String curStatusCode = workflowStep.getWorkflowStep() ;

		LOGGER.debug("curStatusCode : {}", curStatusCode);
		if(curStatusCode == null || "".equals(curStatusCode)) {
			curStatusCode = "REQ";
		}
		LOGGER.debug("curStatusCode : ### {}", curStatusCode);
		inReqVo.setCurStatusCode(curStatusCode);

		//step #1 workflow status code select 
		// PFL_WORKFLOW_REQ(워크플로우 요청) 테이블

		workflowDao.insertWorkFlowReq(inReqVo);

		// PFL_WORKFLOW_REQ_DETAIL(워크플로우 요청 상세) 테이블
		for(WorkflowReqDetailVo detail : inDetailList) {
			LOGGER.debug("detail : {}", detail);
			detail.setWorkflowReqId(String.valueOf(inReqVo.getWorkflowReqId()));
			//detail.setCreateId(inReqVo.getCreateId());
			//detail.setUpdateDate(inReqVo.getCreateId());

			if(StringUtils.isEmpty(detail.getRegistSno())) {
				detail.setRegistSno("1");
			}


			workflowDao.insertWorkFlowReqDetail(detail);
		}

		// PFL_WORKFLOW_REQ_PROGRESS(워크플로우 요청진행상황) 테이블
		WorkflowReqProgressVo progressVo = new WorkflowReqProgressVo();
		progressVo.setWorkflowReqId(String.valueOf(inReqVo.getWorkflowReqId()));
		// 상태코드
		progressVo.setStatusCode(inReqVo.getCurStatusCode());
		// 담당자(요청자가 담당자가 된다.)
		progressVo.setCharger(inReqVo.getReqUserId());
		// 사용여부
		progressVo.setUseYn("Y");
		//progressVo.setCreateId(inReqVo.getCreateId());
		workflowDao.insertWorkFlowReqProgress(progressVo);

		//Step #4 Auto step change.		 
		if(workflowStep != null && workflowStep.getAutoStatusChangeYn() != null && "Y".equals(workflowStep.getAutoStatusChangeYn())) {
			inReqVo.setCurStatusCode(workflowStep.getNextWorkflowStep());
			progressVo.setStatusCode(workflowStep.getNextWorkflowStep());
			workflowDao.updateWorkflowReqCurStatusCode(inReqVo);
			workflowDao.insertWorkFlowReqProgress(progressVo);
		}

	}





	/* ******************** 워크플로우 관리(신청/승인 프로세스) ******************** */

	/**
	 * 워크플로우 관리(신청/승인 프로세스) 관리 리스트
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectReqApprProcList(WorkflowVo workflowVO)  throws Exception {
		List<WorkflowVo> resultList = workflowDao.selectReqApprProcList(workflowVO);
		int resultListCnt = workflowDao.selectReqApprProcListCnt(workflowVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", resultList);
		map.put("totCnt", Integer.toString(resultListCnt));	 

		return map;
	}

	/**
	 * 워크플로우 관리(신청/승인 프로세스) 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public WorkflowVo selectReqApprProc(WorkflowVo workflowVO) throws Exception {
		WorkflowVo resultWorkflowVO = workflowDao.selectReqApprProc(workflowVO);

		return resultWorkflowVO;
	}

	/**
	 * 워크플로우 관리(신청/승인 프로세스) ID 중복체크
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int selectWorkFlowIdChk(WorkflowVo workflowVO)  throws Exception {
		int resultCnt = workflowDao.selectWorkFlowIdChk(workflowVO);

		return resultCnt;
	}


	/**
	 * 워크플로우 관리(신청/승인 프로세스) 스텝 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<WorkflowStepVo> selectReqApprProcStepList(WorkflowVo workflowVO)  throws Exception {
		List<WorkflowStepVo> resultStepList = workflowDao.selectReqApprProcStepList(workflowVO);

		return resultStepList;
	}



	/**
	 * 워크플로우 관리(신청/승인 프로세스) 등록/수정
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public void saveReqApprProc(WorkflowVo workflowVO)  throws Exception {
		workflowDao.mergeReqApprProc(workflowVO);

		String[] workflowStepArr = workflowVO.getWorkflowStepArr();
		String[] workflowRoleArr = workflowVO.getWorkflowRoleArr();
		String[] workflowSortArr = workflowVO.getWorkflowSortArr();
		String[] workflowNextStepArr = workflowVO.getWorkflowNextStepArr();

		// 신청/승인 프로세스 단계 삭제 처리
		workflowDao.updateReqApprProcStepAllUseYn(workflowVO);

		if(workflowStepArr != null){
			// 신청/승인 프로세스 단계 등록/수정 처리
			for(int i=0; i<workflowStepArr.length; i++){
			
				WorkflowStepVo workflowStepVO = new WorkflowStepVo();
				workflowStepVO.setWorkflowId(workflowVO.getWorkflowId());
				workflowStepVO.setWorkflowStep(workflowStepArr[i]);
				workflowStepVO.setWorkflowStepOrder(workflowSortArr[i]);
				workflowStepVO.setChargerRoleId(workflowRoleArr[i]);
				workflowStepVO.setNextWorkflowStep(workflowNextStepArr[i]);

				int detailCnt = workflowDao.selectReqApprProcStepCnt(workflowStepVO);

				if(detailCnt == 0){
					workflowDao.insertReqApprProcStep(workflowStepVO);
				}else{
					workflowDao.updateReqApprProcStep(workflowStepVO);
				}
			}
		}
	}

	/**
	 * 워크플로우 관리(신청/승인 프로세스) 삭제 (+단계 삭제)
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int deleteReqApprProc(WorkflowVo workflowVO)  throws Exception {
		/*workflowVO.setUpdateId(userSession.getUserId());
		workflowVO.setUpdateIp(userSession.getIp());*/

		// 해당 신청/승인 프로세스를 사용한 신청/승인 프로세스 요청 사용여부 
		int cnt = workflowDao.selectReqApprProcDeleteYn(workflowVO);

		if(cnt == 0){
			workflowDao.updateReqApprProcStepAllUseYn(workflowVO);
			workflowDao.updateReqApprProcUseYn(workflowVO);
		}

		return cnt;
	}

	/* ************************************************************ */

	@Cacheable("workflowStep")
	public List<WorkflowStepVo> selectWorkflowStepRole() throws Exception{
		List<WorkflowStepVo> list = workflowDao.selectWorkflowStepRole();
		return list;
	}

	public List<WorkflowStepVo> selectWorkflowStepRole(WorkflowStepVo workflowStepVo)  throws Exception{
		return workflowDao.selectWorkflowStepRoleByWorkflow(workflowStepVo);
	}

	@CachePut("workflowStep")
	public List<WorkflowStepVo> selectWorkflowStepRoleReloadCache() throws Exception {
		return workflowDao.selectWorkflowStepRole();
	}

	@SuppressWarnings({ })
	public boolean isContainsWorkflowStepAuth(String workflowId, String curStatusCode) throws Exception {
		boolean isEditable = false;
		WorkflowStepVo inVo = new WorkflowStepVo();
		inVo.setWorkflowId(workflowId);
		List<WorkflowStepVo> workflowList = selectWorkflowStepRole(inVo); 

		String statusCode = curStatusCode;
		for(WorkflowStepVo workflowStepVO : workflowList){
			if(statusCode == null || "".equals(statusCode)) {
					//get first step 

					WorkflowStepVo workflowStepVo = workflowDao.selectWorkflowFirstStep(inVo); 
					if (workflowStepVo != null) {
						statusCode = workflowStepVo.getWorkflowStep();
					}
					LOGGER.debug("isContainsWorkflowStepAuth 상태 #2...............{}", statusCode);
			}
			/* debug용 코드 
			Map<String,String> check = userSession.getRoleInfo();
			Set s = check.keySet();
			Iterator itr = s.iterator();
			while(itr.hasNext()) {
				String key = (String) itr.next();
				//LOGGER.debug("isContainsWorkflowStepAuth session role {} : {}", key, check.get(key));

			}
			*/

			//LOGGER.debug("step check : {}", (workflowStepVO.getWorkflowId().equals(workflowId) && workflowStepVO.getWorkflowStep().equals(statusCode)) );
			//LOGGER.debug("step check ##: {}", userSession.getRoleInfo().containsKey((String)workflowStepVO.getChargerRoleId())) ;
			if (workflowStepVO.getWorkflowId().equals(workflowId) && workflowStepVO.getWorkflowStep().equals(statusCode)
					//&& userSession != null && userSession.getRoleInfo() != null 
					//&&  userSession.getRoleInfo().containsKey((String)workflowStepVO.getChargerRoleId()) 
					){
					isEditable = true;
					LOGGER.debug("step check set isEditable true");
					if(workflowStepVO.getNextWorkflowStep() == null || "".equals(workflowStepVO.getNextWorkflowStep()) ) {
						// 현재 상태가 최종 step일 경우는 editable false 변환
						isEditable = false;
					}
			}
		}

		return isEditable;
	}
	/*public UserVO selectReqUserInfo(String reqUserId) throws Exception {
		String accountId = workflowDao.selectOne("WorkflowReq.selectReqUserInfo", reqUserId);

		UserVO sUserVO = new UserVO();
		sUserVO.setAccountId(accountId);

		UserVO userVO = workflowDao.selectOne("User.getUserInfo", sUserVO);

		return userVO;
	}*/


	/**
	 * 워크플로우 관리(신청/승인 프로세스) 다음단계 존재여부 (CONE 포탈)
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int selectNextStepCnt(WorkflowReqVo workflowReqVO)  throws Exception{
		int cnt = workflowDao.selectNextStepCnt(workflowReqVO);
		return cnt;
	}
  
	public String selectMaxWorkflowReqIdByReqUserId(String workflowId, String reqUserId) throws Exception {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workflowId", workflowId);
		paramMap.put("reqUserId", reqUserId);
		return workflowDao.selectMaxWorkflowReqIdByReqUserId(paramMap);
	}

}
