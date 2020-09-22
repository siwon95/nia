package egovframework.injeinc.boffice.ex.reservationHealth.vo;

public class ReservationAddHealthVo {
	private String riIdx;    //예약 일련번호
	private int rhIdx = 0;    //요일별 회차
	private String riWeek;   //예약요일
	private String riConfirmSdateHh; //회차 시작 시간
	private String riConfirmSdateMm; //회차 시작 분
	
	private String regId;	//등록자아이디
	private String regDt;	//등록일시
	
	public String getRiIdx() {
		return riIdx == null ? "" : riIdx;
	}
	public void setRiIdx(String riIdx) {
		this.riIdx = riIdx;
	}
	public int getRhIdx() {
		return rhIdx;
	}
	public void setRhIdx(int rhIdx) {
		this.rhIdx = rhIdx;
	}
	public String getRiWeek() {
		return riWeek == null ? "" : riWeek;
	}
	public void setRiWeek(String riWeek) {
		this.riWeek = riWeek;
	}
	public String getRiConfirmSdateHh() {
		return riConfirmSdateHh == null ? "" : riConfirmSdateHh;
	}
	public void setRiConfirmSdateHh(String riConfirmSdateHh) {
		this.riConfirmSdateHh = riConfirmSdateHh;
	}
	public String getRiConfirmSdateMm() {
		return riConfirmSdateMm == null ? "" : riConfirmSdateMm;
	}
	public void setRiConfirmSdateMm(String riConfirmSdateMm) {
		this.riConfirmSdateMm = riConfirmSdateMm;
	}
	public String getRegId() {
		return regId == null ? "" : regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt == null ? "" : regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
}
