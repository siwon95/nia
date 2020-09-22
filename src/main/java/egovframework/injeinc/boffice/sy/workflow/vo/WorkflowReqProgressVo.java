package egovframework.injeinc.boffice.sy.workflow.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class WorkflowReqProgressVo implements Serializable {
	private static final long serialVersionUID = 8750185472320857107L;

	private String workflowReqId;
	private String registSno;
	private String statusCode;
	private String statusCodeNm;
	private String charger;
	private String resourceName;
	private String reqContents;
	private String useYn;
	private String createNm;

	private String chargerDeptNm;
	private String chargerName;
	private String chargerOfficeNo;
	private String chargerEmail2;
	private String statusYmd;
	
	private String regId;
	private String regDt;
	private String modId;
	private String modDt;

	public String getWorkflowReqId() {
		return workflowReqId;
	}
	public void setWorkflowReqId(String workflowReqId) {
		this.workflowReqId = workflowReqId;
	}
	public String getRegistSno() {
		return registSno;
	}
	public void setRegistSno(String registSno) {
		this.registSno = registSno;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusCodeNm() {
		return statusCodeNm;
	}
	public void setStatusCodeNm(String statusCodeNm) {
		this.statusCodeNm = statusCodeNm;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getReqContents() {
		return reqContents;
	}
	public void setReqContents(String reqContents) {
		this.reqContents = reqContents;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getChargerDeptNm() {
		return chargerDeptNm;
	}
	public void setChargerDeptNm(String chargerDeptNm) {
		this.chargerDeptNm = chargerDeptNm;
	}
	public String getChargerName() {
		return chargerName;
	}
	public void setChargerName(String chargerName) {
		this.chargerName = chargerName;
	}
	public String getChargerOfficeNo() {
		return chargerOfficeNo;
	}
	public void setChargerOfficeNo(String chargerOfficeNo) {
		this.chargerOfficeNo = chargerOfficeNo;
	}

	public String getChargerEmail2() {
		return chargerEmail2;
	}
	public void setChargerEmail2(String chargerEmail2) {
		this.chargerEmail2 = chargerEmail2;
	}
	public String getStatusYmd() {
		return statusYmd;
	}
	public void setStatusYmd(String statusYmd) {
		this.statusYmd = statusYmd;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public String getCreateNm() {
		return createNm;
	}
	public void setCreateNm(String createNm) {
		this.createNm = createNm;
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
