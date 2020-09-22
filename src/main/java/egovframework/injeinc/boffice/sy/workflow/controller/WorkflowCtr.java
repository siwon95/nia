package egovframework.injeinc.boffice.sy.workflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;
import egovframework.injeinc.boffice.sy.workflow.service.WorkflowSvc;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowStepVo;
import egovframework.injeinc.boffice.sy.workflow.vo.WorkflowVo;
import egovframework.injeinc.common.util.ConvUtils;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 신청/승인 유형 관리 컨트롤러
 * @since	2015. 11. 2.
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
@Controller
public class WorkflowCtr  extends CmmLogCtr {
	private static final Logger logger = Logger.getLogger(WorkflowCtr.class);

	@Autowired
	private WorkflowSvc workflowSvc;

	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;


	/* ******************** 워크플로우 관리(신청/승인 프로세스) ******************** */

	/**
	 * 워크플로우 관리(신청/승인 프로세스) 리스트 화면
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boffice/sy/workflow/workflowProcList.do")
	public String selectReqApprProcList(
			@ModelAttribute("WorkflowVo") WorkflowVo workflowVo, 
			ModelMap model, 
			HttpServletRequest request, 
			HttpServletResponse response) 
			throws Exception {
		
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(workflowVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(workflowVo.getPageUnit());
		paginationInfo.setPageSize(workflowVo.getPageSize());

		workflowVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		workflowVo.setLastIndex(paginationInfo.getLastRecordIndex());
		workflowVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> resultMap = workflowSvc.selectReqApprProcList(workflowVo);
		
        model.addAttribute("resultList", resultMap.get("resultList"));

        int totCnt = Integer.parseInt(resultMap.get("totCnt").toString());
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/boffice/sy/workflow/workflow_proc_list";
	}

	/**
	 *  워크플로우 관리(신청/승인 프로세스) 등록/수정 화면
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boffice_noDeco/sy/workflow/workflowProcForm.do")
	public String regiReqApprProc(
			@ModelAttribute("WorkflowVo") WorkflowVo workflowVo, 
			ModelMap model, 
			HttpServletRequest request, 
			HttpServletResponse response) 
			throws Exception {
		String type = request.getParameter("type");

		WorkflowVo resultWorkflowVo = null;

		if(workflowVo.getWorkflowId() != null){
			resultWorkflowVo = workflowSvc.selectReqApprProc(workflowVo);
			List<WorkflowStepVo> resultStepList = workflowSvc.selectReqApprProcStepList(workflowVo);
			model.addAttribute("resultStepList", resultStepList);
		} else {
			resultWorkflowVo = workflowVo;
		}

		List<CmmCodeVo> commCodeStatusCd = cmmCodeSvc.retrieveListCmmCode("wf_status_code");
		
		model.addAttribute("commCodeStatusCd",	commCodeStatusCd);		
		model.addAttribute("workflowVo", resultWorkflowVo);
		model.addAttribute("type", type);

		return "injeinc/boffice/sy/workflow/workflow_proc_form";
	}

	/**
	 * 워크플로우 관리(신청/승인 프로세스) ID 중복체크 JSON DATA
	 * @param sNotice
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boffice/sy/workflow/workflowIdChkAx.do" )
	@ResponseBody
	public void selectWorkFlowIdChkAx(
			@ModelAttribute("WorkflowVo") WorkflowVo workflowVO, 
			ModelMap model,
			HttpServletRequest request,  
			HttpServletResponse response) 
			throws Exception  {
		HashMap<String,Object> jsonMap = new HashMap<String,Object>();

		String chkYN = "N";
		int cnt = workflowSvc.selectWorkFlowIdChk(workflowVO);

		if(cnt == 0){
			chkYN = "Y";
		}

		jsonMap.put("chkYN", chkYN);
		jsonMap.put("workflowId", workflowVO.getWorkflowId());

		jsonView.render(jsonMap, request, response);
	}


	/**
	 * 워크플로우 관리(신청/승인 프로세스) 등록/수정
	 * @param sNotice
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boffice/sy/workflow/workflowRegist.do")
	public String saveReqApprProc(
			@ModelAttribute("WorkflowVo") WorkflowVo workflowVO, 
			ModelMap model, 
			HttpServletRequest request, 
			HttpServletResponse response) 
			throws Exception {

 		workflowSvc.saveReqApprProc(workflowVO);
		
		String SVC_MSGE = Message.getMessage("100.message"); 
		return alert("/boffice/sy/workflow/workflowProcList.do", SVC_MSGE, model);
	}

	/**
	 * 워크플로우 관리(신청/승인 프로세스) 삭제 (+상세/단계 삭제)
	 * @param sNotice
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boffice/sy/workflow/workflowDeleteProc.do")
	@ResponseBody
	public void deleteReqApprProc(
			@ModelAttribute("WorkflowVo") WorkflowVo workflowVO, 
			ModelMap model, 
			HttpServletRequest request, 
			HttpServletResponse response) 
			throws Exception {
		HashMap<String,Object> jsonMap = new HashMap<String,Object>();

		int deleteCnt = workflowSvc.deleteReqApprProc(workflowVO);

		// 해당 신청/승인 프로세스를 사용한 신청/승인 프로세스 요청 사용여부
		if(deleteCnt == 0){
			jsonMap.put("deleteYn", "Y");
		}else{
			jsonMap.put("deleteYn", "N");
		}

		jsonMap.put("chkYN", "Y");

		jsonView.render(jsonMap, request, response);
	}
	/* ************************************************************ */

}
