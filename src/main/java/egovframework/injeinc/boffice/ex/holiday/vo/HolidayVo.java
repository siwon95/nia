package egovframework.injeinc.boffice.ex.holiday.vo;

public class HolidayVo {
	
	private String holDate;
	private String holName;
	private String holCode;
	private String oldHolCode;
	
	private String searchYear;
	private String searchHolCode;
	private String actionkey;
	
	public String getHolDate() {
		return holDate;
	}
	public void setHolDate(String holDate) {
		this.holDate = holDate;
	}
	public String getOldHolDate() {
		return oldHolCode;
	}
	public void setOldHolDate(String oldHolCode) {
		this.oldHolCode = oldHolCode;
	}
	public String getHolName() {
		return holName;
	}
	public void setHolName(String holName) {
		this.holName = holName;
	}
	public String getHolCode() {
		return holCode;
	}
	public void setHolCode(String holCode) {
		this.holCode = holCode;
	}
	public String getSearchYear() {
		return searchYear;
	}
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	public String getSearchHolCode() {
		return searchHolCode;
	}
	public void setSearchHolCode(String searchHolCode) {
		this.searchHolCode = searchHolCode;
	}
	public String getActionkey() {
		return actionkey;
	}
	public void setActionkey(String actionkey) {
		this.actionkey = actionkey;
	}
	
}
