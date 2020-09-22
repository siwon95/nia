package egovframework.injeinc.boffice.ex.reservation.vo;

public class ReservationItemVo {
	private String riIdx;    //예약 일련번호
	private int rtIdx;    //항목 순서
	private String rtItemType;    //입력형식
	private String rtItemTitle;    //항목명
	private String rtItemExample;    //보기
	private int rtItemExampleLen = 0;    //보기갯수
	private String rtItemNotice;    //주의사항
	private String rtItemUseCheck = "N";    //필수여부
	private String rtItemEtcUseCheck = "N";    //기타입력여부
	private String rtItemEtcUse;    //기타입력
	private String rtAnswer;    //답변
	private String rtEtcAnswer;    //기타답변
	
	private String regId;
	private String regDt;

	public String getRiIdx() {
		return riIdx == null ? "" : riIdx;
	}
	public void setRiIdx(String riIdx) {
		this.riIdx = riIdx;
	}
	public int getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(int rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getRtItemType() {
		return rtItemType == null ? "" : rtItemType;
	}
	public void setRtItemType(String rtItemType) {
		this.rtItemType = rtItemType;
	}
	public String getRtItemTitle() {
		return rtItemTitle == null ? "" : rtItemTitle;
	}
	public void setRtItemTitle(String rtItemTitle) {
		this.rtItemTitle = rtItemTitle;
	}
	public String getRtItemExample() {
		return rtItemExample == null ? "" : rtItemExample;
	}
	public void setRtItemExample(String rtItemExample) {
		this.rtItemExample = rtItemExample;
	}
	public int getRtItemExampleLen() {
		return rtItemExampleLen;
	}
	public void setRtItemExampleLen(int rtItemExampleLen) {
		this.rtItemExampleLen = rtItemExampleLen;
	}
	public String getRtItemNotice() {
		return rtItemNotice == null ? "" : rtItemNotice;
	}
	public void setRtItemNotice(String rtItemNotice) {
		this.rtItemNotice = rtItemNotice;
	}
	public String getRtItemUseCheck() {
		return rtItemUseCheck == null ? "" : rtItemUseCheck;
	}
	public void setRtItemUseCheck(String rtItemUseCheck) {
		this.rtItemUseCheck = rtItemUseCheck;
	}
	public String getRtItemEtcUseCheck() {
		return rtItemEtcUseCheck == null ? "" : rtItemEtcUseCheck;
	}
	public void setRtItemEtcUseCheck(String rtItemEtcUseCheck) {
		this.rtItemEtcUseCheck = rtItemEtcUseCheck;
	}
	public String getRtItemEtcUse() {
		return rtItemEtcUse == null ? "" : rtItemEtcUse;
	}
	public void setRtItemEtcUse(String rtItemEtcUse) {
		this.rtItemEtcUse = rtItemEtcUse;
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
	public String getRtAnswer() {
		return rtAnswer == null ? "" : rtAnswer;
	}
	public void setRtAnswer(String rtAnswer) {
		this.rtAnswer = rtAnswer;
	}
	public String getRtEtcAnswer() {
		return rtEtcAnswer == null  ? "": rtEtcAnswer;
	}
	public void setRtEtcAnswer(String rtEtcAnswer) {
		this.rtEtcAnswer = rtEtcAnswer;
	}
	
}
