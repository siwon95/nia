package egovframework.injeinc.foffice.user.vo;

import egovframework.cmm.ComDefaultVO;

public class UserFVo extends ComDefaultVO {
	/** 회원키 */
	private String cuIdx = "";
	/** 회원아이디 */
	private String cuId = "";
	/** 패스워드 */
	private String cuPwd = "";
	/** 새 패스워드 */
	private String newpwd = "";
	/** 이메일 */
	private String email = "";
	/** 이메일 아이디*/
	private String emailid = "";
	/** 이메일 주소*/
	private String emaildomain = "";
	/** 이메일 직접입력시*/
	private String emaildomainTextYn = "";
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
	/** 타입(비밀번호 재변경시 사용) */
	private String type = "";
	/** 어린이 여부 */
	private String youngYn = "";
	
	/** 그룹키 */
	private String gcIdx = "";
	/** 맵핑키 */
	private String ugIdx = "";
	/** 사용여부 */
	private String useYn = "";
	/** 그룹명 */
	private String gcName = "";
	/** 그룹설명 */
	private String gcDesc = "";
	/** 자동방지숫자1 */
	private String num1 = "";
	/** 자동방지숫자2 */
	private String num2 = "";
	/** 자동방지숫자3 */
	private String num3 = "";
	/** 자동방지숫자4 */
	private String num4 = "";
	/** 자동방지숫자 합 */
	private String addNum = "";
	/** 확인코드 */
	private String fid = "";
	/** Form 구분값*/
	private String userMenu = "";
	
	
	//마일리지관련
	
	/** 순서 */
	private int seq;
	/** 아이디 */
	private String id;
	/** 타이틀 */
	private String title;
	/** plus or minus */
	private String act;
	/** 사용포인트 */
	private String usePoint;
	/** 등록일 */
	private String insdat;
	/** 현재포인트 */
	private String nowPoint;
	/** 태그 */
	private String tag;
	
	/** 뉴스레터 발송 여부 */
	private String enewsRcvYn ="";
	
	/** 선택설정 항목 */
	private String selectConf;
	
	/** 연락처 구분 */
	private String numGubun = "";
	
	/** 직장 전화번호 */
	private String coNum = "";
	
	/** 직장전화번호 앞자리 */
	private String coTel1 = "";
	
	/** 직장전화번호 중간자리*/
	private String coTel2 = "";
	
	/** 직장전화번호 끝자리*/
	private String coTel3 = "";
	
	/** 생년월일 */
	private String birthDate = "";
	
	private String pHomepage = "";
	
	private String homepageType = "";
	
	private String reason = "";
	
	private String reasonType = "";
	
	private String slibno = "";
	
	private String sex = "";
	
	private String cumax = "";
	
	/* 회원가입 입력폼 > 지역 */
	private String area;
	
	private String searchPwd;
	
	/* 로그인 referer 구분값*/
	private String loginExclude;
	
	public String getCumax() {
		return cumax;
	}
	public void setCumax(String cumax) {
		this.cumax = cumax;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSlibno() {
		return slibno;
	}
	public void setSlibno(String slibno) {
		this.slibno = slibno;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getHomepageType() {
		return homepageType;
	}
	public void setHomepageType(String homepageType) {
		this.homepageType = homepageType;
	}
	public String getpHomepage() {
		return pHomepage;
	}
	public void setpHomepage(String pHomepage) {
		this.pHomepage = pHomepage;
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
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getYoungYn() {
		return youngYn;
	}
	public void setYoungYn(String youngYn) {
		this.youngYn = youngYn;
	}
	public String getGcIdx() {
		return gcIdx;
	}
	public void setGcIdx(String gcIdx) {
		this.gcIdx = gcIdx;
	}
	public String getUgIdx() {
		return ugIdx;
	}
	public void setUgIdx(String ugIdx) {
		this.ugIdx = ugIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getNum1() {
		return num1;
	}
	public void setNum1(String num1) {
		this.num1 = num1;
	}
	public String getNum2() {
		return num2;
	}
	public void setNum2(String num2) {
		this.num2 = num2;
	}
	public String getNum3() {
		return num3;
	}
	public void setNum3(String num3) {
		this.num3 = num3;
	}
	public String getNum4() {
		return num4;
	}
	public void setNum4(String num4) {
		this.num4 = num4;
	}
	public String getAddNum() {
		return addNum;
	}
	public void setAddNum(String addNum) {
		this.addNum = addNum;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getUsePoint() {
		return usePoint;
	}
	public void setUsePoint(String usePoint) {
		this.usePoint = usePoint;
	}
	public String getInsdat() {
		return insdat;
	}
	public void setInsdat(String insdat) {
		this.insdat = insdat;
	}
	public String getNowPoint() {
		return nowPoint;
	}
	public void setNowPoint(String nowPoint) {
		this.nowPoint = nowPoint;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getEnewsRcvYn() {
		return enewsRcvYn;
	}
	public void setEnewsRcvYn(String enewsRcvYn) {
		this.enewsRcvYn = enewsRcvYn;
	}
	public String getSelectConf() {
		return selectConf;
	}
	public void setSelectConf(String selectConf) {
		this.selectConf = selectConf;
	}
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
	public String getCoTel1() {
		return coTel1;
	}
	public void setCoTel1(String coTel1) {
		this.coTel1 = coTel1;
	}
	public String getCoTel2() {
		return coTel2;
	}
	public void setCoTel2(String coTel2) {
		this.coTel2 = coTel2;
	}
	public String getCoTel3() {
		return coTel3;
	}
	public void setCoTel3(String coTel3) {
		this.coTel3 = coTel3;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getUserMenu() {
		return userMenu;
	}
	public void setUserMenu(String userMenu) {
		this.userMenu = userMenu;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSearchPwd() {
		return searchPwd;
	}
	public void setSearchPwd(String searchPwd) {
		this.searchPwd = searchPwd;
	}
	public String getEmaildomainTextYn() {
		return emaildomainTextYn;
	}
	public void setEmaildomainTextYn(String emaildomainTextYn) {
		this.emaildomainTextYn = emaildomainTextYn;
	}
	public String getLoginExclude() {
		return loginExclude;
	}
	public void setLoginExclude(String loginExclude) {
		this.loginExclude = loginExclude;
	}
	
}