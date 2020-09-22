package egovframework.injeinc.foffice.login.vo;

import egovframework.cmm.ComDefaultVO;


public class LoginFVo extends ComDefaultVO {
	
	/** 회원키 */
	private String cuIdx = "";
	/** 회원아이디 */
	private String cuId = "";
	/** 패스워드 */
	private String cuPwd = "";
	/** 이메일 */
	private String email = "";
	/** 이메일 아이디*/
	private String emailid = "";
	/** 이메일 주소*/
	private String emaildomain = "";
	/** 우편번호 */
	private String zip = "";
	/** 주소 */
	private String addr1 = "";
	/** 상세주소 */
	private String addr2 = "";
	/** 전화번호 */
	private String telNum = "";
	/** 전화번호 앞자리 */
	private String tel1 = "";
	/** 전화번호 중간자리*/
	private String tel2 = "";
	/** 전화번호 끝자리*/
	private String tel3 = "";
	/** 핸드폰번호 */
	private String hpNum = "";
	/** 핸드폰번호 앞자리*/
	private String hp1 = "";
	/** 핸드폰번호 중간자리*/
	private String hp2 = "";
	/** 핸드폰번호 끝자리*/
	private String hp3 = "";
	/** 회원이름 */
	private String cuName = "";
	/** 마지막로그인일시 */
	private String lastLogDt = "";
	/** 아이피주소 */
	private String ip = "";
	/** 이메일수신여부 */
	private String emailRcvChk = "";
	/** 14세이하_부모동의 */
	private String youngAgreeName = "";
	/** 개인정보동의일시 */
	private String piAgreeDt = "";
	/** 개인정보동의여부 */
	private String piAgreeYn = "";
	/** sms수신여부 */
	private String smsRcvYn = "";
	/** 등록일시 */
	private String regDt = "";
	/** 등록자ID */
	private String regId = "";
	/** 수정일시 */
	private String modDt = "";
	/** 수정자ID */
	private String modId = "";
	/** 본인확인 */
	private String ownAuth = "";
	/** 비밀번호변경일시 */
	private String pwdChgDt = "";
	/** 로그키 */
	private String ulIdx;
	/** 로그 구분코드*/
	private String logKdCd = "";
	/** 로그인 성공여부*/
	private String loginSucYn;
	/** 로그 구분코드*/
	private String ulMenu;
	/** 리턴url*/
	private String returnurl;
	
	private String birth;
	
	private String coNum;
	
	private String sex;
	
	private String slibno;
	
	private String teamName;
	
	private String numGubun;
	
	//비밀번호 찾기에 사용
	private String searchPwd;
	
	/* 로그인시 referer */
	private String referUrl;
	private String loginReferer;
	
	public String getNumGubun() {
		return numGubun;
	}
	public void setNumGubun(String numGubun) {
		this.numGubun = numGubun;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getSlibno() {
		return slibno;
	}
	public void setSlibno(String slibno) {
		this.slibno = slibno;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCoNum() {
		return coNum;
	}
	public void setCoNum(String coNum) {
		this.coNum = coNum;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCuIdx() {
		return cuIdx;
	}
	public void setCuIdx(String cuIdx) {
		this.cuIdx = cuIdx;
	}
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getCuPwd() {
		return cuPwd;
	}
	public void setCuPwd(String cuPwd) {
		this.cuPwd = cuPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getEmaildomain() {
		return emaildomain;
	}
	public void setEmaildomain(String emaildomain) {
		this.emaildomain = emaildomain;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getHpNum() {
		return hpNum;
	}
	public void setHpNum(String hpNum) {
		this.hpNum = hpNum;
	}
	public String getHp1() {
		return hp1;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	public String getHp2() {
		return hp2;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	public String getHp3() {
		return hp3;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public String getLastLogDt() {
		return lastLogDt;
	}
	public void setLastLogDt(String lastLogDt) {
		this.lastLogDt = lastLogDt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getEmailRcvChk() {
		return emailRcvChk;
	}
	public void setEmailRcvChk(String emailRcvChk) {
		this.emailRcvChk = emailRcvChk;
	}
	public String getYoungAgreeName() {
		return youngAgreeName;
	}
	public void setYoungAgreeName(String youngAgreeName) {
		this.youngAgreeName = youngAgreeName;
	}
	public String getPiAgreeDt() {
		return piAgreeDt;
	}
	public void setPiAgreeDt(String piAgreeDt) {
		this.piAgreeDt = piAgreeDt;
	}
	public String getPiAgreeYn() {
		return piAgreeYn;
	}
	public void setPiAgreeYn(String piAgreeYn) {
		this.piAgreeYn = piAgreeYn;
	}
	public String getSmsRcvYn() {
		return smsRcvYn;
	}
	public void setSmsRcvYn(String smsRcvYn) {
		this.smsRcvYn = smsRcvYn;
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
	public String getOwnAuth() {
		return ownAuth;
	}
	public void setOwnAuth(String ownAuth) {
		this.ownAuth = ownAuth;
	}
	public String getPwdChgDt() {
		return pwdChgDt;
	}
	public void setPwdChgDt(String pwdChgDt) {
		this.pwdChgDt = pwdChgDt;
	}
	public String getUlIdx() {
		return ulIdx;
	}
	public void setUlIdx(String ulIdx) {
		this.ulIdx = ulIdx;
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
	public String getUlMenu() {
		return ulMenu;
	}
	public void setUlMenu(String ulMenu) {
		this.ulMenu = ulMenu;
	}
	public String getReturnurl() {
		return returnurl;
	}
	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}
	public String getReferUrl() {
		return referUrl;
	}
	public void setReferUrl(String referUrl) {
		this.referUrl = referUrl;
	}
	public String getloginReferer() {
		return loginReferer;
	}
	public void setloginReferer(String loginReferer) {
		this.loginReferer = loginReferer;
	}
	public String getSearchPwd() {
		return searchPwd;
	}
	public void setSearchPwd(String searchPwd) {
		this.searchPwd = searchPwd;
	}

}
