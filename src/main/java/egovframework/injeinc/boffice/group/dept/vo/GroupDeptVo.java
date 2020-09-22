package egovframework.injeinc.boffice.group.dept.vo;

import egovframework.cmm.ComDefaultVO;

public class GroupDeptVo extends ComDefaultVO{
	
	//idx
	private String cdIdx = "";
	//이름
	private String cdSubject = "";
	//국+과+팀 코드
	private String cdDepstep = "";
	//국코드
	private String cdDepstep1 = "";
	//과코드
	private String cdDepstep2 = "";
	//팀코드
	private String cdDepstep3 = "";
	//상위코드(위에서 최대값)
	private String maxCdDepstep = "";
	//하위코드(밑에서 최소값)
	private String minCdDepstep = "";
	//임시상위코드
	private String tempMinCdDepstep = "";
	//임시하위코드
	private String tempMaxCdDepstep = "";
	//주요업무
	private String cdText = "";
	//사용여부
	private String cdUse = "";
	//등록일
	private String cdDate = "";
	//수정일
	private String cdUpdate = "";
	//조직도코드
	private String cdCode = "";
	//전화
	private String cdTel = "";
	//팩스
	private String cdFax = "";
	//위치
	private String cdArea = "";
	//홈페이지
	private String cdHomepage = "";
	//영어이름
	private String cdEngSubject = "";
	//중국이름
	private String cdChiSubject = "";
	//서브프레임값
	private String subframe = "";
	//모드(순서변경시 up or down)
	private String mod ="";
	//우선순위(전화번호)
	private String orgTel = "";
	//메뉴 등록날짜
	private String regDt = "";
	//메뉴 수정날짜
	private String modDt = "";
	//(y:레이아웃 O n:레이아웃 X)
	private String layout;
	
	private String dvDepth1;
	private String dvDepth2;
	private String dvDepth3;
	private String dvIdxdepth1;
	private String dvIdxdepth2;
	private String dvIdxdepth3;
	
	private String teamId;
	private String groupId;
	private String teamName;
	private String sortNo;
	
	/**조직트리를 위한 값*/
	private String id;
	private String parent;
	private String text;
	private String position;
	private String deptPath;
	private int level = 0;
	
	private String ceName;
	
	private String mode;

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

	public String getCdDepstep() {
		return cdDepstep;
	}

	public void setCdDepstep(String cdDepstep) {
		this.cdDepstep = cdDepstep;
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

	public String getMaxCdDepstep() {
		return maxCdDepstep;
	}

	public void setMaxCdDepstep(String maxCdDepstep) {
		this.maxCdDepstep = maxCdDepstep;
	}

	public String getMinCdDepstep() {
		return minCdDepstep;
	}

	public void setMinCdDepstep(String minCdDepstep) {
		this.minCdDepstep = minCdDepstep;
	}

	public String getTempMinCdDepstep() {
		return tempMinCdDepstep;
	}

	public void setTempMinCdDepstep(String tempMinCdDepstep) {
		this.tempMinCdDepstep = tempMinCdDepstep;
	}

	public String getTempMaxCdDepstep() {
		return tempMaxCdDepstep;
	}

	public void setTempMaxCdDepstep(String tempMaxCdDepstep) {
		this.tempMaxCdDepstep = tempMaxCdDepstep;
	}

	public String getCdText() {
		return cdText;
	}

	public void setCdText(String cdText) {
		this.cdText = cdText;
	}

	public String getCdUse() {
		return cdUse;
	}

	public void setCdUse(String cdUse) {
		this.cdUse = cdUse;
	}

	public String getCdDate() {
		return cdDate;
	}

	public void setCdDate(String cdDate) {
		this.cdDate = cdDate;
	}

	public String getCdUpdate() {
		return cdUpdate;
	}

	public void setCdUpdate(String cdUpdate) {
		this.cdUpdate = cdUpdate;
	}

	public String getCdCode() {
		return cdCode;
	}

	public void setCdCode(String cdCode) {
		this.cdCode = cdCode;
	}

	public String getCdTel() {
		return cdTel;
	}

	public void setCdTel(String cdTel) {
		this.cdTel = cdTel;
	}

	public String getCdFax() {
		return cdFax;
	}

	public void setCdFax(String cdFax) {
		this.cdFax = cdFax;
	}

	public String getCdArea() {
		return cdArea;
	}

	public void setCdArea(String cdArea) {
		this.cdArea = cdArea;
	}

	public String getCdHomepage() {
		return cdHomepage;
	}

	public void setCdHomepage(String cdHomepage) {
		this.cdHomepage = cdHomepage;
	}

	public String getCdEngSubject() {
		return cdEngSubject;
	}

	public void setCdEngSubject(String cdEngSubject) {
		this.cdEngSubject = cdEngSubject;
	}

	public String getCdChiSubject() {
		return cdChiSubject;
	}

	public void setCdChiSubject(String cdChiSubject) {
		this.cdChiSubject = cdChiSubject;
	}

	public String getSubframe() {
		return subframe;
	}

	public void setSubframe(String subframe) {
		this.subframe = subframe;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	public String getOrgTel() {
		return orgTel;
	}

	public void setOrgTel(String orgTel) {
		this.orgTel = orgTel;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getDvDepth1() {
		return dvDepth1;
	}

	public void setDvDepth1(String dvDepth1) {
		this.dvDepth1 = dvDepth1;
	}

	public String getDvDepth2() {
		return dvDepth2;
	}

	public void setDvDepth2(String dvDepth2) {
		this.dvDepth2 = dvDepth2;
	}

	public String getDvDepth3() {
		return dvDepth3;
	}

	public void setDvDepth3(String dvDepth3) {
		this.dvDepth3 = dvDepth3;
	}

	public String getDvIdxdepth1() {
		return dvIdxdepth1;
	}

	public void setDvIdxdepth1(String dvIdxdepth1) {
		this.dvIdxdepth1 = dvIdxdepth1;
	}

	public String getDvIdxdepth2() {
		return dvIdxdepth2;
	}

	public void setDvIdxdepth2(String dvIdxdepth2) {
		this.dvIdxdepth2 = dvIdxdepth2;
	}

	public String getDvIdxdepth3() {
		return dvIdxdepth3;
	}

	public void setDvIdxdepth3(String dvIdxdepth3) {
		this.dvIdxdepth3 = dvIdxdepth3;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDeptPath() {
		return deptPath;
	}

	public void setDeptPath(String deptPath) {
		this.deptPath = deptPath;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCeName() {
		return ceName;
	}

	public void setCeName(String ceName) {
		this.ceName = ceName;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
