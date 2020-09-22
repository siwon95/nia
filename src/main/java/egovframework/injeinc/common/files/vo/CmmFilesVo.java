package egovframework.injeinc.common.files.vo;

import egovframework.cmm.ComDefaultVO;
import egovframework.injeinc.common.files.vo.enums.CfType;

@SuppressWarnings("serial")
public class CmmFilesVo extends ComDefaultVO {
	
	private String cfIdx		= "";
	private String cfGroup		= "";
	private String cfPkidx		= "";
	private String cfPath		= "";
	private String cfName		= "";
	private String cfRename		= "";
	private String cfSize		= "";
	private String cfMime		= "";
	private String cfFormat		= "";
	private int cfDown			= 0;
	private String useYn		= "";
	private String regDt		= "";
	private String regId		= "";
	private String modDt		= "";
	private String modId		= "";
	private String subCont		= "";
	private String siteCd		= "";

	private String source		= "";
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	private String target		= "";
	
	private CfType cfType		= CfType.BASIC;
	
	public String getCfIdx() {
		return cfIdx;
	}
	public void setCfIdx(String cfIdx) {
		this.cfIdx = cfIdx;
	}
	public String getCfGroup() {
		return cfGroup;
	}
	public void setCfGroup(String cfGroup) {
		this.cfGroup = cfGroup;
	}
	public String getCfPkidx() {
		return cfPkidx;
	}
	public void setCfPkidx(String cfPkidx) {
		this.cfPkidx = cfPkidx;
	}
	public String getCfPath() {
		return cfPath;
	}
	public void setCfPath(String cfPath) {
		this.cfPath = cfPath;
	}
	public String getCfName() {
		return cfName;
	}
	public void setCfName(String cfName) {
		this.cfName = cfName;
	}
	public String getCfRename() {
		return cfRename;
	}
	public void setCfRename(String cfRename) {
		this.cfRename = cfRename;
	}
	public String getCfSize() {
		return cfSize;
	}
	public void setCfSize(String cfSize) {
		this.cfSize = cfSize;
	}
	public String getCfMime() {
		return cfMime;
	}
	public void setCfMime(String cfMime) {
		this.cfMime = cfMime;
	}
	public String getCfFormat() {
		return cfFormat;
	}
	public void setCfFormat(String cfFormat) {
		this.cfFormat = cfFormat;
	}
	public int getCfDown() {
		return cfDown;
	}
	public void setCfDown(int cfDown) {
		this.cfDown = cfDown;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public CfType getCfType() {
		return cfType;
	}
	public void setCfType(CfType cfType) {
		this.cfType = cfType;
	}
	public String getSubCont() {
		return subCont;
	}
	public void setSubCont(String subCont) {
		this.subCont = subCont;
	}
	public String getSiteCd() {
		return siteCd;
	}
	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}
	
}