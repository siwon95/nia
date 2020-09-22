package egovframework.injeinc.boffice.sy.user.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class UserVo extends ComDefaultVO {
	private String guIdx;
	
	private String cuIdx;				//회원키
	private String gcIdx;				//그룹키
	private String cuId;				//회원아이디
	private String cuPwd;				//패스워드
	private String email;				//이메일
	private String email1;				//이메일 앞자리
	private String email2;				//이메일 뒷자리
	private String zip;					//우편번호
	private String addr1;				//기본주소
	private String addr2;				//상세주소
	private String telNum;				//전화번호
	private String hpNum;				//핸드폰번호
	private String cuName;				//회원이름
	private String lastLogDt;			//마지막로그인일시
	private String ip;					//아이피주소
	private String emailRcvChk;			//이메일수신여부 A:전체 Y:동의 N:거부
	private String youngAgreeName;		//14세이이하_부모동의
	private String piAgreeDt;			//개인정보동의일시
	private String piAgreeYn = "Y";		//개인정보동의여부
	private String smsRcvYn;			//sms 수신여부 A:전체 Y:동의 N:거부
	private String regDt;				//등록일시
	private String regId;				//등록자ID
	private String modDt;				//수정일시
	private String modId;				//수정자ID
	private String ownAuth;				//본인확인
	private String pwdChgDt;			//비밀번호변경일시
	private String userMenu;			//Form 구분값
	private String scRegDtSt;			//가입일검색조건(시작일)
	private String scRegDtEd;			//가입일검색조건(종료일)
	private String scLastLogDtSt;		//최종방문일검색조건(시작일)
	private String scLastLogDtEd;		//최종방문일검색조건(종료일)
	private String scSmsRcvYn;			//sms수신검색조건
	private String scEmailRcvChk;		//이메일수신검색조건
	private String telNum1;				//전화번호1
	private String telNum2;				//전화번호2
	private String telNum3;				//전화번호3
	private String hpNum1;				//핸드폰번호1
	private String hpNum2;				//핸드폰번호2
	private String hpNum3;				//핸드폰번호3
	private String doSav; 				//회원관리 로그
	private String logKdCd;				//회원로그 액션(S,U,D,V)
	private String reason;				// 수정/삭제 사유
	private String fid;                 //확인코드
	private String birthdate;		// 생년월일
	private String numGubun;
	private String coNum;
	
	
	
	public String getNumGubun() {
		return numGubun;
	}
	public void setNumGubun(String numGubun) {
		this.numGubun = numGubun;
	}
	public String getCoNum() {
		return coNum;
	}
	public void setCoNum(String coNum) {
		this.coNum = coNum;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public String getHpNum() {
		return hpNum;
	}
	public void setHpNum(String hpNum) {
		this.hpNum = hpNum;
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
	public String getUserMenu() {
		return userMenu;
	}
	public void setUserMenu(String userMenu) {
		this.userMenu = userMenu;
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
	public String getScLastLogDtSt() {
		return scLastLogDtSt;
	}
	public void setScLastLogDtSt(String scLastLogDtSt) {
		this.scLastLogDtSt = scLastLogDtSt;
	}
	public String getScLastLogDtEd() {
		return scLastLogDtEd;
	}
	public void setScLastLogDtEd(String scLastLogDtEd) {
		this.scLastLogDtEd = scLastLogDtEd;
	}
	public String getScSmsRcvYn() {
		return scSmsRcvYn;
	}
	public void setScSmsRcvYn(String scSmsRcvYn) {
		this.scSmsRcvYn = scSmsRcvYn;
	}
	public String getScEmailRcvChk() {
		return scEmailRcvChk;
	}
	public void setScEmailRcvChk(String scEmailRcvChk) {
		this.scEmailRcvChk = scEmailRcvChk;
	}
	public String getGuIdx() {
		return guIdx;
	}
	public void setGuIdx(String guIdx) {
		this.guIdx = guIdx;
	}
	public String getGcIdx() {
		return gcIdx;
	}
	public void setGcIdx(String gcIdx) {
		this.gcIdx = gcIdx;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getTelNum1() {
		return telNum1;
	}
	public void setTelNum1(String telNum1) {
		this.telNum1 = telNum1;
	}
	public String getTelNum2() {
		return telNum2;
	}
	public void setTelNum2(String telNum2) {
		this.telNum2 = telNum2;
	}
	public String getTelNum3() {
		return telNum3;
	}
	public void setTelNum3(String telNum3) {
		this.telNum3 = telNum3;
	}
	public String getHpNum1() {
		return hpNum1;
	}
	public void setHpNum1(String hpNum1) {
		this.hpNum1 = hpNum1;
	}
	public String getHpNum2() {
		return hpNum2;
	}
	public void setHpNum2(String hpNum2) {
		this.hpNum2 = hpNum2;
	}
	public String getHpNum3() {
		return hpNum3;
	}
	public void setHpNum3(String hpNum3) {
		this.hpNum3 = hpNum3;
	}
	public String getDoSav() {
		return doSav;
	}
	public void setDoSav(String doSav) {
		this.doSav = doSav;
	}
	public String getLogKdCd() {
		return logKdCd;
	}
	public void setLogKdCd(String logKdCd) {
		this.logKdCd = logKdCd;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
}
