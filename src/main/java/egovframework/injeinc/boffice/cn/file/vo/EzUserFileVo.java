package egovframework.injeinc.boffice.cn.file.vo;

import egovframework.cmm.ComDefaultVO;

public class EzUserFileVo extends ComDefaultVO {
	//일련번호
	private String seq;
	//제목
	private String title;
	//첨부파일ID
	private String atchFileId;
	//사용여부
	private String useYn;
	//등록일
	private String regDt;
	


	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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
}
