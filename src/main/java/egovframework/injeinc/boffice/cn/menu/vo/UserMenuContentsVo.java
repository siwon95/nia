package egovframework.injeinc.boffice.cn.menu.vo;

import egovframework.cmm.ComDefaultVO;

public class UserMenuContentsVo extends ComDefaultVO {
	//도메인CD
	private String siteCd;
	//메뉴 코드
	private String menuCode;
	//컨텐츠일련번호
	private String contentSeqNo;
	//HEAD컨텐츠
	private String headContent;
	//BODY컨텐츠
	private String bodyContent;
	//적용여부
	private String applyYn;
	//등록자아이디
	private String regId;
	//등록자명
	private String regNm;
	//등록 일시
	private String regDt;
	//수정자아이디
	private String modId;
	//수정자명
	private String modNm;
	//수정 일시
	private String modDt;
	
	private String sitePath;
	
	//발행 일시
	private String publishDt;
	//발행요청 일시
	private String publishReqDt;
	//진행상황
	private String progressStatus;
	//진행상황명
	private String progressStatusNm;
	//기져오기 여부
	private String repairYn;


	public String getSiteCd() {
		return siteCd;
	}
	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}
	public String getSitePath() {
		return sitePath;
	}
	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getContentSeqNo() {
		return contentSeqNo;
	}
	public void setContentSeqNo(String contentSeqNo) {
		this.contentSeqNo = contentSeqNo;
	}
	public String getHeadContent() {
		return headContent;
	}
	public void setHeadContent(String headContent) {
		this.headContent = headContent;
	}
	public String getBodyContent() {
		return bodyContent;
	}
	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}
	public String getApplyYn() {
		return applyYn;
	}
	public void setApplyYn(String applyYn) {
		this.applyYn = applyYn;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
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
	public String getModNm() {
		return modNm;
	}
	public void setModNm(String modNm) {
		this.modNm = modNm;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getPublishDt() {
		return publishDt;
	}
	public void setPublishDt(String publishDt) {
		this.publishDt = publishDt;
	}
	public String getPublishReqDt() {
		return publishReqDt;
	}
	public void setPublishReqDt(String publishReqDt) {
		this.publishReqDt = publishReqDt;
	}
	public String getProgressStatus() {
		return progressStatus;
	}
	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}
	public String getProgressStatusNm() {
		return progressStatusNm;
	}
	public void setProgressStatusNm(String progressStatusNm) {
		this.progressStatusNm = progressStatusNm;
	}
	public String getRepairYn() {
		return repairYn;
	}
	public void setRepairYn(String repairYn) {
		this.repairYn = repairYn;
	}
}
