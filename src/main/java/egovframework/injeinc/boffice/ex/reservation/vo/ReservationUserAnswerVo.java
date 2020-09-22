package egovframework.injeinc.boffice.ex.reservation.vo;

public class ReservationUserAnswerVo {
	private String riIdx;    //예약 일련번호
	private String ruIdx;    //접수자 일련번호
	private int rtIdx;    //항목 순서
	private String raItemType;    //입력형식
	private String raAnswer;    //답변
	private String raEtcAnswer;    //기타답변
	private String raText;    //주관식답변
	
	public String getRiIdx() {
		return riIdx == null ? "" : riIdx;
	}
	public void setRiIdx(String riIdx) {
		this.riIdx = riIdx;
	}
	public String getRuIdx() {
		return ruIdx == null ? "" : ruIdx;
	}
	public void setRuIdx(String ruIdx) {
		this.ruIdx = ruIdx;
	}
	public int getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(int rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getRaItemType() {
		return raItemType == null ? "" : raItemType;
	}
	public void setRaItemType(String raItemType) {
		this.raItemType = raItemType;
	}
	public String getRaAnswer() {
		return raAnswer == null ? "" : raAnswer;
	}
	public void setRaAnswer(String raAnswer) {
		this.raAnswer = raAnswer;
	}
	public String getRaEtcAnswer() {
		return raEtcAnswer == null ? "" : raEtcAnswer;
	}
	public void setRaEtcAnswer(String raEtcAnswer) {
		this.raEtcAnswer = raEtcAnswer;
	}
	public String getRaText() {
		return raText == null ? "" : raText;
	}
	public void setRaText(String raText) {
		this.raText = raText;
	}
	
}
