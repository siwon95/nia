package egovframework.injeinc.boffice.sy.user.vo;

public class UserLogVo {
	
	String cuId;			//로그키
	String ulIdx;			//회원구분코드
	String ip;				//아이피
	String regDt;			//등록일시
	String logKdCd;			//로그구분코드
	String loginSucYn;		//로그인성공여부
	
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getUlIdx() {
		return ulIdx;
	}
	public void setUlIdx(String ulIdx) {
		this.ulIdx = ulIdx;
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
	public String getLogKdCd() {
		return logKdCd;
	}
	public void setLogKdCd(String logKdCd) {
		this.logKdCd = logKdCd;
	}
	public String getLoginSucYn() {
		return loginSucYn;
	}
	public void setLoginSucYn(String loginSucYn) {
		this.loginSucYn = loginSucYn;
	}
	
}
