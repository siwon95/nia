package egovframework.injeinc.boffice.sy.workflow.vo;

import java.util.List;

import egovframework.cmm.ComDefaultVO;

public class WorkflowStepVo extends ComDefaultVO {
	private static final long serialVersionUID = -7618279981507002012L;

	private String workflowId;
	private String workflowStep;
	private String workflowStepName;
	private String workflowStepOrder;
	private String charger;
	private String chargerRoleId;
	private String pageId;
	private String nextWorkflowStep;
	private String autoStatusChangeYn;

	private String curStatusCode;
	private String hasRole;
	
	private List<String> workflowIdList;
	private List<String> roleIdList;
	
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
	public String getWorkflowStep() {
		return workflowStep;
	}
	public void setWorkflowStep(String workflowStep) {
		this.workflowStep = workflowStep;
	}
	public String getWorkflowStepName() {
		return workflowStepName;
	}
	public void setWorkflowStepName(String workflowStepName) {
		this.workflowStepName = workflowStepName;
	}
	public String getWorkflowStepOrder() {
		return workflowStepOrder;
	}
	public void setWorkflowStepOrder(String workflowStepOrder) {
		this.workflowStepOrder = workflowStepOrder;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public String getChargerRoleId() {
		return chargerRoleId;
	}
	public void setChargerRoleId(String chargerRoleId) {
		this.chargerRoleId = chargerRoleId;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getNextWorkflowStep() {
		return nextWorkflowStep;
	}
	public void setNextWorkflowStep(String nextWorkflowStep) {
		this.nextWorkflowStep = nextWorkflowStep;
	}
	public String getAutoStatusChangeYn() {
		return autoStatusChangeYn;
	}
	public void setAutoStatusChangeYn(String autoStatusChangeYn) {
		this.autoStatusChangeYn = autoStatusChangeYn;
	}
	public String getCurStatusCode() {
		return curStatusCode;
	}
	public void setCurStatusCode(String curStatusCode) {
		this.curStatusCode = curStatusCode;
	}

	public String getHasRole() {
		return hasRole;
	}
	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}
	public List<String> getRoleIdList() {
		return roleIdList;
	}
	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}
	public List<String> getWorkflowIdList() {
		return workflowIdList;
	}
	public void setWorkflowIdList(List<String> workflowIdList) {
		this.workflowIdList = workflowIdList;
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
