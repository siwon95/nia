package egovframework.injeinc.boffice.sy.board.vo;

import egovframework.cmm.ComDefaultVO;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;


public class EzBbsTempletVo extends CmmCodeVo{
	// 게시판템플릿구분;
	private String bbsTempletGbn;
	// 게시판목록소스코드
	private String listCode;
	// 게시판뷰소스코드;
	private String viewCode;
	// 게시판등록소스코드;
	private String registCode;

	public String getBbsTempletGbn() {
		return bbsTempletGbn;
	}
	public void setBbsTempletGbn(String bbsTempletGbn) {
		this.bbsTempletGbn = bbsTempletGbn;
	}
	public String getListCode() {
		return listCode;
	}
	public void setListCode(String listCode) {
		this.listCode = listCode;
	}
	public String getViewCode() {
		return viewCode;
	}
	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
	}
	public String getRegistCode() {
		return registCode;
	}
	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}
	
}