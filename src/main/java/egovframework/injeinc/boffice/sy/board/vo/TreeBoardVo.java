package egovframework.injeinc.boffice.sy.board.vo;

public class TreeBoardVo {

	private String id			= "";
	private String parent		= "";
	private String text			= "";
	private int position		= 0;
	private String cbIdx		= "";
	private String publicYn		= "";
	private String cbUse		= "";
	private String cbDepth		= "";
	private String bbsYn		="";
	private String bbsCnt		="";
	private String type			="";
	
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
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getCbIdx() {
		return cbIdx;
	}
	public void setCbIdx(String cbIdx) {
		this.cbIdx = cbIdx;
	}
	public String getPublicYn() {
		return publicYn;
	}
	public void setPublicYn(String publicYn) {
		this.publicYn = publicYn;
	}
	public String getCbUse() {
		return cbUse;
	}
	public void setCbUse(String cbUse) {
		this.cbUse = cbUse;
	}
	public String getCbDepth() {
		return cbDepth;
	}
	public void setCbDepth(String cbDepth) {
		this.cbDepth = cbDepth;
	}
	public String getBbsYn() {
		return bbsYn;
	}
	public void setBbsYn(String bbsYn) {
		this.bbsYn = bbsYn;
	}
	public String getBbsCnt() {
		return bbsCnt;
	}
	public void setbbsCnt(String bbsCnt) {
		this.bbsCnt = bbsCnt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}