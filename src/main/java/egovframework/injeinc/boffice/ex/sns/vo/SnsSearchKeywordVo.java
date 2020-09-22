package egovframework.injeinc.boffice.ex.sns.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import egovframework.cmm.ComDefaultVO;
import egovframework.injeinc.common.util.DateUtil;

@SuppressWarnings("serial")
public class SnsSearchKeywordVo extends ComDefaultVO {

	private String skIdx = "";
	private String keyword = "";
	private String searchType = "";
	private String snsType = "";
	private String tabSnsType = "all";
	private String isValid = "";
	private String useYn = "";
	private String regid = "";
	private String regdt = "";
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSnsType() {
		return snsType;
	}
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSkIdx() {
		return skIdx;
	}
	public void setSkIdx(String skIdx) {
		this.skIdx = skIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getTabSnsType() {
		return tabSnsType;
	}
	public void setTabSnsType(String tabSnsType) {
		this.tabSnsType = tabSnsType;
	}
}