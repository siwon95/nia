package egovframework.injeinc.boffice.sy.workflow.vo;

import java.util.ArrayList;
import java.util.HashMap;

import egovframework.cmm.ComDefaultVO;

/**
 * 워크플로우 VO
 * @author  유기목
 * @since	2015. 12. 2.
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *	  수정일		  수정자						수정내용
 *  ----------------	 ----------	  --------------------------------------------
 *  2015. 12. 2.		유기목		최초 생성
 *  </pre>
 */
public class WorkflowVo extends ComDefaultVO {
	private static final long serialVersionUID = -2199159846583288789L;

	private String workflowId;
	private String workflowNm;
	private String useYn;

	private String detail;

	private ArrayList<HashMap<String, String>> detailList;
	
	private String[] workflowStepArr;
	private String[] workflowRoleArr;
	private String[] workflowSortArr;
	private String[] workflowNextStepArr;
	
	private String regId;
	private String regDt;
	private String modId;
	private String modDt;

	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getWorkflowNm() {
		return workflowNm;
	}
	public void setWorkflowNm(String workflowNm) {
		this.workflowNm = workflowNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public ArrayList<HashMap<String, String>> getDetailList() {
		return detailList;
	}
	public void setDetailList(ArrayList<HashMap<String, String>> detailList) {
		this.detailList = detailList;
	}
	public String[] getWorkflowStepArr() {
		return workflowStepArr;
	}
	public void setWorkflowStepArr(String[] workflowStepArr) {
		this.workflowStepArr = workflowStepArr;
	}
	public String[] getWorkflowRoleArr() {
		return workflowRoleArr;
	}
	public void setWorkflowRoleArr(String[] workflowRoleArr) {
		this.workflowRoleArr = workflowRoleArr;
	}
	public String[] getWorkflowSortArr() {
		return workflowSortArr;
	}
	public void setWorkflowSortArr(String[] workflowSortArr) {
		this.workflowSortArr = workflowSortArr;
	}
	public String[] getWorkflowNextStepArr() {
		return workflowNextStepArr;
	}
	public void setWorkflowNextStepArr(String[] workflowNextStepArr) {
		this.workflowNextStepArr = workflowNextStepArr;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

}
