package egovframework.injeinc.boffice.sy.workflow.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 워크 플로우 요청 상세VO
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
public class WorkflowReqDetailVo implements Serializable {
	private static final long serialVersionUID = 9196831889406286774L;

	private String workflowReqId;
	private String workflowReqDetailId;
	private String fieldId;
	private String fieldNm;
	private String fieldValueType;
	private String registSno;
	private String reqContents;
	private String useYn;
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
	public String getWorkflowReqDetailId() {
		return workflowReqDetailId;
	}
	public void setWorkflowReqDetailId(String workflowReqDetailId) {
		this.workflowReqDetailId = workflowReqDetailId;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldNm() {
		return fieldNm;
	}
	public void setFieldNm(String fieldNm) {
		this.fieldNm = fieldNm;
	}
	public String getFieldValueType() {
		return fieldValueType;
	}
	public void setFieldValueType(String fieldValueType) {
		this.fieldValueType = fieldValueType;
	}
	public String getRegistSno() {
		return registSno;
	}
	public void setRegistSno(String registSno) {
		this.registSno = registSno;
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
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
