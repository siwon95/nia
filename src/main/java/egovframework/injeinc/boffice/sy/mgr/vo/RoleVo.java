package egovframework.injeinc.boffice.sy.mgr.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class RoleVo extends ComDefaultVO {
	private String roleIdx; //일련번호
	private String roleName;				//롤이름
	private String regDt;					//등록일시
	private String regId;					//등록자ID
	private String modDt;					//수정일시
	private String modId;					//수정자ID
	private String useYn;					//사용여부
	private String publishAuthYn;			//발행권한여부
	private String permCd;					//권한유형
	private String mgrSiteCd;				//관리사이트
	
	private String searchPermCd		= "";
	private String actionkey		= "";
	
	public String getRoleIdx() {
		return roleIdx;
	}
	public void setRoleIdx(String roleIdx) {
		this.roleIdx = roleIdx;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getPublishAuthYn() {
		return publishAuthYn;
	}
	public void setPublishAuthYn(String publishAuthYn) {
		this.publishAuthYn = publishAuthYn;
	}
	public String getPermCd() {
		return permCd;
	}
	public void setPermCd(String permCd) {
		this.permCd = permCd;
	}
	public String getActionkey() {
		return actionkey;
	}
	public void setActionkey(String actionkey) {
		this.actionkey = actionkey;
	}
	public String getSearchPermCd() {
		return searchPermCd;
	}
	public void setSearchPermCd(String searchPermCd) {
		this.searchPermCd = searchPermCd;
	}
	public String getMgrSiteCd() {
		return mgrSiteCd;
	}
	public void setMgrSiteCd(String mgrSiteCd) {
		this.mgrSiteCd = mgrSiteCd;
	}

}
