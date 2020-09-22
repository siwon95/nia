package egovframework.injeinc.boffice.ex.bbs.boardAdmin.vo;

import egovframework.cmm.ComDefaultVO;


public class BoardAdminVo extends ComDefaultVO{
	
	private int cbIdx = 0;
	private String cbCd;
	private String cbUprCd;
	private String cbName;
	
	private String noCont;
	private String subCont;
	private String subContTemp;
	private String nameCont;
	private String regDt;
	private int countCont = 0;
	
	private String rowCnt;
	private String labelName;
	private String labelProvSize;
	private String webUseYn;
	private String bbsTempletGbn;
	
	private String flag;
	private String cbGroupName;
	private String id;
	private String parent;
	private String text;
	private String code;
	private String mode;
	private String codeName;
	private String position;
	private String idx;
	private String labelCodeSelectBox;
	private String gbnVal;
	private String searchKeyWord;
	private String searchCondition;
	private String searchBbsGroup;
	private String searchBbs;
	private String scRegDtSt;
	private String startTime;
	private String scRegDtEd;
	private String endTime;
	private String colValue;
	private String itemCode;
	private String gbn;
	
	/** 검색  **/
	private String cateTypeCd;
	private String tgtTypeCd;
	private String searchKey;
	
	/** CMS_BBS **/
	private String ordNo;
	private String useYn;
	private String regId;
	private String modDt;
	private String modId;
	private String mgrSiteCd;
	
	/** BBS_CONFIG **/
	private String bbsApprYn;
	private String categoryUseYn;
	private int bbsFileCnt = 0;
	private String bbsCommentUseYn;
	private String adApprYn;
	private String mwRKd;
	
	/** BBS_CATEGORY **/
	private String categoryCode;
	private String categoryName;
	
	/** BBS_USR_CONFIG **/
	private String listGbn;
	private String readGbn;
	private String writeGbn;
	private String modGbn;
	private String delGbn;
	private String answerGbn;
	
	/** CONFIG_PROPERTY **/
	private String labelOrdNo;
	private String contentMapping;
	private String labelPropGbn;
	private String labelCompYn;
	private String searchLabelUseYn;
	private String searchClobYn;
	private String mobileUseYn;

	/** BBS_CONTENT **/
	private int bcIdx = 0;
	private String delRsnCd;
	private int parentSeq = 0;
	private int answerStep = 0;
	private int answerDepth = 0;
	private String cateCont;
	private String addrCont;
	private String addrCont1;
	private String addrCont2;
	private String addrCont3;
	private String emailCont;
	private String telCont;
	private String telCont1;
	private String telCont2;
	private String telCont3;
	private String phoneCont;
	private String phoneCont1;
	private String phoneCont2;
	private String phoneCont3;
	private String regdtCont;
	private String clobCont;
	private String ClobContSearch;
	private String pwCont;
	private String mLinkCont;
	private String thumnailCont;
	private String subLinkCont;
	private String openYnCont;
	private String ansYnCont;
	private String ansCompCont;
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;
	private String ext6;
	private String ext7;
	private String ext8;
	private String ext9;
	private String ext10;
	private String tempYn;
	private String mwAdOpenYn;
	private String mwUsOpenYn;
	private String ansRYn;
	private String mwRSmsYn;
	private String mwRVmsYn;
	private String mwREmailYn;
	private String mwNoReplyYn;
	private String notiYn;
	private String apprYn;
	private String bcDelYn;
	private String mwStatusCont;
	private String mwBstatusCont;
	private String captionCont;
	
	/** BBS_TEMPLET **/
	private int btIdx;
	private String bbsTempletFileName;
	private String bbsTempletFileSize;
	private String bbsTempletFilePath;
	private String bbsTempletFileType;
	private String fileName;
	
	/** CONTENT_MINWON_RESULT **/
	private String mcIdx;
	private String mcTitle;
	private String mcDeptCd;
	private String mcDeptNo1Cd;
	private String mcDeptHist;
	private String mcReplyer;
	private String mcTel1;
	private String mcTel1_1;
	private String mcTel1_2;
	private String mcTel1_3;
	private String mcTel2;
	private String mcMsg;
	private String mcUnitCd;
	private String mcAreaCd;
	private String mcFiledCd;
	private String mcKdCd;
	private String mcResult;
	private String mcPointTxt;
	private String contentClob;
	private String mcDelayDay;
	private String mcDelayRsn;
	private String mcStatus;
	private String auditYn;
	private String mcBSender;
	private String mcBMth;
	private String mcBDay;
	private String mcBTxt;
	private String mcASender;
	private String mcAMth;
	private String mcADay;
	private String mcATxt;
	
	private String fileNo;
	private String fileStreCours;
	private String streFileNm;
	private String orignlFileNm;
	private String fileExtsn;
	private String fileCn;
	private String fileSize;

	/** session Id **/
	private String adminId;
	/** session 권한 **/
	private String permCd;
	
	/** CONTENT_DEL_RSN **/
	private String delRsnTxt;
	
	public int getCbIdx() {
		return cbIdx;
	}
	public void setCbIdx(int cbIdx) {
		this.cbIdx = cbIdx;
	}
	public String getCbCd() {
		return cbCd;
	}
	public void setCbCd(String cbCd) {
		this.cbCd = cbCd;
	}
	public String getCbUprCd() {
		return cbUprCd;
	}
	public void setCbUprCd(String cbUprCd) {
		this.cbUprCd = cbUprCd;
	}
	public String getCbName() {
		return cbName;
	}
	public void setCbName(String cbName) {
		this.cbName = cbName;
	}
	public String getNoCont() {
		return noCont;
	}
	public void setNoCont(String noCont) {
		this.noCont = noCont;
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
	public String getRowCnt() {
		return rowCnt;
	}
	public void setRowCnt(String rowCnt) {
		this.rowCnt = rowCnt;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getLabelProvSize() {
		return labelProvSize;
	}
	public void setLabelProvSize(String labelProvSize) {
		this.labelProvSize = labelProvSize;
	}
	public String getWebUseYn() {
		return webUseYn;
	}
	public void setWebUseYn(String webUseYn) {
		this.webUseYn = webUseYn;
	}
	public String getBbsTempletGbn() {
		return bbsTempletGbn;
	}
	public void setBbsTempletGbn(String bbsTempletGbn) {
		this.bbsTempletGbn = bbsTempletGbn;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCbGroupName() {
		return cbGroupName;
	}
	public void setCbGroupName(String cbGroupName) {
		this.cbGroupName = cbGroupName;
	}
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getLabelCodeSelectBox() {
		return labelCodeSelectBox;
	}
	public void setLabelCodeSelectBox(String labelCodeSelectBox) {
		this.labelCodeSelectBox = labelCodeSelectBox;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getBbsApprYn() {
		return bbsApprYn;
	}
	public void setBbsApprYn(String bbsApprYn) {
		this.bbsApprYn = bbsApprYn;
	}
	public String getCategoryUseYn() {
		return categoryUseYn;
	}
	public void setCategoryUseYn(String categoryUseYn) {
		this.categoryUseYn = categoryUseYn;
	}
	public int getBbsFileCnt() {
		return bbsFileCnt;
	}
	public void setBbsFileCnt(int bbsFileCnt) {
		this.bbsFileCnt = bbsFileCnt;
	}
	public String getBbsCommentUseYn() {
		return bbsCommentUseYn;
	}
	public void setBbsCommentUseYn(String bbsCommentUseYn) {
		this.bbsCommentUseYn = bbsCommentUseYn;
	}
	public String getAdApprYn() {
		return adApprYn;
	}
	public void setAdApprYn(String adApprYn) {
		this.adApprYn = adApprYn;
	}
	public String getMwRKd() {
		return mwRKd;
	}
	public void setMwRKd(String mwRKd) {
		this.mwRKd = mwRKd;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getListGbn() {
		return listGbn;
	}
	public void setListGbn(String listGbn) {
		this.listGbn = listGbn;
	}
	public String getReadGbn() {
		return readGbn;
	}
	public void setReadGbn(String readGbn) {
		this.readGbn = readGbn;
	}
	public String getWriteGbn() {
		return writeGbn;
	}
	public void setWriteGbn(String writeGbn) {
		this.writeGbn = writeGbn;
	}
	public String getModGbn() {
		return modGbn;
	}
	public void setModGbn(String modGbn) {
		this.modGbn = modGbn;
	}
	public String getDelGbn() {
		return delGbn;
	}
	public void setDelGbn(String delGbn) {
		this.delGbn = delGbn;
	}
	public String getAnswerGbn() {
		return answerGbn;
	}
	public void setAnswerGbn(String answerGbn) {
		this.answerGbn = answerGbn;
	}
	public String getLabelOrdNo() {
		return labelOrdNo;
	}
	public void setLabelOrdNo(String labelOrdNo) {
		this.labelOrdNo = labelOrdNo;
	}
	public String getContentMapping() {
		return contentMapping;
	}
	public void setContentMapping(String contentMapping) {
		this.contentMapping = contentMapping;
	}
	public String getLabelPropGbn() {
		return labelPropGbn;
	}
	public void setLabelPropGbn(String labelPropGbn) {
		this.labelPropGbn = labelPropGbn;
	}
	public String getLabelCompYn() {
		return labelCompYn;
	}
	public void setLabelCompYn(String labelCompYn) {
		this.labelCompYn = labelCompYn;
	}
	public String getSearchLabelUseYn() {
		return searchLabelUseYn;
	}
	public void setSearchLabelUseYn(String searchLabelUseYn) {
		this.searchLabelUseYn = searchLabelUseYn;
	}
	public String getSearchClobYn() {
		return searchClobYn;
	}
	public void setSearchClobYn(String searchClobYn) {
		this.searchClobYn = searchClobYn;
	}
	public String getMobileUseYn() {
		return mobileUseYn;
	}
	public void setMobileUseYn(String mobileUseYn) {
		this.mobileUseYn = mobileUseYn;
	}
	public int getBcIdx() {
		return bcIdx;
	}
	public void setBcIdx(int bcIdx) {
		this.bcIdx = bcIdx;
	}
	public String getDelRsnCd() {
		return delRsnCd;
	}
	public void setDelRsnCd(String delRsnCd) {
		this.delRsnCd = delRsnCd;
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
	public String getCateCont() {
		return cateCont;
	}
	public void setCateCont(String cateCont) {
		this.cateCont = cateCont;
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
	public String getRegdtCont() {
		return regdtCont;
	}
	public void setRegdtCont(String regdtCont) {
		this.regdtCont = regdtCont;
	}
	public String getClobCont() {
		return clobCont;
	}
	public void setClobCont(String clobCont) {
		this.clobCont = clobCont;
	}
	public String getPwCont() {
		return pwCont;
	}
	public void setPwCont(String pwCont) {
		this.pwCont = pwCont;
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
	public String getSubLinkCont() {
		return subLinkCont;
	}
	public void setSubLinkCont(String subLinkCont) {
		this.subLinkCont = subLinkCont;
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
	public String getTempYn() {
		return tempYn;
	}
	public void setTempYn(String tempYn) {
		this.tempYn = tempYn;
	}
	public String getMwAdOpenYn() {
		return mwAdOpenYn;
	}
	public void setMwAdOpenYn(String mwAdOpenYn) {
		this.mwAdOpenYn = mwAdOpenYn;
	}
	public String getMwUsOpenYn() {
		return mwUsOpenYn;
	}
	public void setMwUsOpenYn(String mwUsOpenYn) {
		this.mwUsOpenYn = mwUsOpenYn;
	}
	public String getAnsRYn() {
		return ansRYn;
	}
	public void setAnsRYn(String ansRYn) {
		this.ansRYn = ansRYn;
	}
	public String getMwRSmsYn() {
		return mwRSmsYn;
	}
	public void setMwRSmsYn(String mwRSmsYn) {
		this.mwRSmsYn = mwRSmsYn;
	}
	public String getMwRVmsYn() {
		return mwRVmsYn;
	}
	public void setMwRVmsYn(String mwRVmsYn) {
		this.mwRVmsYn = mwRVmsYn;
	}
	public String getMwREmailYn() {
		return mwREmailYn;
	}
	public void setMwREmailYn(String mwREmailYn) {
		this.mwREmailYn = mwREmailYn;
	}
	public String getMwNoReplyYn() {
		return mwNoReplyYn;
	}
	public void setMwNoReplyYn(String mwNoReplyYn) {
		this.mwNoReplyYn = mwNoReplyYn;
	}
	public String getNotiYn() {
		return notiYn;
	}
	public void setNotiYn(String notiYn) {
		this.notiYn = notiYn;
	}
	public String getApprYn() {
		return apprYn;
	}
	public void setApprYn(String apprYn) {
		this.apprYn = apprYn;
	}
	public int getBtIdx() {
		return btIdx;
	}
	public void setBtIdx(int btIdx) {
		this.btIdx = btIdx;
	}
	public String getBbsTempletFileName() {
		return bbsTempletFileName;
	}
	public void setBbsTempletFileName(String bbsTempletFileName) {
		this.bbsTempletFileName = bbsTempletFileName;
	}
	public String getBbsTempletFileSize() {
		return bbsTempletFileSize;
	}
	public void setBbsTempletFileSize(String bbsTempletFileSize) {
		this.bbsTempletFileSize = bbsTempletFileSize;
	}
	public String getBbsTempletFilePath() {
		return bbsTempletFilePath;
	}
	public void setBbsTempletFilePath(String bbsTempletFilePath) {
		this.bbsTempletFilePath = bbsTempletFilePath;
	}
	public String getBbsTempletFileType() {
		return bbsTempletFileType;
	}
	public void setBbsTempletFileType(String bbsTempletFileType) {
		this.bbsTempletFileType = bbsTempletFileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMcIdx() {
		return mcIdx;
	}
	public void setMcIdx(String mcIdx) {
		this.mcIdx = mcIdx;
	}
	public String getMcTitle() {
		return mcTitle;
	}
	public void setMcTitle(String mcTitle) {
		this.mcTitle = mcTitle;
	}
	public String getMcDeptCd() {
		return mcDeptCd;
	}
	public void setMcDeptCd(String mcDeptCd) {
		this.mcDeptCd = mcDeptCd;
	}
	public String getMcDeptNo1Cd() {
		return mcDeptNo1Cd;
	}
	public void setMcDeptNo1Cd(String mcDeptNo1Cd) {
		this.mcDeptNo1Cd = mcDeptNo1Cd;
	}
	public String getMcDeptHist() {
		return mcDeptHist;
	}
	public void setMcDeptHist(String mcDeptHist) {
		this.mcDeptHist = mcDeptHist;
	}
	public String getMcReplyer() {
		return mcReplyer;
	}
	public void setMcReplyer(String mcReplyer) {
		this.mcReplyer = mcReplyer;
	}
	public String getMcTel1() {
		return mcTel1;
	}
	public void setMcTel1(String mcTel1) {
		this.mcTel1 = mcTel1;
	}
	public String getMcTel2() {
		return mcTel2;
	}
	public void setMcTel2(String mcTel2) {
		this.mcTel2 = mcTel2;
	}
	public String getMcMsg() {
		return mcMsg;
	}
	public void setMcMsg(String mcMsg) {
		this.mcMsg = mcMsg;
	}
	public String getMcUnitCd() {
		return mcUnitCd;
	}
	public void setMcUnitCd(String mcUnitCd) {
		this.mcUnitCd = mcUnitCd;
	}
	public String getMcAreaCd() {
		return mcAreaCd;
	}
	public void setMcAreaCd(String mcAreaCd) {
		this.mcAreaCd = mcAreaCd;
	}
	public String getMcFiledCd() {
		return mcFiledCd;
	}
	public void setMcFiledCd(String mcFiledCd) {
		this.mcFiledCd = mcFiledCd;
	}
	public String getMcKdCd() {
		return mcKdCd;
	}
	public void setMcKdCd(String mcKdCd) {
		this.mcKdCd = mcKdCd;
	}
	public String getMcResult() {
		return mcResult;
	}
	public void setMcResult(String mcResult) {
		this.mcResult = mcResult;
	}
	public String getMcPointTxt() {
		return mcPointTxt;
	}
	public void setMcPointTxt(String mcPointTxt) {
		this.mcPointTxt = mcPointTxt;
	}
	public String getContentClob() {
		return contentClob;
	}
	public void setContentClob(String contentClob) {
		this.contentClob = contentClob;
	}
	public String getMcDelayDay() {
		return mcDelayDay;
	}
	public void setMcDelayDay(String mcDelayDay) {
		this.mcDelayDay = mcDelayDay;
	}
	public String getMcDelayRsn() {
		return mcDelayRsn;
	}
	public void setMcDelayRsn(String mcDelayRsn) {
		this.mcDelayRsn = mcDelayRsn;
	}
	public String getMcStatus() {
		return mcStatus;
	}
	public void setMcStatus(String mcStatus) {
		this.mcStatus = mcStatus;
	}
	public String getAuditYn() {
		return auditYn;
	}
	public void setAuditYn(String auditYn) {
		this.auditYn = auditYn;
	}
	public String getMcBSender() {
		return mcBSender;
	}
	public void setMcBSender(String mcBSender) {
		this.mcBSender = mcBSender;
	}
	public String getMcBMth() {
		return mcBMth;
	}
	public void setMcBMth(String mcBMth) {
		this.mcBMth = mcBMth;
	}
	public String getMcBDay() {
		return mcBDay;
	}
	public void setMcBDay(String mcBDay) {
		this.mcBDay = mcBDay;
	}
	public String getMcBTxt() {
		return mcBTxt;
	}
	public void setMcBTxt(String mcBTxt) {
		this.mcBTxt = mcBTxt;
	}
	public String getMcASender() {
		return mcASender;
	}
	public void setMcASender(String mcASender) {
		this.mcASender = mcASender;
	}
	public String getMcAMth() {
		return mcAMth;
	}
	public void setMcAMth(String mcAMth) {
		this.mcAMth = mcAMth;
	}
	public String getMcADay() {
		return mcADay;
	}
	public void setMcADay(String mcADay) {
		this.mcADay = mcADay;
	}
	public String getMcATxt() {
		return mcATxt;
	}
	public void setMcATxt(String mcATxt) {
		this.mcATxt = mcATxt;
	}
	public String getAddrCont1() {
		return addrCont1;
	}
	public void setAddrCont1(String addrCont1) {
		this.addrCont1 = addrCont1;
	}
	public String getAddrCont2() {
		return addrCont2;
	}
	public void setAddrCont2(String addrCont2) {
		this.addrCont2 = addrCont2;
	}
	public String getAddrCont3() {
		return addrCont3;
	}
	public void setAddrCont3(String addrCont3) {
		this.addrCont3 = addrCont3;
	}
	public String getTelCont1() {
		return telCont1;
	}
	public void setTelCont1(String telCont1) {
		this.telCont1 = telCont1;
	}
	public String getTelCont2() {
		return telCont2;
	}
	public void setTelCont2(String telCont2) {
		this.telCont2 = telCont2;
	}
	public String getTelCont3() {
		return telCont3;
	}
	public void setTelCont3(String telCont3) {
		this.telCont3 = telCont3;
	}
	public String getPhoneCont1() {
		return phoneCont1;
	}
	public void setPhoneCont1(String phoneCont1) {
		this.phoneCont1 = phoneCont1;
	}
	public String getPhoneCont2() {
		return phoneCont2;
	}
	public void setPhoneCont2(String phoneCont2) {
		this.phoneCont2 = phoneCont2;
	}
	public String getPhoneCont3() {
		return phoneCont3;
	}
	public void setPhoneCont3(String phoneCont3) {
		this.phoneCont3 = phoneCont3;
	}
	public String getBcDelYn() {
		return bcDelYn;
	}
	public void setBcDelYn(String bcDelYn) {
		this.bcDelYn = bcDelYn;
	}
	public String getMwStatusCont() {
		return mwStatusCont;
	}
	public void setMwStatusCont(String mwStatusCont) {
		this.mwStatusCont = mwStatusCont;
	}
	public String getMwBstatusCont() {
		return mwBstatusCont;
	}
	public void setMwBstatusCont(String mwBstatusCont) {
		this.mwBstatusCont = mwBstatusCont;
	}
	public String getMcTel1_1() {
		return mcTel1_1;
	}
	public void setMcTel1_1(String mcTel1_1) {
		this.mcTel1_1 = mcTel1_1;
	}
	public String getMcTel1_2() {
		return mcTel1_2;
	}
	public void setMcTel1_2(String mcTel1_2) {
		this.mcTel1_2 = mcTel1_2;
	}
	public String getMcTel1_3() {
		return mcTel1_3;
	}
	public void setMcTel1_3(String mcTel1_3) {
		this.mcTel1_3 = mcTel1_3;
	}
	public String getGbnVal() {
		return gbnVal;
	}
	public void setGbnVal(String gbnVal) {
		this.gbnVal = gbnVal;
	}
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchBbsGroup() {
		return searchBbsGroup;
	}
	public void setSearchBbsGroup(String searchBbsGroup) {
		this.searchBbsGroup = searchBbsGroup;
	}
	public String getSearchBbs() {
		return searchBbs;
	}
	public void setSearchBbs(String searchBbs) {
		this.searchBbs = searchBbs;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileStreCours() {
		return fileStreCours;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}
	public String getFileCn() {
		return fileCn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getScRegDtSt() {
		return scRegDtSt;
	}
	public void setScRegDtSt(String scRegDtSt) {
		this.scRegDtSt = scRegDtSt;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getScRegDtEd() {
		return scRegDtEd;
	}
	public void setScRegDtEd(String scRegDtEd) {
		this.scRegDtEd = scRegDtEd;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getColValue() {
		return colValue;
	}
	public void setColValue(String colValue) {
		this.colValue = colValue;
	}
	public String getCateTypeCd() {
		return cateTypeCd;
	}
	public void setCateTypeCd(String cateTypeCd) {
		this.cateTypeCd = cateTypeCd;
	}
	public String getTgtTypeCd() {
		return tgtTypeCd;
	}
	public void setTgtTypeCd(String tgtTypeCd) {
		this.tgtTypeCd = tgtTypeCd;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getDelRsnTxt() {
		return delRsnTxt;
	}
	public void setDelRsnTxt(String delRsnTxt) {
		this.delRsnTxt = delRsnTxt;
	}
	public String getGbn() {
		return gbn;
	}
	public void setGbn(String gbn) {
		this.gbn = gbn;
	}
	public String getCaptionCont() {
		return captionCont;
	}
	public void setCaptionCont(String captionCont) {
		this.captionCont = captionCont;
	}
	public String getPermCd() {
		return permCd;
	}
	public void setPermCd(String permCd) {
		this.permCd = permCd;
	}
	public String getClobContSearch() {
		return ClobContSearch;
	}
	public void setClobContSearch(String clobContSearch) {
		ClobContSearch = clobContSearch;
	}
	public String getSubContTemp() {
		return subContTemp;
	}
	public void setSubContTemp(String subContTemp) {
		this.subContTemp = subContTemp;
	}
	public String getMgrSiteCd() {
		return mgrSiteCd;
	}
	public void setMgrSiteCd(String mgrSiteCd) {
		this.mgrSiteCd = mgrSiteCd;
	}
	
	
}