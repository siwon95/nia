package egovframework.injeinc.boffice.quick.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class QuickVo extends ComDefaultVO {
	
	private String cqlIdx			= "";
	private String cqlName			= "";
	private String cqlLink			= "";
	private String cqlCode			= "";
	private String cqlCodeName		= "";
	private String cqlTarget		= "";
	private int cqlSort				= 0;
	private String cqlHotYn			= "";
	private String cqlNewYn			= "";
	private String cqlUse			= "";
	private String useYn			= "";
	private String regDt			= "";
	private String regId			= "";
	private String modDt			= "";
	private String modId			= "";
	
	private String searchCqlCode	= "";
	private String searchCqlUse		= "";
	private String searchOrder		= "";
	private String actionkey		= "";
	int changeSort			= 0;
	
	private String[] searchCqlCodeArr;
	
	public String getCqlIdx() {
		return cqlIdx;
	}
	public void setCqlIdx(String cqlIdx) {
		this.cqlIdx = cqlIdx;
	}
	public String getCqlName() {
		return cqlName;
	}
	public void setCqlName(String cqlName) {
		this.cqlName = cqlName;
	}
	public String getCqlCodeName() {
		return cqlCodeName;
	}
	public void setCqlCodeName(String cqlCodeName) {
		this.cqlCodeName = cqlCodeName;
	}
	public String getCqlLink() {
		return cqlLink;
	}
	public void setCqlLink(String cqlLink) {
		this.cqlLink = cqlLink;
	}
	public String getCqlCode() {
		return cqlCode;
	}
	public void setCqlCode(String cqlCode) {
		this.cqlCode = cqlCode;
	}
	public String getCqlTarget() {
		return cqlTarget;
	}
	public void setCqlTarget(String cqlTarget) {
		this.cqlTarget = cqlTarget;
	}
	public int getCqlSort() {
		return cqlSort;
	}
	public void setCqlSort(int cqlSort) {
		this.cqlSort = cqlSort;
	}
	public String getCqlHotYn() {
		return cqlHotYn;
	}
	public void setCqlHotYn(String cqlHotYn) {
		this.cqlHotYn = cqlHotYn;
	}
	public String getCqlNewYn() {
		return cqlNewYn;
	}
	public void setCqlNewYn(String cqlNewYn) {
		this.cqlNewYn = cqlNewYn;
	}
	public String getCqlUse() {
		return cqlUse;
	}
	public void setCqlUse(String cqlUse) {
		this.cqlUse = cqlUse;
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
	public String getSearchCqlCode() {
		return searchCqlCode;
	}
	public void setSearchCqlCode(String searchCqlCode) {
		this.searchCqlCode = searchCqlCode;
	}
	public String getSearchCqlUse() {
		return searchCqlUse;
	}
	public void setSearchCqlUse(String searchCqlUse) {
		this.searchCqlUse = searchCqlUse;
	}
	public String getSearchOrder() {
		return searchOrder;
	}
	public void setSearchOrder(String searchOrder) {
		this.searchOrder = searchOrder;
	}
	public String getActionkey() {
		return actionkey;
	}
	public void setActionkey(String actionkey) {
		this.actionkey = actionkey;
	}
	public int getChangeSort() {
		return changeSort;
	}
	public void setChangeSort(int changeSort) {
		this.changeSort = changeSort;
	}
	public String[] getSearchCqlCodeArr() {
		String [] ret = null;
		if(this.searchCqlCodeArr != null){
			ret = new String[searchCqlCodeArr.length];
			for(int i=0; i<searchCqlCodeArr.length; i++){ ret[i] = this.searchCqlCodeArr[i]; }
		}
		return ret;
	}
	public void setSearchCqlCodeArr(String[] searchCqlCodeArr) {
		this.searchCqlCodeArr = new String[searchCqlCodeArr.length];
		for(int i=0; i<searchCqlCodeArr.length; i++){this.searchCqlCodeArr[i] = searchCqlCodeArr[i]; }
	}
	
}