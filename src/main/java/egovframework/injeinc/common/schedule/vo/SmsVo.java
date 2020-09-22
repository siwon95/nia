package egovframework.injeinc.common.schedule.vo;

import egovframework.cmm.ComDefaultVO;


public class SmsVo extends ComDefaultVO {
	private String strDate;

	private String siteUse;
	
	private String itemName;
	
	private String itemHp;

	private String startDate;
	
	private String endDate;
	
	
	//강좌
	private String clIdx = "";
	private String lmIdx = "";
	private String clName = "";
	private String lcPlaceName = "";
	private String stDate = "";
	private String lmName = "";
	private String hpNum = "";
	private String lmState = "";
	private String lcLimitNumber = "";
	private String rk = "";
	
	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getSiteUse() {
		return siteUse;
	}

	public void setSiteUse(String siteUse) {
		this.siteUse = siteUse;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemHp() {
		return itemHp;
	}

	public void setItemHp(String itemHp) {
		this.itemHp = itemHp;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getClIdx() {
		return clIdx;
	}

	public void setClIdx(String clIdx) {
		this.clIdx = clIdx;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getLmIdx() {
		return lmIdx;
	}

	public void setLmIdx(String lmIdx) {
		this.lmIdx = lmIdx;
	}

	public String getClName() {
		return clName;
	}

	public void setClName(String clName) {
		this.clName = clName;
	}

	public String getLcPlaceName() {
		return lcPlaceName;
	}

	public void setLcPlaceName(String lcPlaceName) {
		this.lcPlaceName = lcPlaceName;
	}

	public String getStDate() {
		return stDate;
	}

	public void setStDate(String stDate) {
		this.stDate = stDate;
	}

	public String getLmName() {
		return lmName;
	}

	public void setLmName(String lmName) {
		this.lmName = lmName;
	}

	public String getHpNum() {
		return hpNum;
	}

	public void setHpNum(String hpNum) {
		this.hpNum = hpNum;
	}

	public String getLmState() {
		return lmState;
	}

	public void setLmState(String lmState) {
		this.lmState = lmState;
	}

	public String getLcLimitNumber() {
		return lcLimitNumber;
	}

	public void setLcLimitNumber(String lcLimitNumber) {
		this.lcLimitNumber = lcLimitNumber;
	}

	public String getRk() {
		return rk;
	}

	public void setRk(String rk) {
		this.rk = rk;
	}
	
}