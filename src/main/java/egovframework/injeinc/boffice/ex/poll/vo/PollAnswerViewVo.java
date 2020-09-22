package egovframework.injeinc.boffice.ex.poll.vo;

import egovframework.cmm.ComDefaultSubVO;

@SuppressWarnings("serial")
public class PollAnswerViewVo extends ComDefaultSubVO {
	
	private String plIdx		= "";
	private String puIdx		= "";
	private String puName		= "";
	private String puId			= "";
	private String pqIdx		= "";
	private int pqSort			= 0;
	private String pqTitle		= "";
	private String pqType		= "";
	private int pqItemCnt		= 0;
	private String pqItemList	= "";
	private String pqEtc		= "";
	private String paAnswer		= "";
	private String paText		= "";

	private String searchCdIdx	= "";
	private String searchUse	= "";
	
	public String getPlIdx() {
		return plIdx;
	}
	public void setPlIdx(String plIdx) {
		this.plIdx = plIdx;
	}
	public String getPuIdx() {
		return puIdx;
	}
	public void setPuIdx(String puIdx) {
		this.puIdx = puIdx;
	}
	public String getPuName() {
		return puName;
	}
	public void setPuName(String puName) {
		this.puName = puName;
	}
	public String getPuId() {
		return puId;
	}
	public void setPuId(String puId) {
		this.puId = puId;
	}
	public String getPqIdx() {
		return pqIdx;
	}
	public void setPqIdx(String pqIdx) {
		this.pqIdx = pqIdx;
	}
	public int getPqSort() {
		return pqSort;
	}
	public void setPqSort(int pqSort) {
		this.pqSort = pqSort;
	}
	public String getPqTitle() {
		return pqTitle;
	}
	public void setPqTitle(String pqTitle) {
		this.pqTitle = pqTitle;
	}
	public String getPqType() {
		return pqType;
	}
	public void setPqType(String pqType) {
		this.pqType = pqType;
	}
	public int getPqItemCnt() {
		return pqItemCnt;
	}
	public void setPqItemCnt(int pqItemCnt) {
		this.pqItemCnt = pqItemCnt;
	}
	public String getPqItemList() {
		return pqItemList;
	}
	public void setPqItemList(String pqItemList) {
		this.pqItemList = pqItemList;
	}
	public String getPqEtc() {
		return pqEtc;
	}
	public void setPqEtc(String pqEtc) {
		this.pqEtc = pqEtc;
	}
	public String getPaAnswer() {
		return paAnswer;
	}
	public void setPaAnswer(String paAnswer) {
		this.paAnswer = paAnswer;
	}
	public String getPaText() {
		return paText;
	}
	public void setPaText(String paText) {
		this.paText = paText;
	}
	public String getSearchCdIdx() {
		return searchCdIdx;
	}
	public void setSearchCdIdx(String searchCdIdx) {
		this.searchCdIdx = searchCdIdx;
	}
	public String getSearchUse() {
		return searchUse;
	}
	public void setSearchUse(String searchUse) {
		this.searchUse = searchUse;
	}
}