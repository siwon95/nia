package egovframework.injeinc.boffice.ex.reservationHealth.vo;

public class ReservationAddNodateVo {
	private String riIdx;    //예약 일련번호
	private int raIdx = 0;    //항목 순서	
	private String raNodate;    //운영불가(휴무일)
	
	private String regId;	//등록자아이디
	private String regDt;	//등록일시
	
	public String getRiIdx() {
		return riIdx == null ? "" : riIdx;
	}
	public void setRiIdx(String riIdx) {
		this.riIdx = riIdx;
	}
	public int getRaIdx() {
		return raIdx;
	}
	public void setRaIdx(int raIdx) {
		this.raIdx = raIdx;
	}
	public String getRaNodate() {
		return raNodate == null ? "" : raNodate;
	}
	public void setRaNodate(String raNodate) {
		this.raNodate = raNodate;
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
