package egovframework.injeinc.boffice.sy.menu.vo;

public class TreeMenuVo {

	private String id			= "";
	private String parent		= "";
	private String text			= "";
	private int position		= 0;
	private String mmIdx		= "";
	private String mmImg		= "";
	private String mgrUrl		= "";
	private String publicYn		= "";
	private String mmDepth		= "";
	private String mmMgrSiteCd		= "";
	private String mmHelp		= "";
	private String mmPath		= "";
	
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
	public String getMmIdx() {
		return mmIdx;
	}
	public String getMmImg() {
		return mmImg;
	}
	public void setMmImg(String mmImg) {
		this.mmImg = mmImg;
	}
	public void setMmIdx(String mmIdx) {
		this.mmIdx = mmIdx;
	}
	public String getMgrUrl() {
		return mgrUrl;
	}
	public void setMgrUrl(String mgrUrl) {
		this.mgrUrl = mgrUrl;
	}
	public String getPublicYn() {
		return publicYn;
	}
	public void setPublicYn(String publicYn) {
		this.publicYn = publicYn;
	}
	public String getMmDepth() {
		return mmDepth;
	}
	public void setMmDepth(String mmDepth) {
		this.mmDepth = mmDepth;
	}
	public String getMmMgrSiteCd() {
		return mmMgrSiteCd;
	}
	public void setMmMgrSiteCd(String mmMgrSiteCd) {
		this.mmMgrSiteCd = mmMgrSiteCd;
	}
	public String getMmHelp() {
		return mmHelp;
	}
	public void setMmHelp(String mmHelp) {
		this.mmHelp = mmHelp;
	}
	public String getMmPath() {
		return mmPath;
	}
	public void setMmPath(String mmPath) {
		this.mmPath = mmPath;
	}
	
}