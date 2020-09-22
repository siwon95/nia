package egovframework.injeinc.foffice.ex.bbs.vo;

import java.util.List;

import egovframework.cmm.ComDefaultVO;

public class BbsFVo extends ComDefaultVO {

	private String flag;
	private String cbGroupName;
	private String id;
	private String parent;
	private String text;
	private String code;
	private String codeName;
	private String position;
	private String idx;
	private String labelCodeSelectBox;
	private String webUseYn;
	private String rowNum = "3";

	/** CMS_BBS **/
	private int cbIdx = 0;
	private	String[] searchCbIdxArr;
	private String cbCd;
	private String cbUprCd;
	private String cbName;
	private String mgrUrl;
	private String usrUrl;
	private String ordNo;
	private String useYn;
	private String regDt;
	private String regId;
	private String modDt;
	private String modId;

	/** BBS_CONFIG **/
	private String bbsTempletGbn;
	private String bbsApprYn;
	private String categoryUseYn;
	private int bbsFileCnt = 0;
	private String fileMaxSize;
	private String bbsCommentUseYn;
	private String adApprYn;
	private String mwRKd;
	private String mListYn;
	private String editorYn;
	private String commentYn;
	private String socialYn;

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
	private String anonymityYn;

	/** CONFIG_PROPERTY **/
	private String labelOrdNo;
	private String contentMapping;
	private String labelPropGbn;
	private String labelCompYn;
	private String labelProvSize;
	private String searchLabelUseYn;
	private String searchClobYn;
	private String mobileUseYn;
	private String itemCode;

	/** BBS_CONTENT **/
	private int bcIdx = 0;
	private	String[] searchBcIdxArr;
	private int parentSeq = 0;
	private int answerStep = 0;
	private int answerDepth = 0;
	private String noCont;
	private String cateCont;
	private String subCont;
	private String subContTemp;
	private String nameCont;
	private String addrCont;
	private String emailCont;
	private String telCont;
	private String phoneCont;
	private int countCont = 0;
	private String chargeNameCont;
	private String chargeTelCont;
	private String clobCont;
	private String clobContSearch;
	private String clobContTemp;
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
	private String mwStatusCont;
	private String captionCont;
	private String dupcode;
	private String clobCont2;
	private String gubPassword;
	private String oldGubPassword;
	private String type;         // 2020.08.04 이솔이 추가
	private String target;       // 2020.08.04 이솔이 추가
	private String area; 
	private String depth_1;
	private String center;
	private String content;      // 2020.08.12 이솔이 추가
	private String proMpPstvt;   //2020.08.25 안정환 추가 
	private String cnt;          //2020.08.26 전진형 추가
	private String checkAll;     //2020.08.26 전진형 추가
	private String law;          //2020.09.02 이솔이 추가
	private String detailType;   //2020.09.07 전진형 추가
	
	
	/** BBS_TEMPLET **/
	private int btIdx;
	private String bbsTempletFileName;
	private String bbsTempletFileSize;
	private String bbsTempletFilePath;
	private String bbsTempletFileType;
	private String fileName;

	/** CONTENT_FILE **/
	private int fileNo =0;
	private String fileStreCours;
	private String streFileNm;
	private String orignlFileNm;
	private String fileExtsn;
	private String fileCn;
	private String fileSize;
	private String tempname;
	private String tempsname;
	private String tempsize;
	
	

	/** CONTENT_MINWON_RESULT **/
	private String mcIdx;
	private String mcTitle;
	private String mcDeptCd;
	private String mcHist;
	private String mcDeptNo1Cd;
	private String mcDeptHist;
	private String mcReplyer;
	private String mcTel1;
	private String mcTel2;
	private String mcMsg;
	private String mcUnitCd;
	private String mcAreaCd;
	private String mcFiledCd;
	private String mcKdCd;
	private String mcResult;
	private String mcPointTxt;
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
	private String contentClob;
	private String modDt1;
	private String modDt2;
	private String modDt3;
	private String mcSurvey;
	private String mcSurveyDesc;

	private String strDomain;
	private String category;

	/** 생성,수정 구분 **/
	private String mode;

	/** 상세페이지 리스트 **/
	private String dataContent;

	/** 검색 **/
	private String cateTypeCd;
	private String tgtTypeCd;
	
	private String searchKey;
	private String searchKey2;    // 2020.08.10 이솔이 기존 소스에서 사용으로 삭제하지 않음 
	private String searchType;    // 2020.07.31 이솔이 searchKey2 -> searchType   수정
	private String searchTarget;  // 2020.07.31 이솔이 searchKey3 -> searchTarget 수정
	private String searchArea;    // 2020.07.31 이솔이 searchKey4 -> searchArea   수정
	private String searchOpenYnCont = "";
	private String searchYear;    // 2020.07.31 박종범 검색조건 추가
	private String searchCenter;  // 2020.08.10 이솔이 추가
	private String searchRegDt;   // 2020.08.12 안정환 추가  
	private String searchContent; // 2020.08.12 이솔이 추가 
	private String searchDepth;   //2020.08.24 안정환 추가
	private String searchKind;    //2020.08.26 전진형 추가
	private String searchLaw;     //2020.09.02 이솔이 추가
	
	private String searchDateFrom;
	private String searchDateTo;
	private String searchDateType;

	/** 본인아이디 일경우 세션 아이디 **/
	private String ssId;
	private String regIp;

	/** 권한 정보 **/
	private String author;

	private String delRsnTxt;
	private String mcEndDay;

	/** 공지(서브도메인 관련코드) 사항 코드 **/
	private String deptCode;
	private String deptCodeName;
	
	
	/** 부서명 리스트 **/
	private String caCdidx = ""; 
	private String cdSubject = "";
	private String subjectCode = "";

	private String mc_dept_cd = "";
	private String ansDeadlineDt = "";
	
	
	private List<String> categoryList;
	
	private String fileViewYn = "N";
	private String contentViewYn = "N";
	private String cdDepstep = "";
	
	private double currentTime;
	private String userId;
	private double playTime;
	private String courseYn;
	private double duration;
	private String abType;

	public String getCdDepstep() {
		return cdDepstep;
	}
	public void setCdDepstep(String cdDepstep) {
		this.cdDepstep = cdDepstep;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMc_dept_cd() {
		return mc_dept_cd;
	}
	public void setMc_dept_cd(String mc_dept_cd) {
		this.mc_dept_cd = mc_dept_cd;
	}
	public List<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
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
	
	
	public String getTempname() {
		return tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
	}

	/*
	 * private String updAuthor; private String delAuthor;
	 */
	/** 순번 **/
	private String rnum;

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

	public String getWebUseYn() {
		return webUseYn;
	}

	public void setWebUseYn(String webUseYn) {
		this.webUseYn = webUseYn;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

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

	public String getMgrUrl() {
		return mgrUrl;
	}

	public void setMgrUrl(String mgrUrl) {
		this.mgrUrl = mgrUrl;
	}

	public String getUsrUrl() {
		return usrUrl;
	}

	public void setUsrUrl(String usrUrl) {
		this.usrUrl = usrUrl;
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

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getRegId() {
		return regId == null ? "" : regId;
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

	public String getBbsTempletGbn() {
		return bbsTempletGbn;
	}

	public void setBbsTempletGbn(String bbsTempletGbn) {
		this.bbsTempletGbn = bbsTempletGbn;
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

	public String getFileMaxSize() {
		return fileMaxSize;
	}

	public void setFileMaxSize(String fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
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

	public String getLabelProvSize() {
		return labelProvSize;
	}

	public void setLabelProvSize(String labelProvSize) {
		this.labelProvSize = labelProvSize;
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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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

	public int getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(int parentSeq) {
		this.parentSeq = parentSeq;
	}

	public int getAnswerDepth() {
		return answerDepth;
	}

	public void setAnswerDepth(int answerDepth) {
		this.answerDepth = answerDepth;
	}

	public String getNoCont() {
		return noCont;
	}

	public void setNoCont(String noCont) {
		this.noCont = noCont;
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

	public int getCountCont() {
		return countCont;
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

	public void setCountCont(int countCont) {
		this.countCont = countCont;
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

	public String getOpenynCont() {
		return openYnCont;
	}

	public void setOpenynCont(String openYnCont) {
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
		return bbsTempletFilePath == null ? "" : bbsTempletFilePath;
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
		return fileName == null ? "" : fileName;
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

	public String getDataContent() {
		return dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	public String getCateTypeCd() {
		return cateTypeCd == null ? "" : cateTypeCd;
	}

	public void setCateTypeCd(String cateTypeCd) {
		this.cateTypeCd = cateTypeCd;
	}

	public String getTgtTypeCd() {
		return tgtTypeCd == null ? "" : tgtTypeCd;
	}

	public void setTgtTypeCd(String tgtTypeCd) {
		this.tgtTypeCd = tgtTypeCd;
	}

	public String getSearchKey() {
		return searchKey == null ? "" : searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchOpenYnCont() {
		return searchOpenYnCont;
	}

	public void setSearchOpenYnCont(String searchOpenYnCont) {
		this.searchOpenYnCont = searchOpenYnCont;
	}

	public String getSsId() {
		return ssId == null ? "" : ssId;
	}

	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getAuthor() {
		return author == null ? "" : author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
		return fileCn == null ? "" : fileCn;
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

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getMcIdx() {
		return mcIdx == null ? "" : mcIdx;
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

	public String getMcHist() {
		return mcHist;
	}

	public void setMcHist(String mcHist) {
		this.mcHist = mcHist;
	}

	private String labelName;

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

	public String getContentClob() {
		return contentClob;
	}

	public void setContentClob(String contentClob) {
		this.contentClob = contentClob;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getMwStatusCont() {
		return mwStatusCont;
	}

	public void setMwStatusCont(String mwStatusCont) {
		this.mwStatusCont = mwStatusCont;
	}

	public String getDelRsnTxt() {
		return delRsnTxt;
	}

	public void setDelRsnTxt(String delRsnTxt) {
		this.delRsnTxt = delRsnTxt;
	}

	public String getMcEndDay() {
		return mcEndDay;
	}

	public void setMcEndDay(String mcEndDay) {
		this.mcEndDay = mcEndDay;
	}

	public String getCaptionCont() {
		return captionCont;
	}

	public void setCaptionCont(String captionCont) {
		this.captionCont = captionCont;
	}

	public String getDupcode() {
		return dupcode;
	}

	public void setDupcode(String dupcode) {
		this.dupcode = dupcode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getModDt1() {
		return modDt1;
	}

	public void setModDt1(String modDt1) {
		this.modDt1 = modDt1;
	}

	public String getModDt2() {
		return modDt2;
	}

	public void setModDt2(String modDt2) {
		this.modDt2 = modDt2;
	}

	public String getModDt3() {
		return modDt3;
	}

	public void setModDt3(String modDt3) {
		this.modDt3 = modDt3;
	}

	public String getmListYn() {
		return mListYn;
	}

	public void setmListYn(String mListYn) {
		this.mListYn = mListYn;
	}

	public String getDeptCodeName() {
		return deptCodeName;
	}

	public void setDeptCodeName(String deptCodeName) {
		this.deptCodeName = deptCodeName;
	}

	public String getMcSurvey() {
		return mcSurvey;
	}

	public void setMcSurvey(String mcSurvey) {
		this.mcSurvey = mcSurvey;
	}

	public String getMcSurveyDesc() {
		return mcSurveyDesc;
	}

	public void setMcSurveyDesc(String mcSurveyDesc) {
		this.mcSurveyDesc = mcSurveyDesc;
	}

	public String getClobContTemp() {
		return clobContTemp;
	}

	public void setClobContTemp(String clobContTemp) {
		this.clobContTemp = clobContTemp;
	}

	public String getEditorYn() {
		return editorYn;
	}

	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}

	public String getClobContSearch() {
		return clobContSearch;
	}

	public void setClobContSearch(String clobContSearch) {
		this.clobContSearch = clobContSearch;
	}

	public String getSubContTemp() {
		return subContTemp;
	}

	public void setSubContTemp(String subContTemp) {
		this.subContTemp = subContTemp;
	}

	public String getAnonymityYn() {
		return anonymityYn;
	}

	public void setAnonymityYn(String anonymityYn) {
		this.anonymityYn = anonymityYn;
	}

	public int getAnswerStep() {
		return answerStep;
	}

	public void setAnswerStep(int answerStep) {
		this.answerStep = answerStep;
	}

	public String getStrDomain() {
		return strDomain;
	}

	public void setStrDomain(String strDomain) {
		this.strDomain = strDomain;
	}

	public String getSearchDateFrom() {
		return searchDateFrom;
	}

	public void setSearchDateFrom(String searchDateFrom) {
		this.searchDateFrom = searchDateFrom;
	}

	public String getSearchDateTo() {
		return searchDateTo;
	}

	public void setSearchDateTo(String searchDateTo) {
		this.searchDateTo = searchDateTo;
	}
	public String getClobCont2() {
		return clobCont2;
	}
	public void setClobCont2(String clobCont2) {
		this.clobCont2 = clobCont2;
	}
	public String getSearchDateType() {
		return searchDateType;
	}
	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}
	public String[] getSearchCbIdxArr() {
		return searchCbIdxArr;
	}
	public void setSearchCbIdxArr(String[] searchCbIdxArr) {
		this.searchCbIdxArr = searchCbIdxArr;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getFileViewYn() {
		return fileViewYn;
	}
	public void setFileViewYn(String fileViewYn) {
		this.fileViewYn = fileViewYn;
	}
	public String getContentViewYn() {
		return contentViewYn;
	}
	public void setContentViewYn(String contentViewYn) {
		this.contentViewYn = contentViewYn;
	}
	public String getGubPassword() {
		return gubPassword;
	}
	public void setGubPassword(String gubPassword) {
		this.gubPassword = gubPassword;
	}
	public String getOldGubPassword() {
		return oldGubPassword;
	}
	public void setOldGubPassword(String oldGubPassword) {
		this.oldGubPassword = oldGubPassword;
	}
	public String[] getSearchBcIdxArr() {
		return searchBcIdxArr;
	}
	public void setSearchBcIdxArr(String[] searchBcIdxArr) {
		this.searchBcIdxArr = searchBcIdxArr;
	}
	public String getAnsDeadlineDt() {
		return ansDeadlineDt;
	}
	public void setAnsDeadlineDt(String ansDeadlineDt) {
		this.ansDeadlineDt = ansDeadlineDt;
	}
	public String getCommentYn() {
		return commentYn;
	}
	public void setCommentYn(String commentYn) {
		this.commentYn = commentYn;
	}
	public String getSocialYn() {
		return socialYn;
	}
	public void setSocialYn(String socialYn) {
		this.socialYn = socialYn;
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
	public String getDepth_1() {
		return depth_1;
	}
	public void setDepth_1(String depth_1) {
		this.depth_1 = depth_1;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getSearchYear() {
		return searchYear;
	}
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchTarget() {
		return searchTarget;
	}
	public void setSearchTarget(String searchTarget) {
		this.searchTarget = searchTarget;
	}
	public String getSearchArea() {
		return searchArea;
	}
	public void setSearchArea(String searchArea) {
		this.searchArea = searchArea;
	}
	public String getSearchKey2() {
		return searchKey2;
	}
	public void setSearchKey2(String searchKey2) {
		this.searchKey2 = searchKey2;
	}
	public String getSearchCenter() {
		return searchCenter;
	}
	public void setSearchCenter(String searchCenter) {
		this.searchCenter = searchCenter;
	}
	public String getSearchRegDt() {
		return searchRegDt;
	}
	public void setSearchRegDt(String searchRegDt) {
		this.searchRegDt = searchRegDt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public String getSearchDepth() {
		return searchDepth;
	}
	public void setSearchDepth(String searchDepth) {
		this.searchDepth = searchDepth;
	}
	public String getProMpPstvt() {
		return proMpPstvt;
	}
	public void setProMpPstvt(String proMpPstvt) {
		this.proMpPstvt = proMpPstvt;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getSearchKind() {
		return searchKind;
	}
	public void setSearchKind(String searchKind) {
		this.searchKind = searchKind;
	}
	public String getCheckAll() {
		return checkAll;
	}
	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCourseYn() {
		return courseYn;
	}
	public void setCourseYn(String courseYn) {
		this.courseYn = courseYn;
	}
	public String getLaw() {
		return law;
	}
	public void setLaw(String law) {
		this.law = law;
	}
	public String getSearchLaw() {
		return searchLaw;
	}
	public void setSearchLaw(String searchLaw) {
		this.searchLaw = searchLaw;
	}
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	public String getAbType() {
		return abType;
	}
	public void setAbType(String abType) {
		this.abType = abType;
	}
	public double getPlayTime() {
		return playTime;
	}
	public void setPlayTime(double playTime) {
		this.playTime = playTime;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public double getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(double currentTime) {
		this.currentTime = currentTime;
	}
	
}
