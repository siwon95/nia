package egovframework.cmm;

@SuppressWarnings("serial")
public class ComDefaultSubVO extends ComDefaultVO {
	
	/** 서브 검색조건 */
	private String searchSubCondition = "";
	
	/** 서브 검색Keyword */
	private String searchSubKeyword = "";
	
	/** 서브 현재페이지 */
	private int pageSubIndex = 1;
	
	/** 서브 페이지갯수 */
	private int pageSubUnit = 10;
	
	/** 서브 페이지사이즈 */
	private int pageSubSize = 10;

	public String getSearchSubCondition() {
		return searchSubCondition;
	}
	public void setSearchSubCondition(String searchSubCondition) {
		this.searchSubCondition = searchSubCondition;
	}
	public String getSearchSubKeyword() {
		return searchSubKeyword;
	}
	public void setSearchSubKeyword(String searchSubKeyword) {
		this.searchSubKeyword = searchSubKeyword;
	}
	public int getPageSubIndex() {
		return pageSubIndex;
	}
	public void setPageSubIndex(int pageSubIndex) {
		this.pageSubIndex = pageSubIndex;
	}
	public int getPageSubUnit() {
		return pageSubUnit;
	}
	public void setPageSubUnit(int pageSubUnit) {
		this.pageSubUnit = pageSubUnit;
	}
	public int getPageSubSize() {
		return pageSubSize;
	}
	public void setPageSubSize(int pageSubSize) {
		this.pageSubSize = pageSubSize;
	}

}
