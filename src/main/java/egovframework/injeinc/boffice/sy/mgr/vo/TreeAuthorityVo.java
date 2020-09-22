package egovframework.injeinc.boffice.sy.mgr.vo;

public class TreeAuthorityVo {

	private String id			= "";
	private String parent		= "";
	private String text			= "";
	private String idx			= "";
	private String saveChk		= "";
	private String depth		= "";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getSaveChk() {
		return saveChk;
	}
	public void setSaveChk(String saveChk) {
		this.saveChk = saveChk;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
}