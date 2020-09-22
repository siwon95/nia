package egovframework.injeinc.common.social.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class PushVo extends ComDefaultVO {
		
	private String piIdx = "";
	private String piRegistrationId = "";
	private String piGubun = "";
	private String regdt = "";
	private String oldRegistkey = "";
	private String newRegistkey = "";
	private String osType="";
	
	
	public String getPiIdx() {
		return piIdx;
	}
	public void setPiIdx(String piIdx) {
		this.piIdx = piIdx;
	}
	public String getPiRegistrationId() {
		return piRegistrationId;
	}
	public void setPiRegistrationId(String piRegistrationId) {
		this.piRegistrationId = piRegistrationId;
	}
	public String getPiGubun() {
		return piGubun;
	}
	public void setPiGubun(String piGubun) {
		this.piGubun = piGubun;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getOldRegistkey() {
		return oldRegistkey;
	}
	public void setOldRegistkey(String oldRegistkey) {
		this.oldRegistkey = oldRegistkey;
	}
	public String getNewRegistkey() {
		return newRegistkey;
	}
	public void setNewRegistkey(String newRegistkey) {
		this.newRegistkey = newRegistkey;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	
	
}
