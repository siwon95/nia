package egovframework.injeinc.boffice.ex.reservationMain.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class ReservationMainVo extends ComDefaultVO {
	
	private String rmiIdx = "";			//idx
	private String rmiTitle = "";		//제목
	private String rmiPostSdt = "";		//게시시작날짜
	private String rmiPostEdt = "";		//게시마감날짜
	private String rmiLink = "";		//링크url
	private String rmiTarget = "";		//타겟
	private String rmiMainOrgname = "";	//메인원본파일명
	private String rmiMainRename = "";	//메인저장파일명
	private String rmiMainPath = "";	//메인저장경로
	private String rmiBannerOrgname = "";//베너원본파일명
	private String rmiBannerRename = "";//베너저장파일명
	private String rmiBannerPath = "";	//베너저장경로
	private String rmiContent = "";		//이미지설명(메인)
	private String rmiContent2 = "";	//이미지설명(베너)
	private String rmiPostYn = "";		//게시상태
	private String regDt = "";			//등록날짜
	private String regId = "";			//등록자ID
	private String modDt = "";			//수정날짜
	private String modId = "";			//수정자ID
	private String useYn = "";			//사용유무
	
	/** 날짜관련 */
	private String startday = "";		//시작날짜
	private String starthour = "";		//시작시
	private String startminute = "";	//시간분
	private String endday = "";			//종료날짜
	private String endhour = "";		//종료시
	private String endminute = "";		//종료분
	
	public String getRmiIdx() {
		return rmiIdx;
	}
	public void setRmiIdx(String rmiIdx) {
		this.rmiIdx = rmiIdx;
	}
	public String getRmiTitle() {
		return rmiTitle;
	}
	public void setRmiTitle(String rmiTitle) {
		this.rmiTitle = rmiTitle;
	}
	public String getRmiPostSdt() {
		return rmiPostSdt;
	}
	public void setRmiPostSdt(String rmiPostSdt) {
		this.rmiPostSdt = rmiPostSdt;
	}
	public String getRmiPostEdt() {
		return rmiPostEdt;
	}
	public void setRmiPostEdt(String rmiPostEdt) {
		this.rmiPostEdt = rmiPostEdt;
	}
	public String getRmiLink() {
		return rmiLink;
	}
	public void setRmiLink(String rmiLink) {
		this.rmiLink = rmiLink;
	}
	public String getRmiTarget() {
		return rmiTarget;
	}
	public void setRmiTarget(String rmiTarget) {
		this.rmiTarget = rmiTarget;
	}
	public String getRmiMainOrgname() {
		return rmiMainOrgname;
	}
	public void setRmiMainOrgname(String rmiMainOrgname) {
		this.rmiMainOrgname = rmiMainOrgname;
	}
	public String getRmiMainRename() {
		return rmiMainRename;
	}
	public void setRmiMainRename(String rmiMainRename) {
		this.rmiMainRename = rmiMainRename;
	}
	public String getRmiMainPath() {
		return rmiMainPath;
	}
	public void setRmiMainPath(String rmiMainPath) {
		this.rmiMainPath = rmiMainPath;
	}
	public String getRmiBannerOrgname() {
		return rmiBannerOrgname;
	}
	public void setRmiBannerOrgname(String rmiBannerOrgname) {
		this.rmiBannerOrgname = rmiBannerOrgname;
	}
	public String getRmiBannerRename() {
		return rmiBannerRename;
	}
	public void setRmiBannerRename(String rmiBannerRename) {
		this.rmiBannerRename = rmiBannerRename;
	}
	public String getRmiBannerPath() {
		return rmiBannerPath;
	}
	public void setRmiBannerPath(String rmiBannerPath) {
		this.rmiBannerPath = rmiBannerPath;
	}
	public String getRmiContent() {
		return rmiContent;
	}
	public void setRmiContent(String rmiContent) {
		this.rmiContent = rmiContent;
	}
	public String getRmiContent2() {
		return rmiContent2;
	}
	public void setRmiContent2(String rmiContent2) {
		this.rmiContent2 = rmiContent2;
	}
	public String getRmiPostYn() {
		return rmiPostYn;
	}
	public void setRmiPostYn(String rmiPostYn) {
		this.rmiPostYn = rmiPostYn;
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getStartday() {
		return startday;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	public String getStarthour() {
		return starthour;
	}
	public void setStarthour(String starthour) {
		this.starthour = starthour;
	}
	public String getStartminute() {
		return startminute;
	}
	public void setStartminute(String startminute) {
		this.startminute = startminute;
	}
	public String getEndday() {
		return endday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
	}
	public String getEndhour() {
		return endhour;
	}
	public void setEndhour(String endhour) {
		this.endhour = endhour;
	}
	public String getEndminute() {
		return endminute;
	}
	public void setEndminute(String endminute) {
		this.endminute = endminute;
	}
	
	
}
