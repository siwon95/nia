package egovframework.injeinc.boffice.pledge.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class PledgeVo extends ComDefaultVO {
	private	int plIdx;
	private	int plWiwid1;
	private	int plWiwid2;
	private	String cateCont;
	private	String subCont;
	private	String telCont;
	private	String emailCont;
	private	String clobCont;
	private	String regId;
	private	String regName;
	private	String regIp;
	private	String regDt;
	private	String modId;
	private	String modName;
	private	String modIp;
	private	String modDt;
	private	int countCont;
	private	int recommendCont;
	private	String plWiwTxt1;
	private	String plWiwTxt2;
	private	String cateContTxt;
	private	String regType;
	private	String actionKey		= "";
	private	String orderBy="1";
	private String fileMaxSize="10";
	private int fileCnt = 0;
	private int maxFileCnt = 3;
	
	/* 검색 */
	private	int searchPlWiwid1 = 0; //지역1
	private	int searchPlWiwid2 = 0; //지역2
	private	String searchStartDate	= ""; //시작 날짜
	private	String searchEndDate	= ""; //끝 날짜
	private	String searchCateCont	= ""; //공약
	
	private	int wiwId; //공약
	private	String wiwName=""; //공약
	private	int wiwParent; //공약
	
	public int getPlIdx() {
		return plIdx;
	}
	public int getSearchPlWiwid1() {
		return searchPlWiwid1;
	}
	public void setSearchPlWiwid1(int searchPlWiwid1) {
		this.searchPlWiwid1 = searchPlWiwid1;
	}
	public int getSearchPlWiwid2() {
		return searchPlWiwid2;
	}
	public void setSearchPlWiwid2(int searchPlWiwid2) {
		this.searchPlWiwid2 = searchPlWiwid2;
	}
	public void setPlIdx(int plIdx) {
		this.plIdx = plIdx;
	}
	public String getSearchStartDate() {
		return searchStartDate;
	}
	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}
	public String getSearchEndDate() {
		return searchEndDate;
	}
	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	public String getSearchCateCont() {
		return searchCateCont;
	}
	public void setSearchCateCont(String searchCateCont) {
		this.searchCateCont = searchCateCont;
	}
	public int getWiwId() {
		return wiwId;
	}
	public void setWiwId(int wiwId) {
		this.wiwId = wiwId;
	}
	public String getWiwName() {
		return wiwName;
	}
	public void setWiwName(String wiwName) {
		this.wiwName = wiwName;
	}
	public int getWiwParent() {
		return wiwParent;
	}
	public void setWiwParent(int wiwParent) {
		this.wiwParent = wiwParent;
	}
	public String getActionKey() {
		return actionKey;
	}
	public void setActionKey(String actionKey) {
		this.actionKey = actionKey;
	}
	public int getPlWiwId1() {
		return plWiwid1;
	}
	public void setPlWiwId1(int plWiwId1) {
		this.plWiwid1 = plWiwId1;
	}
	public int getPlWiwId2() {
		return plWiwid2;
	}
	public void setPlWiwId2(int plWiwId2) {
		this.plWiwid2 = plWiwId2;
	}
	public String getCateCont() {
		return cateCont;
	}
	public void setCateCont(String cateCont) {
		this.cateCont = cateCont;
	}
	public String getSubCont() {
		return subCont;
	}
	public void setSubCont(String subCont) {
		this.subCont = subCont;
	}
	public String getTelCont() {
		return telCont;
	}
	public void setTelCont(String telCont) {
		this.telCont = telCont;
	}
	public String getEmailCont() {
		return emailCont;
	}
	public void setEmailCont(String emailCont) {
		this.emailCont = emailCont;
	}
	public String getClobCont() {
		return clobCont;
	}
	public void setClobCont(String clobCont) {
		this.clobCont = clobCont;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public int getCountCont() {
		return countCont;
	}
	public void setCountCont(int countCont) {
		this.countCont = countCont;
	}
	public int getRecommendCont() {
		return recommendCont;
	}
	public void setRecommendCont(int recommendCont) {
		this.recommendCont = recommendCont;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModIp() {
		return modIp;
	}
	public void setModIp(String modIp) {
		this.modIp = modIp;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
	}
	public String getCateContTxt() {
		return cateContTxt;
	}
	public void setCateContTxt(String cateContTxt) {
		this.cateContTxt = cateContTxt;
	}
	public String getRegType() {
		return regType;
	}
	public void setRegType(String regType) {
		this.regType = regType;
	}
	public String getPlWiwTxt1() {
		return plWiwTxt1;
	}
	public void setPlWiwTxt1(String plWiwTxt1) {
		this.plWiwTxt1 = plWiwTxt1;
	}
	public String getPlWiwTxt2() {
		return plWiwTxt2;
	}
	public void setPlWiwTxt2(String plWiwTxt2) {
		this.plWiwTxt2 = plWiwTxt2;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getFileMaxSize() {
		return fileMaxSize;
	}
	public void setFileMaxSize(String fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}
	public int getFileCnt() {
		return fileCnt;
	}
	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
	public int getMaxFileCnt() {
		return maxFileCnt;
	}
	public void setMaxFileCnt(int maxFileCnt) {
		this.maxFileCnt = maxFileCnt;
	}
	public int getPlWiwid1() {
		return plWiwid1;
	}
	public void setPlWiwid1(int plWiwid1) {
		this.plWiwid1 = plWiwid1;
	}
}