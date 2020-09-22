package egovframework.injeinc.boffice.sy.workflow.vo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.ComDefaultVO;
import egovframework.injeinc.common.util.EgovStringUtil;

/**
 * 워크플로우 요청 VO
 * @author  유기목
 * @since	2015. 11. 2.
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *	  수정일		  수정자						수정내용
 *  ----------------	 ----------	  --------------------------------------------
 *  2015. 11. 2.		유기목		최초 생성
 *  </pre>
 */
public class WorkflowReqVo extends ComDefaultVO {
	private static final long serialVersionUID = 151082479390168044L;

	private String workflowReqId;
	private String workflowId;
	private String reqDate;
	private String insttId;
	private String reqUserId;
	private String reqUserName;
	private String curStatusCodeGroupId;
	private String curStatusCode;
	private String curStatusNm;
	private String curStatusOrder;

	private String reqContents;

	private List<WorkflowReqDetailVo> detailList;
	private List<WorkflowReqProgressVo> progressList;
	private String statusCodeGroupId;

	private String sReqDateFrom;
	private String sReqDateTo;

	// 검색관련
	private String searchFieldId1;
	private String searchFieldValue1;
	private String searchFieldId2;
	private String searchFieldValue2;
	private String searchFieldId3;
	private String searchFieldValue3;
	private String searchFieldId4;
	private String searchFieldValue4;

	private MultipartFile file;

	private String isHiddenCancelButton;

	private List<String> roleIdList;
	private List<String> workflowIdList;
	private List<String> searchRoleIds;
	private String strSearchRoleIds;
	
	private String sessionUserId;
	private String projectId;
	private String projectNm;

	private String apprvCmpnyUserId;
	private String insttRoleId;

	private List<String> userProjectList;
	
	private int updateCount;
	
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
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getInsttId() {
		return insttId;
	}
	public void setInsttId(String insttId) {
		this.insttId = insttId;
	}
	public String getReqUserId() {
		return reqUserId;
	}
	public void setReqUserId(String reqUserId) {
		this.reqUserId = reqUserId;
	}
	public String getReqUserName() {
		return reqUserName;
	}
	public void setReqUserName(String reqUserName) {
		this.reqUserName = reqUserName;
	}
	public String getCurStatusCodeGroupId() {
		return curStatusCodeGroupId;
	}
	public void setCurStatusCodeGroupId(String curStatusCodeGroupId) {
		this.curStatusCodeGroupId = curStatusCodeGroupId;
	}
	public String getCurStatusCode() {
		return curStatusCode;
	}
	public void setCurStatusCode(String curStatusCode) {
		this.curStatusCode = curStatusCode;
	}
	public String getCurStatusNm() {
		return curStatusNm;
	}
	public void setCurStatusNm(String curStatusNm) {
		this.curStatusNm = curStatusNm;
	}

	public String getReqContents() {
		return reqContents;
	}
	public void setReqContents(String reqContents) {
		this.reqContents = reqContents;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public List<WorkflowReqDetailVo> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<WorkflowReqDetailVo> detailList) {
		this.detailList = detailList;
	}

	public List<WorkflowReqProgressVo> getProgressList() {
		return progressList;
	}
	public void setProgressList(List<WorkflowReqProgressVo> progressList) {
		this.progressList = progressList;
	}
	public String getStatusCodeGroupId() {
		return statusCodeGroupId;
	}
	public void setStatusCodeGroupId(String statusCodeGroupId) {
		this.statusCodeGroupId = statusCodeGroupId;
	}

	public String getDetailReqContents(String fieldId) {
		String result = null;

		if(EgovStringUtil.isEmpty(fieldId)) {
			return result;
		}

		for(WorkflowReqDetailVo detail : this.detailList) {
			if(detail.getFieldId().equals(fieldId)) {
				return detail.getReqContents();
			}
		}

		return result;
	}

	public String getsReqDateFrom() {
		return sReqDateFrom;
	}
	public void setsReqDateFrom(String sReqDateFrom) {
		this.sReqDateFrom = sReqDateFrom;
	}
	public String getsReqDateTo() {
		return sReqDateTo;
	}
	public void setsReqDateTo(String sReqDateTo) {
		this.sReqDateTo = sReqDateTo;
	}

	public String getSearchFieldId1() {
		return searchFieldId1;
	}
	public void setSearchFieldId1(String searchFieldId1) {
		this.searchFieldId1 = searchFieldId1;
	}
	public String getSearchFieldValue1() {
		return searchFieldValue1;
	}
	public void setSearchFieldValue1(String searchFieldValue1) {
		this.searchFieldValue1 = searchFieldValue1;
	}
	public String getSearchFieldId2() {
		return searchFieldId2;
	}
	public void setSearchFieldId2(String searchFieldId2) {
		this.searchFieldId2 = searchFieldId2;
	}
	public String getSearchFieldValue2() {
		return searchFieldValue2;
	}
	public void setSearchFieldValue2(String searchFieldValue2) {
		this.searchFieldValue2 = searchFieldValue2;
	}
	public String getSearchFieldId3() {
		return searchFieldId3;
	}
	public void setSearchFieldId3(String searchFieldId3) {
		this.searchFieldId3 = searchFieldId3;
	}
	public String getSearchFieldValue3() {
		return searchFieldValue3;
	}
	public void setSearchFieldValue3(String searchFieldValue3) {
		this.searchFieldValue3 = searchFieldValue3;
	}
	public String getSearchFieldId4() {
		return searchFieldId4;
	}
	public void setSearchFieldId4(String searchFieldId4) {
		this.searchFieldId4 = searchFieldId4;
	}
	public String getSearchFieldValue4() {
		return searchFieldValue4;
	}
	public void setSearchFieldValue4(String searchFieldValue4) {
		this.searchFieldValue4 = searchFieldValue4;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getIsHiddenCancelButton() {
		return this.isHiddenCancelButton ;
	}

	public void setIsHiddenCancelButton(String isHiddenCancelButton) {
		this.isHiddenCancelButton = isHiddenCancelButton;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}
	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getSessionUserId() {
		return sessionUserId;
	}

	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectNm() {
		return projectNm;
	}

	public void setProjectNm(String projectNm) {
		this.projectNm = projectNm;
	}

	public List<String> getUserProjectList() {
		return userProjectList;
	}

	public void setUserProjectList(List<String> userProjectList) {
		this.userProjectList = userProjectList;
	}
	public List<String> getWorkflowIdList() {
		return workflowIdList;
	}
	public void setWorkflowIdList(List<String> workflowIdList) {
		this.workflowIdList = workflowIdList;
	}
	public String getCurStatusOrder() {
		return curStatusOrder;
	}
	public void setCurStatusOrder(String curStatusOrder) {
		this.curStatusOrder = curStatusOrder;
	}
	public String getApprvCmpnyUserId() {
		return apprvCmpnyUserId;
	}
	public void setApprvCmpnyUserId(String apprvCmpnyUserId) {
		this.apprvCmpnyUserId = apprvCmpnyUserId;
	}
	public String getInsttRoleId() {
		return insttRoleId;
	}
	public void setInsttRoleId(String insttRoleId) {
		this.insttRoleId = insttRoleId;
	}
	public List<String> getSearchRoleIds() {
		return searchRoleIds;
	}
	public void setSearchRoleIds(List<String> searchRoleIds) {
		this.searchRoleIds = searchRoleIds;
	}
	public String getStrSearchRoleIds() {
		return strSearchRoleIds;
	}
	public void setStrSearchRoleIds(String strSearchRoleIds) {
		this.strSearchRoleIds = strSearchRoleIds;
	}
	public int getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
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
