package egovframework.injeinc.foffice.ex.poll.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class PollListFVo extends ComDefaultVO {

	private String plIdx			= "";
	private String plCdidx			= "";
	private String plTitle			= "";
	private String plGuide			= "";
	private String plAuthType		= "";
	private String plAddrYn			= "";
	private String plTelYn			= "";
	private String plHpYn			= "";
	private String plEmailYn		= "";
	private int plNumber			= 0;
	private String plResultOpenYn	= "";
	private String plManagerName	= "";
	private String plManagerTel		= "";
	private String plStartDate		= "";
	private String plEndDate		= "";
	private String plUse			= "";
	private String useYn			= "";
	private String regDt			= "";
	private String regId			= "";
	private String modDt			= "";
	private String modId			= "";
	private String cdSubject		= "";
	private int totalCnt			= 0;
	
	private String plManagerTel1	= "";
	private String plManagerTel2	= "";
	private String plManagerTel3	= "";
	
	private String plStartDate1		= "";
	private String plStartDate2		= "";
	private String plStartDate3		= "";
	
	private String plEndDate1		= "";
	private String plEndDate2		= "";
	private String plEndDate3		= "";
	
	private String actionkey		= "";
	private String searchCdIdx		= "";
	private String searchUse        = "";
	private String searchSubKeyword = "";
	private String searchKeyword    = "";
	private String plGubun = "";
	private String regDi = "";	
	
	
	
	public String getRegDi() {
		return regDi;
	}
	public void setRegDi(String regDi) {
		this.regDi = regDi;
	}
	public String getPlGubun() {
		return plGubun;
	}
	public void setPlGubun(String plGubun) {
		this.plGubun = plGubun;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchSubKeyword() {
		return searchSubKeyword;
	}
	public void setSearchSubKeyword(String searchSubKeyword) {
		this.searchSubKeyword = searchSubKeyword;
	}
	public String getSearchUse() {
		return searchUse;
	}
	public void setSearchUse(String searchUse) {
		this.searchUse = searchUse;
	}
	public String getPlIdx() {
		return plIdx;
	}
	public void setPlIdx(String plIdx) {
		this.plIdx = plIdx;
	}
	public String getPlCdidx() {
		return plCdidx;
	}
	public void setPlCdidx(String plCdidx) {
		this.plCdidx = plCdidx;
	}
	public String getPlTitle() {
		return plTitle;
	}
	public void setPlTitle(String plTitle) {
		this.plTitle = plTitle;
	}
	public String getPlGuide() {
		return plGuide;
	}
	public void setPlGuide(String plGuide) {
		this.plGuide = plGuide;
	}
	public String getPlAuthType() {
		return plAuthType;
	}
	public void setPlAuthType(String plAuthType) {
		this.plAuthType = plAuthType;
	}
	public String getPlAddrYn() {
		return plAddrYn;
	}
	public void setPlAddrYn(String plAddrYn) {
		this.plAddrYn = plAddrYn;
	}
	public String getPlTelYn() {
		return plTelYn;
	}
	public void setPlTelYn(String plTelYn) {
		this.plTelYn = plTelYn;
	}
	public String getPlHpYn() {
		return plHpYn;
	}
	public void setPlHpYn(String plHpYn) {
		this.plHpYn = plHpYn;
	}
	public String getPlEmailYn() {
		return plEmailYn;
	}
	public void setPlEmailYn(String plEmailYn) {
		this.plEmailYn = plEmailYn;
	}
	public int getPlNumber() {
		return plNumber;
	}
	public void setPlNumber(int plNumber) {
		this.plNumber = plNumber;
	}
	public String getPlResultOpenYn() {
		return plResultOpenYn;
	}
	public void setPlResultOpenYn(String plResultOpenYn) {
		this.plResultOpenYn = plResultOpenYn;
	}
	public String getPlManagerName() {
		return plManagerName;
	}
	public void setPlManagerName(String plManagerName) {
		this.plManagerName = plManagerName;
	}
	public String getPlManagerTel() {
		return plManagerTel;
	}
	public void setPlManagerTel(String plManagerTel) {
		this.plManagerTel = plManagerTel;
	}
	public String getPlStartDate() {
		return plStartDate;
	}
	public void setPlStartDate(String plStartDate) {
		this.plStartDate = plStartDate;
	}
	public String getPlEndDate() {
		return plEndDate;
	}
	public void setPlEndDate(String plEndDate) {
		this.plEndDate = plEndDate;
	}
	public String getPlUse() {
		return plUse;
	}
	public void setPlUse(String plUse) {
		this.plUse = plUse;
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
	public String getCdSubject() {
		return cdSubject;
	}
	public void setCdSubject(String cdSubject) {
		this.cdSubject = cdSubject;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public String getPlManagerTel1() {
		return plManagerTel1;
	}
	public void setPlManagerTel1(String plManagerTel1) {
		this.plManagerTel1 = plManagerTel1;
	}
	public String getPlManagerTel2() {
		return plManagerTel2;
	}
	public void setPlManagerTel2(String plManagerTel2) {
		this.plManagerTel2 = plManagerTel2;
	}
	public String getPlManagerTel3() {
		return plManagerTel3;
	}
	public void setPlManagerTel3(String plManagerTel3) {
		this.plManagerTel3 = plManagerTel3;
	}
	public String getPlStartDate1() {
		return plStartDate1;
	}
	public void setPlStartDate1(String plStartDate1) {
		this.plStartDate1 = plStartDate1;
	}
	public String getPlStartDate2() {
		return plStartDate2;
	}
	public void setPlStartDate2(String plStartDate2) {
		this.plStartDate2 = plStartDate2;
	}
	public String getPlStartDate3() {
		return plStartDate3;
	}
	public void setPlStartDate3(String plStartDate3) {
		this.plStartDate3 = plStartDate3;
	}
	public String getPlEndDate1() {
		return plEndDate1;
	}
	public void setPlEndDate1(String plEndDate1) {
		this.plEndDate1 = plEndDate1;
	}
	public String getPlEndDate2() {
		return plEndDate2;
	}
	public void setPlEndDate2(String plEndDate2) {
		this.plEndDate2 = plEndDate2;
	}
	public String getPlEndDate3() {
		return plEndDate3;
	}
	public void setPlEndDate3(String plEndDate3) {
		this.plEndDate3 = plEndDate3;
	}
	public String getActionkey() {
		return actionkey;
	}
	public void setActionkey(String actionkey) {
		this.actionkey = actionkey;
	}
	public String getSearchCdIdx() {
		return searchCdIdx;
	}
	public void setSearchCdIdx(String searchCdIdx) {
		this.searchCdIdx = searchCdIdx;
	}
}