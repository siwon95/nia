package egovframework.injeinc.boffice.group.emp.vo;

import egovframework.cmm.ComDefaultVO;

public class EmpTempVo extends ComDefaultVO{
	
	//idx
	private int userIdx = 0;
	//idx 값들
	private String idxValues;
	//아이디
	private String userId;
	//이름
	private String userName;
	//팀아이디
	private String sectionId;
	//팀명
	private String sectionName;
	//과아이디
	private String deptId;
	//과이름
	private String deptName;
	//직위
	private String rankName;
	//전화번호
	private String tel;
	//핸드폰번호
	private String mobile;
	//이메일
	private String mail;
	//업무내용
	private String workDivisionContents;
	//순번
	private String odr;
	//사용
	private String useYn;
	//표기
	private String markYn;
	//상태코드
	private String userStatus;
	//상태명
	private String userStatusName;
	//등록날짜
	private String insertDate;
	//입력여부
	private String insertYn;
	
	//view테이블 국명
	private String dvDepth1;
	//view테이블 과명
	private String dvDepth2;
	//view테이블 팀명
	private String dvDepth3;
	//view테이블 국 cdIdx
	private String dvIdxdepth1;
	//view테이블 과 cdIdx
	private String dvIdxdepth2;
	//view테이블 팀 cdIdx
	private String dvIdxdepth3;
	//view테이블 국 코드
	private String dvDepstep1;
	//view테이블 과 코드
	private String dvDepstep2;
	//view테이블 팀 코드
	private String dvDepstep3;
	
	//검색 년
	private String year;
	//검색 월
	private String month;
	//검색 일
	private String day;
	//검색리스트 줄 수
	private String listcnt;
	
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public String getUserId() {
		return userId == null ? "" : userId;
	}
	public String getIdxValues() {
		return idxValues == null ? "" : idxValues;
	}
	public void setIdxValues(String idxValues) {
		this.idxValues = idxValues;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName == null ? "" : userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSectionId() {
		return sectionId == null ? "" : sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName == null ? "" : sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getDeptId() {
		return deptId == null ? "" : deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName == null ? "" : deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getRankName() {
		return rankName == null ? "" : rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getTel() {
		return tel == null ? "" : tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile == null ? "" : mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail == null ? "" : mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getWorkDivisionContents() {
		return workDivisionContents == null ? "" : workDivisionContents;
	}
	public void setWorkDivisionContents(String workDivisionContents) {
		this.workDivisionContents = workDivisionContents;
	}
	public String getOdr() {
		return odr == null ? "" : odr;
	}
	public void setOdr(String odr) {
		this.odr = odr;
	}
	public String getUseYn() {
		return useYn == null ? "" : useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMarkYn() {
		return markYn == null ? "" : markYn;
	}
	public void setMarkYn(String markYn) {
		this.markYn = markYn;
	}
	public String getUserStatus() {
		return userStatus == null ? "" : userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserStatusName() {
		return userStatusName == null ? "" : userStatusName;
	}
	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}
	public String getInsertDate() {
		return insertDate == null ? "" : insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getInsertYn() {
		return insertYn == null ? "" : insertYn;
	}
	public void setInsertYn(String insertYn) {
		this.insertYn = insertYn;
	}
	public String getDvDepth1() {
		return dvDepth1 == null ? "" : dvDepth1;
	}
	public void setDvDepth1(String dvDepth1) {
		this.dvDepth1 = dvDepth1;
	}
	public String getDvDepth2() {
		return dvDepth2 == null ? "" : dvDepth2;
	}
	public void setDvDepth2(String dvDepth2) {
		this.dvDepth2 = dvDepth2;
	}
	public String getDvDepth3() {
		return dvDepth3 == null ? "" : dvDepth3;
	}
	public void setDvDepth3(String dvDepth3) {
		this.dvDepth3 = dvDepth3;
	}
	public String getDvIdxdepth1() {
		return dvIdxdepth1 == null ? "" : dvIdxdepth1;
	}
	public void setDvIdxdepth1(String dvIdxdepth1) {
		this.dvIdxdepth1 = dvIdxdepth1;
	}
	public String getDvIdxdepth2() {
		return dvIdxdepth2 == null ? "" : dvIdxdepth2;
	}
	public void setDvIdxdepth2(String dvIdxdepth2) {
		this.dvIdxdepth2 = dvIdxdepth2;
	}
	public String getDvIdxdepth3() {
		return dvIdxdepth3 == null ? "" : dvIdxdepth3;
	}
	public void setDvIdxdepth3(String dvIdxdepth3) {
		this.dvIdxdepth3 = dvIdxdepth3;
	}
	public String getDvDepstep1() {
		return dvDepstep1;
	}
	public void setDvDepstep1(String dvDepstep1) {
		this.dvDepstep1 = dvDepstep1;
	}
	public String getDvDepstep2() {
		return dvDepstep2 == null ? "" : dvDepstep2;
	}
	public void setDvDepstep2(String dvDepstep2) {
		this.dvDepstep2 = dvDepstep2;
	}
	public String getDvDepstep3() {
		return dvDepstep3 == null ? "" : dvDepstep3;
	}
	public void setDvDepstep3(String dvDepstep3) {
		this.dvDepstep3 = dvDepstep3;
	}
	public String getYear() {
		return year == null ? "" : year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month == null ? "" : month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day == null ? "" : day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getListcnt() {
		return listcnt == null ? "" : listcnt;
	}
	public void setListcnt(String listcnt) {
		this.listcnt = listcnt;
	}
	
}
