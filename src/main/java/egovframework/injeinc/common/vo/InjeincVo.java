package egovframework.injeinc.common.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * <PRE>
 * 1. FileName : BaseVo.java
 * 2. Package  : kr.co.injeinc.common.base.BaseVo
 * 3. Comment  : 
 * 4. Programmer   : jsh
 * 5. Date   : 2014. 5. 24.
 * </PRE>
 */

@Component
public abstract class InjeincVo implements Serializable {

	private String startPage;

	private String endPage;
	
	private String totCnt;
	
	private String rnum;
	
	private String label;
	
	private String children;
	
	private String keyword;
	
	private String keywordCombo;


	public String getStartPage() {
		return startPage;
	}
	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}
	public String getEndPage() {
		return endPage;
	}
	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}
	public String getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getChildren() {
		return children;
	}
	public void setChildren(String children) {
		this.children = children;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeywordCombo() {
		return keywordCombo;
	}
	public void setKeywordCombo(String keywordCombo) {
		this.keywordCombo = keywordCombo;
	}
}