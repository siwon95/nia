package egovframework.injeinc.boffice.ex.umsLog.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class UmsLogVo extends ComDefaultVO {

	int ulIdx					= 0;
	String ulSiteCd				= "";
	String ulSendtype			= "";
	String ulSubject			= "";
	String ulAddress			= "";
	String ulContent			= "";
	String ulCallbackNo			= "";
	String ulWorktypeNm			= "";
	String ulScheduleType		= "";
	String ulScheduleStime		= "";
	String regDt				= "";
	
	String searchSiteCd			= "";
	String searchStartDt		= "";
	String searchEndDt			= "";
	
	public int getUlIdx() {
		return ulIdx;
	}
	public void setUlIdx(int ulIdx) {
		this.ulIdx = ulIdx;
	}
	public String getUlSiteCd() {
		return ulSiteCd;
	}
	public void setUlSiteCd(String ulSiteCd) {
		this.ulSiteCd = ulSiteCd;
	}
	public String getUlSendtype() {
		return ulSendtype;
	}
	public void setUlSendtype(String ulSendtype) {
		this.ulSendtype = ulSendtype;
	}
	public String getUlSubject() {
		return ulSubject;
	}
	public void setUlSubject(String ulSubject) {
		this.ulSubject = ulSubject;
	}
	public String getUlAddress() {
		return ulAddress;
	}
	public void setUlAddress(String ulAddress) {
		this.ulAddress = ulAddress;
	}
	public String getUlContent() {
		return ulContent;
	}
	public void setUlContent(String ulContent) {
		this.ulContent = ulContent;
	}
	public String getUlCallbackNo() {
		return ulCallbackNo;
	}
	public void setUlCallbackNo(String ulCallbackNo) {
		this.ulCallbackNo = ulCallbackNo;
	}
	public String getUlWorktypeNm() {
		return ulWorktypeNm;
	}
	public void setUlWorktypeNm(String ulWorktypeNm) {
		this.ulWorktypeNm = ulWorktypeNm;
	}
	public String getUlScheduleType() {
		return ulScheduleType;
	}
	public void setUlScheduleType(String ulScheduleType) {
		this.ulScheduleType = ulScheduleType;
	}
	public String getUlScheduleStime() {
		return ulScheduleStime;
	}
	public void setUlScheduleStime(String ulScheduleStime) {
		this.ulScheduleStime = ulScheduleStime;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getSearchSiteCd() {
		return searchSiteCd;
	}
	public void setSearchSiteCd(String searchSiteCd) {
		this.searchSiteCd = searchSiteCd;
	}
	public String getSearchStartDt() {
		return searchStartDt;
	}
	public void setSearchStartDt(String searchStartDt) {
		this.searchStartDt = searchStartDt;
	}
	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}
	
}