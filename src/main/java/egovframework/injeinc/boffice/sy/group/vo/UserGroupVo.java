package egovframework.injeinc.boffice.sy.group.vo;

import egovframework.cmm.ComDefaultVO;

public class UserGroupVo extends ComDefaultVO {
	private String ugIdx;				//회원그룹맵핑키
	private String cuIdx;				//회원키
	private String gcIdx;				//그룹키
	private String useYn;				//사용여부
	private String cuId;				//아이디
	private String cuName;				//이름
	private String lastLogDt;			//최종방문일
	private String regDt;				//등록일자(그룹 가입일)
	private String regId;				//등록자ID
	private String modDt;				//수정일자
	private String modId;				//수정자ID
	private String gcMenu;				//Form 구분값
	private String scRegDtSt;			//등록일(생성일)검색조건(시작일)
	private String scRegDtEd;			//등록일(생성일)검색조건(종료일)
	
	public String getUgIdx() {
		return ugIdx;
	}
	public void setUgIdx(String ugIdx) {
		this.ugIdx = ugIdx;
	}
	public String getCuIdx() {
		return cuIdx;
	}
	public void setCuIdx(String cuIdx) {
		this.cuIdx = cuIdx;
	}
	public String getGcIdx() {
		return gcIdx;
	}
	public void setGcIdx(String gcIdx) {
		this.gcIdx = gcIdx;
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
	public String getLastLogDt() {
		return lastLogDt;
	}
	public void setLastLogDt(String lastLogDt) {
		this.lastLogDt = lastLogDt;
	}
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	@Override
	public String toString() {
		return "UserGroupVo [ugIdx=" + ugIdx + ", cuIdx=" + cuIdx + ", gcIdx="
				+ gcIdx + ", useYn=" + useYn + ", cuId=" + cuId + ", cuName="
				+ cuName + ", lastLogDt=" + lastLogDt + ", regDt=" + regDt
				+ ", regId=" + regId + ", modDt=" + modDt + ", modId=" + modId
				+ ", gcMenu=" + gcMenu + ", scRegDtSt=" + scRegDtSt
				+ ", scRegDtEd=" + scRegDtEd + ", getUgIdx()=" + getUgIdx()
				+ ", getCuIdx()=" + getCuIdx() + ", getGcIdx()=" + getGcIdx()
				+ ", getUseYn()=" + getUseYn() + ", getRegDt()=" + getRegDt()
				+ ", getRegId()=" + getRegId() + ", getModDt()=" + getModDt()
				+ ", getModId()=" + getModId() + ", getGcMenu()=" + getGcMenu()
				+ ", getScRegDtSt()=" + getScRegDtSt() + ", getScRegDtEd()="
				+ getScRegDtEd() + ", getLastLogDt()=" + getLastLogDt()
				+ ", getCuId()=" + getCuId() + ", getCuName()=" + getCuName()
				+ ", getFirstIndex()=" + getFirstIndex() + ", getLastIndex()="
				+ getLastIndex() + ", getRecordCountPerPage()="
				+ getRecordCountPerPage() + ", getSearchCondition()="
				+ getSearchCondition() + ", getSearchKeyword()="
				+ getSearchKeyword() + ", getSearchUseYn()=" + getSearchUseYn()
				+ ", getPageIndex()=" + getPageIndex() + ", getPageUnit()="
				+ getPageUnit() + ", getPageSize()=" + getPageSize()
				+ ", toString()=" + super.toString()
				+ ", getSearchKeywordFrom()=" + getSearchKeywordFrom()
				+ ", getSearchKeywordTo()=" + getSearchKeywordTo()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
}
