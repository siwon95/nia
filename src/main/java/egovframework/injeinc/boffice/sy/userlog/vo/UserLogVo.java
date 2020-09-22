package egovframework.injeinc.boffice.sy.userlog.vo;

import egovframework.cmm.ComDefaultVO;

public class UserLogVo extends ComDefaultVO {
	private String ulIdx;
	private String cuId;
	private String logKdCd;
	private String ip;
	private String regDt;
	private String loginSucYn;
	private String ulMenu;
	
	public String getUlIdx() {
		return ulIdx;
	}
	public void setUlIdx(String ulIdx) {
		this.ulIdx = ulIdx;
	}
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getLogKdCd() {
		return logKdCd;
	}
	public void setLogKdCd(String logKdCd) {
		this.logKdCd = logKdCd;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUlMenu() {
		return ulMenu;
	}
	public void setUlMenu(String ulMenu) {
		this.ulMenu = ulMenu;
	}
	public String getLoginSucYn() {
		return loginSucYn;
	}
	public void setLoginSucYn(String loginSucYn) {
		this.loginSucYn = loginSucYn;
	}
	
}
