package egovframework.injeinc.foffice.ex.siteMap.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class SiteMapVo extends ComDefaultVO {
	
	private String menuNm = ""; 	//메뉴 명
	private String menuDepth = "";  //메뉴 깊이
	private String sortOrder = "";  //메뉴 코드
	private String parent = "";		//부모 코드
	private String userMenuUrl = "";//사용자URL
	private String siteGb = "";		//사이트구분
	
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getMenuDepth() {
		return menuDepth;
	}
	public void setMenuDepth(String menuDepth) {
		this.menuDepth = menuDepth;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getUserMenuUrl() {
		return userMenuUrl;
	}
	public void setUserMenuUrl(String userMenuUrl) {
		this.userMenuUrl = userMenuUrl;
	}
	public String getSiteGb() {
		return siteGb;
	}
	public void setSiteGb(String siteGb) {
		this.siteGb = siteGb;
	}
	
}
