package egovframework.injeinc.foffice.ex.board.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class BbsContentFVo extends ComDefaultVO {
	
	private	int bcIdx				= 0;
	private	int cbIdx				= 0;
	private	int parentSeq			= 0;
	private	int answerStep			= 0;
	private	int answerDepth			= 0;
	private	String notiYn			= "";
	private	String cateCont			= "";
	private	String subCont			= "";
	private	String nameCont			= "";
	private	String addrCont			= "";
	private	String emailCont		= "";
	private	String telCont			= "";
	private	String phoneCont		= "";
	private	String clobCont			= "";
	private	String clobContSearch	= "";
	private	String ext1				= "";
	private	String ext2				= "";
	private	String ext3				= "";
	private	String ext4				= "";
	private	String ext5				= "";
	private	String ext6				= "";
	private	String ext7				= "";
	private	String ext8				= "";
	private	String ext9				= "";
	private	String ext10			= "";
	private	String subLinkCont		= "";
	private	String mLinkCont		= "";
	private	String thumnailCont		= "";
	private	String captionCont		= "";
	private	int countCont			= 0;
	private	String chargeNameCont	= "";
	private	String chargeTelCont	= "";
	private	String openYnCont		= "";
	private	String ansYnCont		= "";
	private	String ansCompCont		= "";
	private	String ansWriter		= "";
	private	String ansContent		= "";
	private	String ansRYn			= "";
	private	String ansDt			= "";
	private	String mwAdOpenYn		= "";
	private	String mwNoReplyYn		= "";
	private	String mwStatusCont		= "";
	private	String apprYn			= "";
	private	String placeType		= "";
	private	String delRsnCd			= "";
	private	String bcDelYn			= "";
	private	String dupcode			= "";
	private	String regDt			= "";
	private	String regId			= "";
	private	String regIp			= "";
	private	String modDt			= "";
	private	String modId			= "";
	private	String modIp			= "";

	private	String actionkey		= "";
	private	String searchCateCont	= "";
	private	String searchNameCont	= "";
	private	String searchOpenYnCont	= "";
	private String searchPlaceType	= "";
	private	String searchDupcode	= "";

	private String cbName			= "";
	private String delRsnTxt		= "";
	private String fileCnt			= "";
	
	private String[] searchNameContInArr;
	private String[] searchNameContNotInArr;
	
	public int getBcIdx() {
		return bcIdx;
	}
	public void setBcIdx(int bcIdx) {
		this.bcIdx = bcIdx;
	}
	public int getCbIdx() {
		return cbIdx;
	}
	public void setCbIdx(int cbIdx) {
		this.cbIdx = cbIdx;
	}
	public int getParentSeq() {
		return parentSeq;
	}
	public void setParentSeq(int parentSeq) {
		this.parentSeq = parentSeq;
	}
	public int getAnswerStep() {
		return answerStep;
	}
	public void setAnswerStep(int answerStep) {
		this.answerStep = answerStep;
	}
	public int getAnswerDepth() {
		return answerDepth;
	}
	public void setAnswerDepth(int answerDepth) {
		this.answerDepth = answerDepth;
	}
	public String getNotiYn() {
		return notiYn;
	}
	public void setNotiYn(String notiYn) {
		this.notiYn = notiYn;
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
	public String getNameCont() {
		return nameCont;
	}
	public void setNameCont(String nameCont) {
		this.nameCont = nameCont;
	}
	public String getAddrCont() {
		return addrCont;
	}
	public void setAddrCont(String addrCont) {
		this.addrCont = addrCont;
	}
	public String getEmailCont() {
		return emailCont;
	}
	public void setEmailCont(String emailCont) {
		this.emailCont = emailCont;
	}
	public String getTelCont() {
		return telCont;
	}
	public void setTelCont(String telCont) {
		this.telCont = telCont;
	}
	public String getPhoneCont() {
		return phoneCont;
	}
	public void setPhoneCont(String phoneCont) {
		this.phoneCont = phoneCont;
	}
	public String getClobCont() {
		return clobCont;
	}
	public void setClobCont(String clobCont) {
		this.clobCont = clobCont;
	}
	public String getClobContSearch() {
		return clobContSearch;
	}
	public void setClobContSearch(String clobContSearch) {
		this.clobContSearch = clobContSearch;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	public String getExt6() {
		return ext6;
	}
	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}
	public String getExt7() {
		return ext7;
	}
	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}
	public String getExt8() {
		return ext8;
	}
	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}
	public String getExt9() {
		return ext9;
	}
	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}
	public String getExt10() {
		return ext10;
	}
	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}
	public String getSubLinkCont() {
		return subLinkCont;
	}
	public void setSubLinkCont(String subLinkCont) {
		this.subLinkCont = subLinkCont;
	}
	public String getmLinkCont() {
		return mLinkCont;
	}
	public void setmLinkCont(String mLinkCont) {
		this.mLinkCont = mLinkCont;
	}
	public String getThumnailCont() {
		return thumnailCont;
	}
	public void setThumnailCont(String thumnailCont) {
		this.thumnailCont = thumnailCont;
	}
	public String getCaptionCont() {
		return captionCont;
	}
	public void setCaptionCont(String captionCont) {
		this.captionCont = captionCont;
	}
	public int getCountCont() {
		return countCont;
	}
	public void setCountCont(int countCont) {
		this.countCont = countCont;
	}
	public String getChargeNameCont() {
		return chargeNameCont;
	}
	public void setChargeNameCont(String chargeNameCont) {
		this.chargeNameCont = chargeNameCont;
	}
	public String getChargeTelCont() {
		return chargeTelCont;
	}
	public void setChargeTelCont(String chargeTelCont) {
		this.chargeTelCont = chargeTelCont;
	}
	public String getOpenYnCont() {
		return openYnCont;
	}
	public void setOpenYnCont(String openYnCont) {
		this.openYnCont = openYnCont;
	}
	public String getAnsYnCont() {
		return ansYnCont;
	}
	public void setAnsYnCont(String ansYnCont) {
		this.ansYnCont = ansYnCont;
	}
	public String getAnsCompCont() {
		return ansCompCont;
	}
	public void setAnsCompCont(String ansCompCont) {
		this.ansCompCont = ansCompCont;
	}
	public String getAnsWriter() {
		return ansWriter;
	}
	public void setAnsWriter(String ansWriter) {
		this.ansWriter = ansWriter;
	}
	public String getAnsContent() {
		return ansContent;
	}
	public void setAnsContent(String ansContent) {
		this.ansContent = ansContent;
	}
	public String getAnsRYn() {
		return ansRYn;
	}
	public void setAnsRYn(String ansRYn) {
		this.ansRYn = ansRYn;
	}
	public String getAnsDt() {
		return ansDt;
	}
	public void setAnsDt(String ansDt) {
		this.ansDt = ansDt;
	}
	public String getMwAdOpenYn() {
		return mwAdOpenYn;
	}
	public void setMwAdOpenYn(String mwAdOpenYn) {
		this.mwAdOpenYn = mwAdOpenYn;
	}
	public String getMwNoReplyYn() {
		return mwNoReplyYn;
	}
	public void setMwNoReplyYn(String mwNoReplyYn) {
		this.mwNoReplyYn = mwNoReplyYn;
	}
	public String getMwStatusCont() {
		return mwStatusCont;
	}
	public void setMwStatusCont(String mwStatusCont) {
		this.mwStatusCont = mwStatusCont;
	}
	public String getApprYn() {
		return apprYn;
	}
	public void setApprYn(String apprYn) {
		this.apprYn = apprYn;
	}
	public String getPlaceType() {
		return placeType;
	}
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}
	public String getDelRsnCd() {
		return delRsnCd;
	}
	public void setDelRsnCd(String delRsnCd) {
		this.delRsnCd = delRsnCd;
	}
	public String getBcDelYn() {
		return bcDelYn;
	}
	public void setBcDelYn(String bcDelYn) {
		this.bcDelYn = bcDelYn;
	}
	public String getDupcode() {
		return dupcode;
	}
	public void setDupcode(String dupcode) {
		this.dupcode = dupcode;
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
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
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
	public String getModIp() {
		return modIp;
	}
	public void setModIp(String modIp) {
		this.modIp = modIp;
	}
	public String getActionkey() {
		return actionkey;
	}
	public void setActionkey(String actionkey) {
		this.actionkey = actionkey;
	}
	public String getSearchCateCont() {
		return searchCateCont;
	}
	public void setSearchCateCont(String searchCateCont) {
		this.searchCateCont = searchCateCont;
	}
	public String getSearchNameCont() {
		return searchNameCont;
	}
	public void setSearchNameCont(String searchNameCont) {
		this.searchNameCont = searchNameCont;
	}
	public String getSearchOpenYnCont() {
		return searchOpenYnCont;
	}
	public void setSearchOpenYnCont(String searchOpenYnCont) {
		this.searchOpenYnCont = searchOpenYnCont;
	}
	public String getSearchPlaceType() {
		return searchPlaceType;
	}
	public void setSearchPlaceType(String searchPlaceType) {
		this.searchPlaceType = searchPlaceType;
	}
	public String getSearchDupcode() {
		return searchDupcode;
	}
	public void setSearchDupcode(String searchDupcode) {
		this.searchDupcode = searchDupcode;
	}
	public String getCbName() {
		return cbName;
	}
	public void setCbName(String cbName) {
		this.cbName = cbName;
	}
	public String getDelRsnTxt() {
		return delRsnTxt;
	}
	public void setDelRsnTxt(String delRsnTxt) {
		this.delRsnTxt = delRsnTxt;
	}
	public String getFileCnt() {
		return fileCnt;
	}
	public void setFileCnt(String fileCnt) {
		this.fileCnt = fileCnt;
	}
	public String[] getSearchNameContInArr() {
		String [] ret = null;
		if(this.searchNameContInArr != null){
			ret = new String[searchNameContInArr.length];
			for(int i=0; i<searchNameContInArr.length; i++){ ret[i] = this.searchNameContInArr[i]; }
		}
		return ret;
	}
	public void setSearchNameContInArr(String[] searchNameContInArr) {
		this.searchNameContInArr = new String[searchNameContInArr.length];
		for(int i=0; i<searchNameContInArr.length; i++){this.searchNameContInArr[i] = searchNameContInArr[i]; }
	}
	public String[] getSearchNameContNotInArr() {
		String [] ret = null;
		if(this.searchNameContNotInArr != null){
			ret = new String[searchNameContNotInArr.length];
			for(int i=0; i<searchNameContNotInArr.length; i++){ ret[i] = this.searchNameContNotInArr[i]; }
		}
		return ret;
	}
	public void setSearchNameContNotInArr(String[] searchNameContNotInArr) {
		this.searchNameContNotInArr = new String[searchNameContNotInArr.length];
		for(int i=0; i<searchNameContNotInArr.length; i++){this.searchNameContNotInArr[i] = searchNameContNotInArr[i]; }		
	}
}