package egovframework.injeinc.boffice.ex.reservation.vo;

import egovframework.cmm.service.FileVO;

@SuppressWarnings("serial")
public class ReservationUserFileVo extends FileVO{
	
	private String riIdx;
	private String rtIdx;
	private String ruIdx;
	private String useYn;
	private String regDt;
	private String regId;
	private String modDt;
	private String modId;
	
	public String getRiIdx() {
		return riIdx;
	}
	public void setRiIdx(String riIdx) {
		this.riIdx = riIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getRuIdx() {
		return ruIdx;
	}
	public void setRuIdx(String ruIdx) {
		this.ruIdx = ruIdx;
	}
}
