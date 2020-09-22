package egovframework.injeinc.boffice.ex.sns.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class SnsCollectVo extends ComDefaultVO {

	private String snsIdx			= "";
	private String snsType			= "";
	private String snsRegdt			= "";
	private String snsRegid			= "";
	private String snsRegnm			= "";
	private String snsProfileimg			= "";
	private String snsDesc			= "";
	private String snsLinkurl			= "";
	private String useYn			= "";
	private String regdt			= "";
	private String regid			= "";
	private String moddt			= "";
	private String modid			= "";
	private String snsThumnail			= "";
	private int snsTNHeight			= 0;
	private int snsTNWidth			= 0;
	private String snsKeyword			= "";
	
	private String isValid			= "";
	private String siteDomain			= "";
	
	public String getSnsIdx() {
		return snsIdx;
	}
	public void setSnsIdx(String snsIdx) {
		this.snsIdx = snsIdx;
	}
	public String getSnsType() {
		return snsType;
	}
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	public String getSnsRegdt() {
		return snsRegdt;
	}
	public void setSnsRegdt(String snsRegdt) {
		this.snsRegdt = snsRegdt;
	}
	public String getSnsDesc() {
		return snsDesc;
	}
	public void setSnsDesc(String snsDesc) {
		this.snsDesc = snsDesc;
	}
	public String getSnsLinkurl() {
		return snsLinkurl;
	}
	public void setSnsLinkurl(String snsLinkurl) {
		this.snsLinkurl = snsLinkurl;
	}
	public String getSnsThumnail() {
		return snsThumnail;
	}
	public void setSnsThumnail(String snsThumnail) {
		this.snsThumnail = snsThumnail;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getModdt() {
		return moddt;
	}
	public void setModdt(String moddt) {
		this.moddt = moddt;
	}
	public String getModid() {
		return modid;
	}
	public void setModid(String modid) {
		this.modid = modid;
	}
	public String getSnsRegid() {
		return snsRegid;
	}
	public void setSnsRegid(String snsRegid) {
		this.snsRegid = snsRegid;
	}
	public String getSnsRegnm() {
		return snsRegnm;
	}
	public void setSnsRegnm(String snsRegnm) {
		this.snsRegnm = snsRegnm;
	}
	public String getSnsProfileimg() {
		return snsProfileimg;
	}
	public void setSnsProfileimg(String snsProfileimg) {
		this.snsProfileimg = snsProfileimg;
	}
	public int getSnsTNHeight() {
		return snsTNHeight;
	}
	public void setSnsTNHeight(int snsTNHeight) {
		this.snsTNHeight = snsTNHeight;
	}
	public int getSnsTNWidth() {
		return snsTNWidth;
	}
	public void setSnsTNWidth(int snsTNWidth) {
		this.snsTNWidth = snsTNWidth;
	}
	public String getSnsKeyword() {
		return snsKeyword;
	}
	public void setSnsKeyword(String snsKeyword) {
		this.snsKeyword = snsKeyword;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}

}