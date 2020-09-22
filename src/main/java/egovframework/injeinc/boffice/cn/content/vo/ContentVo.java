package egovframework.injeinc.boffice.cn.content.vo;

import egovframework.cmm.ComDefaultVO;

public class ContentVo extends ComDefaultVO {
	
	//컨텐츠 내용
	private String contentBody = "";
	//컨텐츠 경로
	private String userMenuUrl = "";
	//사이트코드
	private String siteCd = "";
	//메뉴코드
	private String menuCode = "";
	
	public String getContentBody() {
		return contentBody;
	}
	public void setContentBody(String contentBody) {
		this.contentBody = contentBody;
	}
	public String getUserMenuUrl() {
		return userMenuUrl;
	}
	public void setUserMenuUrl(String userMenuUrl) {
		this.userMenuUrl = userMenuUrl;
	}
	public String getSiteCd() {
		return siteCd;
	}
	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

}
