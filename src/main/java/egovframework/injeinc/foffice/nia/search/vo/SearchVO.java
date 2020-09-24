package egovframework.injeinc.foffice.nia.search.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class SearchVO extends ComDefaultVO {
	
	private String area = ""; 	//메뉴 명
	private String sido1 = "";  //메뉴 깊이
	private String gugun1 = "";  //메뉴 코드
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSido1() {
		return sido1;
	}
	public void Sido1(String sido1) {
		this.sido1 = sido1;
	}
	public String getGugun1() {
		return gugun1;
	}
	public void setGugun1 (String gugun1) {
		this.gugun1 = gugun1;
	}
	
}
