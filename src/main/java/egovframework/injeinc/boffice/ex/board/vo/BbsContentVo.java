package egovframework.injeinc.boffice.ex.board.vo;

import java.util.ArrayList;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class BbsContentVo extends ComDefaultVO {
	
	private	int bcIdx				= 0;
	private	int newBcIdx				= 0;
	private	int cbIdx				= 0;
	private	int newCbIdx				= 0;
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
	private	String searchStartDate	= "";
	private	String searchEndDate	= "";
	private	String searchGroup		= "";
	private	String searchCbIdx		= "";
	private	String searchDelYn		= "";
	private	String searchAnsWriter	= "";
	
	private	String cbName			= "";
	private	String bbsTempletGbn	= "";
	private String delRsnTxt		= "";
	private String fileCnt			= "";
	private String department		= "";
	private	String clobCont2			= "";

	
	private	ArrayList<String> searchCbIdxArr;
	private	String[] searchBcIdxArr;
	
	private String clobAutoMakeYn	= "";
	
	/* 부서명 코드 */

	private String caCdidx = ""; 
	private String cdSubject = "";
	private String subjectCode = "";
	
	/* 바로가기 */
	private String shortCut = "";
	/*2020.08.04 공통코드 추가 ajhwan*/
	private String depth1 = "";
	private String type = "";
	private String target = "";
	private String area = "";
	private String center = "";
	/*2020.08.05 공통코드 추가 이솔이*/
	private String toDate = "";
	private String formDate = "";
	private String contents = "";
	private String proMpPstvt = "";
	private String mode = "";
	/*2020.09.02 공통코드 추가 이솔이*/
	private String law = "";
	
	private String tempname;
	private String tempsname;
	private String tempsize;
	private String pushYn = "";

	private String groupYn = "";

	private int commentCnt = 0;
	

	public String getPushYn() {
		return pushYn;
	}
	public void setPushYn(String pushYn) {
		this.pushYn = pushYn;
	}
	public String getTempname() {
		return tempname;
	}
	public void setTempname(String tempname) {
		this.tempname = tempname;
	}
	public String getTempsname() {
		return tempsname;
	}
	public void setTempsname(String tempsname) {
		this.tempsname = tempsname;
	}
	public String getTempsize() {
		return tempsize;
	}
	public void setTempsize(String tempsize) {
		this.tempsize = tempsize;
	}
	public String getCaCdidx() {
		return caCdidx;
	}
	public void setCaCdidx(String caCdidx) {
		this.caCdidx = caCdidx;
	}
	public String getCdSubject() {
		return cdSubject;
	}
	public void setCdSubject(String cdSubject) {
		this.cdSubject = cdSubject;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getShortCut() {
		return shortCut;
	}
	public void setShortCut(String shortCut) {
		this.shortCut = shortCut;
	}
	
	
	public int getBcIdx() {
		return bcIdx;
	}
	public void setBcIdx(int bcIdx) {
		this.bcIdx = bcIdx;
	}
	
	public int getNewBcIdx() {
		return newBcIdx;
	}
	public void setNewBcIdx(int newBcIdx) {
		this.newBcIdx = newBcIdx;
	}
	public int getCbIdx() {
		return cbIdx;
	}
	public void setCbIdx(int cbIdx) {
		this.cbIdx = cbIdx;
	}
	public int getNewCbIdx() {
		return newCbIdx;
	}
	public void setNewCbIdx(int newCbIdx) {
		this.newCbIdx = newCbIdx;
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
	public String getSearchGroup() {
		return searchGroup;
	}
	public void setSearchGroup(String searchGroup) {
		this.searchGroup = searchGroup;
	}
	public String getSearchCbIdx() {
		return searchCbIdx;
	}
	public void setSearchCbIdx(String searchCbIdx) {
		this.searchCbIdx = searchCbIdx;
	}
	public String getSearchDelYn() {
		return searchDelYn;
	}
	public void setSearchDelYn(String searchDelYn) {
		this.searchDelYn = searchDelYn;
	}
	public String getSearchAnsWriter() {
		return searchAnsWriter;
	}
	public void setSearchAnsWriter(String searchAnsWriter) {
		this.searchAnsWriter = searchAnsWriter;
	}
	public String getCbName() {
		return cbName;
	}
	public void setCbName(String cbName) {
		this.cbName = cbName;
	}
	public String getBbsTempletGbn() {
		return bbsTempletGbn;
	}
	public void setBbsTempletGbn(String bbsTempletGbn) {
		this.bbsTempletGbn = bbsTempletGbn;
	}
	public String getDelRsnTxt() {
		return delRsnTxt;
	}
	public void setDelRsnTxt(String delRsnTxt) {
		this.delRsnTxt = delRsnTxt;
	}
	public ArrayList<String> getSearchCbIdxArr() {
		ArrayList<String> arrRet = new ArrayList<String>(); 
		if(this.searchCbIdxArr != null){
			arrRet.addAll(searchCbIdxArr);
		}
		return arrRet;
	}
	public void setSearchCbIdxArr(ArrayList<String> searchCbIdxArr) {
		this.searchCbIdxArr = new ArrayList<String>();
		if(searchCbIdxArr != null){
			this.searchCbIdxArr.addAll(searchCbIdxArr);
		}
	}
	public String[] getSearchBcIdxArr() {
		String [] ret = null;
		if(this.searchBcIdxArr != null){
			ret = new String[searchBcIdxArr.length];
			for(int i=0; i<searchBcIdxArr.length; i++){ ret[i] = this.searchBcIdxArr[i]; }
		}
		return ret;
	}
	public void setSearchBcIdxArr(String[] searchBcIdxArr) {
		this.searchBcIdxArr = new String[searchBcIdxArr.length];
		for(int i=0; i<searchBcIdxArr.length; i++){this.searchBcIdxArr[i] = searchBcIdxArr[i]; }		
	}
	public String getFileCnt() {
		return fileCnt;
	}
	public void setFileCnt(String fileCnt) {
		this.fileCnt = fileCnt;
	}
	public String getClobAutoMakeYn() {
		return clobAutoMakeYn;
	}
	public void setClobAutoMakeYn(String clobAutoMakeYn) {
		this.clobAutoMakeYn = clobAutoMakeYn;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getClobCont2() {
		return clobCont2;
	}
	public void setClobCont2(String clobCont2) {
		this.clobCont2 = clobCont2;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getGroupYn() {
		return groupYn;
	}
	public void setGroupYn(String groupYn) {
		this.groupYn = groupYn;
	}
	public int getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	public String getDepth1() {
		return depth1;
	}
	public void setDepth1(String depth1) {
		this.depth1 = depth1;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFormDate() {
		return formDate;
	}
	public void setFormDate(String formDate) {
		this.formDate = formDate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getProMpPstvt() {
		return proMpPstvt;
	}
	public void setProMpPstvt(String proMpPstvt) {
		this.proMpPstvt = proMpPstvt;
	}
	public String getLaw() {
		return law;
	}
	public void setLaw(String law) {
		this.law = law;
	}
	
	
	
}