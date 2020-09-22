package egovframework.injeinc.boffice.sy.group.vo;

import egovframework.cmm.ComDefaultVO;

public class GroupConfigVo extends ComDefaultVO {
	
	private String gcIdx;				//그룹키
	private String gcName;				//그룹명
	private String gcDesc;				//그룹설명
	private String memberCount;			//회원수	
	private String useYn;				//사용여부
	private String regDt;				//등록일자
	private String regId;				//등록자ID
	private String modDt;				//수정일자
	private String modId;				//수정자ID
	private String gcMenu;				//Form 구분값
	private String scRegDtSt;			//등록일(생성일)검색조건(시작일)
	private String scRegDtEd;			//등록일(생성일)검색조건(종료일)
	
	public String getGcIdx() {
		return gcIdx;
	}
	public void setGcIdx(String gcIdx) {
		this.gcIdx = gcIdx;
	}
	public String getGcName() {
		return gcName;
	}
	public void setGcName(String gcName) {
		this.gcName = gcName;
	}
	public String getGcDesc() {
		return gcDesc;
	}
	public void setGcDesc(String gcDesc) {
		this.gcDesc = gcDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getGcMenu() {
		return gcMenu;
	}
	public void setGcMenu(String gcMenu) {
		this.gcMenu = gcMenu;
	}
	public String getScRegDtSt() {
		return scRegDtSt;
	}
	public void setScRegDtSt(String scRegDtSt) {
		this.scRegDtSt = scRegDtSt;
	}
	public String getScRegDtEd() {
		return scRegDtEd;
	}
	public void setScRegDtEd(String scRegDtEd) {
		this.scRegDtEd = scRegDtEd;
	}
	public String getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(String memberCount) {
		this.memberCount = memberCount;
	}
	@Override
	public String toString() {
		return "GroupConfigVo [gcIdx=" + gcIdx + ", gcName=" + gcName
				+ ", gcDesc=" + gcDesc + ", memberCount=" + memberCount
				+ ", useYn=" + useYn + ", regDt=" + regDt + ", regId=" + regId
				+ ", modDt=" + modDt + ", modId=" + modId + ", gcMenu="
				+ gcMenu + ", scRegDtSt=" + scRegDtSt + ", scRegDtEd="
				+ scRegDtEd + "]";
	}
	
}
