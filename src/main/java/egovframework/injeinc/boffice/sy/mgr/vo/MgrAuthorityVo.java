package egovframework.injeinc.boffice.sy.mgr.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class MgrAuthorityVo extends ComDefaultVO {

	private String maMlidx			= "";
	private String maType			= "";
	private String maPkidx				= "0";
	private String regDt			= "";
	private String regId			= "";
	
	private String searchType		= "";
	private String roleIdx			= "";
	private String mgrSiteCdQuery			= "";
	
	public String getMaMlidx() {
		return maMlidx;
	}
	public void setMaMlidx(String maMlidx) {
		this.maMlidx = maMlidx;
	}
	public String getMaType() {
		return maType;
	}
	public void setMaType(String maType) {
		this.maType = maType;
	}
	public String getMaPkidx() {
		return maPkidx;
	}
	public void setMaPkidx(String maPkidx) {
		this.maPkidx = maPkidx;
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
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getRoleIdx() {
		return roleIdx;
	}
	public void setRoleIdx(String roleIdx) {
		this.roleIdx = roleIdx;
	}
	public String getMgrSiteCdQuery() {
		return mgrSiteCdQuery;
	}
	public void setMgrSiteCdQuery(String mgrSiteCdQuery) {
		this.mgrSiteCdQuery = mgrSiteCdQuery;
	}
	
}