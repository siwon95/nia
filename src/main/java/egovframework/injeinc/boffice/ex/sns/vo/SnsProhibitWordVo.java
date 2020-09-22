package egovframework.injeinc.boffice.ex.sns.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class SnsProhibitWordVo extends ComDefaultVO {

	private String pwIdx			= "";
	private String word			= "";
	private String useYn			= "";
	private String regId			= "";
	private String regDt			= "";
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getPwIdx() {
		return pwIdx;
	}
	public void setPwIdx(String pwIdx) {
		this.pwIdx = pwIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
}