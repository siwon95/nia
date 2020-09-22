package egovframework.injeinc.boffice.ex.board.vo;

import egovframework.cmm.ComDefaultVO;

public class BbsCommentVo extends ComDefaultVO{
	private	int cmIdx = 0;  /* 댓글코드 */
	private	int bcIdx = 0;  /* 게시물코드 */
	private	int uprCmIdx = 0;  /* 댓글상위코드 */
	private	int cbIdx = 0;  /* 게시물코드 */
	private	String cmCont = "";  /* 댓글내용 */
	private	String regName = "";  /* 작성자 */
	private	String regId = "";  /* 작성아이디 */
	private	String regIp = "";  /* 작성아이피 */
	private	String regDt = "";  /* 작성시간 */
	private	String modId = "";  /* 수정아이디 */
	private	String modIp = "";  /* 수정아이피 */
	private	String modDt = "";  /* 수정시간 */
	private	int cmDepth = 0;  /* Depth */
	private	String cmDelYn = "N";  /* 삭제여부 */
	public int getCmIdx() {
		return cmIdx;
	}
	public void setCmIdx(int cmIdx) {
		this.cmIdx = cmIdx;
	}
	public int getBcIdx() {
		return bcIdx;
	}
	public void setBcIdx(int bcIdx) {
		this.bcIdx = bcIdx;
	}
	public String getCmCont() {
		return cmCont;
	}
	public void setCmCont(String cmCont) {
		this.cmCont = cmCont;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModIp() {
		return modIp;
	}
	public void setModIp(String modIp) {
		this.modIp = modIp;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public int getUprCmIdx() {
		return uprCmIdx;
	}
	public void setUprCmIdx(int uprCmIdx) {
		this.uprCmIdx = uprCmIdx;
	}
	public int getCbIdx() {
		return cbIdx;
	}
	public void setCbIdx(int cbIdx) {
		this.cbIdx = cbIdx;
	}
	public int getCmDepth() {
		return cmDepth;
	}
	public void setCmDepth(int cmDepth) {
		this.cmDepth = cmDepth;
	}
	public String getCmDelYn() {
		return cmDelYn;
	}
	public void setCmDelYn(String cmDelYn) {
		this.cmDelYn = cmDelYn;
	}
}
