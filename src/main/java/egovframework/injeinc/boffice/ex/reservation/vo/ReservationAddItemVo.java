package egovframework.injeinc.boffice.ex.reservation.vo;

public class ReservationAddItemVo {
	private String riIdx;    //예약 일련번호
	private int raIdx = 0;    //항목 순서	
	private String raTitle;    //항목명
	private String raContent;    //보기
	private String raLimitCnt;    //모집정원
	private String raApplyCnt;    //신청인원
	
	private String regId;
	private String regDt;
	
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
	public String getRaTitle() {
		return raTitle == null ? "" : raTitle;
	}
	public void setRaTitle(String raTitle) {
		this.raTitle = raTitle;
	}
	public String getRaContent() {
		return raContent == null ? "" : raContent;
	}
	public void setRaContent(String raContent) {
		this.raContent = raContent;
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
	public String getRaLimitCnt() {
		return raLimitCnt;
	}
	public void setRaLimitCnt(String raLimitCnt) {
		this.raLimitCnt = raLimitCnt;
	}
	public String getRaApplyCnt() {
		return raApplyCnt;
	}
	public void setRaApplyCnt(String raApplyCnt) {
		this.raApplyCnt = raApplyCnt;
	}
}
