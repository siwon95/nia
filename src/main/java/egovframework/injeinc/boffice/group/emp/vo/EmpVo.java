package egovframework.injeinc.boffice.group.emp.vo;

import egovframework.cmm.ComDefaultVO;

public class EmpVo extends ComDefaultVO{
	
	//idx
	private String ceIdx = "";
	//등록일
	private String ceDate = "";
	//수정일
	private String ceUpdate = "";
	//순위
	private String ceSort = "";
	//이름
	private String ceName = "";
	//이메일
	private String ceMailid = "";
	//전화번호
	private String ceTel = "";
	//핸드폰번호
	private String ceHp = "";
	//사용유무
	private String ceUse = "";
	//사진 파일
	private String cePhoto = "";
	//국명
	private String ceDepstep1 = "";
	//과명
	private String ceDepstep2 = "";
	//팀명
	private String ceDepstep3 = "";
	//직위
	private String ceDepstep4 = "";
	//부서 검색
	private String srchceDepstep ="";
	//업무
	private String ceWorks = "";
	//CMS_DEPARTMENT fk
	private String ceCdIdx = "";
	//방문예약 사용여부
	private String ceMeet = "";
	//CMS_EMPLOYEE_TEMP fk
	private String ceDuty = "";
	//부서idx
	private String cdIdx = "";
	//이름
	private String cdSubject = "";
	
	private String cdDepstep = "";
	//국명
	private String cdDepstep1 = "";
	//과명
	private String cdDepstep2 = "";
	//팀명
	private String cdDepstep3 = "";
	//팩스번호
	private String cdFax = "";
	//해당부서 인원수
	private String empCnt = "";
	//셀렉트박스 값
	private String selBox = "";
	//전체검색 체크여부
	private String srchAll = "";
	//actionKey
	private String actionKey = "";
	//최소상위 ceSort
	private String minUpCeSort = "";
	//최대하위 ceSort
	private String maxDownCeSort = "";
	//임의 ceSort
	private String tempCeSort = "";
	//정렬시 위,아래 구분값
	private String step = "";
	
	private String siteCd = "";
	
	private String roleIdx = "";
	
	private String memSsn = "";
	private String memName = "";
	private String memTask = "";
	private String memHp = "";
	private String memTel = "";
	private String memEmail = "";
	private String id = "";
	private String position = "";
	private String teamId = "";
	private String teamName = "";
	private String teamOrder = "0";
	private String sortNum = "0";
	private String photoPath = "";
	private String memExtNum = "0";
	private String ceUserId = "";
	private String siteViewYn = "";
	
	private String mode = "";
	private String level = "";

	public String getCeIdx() {
		return ceIdx;
	}
	public void setCeIdx(String ceIdx) {
		this.ceIdx = ceIdx;
	}
	public String getCeDate() {
		return ceDate;
	}
	public void setCeDate(String ceDate) {
		this.ceDate = ceDate;
	}
	public String getCeUpdate() {
		return ceUpdate;
	}
	public void setCeUpdate(String ceUpdate) {
		this.ceUpdate = ceUpdate;
	}
	public String getCeSort() {
		return ceSort;
	}
	public void setCeSort(String ceSort) {
		this.ceSort = ceSort;
	}
	public String getCeName() {
		return ceName;
	}
	public void setCeName(String ceName) {
		this.ceName = ceName;
	}
	public String getCeMailid() {
		return ceMailid;
	}
	public void setCeMailid(String ceMailid) {
		this.ceMailid = ceMailid;
	}
	public String getCeTel() {
		return ceTel;
	}
	public void setCeTel(String ceTel) {
		this.ceTel = ceTel;
	}
	public String getCeHp() {
		return ceHp;
	}
	public void setCeHp(String ceHp) {
		this.ceHp = ceHp;
	}
	public String getCeUse() {
		return ceUse;
	}
	public void setCeUse(String ceUse) {
		this.ceUse = ceUse;
	}
	public String getCePhoto() {
		return cePhoto;
	}
	public void setCePhoto(String cePhoto) {
		this.cePhoto = cePhoto;
	}
	public String getCeDepstep1() {
		return ceDepstep1;
	}
	public void setCeDepstep1(String ceDepstep1) {
		this.ceDepstep1 = ceDepstep1;
	}
	public String getCeDepstep2() {
		return ceDepstep2;
	}
	public void setCeDepstep2(String ceDepstep2) {
		this.ceDepstep2 = ceDepstep2;
	}
	public String getCeDepstep3() {
		return ceDepstep3;
	}
	public void setCeDepstep3(String ceDepstep3) {
		this.ceDepstep3 = ceDepstep3;
	}
	public String getCeDepstep4() {
		return ceDepstep4;
	}
	public void setCeDepstep4(String ceDepstep4) {
		this.ceDepstep4 = ceDepstep4;
	}
	public String getSrchceDepstep() {
		return srchceDepstep;
	}
	public void setSrchceDepstep(String srchceDepstep) {
		this.srchceDepstep = srchceDepstep;
	}
	public String getCeWorks() {
		return ceWorks;
	}
	public void setCeWorks(String ceWorks) {
		this.ceWorks = ceWorks;
	}
	public String getCeCdIdx() {
		return ceCdIdx;
	}
	public void setCeCdIdx(String ceCdIdx) {
		this.ceCdIdx = ceCdIdx;
	}
	public String getCeMeet() {
		return ceMeet;
	}
	public void setCeMeet(String ceMeet) {
		this.ceMeet = ceMeet;
	}
	public String getCeDuty() {
		return ceDuty;
	}
	public void setCeDuty(String ceDuty) {
		this.ceDuty = ceDuty;
	}
	public String getCdIdx() {
		return cdIdx;
	}
	public void setCdIdx(String cdIdx) {
		this.cdIdx = cdIdx;
	}
	public String getCdSubject() {
		return cdSubject;
	}
	public void setCdSubject(String cdSubject) {
		this.cdSubject = cdSubject;
	}
	public String getCdDepstep1() {
		return cdDepstep1;
	}
	public void setCdDepstep1(String cdDepstep1) {
		this.cdDepstep1 = cdDepstep1;
	}
	public String getCdDepstep2() {
		return cdDepstep2;
	}
	public void setCdDepstep2(String cdDepstep2) {
		this.cdDepstep2 = cdDepstep2;
	}
	public String getCdDepstep3() {
		return cdDepstep3;
	}
	public void setCdDepstep3(String cdDepstep3) {
		this.cdDepstep3 = cdDepstep3;
	}
	public String getEmpCnt() {
		return empCnt;
	}
	public void setEmpCnt(String empCnt) {
		this.empCnt = empCnt;
	}
	public String getSelBox() {
		return selBox;
	}
	public void setSelBox(String selBox) {
		this.selBox = selBox;
	}
	public String getSrchAll() {
		return srchAll;
	}
	public void setSrchAll(String srchAll) {
		this.srchAll = srchAll;
	}
	public String getActionKey() {
		return actionKey;
	}
	public void setActionKey(String actionKey) {
		this.actionKey = actionKey;
	}
	public String getMinUpCeSort() {
		return minUpCeSort;
	}
	public void setMinUpCeSort(String minUpCeSort) {
		this.minUpCeSort = minUpCeSort;
	}
	public String getMaxDownCeSort() {
		return maxDownCeSort;
	}
	public void setMaxDownCeSort(String maxDownCeSort) {
		this.maxDownCeSort = maxDownCeSort;
	}
	public String getTempCeSort() {
		return tempCeSort;
	}
	public void setTempCeSort(String tempCeSort) {
		this.tempCeSort = tempCeSort;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getSiteCd() {
		return siteCd;
	}
	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}
	public String getRoleIdx() {
		return roleIdx;
	}
	public void setRoleIdx(String roleIdx) {
		this.roleIdx = roleIdx;
	}
	public String getMemSsn() {
		return memSsn;
	}
	public void setMemSsn(String memSsn) {
		this.memSsn = memSsn;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemTask() {
		return memTask;
	}
	public void setMemTask(String memTask) {
		this.memTask = memTask;
	}
	public String getMemHp() {
		return memHp;
	}
	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamOrder() {
		return teamOrder;
	}
	public void setTeamOrder(String teamOrder) {
		this.teamOrder = teamOrder;
	}
	public String getSortNum() {
		return sortNum;
	}
	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getMemExtNum() {
		return memExtNum;
	}
	public void setMemExtNum(String memExtNum) {
		this.memExtNum = memExtNum;
	}
	public String getCeUserId() {
		return ceUserId;
	}
	public void setCeUserId(String ceUserId) {
		this.ceUserId = ceUserId;
	}
	public String getSiteViewYn() {
		return siteViewYn;
	}
	public void setSiteViewYn(String siteViewYn) {
		this.siteViewYn = siteViewYn;
	}
	public String getCdFax() {
		return cdFax;
	}
	public void setCdFax(String cdFax) {
		this.cdFax = cdFax;
	}
	public String getCdDepstep() {
		return cdDepstep;
	}
	public void setCdDepstep(String cdDepstep) {
		this.cdDepstep = cdDepstep;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
